package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzmc;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmh;
import com.google.android.gms.internal.zzov;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzmd extends zzpd implements zzme.zza {
    private final Context mContext;
    private final zzav zzGr;
    private zzmh zzKG;
    zzjj zzKq;
    zzmk zzPp;
    /* access modifiers changed from: private */
    public Runnable zzPq;
    /* access modifiers changed from: private */
    public final Object zzPr = new Object();
    private final zzmc.zza zzQQ;
    /* access modifiers changed from: private */
    public final zzmh.zza zzQR;
    zzpk zzQS;

    @zzmb
    static final class zza extends Exception {
        private final int zzPF;

        public zza(String str, int i) {
            super(str);
            this.zzPF = i;
        }

        public int getErrorCode() {
            return this.zzPF;
        }
    }

    public zzmd(Context context, zzmh.zza zza2, zzav zzav, zzmc.zza zza3) {
        this.zzQQ = zza3;
        this.mContext = context;
        this.zzQR = zza2;
        this.zzGr = zzav;
    }

    /* access modifiers changed from: private */
    public void zzd(int i, String str) {
        if (i == 3 || i == -1) {
            zzpe.zzbd(str);
        } else {
            zzpe.zzbe(str);
        }
        if (this.zzPp == null) {
            this.zzPp = new zzmk(i);
        } else {
            this.zzPp = new zzmk(i, this.zzPp.zzKe);
        }
        this.zzQQ.zza(new zzov.zza(this.zzKG != null ? this.zzKG : new zzmh(this.zzQR, null, -1), this.zzPp, this.zzKq, null, i, -1, this.zzPp.zzRO, null));
    }

    public void onStop() {
        synchronized (this.zzPr) {
            if (this.zzQS != null) {
                this.zzQS.cancel();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public zzpk zza(zzqa zzqa, zzqi<zzmh> zzqi) {
        return zzme.zza(this.mContext, zzqa, zzqi, this);
    }

    /* access modifiers changed from: protected */
    public zzec zzb(zzmh zzmh) {
        if (this.zzPp.zzzo) {
            for (zzec zzec : zzmh.zzvj.zzzm) {
                if (zzec.zzzo) {
                    return new zzec(zzec, zzmh.zzvj.zzzm);
                }
            }
        }
        if (this.zzPp.zzRN == null) {
            throw new zza("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzPp.zzRN.split("x");
        if (split.length != 2) {
            String valueOf = String.valueOf(this.zzPp.zzRN);
            throw new zza(valueOf.length() != 0 ? "Invalid ad size format from the ad response: ".concat(valueOf) : new String("Invalid ad size format from the ad response: "), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (zzec zzec2 : zzmh.zzvj.zzzm) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                int i = zzec2.width == -1 ? (int) (((float) zzec2.widthPixels) / f) : zzec2.width;
                int i2 = zzec2.height == -2 ? (int) (((float) zzec2.heightPixels) / f) : zzec2.height;
                if (parseInt == i && parseInt2 == i2 && !zzec2.zzzo) {
                    return new zzec(zzec2, zzmh.zzvj.zzzm);
                }
            }
            String valueOf2 = String.valueOf(this.zzPp.zzRN);
            throw new zza(valueOf2.length() != 0 ? "The ad size from the ad response was not one of the requested sizes: ".concat(valueOf2) : new String("The ad size from the ad response was not one of the requested sizes: "), 0);
        } catch (NumberFormatException e) {
            String valueOf3 = String.valueOf(this.zzPp.zzRN);
            throw new zza(valueOf3.length() != 0 ? "Invalid ad size number from the ad response: ".concat(valueOf3) : new String("Invalid ad size number from the ad response: "), 0);
        }
    }

    public void zzb(@NonNull zzmk zzmk) {
        JSONObject jSONObject;
        zzpe.zzbc("Received ad response.");
        this.zzPp = zzmk;
        long elapsedRealtime = zzv.zzcP().elapsedRealtime();
        synchronized (this.zzPr) {
            this.zzQS = null;
        }
        zzv.zzcN().zzd(this.mContext, this.zzPp.zzRB);
        try {
            if (this.zzPp.errorCode == -2 || this.zzPp.errorCode == -3) {
                zziX();
                zzec zzb = this.zzKG.zzvj.zzzm != null ? zzb(this.zzKG) : null;
                zzv.zzcN().zzE(this.zzPp.zzRU);
                zzv.zzcN().zzF(this.zzPp.zzSh);
                if (!TextUtils.isEmpty(this.zzPp.zzRS)) {
                    try {
                        jSONObject = new JSONObject(this.zzPp.zzRS);
                    } catch (Exception e) {
                        zzpe.zzb("Error parsing the JSON for Active View.", e);
                    }
                    this.zzQQ.zza(new zzov.zza(this.zzKG, this.zzPp, this.zzKq, zzb, -2, elapsedRealtime, this.zzPp.zzRO, jSONObject));
                    zzpi.zzWR.removeCallbacks(this.zzPq);
                    return;
                }
                jSONObject = null;
                this.zzQQ.zza(new zzov.zza(this.zzKG, this.zzPp, this.zzKq, zzb, -2, elapsedRealtime, this.zzPp.zzRO, jSONObject));
                zzpi.zzWR.removeCallbacks(this.zzPq);
                return;
            }
            throw new zza(new StringBuilder(66).append("There was a problem getting an ad response. ErrorCode: ").append(this.zzPp.errorCode).toString(), this.zzPp.errorCode);
        } catch (zza e2) {
            zzd(e2.getErrorCode(), e2.getMessage());
            zzpi.zzWR.removeCallbacks(this.zzPq);
        }
    }

    public void zzcm() {
        zzpe.zzbc("AdLoaderBackgroundTask started.");
        this.zzPq = new Runnable() {
            public void run() {
                synchronized (zzmd.this.zzPr) {
                    if (zzmd.this.zzQS != null) {
                        zzmd.this.onStop();
                        zzmd.this.zzd(2, "Timed out waiting for ad response.");
                    }
                }
            }
        };
        zzpi.zzWR.postDelayed(this.zzPq, zzfx.zzDd.get().longValue());
        final zzqj zzqj = new zzqj();
        long elapsedRealtime = zzv.zzcP().elapsedRealtime();
        zzph.zza((Runnable) new Runnable() {
            public void run() {
                synchronized (zzmd.this.zzPr) {
                    zzmd.this.zzQS = zzmd.this.zza(zzmd.this.zzQR.zzvf, zzqj);
                    if (zzmd.this.zzQS == null) {
                        zzmd.this.zzd(0, "Could not start the ad request service.");
                        zzpi.zzWR.removeCallbacks(zzmd.this.zzPq);
                    }
                }
            }
        });
        this.zzKG = new zzmh(this.zzQR, this.zzGr.zzW().zzb(this.mContext), elapsedRealtime);
        zzqj.zzg(this.zzKG);
    }

    /* access modifiers changed from: protected */
    public void zziX() {
        if (this.zzPp.errorCode != -3) {
            if (TextUtils.isEmpty(this.zzPp.body)) {
                throw new zza("No fill from ad server.", 3);
            }
            zzv.zzcN().zzc(this.mContext, this.zzPp.zzRl);
            if (this.zzPp.zzRK) {
                try {
                    this.zzKq = new zzjj(this.zzPp.body);
                    zzv.zzcN().zzG(this.zzKq.zzKc);
                } catch (JSONException e) {
                    zzpe.zzb("Could not parse mediation config.", e);
                    String valueOf = String.valueOf(this.zzPp.body);
                    throw new zza(valueOf.length() != 0 ? "Could not parse mediation config: ".concat(valueOf) : new String("Could not parse mediation config: "), 0);
                }
            } else {
                zzv.zzcN().zzG(this.zzPp.zzKc);
            }
            if (!TextUtils.isEmpty(this.zzPp.zzRC) && zzfx.zzEH.get().booleanValue()) {
                zzpe.zzbc("Received cookie from server. Setting webview cookie in CookieManager.");
                CookieManager zzL = zzv.zzcL().zzL(this.mContext);
                if (zzL != null) {
                    zzL.setCookie("googleads.g.doubleclick.net", this.zzPp.zzRC);
                }
            }
        }
    }
}
