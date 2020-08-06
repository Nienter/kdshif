package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzauc implements Parcelable.Creator<zzaub> {
    static void zza(zzaub zzaub, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzaub.versionCode);
        zzc.zza(parcel, 2, zzaub.name, false);
        zzc.zza(parcel, 3, zzaub.zzbuZ);
        zzc.zza(parcel, 4, zzaub.zzbva, false);
        zzc.zza(parcel, 5, zzaub.zzbvb, false);
        zzc.zza(parcel, 6, zzaub.zzaFy, false);
        zzc.zza(parcel, 7, zzaub.zzbqQ, false);
        zzc.zza(parcel, 8, zzaub.zzbvc, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzhN */
    public zzaub createFromParcel(Parcel parcel) {
        Double d = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        long j = 0;
        String str = null;
        String str2 = null;
        Float f = null;
        Long l = null;
        String str3 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                case 4:
                    l = zzb.zzj(parcel, zzaT);
                    break;
                case 5:
                    f = zzb.zzm(parcel, zzaT);
                    break;
                case 6:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 8:
                    d = zzb.zzo(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzaub(i, str3, j, l, f, str2, str, d);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzlt */
    public zzaub[] newArray(int i) {
        return new zzaub[i];
    }
}
