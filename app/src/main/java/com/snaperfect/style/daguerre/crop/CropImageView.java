package com.snaperfect.style.daguerre.crop;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.snaperfect.style.daguerre.crop.HighlightView;
import com.snaperfect.style.daguerre.math.CGMatrix;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

public class CropImageView extends ImageViewTouchBase {

    /* renamed from: a */
    public ArrayList<HighlightView> f1955a = new ArrayList<>();

    /* renamed from: b */
    HighlightView f1956b = null;

    /* renamed from: c */
    float f1957c;

    /* renamed from: d */
    float f1958d;

    /* renamed from: e */
    int f1959e;

    /* renamed from: n */
    private CropImage f1960n;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0017  */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f1967h.mo17091b() != null) {
            Iterator<HighlightView> it = this.f1955a.iterator();
            while (it.hasNext()) {
                HighlightView next = it.next();
                next.f2019f.set(getImageMatrix());
                next.mo17087c();
                if (next.f2015b) {
                }
                while (it.hasNext()) {
                }
            }
        }
    }

    @TargetApi(11)
    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{1, null});
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            } catch (UnsupportedOperationException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17041a(float f, float f2, float f3, float f4, float f5) {
        super.mo17041a(f, f2, f3, f4, f5);
        Iterator<HighlightView> it = this.f1955a.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.f2019f.set(getImageMatrix());
            next.mo17087c();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17040a(float f, float f2) {
        super.mo17040a(f, f2);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f1955a.size()) {
                HighlightView dVar = this.f1955a.get(i2);
                dVar.f2019f.postTranslate(f, f2);
                dVar.mo17087c();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private void m2697a(MotionEvent motionEvent) {
        int i = 0;
        for (int i2 = 0; i2 < this.f1955a.size(); i2++) {
            HighlightView dVar = this.f1955a.get(i2);
            dVar.mo17081a(false);
            dVar.mo17087c();
        }
        while (true) {
            if (i >= this.f1955a.size()) {
                break;
            }
            HighlightView dVar2 = this.f1955a.get(i);
            if (dVar2.mo17076a(motionEvent.getX(), motionEvent.getY()) == 1) {
                i++;
            } else if (!dVar2.mo17082a()) {
                dVar2.mo17081a(true);
                dVar2.mo17087c();
            }
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        CropImage bVar = this.f1960n;
        if (bVar.f1979b || bVar.f1980c) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (!bVar.f1978a) {
                    int i = 0;
                    while (true) {
                        if (i >= this.f1955a.size()) {
                            break;
                        } else {
                            HighlightView dVar = this.f1955a.get(i);
                            int a = dVar.mo17076a(motionEvent.getX(), motionEvent.getY());
                            if (a != 1) {
                                this.f1959e = a;
                                this.f1956b = dVar;
                                this.f1957c = motionEvent.getX();
                                this.f1958d = motionEvent.getY();
                                this.f1956b.mo17080a(a == 32 ? HighlightView.C1535a.Move : HighlightView.C1535a.Grow);
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                } else {
                    m2697a(motionEvent);
                    break;
                }
            case 1:
                if (bVar.f1978a) {
                    for (int i2 = 0; i2 < this.f1955a.size(); i2++) {
                        HighlightView dVar2 = this.f1955a.get(i2);
                        if (dVar2.mo17082a()) {
                            bVar.f1981d = dVar2;
                            for (int i3 = 0; i3 < this.f1955a.size(); i3++) {
                                if (i3 != i2) {
                                    this.f1955a.get(i3).mo17086b(true);
                                }
                            }
                            m2700d(dVar2);
                            this.f1960n.f1978a = false;
                            return true;
                        }
                    }
                } else if (this.f1956b != null) {
                    m2700d(this.f1956b);
                    this.f1956b.mo17080a(HighlightView.C1535a.None);
                }
                this.f1956b = null;
                break;
            case 2:
                if (!bVar.f1978a) {
                    if (this.f1956b != null) {
                        this.f1956b.mo17077a(this.f1959e, motionEvent.getX() - this.f1957c, motionEvent.getY() - this.f1958d);
                        if (!m2699c(this.f1956b)) {
                            m2698b(this.f1956b);
                        }
                        this.f1957c = motionEvent.getX();
                        this.f1958d = motionEvent.getY();
                        break;
                    }
                } else {
                    m2697a(motionEvent);
                    break;
                }
                break;
        }
        switch (motionEvent.getAction()) {
            case 1:
                mo17055a(true, true);
                break;
            case 2:
                mo17055a(true, true);
                break;
        }
        return true;
    }

    /* renamed from: b */
    private void m2698b(HighlightView dVar) {
        Rect rect = dVar.f2017d;
        int max = Math.max(0, 0 - rect.left);
        int min = Math.min(0, (getRight() - getLeft()) - rect.right);
        int max2 = Math.max(0, 0 - rect.top);
        int min2 = Math.min(0, (getBottom() - getTop()) - rect.bottom);
        if (max == 0) {
            max = min;
        }
        if (max2 == 0) {
            max2 = min2;
        }
        if (max != 0 || max2 != 0) {
            mo17056b((float) max, (float) max2);
        }
    }

    /* renamed from: c */
    private boolean m2699c(HighlightView dVar) {
        int i = dVar.f2017d.left;
        int i2 = dVar.f2017d.right;
        int i3 = dVar.f2017d.top;
        int i4 = dVar.f2017d.bottom;
        if (i < 1 && i2 > getRight() - 1) {
            return true;
        }
        if (i3 >= 1 || i4 <= getBottom() - 1) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private Point m2696a(HighlightView dVar, float f) {
        if (f > this.f1970k) {
            f = this.f1970k;
        }
        float[] fArr = {dVar.f2018e.centerX(), dVar.f2018e.centerY()};
        getImageMatrix().mapPoints(fArr);
        Matrix matrix = new Matrix(this.f1966g);
        matrix.postScale(f / getScale(), f / getScale(), fArr[0], fArr[1]);
        Matrix matrix2 = new Matrix(this.f1965f);
        matrix2.postConcat(matrix);
        RectF rectF = new RectF(dVar.f2018e.left, dVar.f2018e.top, dVar.f2018e.right, dVar.f2018e.bottom);
        matrix2.mapRect(rectF);
        Rect rect = new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int right = getRight() - getPaddingRight();
        int bottom = getBottom() - getPaddingBottom();
        int max = Math.max(paddingLeft, paddingLeft - rect.left);
        int min = Math.min(right, right - rect.right);
        int max2 = Math.max(paddingTop, paddingTop - rect.top);
        int min2 = Math.min(bottom, bottom - rect.bottom);
        if (max == 0) {
            max = min;
        }
        if (max2 == 0) {
            max2 = min2;
        }
        return new Point(max, max2);
    }

    /* renamed from: d */
    private void m2700d(HighlightView dVar) {
        Rect rect = dVar.f2017d;
        float max = Math.max(1.0f, Math.min((((float) getWidth()) / ((float) rect.width())) * 0.6f, (((float) getHeight()) / ((float) rect.height())) * 0.6f) * getScale());
        if (((double) (Math.abs(max - getScale()) / max)) > 0.1d) {
            Point a = m2696a(dVar, max);
            float[] fArr = {dVar.f2018e.centerX(), dVar.f2018e.centerY()};
            getImageMatrix().mapPoints(fArr);
            mo17052a(max, fArr[0], fArr[1], 300.0f, a);
        }
        m2698b(dVar);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        if (bitmapDrawable != null && bitmapDrawable.getBitmap() != null && !bitmapDrawable.getBitmap().isRecycled()) {
            Log.d("CROP_DRAW", "" + getImageMatrix());
            super.onDraw(canvas);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.f1955a.size()) {
                    this.f1955a.get(i2).mo17078a(canvas);
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public void mo17042a(HighlightView dVar) {
        this.f1955a.add(dVar);
        invalidate();
    }

    public void setCropImage(CropImage bVar) {
        this.f1960n = bVar;
    }

    public void setImageMatrix(Matrix matrix) {
        float[] c = CGMatrix.m2899c(matrix);
        if (c[0] > 0.0f) {
            Log.d("CROP", "(" + c[0] + ", " + c[1] + "]");
        }
        super.setImageMatrix(matrix);
    }
}
