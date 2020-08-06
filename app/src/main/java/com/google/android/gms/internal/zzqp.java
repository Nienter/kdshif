package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzt;
import com.google.android.gms.internal.zzcv;
import java.util.Map;
import org.json.JSONObject;

@zzmb
public interface zzqp extends zzt, zzcv.zzb, zzjb {
    void destroy();

    Context getContext();

    ViewGroup.LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    int getMeasuredHeight();

    int getMeasuredWidth();

    ViewParent getParent();

    String getRequestId();

    int getRequestedOrientation();

    View getView();

    WebView getWebView();

    boolean isDestroyed();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, @Nullable String str5);

    void loadUrl(String str);

    void measure(int i, int i2);

    void onPause();

    void onResume();

    void setBackgroundColor(int i);

    void setContext(Context context);

    void setOnClickListener(View.OnClickListener onClickListener);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setRequestedOrientation(int i);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void stopLoading();

    void zzJ(boolean z);

    void zzK(int i);

    void zzK(boolean z);

    void zzL(boolean z);

    void zzM(boolean z);

    void zza(Context context, zzec zzec, zzgf zzgf);

    void zza(zzec zzec);

    void zza(zzqu zzqu);

    void zza(String str, Map<String, ?> map);

    void zza(String str, JSONObject jSONObject);

    void zzb(zze zze);

    void zzb(zzgs zzgs);

    zzec zzbD();

    void zzbf(String str);

    void zzbg(String str);

    zzd zzbz();

    void zzc(zze zze);

    void zzhp();

    void zzi(String str, String str2);

    void zzkP();

    void zzkQ();

    Activity zzkR();

    Context zzkS();

    zze zzkT();

    zze zzkU();

    @Nullable
    zzqq zzkV();

    boolean zzkW();

    zzav zzkX();

    zzqa zzkY();

    boolean zzkZ();

    void zzla();

    boolean zzlb();

    boolean zzlc();

    @Nullable
    zzqo zzld();

    @Nullable
    zzgd zzle();

    zzge zzlf();

    @Nullable
    zzqu zzlg();

    boolean zzlh();

    void zzli();

    void zzlj();

    @Nullable
    View.OnClickListener zzlk();

    zzgs zzll();

    void zzlm();
}
