package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzjn;
import com.google.android.gms.internal.zzju;

@zzmb
public final class zzjl extends zzju.zza {
    private zzjn.zza zzKm;
    private zzjk zzKn;
    private final Object zzrN = new Object();

    public void onAdClicked() {
        synchronized (this.zzrN) {
            if (this.zzKn != null) {
                this.zzKn.zzbP();
            }
        }
    }

    public void onAdClosed() {
        synchronized (this.zzrN) {
            if (this.zzKn != null) {
                this.zzKn.zzbQ();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.zzrN) {
            if (this.zzKm != null) {
                this.zzKm.zzD(i == 3 ? 1 : 2);
                this.zzKm = null;
            }
        }
    }

    public void onAdImpression() {
        synchronized (this.zzrN) {
            if (this.zzKn != null) {
                this.zzKn.zzbU();
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.zzrN) {
            if (this.zzKn != null) {
                this.zzKn.zzbR();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    public void onAdLoaded() {
        synchronized (this.zzrN) {
            if (this.zzKm != null) {
                this.zzKm.zzD(0);
                this.zzKm = null;
            } else if (this.zzKn != null) {
                this.zzKn.zzbT();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.zzrN) {
            if (this.zzKn != null) {
                this.zzKn.zzbS();
            }
        }
    }

    public void zza(@Nullable zzjk zzjk) {
        synchronized (this.zzrN) {
            this.zzKn = zzjk;
        }
    }

    public void zza(zzjn.zza zza) {
        synchronized (this.zzrN) {
            this.zzKm = zza;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    public void zza(zzjv zzjv) {
        synchronized (this.zzrN) {
            if (this.zzKm != null) {
                this.zzKm.zza(0, zzjv);
                this.zzKm = null;
            } else if (this.zzKn != null) {
                this.zzKn.zzbT();
            }
        }
    }
}
