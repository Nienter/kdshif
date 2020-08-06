package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzqb implements Parcelable.Creator<zzqa> {
    static void zza(zzqa zzqa, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzqa.versionCode);
        zzc.zza(parcel, 2, zzqa.zzaZ, false);
        zzc.zzc(parcel, 3, zzqa.zzYb);
        zzc.zzc(parcel, 4, zzqa.zzYc);
        zzc.zza(parcel, 5, zzqa.zzYd);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaj */
    public zzqa[] newArray(int i) {
        return new zzqa[i];
    }

    /* renamed from: zzv */
    public zzqa createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaU = zzb.zzaU(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 4:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 5:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzqa(i3, str, i2, i, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }
}
