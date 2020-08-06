package com.snaperfect.style.daguerre.math;

/* renamed from: com.snaperfect.style.daguerre.math.a */
public class CGFloat {
    /* renamed from: a */
    public static final boolean m2896a(float f, float f2, float f3) {
        return Math.abs(f - f2) < f3;
    }

    /* renamed from: a */
    public static final boolean m2895a(float f, float f2) {
        return m2896a(f, f2, 0.001f);
    }

    /* renamed from: a */
    public static float m2894a(float f) {
        float f2 = 1.5707964f * ((float) (f < 0.0f ? -1 : 1));
        float f3 = f - (((float) ((int) (f / f2))) * f2);
        float f4 = 0.0f - f3;
        float f5 = f2 - f3;
        return Math.abs(f4) < Math.abs(f5) ? f4 : f5;
    }
}
