package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zzj implements Parcelable.Creator<Barcode.Phone> {
    static void zza(Barcode.Phone phone, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, phone.versionCode);
        zzc.zzc(parcel, 2, phone.type);
        zzc.zza(parcel, 3, phone.number, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzjc */
    public Barcode.Phone createFromParcel(Parcel parcel) {
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
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 3:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new Barcode.Phone(i2, i, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznr */
    public Barcode.Phone[] newArray(int i) {
        return new Barcode.Phone[i];
    }
}
