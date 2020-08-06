package android.support.p004v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p001v4.view.GravityCompat;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.appcompat.C0309R;
import android.support.p004v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.AlertDialogLayout */
public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(@Nullable Context context) {
        super(context);
    }

    public AlertDialogLayout(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (!tryOnMeasure(i, i2)) {
            super.onMeasure(i, i2);
        }
    }

    private boolean tryOnMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int makeMeasureSpec;
        View view;
        View view2 = null;
        View view3 = null;
        int childCount = getChildCount();
        int i10 = 0;
        View view4 = null;
        while (i10 < childCount) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() == 8) {
                childAt = view3;
                view = view2;
            } else {
                int id = childAt.getId();
                if (id == C0309R.C0311id.topPanel) {
                    View view5 = view3;
                    view = childAt;
                    childAt = view5;
                } else if (id == C0309R.C0311id.buttonPanel) {
                    view = view2;
                } else if (id != C0309R.C0311id.contentPanel && id != C0309R.C0311id.customPanel) {
                    return false;
                } else {
                    if (view4 != null) {
                        return false;
                    }
                    view4 = childAt;
                    childAt = view3;
                    view = view2;
                }
            }
            i10++;
            view2 = view;
            view3 = childAt;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i);
        int i11 = 0;
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (view2 != null) {
            view2.measure(i, 0);
            paddingBottom += view2.getMeasuredHeight();
            i11 = ViewCompat.combineMeasuredStates(0, ViewCompat.getMeasuredState(view2));
        }
        int i12 = 0;
        if (view3 != null) {
            view3.measure(i, 0);
            i12 = resolveMinimumHeight(view3);
            paddingBottom += i12;
            i11 = ViewCompat.combineMeasuredStates(i11, ViewCompat.getMeasuredState(view3));
            i3 = view3.getMeasuredHeight() - i12;
        } else {
            i3 = 0;
        }
        if (view4 != null) {
            if (mode == 0) {
                makeMeasureSpec = 0;
            } else {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingBottom), mode);
            }
            view4.measure(i, makeMeasureSpec);
            int measuredHeight = view4.getMeasuredHeight();
            paddingBottom += measuredHeight;
            i11 = ViewCompat.combineMeasuredStates(i11, ViewCompat.getMeasuredState(view4));
            i4 = measuredHeight;
        } else {
            i4 = 0;
        }
        int i13 = size - paddingBottom;
        if (view3 != null) {
            int i14 = paddingBottom - i12;
            int min = Math.min(i13, i3);
            if (min > 0) {
                i13 -= min;
                i12 += min;
            }
            view3.measure(i, View.MeasureSpec.makeMeasureSpec(i12, 1073741824));
            i6 = ViewCompat.combineMeasuredStates(i11, ViewCompat.getMeasuredState(view3));
            int i15 = i13;
            i7 = view3.getMeasuredHeight() + i14;
            i5 = i15;
        } else {
            i5 = i13;
            i6 = i11;
            i7 = paddingBottom;
        }
        if (view4 == null || i5 <= 0) {
            i8 = i7;
            i9 = i6;
        } else {
            int i16 = i5 - i5;
            view4.measure(i, View.MeasureSpec.makeMeasureSpec(i5 + i4, mode));
            int measuredHeight2 = (i7 - i4) + view4.getMeasuredHeight();
            i9 = ViewCompat.combineMeasuredStates(i6, ViewCompat.getMeasuredState(view4));
            i8 = measuredHeight2;
        }
        int i17 = 0;
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt2 = getChildAt(i18);
            if (childAt2.getVisibility() != 8) {
                i17 = Math.max(i17, childAt2.getMeasuredWidth());
            }
        }
        setMeasuredDimension(ViewCompat.resolveSizeAndState(i17 + getPaddingLeft() + getPaddingRight(), i, i9), ViewCompat.resolveSizeAndState(i8, i2, 0));
        if (mode2 != 1073741824) {
            forceUniformWidth(childCount, i2);
        }
        return true;
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) childAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = childAt.getMeasuredHeight();
                    measureChildWithMargins(childAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    private static int resolveMinimumHeight(View view) {
        int minimumHeight = ViewCompat.getMinimumHeight(view);
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return resolveMinimumHeight(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingTop;
        int intrinsicHeight;
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i3 - i;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        int gravity = getGravity();
        int i8 = gravity & 112;
        int i9 = gravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        switch (i8) {
            case 16:
                paddingTop = (((i4 - i2) - measuredHeight) / 2) + getPaddingTop();
                break;
            case 80:
                paddingTop = ((getPaddingTop() + i4) - i2) - measuredHeight;
                break;
            default:
                paddingTop = getPaddingTop();
                break;
        }
        Drawable dividerDrawable = getDividerDrawable();
        if (dividerDrawable == null) {
            intrinsicHeight = 0;
        } else {
            intrinsicHeight = dividerDrawable.getIntrinsicHeight();
        }
        int i10 = paddingTop;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight2 = childAt.getMeasuredHeight();
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) childAt.getLayoutParams();
                int i12 = layoutParams.gravity;
                if (i12 < 0) {
                    i12 = i9;
                }
                switch (GravityCompat.getAbsoluteGravity(i12, ViewCompat.getLayoutDirection(this)) & 7) {
                    case 1:
                        i5 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                        break;
                    case 5:
                        i5 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                        break;
                    default:
                        i5 = paddingLeft + layoutParams.leftMargin;
                        break;
                }
                if (hasDividerBeforeChildAt(i11)) {
                    i6 = i10 + intrinsicHeight;
                } else {
                    i6 = i10;
                }
                int i13 = layoutParams.topMargin + i6;
                setChildFrame(childAt, i5, i13, measuredWidth, measuredHeight2);
                i10 = i13 + layoutParams.bottomMargin + measuredHeight2;
            }
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i + i3, i2 + i4);
    }
}
