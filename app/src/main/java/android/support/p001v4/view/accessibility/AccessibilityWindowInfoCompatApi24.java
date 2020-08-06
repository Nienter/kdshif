package android.support.p001v4.view.accessibility;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityWindowInfo;

@TargetApi(24)
@RequiresApi(24)
/* renamed from: android.support.v4.view.accessibility.AccessibilityWindowInfoCompatApi24 */
class AccessibilityWindowInfoCompatApi24 {
    AccessibilityWindowInfoCompatApi24() {
    }

    public static CharSequence getTitle(Object obj) {
        return ((AccessibilityWindowInfo) obj).getTitle();
    }

    public static Object getAnchor(Object obj) {
        return ((AccessibilityWindowInfo) obj).getAnchor();
    }
}
