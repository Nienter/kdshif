package android.support.p001v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.ViewConfiguration;

@TargetApi(14)
@RequiresApi(14)
/* renamed from: android.support.v4.view.ViewConfigurationCompatICS */
class ViewConfigurationCompatICS {
    ViewConfigurationCompatICS() {
    }

    static boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
        return viewConfiguration.hasPermanentMenuKey();
    }
}
