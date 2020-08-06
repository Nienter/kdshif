package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzaco;
import java.util.ArrayList;

public class zzacq implements Parcelable.Creator<zzaco.zza> {
    static void zza(zzaco.zza zza, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zza.versionCode);
        zzc.zza(parcel, 2, zza.className, false);
        zzc.zzc(parcel, 3, zza.zzaFN, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzbd */
    public zzaco.zza createFromParcel(Parcel parcel) {
        ArrayList<zzaco.zzb> arrayList = null;
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
                    arrayList = zzb.zzc(parcel, zzaT, zzaco.zzb.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzaco.zza(i, str, arrayList);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzdf */
    public zzaco.zza[] newArray(int i) {
        return new zzaco.zza[i];
    }
}
