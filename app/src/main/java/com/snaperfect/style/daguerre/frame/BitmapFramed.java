package com.snaperfect.style.daguerre.frame;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

/* renamed from: com.snaperfect.style.daguerre.frame.b */
public class BitmapFramed {
    /* renamed from: a */
    public static GradientDrawable m2809a(int i, int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, i2});
        gradientDrawable.setCornerRadius(0.0f);
        return gradientDrawable;
    }

    /* renamed from: a */
    public static void m2810a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
