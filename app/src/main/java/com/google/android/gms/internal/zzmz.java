package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zznj;
import com.google.android.gms.internal.zzqi;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public final class zzmz extends zzmq.zza {
    private static zzmz zzSS;
    private static final Object zztU = new Object();
    private final Context mContext;
    private final zzmy zzST;
    private final zzfq zzSU;
    private final zzja zzSV;

    zzmz(Context context, zzfq zzfq, zzmy zzmy) {
        this.mContext = context;
        this.zzST = zzmy;
        this.zzSU = zzfq;
        this.zzSV = new zzja(context.getApplicationContext() != null ? context.getApplicationContext() : context, zzqa.zzkK(), zzfq.zzfl(), new zzpn<zzix>(this) {
            /* renamed from: zza */
            public void zzd(zzix zzix) {
                zzix.zza("/log", zzhw.zzHl);
            }
        }, new zzja.zzb());
    }

    private static zzmk zza(Context context, zzja zzja, zzfq zzfq, zzmy zzmy, zzmh zzmh) {
        Bundle bundle;
        zzqf zzqf;
        zzpe.zzbc("Starting ad request from service using: AFMA_getAd");
        zzfx.initialize(context);
        zzqf<Bundle> zzii = zzmy.zzSQ.zzii();
        final zzgf zzgf = new zzgf(zzfx.zzBK.get().booleanValue(), "load_ad", zzmh.zzvj.zzzk);
        if (zzmh.versionCode > 10 && zzmh.zzRu != -1) {
            zzgf.zza(zzgf.zzc(zzmh.zzRu), "cts");
        }
        zzgd zzfw = zzgf.zzfw();
        final Bundle bundle2 = (zzmh.versionCode < 4 || zzmh.zzRk == null) ? null : zzmh.zzRk;
        if (!zzfx.zzCb.get().booleanValue() || zzmy.zzSI == null) {
            bundle = bundle2;
            zzqf = null;
        } else {
            if (bundle2 == null && zzfx.zzCc.get().booleanValue()) {
                zzpe.m2431v("contentInfo is not present, but we'll still launch the app index task");
                bundle2 = new Bundle();
            }
            if (bundle2 != null) {
                final zzmy zzmy2 = zzmy;
                final Context context2 = context;
                final zzmh zzmh2 = zzmh;
                bundle = bundle2;
                zzqf = zzph.zza(new Callable<Void>() {
                    /* renamed from: zzbl */
                    public Void call() {
                        String str = zzmh2.zzRe.packageName;
                        return null;
                    }
                });
            } else {
                bundle = bundle2;
                zzqf = null;
            }
        }
        zzqf zzqd = new zzqd(null);
        Bundle bundle3 = zzmh.zzRd.extras;
        zzqf zza = (!zzmh.zzRB || (bundle3 != null && bundle3.getString("_ad") != null)) ? zzqd : zzmy.zzSN.zza(zzmh.applicationInfo);
        zznf zzv = zzv.zzcS().zzv(context);
        if (zzv.zzUm == -1) {
            zzpe.zzbc("Device is offline.");
            return new zzmk(2);
        }
        String uuid = zzmh.versionCode >= 7 ? zzmh.zzRr : UUID.randomUUID().toString();
        final zznb zznb = new zznb(uuid, zzmh.applicationInfo.packageName);
        if (zzmh.zzRd.extras != null) {
            String string = zzmh.zzRd.extras.getString("_ad");
            if (string != null) {
                return zzna.zza(context, zzmh, string);
            }
        }
        List<String> zza2 = zzmy.zzSL.zza(zzmh);
        String zzg = zzmy.zzSR.zzg(zzmh);
        if (zzqf != null) {
            try {
                zzpe.m2431v("Waiting for app index fetching task.");
                zzqf.get(zzfx.zzCd.get().longValue(), TimeUnit.MILLISECONDS);
                zzpe.m2431v("App index fetching task completed.");
            } catch (InterruptedException | ExecutionException e) {
                zzpe.zzc("Failed to fetch app index signal", e);
            } catch (TimeoutException e2) {
                zzpe.zzbc("Timed out waiting for app index fetching task");
            }
        }
        String str = zzmh.zzRe.packageName;
        zzd(zzii);
        JSONObject zza3 = zzna.zza(context, new zzmx().zzf(zzmh).zza(zzv).zza((zznj.zza) null).zzc(zzc(zza)).zze(zzd(zzii)).zzaJ(zzg).zzk(zza2).zzf(bundle).zzaK(null).zzh(zzmy.zzSJ.zzi(context)));
        if (zza3 == null) {
            return new zzmk(0);
        }
        if (zzmh.versionCode < 7) {
            try {
                zza3.put("request_id", uuid);
            } catch (JSONException e3) {
            }
        }
        try {
            zza3.put("prefetch_mode", "url");
        } catch (JSONException e4) {
            zzpe.zzc("Failed putting prefetch parameters to ad request.", e4);
        }
        final String jSONObject = zza3.toString();
        zzgf.zza(zzfw, "arc");
        final zzgd zzfw2 = zzgf.zzfw();
        final zzja zzja2 = zzja;
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                zzja.zzc zzgv = zzja.this.zzgv();
                zznb.zzb(zzgv);
                zzgf.zza(zzfw2, "rwc");
                final zzgd zzfw = zzgf.zzfw();
                zzgv.zza(new zzqi.zzc<zzjb>() {
                    /* renamed from: zzb */
                    public void zzd(zzjb zzjb) {
                        zzgf.zza(zzfw, "jsf");
                        zzgf.zzfx();
                        zzjb.zza("/invalidRequest", zznb.zzTk);
                        zzjb.zza("/loadAdURL", zznb.zzTl);
                        zzjb.zza("/loadAd", zznb.zzTm);
                        try {
                            zzjb.zzi("AFMA_getAd", jSONObject);
                        } catch (Exception e) {
                            zzpe.zzb("Error requesting an ad url", e);
                        }
                    }
                }, new zzqi.zza(this) {
                    public void run() {
                    }
                });
            }
        });
        try {
            zzne zzne = zznb.zzjh().get(10, TimeUnit.SECONDS);
            if (zzne == null) {
                return new zzmk(0);
            }
            if (zzne.getErrorCode() != -2) {
                zzmk zzmk = new zzmk(zzne.getErrorCode());
                final zzmy zzmy3 = zzmy;
                final Context context3 = context;
                final zzmh zzmh3 = zzmh;
                zzpi.zzWR.post(new Runnable() {
                    public void run() {
                        zzmy.this.zzSM.zza(r1, zznb, r2.zzvf);
                    }
                });
                return zzmk;
            }
            if (zzgf.zzfA() != null) {
                zzgf.zza(zzgf.zzfA(), "rur");
            }
            zzmk zzmk2 = null;
            if (!TextUtils.isEmpty(zzne.zzjm())) {
                zzmk2 = zzna.zza(context, zzmh, zzne.zzjm());
            }
            if (zzmk2 == null && !TextUtils.isEmpty(zzne.getUrl())) {
                zzmk2 = zza(zzmh, context, zzmh.zzvf.zzaZ, zzne.getUrl(), null, zzne, zzgf, zzmy);
            }
            if (zzmk2 == null) {
                zzmk2 = new zzmk(0);
            }
            zzgf.zza(zzfw, "tts");
            zzmk2.zzRX = zzgf.zzfy();
            final zzmy zzmy4 = zzmy;
            final Context context4 = context;
            final zzmh zzmh4 = zzmh;
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zzmy.this.zzSM.zza(r1, zznb, r2.zzvf);
                }
            });
            return zzmk2;
        } catch (Exception e5) {
            return new zzmk(0);
        } finally {
            final zzmy zzmy5 = zzmy;
            final Context context5 = context;
            final zzmh zzmh5 = zzmh;
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zzmy.this.zzSM.zza(context5, zznb, zzmh5.zzvf);
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a4, code lost:
        r6 = r7.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r4 = new java.io.InputStreamReader(r2.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r5 = com.google.android.gms.ads.internal.zzv.zzcJ().zza(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        com.google.android.gms.common.util.zzo.zzb(r4);
        zza(r6, r12, r5, r9);
        r8.zzb(r6, r12, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c3, code lost:
        if (r19 == null) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c5, code lost:
        r19.zza(r3, "ufe");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d2, code lost:
        r3 = r8.zzj(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0110, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0111, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        com.google.android.gms.common.util.zzo.zzb(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0115, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        com.google.android.gms.internal.zzpe.zzbe(new java.lang.StringBuilder(46).append("Received error HTTP response code: ").append(r9).toString());
        r3 = new com.google.android.gms.internal.zzmk(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r2.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0194, code lost:
        r3 = th;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:63:0x0112=Splitter:B:63:0x0112, B:54:0x0107=Splitter:B:54:0x0107, B:83:0x015d=Splitter:B:83:0x015d} */
    public static zzmk zza(zzmh zzmh, Context context, String str, String str2, String str3, zzne zzne, zzgf zzgf, zzmy zzmy) {
        HttpURLConnection httpURLConnection;
        BufferedOutputStream bufferedOutputStream;
        zzgd zzfw = zzgf != null ? zzgf.zzfw() : null;
        try {
            zznc zznc = new zznc(zzmh, zzne.zzjj());
            String valueOf = String.valueOf(str2);
            zzpe.zzbc(valueOf.length() != 0 ? "AdRequestServiceImpl: Sending request: ".concat(valueOf) : new String("AdRequestServiceImpl: Sending request: "));
            URL url = new URL(str2);
            long elapsedRealtime = zzv.zzcP().elapsedRealtime();
            int i = 0;
            URL url2 = url;
            while (true) {
                httpURLConnection = (HttpURLConnection) url2.openConnection();
                zzv.zzcJ().zza(context, str, false, httpURLConnection);
                if (!TextUtils.isEmpty(str3) && zzne.zzjl()) {
                    httpURLConnection.addRequestProperty("x-afma-drt-cookie", str3);
                }
                String str4 = zzmh.zzRC;
                if (!TextUtils.isEmpty(str4)) {
                    zzpe.zzbc("Sending webview cookie in ad request header.");
                    httpURLConnection.addRequestProperty("Cookie", str4);
                }
                if (zzne != null && !TextUtils.isEmpty(zzne.zzjk())) {
                    httpURLConnection.setDoOutput(true);
                    byte[] bytes = zzne.zzjk().getBytes();
                    httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                    try {
                        bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                        try {
                            bufferedOutputStream.write(bytes);
                            zzo.zzb(bufferedOutputStream);
                        } catch (Throwable th) {
                            th = th;
                            zzo.zzb(bufferedOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = null;
                        zzo.zzb(bufferedOutputStream);
                        throw th;
                    }
                }
                int responseCode = httpURLConnection.getResponseCode();
                Map headerFields = httpURLConnection.getHeaderFields();
                if (responseCode >= 200 && responseCode < 300) {
                    break;
                }
                zza(url2.toString(), headerFields, null, responseCode);
                if (responseCode < 300 || responseCode >= 400) {
                    break;
                }
                String headerField = httpURLConnection.getHeaderField("Location");
                if (TextUtils.isEmpty(headerField)) {
                    zzpe.zzbe("No location header to follow redirect.");
                    zzmk zzmk = new zzmk(0);
                    httpURLConnection.disconnect();
                    return zzmk;
                }
                URL url3 = new URL(headerField);
                int i2 = i + 1;
                if (i2 > 5) {
                    zzpe.zzbe("Too many redirects.");
                    zzmk zzmk2 = new zzmk(0);
                    httpURLConnection.disconnect();
                    return zzmk2;
                }
                zznc.zzk(headerFields);
                httpURLConnection.disconnect();
                if (zzmy != null) {
                }
                i = i2;
                url2 = url3;
            }
        } catch (IOException e) {
            String valueOf2 = String.valueOf(e.getMessage());
            zzpe.zzbe(valueOf2.length() != 0 ? "Error while connecting to ad server: ".concat(valueOf2) : new String("Error while connecting to ad server: "));
            return new zzmk(2);
        } catch (Throwable th3) {
            httpURLConnection.disconnect();
            throw th3;
        }
    }

    public static zzmz zza(Context context, zzfq zzfq, zzmy zzmy) {
        zzmz zzmz;
        synchronized (zztU) {
            if (zzSS == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzSS = new zzmz(context, zzfq, zzmy);
            }
            zzmz = zzSS;
        }
        return zzmz;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzpe.zzai(2)) {
            zzpe.m2431v(new StringBuilder(String.valueOf(str).length() + 39).append("Http Response: {\n  URL:\n    ").append(str).append("\n  Headers:").toString());
            if (map != null) {
                for (String next : map.keySet()) {
                    zzpe.m2431v(new StringBuilder(String.valueOf(next).length() + 5).append("    ").append(next).append(":").toString());
                    for (String valueOf : map.get(next)) {
                        String valueOf2 = String.valueOf(valueOf);
                        zzpe.m2431v(valueOf2.length() != 0 ? "      ".concat(valueOf2) : new String("      "));
                    }
                }
            }
            zzpe.m2431v("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    zzpe.m2431v(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                zzpe.m2431v("    null");
            }
            zzpe.m2431v(new StringBuilder(34).append("  Response Code:\n    ").append(i).append("\n}").toString());
        }
    }

    private static Location zzc(zzqf<Location> zzqf) {
        try {
            return (Location) zzqf.get(zzfx.zzEl.get().longValue(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            zzpe.zzc("Exception caught while getting location", e);
            return null;
        }
    }

    private static Bundle zzd(zzqf<Bundle> zzqf) {
        Bundle bundle = new Bundle();
        try {
            return (Bundle) zzqf.get(zzfx.zzED.get().longValue(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            zzpe.zzc("Exception caught while getting parental controls.", e);
            return bundle;
        }
    }

    public void zza(final zzmh zzmh, final zzmr zzmr) {
        zzv.zzcN().zzc(this.mContext, zzmh.zzvf);
        zzph.zza((Runnable) new Runnable() {
            public void run() {
                zzmk zzmk;
                try {
                    zzmk = zzmz.this.zzd(zzmh);
                } catch (Exception e) {
                    zzv.zzcN().zza((Throwable) e, "AdRequestServiceImpl.loadAdAsync");
                    zzpe.zzc("Could not fetch ad response due to an Exception.", e);
                    zzmk = null;
                }
                if (zzmk == null) {
                    zzmk = new zzmk(0);
                }
                try {
                    zzmr.zzb(zzmk);
                } catch (RemoteException e2) {
                    zzpe.zzc("Fail to forward ad response.", e2);
                }
            }
        });
    }

    public zzmk zzd(zzmh zzmh) {
        return zza(this.mContext, this.zzSV, this.zzSU, this.zzST, zzmh);
    }
}
