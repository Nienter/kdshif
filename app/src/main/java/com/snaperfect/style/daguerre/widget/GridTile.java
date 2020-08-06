package com.snaperfect.style.daguerre.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.p004v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.snaperfect.style.daguerre.frame.CollageFrameTile;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.math.CGRect;
import com.snaperfect.style.daguerre.math.CGSize;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import com.snaperfect.style.daguerre.utils.ScreenInfo;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;

public class GridTile extends AppCompatImageView {

    /* renamed from: a */
    public static final Xfermode f2308a = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    /* renamed from: b */
    public static final Xfermode f2309b = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);

    /* renamed from: c */
    private Bitmap f2310c;

    /* renamed from: d */
    private Bitmap f2311d;

    /* renamed from: e */
    private Rect f2312e;

    /* renamed from: f */
    private Paint f2313f;

    /* renamed from: g */
    private PhotoAsset f2314g;

    /* renamed from: h */
    private C1597a f2315h;

    /* renamed from: i */
    private Matrix[] f2316i;

    /* renamed from: j */
    private CGRect f2317j;

    /* renamed from: k */
    private RectF f2318k;

    /* renamed from: l */
    private Path f2319l;

    /* renamed from: m */
    private CollageFrameTile f2320m;

    /* renamed from: n */
    private float f2321n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public CGSize f2322o;

    /* renamed from: p */
    private int f2323p;

    /* renamed from: q */
    private float f2324q;

    /* renamed from: r */
    private float f2325r;

    /* renamed from: s */
    private final float f2326s;

    /* renamed from: com.snaperfect.style.daguerre.widget.GridTile$a */
    public interface C1597a {
        /* renamed from: a */
        GPUImage mo16899a();

        /* renamed from: a */
        void mo16903a(GridTile gridTile);

        /* renamed from: a */
        boolean mo16906a(GridTile gridTile, Object obj);
    }

    /* renamed from: com.snaperfect.style.daguerre.widget.GridTile$b */
    public interface C1598b {
        /* renamed from: a */
        void mo16930a(boolean z, GridTile gridTile);
    }

    public GridTile(Context context) {
        this(context, null);
    }

    public GridTile(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GridTile(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2326s = ScreenInfo.m3112a(context).f2102a;
        this.f2316i = new Matrix[3];
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.f2310c = bitmap;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.f2319l == null) {
            this.f2319l = m3156c();
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        if (this.f2319l != null) {
            canvas.clipPath(this.f2319l);
        }
        if (this.f2311d == null) {
            super.onDraw(canvas);
        } else {
            Matrix imageMatrix = getImageMatrix();
            this.f2313f.setXfermode(f2308a);
            canvas.drawBitmap(this.f2311d, this.f2312e, this.f2318k, this.f2313f);
            this.f2313f.setXfermode(f2309b);
            canvas.drawBitmap(this.f2310c, imageMatrix, this.f2313f);
        }
        canvas.restoreToCount(saveCount);
    }

    public int getFilterIndex() {
        if (this.f2320m != null) {
            return this.f2320m.f2093h;
        }
        return 0;
    }

    public float getFilterMix() {
        if (this.f2320m != null) {
            return this.f2320m.f2094i;
        }
        return 1.0f;
    }

    public int getRetouch() {
        return this.f2323p;
    }

    public CollageFrameTile getTile() {
        return this.f2320m;
    }

    public void setFilterIndex(int i) {
        this.f2320m.f2093h = i;
    }

    public void setFilterMix(float f) {
        this.f2320m.f2094i = f;
    }

    public void setCallback(C1597a aVar) {
        this.f2315h = aVar;
    }

    public void setRetouch(int i) {
        this.f2323p = i;
    }

    /* renamed from: c */
    private Path m3156c() {
        if (this.f2320m != null) {
            return this.f2320m.mo17117a(this.f2321n, 0);
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m3157d() {
        if (this.f2322o != null && this.f2310c != null) {
            CGRect cGRect = new CGRect(0.0f, 0.0f, this.f2317j.f2100c, this.f2317j.f2101d);
            float[] fArr = {0.0f};
            float[] fArr2 = {0.0f};
            this.f2316i[0] = CGRect.m2849a(cGRect, this.f2322o, this.f2316i[0], fArr2);
            this.f2316i[1] = CGRect.m2855b(cGRect, this.f2322o, this.f2316i[1], fArr);
            this.f2324q = (float) Math.log((double) fArr[0]);
            this.f2325r = (float) Math.log((double) fArr2[0]);
            Matrix matrix = new Matrix(this.f2316i[1]);
            matrix.postScale(0.9f, 0.9f);
            matrix.postRotate(-5.0f);
            float[] fArr3 = {0.0f, 0.0f, (float) this.f2310c.getWidth(), (float) this.f2310c.getHeight()};
            matrix.mapPoints(fArr3);
            CGPoint b = new CGPoint(this.f2317j.f2100c, this.f2317j.f2101d).mo17126b(2.0f).mo17127b(new CGPoint(fArr3[0] + fArr3[2], fArr3[3] + fArr3[1]).mo17126b(2.0f));
            matrix.postTranslate(b.x, b.y);
            this.f2316i[2] = matrix;
            setImageMatrix(this.f2316i[this.f2320m.f2092g]);
        }
    }

    /* renamed from: a */
    public void mo17352a(CollageFrameTile dVar, PhotoAsset photoAsset, final C1598b bVar) {
        setTile(dVar);
        this.f2314g = photoAsset;
        int a = photoAsset.mo17246a(this.f2326s, this.f2326s);
        C15951 r5 = new PhotoAsset.C1581d() {
            /* renamed from: a */
            public void mo16936a(@Nullable Bitmap bitmap) {
                boolean z = bitmap != null && !bitmap.isRecycled();
                if (z) {
                    GridTile.this.setImageBitmap(bitmap);
                    CGSize unused = GridTile.this.f2322o = new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight());
                    GridTile.this.m3157d();
                }
                if (bVar != null) {
                    bVar.mo16930a(z, this);
                }
            }
        };
        GPUImage a2 = this.f2315h.mo16899a();
        if (this.f2320m.f2092g == 2) {
            this.f2314g.mo17260b(a2, a, getFilterIndex(), this.f2323p, r5);
        } else {
            this.f2314g.mo17257a(a2, a, getFilterIndex(), this.f2323p, (PhotoAsset.C1581d) r5);
        }
    }

    /* renamed from: a */
    public void mo17353a(final C1598b bVar) {
        int a = this.f2314g.mo17246a(this.f2326s, this.f2326s);
        C15962 r5 = new PhotoAsset.C1581d() {
            /* renamed from: a */
            public void mo16936a(@Nullable Bitmap bitmap) {
                boolean z = bitmap != null && !bitmap.isRecycled();
                if (z) {
                    GridTile.this.setImageBitmap(bitmap);
                }
                if (bVar != null) {
                    bVar.mo16930a(z, this);
                }
            }
        };
        GPUImage a2 = this.f2315h.mo16899a();
        if (this.f2320m.f2092g == 2) {
            this.f2314g.mo17260b(a2, a, getFilterIndex(), this.f2323p, r5);
        } else {
            this.f2314g.mo17257a(a2, a, getFilterIndex(), this.f2323p, (PhotoAsset.C1581d) r5);
        }
    }

    public void setTile(CollageFrameTile dVar) {
        this.f2317j = dVar.f2091f;
        this.f2318k = new RectF(0.0f, 0.0f, this.f2317j.f2100c, this.f2317j.f2101d);
        this.f2320m = dVar;
        this.f2319l = null;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins((int) this.f2317j.f2098a, (int) this.f2317j.f2099b, 0, 0);
        layoutParams.width = (int) this.f2317j.f2100c;
        layoutParams.height = (int) this.f2317j.f2101d;
        setLayoutParams(layoutParams);
        m3157d();
    }

    public void setMaskBitmap(Bitmap bitmap) {
        this.f2311d = bitmap;
        this.f2312e = bitmap != null ? new Rect(0, 0, this.f2311d.getWidth(), this.f2311d.getHeight()) : null;
        if (bitmap != null && this.f2313f == null) {
            this.f2313f = new Paint(3);
        }
        invalidate();
    }

    public void setCornerRadius(float f) {
        this.f2321n = f;
        this.f2319l = null;
        invalidate();
    }

    public float[] getNearestOffsets() {
        float[] fArr = {((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, 0.0f, 0.0f, (float) getWidth(), (float) getHeight()};
        if (this.f2310c == null) {
            return fArr;
        }
        float[] fArr2 = {0.0f, 0.0f, 0.0f, (float) this.f2310c.getWidth(), (float) this.f2310c.getWidth(), (float) this.f2310c.getHeight(), 0.0f, (float) this.f2310c.getHeight(), ((float) this.f2310c.getWidth()) / 2.0f, ((float) this.f2310c.getHeight()) / 2.0f};
        getImageMatrix().mapPoints(fArr2);
        for (int i = 0; i < fArr.length; i++) {
            int i2 = i % 2;
            float f = 0.0f;
            float f2 = Float.MAX_VALUE;
            for (int i3 = 0; i3 < fArr2.length; i3 += 2) {
                float f3 = fArr[i] - fArr2[i3 + i2];
                if (Math.abs(f3) < f2) {
                    f2 = Math.abs(f3);
                    f = f3;
                }
            }
            fArr[i] = -f;
        }
        return fArr;
    }

    public PhotoAsset getAsset() {
        return this.f2314g;
    }

    /* renamed from: a */
    public float mo17350a(float f) {
        float f2 = this.f2325r - f;
        float f3 = this.f2324q - f;
        if (Math.abs(f2) >= Math.abs(f3)) {
            f2 = f3;
        }
        return f2 * -1.0f;
    }

    public CGSize getActualImageSize() {
        if (this.f2310c == null) {
            return new CGSize(0.0f);
        }
        float[] fArr = {0.0f, 0.0f, (float) this.f2310c.getWidth(), 0.0f, 0.0f, (float) this.f2310c.getHeight()};
        getImageMatrix().mapPoints(fArr);
        return new CGSize(new CGPoint(fArr[0] - fArr[2], fArr[1] - fArr[3]).length(), new CGPoint(fArr[0] - fArr[4], fArr[1] - fArr[5]).length());
    }

    /* renamed from: a */
    public void mo17351a() {
        setImageDrawable(null);
        this.f2311d = null;
        this.f2310c = null;
    }

    /* renamed from: b */
    public void mo17356b() {
        Bitmap bitmap = this.f2310c;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float[] fArr = {(float) (width / 2), (float) (height / 2), 0.0f, 0.0f, (float) width, 0.0f, (float) width, (float) height, 0.0f, (float) height};
            float[] fArr2 = {0.0f, 0.0f, (float) getWidth(), 0.0f, (float) getWidth(), (float) getHeight(), 0.0f, (float) getHeight(), (float) (getWidth() / 2), (float) (getHeight() / 2)};
            getImageMatrix().mapPoints(fArr);
            float mapRadius = getImageMatrix().mapRadius((float) Math.max(width, height));
            CGRect cGRect = new CGRect(0.0f, 0.0f, this.f2317j.f2100c, this.f2317j.f2101d);
            CGRect a = m3153a(fArr);
            if (!cGRect.mo17136a(fArr) && !a.mo17136a(fArr2)) {
                CGPoint b = CGPoint.m2834b(new CGPoint(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f), new CGPoint(fArr[0], fArr[1]));
                float width2 = (mapRadius + ((float) getWidth())) / 2.0f;
                if (b.length() > width2) {
                    b.mo17122a(1.0f - (width2 / b.length()));
                    Matrix matrix = new Matrix(getImageMatrix());
                    matrix.postTranslate(b.x, b.y);
                    setImageMatrix(matrix);
                }
            }
        }
    }

    /* renamed from: a */
    private CGRect m3153a(float[] fArr) {
        float f = Float.MAX_VALUE;
        float f2 = -3.4028235E38f;
        float f3 = -3.4028235E38f;
        float f4 = Float.MAX_VALUE;
        for (int i = 0; i < fArr.length; i++) {
            if (i % 2 == 0) {
                if (fArr[i] < f4) {
                    f4 = fArr[i];
                }
                if (fArr[i] > f3) {
                    f3 = fArr[i];
                }
            } else {
                if (fArr[i] < f) {
                    f = fArr[i];
                }
                if (fArr[i] > f2) {
                    f2 = fArr[i];
                }
            }
        }
        return new CGRect(f4, f, f3 - f4, f2 - f);
    }

    /* renamed from: a */
    public void mo17354a(boolean z) {
        this.f2315h.mo16903a(this);
    }

    /* renamed from: a */
    public boolean mo17355a(Object obj) {
        return this.f2315h.mo16906a(this, obj);
    }
}
