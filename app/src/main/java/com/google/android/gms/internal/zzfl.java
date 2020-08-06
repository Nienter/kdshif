package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzc;

@zzmb
public class zzfl extends zzec {
    public zzfl(zzec zzec) {
        super(zzec.versionCode, zzec.zzzk, zzec.height, zzec.heightPixels, zzec.zzzl, zzec.width, zzec.widthPixels, zzec.zzzm, zzec.zzzn, zzec.zzzo, zzec.zzzp);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, this.versionCode);
        zzc.zza(parcel, 2, this.zzzk, false);
        zzc.zzc(parcel, 3, this.height);
        zzc.zzc(parcel, 6, this.width);
        zzc.zzJ(parcel, zzaV);
    }
}
