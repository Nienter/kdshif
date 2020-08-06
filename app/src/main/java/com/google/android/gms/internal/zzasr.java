package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzasr implements Parcelable.Creator<zzasq> {
    static void zza(zzasq zzasq, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzasq.versionCode);
        zzc.zza(parcel, 2, zzasq.packageName, false);
        zzc.zza(parcel, 3, zzasq.zzbqf, false);
        zzc.zza(parcel, 4, zzasq.zzbhg, false);
        zzc.zza(parcel, 5, zzasq.zzbqg, false);
        zzc.zza(parcel, 6, zzasq.zzbqh);
        zzc.zza(parcel, 7, zzasq.zzbqi);
        zzc.zza(parcel, 8, zzasq.zzbqj, false);
        zzc.zza(parcel, 9, zzasq.zzbqk);
        zzc.zza(parcel, 10, zzasq.zzbql);
        zzc.zza(parcel, 11, zzasq.zzbqm);
        zzc.zza(parcel, 12, zzasq.zzbqn, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzhK */
    public zzasq createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        long j = 0;
        long j2 = 0;
        String str5 = null;
        boolean z = false;
        boolean z2 = false;
        long j3 = 0;
        String str6 = null;
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
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                case 7:
                    j2 = zzb.zzi(parcel, zzaT);
                    break;
                case 8:
                    str5 = zzb.zzq(parcel, zzaT);
                    break;
                case 9:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 10:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 11:
                    j3 = zzb.zzi(parcel, zzaT);
                    break;
                case 12:
                    str6 = zzb.zzq(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzasq(i, str, str2, str3, str4, j, j2, str5, z, z2, j3, str6);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzlp */
    public zzasq[] newArray(int i) {
        return new zzasq[i];
    }
}
