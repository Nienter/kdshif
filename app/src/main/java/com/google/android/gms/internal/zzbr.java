package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;
import java.util.List;

public class zzbr extends zzby {
    private List<Long> zzrd = null;

    public zzbr(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        this.zzra.zzbw = -1L;
        this.zzra.zzbx = -1L;
        if (this.zzrd == null) {
            this.zzrd = (List) this.zzrj.invoke(null, new Object[]{this.zzpC.getContext()});
        }
        if (this.zzrd != null && this.zzrd.size() == 2) {
            synchronized (this.zzra) {
                this.zzra.zzbw = Long.valueOf(this.zzrd.get(0).longValue());
                this.zzra.zzbx = Long.valueOf(this.zzrd.get(1).longValue());
            }
        }
    }
}
