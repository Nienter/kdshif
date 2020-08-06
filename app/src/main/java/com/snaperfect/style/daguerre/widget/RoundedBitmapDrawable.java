package com.snaperfect.style.daguerre.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.p001v4.view.GravityCompat;

/* renamed from: com.snaperfect.style.daguerre.widget.f */
public class RoundedBitmapDrawable extends Drawable {

    /* renamed from: a */
    Bitmap f2375a;

    /* renamed from: b */
    final Rect f2376b = new Rect();

    /* renamed from: c */
    final RectF f2377c = new RectF();

    /* renamed from: d */
    private int f2378d = 160;

    /* renamed from: e */
    private int f2379e = 119;

    /* renamed from: f */
    private Paint f2380f = new Paint(6);

    /* renamed from: g */
    private BitmapShader f2381g;

    /* renamed from: h */
    private float f2382h;

    /* renamed from: i */
    private boolean f2383i = true;

    /* renamed from: j */
    private int f2384j;

    /* renamed from: k */
    private int f2385k;

    /* renamed from: b */
    private void m3194b() {
        this.f2384j = this.f2375a.getScaledWidth(this.f2378d);
        this.f2385k = this.f2375a.getScaledHeight(this.f2378d);
    }

    /* renamed from: a */
    public void mo17420a(boolean z) {
        this.f2380f.setAntiAlias(z);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean z) {
        this.f2380f.setFilterBitmap(z);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.f2380f.setDither(z);
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17419a(int i, int i2, int i3, Rect rect, Rect rect2) {
        GravityCompat.apply(i, i2, i3, rect, rect2, 0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17417a() {
        if (this.f2383i) {
            mo17419a(this.f2379e, this.f2384j, this.f2385k, getBounds(), this.f2376b);
            this.f2377c.set(this.f2376b);
            this.f2383i = false;
        }
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.f2375a;
        if (bitmap != null) {
            mo17417a();
            Paint paint = this.f2380f;
            if (paint.getShader() == null) {
                canvas.drawBitmap(bitmap, null, this.f2376b, paint);
            } else {
                canvas.drawRoundRect(this.f2377c, this.f2382h, this.f2382h, paint);
            }
        }
    }

    public void setAlpha(int i) {
        if (i != this.f2380f.getAlpha()) {
            this.f2380f.setAlpha(i);
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.f2380f.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f2380f.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public ColorFilter getColorFilter() {
        return this.f2380f.getColorFilter();
    }

    /* renamed from: a */
    public void mo17418a(float f) {
        if (m3195b(f)) {
            this.f2380f.setShader(this.f2381g);
        } else {
            this.f2380f.setShader(null);
        }
        this.f2382h = f;
    }

    public int getIntrinsicWidth() {
        return this.f2384j;
    }

    public int getIntrinsicHeight() {
        return this.f2385k;
    }

    public int getOpacity() {
        if (this.f2379e != 119) {
            return -3;
        }
        Bitmap bitmap = this.f2375a;
        if (bitmap == null || bitmap.hasAlpha() || this.f2380f.getAlpha() < 255 || m3195b(this.f2382h)) {
            return -3;
        }
        return -1;
    }

    public RoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
        if (resources != null) {
            this.f2378d = resources.getDisplayMetrics().densityDpi;
        }
        this.f2375a = bitmap;
        if (this.f2375a != null) {
            m3194b();
            this.f2381g = new BitmapShader(this.f2375a, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            return;
        }
        this.f2385k = -1;
        this.f2384j = -1;
    }

    /* renamed from: b */
    private static boolean m3195b(float f) {
        return Float.compare(f, 0.0f) > 0;
    }
}
