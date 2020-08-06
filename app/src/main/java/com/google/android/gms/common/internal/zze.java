package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zze implements Parcelable.Creator<zzd> {
    static void zza(zzd zzd, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzd.mVersionCode);
        zzc.zza(parcel, 2, zzd.zzaDx, false);
        zzc.zza(parcel, 3, (T[]) zzd.zzaDy, i, false);
        zzc.zza(parcel, 4, zzd.zzaDz, false);
        zzc.zza(parcel, 5, zzd.zzaDA, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaM */
    public zzd createFromParcel(Parcel parcel) {
        Integer num = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        Integer num2 = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    iBinder = zzb.zzr(parcel, zzaT);
                    break;
                case 3:
                    scopeArr = (Scope[]) zzb.zzb(parcel, zzaT, Scope.CREATOR);
                    break;
                case 4:
                    num2 = zzb.zzh(parcel, zzaT);
                    break;
                case 5:
                    num = zzb.zzh(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzd(i, iBinder, scopeArr, num2, num);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzcL */
    public zzd[] newArray(int i) {
        return new zzd[i];
    }
}
