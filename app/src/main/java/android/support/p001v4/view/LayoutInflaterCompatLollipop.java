package android.support.p001v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.p001v4.view.LayoutInflaterCompatHC;
import android.view.LayoutInflater;

@TargetApi(21)
@RequiresApi(21)
/* renamed from: android.support.v4.view.LayoutInflaterCompatLollipop */
class LayoutInflaterCompatLollipop {
    LayoutInflaterCompatLollipop() {
    }

    static void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
        layoutInflater.setFactory2(layoutInflaterFactory != null ? new LayoutInflaterCompatHC.FactoryWrapperHC(layoutInflaterFactory) : null);
    }
}
