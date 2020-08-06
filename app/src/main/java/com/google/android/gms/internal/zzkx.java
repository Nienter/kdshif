package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzmb
public class zzkx implements zzkv {
    private final Context mContext;
    final Set<WebView> zzOG = Collections.synchronizedSet(new HashSet());

    public zzkx(Context context) {
        this.mContext = context;
    }

    public void zza(String str, final String str2, final String str3) {
        zzpe.zzbc("Fetching assets for the given html");
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                final WebView zzij = zzkx.this.zzij();
                zzij.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView webView, String str) {
                        zzpe.zzbc("Loading assets have finished");
                        zzkx.this.zzOG.remove(zzij);
                    }

                    public void onReceivedError(WebView webView, int i, String str, String str2) {
                        zzpe.zzbe("Loading assets have failed.");
                        zzkx.this.zzOG.remove(zzij);
                    }
                });
                zzkx.this.zzOG.add(zzij);
                zzij.loadDataWithBaseURL(str2, str3, "text/html", "UTF-8", null);
                zzpe.zzbc("Fetching assets finished.");
            }
        });
    }

    public WebView zzij() {
        WebView webView = new WebView(this.mContext);
        webView.getSettings().setJavaScriptEnabled(true);
        return webView;
    }
}
