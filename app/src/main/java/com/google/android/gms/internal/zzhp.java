package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.internal.zzhk;

@zzmb
public class zzhp extends zzhk.zza {
    private final NativeContentAd.OnContentAdLoadedListener zzGZ;

    public zzhp(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzGZ = onContentAdLoadedListener;
    }

    public void zza(zzhf zzhf) {
        this.zzGZ.onContentAdLoaded(zzb(zzhf));
    }

    /* access modifiers changed from: package-private */
    public zzhg zzb(zzhf zzhf) {
        return new zzhg(zzhf);
    }
}
