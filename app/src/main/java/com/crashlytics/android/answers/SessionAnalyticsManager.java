package com.crashlytics.android.answers;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.crashlytics.android.answers.BackgroundManager;
import com.crashlytics.android.answers.SessionEvent;
import java.util.concurrent.ScheduledExecutorService;
import p005b.p006a.p007a.p008a.ActivityLifecycleManager;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.ExecutorUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p015e.DefaultHttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p016f.FileStoreImpl;
import p005b.p006a.p007a.p008a.p009a.p017g.AnalyticsSettingsData;

class SessionAnalyticsManager implements BackgroundManager.Listener {
    static final String EXECUTOR_SERVICE = "Answers Events Handler";
    static final String ON_CRASH_ERROR_MSG = "onCrash called from main thread!!!";
    final BackgroundManager backgroundManager;
    final AnswersEventsHandler eventsHandler;
    private final long installedAt;
    final ActivityLifecycleManager lifecycleManager;
    final AnswersPreferenceManager preferenceManager;

    public static SessionAnalyticsManager build(Kit iVar, Context context, IdManager pVar, String str, String str2, long j) {
        SessionMetadataCollector sessionMetadataCollector = new SessionMetadataCollector(context, pVar, str, str2);
        AnswersFilesManagerProvider answersFilesManagerProvider = new AnswersFilesManagerProvider(context, new FileStoreImpl(iVar));
        DefaultHttpRequestFactory bVar = new DefaultHttpRequestFactory(Fabric.m456h());
        ActivityLifecycleManager aVar = new ActivityLifecycleManager(context);
        ScheduledExecutorService b = ExecutorUtils.m173b(EXECUTOR_SERVICE);
        BackgroundManager backgroundManager2 = new BackgroundManager(b);
        return new SessionAnalyticsManager(new AnswersEventsHandler(iVar, context, answersFilesManagerProvider, sessionMetadataCollector, bVar, b, new FirebaseAnalyticsApiAdapter(context)), aVar, backgroundManager2, AnswersPreferenceManager.build(context), j);
    }

    SessionAnalyticsManager(AnswersEventsHandler answersEventsHandler, ActivityLifecycleManager aVar, BackgroundManager backgroundManager2, AnswersPreferenceManager answersPreferenceManager, long j) {
        this.eventsHandler = answersEventsHandler;
        this.lifecycleManager = aVar;
        this.backgroundManager = backgroundManager2;
        this.preferenceManager = answersPreferenceManager;
        this.installedAt = j;
    }

    public void enable() {
        this.eventsHandler.enable();
        this.lifecycleManager.mo8234a(new AnswersLifecycleCallbacks(this, this.backgroundManager));
        this.backgroundManager.registerListener(this);
        if (isFirstLaunch()) {
            onInstall(this.installedAt);
            this.preferenceManager.setAnalyticsLaunched();
        }
    }

    public void disable() {
        this.lifecycleManager.mo8233a();
        this.eventsHandler.disable();
    }

    public void onCustom(CustomEvent customEvent) {
        Fabric.m456h().mo8506a(Answers.TAG, "Logged custom event: " + customEvent);
        this.eventsHandler.processEventAsync(SessionEvent.customEventBuilder(customEvent));
    }

    public void onPredefined(PredefinedEvent predefinedEvent) {
        Fabric.m456h().mo8506a(Answers.TAG, "Logged predefined event: " + predefinedEvent);
        this.eventsHandler.processEventAsync(SessionEvent.predefinedEventBuilder(predefinedEvent));
    }

    public void onCrash(String str, String str2) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(ON_CRASH_ERROR_MSG);
        }
        Fabric.m456h().mo8506a(Answers.TAG, "Logged crash");
        this.eventsHandler.processEventSync(SessionEvent.crashEventBuilder(str, str2));
    }

    public void onError(String str) {
    }

    public void onInstall(long j) {
        Fabric.m456h().mo8506a(Answers.TAG, "Logged install");
        this.eventsHandler.processEventAsyncAndFlush(SessionEvent.installEventBuilder(j));
    }

    public void onLifecycle(Activity activity, SessionEvent.Type type) {
        Fabric.m456h().mo8506a(Answers.TAG, "Logged lifecycle event: " + type.name());
        this.eventsHandler.processEventAsync(SessionEvent.lifecycleEventBuilder(type, activity));
    }

    public void onBackground() {
        Fabric.m456h().mo8506a(Answers.TAG, "Flush events when app is backgrounded");
        this.eventsHandler.flushEvents();
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData bVar, String str) {
        this.backgroundManager.setFlushOnBackground(bVar.f277j);
        this.eventsHandler.setAnalyticsSettingsData(bVar, str);
    }

    /* access modifiers changed from: package-private */
    public boolean isFirstLaunch() {
        return !this.preferenceManager.hasAnalyticsLaunched();
    }
}
