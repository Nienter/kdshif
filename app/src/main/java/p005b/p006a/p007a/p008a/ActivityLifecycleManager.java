package p005b.p006a.p007a.p008a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Set;

/* renamed from: b.a.a.a.a */
public class ActivityLifecycleManager {

    /* renamed from: a */
    private final Application f100a;

    /* renamed from: b */
    private C0416a f101b;

    /* renamed from: b.a.a.a.a$a */
    /* compiled from: ActivityLifecycleManager */
    private static class C0416a {

        /* renamed from: a */
        private final Set<Application.ActivityLifecycleCallbacks> f102a = new HashSet();

        /* renamed from: b */
        private final Application f103b;

        C0416a(Application application) {
            this.f103b = application;
        }

        /* access modifiers changed from: private */
        @TargetApi(14)
        /* renamed from: a */
        public void m84a() {
            for (Application.ActivityLifecycleCallbacks unregisterActivityLifecycleCallbacks : this.f102a) {
                this.f103b.unregisterActivityLifecycleCallbacks(unregisterActivityLifecycleCallbacks);
            }
        }

        /* access modifiers changed from: private */
        @TargetApi(14)
        /* renamed from: a */
        public boolean m87a(final C0418b bVar) {
            if (this.f103b == null) {
                return false;
            }
            C04171 r0 = new Application.ActivityLifecycleCallbacks() {
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    bVar.onActivityCreated(activity, bundle);
                }

                public void onActivityStarted(Activity activity) {
                    bVar.onActivityStarted(activity);
                }

                public void onActivityResumed(Activity activity) {
                    bVar.onActivityResumed(activity);
                }

                public void onActivityPaused(Activity activity) {
                    bVar.onActivityPaused(activity);
                }

                public void onActivityStopped(Activity activity) {
                    bVar.onActivityStopped(activity);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    bVar.onActivitySaveInstanceState(activity, bundle);
                }

                public void onActivityDestroyed(Activity activity) {
                    bVar.onActivityDestroyed(activity);
                }
            };
            this.f103b.registerActivityLifecycleCallbacks(r0);
            this.f102a.add(r0);
            return true;
        }
    }

    /* renamed from: b.a.a.a.a$b */
    /* compiled from: ActivityLifecycleManager */
    public static abstract class C0418b {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    public ActivityLifecycleManager(Context context) {
        this.f100a = (Application) context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 14) {
            this.f101b = new C0416a(this.f100a);
        }
    }

    /* renamed from: a */
    public boolean mo8234a(C0418b bVar) {
        return this.f101b != null && this.f101b.m87a(bVar);
    }

    /* renamed from: a */
    public void mo8233a() {
        if (this.f101b != null) {
            this.f101b.m84a();
        }
    }
}
