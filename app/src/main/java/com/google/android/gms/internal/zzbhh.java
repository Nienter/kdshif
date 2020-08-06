package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbhh implements Parcelable.Creator<zzbhg> {
    static void zza(zzbhg zzbhg, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzbhg.versionCode);
        zzc.zzc(parcel, 2, zzbhg.left);
        zzc.zzc(parcel, 3, zzbhg.top);
        zzc.zzc(parcel, 4, zzbhg.width);
        zzc.zzc(parcel, 5, zzbhg.height);
        zzc.zza(parcel, 6, zzbhg.zzbNA);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjl */
    public zzbhg createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        float f = 0.0f;
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
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 6:
                    f = zzb.zzl(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzbhg(i5, i4, i3, i2, i, f);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznC */
    public zzbhg[] newArray(int i) {
        return new zzbhg[i];
    }
}
