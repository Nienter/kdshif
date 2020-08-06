package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.List;

@zzmb
public final class zzdy extends zza {
    public static final Parcelable.Creator<zzdy> CREATOR = new zzea();
    public final Bundle extras;
    public final int versionCode;
    public final long zzyF;
    public final int zzyG;
    public final List<String> zzyH;
    public final boolean zzyI;
    public final int zzyJ;
    public final boolean zzyK;
    public final String zzyL;
    public final zzfj zzyM;
    public final Location zzyN;
    public final String zzyO;
    public final Bundle zzyP;
    public final Bundle zzyQ;
    public final List<String> zzyR;
    public final String zzyS;
    public final String zzyT;
    public final boolean zzyU;

    public zzdy(int i, long j, Bundle bundle, int i2, List<String> list, boolean z, int i3, boolean z2, String str, zzfj zzfj, Location location, String str2, Bundle bundle2, Bundle bundle3, List<String> list2, String str3, String str4, boolean z3) {
        this.versionCode = i;
        this.zzyF = j;
        this.extras = bundle == null ? new Bundle() : bundle;
        this.zzyG = i2;
        this.zzyH = list;
        this.zzyI = z;
        this.zzyJ = i3;
        this.zzyK = z2;
        this.zzyL = str;
        this.zzyM = zzfj;
        this.zzyN = location;
        this.zzyO = str2;
        this.zzyP = bundle2 == null ? new Bundle() : bundle2;
        this.zzyQ = bundle3;
        this.zzyR = list2;
        this.zzyS = str3;
        this.zzyT = str4;
        this.zzyU = z3;
    }

    public static void zzj(zzdy zzdy) {
        zzdy.zzyP.putBundle("com.google.ads.mediation.admob.AdMobAdapter", zzdy.extras);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzdy)) {
            return false;
        }
        zzdy zzdy = (zzdy) obj;
        return this.versionCode == zzdy.versionCode && this.zzyF == zzdy.zzyF && zzaa.equal(this.extras, zzdy.extras) && this.zzyG == zzdy.zzyG && zzaa.equal(this.zzyH, zzdy.zzyH) && this.zzyI == zzdy.zzyI && this.zzyJ == zzdy.zzyJ && this.zzyK == zzdy.zzyK && zzaa.equal(this.zzyL, zzdy.zzyL) && zzaa.equal(this.zzyM, zzdy.zzyM) && zzaa.equal(this.zzyN, zzdy.zzyN) && zzaa.equal(this.zzyO, zzdy.zzyO) && zzaa.equal(this.zzyP, zzdy.zzyP) && zzaa.equal(this.zzyQ, zzdy.zzyQ) && zzaa.equal(this.zzyR, zzdy.zzyR) && zzaa.equal(this.zzyS, zzdy.zzyS) && zzaa.equal(this.zzyT, zzdy.zzyT) && this.zzyU == zzdy.zzyU;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.versionCode), Long.valueOf(this.zzyF), this.extras, Integer.valueOf(this.zzyG), this.zzyH, Boolean.valueOf(this.zzyI), Integer.valueOf(this.zzyJ), Boolean.valueOf(this.zzyK), this.zzyL, this.zzyM, this.zzyN, this.zzyO, this.zzyP, this.zzyQ, this.zzyR, this.zzyS, this.zzyT, Boolean.valueOf(this.zzyU));
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzea.zza(this, parcel, i);
    }
}
