package com.google.android.gms.measurement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.p001v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.zzatm;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzatm.zza {
    private zzatm zzbpD;

    private zzatm zzJa() {
        if (this.zzbpD == null) {
            this.zzbpD = new zzatm(this);
        }
        return this.zzbpD;
    }

    @MainThread
    public void doStartService(Context context, Intent intent) {
        startWakefulService(context, intent);
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        zzJa().onReceive(context, intent);
    }
}
