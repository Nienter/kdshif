package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzny implements Parcelable.Creator<zznx> {
    static void zza(zznx zznx, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zznx.versionCode);
        zzc.zza(parcel, 2, (Parcelable) zznx.zzRd, i, false);
        zzc.zza(parcel, 3, zznx.zzvd, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaa */
    public zznx[] newArray(int i) {
        return new zznx[i];
    }

    /* renamed from: zzs */
    public zznx createFromParcel(Parcel parcel) {
        String zzq;
        zzdy zzdy;
        int i;
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        int i2 = 0;
        zzdy zzdy2 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    String str2 = str;
                    zzdy = zzdy2;
                    i = zzb.zzg(parcel, zzaT);
                    zzq = str2;
                    break;
                case 2:
                    i = i2;
                    zzdy zzdy3 = (zzdy) zzb.zza(parcel, zzaT, zzdy.CREATOR);
                    zzq = str;
                    zzdy = zzdy3;
                    break;
                case 3:
                    zzq = zzb.zzq(parcel, zzaT);
                    zzdy = zzdy2;
                    i = i2;
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    zzq = str;
                    zzdy = zzdy2;
                    i = i2;
                    break;
            }
            i2 = i;
            zzdy2 = zzdy;
            str = zzq;
        }
        if (parcel.dataPosition() == zzaU) {
            return new zznx(i2, zzdy2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }
}
