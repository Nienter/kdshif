package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzmn implements Parcelable.Creator<zzmm> {
    static void zza(zzmm zzmm, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzmm.versionCode);
        zzc.zza(parcel, 2, zzmm.zzSi);
        zzc.zzb(parcel, 3, zzmm.zzSj, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzV */
    public zzmm[] newArray(int i) {
        return new zzmm[i];
    }

    /* renamed from: zzo */
    public zzmm createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaU = zzb.zzaU(parcel);
        ArrayList<String> arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 3:
                    arrayList = zzb.zzE(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzmm(i, z, arrayList);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }
}
