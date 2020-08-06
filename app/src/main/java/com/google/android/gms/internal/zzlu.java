package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpp;
import com.google.android.gms.internal.zzqe;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzlu implements Callable<zzov> {
    static long zzPS = TimeUnit.SECONDS.toMillis(60);
    private final Context mContext;
    private final zzlt zzGp;
    private final zzav zzGr;
    private int zzPF;
    /* access modifiers changed from: private */
    public final zzov.zza zzPo;
    private final zzpp zzQb;
    /* access modifiers changed from: private */
    public final zzr zzQc;
    private boolean zzQd;
    private List<String> zzQe;
    private JSONObject zzQf;
    private final Object zzrN = new Object();
    private final zzgf zzsr;

    public interface zza<T extends zzgu.zza> {
        T zza(zzlu zzlu, JSONObject jSONObject);
    }

    class zzb {
        public zzhx zzQz;

        zzb(zzlu zzlu) {
        }
    }

    public zzlu(Context context, zzr zzr, zzpp zzpp, zzav zzav, zzov.zza zza2, zzgf zzgf) {
        this.mContext = context;
        this.zzQc = zzr;
        this.zzQb = zzpp;
        this.zzPo = zza2;
        this.zzGr = zzav;
        this.zzsr = zzgf;
        this.zzGp = zza(context, zza2, zzr, zzav);
        this.zzGp.zziE();
        this.zzQd = false;
        this.zzPF = -2;
        this.zzQe = null;
    }

    private zzgu.zza zza(zza zza2, JSONObject jSONObject, String str) {
        if (zziQ() || zza2 == null || jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("tracking_urls_and_actions");
        String[] zzd = zzd(jSONObject2, "impression_tracking_urls");
        this.zzQe = zzd == null ? null : Arrays.asList(zzd);
        this.zzQf = jSONObject2.optJSONObject("active_view");
        zzgu.zza zza3 = zza2.zza(this, jSONObject);
        if (zza3 == null) {
            zzpe.m2432e("Failed to retrieve ad assets.");
            return null;
        }
        zza3.zzb(new zzgv(this.mContext, this.zzQc, this.zzGp, this.zzGr, jSONObject, zza3, this.zzPo.zzSF.zzvf, str));
        return zza3;
    }

    private zzqf<zzgo> zza(JSONObject jSONObject, boolean z, boolean z2) {
        final String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        final double optDouble = jSONObject.optDouble("scale", 1.0d);
        final boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (TextUtils.isEmpty(string)) {
            zza(0, z);
            return new zzqd(null);
        } else if (z2) {
            return new zzqd(new zzgo(null, Uri.parse(string), optDouble));
        } else {
            final boolean z3 = z;
            return this.zzQb.zza(string, new zzpp.zza<zzgo>() {
                @TargetApi(19)
                /* renamed from: zzg */
                public zzgo zzh(InputStream inputStream) {
                    Bitmap bitmap;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inDensity = (int) (160.0d * optDouble);
                    if (!optBoolean) {
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                    }
                    try {
                        bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    } catch (Exception e) {
                        zzpe.zzb("Error grabbing image.", e);
                        bitmap = null;
                    }
                    if (bitmap == null) {
                        zzlu.this.zza(2, z3);
                        return null;
                    }
                    if (zzs.zzyF()) {
                        int width = bitmap.getWidth();
                        zzpe.m2431v(new StringBuilder(61).append("Decoded image w: ").append(width).append(" h:").append(bitmap.getHeight()).append(" bytes: ").append(bitmap.getAllocationByteCount()).toString());
                    }
                    return new zzgo(new BitmapDrawable(Resources.getSystem(), bitmap), Uri.parse(string), optDouble);
                }

                /* renamed from: zziS */
                public zzgo zziT() {
                    zzlu.this.zza(2, z3);
                    return null;
                }
            });
        }
    }

    private void zza(zzgu.zza zza2) {
        if (zza2 instanceof zzgr) {
            final zzgr zzgr = (zzgr) zza2;
            zzb zzb2 = new zzb(this);
            final C13313 r1 = new zzhx() {
                public void zza(zzqp zzqp, Map<String, String> map) {
                    zzlu.this.zzb((zzhh) zzgr, map.get("asset"));
                }
            };
            zzb2.zzQz = r1;
            this.zzGp.zza((zzlt.zza) new zzlt.zza(this) {
                public void zze(zzjb zzjb) {
                    zzjb.zza("/nativeAdCustomClick", r1);
                }
            });
        }
    }

    private JSONObject zzaG(final String str) {
        if (zziQ()) {
            return null;
        }
        final zzqc zzqc = new zzqc();
        final zzb zzb2 = new zzb(this);
        this.zzGp.zza((zzlt.zza) new zzlt.zza() {
            public void zze(final zzjb zzjb) {
                C13291 r0 = new zzhx() {
                    public void zza(zzqp zzqp, Map<String, String> map) {
                        try {
                            String str = map.get(GraphResponse.SUCCESS_KEY);
                            if (!TextUtils.isEmpty(str)) {
                                if (str.equals(new JSONObject(str).optString("ads_id", ""))) {
                                    zzjb.zzb("/nativeAdPreProcess", zzb2.zzQz);
                                    zzqc.zzh(new JSONObject(str).getJSONArray("ads").getJSONObject(0));
                                }
                            }
                        } catch (JSONException e) {
                            zzpe.zzb("Malformed native JSON response.", e);
                            zzlu.this.zzS(0);
                            zzac.zza(zzlu.this.zziQ(), (Object) "Unable to set the ad state error!");
                            zzqc.zzh(null);
                        }
                    }
                };
                zzb2.zzQz = r0;
                zzjb.zza("/nativeAdPreProcess", (zzhx) r0);
                try {
                    JSONObject jSONObject = new JSONObject(zzlu.this.zzPo.zzVB.body);
                    jSONObject.put("ads_id", str);
                    zzjb.zza("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
                } catch (JSONException e) {
                    zzpe.zzc("Exception occurred while invoking javascript", e);
                    zzqc.zzh(null);
                }
            }

            public void zziO() {
                zzqc.zzh(null);
            }
        });
        return (JSONObject) zzqc.get(zzPS, TimeUnit.MILLISECONDS);
    }

    private zzov zzb(zzgu.zza zza2) {
        int i;
        synchronized (this.zzrN) {
            i = this.zzPF;
            if (zza2 == null && this.zzPF == -2) {
                i = 0;
            }
        }
        return new zzov(this.zzPo.zzSF.zzRd, null, this.zzPo.zzVB.zzJY, i, this.zzPo.zzVB.zzJZ, this.zzQe, this.zzPo.zzVB.orientation, this.zzPo.zzVB.zzKe, this.zzPo.zzSF.zzRg, false, null, null, null, null, null, 0, this.zzPo.zzvj, this.zzPo.zzVB.zzRJ, this.zzPo.zzVv, this.zzPo.zzVw, this.zzPo.zzVB.zzRP, this.zzQf, i != -2 ? null : zza2, null, null, null, this.zzPo.zzVB.zzSc, this.zzPo.zzVB.zzSd, null, this.zzPo.zzVB.zzKb, this.zzPo.zzVB.zzSg);
    }

    private Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException e) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void zzb(zzhh zzhh, String str) {
        try {
            zzhl zzz = this.zzQc.zzz(zzhh.getCustomTemplateId());
            if (zzz != null) {
                zzz.zza(zzhh, str);
            }
        } catch (RemoteException e) {
            zzpe.zzc(new StringBuilder(String.valueOf(str).length() + 40).append("Failed to call onCustomClick for asset ").append(str).append(".").toString(), e);
        }
    }

    private String[] zzd(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    /* access modifiers changed from: private */
    public static List<Drawable> zzh(List<zzgo> list) {
        ArrayList arrayList = new ArrayList();
        for (zzgo zzfK : list) {
            arrayList.add((Drawable) zze.zzE(zzfK.zzfK()));
        }
        return arrayList;
    }

    public void zzS(int i) {
        synchronized (this.zzrN) {
            this.zzQd = true;
            this.zzPF = i;
        }
    }

    /* access modifiers changed from: package-private */
    public zzlt zza(Context context, zzov.zza zza2, zzr zzr, zzav zzav) {
        return new zzlt(context, zza2, zzr, zzav);
    }

    /* access modifiers changed from: package-private */
    public zzlv zza(Context context, zzav zzav, zzov.zza zza2, zzgf zzgf, zzr zzr) {
        return new zzlv(context, zzav, zza2, zzgf, zzr);
    }

    public zzqf<zzgo> zza(JSONObject jSONObject, String str, boolean z, boolean z2) {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public List<zzqf<zzgo>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) {
        JSONArray jSONArray = z ? jSONObject.getJSONArray(str) : jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            zza(0, z);
            return arrayList;
        }
        int length = z3 ? jSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, z, z2));
        }
        return arrayList;
    }

    public Future<zzgo> zza(JSONObject jSONObject, String str, boolean z) {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    public void zza(int i, boolean z) {
        if (z) {
            zzS(i);
        }
    }

    public zzqf<zzqp> zzc(JSONObject jSONObject, String str) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return new zzqd(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzpe.zzbe("Required field 'vast_xml' is missing");
            return new zzqd(null);
        }
        return zza(this.mContext, this.zzGr, this.zzPo, this.zzsr, this.zzQc).zzf(optJSONObject);
    }

    /* access modifiers changed from: protected */
    public zza zzd(JSONObject jSONObject) {
        if (zziQ() || jSONObject == null) {
            return null;
        }
        String string = jSONObject.getString("template_id");
        boolean z = this.zzPo.zzSF.zzvx != null ? this.zzPo.zzSF.zzvx.zzGD : false;
        boolean z2 = this.zzPo.zzSF.zzvx != null ? this.zzPo.zzSF.zzvx.zzGF : false;
        if ("2".equals(string)) {
            return new zzlw(z, z2);
        }
        if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(string)) {
            return new zzlx(z, z2);
        }
        if ("3".equals(string)) {
            final String string2 = jSONObject.getString("custom_template_id");
            final zzqc zzqc = new zzqc();
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zzqc.zzh(zzlu.this.zzQc.zzcs().get(string2));
                }
            });
            if (zzqc.get(zzPS, TimeUnit.MILLISECONDS) != null) {
                return new zzly(z);
            }
            String valueOf = String.valueOf(jSONObject.getString("custom_template_id"));
            zzpe.m2432e(valueOf.length() != 0 ? "No handler for custom template: ".concat(valueOf) : new String("No handler for custom template: "));
        } else {
            zzS(0);
        }
        return null;
    }

    public zzqf<zzgm> zze(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return new zzqd(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb2 = zzb(optJSONObject, "text_color");
        Integer zzb3 = zzb(optJSONObject, "bg_color");
        final int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        final int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        final int i = (this.zzPo.zzSF.zzvx == null || this.zzPo.zzSF.zzvx.versionCode < 2) ? 1 : this.zzPo.zzSF.zzvx.zzGG;
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        final String str = optString;
        final Integer num = zzb3;
        final Integer num2 = zzb2;
        final int i2 = optInt;
        return zzqe.zza(zzqe.zzo(arrayList), new zzqe.zza<List<zzgo>, zzgm>(this) {
            /* renamed from: zzj */
            public zzgm apply(List<zzgo> list) {
                if (list != null) {
                    try {
                        if (!list.isEmpty()) {
                            new zzgm(str, zzlu.zzh(list), num, num2, i2 > 0 ? Integer.valueOf(i2) : null, optInt3 + optInt2, i);
                            return r0;
                        }
                    } catch (RemoteException e) {
                        zzpe.zzb("Could not get attribution icon", e);
                        return null;
                    }
                }
                zzgm zzgm = null;
                return zzgm;
            }
        });
    }

    /* renamed from: zziP */
    public zzov call() {
        try {
            this.zzGp.zziF();
            String zziR = zziR();
            JSONObject zzaG = zzaG(zziR);
            zzgu.zza zza2 = zza(zzd(zzaG), zzaG, zziR);
            zza(zza2);
            return zzb(zza2);
        } catch (InterruptedException | CancellationException | ExecutionException e) {
        } catch (JSONException e2) {
            zzpe.zzc("Malformed native JSON response.", e2);
        } catch (TimeoutException e3) {
            zzpe.zzc("Timeout when loading native ad.", e3);
        }
        if (!this.zzQd) {
            zzS(0);
        }
        return zzb((zzgu.zza) null);
    }

    public boolean zziQ() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzQd;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public String zziR() {
        return UUID.randomUUID().toString();
    }
}
