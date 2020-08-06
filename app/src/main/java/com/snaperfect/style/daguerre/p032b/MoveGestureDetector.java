package com.snaperfect.style.daguerre.p032b;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import com.snaperfect.style.daguerre.math.CGPoint;

/* renamed from: com.snaperfect.style.daguerre.b.b */
public class MoveGestureDetector extends BaseGestureDetector {

    /* renamed from: i */
    private static final PointF f1919i = new PointF();

    /* renamed from: j */
    private final C1519a f1920j;

    /* renamed from: k */
    private final boolean f1921k;

    /* renamed from: l */
    private boolean f1922l = false;

    /* renamed from: m */
    private CGPoint f1923m = new CGPoint();

    /* renamed from: n */
    private CGPoint f1924n = new CGPoint();

    /* renamed from: com.snaperfect.style.daguerre.b.b$a */
    /* compiled from: MoveGestureDetector */
    public interface C1519a {
        /* renamed from: a */
        boolean mo17025a(MoveGestureDetector bVar);

        /* renamed from: b */
        boolean mo17026b(MoveGestureDetector bVar);

        /* renamed from: c */
        void mo17027c(MoveGestureDetector bVar);
    }

    public MoveGestureDetector(Context context, int i, boolean z, C1519a aVar) {
        super(context, i);
        this.f1920j = aVar;
        this.f1921k = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo17011a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 0:
                mo17013b();
                this.f1914d = MotionEvent.obtain(motionEvent);
                this.f1918h = 0;
                this.f1922l = false;
                mo17018d(motionEvent);
                return this.f1920j.mo17026b(this);
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo17014b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 1:
            case 3:
                this.f1920j.mo17027c(this);
                mo17013b();
                break;
            case 2:
                mo17018d(motionEvent);
                if (mo17017c()) {
                    this.f1922l = true;
                    if (this.f1920j.mo17025a(this)) {
                        if (this.f1914d != null) {
                            this.f1914d.recycle();
                        }
                        this.f1914d = MotionEvent.obtain(motionEvent);
                        break;
                    }
                }
                break;
        }
        return this.f1913c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17010a() {
        this.f1920j.mo17027c(this);
        mo17013b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo17018d(MotionEvent motionEvent) {
        super.mo17018d(motionEvent);
        if (motionEvent != null) {
            MotionEvent motionEvent2 = this.f1914d;
            CGPoint f = this.f1921k ? mo17020f(motionEvent) : mo17019e(motionEvent);
            this.f1924n = f.mo17127b(motionEvent2 != null ? this.f1921k ? mo17020f(motionEvent2) : mo17019e(motionEvent2) : f);
            this.f1923m.mo17123a(this.f1924n);
        }
    }

    /* renamed from: d */
    public boolean mo17021d() {
        return this.f1922l;
    }

    /* renamed from: e */
    public float mo17022e() {
        return this.f1914d.getRawX();
    }

    /* renamed from: f */
    public float mo17023f() {
        return this.f1914d.getRawY();
    }

    /* renamed from: g */
    public PointF mo17024g() {
        return this.f1924n;
    }
}
