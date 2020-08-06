package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.zzh;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgi;

@zzmb
public final class zzgg extends zzgi.zza {
    private final zzh zzFw;
    @Nullable
    private final String zzFx;
    private final String zzFy;

    public zzgg(zzh zzh, @Nullable String str, String str2) {
        this.zzFw = zzh;
        this.zzFx = str;
        this.zzFy = str2;
    }

    public String getContent() {
        return this.zzFy;
    }

    public void recordClick() {
        this.zzFw.zzbX();
    }

    public void recordImpression() {
        this.zzFw.zzbY();
    }

    public String zzfB() {
        return this.zzFx;
    }

    public void zzi(@Nullable zzd zzd) {
        if (zzd != null) {
            this.zzFw.zzc((View) zze.zzE(zzd));
        }
    }
}
