package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButtonImpl;
import android.support.design.widget.ValueAnimatorCompat;
import android.support.p001v4.view.ViewCompat;

@TargetApi(14)
@RequiresApi(14)
class FloatingActionButtonIcs extends FloatingActionButtonGingerbread {
    private float mRotation = this.mView.getRotation();

    FloatingActionButtonIcs(VisibilityAwareImageButton visibilityAwareImageButton, ShadowViewDelegate shadowViewDelegate, ValueAnimatorCompat.Creator creator) {
        super(visibilityAwareImageButton, shadowViewDelegate, creator);
    }

    /* access modifiers changed from: package-private */
    public boolean requirePreDrawListener() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onPreDraw() {
        float rotation = this.mView.getRotation();
        if (this.mRotation != rotation) {
            this.mRotation = rotation;
            updateFromViewRotation();
        }
    }

    /* access modifiers changed from: package-private */
    public void hide(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean z) {
        if (!isOrWillBeHidden()) {
            this.mView.animate().cancel();
            if (shouldAnimateVisibilityChange()) {
                this.mAnimState = 1;
                this.mView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
                    private boolean mCancelled;

                    public void onAnimationStart(Animator animator) {
                        FloatingActionButtonIcs.this.mView.internalSetVisibility(0, z);
                        this.mCancelled = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.mCancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        FloatingActionButtonIcs.this.mAnimState = 0;
                        if (!this.mCancelled) {
                            FloatingActionButtonIcs.this.mView.internalSetVisibility(z ? 8 : 4, z);
                            if (internalVisibilityChangedListener != null) {
                                internalVisibilityChangedListener.onHidden();
                            }
                        }
                    }
                });
                return;
            }
            this.mView.internalSetVisibility(z ? 8 : 4, z);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void show(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean z) {
        if (!isOrWillBeShown()) {
            this.mView.animate().cancel();
            if (shouldAnimateVisibilityChange()) {
                this.mAnimState = 2;
                if (this.mView.getVisibility() != 0) {
                    this.mView.setAlpha(0.0f);
                    this.mView.setScaleY(0.0f);
                    this.mView.setScaleX(0.0f);
                }
                this.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        FloatingActionButtonIcs.this.mView.internalSetVisibility(0, z);
                    }

                    public void onAnimationEnd(Animator animator) {
                        FloatingActionButtonIcs.this.mAnimState = 0;
                        if (internalVisibilityChangedListener != null) {
                            internalVisibilityChangedListener.onShown();
                        }
                    }
                });
                return;
            }
            this.mView.internalSetVisibility(0, z);
            this.mView.setAlpha(1.0f);
            this.mView.setScaleY(1.0f);
            this.mView.setScaleX(1.0f);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onShown();
            }
        }
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this.mView) && !this.mView.isInEditMode();
    }

    private void updateFromViewRotation() {
        if (Build.VERSION.SDK_INT == 19) {
            if (this.mRotation % 90.0f != 0.0f) {
                if (this.mView.getLayerType() != 1) {
                    this.mView.setLayerType(1, null);
                }
            } else if (this.mView.getLayerType() != 0) {
                this.mView.setLayerType(0, null);
            }
        }
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setRotation(-this.mRotation);
        }
        if (this.mBorderDrawable != null) {
            this.mBorderDrawable.setRotation(-this.mRotation);
        }
    }
}
