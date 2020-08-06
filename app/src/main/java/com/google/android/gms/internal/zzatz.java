package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;

class zzatz {
    private long zzKH;
    private final zze zzuI;

    public zzatz(zze zze) {
        zzac.zzw(zze);
        this.zzuI = zze;
    }

    public void clear() {
        this.zzKH = 0;
    }

    public void start() {
        this.zzKH = this.zzuI.elapsedRealtime();
    }

    public boolean zzz(long j) {
        return this.zzKH == 0 || this.zzuI.elapsedRealtime() - this.zzKH >= j;
    }
}
