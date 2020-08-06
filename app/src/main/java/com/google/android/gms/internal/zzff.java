package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzep;

public class zzff extends zzep.zza {
    /* access modifiers changed from: private */
    public zzel zzti;

    public void destroy() {
    }

    public String getMediationAdapterClassName() {
        return null;
    }

    public boolean isLoading() {
        return false;
    }

    public boolean isReady() {
        return false;
    }

    public void pause() {
    }

    public void resume() {
    }

    public void setManualImpressionsEnabled(boolean z) {
    }

    public void setUserId(String str) {
    }

    public void showInterstitial() {
    }

    public void stopLoading() {
    }

    public void zza(zzec zzec) {
    }

    public void zza(zzek zzek) {
    }

    public void zza(zzel zzel) {
        this.zzti = zzel;
    }

    public void zza(zzer zzer) {
    }

    public void zza(zzet zzet) {
    }

    public void zza(zzfn zzfn) {
    }

    public void zza(zzgj zzgj) {
    }

    public void zza(zzkz zzkz) {
    }

    public void zza(zzld zzld, String str) {
    }

    public void zza(zznt zznt) {
    }

    public boolean zzb(zzdy zzdy) {
        zzpy.m2432e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzpx.zzXU.post(new Runnable() {
            public void run() {
                if (zzff.this.zzti != null) {
                    try {
                        zzff.this.zzti.onAdFailedToLoad(1);
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not notify onAdFailedToLoad event.", e);
                    }
                }
            }
        });
        return false;
    }

    public zzd zzbC() {
        return null;
    }

    public zzec zzbD() {
        return null;
    }

    public void zzbF() {
    }

    public zzew zzbG() {
        return null;
    }
}
