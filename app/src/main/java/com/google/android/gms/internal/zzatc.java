package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzatc implements Parcelable.Creator<zzatb> {
    static void zza(zzatb zzatb, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzatb.versionCode);
        zzc.zza(parcel, 2, zzatb.name, false);
        zzc.zza(parcel, 3, (Parcelable) zzatb.zzbqP, i, false);
        zzc.zza(parcel, 4, zzatb.zzbqQ, false);
        zzc.zza(parcel, 5, zzatb.zzbqR);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzhM */
    public zzatb createFromParcel(Parcel parcel) {
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        long j = 0;
        zzasz zzasz = null;
        String str2 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    zzasz = (zzasz) zzb.zza(parcel, zzaT, zzasz.CREATOR);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzatb(i, str2, zzasz, str, j);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzlr */
    public zzatb[] newArray(int i) {
        return new zzatb[i];
    }
}
