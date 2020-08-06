package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.zze;

@zzmb
public class zzgh implements CustomRenderedAd {
    private final zzgi zzFz;

    public zzgh(zzgi zzgi) {
        this.zzFz = zzgi;
    }

    public String getBaseUrl() {
        try {
            return this.zzFz.zzfB();
        } catch (RemoteException e) {
            zzpy.zzc("Could not delegate getBaseURL to CustomRenderedAd", e);
            return null;
        }
    }

    public String getContent() {
        try {
            return this.zzFz.getContent();
        } catch (RemoteException e) {
            zzpy.zzc("Could not delegate getContent to CustomRenderedAd", e);
            return null;
        }
    }

    public void onAdRendered(View view) {
        try {
            this.zzFz.zzi(view != null ? zze.zzA(view) : null);
        } catch (RemoteException e) {
            zzpy.zzc("Could not delegate onAdRendered to CustomRenderedAd", e);
        }
    }

    public void recordClick() {
        try {
            this.zzFz.recordClick();
        } catch (RemoteException e) {
            zzpy.zzc("Could not delegate recordClick to CustomRenderedAd", e);
        }
    }

    public void recordImpression() {
        try {
            this.zzFz.recordImpression();
        } catch (RemoteException e) {
            zzpy.zzc("Could not delegate recordImpression to CustomRenderedAd", e);
        }
    }
}
