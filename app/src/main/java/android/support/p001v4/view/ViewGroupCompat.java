package android.support.p001v4.view;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: android.support.v4.view.ViewGroupCompat */
public final class ViewGroupCompat {
    static final ViewGroupCompatImpl IMPL;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    /* renamed from: android.support.v4.view.ViewGroupCompat$ViewGroupCompatHCImpl */
    static class ViewGroupCompatHCImpl extends ViewGroupCompatStubImpl {
        ViewGroupCompatHCImpl() {
        }

        public void setMotionEventSplittingEnabled(ViewGroup viewGroup, boolean z) {
            ViewGroupCompatHC.setMotionEventSplittingEnabled(viewGroup, z);
        }
    }

    /* renamed from: android.support.v4.view.ViewGroupCompat$ViewGroupCompatIcsImpl */
    static class ViewGroupCompatIcsImpl extends ViewGroupCompatHCImpl {
        ViewGroupCompatIcsImpl() {
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return ViewGroupCompatIcs.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }

    /* renamed from: android.support.v4.view.ViewGroupCompat$ViewGroupCompatImpl */
    interface ViewGroupCompatImpl {
        int getLayoutMode(ViewGroup viewGroup);

        int getNestedScrollAxes(ViewGroup viewGroup);

        boolean isTransitionGroup(ViewGroup viewGroup);

        boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void setLayoutMode(ViewGroup viewGroup, int i);

        void setMotionEventSplittingEnabled(ViewGroup viewGroup, boolean z);

        void setTransitionGroup(ViewGroup viewGroup, boolean z);
    }

    /* renamed from: android.support.v4.view.ViewGroupCompat$ViewGroupCompatJellybeanMR2Impl */
    static class ViewGroupCompatJellybeanMR2Impl extends ViewGroupCompatIcsImpl {
        ViewGroupCompatJellybeanMR2Impl() {
        }

        public int getLayoutMode(ViewGroup viewGroup) {
            return ViewGroupCompatJellybeanMR2.getLayoutMode(viewGroup);
        }

        public void setLayoutMode(ViewGroup viewGroup, int i) {
            ViewGroupCompatJellybeanMR2.setLayoutMode(viewGroup, i);
        }
    }

    /* renamed from: android.support.v4.view.ViewGroupCompat$ViewGroupCompatLollipopImpl */
    static class ViewGroupCompatLollipopImpl extends ViewGroupCompatJellybeanMR2Impl {
        ViewGroupCompatLollipopImpl() {
        }

        public void setTransitionGroup(ViewGroup viewGroup, boolean z) {
            ViewGroupCompatLollipop.setTransitionGroup(viewGroup, z);
        }

        public boolean isTransitionGroup(ViewGroup viewGroup) {
            return ViewGroupCompatLollipop.isTransitionGroup(viewGroup);
        }

        public int getNestedScrollAxes(ViewGroup viewGroup) {
            return ViewGroupCompatLollipop.getNestedScrollAxes(viewGroup);
        }
    }

    /* renamed from: android.support.v4.view.ViewGroupCompat$ViewGroupCompatStubImpl */
    static class ViewGroupCompatStubImpl implements ViewGroupCompatImpl {
        ViewGroupCompatStubImpl() {
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return true;
        }

        public void setMotionEventSplittingEnabled(ViewGroup viewGroup, boolean z) {
        }

        public int getLayoutMode(ViewGroup viewGroup) {
            return 0;
        }

        public void setLayoutMode(ViewGroup viewGroup, int i) {
        }

        public void setTransitionGroup(ViewGroup viewGroup, boolean z) {
        }

        public boolean isTransitionGroup(ViewGroup viewGroup) {
            return false;
        }

        public int getNestedScrollAxes(ViewGroup viewGroup) {
            if (viewGroup instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewGroup).getNestedScrollAxes();
            }
            return 0;
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            IMPL = new ViewGroupCompatLollipopImpl();
        } else if (i >= 18) {
            IMPL = new ViewGroupCompatJellybeanMR2Impl();
        } else if (i >= 14) {
            IMPL = new ViewGroupCompatIcsImpl();
        } else if (i >= 11) {
            IMPL = new ViewGroupCompatHCImpl();
        } else {
            IMPL = new ViewGroupCompatStubImpl();
        }
    }

    private ViewGroupCompat() {
    }

    public static boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return IMPL.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public static void setMotionEventSplittingEnabled(ViewGroup viewGroup, boolean z) {
        IMPL.setMotionEventSplittingEnabled(viewGroup, z);
    }

    public static int getLayoutMode(ViewGroup viewGroup) {
        return IMPL.getLayoutMode(viewGroup);
    }

    public static void setLayoutMode(ViewGroup viewGroup, int i) {
        IMPL.setLayoutMode(viewGroup, i);
    }

    public static void setTransitionGroup(ViewGroup viewGroup, boolean z) {
        IMPL.setTransitionGroup(viewGroup, z);
    }

    public static boolean isTransitionGroup(ViewGroup viewGroup) {
        return IMPL.isTransitionGroup(viewGroup);
    }

    public static int getNestedScrollAxes(ViewGroup viewGroup) {
        return IMPL.getNestedScrollAxes(viewGroup);
    }
}
