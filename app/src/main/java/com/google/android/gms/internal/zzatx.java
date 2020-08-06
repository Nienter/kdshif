package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzac;

public final class zzatx {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public final zza zzbuQ;

    public interface zza {
        boolean callServiceStopSelfResult(int i);

        Context getContext();
    }

    public zzatx(zza zza2) {
        this.mContext = zza2.getContext();
        zzac.zzw(this.mContext);
        this.zzbuQ = zza2;
    }

    private zzati zzJt() {
        return zzatp.zzbu(this.mContext).zzJt();
    }

    public static boolean zzj(Context context, boolean z) {
        zzac.zzw(context);
        return zzaue.zzr(context, z ? "com.google.android.gms.measurement.PackageMeasurementService" : "com.google.android.gms.measurement.AppMeasurementService");
    }

    @MainThread
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            zzJt().zzLa().log("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzatq(zzatp.zzbu(this.mContext));
        }
        zzJt().zzLc().zzj("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public void onCreate() {
        zzatp zzbu = zzatp.zzbu(this.mContext);
        zzati zzJt = zzbu.zzJt();
        zzbu.zzJv().zzKk();
        zzJt.zzLg().log("Local AppMeasurementService is starting up");
    }

    @MainThread
    public void onDestroy() {
        zzatp zzbu = zzatp.zzbu(this.mContext);
        zzati zzJt = zzbu.zzJt();
        zzbu.zzJv().zzKk();
        zzJt.zzLg().log("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public void onRebind(Intent intent) {
        if (intent == null) {
            zzJt().zzLa().log("onRebind called with null intent");
            return;
        }
        zzJt().zzLg().zzj("onRebind called. action", intent.getAction());
    }

    @MainThread
    public int onStartCommand(Intent intent, int i, final int i2) {
        final zzatp zzbu = zzatp.zzbu(this.mContext);
        final zzati zzJt = zzbu.zzJt();
        if (intent == null) {
            zzJt.zzLc().log("AppMeasurementService started with null intent");
        } else {
            String action = intent.getAction();
            zzbu.zzJv().zzKk();
            zzJt.zzLg().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                zzbu.zzJs().zzm(new Runnable() {
                    public void run() {
                        zzbu.zzLL();
                        zzbu.zzLG();
                        zzatx.this.mHandler.post(new Runnable() {
                            public void run() {
                                if (zzatx.this.zzbuQ.callServiceStopSelfResult(i2)) {
                                    zzbu.zzJv().zzKk();
                                    zzJt.zzLg().log("Local AppMeasurementService processed last upload request");
                                }
                            }
                        });
                    }
                });
            }
        }
        return 2;
    }

    @MainThread
    public boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzJt().zzLa().log("onUnbind called with null intent");
        } else {
            zzJt().zzLg().zzj("onUnbind called for intent. action", intent.getAction());
        }
        return true;
    }
}
