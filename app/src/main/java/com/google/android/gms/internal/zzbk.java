package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;

public class zzbk extends zzby {
    private static volatile Long zzbH = null;
    private static final Object zzqW = new Object();

    public zzbk(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        if (zzbH == null) {
            synchronized (zzqW) {
                if (zzbH == null) {
                    zzbH = (Long) this.zzrj.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzra) {
            this.zzra.zzbH = zzbH;
        }
    }
}
