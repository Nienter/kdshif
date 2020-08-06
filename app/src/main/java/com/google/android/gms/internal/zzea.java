package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzea implements Parcelable.Creator<zzdy> {
    static void zza(zzdy zzdy, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzdy.versionCode);
        zzc.zza(parcel, 2, zzdy.zzyF);
        zzc.zza(parcel, 3, zzdy.extras, false);
        zzc.zzc(parcel, 4, zzdy.zzyG);
        zzc.zzb(parcel, 5, zzdy.zzyH, false);
        zzc.zza(parcel, 6, zzdy.zzyI);
        zzc.zzc(parcel, 7, zzdy.zzyJ);
        zzc.zza(parcel, 8, zzdy.zzyK);
        zzc.zza(parcel, 9, zzdy.zzyL, false);
        zzc.zza(parcel, 10, (Parcelable) zzdy.zzyM, i, false);
        zzc.zza(parcel, 11, (Parcelable) zzdy.zzyN, i, false);
        zzc.zza(parcel, 12, zzdy.zzyO, false);
        zzc.zza(parcel, 13, zzdy.zzyP, false);
        zzc.zza(parcel, 14, zzdy.zzyQ, false);
        zzc.zzb(parcel, 15, zzdy.zzyR, false);
        zzc.zza(parcel, 16, zzdy.zzyS, false);
        zzc.zza(parcel, 17, zzdy.zzyT, false);
        zzc.zza(parcel, 18, zzdy.zzyU);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zze */
    public zzdy createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        ArrayList<String> arrayList = null;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        zzfj zzfj = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        Bundle bundle3 = null;
        ArrayList<String> arrayList2 = null;
        String str3 = null;
        String str4 = null;
        boolean z3 = false;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                case 3:
                    bundle = zzb.zzs(parcel, zzaT);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 5:
                    arrayList = zzb.zzE(parcel, zzaT);
                    break;
                case 6:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 7:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 8:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 9:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 10:
                    zzfj = (zzfj) zzb.zza(parcel, zzaT, zzfj.CREATOR);
                    break;
                case 11:
                    location = (Location) zzb.zza(parcel, zzaT, Location.CREATOR);
                    break;
                case 12:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 13:
                    bundle2 = zzb.zzs(parcel, zzaT);
                    break;
                case 14:
                    bundle3 = zzb.zzs(parcel, zzaT);
                    break;
                case 15:
                    arrayList2 = zzb.zzE(parcel, zzaT);
                    break;
                case 16:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 17:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 18:
                    z3 = zzb.zzc(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzdy(i, j, bundle, i2, arrayList, z, i3, z2, str, zzfj, location, str2, bundle2, bundle3, arrayList2, str3, str4, z3);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzv */
    public zzdy[] newArray(int i) {
        return new zzdy[i];
    }
}
