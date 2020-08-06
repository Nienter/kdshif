package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzoi;

@zzmb
public class zzoe extends zzoi.zza {
    private volatile zzof zzUM;
    private volatile zzoc zzUY;
    private volatile zzod zzUZ;

    public zzoe(zzod zzod) {
        this.zzUZ = zzod;
    }

    public void zza(zzd zzd, zzok zzok) {
        if (this.zzUZ != null) {
            this.zzUZ.zzc(zzok);
        }
    }

    public void zza(zzoc zzoc) {
        this.zzUY = zzoc;
    }

    public void zza(zzof zzof) {
        this.zzUM = zzof;
    }

    public void zzb(zzd zzd, int i) {
        if (this.zzUY != null) {
            this.zzUY.zzab(i);
        }
    }

    public void zzc(zzd zzd, int i) {
        if (this.zzUM != null) {
            this.zzUM.zza(zze.zzE(zzd).getClass().getName(), i);
        }
    }

    public void zzq(zzd zzd) {
        if (this.zzUY != null) {
            this.zzUY.zzjs();
        }
    }

    public void zzr(zzd zzd) {
        if (this.zzUM != null) {
            this.zzUM.zzaN(zze.zzE(zzd).getClass().getName());
        }
    }

    public void zzs(zzd zzd) {
        if (this.zzUZ != null) {
            this.zzUZ.onRewardedVideoAdOpened();
        }
    }

    public void zzt(zzd zzd) {
        if (this.zzUZ != null) {
            this.zzUZ.onRewardedVideoStarted();
        }
    }

    public void zzu(zzd zzd) {
        if (this.zzUZ != null) {
            this.zzUZ.onRewardedVideoAdClosed();
        }
    }

    public void zzv(zzd zzd) {
        if (this.zzUZ != null) {
            this.zzUZ.zzjp();
        }
    }

    public void zzw(zzd zzd) {
        if (this.zzUZ != null) {
            this.zzUZ.onRewardedVideoAdLeftApplication();
        }
    }
}
