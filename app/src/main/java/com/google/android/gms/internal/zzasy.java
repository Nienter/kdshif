package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;

class zzasy {
    final String mName;
    final String zzVQ;
    final long zzbqJ;
    final long zzbqK;
    final long zzbqL;

    zzasy(String str, String str2, long j, long j2, long j3) {
        boolean z = true;
        zzac.zzdv(str);
        zzac.zzdv(str2);
        zzac.zzas(j >= 0);
        zzac.zzas(j2 < 0 ? false : z);
        this.zzVQ = str;
        this.mName = str2;
        this.zzbqJ = j;
        this.zzbqK = j2;
        this.zzbqL = j3;
    }

    /* access modifiers changed from: package-private */
    public zzasy zzKX() {
        return new zzasy(this.zzVQ, this.mName, this.zzbqJ + 1, this.zzbqK + 1, this.zzbqL);
    }

    /* access modifiers changed from: package-private */
    public zzasy zzan(long j) {
        return new zzasy(this.zzVQ, this.mName, this.zzbqJ, this.zzbqK, j);
    }
}
