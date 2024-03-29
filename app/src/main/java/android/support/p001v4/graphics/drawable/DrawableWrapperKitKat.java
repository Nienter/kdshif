package android.support.p001v4.graphics.drawable;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.p001v4.graphics.drawable.DrawableWrapperGingerbread;

@TargetApi(19)
@RequiresApi(19)
/* renamed from: android.support.v4.graphics.drawable.DrawableWrapperKitKat */
class DrawableWrapperKitKat extends DrawableWrapperHoneycomb {

    /* renamed from: android.support.v4.graphics.drawable.DrawableWrapperKitKat$DrawableWrapperStateKitKat */
    private static class DrawableWrapperStateKitKat extends DrawableWrapperGingerbread.DrawableWrapperState {
        DrawableWrapperStateKitKat(@Nullable DrawableWrapperGingerbread.DrawableWrapperState drawableWrapperState, @Nullable Resources resources) {
            super(drawableWrapperState, resources);
        }

        public Drawable newDrawable(@Nullable Resources resources) {
            return new DrawableWrapperKitKat(this, resources);
        }
    }

    DrawableWrapperKitKat(Drawable drawable) {
        super(drawable);
    }

    DrawableWrapperKitKat(DrawableWrapperGingerbread.DrawableWrapperState drawableWrapperState, Resources resources) {
        super(drawableWrapperState, resources);
    }

    public void setAutoMirrored(boolean z) {
        this.mDrawable.setAutoMirrored(z);
    }

    public boolean isAutoMirrored() {
        return this.mDrawable.isAutoMirrored();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public DrawableWrapperGingerbread.DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperStateKitKat(this.mState, null);
    }
}
