package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzaco;
import java.util.ArrayList;

public class zzacp implements Parcelable.Creator<zzaco> {
    static void zza(zzaco zzaco, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzaco.mVersionCode);
        zzc.zzc(parcel, 2, zzaco.zzxX(), false);
        zzc.zza(parcel, 3, zzaco.zzxY(), false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzbc */
    public zzaco createFromParcel(Parcel parcel) {
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        ArrayList<zzaco.zza> arrayList = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    arrayList = zzb.zzc(parcel, zzaT, zzaco.zza.CREATOR);
                    break;
                case 3:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzaco(i, arrayList, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzde */
    public zzaco[] newArray(int i) {
        return new zzaco[i];
    }
}
