package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzb implements Parcelable.Creator<zzc> {
    static void zza(zzc zzc, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzc.versionCode);
        zzc.zza(parcel, 2, zzc.zzLY, false);
        zzc.zza(parcel, 3, zzc.url, false);
        zzc.zza(parcel, 4, zzc.mimeType, false);
        zzc.zza(parcel, 5, zzc.packageName, false);
        zzc.zza(parcel, 6, zzc.zzLZ, false);
        zzc.zza(parcel, 7, zzc.zzMa, false);
        zzc.zza(parcel, 8, zzc.zzMb, false);
        zzc.zza(parcel, 9, (Parcelable) zzc.intent, i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzH */
    public zzc[] newArray(int i) {
        return new zzc[i];
    }

    /* renamed from: zzj */
    public zzc createFromParcel(Parcel parcel) {
        Intent intent = null;
        int zzaU = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(zzaT)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    str6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    str4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    str3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    str2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 8:
                    str = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 9:
                    intent = (Intent) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Intent.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzc(i, str7, str6, str5, str4, str3, str2, str, intent);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }
}
