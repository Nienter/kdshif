package com.p028a.p029a.p030a;

import android.content.Context;
import android.text.TextUtils;
import com.p028a.p029a.p030a.UMStoreManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.a.a.a.af */
public class ViewPageTracker {

    /* renamed from: b */
    private static JSONObject f1194b = new JSONObject();

    /* renamed from: a */
    private final Map<String, Long> f1195a = new HashMap();

    /* renamed from: a */
    public void mo9102a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.f1195a) {
                this.f1195a.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    /* renamed from: b */
    public void mo9103b(String str) {
        Long remove;
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.f1195a) {
                remove = this.f1195a.remove(str);
            }
            if (remove != null) {
                long currentTimeMillis = System.currentTimeMillis() - remove.longValue();
                synchronized (f1194b) {
                    try {
                        f1194b = new JSONObject();
                        f1194b.put("page_name", str);
                        f1194b.put("duration", currentTimeMillis);
                    } catch (Throwable th) {
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public void mo9101a() {
        long j;
        String str;
        String str2 = null;
        long j2 = 0;
        synchronized (this.f1195a) {
            for (Map.Entry next : this.f1195a.entrySet()) {
                if (((Long) next.getValue()).longValue() > j2) {
                    long longValue = ((Long) next.getValue()).longValue();
                    str = (String) next.getKey();
                    j = longValue;
                } else {
                    j = j2;
                    str = str2;
                }
                str2 = str;
                j2 = j;
            }
        }
        if (str2 != null) {
            mo9103b(str2);
        }
    }

    /* renamed from: a */
    public static void m1373a(Context context) {
        if (context != null) {
            try {
                synchronized (f1194b) {
                    if (f1194b.length() > 0) {
                        UMStoreManager.m2242a(context).mo9559a(SessionTracker.m1349a(), f1194b, UMStoreManager.C0636a.PAGE);
                        f1194b = new JSONObject();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
