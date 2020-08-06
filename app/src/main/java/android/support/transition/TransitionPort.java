package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p001v4.util.ArrayMap;
import android.support.p001v4.util.LongSparseArray;
import android.support.p001v4.util.SimpleArrayMap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(14)
@RequiresApi(14)
abstract class TransitionPort implements Cloneable {
    static final boolean DBG = false;
    private static final String LOG_TAG = "Transition";
    private static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators = new ThreadLocal<>();
    ArrayList<Animator> mAnimators = new ArrayList<>();
    boolean mCanRemoveViews = false;
    ArrayList<Animator> mCurrentAnimators = new ArrayList<>();
    long mDuration = -1;
    private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
    private boolean mEnded = false;
    TimeInterpolator mInterpolator = null;
    ArrayList<TransitionListener> mListeners = null;
    private String mName = getClass().getName();
    int mNumInstances = 0;
    TransitionSetPort mParent = null;
    boolean mPaused = false;
    ViewGroup mSceneRoot = null;
    long mStartDelay = -1;
    private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
    ArrayList<View> mTargetChildExcludes = null;
    ArrayList<View> mTargetExcludes = null;
    ArrayList<Integer> mTargetIdChildExcludes = null;
    ArrayList<Integer> mTargetIdExcludes = null;
    ArrayList<Integer> mTargetIds = new ArrayList<>();
    ArrayList<Class> mTargetTypeChildExcludes = null;
    ArrayList<Class> mTargetTypeExcludes = null;
    ArrayList<View> mTargets = new ArrayList<>();

    private static class AnimationInfo {
        String name;
        TransitionValues values;
        View view;
        WindowIdPort windowId;

        AnimationInfo(View view2, String str, WindowIdPort windowIdPort, TransitionValues transitionValues) {
            this.view = view2;
            this.name = str;
            this.values = transitionValues;
            this.windowId = windowIdPort;
        }
    }

    private static class ArrayListManager {
        private ArrayListManager() {
        }

        static <T> ArrayList<T> add(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            if (!arrayList.contains(t)) {
                arrayList.add(t);
            }
            return arrayList;
        }

        static <T> ArrayList<T> remove(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                return arrayList;
            }
            arrayList.remove(t);
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
    }

    public interface TransitionListener {
        void onTransitionCancel(TransitionPort transitionPort);

        void onTransitionEnd(TransitionPort transitionPort);

        void onTransitionPause(TransitionPort transitionPort);

        void onTransitionResume(TransitionPort transitionPort);

        void onTransitionStart(TransitionPort transitionPort);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class TransitionListenerAdapter implements TransitionListener {
        public void onTransitionStart(TransitionPort transitionPort) {
        }

        public void onTransitionEnd(TransitionPort transitionPort) {
        }

        public void onTransitionCancel(TransitionPort transitionPort) {
        }

        public void onTransitionPause(TransitionPort transitionPort) {
        }

        public void onTransitionResume(TransitionPort transitionPort) {
        }
    }

    public abstract void captureEndValues(TransitionValues transitionValues);

    public abstract void captureStartValues(TransitionValues transitionValues);

    private static ArrayMap<Animator, AnimationInfo> getRunningAnimators() {
        ArrayMap<Animator, AnimationInfo> arrayMap = sRunningAnimators.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        ArrayMap<Animator, AnimationInfo> arrayMap2 = new ArrayMap<>();
        sRunningAnimators.set(arrayMap2);
        return arrayMap2;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public TransitionPort setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public TransitionPort setStartDelay(long j) {
        this.mStartDelay = j;
        return this;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    public TransitionPort setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2) {
        View view;
        TransitionValues transitionValues;
        Animator animator;
        TransitionValues transitionValues2;
        ArrayMap arrayMap = new ArrayMap((SimpleArrayMap) transitionValuesMaps2.viewValues);
        SparseArray sparseArray = new SparseArray(transitionValuesMaps2.idValues.size());
        for (int i = 0; i < transitionValuesMaps2.idValues.size(); i++) {
            sparseArray.put(transitionValuesMaps2.idValues.keyAt(i), transitionValuesMaps2.idValues.valueAt(i));
        }
        LongSparseArray longSparseArray = new LongSparseArray(transitionValuesMaps2.itemIdValues.size());
        for (int i2 = 0; i2 < transitionValuesMaps2.itemIdValues.size(); i2++) {
            longSparseArray.put(transitionValuesMaps2.itemIdValues.keyAt(i2), transitionValuesMaps2.itemIdValues.valueAt(i2));
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (View next : transitionValuesMaps.viewValues.keySet()) {
            boolean z = false;
            if (next.getParent() instanceof ListView) {
                z = true;
            }
            if (!z) {
                int id = next.getId();
                TransitionValues transitionValues3 = transitionValuesMaps.viewValues.get(next) != null ? transitionValuesMaps.viewValues.get(next) : transitionValuesMaps.idValues.get(id);
                if (transitionValuesMaps2.viewValues.get(next) != null) {
                    transitionValues2 = transitionValuesMaps2.viewValues.get(next);
                    arrayMap.remove(next);
                } else if (id != -1) {
                    transitionValues2 = transitionValuesMaps2.idValues.get(id);
                    View view2 = null;
                    for (View view3 : arrayMap.keySet()) {
                        if (view3.getId() != id) {
                            view3 = view2;
                        }
                        view2 = view3;
                    }
                    if (view2 != null) {
                        arrayMap.remove(view2);
                    }
                } else {
                    transitionValues2 = null;
                }
                sparseArray.remove(id);
                if (isValidTarget(next, (long) id)) {
                    arrayList.add(transitionValues3);
                    arrayList2.add(transitionValues2);
                }
            } else {
                ListView listView = (ListView) next.getParent();
                if (listView.getAdapter().hasStableIds()) {
                    long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(next));
                    longSparseArray.remove(itemIdAtPosition);
                    arrayList.add(transitionValuesMaps.itemIdValues.get(itemIdAtPosition));
                    arrayList2.add(null);
                }
            }
        }
        int size = transitionValuesMaps.itemIdValues.size();
        for (int i3 = 0; i3 < size; i3++) {
            long keyAt = transitionValuesMaps.itemIdValues.keyAt(i3);
            if (isValidTarget(null, keyAt)) {
                longSparseArray.remove(keyAt);
                arrayList.add(transitionValuesMaps.itemIdValues.get(keyAt));
                arrayList2.add(transitionValuesMaps2.itemIdValues.get(keyAt));
            }
        }
        for (View view4 : arrayMap.keySet()) {
            int id2 = view4.getId();
            if (isValidTarget(view4, (long) id2)) {
                Object obj = transitionValuesMaps.viewValues.get(view4) != null ? transitionValuesMaps.viewValues.get(view4) : transitionValuesMaps.idValues.get(id2);
                sparseArray.remove(id2);
                arrayList.add((TransitionValues) obj);
                arrayList2.add((TransitionValues) arrayMap.get(view4));
            }
        }
        int size2 = sparseArray.size();
        for (int i4 = 0; i4 < size2; i4++) {
            int keyAt2 = sparseArray.keyAt(i4);
            if (isValidTarget(null, (long) keyAt2)) {
                arrayList.add(transitionValuesMaps.idValues.get(keyAt2));
                arrayList2.add((TransitionValues) sparseArray.get(keyAt2));
            }
        }
        int size3 = longSparseArray.size();
        for (int i5 = 0; i5 < size3; i5++) {
            long keyAt3 = longSparseArray.keyAt(i5);
            arrayList.add(transitionValuesMaps.itemIdValues.get(keyAt3));
            arrayList2.add((TransitionValues) longSparseArray.get(keyAt3));
        }
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i7 < arrayList.size()) {
                TransitionValues transitionValues4 = (TransitionValues) arrayList.get(i7);
                TransitionValues transitionValues5 = (TransitionValues) arrayList2.get(i7);
                if (!(transitionValues4 == null && transitionValues5 == null) && (transitionValues4 == null || !transitionValues4.equals(transitionValues5))) {
                    Animator createAnimator = createAnimator(viewGroup, transitionValues4, transitionValues5);
                    if (createAnimator != null) {
                        if (transitionValues5 != null) {
                            View view5 = transitionValues5.view;
                            String[] transitionProperties = getTransitionProperties();
                            if (view5 != null && transitionProperties != null && transitionProperties.length > 0) {
                                TransitionValues transitionValues6 = new TransitionValues();
                                transitionValues6.view = view5;
                                TransitionValues transitionValues7 = transitionValuesMaps2.viewValues.get(view5);
                                if (transitionValues7 != null) {
                                    for (int i8 = 0; i8 < transitionProperties.length; i8++) {
                                        transitionValues6.values.put(transitionProperties[i8], transitionValues7.values.get(transitionProperties[i8]));
                                    }
                                }
                                int size4 = runningAnimators.size();
                                int i9 = 0;
                                while (true) {
                                    if (i9 >= size4) {
                                        transitionValues = transitionValues6;
                                        animator = createAnimator;
                                        break;
                                    }
                                    AnimationInfo animationInfo = runningAnimators.get(runningAnimators.keyAt(i9));
                                    if (animationInfo.values != null && animationInfo.view == view5 && (((animationInfo.name == null && getName() == null) || animationInfo.name.equals(getName())) && animationInfo.values.equals(transitionValues6))) {
                                        animator = null;
                                        transitionValues = transitionValues6;
                                        break;
                                    }
                                    i9++;
                                }
                            } else {
                                transitionValues = null;
                                animator = createAnimator;
                            }
                            createAnimator = animator;
                            view = view5;
                        } else {
                            view = transitionValues4.view;
                            transitionValues = null;
                        }
                        if (createAnimator != null) {
                            runningAnimators.put(createAnimator, new AnimationInfo(view, getName(), WindowIdPort.getWindowId(viewGroup), transitionValues));
                            this.mAnimators.add(createAnimator);
                        }
                    }
                }
                i6 = i7 + 1;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isValidTarget(View view, long j) {
        if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf((int) j))) {
            return false;
        }
        if (this.mTargetExcludes != null && this.mTargetExcludes.contains(view)) {
            return false;
        }
        if (!(this.mTargetTypeExcludes == null || view == null)) {
            int size = this.mTargetTypeExcludes.size();
            for (int i = 0; i < size; i++) {
                if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.mTargetIds.size() == 0 && this.mTargets.size() == 0) {
            return true;
        }
        if (this.mTargetIds.size() > 0) {
            for (int i2 = 0; i2 < this.mTargetIds.size(); i2++) {
                if (((long) this.mTargetIds.get(i2).intValue()) == j) {
                    return true;
                }
            }
        }
        if (view == null || this.mTargets.size() <= 0) {
            return false;
        }
        for (int i3 = 0; i3 < this.mTargets.size(); i3++) {
            if (this.mTargets.get(i3) == view) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void runAnimators() {
        start();
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (runningAnimators.containsKey(next)) {
                start();
                runAnimator(next, runningAnimators);
            }
        }
        this.mAnimators.clear();
        end();
    }

    private void runAnimator(Animator animator, final ArrayMap<Animator, AnimationInfo> arrayMap) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    TransitionPort.this.mCurrentAnimators.add(animator);
                }

                public void onAnimationEnd(Animator animator) {
                    arrayMap.remove(animator);
                    TransitionPort.this.mCurrentAnimators.remove(animator);
                }
            });
            animate(animator);
        }
    }

    public TransitionPort addTarget(int i) {
        if (i > 0) {
            this.mTargetIds.add(Integer.valueOf(i));
        }
        return this;
    }

    public TransitionPort removeTarget(int i) {
        if (i > 0) {
            this.mTargetIds.remove(Integer.valueOf(i));
        }
        return this;
    }

    public TransitionPort excludeTarget(int i, boolean z) {
        this.mTargetIdExcludes = excludeId(this.mTargetIdExcludes, i, z);
        return this;
    }

    public TransitionPort excludeChildren(int i, boolean z) {
        this.mTargetIdChildExcludes = excludeId(this.mTargetIdChildExcludes, i, z);
        return this;
    }

    private ArrayList<Integer> excludeId(ArrayList<Integer> arrayList, int i, boolean z) {
        if (i <= 0) {
            return arrayList;
        }
        if (z) {
            return ArrayListManager.add(arrayList, Integer.valueOf(i));
        }
        return ArrayListManager.remove(arrayList, Integer.valueOf(i));
    }

    public TransitionPort excludeTarget(View view, boolean z) {
        this.mTargetExcludes = excludeView(this.mTargetExcludes, view, z);
        return this;
    }

    public TransitionPort excludeChildren(View view, boolean z) {
        this.mTargetChildExcludes = excludeView(this.mTargetChildExcludes, view, z);
        return this;
    }

    private ArrayList<View> excludeView(ArrayList<View> arrayList, View view, boolean z) {
        if (view == null) {
            return arrayList;
        }
        if (z) {
            return ArrayListManager.add(arrayList, view);
        }
        return ArrayListManager.remove(arrayList, view);
    }

    public TransitionPort excludeTarget(Class cls, boolean z) {
        this.mTargetTypeExcludes = excludeType(this.mTargetTypeExcludes, cls, z);
        return this;
    }

    public TransitionPort excludeChildren(Class cls, boolean z) {
        this.mTargetTypeChildExcludes = excludeType(this.mTargetTypeChildExcludes, cls, z);
        return this;
    }

    private ArrayList<Class> excludeType(ArrayList<Class> arrayList, Class cls, boolean z) {
        if (cls == null) {
            return arrayList;
        }
        if (z) {
            return ArrayListManager.add(arrayList, cls);
        }
        return ArrayListManager.remove(arrayList, cls);
    }

    public TransitionPort addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    public TransitionPort removeTarget(View view) {
        if (view != null) {
            this.mTargets.remove(view);
        }
        return this;
    }

    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    public List<View> getTargets() {
        return this.mTargets;
    }

    /* access modifiers changed from: package-private */
    public void captureValues(ViewGroup viewGroup, boolean z) {
        clearValues(z);
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            if (this.mTargetIds.size() > 0) {
                for (int i = 0; i < this.mTargetIds.size(); i++) {
                    int intValue = this.mTargetIds.get(i).intValue();
                    View findViewById = viewGroup.findViewById(intValue);
                    if (findViewById != null) {
                        TransitionValues transitionValues = new TransitionValues();
                        transitionValues.view = findViewById;
                        if (z) {
                            captureStartValues(transitionValues);
                        } else {
                            captureEndValues(transitionValues);
                        }
                        if (z) {
                            this.mStartValues.viewValues.put(findViewById, transitionValues);
                            if (intValue >= 0) {
                                this.mStartValues.idValues.put(intValue, transitionValues);
                            }
                        } else {
                            this.mEndValues.viewValues.put(findViewById, transitionValues);
                            if (intValue >= 0) {
                                this.mEndValues.idValues.put(intValue, transitionValues);
                            }
                        }
                    }
                }
            }
            if (this.mTargets.size() > 0) {
                for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                    View view = this.mTargets.get(i2);
                    if (view != null) {
                        TransitionValues transitionValues2 = new TransitionValues();
                        transitionValues2.view = view;
                        if (z) {
                            captureStartValues(transitionValues2);
                        } else {
                            captureEndValues(transitionValues2);
                        }
                        if (z) {
                            this.mStartValues.viewValues.put(view, transitionValues2);
                        } else {
                            this.mEndValues.viewValues.put(view, transitionValues2);
                        }
                    }
                }
                return;
            }
            return;
        }
        captureHierarchy(viewGroup, z);
    }

    /* access modifiers changed from: package-private */
    public void clearValues(boolean z) {
        if (z) {
            this.mStartValues.viewValues.clear();
            this.mStartValues.idValues.clear();
            this.mStartValues.itemIdValues.clear();
            return;
        }
        this.mEndValues.viewValues.clear();
        this.mEndValues.idValues.clear();
        this.mEndValues.itemIdValues.clear();
    }

    private void captureHierarchy(View view, boolean z) {
        boolean z2;
        int i;
        long itemIdAtPosition;
        if (view != null) {
            if (view.getParent() instanceof ListView) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2 || ((ListView) view.getParent()).getAdapter().hasStableIds()) {
                if (!z2) {
                    i = view.getId();
                    itemIdAtPosition = -1;
                } else {
                    ListView listView = (ListView) view.getParent();
                    i = -1;
                    itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                }
                if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf(i))) {
                    return;
                }
                if (this.mTargetExcludes == null || !this.mTargetExcludes.contains(view)) {
                    if (!(this.mTargetTypeExcludes == null || view == null)) {
                        int size = this.mTargetTypeExcludes.size();
                        int i2 = 0;
                        while (i2 < size) {
                            if (!this.mTargetTypeExcludes.get(i2).isInstance(view)) {
                                i2++;
                            } else {
                                return;
                            }
                        }
                    }
                    TransitionValues transitionValues = new TransitionValues();
                    transitionValues.view = view;
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    if (z) {
                        if (!z2) {
                            this.mStartValues.viewValues.put(view, transitionValues);
                            if (i >= 0) {
                                this.mStartValues.idValues.put(i, transitionValues);
                            }
                        } else {
                            this.mStartValues.itemIdValues.put(itemIdAtPosition, transitionValues);
                        }
                    } else if (!z2) {
                        this.mEndValues.viewValues.put(view, transitionValues);
                        if (i >= 0) {
                            this.mEndValues.idValues.put(i, transitionValues);
                        }
                    } else {
                        this.mEndValues.itemIdValues.put(itemIdAtPosition, transitionValues);
                    }
                    if (!(view instanceof ViewGroup)) {
                        return;
                    }
                    if (this.mTargetIdChildExcludes != null && this.mTargetIdChildExcludes.contains(Integer.valueOf(i))) {
                        return;
                    }
                    if (this.mTargetChildExcludes == null || !this.mTargetChildExcludes.contains(view)) {
                        if (!(this.mTargetTypeChildExcludes == null || view == null)) {
                            int size2 = this.mTargetTypeChildExcludes.size();
                            int i3 = 0;
                            while (i3 < size2) {
                                if (!this.mTargetTypeChildExcludes.get(i3).isInstance(view)) {
                                    i3++;
                                } else {
                                    return;
                                }
                            }
                        }
                        ViewGroup viewGroup = (ViewGroup) view;
                        for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                            captureHierarchy(viewGroup.getChildAt(i4), z);
                        }
                    }
                }
            }
        }
    }

    public TransitionValues getTransitionValues(View view, boolean z) {
        if (this.mParent != null) {
            return this.mParent.getTransitionValues(view, z);
        }
        TransitionValuesMaps transitionValuesMaps = z ? this.mStartValues : this.mEndValues;
        TransitionValues transitionValues = transitionValuesMaps.viewValues.get(view);
        if (transitionValues != null) {
            return transitionValues;
        }
        int id = view.getId();
        if (id >= 0) {
            transitionValues = transitionValuesMaps.idValues.get(id);
        }
        if (transitionValues != null || !(view.getParent() instanceof ListView)) {
            return transitionValues;
        }
        ListView listView = (ListView) view.getParent();
        return transitionValuesMaps.itemIdValues.get(listView.getItemIdAtPosition(listView.getPositionForView(view)));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void pause(View view) {
        if (!this.mEnded) {
            ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
            int size = runningAnimators.size();
            WindowIdPort windowId = WindowIdPort.getWindowId(view);
            for (int i = size - 1; i >= 0; i--) {
                AnimationInfo valueAt = runningAnimators.valueAt(i);
                if (valueAt.view != null && windowId.equals(valueAt.windowId)) {
                    runningAnimators.keyAt(i).cancel();
                }
            }
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size2 = arrayList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    ((TransitionListener) arrayList.get(i2)).onTransitionPause(this);
                }
            }
            this.mPaused = true;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
                int size = runningAnimators.size();
                WindowIdPort windowId = WindowIdPort.getWindowId(view);
                for (int i = size - 1; i >= 0; i--) {
                    AnimationInfo valueAt = runningAnimators.valueAt(i);
                    if (valueAt.view != null && windowId.equals(valueAt.windowId)) {
                        runningAnimators.keyAt(i).end();
                    }
                }
                if (this.mListeners != null && this.mListeners.size() > 0) {
                    ArrayList arrayList = (ArrayList) this.mListeners.clone();
                    int size2 = arrayList.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((TransitionListener) arrayList.get(i2)).onTransitionResume(this);
                    }
                }
            }
            this.mPaused = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void playTransition(ViewGroup viewGroup) {
        TransitionValues transitionValues;
        boolean z;
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        for (int size = runningAnimators.size() - 1; size >= 0; size--) {
            Animator keyAt = runningAnimators.keyAt(size);
            if (keyAt != null) {
                AnimationInfo animationInfo = runningAnimators.get(keyAt);
                if (!(animationInfo == null || animationInfo.view == null || animationInfo.view.getContext() != viewGroup.getContext())) {
                    TransitionValues transitionValues2 = animationInfo.values;
                    View view = animationInfo.view;
                    TransitionValues transitionValues3 = this.mEndValues.viewValues != null ? this.mEndValues.viewValues.get(view) : null;
                    if (transitionValues3 == null) {
                        transitionValues = this.mEndValues.idValues.get(view.getId());
                    } else {
                        transitionValues = transitionValues3;
                    }
                    if (transitionValues2 != null && transitionValues != null) {
                        Iterator<String> it = transitionValues2.values.keySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String next = it.next();
                            Object obj = transitionValues2.values.get(next);
                            Object obj2 = transitionValues.values.get(next);
                            if (obj != null && obj2 != null && !obj.equals(obj2)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    z = false;
                    if (z) {
                        if (keyAt.isRunning() || keyAt.isStarted()) {
                            keyAt.cancel();
                        } else {
                            runningAnimators.remove(keyAt);
                        }
                    }
                }
            }
        }
        createAnimators(viewGroup, this.mStartValues, this.mEndValues);
        runAnimators();
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                TransitionPort.this.end();
                animator.removeListener(this);
            }
        });
        animator.start();
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void start() {
        if (this.mNumInstances == 0) {
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((TransitionListener) arrayList.get(i)).onTransitionStart(this);
                }
            }
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void end() {
        this.mNumInstances--;
        if (this.mNumInstances == 0) {
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((TransitionListener) arrayList.get(i)).onTransitionEnd(this);
                }
            }
            for (int i2 = 0; i2 < this.mStartValues.itemIdValues.size(); i2++) {
                View view = this.mStartValues.itemIdValues.valueAt(i2).view;
            }
            for (int i3 = 0; i3 < this.mEndValues.itemIdValues.size(); i3++) {
                View view2 = this.mEndValues.itemIdValues.valueAt(i3).view;
            }
            this.mEnded = true;
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void cancel() {
        for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
            this.mCurrentAnimators.get(size).cancel();
        }
        if (this.mListeners != null && this.mListeners.size() > 0) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size2 = arrayList.size();
            for (int i = 0; i < size2; i++) {
                ((TransitionListener) arrayList.get(i)).onTransitionCancel(this);
            }
        }
    }

    public TransitionPort addListener(TransitionListener transitionListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(transitionListener);
        return this;
    }

    public TransitionPort removeListener(TransitionListener transitionListener) {
        if (this.mListeners != null) {
            this.mListeners.remove(transitionListener);
            if (this.mListeners.size() == 0) {
                this.mListeners = null;
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public TransitionPort setSceneRoot(ViewGroup viewGroup) {
        this.mSceneRoot = viewGroup;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setCanRemoveViews(boolean z) {
        this.mCanRemoveViews = z;
    }

    public String toString() {
        return toString("");
    }

    public TransitionPort clone() {
        try {
            TransitionPort transitionPort = (TransitionPort) super.clone();
            try {
                transitionPort.mAnimators = new ArrayList<>();
                transitionPort.mStartValues = new TransitionValuesMaps();
                transitionPort.mEndValues = new TransitionValuesMaps();
                return transitionPort;
            } catch (CloneNotSupportedException e) {
                return transitionPort;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    public String getName() {
        return this.mName;
    }

    /* access modifiers changed from: package-private */
    public String toString(String str) {
        String str2;
        String str3 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.mDuration != -1) {
            str3 = str3 + "dur(" + this.mDuration + ") ";
        }
        if (this.mStartDelay != -1) {
            str3 = str3 + "dly(" + this.mStartDelay + ") ";
        }
        if (this.mInterpolator != null) {
            str3 = str3 + "interp(" + this.mInterpolator + ") ";
        }
        if (this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) {
            return str3;
        }
        String str4 = str3 + "tgts(";
        if (this.mTargetIds.size() > 0) {
            str2 = str4;
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                if (i > 0) {
                    str2 = str2 + ", ";
                }
                str2 = str2 + this.mTargetIds.get(i);
            }
        } else {
            str2 = str4;
        }
        if (this.mTargets.size() > 0) {
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                if (i2 > 0) {
                    str2 = str2 + ", ";
                }
                str2 = str2 + this.mTargets.get(i2);
            }
        }
        return str2 + ")";
    }
}
