package android.support.design.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.C0030R;
import android.support.design.widget.BottomSheetBehavior;
import android.support.p001v4.view.AccessibilityDelegateCompat;
import android.support.p001v4.view.ViewCompat;
import android.support.p001v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p004v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class BottomSheetDialog extends AppCompatDialog {
    private BottomSheetBehavior<FrameLayout> mBehavior;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback;
    boolean mCancelable;
    private boolean mCanceledOnTouchOutside;
    private boolean mCanceledOnTouchOutsideSet;

    public BottomSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public BottomSheetDialog(@NonNull Context context, @StyleRes int i) {
        super(context, getThemeResId(context, i));
        this.mCancelable = true;
        this.mCanceledOnTouchOutside = true;
        this.mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            public void onStateChanged(@NonNull View view, int i) {
                if (i == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }

            public void onSlide(@NonNull View view, float f) {
            }
        };
        supportRequestWindowFeature(1);
    }

    protected BottomSheetDialog(@NonNull Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mCancelable = true;
        this.mCanceledOnTouchOutside = true;
        this.mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            public void onStateChanged(@NonNull View view, int i) {
                if (i == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }

            public void onSlide(@NonNull View view, float f) {
            }
        };
        supportRequestWindowFeature(1);
        this.mCancelable = z;
    }

    public void setContentView(@LayoutRes int i) {
        super.setContentView(wrapInBottomSheet(i, null, null));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setLayout(-1, -1);
    }

    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(wrapInBottomSheet(0, view, layoutParams));
    }

    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.mCancelable != z) {
            this.mCancelable = z;
            if (this.mBehavior != null) {
                this.mBehavior.setHideable(z);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.mBehavior != null) {
            this.mBehavior.setState(4);
        }
    }

    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.mCancelable) {
            this.mCancelable = true;
        }
        this.mCanceledOnTouchOutside = z;
        this.mCanceledOnTouchOutsideSet = true;
    }

    private View wrapInBottomSheet(int i, View view, ViewGroup.LayoutParams layoutParams) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) View.inflate(getContext(), C0030R.layout.design_bottom_sheet_dialog, null);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, coordinatorLayout, false);
        }
        FrameLayout frameLayout = (FrameLayout) coordinatorLayout.findViewById(C0030R.C0032id.design_bottom_sheet);
        this.mBehavior = BottomSheetBehavior.from(frameLayout);
        this.mBehavior.setBottomSheetCallback(this.mBottomSheetCallback);
        this.mBehavior.setHideable(this.mCancelable);
        if (layoutParams == null) {
            frameLayout.addView(view);
        } else {
            frameLayout.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(C0030R.C0032id.touch_outside).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BottomSheetDialog.this.mCancelable && BottomSheetDialog.this.isShowing() && BottomSheetDialog.this.shouldWindowCloseOnTouchOutside()) {
                    BottomSheetDialog.this.cancel();
                }
            }
        });
        ViewCompat.setAccessibilityDelegate(frameLayout, new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                if (BottomSheetDialog.this.mCancelable) {
                    accessibilityNodeInfoCompat.addAction(1048576);
                    accessibilityNodeInfoCompat.setDismissable(true);
                    return;
                }
                accessibilityNodeInfoCompat.setDismissable(false);
            }

            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != 1048576 || !BottomSheetDialog.this.mCancelable) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                BottomSheetDialog.this.cancel();
                return true;
            }
        });
        return coordinatorLayout;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldWindowCloseOnTouchOutside() {
        if (!this.mCanceledOnTouchOutsideSet) {
            if (Build.VERSION.SDK_INT < 11) {
                this.mCanceledOnTouchOutside = true;
            } else {
                TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{16843611});
                this.mCanceledOnTouchOutside = obtainStyledAttributes.getBoolean(0, true);
                obtainStyledAttributes.recycle();
            }
            this.mCanceledOnTouchOutsideSet = true;
        }
        return this.mCanceledOnTouchOutside;
    }

    private static int getThemeResId(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(C0030R.attr.bottomSheetDialogTheme, typedValue, true)) {
            return typedValue.resourceId;
        }
        return C0030R.C0033style.Theme_Design_Light_BottomSheetDialog;
    }
}
