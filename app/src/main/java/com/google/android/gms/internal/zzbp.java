package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;

public class zzbp extends zzby {
    private long zzrc = -1;

    public zzbp(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        this.zzra.zzbk = -1L;
        if (this.zzrc == -1) {
            this.zzrc = (long) ((Integer) this.zzrj.invoke(null, new Object[]{this.zzpC.getContext()})).intValue();
        }
        synchronized (this.zzra) {
            this.zzra.zzbk = Long.valueOf(this.zzrc);
        }
    }
}
