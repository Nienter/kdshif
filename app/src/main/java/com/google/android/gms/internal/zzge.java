package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@zzmb
public class zzge {
    private final Map<String, zzgd> zzFq = new HashMap();
    @Nullable
    private final zzgf zzsr;

    public zzge(@Nullable zzgf zzgf) {
        this.zzsr = zzgf;
    }

    public void zza(String str, zzgd zzgd) {
        this.zzFq.put(str, zzgd);
    }

    public void zza(String str, String str2, long j) {
        zzgb.zza(this.zzsr, this.zzFq.get(str2), j, str);
        this.zzFq.put(str, zzgb.zza(this.zzsr, j));
    }

    @Nullable
    public zzgf zzfv() {
        return this.zzsr;
    }
}
