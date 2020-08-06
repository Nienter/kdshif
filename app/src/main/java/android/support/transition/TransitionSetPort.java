package android.support.transition;

import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.transition.TransitionPort;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(14)
@RequiresApi(14)
class TransitionSetPort extends TransitionPort {
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    int mCurrentListeners;
    private boolean mPlayTogether = true;
    boolean mStarted = false;
    ArrayList<TransitionPort> mTransitions = new ArrayList<>();

    static class TransitionSetListener extends TransitionPort.TransitionListenerAdapter {
        TransitionSetPort mTransitionSet;

        TransitionSetListener(TransitionSetPort transitionSetPort) {
            this.mTransitionSet = transitionSetPort;
        }

        public void onTransitionStart(TransitionPort transitionPort) {
            if (!this.mTransitionSet.mStarted) {
                this.mTransitionSet.start();
                this.mTransitionSet.mStarted = true;
            }
        }

        public void onTransitionEnd(TransitionPort transitionPort) {
            TransitionSetPort transitionSetPort = this.mTransitionSet;
            transitionSetPort.mCurrentListeners--;
            if (this.mTransitionSet.mCurrentListeners == 0) {
                this.mTransitionSet.mStarted = false;
                this.mTransitionSet.end();
            }
            transitionPort.removeListener(this);
        }
    }

    public int getOrdering() {
        return this.mPlayTogether ? 0 : 1;
    }

    public TransitionSetPort setOrdering(int i) {
        switch (i) {
            case 0:
                this.mPlayTogether = true;
                break;
            case 1:
                this.mPlayTogether = false;
                break;
            default:
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
        }
        return this;
    }

    public TransitionSetPort addTransition(TransitionPort transitionPort) {
        if (transitionPort != null) {
            this.mTransitions.add(transitionPort);
            transitionPort.mParent = this;
            if (this.mDuration >= 0) {
                transitionPort.setDuration(this.mDuration);
            }
        }
        return this;
    }

    public TransitionSetPort setDuration(long j) {
        super.setDuration(j);
        if (this.mDuration >= 0) {
            int size = this.mTransitions.size();
            for (int i = 0; i < size; i++) {
                this.mTransitions.get(i).setDuration(j);
            }
        }
        return this;
    }

    public TransitionSetPort setStartDelay(long j) {
        return (TransitionSetPort) super.setStartDelay(j);
    }

    public TransitionSetPort setInterpolator(TimeInterpolator timeInterpolator) {
        return (TransitionSetPort) super.setInterpolator(timeInterpolator);
    }

    public TransitionSetPort addTarget(View view) {
        return (TransitionSetPort) super.addTarget(view);
    }

    public TransitionSetPort addTarget(int i) {
        return (TransitionSetPort) super.addTarget(i);
    }

    public TransitionSetPort addListener(TransitionPort.TransitionListener transitionListener) {
        return (TransitionSetPort) super.addListener(transitionListener);
    }

    public TransitionSetPort removeTarget(int i) {
        return (TransitionSetPort) super.removeTarget(i);
    }

    public TransitionSetPort removeTarget(View view) {
        return (TransitionSetPort) super.removeTarget(view);
    }

    public TransitionSetPort removeListener(TransitionPort.TransitionListener transitionListener) {
        return (TransitionSetPort) super.removeListener(transitionListener);
    }

    public TransitionSetPort removeTransition(TransitionPort transitionPort) {
        this.mTransitions.remove(transitionPort);
        transitionPort.mParent = null;
        return this;
    }

    private void setupStartEndListeners() {
        TransitionSetListener transitionSetListener = new TransitionSetListener(this);
        Iterator<TransitionPort> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().addListener(transitionSetListener);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2) {
        Iterator<TransitionPort> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().createAnimators(viewGroup, transitionValuesMaps, transitionValuesMaps2);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void runAnimators() {
        if (this.mTransitions.isEmpty()) {
            start();
            end();
            return;
        }
        setupStartEndListeners();
        if (!this.mPlayTogether) {
            int i = 1;
            while (true) {
                int i2 = i;
                if (i2 >= this.mTransitions.size()) {
                    break;
                }
                final TransitionPort transitionPort = this.mTransitions.get(i2);
                this.mTransitions.get(i2 - 1).addListener(new TransitionPort.TransitionListenerAdapter() {
                    public void onTransitionEnd(TransitionPort transitionPort) {
                        transitionPort.runAnimators();
                        transitionPort.removeListener(this);
                    }
                });
                i = i2 + 1;
            }
            TransitionPort transitionPort2 = this.mTransitions.get(0);
            if (transitionPort2 != null) {
                transitionPort2.runAnimators();
                return;
            }
            return;
        }
        Iterator<TransitionPort> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().runAnimators();
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        int id = transitionValues.view.getId();
        if (isValidTarget(transitionValues.view, (long) id)) {
            Iterator<TransitionPort> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                TransitionPort next = it.next();
                if (next.isValidTarget(transitionValues.view, (long) id)) {
                    next.captureStartValues(transitionValues);
                }
            }
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        int id = transitionValues.view.getId();
        if (isValidTarget(transitionValues.view, (long) id)) {
            Iterator<TransitionPort> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                TransitionPort next = it.next();
                if (next.isValidTarget(transitionValues.view, (long) id)) {
                    next.captureEndValues(transitionValues);
                }
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void pause(View view) {
        super.pause(view);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).pause(view);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void resume(View view) {
        super.resume(view);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).resume(view);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void cancel() {
        super.cancel();
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public TransitionSetPort setSceneRoot(ViewGroup viewGroup) {
        super.setSceneRoot(viewGroup);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).setSceneRoot(viewGroup);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setCanRemoveViews(boolean z) {
        super.setCanRemoveViews(z);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).setCanRemoveViews(z);
        }
    }

    /* access modifiers changed from: package-private */
    public String toString(String str) {
        String transitionPort = super.toString(str);
        int i = 0;
        while (i < this.mTransitions.size()) {
            String str2 = transitionPort + "\n" + this.mTransitions.get(i).toString(str + "  ");
            i++;
            transitionPort = str2;
        }
        return transitionPort;
    }

    public TransitionSetPort clone() {
        TransitionSetPort transitionSetPort = (TransitionSetPort) super.clone();
        transitionSetPort.mTransitions = new ArrayList<>();
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            transitionSetPort.addTransition(this.mTransitions.get(i).clone());
        }
        return transitionSetPort;
    }
}
