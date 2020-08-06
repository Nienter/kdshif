package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@TargetApi(11)
@zzmb
public class zzqx extends zzqz {
    public zzqx(zzqp zzqp, boolean z) {
        super(zzqp, z);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, null);
    }
}
