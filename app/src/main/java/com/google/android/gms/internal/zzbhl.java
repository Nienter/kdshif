package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbhl implements Parcelable.Creator<zzbhk> {
    static void zza(zzbhk zzbhk, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzbhk.versionCode);
        zzc.zza(parcel, 2, (T[]) zzbhk.zzbNB, i, false);
        zzc.zza(parcel, 3, (Parcelable) zzbhk.zzbNC, i, false);
        zzc.zza(parcel, 4, (Parcelable) zzbhk.zzbND, i, false);
        zzc.zza(parcel, 5, (Parcelable) zzbhk.zzbNE, i, false);
        zzc.zza(parcel, 6, zzbhk.zzbNF, false);
        zzc.zza(parcel, 7, zzbhk.zzbNG);
        zzc.zza(parcel, 8, zzbhk.zzbNw, false);
        zzc.zzc(parcel, 9, zzbhk.zzbNH);
        zzc.zza(parcel, 10, zzbhk.zzbNI);
        zzc.zzc(parcel, 11, zzbhk.zzbNJ);
        zzc.zzc(parcel, 12, zzbhk.zzbNK);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjm */
    public zzbhk createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        zzbht[] zzbhtArr = null;
        zzbhg zzbhg = null;
        zzbhg zzbhg2 = null;
        zzbhg zzbhg3 = null;
        String str = null;
        float f = 0.0f;
        String str2 = null;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    zzbhtArr = (zzbht[]) zzb.zzb(parcel, zzaT, zzbht.CREATOR);
                    break;
                case 3:
                    zzbhg = (zzbhg) zzb.zza(parcel, zzaT, zzbhg.CREATOR);
                    break;
                case 4:
                    zzbhg2 = (zzbhg) zzb.zza(parcel, zzaT, zzbhg.CREATOR);
                    break;
                case 5:
                    zzbhg3 = (zzbhg) zzb.zza(parcel, zzaT, zzbhg.CREATOR);
                    break;
                case 6:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    f = zzb.zzl(parcel, zzaT);
                    break;
                case 8:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 9:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 10:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 11:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 12:
                    i4 = zzb.zzg(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzbhk(i, zzbhtArr, zzbhg, zzbhg2, zzbhg3, str, f, str2, i2, z, i3, i4);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznD */
    public zzbhk[] newArray(int i) {
        return new zzbhk[i];
    }
}
