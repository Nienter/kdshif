package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzby implements Callable {
    protected final String TAG = getClass().getSimpleName();
    protected final String className;
    protected final zzbc zzpC;
    protected final zzaf.zza zzra;
    protected final String zzrh;
    protected Method zzrj;
    protected final int zzrn;
    protected final int zzro;

    public zzby(zzbc zzbc, String str, String str2, zzaf.zza zza, int i, int i2) {
        this.zzpC = zzbc;
        this.className = str;
        this.zzrh = str2;
        this.zzra = zza;
        this.zzrn = i;
        this.zzro = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void zzbe();

    /* renamed from: zzbl */
    public Void call() {
        try {
            long nanoTime = System.nanoTime();
            this.zzrj = this.zzpC.zzc(this.className, this.zzrh);
            if (this.zzrj != null) {
                zzbe();
                zzap zzaQ = this.zzpC.zzaQ();
                if (!(zzaQ == null || this.zzrn == Integer.MIN_VALUE)) {
                    zzaQ.zza(this.zzro, this.zzrn, (System.nanoTime() - nanoTime) / 1000);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
        }
        return null;
    }
}
