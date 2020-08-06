package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zznr;

@zzmb
public class zzno extends zznr.zza {
    private final Context mContext;
    private final zznp zzUE;
    private final Object zzrN;
    private final zzqa zztr;

    public zzno(Context context, zzd zzd, zzjs zzjs, zzqa zzqa) {
        this(context, zzqa, new zznp(context, zzd, zzec.zzez(), zzjs, zzqa));
    }

    zzno(Context context, zzqa zzqa, zznp zznp) {
        this.zzrN = new Object();
        this.mContext = context;
        this.zztr = zzqa;
        this.zzUE = zznp;
    }

    public void destroy() {
        zzh(null);
    }

    public boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.zzrN) {
            isLoaded = this.zzUE.isLoaded();
        }
        return isLoaded;
    }

    public void pause() {
        zzf(null);
    }

    public void resume() {
        zzg(null);
    }

    public void setUserId(String str) {
        zzpe.zzbe("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void show() {
        synchronized (this.zzrN) {
            this.zzUE.zzjo();
        }
    }

    public void zza(zznt zznt) {
        synchronized (this.zzrN) {
            this.zzUE.zza(zznt);
        }
    }

    public void zza(zznx zznx) {
        synchronized (this.zzrN) {
            this.zzUE.zza(zznx);
        }
    }

    public void zzf(com.google.android.gms.dynamic.zzd zzd) {
        synchronized (this.zzrN) {
            this.zzUE.pause();
        }
    }

    public void zzg(com.google.android.gms.dynamic.zzd zzd) {
        synchronized (this.zzrN) {
            Context context = zzd == null ? null : (Context) zze.zzE(zzd);
            if (context != null) {
                try {
                    this.zzUE.onContextChanged(context);
                } catch (Exception e) {
                    zzpe.zzc("Unable to extract updated context.", e);
                }
            }
            this.zzUE.resume();
        }
    }

    public void zzh(com.google.android.gms.dynamic.zzd zzd) {
        synchronized (this.zzrN) {
            this.zzUE.destroy();
        }
    }
}
