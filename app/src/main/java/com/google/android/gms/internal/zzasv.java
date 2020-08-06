package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzac;

abstract class zzasv {
    private static volatile Handler zzaec;
    /* access modifiers changed from: private */
    public volatile long zzaed;
    /* access modifiers changed from: private */
    public final zzatp zzbpw;
    /* access modifiers changed from: private */
    public boolean zzbqB = true;
    private final Runnable zzv = new Runnable() {
        public void run() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                zzasv.this.zzbpw.zzJs().zzm(this);
                return;
            }
            boolean zzcv = zzasv.this.zzcv();
            long unused = zzasv.this.zzaed = 0;
            if (zzcv && zzasv.this.zzbqB) {
                zzasv.this.run();
            }
        }
    };

    zzasv(zzatp zzatp) {
        zzac.zzw(zzatp);
        this.zzbpw = zzatp;
    }

    private Handler getHandler() {
        Handler handler;
        if (zzaec != null) {
            return zzaec;
        }
        synchronized (zzasv.class) {
            if (zzaec == null) {
                zzaec = new Handler(this.zzbpw.getContext().getMainLooper());
            }
            handler = zzaec;
        }
        return handler;
    }

    public void cancel() {
        this.zzaed = 0;
        getHandler().removeCallbacks(this.zzv);
    }

    public abstract void run();

    public boolean zzcv() {
        return this.zzaed != 0;
    }

    public void zzx(long j) {
        cancel();
        if (j >= 0) {
            this.zzaed = this.zzbpw.zznq().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzv, j)) {
                this.zzbpw.zzJt().zzLa().zzj("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }
}
