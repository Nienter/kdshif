package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzawf implements Parcelable.Creator<zzawe> {
    static void zza(zzawe zzawe, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzawe.versionCode);
        zzc.zza(parcel, 2, zzawe.packageName, false);
        zzc.zzc(parcel, 3, zzawe.zzbzy);
        zzc.zzc(parcel, 4, zzawe.zzbzz);
        zzc.zza(parcel, 5, zzawe.zzbzA, false);
        zzc.zza(parcel, 6, zzawe.zzbzB, false);
        zzc.zza(parcel, 7, zzawe.zzbzC);
        zzc.zza(parcel, 8, zzawe.zzbzD, false);
        zzc.zza(parcel, 9, zzawe.zzbzE);
        zzc.zzc(parcel, 10, zzawe.zzbzF);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziv */
    public zzawe createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        boolean z = true;
        boolean z2 = false;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        int i3 = 0;
        String str4 = null;
        int i4 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i4 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 5:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 8:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 9:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 10:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzawe(i4, str4, i3, i2, str3, str2, z, str, z2, i);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzmn */
    public zzawe[] newArray(int i) {
        return new zzawe[i];
    }
}
