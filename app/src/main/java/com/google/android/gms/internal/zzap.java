package com.google.android.gms.internal;

import android.os.Build;
import android.os.ConditionVariable;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzzf;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

public class zzap {
    /* access modifiers changed from: private */
    public static final ConditionVariable zzpD = new ConditionVariable();
    protected static volatile zzzf zzpE = null;
    private static volatile Random zzpG = null;
    /* access modifiers changed from: private */
    public zzbc zzpC;
    protected Boolean zzpF;

    public zzap(zzbc zzbc) {
        this.zzpC = zzbc;
        zza((Executor) zzbc.zzaJ());
    }

    private static Random zzU() {
        if (zzpG == null) {
            synchronized (zzap.class) {
                if (zzpG == null) {
                    zzpG = new Random();
                }
            }
        }
        return zzpG;
    }

    private void zza(Executor executor) {
        executor.execute(new Runnable() {
            public void run() {
                if (zzap.this.zzpF == null) {
                    synchronized (zzap.zzpD) {
                        if (zzap.this.zzpF == null) {
                            boolean booleanValue = zzfx.zzDf.get().booleanValue();
                            if (booleanValue) {
                                zzap.zzpE = new zzzf(zzap.this.zzpC.getContext(), "ADSHIELD", null);
                            }
                            zzap.this.zzpF = Boolean.valueOf(booleanValue);
                            zzap.zzpD.open();
                        }
                    }
                }
            }
        });
    }

    public int zzT() {
        try {
            return Build.VERSION.SDK_INT >= 21 ? ThreadLocalRandom.current().nextInt() : zzU().nextInt();
        } catch (RuntimeException e) {
            return zzU().nextInt();
        }
    }

    public void zza(int i, int i2, long j) {
        try {
            zzpD.block();
            if (this.zzpF.booleanValue() && zzpE != null && this.zzpC.zzaP()) {
                zzae.zza zza = new zzae.zza();
                zza.zzaR = this.zzpC.getContext().getPackageName();
                zza.zzaS = Long.valueOf(j);
                zzzf.zza zzm = zzpE.zzm(zzbut.zzf(zza));
                zzm.zzco(i2);
                zzm.zzcn(i);
                zzm.zze(this.zzpC.zzaN());
            }
        } catch (Exception e) {
        }
    }
}
