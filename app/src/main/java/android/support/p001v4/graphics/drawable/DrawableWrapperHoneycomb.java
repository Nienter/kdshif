package android.support.p001v4.graphics.drawable;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.p001v4.graphics.drawable.DrawableWrapperGingerbread;

@TargetApi(11)
@RequiresApi(11)
/* renamed from: android.support.v4.graphics.drawable.DrawableWrapperHoneycomb */
class DrawableWrapperHoneycomb extends DrawableWrapperGingerbread {

    /* renamed from: android.support.v4.graphics.drawable.DrawableWrapperHoneycomb$DrawableWrapperStateHoneycomb */
    private static class DrawableWrapperStateHoneycomb extends DrawableWrapperGingerbread.DrawableWrapperState {
        DrawableWrapperStateHoneycomb(@Nullable DrawableWrapperGingerbread.DrawableWrapperState drawableWrapperState, @Nullable Resources resources) {
            super(drawableWrapperState, resources);
        }

        public Drawable newDrawable(@Nullable Resources resources) {
            return new DrawableWrapperHoneycomb(this, resources);
        }
    }

    DrawableWrapperHoneycomb(Drawable drawable) {
        super(drawable);
    }

    DrawableWrapperHoneycomb(DrawableWrapperGingerbread.DrawableWrapperState drawableWrapperState, Resources resources) {
        super(drawableWrapperState, resources);
    }

    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public DrawableWrapperGingerbread.DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperStateHoneycomb(this.mState, null);
    }
}
