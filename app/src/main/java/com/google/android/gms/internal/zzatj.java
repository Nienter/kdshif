package com.google.android.gms.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class zzatj extends zzats {

    @WorkerThread
    interface zza {
        void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map);
    }

    @WorkerThread
    private static class zzb implements Runnable {
        private final int zzJh;
        private final String zzQL;
        private final Throwable zzYf;
        private final zza zzbrV;
        private final byte[] zzbrW;
        private final Map<String, List<String>> zzbrX;

        private zzb(String str, zza zza, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            zzac.zzw(zza);
            this.zzbrV = zza;
            this.zzJh = i;
            this.zzYf = th;
            this.zzbrW = bArr;
            this.zzQL = str;
            this.zzbrX = map;
        }

        public void run() {
            this.zzbrV.zza(this.zzQL, this.zzJh, this.zzYf, this.zzbrW, this.zzbrX);
        }
    }

    @WorkerThread
    private class zzc implements Runnable {
        private final URL zzHD;
        private final String zzQL;
        private final byte[] zzbrY;
        private final zza zzbrZ;
        private final Map<String, String> zzbsa;

        public zzc(String str, URL url, byte[] bArr, Map<String, String> map, zza zza) {
            zzac.zzdv(str);
            zzac.zzw(url);
            zzac.zzw(zza);
            this.zzHD = url;
            this.zzbrY = bArr;
            this.zzbrZ = zza;
            this.zzQL = str;
            this.zzbsa = map;
        }

        /* JADX WARNING: Removed duplicated region for block: B:36:0x00e3 A[SYNTHETIC, Splitter:B:36:0x00e3] */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00e8  */
        public void run() {
            Map map;
            int i;
            OutputStream outputStream;
            HttpURLConnection httpURLConnection;
            Throwable th;
            Map map2;
            HttpURLConnection httpURLConnection2;
            zzatj.this.zzJf();
            int i2 = 0;
            try {
                httpURLConnection2 = zzatj.this.zzc(this.zzHD);
                try {
                    if (this.zzbsa != null) {
                        for (Map.Entry next : this.zzbsa.entrySet()) {
                            httpURLConnection2.addRequestProperty((String) next.getKey(), (String) next.getValue());
                        }
                    }
                    if (this.zzbrY != null) {
                        byte[] zzk = zzatj.this.zzJp().zzk(this.zzbrY);
                        zzatj.this.zzJt().zzLg().zzj("Uploading data. size", Integer.valueOf(zzk.length));
                        httpURLConnection2.setDoOutput(true);
                        httpURLConnection2.addRequestProperty("Content-Encoding", "gzip");
                        httpURLConnection2.setFixedLengthStreamingMode(zzk.length);
                        httpURLConnection2.connect();
                        outputStream = httpURLConnection2.getOutputStream();
                        try {
                            outputStream.write(zzk);
                            outputStream.close();
                        } catch (IOException e) {
                            e = e;
                            map = null;
                            i = 0;
                            httpURLConnection = httpURLConnection2;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e2) {
                                    zzatj.this.zzJt().zzLa().zze("Error closing HTTP compressed POST connection output stream. appId", zzati.zzfI(this.zzQL), e2);
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            zzatj.this.zzJs().zzm(new zzb(this.zzQL, this.zzbrZ, i, e, null, map));
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            map2 = null;
                            if (outputStream != null) {
                            }
                            if (httpURLConnection2 != null) {
                            }
                            zzatj.this.zzJs().zzm(new zzb(this.zzQL, this.zzbrZ, i2, null, null, map2));
                            throw th;
                        }
                    }
                    i2 = httpURLConnection2.getResponseCode();
                    map2 = httpURLConnection2.getHeaderFields();
                } catch (IOException e3) {
                    e = e3;
                    map = null;
                    i = i2;
                    outputStream = null;
                    httpURLConnection = httpURLConnection2;
                } catch (Throwable th3) {
                    th = th3;
                    map2 = null;
                    outputStream = null;
                    if (outputStream != null) {
                    }
                    if (httpURLConnection2 != null) {
                    }
                    zzatj.this.zzJs().zzm(new zzb(this.zzQL, this.zzbrZ, i2, null, null, map2));
                    throw th;
                }
                try {
                    byte[] zza = zzatj.this.zzc(httpURLConnection2);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    zzatj.this.zzJs().zzm(new zzb(this.zzQL, this.zzbrZ, i2, null, zza, map2));
                } catch (IOException e4) {
                    e = e4;
                    map = map2;
                    i = i2;
                    outputStream = null;
                    httpURLConnection = httpURLConnection2;
                } catch (Throwable th4) {
                    th = th4;
                    outputStream = null;
                    if (outputStream != null) {
                    }
                    if (httpURLConnection2 != null) {
                    }
                    zzatj.this.zzJs().zzm(new zzb(this.zzQL, this.zzbrZ, i2, null, null, map2));
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                map = null;
                i = 0;
                outputStream = null;
                httpURLConnection = null;
            } catch (Throwable th5) {
                th = th5;
                map2 = null;
                httpURLConnection2 = null;
                outputStream = null;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e6) {
                        zzatj.this.zzJt().zzLa().zze("Error closing HTTP compressed POST connection output stream. appId", zzati.zzfI(this.zzQL), e6);
                    }
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                zzatj.this.zzJs().zzm(new zzb(this.zzQL, this.zzbrZ, i2, null, null, map2));
                throw th;
            }
        }
    }

    public zzatj(zzatp zzatp) {
        super(zzatp);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public byte[] zzc(HttpURLConnection httpURLConnection) {
        InputStream inputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJd() {
        super.zzJd();
    }

    public /* bridge */ /* synthetic */ void zzJe() {
        super.zzJe();
    }

    public /* bridge */ /* synthetic */ void zzJf() {
        super.zzJf();
    }

    public /* bridge */ /* synthetic */ zzaso zzJg() {
        return super.zzJg();
    }

    public /* bridge */ /* synthetic */ zzass zzJh() {
        return super.zzJh();
    }

    public /* bridge */ /* synthetic */ zzatu zzJi() {
        return super.zzJi();
    }

    public /* bridge */ /* synthetic */ zzatf zzJj() {
        return super.zzJj();
    }

    public /* bridge */ /* synthetic */ zzasw zzJk() {
        return super.zzJk();
    }

    public /* bridge */ /* synthetic */ zzatw zzJl() {
        return super.zzJl();
    }

    public /* bridge */ /* synthetic */ zzatv zzJm() {
        return super.zzJm();
    }

    public /* bridge */ /* synthetic */ zzatg zzJn() {
        return super.zzJn();
    }

    public /* bridge */ /* synthetic */ zzasu zzJo() {
        return super.zzJo();
    }

    public /* bridge */ /* synthetic */ zzaue zzJp() {
        return super.zzJp();
    }

    public /* bridge */ /* synthetic */ zzatn zzJq() {
        return super.zzJq();
    }

    public /* bridge */ /* synthetic */ zzaty zzJr() {
        return super.zzJr();
    }

    public /* bridge */ /* synthetic */ zzato zzJs() {
        return super.zzJs();
    }

    public /* bridge */ /* synthetic */ zzati zzJt() {
        return super.zzJt();
    }

    public /* bridge */ /* synthetic */ zzatl zzJu() {
        return super.zzJu();
    }

    public /* bridge */ /* synthetic */ zzast zzJv() {
        return super.zzJv();
    }

    @WorkerThread
    public void zza(String str, URL url, Map<String, String> map, zza zza2) {
        zzmq();
        zznA();
        zzac.zzw(url);
        zzac.zzw(zza2);
        zzJs().zzn(new zzc(str, url, null, map, zza2));
    }

    @WorkerThread
    public void zza(String str, URL url, byte[] bArr, Map<String, String> map, zza zza2) {
        zzmq();
        zznA();
        zzac.zzw(url);
        zzac.zzw(bArr);
        zzac.zzw(zza2);
        zzJs().zzn(new zzc(str, url, bArr, map, zza2));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public HttpURLConnection zzc(URL url) {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain HTTP connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        zzJv().zzKh();
        httpURLConnection.setConnectTimeout(60000);
        zzJv().zzKi();
        httpURLConnection.setReadTimeout(61000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }

    public boolean zzpA() {
        NetworkInfo networkInfo;
        zznA();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            networkInfo = null;
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
