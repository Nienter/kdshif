package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.TransitionManager;
import android.view.ViewGroup;

@TargetApi(19)
@RequiresApi(19)
class TransitionManagerStaticsKitKat extends TransitionManagerStaticsImpl {
    TransitionManagerStaticsKitKat() {
    }

    /* renamed from: go */
    public void mo1390go(SceneImpl sceneImpl) {
        TransitionManager.go(((SceneWrapper) sceneImpl).mScene);
    }

    /* renamed from: go */
    public void mo1391go(SceneImpl sceneImpl, TransitionImpl transitionImpl) {
        TransitionManager.go(((SceneWrapper) sceneImpl).mScene, transitionImpl == null ? null : ((TransitionKitKat) transitionImpl).mTransition);
    }

    public void beginDelayedTransition(ViewGroup viewGroup) {
        TransitionManager.beginDelayedTransition(viewGroup);
    }

    public void beginDelayedTransition(ViewGroup viewGroup, TransitionImpl transitionImpl) {
        TransitionManager.beginDelayedTransition(viewGroup, transitionImpl == null ? null : ((TransitionKitKat) transitionImpl).mTransition);
    }
}
