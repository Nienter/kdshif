package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzawd implements Parcelable.Creator<zzawc> {
    static void zza(zzawc zzawc, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzawc.mVersionCode);
        zzc.zza(parcel, 2, zzawc.name, false);
        zzc.zza(parcel, 3, zzawc.zzbzt);
        zzc.zza(parcel, 4, zzawc.zzbgG);
        zzc.zza(parcel, 5, zzawc.zzbgI);
        zzc.zza(parcel, 6, zzawc.zzaFy, false);
        zzc.zza(parcel, 7, zzawc.zzbzu, false);
        zzc.zzc(parcel, 8, zzawc.zzbzv);
        zzc.zzc(parcel, 9, zzawc.zzbzw);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziu */
    public zzawc createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        long j = 0;
        double d = 0.0d;
        int i2 = 0;
        String str = null;
        boolean z = false;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                case 4:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 5:
                    d = zzb.zzn(parcel, zzaT);
                    break;
                case 6:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    bArr = zzb.zzt(parcel, zzaT);
                    break;
                case 8:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 9:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzawc(i3, str2, j, z, d, str, bArr, i2, i);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzmm */
    public zzawc[] newArray(int i) {
        return new zzawc[i];
    }
}
