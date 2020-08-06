package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.internal.zzaf;

public class zzbw extends zzby {
    private final View zzrg;

    public zzbw(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2, View view) {
        super(zzbc, str, str2, zza, i, i2);
        this.zzrg = view;
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        if (this.zzrg != null) {
            zzbf zzbf = new zzbf((String) this.zzrj.invoke(null, new Object[]{this.zzrg}));
            synchronized (this.zzra) {
                this.zzra.zzbT = new zzaf.zza.zzb();
                this.zzra.zzbT.zzcm = zzbf.zzqU;
            }
        }
    }
}
