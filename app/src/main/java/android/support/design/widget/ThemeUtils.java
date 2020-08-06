package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p004v7.appcompat.C0309R;

class ThemeUtils {
    private static final int[] APPCOMPAT_CHECK_ATTRS = {C0309R.attr.colorPrimary};

    ThemeUtils() {
    }

    static void checkAppCompatTheme(Context context) {
        boolean z = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        if (!obtainStyledAttributes.hasValue(0)) {
            z = true;
        }
        obtainStyledAttributes.recycle();
        if (z) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
        }
    }
}
