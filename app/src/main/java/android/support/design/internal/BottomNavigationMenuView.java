package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.design.C0030R;
import android.support.p001v4.util.Pools;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.view.menu.MenuBuilder;
import android.support.p004v7.view.menu.MenuItemImpl;
import android.support.p004v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BottomNavigationMenuView extends ViewGroup implements MenuView {
    private final int mActiveItemMaxWidth;
    private final BottomNavigationAnimationHelperBase mAnimationHelper;
    private BottomNavigationItemView[] mButtons;
    private final int mInactiveItemMaxWidth;
    private final int mInactiveItemMinWidth;
    private int mItemBackgroundRes;
    private final int mItemHeight;
    private ColorStateList mItemIconTint;
    private final Pools.Pool<BottomNavigationItemView> mItemPool;
    private ColorStateList mItemTextColor;
    /* access modifiers changed from: private */
    public MenuBuilder mMenu;
    private final View.OnClickListener mOnClickListener;
    /* access modifiers changed from: private */
    public BottomNavigationPresenter mPresenter;
    private int mSelectedItemId;
    private int mSelectedItemPosition;
    private boolean mShiftingMode;
    private int[] mTempChildWidths;

    public BottomNavigationMenuView(Context context) {
        this(context, null);
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItemPool = new Pools.SynchronizedPool(5);
        this.mShiftingMode = true;
        this.mSelectedItemId = 0;
        this.mSelectedItemPosition = 0;
        Resources resources = getResources();
        this.mInactiveItemMaxWidth = resources.getDimensionPixelSize(C0030R.dimen.design_bottom_navigation_item_max_width);
        this.mInactiveItemMinWidth = resources.getDimensionPixelSize(C0030R.dimen.design_bottom_navigation_item_min_width);
        this.mActiveItemMaxWidth = resources.getDimensionPixelSize(C0030R.dimen.design_bottom_navigation_active_item_max_width);
        this.mItemHeight = resources.getDimensionPixelSize(C0030R.dimen.design_bottom_navigation_height);
        if (Build.VERSION.SDK_INT >= 14) {
            this.mAnimationHelper = new BottomNavigationAnimationHelperIcs();
        } else {
            this.mAnimationHelper = new BottomNavigationAnimationHelperBase();
        }
        this.mOnClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                MenuItemImpl itemData = ((BottomNavigationItemView) view).getItemData();
                if (!BottomNavigationMenuView.this.mMenu.performItemAction(itemData, BottomNavigationMenuView.this.mPresenter, 0)) {
                    itemData.setChecked(true);
                }
            }
        };
        this.mTempChildWidths = new int[5];
    }

    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int size = View.MeasureSpec.getSize(i);
        int childCount = getChildCount();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mItemHeight, 1073741824);
        if (this.mShiftingMode) {
            int i6 = childCount - 1;
            int min = Math.min(size - (this.mInactiveItemMinWidth * i6), this.mActiveItemMaxWidth);
            int min2 = Math.min((size - min) / i6, this.mInactiveItemMaxWidth);
            int i7 = (size - min) - (i6 * min2);
            int i8 = 0;
            while (i8 < childCount) {
                int[] iArr = this.mTempChildWidths;
                if (i8 == this.mSelectedItemPosition) {
                    i4 = min;
                } else {
                    i4 = min2;
                }
                iArr[i8] = i4;
                if (i7 > 0) {
                    int[] iArr2 = this.mTempChildWidths;
                    iArr2[i8] = iArr2[i8] + 1;
                    i5 = i7 - 1;
                } else {
                    i5 = i7;
                }
                i8++;
                i7 = i5;
            }
        } else {
            if (childCount == 0) {
                i3 = 1;
            } else {
                i3 = childCount;
            }
            int min3 = Math.min(size / i3, this.mActiveItemMaxWidth);
            int i9 = size - (min3 * childCount);
            for (int i10 = 0; i10 < childCount; i10++) {
                this.mTempChildWidths[i10] = min3;
                if (i9 > 0) {
                    int[] iArr3 = this.mTempChildWidths;
                    iArr3[i10] = iArr3[i10] + 1;
                    i9--;
                }
            }
        }
        int i11 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.mTempChildWidths[i12], 1073741824), makeMeasureSpec);
                childAt.getLayoutParams().width = childAt.getMeasuredWidth();
                i11 += childAt.getMeasuredWidth();
            }
        }
        setMeasuredDimension(ViewCompat.resolveSizeAndState(i11, View.MeasureSpec.makeMeasureSpec(i11, 1073741824), 0), ViewCompat.resolveSizeAndState(this.mItemHeight, makeMeasureSpec, 0));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    childAt.layout((i5 - i7) - childAt.getMeasuredWidth(), 0, i5 - i7, i6);
                } else {
                    childAt.layout(i7, 0, childAt.getMeasuredWidth() + i7, i6);
                }
                i7 += childAt.getMeasuredWidth();
            }
        }
    }

    public int getWindowAnimations() {
        return 0;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.mItemIconTint = colorStateList;
        if (this.mButtons != null) {
            for (BottomNavigationItemView iconTintList : this.mButtons) {
                iconTintList.setIconTintList(colorStateList);
            }
        }
    }

    @Nullable
    public ColorStateList getIconTintList() {
        return this.mItemIconTint;
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.mItemTextColor = colorStateList;
        if (this.mButtons != null) {
            for (BottomNavigationItemView textColor : this.mButtons) {
                textColor.setTextColor(colorStateList);
            }
        }
    }

    public ColorStateList getItemTextColor() {
        return this.mItemTextColor;
    }

    public void setItemBackgroundRes(int i) {
        this.mItemBackgroundRes = i;
        if (this.mButtons != null) {
            for (BottomNavigationItemView itemBackground : this.mButtons) {
                itemBackground.setItemBackground(i);
            }
        }
    }

    public int getItemBackgroundRes() {
        return this.mItemBackgroundRes;
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.mPresenter = bottomNavigationPresenter;
    }

    public void buildMenuView() {
        boolean z;
        removeAllViews();
        if (this.mButtons != null) {
            for (BottomNavigationItemView release : this.mButtons) {
                this.mItemPool.release(release);
            }
        }
        if (this.mMenu.size() == 0) {
            this.mSelectedItemId = 0;
            this.mSelectedItemPosition = 0;
            this.mButtons = null;
            return;
        }
        this.mButtons = new BottomNavigationItemView[this.mMenu.size()];
        if (this.mMenu.size() > 3) {
            z = true;
        } else {
            z = false;
        }
        this.mShiftingMode = z;
        for (int i = 0; i < this.mMenu.size(); i++) {
            this.mPresenter.setUpdateSuspended(true);
            this.mMenu.getItem(i).setCheckable(true);
            this.mPresenter.setUpdateSuspended(false);
            BottomNavigationItemView newItem = getNewItem();
            this.mButtons[i] = newItem;
            newItem.setIconTintList(this.mItemIconTint);
            newItem.setTextColor(this.mItemTextColor);
            newItem.setItemBackground(this.mItemBackgroundRes);
            newItem.setShiftingMode(this.mShiftingMode);
            newItem.initialize((MenuItemImpl) this.mMenu.getItem(i), 0);
            newItem.setItemPosition(i);
            newItem.setOnClickListener(this.mOnClickListener);
            addView(newItem);
        }
        this.mSelectedItemPosition = Math.min(this.mMenu.size() - 1, this.mSelectedItemPosition);
        this.mMenu.getItem(this.mSelectedItemPosition).setChecked(true);
    }

    public void updateMenuView() {
        int size = this.mMenu.size();
        if (size != this.mButtons.length) {
            buildMenuView();
            return;
        }
        int i = this.mSelectedItemId;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.mMenu.getItem(i2);
            if (item.isChecked()) {
                this.mSelectedItemId = item.getItemId();
                this.mSelectedItemPosition = i2;
            }
        }
        if (i != this.mSelectedItemId) {
            this.mAnimationHelper.beginDelayedTransition(this);
        }
        for (int i3 = 0; i3 < size; i3++) {
            this.mPresenter.setUpdateSuspended(true);
            this.mButtons[i3].initialize((MenuItemImpl) this.mMenu.getItem(i3), 0);
            this.mPresenter.setUpdateSuspended(false);
        }
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView acquire = this.mItemPool.acquire();
        if (acquire == null) {
            return new BottomNavigationItemView(getContext());
        }
        return acquire;
    }

    public int getSelectedItemId() {
        return this.mSelectedItemId;
    }

    /* access modifiers changed from: package-private */
    public void tryRestoreSelectedItemId(int i) {
        int size = this.mMenu.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.mMenu.getItem(i2);
            if (i == item.getItemId()) {
                this.mSelectedItemId = i;
                this.mSelectedItemPosition = i2;
                item.setChecked(true);
                return;
            }
        }
    }
}
