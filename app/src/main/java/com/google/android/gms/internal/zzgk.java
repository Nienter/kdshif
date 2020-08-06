package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.internal.zzgj;

@zzmb
public final class zzgk extends zzgj.zza {
    private final OnCustomRenderedAdLoadedListener zzAb;

    public zzgk(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzAb = onCustomRenderedAdLoadedListener;
    }

    public void zza(zzgi zzgi) {
        this.zzAb.onCustomRenderedAdLoaded(new zzgh(zzgi));
    }
}
