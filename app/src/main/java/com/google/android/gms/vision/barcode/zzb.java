package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zzb implements Parcelable.Creator<Barcode> {
    static void zza(Barcode barcode, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, barcode.versionCode);
        zzc.zzc(parcel, 2, barcode.format);
        zzc.zza(parcel, 3, barcode.rawValue, false);
        zzc.zza(parcel, 4, barcode.displayValue, false);
        zzc.zzc(parcel, 5, barcode.valueFormat);
        zzc.zza(parcel, 6, (T[]) barcode.cornerPoints, i, false);
        zzc.zza(parcel, 7, (Parcelable) barcode.email, i, false);
        zzc.zza(parcel, 8, (Parcelable) barcode.phone, i, false);
        zzc.zza(parcel, 9, (Parcelable) barcode.sms, i, false);
        zzc.zza(parcel, 10, (Parcelable) barcode.wifi, i, false);
        zzc.zza(parcel, 11, (Parcelable) barcode.url, i, false);
        zzc.zza(parcel, 12, (Parcelable) barcode.geoPoint, i, false);
        zzc.zza(parcel, 13, (Parcelable) barcode.calendarEvent, i, false);
        zzc.zza(parcel, 14, (Parcelable) barcode.contactInfo, i, false);
        zzc.zza(parcel, 15, (Parcelable) barcode.driverLicense, i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziU */
    public Barcode createFromParcel(Parcel parcel) {
        int zzaU = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        int i3 = 0;
        Point[] pointArr = null;
        Barcode.Email email = null;
        Barcode.Phone phone = null;
        Barcode.Sms sms = null;
        Barcode.WiFi wiFi = null;
        Barcode.UrlBookmark urlBookmark = null;
        Barcode.GeoPoint geoPoint = null;
        Barcode.CalendarEvent calendarEvent = null;
        Barcode.ContactInfo contactInfo = null;
        Barcode.DriverLicense driverLicense = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(zzaT)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    i2 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaT);
                    break;
                case 3:
                    str = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    i3 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaT);
                    break;
                case 6:
                    pointArr = (Point[]) com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, zzaT, Point.CREATOR);
                    break;
                case 7:
                    email = (Barcode.Email) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.Email.CREATOR);
                    break;
                case 8:
                    phone = (Barcode.Phone) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.Phone.CREATOR);
                    break;
                case 9:
                    sms = (Barcode.Sms) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.Sms.CREATOR);
                    break;
                case 10:
                    wiFi = (Barcode.WiFi) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.WiFi.CREATOR);
                    break;
                case 11:
                    urlBookmark = (Barcode.UrlBookmark) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.UrlBookmark.CREATOR);
                    break;
                case 12:
                    geoPoint = (Barcode.GeoPoint) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.GeoPoint.CREATOR);
                    break;
                case 13:
                    calendarEvent = (Barcode.CalendarEvent) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.CalendarEvent.CREATOR);
                    break;
                case 14:
                    contactInfo = (Barcode.ContactInfo) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.ContactInfo.CREATOR);
                    break;
                case 15:
                    driverLicense = (Barcode.DriverLicense) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Barcode.DriverLicense.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new Barcode(i, i2, str, str2, i3, pointArr, email, phone, sms, wiFi, urlBookmark, geoPoint, calendarEvent, contactInfo, driverLicense);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznj */
    public Barcode[] newArray(int i) {
        return new Barcode[i];
    }
}
