package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzmb
public class zzmv extends zza {
    public static final Parcelable.Creator<zzmv> CREATOR = new zzmw();
    final int mVersionCode;
    String zzFy;

    zzmv(int i, String str) {
        this.mVersionCode = i;
        this.zzFy = str;
    }

    public zzmv(String str) {
        this.mVersionCode = 1;
        this.zzFy = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzmw.zza(this, parcel, i);
    }

    public String zzje() {
        return this.zzFy;
    }
}
