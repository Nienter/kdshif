package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzad;

public class zzaya implements Parcelable.Creator<zzaxz> {
    static void zza(zzaxz zzaxz, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzaxz.mVersionCode);
        zzc.zza(parcel, 2, (Parcelable) zzaxz.zzOo(), i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziR */
    public zzaxz createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        zzad zzad = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    zzad = (zzad) zzb.zza(parcel, zzaT, zzad.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzaxz(i, zzad);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzmM */
    public zzaxz[] newArray(int i) {
        return new zzaxz[i];
    }
}
