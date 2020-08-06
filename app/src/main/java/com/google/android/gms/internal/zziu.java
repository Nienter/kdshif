package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.LinkedList;

@zzmb
class zziu {
    private final LinkedList<zza> zzIC = new LinkedList<>();
    /* access modifiers changed from: private */
    public zzdy zzID;
    private final int zzIE;
    private boolean zzIF;
    /* access modifiers changed from: private */
    public final String zztq;

    class zza {
        zzl zzIG;
        @Nullable
        zzdy zzIH;
        zziq zzII;
        long zzIJ;
        boolean zzIK;
        boolean zzIL;

        zza(zzip zzip) {
            this.zzIG = zzip.zzah(zziu.this.zztq);
            this.zzII = new zziq();
            this.zzII.zzc(this.zzIG);
        }

        zza(zziu zziu, zzip zzip, zzdy zzdy) {
            this(zzip);
            this.zzIH = zzdy;
        }

        /* access modifiers changed from: package-private */
        public void zzgq() {
            if (!this.zzIK) {
                this.zzIL = this.zzIG.zzb(zzis.zzl(this.zzIH != null ? this.zzIH : zziu.this.zzID));
                this.zzIK = true;
                this.zzIJ = zzv.zzcP().currentTimeMillis();
            }
        }
    }

    zziu(zzdy zzdy, String str, int i) {
        zzac.zzw(zzdy);
        zzac.zzw(str);
        this.zzID = zzdy;
        this.zztq = str;
        this.zzIE = i;
    }

    /* access modifiers changed from: package-private */
    public String getAdUnitId() {
        return this.zztq;
    }

    /* access modifiers changed from: package-private */
    public int getNetworkType() {
        return this.zzIE;
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.zzIC.size();
    }

    /* access modifiers changed from: package-private */
    public void zza(zzip zzip, zzdy zzdy) {
        this.zzIC.add(new zza(this, zzip, zzdy));
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzip zzip) {
        zza zza2 = new zza(zzip);
        this.zzIC.add(zza2);
        zza2.zzgq();
    }

    /* access modifiers changed from: package-private */
    public zzdy zzgl() {
        return this.zzID;
    }

    /* access modifiers changed from: package-private */
    public int zzgm() {
        int i = 0;
        Iterator it = this.zzIC.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = ((zza) it.next()).zzIK ? i2 + 1 : i2;
        }
    }

    /* access modifiers changed from: package-private */
    public void zzgn() {
        Iterator it = this.zzIC.iterator();
        while (it.hasNext()) {
            ((zza) it.next()).zzgq();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzgo() {
        this.zzIF = true;
    }

    /* access modifiers changed from: package-private */
    public boolean zzgp() {
        return this.zzIF;
    }

    /* access modifiers changed from: package-private */
    public zza zzp(@Nullable zzdy zzdy) {
        if (zzdy != null) {
            this.zzID = zzdy;
        }
        return this.zzIC.remove();
    }
}
