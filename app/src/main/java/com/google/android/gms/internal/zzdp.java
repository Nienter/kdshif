package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzdp implements Parcelable.Creator<zzdo> {
    static void zza(zzdo zzdo, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzdo.version);
        zzc.zza(parcel, 2, zzdo.url, false);
        zzc.zza(parcel, 3, zzdo.zzyx);
        zzc.zza(parcel, 4, zzdo.zzyy, false);
        zzc.zza(parcel, 5, zzdo.zzyz, false);
        zzc.zza(parcel, 6, zzdo.zzyA, false);
        zzc.zza(parcel, 7, zzdo.zzyB, false);
        zzc.zza(parcel, 8, zzdo.zzyC);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzd */
    public zzdo createFromParcel(Parcel parcel) {
        boolean z = false;
        Bundle bundle = null;
        int zzaU = zzb.zzaU(parcel);
        long j = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    bundle = zzb.zzs(parcel, zzaT);
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
            return new zzdo(i, str4, j, str3, str2, str, bundle, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzu */
    public zzdo[] newArray(int i) {
        return new zzdo[i];
    }
}
