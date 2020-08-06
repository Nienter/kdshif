package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzgf;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzqp;

@zzmb
public abstract class zzk {
    @Nullable
    public abstract zzj zza(Context context, zzqp zzqp, int i, boolean z, zzgf zzgf);

    /* access modifiers changed from: protected */
    public boolean zzh(zzqp zzqp) {
        return zzqp.zzbD().zzzl;
    }

    /* access modifiers changed from: protected */
    public boolean zzp(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return zzs.zzyA() && (applicationInfo == null || applicationInfo.targetSdkVersion >= 11);
    }
}
