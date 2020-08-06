package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zznr;

public class zzfi extends zznr.zza {
    /* access modifiers changed from: private */
    public zznt zzAp;

    public void destroy() {
    }

    public boolean isLoaded() {
        return false;
    }

    public void pause() {
    }

    public void resume() {
    }

    public void setUserId(String str) {
    }

    public void show() {
    }

    public void zza(zznt zznt) {
        this.zzAp = zznt;
    }

    public void zza(zznx zznx) {
        zzpy.m2432e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzpx.zzXU.post(new Runnable() {
            public void run() {
                if (zzfi.this.zzAp != null) {
                    try {
                        zzfi.this.zzAp.onRewardedVideoAdFailedToLoad(1);
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", e);
                    }
                }
            }
        });
    }

    public void zzf(zzd zzd) {
    }

    public void zzg(zzd zzd) {
    }

    public void zzh(zzd zzd) {
    }
}
