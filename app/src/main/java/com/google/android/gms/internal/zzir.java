package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzel;

@zzmb
class zzir {
    @Nullable
    zzer zzIt;
    @Nullable
    zzkz zzIu;
    @Nullable
    zzgj zzIv;
    @Nullable
    zzek zzIw;
    @Nullable
    zznt zzIx;
    @Nullable
    zzel zzti;

    private static class zza extends zzel.zza {
        private final zzel zzIy;

        zza(zzel zzel) {
            this.zzIy = zzel;
        }

        public void onAdClosed() {
            this.zzIy.onAdClosed();
            zzv.zzcY().zzgj();
        }

        public void onAdFailedToLoad(int i) {
            this.zzIy.onAdFailedToLoad(i);
        }

        public void onAdLeftApplication() {
            this.zzIy.onAdLeftApplication();
        }

        public void onAdLoaded() {
            this.zzIy.onAdLoaded();
        }

        public void onAdOpened() {
            this.zzIy.onAdOpened();
        }
    }

    zzir() {
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzl zzl) {
        if (this.zzti != null) {
            zzl.zza((zzel) new zza(this.zzti));
        }
        if (this.zzIt != null) {
            zzl.zza(this.zzIt);
        }
        if (this.zzIu != null) {
            zzl.zza(this.zzIu);
        }
        if (this.zzIv != null) {
            zzl.zza(this.zzIv);
        }
        if (this.zzIw != null) {
            zzl.zza(this.zzIw);
        }
        if (this.zzIx != null) {
            zzl.zza(this.zzIx);
        }
    }
}
