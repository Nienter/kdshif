package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzqq;
import java.util.concurrent.atomic.AtomicBoolean;

@zzmb
public abstract class zzlj implements zzpk<Void>, zzqq.zza {
    protected final Context mContext;
    protected final zzqp zzGt;
    protected final zzln.zza zzPn;
    protected final zzov.zza zzPo;
    protected zzmk zzPp;
    private Runnable zzPq;
    protected final Object zzPr = new Object();
    /* access modifiers changed from: private */
    public AtomicBoolean zzPs = new AtomicBoolean(true);

    protected zzlj(Context context, zzov.zza zza, zzqp zzqp, zzln.zza zza2) {
        this.mContext = context;
        this.zzPo = zza;
        this.zzPp = this.zzPo.zzVB;
        this.zzGt = zzqp;
        this.zzPn = zza2;
    }

    private zzov zzP(int i) {
        zzmh zzmh = this.zzPo.zzSF;
        return new zzov(zzmh.zzRd, this.zzGt, this.zzPp.zzJY, i, this.zzPp.zzJZ, this.zzPp.zzRM, this.zzPp.orientation, this.zzPp.zzKe, zzmh.zzRg, this.zzPp.zzRK, null, null, null, null, null, this.zzPp.zzRL, this.zzPo.zzvj, this.zzPp.zzRJ, this.zzPo.zzVv, this.zzPp.zzRO, this.zzPp.zzRP, this.zzPo.zzVp, null, this.zzPp.zzRZ, this.zzPp.zzSa, this.zzPp.zzSb, this.zzPp.zzSc, this.zzPp.zzSd, null, this.zzPp.zzKb, this.zzPp.zzSg);
    }

    public void cancel() {
        if (this.zzPs.getAndSet(false)) {
            this.zzGt.stopLoading();
            zzv.zzcL().zzl(this.zzGt);
            zzO(-1);
            zzpi.zzWR.removeCallbacks(this.zzPq);
        }
    }

    /* access modifiers changed from: protected */
    public void zzO(int i) {
        if (i != -2) {
            this.zzPp = new zzmk(i, this.zzPp.zzKe);
        }
        this.zzGt.zzkQ();
        this.zzPn.zzb(zzP(i));
    }

    public void zza(zzqp zzqp, boolean z) {
        int i = 0;
        zzpe.zzbc("WebView finished loading.");
        if (this.zzPs.getAndSet(false)) {
            if (z) {
                i = zziv();
            }
            zzO(i);
            zzpi.zzWR.removeCallbacks(this.zzPq);
        }
    }

    /* renamed from: zzit */
    public final Void zziw() {
        zzac.zzdn("Webview render task needs to be called on UI thread.");
        this.zzPq = new Runnable() {
            public void run() {
                if (zzlj.this.zzPs.get()) {
                    zzpe.m2432e("Timed out waiting for WebView to finish loading.");
                    zzlj.this.cancel();
                }
            }
        };
        zzpi.zzWR.postDelayed(this.zzPq, zzfx.zzDe.get().longValue());
        zziu();
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void zziu();

    /* access modifiers changed from: protected */
    public int zziv() {
        return -2;
    }
}
