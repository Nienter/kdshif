package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzqq;

@zzmb
public class zzlk implements Runnable {
    protected final zzqp zzGt;
    /* access modifiers changed from: private */
    public final Handler zzPu;
    /* access modifiers changed from: private */
    public final long zzPv;
    /* access modifiers changed from: private */
    public long zzPw;
    /* access modifiers changed from: private */
    public zzqq.zza zzPx;
    protected boolean zzPy;
    protected boolean zzPz;
    /* access modifiers changed from: private */
    public final int zzrG;
    /* access modifiers changed from: private */
    public final int zzrH;

    protected final class zza extends AsyncTask<Void, Void, Boolean> {
        private final WebView zzPA;
        private Bitmap zzPB;

        public zza(WebView webView) {
            this.zzPA = webView;
        }

        /* access modifiers changed from: protected */
        public synchronized void onPreExecute() {
            this.zzPB = Bitmap.createBitmap(zzlk.this.zzrG, zzlk.this.zzrH, Bitmap.Config.ARGB_8888);
            this.zzPA.setVisibility(0);
            this.zzPA.measure(View.MeasureSpec.makeMeasureSpec(zzlk.this.zzrG, 0), View.MeasureSpec.makeMeasureSpec(zzlk.this.zzrH, 0));
            this.zzPA.layout(0, 0, zzlk.this.zzrG, zzlk.this.zzrH);
            this.zzPA.draw(new Canvas(this.zzPB));
            this.zzPA.invalidate();
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public void onPostExecute(Boolean bool) {
            zzlk.zzc(zzlk.this);
            if (bool.booleanValue() || zzlk.this.zziz() || zzlk.this.zzPw <= 0) {
                zzlk.this.zzPz = bool.booleanValue();
                zzlk.this.zzPx.zza(zzlk.this.zzGt, true);
            } else if (zzlk.this.zzPw > 0) {
                if (zzpe.zzai(2)) {
                    zzpe.zzbc("Ad not detected, scheduling another run.");
                }
                zzlk.this.zzPu.postDelayed(zzlk.this, zzlk.this.zzPv);
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzb */
        public synchronized Boolean doInBackground(Void... voidArr) {
            boolean z;
            int width = this.zzPB.getWidth();
            int height = this.zzPB.getHeight();
            if (width == 0 || height == 0) {
                z = false;
            } else {
                int i = 0;
                for (int i2 = 0; i2 < width; i2 += 10) {
                    for (int i3 = 0; i3 < height; i3 += 10) {
                        if (this.zzPB.getPixel(i2, i3) != 0) {
                            i++;
                        }
                    }
                }
                z = Boolean.valueOf(((double) i) / (((double) (width * height)) / 100.0d) > 0.1d);
            }
            return z;
        }
    }

    public zzlk(zzqq.zza zza2, zzqp zzqp, int i, int i2) {
        this(zza2, zzqp, i, i2, 200, 50);
    }

    public zzlk(zzqq.zza zza2, zzqp zzqp, int i, int i2, long j, long j2) {
        this.zzPv = j;
        this.zzPw = j2;
        this.zzPu = new Handler(Looper.getMainLooper());
        this.zzGt = zzqp;
        this.zzPx = zza2;
        this.zzPy = false;
        this.zzPz = false;
        this.zzrH = i2;
        this.zzrG = i;
    }

    static /* synthetic */ long zzc(zzlk zzlk) {
        long j = zzlk.zzPw - 1;
        zzlk.zzPw = j;
        return j;
    }

    public void run() {
        if (this.zzGt == null || zziz()) {
            this.zzPx.zza(this.zzGt, true);
        } else {
            new zza(this.zzGt.getWebView()).execute(new Void[0]);
        }
    }

    public void zza(zzmk zzmk) {
        zza(zzmk, new zzra(this, this.zzGt, zzmk.zzRR));
    }

    public void zza(zzmk zzmk, zzra zzra) {
        this.zzGt.setWebViewClient(zzra);
        this.zzGt.loadDataWithBaseURL(TextUtils.isEmpty(zzmk.zzNb) ? null : zzv.zzcJ().zzaV(zzmk.zzNb), zzmk.body, "text/html", "UTF-8", null);
    }

    public boolean zziA() {
        return this.zzPz;
    }

    public void zzix() {
        this.zzPu.postDelayed(this, this.zzPv);
    }

    public synchronized void zziy() {
        this.zzPy = true;
    }

    public synchronized boolean zziz() {
        return this.zzPy;
    }
}
