package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzfo implements Parcelable.Creator<zzfn> {
    static void zza(zzfn zzfn, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzfn.versionCode);
        zzc.zza(parcel, 2, zzfn.zzAE);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzA */
    public zzfn[] newArray(int i) {
        return new zzfn[i];
    }

    /* renamed from: zzh */
    public zzfn createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzfn(i, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }
}
