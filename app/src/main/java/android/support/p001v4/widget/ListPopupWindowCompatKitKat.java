package android.support.p001v4.widget;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ListPopupWindow;

@TargetApi(19)
@RequiresApi(19)
/* renamed from: android.support.v4.widget.ListPopupWindowCompatKitKat */
class ListPopupWindowCompatKitKat {
    ListPopupWindowCompatKitKat() {
    }

    public static View.OnTouchListener createDragToOpenListener(Object obj, View view) {
        return ((ListPopupWindow) obj).createDragToOpenListener(view);
    }
}
