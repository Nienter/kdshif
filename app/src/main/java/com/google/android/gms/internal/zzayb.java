package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaf;

public class zzayb extends zza {
    public static final Parcelable.Creator<zzayb> CREATOR = new zzayc();
    final int mVersionCode;
    private final ConnectionResult zzaFh;
    private final zzaf zzbCs;

    public zzayb(int i) {
        this(new ConnectionResult(i, null), null);
    }

    zzayb(int i, ConnectionResult connectionResult, zzaf zzaf) {
        this.mVersionCode = i;
        this.zzaFh = connectionResult;
        this.zzbCs = zzaf;
    }

    public zzayb(ConnectionResult connectionResult, zzaf zzaf) {
        this(1, connectionResult, zzaf);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzayc.zza(this, parcel, i);
    }

    public zzaf zzOp() {
        return this.zzbCs;
    }

    public ConnectionResult zzxA() {
        return this.zzaFh;
    }
}
