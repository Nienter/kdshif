package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzmp implements Parcelable.Creator<zzmo> {
    static void zza(zzmo zzmo, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzmo.versionCode);
        zzc.zza(parcel, 2, zzmo.zzSk);
        zzc.zza(parcel, 3, zzmo.zzSl);
        zzc.zza(parcel, 4, zzmo.zzSm);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzW */
    public zzmo[] newArray(int i) {
        return new zzmo[i];
    }

    /* renamed from: zzp */
    public zzmo createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaU = zzb.zzaU(parcel);
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    z3 = zzb.zzc(parcel, zzaT);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 4:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzmo(i, z3, z2, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }
}
