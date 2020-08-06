package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzawb implements Parcelable.Creator<zzawa> {
    static void zza(zzawa zzawa, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzawa.mVersionCode);
        zzc.zzc(parcel, 2, zzawa.zzbzp);
        zzc.zza(parcel, 3, (T[]) zzawa.zzbzq, i, false);
        zzc.zza(parcel, 4, zzawa.zzbzr, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzit */
    public zzawa createFromParcel(Parcel parcel) {
        String[] zzC;
        zzawc[] zzawcArr;
        int i;
        int i2;
        String[] strArr = null;
        int i3 = 0;
        int zzaU = zzb.zzaU(parcel);
        zzawc[] zzawcArr2 = null;
        int i4 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    String[] strArr2 = strArr;
                    zzawcArr = zzawcArr2;
                    i = i3;
                    i2 = zzb.zzg(parcel, zzaT);
                    zzC = strArr2;
                    break;
                case 2:
                    i2 = i4;
                    zzawc[] zzawcArr3 = zzawcArr2;
                    i = zzb.zzg(parcel, zzaT);
                    zzC = strArr;
                    zzawcArr = zzawcArr3;
                    break;
                case 3:
                    i = i3;
                    i2 = i4;
                    String[] strArr3 = strArr;
                    zzawcArr = (zzawc[]) zzb.zzb(parcel, zzaT, zzawc.CREATOR);
                    zzC = strArr3;
                    break;
                case 4:
                    zzC = zzb.zzC(parcel, zzaT);
                    zzawcArr = zzawcArr2;
                    i = i3;
                    i2 = i4;
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    zzC = strArr;
                    zzawcArr = zzawcArr2;
                    i = i3;
                    i2 = i4;
                    break;
            }
            i4 = i2;
            i3 = i;
            zzawcArr2 = zzawcArr;
            strArr = zzC;
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzawa(i4, i3, zzawcArr2, strArr);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzml */
    public zzawa[] newArray(int i) {
        return new zzawa[i];
    }
}
