package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;

public class zzbj extends zzby {
    public zzbj(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        this.zzra.zzbd = -1L;
        this.zzra.zzbe = -1L;
        int[] iArr = (int[]) this.zzrj.invoke(null, new Object[]{this.zzpC.getContext()});
        synchronized (this.zzra) {
            this.zzra.zzbd = Long.valueOf((long) iArr[0]);
            this.zzra.zzbe = Long.valueOf((long) iArr[1]);
        }
    }
}
