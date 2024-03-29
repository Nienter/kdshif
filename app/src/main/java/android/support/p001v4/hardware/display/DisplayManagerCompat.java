package android.support.p001v4.hardware.display;

import android.content.Context;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

/* renamed from: android.support.v4.hardware.display.DisplayManagerCompat */
public abstract class DisplayManagerCompat {
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap<Context, DisplayManagerCompat> sInstances = new WeakHashMap<>();

    /* renamed from: android.support.v4.hardware.display.DisplayManagerCompat$JellybeanMr1Impl */
    private static class JellybeanMr1Impl extends DisplayManagerCompat {
        private final Object mDisplayManagerObj;

        public JellybeanMr1Impl(Context context) {
            this.mDisplayManagerObj = DisplayManagerJellybeanMr1.getDisplayManager(context);
        }

        public Display getDisplay(int i) {
            return DisplayManagerJellybeanMr1.getDisplay(this.mDisplayManagerObj, i);
        }

        public Display[] getDisplays() {
            return DisplayManagerJellybeanMr1.getDisplays(this.mDisplayManagerObj);
        }

        public Display[] getDisplays(String str) {
            return DisplayManagerJellybeanMr1.getDisplays(this.mDisplayManagerObj, str);
        }
    }

    /* renamed from: android.support.v4.hardware.display.DisplayManagerCompat$LegacyImpl */
    private static class LegacyImpl extends DisplayManagerCompat {
        private final WindowManager mWindowManager;

        public LegacyImpl(Context context) {
            this.mWindowManager = (WindowManager) context.getSystemService("window");
        }

        public Display getDisplay(int i) {
            Display defaultDisplay = this.mWindowManager.getDefaultDisplay();
            if (defaultDisplay.getDisplayId() == i) {
                return defaultDisplay;
            }
            return null;
        }

        public Display[] getDisplays() {
            return new Display[]{this.mWindowManager.getDefaultDisplay()};
        }

        public Display[] getDisplays(String str) {
            return str == null ? getDisplays() : new Display[0];
        }
    }

    public abstract Display getDisplay(int i);

    public abstract Display[] getDisplays();

    public abstract Display[] getDisplays(String str);

    DisplayManagerCompat() {
    }

    public static DisplayManagerCompat getInstance(Context context) {
        DisplayManagerCompat displayManagerCompat;
        synchronized (sInstances) {
            displayManagerCompat = sInstances.get(context);
            if (displayManagerCompat == null) {
                if (Build.VERSION.SDK_INT >= 17) {
                    displayManagerCompat = new JellybeanMr1Impl(context);
                } else {
                    displayManagerCompat = new LegacyImpl(context);
                }
                sInstances.put(context, displayManagerCompat);
            }
        }
        return displayManagerCompat;
    }
}
