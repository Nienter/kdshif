package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzov;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzmb
public class zzls extends zzpd {
    private final zzlu zzPP;
    private Future<zzov> zzPQ;
    /* access modifiers changed from: private */
    public final zzln.zza zzPn;
    private final zzov.zza zzPo;
    private final zzmk zzPp;
    private final Object zzrN;

    public zzls(Context context, zzr zzr, zzov.zza zza, zzav zzav, zzln.zza zza2, zzgf zzgf) {
        this(zza, zza2, new zzlu(context, zzr, new zzpp(context), zzav, zza, zzgf));
    }

    zzls(zzov.zza zza, zzln.zza zza2, zzlu zzlu) {
        this.zzrN = new Object();
        this.zzPo = zza;
        this.zzPp = zza.zzVB;
        this.zzPn = zza2;
        this.zzPP = zzlu;
    }

    private zzov zzQ(int i) {
        return new zzov(this.zzPo.zzSF.zzRd, null, null, i, null, null, this.zzPp.orientation, this.zzPp.zzKe, this.zzPo.zzSF.zzRg, false, null, null, null, null, null, this.zzPp.zzRL, this.zzPo.zzvj, this.zzPp.zzRJ, this.zzPo.zzVv, this.zzPp.zzRO, this.zzPp.zzRP, this.zzPo.zzVp, null, null, null, null, this.zzPo.zzVB.zzSc, this.zzPo.zzVB.zzSd, null, null, this.zzPp.zzSg);
    }

    public void onStop() {
        synchronized (this.zzrN) {
            if (this.zzPQ != null) {
                this.zzPQ.cancel(true);
            }
        }
    }

    public void zzcm() {
        final zzov zzov;
        int i;
        try {
            synchronized (this.zzrN) {
                this.zzPQ = zzph.zza(this.zzPP);
            }
            zzov = this.zzPQ.get(60000, TimeUnit.MILLISECONDS);
            i = -2;
        } catch (TimeoutException e) {
            zzpe.zzbe("Timed out waiting for native ad.");
            this.zzPQ.cancel(true);
            i = 2;
            zzov = null;
        } catch (ExecutionException e2) {
            zzov = null;
            i = 0;
        } catch (InterruptedException e3) {
            zzov = null;
            i = 0;
        } catch (CancellationException e4) {
            zzov = null;
            i = 0;
        }
        if (zzov == null) {
            zzov = zzQ(i);
        }
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                zzls.this.zzPn.zzb(zzov);
            }
        });
    }
}
