package com.snaperfect.style.daguerre.widget;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.p001v4.view.ViewCompat;
import android.util.Log;
import android.widget.ImageView;
import java.util.HashSet;

/* renamed from: com.snaperfect.style.daguerre.widget.g */
public class RoundedDrawable extends Drawable {

    /* renamed from: a */
    private final RectF f2386a = new RectF();

    /* renamed from: b */
    private final RectF f2387b = new RectF();

    /* renamed from: c */
    private final RectF f2388c = new RectF();

    /* renamed from: d */
    private final Bitmap f2389d;

    /* renamed from: e */
    private final Paint f2390e;

    /* renamed from: f */
    private final int f2391f;

    /* renamed from: g */
    private final int f2392g;

    /* renamed from: h */
    private final RectF f2393h = new RectF();

    /* renamed from: i */
    private final Paint f2394i;

    /* renamed from: j */
    private final Matrix f2395j = new Matrix();

    /* renamed from: k */
    private final RectF f2396k = new RectF();

    /* renamed from: l */
    private Shader.TileMode f2397l = Shader.TileMode.CLAMP;

    /* renamed from: m */
    private Shader.TileMode f2398m = Shader.TileMode.CLAMP;

    /* renamed from: n */
    private boolean f2399n = true;

    /* renamed from: o */
    private float f2400o = 0.0f;

    /* renamed from: p */
    private final boolean[] f2401p = {true, true, true, true};

    /* renamed from: q */
    private boolean f2402q = false;

    /* renamed from: r */
    private float f2403r = 0.0f;

    /* renamed from: s */
    private ColorStateList f2404s = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);

    /* renamed from: t */
    private ImageView.ScaleType f2405t = ImageView.ScaleType.FIT_CENTER;

    /* renamed from: com.snaperfect.style.daguerre.widget.g$1 */
    /* compiled from: RoundedDrawable */
    static /* synthetic */ class C16031 {

        /* renamed from: a */
        static final /* synthetic */ int[] f2406a = new int[ImageView.ScaleType.values().length];

        static {
            try {
                f2406a[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2406a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2406a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2406a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f2406a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f2406a[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f2406a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public RoundedDrawable(Bitmap bitmap) {
        this.f2389d = bitmap;
        this.f2391f = bitmap.getWidth();
        this.f2392g = bitmap.getHeight();
        this.f2388c.set(0.0f, 0.0f, (float) this.f2391f, (float) this.f2392g);
        this.f2390e = new Paint();
        this.f2390e.setStyle(Paint.Style.FILL);
        this.f2390e.setAntiAlias(true);
        this.f2394i = new Paint();
        this.f2394i.setStyle(Paint.Style.STROKE);
        this.f2394i.setAntiAlias(true);
        this.f2394i.setColor(this.f2404s.getColorForState(getState(), ViewCompat.MEASURED_STATE_MASK));
        this.f2394i.setStrokeWidth(this.f2403r);
    }

    /* renamed from: a */
    public static RoundedDrawable m3201a(Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        }
        return null;
    }

    /* renamed from: a */
    public static Drawable m3200a(Drawable drawable) {
        if (drawable == null || (drawable instanceof RoundedDrawable)) {
            return drawable;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), m3200a(layerDrawable.getDrawable(i)));
            }
            return layerDrawable;
        }
        Bitmap b = m3205b(drawable);
        if (b != null) {
            return new RoundedDrawable(b);
        }
        return drawable;
    }

    /* renamed from: b */
    public static Bitmap m3205b(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("RoundedDrawable", "Failed to create bitmap from drawable!");
            return null;
        }
    }

    public boolean isStateful() {
        return this.f2404s.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int colorForState = this.f2404s.getColorForState(iArr, 0);
        if (this.f2394i.getColor() == colorForState) {
            return super.onStateChange(iArr);
        }
        this.f2394i.setColor(colorForState);
        return true;
    }

    /* renamed from: a */
    private void m3202a() {
        float min;
        float width;
        float f;
        float f2 = 0.0f;
        switch (C16031.f2406a[this.f2405t.ordinal()]) {
            case 1:
                this.f2393h.set(this.f2386a);
                this.f2393h.inset(this.f2403r / 2.0f, this.f2403r / 2.0f);
                this.f2395j.reset();
                this.f2395j.setTranslate((float) ((int) (((this.f2393h.width() - ((float) this.f2391f)) * 0.5f) + 0.5f)), (float) ((int) (((this.f2393h.height() - ((float) this.f2392g)) * 0.5f) + 0.5f)));
                break;
            case 2:
                this.f2393h.set(this.f2386a);
                this.f2393h.inset(this.f2403r / 2.0f, this.f2403r / 2.0f);
                this.f2395j.reset();
                if (((float) this.f2391f) * this.f2393h.height() > this.f2393h.width() * ((float) this.f2392g)) {
                    width = this.f2393h.height() / ((float) this.f2392g);
                    f = (this.f2393h.width() - (((float) this.f2391f) * width)) * 0.5f;
                } else {
                    width = this.f2393h.width() / ((float) this.f2391f);
                    f = 0.0f;
                    f2 = (this.f2393h.height() - (((float) this.f2392g) * width)) * 0.5f;
                }
                this.f2395j.setScale(width, width);
                this.f2395j.postTranslate(((float) ((int) (f + 0.5f))) + (this.f2403r / 2.0f), ((float) ((int) (f2 + 0.5f))) + (this.f2403r / 2.0f));
                break;
            case 3:
                this.f2395j.reset();
                if (((float) this.f2391f) > this.f2386a.width() || ((float) this.f2392g) > this.f2386a.height()) {
                    min = Math.min(this.f2386a.width() / ((float) this.f2391f), this.f2386a.height() / ((float) this.f2392g));
                } else {
                    min = 1.0f;
                }
                this.f2395j.setScale(min, min);
                this.f2395j.postTranslate((float) ((int) (((this.f2386a.width() - (((float) this.f2391f) * min)) * 0.5f) + 0.5f)), (float) ((int) (((this.f2386a.height() - (((float) this.f2392g) * min)) * 0.5f) + 0.5f)));
                this.f2393h.set(this.f2388c);
                this.f2395j.mapRect(this.f2393h);
                this.f2393h.inset(this.f2403r / 2.0f, this.f2403r / 2.0f);
                this.f2395j.setRectToRect(this.f2388c, this.f2393h, Matrix.ScaleToFit.FILL);
                break;
            case 5:
                this.f2393h.set(this.f2388c);
                this.f2395j.setRectToRect(this.f2388c, this.f2386a, Matrix.ScaleToFit.END);
                this.f2395j.mapRect(this.f2393h);
                this.f2393h.inset(this.f2403r / 2.0f, this.f2403r / 2.0f);
                this.f2395j.setRectToRect(this.f2388c, this.f2393h, Matrix.ScaleToFit.FILL);
                break;
            case 6:
                this.f2393h.set(this.f2388c);
                this.f2395j.setRectToRect(this.f2388c, this.f2386a, Matrix.ScaleToFit.START);
                this.f2395j.mapRect(this.f2393h);
                this.f2393h.inset(this.f2403r / 2.0f, this.f2403r / 2.0f);
                this.f2395j.setRectToRect(this.f2388c, this.f2393h, Matrix.ScaleToFit.FILL);
                break;
            case 7:
                this.f2393h.set(this.f2386a);
                this.f2393h.inset(this.f2403r / 2.0f, this.f2403r / 2.0f);
                this.f2395j.reset();
                this.f2395j.setRectToRect(this.f2388c, this.f2393h, Matrix.ScaleToFit.FILL);
                break;
            default:
                this.f2393h.set(this.f2388c);
                this.f2395j.setRectToRect(this.f2388c, this.f2386a, Matrix.ScaleToFit.CENTER);
                this.f2395j.mapRect(this.f2393h);
                this.f2393h.inset(this.f2403r / 2.0f, this.f2403r / 2.0f);
                this.f2395j.setRectToRect(this.f2388c, this.f2393h, Matrix.ScaleToFit.FILL);
                break;
        }
        this.f2387b.set(this.f2393h);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(@NonNull Rect rect) {
        super.onBoundsChange(rect);
        this.f2386a.set(rect);
        m3202a();
    }

    public void draw(@NonNull Canvas canvas) {
        if (this.f2399n) {
            BitmapShader bitmapShader = new BitmapShader(this.f2389d, this.f2397l, this.f2398m);
            if (this.f2397l == Shader.TileMode.CLAMP && this.f2398m == Shader.TileMode.CLAMP) {
                bitmapShader.setLocalMatrix(this.f2395j);
            }
            this.f2390e.setShader(bitmapShader);
            this.f2399n = false;
        }
        if (this.f2402q) {
            if (this.f2403r > 0.0f) {
                canvas.drawOval(this.f2387b, this.f2390e);
                canvas.drawOval(this.f2393h, this.f2394i);
                return;
            }
            canvas.drawOval(this.f2387b, this.f2390e);
        } else if (m3204a(this.f2401p)) {
            float f = this.f2400o;
            if (this.f2403r > 0.0f) {
                canvas.drawRoundRect(this.f2387b, f, f, this.f2390e);
                canvas.drawRoundRect(this.f2393h, f, f, this.f2394i);
                m3203a(canvas);
                m3206b(canvas);
                return;
            }
            canvas.drawRoundRect(this.f2387b, f, f, this.f2390e);
            m3203a(canvas);
        } else {
            canvas.drawRect(this.f2387b, this.f2390e);
            if (this.f2403r > 0.0f) {
                canvas.drawRect(this.f2393h, this.f2394i);
            }
        }
    }

    /* renamed from: a */
    private void m3203a(Canvas canvas) {
        if (!m3207b(this.f2401p) && this.f2400o != 0.0f) {
            float f = this.f2387b.left;
            float f2 = this.f2387b.top;
            float width = this.f2387b.width() + f;
            float height = this.f2387b.height() + f2;
            float f3 = this.f2400o;
            if (!this.f2401p[0]) {
                this.f2396k.set(f, f2, f + f3, f2 + f3);
                canvas.drawRect(this.f2396k, this.f2390e);
            }
            if (!this.f2401p[1]) {
                this.f2396k.set(width - f3, f2, width, f3);
                canvas.drawRect(this.f2396k, this.f2390e);
            }
            if (!this.f2401p[2]) {
                this.f2396k.set(width - f3, height - f3, width, height);
                canvas.drawRect(this.f2396k, this.f2390e);
            }
            if (!this.f2401p[3]) {
                this.f2396k.set(f, height - f3, f3 + f, height);
                canvas.drawRect(this.f2396k, this.f2390e);
            }
        }
    }

    /* renamed from: b */
    private void m3206b(Canvas canvas) {
        if (!m3207b(this.f2401p) && this.f2400o != 0.0f) {
            float f = this.f2387b.left;
            float f2 = this.f2387b.top;
            float width = f + this.f2387b.width();
            float height = f2 + this.f2387b.height();
            float f3 = this.f2400o;
            float f4 = this.f2403r / 2.0f;
            if (!this.f2401p[0]) {
                canvas.drawLine(f - f4, f2, f + f3, f2, this.f2394i);
                canvas.drawLine(f, f2 - f4, f, f2 + f3, this.f2394i);
            }
            if (!this.f2401p[1]) {
                canvas.drawLine((width - f3) - f4, f2, width, f2, this.f2394i);
                canvas.drawLine(width, f2 - f4, width, f2 + f3, this.f2394i);
            }
            if (!this.f2401p[2]) {
                canvas.drawLine((width - f3) - f4, height, width + f4, height, this.f2394i);
                canvas.drawLine(width, height - f3, width, height, this.f2394i);
            }
            if (!this.f2401p[3]) {
                canvas.drawLine(f - f4, height, f + f3, height, this.f2394i);
                canvas.drawLine(f, height - f3, f, height, this.f2394i);
            }
        }
    }

    public int getOpacity() {
        return -3;
    }

    public int getAlpha() {
        return this.f2390e.getAlpha();
    }

    public void setAlpha(int i) {
        this.f2390e.setAlpha(i);
        invalidateSelf();
    }

    public ColorFilter getColorFilter() {
        return this.f2390e.getColorFilter();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f2390e.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.f2390e.setDither(z);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean z) {
        this.f2390e.setFilterBitmap(z);
        invalidateSelf();
    }

    public int getIntrinsicWidth() {
        return this.f2391f;
    }

    public int getIntrinsicHeight() {
        return this.f2392g;
    }

    /* renamed from: a */
    public RoundedDrawable mo17432a(float f, float f2, float f3, float f4) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        HashSet hashSet = new HashSet(4);
        hashSet.add(Float.valueOf(f));
        hashSet.add(Float.valueOf(f2));
        hashSet.add(Float.valueOf(f3));
        hashSet.add(Float.valueOf(f4));
        hashSet.remove(Float.valueOf(0.0f));
        if (hashSet.size() > 1) {
            throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
        }
        if (!hashSet.isEmpty()) {
            float floatValue = ((Float) hashSet.iterator().next()).floatValue();
            if (Float.isInfinite(floatValue) || Float.isNaN(floatValue) || floatValue < 0.0f) {
                throw new IllegalArgumentException("Invalid radius value: " + floatValue);
            }
            this.f2400o = floatValue;
        } else {
            this.f2400o = 0.0f;
        }
        boolean[] zArr = this.f2401p;
        if (f > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        zArr[0] = z;
        boolean[] zArr2 = this.f2401p;
        if (f2 > 0.0f) {
            z2 = true;
        } else {
            z2 = false;
        }
        zArr2[1] = z2;
        boolean[] zArr3 = this.f2401p;
        if (f3 > 0.0f) {
            z3 = true;
        } else {
            z3 = false;
        }
        zArr3[2] = z3;
        boolean[] zArr4 = this.f2401p;
        if (f4 <= 0.0f) {
            z4 = false;
        }
        zArr4[3] = z4;
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable mo17431a(float f) {
        this.f2403r = f;
        this.f2394i.setStrokeWidth(this.f2403r);
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable mo17433a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f2404s = colorStateList;
        this.f2394i.setColor(this.f2404s.getColorForState(getState(), ViewCompat.MEASURED_STATE_MASK));
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable mo17436a(boolean z) {
        this.f2402q = z;
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable mo17435a(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        if (this.f2405t != scaleType) {
            this.f2405t = scaleType;
            m3202a();
        }
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable mo17434a(Shader.TileMode tileMode) {
        if (this.f2397l != tileMode) {
            this.f2397l = tileMode;
            this.f2399n = true;
            invalidateSelf();
        }
        return this;
    }

    /* renamed from: b */
    public RoundedDrawable mo17437b(Shader.TileMode tileMode) {
        if (this.f2398m != tileMode) {
            this.f2398m = tileMode;
            this.f2399n = true;
            invalidateSelf();
        }
        return this;
    }

    /* renamed from: a */
    private static boolean m3204a(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private static boolean m3207b(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return false;
            }
        }
        return true;
    }
}
