package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zzh implements Parcelable.Creator<Barcode.GeoPoint> {
    static void zza(Barcode.GeoPoint geoPoint, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, geoPoint.versionCode);
        zzc.zza(parcel, 2, geoPoint.lat);
        zzc.zza(parcel, 3, geoPoint.lng);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzja */
    public Barcode.GeoPoint createFromParcel(Parcel parcel) {
        double d = 0.0d;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    d2 = zzb.zzn(parcel, zzaT);
                    break;
                case 3:
                    d = zzb.zzn(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new Barcode.GeoPoint(i, d2, d);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznp */
    public Barcode.GeoPoint[] newArray(int i) {
        return new Barcode.GeoPoint[i];
    }
}
