package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;

@TargetApi(14)
@RequiresApi(14)
class TransitionManagerStaticsIcs extends TransitionManagerStaticsImpl {
    TransitionManagerStaticsIcs() {
    }

    /* renamed from: go */
    public void mo1390go(SceneImpl sceneImpl) {
        TransitionManagerPort.m56go(((SceneIcs) sceneImpl).mScene);
    }

    /* renamed from: go */
    public void mo1391go(SceneImpl sceneImpl, TransitionImpl transitionImpl) {
        TransitionManagerPort.m57go(((SceneIcs) sceneImpl).mScene, transitionImpl == null ? null : ((TransitionIcs) transitionImpl).mTransition);
    }

    public void beginDelayedTransition(ViewGroup viewGroup) {
        TransitionManagerPort.beginDelayedTransition(viewGroup);
    }

    public void beginDelayedTransition(ViewGroup viewGroup, TransitionImpl transitionImpl) {
        TransitionManagerPort.beginDelayedTransition(viewGroup, transitionImpl == null ? null : ((TransitionIcs) transitionImpl).mTransition);
    }
}
