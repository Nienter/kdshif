package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzbha extends zza {
    public static final Parcelable.Creator<zzbha> CREATOR = new zzbhb();
    public final int type;
    public final int versionCode;

    /* renamed from: x */
    public final float f1724x;

    /* renamed from: y */
    public final float f1725y;

    public zzbha(int i, float f, float f2, int i2) {
        this.versionCode = i;
        this.f1724x = f;
        this.f1725y = f2;
        this.type = i2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzbhb.zza(this, parcel, i);
    }
}
