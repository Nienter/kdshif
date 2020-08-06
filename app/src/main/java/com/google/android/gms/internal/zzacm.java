package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzack;

public class zzacm implements Parcelable.Creator<zzack.zza> {
    static void zza(zzack.zza zza, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zza.getVersionCode());
        zzc.zzc(parcel, 2, zza.zzxL());
        zzc.zza(parcel, 3, zza.zzxM());
        zzc.zzc(parcel, 4, zza.zzxN());
        zzc.zza(parcel, 5, zza.zzxO());
        zzc.zza(parcel, 6, zza.zzxP(), false);
        zzc.zzc(parcel, 7, zza.zzxQ());
        zzc.zza(parcel, 8, zza.zzxS(), false);
        zzc.zza(parcel, 9, (Parcelable) zza.zzxU(), i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzba */
    public zzack.zza createFromParcel(Parcel parcel) {
        zzacf zzacf = null;
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i4 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 5:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 6:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 8:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 9:
                    zzacf = (zzacf) zzb.zza(parcel, zzaT, zzacf.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzack.zza(i4, i3, z2, i2, z, str2, i, str, zzacf);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzdc */
    public zzack.zza[] newArray(int i) {
        return new zzack.zza[i];
    }
}
