package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;

public class zzbg extends zzby {
    public zzbg(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        synchronized (this.zzra) {
            this.zzra.zzbb = -1L;
            this.zzra.zzbb = (Long) this.zzrj.invoke(null, new Object[]{this.zzpC.getContext()});
        }
    }
}
