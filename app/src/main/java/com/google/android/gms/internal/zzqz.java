package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzv;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

@TargetApi(11)
@zzmb
public class zzqz extends zzqq {
    public zzqz(zzqp zzqp, boolean z) {
        super(zzqp, z);
    }

    /* access modifiers changed from: protected */
    public WebResourceResponse zza(WebView webView, String str, @Nullable Map<String, String> map) {
        if (!(webView instanceof zzqp)) {
            zzpe.zzbe("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzqp zzqp = (zzqp) webView;
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            return super.shouldInterceptRequest(webView, str);
        }
        if (zzqp.zzkV() != null) {
            zzqp.zzkV().zzhl();
        }
        try {
            return zzf(zzqp.getContext(), zzqp.zzkY().zzaZ, zzqp.zzbD().zzzl ? zzfx.zzBJ.get() : zzqp.zzkZ() ? zzfx.zzBI.get() : zzfx.zzBH.get());
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzpe.zzbe(valueOf.length() != 0 ? "Could not fetch MRAID JS. ".concat(valueOf) : new String("Could not fetch MRAID JS. "));
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public WebResourceResponse zzf(Context context, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractSpiCall.HEADER_USER_AGENT, zzv.zzcJ().zzh(context, str));
        hashMap.put("Cache-Control", "max-stale=3600");
        String str3 = (String) new zzpp(context).zzc(str2, hashMap).get(60, TimeUnit.SECONDS);
        if (str3 == null) {
            return null;
        }
        return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
    }
}
