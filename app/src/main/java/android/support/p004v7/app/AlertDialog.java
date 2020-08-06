package android.support.p004v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.p004v7.app.AlertController;
import android.support.p004v7.appcompat.C0309R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/* renamed from: android.support.v7.app.AlertDialog */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    /* renamed from: android.support.v7.app.AlertDialog$Builder */
    public static class Builder {

        /* renamed from: P */
        private final AlertController.AlertParams f84P;
        private final int mTheme;

        public Builder(@NonNull Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(@NonNull Context context, @StyleRes int i) {
            this.f84P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i)));
            this.mTheme = i;
        }

        @NonNull
        public Context getContext() {
            return this.f84P.mContext;
        }

        public Builder setTitle(@StringRes int i) {
            this.f84P.mTitle = this.f84P.mContext.getText(i);
            return this;
        }

        public Builder setTitle(@Nullable CharSequence charSequence) {
            this.f84P.mTitle = charSequence;
            return this;
        }

        public Builder setCustomTitle(@Nullable View view) {
            this.f84P.mCustomTitleView = view;
            return this;
        }

        public Builder setMessage(@StringRes int i) {
            this.f84P.mMessage = this.f84P.mContext.getText(i);
            return this;
        }

        public Builder setMessage(@Nullable CharSequence charSequence) {
            this.f84P.mMessage = charSequence;
            return this;
        }

        public Builder setIcon(@DrawableRes int i) {
            this.f84P.mIconId = i;
            return this;
        }

        public Builder setIcon(@Nullable Drawable drawable) {
            this.f84P.mIcon = drawable;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int i) {
            TypedValue typedValue = new TypedValue();
            this.f84P.mContext.getTheme().resolveAttribute(i, typedValue, true);
            this.f84P.mIconId = typedValue.resourceId;
            return this;
        }

        public Builder setPositiveButton(@StringRes int i, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mPositiveButtonText = this.f84P.mContext.getText(i);
            this.f84P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mPositiveButtonText = charSequence;
            this.f84P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(@StringRes int i, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mNegativeButtonText = this.f84P.mContext.getText(i);
            this.f84P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mNegativeButtonText = charSequence;
            this.f84P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(@StringRes int i, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mNeutralButtonText = this.f84P.mContext.getText(i);
            this.f84P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mNeutralButtonText = charSequence;
            this.f84P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.f84P.mCancelable = z;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f84P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.f84P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f84P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(@ArrayRes int i, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mItems = this.f84P.mContext.getResources().getTextArray(i);
            this.f84P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mItems = charSequenceArr;
            this.f84P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mAdapter = listAdapter;
            this.f84P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            this.f84P.mCursor = cursor;
            this.f84P.mLabelColumn = str;
            this.f84P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int i, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f84P.mItems = this.f84P.mContext.getResources().getTextArray(i);
            this.f84P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f84P.mCheckedItems = zArr;
            this.f84P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f84P.mItems = charSequenceArr;
            this.f84P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f84P.mCheckedItems = zArr;
            this.f84P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f84P.mCursor = cursor;
            this.f84P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f84P.mIsCheckedColumn = str;
            this.f84P.mLabelColumn = str2;
            this.f84P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int i, int i2, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mItems = this.f84P.mContext.getResources().getTextArray(i);
            this.f84P.mOnClickListener = onClickListener;
            this.f84P.mCheckedItem = i2;
            this.f84P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int i, String str, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mCursor = cursor;
            this.f84P.mOnClickListener = onClickListener;
            this.f84P.mCheckedItem = i;
            this.f84P.mLabelColumn = str;
            this.f84P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mItems = charSequenceArr;
            this.f84P.mOnClickListener = onClickListener;
            this.f84P.mCheckedItem = i;
            this.f84P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            this.f84P.mAdapter = listAdapter;
            this.f84P.mOnClickListener = onClickListener;
            this.f84P.mCheckedItem = i;
            this.f84P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.f84P.mOnItemSelectedListener = onItemSelectedListener;
            return this;
        }

        public Builder setView(int i) {
            this.f84P.mView = null;
            this.f84P.mViewLayoutResId = i;
            this.f84P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            this.f84P.mView = view;
            this.f84P.mViewLayoutResId = 0;
            this.f84P.mViewSpacingSpecified = false;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Deprecated
        public Builder setView(View view, int i, int i2, int i3, int i4) {
            this.f84P.mView = view;
            this.f84P.mViewLayoutResId = 0;
            this.f84P.mViewSpacingSpecified = true;
            this.f84P.mViewSpacingLeft = i;
            this.f84P.mViewSpacingTop = i2;
            this.f84P.mViewSpacingRight = i3;
            this.f84P.mViewSpacingBottom = i4;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean z) {
            this.f84P.mForceInverseBackground = z;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setRecycleOnMeasureEnabled(boolean z) {
            this.f84P.mRecycleOnMeasure = z;
            return this;
        }

        public AlertDialog create() {
            AlertDialog alertDialog = new AlertDialog(this.f84P.mContext, this.mTheme);
            this.f84P.apply(alertDialog.mAlert);
            alertDialog.setCancelable(this.f84P.mCancelable);
            if (this.f84P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f84P.mOnCancelListener);
            alertDialog.setOnDismissListener(this.f84P.mOnDismissListener);
            if (this.f84P.mOnKeyListener != null) {
                alertDialog.setOnKeyListener(this.f84P.mOnKeyListener);
            }
            return alertDialog;
        }

        public AlertDialog show() {
            AlertDialog create = create();
            create.show();
            return create;
        }
    }

    protected AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    protected AlertDialog(@NonNull Context context, @StyleRes int i) {
        super(context, resolveDialogTheme(context, i));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        this(context, 0);
        setCancelable(z);
        setOnCancelListener(onCancelListener);
    }

    static int resolveDialogTheme(@NonNull Context context, @StyleRes int i) {
        if (i >= 16777216) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0309R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button getButton(int i) {
        return this.mAlert.getButton(i);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.setTitle(charSequence);
    }

    public void setCustomTitle(View view) {
        this.mAlert.setCustomTitle(view);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.setMessage(charSequence);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.mAlert.setView(view, i, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    public void setButtonPanelLayoutHint(int i) {
        this.mAlert.setButtonPanelLayoutHint(i);
    }

    public void setButton(int i, CharSequence charSequence, Message message) {
        this.mAlert.setButton(i, charSequence, null, message);
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.setButton(i, charSequence, onClickListener, null);
    }

    public void setIcon(int i) {
        this.mAlert.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        this.mAlert.setIcon(drawable);
    }

    public void setIconAttribute(int i) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(i, typedValue, true);
        this.mAlert.setIcon(typedValue.resourceId);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mAlert.onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mAlert.onKeyUp(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }
}
