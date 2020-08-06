package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.vision.barcode.Barcode;

public class zzl implements Parcelable.Creator<Barcode.UrlBookmark> {
    static void zza(Barcode.UrlBookmark urlBookmark, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, urlBookmark.versionCode);
        zzc.zza(parcel, 2, urlBookmark.title, false);
        zzc.zza(parcel, 3, urlBookmark.url, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzje */
    public Barcode.UrlBookmark createFromParcel(Parcel parcel) {
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str2 = zzb.zzq(parcel, zzaT);
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
            return new Barcode.UrlBookmark(i, str2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zznt */
    public Barcode.UrlBookmark[] newArray(int i) {
        return new Barcode.UrlBookmark[i];
    }
}
