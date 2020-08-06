package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

@zzmb
public class zzlg implements InAppPurchaseResult {
    private final zzlc zzPm;

    public zzlg(zzlc zzlc) {
        this.zzPm = zzlc;
    }

    public void finishPurchase() {
        try {
            this.zzPm.finishPurchase();
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward finishPurchase to InAppPurchaseResult", e);
        }
    }

    public String getProductId() {
        try {
            return this.zzPm.getProductId();
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward getProductId to InAppPurchaseResult", e);
            return null;
        }
    }

    public Intent getPurchaseData() {
        try {
            return this.zzPm.getPurchaseData();
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return null;
        }
    }

    public int getResultCode() {
        try {
            return this.zzPm.getResultCode();
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return 0;
        }
    }

    public boolean isVerified() {
        try {
            return this.zzPm.isVerified();
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward isVerified to InAppPurchaseResult", e);
            return false;
        }
    }
}
