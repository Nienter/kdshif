package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.design.C0030R;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.p001v4.content.ContextCompat;
import android.support.p001v4.p003os.ParcelableCompat;
import android.support.p001v4.p003os.ParcelableCompatCreatorCallbacks;
import android.support.p001v4.view.AbsSavedState;
import android.support.p001v4.view.ViewCompat;
import android.support.p001v4.view.WindowInsetsCompat;
import android.support.p004v7.appcompat.C0309R;
import android.support.p004v7.content.res.AppCompatResources;
import android.support.p004v7.view.SupportMenuInflater;
import android.support.p004v7.view.menu.MenuBuilder;
import android.support.p004v7.view.menu.MenuItemImpl;
import android.support.p004v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    OnNavigationItemSelectedListener mListener;
    private int mMaxWidth;
    private final NavigationMenu mMenu;
    private MenuInflater mMenuInflater;
    private final NavigationMenuPresenter mPresenter;

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        });
        public Bundle menuState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuState);
        }
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ColorStateList createDefaultColorStateList;
        int i2;
        boolean z;
        this.mPresenter = new NavigationMenuPresenter();
        ThemeUtils.checkAppCompatTheme(context);
        this.mMenu = new NavigationMenu(context);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0030R.styleable.NavigationView, i, C0030R.C0033style.Widget_Design_NavigationView);
        ViewCompat.setBackground(this, obtainStyledAttributes.getDrawable(C0030R.styleable.NavigationView_android_background));
        if (obtainStyledAttributes.hasValue(C0030R.styleable.NavigationView_elevation)) {
            ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(C0030R.styleable.NavigationView_elevation, 0));
        }
        ViewCompat.setFitsSystemWindows(this, obtainStyledAttributes.getBoolean(C0030R.styleable.NavigationView_android_fitsSystemWindows, false));
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(C0030R.styleable.NavigationView_android_maxWidth, 0);
        if (obtainStyledAttributes.hasValue(C0030R.styleable.NavigationView_itemIconTint)) {
            createDefaultColorStateList = obtainStyledAttributes.getColorStateList(C0030R.styleable.NavigationView_itemIconTint);
        } else {
            createDefaultColorStateList = createDefaultColorStateList(16842808);
        }
        if (obtainStyledAttributes.hasValue(C0030R.styleable.NavigationView_itemTextAppearance)) {
            i2 = obtainStyledAttributes.getResourceId(C0030R.styleable.NavigationView_itemTextAppearance, 0);
            z = true;
        } else {
            i2 = 0;
            z = false;
        }
        ColorStateList colorStateList = null;
        colorStateList = obtainStyledAttributes.hasValue(C0030R.styleable.NavigationView_itemTextColor) ? obtainStyledAttributes.getColorStateList(C0030R.styleable.NavigationView_itemTextColor) : colorStateList;
        if (!z && colorStateList == null) {
            colorStateList = createDefaultColorStateList(16842806);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(C0030R.styleable.NavigationView_itemBackground);
        this.mMenu.setCallback(new MenuBuilder.Callback() {
            public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
                return NavigationView.this.mListener != null && NavigationView.this.mListener.onNavigationItemSelected(menuItem);
            }

            public void onMenuModeChange(MenuBuilder menuBuilder) {
            }
        });
        this.mPresenter.setId(1);
        this.mPresenter.initForMenu(context, this.mMenu);
        this.mPresenter.setItemIconTintList(createDefaultColorStateList);
        if (z) {
            this.mPresenter.setItemTextAppearance(i2);
        }
        this.mPresenter.setItemTextColor(colorStateList);
        this.mPresenter.setItemBackground(drawable);
        this.mMenu.addMenuPresenter(this.mPresenter);
        addView((View) this.mPresenter.getMenuView(this));
        if (obtainStyledAttributes.hasValue(C0030R.styleable.NavigationView_menu)) {
            inflateMenu(obtainStyledAttributes.getResourceId(C0030R.styleable.NavigationView_menu, 0));
        }
        if (obtainStyledAttributes.hasValue(C0030R.styleable.NavigationView_headerLayout)) {
            inflateHeaderView(obtainStyledAttributes.getResourceId(C0030R.styleable.NavigationView_headerLayout, 0));
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuState = new Bundle();
        this.mMenu.savePresenterStates(savedState.menuState);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mMenu.restorePresenterStates(savedState.menuState);
    }

    public void setNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.mListener = onNavigationItemSelectedListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        switch (View.MeasureSpec.getMode(i)) {
            case Integer.MIN_VALUE:
                i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), this.mMaxWidth), 1073741824);
                break;
            case 0:
                i = View.MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
                break;
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onInsetsChanged(WindowInsetsCompat windowInsetsCompat) {
        this.mPresenter.dispatchApplyWindowInsets(windowInsetsCompat);
    }

    public void inflateMenu(int i) {
        this.mPresenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i, this.mMenu);
        this.mPresenter.setUpdateSuspended(false);
        this.mPresenter.updateMenuView(false);
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public View inflateHeaderView(@LayoutRes int i) {
        return this.mPresenter.inflateHeaderView(i);
    }

    public void addHeaderView(@NonNull View view) {
        this.mPresenter.addHeaderView(view);
    }

    public void removeHeaderView(@NonNull View view) {
        this.mPresenter.removeHeaderView(view);
    }

    public int getHeaderCount() {
        return this.mPresenter.getHeaderCount();
    }

    public View getHeaderView(int i) {
        return this.mPresenter.getHeaderView(i);
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.mPresenter.getItemTintList();
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.mPresenter.setItemIconTintList(colorStateList);
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.mPresenter.getItemTextColor();
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.mPresenter.setItemTextColor(colorStateList);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.mPresenter.getItemBackground();
    }

    public void setItemBackgroundResource(@DrawableRes int i) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.mPresenter.setItemBackground(drawable);
    }

    public void setCheckedItem(@IdRes int i) {
        MenuItem findItem = this.mMenu.findItem(i);
        if (findItem != null) {
            this.mPresenter.setCheckedItem((MenuItemImpl) findItem);
        }
    }

    public void setItemTextAppearance(@StyleRes int i) {
        this.mPresenter.setItemTextAppearance(i);
    }

    private MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(getContext());
        }
        return this.mMenuInflater;
    }

    private ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(C0309R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        return new ColorStateList(new int[][]{DISABLED_STATE_SET, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(DISABLED_STATE_SET, defaultColor), i2, defaultColor});
    }
}
