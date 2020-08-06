package com.snaperfect.style.daguerre.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.internal.AnalyticsEvents;
import com.snaperfect.inframe1.R;
import java.util.HashMap;

/* renamed from: com.snaperfect.style.daguerre.utils.n */
public class StoreUtils {

    /* renamed from: a */
    private static final HashMap<String, Object> f2271a = new HashMap<>();

    /* renamed from: b */
    private static String f2272b;

    /* renamed from: a */
    public static void m3115a(Context context) {
        f2272b = context.getString(R.string.app_name);
        SharedPreferences sharedPreferences = context.getSharedPreferences(f2272b, 0);
        if (sharedPreferences.contains(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE)) {
            f2271a.put(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, Integer.valueOf(sharedPreferences.getInt(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, 0)));
        }
        if (sharedPreferences.contains("blurRadius")) {
            f2271a.put("blurRadius", Integer.valueOf(sharedPreferences.getInt("blurRadius", 0)));
        }
        if (sharedPreferences.contains("saveCount")) {
            f2271a.put("saveCount", Integer.valueOf(sharedPreferences.getInt("saveCount", 0)));
        }
    }

    /* renamed from: a */
    public static int m3114a(String str, int i) {
        if (f2271a.containsKey(str)) {
            return ((Integer) f2271a.get(str)).intValue();
        }
        return i;
    }

    /* renamed from: a */
    public static void m3117a(Context context, String str, int i) {
        f2271a.put(str, Integer.valueOf(i));
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(f2272b, 0).edit();
            edit.putInt(str, i);
            edit.apply();
        }
    }

    /* renamed from: a */
    public static int m3113a() {
        return m3114a(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, 0);
    }

    /* renamed from: a */
    public static void m3116a(Context context, int i) {
        m3117a(context, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, i);
    }

    /* renamed from: b */
    public static int m3118b() {
        return m3114a("blurRadius", 10);
    }

    /* renamed from: b */
    public static void m3119b(Context context, int i) {
        m3117a(context, "blurRadius", i);
    }

    /* renamed from: c */
    public static int m3120c() {
        return m3114a("saveCount", 0);
    }

    /* renamed from: c */
    public static void m3121c(Context context, int i) {
        m3117a(context, "saveCount", i);
    }
}
