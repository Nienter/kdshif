package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/* renamed from: com.squareup.picasso.m */
class ImageViewAction extends Action<ImageView> {

    /* renamed from: m */
    C1626e f2525m;

    ImageViewAction(Picasso vVar, ImageView imageView, C1645y yVar, int i, int i2, int i3, Drawable drawable, String str, Object obj, C1626e eVar, boolean z) {
        super(vVar, imageView, yVar, i, i2, i3, drawable, str, obj, z);
        this.f2525m = eVar;
    }

    /* renamed from: a */
    public void mo17463a(Bitmap bitmap, Picasso.C1640d dVar) {
        if (bitmap == null) {
            throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[]{this}));
        }
        ImageView imageView = (ImageView) this.f2416c.get();
        if (imageView != null) {
            Bitmap bitmap2 = bitmap;
            Picasso.C1640d dVar2 = dVar;
            PicassoDrawable.m3416a(imageView, this.f2414a.f2551c, bitmap2, dVar2, this.f2417d, this.f2414a.f2559k);
            if (this.f2525m != null) {
                this.f2525m.mo16960a();
            }
        }
    }

    /* renamed from: a */
    public void mo17464a(Exception exc) {
        ImageView imageView = (ImageView) this.f2416c.get();
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            if (this.f2420g != 0) {
                imageView.setImageResource(this.f2420g);
            } else if (this.f2421h != null) {
                imageView.setImageDrawable(this.f2421h);
            }
            if (this.f2525m != null) {
                this.f2525m.mo16961a(exc);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17462a() {
        super.mo17462a();
        if (this.f2525m != null) {
            this.f2525m = null;
        }
    }
}
