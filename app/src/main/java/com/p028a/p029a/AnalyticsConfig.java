package com.p028a.p029a;

import android.content.Context;
import android.text.TextUtils;
import com.p028a.p029a.p030a.DeviceConfig;
import com.p028a.p029a.p030a.StoreHelper;

/* renamed from: com.a.a.a */
public class AnalyticsConfig {

    /* renamed from: a */
    public static String f1156a = null;

    /* renamed from: b */
    public static String f1157b = null;

    /* renamed from: c */
    public static String f1158c = "";

    /* renamed from: d */
    public static String f1159d = "";

    /* renamed from: e */
    public static boolean f1160e = true;

    /* renamed from: f */
    public static boolean f1161f = true;

    /* renamed from: g */
    public static long f1162g = 30000;

    /* renamed from: h */
    public static boolean f1163h = false;

    /* renamed from: i */
    public static int f1164i;

    /* renamed from: j */
    static double[] f1165j = null;

    /* renamed from: k */
    private static String f1166k = null;

    /* renamed from: l */
    private static String f1167l = null;

    /* renamed from: m */
    private static String f1168m = null;

    /* renamed from: n */
    private static int f1169n = 0;

    /* renamed from: a */
    public static String m1311a(Context context) {
        if (TextUtils.isEmpty(f1166k)) {
            f1166k = DeviceConfig.m1809m(context);
            if (TextUtils.isEmpty(f1166k)) {
                f1166k = StoreHelper.m1888a(context).mo9390b();
            }
        }
        return f1166k;
    }

    /* renamed from: b */
    public static String m1313b(Context context) {
        if (TextUtils.isEmpty(f1167l)) {
            f1167l = DeviceConfig.m1812p(context);
        }
        return f1167l;
    }

    /* renamed from: a */
    public static double[] m1312a() {
        return f1165j;
    }

    /* renamed from: c */
    public static String m1314c(Context context) {
        if (TextUtils.isEmpty(f1168m)) {
            f1168m = StoreHelper.m1888a(context).mo9391c();
        }
        return f1168m;
    }

    /* renamed from: d */
    public static int m1315d(Context context) {
        if (f1169n == 0) {
            f1169n = StoreHelper.m1888a(context).mo9392d();
        }
        return f1169n;
    }

    /* renamed from: e */
    public static String m1316e(Context context) {
        return "6.1.2";
    }
}
