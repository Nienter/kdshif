package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzdr;

@zzmb
public class zzdn extends zzf<zzdr> {
    zzdn(Context context, Looper looper, zzf.zzb zzb, zzf.zzc zzc) {
        super(context, looper, 123, zzb, zzc, null);
    }

    /* access modifiers changed from: protected */
    public String zzeu() {
        return "com.google.android.gms.ads.service.CACHE";
    }

    /* access modifiers changed from: protected */
    public String zzev() {
        return "com.google.android.gms.ads.internal.cache.ICacheService";
    }

    public zzdr zzew() {
        return (zzdr) super.zzwW();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzg */
    public zzdr zzh(IBinder iBinder) {
        return zzdr.zza.zzi(iBinder);
    }
}
