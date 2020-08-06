package com.google.android.gms.internal;

import com.google.android.gms.ads.VideoController;
import com.google.android.gms.internal.zzex;

public final class zzfm extends zzex.zza {
    private final VideoController.VideoLifecycleCallbacks zzrP;

    public zzfm(VideoController.VideoLifecycleCallbacks videoLifecycleCallbacks) {
        this.zzrP = videoLifecycleCallbacks;
    }

    public void onVideoEnd() {
        this.zzrP.onVideoEnd();
    }

    public void zzeT() {
    }

    public void zzeU() {
    }

    public void zzeV() {
    }
}
