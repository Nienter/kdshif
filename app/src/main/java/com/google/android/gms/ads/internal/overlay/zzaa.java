package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpi;

@zzmb
class zzaa implements Runnable {
    private boolean mCancelled = false;
    private zzl zzOA;

    zzaa(zzl zzl) {
        this.zzOA = zzl;
    }

    public void cancel() {
        this.mCancelled = true;
        zzpi.zzWR.removeCallbacks(this);
    }

    public void run() {
        if (!this.mCancelled) {
            this.zzOA.zzhH();
            zzid();
        }
    }

    public void zzid() {
        zzpi.zzWR.postDelayed(this, 250);
    }
}
