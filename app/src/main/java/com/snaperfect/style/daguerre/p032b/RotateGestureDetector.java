package com.snaperfect.style.daguerre.p032b;

import android.content.Context;
import android.view.MotionEvent;

/* renamed from: com.snaperfect.style.daguerre.b.c */
public class RotateGestureDetector extends TwoFingerGestureDetector {

    /* renamed from: o */
    private final C1520a f1925o;

    /* renamed from: p */
    private boolean f1926p;

    /* renamed from: com.snaperfect.style.daguerre.b.c$a */
    /* compiled from: RotateGestureDetector */
    public interface C1520a {
        /* renamed from: a */
        boolean mo17029a(RotateGestureDetector cVar);

        /* renamed from: b */
        boolean mo17030b(RotateGestureDetector cVar);

        /* renamed from: c */
        void mo17031c(RotateGestureDetector cVar);
    }

    public RotateGestureDetector(Context context, C1520a aVar) {
        super(context);
        this.f1925o = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo17011a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                return this.f1925o.mo17030b(this);
            case 5:
                mo17013b();
                this.f1914d = MotionEvent.obtain(motionEvent);
                this.f1918h = 0;
                mo17018d(motionEvent);
                return this.f1925o.mo17030b(this);
            case 6:
                if (!this.f1926p) {
                }
                break;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo17014b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                mo17018d(motionEvent);
                if (mo17017c() && this.f1925o.mo17029a(this)) {
                    if (this.f1914d != null) {
                        this.f1914d.recycle();
                    }
                    this.f1914d = MotionEvent.obtain(motionEvent);
                    break;
                }
            case 3:
                if (!this.f1926p) {
                    this.f1925o.mo17031c(this);
                }
                mo17013b();
                break;
            case 6:
                mo17018d(motionEvent);
                if (!this.f1926p) {
                    this.f1925o.mo17031c(this);
                }
                mo17013b();
                break;
        }
        return this.f1913c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17010a() {
        this.f1925o.mo17031c(this);
        mo17013b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo17013b() {
        super.mo17013b();
        this.f1926p = false;
    }

    /* renamed from: d */
    public float mo17028d() {
        return (float) (Math.atan2((double) this.f1930l, (double) this.f1929k) - Math.atan2((double) this.f1928j, (double) this.f1927i));
    }
}
