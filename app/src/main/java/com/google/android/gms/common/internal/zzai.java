package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzai implements Parcelable.Creator<zzah> {
    static void zza(zzah zzah, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzah.mVersionCode);
        zzc.zzc(parcel, 2, zzah.zzxD());
        zzc.zzc(parcel, 3, zzah.zzxE());
        zzc.zza(parcel, 4, (T[]) zzah.zzxF(), i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaR */
    public zzah createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        Scope[] scopeArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 3:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 4:
                    scopeArr = (Scope[]) zzb.zzb(parcel, zzaT, Scope.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzah(i3, i2, i, scopeArr);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzcU */
    public zzah[] newArray(int i) {
        return new zzah[i];
    }
}
