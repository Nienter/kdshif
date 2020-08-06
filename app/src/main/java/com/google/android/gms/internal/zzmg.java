package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzmq;

@zzmb
public class zzmg extends zzf<zzmq> {
    final int zzRb;

    public zzmg(Context context, Looper looper, zzf.zzb zzb, zzf.zzc zzc, int i) {
        super(context, looper, 8, zzb, zzc, null);
        this.zzRb = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzad */
    public zzmq zzh(IBinder iBinder) {
        return zzmq.zza.zzae(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeu() {
        return "com.google.android.gms.ads.service.START";
    }

    /* access modifiers changed from: protected */
    public String zzev() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }

    public zzmq zzjb() {
        return (zzmq) super.zzwW();
    }
}
