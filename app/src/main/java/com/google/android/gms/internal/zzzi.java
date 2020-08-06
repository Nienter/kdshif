package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzzi implements Parcelable.Creator<zzzh> {
    static void zza(zzzh zzzh, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzzh.versionCode);
        zzc.zza(parcel, 2, (Parcelable) zzzh.zzawE, i, false);
        zzc.zza(parcel, 3, zzzh.zzawF, false);
        zzc.zza(parcel, 4, zzzh.zzawG, false);
        zzc.zza(parcel, 5, zzzh.zzawH, false);
        zzc.zza(parcel, 6, zzzh.zzawI, false);
        zzc.zza(parcel, 7, zzzh.zzawJ, false);
        zzc.zza(parcel, 8, zzzh.zzawK);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaF */
    public zzzh createFromParcel(Parcel parcel) {
        byte[][] bArr = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        boolean z = true;
        int[] iArr = null;
        String[] strArr = null;
        int[] iArr2 = null;
        byte[] bArr2 = null;
        zzawe zzawe = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    zzawe = (zzawe) zzb.zza(parcel, zzaT, zzawe.CREATOR);
                    break;
                case 3:
                    bArr2 = zzb.zzt(parcel, zzaT);
                    break;
                case 4:
                    iArr2 = zzb.zzw(parcel, zzaT);
                    break;
                case 5:
                    strArr = zzb.zzC(parcel, zzaT);
                    break;
                case 6:
                    iArr = zzb.zzw(parcel, zzaT);
                    break;
                case 7:
                    bArr = zzb.zzu(parcel, zzaT);
                    break;
                case 8:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzzh(i, zzawe, bArr2, iArr2, strArr, iArr, bArr, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzcp */
    public zzzh[] newArray(int i) {
        return new zzzh[i];
    }
}
