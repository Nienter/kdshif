package android.support.p001v4.view.accessibility;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;

@TargetApi(24)
@RequiresApi(24)
/* renamed from: android.support.v4.view.accessibility.AccessibilityNodeInfoCompatApi24 */
class AccessibilityNodeInfoCompatApi24 {
    AccessibilityNodeInfoCompatApi24() {
    }

    public static Object getActionSetProgress() {
        return AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
    }

    public static int getDrawingOrder(Object obj) {
        return ((AccessibilityNodeInfo) obj).getDrawingOrder();
    }

    public static void setDrawingOrder(Object obj, int i) {
        ((AccessibilityNodeInfo) obj).setDrawingOrder(i);
    }

    public static boolean isImportantForAccessibility(Object obj) {
        return ((AccessibilityNodeInfo) obj).isImportantForAccessibility();
    }

    public static void setImportantForAccessibility(Object obj, boolean z) {
        ((AccessibilityNodeInfo) obj).setImportantForAccessibility(z);
    }
}
