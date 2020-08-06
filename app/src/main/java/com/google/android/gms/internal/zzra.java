package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.internal.zzaa;
import java.net.URI;
import java.net.URISyntaxException;

@zzmb
public class zzra extends WebViewClient {
    private final zzqp zzGt;
    private final zzlk zzPD;
    private final String zzZL;
    private boolean zzZM = false;

    public zzra(zzlk zzlk, zzqp zzqp, String str) {
        this.zzZL = zzbm(str);
        this.zzGt = zzqp;
        this.zzPD = zzlk;
    }

    private String zzbm(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return str.endsWith("/") ? str.substring(0, str.length() - 1) : str;
        } catch (IndexOutOfBoundsException e) {
            zzpe.m2432e(e.getMessage());
            return str;
        }
    }

    public void onLoadResource(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzpe.zzbc(valueOf.length() != 0 ? "JavascriptAdWebViewClient::onLoadResource: ".concat(valueOf) : new String("JavascriptAdWebViewClient::onLoadResource: "));
        if (!zzbl(str)) {
            this.zzGt.zzkV().onLoadResource(this.zzGt.getWebView(), str);
        }
    }

    public void onPageFinished(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzpe.zzbc(valueOf.length() != 0 ? "JavascriptAdWebViewClient::onPageFinished: ".concat(valueOf) : new String("JavascriptAdWebViewClient::onPageFinished: "));
        if (!this.zzZM) {
            this.zzPD.zzix();
            this.zzZM = true;
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzpe.zzbc(valueOf.length() != 0 ? "JavascriptAdWebViewClient::shouldOverrideUrlLoading: ".concat(valueOf) : new String("JavascriptAdWebViewClient::shouldOverrideUrlLoading: "));
        if (!zzbl(str)) {
            return this.zzGt.zzkV().shouldOverrideUrlLoading(this.zzGt.getWebView(), str);
        }
        zzpe.zzbc("shouldOverrideUrlLoading: received passback url");
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean zzbl(String str) {
        String zzbm = zzbm(str);
        if (TextUtils.isEmpty(zzbm)) {
            return false;
        }
        try {
            URI uri = new URI(zzbm);
            if ("passback".equals(uri.getScheme())) {
                zzpe.zzbc("Passback received");
                this.zzPD.zziy();
                return true;
            } else if (TextUtils.isEmpty(this.zzZL)) {
                return false;
            } else {
                URI uri2 = new URI(this.zzZL);
                String host = uri2.getHost();
                String host2 = uri.getHost();
                String path = uri2.getPath();
                String path2 = uri.getPath();
                if (!zzaa.equal(host, host2) || !zzaa.equal(path, path2)) {
                    return false;
                }
                zzpe.zzbc("Passback received");
                this.zzPD.zziy();
                return true;
            }
        } catch (URISyntaxException e) {
            zzpe.m2432e(e.getMessage());
            return false;
        }
    }
}
