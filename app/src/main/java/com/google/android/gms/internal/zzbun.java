package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbun;

public abstract class zzbun<M extends zzbun<M>> extends zzbut {
    protected zzbup zzcrX;

    public final <T> T zza(zzbuo<M, T> zzbuo) {
        if (this.zzcrX == null) {
            return null;
        }
        zzbuq zzqx = this.zzcrX.zzqx(zzbuw.zzqB(zzbuo.tag));
        if (zzqx != null) {
            return zzqx.zzb(zzbuo);
        }
        return null;
    }

    public void zza(zzbum zzbum) {
        if (this.zzcrX != null) {
            for (int i = 0; i < this.zzcrX.size(); i++) {
                this.zzcrX.zzqy(i).zza(zzbum);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbul zzbul, int i) {
        int position = zzbul.getPosition();
        if (!zzbul.zzqh(i)) {
            return false;
        }
        int zzqB = zzbuw.zzqB(i);
        zzbuv zzbuv = new zzbuv(i, zzbul.zzE(position, zzbul.getPosition() - position));
        zzbuq zzbuq = null;
        if (this.zzcrX == null) {
            this.zzcrX = new zzbup();
        } else {
            zzbuq = this.zzcrX.zzqx(zzqB);
        }
        if (zzbuq == null) {
            zzbuq = new zzbuq();
            this.zzcrX.zza(zzqB, zzbuq);
        }
        zzbuq.zza(zzbuv);
        return true;
    }

    /* renamed from: zzacN */
    public M clone() {
        M m = (zzbun) super.clone();
        zzbur.zza(this, (zzbun) m);
        return m;
    }

    public /* synthetic */ zzbut zzacO() {
        return (zzbun) clone();
    }

    /* access modifiers changed from: protected */
    public int zzv() {
        if (this.zzcrX == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzcrX.size(); i2++) {
            i += this.zzcrX.zzqy(i2).zzv();
        }
        return i;
    }
}
