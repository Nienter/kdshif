package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzapz;

public class zzapu extends zzf<zzapz> {
    public zzapu(Context context, Looper looper, zzf.zzb zzb, zzf.zzc zzc) {
        super(context, looper, 116, zzb, zzc, null);
    }

    public zzapz zzFX() {
        return (zzapz) super.zzwW();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzcT */
    public zzapz zzh(IBinder iBinder) {
        return zzapz.zza.zzcU(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeu() {
        return "com.google.android.gms.gass.START";
    }

    /* access modifiers changed from: protected */
    public String zzev() {
        return "com.google.android.gms.gass.internal.IGassService";
    }
}
