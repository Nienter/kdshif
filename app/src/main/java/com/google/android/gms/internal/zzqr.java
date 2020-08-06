package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzt;
import com.google.android.gms.ads.internal.zzv;

@zzmb
public class zzqr {
    public zzqp zza(Context context, zzec zzec, boolean z, boolean z2, @Nullable zzav zzav, zzqa zzqa) {
        return zza(context, zzec, z, z2, zzav, zzqa, null, null, null);
    }

    public zzqp zza(Context context, zzec zzec, boolean z, boolean z2, @Nullable zzav zzav, zzqa zzqa, zzgf zzgf, zzt zzt, zzd zzd) {
        zzqs zzqs = new zzqs(zzqt.zzb(context, zzec, z, z2, zzav, zzqa, zzgf, zzt, zzd));
        zzqs.setWebViewClient(zzv.zzcL().zzb((zzqp) zzqs, z2));
        zzqs.setWebChromeClient(zzv.zzcL().zzn(zzqs));
        return zzqs;
    }
}
