package android.support.transition;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public class TransitionManager {
    private static TransitionManagerStaticsImpl sImpl;
    private TransitionManagerImpl mImpl;

    static {
        if (Build.VERSION.SDK_INT < 19) {
            sImpl = new TransitionManagerStaticsIcs();
        } else {
            sImpl = new TransitionManagerStaticsKitKat();
        }
    }

    public TransitionManager() {
        if (Build.VERSION.SDK_INT < 19) {
            this.mImpl = new TransitionManagerIcs();
        } else {
            this.mImpl = new TransitionManagerKitKat();
        }
    }

    /* renamed from: go */
    public static void m54go(@NonNull Scene scene) {
        sImpl.mo1390go(scene.mImpl);
    }

    /* renamed from: go */
    public static void m55go(@NonNull Scene scene, @Nullable Transition transition) {
        sImpl.mo1391go(scene.mImpl, transition == null ? null : transition.mImpl);
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup) {
        sImpl.beginDelayedTransition(viewGroup);
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup, @Nullable Transition transition) {
        sImpl.beginDelayedTransition(viewGroup, transition == null ? null : transition.mImpl);
    }

    public void setTransition(@NonNull Scene scene, @Nullable Transition transition) {
        this.mImpl.setTransition(scene.mImpl, transition == null ? null : transition.mImpl);
    }

    public void setTransition(@NonNull Scene scene, @NonNull Scene scene2, @Nullable Transition transition) {
        this.mImpl.setTransition(scene.mImpl, scene2.mImpl, transition == null ? null : transition.mImpl);
    }

    public void transitionTo(@NonNull Scene scene) {
        this.mImpl.transitionTo(scene.mImpl);
    }
}
