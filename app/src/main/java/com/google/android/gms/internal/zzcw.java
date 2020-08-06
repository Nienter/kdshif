package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

class zzcw implements Application.ActivityLifecycleCallbacks {
    private final Application zzwI;
    private final WeakReference<Application.ActivityLifecycleCallbacks> zzxc;

    public interface zza {
        void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks);
    }

    public zzcw(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzxc = new WeakReference<>(activityLifecycleCallbacks);
        this.zzwI = application;
    }

    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        zza(new zza(this) {
            public void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityCreated(activity, bundle);
            }
        });
    }

    public void onActivityDestroyed(final Activity activity) {
        zza(new zza(this) {
            public void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityDestroyed(activity);
            }
        });
    }

    public void onActivityPaused(final Activity activity) {
        zza(new zza(this) {
            public void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityPaused(activity);
            }
        });
    }

    public void onActivityResumed(final Activity activity) {
        zza(new zza(this) {
            public void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityResumed(activity);
            }
        });
    }

    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        zza(new zza(this) {
            public void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivitySaveInstanceState(activity, bundle);
            }
        });
    }

    public void onActivityStarted(final Activity activity) {
        zza(new zza(this) {
            public void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityStarted(activity);
            }
        });
    }

    public void onActivityStopped(final Activity activity) {
        zza(new zza(this) {
            public void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityStopped(activity);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zza(zza zza2) {
        try {
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = (Application.ActivityLifecycleCallbacks) this.zzxc.get();
            if (activityLifecycleCallbacks != null) {
                zza2.zza(activityLifecycleCallbacks);
            } else {
                this.zzwI.unregisterActivityLifecycleCallbacks(this);
            }
        } catch (Exception e) {
            zzpe.zzb("Error while dispatching lifecycle callback.", e);
        }
    }
}
