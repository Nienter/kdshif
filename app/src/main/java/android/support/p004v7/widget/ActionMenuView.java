package android.support.p004v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.p004v7.view.menu.ActionMenuItemView;
import android.support.p004v7.view.menu.MenuBuilder;
import android.support.p004v7.view.menu.MenuItemImpl;
import android.support.p004v7.view.menu.MenuPresenter;
import android.support.p004v7.view.menu.MenuView;
import android.support.p004v7.widget.ActivityChooserView;
import android.support.p004v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: android.support.v7.widget.ActionMenuView */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.widget.ActionMenuView$ActionMenuChildView */
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    /* renamed from: android.support.v7.widget.ActionMenuView$ActionMenuPresenterCallback */
    private class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        ActionMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuView$LayoutParams */
    public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
        @ViewDebug.ExportedProperty
        public int cellsUsed;
        @ViewDebug.ExportedProperty
        public boolean expandable;
        boolean expanded;
        @ViewDebug.ExportedProperty
        public int extraPixels;
        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;
        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.isOverflowButton = false;
        }

        LayoutParams(int i, int i2, boolean z) {
            super(i, i2);
            this.isOverflowButton = z;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuView$MenuBuilderCallback */
    private class MenuBuilderCallback implements MenuBuilder.Callback {
        MenuBuilderCallback() {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return ActionMenuView.this.mOnMenuItemClickListener != null && ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(menuItem);
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (ActionMenuView.this.mMenuBuilderCallback != null) {
                ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(menuBuilder);
            }
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuView$OnMenuItemClickListener */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * f);
        this.mGeneratedItemPadding = (int) (f * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
    }

    public void setPopupTheme(@StyleRes int i) {
        if (this.mPopupTheme != i) {
            this.mPopupTheme = i;
            if (i == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        this.mPresenter.setMenuView(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mPresenter != null) {
            this.mPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean z = this.mFormatItems;
        this.mFormatItems = View.MeasureSpec.getMode(i) == 1073741824;
        if (z != this.mFormatItems) {
            this.mFormatItemsWidth = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (!(!this.mFormatItems || this.mMenu == null || size == this.mFormatItemsWidth)) {
            this.mFormatItemsWidth = size;
            this.mMenu.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (!this.mFormatItems || childCount <= 0) {
            for (int i3 = 0; i3 < childCount; i3++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
                layoutParams.rightMargin = 0;
                layoutParams.leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        onMeasureExactFormat(i, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01de  */
    private void onMeasureExactFormat(int i, int i2) {
        boolean z;
        long j;
        boolean z2;
        float f;
        int i3;
        boolean z3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z4;
        int i9;
        int i10;
        int i11;
        int i12;
        long j2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
        int i13 = size - paddingLeft;
        int i14 = i13 / this.mMinCellSize;
        int i15 = i13 % this.mMinCellSize;
        if (i14 == 0) {
            setMeasuredDimension(i13, 0);
            return;
        }
        int i16 = this.mMinCellSize + (i15 / i14);
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        boolean z5 = false;
        long j3 = 0;
        int childCount = getChildCount();
        int i21 = 0;
        while (i21 < childCount) {
            View childAt = getChildAt(i21);
            if (childAt.getVisibility() == 8) {
                i9 = i20;
                j2 = j3;
                i11 = i17;
                i12 = i14;
                i10 = i18;
            } else {
                boolean z6 = childAt instanceof ActionMenuItemView;
                int i22 = i20 + 1;
                if (z6) {
                    childAt.setPadding(this.mGeneratedItemPadding, 0, this.mGeneratedItemPadding, 0);
                }
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.expanded = false;
                layoutParams.extraPixels = 0;
                layoutParams.cellsUsed = 0;
                layoutParams.expandable = false;
                layoutParams.leftMargin = 0;
                layoutParams.rightMargin = 0;
                layoutParams.preventEdgeOffset = z6 && ((ActionMenuItemView) childAt).hasText();
                if (layoutParams.isOverflowButton) {
                    i7 = 1;
                } else {
                    i7 = i14;
                }
                int measureChildForCells = measureChildForCells(childAt, i16, i7, childMeasureSpec, paddingTop);
                int max = Math.max(i18, measureChildForCells);
                if (layoutParams.expandable) {
                    i8 = i19 + 1;
                } else {
                    i8 = i19;
                }
                if (layoutParams.isOverflowButton) {
                    z4 = true;
                } else {
                    z4 = z5;
                }
                int i23 = i14 - measureChildForCells;
                int max2 = Math.max(i17, childAt.getMeasuredHeight());
                if (measureChildForCells == 1) {
                    long j4 = ((long) (1 << i21)) | j3;
                    i11 = max2;
                    i12 = i23;
                    i19 = i8;
                    z5 = z4;
                    j2 = j4;
                    i10 = max;
                    i9 = i22;
                } else {
                    i9 = i22;
                    i10 = max;
                    long j5 = j3;
                    i11 = max2;
                    i12 = i23;
                    z5 = z4;
                    i19 = i8;
                    j2 = j5;
                }
            }
            i21++;
            i18 = i10;
            i17 = i11;
            i14 = i12;
            j3 = j2;
            i20 = i9;
        }
        if (!z5 || i20 != 2) {
            z = false;
        } else {
            z = true;
        }
        boolean z7 = false;
        long j6 = j3;
        int i24 = i14;
        while (true) {
            if (i19 <= 0 || i24 <= 0) {
                j = j6;
            } else {
                int i25 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                long j7 = 0;
                int i26 = 0;
                int i27 = 0;
                while (i27 < childCount) {
                    LayoutParams layoutParams2 = (LayoutParams) getChildAt(i27).getLayoutParams();
                    if (!layoutParams2.expandable) {
                        i5 = i26;
                        i6 = i25;
                    } else if (layoutParams2.cellsUsed < i25) {
                        i6 = layoutParams2.cellsUsed;
                        j7 = (long) (1 << i27);
                        i5 = 1;
                    } else if (layoutParams2.cellsUsed == i25) {
                        j7 |= (long) (1 << i27);
                        i5 = i26 + 1;
                        i6 = i25;
                    } else {
                        i5 = i26;
                        i6 = i25;
                    }
                    i27++;
                    i25 = i6;
                    i26 = i5;
                }
                long j8 = j6 | j7;
                if (i26 > i24) {
                    j = j8;
                    break;
                }
                int i28 = i25 + 1;
                int i29 = 0;
                int i30 = i24;
                long j9 = j8;
                while (i29 < childCount) {
                    View childAt2 = getChildAt(i29);
                    LayoutParams layoutParams3 = (LayoutParams) childAt2.getLayoutParams();
                    if ((((long) (1 << i29)) & j7) != 0) {
                        if (z && layoutParams3.preventEdgeOffset && i30 == 1) {
                            childAt2.setPadding(this.mGeneratedItemPadding + i16, 0, this.mGeneratedItemPadding, 0);
                        }
                        layoutParams3.cellsUsed++;
                        layoutParams3.expanded = true;
                        i4 = i30 - 1;
                    } else if (layoutParams3.cellsUsed == i28) {
                        j9 |= (long) (1 << i29);
                        i4 = i30;
                    } else {
                        i4 = i30;
                    }
                    i29++;
                    i30 = i4;
                }
                j6 = j9;
                z7 = true;
                i24 = i30;
            }
        }
        boolean z8 = !z5 && i20 == 1;
        if (i24 <= 0 || j == 0 || (i24 >= i20 - 1 && !z8 && i18 <= 1)) {
            z2 = z7;
        } else {
            float bitCount = (float) Long.bitCount(j);
            if (!z8) {
                if ((1 & j) != 0 && !((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                    bitCount -= 0.5f;
                }
                if ((((long) (1 << (childCount - 1))) & j) != 0) {
                    if (!((LayoutParams) getChildAt(childCount - 1).getLayoutParams()).preventEdgeOffset) {
                        f = bitCount - 0.5f;
                        int i31 = f <= 0.0f ? (int) (((float) (i24 * i16)) / f) : 0;
                        i3 = 0;
                        z2 = z7;
                        while (i3 < childCount) {
                            if ((((long) (1 << i3)) & j) == 0) {
                                z3 = z2;
                            } else {
                                View childAt3 = getChildAt(i3);
                                LayoutParams layoutParams4 = (LayoutParams) childAt3.getLayoutParams();
                                if (childAt3 instanceof ActionMenuItemView) {
                                    layoutParams4.extraPixels = i31;
                                    layoutParams4.expanded = true;
                                    if (i3 == 0 && !layoutParams4.preventEdgeOffset) {
                                        layoutParams4.leftMargin = (-i31) / 2;
                                    }
                                    z3 = true;
                                } else if (layoutParams4.isOverflowButton) {
                                    layoutParams4.extraPixels = i31;
                                    layoutParams4.expanded = true;
                                    layoutParams4.rightMargin = (-i31) / 2;
                                    z3 = true;
                                } else {
                                    if (i3 != 0) {
                                        layoutParams4.leftMargin = i31 / 2;
                                    }
                                    if (i3 != childCount - 1) {
                                        layoutParams4.rightMargin = i31 / 2;
                                    }
                                    z3 = z2;
                                }
                            }
                            i3++;
                            z2 = z3;
                        }
                    }
                }
            }
            f = bitCount;
            if (f <= 0.0f) {
            }
            i3 = 0;
            z2 = z7;
            while (i3 < childCount) {
            }
        }
        if (z2) {
            int i32 = 0;
            while (true) {
                int i33 = i32;
                if (i33 >= childCount) {
                    break;
                }
                View childAt4 = getChildAt(i33);
                LayoutParams layoutParams5 = (LayoutParams) childAt4.getLayoutParams();
                if (layoutParams5.expanded) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec(layoutParams5.extraPixels + (layoutParams5.cellsUsed * i16), 1073741824), childMeasureSpec);
                }
                i32 = i33 + 1;
            }
        }
        if (mode == 1073741824) {
            i17 = size2;
        }
        setMeasuredDimension(i13, i17);
    }

    static int measureChildForCells(View view, int i, int i2, int i3, int i4) {
        boolean z;
        int i5;
        boolean z2 = false;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        if (actionMenuItemView == null || !actionMenuItemView.hasText()) {
            z = false;
        } else {
            z = true;
        }
        if (i2 <= 0 || (z && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i * i2, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            i5 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i5++;
            }
            if (z && i5 < 2) {
                i5 = 2;
            }
        }
        if (!layoutParams.isOverflowButton && z) {
            z2 = true;
        }
        layoutParams.expandable = z2;
        layoutParams.cellsUsed = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i5 * i, 1073741824), makeMeasureSpec);
        return i5;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z2;
        int width;
        int i10;
        if (!this.mFormatItems) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i11 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i12 = 0;
        int i13 = 0;
        int paddingRight = ((i3 - i) - getPaddingRight()) - getPaddingLeft();
        boolean z3 = false;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i14 = 0;
        while (i14 < childCount) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() == 8) {
                z2 = z3;
                i8 = i13;
                i7 = paddingRight;
                i9 = i12;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (hasSupportDividerBeforeChildAt(i14)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (isLayoutRtl) {
                        i10 = layoutParams.leftMargin + getPaddingLeft();
                        width = i10 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                        i10 = width - measuredWidth;
                    }
                    int i15 = i11 - (measuredHeight / 2);
                    childAt.layout(i10, i15, width, measuredHeight + i15);
                    i7 = paddingRight - measuredWidth;
                    z2 = true;
                    i8 = i13;
                    i9 = i12;
                } else {
                    int measuredWidth2 = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                    int i16 = i12 + measuredWidth2;
                    int i17 = paddingRight - measuredWidth2;
                    if (hasSupportDividerBeforeChildAt(i14)) {
                        i16 += dividerWidth;
                    }
                    boolean z4 = z3;
                    i7 = i17;
                    i8 = i13 + 1;
                    i9 = i16;
                    z2 = z4;
                }
            }
            i14++;
            i12 = i9;
            paddingRight = i7;
            i13 = i8;
            z3 = z2;
        }
        if (childCount != 1 || z3) {
            int i18 = i13 - (z3 ? 0 : 1);
            int max = Math.max(0, i18 > 0 ? paddingRight / i18 : 0);
            if (isLayoutRtl) {
                int width2 = getWidth() - getPaddingRight();
                int i19 = 0;
                while (i19 < childCount) {
                    View childAt2 = getChildAt(i19);
                    LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() == 8) {
                        i6 = width2;
                    } else if (layoutParams2.isOverflowButton) {
                        i6 = width2;
                    } else {
                        int i20 = width2 - layoutParams2.rightMargin;
                        int measuredWidth3 = childAt2.getMeasuredWidth();
                        int measuredHeight2 = childAt2.getMeasuredHeight();
                        int i21 = i11 - (measuredHeight2 / 2);
                        childAt2.layout(i20 - measuredWidth3, i21, i20, measuredHeight2 + i21);
                        i6 = i20 - ((layoutParams2.leftMargin + measuredWidth3) + max);
                    }
                    i19++;
                    width2 = i6;
                }
                return;
            }
            int paddingLeft = getPaddingLeft();
            int i22 = 0;
            while (i22 < childCount) {
                View childAt3 = getChildAt(i22);
                LayoutParams layoutParams3 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() == 8) {
                    i5 = paddingLeft;
                } else if (layoutParams3.isOverflowButton) {
                    i5 = paddingLeft;
                } else {
                    int i23 = paddingLeft + layoutParams3.leftMargin;
                    int measuredWidth4 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i24 = i11 - (measuredHeight3 / 2);
                    childAt3.layout(i23, i24, i23 + measuredWidth4, measuredHeight3 + i24);
                    i5 = layoutParams3.rightMargin + measuredWidth4 + max + i23;
                }
                i22++;
                paddingLeft = i5;
            }
            return;
        }
        View childAt4 = getChildAt(0);
        int measuredWidth5 = childAt4.getMeasuredWidth();
        int measuredHeight4 = childAt4.getMeasuredHeight();
        int i25 = ((i3 - i) / 2) - (measuredWidth5 / 2);
        int i26 = i11 - (measuredHeight4 / 2);
        childAt4.layout(i25, i26, measuredWidth5 + i25, measuredHeight4 + i26);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    public void setOverflowIcon(@Nullable Drawable drawable) {
        getMenu();
        this.mPresenter.setOverflowIcon(drawable);
    }

    @Nullable
    public Drawable getOverflowIcon() {
        getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setOverflowReserved(boolean z) {
        this.mReserveOverflow = z;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        return layoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : new LayoutParams(layoutParams);
        if (layoutParams2.gravity > 0) {
            return layoutParams2;
        }
        layoutParams2.gravity = 16;
        return layoutParams2;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.isOverflowButton = true;
        return generateDefaultLayoutParams;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getWindowAnimations() {
        return 0;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    public Menu getMenu() {
        if (this.mMenu == null) {
            Context context = getContext();
            this.mMenu = new MenuBuilder(context);
            this.mMenu.setCallback(new MenuBuilderCallback());
            this.mPresenter = new ActionMenuPresenter(context);
            this.mPresenter.setReserveOverflow(true);
            this.mPresenter.setCallback(this.mActionMenuPresenterCallback != null ? this.mActionMenuPresenterCallback : new ActionMenuPresenterCallback());
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    public boolean showOverflowMenu() {
        return this.mPresenter != null && this.mPresenter.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        return this.mPresenter != null && this.mPresenter.hideOverflowMenu();
    }

    public boolean isOverflowMenuShowing() {
        return this.mPresenter != null && this.mPresenter.isOverflowMenuShowing();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isOverflowMenuShowPending() {
        return this.mPresenter != null && this.mPresenter.isOverflowMenuShowPending();
    }

    public void dismissPopupMenus() {
        if (this.mPresenter != null) {
            this.mPresenter.dismissPopupMenus();
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean hasSupportDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = false | ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        return (i <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? z : ((ActionMenuChildView) childAt2).needsDividerBefore() | z;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setExpandedActionViewsExclusive(boolean z) {
        this.mPresenter.setExpandedActionViewsExclusive(z);
    }
}
