package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzn implements Parcelable.Creator<zzm> {
    static void zza(zzm zzm, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzm.versionCode);
        zzc.zza(parcel, 2, zzm.zztH);
        zzc.zza(parcel, 3, zzm.zztI);
        zzc.zza(parcel, 4, zzm.zztJ, false);
        zzc.zza(parcel, 5, zzm.zztK);
        zzc.zza(parcel, 6, zzm.zztL);
        zzc.zzc(parcel, 7, zzm.zztM);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzb */
    public zzm createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        String str = null;
        float f = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    z3 = zzb.zzc(parcel, zzaT);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 6:
                    f = zzb.zzl(parcel, zzaT);
                    break;
                case 7:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzm(i2, z3, z2, str, z, f, i);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzi */
    public zzm[] newArray(int i) {
        return new zzm[i];
    }
}
