package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzgf;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzqp;

@zzmb
public class zzo extends zzk {
    @Nullable
    public zzj zza(Context context, zzqp zzqp, int i, boolean z, zzgf zzgf) {
        if (!zzp(context)) {
            return null;
        }
        return new zzd(context, z, zzh(zzqp), new zzz(context, zzqp.zzkY(), zzqp.getRequestId(), zzgf, zzqp.zzle()));
    }
}
