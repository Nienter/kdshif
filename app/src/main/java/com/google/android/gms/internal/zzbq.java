package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;

public class zzbq extends zzby {
    private static volatile String zzba = null;
    private static final Object zzqW = new Object();

    public zzbq(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        this.zzra.zzba = "E";
        if (zzba == null) {
            synchronized (zzqW) {
                if (zzba == null) {
                    zzba = (String) this.zzrj.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzra) {
            this.zzra.zzba = zzba;
        }
    }
}
