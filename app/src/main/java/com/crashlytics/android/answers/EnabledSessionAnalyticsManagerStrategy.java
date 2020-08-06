package com.crashlytics.android.answers;

import android.content.Context;
import com.crashlytics.android.answers.SessionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.ApiKey;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p014d.FilesSender;
import p005b.p006a.p007a.p008a.p009a.p014d.TimeBasedFileRollOverRunnable;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p017g.AnalyticsSettingsData;

class EnabledSessionAnalyticsManagerStrategy implements SessionAnalyticsManagerStrategy {
    static final int UNDEFINED_ROLLOVER_INTERVAL_SECONDS = -1;
    ApiKey apiKey = new ApiKey();
    private final Context context;
    boolean customEventsEnabled = true;
    EventFilter eventFilter = new KeepAllEventFilter();
    private final ScheduledExecutorService executorService;
    private final SessionAnalyticsFilesManager filesManager;
    FilesSender filesSender;
    private final FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter;
    boolean forwardToFirebaseAnalyticsEnabled = false;
    private final HttpRequestFactory httpRequestFactory;
    boolean includePurchaseEventsInForwardedEvents = false;
    private final Kit kit;
    final SessionEventMetadata metadata;
    boolean predefinedEventsEnabled = true;
    private final AtomicReference<ScheduledFuture<?>> rolloverFutureRef = new AtomicReference<>();
    volatile int rolloverIntervalSeconds = -1;

    public EnabledSessionAnalyticsManagerStrategy(Kit iVar, Context context2, ScheduledExecutorService scheduledExecutorService, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, HttpRequestFactory eVar, SessionEventMetadata sessionEventMetadata, FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter2) {
        this.kit = iVar;
        this.context = context2;
        this.executorService = scheduledExecutorService;
        this.filesManager = sessionAnalyticsFilesManager;
        this.httpRequestFactory = eVar;
        this.metadata = sessionEventMetadata;
        this.firebaseAnalyticsApiAdapter = firebaseAnalyticsApiAdapter2;
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData bVar, String str) {
        this.filesSender = AnswersRetryFilesSender.build(new SessionAnalyticsFilesSender(this.kit, str, bVar.f268a, this.httpRequestFactory, this.apiKey.mo8272a(this.context)));
        this.filesManager.setAnalyticsSettingsData(bVar);
        this.forwardToFirebaseAnalyticsEnabled = bVar.f273f;
        this.includePurchaseEventsInForwardedEvents = bVar.f274g;
        Fabric.m456h().mo8506a(Answers.TAG, "Firebase analytics forwarding " + (this.forwardToFirebaseAnalyticsEnabled ? "enabled" : "disabled"));
        Fabric.m456h().mo8506a(Answers.TAG, "Firebase analytics including purchase events " + (this.includePurchaseEventsInForwardedEvents ? "enabled" : "disabled"));
        this.customEventsEnabled = bVar.f275h;
        Fabric.m456h().mo8506a(Answers.TAG, "Custom event tracking " + (this.customEventsEnabled ? "enabled" : "disabled"));
        this.predefinedEventsEnabled = bVar.f276i;
        Fabric.m456h().mo8506a(Answers.TAG, "Predefined event tracking " + (this.predefinedEventsEnabled ? "enabled" : "disabled"));
        if (bVar.f278k > 1) {
            Fabric.m456h().mo8506a(Answers.TAG, "Event sampling enabled");
            this.eventFilter = new SamplingEventFilter(bVar.f278k);
        }
        this.rolloverIntervalSeconds = bVar.f269b;
        scheduleTimeBasedFileRollOver(0, (long) this.rolloverIntervalSeconds);
    }

    public void processEvent(SessionEvent.Builder builder) {
        SessionEvent build = builder.build(this.metadata);
        if (!this.customEventsEnabled && SessionEvent.Type.CUSTOM.equals(build.type)) {
            Fabric.m456h().mo8506a(Answers.TAG, "Custom events tracking disabled - skipping event: " + build);
        } else if (!this.predefinedEventsEnabled && SessionEvent.Type.PREDEFINED.equals(build.type)) {
            Fabric.m456h().mo8506a(Answers.TAG, "Predefined events tracking disabled - skipping event: " + build);
        } else if (this.eventFilter.skipEvent(build)) {
            Fabric.m456h().mo8506a(Answers.TAG, "Skipping filtered event: " + build);
        } else {
            try {
                this.filesManager.writeEvent(build);
            } catch (IOException e) {
                Fabric.m456h().mo8516e(Answers.TAG, "Failed to write event: " + build, e);
            }
            scheduleTimeBasedRollOverIfNeeded();
            boolean z = SessionEvent.Type.CUSTOM.equals(build.type) || SessionEvent.Type.PREDEFINED.equals(build.type);
            boolean equals = "purchase".equals(build.predefinedType);
            if (this.forwardToFirebaseAnalyticsEnabled && z) {
                if (!equals || this.includePurchaseEventsInForwardedEvents) {
                    try {
                        this.firebaseAnalyticsApiAdapter.processEvent(build);
                    } catch (Exception e2) {
                        Fabric.m456h().mo8516e(Answers.TAG, "Failed to map event to Firebase: " + build, e2);
                    }
                }
            }
        }
    }

    public void scheduleTimeBasedRollOverIfNeeded() {
        if (this.rolloverIntervalSeconds != -1) {
            scheduleTimeBasedFileRollOver((long) this.rolloverIntervalSeconds, (long) this.rolloverIntervalSeconds);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    public void sendEvents() {
        int i;
        Exception e;
        if (this.filesSender == null) {
            CommonUtils.m137a(this.context, "skipping files send because we don't yet know the target endpoint");
            return;
        }
        CommonUtils.m137a(this.context, "Sending all files");
        List<File> batchOfFilesToSend = this.filesManager.getBatchOfFilesToSend();
        int i2 = 0;
        while (batchOfFilesToSend.size() > 0) {
            try {
                CommonUtils.m137a(this.context, String.format(Locale.US, "attempt to send batch of %d files", new Object[]{Integer.valueOf(batchOfFilesToSend.size())}));
                boolean send = this.filesSender.send(batchOfFilesToSend);
                if (send) {
                    i = batchOfFilesToSend.size() + i2;
                    try {
                        this.filesManager.deleteSentFiles(batchOfFilesToSend);
                        i2 = i;
                    } catch (Exception e2) {
                        e = e2;
                        CommonUtils.m138a(this.context, "Failed to send batch of analytics files to server: " + e.getMessage(), (Throwable) e);
                        i2 = i;
                        if (i2 == 0) {
                        }
                    }
                }
                if (!send) {
                    break;
                }
                batchOfFilesToSend = this.filesManager.getBatchOfFilesToSend();
            } catch (Exception e3) {
                Exception exc = e3;
                i = i2;
                e = exc;
                CommonUtils.m138a(this.context, "Failed to send batch of analytics files to server: " + e.getMessage(), (Throwable) e);
                i2 = i;
                if (i2 == 0) {
                }
            }
        }
        if (i2 == 0) {
            this.filesManager.deleteOldestInRollOverIfOverMax();
        }
    }

    public void cancelTimeBasedFileRollOver() {
        if (this.rolloverFutureRef.get() != null) {
            CommonUtils.m137a(this.context, "Cancelling time-based rollover because no events are currently being generated.");
            this.rolloverFutureRef.get().cancel(false);
            this.rolloverFutureRef.set(null);
        }
    }

    public void deleteAllEvents() {
        this.filesManager.deleteAllEventsFiles();
    }

    public boolean rollFileOver() {
        try {
            return this.filesManager.rollFileOver();
        } catch (IOException e) {
            CommonUtils.m138a(this.context, "Failed to roll file over.", (Throwable) e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void scheduleTimeBasedFileRollOver(long j, long j2) {
        if (this.rolloverFutureRef.get() == null) {
            TimeBasedFileRollOverRunnable iVar = new TimeBasedFileRollOverRunnable(this.context, this);
            CommonUtils.m137a(this.context, "Scheduling time based file roll over every " + j2 + " seconds");
            try {
                this.rolloverFutureRef.set(this.executorService.scheduleAtFixedRate(iVar, j, j2, TimeUnit.SECONDS));
            } catch (RejectedExecutionException e) {
                CommonUtils.m138a(this.context, "Failed to schedule time based file roll over", (Throwable) e);
            }
        }
    }
}
