package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzqi;
import java.util.concurrent.TimeUnit;

@zzmb
public class zzlt {
    private static final long zzPS = TimeUnit.SECONDS.toMillis(60);
    private static boolean zzPT = false;
    private static zzja zzPU = null;
    private static final Object zztU = new Object();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzr zzGl;
    private final zzav zzGr;
    private zziy zzPV;
    private zzja.zze zzPW;
    private zzix zzPX;
    private boolean zzPY = false;
    private final zzov.zza zzPo;

    public static abstract class zza {
        public abstract void zze(zzjb zzjb);

        public void zziO() {
        }
    }

    public zzlt(Context context, zzov.zza zza2, zzr zzr, zzav zzav) {
        this.mContext = context;
        this.zzPo = zza2;
        this.zzGl = zzr;
        this.zzGr = zzav;
        this.zzPY = zzfx.zzDT.get().booleanValue();
    }

    public static String zza(zzov.zza zza2, String str) {
        String valueOf = String.valueOf(zza2.zzVB.zzNb.indexOf("https") == 0 ? "https:" : "http:");
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    private void zziG() {
        synchronized (zztU) {
            if (!zzPT) {
                zzPU = new zzja(this.mContext.getApplicationContext() != null ? this.mContext.getApplicationContext() : this.mContext, this.zzPo.zzSF.zzvf, zza(this.zzPo, zzfx.zzDR.get()), new zzpn<zzix>() {
                    /* renamed from: zza */
                    public void zzd(zzix zzix) {
                        zzix.zza(zzlt.this.zzGl, zzlt.this.zzGl, zzlt.this.zzGl, zzlt.this.zzGl, false, null, null, null, null);
                    }
                }, new zzja.zzb());
                zzPT = true;
            }
        }
    }

    private void zziH() {
        this.zzPW = new zzja.zze(zziM().zzc(this.zzGr));
    }

    private void zziI() {
        this.zzPV = new zziy();
    }

    private void zziJ() {
        this.zzPX = zziK().zza(this.mContext, this.zzPo.zzSF.zzvf, zza(this.zzPo, zzfx.zzDR.get()), this.zzGr, this.zzGl.zzbz()).get(zzPS, TimeUnit.MILLISECONDS);
        this.zzPX.zza(this.zzGl, this.zzGl, this.zzGl, this.zzGl, false, null, null, null, null);
    }

    public void zza(final zza zza2) {
        if (this.zzPY) {
            zzja.zze zziN = zziN();
            if (zziN == null) {
                zzpe.zzbe("SharedJavascriptEngine not initialized");
            } else {
                zziN.zza(new zzqi.zzc<zzjb>(this) {
                    /* renamed from: zzb */
                    public void zzd(zzjb zzjb) {
                        zza2.zze(zzjb);
                    }
                }, new zzqi.zza(this) {
                    public void run() {
                        zza2.zziO();
                    }
                });
            }
        } else {
            zzix zziL = zziL();
            if (zziL == null) {
                zzpe.zzbe("JavascriptEngine not initialized");
            } else {
                zza2.zze(zziL);
            }
        }
    }

    public void zziE() {
        if (this.zzPY) {
            zziG();
        } else {
            zziI();
        }
    }

    public void zziF() {
        if (this.zzPY) {
            zziH();
        } else {
            zziJ();
        }
    }

    /* access modifiers changed from: protected */
    public zziy zziK() {
        return this.zzPV;
    }

    /* access modifiers changed from: protected */
    public zzix zziL() {
        return this.zzPX;
    }

    /* access modifiers changed from: protected */
    public zzja zziM() {
        return zzPU;
    }

    /* access modifiers changed from: protected */
    public zzja.zze zziN() {
        return this.zzPW;
    }
}
