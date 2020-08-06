package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.applinks.AppLinkData;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zznj;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public final class zzna {
    private static final SimpleDateFormat zzTg = new SimpleDateFormat("yyyyMMdd", Locale.US);

    private static Integer zzB(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static String zzZ(int i) {
        return String.format(Locale.US, "#%06x", new Object[]{Integer.valueOf(16777215 & i)});
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0144 A[Catch:{ JSONException -> 0x0236 }] */
    public static zzmk zza(Context context, zzmh zzmh, String str) {
        String str2;
        long j;
        boolean optBoolean;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("ad_base_url", null);
            String optString2 = jSONObject.optString("ad_url", null);
            String optString3 = jSONObject.optString("ad_size", null);
            String optString4 = jSONObject.optString("ad_slot_size", optString3);
            boolean z = (zzmh == null || zzmh.zzRj == 0) ? false : true;
            String optString5 = jSONObject.optString("ad_json", null);
            if (optString5 == null) {
                optString5 = jSONObject.optString("ad_html", null);
            }
            if (optString5 == null) {
                optString5 = jSONObject.optString("body", null);
            }
            long j2 = -1;
            String optString6 = jSONObject.optString("debug_dialog", null);
            String optString7 = jSONObject.optString("debug_signals", null);
            long j3 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            String optString8 = jSONObject.optString("orientation", null);
            int i = -1;
            if ("portrait".equals(optString8)) {
                i = zzv.zzcL().zzkq();
            } else if ("landscape".equals(optString8)) {
                i = zzv.zzcL().zzkp();
            }
            zzmk zzmk = null;
            if (!TextUtils.isEmpty(optString5) || TextUtils.isEmpty(optString2)) {
                str2 = optString5;
            } else {
                zzmk = zzmz.zza(zzmh, context, zzmh.zzvf.zzaZ, optString2, null, null, null, null);
                optString = zzmk.zzNb;
                str2 = zzmk.body;
                j2 = zzmk.zzRO;
            }
            if (str2 == null) {
                return new zzmk(0);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List<String> list = zzmk == null ? null : zzmk.zzJY;
            if (optJSONArray != null) {
                list = zza(optJSONArray, list);
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("impression_urls");
            List<String> list2 = zzmk == null ? null : zzmk.zzJZ;
            if (optJSONArray2 != null) {
                list2 = zza(optJSONArray2, list2);
            }
            JSONArray optJSONArray3 = jSONObject.optJSONArray("manual_impression_urls");
            List<String> list3 = zzmk == null ? null : zzmk.zzRM;
            if (optJSONArray3 != null) {
                list3 = zza(optJSONArray3, list3);
            }
            if (zzmk != null) {
                if (zzmk.orientation != -1) {
                    i = zzmk.orientation;
                }
                if (zzmk.zzRJ > 0) {
                    j = zzmk.zzRJ;
                    String optString9 = jSONObject.optString("active_view");
                    String str3 = null;
                    optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                    if (optBoolean) {
                        str3 = jSONObject.optString("ad_passback_url", null);
                    }
                    return new zzmk(zzmh, optString, str2, list, list2, j, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString3, j2, optString6, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, zzmh.zzRl, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzok.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), (List<String>) null), zza(jSONObject.optJSONArray("video_complete_urls"), (List<String>) null), jSONObject.optBoolean("use_displayed_impression", false), zzmm.zzg(jSONObject.optJSONObject("auto_protection_configuration")), zzmh.zzRB, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), (List<String>) null), jSONObject.optBoolean("render_in_browser", zzmh.zzKc), optString4, zzon.zzi(jSONObject.optJSONObject("safe_browsing")), optString7, jSONObject.optBoolean("content_vertical_opted_out", true));
                }
            }
            j = j3;
            String optString92 = jSONObject.optString("active_view");
            String str32 = null;
            optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (optBoolean) {
            }
            return new zzmk(zzmh, optString, str2, list, list2, j, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString3, j2, optString6, optBoolean, str32, optString92, jSONObject.optBoolean("custom_render_allowed", false), z, zzmh.zzRl, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzok.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), (List<String>) null), zza(jSONObject.optJSONArray("video_complete_urls"), (List<String>) null), jSONObject.optBoolean("use_displayed_impression", false), zzmm.zzg(jSONObject.optJSONObject("auto_protection_configuration")), zzmh.zzRB, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), (List<String>) null), jSONObject.optBoolean("render_in_browser", zzmh.zzKc), optString4, zzon.zzi(jSONObject.optJSONObject("safe_browsing")), optString7, jSONObject.optBoolean("content_vertical_opted_out", true));
        } catch (JSONException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzpe.zzbe(valueOf.length() != 0 ? "Could not parse the inline ad response: ".concat(valueOf) : new String("Could not parse the inline ad response: "));
            return new zzmk(0);
        }
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList<>();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    public static JSONObject zza(Context context, zzmx zzmx) {
        zzmh zzmh = zzmx.zzSF;
        Location location = zzmx.zzyN;
        zznf zznf = zzmx.zzSG;
        Bundle bundle = zzmx.zzRk;
        JSONObject jSONObject = zzmx.zzSH;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("extra_caps", zzfx.zzDN.get());
            if (zzmx.zzRs.size() > 0) {
                hashMap.put("eid", TextUtils.join(",", zzmx.zzRs));
            }
            if (zzmh.zzRc != null) {
                hashMap.put("ad_pos", zzmh.zzRc);
            }
            zza((HashMap<String, Object>) hashMap, zzmh.zzRd);
            if (zzmh.zzvj.zzzm != null) {
                boolean z = false;
                boolean z2 = false;
                for (zzec zzec : zzmh.zzvj.zzzm) {
                    if (!zzec.zzzo && !z2) {
                        hashMap.put("format", zzec.zzzk);
                        z2 = true;
                    }
                    if (zzec.zzzo && !z) {
                        hashMap.put("fluid", "height");
                        z = true;
                    }
                    if (z2 && z) {
                        break;
                    }
                }
            } else {
                hashMap.put("format", zzmh.zzvj.zzzk);
                if (zzmh.zzvj.zzzo) {
                    hashMap.put("fluid", "height");
                }
            }
            if (zzmh.zzvj.width == -1) {
                hashMap.put("smart_w", "full");
            }
            if (zzmh.zzvj.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (zzmh.zzvj.zzzm != null) {
                StringBuilder sb = new StringBuilder();
                boolean z3 = false;
                for (zzec zzec2 : zzmh.zzvj.zzzm) {
                    if (zzec2.zzzo) {
                        z3 = true;
                    } else {
                        if (sb.length() != 0) {
                            sb.append("|");
                        }
                        sb.append(zzec2.width == -1 ? (int) (((float) zzec2.widthPixels) / zznf.zzxa) : zzec2.width);
                        sb.append("x");
                        sb.append(zzec2.height == -2 ? (int) (((float) zzec2.heightPixels) / zznf.zzxa) : zzec2.height);
                    }
                }
                if (z3) {
                    if (sb.length() != 0) {
                        sb.insert(0, "|");
                    }
                    sb.insert(0, "320x50");
                }
                hashMap.put("sz", sb);
            }
            if (zzmh.zzRj != 0) {
                hashMap.put("native_version", Integer.valueOf(zzmh.zzRj));
                hashMap.put("native_templates", zzmh.zzvB);
                hashMap.put("native_image_orientation", zzc(zzmh.zzvx));
                if (!zzmh.zzRt.isEmpty()) {
                    hashMap.put("native_custom_templates", zzmh.zzRt);
                }
            }
            if (zzmh.zzvj.zzzp) {
                hashMap.put("ene", true);
            }
            hashMap.put("slotname", zzmh.zzvd);
            hashMap.put("pn", zzmh.applicationInfo.packageName);
            if (zzmh.zzRe != null) {
                hashMap.put("vc", Integer.valueOf(zzmh.zzRe.versionCode));
            }
            hashMap.put("ms", zzmx.zzRf);
            hashMap.put("seq_num", zzmh.zzRg);
            hashMap.put("session_id", zzmh.zzRh);
            hashMap.put("js", zzmh.zzvf.zzaZ);
            zza(hashMap, zznf, zzmx.zzSD, zzmh.zzRG, zzmx.zzSC);
            zza((HashMap<String, Object>) hashMap, zzmx.zzSE);
            hashMap.put("platform", Build.MANUFACTURER);
            hashMap.put("submodel", Build.MODEL);
            if (location != null) {
                zza((HashMap<String, Object>) hashMap, location);
            } else if (zzmh.zzRd.versionCode >= 2 && zzmh.zzRd.zzyN != null) {
                zza((HashMap<String, Object>) hashMap, zzmh.zzRd.zzyN);
            }
            if (zzmh.versionCode >= 2) {
                hashMap.put("quality_signals", zzmh.zzRi);
            }
            if (zzmh.versionCode >= 4 && zzmh.zzRl) {
                hashMap.put("forceHttps", Boolean.valueOf(zzmh.zzRl));
            }
            if (bundle != null) {
                hashMap.put("content_info", bundle);
            }
            if (zzmh.versionCode >= 5) {
                hashMap.put("u_sd", Float.valueOf(zzmh.zzxa));
                hashMap.put("sh", Integer.valueOf(zzmh.zzRo));
                hashMap.put("sw", Integer.valueOf(zzmh.zzRn));
            } else {
                hashMap.put("u_sd", Float.valueOf(zznf.zzxa));
                hashMap.put("sh", Integer.valueOf(zznf.zzRo));
                hashMap.put("sw", Integer.valueOf(zznf.zzRn));
            }
            if (zzmh.versionCode >= 6) {
                if (!TextUtils.isEmpty(zzmh.zzRp)) {
                    try {
                        hashMap.put("view_hierarchy", new JSONObject(zzmh.zzRp));
                    } catch (JSONException e) {
                        zzpe.zzc("Problem serializing view hierarchy to JSON", e);
                    }
                }
                hashMap.put("correlation_id", Long.valueOf(zzmh.zzRq));
            }
            if (zzmh.versionCode >= 7) {
                hashMap.put("request_id", zzmh.zzRr);
            }
            if (zzmh.versionCode >= 11 && zzmh.zzRv != null) {
                hashMap.put("capability", zzmh.zzRv.toBundle());
            }
            if (zzmh.versionCode >= 12 && !TextUtils.isEmpty(zzmh.zzRw)) {
                hashMap.put("anchor", zzmh.zzRw);
            }
            if (zzmh.versionCode >= 13) {
                hashMap.put("android_app_volume", Float.valueOf(zzmh.zzRx));
            }
            if (zzmh.versionCode >= 18) {
                hashMap.put("android_app_muted", Boolean.valueOf(zzmh.zzRD));
            }
            if (zzmh.versionCode >= 14 && zzmh.zzRy > 0) {
                hashMap.put("target_api", Integer.valueOf(zzmh.zzRy));
            }
            if (zzmh.versionCode >= 15) {
                hashMap.put("scroll_index", Integer.valueOf(zzmh.zzRz == -1 ? -1 : zzmh.zzRz));
            }
            if (zzmh.versionCode >= 16) {
                hashMap.put("_activity_context", Boolean.valueOf(zzmh.zzRA));
            }
            if (zzmh.versionCode >= 18) {
                if (!TextUtils.isEmpty(zzmh.zzRE)) {
                    try {
                        hashMap.put("app_settings", new JSONObject(zzmh.zzRE));
                    } catch (JSONException e2) {
                        zzpe.zzc("Problem creating json from app settings", e2);
                    }
                }
                hashMap.put("render_in_browser", Boolean.valueOf(zzmh.zzKc));
            }
            if (zzmh.versionCode >= 18) {
                hashMap.put("android_num_video_cache_tasks", Integer.valueOf(zzmh.zzRF));
            }
            zza(hashMap);
            hashMap.put("cache_state", jSONObject);
            if (zzmh.versionCode >= 19) {
                hashMap.put("gct", zzmh.zzRH);
            }
            if (zzpe.zzai(2)) {
                String valueOf = String.valueOf(zzv.zzcJ().zzP(hashMap).toString(2));
                zzpe.m2431v(valueOf.length() != 0 ? "Ad Request JSON: ".concat(valueOf) : new String("Ad Request JSON: "));
            }
            return zzv.zzcJ().zzP(hashMap);
        } catch (JSONException e3) {
            String valueOf2 = String.valueOf(e3.getMessage());
            zzpe.zzbe(valueOf2.length() != 0 ? "Problem serializing ad request to JSON: ".concat(valueOf2) : new String("Problem serializing ad request to JSON: "));
            return null;
        }
    }

    private static void zza(HashMap<String, Object> hashMap) {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle2.putString("cl", "139965458");
        bundle2.putString("rapid_rc", "dev");
        bundle2.putString("rapid_rollup", "HEAD");
        bundle.putBundle("build_meta", bundle2);
        bundle.putString("mf", Boolean.toString(zzfx.zzDP.get().booleanValue()));
        hashMap.put("sdk_env", bundle);
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static void zza(HashMap<String, Object> hashMap, zzdy zzdy) {
        String zzke = zzpc.zzke();
        if (zzke != null) {
            hashMap.put("abf", zzke);
        }
        if (zzdy.zzyF != -1) {
            hashMap.put("cust_age", zzTg.format(new Date(zzdy.zzyF)));
        }
        if (zzdy.extras != null) {
            hashMap.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, zzdy.extras);
        }
        if (zzdy.zzyG != -1) {
            hashMap.put("cust_gender", Integer.valueOf(zzdy.zzyG));
        }
        if (zzdy.zzyH != null) {
            hashMap.put("kw", zzdy.zzyH);
        }
        if (zzdy.zzyJ != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(zzdy.zzyJ));
        }
        if (zzdy.zzyI) {
            hashMap.put("adtest", "on");
        }
        if (zzdy.versionCode >= 2) {
            if (zzdy.zzyK) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty(zzdy.zzyL)) {
                hashMap.put("ppid", zzdy.zzyL);
            }
            if (zzdy.zzyM != null) {
                zza(hashMap, zzdy.zzyM);
            }
        }
        if (zzdy.versionCode >= 3 && zzdy.zzyO != null) {
            hashMap.put("url", zzdy.zzyO);
        }
        if (zzdy.versionCode >= 5) {
            if (zzdy.zzyQ != null) {
                hashMap.put("custom_targeting", zzdy.zzyQ);
            }
            if (zzdy.zzyR != null) {
                hashMap.put("category_exclusions", zzdy.zzyR);
            }
            if (zzdy.zzyS != null) {
                hashMap.put("request_agent", zzdy.zzyS);
            }
        }
        if (zzdy.versionCode >= 6 && zzdy.zzyT != null) {
            hashMap.put("request_pkg", zzdy.zzyT);
        }
        if (zzdy.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(zzdy.zzyU));
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zzfj zzfj) {
        String str;
        String str2 = null;
        if (Color.alpha(zzfj.zzAr) != 0) {
            hashMap.put("acolor", zzZ(zzfj.zzAr));
        }
        if (Color.alpha(zzfj.backgroundColor) != 0) {
            hashMap.put("bgcolor", zzZ(zzfj.backgroundColor));
        }
        if (!(Color.alpha(zzfj.zzAs) == 0 || Color.alpha(zzfj.zzAt) == 0)) {
            hashMap.put("gradientto", zzZ(zzfj.zzAs));
            hashMap.put("gradientfrom", zzZ(zzfj.zzAt));
        }
        if (Color.alpha(zzfj.zzAu) != 0) {
            hashMap.put("bcolor", zzZ(zzfj.zzAu));
        }
        hashMap.put("bthick", Integer.toString(zzfj.zzAv));
        switch (zzfj.zzAw) {
            case 0:
                str = "none";
                break;
            case 1:
                str = "dashed";
                break;
            case 2:
                str = "dotted";
                break;
            case 3:
                str = "solid";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            hashMap.put("btype", str);
        }
        switch (zzfj.zzAx) {
            case 0:
                str2 = "light";
                break;
            case 1:
                str2 = "medium";
                break;
            case 2:
                str2 = "dark";
                break;
        }
        if (str2 != null) {
            hashMap.put("callbuttoncolor", str2);
        }
        if (zzfj.zzAy != null) {
            hashMap.put("channel", zzfj.zzAy);
        }
        if (Color.alpha(zzfj.zzAz) != 0) {
            hashMap.put("dcolor", zzZ(zzfj.zzAz));
        }
        if (zzfj.zzAA != null) {
            hashMap.put("font", zzfj.zzAA);
        }
        if (Color.alpha(zzfj.zzAB) != 0) {
            hashMap.put("hcolor", zzZ(zzfj.zzAB));
        }
        hashMap.put("headersize", Integer.toString(zzfj.zzAC));
        if (zzfj.zzAD != null) {
            hashMap.put("q", zzfj.zzAD);
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zznf zznf, zznj.zza zza, Bundle bundle, Bundle bundle2) {
        hashMap.put("am", Integer.valueOf(zznf.zzUa));
        hashMap.put("cog", zzB(zznf.zzUb));
        hashMap.put("coh", zzB(zznf.zzUc));
        if (!TextUtils.isEmpty(zznf.zzUd)) {
            hashMap.put("carrier", zznf.zzUd);
        }
        hashMap.put("gl", zznf.zzUe);
        if (zznf.zzUf) {
            hashMap.put("simulator", 1);
        }
        if (zznf.zzUg) {
            hashMap.put("is_sidewinder", 1);
        }
        hashMap.put("ma", zzB(zznf.zzUh));
        hashMap.put("sp", zzB(zznf.zzUi));
        hashMap.put("hl", zznf.zzUj);
        if (!TextUtils.isEmpty(zznf.zzUk)) {
            hashMap.put("mv", zznf.zzUk);
        }
        hashMap.put("muv", Integer.valueOf(zznf.zzUl));
        if (zznf.zzUm != -2) {
            hashMap.put("cnt", Integer.valueOf(zznf.zzUm));
        }
        hashMap.put("gnt", Integer.valueOf(zznf.zzUn));
        hashMap.put("pt", Integer.valueOf(zznf.zzUo));
        hashMap.put("rm", Integer.valueOf(zznf.zzUp));
        hashMap.put("riv", Integer.valueOf(zznf.zzUq));
        Bundle bundle3 = new Bundle();
        bundle3.putString("build", zznf.zzUv);
        Bundle bundle4 = new Bundle();
        bundle4.putBoolean("is_charging", zznf.zzUs);
        bundle4.putDouble("battery_level", zznf.zzUr);
        bundle3.putBundle("battery", bundle4);
        Bundle bundle5 = new Bundle();
        bundle5.putInt("active_network_state", zznf.zzUu);
        bundle5.putBoolean("active_network_metered", zznf.zzUt);
        if (zza != null) {
            Bundle bundle6 = new Bundle();
            bundle6.putInt("predicted_latency_micros", zza.zzUA);
            bundle6.putLong("predicted_down_throughput_bps", zza.zzUB);
            bundle6.putLong("predicted_up_throughput_bps", zza.zzUC);
            bundle5.putBundle("predictions", bundle6);
        }
        bundle3.putBundle("network", bundle5);
        Bundle bundle7 = new Bundle();
        bundle7.putBoolean("is_browser_custom_tabs_capable", zznf.zzUw);
        bundle3.putBundle("browser", bundle7);
        if (bundle != null) {
            bundle3.putBundle("android_mem_info", zzg(bundle));
        }
        Bundle bundle8 = new Bundle();
        bundle8.putBundle("parental_controls", bundle2);
        bundle3.putBundle("play_store", bundle8);
        hashMap.put("device", bundle3);
    }

    private static void zza(HashMap<String, Object> hashMap, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("doritos", str);
        hashMap.put("pii", bundle);
    }

    private static String zzc(zzgw zzgw) {
        switch (zzgw != null ? zzgw.zzGE : 0) {
            case 1:
                return "portrait";
            case 2:
                return "landscape";
            default:
                return "any";
        }
    }

    public static JSONObject zzc(zzmk zzmk) {
        JSONObject jSONObject = new JSONObject();
        if (zzmk.zzNb != null) {
            jSONObject.put("ad_base_url", zzmk.zzNb);
        }
        if (zzmk.zzRN != null) {
            jSONObject.put("ad_size", zzmk.zzRN);
        }
        jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, zzmk.zzzn);
        if (zzmk.zzzn) {
            jSONObject.put("ad_json", zzmk.body);
        } else {
            jSONObject.put("ad_html", zzmk.body);
        }
        if (zzmk.zzRP != null) {
            jSONObject.put("debug_dialog", zzmk.zzRP);
        }
        if (zzmk.zzSg != null) {
            jSONObject.put("debug_signals", zzmk.zzSg);
        }
        if (zzmk.zzRJ != -1) {
            jSONObject.put("interstitial_timeout", ((double) zzmk.zzRJ) / 1000.0d);
        }
        if (zzmk.orientation == zzv.zzcL().zzkq()) {
            jSONObject.put("orientation", "portrait");
        } else if (zzmk.orientation == zzv.zzcL().zzkp()) {
            jSONObject.put("orientation", "landscape");
        }
        if (zzmk.zzJY != null) {
            jSONObject.put("click_urls", zzl(zzmk.zzJY));
        }
        if (zzmk.zzJZ != null) {
            jSONObject.put("impression_urls", zzl(zzmk.zzJZ));
        }
        if (zzmk.zzRM != null) {
            jSONObject.put("manual_impression_urls", zzl(zzmk.zzRM));
        }
        if (zzmk.zzRS != null) {
            jSONObject.put("active_view", zzmk.zzRS);
        }
        jSONObject.put("ad_is_javascript", zzmk.zzRQ);
        if (zzmk.zzRR != null) {
            jSONObject.put("ad_passback_url", zzmk.zzRR);
        }
        jSONObject.put("mediation", zzmk.zzRK);
        jSONObject.put("custom_render_allowed", zzmk.zzRT);
        jSONObject.put("content_url_opted_out", zzmk.zzRU);
        jSONObject.put("content_vertical_opted_out", zzmk.zzSh);
        jSONObject.put("prefetch", zzmk.zzRV);
        if (zzmk.zzKe != -1) {
            jSONObject.put("refresh_interval_milliseconds", zzmk.zzKe);
        }
        if (zzmk.zzRL != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", zzmk.zzRL);
        }
        if (!TextUtils.isEmpty(zzmk.zzRY)) {
            jSONObject.put("gws_query_id", zzmk.zzRY);
        }
        jSONObject.put("fluid", zzmk.zzzo ? "height" : "");
        jSONObject.put("native_express", zzmk.zzzp);
        if (zzmk.zzSa != null) {
            jSONObject.put("video_start_urls", zzl(zzmk.zzSa));
        }
        if (zzmk.zzSb != null) {
            jSONObject.put("video_complete_urls", zzl(zzmk.zzSb));
        }
        if (zzmk.zzRZ != null) {
            jSONObject.put("rewards", zzmk.zzRZ.zzjy());
        }
        jSONObject.put("use_displayed_impression", zzmk.zzSc);
        jSONObject.put("auto_protection_configuration", zzmk.zzSd);
        jSONObject.put("render_in_browser", zzmk.zzKc);
        return jSONObject;
    }

    private static Bundle zzg(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("runtime_free", Long.toString(bundle.getLong("runtime_free_memory", -1)));
        bundle2.putString("runtime_max", Long.toString(bundle.getLong("runtime_max_memory", -1)));
        bundle2.putString("runtime_total", Long.toString(bundle.getLong("runtime_total_memory", -1)));
        Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo) bundle.getParcelable("debug_memory_info");
        if (memoryInfo != null) {
            bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
            bundle2.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
            bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
            bundle2.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
            bundle2.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
            bundle2.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
            bundle2.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
            bundle2.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
            bundle2.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
        }
        return bundle2;
    }

    @Nullable
    static JSONArray zzl(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }
}
