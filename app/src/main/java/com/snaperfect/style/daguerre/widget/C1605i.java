package com.snaperfect.style.daguerre.widget;

/* renamed from: com.snaperfect.style.daguerre.widget.i */
/* compiled from: AlignHelper */
class C1605i extends AlignHelper {

    /* renamed from: d */
    private float f2411d;

    /* renamed from: e */
    private int f2412e;

    public C1605i(float f, String str) {
        super(f, str);
    }

    /* renamed from: a */
    public void mo17453a() {
        this.f2412e = 0;
        this.f2411d = 0.0f;
    }

    /* renamed from: a */
    public float mo17452a(float f, float f2) {
        this.f2411d += f2;
        float f3 = f + f2;
        switch (this.f2412e) {
            case 0:
                if (Math.abs(f3) <= this.f2354b * 1.5f) {
                    return f2;
                }
                this.f2412e = 1;
                this.f2411d = 0.0f;
                return f2;
            case 1:
                if (Math.abs(f3) >= this.f2354b) {
                    return f2;
                }
                this.f2412e = 2;
                float f4 = f2 - f3;
                this.f2411d = 0.0f;
                return f4;
            case 2:
                if (Math.abs(this.f2411d) <= this.f2354b) {
                    return 0.0f;
                }
                this.f2412e = 0;
                this.f2411d = 0.0f;
                return f2;
            default:
                return f2;
        }
    }
}
