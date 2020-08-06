package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.design.C0030R;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.p001v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SnackbarContentLayout extends LinearLayout implements BaseTransientBottomBar.ContentViewCallback {
    private Button mActionView;
    private int mMaxInlineActionWidth;
    private int mMaxWidth;
    private TextView mMessageView;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0030R.styleable.SnackbarLayout);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(C0030R.styleable.SnackbarLayout_android_maxWidth, -1);
        this.mMaxInlineActionWidth = obtainStyledAttributes.getDimensionPixelSize(C0030R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mMessageView = (TextView) findViewById(C0030R.C0032id.snackbar_text);
        this.mActionView = (Button) findViewById(C0030R.C0032id.snackbar_action);
    }

    public TextView getMessageView() {
        return this.mMessageView;
    }

    public Button getActionView() {
        return this.mActionView;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean z;
        super.onMeasure(i, i2);
        if (this.mMaxWidth > 0 && getMeasuredWidth() > this.mMaxWidth) {
            i = View.MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
            super.onMeasure(i, i2);
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(C0030R.dimen.design_snackbar_padding_vertical_2lines);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(C0030R.dimen.design_snackbar_padding_vertical);
        boolean z2 = this.mMessageView.getLayout().getLineCount() > 1;
        if (!z2 || this.mMaxInlineActionWidth <= 0 || this.mActionView.getMeasuredWidth() <= this.mMaxInlineActionWidth) {
            if (!z2) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (updateViewsWithinLayout(0, dimensionPixelSize, dimensionPixelSize)) {
                z = true;
            }
            z = false;
        } else {
            if (updateViewsWithinLayout(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
                z = true;
            }
            z = false;
        }
        if (z) {
            super.onMeasure(i, i2);
        }
    }

    private boolean updateViewsWithinLayout(int i, int i2, int i3) {
        boolean z = false;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        }
        if (this.mMessageView.getPaddingTop() == i2 && this.mMessageView.getPaddingBottom() == i3) {
            return z;
        }
        updateTopBottomPadding(this.mMessageView, i2, i3);
        return true;
    }

    private static void updateTopBottomPadding(View view, int i, int i2) {
        if (ViewCompat.isPaddingRelative(view)) {
            ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), i, ViewCompat.getPaddingEnd(view), i2);
        } else {
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), i2);
        }
    }

    public void animateContentIn(int i, int i2) {
        ViewCompat.setAlpha(this.mMessageView, 0.0f);
        ViewCompat.animate(this.mMessageView).alpha(1.0f).setDuration((long) i2).setStartDelay((long) i).start();
        if (this.mActionView.getVisibility() == 0) {
            ViewCompat.setAlpha(this.mActionView, 0.0f);
            ViewCompat.animate(this.mActionView).alpha(1.0f).setDuration((long) i2).setStartDelay((long) i).start();
        }
    }

    public void animateContentOut(int i, int i2) {
        ViewCompat.setAlpha(this.mMessageView, 1.0f);
        ViewCompat.animate(this.mMessageView).alpha(0.0f).setDuration((long) i2).setStartDelay((long) i).start();
        if (this.mActionView.getVisibility() == 0) {
            ViewCompat.setAlpha(this.mActionView, 1.0f);
            ViewCompat.animate(this.mActionView).alpha(0.0f).setDuration((long) i2).setStartDelay((long) i).start();
        }
    }
}
