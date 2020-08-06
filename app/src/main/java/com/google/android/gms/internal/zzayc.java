package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzaf;

public class zzayc implements Parcelable.Creator<zzayb> {
    static void zza(zzayb zzayb, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzayb.mVersionCode);
        zzc.zza(parcel, 2, (Parcelable) zzayb.zzxA(), i, false);
        zzc.zza(parcel, 3, (Parcelable) zzayb.zzOp(), i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziS */
    public zzayb createFromParcel(Parcel parcel) {
        zzaf zzaf;
        ConnectionResult connectionResult;
        int i;
        zzaf zzaf2 = null;
        int zzaU = zzb.zzaU(parcel);
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    zzaf zzaf3 = zzaf2;
                    connectionResult = connectionResult2;
                    i = zzb.zzg(parcel, zzaT);
                    zzaf = zzaf3;
                    break;
                case 2:
                    i = i2;
                    ConnectionResult connectionResult3 = (ConnectionResult) zzb.zza(parcel, zzaT, ConnectionResult.CREATOR);
                    zzaf = zzaf2;
                    connectionResult = connectionResult3;
                    break;
                case 3:
                    zzaf = (zzaf) zzb.zza(parcel, zzaT, zzaf.CREATOR);
                    connectionResult = connectionResult2;
                    i = i2;
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    zzaf = zzaf2;
                    connectionResult = connectionResult2;
                    i = i2;
                    break;
            }
            i2 = i;
            connectionResult2 = connectionResult;
            zzaf2 = zzaf;
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzayb(i2, connectionResult2, zzaf2);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzmN */
    public zzayb[] newArray(int i) {
        return new zzayb[i];
    }
}
