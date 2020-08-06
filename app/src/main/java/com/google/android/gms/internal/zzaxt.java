package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzaxt implements Parcelable.Creator<zzaxs> {
    static void zza(zzaxs zzaxs, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzaxs.mVersionCode);
        zzc.zza(parcel, 2, zzaxs.zzbCn);
        zzc.zzc(parcel, 3, zzaxs.zzbCo, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziP */
    public zzaxs createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaU = zzb.zzaU(parcel);
        ArrayList<Scope> arrayList = null;
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
                    arrayList = zzb.zzc(parcel, zzaT, Scope.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzaxs(i, z, arrayList);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzmJ */
    public zzaxs[] newArray(int i) {
        return new zzaxs[i];
    }
}
