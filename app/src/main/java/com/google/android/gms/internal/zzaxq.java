package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzaxq implements Parcelable.Creator<zzaxp> {
    static void zza(zzaxp zzaxp, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzaxp.mVersionCode);
        zzc.zzc(parcel, 2, zzaxp.zzOk());
        zzc.zza(parcel, 3, (Parcelable) zzaxp.zzOl(), i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziO */
    public zzaxp createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 3:
                    intent = (Intent) zzb.zza(parcel, zzaT, Intent.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzaxp(i2, i, intent);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzmI */
    public zzaxp[] newArray(int i) {
        return new zzaxp[i];
    }
}
