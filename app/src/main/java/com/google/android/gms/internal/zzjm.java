package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzjn;
import com.google.android.gms.internal.zzjv;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzjm implements zzjn.zza {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final String zzKo;
    private final long zzKp;
    private final zzjj zzKq;
    private final zzji zzKr;
    private final boolean zzKs;
    /* access modifiers changed from: private */
    public zzjt zzKt;
    /* access modifiers changed from: private */
    public int zzKu = -2;
    private zzjv zzKv;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();
    private final zzjs zzsD;
    private final zzgw zztn;
    private final List<String> zzto;
    private final zzqa zztr;
    private zzdy zzug;
    private final zzec zzum;
    private final boolean zzvW;

    public zzjm(Context context, String str, zzjs zzjs, zzjj zzjj, zzji zzji, zzdy zzdy, zzec zzec, zzqa zzqa, boolean z, boolean z2, zzgw zzgw, List<String> list) {
        this.mContext = context;
        this.zzsD = zzjs;
        this.zzKr = zzji;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.zzKo = zzgC();
        } else {
            this.zzKo = str;
        }
        this.zzKq = zzjj;
        this.zzKp = zzjj.zzJX != -1 ? zzjj.zzJX : 10000;
        this.zzug = zzdy;
        this.zzum = zzec;
        this.zztr = zzqa;
        this.zzvW = z;
        this.zzKs = z2;
        this.zztn = zzgw;
        this.zzto = list;
    }

    /* access modifiers changed from: private */
    public boolean zzE(int i) {
        try {
            Bundle zzgM = this.zzvW ? this.zzKt.zzgM() : this.zzum.zzzl ? this.zzKt.getInterstitialAdapterInfo() : this.zzKt.zzgL();
            if (zzgM == null) {
                return false;
            }
            return (zzgM.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException e) {
            zzpe.zzbe("Could not get adapter info. Returning false");
            return false;
        }
    }

    private static zzjv zzF(final int i) {
        return new zzjv.zza() {
            public int zzgH() {
                return i;
            }
        };
    }

    private long zza(long j, long j2, long j3, long j4) {
        while (this.zzKu == -2) {
            zzb(j, j2, j3, j4);
        }
        return zzv.zzcP().elapsedRealtime() - j;
    }

    /* access modifiers changed from: private */
    public void zza(zzjl zzjl) {
        String zzao = zzao(this.zzKr.zzJO);
        try {
            if (this.zztr.zzYc < 4100000) {
                if (this.zzum.zzzl) {
                    this.zzKt.zza(zze.zzA(this.mContext), this.zzug, zzao, zzjl);
                } else {
                    this.zzKt.zza(zze.zzA(this.mContext), this.zzum, this.zzug, zzao, (zzju) zzjl);
                }
            } else if (this.zzvW) {
                this.zzKt.zza(zze.zzA(this.mContext), this.zzug, zzao, this.zzKr.zzJG, zzjl, this.zztn, this.zzto);
            } else if (this.zzum.zzzl) {
                this.zzKt.zza(zze.zzA(this.mContext), this.zzug, zzao, this.zzKr.zzJG, (zzju) zzjl);
            } else if (!this.zzKs) {
                this.zzKt.zza(zze.zzA(this.mContext), this.zzum, this.zzug, zzao, this.zzKr.zzJG, zzjl);
            } else if (this.zzKr.zzJR != null) {
                this.zzKt.zza(zze.zzA(this.mContext), this.zzug, zzao, this.zzKr.zzJG, zzjl, new zzgw(zzap(this.zzKr.zzJV)), this.zzKr.zzJU);
            } else {
                this.zzKt.zza(zze.zzA(this.mContext), this.zzum, this.zzug, zzao, this.zzKr.zzJG, zzjl);
            }
        } catch (RemoteException e) {
            zzpe.zzc("Could not request ad from mediation adapter.", e);
            zzD(5);
        }
    }

    private String zzao(String str) {
        if (str == null || !zzgF() || zzE(2)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.remove("cpm_floor_cents");
            return jSONObject.toString();
        } catch (JSONException e) {
            zzpe.zzbe("Could not remove field. Returning the original value");
            return str;
        }
    }

    private static NativeAdOptions zzap(String str) {
        NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            builder.setImageOrientation(zzaq(jSONObject.optString("native_image_orientation", "any")));
        } catch (JSONException e) {
            zzpe.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private static int zzaq(String str) {
        if ("landscape".equals(str)) {
            return 2;
        }
        return "portrait".equals(str) ? 1 : 0;
    }

    private void zzb(long j, long j2, long j3, long j4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j5 = j2 - (elapsedRealtime - j);
        long j6 = j4 - (elapsedRealtime - j3);
        if (j5 <= 0 || j6 <= 0) {
            zzpe.zzbd("Timed out waiting for adapter.");
            this.zzKu = 3;
            return;
        }
        try {
            this.zzrN.wait(Math.min(j5, j6));
        } catch (InterruptedException e) {
            this.zzKu = -1;
        }
    }

    private String zzgC() {
        try {
            if (!TextUtils.isEmpty(this.zzKr.zzJK)) {
                return this.zzsD.zzas(this.zzKr.zzJK) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException e) {
            zzpe.zzbe("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private zzjv zzgD() {
        if (this.zzKu != 0 || !zzgF()) {
            return null;
        }
        try {
            if (!(!zzE(4) || this.zzKv == null || this.zzKv.zzgH() == 0)) {
                return this.zzKv;
            }
        } catch (RemoteException e) {
            zzpe.zzbe("Could not get cpm value from MediationResponseMetadata");
        }
        return zzF(zzgG());
    }

    /* access modifiers changed from: private */
    public zzjt zzgE() {
        String valueOf = String.valueOf(this.zzKo);
        zzpe.zzbd(valueOf.length() != 0 ? "Instantiating mediation adapter: ".concat(valueOf) : new String("Instantiating mediation adapter: "));
        if (!this.zzvW) {
            if (zzfx.zzDb.get().booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzKo)) {
                return zza((MediationAdapter) new AdMobAdapter());
            }
            if (zzfx.zzDc.get().booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzKo)) {
                return zza((MediationAdapter) new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzKo)) {
                return new zzjz(new zzkh());
            }
        }
        try {
            return this.zzsD.zzar(this.zzKo);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            String valueOf2 = String.valueOf(this.zzKo);
            zzpe.zza(valueOf2.length() != 0 ? "Could not instantiate mediation adapter: ".concat(valueOf2) : new String("Could not instantiate mediation adapter: "), remoteException);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean zzgF() {
        return this.zzKq.zzKh != -1;
    }

    private int zzgG() {
        if (this.zzKr.zzJO == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzKr.zzJO);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzKo)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzE(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException e) {
            zzpe.zzbe("Could not convert to json. Returning 0");
            return 0;
        }
    }

    public void cancel() {
        synchronized (this.zzrN) {
            try {
                if (this.zzKt != null) {
                    this.zzKt.destroy();
                }
            } catch (RemoteException e) {
                zzpe.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzKu = -1;
            this.zzrN.notify();
        }
    }

    public void zzD(int i) {
        synchronized (this.zzrN) {
            this.zzKu = i;
            this.zzrN.notify();
        }
    }

    public zzjn zza(long j, long j2) {
        zzjn zzjn;
        synchronized (this.zzrN) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            final zzjl zzjl = new zzjl();
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    synchronized (zzjm.this.zzrN) {
                        if (zzjm.this.zzKu == -2) {
                            zzjt unused = zzjm.this.zzKt = zzjm.this.zzgE();
                            if (zzjm.this.zzKt == null) {
                                zzjm.this.zzD(4);
                            } else if (!zzjm.this.zzgF() || zzjm.this.zzE(1)) {
                                zzjl.zza((zzjn.zza) zzjm.this);
                                zzjm.this.zza(zzjl);
                            } else {
                                String zzf = zzjm.this.zzKo;
                                zzpe.zzbe(new StringBuilder(String.valueOf(zzf).length() + 56).append("Ignoring adapter ").append(zzf).append(" as delayed impression is not supported").toString());
                                zzjm.this.zzD(2);
                            }
                        }
                    }
                }
            });
            zzjl zzjl2 = zzjl;
            zzjn = new zzjn(this.zzKr, this.zzKt, this.zzKo, zzjl2, this.zzKu, zzgD(), zza(elapsedRealtime, this.zzKp, j, j2));
        }
        return zzjn;
    }

    /* access modifiers changed from: protected */
    public zzjt zza(MediationAdapter mediationAdapter) {
        return new zzjz(mediationAdapter);
    }

    public void zza(int i, zzjv zzjv) {
        synchronized (this.zzrN) {
            this.zzKu = i;
            this.zzKv = zzjv;
            this.zzrN.notify();
        }
    }
}
