package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbhe implements Parcelable.Creator<zzbhd> {
    static void zza(zzbhd zzbhd, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzbhd.versionCode);
        zzc.zzc(parcel, 2, zzbhd.width);
        zzc.zzc(parcel, 3, zzbhd.height);
        zzc.zzc(parcel, 4, zzbhd.f1726id);
        zzc.zza(parcel, 5, zzbhd.zzbNq);
        zzc.zzc(parcel, 6, zzbhd.rotation);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjk */
    public zzbhd createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        long j = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i5 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    i4 = zzb.zzg(parcel, zzaT);
                    break;
                case 3:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 5:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                case 6:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzbhd(i5, i4, i3, i2, j, i);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznA */
    public zzbhd[] newArray(int i) {
        return new zzbhd[i];
    }
}
