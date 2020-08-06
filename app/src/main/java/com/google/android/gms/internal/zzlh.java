package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchase;

@zzmb
public class zzlh implements InAppPurchase {
    private final zzky zzPa;

    public zzlh(zzky zzky) {
        this.zzPa = zzky;
    }

    public String getProductId() {
        try {
            return this.zzPa.getProductId();
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward getProductId to InAppPurchase", e);
            return null;
        }
    }

    public void recordPlayBillingResolution(int i) {
        try {
            this.zzPa.recordPlayBillingResolution(i);
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward recordPlayBillingResolution to InAppPurchase", e);
        }
    }

    public void recordResolution(int i) {
        try {
            this.zzPa.recordResolution(i);
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward recordResolution to InAppPurchase", e);
        }
    }
}
