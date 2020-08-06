package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzaq;
import com.google.android.gms.internal.zzau;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzph;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzmb
class zzi implements zzaq, Runnable {
    private zzw zzsw;
    private final List<Object[]> zztf = new Vector();
    private final AtomicReference<zzaq> zztg = new AtomicReference<>();
    CountDownLatch zzth = new CountDownLatch(1);

    public zzi(zzw zzw) {
        this.zzsw = zzw;
        if (zzeh.zzeO().zzkJ()) {
            zzph.zza((Runnable) this);
        } else {
            run();
        }
    }

    private void zzcf() {
        if (!this.zztf.isEmpty()) {
            for (Object[] next : this.zztf) {
                if (next.length == 1) {
                    this.zztg.get().zza((MotionEvent) next[0]);
                } else if (next.length == 3) {
                    this.zztg.get().zza(((Integer) next[0]).intValue(), ((Integer) next[1]).intValue(), ((Integer) next[2]).intValue());
                }
            }
            this.zztf.clear();
        }
    }

    private Context zzg(Context context) {
        if (!zzfx.zzBd.get().booleanValue()) {
            return context;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public void run() {
        try {
            zza(zzd(this.zzsw.zzvf.zzaZ, zzg(this.zzsw.zzqr), !zzfx.zzBz.get().booleanValue() || this.zzsw.zzvf.zzYd));
        } finally {
            this.zzth.countDown();
            this.zzsw = null;
        }
    }

    public String zza(Context context, String str, View view) {
        if (zzce()) {
            zzaq zzaq = this.zztg.get();
            if (zzaq != null) {
                zzcf();
                return zzaq.zza(zzg(context), str, view);
            }
        }
        return "";
    }

    public String zza(Context context, byte[] bArr) {
        if (zzce()) {
            zzaq zzaq = this.zztg.get();
            if (zzaq != null) {
                zzcf();
                return zzaq.zzb(zzg(context));
            }
        }
        return "";
    }

    public void zza(int i, int i2, int i3) {
        zzaq zzaq = this.zztg.get();
        if (zzaq != null) {
            zzcf();
            zzaq.zza(i, i2, i3);
            return;
        }
        this.zztf.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public void zza(MotionEvent motionEvent) {
        zzaq zzaq = this.zztg.get();
        if (zzaq != null) {
            zzcf();
            zzaq.zza(motionEvent);
            return;
        }
        this.zztf.add(new Object[]{motionEvent});
    }

    /* access modifiers changed from: protected */
    public void zza(zzaq zzaq) {
        this.zztg.set(zzaq);
    }

    public String zzb(Context context) {
        return zza(context, null);
    }

    /* access modifiers changed from: protected */
    public boolean zzce() {
        try {
            this.zzth.await();
            return true;
        } catch (InterruptedException e) {
            zzpe.zzc("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public zzaq zzd(String str, Context context, boolean z) {
        return zzau.zza(str, context, z);
    }
}
