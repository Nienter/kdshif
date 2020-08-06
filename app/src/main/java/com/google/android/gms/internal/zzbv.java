package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;

public class zzbv extends zzby {
    public zzbv(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        this.zzra.zzbJ = 2;
        boolean booleanValue = ((Boolean) this.zzrj.invoke(null, new Object[]{this.zzpC.getApplicationContext()})).booleanValue();
        synchronized (this.zzra) {
            if (booleanValue) {
                this.zzra.zzbJ = 1;
            } else {
                this.zzra.zzbJ = 0;
            }
        }
    }
}
