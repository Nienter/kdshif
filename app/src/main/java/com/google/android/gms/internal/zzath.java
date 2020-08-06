package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzate;

public class zzath extends zzf<zzate> {
    public zzath(Context context, Looper looper, zzf.zzb zzb, zzf.zzc zzc) {
        super(context, looper, 93, zzb, zzc, null);
    }

    /* renamed from: zzes */
    public zzate zzh(IBinder iBinder) {
        return zzate.zza.zzer(iBinder);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String zzeu() {
        return "com.google.android.gms.measurement.START";
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String zzev() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }
}
