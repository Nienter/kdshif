package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p001v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpd;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import com.google.android.gms.internal.zzpl;
import com.google.android.gms.internal.zzqp;
import com.google.android.gms.internal.zzqq;
import java.util.Collections;
import java.util.Map;

@zzmb
public class zze extends zzkr.zza implements zzv {
    static final int zzMx = Color.argb(0, 0, 0, 0);
    /* access modifiers changed from: private */
    public final Activity mActivity;
    zzqp zzGt;
    zzp zzMA;
    boolean zzMB = false;
    FrameLayout zzMC;
    WebChromeClient.CustomViewCallback zzMD;
    boolean zzME = false;
    boolean zzMF = false;
    zzb zzMG;
    boolean zzMH = false;
    int zzMI = 0;
    zzm zzMJ;
    private final Object zzMK = new Object();
    private Runnable zzML;
    private boolean zzMM;
    private boolean zzMN;
    private boolean zzMO = false;
    private boolean zzMP = false;
    private boolean zzMQ = true;
    AdOverlayInfoParcel zzMy;
    zzc zzMz;

    @zzmb
    private static final class zza extends Exception {
        public zza(String str) {
            super(str);
        }
    }

    @zzmb
    static class zzb extends RelativeLayout {
        boolean zzMS;
        zzpl zzvO;

        public zzb(Context context, String str) {
            super(context);
            this.zzvO = new zzpl(context, str);
        }

        /* access modifiers changed from: package-private */
        public void disable() {
            this.zzMS = true;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (!this.zzMS) {
                this.zzvO.zzg(motionEvent);
            }
            return false;
        }
    }

    @zzmb
    public static class zzc {
        public final int index;
        public final ViewGroup parent;
        public final ViewGroup.LayoutParams zzMT;
        public final Context zzqr;

        public zzc(zzqp zzqp) {
            this.zzMT = zzqp.getLayoutParams();
            ViewParent parent2 = zzqp.getParent();
            this.zzqr = zzqp.zzkS();
            if (parent2 == null || !(parent2 instanceof ViewGroup)) {
                throw new zza("Could not get the parent of the WebView for an overlay.");
            }
            this.parent = (ViewGroup) parent2;
            this.index = this.parent.indexOfChild(zzqp.getView());
            this.parent.removeView(zzqp.getView());
            zzqp.zzJ(true);
        }
    }

    @zzmb
    private class zzd extends zzpd {
        private zzd() {
        }

        public void onStop() {
        }

        public void zzcm() {
            Bitmap zza = zzv.zzde().zza(Integer.valueOf(zze.this.zzMy.zzNi.zztM));
            if (zza != null) {
                final Drawable zza2 = zzv.zzcL().zza(zze.this.mActivity, zza, zze.this.zzMy.zzNi.zztK, zze.this.zzMy.zzNi.zztL);
                zzpi.zzWR.post(new Runnable() {
                    public void run() {
                        zze.this.mActivity.getWindow().setBackgroundDrawable(zza2);
                    }
                });
            }
        }
    }

    public zze(Activity activity) {
        this.mActivity = activity;
        this.zzMJ = new zzt();
    }

    public void close() {
        this.zzMI = 2;
        this.mActivity.finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onBackPressed() {
        this.zzMI = 0;
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        this.mActivity.requestWindowFeature(1);
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzME = z;
        try {
            this.zzMy = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
            if (this.zzMy == null) {
                throw new zza("Could not get info for ad overlay.");
            }
            if (this.zzMy.zzvf.zzYc > 7500000) {
                this.zzMI = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzMQ = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzMy.zzNi != null) {
                this.zzMF = this.zzMy.zzNi.zztH;
            } else {
                this.zzMF = false;
            }
            if (zzfx.zzDJ.get().booleanValue() && this.zzMF && this.zzMy.zzNi.zztM != -1) {
                new zzd().zziw();
            }
            if (bundle == null) {
                if (this.zzMy.zzMY != null && this.zzMQ) {
                    this.zzMy.zzMY.zzbO();
                }
                if (!(this.zzMy.zzNf == 1 || this.zzMy.zzMX == null)) {
                    this.zzMy.zzMX.onAdClicked();
                }
            }
            this.zzMG = new zzb(this.mActivity, this.zzMy.zzNh);
            this.zzMG.setId(1000);
            switch (this.zzMy.zzNf) {
                case 1:
                    zzA(false);
                    return;
                case 2:
                    this.zzMz = new zzc(this.zzMy.zzMZ);
                    zzA(false);
                    return;
                case 3:
                    zzA(true);
                    return;
                case 4:
                    if (this.zzME) {
                        this.zzMI = 3;
                        this.mActivity.finish();
                        return;
                    } else if (!zzv.zzcG().zza((Context) this.mActivity, this.zzMy.zzMW, this.zzMy.zzNe)) {
                        this.zzMI = 3;
                        this.mActivity.finish();
                        return;
                    } else {
                        return;
                    }
                default:
                    throw new zza("Could not determine ad overlay type.");
            }
        } catch (zza e) {
            zzpe.zzbe(e.getMessage());
            this.zzMI = 3;
            this.mActivity.finish();
        }
    }

    public void onDestroy() {
        if (this.zzGt != null) {
            this.zzMG.removeView(this.zzGt.getView());
        }
        zzhm();
    }

    public void onPause() {
        zzhi();
        if (this.zzMy.zzMY != null) {
            this.zzMy.zzMY.onPause();
        }
        if (!zzfx.zzEZ.get().booleanValue() && this.zzGt != null && (!this.mActivity.isFinishing() || this.zzMz == null)) {
            zzv.zzcL().zzl(this.zzGt);
        }
        zzhm();
    }

    public void onRestart() {
    }

    public void onResume() {
        if (this.zzMy != null && this.zzMy.zzNf == 4) {
            if (this.zzME) {
                this.zzMI = 3;
                this.mActivity.finish();
            } else {
                this.zzME = true;
            }
        }
        if (this.zzMy.zzMY != null) {
            this.zzMy.zzMY.onResume();
        }
        if (zzfx.zzEZ.get().booleanValue()) {
            return;
        }
        if (this.zzGt == null || this.zzGt.isDestroyed()) {
            zzpe.zzbe("The webview does not exist. Ignoring action.");
        } else {
            zzv.zzcL().zzm(this.zzGt);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzME);
    }

    public void onStart() {
        if (!zzfx.zzEZ.get().booleanValue()) {
            return;
        }
        if (this.zzGt == null || this.zzGt.isDestroyed()) {
            zzpe.zzbe("The webview does not exist. Ignoring action.");
        } else {
            zzv.zzcL().zzm(this.zzGt);
        }
    }

    public void onStop() {
        if (zzfx.zzEZ.get().booleanValue() && this.zzGt != null && (!this.mActivity.isFinishing() || this.zzMz == null)) {
            zzv.zzcL().zzl(this.zzGt);
        }
        zzhm();
    }

    public void setRequestedOrientation(int i) {
        this.mActivity.setRequestedOrientation(i);
    }

    /* access modifiers changed from: protected */
    public void zzA(boolean z) {
        if (!this.zzMN) {
            this.mActivity.requestWindowFeature(1);
        }
        Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zza("Invalid activity, no window available.");
        }
        boolean zza2 = (!zzs.isAtLeastN() || !zzfx.zzEY.get().booleanValue()) ? true : zzv.zzcJ().zza(this.mActivity, this.mActivity.getResources().getConfiguration());
        boolean z2 = this.zzMy.zzNi != null && this.zzMy.zzNi.zztI;
        if ((!this.zzMF || z2) && zza2) {
            window.setFlags(1024, 1024);
        }
        zzqq zzkV = this.zzMy.zzMZ.zzkV();
        boolean zzdz = zzkV != null ? zzkV.zzdz() : false;
        this.zzMH = false;
        if (zzdz) {
            if (this.zzMy.orientation == zzv.zzcL().zzkp()) {
                this.zzMH = this.mActivity.getResources().getConfiguration().orientation == 1;
            } else if (this.zzMy.orientation == zzv.zzcL().zzkq()) {
                this.zzMH = this.mActivity.getResources().getConfiguration().orientation == 2;
            }
        }
        zzpe.zzbc(new StringBuilder(46).append("Delay onShow to next orientation change: ").append(this.zzMH).toString());
        setRequestedOrientation(this.zzMy.orientation);
        if (zzv.zzcL().zza(window)) {
            zzpe.zzbc("Hardware acceleration on the AdActivity window enabled.");
        }
        if (!this.zzMF) {
            this.zzMG.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.zzMG.setBackgroundColor(zzMx);
        }
        this.mActivity.setContentView(this.zzMG);
        zzbp();
        if (z) {
            this.zzGt = zzv.zzcK().zza(this.mActivity, this.zzMy.zzMZ.zzbD(), true, zzdz, null, this.zzMy.zzvf, null, null, this.zzMy.zzMZ.zzbz());
            this.zzGt.zzkV().zza(null, null, this.zzMy.zzNa, this.zzMy.zzNe, true, this.zzMy.zzNg, null, this.zzMy.zzMZ.zzkV().zzln(), null, null);
            this.zzGt.zzkV().zza((zzqq.zza) new zzqq.zza(this) {
                public void zza(zzqp zzqp, boolean z) {
                    zzqp.zzhp();
                }
            });
            if (this.zzMy.url != null) {
                this.zzGt.loadUrl(this.zzMy.url);
            } else if (this.zzMy.zzNd != null) {
                this.zzGt.loadDataWithBaseURL(this.zzMy.zzNb, this.zzMy.zzNd, "text/html", "UTF-8", null);
            } else {
                throw new zza("No URL or HTML to display in ad overlay.");
            }
            if (this.zzMy.zzMZ != null) {
                this.zzMy.zzMZ.zzc(this);
            }
        } else {
            this.zzGt = this.zzMy.zzMZ;
            this.zzGt.setContext(this.mActivity);
        }
        this.zzGt.zzb(this);
        ViewParent parent = this.zzGt.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.zzGt.getView());
        }
        if (this.zzMF) {
            this.zzGt.zzlm();
        }
        this.zzMG.addView(this.zzGt.getView(), -1, -1);
        if (!z && !this.zzMH) {
            zzhp();
        }
        zzz(zzdz);
        if (this.zzGt.zzkW()) {
            zza(zzdz, true);
        }
        com.google.android.gms.ads.internal.zzd zzbz = this.zzGt.zzbz();
        zzn zzn = zzbz != null ? zzbz.zzsO : null;
        if (zzn != null) {
            this.zzMJ = zzn.zza(this.mActivity, this.zzGt, this.zzMG);
        } else {
            zzpe.zzbe("Appstreaming controller is null.");
        }
    }

    /* access modifiers changed from: protected */
    public void zzK(int i) {
        this.zzGt.zzK(i);
    }

    public void zza(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.zzMC = new FrameLayout(this.mActivity);
        this.zzMC.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzMC.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzMC);
        zzbp();
        this.zzMD = customViewCallback;
        this.zzMB = true;
    }

    public void zza(boolean z, boolean z2) {
        if (this.zzMA != null) {
            this.zzMA.zza(z, z2);
        }
    }

    public void zzbp() {
        this.zzMN = true;
    }

    public void zzg(zzqp zzqp, Map<String, String> map) {
    }

    public void zzhi() {
        if (this.zzMy != null && this.zzMB) {
            setRequestedOrientation(this.zzMy.orientation);
        }
        if (this.zzMC != null) {
            this.mActivity.setContentView(this.zzMG);
            zzbp();
            this.zzMC.removeAllViews();
            this.zzMC = null;
        }
        if (this.zzMD != null) {
            this.zzMD.onCustomViewHidden();
            this.zzMD = null;
        }
        this.zzMB = false;
    }

    public void zzhj() {
        this.zzMI = 1;
        this.mActivity.finish();
    }

    public boolean zzhk() {
        boolean z = true;
        this.zzMI = 0;
        if (this.zzGt != null) {
            if (!this.zzGt.zzlb()) {
                z = false;
            }
            if (!z) {
                this.zzGt.zza("onbackblocked", (Map<String, ?>) Collections.emptyMap());
            }
        }
        return z;
    }

    public void zzhl() {
        this.zzMG.removeView(this.zzMA);
        zzz(true);
    }

    /* access modifiers changed from: protected */
    public void zzhm() {
        if (this.mActivity.isFinishing() && !this.zzMO) {
            this.zzMO = true;
            if (this.zzGt != null) {
                zzK(this.zzMI);
                synchronized (this.zzMK) {
                    if (!this.zzMM && this.zzGt.zzlh()) {
                        this.zzML = new Runnable() {
                            public void run() {
                                zze.this.zzhn();
                            }
                        };
                        zzpi.zzWR.postDelayed(this.zzML, zzfx.zzCz.get().longValue());
                        return;
                    }
                }
            }
            zzhn();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzhn() {
        if (!this.zzMP) {
            this.zzMP = true;
            if (this.zzGt != null) {
                this.zzMG.removeView(this.zzGt.getView());
                if (this.zzMz != null) {
                    this.zzGt.setContext(this.zzMz.zzqr);
                    this.zzGt.zzJ(false);
                    this.zzMz.parent.addView(this.zzGt.getView(), this.zzMz.index, this.zzMz.zzMT);
                    this.zzMz = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzGt.setContext(this.mActivity.getApplicationContext());
                }
                this.zzGt = null;
            }
            if (this.zzMy != null && this.zzMy.zzMY != null) {
                this.zzMy.zzMY.zzbN();
            }
        }
    }

    public void zzho() {
        if (this.zzMH) {
            this.zzMH = false;
            zzhp();
        }
    }

    /* access modifiers changed from: protected */
    public void zzhp() {
        this.zzGt.zzhp();
    }

    public void zzhq() {
        this.zzMG.disable();
    }

    public void zzhr() {
        synchronized (this.zzMK) {
            this.zzMM = true;
            if (this.zzML != null) {
                zzpi.zzWR.removeCallbacks(this.zzML);
                zzpi.zzWR.post(this.zzML);
            }
        }
    }

    public void zzn(com.google.android.gms.dynamic.zzd zzd2) {
        if (zzfx.zzEY.get().booleanValue() && zzs.isAtLeastN()) {
            if (zzv.zzcJ().zza(this.mActivity, (Configuration) com.google.android.gms.dynamic.zze.zzE(zzd2))) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }

    public void zzz(boolean z) {
        this.zzMA = new zzp(this.mActivity, z ? 50 : 32, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.zzMA.zza(z, this.zzMy.zzNc);
        this.zzMG.addView(this.zzMA, layoutParams);
    }
}
