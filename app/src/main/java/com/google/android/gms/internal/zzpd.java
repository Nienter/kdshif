package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzmb
public abstract class zzpd implements zzpk<Future> {
    /* access modifiers changed from: private */
    public volatile Thread zzWx;
    private boolean zzWy;
    private final Runnable zzv;

    public zzpd() {
        this.zzv = new Runnable() {
            public final void run() {
                Thread unused = zzpd.this.zzWx = Thread.currentThread();
                zzpd.this.zzcm();
            }
        };
        this.zzWy = false;
    }

    public zzpd(boolean z) {
        this.zzv = new Runnable() {
            public final void run() {
                Thread unused = zzpd.this.zzWx = Thread.currentThread();
                zzpd.this.zzcm();
            }
        };
        this.zzWy = z;
    }

    public final void cancel() {
        onStop();
        if (this.zzWx != null) {
            this.zzWx.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzcm();

    /* renamed from: zzkf */
    public final Future zziw() {
        return this.zzWy ? zzph.zza(1, this.zzv) : zzph.zza(this.zzv);
    }
}
