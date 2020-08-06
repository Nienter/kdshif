package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Activity;
import java.util.concurrent.ExecutorService;
import p005b.p006a.p007a.p008a.ActivityLifecycleManager;

@TargetApi(14)
class ActivityLifecycleCheckForUpdatesController extends AbstractCheckForUpdatesController {
    private final ActivityLifecycleManager.C0418b callbacks = new ActivityLifecycleManager.C0418b() {
        public void onActivityStarted(Activity activity) {
            if (ActivityLifecycleCheckForUpdatesController.this.signalExternallyReady()) {
                ActivityLifecycleCheckForUpdatesController.this.executorService.submit(new Runnable() {
                    public void run() {
                        ActivityLifecycleCheckForUpdatesController.this.checkForUpdates();
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public final ExecutorService executorService;

    public ActivityLifecycleCheckForUpdatesController(ActivityLifecycleManager aVar, ExecutorService executorService2) {
        this.executorService = executorService2;
        aVar.mo8234a(this.callbacks);
    }

    public boolean isActivityLifecycleTriggered() {
        return true;
    }
}
