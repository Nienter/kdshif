package com.google.android.gms.internal;

import android.support.annotation.Nullable;

@zzmb
public class zzgb {
    @Nullable
    public static zzgd zza(@Nullable zzgf zzgf, long j) {
        if (zzgf == null) {
            return null;
        }
        return zzgf.zzc(j);
    }

    public static boolean zza(@Nullable zzgf zzgf, @Nullable zzgd zzgd, long j, String... strArr) {
        if (zzgf == null || zzgd == null) {
            return false;
        }
        return zzgf.zza(zzgd, j, strArr);
    }

    public static boolean zza(@Nullable zzgf zzgf, @Nullable zzgd zzgd, String... strArr) {
        if (zzgf == null || zzgd == null) {
            return false;
        }
        return zzgf.zza(zzgd, strArr);
    }

    @Nullable
    public static zzgd zzb(@Nullable zzgf zzgf) {
        if (zzgf == null) {
            return null;
        }
        return zzgf.zzfw();
    }
}
