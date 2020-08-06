package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzapy implements Parcelable.Creator<zzapx> {
    static void zza(zzapx zzapx, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzapx.versionCode);
        zzc.zza(parcel, 2, zzapx.zzFY(), false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzgg */
    public zzapx createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        byte[] bArr = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    bArr = zzb.zzt(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzapx(i, bArr);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzjq */
    public zzapx[] newArray(int i) {
        return new zzapx[i];
    }
}
