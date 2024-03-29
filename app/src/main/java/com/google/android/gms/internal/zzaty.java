package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;

public class zzaty extends zzats {
    private Handler mHandler;
    protected long zzbuT;
    private final zzasv zzbuU = new zzasv(this.zzbpw) {
        @WorkerThread
        public void run() {
            zzaty.this.zzMf();
        }
    };
    private final zzasv zzbuV = new zzasv(this.zzbpw) {
        @WorkerThread
        public void run() {
            zzaty.this.zzMg();
        }
    };

    zzaty(zzatp zzatp) {
        super(zzatp);
    }

    private void zzMd() {
        synchronized (this) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper());
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzMg() {
        zzmq();
        zzaJ(false);
        zzJg().zzV(zznq().elapsedRealtime());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzap(long j) {
        zzmq();
        zzMd();
        this.zzbuU.cancel();
        this.zzbuV.cancel();
        zzJt().zzLg().zzj("Activity resumed, time", Long.valueOf(j));
        this.zzbuT = j;
        if (zznq().currentTimeMillis() - zzJu().zzbsq.get() > zzJu().zzbss.get()) {
            zzJu().zzbsr.set(true);
            zzJu().zzbst.set(0);
        }
        if (zzJu().zzbsr.get()) {
            this.zzbuU.zzx(Math.max(0, zzJu().zzbsp.get() - zzJu().zzbst.get()));
        } else {
            this.zzbuV.zzx(Math.max(0, 3600000 - zzJu().zzbst.get()));
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzaq(long j) {
        zzmq();
        zzMd();
        this.zzbuU.cancel();
        this.zzbuV.cancel();
        zzJt().zzLg().zzj("Activity paused, time", Long.valueOf(j));
        if (this.zzbuT != 0) {
            zzJu().zzbst.set(zzJu().zzbst.get() + (j - this.zzbuT));
        }
        zzJu().zzbss.set(zznq().currentTimeMillis());
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJd() {
        super.zzJd();
    }

    public /* bridge */ /* synthetic */ void zzJe() {
        super.zzJe();
    }

    public /* bridge */ /* synthetic */ void zzJf() {
        super.zzJf();
    }

    public /* bridge */ /* synthetic */ zzaso zzJg() {
        return super.zzJg();
    }

    public /* bridge */ /* synthetic */ zzass zzJh() {
        return super.zzJh();
    }

    public /* bridge */ /* synthetic */ zzatu zzJi() {
        return super.zzJi();
    }

    public /* bridge */ /* synthetic */ zzatf zzJj() {
        return super.zzJj();
    }

    public /* bridge */ /* synthetic */ zzasw zzJk() {
        return super.zzJk();
    }

    public /* bridge */ /* synthetic */ zzatw zzJl() {
        return super.zzJl();
    }

    public /* bridge */ /* synthetic */ zzatv zzJm() {
        return super.zzJm();
    }

    public /* bridge */ /* synthetic */ zzatg zzJn() {
        return super.zzJn();
    }

    public /* bridge */ /* synthetic */ zzasu zzJo() {
        return super.zzJo();
    }

    public /* bridge */ /* synthetic */ zzaue zzJp() {
        return super.zzJp();
    }

    public /* bridge */ /* synthetic */ zzatn zzJq() {
        return super.zzJq();
    }

    public /* bridge */ /* synthetic */ zzaty zzJr() {
        return super.zzJr();
    }

    public /* bridge */ /* synthetic */ zzato zzJs() {
        return super.zzJs();
    }

    public /* bridge */ /* synthetic */ zzati zzJt() {
        return super.zzJt();
    }

    public /* bridge */ /* synthetic */ zzatl zzJu() {
        return super.zzJu();
    }

    public /* bridge */ /* synthetic */ zzast zzJv() {
        return super.zzJv();
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void zzMc() {
        final long elapsedRealtime = zznq().elapsedRealtime();
        zzJs().zzm(new Runnable() {
            public void run() {
                zzaty.this.zzap(elapsedRealtime);
            }
        });
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void zzMe() {
        final long elapsedRealtime = zznq().elapsedRealtime();
        zzJs().zzm(new Runnable() {
            public void run() {
                zzaty.this.zzaq(elapsedRealtime);
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzMf() {
        zzmq();
        zzJt().zzLg().zzj("Session started, time", Long.valueOf(zznq().elapsedRealtime()));
        zzJu().zzbsr.set(false);
        zzJi().zze("auto", "_s", new Bundle());
    }

    @WorkerThread
    public boolean zzaJ(boolean z) {
        zzmq();
        zznA();
        long elapsedRealtime = zznq().elapsedRealtime();
        if (this.zzbuT == 0) {
            this.zzbuT = elapsedRealtime - 3600000;
        }
        long j = elapsedRealtime - this.zzbuT;
        if (z || j >= 1000) {
            zzJu().zzbst.set(j);
            zzJt().zzLg().zzj("Recording user engagement, ms", Long.valueOf(j));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            zzatv.zza((AppMeasurement.zzf) zzJm().zzLU(), bundle);
            zzJi().zze("auto", "_e", bundle);
            this.zzbuT = elapsedRealtime;
            this.zzbuV.cancel();
            this.zzbuV.zzx(Math.max(0, 3600000 - zzJu().zzbst.get()));
            return true;
        }
        zzJt().zzLg().zzj("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j));
        return false;
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }
}
