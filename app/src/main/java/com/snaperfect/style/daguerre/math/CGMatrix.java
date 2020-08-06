package com.snaperfect.style.daguerre.math;

import android.graphics.Matrix;

/* renamed from: com.snaperfect.style.daguerre.math.b */
public class CGMatrix {
    /* renamed from: a */
    public static float m2897a(Matrix matrix) {
        return matrix.mapRadius(1.0f);
    }

    /* renamed from: b */
    public static float m2898b(Matrix matrix) {
        float[] fArr = {0.0f, 0.0f, 1.0f, 0.0f};
        matrix.mapPoints(fArr);
        return (float) Math.atan2((double) (fArr[1] - fArr[3]), (double) (fArr[2] - fArr[0]));
    }

    /* renamed from: c */
    public static float[] m2899c(Matrix matrix) {
        float[] fArr = {0.0f, 0.0f};
        matrix.mapPoints(fArr);
        return fArr;
    }
}
