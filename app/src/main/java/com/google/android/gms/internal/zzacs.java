package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzacs implements Parcelable.Creator<zzacr> {
    static void zza(zzacr zzacr, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzacr.getVersionCode());
        zzc.zza(parcel, 2, zzacr.zzya(), false);
        zzc.zza(parcel, 3, (Parcelable) zzacr.zzyb(), i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzbe */
    public zzacr createFromParcel(Parcel parcel) {
        zzaco zzaco = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    parcel2 = zzb.zzF(parcel, zzaT);
                    break;
                case 3:
                    zzaco = (zzaco) zzb.zza(parcel, zzaT, zzaco.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzacr(i, parcel2, zzaco);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzdg */
    public zzacr[] newArray(int i) {
        return new zzacr[i];
    }
}
