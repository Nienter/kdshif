package com.p028a.p029a.p030a;

import android.content.Context;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.a.a.a.t */
public class EventTracker {

    /* renamed from: a */
    private final int f1667a = 128;

    /* renamed from: b */
    private final int f1668b = 256;

    /* renamed from: c */
    private final int f1669c = 10;

    /* renamed from: d */
    private Context f1670d;

    /* renamed from: e */
    private CacheService f1671e = null;

    /* renamed from: f */
    private CacheImpl f1672f = null;

    /* renamed from: g */
    private JSONObject f1673g = null;

    /* renamed from: h */
    private CacheService f1674h;

    public EventTracker(Context context) {
        if (context == null) {
            try {
                MLog.m1844c("Context is null, can't track event");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.f1674h = CacheService.m2369b(context);
        this.f1670d = context;
        this.f1671e = CacheService.m2369b(this.f1670d);
        this.f1672f = this.f1671e.mo9626a(this.f1670d);
        if (this.f1673g == null) {
            m2380a(context);
        }
    }

    /* renamed from: a */
    public void mo9630a(String str, Map<String, Object> map, long j) {
        try {
            if (m2383a(str) && m2384a(map)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                if (j > 0) {
                    jSONObject.put("du", j);
                }
                jSONObject.put("__t", 2049);
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Map.Entry next = it.next();
                    if (!"$st_fl".equals(next.getKey()) && !"dplus_st".equals(next.getKey()) && !"du".equals(next.getKey()) && !"id".equals(next.getKey()) && !"ts".equals(next.getKey())) {
                        Object value = next.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put((String) next.getKey(), value);
                        }
                    }
                }
                jSONObject.put("__i", SessionTracker.m1353g(this.f1670d));
                jSONObject.put("_umpname", AutoViewPageTracker.f1628a);
                this.f1674h.mo9620a((Object) jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo9629a(String str, String str2, long j, int i) {
        try {
            if (m2383a(str) && m2386b(str2)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                if (j > 0) {
                    jSONObject.put("du", j);
                }
                jSONObject.put("__t", 2049);
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(str, str2);
                jSONObject.put("__i", SessionTracker.m1353g(this.f1670d));
                jSONObject.put("_umpname", AutoViewPageTracker.f1628a);
                this.f1674h.mo9620a((Object) jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private void m2380a(Context context) {
        try {
            String string = PreferenceWrapper.m1333a(context).getString("fs_lc_tl", null);
            if (!TextUtils.isEmpty(string)) {
                this.f1673g = new JSONObject(string);
            }
            m2379a();
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private void m2379a() {
        int i = 0;
        try {
            if (!TextUtils.isEmpty(this.f1672f.f1635a)) {
                String[] split = this.f1672f.f1635a.split("!");
                JSONObject jSONObject = new JSONObject();
                if (this.f1673g != null) {
                    for (String a : split) {
                        String a2 = HelperUtils.m1826a(a, 128);
                        if (this.f1673g.has(a2)) {
                            jSONObject.put(a2, this.f1673g.get(a2));
                        }
                    }
                }
                this.f1673g = new JSONObject();
                if (split.length >= 10) {
                    while (i < 10) {
                        m2381a(split[i], jSONObject);
                        i++;
                    }
                } else {
                    while (i < split.length) {
                        m2381a(split[i], jSONObject);
                        i++;
                    }
                }
                m2385b(this.f1670d);
                this.f1672f.f1635a = null;
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private void m2381a(String str, JSONObject jSONObject) {
        String a = HelperUtils.m1826a(str, 128);
        if (jSONObject.has(a)) {
            m2382a(a, ((Boolean) jSONObject.get(a)).booleanValue());
        } else {
            m2382a(a, false);
        }
    }

    /* renamed from: a */
    private void m2382a(String str, boolean z) {
        try {
            if (!"$st_fl".equals(str) && !"dplus_st".equals(str) && !"du".equals(str) && !"id".equals(str) && !"ts".equals(str) && !this.f1673g.has(str)) {
                this.f1673g.put(str, z);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    private void m2385b(Context context) {
        try {
            if (this.f1673g != null) {
                PreferenceWrapper.m1333a(this.f1670d).edit().putString("fs_lc_tl", this.f1673g.toString()).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private boolean m2383a(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length <= 128) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        MLog.m1844c("Event id is empty or too long in tracking Event");
        return false;
    }

    /* renamed from: b */
    private boolean m2386b(String str) {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 256) {
                return true;
            }
            MLog.m1844c("Event label or value is empty or too long in tracking Event");
            return false;
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private boolean m2384a(Map<String, Object> map) {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    for (Map.Entry next : map.entrySet()) {
                        if (!m2383a((String) next.getKey())) {
                            return false;
                        }
                        if (next.getValue() == null) {
                            return false;
                        }
                        if ((next.getValue() instanceof String) && !m2386b(next.getValue().toString())) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Exception e) {
            }
        }
        MLog.m1844c("map is null or empty in onEvent");
        return false;
    }
}
