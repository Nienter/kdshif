package com.google.android.gms.internal;

import android.os.SystemClock;
import com.google.android.gms.internal.zzb;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

public class zzt implements zzf {
    protected static final boolean DEBUG = zzs.DEBUG;
    private static int zzam = 3000;
    private static int zzan = 4096;
    protected final zzy zzao;
    protected final zzu zzap;

    public zzt(zzy zzy) {
        this(zzy, new zzu(zzan));
    }

    public zzt(zzy zzy, zzu zzu) {
        this.zzao = zzy;
        this.zzap = zzu;
    }

    protected static Map<String, String> zza(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }

    private void zza(long j, zzk<?> zzk, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > ((long) zzam)) {
            Object[] objArr = new Object[5];
            objArr[0] = zzk;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(zzk.zzq().zzd());
            zzs.zzb("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void zza(String str, zzk<?> zzk, zzr zzr) {
        zzo zzq = zzk.zzq();
        int zzp = zzk.zzp();
        try {
            zzq.zza(zzr);
            zzk.zzc(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(zzp)}));
        } catch (zzr e) {
            zzk.zzc(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(zzp)}));
            throw e;
        }
    }

    private void zza(Map<String, String> map, zzb.zza zza) {
        if (zza != null) {
            if (zza.zza != null) {
                map.put("If-None-Match", zza.zza);
            }
            if (zza.zzc > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(zza.zzc)));
            }
        }
    }

    private byte[] zza(HttpEntity httpEntity) {
        zzaa zzaa = new zzaa(this.zzap, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new zzp();
            }
            bArr = this.zzap.zzb(1024);
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                zzaa.write(bArr, 0, read);
            }
            byte[] byteArray = zzaa.toByteArray();
            try {
            } catch (IOException e) {
                zzs.zza("Error occured when calling consumingContent", new Object[0]);
            }
            return byteArray;
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException e2) {
                zzs.zza("Error occured when calling consumingContent", new Object[0]);
            }
            this.zzap.zza(bArr);
            zzaa.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0091, code lost:
        zza("socket", r19, new com.google.android.gms.internal.zzq());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b2, code lost:
        zza("connection", r19, new com.google.android.gms.internal.zzq());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c0, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c1, code lost:
        r3 = r2;
        r2 = java.lang.String.valueOf(r19.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d2, code lost:
        if (r2.length() != 0) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d4, code lost:
        r2 = "Bad URL ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00db, code lost:
        throw new java.lang.RuntimeException(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00dc, code lost:
        r2 = new java.lang.String("Bad URL ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e2, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e3, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e6, code lost:
        r4 = r3.getStatusLine().getStatusCode();
        com.google.android.gms.internal.zzs.zzc("Unexpected response code %d for %s", java.lang.Integer.valueOf(r4), r19.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0104, code lost:
        if (r5 != null) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0106, code lost:
        r3 = new com.google.android.gms.internal.zzi(r4, r5, r6, false, android.os.SystemClock.elapsedRealtime() - r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0114, code lost:
        if (r4 == 401) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x011a, code lost:
        zza("auth", r19, new com.google.android.gms.internal.zza(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x012d, code lost:
        throw new com.google.android.gms.internal.zzj(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0133, code lost:
        throw new com.google.android.gms.internal.zzp(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x013a, code lost:
        throw new com.google.android.gms.internal.zzh((com.google.android.gms.internal.zzi) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x013f, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0140, code lost:
        r5 = r11;
        r3 = r15;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0090 A[ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), Splitter:B:2:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b1 A[ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:2:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c0 A[ExcHandler: MalformedURLException (r2v9 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0128 A[SYNTHETIC] */
    public zzi zza(zzk<?> zzk) {
        HttpResponse zza;
        byte[] bArr;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            HttpResponse httpResponse = null;
            Map<String, String> emptyMap = Collections.emptyMap();
            try {
                HashMap hashMap = new HashMap();
                zza(hashMap, zzk.zzh());
                zza = this.zzao.zza(zzk, hashMap);
                StatusLine statusLine = zza.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                emptyMap = zza(zza.getAllHeaders());
                if (statusCode == 304) {
                    zzb.zza zzh = zzk.zzh();
                    if (zzh == null) {
                        return new zzi(304, null, emptyMap, true, SystemClock.elapsedRealtime() - elapsedRealtime);
                    }
                    zzh.zzf.putAll(emptyMap);
                    return new zzi(304, zzh.data, zzh.zzf, true, SystemClock.elapsedRealtime() - elapsedRealtime);
                }
                if (zza.getEntity() != null) {
                    bArr = zza(zza.getEntity());
                } else {
                    bArr = new byte[0];
                }
                zza(SystemClock.elapsedRealtime() - elapsedRealtime, zzk, bArr, statusLine);
                if (statusCode >= 200 && statusCode <= 299) {
                    return new zzi(statusCode, bArr, emptyMap, false, SystemClock.elapsedRealtime() - elapsedRealtime);
                }
            } catch (SocketTimeoutException e) {
            } catch (ConnectTimeoutException e2) {
            } catch (MalformedURLException e3) {
            } catch (IOException e4) {
                e = e4;
                byte[] bArr2 = null;
                httpResponse = zza;
                if (httpResponse != null) {
                }
            }
        }
        throw new IOException();
    }
}
