package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.internal.zzhm;

@zzmb
public class zzhr extends zzhm.zza {
    private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzHb;

    public zzhr(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzHb = onCustomTemplateAdLoadedListener;
    }

    public void zza(zzhh zzhh) {
        this.zzHb.onCustomTemplateAdLoaded(new zzhi(zzhh));
    }
}
