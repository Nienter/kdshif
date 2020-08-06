package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.internal.zzacd;

public class zzacb extends zzl<zzacd> {
    public zzacb(Context context, Looper looper, zzg zzg, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 39, zzg, connectionCallbacks, onConnectionFailedListener);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzbz */
    public zzacd zzh(IBinder iBinder) {
        return zzacd.zza.zzbB(iBinder);
    }

    public String zzeu() {
        return "com.google.android.gms.common.service.START";
    }

    /* access modifiers changed from: protected */
    public String zzev() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }
}
