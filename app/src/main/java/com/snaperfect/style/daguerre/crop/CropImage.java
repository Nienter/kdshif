package com.snaperfect.style.daguerre.crop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import com.snaperfect.style.daguerre.math.CGRect;
import com.snaperfect.style.daguerre.math.CGSize;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.snaperfect.style.daguerre.crop.b */
public class CropImage {

    /* renamed from: a */
    public boolean f1978a;

    /* renamed from: b */
    public boolean f1979b;

    /* renamed from: c */
    public boolean f1980c;

    /* renamed from: d */
    public HighlightView f1981d;

    /* renamed from: e */
    Runnable f1982e = new Runnable() {

        /* renamed from: a */
        float f2000a = 1.0f;

        /* renamed from: b */
        Matrix f2001b;

        /* access modifiers changed from: private */
        /* renamed from: a */
        public void m2735a() {
            CGRect cGRect;
            boolean z = true;
            HighlightView dVar = new HighlightView(CropImage.this.f1986i);
            CGRect cGRect2 = new CGRect(0.0f, 0.0f, (float) CropImage.this.f1987j.getWidth(), (float) CropImage.this.f1987j.getHeight());
            if (CropImage.this.f1988k <= 0 || CropImage.this.f1989l <= 0) {
                cGRect = cGRect2;
            } else {
                cGRect = CGRect.m2853a(cGRect2, new CGSize((float) CropImage.this.f1988k, (float) CropImage.this.f1989l), false);
            }
            Rect unused = CropImage.this.f1994q = cGRect.mo17144f();
            if (CropImage.this.f1993p) {
                Matrix matrix = this.f2001b;
                Rect f = cGRect2.mo17144f();
                RectF e = cGRect.mo17142e();
                boolean g = CropImage.this.f1995r;
                if (CropImage.this.f1992o) {
                    z = false;
                }
                dVar.mo17079a(matrix, f, e, g, z);
            } else {
                Matrix matrix2 = this.f2001b;
                Rect f2 = cGRect2.mo17144f();
                RectF e2 = cGRect.mo17142e();
                boolean g2 = CropImage.this.f1995r;
                if (CropImage.this.f1988k == 0 || CropImage.this.f1989l == 0) {
                    z = false;
                }
                dVar.mo17079a(matrix2, f2, e2, g2, z);
            }
            CropImage.this.f1986i.mo17042a(dVar);
        }

        public void run() {
            this.f2001b = new Matrix(CropImage.this.f1986i.getImageMatrix());
            this.f2001b.postTranslate((float) CropImage.this.f1986i.getPaddingLeft(), (float) CropImage.this.f1986i.getPaddingTop());
            this.f2000a = 1.0f / this.f2000a;
            CropImage.this.f1985h.post(new Runnable() {
                public void run() {
                    C15302.this.m2735a();
                    CropImage.this.f1986i.invalidate();
                    if (CropImage.this.f1986i.f1955a.size() == 1) {
                        CropImage.this.f1981d = CropImage.this.f1986i.f1955a.get(0);
                        CropImage.this.f1981d.mo17081a(true);
                    }
                }
            });
        }
    };

    /* renamed from: f */
    private final String f1983f = "CropImage";

    /* renamed from: g */
    private Context f1984g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public Handler f1985h = new Handler();
    /* access modifiers changed from: private */

    /* renamed from: i */
    public CropImageView f1986i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public Bitmap f1987j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public int f1988k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public int f1989l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public boolean f1990m = false;

    /* renamed from: n */
    private boolean f1991n = false;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public boolean f1992o = false;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public boolean f1993p = false;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public Rect f1994q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public boolean f1995r = false;

    /* renamed from: com.snaperfect.style.daguerre.crop.b$a */
    /* compiled from: CropImage */
    class C1532a implements Runnable {

        /* renamed from: b */
        private Runnable f2005b;

        /* renamed from: c */
        private Handler f2006c;

        public C1532a(Runnable runnable, Handler handler) {
            this.f2005b = runnable;
            this.f2006c = handler;
        }

        public void run() {
            try {
                this.f2005b.run();
            } finally {
                this.f2006c.post(new Runnable() {
                    public void run() {
                        boolean unused = CropImage.this.f1990m = false;
                    }
                });
            }
        }
    }

    public CropImage(Context context, CropImageView cropImageView) {
        this.f1984g = context;
        this.f1986i = cropImageView;
        this.f1986i.setCropImage(this);
    }

    /* renamed from: a */
    public void mo17065a(Bitmap bitmap) {
        if (!this.f1990m && bitmap != null) {
            this.f1990m = true;
            this.f1987j = bitmap;
            m2722a();
        }
    }

    /* renamed from: a */
    private void m2722a() {
        if (!((Activity) this.f1984g).isFinishing()) {
            m2723a((Runnable) new Runnable() {
                public void run() {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    final Bitmap a = CropImage.this.f1987j;
                    CropImage.this.f1985h.post(new Runnable() {
                        public void run() {
                            if (!(a == CropImage.this.f1987j || a == null)) {
                                CropImage.this.f1986i.mo17053a(a, true);
                                CropImage.this.f1987j.recycle();
                                Bitmap unused = CropImage.this.f1987j = a;
                            }
                            if (CropImage.this.f1986i.getScale() == 1.0f) {
                                CropImage.this.f1986i.mo17055a(true, true);
                            }
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        countDownLatch.await();
                        CropImage.this.f1982e.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, this.f1985h);
        }
    }

    /* renamed from: a */
    public void mo17064a(int i, int i2) {
        CGRect cGRect;
        boolean z = false;
        if (this.f1981d != null && this.f1987j != null && !this.f1987j.isRecycled()) {
            this.f1988k = i;
            this.f1989l = i2;
            CGRect cGRect2 = new CGRect(0.0f, 0.0f, (float) this.f1987j.getWidth(), (float) this.f1987j.getHeight());
            if (this.f1988k <= 0 || this.f1989l <= 0) {
                cGRect = cGRect2;
            } else {
                cGRect = CGRect.m2853a(cGRect2, new CGSize((float) this.f1988k, (float) this.f1989l), false);
            }
            this.f1994q = cGRect.mo17144f();
            Matrix matrix = new Matrix(this.f1986i.getImageMatrix());
            matrix.postTranslate((float) this.f1986i.getPaddingLeft(), (float) this.f1986i.getPaddingTop());
            this.f1986i.mo17053a(this.f1987j, true);
            HighlightView dVar = this.f1981d;
            Rect f = cGRect2.mo17144f();
            RectF e = cGRect.mo17142e();
            boolean z2 = this.f1995r;
            if (!(this.f1988k == 0 || this.f1989l == 0)) {
                z = true;
            }
            dVar.mo17079a(matrix, f, e, z2, z);
            this.f1986i.invalidate();
            this.f1981d.mo17087c();
            this.f1981d.mo17081a(true);
        }
    }

    /* renamed from: a */
    private void m2723a(Runnable runnable, Handler handler) {
        new Thread(new C1532a(runnable, handler)).start();
    }

    /* renamed from: b */
    public void mo17066b(int i, int i2) {
        this.f1988k = i;
        this.f1989l = i2;
    }
}
