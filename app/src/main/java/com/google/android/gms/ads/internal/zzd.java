package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.zzk;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzo;
import com.google.android.gms.ads.internal.overlay.zzu;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzom;
import com.google.android.gms.internal.zzoq;

@zzmb
public class zzd {
    public final zzin zzsM;
    public final zzk zzsN;
    public final zzn zzsO;
    public final zzoq zzsP;

    public zzd(zzin zzin, zzk zzk, zzn zzn, zzoq zzoq) {
        this.zzsM = zzin;
        this.zzsN = zzk;
        this.zzsO = zzn;
        this.zzsP = zzoq;
    }

    public static zzd zzca() {
        return new zzd(new zzhu(), new zzo(), new zzu(), new zzom());
    }
}
