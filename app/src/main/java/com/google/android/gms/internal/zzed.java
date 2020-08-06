package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzed implements Parcelable.Creator<zzec> {
    static void zza(zzec zzec, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzec.versionCode);
        zzc.zza(parcel, 2, zzec.zzzk, false);
        zzc.zzc(parcel, 3, zzec.height);
        zzc.zzc(parcel, 4, zzec.heightPixels);
        zzc.zza(parcel, 5, zzec.zzzl);
        zzc.zzc(parcel, 6, zzec.width);
        zzc.zzc(parcel, 7, zzec.widthPixels);
        zzc.zza(parcel, 8, (T[]) zzec.zzzm, i, false);
        zzc.zza(parcel, 9, zzec.zzzn);
        zzc.zza(parcel, 10, zzec.zzzo);
        zzc.zza(parcel, 11, zzec.zzzp);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzf */
    public zzec createFromParcel(Parcel parcel) {
        zzec[] zzecArr = null;
        boolean z = false;
        int zzaU = zzb.zzaU(parcel);
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        int i2 = 0;
        boolean z4 = false;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        int i5 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i5 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    i4 = zzb.zzg(parcel, zzaT);
                    break;
                case 4:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 5:
                    z4 = zzb.zzc(parcel, zzaT);
                    break;
                case 6:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 7:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 8:
                    zzecArr = (zzec[]) zzb.zzb(parcel, zzaT, zzec.CREATOR);
                    break;
                case 9:
                    z3 = zzb.zzc(parcel, zzaT);
                    break;
                case 10:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 11:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzec(i5, str, i4, i3, z4, i2, i, zzecArr, z3, z2, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzw */
    public zzec[] newArray(int i) {
        return new zzec[i];
    }
}
