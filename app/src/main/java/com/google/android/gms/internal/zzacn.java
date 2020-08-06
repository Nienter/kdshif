package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzack;
import com.google.android.gms.internal.zzaco;

public class zzacn implements Parcelable.Creator<zzaco.zzb> {
    static void zza(zzaco.zzb zzb, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzb.versionCode);
        zzc.zza(parcel, 2, zzb.zzaA, false);
        zzc.zza(parcel, 3, (Parcelable) zzb.zzaFO, i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzbb */
    public zzaco.zzb createFromParcel(Parcel parcel) {
        zzack.zza zza = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    zza = (zzack.zza) zzb.zza(parcel, zzaT, zzack.zza.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzaco.zzb(i, str, zza);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzdd */
    public zzaco.zzb[] newArray(int i) {
        return new zzaco.zzb[i];
    }
}
