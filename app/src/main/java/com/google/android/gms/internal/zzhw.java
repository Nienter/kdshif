package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzv;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public final class zzhw {
    public static final zzhx zzHd = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
        }
    };
    public static final zzhx zzHe = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            String str = map.get("urls");
            if (TextUtils.isEmpty(str)) {
                zzpe.zzbe("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String[] split = str.split(",");
            HashMap hashMap = new HashMap();
            PackageManager packageManager = zzqp.getContext().getPackageManager();
            for (String str2 : split) {
                String[] split2 = str2.split(";", 2);
                hashMap.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) != null));
            }
            zzqp.zza("openableURLs", (Map<String, ?>) hashMap);
        }
    };
    public static final zzhx zzHf = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            PackageManager packageManager = zzqp.getContext().getPackageManager();
            try {
                try {
                    JSONArray jSONArray = new JSONObject(map.get(ShareConstants.WEB_DIALOG_PARAM_DATA)).getJSONArray("intents");
                    JSONObject jSONObject = new JSONObject();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String optString = jSONObject2.optString("id");
                            String optString2 = jSONObject2.optString("u");
                            String optString3 = jSONObject2.optString("i");
                            String optString4 = jSONObject2.optString("m");
                            String optString5 = jSONObject2.optString("p");
                            String optString6 = jSONObject2.optString("c");
                            jSONObject2.optString("f");
                            jSONObject2.optString("e");
                            Intent intent = new Intent();
                            if (!TextUtils.isEmpty(optString2)) {
                                intent.setData(Uri.parse(optString2));
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                intent.setAction(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4)) {
                                intent.setType(optString4);
                            }
                            if (!TextUtils.isEmpty(optString5)) {
                                intent.setPackage(optString5);
                            }
                            if (!TextUtils.isEmpty(optString6)) {
                                String[] split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    intent.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                            try {
                                jSONObject.put(optString, packageManager.resolveActivity(intent, 65536) != null);
                            } catch (JSONException e) {
                                zzpe.zzb("Error constructing openable urls response.", e);
                            }
                        } catch (JSONException e2) {
                            zzpe.zzb("Error parsing the intent data.", e2);
                        }
                    }
                    zzqp.zzb("openableIntents", jSONObject);
                } catch (JSONException e3) {
                    zzqp.zzb("openableIntents", new JSONObject());
                }
            } catch (JSONException e4) {
                zzqp.zzb("openableIntents", new JSONObject());
            }
        }
    };
    public static final zzhx zzHg = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            Uri uri;
            String str = map.get("u");
            if (str == null) {
                zzpe.zzbe("URL missing from click GMSG.");
                return;
            }
            Uri parse = Uri.parse(str);
            try {
                zzav zzkX = zzqp.zzkX();
                if (zzkX != null && zzkX.zzc(parse)) {
                    uri = zzkX.zza(parse, zzqp.getContext(), zzqp.getView());
                    new zzps(zzqp.getContext(), zzqp.zzkY().zzaZ, uri.toString()).zziw();
                }
            } catch (zzaw e) {
                String valueOf = String.valueOf(str);
                zzpe.zzbe(valueOf.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf) : new String("Unable to append parameter to URL: "));
            }
            uri = parse;
            new zzps(zzqp.getContext(), zzqp.zzkY().zzaZ, uri.toString()).zziw();
        }
    };
    public static final zzhx zzHh = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            zze zzkT = zzqp.zzkT();
            if (zzkT != null) {
                zzkT.close();
                return;
            }
            zze zzkU = zzqp.zzkU();
            if (zzkU != null) {
                zzkU.close();
            } else {
                zzpe.zzbe("A GMSG tried to close something that wasn't an overlay.");
            }
        }
    };
    public static final zzhx zzHi = new zzhx() {
        private void zzd(zzqp zzqp) {
            zzpe.zzbd("Received support message, responding.");
            zzd zzbz = zzqp.zzbz();
            if (!(zzbz == null || zzbz.zzsO == null)) {
                zzqp.getContext();
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("event", "checkSupport");
                jSONObject.put("supports", false);
                zzqp.zzb("appStreaming", jSONObject);
            } catch (Throwable th) {
            }
        }

        public void zza(zzqp zzqp, Map<String, String> map) {
            if ("checkSupport".equals(map.get(NativeProtocol.WEB_DIALOG_ACTION))) {
                zzd(zzqp);
                return;
            }
            zze zzkT = zzqp.zzkT();
            if (zzkT != null) {
                zzkT.zzg(zzqp, map);
            }
        }
    };
    public static final zzhx zzHj = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            zzqp.zzK(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close")));
        }
    };
    public static final zzhx zzHk = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            String str = map.get("u");
            if (str == null) {
                zzpe.zzbe("URL missing from httpTrack GMSG.");
            } else {
                new zzps(zzqp.getContext(), zzqp.zzkY().zzaZ, str).zziw();
            }
        }
    };
    public static final zzhx zzHl = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            String valueOf = String.valueOf(map.get("string"));
            zzpe.zzbd(valueOf.length() != 0 ? "Received log message: ".concat(valueOf) : new String("Received log message: "));
        }
    };
    public static final zzhx zzHm = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            zzgs zzll = zzqp.zzll();
            if (zzll != null) {
                zzll.zzfR();
            }
        }
    };
    public static final zzhx zzHn = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            String str = map.get("tx");
            String str2 = map.get("ty");
            String str3 = map.get("td");
            try {
                int parseInt = Integer.parseInt(str);
                int parseInt2 = Integer.parseInt(str2);
                int parseInt3 = Integer.parseInt(str3);
                zzav zzkX = zzqp.zzkX();
                if (zzkX != null) {
                    zzkX.zzW().zza(parseInt, parseInt2, parseInt3);
                }
            } catch (NumberFormatException e) {
                zzpe.zzbe("Could not parse touch parameters from gmsg.");
            }
        }
    };
    public static final zzhx zzHo = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            if (zzfx.zzCX.get().booleanValue()) {
                zzqp.zzL(!Boolean.parseBoolean(map.get("disabled")));
            }
        }
    };
    public static final zzhx zzHp = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            String str = map.get(NativeProtocol.WEB_DIALOG_ACTION);
            if ("pause".equals(str)) {
                zzqp.zzbV();
            } else if ("resume".equals(str)) {
                zzqp.zzbW();
            }
        }
    };
    public static final zzhx zzHq = new zzih();
    public static final zzhx zzHr = new zzii();
    public static final zzhx zzHs = new zzim();
    public static final zzhx zzHt = new zzhv();
    public static final zzif zzHu = new zzif();
    public static final zzhx zzHv = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            if (map.keySet().contains("start")) {
                zzqp.zzkV().zzlu();
            } else if (map.keySet().contains("stop")) {
                zzqp.zzkV().zzlv();
            } else if (map.keySet().contains("cancel")) {
                zzqp.zzkV().zzlw();
            }
        }
    };
    public static final zzhx zzHw = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            if (map.keySet().contains("start")) {
                zzqp.zzM(true);
            }
            if (map.keySet().contains("stop")) {
                zzqp.zzM(false);
            }
        }
    };
    public static final zzhx zzHx = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            zzqp.zza("locationReady", (Map<String, ?>) zzv.zzcJ().zza((View) zzqp, (WindowManager) zzqp.getContext().getSystemService("window")));
            zzpe.zzbe("GET LOCATION COMPILED");
        }
    };
}
