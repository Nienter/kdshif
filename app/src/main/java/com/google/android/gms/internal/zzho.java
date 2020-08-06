package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.internal.zzhj;

@zzmb
public class zzho extends zzhj.zza {
    private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzGY;

    public zzho(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzGY = onAppInstallAdLoadedListener;
    }

    public void zza(zzhd zzhd) {
        this.zzGY.onAppInstallAdLoaded(zzb(zzhd));
    }

    /* access modifiers changed from: package-private */
    public zzhe zzb(zzhd zzhd) {
        return new zzhe(zzhd);
    }
}
