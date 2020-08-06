package com.snaperfect.style.daguerre.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.snaperfect.style.daguerre.R;

public class RoundedImageView extends AppCompatImageView {

    /* renamed from: a */
    public static final Shader.TileMode f2334a = Shader.TileMode.CLAMP;

    /* renamed from: b */
    static final /* synthetic */ boolean f2335b;

    /* renamed from: c */
    private static final ImageView.ScaleType[] f2336c = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};

    /* renamed from: d */
    private final float[] f2337d;

    /* renamed from: e */
    private Drawable f2338e;

    /* renamed from: f */
    private ColorStateList f2339f;

    /* renamed from: g */
    private float f2340g;

    /* renamed from: h */
    private ColorFilter f2341h;

    /* renamed from: i */
    private boolean f2342i;

    /* renamed from: j */
    private Drawable f2343j;

    /* renamed from: k */
    private boolean f2344k;

    /* renamed from: l */
    private boolean f2345l;

    /* renamed from: m */
    private boolean f2346m;

    /* renamed from: n */
    private int f2347n;

    /* renamed from: o */
    private int f2348o;

    /* renamed from: p */
    private ImageView.ScaleType f2349p;

    /* renamed from: q */
    private Shader.TileMode f2350q;

    /* renamed from: r */
    private Shader.TileMode f2351r;

    /* renamed from: com.snaperfect.style.daguerre.widget.RoundedImageView$1 */
    static /* synthetic */ class C15991 {

        /* renamed from: a */
        static final /* synthetic */ int[] f2352a = new int[ImageView.ScaleType.values().length];

        static {
            try {
                f2352a[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2352a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2352a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2352a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f2352a[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f2352a[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f2352a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    static {
        boolean z;
        if (!RoundedImageView.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        f2335b = z;
    }

    public RoundedImageView(Context context) {
        super(context);
        this.f2337d = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.f2339f = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        this.f2340g = 0.0f;
        this.f2341h = null;
        this.f2342i = false;
        this.f2344k = false;
        this.f2345l = false;
        this.f2346m = false;
        this.f2350q = f2334a;
        this.f2351r = f2334a;
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        float f;
        this.f2337d = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.f2339f = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        this.f2340g = 0.0f;
        this.f2341h = null;
        this.f2342i = false;
        this.f2344k = false;
        this.f2345l = false;
        this.f2346m = false;
        this.f2350q = f2334a;
        this.f2351r = f2334a;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(0, -1);
        if (i2 >= 0) {
            setScaleType(f2336c[i2]);
        } else {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        float dimensionPixelSize = (float) obtainStyledAttributes.getDimensionPixelSize(1, -1);
        this.f2337d[0] = (float) obtainStyledAttributes.getDimensionPixelSize(2, -1);
        this.f2337d[1] = (float) obtainStyledAttributes.getDimensionPixelSize(3, -1);
        this.f2337d[2] = (float) obtainStyledAttributes.getDimensionPixelSize(5, -1);
        this.f2337d[3] = (float) obtainStyledAttributes.getDimensionPixelSize(4, -1);
        int length = this.f2337d.length;
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.f2337d[i3] < 0.0f) {
                this.f2337d[i3] = 0.0f;
            } else {
                z = true;
            }
        }
        if (!z) {
            if (dimensionPixelSize < 0.0f) {
                f = 0.0f;
            } else {
                f = dimensionPixelSize;
            }
            int length2 = this.f2337d.length;
            for (int i4 = 0; i4 < length2; i4++) {
                this.f2337d[i4] = f;
            }
        }
        this.f2340g = (float) obtainStyledAttributes.getDimensionPixelSize(6, -1);
        if (this.f2340g < 0.0f) {
            this.f2340g = 0.0f;
        }
        this.f2339f = obtainStyledAttributes.getColorStateList(7);
        if (this.f2339f == null) {
            this.f2339f = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        }
        this.f2346m = obtainStyledAttributes.getBoolean(8, false);
        this.f2345l = obtainStyledAttributes.getBoolean(9, false);
        int i5 = obtainStyledAttributes.getInt(10, -2);
        if (i5 != -2) {
            setTileModeX(m3171a(i5));
            setTileModeY(m3171a(i5));
        }
        int i6 = obtainStyledAttributes.getInt(11, -2);
        if (i6 != -2) {
            setTileModeX(m3171a(i6));
        }
        int i7 = obtainStyledAttributes.getInt(12, -2);
        if (i7 != -2) {
            setTileModeY(m3171a(i7));
        }
        m3176c();
        m3174a(true);
        if (this.f2346m) {
            super.setBackgroundDrawable(this.f2338e);
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    private static Shader.TileMode m3171a(int i) {
        switch (i) {
            case 0:
                return Shader.TileMode.CLAMP;
            case 1:
                return Shader.TileMode.REPEAT;
            case 2:
                return Shader.TileMode.MIRROR;
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public ImageView.ScaleType getScaleType() {
        return this.f2349p;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (!f2335b && scaleType == null) {
            throw new AssertionError();
        } else if (this.f2349p != scaleType) {
            this.f2349p = scaleType;
            switch (C15991.f2352a[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    super.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            m3176c();
            m3174a(false);
            invalidate();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        this.f2347n = 0;
        this.f2343j = RoundedDrawable.m3200a(drawable);
        m3176c();
        super.setImageDrawable(this.f2343j);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.f2347n = 0;
        this.f2343j = RoundedDrawable.m3201a(bitmap);
        m3176c();
        super.setImageDrawable(this.f2343j);
    }

    public void setImageResource(@DrawableRes int i) {
        if (this.f2347n != i) {
            this.f2347n = i;
            this.f2343j = m3172a();
            m3176c();
            super.setImageDrawable(this.f2343j);
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    /* renamed from: a */
    private Drawable m3172a() {
        Drawable drawable = null;
        Resources resources = getResources();
        if (resources == null) {
            return drawable;
        }
        if (this.f2347n != 0) {
            try {
                drawable = resources.getDrawable(this.f2347n);
            } catch (Exception e) {
                Log.w("RoundedImageView", "Unable to find resource: " + this.f2347n, e);
                this.f2347n = 0;
            }
        }
        return RoundedDrawable.m3200a(drawable);
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundResource(@DrawableRes int i) {
        if (this.f2348o != i) {
            this.f2348o = i;
            this.f2338e = m3175b();
            setBackgroundDrawable(this.f2338e);
        }
    }

    public void setBackgroundColor(int i) {
        this.f2338e = new ColorDrawable(i);
        setBackgroundDrawable(this.f2338e);
    }

    /* renamed from: b */
    private Drawable m3175b() {
        Drawable drawable = null;
        Resources resources = getResources();
        if (resources == null) {
            return drawable;
        }
        if (this.f2348o != 0) {
            try {
                drawable = resources.getDrawable(this.f2348o);
            } catch (Exception e) {
                Log.w("RoundedImageView", "Unable to find resource: " + this.f2348o, e);
                this.f2348o = 0;
            }
        }
        return RoundedDrawable.m3200a(drawable);
    }

    /* renamed from: c */
    private void m3176c() {
        m3173a(this.f2343j, this.f2349p);
    }

    /* renamed from: a */
    private void m3174a(boolean z) {
        if (this.f2346m) {
            if (z) {
                this.f2338e = RoundedDrawable.m3200a(this.f2338e);
            }
            m3173a(this.f2338e, ImageView.ScaleType.FIT_XY);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.f2341h != colorFilter) {
            this.f2341h = colorFilter;
            this.f2344k = true;
            this.f2342i = true;
            m3177d();
            invalidate();
        }
    }

    /* renamed from: d */
    private void m3177d() {
        if (this.f2343j != null && this.f2342i) {
            this.f2343j = this.f2343j.mutate();
            if (this.f2344k) {
                this.f2343j.setColorFilter(this.f2341h);
            }
        }
    }

    /* renamed from: a */
    private void m3173a(Drawable drawable, ImageView.ScaleType scaleType) {
        if (drawable != null) {
            if (drawable instanceof RoundedDrawable) {
                ((RoundedDrawable) drawable).mo17435a(scaleType).mo17431a(this.f2340g).mo17433a(this.f2339f).mo17436a(this.f2345l).mo17434a(this.f2350q).mo17437b(this.f2351r);
                if (this.f2337d != null) {
                    ((RoundedDrawable) drawable).mo17432a(this.f2337d[0], this.f2337d[1], this.f2337d[2], this.f2337d[3]);
                }
                m3177d();
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    m3173a(layerDrawable.getDrawable(i), scaleType);
                }
            }
        }
    }

    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        this.f2338e = drawable;
        m3174a(true);
        super.setBackgroundDrawable(this.f2338e);
    }

    public float getCornerRadius() {
        return getMaxCornerRadius();
    }

    public float getMaxCornerRadius() {
        float f = 0.0f;
        for (float max : this.f2337d) {
            f = Math.max(max, f);
        }
        return f;
    }

    public void setCornerRadiusDimen(@DimenRes int i) {
        float dimension = getResources().getDimension(i);
        mo17376a(dimension, dimension, dimension, dimension);
    }

    public void setCornerRadius(float f) {
        mo17376a(f, f, f, f);
    }

    /* renamed from: a */
    public void mo17376a(float f, float f2, float f3, float f4) {
        if (this.f2337d[0] != f || this.f2337d[1] != f2 || this.f2337d[2] != f4 || this.f2337d[3] != f3) {
            this.f2337d[0] = f;
            this.f2337d[1] = f2;
            this.f2337d[3] = f3;
            this.f2337d[2] = f4;
            m3176c();
            m3174a(false);
            invalidate();
        }
    }

    public float getBorderWidth() {
        return this.f2340g;
    }

    public void setBorderWidth(@DimenRes int i) {
        setBorderWidth(getResources().getDimension(i));
    }

    public void setBorderWidth(float f) {
        if (this.f2340g != f) {
            this.f2340g = f;
            m3176c();
            m3174a(false);
            invalidate();
        }
    }

    @ColorInt
    public int getBorderColor() {
        return this.f2339f.getDefaultColor();
    }

    public void setBorderColor(@ColorInt int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.f2339f;
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (!this.f2339f.equals(colorStateList)) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
            }
            this.f2339f = colorStateList;
            m3176c();
            m3174a(false);
            if (this.f2340g > 0.0f) {
                invalidate();
            }
        }
    }

    public void setOval(boolean z) {
        this.f2345l = z;
        m3176c();
        m3174a(false);
        invalidate();
    }

    public Shader.TileMode getTileModeX() {
        return this.f2350q;
    }

    public void setTileModeX(Shader.TileMode tileMode) {
        if (this.f2350q != tileMode) {
            this.f2350q = tileMode;
            m3176c();
            m3174a(false);
            invalidate();
        }
    }

    public Shader.TileMode getTileModeY() {
        return this.f2351r;
    }

    public void setTileModeY(Shader.TileMode tileMode) {
        if (this.f2351r != tileMode) {
            this.f2351r = tileMode;
            m3176c();
            m3174a(false);
            invalidate();
        }
    }
}
