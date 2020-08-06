package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.zzld;

@zzmb
public final class zzli extends zzld.zza {
    private final PlayStorePurchaseListener zzAc;

    public zzli(PlayStorePurchaseListener playStorePurchaseListener) {
        this.zzAc = playStorePurchaseListener;
    }

    public boolean isValidPurchase(String str) {
        return this.zzAc.isValidPurchase(str);
    }

    public void zza(zzlc zzlc) {
        this.zzAc.onInAppPurchaseFinished(new zzlg(zzlc));
    }
}
