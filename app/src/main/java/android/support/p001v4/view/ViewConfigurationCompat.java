package android.support.p001v4.view;

import android.os.Build;
import android.view.ViewConfiguration;

/* renamed from: android.support.v4.view.ViewConfigurationCompat */
public final class ViewConfigurationCompat {
    static final ViewConfigurationVersionImpl IMPL;

    /* renamed from: android.support.v4.view.ViewConfigurationCompat$BaseViewConfigurationVersionImpl */
    static class BaseViewConfigurationVersionImpl implements ViewConfigurationVersionImpl {
        BaseViewConfigurationVersionImpl() {
        }

        public boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
            return true;
        }
    }

    /* renamed from: android.support.v4.view.ViewConfigurationCompat$HoneycombViewConfigurationVersionImpl */
    static class HoneycombViewConfigurationVersionImpl extends BaseViewConfigurationVersionImpl {
        HoneycombViewConfigurationVersionImpl() {
        }

        public boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
            return false;
        }
    }

    /* renamed from: android.support.v4.view.ViewConfigurationCompat$IcsViewConfigurationVersionImpl */
    static class IcsViewConfigurationVersionImpl extends HoneycombViewConfigurationVersionImpl {
        IcsViewConfigurationVersionImpl() {
        }

        public boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
            return ViewConfigurationCompatICS.hasPermanentMenuKey(viewConfiguration);
        }
    }

    /* renamed from: android.support.v4.view.ViewConfigurationCompat$ViewConfigurationVersionImpl */
    interface ViewConfigurationVersionImpl {
        boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration);
    }

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            IMPL = new IcsViewConfigurationVersionImpl();
        } else if (Build.VERSION.SDK_INT >= 11) {
            IMPL = new HoneycombViewConfigurationVersionImpl();
        } else {
            IMPL = new BaseViewConfigurationVersionImpl();
        }
    }

    @Deprecated
    public static int getScaledPagingTouchSlop(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledPagingTouchSlop();
    }

    public static boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
        return IMPL.hasPermanentMenuKey(viewConfiguration);
    }

    private ViewConfigurationCompat() {
    }
}
