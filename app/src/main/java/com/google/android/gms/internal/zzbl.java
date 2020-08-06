package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;

public class zzbl extends zzby {
    private long startTime;

    public zzbl(zzbc zzbc, String str, String str2, zzaf.zza zza, long j, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
        this.startTime = j;
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        long longValue = ((Long) this.zzrj.invoke(null, new Object[0])).longValue();
        synchronized (this.zzra) {
            this.zzra.zzbZ = Long.valueOf(longValue);
            if (this.startTime != 0) {
                this.zzra.zzbp = Long.valueOf(longValue - this.startTime);
                this.zzra.zzbu = Long.valueOf(this.startTime);
            }
        }
    }
}
