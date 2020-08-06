package com.google.android.gms.internal;

import java.util.Arrays;

final class zzbuv {
    final int tag;
    final byte[] zzcsh;

    zzbuv(int i, byte[] bArr) {
        this.tag = i;
        this.zzcsh = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbuv)) {
            return false;
        }
        zzbuv zzbuv = (zzbuv) obj;
        return this.tag == zzbuv.tag && Arrays.equals(this.zzcsh, zzbuv.zzcsh);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzcsh);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbum zzbum) {
        zzbum.zzqt(this.tag);
        zzbum.zzah(this.zzcsh);
    }

    /* access modifiers changed from: package-private */
    public int zzv() {
        return zzbum.zzqu(this.tag) + 0 + this.zzcsh.length;
    }
}
