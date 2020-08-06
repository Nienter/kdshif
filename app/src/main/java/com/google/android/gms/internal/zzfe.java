package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.internal.zzen;

public class zzfe extends zzen.zza {
    /* access modifiers changed from: private */
    public zzel zzti;

    private class zza extends zzem.zza {
        private zza() {
        }

        public String getMediationAdapterClassName() {
            return null;
        }

        public boolean isLoading() {
            return false;
        }

        public void zzf(zzdy zzdy) {
            zzpy.m2432e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    if (zzfe.this.zzti != null) {
                        try {
                            zzfe.this.zzti.onAdFailedToLoad(1);
                        } catch (RemoteException e) {
                            zzpy.zzc("Could not notify onAdFailedToLoad event.", e);
                        }
                    }
                }
            });
        }
    }

    public void zza(zzgw zzgw) {
    }

    public void zza(zzhj zzhj) {
    }

    public void zza(zzhk zzhk) {
    }

    public void zza(String str, zzhm zzhm, zzhl zzhl) {
    }

    public void zzb(zzel zzel) {
        this.zzti = zzel;
    }

    public void zzb(zzet zzet) {
    }

    public zzem zzci() {
        return new zza();
    }
}
