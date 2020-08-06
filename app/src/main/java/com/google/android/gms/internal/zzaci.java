package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzach;
import java.util.ArrayList;

public class zzaci implements Parcelable.Creator<zzach> {
    static void zza(zzach zzach, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzach.mVersionCode);
        zzc.zzc(parcel, 2, zzach.zzxJ(), false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaY */
    public zzach createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        ArrayList<zzach.zza> arrayList = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    arrayList = zzb.zzc(parcel, zzaT, zzach.zza.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzach(i, arrayList);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzda */
    public zzach[] newArray(int i) {
        return new zzach[i];
    }
}
