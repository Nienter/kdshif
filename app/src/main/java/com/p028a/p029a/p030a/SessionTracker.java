package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.p028a.p029a.AnalyticsConfig;
import com.p028a.p029a.p030a.UMStoreManager;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* renamed from: com.a.a.a.ad */
public class SessionTracker {

    /* renamed from: c */
    private static String f1182c = null;

    /* renamed from: d */
    private static Context f1183d = null;

    /* renamed from: a */
    private final String f1184a = "a_start_time";

    /* renamed from: b */
    private final String f1185b = "a_end_time";

    /* renamed from: a */
    public boolean mo9083a(Context context) {
        SharedPreferences a = PreferenceWrapper.m1333a(context);
        String string = a.getString("session_id", null);
        if (string == null) {
            return false;
        }
        long j = a.getLong("session_start_time", 0);
        long j2 = a.getLong("session_end_time", 0);
        if (j2 == 0 || Math.abs(j2 - j) > 86400000) {
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__ii", string);
            jSONObject.put("__e", j);
            jSONObject.put("__f", j2);
            double[] a2 = AnalyticsConfig.m1312a();
            if (a2 != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("lat", a2[0]);
                jSONObject2.put("lng", a2[1]);
                jSONObject2.put("ts", System.currentTimeMillis());
                jSONObject.put("__d", jSONObject2);
            }
            Class<?> cls = Class.forName("android.net.TrafficStats");
            Method method = cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
            Method method2 = cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
            int i = context.getApplicationInfo().uid;
            if (i == -1) {
                return false;
            }
            long longValue = ((Long) method.invoke(null, new Object[]{Integer.valueOf(i)})).longValue();
            long longValue2 = ((Long) method2.invoke(null, new Object[]{Integer.valueOf(i)})).longValue();
            if (longValue > 0 && longValue2 > 0) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("download_traffic", longValue);
                jSONObject3.put("upload_traffic", longValue2);
                jSONObject.put("__c", jSONObject3);
            }
            UMStoreManager.m2242a(context).mo9559a(string, jSONObject, UMStoreManager.C0636a.NEWSESSION);
            ViewPageTracker.m1373a(f1183d);
            AutoViewPageTracker.m2334b(f1183d);
            m1351a(a);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: a */
    private void m1351a(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove("session_start_time");
        edit.remove("session_end_time");
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.commit();
    }

    /* renamed from: b */
    public String mo9084b(Context context) {
        String c = DeviceConfig.m1796c(context);
        String a = AnalyticsConfig.m1311a(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (a == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(currentTimeMillis).append(a).append(c);
        f1182c = HelperUtils.m1825a(sb.toString());
        return f1182c;
    }

    /* renamed from: c */
    public void mo9085c(Context context) {
        f1183d = context;
        SharedPreferences a = PreferenceWrapper.m1333a(context);
        if (a != null) {
            SharedPreferences.Editor edit = a.edit();
            int i = a.getInt("versioncode", 0);
            int parseInt = Integer.parseInt(DeviceConfig.m1787a(f1183d));
            if (i != 0 && parseInt != i) {
                try {
                    edit.putInt("vers_code", i);
                    edit.putString("vers_name", a.getString("versionname", ""));
                    edit.commit();
                } catch (Throwable th) {
                }
                if (m1353g(context) == null) {
                    m1350a(context, a);
                }
                mo9087e(f1183d);
                CacheService.m2369b(f1183d).mo9622b();
                mo9088f(f1183d);
                CacheService.m2369b(f1183d).mo9618a();
            } else if (m1352b(a)) {
                MLog.m1842b("Start new session: " + m1350a(context, a));
            } else {
                String string = a.getString("session_id", null);
                edit.putLong("a_start_time", System.currentTimeMillis());
                edit.putLong("a_end_time", 0);
                edit.commit();
                MLog.m1842b("Extend current session: " + string);
            }
        }
    }

    /* renamed from: d */
    public void mo9086d(Context context) {
        SharedPreferences a = PreferenceWrapper.m1333a(context);
        if (a != null) {
            if (a.getLong("a_start_time", 0) != 0 || !AnalyticsConfig.f1160e) {
                long currentTimeMillis = System.currentTimeMillis();
                SharedPreferences.Editor edit = a.edit();
                edit.putLong("a_start_time", 0);
                edit.putLong("a_end_time", currentTimeMillis);
                edit.putLong("session_end_time", currentTimeMillis);
                edit.commit();
                return;
            }
            MLog.m1844c("onPause called before onResume");
        }
    }

    /* renamed from: b */
    private boolean m1352b(SharedPreferences sharedPreferences) {
        long j = sharedPreferences.getLong("a_start_time", 0);
        long j2 = sharedPreferences.getLong("a_end_time", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (j != 0 && currentTimeMillis - j < AnalyticsConfig.f1162g) {
            MLog.m1844c("onResume called before onPause");
            return false;
        } else if (currentTimeMillis - j2 <= AnalyticsConfig.f1162g) {
            return false;
        } else {
            String a = m1349a();
            if (!TextUtils.isEmpty(a)) {
                long j3 = sharedPreferences.getLong("session_end_time", 0);
                if (j3 == 0) {
                    j3 = System.currentTimeMillis();
                }
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("__f", j3);
                    UMStoreManager.m2242a(f1183d).mo9559a(a, jSONObject, UMStoreManager.C0636a.END);
                } catch (Throwable th) {
                }
            }
            return true;
        }
    }

    /* renamed from: a */
    private String m1350a(Context context, SharedPreferences sharedPreferences) {
        CacheService b = CacheService.m2369b(context);
        String b2 = mo9084b(context);
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", currentTimeMillis);
            UMStoreManager.m2242a(f1183d).mo9559a(b2, jSONObject, UMStoreManager.C0636a.BEGIN);
        } catch (Throwable th) {
        }
        mo9083a(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("session_id", b2);
        edit.putLong("session_start_time", System.currentTimeMillis());
        edit.putLong("session_end_time", 0);
        edit.putLong("a_start_time", currentTimeMillis);
        edit.putLong("a_end_time", 0);
        edit.putInt("versioncode", Integer.parseInt(DeviceConfig.m1787a(context)));
        edit.putString("versionname", DeviceConfig.m1793b(context));
        edit.commit();
        b.mo9620a((Object) true);
        return b2;
    }

    /* renamed from: e */
    public boolean mo9087e(Context context) {
        boolean z = false;
        SharedPreferences a = PreferenceWrapper.m1333a(context);
        if (!(a == null || a.getString("session_id", null) == null)) {
            long j = a.getLong("a_start_time", 0);
            long j2 = a.getLong("a_end_time", 0);
            if (j > 0 && j2 == 0) {
                z = true;
                mo9086d(context);
            }
            long j3 = a.getLong("session_end_time", 0);
            try {
                JSONObject jSONObject = new JSONObject();
                if (j3 == 0) {
                    j3 = System.currentTimeMillis();
                }
                jSONObject.put("__f", j3);
                UMStoreManager.m2242a(f1183d).mo9559a(m1349a(), jSONObject, UMStoreManager.C0636a.END);
            } catch (Throwable th) {
            }
            mo9083a(context);
        }
        return z;
    }

    /* renamed from: f */
    public void mo9088f(Context context) {
        SharedPreferences a = PreferenceWrapper.m1333a(context);
        if (a != null) {
            String b = mo9084b(context);
            SharedPreferences.Editor edit = a.edit();
            long currentTimeMillis = System.currentTimeMillis();
            edit.putString("session_id", b);
            edit.putLong("session_start_time", System.currentTimeMillis());
            edit.putLong("session_end_time", 0);
            edit.putLong("a_start_time", currentTimeMillis);
            edit.putLong("a_end_time", 0);
            edit.putInt("versioncode", Integer.parseInt(DeviceConfig.m1787a(context)));
            edit.putString("versionname", DeviceConfig.m1793b(context));
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("__e", currentTimeMillis);
                UMStoreManager.m2242a(f1183d).mo9559a(b, jSONObject, UMStoreManager.C0636a.BEGIN);
            } catch (Throwable th) {
            }
            edit.commit();
        }
    }

    /* renamed from: g */
    public static String m1353g(Context context) {
        if (f1182c == null) {
            f1182c = PreferenceWrapper.m1333a(context).getString("session_id", null);
        }
        return f1182c;
    }

    /* renamed from: a */
    public static String m1349a() {
        return m1353g(f1183d);
    }
}
