package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/* renamed from: com.squareup.picasso.w */
final class PicassoDrawable extends BitmapDrawable {

    /* renamed from: e */
    private static final Paint f2583e = new Paint();

    /* renamed from: a */
    Drawable f2584a;

    /* renamed from: b */
    long f2585b;

    /* renamed from: c */
    boolean f2586c;

    /* renamed from: d */
    int f2587d = 255;

    /* renamed from: f */
    private final boolean f2588f;

    /* renamed from: g */
    private final float f2589g;

    /* renamed from: h */
    private final Picasso.C1640d f2590h;

    /* renamed from: a */
    static void m3416a(ImageView imageView, Context context, Bitmap bitmap, Picasso.C1640d dVar, boolean z, boolean z2) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
        imageView.setImageDrawable(new PicassoDrawable(context, bitmap, drawable, dVar, z, z2));
    }

    /* renamed from: a */
    static void m3417a(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
        if (imageView.getDrawable() instanceof AnimationDrawable) {
            ((AnimationDrawable) imageView.getDrawable()).start();
        }
    }

    PicassoDrawable(Context context, Bitmap bitmap, Drawable drawable, Picasso.C1640d dVar, boolean z, boolean z2) {
        super(context.getResources(), bitmap);
        this.f2588f = z2;
        this.f2589g = context.getResources().getDisplayMetrics().density;
        this.f2590h = dVar;
        if (dVar != Picasso.C1640d.MEMORY && !z) {
            this.f2584a = drawable;
            this.f2586c = true;
            this.f2585b = SystemClock.uptimeMillis();
        }
    }

    public void draw(Canvas canvas) {
        if (!this.f2586c) {
            super.draw(canvas);
        } else {
            float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.f2585b)) / 200.0f;
            if (uptimeMillis >= 1.0f) {
                this.f2586c = false;
                this.f2584a = null;
                super.draw(canvas);
            } else {
                if (this.f2584a != null) {
                    this.f2584a.draw(canvas);
                }
                super.setAlpha((int) (uptimeMillis * ((float) this.f2587d)));
                super.draw(canvas);
                super.setAlpha(this.f2587d);
            }
        }
        if (this.f2588f) {
            m3415a(canvas);
        }
    }

    public void setAlpha(int i) {
        this.f2587d = i;
        if (this.f2584a != null) {
            this.f2584a.setAlpha(i);
        }
        super.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.f2584a != null) {
            this.f2584a.setColorFilter(colorFilter);
        }
        super.setColorFilter(colorFilter);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (this.f2584a != null) {
            this.f2584a.setBounds(rect);
        }
        super.onBoundsChange(rect);
    }

    /* renamed from: a */
    private void m3415a(Canvas canvas) {
        f2583e.setColor(-1);
        canvas.drawPath(m3414a(0, 0, (int) (16.0f * this.f2589g)), f2583e);
        f2583e.setColor(this.f2590h.debugColor);
        canvas.drawPath(m3414a(0, 0, (int) (15.0f * this.f2589g)), f2583e);
    }

    /* renamed from: a */
    private static Path m3414a(int i, int i2, int i3) {
        Path path = new Path();
        path.moveTo((float) i, (float) i2);
        path.lineTo((float) (i + i3), (float) i2);
        path.lineTo((float) i, (float) (i2 + i3));
        return path;
    }
}
