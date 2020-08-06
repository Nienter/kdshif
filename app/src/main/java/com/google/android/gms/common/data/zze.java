package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zze implements Parcelable.Creator<DataHolder> {
    static void zza(DataHolder dataHolder, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zza(parcel, 1, dataHolder.zzwE(), false);
        zzc.zza(parcel, 2, (T[]) dataHolder.zzwF(), i, false);
        zzc.zzc(parcel, 3, dataHolder.getStatusCode());
        zzc.zza(parcel, 4, dataHolder.zzwy(), false);
        zzc.zzc(parcel, 1000, dataHolder.mVersionCode);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaK */
    public DataHolder createFromParcel(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int zzaU = zzb.zzaU(parcel);
        CursorWindow[] cursorWindowArr = null;
        String[] strArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    strArr = zzb.zzC(parcel, zzaT);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) zzb.zzb(parcel, zzaT, CursorWindow.CREATOR);
                    break;
                case 3:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 4:
                    bundle = zzb.zzs(parcel, zzaT);
                    break;
                case 1000:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() != zzaU) {
            throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
        }
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i, bundle);
        dataHolder.zzwD();
        return dataHolder;
    }

    /* renamed from: zzcF */
    public DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
