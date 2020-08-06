package com.snaperfect.style.daguerre.crop;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.p001v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.math.CGRect;

/* renamed from: com.snaperfect.style.daguerre.crop.d */
public class HighlightView {

    /* renamed from: a */
    View f2014a;

    /* renamed from: b */
    public boolean f2015b;

    /* renamed from: c */
    boolean f2016c;

    /* renamed from: d */
    public Rect f2017d;

    /* renamed from: e */
    public RectF f2018e;

    /* renamed from: f */
    public Matrix f2019f;

    /* renamed from: g */
    private C1535a f2020g = C1535a.None;

    /* renamed from: h */
    private RectF f2021h;

    /* renamed from: i */
    private boolean f2022i = false;

    /* renamed from: j */
    private float f2023j;

    /* renamed from: k */
    private boolean f2024k = false;

    /* renamed from: l */
    private Drawable f2025l;

    /* renamed from: m */
    private final Paint f2026m = new Paint();

    /* renamed from: n */
    private final Paint f2027n = new Paint();

    /* renamed from: o */
    private final Paint f2028o = new Paint();

    /* renamed from: com.snaperfect.style.daguerre.crop.d$a */
    /* compiled from: HighlightView */
    public enum C1535a {
        None,
        Move,
        Grow
    }

    public HighlightView(View view) {
        this.f2014a = view;
    }

    /* renamed from: d */
    private void m2740d() {
        this.f2025l = this.f2014a.getResources().getDrawable(R.mipmap.roidapp_imagelib_icon_handle);
    }

    /* renamed from: a */
    public boolean mo17082a() {
        return this.f2015b;
    }

    /* renamed from: a */
    public void mo17081a(boolean z) {
        this.f2015b = z;
    }

    /* renamed from: b */
    public void mo17086b(boolean z) {
        this.f2016c = z;
    }

    /* renamed from: a */
    public void mo17078a(Canvas canvas) {
        if (!this.f2016c) {
            canvas.save();
            Path path = new Path();
            if (!mo17082a()) {
                this.f2028o.setColor(ViewCompat.MEASURED_STATE_MASK);
                canvas.drawRect(this.f2017d, this.f2028o);
                return;
            }
            Rect rect = new Rect();
            this.f2014a.getDrawingRect(rect);
            if (this.f2024k) {
                float width = (float) this.f2017d.width();
                path.addCircle(((float) this.f2017d.left) + (width / 2.0f), (((float) this.f2017d.height()) / 2.0f) + ((float) this.f2017d.top), width / 2.0f, Path.Direction.CW);
                this.f2028o.setColor(-2013265665);
            } else {
                path.addRect(new RectF(this.f2017d), Path.Direction.CW);
                this.f2028o.setColor(-2013265665);
            }
            this.f2028o.setAntiAlias(true);
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawRect(rect, mo17082a() ? this.f2026m : this.f2027n);
            canvas.restore();
            canvas.drawPath(path, this.f2028o);
            if (this.f2020g != C1535a.None && this.f2020g != C1535a.Grow) {
                return;
            }
            if (this.f2024k) {
                int intrinsicWidth = this.f2025l.getIntrinsicWidth();
                int intrinsicHeight = this.f2025l.getIntrinsicHeight();
                int round = (int) Math.round(Math.cos(0.7853981633974483d) * (((double) this.f2017d.width()) / 2.0d));
                int width2 = ((this.f2017d.left + (this.f2017d.width() / 2)) + round) - (intrinsicWidth / 2);
                int height = ((this.f2017d.top + (this.f2017d.height() / 2)) - round) - (intrinsicHeight / 2);
                this.f2025l.setBounds(width2, height, this.f2025l.getIntrinsicWidth() + width2, this.f2025l.getIntrinsicHeight() + height);
                this.f2025l.draw(canvas);
                return;
            }
            int i = this.f2017d.left;
            int i2 = this.f2017d.right;
            int i3 = this.f2017d.top;
            int i4 = this.f2017d.bottom;
            int intrinsicWidth2 = this.f2025l.getIntrinsicWidth() / 2;
            int intrinsicHeight2 = this.f2025l.getIntrinsicHeight() / 2;
            int i5 = this.f2017d.left + ((this.f2017d.right - this.f2017d.left) / 2);
            int i6 = this.f2017d.top + ((this.f2017d.bottom - this.f2017d.top) / 2);
            this.f2025l.setBounds(i - intrinsicWidth2, i6 - intrinsicHeight2, i + intrinsicWidth2, i6 + intrinsicHeight2);
            this.f2025l.draw(canvas);
            this.f2025l.setBounds(i2 - intrinsicWidth2, i6 - intrinsicHeight2, i2 + intrinsicWidth2, i6 + intrinsicHeight2);
            this.f2025l.draw(canvas);
            this.f2025l.setBounds(i5 - intrinsicWidth2, i3 - intrinsicHeight2, i5 + intrinsicWidth2, i3 + intrinsicHeight2);
            this.f2025l.draw(canvas);
            this.f2025l.setBounds(i5 - intrinsicWidth2, i4 - intrinsicHeight2, i5 + intrinsicWidth2, i4 + intrinsicHeight2);
            this.f2025l.draw(canvas);
            this.f2025l.setBounds(i - intrinsicWidth2, i3 - intrinsicHeight2, i + intrinsicWidth2, i3 + intrinsicHeight2);
            this.f2025l.draw(canvas);
            this.f2025l.setBounds(i2 - intrinsicWidth2, i3 - intrinsicHeight2, i2 + intrinsicWidth2, i3 + intrinsicHeight2);
            this.f2025l.draw(canvas);
            this.f2025l.setBounds(i - intrinsicWidth2, i4 - intrinsicHeight2, i + intrinsicWidth2, i4 + intrinsicHeight2);
            this.f2025l.draw(canvas);
            this.f2025l.setBounds(i2 - intrinsicWidth2, i4 - intrinsicHeight2, i2 + intrinsicWidth2, i4 + intrinsicHeight2);
            this.f2025l.draw(canvas);
        }
    }

    /* renamed from: a */
    public void mo17080a(C1535a aVar) {
        if (aVar != this.f2020g) {
            this.f2020g = aVar;
            this.f2014a.invalidate();
        }
    }

    /* renamed from: a */
    public int mo17076a(float f, float f2) {
        int i;
        int i2;
        boolean z = false;
        Rect e = m2741e();
        if (this.f2024k) {
            float centerX = f - ((float) e.centerX());
            float centerY = f2 - ((float) e.centerY());
            int sqrt = (int) Math.sqrt((double) ((centerX * centerX) + (centerY * centerY)));
            int width = this.f2017d.width() / 2;
            if (((float) Math.abs(sqrt - width)) <= 30.0f) {
                if (Math.abs(centerY) > Math.abs(centerX)) {
                    if (centerY < 0.0f) {
                        return 8;
                    }
                    return 16;
                } else if (centerX < 0.0f) {
                    return 2;
                } else {
                    return 4;
                }
            } else if (sqrt >= width) {
                return 1;
            } else {
                return 32;
            }
        } else {
            boolean z2 = f2 >= ((float) e.top) - 30.0f && f2 < ((float) e.bottom) + 30.0f;
            if (f >= ((float) e.left) - 30.0f && f < ((float) e.right) + 30.0f) {
                z = true;
            }
            if (Math.abs(((float) e.left) - f) >= 30.0f || !z2) {
                i = 1;
            } else {
                i = 3;
            }
            if (Math.abs(((float) e.right) - f) < 30.0f && z2) {
                i |= 4;
            }
            if (Math.abs(((float) e.top) - f2) < 30.0f && z) {
                i |= 8;
            }
            if (Math.abs(((float) e.bottom) - f2) >= 30.0f || !z) {
                i2 = i;
            } else {
                i2 = i | 16;
            }
            if (i2 != 1 || !e.contains((int) f, (int) f2)) {
                return i2;
            }
            return 32;
        }
    }

    /* renamed from: a */
    public void mo17077a(int i, float f, float f2) {
        int width;
        int height;
        int i2;
        int i3 = 1;
        Rect e = m2741e();
        if (i != 1) {
            if (i == 32) {
                mo17084b((this.f2018e.width() / ((float) e.width())) * f, (this.f2018e.height() / ((float) e.height())) * f2);
                return;
            }
            if ((i & 6) == 0) {
                f = 0.0f;
            }
            if ((i & 24) == 0) {
                f2 = 0.0f;
            }
            if (e.width() == 0) {
                width = 1;
            } else {
                width = e.width();
            }
            if (e.height() == 0) {
                height = 1;
            } else {
                height = e.height();
            }
            float width2 = f * (this.f2018e.width() / ((float) width));
            float height2 = f2 * (this.f2018e.height() / ((float) height));
            if ((i & 2) != 0) {
                i2 = -1;
            } else {
                i2 = 1;
            }
            float f3 = ((float) i2) * width2;
            if ((i & 8) != 0) {
                i3 = -1;
            }
            mo17085b(i, f3, ((float) i3) * height2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17084b(float f, float f2) {
        Rect rect = new Rect(this.f2017d);
        this.f2018e.offset(f, f2);
        this.f2018e.offset(Math.max(0.0f, this.f2021h.left - this.f2018e.left), Math.max(0.0f, this.f2021h.top - this.f2018e.top));
        this.f2018e.offset(Math.min(0.0f, this.f2021h.right - this.f2018e.right), Math.min(0.0f, this.f2021h.bottom - this.f2018e.bottom));
        this.f2017d = m2741e();
        rect.union(this.f2017d);
        rect.inset(-10, -10);
        this.f2014a.invalidate(rect);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0192  */
    /* renamed from: b */
    public void mo17085b(int i, float f, float f2) {
        float f3;
        float f4;
        float f5;
        if (this.f2022i) {
            if (f != 0.0f) {
                f2 = f / this.f2023j;
            } else if (f2 != 0.0f) {
                f = f2 * this.f2023j;
            }
        }
        RectF rectF = new RectF(this.f2018e);
        if (f > 0.0f && rectF.width() + (2.0f * f) > this.f2021h.width()) {
            f = (this.f2021h.width() - rectF.width()) / 2.0f;
            if (this.f2022i) {
                f3 = f / this.f2023j;
                f4 = f;
                if (f3 > 0.0f && rectF.height() + (2.0f * f3) > this.f2021h.height()) {
                    f3 = (this.f2021h.height() - rectF.height()) / 2.0f;
                    if (this.f2022i) {
                        f4 = this.f2023j * f3;
                    }
                }
                if (!this.f2022i) {
                    rectF.inset(-f4, -f3);
                } else {
                    if ((i & 2) != 0) {
                        rectF.left -= f4;
                        if (rectF.width() < 25.0f) {
                            rectF.left += f4;
                        }
                    }
                    if ((i & 4) != 0) {
                        rectF.right += f4;
                        if (rectF.width() < 25.0f) {
                            rectF.right -= f4;
                        }
                    }
                    if ((i & 8) != 0) {
                        rectF.top -= f3;
                        if (rectF.height() < 25.0f) {
                            rectF.top += f3;
                        }
                    }
                    if ((i & 16) != 0) {
                        rectF.bottom += f3;
                        if (rectF.height() < 25.0f) {
                            rectF.bottom -= f3;
                        }
                    }
                }
                if (rectF.width() < 25.0f) {
                    rectF.inset((-(25.0f - rectF.width())) / 2.0f, 0.0f);
                }
                f5 = !this.f2022i ? 25.0f / this.f2023j : 25.0f;
                if (rectF.height() < f5) {
                    rectF.inset(0.0f, (-(f5 - rectF.height())) / 2.0f);
                }
                if (rectF.left >= this.f2021h.left) {
                    if (this.f2022i) {
                        rectF.offset(this.f2021h.left - rectF.left, 0.0f);
                    } else {
                        rectF.left += this.f2021h.left - rectF.left;
                    }
                } else if (rectF.right > this.f2021h.right) {
                    if (this.f2022i) {
                        rectF.offset(-(rectF.right - this.f2021h.right), 0.0f);
                    } else {
                        rectF.right += -(rectF.right - this.f2021h.right);
                    }
                }
                if (rectF.top >= this.f2021h.top) {
                    if (this.f2022i) {
                        rectF.offset(0.0f, this.f2021h.top - rectF.top);
                    } else {
                        rectF.top += this.f2021h.top - rectF.top;
                    }
                } else if (rectF.bottom > this.f2021h.bottom) {
                    if (this.f2022i) {
                        rectF.offset(0.0f, -(rectF.bottom - this.f2021h.bottom));
                    } else {
                        rectF.bottom += -(rectF.bottom - this.f2021h.bottom);
                    }
                }
                this.f2018e.set(rectF);
                this.f2017d = m2741e();
                this.f2014a.invalidate();
            }
        }
        f3 = f2;
        f4 = f;
        f3 = (this.f2021h.height() - rectF.height()) / 2.0f;
        if (this.f2022i) {
        }
        if (!this.f2022i) {
        }
        if (rectF.width() < 25.0f) {
        }
        if (!this.f2022i) {
        }
        if (rectF.height() < f5) {
        }
        if (rectF.left >= this.f2021h.left) {
        }
        if (rectF.top >= this.f2021h.top) {
        }
        this.f2018e.set(rectF);
        this.f2017d = m2741e();
        this.f2014a.invalidate();
    }

    /* renamed from: b */
    public CGRect mo17083b() {
        return new CGRect(this.f2018e.left, this.f2018e.top, this.f2018e.width(), this.f2018e.height());
    }

    /* renamed from: e */
    private Rect m2741e() {
        RectF rectF = new RectF(this.f2018e.left, this.f2018e.top, this.f2018e.right, this.f2018e.bottom);
        RectF rectF2 = new RectF();
        this.f2019f.mapRect(rectF2, rectF);
        Log.d("CROP", "crop:" + rectF + " mtx:" + this.f2019f + " maped:" + rectF2);
        return new Rect(Math.round(rectF2.left), Math.round(rectF2.top), Math.round(rectF2.right), Math.round(rectF2.bottom));
    }

    /* renamed from: c */
    public void mo17087c() {
        this.f2017d = m2741e();
    }

    /* renamed from: a */
    public void mo17079a(Matrix matrix, Rect rect, RectF rectF, boolean z, boolean z2) {
        if (z) {
            z2 = true;
        }
        this.f2019f = new Matrix(matrix);
        this.f2018e = rectF;
        this.f2021h = new RectF(rect);
        this.f2022i = z2;
        this.f2024k = z;
        this.f2023j = this.f2018e.width() / this.f2018e.height();
        this.f2017d = m2741e();
        this.f2026m.setARGB(125, 0, 0, 0);
        this.f2027n.setARGB(125, 0, 0, 0);
        this.f2028o.setStrokeWidth(3.0f);
        this.f2028o.setStyle(Paint.Style.STROKE);
        this.f2028o.setAntiAlias(true);
        this.f2020g = C1535a.None;
        m2740d();
    }
}
