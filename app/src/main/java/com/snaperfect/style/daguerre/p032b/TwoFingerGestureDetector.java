package com.snaperfect.style.daguerre.p032b;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* renamed from: com.snaperfect.style.daguerre.b.d */
public abstract class TwoFingerGestureDetector extends BaseGestureDetector {

    /* renamed from: i */
    protected float f1927i;

    /* renamed from: j */
    protected float f1928j;

    /* renamed from: k */
    protected float f1929k;

    /* renamed from: l */
    protected float f1930l;

    /* renamed from: m */
    protected float f1931m;

    /* renamed from: n */
    protected float f1932n;

    /* renamed from: o */
    private final float f1933o;

    /* renamed from: p */
    private float f1934p;

    /* renamed from: q */
    private float f1935q;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo17010a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo17011a(int i, MotionEvent motionEvent);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract boolean mo17014b(int i, MotionEvent motionEvent);

    public TwoFingerGestureDetector(Context context) {
        super(context, 2);
        this.f1933o = (float) ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo17018d(MotionEvent motionEvent) {
        super.mo17018d(motionEvent);
        if (this.f1914d != null) {
            MotionEvent motionEvent2 = this.f1914d;
            this.f1934p = -1.0f;
            this.f1935q = -1.0f;
            float x = motionEvent2.getX(motionEvent2.findPointerIndex(this.f1917g[0]));
            float y = motionEvent2.getY(motionEvent2.findPointerIndex(this.f1917g[0]));
            float x2 = motionEvent2.getX(motionEvent2.findPointerIndex(this.f1917g[1]));
            float y2 = motionEvent2.getY(motionEvent2.findPointerIndex(this.f1917g[1]));
            this.f1927i = x2 - x;
            this.f1928j = y2 - y;
            float x3 = motionEvent.getX(motionEvent.findPointerIndex(this.f1917g[0]));
            float y3 = motionEvent.getY(motionEvent.findPointerIndex(this.f1917g[0]));
            float x4 = motionEvent.getX(motionEvent.findPointerIndex(this.f1917g[1]));
            float y4 = motionEvent.getY(motionEvent.findPointerIndex(this.f1917g[1]));
            this.f1929k = x4 - x3;
            this.f1930l = y4 - y3;
            this.f1931m = (((x + x2) + x3) + x4) / 4.0f;
            this.f1932n = (((y2 + y) + y3) + y4) / 4.0f;
        }
    }

    /* renamed from: e */
    public float mo17032e() {
        return this.f1931m;
    }

    /* renamed from: f */
    public float mo17033f() {
        return this.f1932n;
    }
}
