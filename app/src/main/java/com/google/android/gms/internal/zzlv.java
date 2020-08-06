package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzqq;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@zzmb
public class zzlv {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzr zzGl;
    private final zzav zzGr;
    /* access modifiers changed from: private */
    public final zzov.zza zzPo;
    private ViewTreeObserver.OnGlobalLayoutListener zzQA;
    private ViewTreeObserver.OnScrollChangedListener zzQB;
    private final Object zzrN = new Object();
    private final zzgf zzsr;
    private int zzvI = -1;
    private int zzvJ = -1;
    private zzpt zzvK;

    public zzlv(Context context, zzav zzav, zzov.zza zza, zzgf zzgf, zzr zzr) {
        this.mContext = context;
        this.zzGr = zzav;
        this.zzPo = zza;
        this.zzsr = zzgf;
        this.zzGl = zzr;
        this.zzvK = new zzpt(200);
    }

    /* access modifiers changed from: private */
    public ViewTreeObserver.OnGlobalLayoutListener zza(final WeakReference<zzqp> weakReference) {
        if (this.zzQA == null) {
            this.zzQA = new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    zzlv.this.zza((WeakReference<zzqp>) weakReference, false);
                }
            };
        }
        return this.zzQA;
    }

    /* access modifiers changed from: private */
    public void zza(WeakReference<zzqp> weakReference, boolean z) {
        if (weakReference != null) {
            zzqp zzqp = (zzqp) weakReference.get();
            if (zzqp != null && zzqp.getView() != null) {
                if (!z || this.zzvK.tryAcquire()) {
                    int[] iArr = new int[2];
                    zzqp.getView().getLocationOnScreen(iArr);
                    int zzc = zzeh.zzeO().zzc(this.mContext, iArr[0]);
                    int zzc2 = zzeh.zzeO().zzc(this.mContext, iArr[1]);
                    synchronized (this.zzrN) {
                        if (!(this.zzvI == zzc && this.zzvJ == zzc2)) {
                            this.zzvI = zzc;
                            this.zzvJ = zzc2;
                            zzqp.zzkV().zza(this.zzvI, this.zzvJ, !z);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public ViewTreeObserver.OnScrollChangedListener zzb(final WeakReference<zzqp> weakReference) {
        if (this.zzQB == null) {
            this.zzQB = new ViewTreeObserver.OnScrollChangedListener() {
                public void onScrollChanged() {
                    zzlv.this.zza((WeakReference<zzqp>) weakReference, true);
                }
            };
        }
        return this.zzQB;
    }

    /* access modifiers changed from: private */
    public void zzj(zzqp zzqp) {
        zzqq zzkV = zzqp.zzkV();
        zzkV.zza("/video", zzhw.zzHq);
        zzkV.zza("/videoMeta", zzhw.zzHr);
        zzkV.zza("/precache", zzhw.zzHs);
        zzkV.zza("/delayPageLoaded", zzhw.zzHv);
        zzkV.zza("/instrument", zzhw.zzHt);
        zzkV.zza("/log", zzhw.zzHl);
        zzkV.zza("/videoClicked", zzhw.zzHm);
        zzkV.zza("/trackActiveViewUnit", (zzhx) new zzhx() {
            public void zza(zzqp zzqp, Map<String, String> map) {
                zzlv.this.zzGl.zzcr();
            }
        });
    }

    public zzqf<zzqp> zzf(final JSONObject jSONObject) {
        final zzqc zzqc = new zzqc();
        zzv.zzcJ().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    final zzqp zziU = zzlv.this.zziU();
                    zzlv.this.zzGl.zzc(zziU);
                    WeakReference weakReference = new WeakReference(zziU);
                    zziU.zzkV().zza(zzlv.this.zza((WeakReference<zzqp>) weakReference), zzlv.this.zzb((WeakReference<zzqp>) weakReference));
                    zzlv.this.zzj(zziU);
                    zziU.zzkV().zza((zzqq.zzb) new zzqq.zzb() {
                        public void zzk(zzqp zzqp) {
                            zziU.zza("google.afma.nativeAds.renderVideo", jSONObject);
                        }
                    });
                    zziU.zzkV().zza((zzqq.zza) new zzqq.zza() {
                        public void zza(zzqp zzqp, boolean z) {
                            zzlv.this.zzGl.zzcu();
                            zzqc.zzh(zzqp);
                        }
                    });
                    zziU.loadUrl(zzlt.zza(zzlv.this.zzPo, zzfx.zzDS.get()));
                } catch (Exception e) {
                    zzpe.zzc("Exception occurred while getting video view", e);
                    zzqc.zzh(null);
                }
            }
        });
        return zzqc;
    }

    /* access modifiers changed from: package-private */
    public zzqp zziU() {
        return zzv.zzcK().zza(this.mContext, zzec.zzj(this.mContext), false, false, this.zzGr, this.zzPo.zzSF.zzvf, this.zzsr, null, this.zzGl.zzbz());
    }
}
