package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzaa;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public class zzawc extends com.google.android.gms.common.internal.safeparcel.zza implements Comparable<zzawc> {
    public static final Parcelable.Creator<zzawc> CREATOR = new zzawd();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final zza zzbzx = new zza();
    final int mVersionCode;
    public final String name;
    final String zzaFy;
    final boolean zzbgG;
    final double zzbgI;
    final long zzbzt;
    final byte[] zzbzu;
    public final int zzbzv;
    public final int zzbzw;

    public static class zza implements Comparator<zzawc> {
        /* renamed from: zza */
        public int compare(zzawc zzawc, zzawc zzawc2) {
            return zzawc.zzbzw == zzawc2.zzbzw ? zzawc.name.compareTo(zzawc2.name) : zzawc.zzbzw - zzawc2.zzbzw;
        }
    }

    zzawc(int i, String str, long j, boolean z, double d, String str2, byte[] bArr, int i2, int i3) {
        this.mVersionCode = i;
        this.name = str;
        this.zzbzt = j;
        this.zzbgG = z;
        this.zzbgI = d;
        this.zzaFy = str2;
        this.zzbzu = bArr;
        this.zzbzv = i2;
        this.zzbzw = i3;
    }

    private static int compare(byte b, byte b2) {
        return b - b2;
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    private static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    private static int compare(String str, String str2) {
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    private static int compare(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzawc)) {
            return false;
        }
        zzawc zzawc = (zzawc) obj;
        if (this.mVersionCode != zzawc.mVersionCode || !zzaa.equal(this.name, zzawc.name) || this.zzbzv != zzawc.zzbzv || this.zzbzw != zzawc.zzbzw) {
            return false;
        }
        switch (this.zzbzv) {
            case 1:
                return this.zzbzt == zzawc.zzbzt;
            case 2:
                return this.zzbgG == zzawc.zzbgG;
            case 3:
                return this.zzbgI == zzawc.zzbgI;
            case 4:
                return zzaa.equal(this.zzaFy, zzawc.zzaFy);
            case 5:
                return Arrays.equals(this.zzbzu, zzawc.zzbzu);
            default:
                throw new AssertionError(new StringBuilder(31).append("Invalid enum value: ").append(this.zzbzv).toString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        zza(sb);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzawd.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(zzawc zzawc) {
        int compareTo = this.name.compareTo(zzawc.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.zzbzv, zzawc.zzbzv);
        if (compare != 0) {
            return compare;
        }
        switch (this.zzbzv) {
            case 1:
                return compare(this.zzbzt, zzawc.zzbzt);
            case 2:
                return compare(this.zzbgG, zzawc.zzbgG);
            case 3:
                return Double.compare(this.zzbgI, zzawc.zzbgI);
            case 4:
                return compare(this.zzaFy, zzawc.zzaFy);
            case 5:
                if (this.zzbzu == zzawc.zzbzu) {
                    return 0;
                }
                if (this.zzbzu == null) {
                    return -1;
                }
                if (zzawc.zzbzu == null) {
                    return 1;
                }
                for (int i = 0; i < Math.min(this.zzbzu.length, zzawc.zzbzu.length); i++) {
                    int compare2 = compare(this.zzbzu[i], zzawc.zzbzu[i]);
                    if (compare2 != 0) {
                        return compare2;
                    }
                }
                return compare(this.zzbzu.length, zzawc.zzbzu.length);
            default:
                throw new AssertionError(new StringBuilder(31).append("Invalid enum value: ").append(this.zzbzv).toString());
        }
    }

    public String zza(StringBuilder sb) {
        sb.append("Flag(");
        sb.append(this.mVersionCode);
        sb.append(", ");
        sb.append(this.name);
        sb.append(", ");
        switch (this.zzbzv) {
            case 1:
                sb.append(this.zzbzt);
                break;
            case 2:
                sb.append(this.zzbgG);
                break;
            case 3:
                sb.append(this.zzbgI);
                break;
            case 4:
                sb.append("'");
                sb.append(this.zzaFy);
                sb.append("'");
                break;
            case 5:
                if (this.zzbzu != null) {
                    sb.append("'");
                    sb.append(new String(this.zzbzu, UTF_8));
                    sb.append("'");
                    break;
                } else {
                    sb.append("null");
                    break;
                }
            default:
                String str = this.name;
                throw new AssertionError(new StringBuilder(String.valueOf(str).length() + 27).append("Invalid type: ").append(str).append(", ").append(this.zzbzv).toString());
        }
        sb.append(", ");
        sb.append(this.zzbzv);
        sb.append(", ");
        sb.append(this.zzbzw);
        sb.append(")");
        return sb.toString();
    }
}
