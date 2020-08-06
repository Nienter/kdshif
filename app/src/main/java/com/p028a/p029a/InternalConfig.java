package com.p028a.p029a;

import android.content.Context;
import android.text.TextUtils;
import com.p028a.p029a.p030a.StoreHelper;

/* renamed from: com.a.a.e */
public class InternalConfig {

    /* renamed from: a */
    private static String[] f1713a = new String[2];

    /* renamed from: a */
    public static String[] m2430a(Context context) {
        if (!TextUtils.isEmpty(f1713a[0]) && !TextUtils.isEmpty(f1713a[1])) {
            return f1713a;
        }
        if (context != null) {
            String[] a = StoreHelper.m1888a(context).mo9389a();
            if (a != null) {
                f1713a[0] = a[0];
                f1713a[1] = a[1];
                return f1713a;
            }
        }
        return null;
    }
}
