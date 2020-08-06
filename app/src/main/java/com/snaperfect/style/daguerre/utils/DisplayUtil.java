package com.snaperfect.style.daguerre.utils;

import android.content.Context;
import com.snaperfect.style.daguerre.application.DaguerreApp;

/* renamed from: com.snaperfect.style.daguerre.utils.e */
public class DisplayUtil {
    /* renamed from: a */
    public static float m3087a(float f) {
        return DaguerreApp.f1910a.density * f;
    }

    /* renamed from: a */
    public static int m3088a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    /* renamed from: b */
    public static int m3089b(float f) {
        return (int) ((DaguerreApp.f1910a.scaledDensity * f) + 0.5f);
    }
}
