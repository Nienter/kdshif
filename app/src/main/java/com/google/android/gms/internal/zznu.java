package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

@zzmb
public class zznu implements RewardItem {
    private final zznq zzUJ;

    public zznu(zznq zznq) {
        this.zzUJ = zznq;
    }

    public int getAmount() {
        int i = 0;
        if (this.zzUJ == null) {
            return i;
        }
        try {
            return this.zzUJ.getAmount();
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward getAmount to RewardItem", e);
            return i;
        }
    }

    public String getType() {
        String str = null;
        if (this.zzUJ == null) {
            return str;
        }
        try {
            return this.zzUJ.getType();
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward getType to RewardItem", e);
            return str;
        }
    }
}
