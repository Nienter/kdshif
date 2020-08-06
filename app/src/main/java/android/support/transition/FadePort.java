package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.p001v4.view.ViewCompat;
import android.support.transition.TransitionPort;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(14)
@RequiresApi(14)
class FadePort extends VisibilityPort {
    private static boolean DBG = false;

    /* renamed from: IN */
    public static final int f73IN = 1;
    private static final String LOG_TAG = "Fade";
    public static final int OUT = 2;
    private static final String PROPNAME_SCREEN_X = "android:fade:screenX";
    private static final String PROPNAME_SCREEN_Y = "android:fade:screenY";
    private int mFadingMode;

    public FadePort() {
        this(3);
    }

    public FadePort(int i) {
        this.mFadingMode = i;
    }

    private Animator createAnimation(View view, float f, float f2, AnimatorListenerAdapter animatorListenerAdapter) {
        ObjectAnimator objectAnimator = null;
        if (f != f2) {
            objectAnimator = ObjectAnimator.ofFloat(view, "alpha", new float[]{f, f2});
            if (DBG) {
                Log.d(LOG_TAG, "Created animator " + objectAnimator);
            }
            if (animatorListenerAdapter != null) {
                objectAnimator.addListener(animatorListenerAdapter);
            }
        } else if (animatorListenerAdapter != null) {
            animatorListenerAdapter.onAnimationEnd(null);
        }
        return objectAnimator;
    }

    private void captureValues(TransitionValues transitionValues) {
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put(PROPNAME_SCREEN_X, Integer.valueOf(iArr[0]));
        transitionValues.values.put(PROPNAME_SCREEN_Y, Integer.valueOf(iArr[1]));
    }

    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        captureValues(transitionValues);
    }

    public Animator onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        if ((this.mFadingMode & 1) != 1 || transitionValues2 == null) {
            return null;
        }
        final View view = transitionValues2.view;
        if (DBG) {
            Log.d(LOG_TAG, "Fade.onAppear: startView, startVis, endView, endVis = " + (transitionValues != null ? transitionValues.view : null) + ", " + i + ", " + view + ", " + i2);
        }
        view.setAlpha(0.0f);
        addListener(new TransitionPort.TransitionListenerAdapter() {
            boolean mCanceled = false;
            float mPausedAlpha;

            public void onTransitionCancel(TransitionPort transitionPort) {
                view.setAlpha(1.0f);
                this.mCanceled = true;
            }

            public void onTransitionEnd(TransitionPort transitionPort) {
                if (!this.mCanceled) {
                    view.setAlpha(1.0f);
                }
            }

            public void onTransitionPause(TransitionPort transitionPort) {
                this.mPausedAlpha = view.getAlpha();
                view.setAlpha(1.0f);
            }

            public void onTransitionResume(TransitionPort transitionPort) {
                view.setAlpha(this.mPausedAlpha);
            }
        });
        return createAnimation(view, 0.0f, 1.0f, null);
    }

    public Animator onDisappear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        View view;
        final View view2;
        final View view3;
        View view4;
        if ((this.mFadingMode & 2) != 2) {
            return null;
        }
        final View view5 = transitionValues != null ? transitionValues.view : null;
        if (transitionValues2 != null) {
            view = transitionValues2.view;
        } else {
            view = null;
        }
        if (DBG) {
            Log.d(LOG_TAG, "Fade.onDisappear: startView, startVis, endView, endVis = " + view5 + ", " + i + ", " + view + ", " + i2);
        }
        if (view == null || view.getParent() == null) {
            if (view != null) {
                view3 = null;
                view2 = view;
                view5 = view;
            } else {
                if (view5 != null) {
                    if (view5.getParent() == null) {
                        view3 = null;
                        view2 = view5;
                    } else if ((view5.getParent() instanceof View) && view5.getParent().getParent() == null) {
                        int id = ((View) view5.getParent()).getId();
                        if (id == -1 || viewGroup.findViewById(id) == null || !this.mCanRemoveViews) {
                            view4 = null;
                            view5 = null;
                        } else {
                            view4 = view5;
                        }
                        view3 = null;
                        view2 = view4;
                    }
                }
                view3 = null;
                view2 = null;
                view5 = null;
            }
        } else if (i2 == 4) {
            view3 = view;
            view2 = null;
            view5 = view;
        } else if (view5 == view) {
            view3 = view;
            view2 = null;
            view5 = view;
        } else {
            view3 = null;
            view2 = view5;
        }
        if (view2 != null) {
            int intValue = ((Integer) transitionValues.values.get(PROPNAME_SCREEN_X)).intValue();
            int intValue2 = ((Integer) transitionValues.values.get(PROPNAME_SCREEN_Y)).intValue();
            int[] iArr = new int[2];
            viewGroup.getLocationOnScreen(iArr);
            ViewCompat.offsetLeftAndRight(view2, (intValue - iArr[0]) - view2.getLeft());
            ViewCompat.offsetTopAndBottom(view2, (intValue2 - iArr[1]) - view2.getTop());
            ViewGroupOverlay.createFrom(viewGroup).add(view2);
            final int i3 = i2;
            final ViewGroup viewGroup2 = viewGroup;
            return createAnimation(view5, 1.0f, 0.0f, new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    view5.setAlpha(1.0f);
                    if (view3 != null) {
                        view3.setVisibility(i3);
                    }
                    if (view2 != null) {
                        ViewGroupOverlay.createFrom(viewGroup2).remove(view2);
                    }
                }
            });
        } else if (view3 == null) {
            return null;
        } else {
            view3.setVisibility(0);
            final int i4 = i2;
            final ViewGroup viewGroup3 = viewGroup;
            return createAnimation(view5, 1.0f, 0.0f, new AnimatorListenerAdapter() {
                boolean mCanceled = false;
                float mPausedAlpha = -1.0f;

                public void onAnimationCancel(Animator animator) {
                    this.mCanceled = true;
                    if (this.mPausedAlpha >= 0.0f) {
                        view5.setAlpha(this.mPausedAlpha);
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    if (!this.mCanceled) {
                        view5.setAlpha(1.0f);
                    }
                    if (view3 != null && !this.mCanceled) {
                        view3.setVisibility(i4);
                    }
                    if (view2 != null) {
                        ViewGroupOverlay.createFrom(viewGroup3).add(view2);
                    }
                }
            });
        }
    }
}
