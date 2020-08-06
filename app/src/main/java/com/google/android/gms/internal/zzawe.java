package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;

public class zzawe extends zza {
    public static final Parcelable.Creator<zzawe> CREATOR = new zzawf();
    public final String packageName;
    public final int versionCode;
    public final String zzbzA;
    public final String zzbzB;
    public final boolean zzbzC;
    public final String zzbzD;
    public final boolean zzbzE;
    public final int zzbzF;
    public final int zzbzy;
    public final int zzbzz;

    public zzawe(int i, String str, int i2, int i3, String str2, String str3, boolean z, String str4, boolean z2, int i4) {
        this.versionCode = i;
        this.packageName = str;
        this.zzbzy = i2;
        this.zzbzz = i3;
        this.zzbzA = str2;
        this.zzbzB = str3;
        this.zzbzC = z;
        this.zzbzD = str4;
        this.zzbzE = z2;
        this.zzbzF = i4;
    }

    public zzawe(String str, int i, int i2, String str2, String str3, String str4, boolean z, int i3) {
        this.versionCode = 1;
        this.packageName = (String) zzac.zzw(str);
        this.zzbzy = i;
        this.zzbzz = i2;
        this.zzbzD = str2;
        this.zzbzA = str3;
        this.zzbzB = str4;
        this.zzbzC = !z;
        this.zzbzE = z;
        this.zzbzF = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzawe)) {
            return false;
        }
        zzawe zzawe = (zzawe) obj;
        return this.versionCode == zzawe.versionCode && this.packageName.equals(zzawe.packageName) && this.zzbzy == zzawe.zzbzy && this.zzbzz == zzawe.zzbzz && zzaa.equal(this.zzbzD, zzawe.zzbzD) && zzaa.equal(this.zzbzA, zzawe.zzbzA) && zzaa.equal(this.zzbzB, zzawe.zzbzB) && this.zzbzC == zzawe.zzbzC && this.zzbzE == zzawe.zzbzE && this.zzbzF == zzawe.zzbzF;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.versionCode), this.packageName, Integer.valueOf(this.zzbzy), Integer.valueOf(this.zzbzz), this.zzbzD, this.zzbzA, this.zzbzB, Boolean.valueOf(this.zzbzC), Boolean.valueOf(this.zzbzE), Integer.valueOf(this.zzbzF));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("versionCode=").append(this.versionCode).append(',');
        sb.append("package=").append(this.packageName).append(',');
        sb.append("packageVersionCode=").append(this.zzbzy).append(',');
        sb.append("logSource=").append(this.zzbzz).append(',');
        sb.append("logSourceName=").append(this.zzbzD).append(',');
        sb.append("uploadAccount=").append(this.zzbzA).append(',');
        sb.append("loggingId=").append(this.zzbzB).append(',');
        sb.append("logAndroidId=").append(this.zzbzC).append(',');
        sb.append("isAnonymous=").append(this.zzbzE).append(',');
        sb.append("qosTier=").append(this.zzbzF);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzawf.zza(this, parcel, i);
    }
}
