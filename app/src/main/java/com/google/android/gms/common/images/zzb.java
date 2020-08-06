package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzb implements Parcelable.Creator<WebImage> {
    static void zza(WebImage webImage, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, webImage.mVersionCode);
        zzc.zza(parcel, 2, (Parcelable) webImage.getUrl(), i, false);
        zzc.zzc(parcel, 3, webImage.getWidth());
        zzc.zzc(parcel, 4, webImage.getHeight());
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaL */
    public WebImage createFromParcel(Parcel parcel) {
        int zzg;
        int i;
        Uri uri;
        int i2;
        int i3 = 0;
        int zzaU = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(parcel);
        Uri uri2 = null;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(zzaT)) {
                case 1:
                    int i6 = i3;
                    i = i4;
                    uri = uri2;
                    i2 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaT);
                    zzg = i6;
                    break;
                case 2:
                    i2 = i5;
                    int i7 = i4;
                    uri = (Uri) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Uri.CREATOR);
                    zzg = i3;
                    i = i7;
                    break;
                case 3:
                    uri = uri2;
                    i2 = i5;
                    int i8 = i3;
                    i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaT);
                    zzg = i8;
                    break;
                case 4:
                    zzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaT);
                    i = i4;
                    uri = uri2;
                    i2 = i5;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, zzaT);
                    zzg = i3;
                    i = i4;
                    uri = uri2;
                    i2 = i5;
                    break;
            }
            i5 = i2;
            uri2 = uri;
            i4 = i;
            i3 = zzg;
        }
        if (parcel.dataPosition() == zzaU) {
            return new WebImage(i5, uri2, i4, i3);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzcJ */
    public WebImage[] newArray(int i) {
        return new WebImage[i];
    }
}
