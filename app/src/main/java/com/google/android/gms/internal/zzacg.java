package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzacg implements Parcelable.Creator<zzacf> {
    static void zza(zzacf zzacf, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzacf.mVersionCode);
        zzc.zza(parcel, 2, (Parcelable) zzacf.zzxH(), i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaX */
    public zzacf createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        zzach zzach = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    zzach = (zzach) zzb.zza(parcel, zzaT, zzach.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzacf(i, zzach);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzcZ */
    public zzacf[] newArray(int i) {
        return new zzacf[i];
    }
}
