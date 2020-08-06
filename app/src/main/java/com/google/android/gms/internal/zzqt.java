package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.p004v7.widget.ActivityChooserView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzt;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzcv;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
class zzqt extends WebView implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzqp {
    @Nullable
    private final zzav zzGr;
    private int zzLQ = -1;
    private int zzLR = -1;
    private int zzLT = -1;
    private int zzLU = -1;
    private String zzOn = "";
    private zzgd zzOo;
    private Boolean zzWh;
    private final zza zzYX;
    private final zzt zzYY;
    private zzqq zzYZ;
    private zze zzZa;
    private boolean zzZb;
    private boolean zzZc;
    private boolean zzZd;
    private boolean zzZe;
    private int zzZf;
    private boolean zzZg = true;
    boolean zzZh = false;
    private zzqu zzZi;
    private boolean zzZj;
    private boolean zzZk;
    private zzgs zzZl;
    private int zzZm;
    /* access modifiers changed from: private */
    public int zzZn;
    private zzgd zzZo;
    private zzgd zzZp;
    private zzge zzZq;
    private WeakReference<View.OnClickListener> zzZr;
    private zze zzZs;
    private Map<String, zzil> zzZt;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();
    private final zzd zzsz;
    private final zzqa zztr;
    private zzec zzum;
    private zzpw zzvP;
    private final WindowManager zzwf;

    @zzmb
    public static class zza extends MutableContextWrapper {
        private Activity zzXO;
        private Context zzZv;
        private Context zzvZ;

        public zza(Context context) {
            super(context);
            setBaseContext(context);
        }

        public Object getSystemService(String str) {
            return this.zzZv.getSystemService(str);
        }

        public void setBaseContext(Context context) {
            this.zzvZ = context.getApplicationContext();
            this.zzXO = context instanceof Activity ? (Activity) context : null;
            this.zzZv = context;
            super.setBaseContext(this.zzvZ);
        }

        public void startActivity(Intent intent) {
            if (this.zzXO != null) {
                this.zzXO.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.zzvZ.startActivity(intent);
        }

        public Activity zzkR() {
            return this.zzXO;
        }

        public Context zzkS() {
            return this.zzZv;
        }
    }

    protected zzqt(zza zza2, zzec zzec, boolean z, boolean z2, @Nullable zzav zzav, zzqa zzqa, zzgf zzgf, zzt zzt, zzd zzd) {
        super(zza2);
        this.zzYX = zza2;
        this.zzum = zzec;
        this.zzZd = z;
        this.zzZf = -1;
        this.zzGr = zzav;
        this.zztr = zzqa;
        this.zzYY = zzt;
        this.zzsz = zzd;
        this.zzwf = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzv.zzcJ().zza((Context) zza2, zzqa.zzaZ, settings);
        zzv.zzcL().zza(getContext(), settings);
        setDownloadListener(this);
        zzlF();
        if (zzs.zzyD()) {
            addJavascriptInterface(new zzqv(this), "googleAdsJsInterface");
        }
        if (zzs.zzyx()) {
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
        this.zzvP = new zzpw(this.zzYX.zzkR(), this, this, null);
        zzd(zzgf);
    }

    private void zzO(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        zza("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }

    static zzqt zzb(Context context, zzec zzec, boolean z, boolean z2, @Nullable zzav zzav, zzqa zzqa, zzgf zzgf, zzt zzt, zzd zzd) {
        return new zzqt(new zza(context), zzec, z, z2, zzav, zzqa, zzgf, zzt, zzd);
    }

    private void zzd(zzgf zzgf) {
        zzlJ();
        this.zzZq = new zzge(new zzgf(true, "make_wv", this.zzum.zzzk));
        this.zzZq.zzfv().zzc(zzgf);
        this.zzOo = zzgb.zzb(this.zzZq.zzfv());
        this.zzZq.zza("native:view_create", this.zzOo);
        this.zzZp = null;
        this.zzZo = null;
    }

    private void zzlB() {
        synchronized (this.zzrN) {
            this.zzWh = zzv.zzcN().zzjT();
            if (this.zzWh == null) {
                try {
                    evaluateJavascript("(function(){})()", null);
                    zzb((Boolean) true);
                } catch (IllegalStateException e) {
                    zzb((Boolean) false);
                }
            }
        }
    }

    private void zzlC() {
        zzgb.zza(this.zzZq.zzfv(), this.zzOo, "aeh2");
    }

    private void zzlD() {
        zzgb.zza(this.zzZq.zzfv(), this.zzOo, "aebb2");
    }

    private void zzlF() {
        synchronized (this.zzrN) {
            if (this.zzZd || this.zzum.zzzl) {
                if (Build.VERSION.SDK_INT < 14) {
                    zzpe.zzbc("Disabling hardware acceleration on an overlay.");
                    zzlG();
                } else {
                    zzpe.zzbc("Enabling hardware acceleration on an overlay.");
                    zzlH();
                }
            } else if (Build.VERSION.SDK_INT < 18) {
                zzpe.zzbc("Disabling hardware acceleration on an AdView.");
                zzlG();
            } else {
                zzpe.zzbc("Enabling hardware acceleration on an AdView.");
                zzlH();
            }
        }
    }

    private void zzlG() {
        synchronized (this.zzrN) {
            if (!this.zzZe) {
                zzv.zzcL().zzu(this);
            }
            this.zzZe = true;
        }
    }

    private void zzlH() {
        synchronized (this.zzrN) {
            if (this.zzZe) {
                zzv.zzcL().zzt(this);
            }
            this.zzZe = false;
        }
    }

    private void zzlI() {
        synchronized (this.zzrN) {
            this.zzZt = null;
        }
    }

    private void zzlJ() {
        if (this.zzZq != null) {
            zzgf zzfv = this.zzZq.zzfv();
            if (zzfv != null && zzv.zzcN().zzjN() != null) {
                zzv.zzcN().zzjN().zza(zzfv);
            }
        }
    }

    public void destroy() {
        synchronized (this.zzrN) {
            zzlJ();
            this.zzvP.zzkF();
            if (this.zzZa != null) {
                this.zzZa.close();
                this.zzZa.onDestroy();
                this.zzZa = null;
            }
            this.zzYZ.reset();
            if (!this.zzZc) {
                zzv.zzdg().zze(this);
                zzlI();
                this.zzZc = true;
                zzpe.m2431v("Initiating WebView self destruct sequence in 3...");
                this.zzYZ.zzls();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    @TargetApi(19)
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        synchronized (this.zzrN) {
            if (isDestroyed()) {
                zzpe.zzbe("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                }
            } else {
                super.evaluateJavascript(str, valueCallback);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        synchronized (this.zzrN) {
            if (!this.zzZc) {
                this.zzYZ.reset();
                zzv.zzdg().zze(this);
                zzlI();
            }
        }
        super.finalize();
    }

    public String getRequestId() {
        String str;
        synchronized (this.zzrN) {
            str = this.zzOn;
        }
        return str;
    }

    public int getRequestedOrientation() {
        int i;
        synchronized (this.zzrN) {
            i = this.zzZf;
        }
        return i;
    }

    public View getView() {
        return this;
    }

    public WebView getWebView() {
        return this;
    }

    public boolean isDestroyed() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzZc;
        }
        return z;
    }

    public void loadData(String str, String str2, String str3) {
        synchronized (this.zzrN) {
            if (!isDestroyed()) {
                super.loadData(str, str2, str3);
            } else {
                zzpe.zzbe("The webview is destroyed. Ignoring action.");
            }
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        synchronized (this.zzrN) {
            if (!isDestroyed()) {
                super.loadDataWithBaseURL(str, str2, str3, str4, str5);
            } else {
                zzpe.zzbe("The webview is destroyed. Ignoring action.");
            }
        }
    }

    public void loadUrl(String str) {
        synchronized (this.zzrN) {
            if (!isDestroyed()) {
                try {
                    super.loadUrl(str);
                } catch (Throwable th) {
                    String valueOf = String.valueOf(th);
                    zzpe.zzbe(new StringBuilder(String.valueOf(valueOf).length() + 24).append("Could not call loadUrl. ").append(valueOf).toString());
                }
            } else {
                zzpe.zzbe("The webview is destroyed. Ignoring action.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        boolean z = true;
        synchronized (this.zzrN) {
            super.onAttachedToWindow();
            if (!isDestroyed()) {
                this.zzvP.onAttachedToWindow();
            }
            boolean z2 = this.zzZj;
            if (zzkV() == null || !zzkV().zzlo()) {
                z = z2;
            } else if (!this.zzZk) {
                ViewTreeObserver.OnGlobalLayoutListener zzlp = zzkV().zzlp();
                if (zzlp != null) {
                    zzv.zzdh().zza(getView(), zzlp);
                }
                ViewTreeObserver.OnScrollChangedListener zzlq = zzkV().zzlq();
                if (zzlq != null) {
                    zzv.zzdh().zza(getView(), zzlq);
                }
                this.zzZk = true;
            }
            zzO(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        synchronized (this.zzrN) {
            if (!isDestroyed()) {
                this.zzvP.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzZk && zzkV() != null && zzkV().zzlo() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener zzlp = zzkV().zzlp();
                if (zzlp != null) {
                    zzv.zzcL().zza(getViewTreeObserver(), zzlp);
                }
                ViewTreeObserver.OnScrollChangedListener zzlq = zzkV().zzlq();
                if (zzlq != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzlq);
                }
                this.zzZk = false;
            }
        }
        zzO(false);
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzv.zzcJ().zzb(getContext(), intent);
        } catch (ActivityNotFoundException e) {
            zzpe.zzbc(new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length()).append("Couldn't find an Activity to view url/mimetype: ").append(str).append(" / ").append(str4).toString());
        }
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                if (zzkV() != null && zzkV().zzlz() != null) {
                    zzkV().zzlz().zzcc();
                }
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (zzfx.zzCp.get().booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if ((motionEvent.getActionMasked() == 8) && ((axisValue > 0.0f && !canScrollVertically(-1)) || ((axisValue < 0.0f && !canScrollVertically(1)) || ((axisValue2 > 0.0f && !canScrollHorizontally(-1)) || (axisValue2 < 0.0f && !canScrollHorizontally(1)))))) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void onGlobalLayout() {
        boolean zzlA = zzlA();
        zze zzkT = zzkT();
        if (zzkT != null && zzlA) {
            zzkT.zzho();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        return;
     */
    @SuppressLint({"DrawAllocation"})
    public void onMeasure(int i, int i2) {
        int size;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        synchronized (this.zzrN) {
            if (isDestroyed()) {
                setMeasuredDimension(0, 0);
            } else if (isInEditMode() || this.zzZd || this.zzum.zzzn) {
                super.onMeasure(i, i2);
            } else if (this.zzum.zzzo) {
                if (zzfx.zzEe.get().booleanValue() || !zzs.zzyD()) {
                    super.onMeasure(i, i2);
                    return;
                }
                zza("/contentHeight", zzlE());
                zzbj("(function() {  var height = -1;  if (document.body) { height = document.body.offsetHeight;}  else if (document.documentElement) {      height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  window.googleAdsJsInterface.notify(url);  })();");
                float f = this.zzYX.getResources().getDisplayMetrics().density;
                int size2 = View.MeasureSpec.getSize(i);
                switch (this.zzZn) {
                    case -1:
                        size = View.MeasureSpec.getSize(i2);
                        break;
                    default:
                        size = (int) (f * ((float) this.zzZn));
                        break;
                }
                setMeasuredDimension(size2, size);
            } else if (this.zzum.zzzl) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                this.zzwf.getDefaultDisplay().getMetrics(displayMetrics);
                setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
            } else {
                int mode = View.MeasureSpec.getMode(i);
                int size3 = View.MeasureSpec.getSize(i);
                int mode2 = View.MeasureSpec.getMode(i2);
                int size4 = View.MeasureSpec.getSize(i2);
                int i4 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size3 : Integer.MAX_VALUE;
                if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                    i3 = size4;
                }
                if (this.zzum.widthPixels > i4 || this.zzum.heightPixels > i3) {
                    float f2 = this.zzYX.getResources().getDisplayMetrics().density;
                    zzpe.zzbe(new StringBuilder(103).append("Not enough space to show ad. Needs ").append((int) (((float) this.zzum.widthPixels) / f2)).append("x").append((int) (((float) this.zzum.heightPixels) / f2)).append(" dp, but only has ").append((int) (((float) size3) / f2)).append("x").append((int) (((float) size4) / f2)).append(" dp.").toString());
                    if (getVisibility() != 8) {
                        setVisibility(4);
                    }
                    setMeasuredDimension(0, 0);
                } else {
                    if (getVisibility() != 8) {
                        setVisibility(0);
                    }
                    setMeasuredDimension(this.zzum.widthPixels, this.zzum.heightPixels);
                }
            }
        }
    }

    public void onPause() {
        if (!isDestroyed()) {
            try {
                if (zzs.zzyx()) {
                    super.onPause();
                }
            } catch (Exception e) {
                zzpe.zzb("Could not pause webview.", e);
            }
        }
    }

    public void onResume() {
        if (!isDestroyed()) {
            try {
                if (zzs.zzyx()) {
                    super.onResume();
                }
            } catch (Exception e) {
                zzpe.zzb("Could not resume webview.", e);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (zzkV().zzlo()) {
            synchronized (this.zzrN) {
                if (this.zzZl != null) {
                    this.zzZl.zzc(motionEvent);
                }
            }
        } else if (this.zzGr != null) {
            this.zzGr.zza(motionEvent);
        }
        if (isDestroyed()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setContext(Context context) {
        this.zzYX.setBaseContext(context);
        this.zzvP.zzl(this.zzYX.zzkR());
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzZr = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public void setRequestedOrientation(int i) {
        synchronized (this.zzrN) {
            this.zzZf = i;
            if (this.zzZa != null) {
                this.zzZa.setRequestedOrientation(this.zzZf);
            }
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzqq) {
            this.zzYZ = (zzqq) webViewClient;
        }
    }

    public void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Exception e) {
                zzpe.zzb("Could not stop loading webview.", e);
            }
        }
    }

    public void zzJ(boolean z) {
        synchronized (this.zzrN) {
            this.zzZd = z;
            zzlF();
        }
    }

    public void zzK(int i) {
        if (i == 0) {
            zzlD();
        }
        zzlC();
        if (this.zzZq.zzfv() != null) {
            this.zzZq.zzfv().zzg("close_type", String.valueOf(i));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, this.zztr.zzaZ);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public void zzK(boolean z) {
        synchronized (this.zzrN) {
            if (this.zzZa != null) {
                this.zzZa.zza(this.zzYZ.zzdz(), z);
            } else {
                this.zzZb = z;
            }
        }
    }

    public void zzL(boolean z) {
        synchronized (this.zzrN) {
            this.zzZg = z;
        }
    }

    public void zzM(boolean z) {
        synchronized (this.zzrN) {
            this.zzZm = (z ? 1 : -1) + this.zzZm;
            if (this.zzZm <= 0 && this.zzZa != null) {
                this.zzZa.zzhr();
            }
        }
    }

    public void zza(Context context, zzec zzec, zzgf zzgf) {
        synchronized (this.zzrN) {
            this.zzvP.zzkF();
            setContext(context);
            this.zzZa = null;
            this.zzum = zzec;
            this.zzZd = false;
            this.zzZb = false;
            this.zzOn = "";
            this.zzZf = -1;
            zzv.zzcL().zzm(this);
            loadUrl("about:blank");
            this.zzYZ.reset();
            setOnTouchListener(null);
            setOnClickListener(null);
            this.zzZg = true;
            this.zzZh = false;
            this.zzZi = null;
            zzd(zzgf);
            this.zzZj = false;
            this.zzZm = 0;
            zzv.zzdg().zze(this);
            zzlI();
        }
    }

    public void zza(zzcv.zza zza2) {
        synchronized (this.zzrN) {
            this.zzZj = zza2.zzxb;
        }
        zzO(zza2.zzxb);
    }

    public void zza(zzec zzec) {
        synchronized (this.zzrN) {
            this.zzum = zzec;
            requestLayout();
        }
    }

    public void zza(zzqu zzqu) {
        synchronized (this.zzrN) {
            if (this.zzZi != null) {
                zzpe.m2432e("Attempt to create multiple AdWebViewVideoControllers.");
            } else {
                this.zzZi = zzqu;
            }
        }
    }

    /* access modifiers changed from: protected */
    @TargetApi(19)
    public void zza(String str, ValueCallback<String> valueCallback) {
        synchronized (this.zzrN) {
            if (!isDestroyed()) {
                evaluateJavascript(str, valueCallback);
            } else {
                zzpe.zzbe("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                }
            }
        }
    }

    public void zza(String str, zzhx zzhx) {
        if (this.zzYZ != null) {
            this.zzYZ.zza(str, zzhx);
        }
    }

    public void zza(String str, Map<String, ?> map) {
        try {
            zzb(str, zzv.zzcJ().zzP(map));
        } catch (JSONException e) {
            zzpe.zzbe("Could not convert parameters to JSON.");
        }
    }

    public void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzi(str, jSONObject.toString());
    }

    public void zzb(zze zze) {
        synchronized (this.zzrN) {
            this.zzZa = zze;
        }
    }

    public void zzb(zzgs zzgs) {
        synchronized (this.zzrN) {
            this.zzZl = zzgs;
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(Boolean bool) {
        synchronized (this.zzrN) {
            this.zzWh = bool;
        }
        zzv.zzcN().zzb(bool);
    }

    public void zzb(String str, zzhx zzhx) {
        if (this.zzYZ != null) {
            this.zzYZ.zzb(str, zzhx);
        }
    }

    public void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String valueOf = String.valueOf(sb.toString());
        zzpe.zzbc(valueOf.length() != 0 ? "Dispatching AFMA event: ".concat(valueOf) : new String("Dispatching AFMA event: "));
        zzbj(sb.toString());
    }

    public zzec zzbD() {
        zzec zzec;
        synchronized (this.zzrN) {
            zzec = this.zzum;
        }
        return zzec;
    }

    public void zzbV() {
        synchronized (this.zzrN) {
            this.zzZh = true;
            if (this.zzYY != null) {
                this.zzYY.zzbV();
            }
        }
    }

    public void zzbW() {
        synchronized (this.zzrN) {
            this.zzZh = false;
            if (this.zzYY != null) {
                this.zzYY.zzbW();
            }
        }
    }

    public void zzbf(String str) {
        synchronized (this.zzrN) {
            try {
                super.loadUrl(str);
            } catch (Throwable th) {
                String valueOf = String.valueOf(th);
                zzpe.zzbe(new StringBuilder(String.valueOf(valueOf).length() + 24).append("Could not call loadUrl. ").append(valueOf).toString());
            }
        }
    }

    public void zzbg(String str) {
        synchronized (this.zzrN) {
            if (str == null) {
                str = "";
            }
            this.zzOn = str;
        }
    }

    /* access modifiers changed from: protected */
    public void zzbi(String str) {
        synchronized (this.zzrN) {
            if (!isDestroyed()) {
                loadUrl(str);
            } else {
                zzpe.zzbe("The webview is destroyed. Ignoring action.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbj(String str) {
        if (zzs.zzyF()) {
            if (zzjT() == null) {
                zzlB();
            }
            if (zzjT().booleanValue()) {
                zza(str, (ValueCallback<String>) null);
                return;
            }
            String valueOf = String.valueOf(str);
            zzbi(valueOf.length() != 0 ? "javascript:".concat(valueOf) : new String("javascript:"));
            return;
        }
        String valueOf2 = String.valueOf(str);
        zzbi(valueOf2.length() != 0 ? "javascript:".concat(valueOf2) : new String("javascript:"));
    }

    public zzd zzbz() {
        return this.zzsz;
    }

    public void zzc(zze zze) {
        synchronized (this.zzrN) {
            this.zzZs = zze;
        }
    }

    public void zzhp() {
        if (this.zzZo == null) {
            zzgb.zza(this.zzZq.zzfv(), this.zzOo, "aes2");
            this.zzZo = zzgb.zzb(this.zzZq.zzfv());
            this.zzZq.zza("native:view_show", this.zzZo);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, this.zztr.zzaZ);
        zza("onshow", (Map<String, ?>) hashMap);
    }

    public void zzi(String str, String str2) {
        zzbj(new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length()).append(str).append("(").append(str2).append(");").toString());
    }

    /* access modifiers changed from: package-private */
    public Boolean zzjT() {
        Boolean bool;
        synchronized (this.zzrN) {
            bool = this.zzWh;
        }
        return bool;
    }

    public void zzkP() {
        zzlC();
        HashMap hashMap = new HashMap(1);
        hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, this.zztr.zzaZ);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public void zzkQ() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzv.zzcJ().zzcq()));
        hashMap.put("app_volume", String.valueOf(zzv.zzcJ().zzco()));
        hashMap.put("device_volume", String.valueOf(zzv.zzcJ().zzH(getContext())));
        zza("volume", (Map<String, ?>) hashMap);
    }

    public Activity zzkR() {
        return this.zzYX.zzkR();
    }

    public Context zzkS() {
        return this.zzYX.zzkS();
    }

    public zze zzkT() {
        zze zze;
        synchronized (this.zzrN) {
            zze = this.zzZa;
        }
        return zze;
    }

    public zze zzkU() {
        zze zze;
        synchronized (this.zzrN) {
            zze = this.zzZs;
        }
        return zze;
    }

    public zzqq zzkV() {
        return this.zzYZ;
    }

    public boolean zzkW() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzZb;
        }
        return z;
    }

    public zzav zzkX() {
        return this.zzGr;
    }

    public zzqa zzkY() {
        return this.zztr;
    }

    public boolean zzkZ() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzZd;
        }
        return z;
    }

    public boolean zzlA() {
        int i;
        int i2;
        if (!zzkV().zzdz() && !zzkV().zzlo()) {
            return false;
        }
        DisplayMetrics zza2 = zzv.zzcJ().zza(this.zzwf);
        int zzb = zzeh.zzeO().zzb(zza2, zza2.widthPixels);
        int zzb2 = zzeh.zzeO().zzb(zza2, zza2.heightPixels);
        Activity zzkR = zzkR();
        if (zzkR == null || zzkR.getWindow() == null) {
            i = zzb2;
            i2 = zzb;
        } else {
            int[] zzh = zzv.zzcJ().zzh(zzkR);
            i2 = zzeh.zzeO().zzb(zza2, zzh[0]);
            i = zzeh.zzeO().zzb(zza2, zzh[1]);
        }
        if (this.zzLQ == zzb && this.zzLR == zzb2 && this.zzLT == i2 && this.zzLU == i) {
            return false;
        }
        boolean z = (this.zzLQ == zzb && this.zzLR == zzb2) ? false : true;
        this.zzLQ = zzb;
        this.zzLR = zzb2;
        this.zzLT = i2;
        this.zzLU = i;
        new zzko(this).zza(zzb, zzb2, i2, i, zza2.density, this.zzwf.getDefaultDisplay().getRotation());
        return z;
    }

    /* access modifiers changed from: package-private */
    public zzhx zzlE() {
        return new zzhx() {
            public void zza(zzqp zzqp, Map<String, String> map) {
                if (map != null) {
                    String str = map.get("height");
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            int parseInt = Integer.parseInt(str);
                            synchronized (zzqt.this.zzrN) {
                                if (zzqt.this.zzZn != parseInt) {
                                    int unused = zzqt.this.zzZn = parseInt;
                                    zzqt.this.requestLayout();
                                }
                            }
                        } catch (Exception e) {
                            zzpe.zzc("Exception occurred while getting webview content height", e);
                        }
                    }
                }
            }
        };
    }

    public void zzla() {
        synchronized (this.zzrN) {
            zzpe.m2431v("Destroying WebView!");
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zzqt.super.destroy();
                }
            });
        }
    }

    public boolean zzlb() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzZg;
        }
        return z;
    }

    public boolean zzlc() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzZh;
        }
        return z;
    }

    public zzqo zzld() {
        return null;
    }

    public zzgd zzle() {
        return this.zzOo;
    }

    public zzge zzlf() {
        return this.zzZq;
    }

    public zzqu zzlg() {
        zzqu zzqu;
        synchronized (this.zzrN) {
            zzqu = this.zzZi;
        }
        return zzqu;
    }

    public boolean zzlh() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzZm > 0;
        }
        return z;
    }

    public void zzli() {
        this.zzvP.zzkE();
    }

    public void zzlj() {
        if (this.zzZp == null) {
            this.zzZp = zzgb.zzb(this.zzZq.zzfv());
            this.zzZq.zza("native:view_load", this.zzZp);
        }
    }

    public View.OnClickListener zzlk() {
        return (View.OnClickListener) this.zzZr.get();
    }

    public zzgs zzll() {
        zzgs zzgs;
        synchronized (this.zzrN) {
            zzgs = this.zzZl;
        }
        return zzgs;
    }

    public void zzlm() {
        setBackgroundColor(0);
    }
}
