package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.internal.zzzn;

public class zzzk extends zzl<zzzn> {
    public zzzk(Context context, Looper looper, zzg zzg, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, zzg, connectionCallbacks, onConnectionFailedListener);
    }

    public void zza(zzzm zzzm, zzzh zzzh) {
        ((zzzn) zzwW()).zza(zzzm, zzzh);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzbm */
    public zzzn zzh(IBinder iBinder) {
        return zzzn.zza.zzbo(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeu() {
        return "com.google.android.gms.clearcut.service.START";
    }

    /* access modifiers changed from: protected */
    public String zzev() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}
