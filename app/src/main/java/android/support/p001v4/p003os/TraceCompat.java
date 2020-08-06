package android.support.p001v4.p003os;

import android.os.Build;

/* renamed from: android.support.v4.os.TraceCompat */
public final class TraceCompat {
    public static void beginSection(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            TraceJellybeanMR2.beginSection(str);
        }
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            TraceJellybeanMR2.endSection();
        }
    }

    private TraceCompat() {
    }
}
