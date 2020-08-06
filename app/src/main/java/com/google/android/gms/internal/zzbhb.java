package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbhb implements Parcelable.Creator<zzbha> {
    static void zza(zzbha zzbha, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzbha.versionCode);
        zzc.zza(parcel, 2, zzbha.f1724x);
        zzc.zza(parcel, 3, zzbha.f1725y);
        zzc.zzc(parcel, 4, zzbha.type);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjj */
    public zzbha createFromParcel(Parcel parcel) {
        int i = 0;
        float f = 0.0f;
        int zzaU = zzb.zzaU(parcel);
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    f2 = zzb.zzl(parcel, zzaT);
                    break;
                case 3:
                    f = zzb.zzl(parcel, zzaT);
                    break;
                case 4:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzbha(i2, f2, f, i);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznz */
    public zzbha[] newArray(int i) {
        return new zzbha[i];
    }
}
