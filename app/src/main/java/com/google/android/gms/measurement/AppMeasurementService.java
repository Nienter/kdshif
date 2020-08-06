package com.google.android.gms.measurement;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.zzatx;

public final class AppMeasurementService extends Service implements zzatx.zza {
    private zzatx zzbpE;

    private zzatx zzJb() {
        if (this.zzbpE == null) {
            this.zzbpE = new zzatx(this);
        }
        return this.zzbpE;
    }

    public boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public Context getContext() {
        return this;
    }

    @MainThread
    public IBinder onBind(Intent intent) {
        return zzJb().onBind(intent);
    }

    @MainThread
    public void onCreate() {
        super.onCreate();
        zzJb().onCreate();
    }

    @MainThread
    public void onDestroy() {
        zzJb().onDestroy();
        super.onDestroy();
    }

    @MainThread
    public void onRebind(Intent intent) {
        zzJb().onRebind(intent);
    }

    @MainThread
    public int onStartCommand(Intent intent, int i, int i2) {
        zzJb().onStartCommand(intent, i, i2);
        AppMeasurementReceiver.completeWakefulIntent(intent);
        return 2;
    }

    @MainThread
    public boolean onUnbind(Intent intent) {
        return zzJb().onUnbind(intent);
    }
}
