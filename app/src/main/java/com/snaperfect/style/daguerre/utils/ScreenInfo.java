package com.snaperfect.style.daguerre.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;
import com.snaperfect.style.daguerre.math.CGSize;

/* renamed from: com.snaperfect.style.daguerre.utils.m */
public class ScreenInfo {
    /* renamed from: a */
    public static CGSize m3112a(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point);
        return new CGSize((float) point.x, (float) point.y);
    }
}
