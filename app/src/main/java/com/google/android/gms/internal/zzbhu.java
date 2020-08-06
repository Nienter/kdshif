package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbhu implements Parcelable.Creator<zzbht> {
    static void zza(zzbht zzbht, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzbht.versionCode);
        zzc.zza(parcel, 2, (T[]) zzbht.zzbNM, i, false);
        zzc.zza(parcel, 3, (Parcelable) zzbht.zzbNC, i, false);
        zzc.zza(parcel, 4, (Parcelable) zzbht.zzbND, i, false);
        zzc.zza(parcel, 5, zzbht.zzbNF, false);
        zzc.zza(parcel, 6, zzbht.zzbNG);
        zzc.zza(parcel, 7, zzbht.zzbNw, false);
        zzc.zza(parcel, 8, zzbht.zzbNN);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjq */
    public zzbht createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        float f = 0.0f;
        String str2 = null;
        zzbhg zzbhg = null;
        zzbhg zzbhg2 = null;
        zzbho[] zzbhoArr = null;
        int i = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    zzbhoArr = (zzbho[]) zzb.zzb(parcel, zzaT, zzbho.CREATOR);
                    break;
                case 3:
                    zzbhg2 = (zzbhg) zzb.zza(parcel, zzaT, zzbhg.CREATOR);
                    break;
                case 4:
                    zzbhg = (zzbhg) zzb.zza(parcel, zzaT, zzbhg.CREATOR);
                    break;
                case 5:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    f = zzb.zzl(parcel, zzaT);
                    break;
                case 7:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 8:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzbht(i, zzbhoArr, zzbhg2, zzbhg, str2, f, str, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznH */
    public zzbht[] newArray(int i) {
        return new zzbht[i];
    }
}
