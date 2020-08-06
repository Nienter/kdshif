package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class zzawa extends zza implements Comparable<zzawa> {
    public static final Parcelable.Creator<zzawa> CREATOR = new zzawb();
    final int mVersionCode;
    public final int zzbzp;
    public final zzawc[] zzbzq;
    public final String[] zzbzr;
    public final Map<String, zzawc> zzbzs = new TreeMap();

    zzawa(int i, int i2, zzawc[] zzawcArr, String[] strArr) {
        this.mVersionCode = i;
        this.zzbzp = i2;
        this.zzbzq = zzawcArr;
        for (zzawc zzawc : zzawcArr) {
            this.zzbzs.put(zzawc.name, zzawc);
        }
        this.zzbzr = strArr;
        if (this.zzbzr != null) {
            Arrays.sort(this.zzbzr);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzawa)) {
            return false;
        }
        zzawa zzawa = (zzawa) obj;
        return this.mVersionCode == zzawa.mVersionCode && this.zzbzp == zzawa.zzbzp && zzaa.equal(this.zzbzs, zzawa.zzbzs) && Arrays.equals(this.zzbzr, zzawa.zzbzr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.mVersionCode);
        sb.append(", ");
        sb.append(this.zzbzp);
        sb.append(", ");
        sb.append("(");
        for (zzawc append : this.zzbzs.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        if (this.zzbzr != null) {
            for (String append2 : this.zzbzr) {
                sb.append(append2);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzawb.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(zzawa zzawa) {
        return this.zzbzp - zzawa.zzbzp;
    }
}
