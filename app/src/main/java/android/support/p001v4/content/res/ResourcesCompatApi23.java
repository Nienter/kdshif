package android.support.p001v4.content.res;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.RequiresApi;

@TargetApi(23)
@RequiresApi(23)
/* renamed from: android.support.v4.content.res.ResourcesCompatApi23 */
class ResourcesCompatApi23 {
    ResourcesCompatApi23() {
    }

    public static int getColor(Resources resources, int i, Resources.Theme theme) {
        return resources.getColor(i, theme);
    }

    public static ColorStateList getColorStateList(Resources resources, int i, Resources.Theme theme) {
        return resources.getColorStateList(i, theme);
    }
}
