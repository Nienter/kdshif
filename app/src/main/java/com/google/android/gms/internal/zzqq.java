package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p001v4.media.TransportMediator;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewTreeObserver;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzf;
import com.google.android.gms.ads.internal.overlay.zzh;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.zzv;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@zzmb
public class zzqq extends WebViewClient {
    private static final String[] zzYA = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzYB = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    protected zzqp zzGt;
    private zzib zzHL;
    private com.google.android.gms.ads.internal.zze zzHN;
    private zzkj zzHO;
    private zzhz zzHQ;
    private zzht zzHc;
    private zzkp zzLz;
    private zza zzPx;
    private final HashMap<String, List<zzhx>> zzYC;
    private zzh zzYD;
    private zzb zzYE;
    /* access modifiers changed from: private */
    public zzc zzYF;
    private boolean zzYG;
    private boolean zzYH;
    private ViewTreeObserver.OnGlobalLayoutListener zzYI;
    private ViewTreeObserver.OnScrollChangedListener zzYJ;
    private boolean zzYK;
    private zzq zzYL;
    private final zzkn zzYM;
    private zze zzYN;
    @Nullable
    protected zzop zzYO;
    private boolean zzYP;
    private boolean zzYQ;
    private boolean zzYR;
    private int zzYS;
    private final Object zzrN;
    private boolean zzvV;
    private zzdt zzyD;

    public interface zza {
        void zza(zzqp zzqp, boolean z);
    }

    public interface zzb {
        void zzk(zzqp zzqp);
    }

    public interface zzc {
        void zzcd();
    }

    private static class zzd implements zzh {
        private zzh zzYD;
        private zzqp zzYU;

        public zzd(zzqp zzqp, zzh zzh) {
            this.zzYU = zzqp;
            this.zzYD = zzh;
        }

        public void onPause() {
        }

        public void onResume() {
        }

        public void zzbN() {
            this.zzYD.zzbN();
            this.zzYU.zzkP();
        }

        public void zzbO() {
            this.zzYD.zzbO();
            this.zzYU.zzhp();
        }
    }

    public interface zze {
        void zzcc();
    }

    public zzqq(zzqp zzqp, boolean z) {
        this(zzqp, z, new zzkn(zzqp, zzqp.zzkS(), new zzfp(zzqp.getContext())), null);
    }

    zzqq(zzqp zzqp, boolean z, zzkn zzkn, zzkj zzkj) {
        this.zzYC = new HashMap<>();
        this.zzrN = new Object();
        this.zzYG = false;
        this.zzGt = zzqp;
        this.zzvV = z;
        this.zzYM = zzkn;
        this.zzHO = zzkj;
    }

    private void zzb(Context context, String str, String str2, String str3) {
        if (zzfx.zzCS.get().booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString("err", str);
            bundle.putString("code", str2);
            bundle.putString("host", zzbh(str3));
            zzv.zzcJ().zza(context, this.zzGt.zzkY().zzaZ, "gmob-apps", bundle, true);
        }
    }

    private String zzbh(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Uri parse = Uri.parse(str);
        return parse.getHost() != null ? parse.getHost() : "";
    }

    private static boolean zzi(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private void zzlx() {
        if (this.zzYE != null) {
            this.zzYE.zzk(this.zzGt);
            this.zzYE = null;
        }
    }

    public final void onLoadResource(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzpe.m2431v(valueOf.length() != 0 ? "Loading resource: ".concat(valueOf) : new String("Loading resource: "));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzj(parse);
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        synchronized (this.zzrN) {
            if (this.zzYP) {
                zzpe.m2431v("Blank page loaded, 1...");
                this.zzGt.zzla();
                return;
            }
            this.zzYQ = true;
            zzlx();
            zzly();
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        zzb(this.zzGt.getContext(), "http_err", (i >= 0 || (-i) + -1 >= zzYA.length) ? String.valueOf(i) : zzYA[(-i) - 1], str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            zzb(this.zzGt.getContext(), "ssl_err", (primaryError < 0 || primaryError >= zzYB.length) ? String.valueOf(primaryError) : zzYB[primaryError], zzv.zzcL().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        if (this.zzYO != null) {
            this.zzYO = null;
        }
        synchronized (this.zzrN) {
            this.zzYC.clear();
            this.zzyD = null;
            this.zzYD = null;
            this.zzPx = null;
            this.zzYE = null;
            this.zzHc = null;
            this.zzYG = false;
            this.zzvV = false;
            this.zzYH = false;
            this.zzYK = false;
            this.zzHQ = null;
            this.zzYL = null;
            this.zzYF = null;
            if (this.zzHO != null) {
                this.zzHO.zzs(true);
                this.zzHO = null;
            }
        }
    }

    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        try {
            zzdo zzJ = zzdo.zzJ(str);
            if (zzJ == null) {
                return null;
            }
            zzdl zza2 = zzv.zzcO().zza(zzJ);
            if (zza2 == null || !zza2.zzer()) {
                return null;
            }
            return new WebResourceResponse("", "", zza2.zzes());
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case TransportMediator.KEYCODE_MEDIA_PLAY:
            case TransportMediator.KEYCODE_MEDIA_PAUSE:
            case 128:
            case 129:
            case TransportMediator.KEYCODE_MEDIA_RECORD:
            case 222:
                return true;
            default:
                return false;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Uri uri;
        String valueOf = String.valueOf(str);
        zzpe.m2431v(valueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(valueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzj(parse);
        } else if (this.zzYG && webView == this.zzGt.getWebView() && zzi(parse)) {
            if (this.zzyD != null && zzfx.zzCi.get().booleanValue()) {
                this.zzyD.onAdClicked();
                this.zzyD = null;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        } else if (!this.zzGt.getWebView().willNotDraw()) {
            try {
                zzav zzkX = this.zzGt.zzkX();
                if (zzkX != null && zzkX.zzc(parse)) {
                    parse = zzkX.zza(parse, this.zzGt.getContext(), this.zzGt.getView());
                }
                uri = parse;
            } catch (zzaw e) {
                String valueOf2 = String.valueOf(str);
                zzpe.zzbe(valueOf2.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf2) : new String("Unable to append parameter to URL: "));
                uri = parse;
            }
            if (this.zzHN == null || this.zzHN.zzcb()) {
                zza(new com.google.android.gms.ads.internal.overlay.zzc("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
            } else {
                this.zzHN.zzx(str);
            }
        } else {
            String valueOf3 = String.valueOf(str);
            zzpe.zzbe(valueOf3.length() != 0 ? "AdWebView unable to handle URL: ".concat(valueOf3) : new String("AdWebView unable to handle URL: "));
        }
        return true;
    }

    public void zzN(boolean z) {
        this.zzYG = z;
    }

    public void zza(int i, int i2, boolean z) {
        this.zzYM.zze(i, i2);
        if (this.zzHO != null) {
            this.zzHO.zza(i, i2, z);
        }
    }

    public final void zza(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.zzrN) {
            this.zzYH = true;
            this.zzGt.zzli();
            this.zzYI = onGlobalLayoutListener;
            this.zzYJ = onScrollChangedListener;
        }
    }

    public void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean zzgR = this.zzHO != null ? this.zzHO.zzgR() : false;
        zzf zzcH = zzv.zzcH();
        Context context = this.zzGt.getContext();
        if (!zzgR) {
            z = true;
        }
        zzcH.zza(context, adOverlayInfoParcel, z);
        if (this.zzYO != null && adOverlayInfoParcel.url == null && adOverlayInfoParcel.zzMW != null) {
            String str = adOverlayInfoParcel.zzMW.url;
        }
    }

    public final void zza(com.google.android.gms.ads.internal.overlay.zzc zzc2) {
        zzh zzh = null;
        boolean zzkZ = this.zzGt.zzkZ();
        zzdt zzdt = (!zzkZ || this.zzGt.zzbD().zzzl) ? this.zzyD : null;
        if (!zzkZ) {
            zzh = this.zzYD;
        }
        zza(new AdOverlayInfoParcel(zzc2, zzdt, zzh, this.zzYL, this.zzGt.zzkY()));
    }

    public void zza(zzdt zzdt, zzh zzh, zzht zzht, zzq zzq, boolean z, zzhz zzhz, @Nullable zzib zzib, com.google.android.gms.ads.internal.zze zze2, zzkp zzkp, @Nullable zzop zzop) {
        if (zze2 == null) {
            zze2 = new com.google.android.gms.ads.internal.zze(this.zzGt.getContext());
        }
        this.zzHO = new zzkj(this.zzGt, zzkp);
        this.zzYO = zzop;
        zza("/appEvent", (zzhx) new zzhs(zzht));
        zza("/backButton", zzhw.zzHo);
        zza("/refresh", zzhw.zzHp);
        zza("/canOpenURLs", zzhw.zzHe);
        zza("/canOpenIntents", zzhw.zzHf);
        zza("/click", zzhw.zzHg);
        zza("/close", zzhw.zzHh);
        zza("/customClose", zzhw.zzHj);
        zza("/instrument", zzhw.zzHt);
        zza("/delayPageLoaded", zzhw.zzHv);
        zza("/delayPageClosed", zzhw.zzHw);
        zza("/getLocationInfo", zzhw.zzHx);
        zza("/httpTrack", zzhw.zzHk);
        zza("/log", zzhw.zzHl);
        zza("/mraid", (zzhx) new zzid(zze2, this.zzHO));
        zza("/mraidLoaded", (zzhx) this.zzYM);
        zza("/open", (zzhx) new zzie(zzhz, zze2, this.zzHO));
        zza("/precache", zzhw.zzHs);
        zza("/touch", zzhw.zzHn);
        zza("/video", zzhw.zzHq);
        zza("/videoMeta", zzhw.zzHr);
        zza("/appStreaming", zzhw.zzHi);
        if (zzib != null) {
            zza("/setInterstitialProperties", (zzhx) new zzia(zzib));
        }
        this.zzyD = zzdt;
        this.zzYD = zzh;
        this.zzHc = zzht;
        this.zzHQ = zzhz;
        this.zzYL = zzq;
        this.zzHN = zze2;
        this.zzLz = zzkp;
        this.zzHL = zzib;
        zzN(z);
    }

    public void zza(zza zza2) {
        this.zzPx = zza2;
    }

    public void zza(zzb zzb2) {
        this.zzYE = zzb2;
    }

    public void zza(zzc zzc2) {
        this.zzYF = zzc2;
    }

    public void zza(zze zze2) {
        this.zzYN = zze2;
    }

    public void zza(String str, zzhx zzhx) {
        synchronized (this.zzrN) {
            List list = this.zzYC.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzYC.put(str, list);
            }
            list.add(zzhx);
        }
    }

    public final void zza(boolean z, int i) {
        zza(new AdOverlayInfoParcel((!this.zzGt.zzkZ() || this.zzGt.zzbD().zzzl) ? this.zzyD : null, this.zzYD, this.zzYL, this.zzGt, z, i, this.zzGt.zzkY()));
    }

    public final void zza(boolean z, int i, String str) {
        zzd zzd2 = null;
        boolean zzkZ = this.zzGt.zzkZ();
        zzdt zzdt = (!zzkZ || this.zzGt.zzbD().zzzl) ? this.zzyD : null;
        if (!zzkZ) {
            zzd2 = new zzd(this.zzGt, this.zzYD);
        }
        zza(new AdOverlayInfoParcel(zzdt, zzd2, this.zzHc, this.zzYL, this.zzGt, z, i, str, this.zzGt.zzkY(), this.zzHQ));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzkZ = this.zzGt.zzkZ();
        zza(new AdOverlayInfoParcel((!zzkZ || this.zzGt.zzbD().zzzl) ? this.zzyD : null, zzkZ ? null : new zzd(this.zzGt, this.zzYD), this.zzHc, this.zzYL, this.zzGt, z, i, str, str2, this.zzGt.zzkY(), this.zzHQ));
    }

    public void zzb(String str, zzhx zzhx) {
        synchronized (this.zzrN) {
            List list = this.zzYC.get(str);
            if (list != null) {
                list.remove(zzhx);
            }
        }
    }

    public void zzd(int i, int i2) {
        if (this.zzHO != null) {
            this.zzHO.zzd(i, i2);
        }
    }

    public boolean zzdz() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzvV;
        }
        return z;
    }

    public final void zzhl() {
        synchronized (this.zzrN) {
            this.zzYG = false;
            this.zzvV = true;
            zzv.zzcJ().runOnUiThread(new Runnable() {
                public void run() {
                    zzqq.this.zzGt.zzli();
                    com.google.android.gms.ads.internal.overlay.zze zzkT = zzqq.this.zzGt.zzkT();
                    if (zzkT != null) {
                        zzkT.zzhl();
                    }
                    if (zzqq.this.zzYF != null) {
                        zzqq.this.zzYF.zzcd();
                        zzc unused = zzqq.this.zzYF = null;
                    }
                }
            });
        }
    }

    public void zzj(Uri uri) {
        String path = uri.getPath();
        List<zzhx> list = this.zzYC.get(path);
        if (list != null) {
            Map<String, String> zzg = zzv.zzcJ().zzg(uri);
            if (zzpe.zzai(2)) {
                String valueOf = String.valueOf(path);
                zzpe.m2431v(valueOf.length() != 0 ? "Received GMSG: ".concat(valueOf) : new String("Received GMSG: "));
                for (String next : zzg.keySet()) {
                    String str = zzg.get(next);
                    zzpe.m2431v(new StringBuilder(String.valueOf(next).length() + 4 + String.valueOf(str).length()).append("  ").append(next).append(": ").append(str).toString());
                }
            }
            for (zzhx zza2 : list) {
                zza2.zza(this.zzGt, zzg);
            }
            return;
        }
        String valueOf2 = String.valueOf(uri);
        zzpe.m2431v(new StringBuilder(String.valueOf(valueOf2).length() + 32).append("No GMSG handler found for GMSG: ").append(valueOf2).toString());
    }

    public com.google.android.gms.ads.internal.zze zzln() {
        return this.zzHN;
    }

    public boolean zzlo() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzYH;
        }
        return z;
    }

    public ViewTreeObserver.OnGlobalLayoutListener zzlp() {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.zzrN) {
            onGlobalLayoutListener = this.zzYI;
        }
        return onGlobalLayoutListener;
    }

    public ViewTreeObserver.OnScrollChangedListener zzlq() {
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
        synchronized (this.zzrN) {
            onScrollChangedListener = this.zzYJ;
        }
        return onScrollChangedListener;
    }

    public boolean zzlr() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzYK;
        }
        return z;
    }

    public void zzls() {
        synchronized (this.zzrN) {
            zzpe.m2431v("Loading blank page in WebView, 2...");
            this.zzYP = true;
            this.zzGt.zzbf("about:blank");
        }
    }

    public void zzlt() {
        if (this.zzYO != null) {
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    if (zzqq.this.zzYO != null) {
                        zzop zzop = zzqq.this.zzYO;
                        zzqp zzqp = zzqq.this.zzGt;
                    }
                }
            });
        }
    }

    public void zzlu() {
        synchronized (this.zzrN) {
            this.zzYK = true;
        }
        this.zzYS++;
        zzly();
    }

    public void zzlv() {
        this.zzYS--;
        zzly();
    }

    public void zzlw() {
        this.zzYR = true;
        zzly();
    }

    public final void zzly() {
        if (this.zzPx != null && ((this.zzYQ && this.zzYS <= 0) || this.zzYR)) {
            this.zzPx.zza(this.zzGt, !this.zzYR);
            this.zzPx = null;
        }
        this.zzGt.zzlj();
    }

    public zze zzlz() {
        return this.zzYN;
    }

    public void zzo(zzqp zzqp) {
        this.zzGt = zzqp;
    }
}
