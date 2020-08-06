package com.snaperfect.style.daguerre.widget;

/* renamed from: com.snaperfect.style.daguerre.widget.e */
/* compiled from: AlignHelper */
class C1602e extends AlignHelper {

    /* renamed from: d */
    private float[] f2373d;

    /* renamed from: e */
    private int[] f2374e;

    public C1602e(float f, String str, int i) {
        super(f, str);
        this.f2373d = new float[i];
        this.f2374e = new int[i];
    }

    /* renamed from: a */
    public float[] mo17416a(float[] fArr, float[] fArr2) {
        boolean[] zArr = {false, false};
        for (int i = 0; i < this.f2373d.length; i++) {
            int length = i % fArr2.length;
            int length2 = i % zArr.length;
            float[] fArr3 = this.f2373d;
            fArr3[i] = fArr3[i] + fArr2[length];
            float f = fArr[i] + fArr2[length];
            if (!zArr[length2]) {
                switch (this.f2374e[i]) {
                    case 0:
                        if (Math.abs(f) > this.f2354b * 1.5f) {
                            this.f2374e[i] = 1;
                            this.f2373d[i] = 0.0f;
                            break;
                        }
                        break;
                    case 1:
                        if (Math.abs(f) < this.f2354b) {
                            this.f2374e[i] = 2;
                            fArr2[length] = fArr2[length] - f;
                            this.f2373d[i] = 0.0f;
                            int i2 = i % 2 == 0 ? i + 1 : i - 1;
                            if (i2 < this.f2373d.length && this.f2374e[i2] == 0) {
                                this.f2374e[i2] = 1;
                                break;
                            }
                        }
                        break;
                    case 2:
                        if (Math.abs(this.f2373d[i]) <= this.f2354b) {
                            fArr2[length] = 0.0f;
                            break;
                        } else {
                            this.f2374e[i] = 0;
                            this.f2373d[i] = 0.0f;
                            break;
                        }
                }
            }
            if (this.f2374e[i] == 2) {
                zArr[length2] = true;
            }
            if (zArr[0] && zArr[1]) {
                return fArr2;
            }
        }
        return fArr2;
    }

    /* renamed from: a */
    public void mo17415a() {
        for (int i = 0; i < this.f2373d.length; i++) {
            this.f2373d[i] = 0.0f;
            this.f2374e[i] = 0;
        }
    }
}
