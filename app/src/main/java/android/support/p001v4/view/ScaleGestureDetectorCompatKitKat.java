package android.support.p001v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.ScaleGestureDetector;

@TargetApi(19)
@RequiresApi(19)
/* renamed from: android.support.v4.view.ScaleGestureDetectorCompatKitKat */
class ScaleGestureDetectorCompatKitKat {
    private ScaleGestureDetectorCompatKitKat() {
    }

    public static void setQuickScaleEnabled(Object obj, boolean z) {
        ((ScaleGestureDetector) obj).setQuickScaleEnabled(z);
    }

    public static boolean isQuickScaleEnabled(Object obj) {
        return ((ScaleGestureDetector) obj).isQuickScaleEnabled();
    }
}
