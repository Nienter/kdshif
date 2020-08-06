package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzmb
public final class zznx extends zza {
    public static final Parcelable.Creator<zznx> CREATOR = new zzny();
    public final int versionCode;
    public final zzdy zzRd;
    public final String zzvd;

    public zznx(int i, zzdy zzdy, String str) {
        this.versionCode = i;
        this.zzRd = zzdy;
        this.zzvd = str;
    }

    public zznx(zzdy zzdy, String str) {
        this(1, zzdy, str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzny.zza(this, parcel, i);
    }
}
