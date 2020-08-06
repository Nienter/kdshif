package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zzg implements Parcelable.Creator<Barcode.Email> {
    static void zza(Barcode.Email email, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, email.versionCode);
        zzc.zzc(parcel, 2, email.type);
        zzc.zza(parcel, 3, email.address, false);
        zzc.zza(parcel, 4, email.subject, false);
        zzc.zza(parcel, 5, email.body, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziZ */
    public Barcode.Email createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        String str2 = null;
        String str3 = null;
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
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new Barcode.Email(i2, i, str3, str2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzno */
    public Barcode.Email[] newArray(int i) {
        return new Barcode.Email[i];
    }
}
