package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zzi implements Parcelable.Creator<Barcode.PersonName> {
    static void zza(Barcode.PersonName personName, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, personName.versionCode);
        zzc.zza(parcel, 2, personName.formattedName, false);
        zzc.zza(parcel, 3, personName.pronunciation, false);
        zzc.zza(parcel, 4, personName.prefix, false);
        zzc.zza(parcel, 5, personName.first, false);
        zzc.zza(parcel, 6, personName.middle, false);
        zzc.zza(parcel, 7, personName.last, false);
        zzc.zza(parcel, 8, personName.suffix, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjb */
    public Barcode.PersonName createFromParcel(Parcel parcel) {
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str7 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    str6 = zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str5 = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 8:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new Barcode.PersonName(i, str7, str6, str5, str4, str3, str2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznq */
    public Barcode.PersonName[] newArray(int i) {
        return new Barcode.PersonName[i];
    }
}
