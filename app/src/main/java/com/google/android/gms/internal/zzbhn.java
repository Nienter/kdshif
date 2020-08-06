package com.google.android.gms.internal;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbhn implements Parcelable.Creator<zzbhm> {
    static void zza(zzbhm zzbhm, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzbhm.versionCode);
        zzc.zza(parcel, 2, (Parcelable) zzbhm.zzbNL, i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjn */
    public zzbhm createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        Rect rect = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    rect = (Rect) zzb.zza(parcel, zzaT, Rect.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzbhm(i, rect);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznE */
    public zzbhm[] newArray(int i) {
        return new zzbhm[i];
    }
}
