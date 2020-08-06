package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.vision.Frame;

public class zzbhd extends zza {
    public static final Parcelable.Creator<zzbhd> CREATOR = new zzbhe();
    public int height;

    /* renamed from: id */
    public int f1726id;
    public int rotation;
    final int versionCode;
    public int width;
    public long zzbNq;

    public zzbhd() {
        this.versionCode = 1;
    }

    public zzbhd(int i, int i2, int i3, int i4, long j, int i5) {
        this.versionCode = i;
        this.width = i2;
        this.height = i3;
        this.f1726id = i4;
        this.zzbNq = j;
        this.rotation = i5;
    }

    public static zzbhd zzc(Frame frame) {
        zzbhd zzbhd = new zzbhd();
        zzbhd.width = frame.getMetadata().getWidth();
        zzbhd.height = frame.getMetadata().getHeight();
        zzbhd.rotation = frame.getMetadata().getRotation();
        zzbhd.f1726id = frame.getMetadata().getId();
        zzbhd.zzbNq = frame.getMetadata().getTimestampMillis();
        return zzbhd;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzbhe.zza(this, parcel, i);
    }
}
