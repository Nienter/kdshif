package com.google.android.gms.internal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class zzz implements zzy {
    private final zza zzaD;
    private final SSLSocketFactory zzaE;

    public interface zza {
        String zzh(String str);
    }

    public zzz() {
        this(null);
    }

    public zzz(zza zza2) {
        this(zza2, null);
    }

    public zzz(zza zza2, SSLSocketFactory sSLSocketFactory) {
        this.zzaD = zza2;
        this.zzaE = sSLSocketFactory;
    }

    private HttpURLConnection zza(URL url, zzk<?> zzk) {
        HttpURLConnection zza2 = zza(url);
        int zzp = zzk.zzp();
        zza2.setConnectTimeout(zzp);
        zza2.setReadTimeout(zzp);
        zza2.setUseCaches(false);
        zza2.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.zzaE != null) {
            ((HttpsURLConnection) zza2).setSSLSocketFactory(this.zzaE);
        }
        return zza2;
    }

    private static HttpEntity zza(HttpURLConnection httpURLConnection) {
        InputStream errorStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            errorStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            errorStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(errorStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    static void zza(HttpURLConnection httpURLConnection, zzk<?> zzk) {
        switch (zzk.getMethod()) {
            case -1:
                byte[] zzj = zzk.zzj();
                if (zzj != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", zzk.zzi());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(zzj);
                    dataOutputStream.close();
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                return;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                zzb(httpURLConnection, zzk);
                return;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                zzb(httpURLConnection, zzk);
                return;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                return;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                return;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                zzb(httpURLConnection, zzk);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void zzb(HttpURLConnection httpURLConnection, zzk<?> zzk) {
        byte[] zzm = zzk.zzm();
        if (zzm != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", zzk.zzl());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzm);
            dataOutputStream.close();
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection zza(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    public HttpResponse zza(zzk<?> zzk, Map<String, String> map) {
        String str;
        String url = zzk.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(zzk.getHeaders());
        hashMap.putAll(map);
        if (this.zzaD != null) {
            str = this.zzaD.zzh(url);
            if (str == null) {
                String valueOf = String.valueOf(url);
                throw new IOException(valueOf.length() != 0 ? "URL blocked by rewriter: ".concat(valueOf) : new String("URL blocked by rewriter: "));
            }
        } else {
            str = url;
        }
        HttpURLConnection zza2 = zza(new URL(str), zzk);
        for (String str2 : hashMap.keySet()) {
            zza2.addRequestProperty(str2, (String) hashMap.get(str2));
        }
        zza(zza2, zzk);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (zza2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, zza2.getResponseCode(), zza2.getResponseMessage()));
        basicHttpResponse.setEntity(zza(zza2));
        for (Map.Entry entry : zza2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }
}
