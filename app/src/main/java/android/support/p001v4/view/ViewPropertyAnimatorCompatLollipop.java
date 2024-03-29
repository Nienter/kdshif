package android.support.p001v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;

@TargetApi(21)
@RequiresApi(21)
/* renamed from: android.support.v4.view.ViewPropertyAnimatorCompatLollipop */
class ViewPropertyAnimatorCompatLollipop {
    ViewPropertyAnimatorCompatLollipop() {
    }

    public static void translationZ(View view, float f) {
        view.animate().translationZ(f);
    }

    public static void translationZBy(View view, float f) {
        view.animate().translationZBy(f);
    }

    /* renamed from: z */
    public static void m80z(View view, float f) {
        view.animate().z(f);
    }

    public static void zBy(View view, float f) {
        view.animate().zBy(f);
    }
}
