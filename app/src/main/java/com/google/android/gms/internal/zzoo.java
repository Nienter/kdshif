package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzoo implements Parcelable.Creator<zzon> {
    static void zza(zzon zzon, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzon.versionCode);
        zzc.zza(parcel, 2, zzon.zzVk, false);
        zzc.zza(parcel, 3, zzon.zzVl, false);
        zzc.zza(parcel, 4, zzon.zzVm);
        zzc.zza(parcel, 5, zzon.zzVn);
        zzc.zzb(parcel, 6, zzon.zzVo, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzae */
    public zzon[] newArray(int i) {
        return new zzon[i];
    }

    /* renamed from: zzu */
    public zzon createFromParcel(Parcel parcel) {
        ArrayList<String> arrayList = null;
        boolean z = false;
        int zzaU = zzb.zzaU(parcel);
        boolean z2 = false;
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 5:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 6:
                    arrayList = zzb.zzE(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzon(i, str2, str, z2, z, arrayList);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }
}
