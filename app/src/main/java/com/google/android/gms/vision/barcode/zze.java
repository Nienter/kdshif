package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zze implements Parcelable.Creator<Barcode.ContactInfo> {
    static void zza(Barcode.ContactInfo contactInfo, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, contactInfo.versionCode);
        zzc.zza(parcel, 2, (Parcelable) contactInfo.name, i, false);
        zzc.zza(parcel, 3, contactInfo.organization, false);
        zzc.zza(parcel, 4, contactInfo.title, false);
        zzc.zza(parcel, 5, (T[]) contactInfo.phones, i, false);
        zzc.zza(parcel, 6, (T[]) contactInfo.emails, i, false);
        zzc.zza(parcel, 7, contactInfo.urls, false);
        zzc.zza(parcel, 8, (T[]) contactInfo.addresses, i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziX */
    public Barcode.ContactInfo createFromParcel(Parcel parcel) {
        Barcode.Address[] addressArr = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        String[] strArr = null;
        Barcode.Email[] emailArr = null;
        Barcode.Phone[] phoneArr = null;
        String str = null;
        String str2 = null;
        Barcode.PersonName personName = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    personName = (Barcode.PersonName) zzb.zza(parcel, zzaT, Barcode.PersonName.CREATOR);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    phoneArr = (Barcode.Phone[]) zzb.zzb(parcel, zzaT, Barcode.Phone.CREATOR);
                    break;
                case 6:
                    emailArr = (Barcode.Email[]) zzb.zzb(parcel, zzaT, Barcode.Email.CREATOR);
                    break;
                case 7:
                    strArr = zzb.zzC(parcel, zzaT);
                    break;
                case 8:
                    addressArr = (Barcode.Address[]) zzb.zzb(parcel, zzaT, Barcode.Address.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new Barcode.ContactInfo(i, personName, str2, str, phoneArr, emailArr, strArr, addressArr);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznm */
    public Barcode.ContactInfo[] newArray(int i) {
        return new Barcode.ContactInfo[i];
    }
}
