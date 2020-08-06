package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.internal.zzcv;
import java.util.Map;
import org.json.JSONObject;

@zzmb
class zzqs extends FrameLayout implements zzqp {
    private static final int zzMx = Color.argb(0, 0, 0, 0);
    private final zzqp zzYV;
    private final zzqo zzYW;

    public zzqs(zzqp zzqp) {
        super(zzqp.getContext());
        this.zzYV = zzqp;
        this.zzYW = new zzqo(zzqp.zzkS(), this, this);
        zzqq zzkV = this.zzYV.zzkV();
        if (zzkV != null) {
            zzkV.zzo(this);
        }
        addView(this.zzYV.getView());
    }

    public void destroy() {
        this.zzYV.destroy();
    }

    public String getRequestId() {
        return this.zzYV.getRequestId();
    }

    public int getRequestedOrientation() {
        return this.zzYV.getRequestedOrientation();
    }

    public View getView() {
        return this;
    }

    public WebView getWebView() {
        return this.zzYV.getWebView();
    }

    public boolean isDestroyed() {
        return this.zzYV.isDestroyed();
    }

    public void loadData(String str, String str2, String str3) {
        this.zzYV.loadData(str, str2, str3);
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzYV.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void loadUrl(String str) {
        this.zzYV.loadUrl(str);
    }

    public void onPause() {
        this.zzYW.onPause();
        this.zzYV.onPause();
    }

    public void onResume() {
        this.zzYV.onResume();
    }

    public void setContext(Context context) {
        this.zzYV.setContext(context);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzYV.setOnClickListener(onClickListener);
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.zzYV.setOnTouchListener(onTouchListener);
    }

    public void setRequestedOrientation(int i) {
        this.zzYV.setRequestedOrientation(i);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzYV.setWebChromeClient(webChromeClient);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.zzYV.setWebViewClient(webViewClient);
    }

    public void stopLoading() {
        this.zzYV.stopLoading();
    }

    public void zzJ(boolean z) {
        this.zzYV.zzJ(z);
    }

    public void zzK(int i) {
        this.zzYV.zzK(i);
    }

    public void zzK(boolean z) {
        this.zzYV.zzK(z);
    }

    public void zzL(boolean z) {
        this.zzYV.zzL(z);
    }

    public void zzM(boolean z) {
        this.zzYV.zzM(z);
    }

    public void zza(Context context, zzec zzec, zzgf zzgf) {
        this.zzYW.onDestroy();
        this.zzYV.zza(context, zzec, zzgf);
    }

    public void zza(zzcv.zza zza) {
        this.zzYV.zza(zza);
    }

    public void zza(zzec zzec) {
        this.zzYV.zza(zzec);
    }

    public void zza(zzqu zzqu) {
        this.zzYV.zza(zzqu);
    }

    public void zza(String str, zzhx zzhx) {
        this.zzYV.zza(str, zzhx);
    }

    public void zza(String str, Map<String, ?> map) {
        this.zzYV.zza(str, map);
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzYV.zza(str, jSONObject);
    }

    public void zzb(zze zze) {
        this.zzYV.zzb(zze);
    }

    public void zzb(@Nullable zzgs zzgs) {
        this.zzYV.zzb(zzgs);
    }

    public void zzb(String str, zzhx zzhx) {
        this.zzYV.zzb(str, zzhx);
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzYV.zzb(str, jSONObject);
    }

    public zzec zzbD() {
        return this.zzYV.zzbD();
    }

    public void zzbV() {
        this.zzYV.zzbV();
    }

    public void zzbW() {
        this.zzYV.zzbW();
    }

    public void zzbf(String str) {
        this.zzYV.zzbf(str);
    }

    public void zzbg(String str) {
        this.zzYV.zzbg(str);
    }

    public zzd zzbz() {
        return this.zzYV.zzbz();
    }

    public void zzc(zze zze) {
        this.zzYV.zzc(zze);
    }

    public void zzhp() {
        this.zzYV.zzhp();
    }

    public void zzi(String str, String str2) {
        this.zzYV.zzi(str, str2);
    }

    public void zzkP() {
        this.zzYV.zzkP();
    }

    public void zzkQ() {
        this.zzYV.zzkQ();
    }

    public Activity zzkR() {
        return this.zzYV.zzkR();
    }

    public Context zzkS() {
        return this.zzYV.zzkS();
    }

    public zze zzkT() {
        return this.zzYV.zzkT();
    }

    public zze zzkU() {
        return this.zzYV.zzkU();
    }

    public zzqq zzkV() {
        return this.zzYV.zzkV();
    }

    public boolean zzkW() {
        return this.zzYV.zzkW();
    }

    public zzav zzkX() {
        return this.zzYV.zzkX();
    }

    public zzqa zzkY() {
        return this.zzYV.zzkY();
    }

    public boolean zzkZ() {
        return this.zzYV.zzkZ();
    }

    public void zzla() {
        this.zzYW.onDestroy();
        this.zzYV.zzla();
    }

    public boolean zzlb() {
        return this.zzYV.zzlb();
    }

    public boolean zzlc() {
        return this.zzYV.zzlc();
    }

    public zzqo zzld() {
        return this.zzYW;
    }

    public zzgd zzle() {
        return this.zzYV.zzle();
    }

    public zzge zzlf() {
        return this.zzYV.zzlf();
    }

    public zzqu zzlg() {
        return this.zzYV.zzlg();
    }

    public boolean zzlh() {
        return this.zzYV.zzlh();
    }

    public void zzli() {
        this.zzYV.zzli();
    }

    public void zzlj() {
        this.zzYV.zzlj();
    }

    public View.OnClickListener zzlk() {
        return this.zzYV.zzlk();
    }

    @Nullable
    public zzgs zzll() {
        return this.zzYV.zzll();
    }

    public void zzlm() {
        setBackgroundColor(zzMx);
        this.zzYV.setBackgroundColor(zzMx);
    }
}
