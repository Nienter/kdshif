package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.internal.zzaf;
import java.io.IOException;

public class zzbm extends zzby {
    public zzbm(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        super(zzbc, str, str2, zza, i, i2);
    }

    private void zzbj() {
        synchronized (this.zzra) {
            this.zzra.zzbV = (String) this.zzrj.invoke(null, new Object[]{this.zzpC.getApplicationContext()});
        }
    }

    private void zzbk() {
        AdvertisingIdClient zzaZ = this.zzpC.zzaZ();
        if (zzaZ != null) {
            try {
                AdvertisingIdClient.Info info = zzaZ.getInfo();
                String zzr = zzbe.zzr(info.getId());
                if (zzr != null) {
                    synchronized (this.zzra) {
                        this.zzra.zzbV = zzr;
                        this.zzra.zzbX = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                        this.zzra.zzbW = 5;
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbe() {
        if (this.zzpC.zzaO()) {
            zzbk();
        } else {
            zzbj();
        }
    }
}
