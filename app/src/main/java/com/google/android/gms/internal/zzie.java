package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzmb
public final class zzie implements zzhx {
    private final zze zzHN;
    private final zzkj zzHO;
    private final zzhz zzHQ;

    public static class zza {
        private final zzqp zzGt;

        public zza(zzqp zzqp) {
            this.zzGt = zzqp;
        }

        public Intent zza(Context context, Map<String, String> map) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            String str = map.get("u");
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (this.zzGt != null) {
                str = zzv.zzcJ().zza(this.zzGt, str);
            }
            Uri parse = Uri.parse(str);
            boolean parseBoolean = Boolean.parseBoolean(map.get("use_first_package"));
            boolean parseBoolean2 = Boolean.parseBoolean(map.get("use_running_process"));
            Uri build = "http".equalsIgnoreCase(parse.getScheme()) ? parse.buildUpon().scheme("https").build() : "https".equalsIgnoreCase(parse.getScheme()) ? parse.buildUpon().scheme("http").build() : null;
            ArrayList arrayList = new ArrayList();
            Intent zzf = zzf(parse);
            Intent zzf2 = zzf(build);
            ResolveInfo zza = zza(context, zzf, arrayList);
            if (zza != null) {
                return zza(zzf, zza);
            }
            if (zzf2 != null) {
                ResolveInfo zza2 = zza(context, zzf2);
                if (zza2 != null) {
                    Intent zza3 = zza(zzf, zza2);
                    if (zza(context, zza3) != null) {
                        return zza3;
                    }
                }
            }
            if (arrayList.size() == 0) {
                return zzf;
            }
            if (parseBoolean2 && activityManager != null) {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ResolveInfo resolveInfo = (ResolveInfo) it.next();
                        Iterator<ActivityManager.RunningAppProcessInfo> it2 = runningAppProcesses.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (it2.next().processName.equals(resolveInfo.activityInfo.packageName)) {
                                    return zza(zzf, resolveInfo);
                                }
                            }
                        }
                    }
                }
            }
            return parseBoolean ? zza(zzf, (ResolveInfo) arrayList.get(0)) : zzf;
        }

        public Intent zza(Intent intent, ResolveInfo resolveInfo) {
            Intent intent2 = new Intent(intent);
            intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            return intent2;
        }

        public ResolveInfo zza(Context context, Intent intent) {
            return zza(context, intent, new ArrayList());
        }

        public ResolveInfo zza(Context context, Intent intent, ArrayList<ResolveInfo> arrayList) {
            ResolveInfo resolveInfo;
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (queryIntentActivities != null && resolveActivity != null) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= queryIntentActivities.size()) {
                        break;
                    }
                    ResolveInfo resolveInfo2 = queryIntentActivities.get(i2);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                        resolveInfo = resolveActivity;
                        break;
                    }
                    i = i2 + 1;
                }
            }
            resolveInfo = null;
            arrayList.addAll(queryIntentActivities);
            return resolveInfo;
        }

        public Intent zzf(Uri uri) {
            if (uri == null) {
                return null;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(uri);
            intent.setAction("android.intent.action.VIEW");
            return intent;
        }
    }

    public zzie(zzhz zzhz, zze zze, zzkj zzkj) {
        this.zzHQ = zzhz;
        this.zzHN = zze;
        this.zzHO = zzkj;
    }

    private static boolean zzd(Map<String, String> map) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close"));
    }

    private static int zze(Map<String, String> map) {
        String str = map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return zzv.zzcL().zzkq();
            }
            if ("l".equalsIgnoreCase(str)) {
                return zzv.zzcL().zzkp();
            }
            if ("c".equalsIgnoreCase(str)) {
                return zzv.zzcL().zzkr();
            }
        }
        return -1;
    }

    private static void zzf(zzqp zzqp, Map<String, String> map) {
        Context context = zzqp.getContext();
        if (TextUtils.isEmpty(map.get("u"))) {
            zzpe.zzbe("Destination url cannot be empty.");
            return;
        }
        try {
            zzqp.zzkV().zza(new zzc(new zza(zzqp).zza(context, map)));
        } catch (ActivityNotFoundException e) {
            zzpe.zzbe(e.getMessage());
        }
    }

    private void zzr(boolean z) {
        if (this.zzHO != null) {
            this.zzHO.zzs(z);
        }
    }

    public void zza(zzqp zzqp, Map<String, String> map) {
        String str = map.get("a");
        if (str == null) {
            zzpe.zzbe("Action missing from an open GMSG.");
        } else if (this.zzHN == null || this.zzHN.zzcb()) {
            zzqq zzkV = zzqp.zzkV();
            if ("expand".equalsIgnoreCase(str)) {
                if (zzqp.zzkZ()) {
                    zzpe.zzbe("Cannot expand WebView that is already expanded.");
                    return;
                }
                zzr(false);
                zzkV.zza(zzd(map), zze(map));
            } else if ("webapp".equalsIgnoreCase(str)) {
                String str2 = map.get("u");
                zzr(false);
                if (str2 != null) {
                    zzkV.zza(zzd(map), zze(map), str2);
                } else {
                    zzkV.zza(zzd(map), zze(map), map.get("html"), map.get("baseurl"));
                }
            } else if ("in_app_purchase".equalsIgnoreCase(str)) {
                String str3 = map.get("product_id");
                String str4 = map.get("report_urls");
                if (this.zzHQ == null) {
                    return;
                }
                if (str4 == null || str4.isEmpty()) {
                    this.zzHQ.zza(str3, new ArrayList());
                } else {
                    this.zzHQ.zza(str3, new ArrayList(Arrays.asList(str4.split(" "))));
                }
            } else if (!"app".equalsIgnoreCase(str) || !ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(map.get("system_browser"))) {
                zzr(true);
                String str5 = map.get("u");
                zzkV.zza(new zzc(map.get("i"), !TextUtils.isEmpty(str5) ? zzv.zzcJ().zza(zzqp, str5) : str5, map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
            } else {
                zzr(true);
                zzf(zzqp, map);
            }
        } else {
            this.zzHN.zzx(map.get("u"));
        }
    }
}
