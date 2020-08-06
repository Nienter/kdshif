package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zza implements Parcelable.Creator<BitmapTeleporter> {
    static void zza(BitmapTeleporter bitmapTeleporter, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, bitmapTeleporter.mVersionCode);
        zzc.zza(parcel, 2, (Parcelable) bitmapTeleporter.zzSn, i, false);
        zzc.zzc(parcel, 3, bitmapTeleporter.zzanR);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaJ */
    public BitmapTeleporter createFromParcel(Parcel parcel) {
        int zzg;
        ParcelFileDescriptor parcelFileDescriptor;
        int i;
        int i2 = 0;
        int zzaU = zzb.zzaU(parcel);
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    int i4 = i2;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = zzb.zzg(parcel, zzaT);
                    zzg = i4;
                    break;
                case 2:
                    i = i3;
                    ParcelFileDescriptor parcelFileDescriptor3 = (ParcelFileDescriptor) zzb.zza(parcel, zzaT, ParcelFileDescriptor.CREATOR);
                    zzg = i2;
                    parcelFileDescriptor = parcelFileDescriptor3;
                    break;
                case 3:
                    zzg = zzb.zzg(parcel, zzaT);
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = i3;
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    zzg = i2;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = i3;
                    break;
            }
            i3 = i;
            parcelFileDescriptor2 = parcelFileDescriptor;
            i2 = zzg;
        }
        if (parcel.dataPosition() == zzaU) {
            return new BitmapTeleporter(i3, parcelFileDescriptor2, i2);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzcz */
    public BitmapTeleporter[] newArray(int i) {
        return new BitmapTeleporter[i];
    }
}
