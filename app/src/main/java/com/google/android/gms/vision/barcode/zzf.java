package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zzf implements Parcelable.Creator<Barcode.DriverLicense> {
    static void zza(Barcode.DriverLicense driverLicense, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, driverLicense.versionCode);
        zzc.zza(parcel, 2, driverLicense.documentType, false);
        zzc.zza(parcel, 3, driverLicense.firstName, false);
        zzc.zza(parcel, 4, driverLicense.middleName, false);
        zzc.zza(parcel, 5, driverLicense.lastName, false);
        zzc.zza(parcel, 6, driverLicense.gender, false);
        zzc.zza(parcel, 7, driverLicense.addressStreet, false);
        zzc.zza(parcel, 8, driverLicense.addressCity, false);
        zzc.zza(parcel, 9, driverLicense.addressState, false);
        zzc.zza(parcel, 10, driverLicense.addressZip, false);
        zzc.zza(parcel, 11, driverLicense.licenseNumber, false);
        zzc.zza(parcel, 12, driverLicense.issueDate, false);
        zzc.zza(parcel, 13, driverLicense.expiryDate, false);
        zzc.zza(parcel, 14, driverLicense.birthDate, false);
        zzc.zza(parcel, 15, driverLicense.issuingCountry, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziY */
    public Barcode.DriverLicense createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    str5 = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    str6 = zzb.zzq(parcel, zzaT);
                    break;
                case 8:
                    str7 = zzb.zzq(parcel, zzaT);
                    break;
                case 9:
                    str8 = zzb.zzq(parcel, zzaT);
                    break;
                case 10:
                    str9 = zzb.zzq(parcel, zzaT);
                    break;
                case 11:
                    str10 = zzb.zzq(parcel, zzaT);
                    break;
                case 12:
                    str11 = zzb.zzq(parcel, zzaT);
                    break;
                case 13:
                    str12 = zzb.zzq(parcel, zzaT);
                    break;
                case 14:
                    str13 = zzb.zzq(parcel, zzaT);
                    break;
                case 15:
                    str14 = zzb.zzq(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new Barcode.DriverLicense(i, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznn */
    public Barcode.DriverLicense[] newArray(int i) {
        return new Barcode.DriverLicense[i];
    }
}
