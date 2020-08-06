package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzmc;
import com.google.android.gms.internal.zzmh;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzqi;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzmu extends zzpd {
    /* access modifiers changed from: private */
    public static zzja zzPU = null;
    static final long zzSs = TimeUnit.SECONDS.toMillis(10);
    static boolean zzSt = false;
    private static zzhy zzSu = null;
    /* access modifiers changed from: private */
    public static zzic zzSv = null;
    private static zzhx zzSw = null;
    private static final Object zztU = new Object();
    private final Context mContext;
    private final Object zzPr = new Object();
    /* access modifiers changed from: private */
    public final zzmc.zza zzQQ;
    private final zzmh.zza zzQR;
    /* access modifiers changed from: private */
    public zzja.zzc zzSx;

    public static class zza implements zzpn<zzix> {
        /* renamed from: zza */
        public void zzd(zzix zzix) {
            zzmu.zzc(zzix);
        }
    }

    public static class zzb implements zzpn<zzix> {
        /* renamed from: zza */
        public void zzd(zzix zzix) {
            zzmu.zzb(zzix);
        }
    }

    public static class zzc implements zzhx {
        public void zza(zzqp zzqp, Map<String, String> map) {
            String str = map.get("request_id");
            String valueOf = String.valueOf(map.get("errors"));
            zzpe.zzbe(valueOf.length() != 0 ? "Invalid request: ".concat(valueOf) : new String("Invalid request: "));
            zzmu.zzSv.zzac(str);
        }
    }

    public zzmu(Context context, zzmh.zza zza2, zzmc.zza zza3) {
        super(true);
        this.zzQQ = zza3;
        this.mContext = context;
        this.zzQR = zza2;
        synchronized (zztU) {
            if (!zzSt) {
                zzSv = new zzic();
                zzSu = new zzhy(context.getApplicationContext(), zza2.zzvf);
                zzSw = new zzc();
                zzPU = new zzja(this.mContext.getApplicationContext(), this.zzQR.zzvf, zzfx.zzAR.get(), new zzb(), new zza());
                zzSt = true;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.google.android.gms.ads.identifier.AdvertisingIdClient$Info} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.google.android.gms.ads.identifier.AdvertisingIdClient$Info} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: com.google.android.gms.ads.identifier.AdvertisingIdClient$Info} */
    /* JADX WARNING: Multi-variable type inference failed */
    private JSONObject zza(zzmh zzmh, String str) {
        AdvertisingIdClient.Info info;
        JSONObject jSONObject = null;
        Bundle bundle = zzmh.zzRd.extras.getBundle("sdk_less_server_data");
        if (bundle == null) {
            return jSONObject;
        }
        JSONObject zza2 = zzna.zza(this.mContext, new zzmx().zzf(zzmh).zza(zzv.zzcS().zzv(this.mContext)));
        if (zza2 == null) {
            return jSONObject;
        }
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            zzpe.zzc("Cannot get advertising id info", e);
            info = jSONObject;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request_id", str);
        hashMap.put("request_param", zza2);
        hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
        if (info != 0) {
            hashMap.put("adid", info.getId());
            hashMap.put("lat", Integer.valueOf(info.isLimitAdTrackingEnabled() ? 1 : 0));
        }
        try {
            return zzv.zzcJ().zzP(hashMap);
        } catch (JSONException e2) {
            return jSONObject;
        }
    }

    protected static void zzb(zzix zzix) {
        zzix.zza("/loadAd", (zzhx) zzSv);
        zzix.zza("/fetchHttpRequest", (zzhx) zzSu);
        zzix.zza("/invalidRequest", zzSw);
    }

    protected static void zzc(zzix zzix) {
        zzix.zzb("/loadAd", (zzhx) zzSv);
        zzix.zzb("/fetchHttpRequest", (zzhx) zzSu);
        zzix.zzb("/invalidRequest", zzSw);
    }

    private zzmk zze(zzmh zzmh) {
        final String zzkk = zzv.zzcJ().zzkk();
        final JSONObject zza2 = zza(zzmh, zzkk);
        if (zza2 == null) {
            return new zzmk(0);
        }
        long elapsedRealtime = zzv.zzcP().elapsedRealtime();
        Future<JSONObject> zzab = zzSv.zzab(zzkk);
        zzpx.zzXU.post(new Runnable() {
            public void run() {
                zzja.zzc unused = zzmu.this.zzSx = zzmu.zzPU.zzgv();
                zzmu.this.zzSx.zza(new zzqi.zzc<zzjb>() {
                    /* renamed from: zzb */
                    public void zzd(zzjb zzjb) {
                        try {
                            zzjb.zza("AFMA_getAdapterLessMediationAd", zza2);
                        } catch (Exception e) {
                            zzpe.zzb("Error requesting an ad url", e);
                            zzmu.zzSv.zzac(zzkk);
                        }
                    }
                }, new zzqi.zza() {
                    public void run() {
                        zzmu.zzSv.zzac(zzkk);
                    }
                });
            }
        });
        try {
            JSONObject jSONObject = zzab.get(zzSs - (zzv.zzcP().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzmk(-1);
            }
            zzmk zza3 = zzna.zza(this.mContext, zzmh, jSONObject.toString());
            return (zza3.errorCode == -3 || !TextUtils.isEmpty(zza3.body)) ? zza3 : new zzmk(3);
        } catch (InterruptedException | CancellationException e) {
            return new zzmk(-1);
        } catch (TimeoutException e2) {
            return new zzmk(2);
        } catch (ExecutionException e3) {
            return new zzmk(0);
        }
    }

    public void onStop() {
        synchronized (this.zzPr) {
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    if (zzmu.this.zzSx != null) {
                        zzmu.this.zzSx.release();
                        zzja.zzc unused = zzmu.this.zzSx = null;
                    }
                }
            });
        }
    }

    public void zzcm() {
        zzpe.zzbc("SdkLessAdLoaderBackgroundTask started.");
        zzmh zzmh = new zzmh(this.zzQR, null, -1);
        zzmk zze = zze(zzmh);
        final zzov.zza zza2 = new zzov.zza(zzmh, zze, null, null, zze.errorCode, zzv.zzcP().elapsedRealtime(), zze.zzRO, null);
        zzpx.zzXU.post(new Runnable() {
            public void run() {
                zzmu.this.zzQQ.zza(zza2);
                if (zzmu.this.zzSx != null) {
                    zzmu.this.zzSx.release();
                    zzja.zzc unused = zzmu.this.zzSx = null;
                }
            }
        });
    }
}
