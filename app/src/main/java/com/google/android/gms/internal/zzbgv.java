package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbgv implements Parcelable.Creator<zzbgu> {
    static void zza(zzbgu zzbgu, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzbgu.versionCode);
        zzc.zzc(parcel, 2, zzbgu.f1723id);
        zzc.zza(parcel, 3, zzbgu.centerX);
        zzc.zza(parcel, 4, zzbgu.centerY);
        zzc.zza(parcel, 5, zzbgu.width);
        zzc.zza(parcel, 6, zzbgu.height);
        zzc.zza(parcel, 7, zzbgu.zzbNd);
        zzc.zza(parcel, 8, zzbgu.zzbNe);
        zzc.zza(parcel, 9, (T[]) zzbgu.zzbNf, i, false);
        zzc.zza(parcel, 10, zzbgu.zzbNg);
        zzc.zza(parcel, 11, zzbgu.zzbNh);
        zzc.zza(parcel, 12, zzbgu.zzbNi);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjh */
    public zzbgu createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        zzbha[] zzbhaArr = null;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 3:
                    f = zzb.zzl(parcel, zzaT);
                    break;
                case 4:
                    f2 = zzb.zzl(parcel, zzaT);
                    break;
                case 5:
                    f3 = zzb.zzl(parcel, zzaT);
                    break;
                case 6:
                    f4 = zzb.zzl(parcel, zzaT);
                    break;
                case 7:
                    f5 = zzb.zzl(parcel, zzaT);
                    break;
                case 8:
                    f6 = zzb.zzl(parcel, zzaT);
                    break;
                case 9:
                    zzbhaArr = (zzbha[]) zzb.zzb(parcel, zzaT, zzbha.CREATOR);
                    break;
                case 10:
                    f7 = zzb.zzl(parcel, zzaT);
                    break;
                case 11:
                    f8 = zzb.zzl(parcel, zzaT);
                    break;
                case 12:
                    f9 = zzb.zzl(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzbgu(i, i2, f, f2, f3, f4, f5, f6, zzbhaArr, f7, f8, f9);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznx */
    public zzbgu[] newArray(int i) {
        return new zzbgu[i];
    }
}
