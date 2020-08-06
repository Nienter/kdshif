package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzad;

public class zzaxz extends zza {
    public static final Parcelable.Creator<zzaxz> CREATOR = new zzaya();
    final int mVersionCode;
    final zzad zzbCr;

    zzaxz(int i, zzad zzad) {
        this.mVersionCode = i;
        this.zzbCr = zzad;
    }

    public zzaxz(zzad zzad) {
        this(1, zzad);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaya.zza(this, parcel, i);
    }

    public zzad zzOo() {
        return this.zzbCr;
    }
}
