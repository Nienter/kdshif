package android.support.p001v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;

@TargetApi(14)
@RequiresApi(14)
/* renamed from: android.support.v4.view.MotionEventCompatICS */
class MotionEventCompatICS {
    MotionEventCompatICS() {
    }

    public static int getButtonState(MotionEvent motionEvent) {
        return motionEvent.getButtonState();
    }
}
