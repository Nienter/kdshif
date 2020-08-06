package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzs;
import java.util.ArrayList;
import java.util.List;

@zzmb
public class zzda {
    private final Object zzxv = new Object();
    private zza zzxw = null;
    private boolean zzxx = false;

    @TargetApi(14)
    static class zza implements Application.ActivityLifecycleCallbacks {
        @Nullable
        private Activity mActivity;
        private Context mContext;
        /* access modifiers changed from: private */
        public List<zzb> mListeners = new ArrayList();
        /* access modifiers changed from: private */
        public final Object zzrN = new Object();
        private boolean zztW = false;
        private Runnable zzxA;
        private long zzxB;
        /* access modifiers changed from: private */
        public boolean zzxy = true;
        /* access modifiers changed from: private */
        public boolean zzxz = false;

        zza() {
        }

        private void setActivity(Activity activity) {
            synchronized (this.zzrN) {
                if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                    this.mActivity = activity;
                }
            }
        }

        @Nullable
        public Activity getActivity() {
            return this.mActivity;
        }

        @Nullable
        public Context getContext() {
            return this.mContext;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        public void onActivityDestroyed(Activity activity) {
            synchronized (this.zzrN) {
                if (this.mActivity != null) {
                    if (this.mActivity.equals(activity)) {
                        this.mActivity = null;
                    }
                }
            }
        }

        public void onActivityPaused(Activity activity) {
            setActivity(activity);
            this.zzxz = true;
            if (this.zzxA != null) {
                zzpi.zzWR.removeCallbacks(this.zzxA);
            }
            Handler handler = zzpi.zzWR;
            C11741 r1 = new Runnable() {
                public void run() {
                    synchronized (zza.this.zzrN) {
                        if (!zza.this.zzxy || !zza.this.zzxz) {
                            zzpe.zzbc("App is still foreground");
                        } else {
                            boolean unused = zza.this.zzxy = false;
                            zzpe.zzbc("App went background");
                            for (zzb zzk : zza.this.mListeners) {
                                try {
                                    zzk.zzk(false);
                                } catch (Exception e) {
                                    zzpe.zzb("OnForegroundStateChangedListener threw exception.", e);
                                }
                            }
                        }
                    }
                }
            };
            this.zzxA = r1;
            handler.postDelayed(r1, this.zzxB);
        }

        public void onActivityResumed(Activity activity) {
            boolean z = false;
            setActivity(activity);
            this.zzxz = false;
            if (!this.zzxy) {
                z = true;
            }
            this.zzxy = true;
            if (this.zzxA != null) {
                zzpi.zzWR.removeCallbacks(this.zzxA);
            }
            synchronized (this.zzrN) {
                if (z) {
                    for (zzb zzk : this.mListeners) {
                        try {
                            zzk.zzk(true);
                        } catch (Exception e) {
                            zzpe.zzb("OnForegroundStateChangedListener threw exception.", e);
                        }
                    }
                } else {
                    zzpe.zzbc("App is still foreground.");
                }
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            setActivity(activity);
        }

        public void onActivityStopped(Activity activity) {
        }

        public void zza(Application application, Context context) {
            if (!this.zztW) {
                application.registerActivityLifecycleCallbacks(this);
                if (context instanceof Activity) {
                    setActivity((Activity) context);
                }
                this.mContext = context;
                this.zzxB = zzfx.zzCu.get().longValue();
                this.zztW = true;
            }
        }

        public void zza(zzb zzb) {
            this.mListeners.add(zzb);
        }
    }

    public interface zzb {
        void zzk(boolean z);
    }

    @Nullable
    public Activity getActivity() {
        Activity activity = null;
        synchronized (this.zzxv) {
            if (zzs.zzyA()) {
                if (this.zzxw != null) {
                    activity = this.zzxw.getActivity();
                }
            }
        }
        return activity;
    }

    @Nullable
    public Context getContext() {
        Context context = null;
        synchronized (this.zzxv) {
            if (zzs.zzyA()) {
                if (this.zzxw != null) {
                    context = this.zzxw.getContext();
                }
            }
        }
        return context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    public void initialize(Context context) {
        synchronized (this.zzxv) {
            if (!this.zzxx) {
                if (zzs.zzyA()) {
                    if (zzfx.zzCt.get().booleanValue()) {
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext == null) {
                            applicationContext = context;
                        }
                        Application application = applicationContext instanceof Application ? (Application) applicationContext : null;
                        if (application == null) {
                            zzpe.zzbe("Can not cast Context to Application");
                            return;
                        }
                        if (this.zzxw == null) {
                            this.zzxw = new zza();
                        }
                        this.zzxw.zza(application, context);
                        this.zzxx = true;
                    }
                }
            }
        }
    }

    public void zza(zzb zzb2) {
        synchronized (this.zzxv) {
            if (zzs.zzyA()) {
                if (zzfx.zzCt.get().booleanValue()) {
                    if (this.zzxw == null) {
                        this.zzxw = new zza();
                    }
                    this.zzxw.zza(zzb2);
                }
            }
        }
    }
}
