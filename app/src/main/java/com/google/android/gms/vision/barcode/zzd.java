package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zzd implements Parcelable.Creator<Barcode.CalendarEvent> {
    static void zza(Barcode.CalendarEvent calendarEvent, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, calendarEvent.versionCode);
        zzc.zza(parcel, 2, calendarEvent.summary, false);
        zzc.zza(parcel, 3, calendarEvent.description, false);
        zzc.zza(parcel, 4, calendarEvent.location, false);
        zzc.zza(parcel, 5, calendarEvent.organizer, false);
        zzc.zza(parcel, 6, calendarEvent.status, false);
        zzc.zza(parcel, 7, (Parcelable) calendarEvent.start, i, false);
        zzc.zza(parcel, 8, (Parcelable) calendarEvent.end, i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziW */
    public Barcode.CalendarEvent createFromParcel(Parcel parcel) {
        Barcode.CalendarDateTime calendarDateTime = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        Barcode.CalendarDateTime calendarDateTime2 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str5 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 7:
                    calendarDateTime2 = (Barcode.CalendarDateTime) zzb.zza(parcel, zzaT, Barcode.CalendarDateTime.CREATOR);
                    break;
                case 8:
                    calendarDateTime = (Barcode.CalendarDateTime) zzb.zza(parcel, zzaT, Barcode.CalendarDateTime.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new Barcode.CalendarEvent(i, str5, str4, str3, str2, str, calendarDateTime2, calendarDateTime);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznl */
    public Barcode.CalendarEvent[] newArray(int i) {
        return new Barcode.CalendarEvent[i];
    }
}
