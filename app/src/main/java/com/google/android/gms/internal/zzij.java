package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzv;

@zzmb
public class zzij extends zzpd {
    final zzqp zzGt;
    final zzil zzHU;
    private final String zzHV;

    zzij(zzqp zzqp, zzil zzil, String str) {
        this.zzGt = zzqp;
        this.zzHU = zzil;
        this.zzHV = str;
        zzv.zzdg().zza(this);
    }

    public void onStop() {
        this.zzHU.abort();
    }

    public void zzcm() {
        try {
            this.zzHU.zzad(this.zzHV);
        } finally {
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zzv.zzdg().zzb(zzij.this);
                }
            });
        }
    }
}
