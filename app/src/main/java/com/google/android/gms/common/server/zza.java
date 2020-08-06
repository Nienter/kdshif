package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zza implements Parcelable.Creator<FavaDiagnosticsEntity> {
    static void zza(FavaDiagnosticsEntity favaDiagnosticsEntity, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, favaDiagnosticsEntity.mVersionCode);
        zzc.zza(parcel, 2, favaDiagnosticsEntity.zzaFs, false);
        zzc.zzc(parcel, 3, favaDiagnosticsEntity.zzaFt);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaW */
    public FavaDiagnosticsEntity createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaU = zzb.zzaU(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new FavaDiagnosticsEntity(i2, str, i);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzcY */
    public FavaDiagnosticsEntity[] newArray(int i) {
        return new FavaDiagnosticsEntity[i];
    }
}
