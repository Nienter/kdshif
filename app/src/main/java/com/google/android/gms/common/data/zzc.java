package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;

public abstract class zzc {
    protected int zzaCm;
    private int zzaCn;
    protected final DataHolder zzazI;

    public zzc(DataHolder dataHolder, int i) {
        this.zzazI = (DataHolder) zzac.zzw(dataHolder);
        zzcA(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return zzaa.equal(Integer.valueOf(zzc.zzaCm), Integer.valueOf(this.zzaCm)) && zzaa.equal(Integer.valueOf(zzc.zzaCn), Integer.valueOf(this.zzaCn)) && zzc.zzazI == this.zzazI;
    }

    /* access modifiers changed from: protected */
    public boolean getBoolean(String str) {
        return this.zzazI.zze(str, this.zzaCm, this.zzaCn);
    }

    /* access modifiers changed from: protected */
    public byte[] getByteArray(String str) {
        return this.zzazI.zzg(str, this.zzaCm, this.zzaCn);
    }

    /* access modifiers changed from: protected */
    public float getFloat(String str) {
        return this.zzazI.zzf(str, this.zzaCm, this.zzaCn);
    }

    /* access modifiers changed from: protected */
    public int getInteger(String str) {
        return this.zzazI.zzc(str, this.zzaCm, this.zzaCn);
    }

    /* access modifiers changed from: protected */
    public long getLong(String str) {
        return this.zzazI.zzb(str, this.zzaCm, this.zzaCn);
    }

    /* access modifiers changed from: protected */
    public String getString(String str) {
        return this.zzazI.zzd(str, this.zzaCm, this.zzaCn);
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.zzaCm), Integer.valueOf(this.zzaCn), this.zzazI);
    }

    public boolean isDataValid() {
        return !this.zzazI.isClosed();
    }

    /* access modifiers changed from: protected */
    public void zza(String str, CharArrayBuffer charArrayBuffer) {
        this.zzazI.zza(str, this.zzaCm, this.zzaCn, charArrayBuffer);
    }

    /* access modifiers changed from: protected */
    public void zzcA(int i) {
        zzac.zzar(i >= 0 && i < this.zzazI.getCount());
        this.zzaCm = i;
        this.zzaCn = this.zzazI.zzcC(this.zzaCm);
    }

    public boolean zzdj(String str) {
        return this.zzazI.zzdj(str);
    }

    /* access modifiers changed from: protected */
    public Uri zzdk(String str) {
        return this.zzazI.zzh(str, this.zzaCm, this.zzaCn);
    }

    /* access modifiers changed from: protected */
    public boolean zzdl(String str) {
        return this.zzazI.zzi(str, this.zzaCm, this.zzaCn);
    }

    /* access modifiers changed from: protected */
    public int zzwB() {
        return this.zzaCm;
    }
}
