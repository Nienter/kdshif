package android.support.p004v7.widget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.p004v7.appcompat.C0309R;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.AppCompatImageHelper */
public class AppCompatImageHelper {
    private final ImageView mView;

    public AppCompatImageHelper(ImageView imageView) {
        this.mView = imageView;
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TintTypedArray tintTypedArray = null;
        try {
            Drawable drawable = this.mView.getDrawable();
            if (drawable == null) {
                tintTypedArray = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, C0309R.styleable.AppCompatImageView, i, 0);
                int resourceId = tintTypedArray.getResourceId(C0309R.styleable.AppCompatImageView_srcCompat, -1);
                if (resourceId != -1) {
                    drawable = AppCompatResources.getDrawable(this.mView.getContext(), resourceId);
                    if (drawable != null) {
                        this.mView.setImageDrawable(drawable);
                    }
                }
            }
            if (drawable != null) {
                DrawableUtils.fixDrawable(drawable);
            }
        } finally {
            if (tintTypedArray != null) {
                tintTypedArray.recycle();
            }
        }
    }

    public void setImageResource(int i) {
        if (i != 0) {
            Drawable drawable = AppCompatResources.getDrawable(this.mView.getContext(), i);
            if (drawable != null) {
                DrawableUtils.fixDrawable(drawable);
            }
            this.mView.setImageDrawable(drawable);
            return;
        }
        this.mView.setImageDrawable(null);
    }

    /* access modifiers changed from: package-private */
    public boolean hasOverlappingRendering() {
        Drawable background = this.mView.getBackground();
        if (Build.VERSION.SDK_INT < 21 || !(background instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }
}
