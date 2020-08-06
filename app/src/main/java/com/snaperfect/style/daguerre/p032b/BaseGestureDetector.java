package com.snaperfect.style.daguerre.p032b;

import android.content.Context;
import android.view.MotionEvent;
import com.snaperfect.style.daguerre.math.CGPoint;

/* renamed from: com.snaperfect.style.daguerre.b.a */
public abstract class BaseGestureDetector {

    /* renamed from: a */
    protected final Context f1911a;

    /* renamed from: b */
    protected final int f1912b;

    /* renamed from: c */
    protected boolean f1913c;

    /* renamed from: d */
    protected MotionEvent f1914d;

    /* renamed from: e */
    protected float[] f1915e;

    /* renamed from: f */
    protected float[] f1916f;

    /* renamed from: g */
    protected int[] f1917g;

    /* renamed from: h */
    protected long f1918h;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo17010a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo17011a(int i, MotionEvent motionEvent);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract boolean mo17014b(int i, MotionEvent motionEvent);

    public BaseGestureDetector(Context context, int i) {
        this.f1911a = context;
        this.f1912b = i;
        this.f1915e = new float[i];
        this.f1916f = new float[i];
        this.f1917g = new int[i];
    }

    /* renamed from: a */
    public boolean mo17012a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != this.f1912b) {
            if (this.f1913c) {
                mo17010a();
            }
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (!this.f1913c) {
            mo17016c(motionEvent);
            this.f1913c = mo17011a(action, motionEvent);
        } else if (mo17015b(motionEvent)) {
            this.f1913c = mo17014b(action, motionEvent);
        } else if (this.f1913c) {
            mo17010a();
        }
        return this.f1913c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo17015b(MotionEvent motionEvent) {
        for (int i = 0; i < this.f1912b; i++) {
            if (motionEvent.findPointerIndex(this.f1917g[i]) < 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo17016c(MotionEvent motionEvent) {
        for (int i = 0; i < this.f1912b; i++) {
            this.f1917g[i] = motionEvent.getPointerId(i);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo17018d(MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent != null) {
            MotionEvent motionEvent2 = this.f1914d;
            if (motionEvent.getActionMasked() != 1) {
                z = false;
            }
            int actionIndex = z ? motionEvent.getActionIndex() : -1;
            for (int i = 0; i < this.f1912b; i++) {
                if (actionIndex != i) {
                    this.f1915e[i] = motionEvent.getPressure(motionEvent.findPointerIndex(this.f1917g[i]));
                    if (motionEvent2 != null) {
                        this.f1916f[i] = motionEvent2.getPressure(motionEvent2.findPointerIndex(this.f1917g[i]));
                    }
                }
            }
            if (motionEvent2 != null) {
                this.f1918h = motionEvent.getEventTime() - motionEvent2.getEventTime();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo17013b() {
        if (this.f1914d != null) {
            this.f1914d.recycle();
            this.f1914d = null;
        }
        this.f1913c = false;
    }

    /* renamed from: c */
    public boolean mo17017c() {
        for (int i = 0; i < this.f1912b; i++) {
            if (this.f1915e[i] / this.f1916f[i] < 0.67f) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: e */
    public CGPoint mo17019e(MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getActionMasked() != 1) {
            z = false;
        }
        int actionIndex = z ? motionEvent.getActionIndex() : -1;
        float f = 0.0f;
        float f2 = 0.0f;
        int i = 0;
        for (int i2 = 0; i2 < this.f1912b; i2++) {
            if (actionIndex != i2) {
                i++;
                f2 += motionEvent.getX(motionEvent.findPointerIndex(this.f1917g[i2]));
                f += motionEvent.getY(motionEvent.findPointerIndex(this.f1917g[i2]));
            }
        }
        return new CGPoint(f2 / ((float) i), f / ((float) i));
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public CGPoint mo17020f(MotionEvent motionEvent) {
        return new CGPoint(motionEvent.getRawX(), motionEvent.getRawY());
    }
}
