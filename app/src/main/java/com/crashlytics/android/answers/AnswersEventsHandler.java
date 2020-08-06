package com.crashlytics.android.answers;

import android.content.Context;
import com.crashlytics.android.answers.SessionEvent;
import java.util.concurrent.ScheduledExecutorService;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p014d.EventsStorageListener;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p017g.AnalyticsSettingsData;

class AnswersEventsHandler implements EventsStorageListener {
    /* access modifiers changed from: private */
    public final Context context;
    final ScheduledExecutorService executor;
    /* access modifiers changed from: private */
    public final AnswersFilesManagerProvider filesManagerProvider;
    /* access modifiers changed from: private */
    public final FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter;
    /* access modifiers changed from: private */
    public final Kit kit;
    /* access modifiers changed from: private */
    public final SessionMetadataCollector metadataCollector;
    /* access modifiers changed from: private */
    public final HttpRequestFactory requestFactory;
    SessionAnalyticsManagerStrategy strategy = new DisabledSessionAnalyticsManagerStrategy();

    public AnswersEventsHandler(Kit iVar, Context context2, AnswersFilesManagerProvider answersFilesManagerProvider, SessionMetadataCollector sessionMetadataCollector, HttpRequestFactory eVar, ScheduledExecutorService scheduledExecutorService, FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter2) {
        this.kit = iVar;
        this.context = context2;
        this.filesManagerProvider = answersFilesManagerProvider;
        this.metadataCollector = sessionMetadataCollector;
        this.requestFactory = eVar;
        this.executor = scheduledExecutorService;
        this.firebaseAnalyticsApiAdapter = firebaseAnalyticsApiAdapter2;
    }

    public void processEventAsync(SessionEvent.Builder builder) {
        processEvent(builder, false, false);
    }

    public void processEventAsyncAndFlush(SessionEvent.Builder builder) {
        processEvent(builder, false, true);
    }

    public void processEventSync(SessionEvent.Builder builder) {
        processEvent(builder, true, false);
    }

    public void setAnalyticsSettingsData(final AnalyticsSettingsData bVar, final String str) {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.setAnalyticsSettingsData(bVar, str);
                } catch (Exception e) {
                    Fabric.m456h().mo8516e(Answers.TAG, "Failed to set analytics settings data", e);
                }
            }
        });
    }

    public void disable() {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    SessionAnalyticsManagerStrategy sessionAnalyticsManagerStrategy = AnswersEventsHandler.this.strategy;
                    AnswersEventsHandler.this.strategy = new DisabledSessionAnalyticsManagerStrategy();
                    sessionAnalyticsManagerStrategy.deleteAllEvents();
                } catch (Exception e) {
                    Fabric.m456h().mo8516e(Answers.TAG, "Failed to disable events", e);
                }
            }
        });
    }

    public void onRollOver(String str) {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.sendEvents();
                } catch (Exception e) {
                    Fabric.m456h().mo8516e(Answers.TAG, "Failed to send events files", e);
                }
            }
        });
    }

    public void enable() {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    SessionEventMetadata metadata = AnswersEventsHandler.this.metadataCollector.getMetadata();
                    SessionAnalyticsFilesManager analyticsFilesManager = AnswersEventsHandler.this.filesManagerProvider.getAnalyticsFilesManager();
                    analyticsFilesManager.registerRollOverListener(AnswersEventsHandler.this);
                    AnswersEventsHandler.this.strategy = new EnabledSessionAnalyticsManagerStrategy(AnswersEventsHandler.this.kit, AnswersEventsHandler.this.context, AnswersEventsHandler.this.executor, analyticsFilesManager, AnswersEventsHandler.this.requestFactory, metadata, AnswersEventsHandler.this.firebaseAnalyticsApiAdapter);
                } catch (Exception e) {
                    Fabric.m456h().mo8516e(Answers.TAG, "Failed to enable events", e);
                }
            }
        });
    }

    public void flushEvents() {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.rollFileOver();
                } catch (Exception e) {
                    Fabric.m456h().mo8516e(Answers.TAG, "Failed to flush events", e);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void processEvent(final SessionEvent.Builder builder, boolean z, final boolean z2) {
        C06606 r0 = new Runnable() {
            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.processEvent(builder);
                    if (z2) {
                        AnswersEventsHandler.this.strategy.rollFileOver();
                    }
                } catch (Exception e) {
                    Fabric.m456h().mo8516e(Answers.TAG, "Failed to process event", e);
                }
            }
        };
        if (z) {
            executeSync(r0);
        } else {
            executeAsync(r0);
        }
    }

    private void executeSync(Runnable runnable) {
        try {
            this.executor.submit(runnable).get();
        } catch (Exception e) {
            Fabric.m456h().mo8516e(Answers.TAG, "Failed to run events task", e);
        }
    }

    private void executeAsync(Runnable runnable) {
        try {
            this.executor.submit(runnable);
        } catch (Exception e) {
            Fabric.m456h().mo8516e(Answers.TAG, "Failed to submit events task", e);
        }
    }
}
