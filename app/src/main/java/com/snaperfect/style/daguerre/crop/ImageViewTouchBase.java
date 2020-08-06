package com.snaperfect.style.daguerre.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.p004v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;

public abstract class ImageViewTouchBase extends AppCompatImageView {

    /* renamed from: a */
    private final Matrix f1961a = new Matrix();

    /* renamed from: b */
    private final float[] f1962b = new float[9];

    /* renamed from: c */
    private C1527a f1963c;

    /* renamed from: d */
    private Runnable f1964d = null;

    /* renamed from: f */
    protected Matrix f1965f = new Matrix();

    /* renamed from: g */
    protected Matrix f1966g = new Matrix();

    /* renamed from: h */
    protected final RotateBitmap f1967h = new RotateBitmap(null);

    /* renamed from: i */
    int f1968i = -1;

    /* renamed from: j */
    int f1969j = -1;

    /* renamed from: k */
    float f1970k;

    /* renamed from: l */
    protected int f1971l = 0;

    /* renamed from: m */
    protected Handler f1972m = new Handler();

    /* renamed from: com.snaperfect.style.daguerre.crop.ImageViewTouchBase$a */
    public interface C1527a {
        /* renamed from: a */
        void mo17063a(Bitmap bitmap);
    }

    public void setRecycler(C1527a aVar) {
        this.f1963c = aVar;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f1968i = i3 - i;
        this.f1969j = i4 - i2;
        Runnable runnable = this.f1964d;
        if (runnable != null) {
            this.f1964d = null;
            runnable.run();
        }
        if (this.f1967h.mo17091b() != null) {
            m2705a(this.f1967h, this.f1965f);
            setImageMatrix(getImageViewMatrix());
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || getScale() <= 1.0f) {
            return super.onKeyDown(i, keyEvent);
        }
        mo17051a(1.0f);
        return true;
    }

    public void setImageBitmap(Bitmap bitmap) {
        m2704a(bitmap, 0);
    }

    /* renamed from: a */
    private void m2704a(Bitmap bitmap, int i) {
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setDither(true);
        }
        Bitmap b = this.f1967h.mo17091b();
        this.f1967h.mo17090a(bitmap);
        this.f1967h.mo17089a(i);
        if (b != null && b != bitmap && this.f1963c != null) {
            this.f1963c.mo17063a(b);
        }
    }

    /* renamed from: a */
    public void mo17053a(Bitmap bitmap, boolean z) {
        mo17054a(new RotateBitmap(bitmap), z);
    }

    /* renamed from: a */
    public void mo17054a(final RotateBitmap eVar, final boolean z) {
        if (getWidth() <= 0) {
            this.f1964d = new Runnable() {
                public void run() {
                    ImageViewTouchBase.this.mo17054a(eVar, z);
                }
            };
            return;
        }
        if (eVar.mo17091b() != null) {
            m2705a(eVar, this.f1965f);
            m2704a(eVar.mo17091b(), eVar.mo17088a());
        } else {
            this.f1965f.reset();
            setImageBitmap(null);
        }
        if (z) {
            this.f1966g.reset();
        }
        setImageMatrix(getImageViewMatrix());
        this.f1970k = mo17048a();
    }

    /* renamed from: a */
    public void mo17055a(boolean z, boolean z2) {
        if (this.f1967h.mo17091b() != null) {
            Matrix imageViewMatrix = getImageViewMatrix();
            RectF rectF = new RectF(0.0f, 0.0f, (float) this.f1967h.mo17091b().getWidth(), (float) this.f1967h.mo17091b().getHeight());
            imageViewMatrix.mapRect(rectF);
            rectF.height();
            rectF.width();
            if (z2) {
                float top = ((float) ((getTop() + getBottom()) / 2)) - rectF.centerY();
            }
            if (z) {
                float left = ((float) ((getLeft() + getRight()) / 2)) - rectF.centerX();
            }
            setImageMatrix(getImageViewMatrix());
        }
    }

    public ImageViewTouchBase(Context context) {
        super(context);
        m2706b();
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m2706b();
    }

    /* renamed from: b */
    private void m2706b() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public float mo17050a(Matrix matrix, int i) {
        matrix.getValues(this.f1962b);
        return this.f1962b[i];
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public float mo17049a(Matrix matrix) {
        return mo17050a(matrix, 0);
    }

    public float getScale() {
        return mo17049a(this.f1966g);
    }

    /* renamed from: a */
    private void m2705a(RotateBitmap eVar, Matrix matrix) {
        float width = (float) ((getWidth() - getPaddingLeft()) - getPaddingRight());
        float height = (float) ((getHeight() - getPaddingTop()) - getPaddingBottom());
        float f = (float) eVar.mo17095f();
        float e = (float) eVar.mo17094e();
        matrix.reset();
        float min = Math.min(Math.min(width / f, 2.0f), Math.min(height / e, 2.0f));
        matrix.postConcat(eVar.mo17092c());
        matrix.postScale(min, min);
        matrix.postTranslate((width - (f * min)) / 2.0f, (height - (e * min)) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public Matrix getImageViewMatrix() {
        this.f1961a.set(this.f1965f);
        this.f1961a.postConcat(this.f1966g);
        return this.f1961a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public float mo17048a() {
        if (this.f1967h.mo17091b() == null) {
            return 1.0f;
        }
        return Math.max(Math.max(((float) this.f1967h.mo17095f()) / ((float) this.f1968i), ((float) this.f1967h.mo17094e()) / ((float) this.f1969j)) * 4.0f, 1.0f);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17041a(float f, float f2, float f3, float f4, float f5) {
        if (f > this.f1970k) {
            f = this.f1970k;
        }
        float scale = f / getScale();
        this.f1966g.postScale(scale, scale, f2, f3);
        this.f1966g.postTranslate(f4, f5);
        setImageMatrix(getImageViewMatrix());
        mo17055a(true, true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17052a(float f, float f2, float f3, float f4, Point point) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17051a(float f) {
        mo17041a(f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, 0.0f, 0.0f);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17040a(float f, float f2) {
        this.f1966g.postTranslate(f, f2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo17056b(float f, float f2) {
        mo17040a(f, f2);
        setImageMatrix(getImageViewMatrix());
    }
}
