package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbgx implements Parcelable.Creator<zzbgw> {
    static void zza(zzbgw zzbgw, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzbgw.versionCode);
        zzc.zzc(parcel, 2, zzbgw.mode);
        zzc.zzc(parcel, 3, zzbgw.zzbNj);
        zzc.zzc(parcel, 4, zzbgw.zzbNk);
        zzc.zza(parcel, 5, zzbgw.zzbNl);
        zzc.zza(parcel, 6, zzbgw.zzbNm);
        zzc.zza(parcel, 7, zzbgw.zzbNn);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzji */
    public zzbgw createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaU = zzb.zzaU(parcel);
        float f = -1.0f;
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i4 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 3:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 4:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 5:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 6:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 7:
                    f = zzb.zzl(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzbgw(i4, i3, i2, i, z2, z, f);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzny */
    public zzbgw[] newArray(int i) {
        return new zzbgw[i];
    }
}
