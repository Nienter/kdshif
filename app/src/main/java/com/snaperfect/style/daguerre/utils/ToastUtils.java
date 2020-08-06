package com.snaperfect.style.daguerre.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.InflateException;
import android.widget.Toast;
import java.lang.ref.WeakReference;

/* renamed from: com.snaperfect.style.daguerre.utils.o */
public class ToastUtils {

    /* renamed from: a */
    protected static Toast f2273a = null;

    /* renamed from: b */
    protected static Toast f2274b = null;

    /* renamed from: c */
    protected static Toast f2275c = null;

    /* renamed from: d */
    private static String f2276d;

    /* renamed from: e */
    private static long f2277e = 0;

    /* renamed from: f */
    private static long f2278f = 0;

    /* renamed from: a */
    public static void m3122a(Context context, int i) {
        if (context != null) {
            Resources resources = context.getResources();
            if (resources != null) {
                m3123a((WeakReference<Context>) new WeakReference(context), resources.getString(i));
            }
        }
    }

    /* renamed from: a */
    public static void m3123a(WeakReference<Context> weakReference, String str) {
        if (f2273a == null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                try {
                    f2273a = Toast.makeText(context.getApplicationContext(), str, 0);
                    f2273a.show();
                    f2277e = System.currentTimeMillis();
                } catch (InflateException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            f2278f = System.currentTimeMillis();
            if (str != f2276d) {
                f2276d = str;
                f2273a.setText(str);
                f2273a.show();
            } else if (f2278f - f2277e > 0) {
                f2273a.show();
            }
        }
        f2277e = f2278f;
    }
}
