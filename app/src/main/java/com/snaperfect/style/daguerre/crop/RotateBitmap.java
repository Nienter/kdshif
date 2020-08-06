package com.snaperfect.style.daguerre.crop;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* renamed from: com.snaperfect.style.daguerre.crop.e */
public class RotateBitmap {

    /* renamed from: a */
    private Bitmap f2030a;

    /* renamed from: b */
    private int f2031b = 0;

    public RotateBitmap(Bitmap bitmap) {
        this.f2030a = bitmap;
    }

    /* renamed from: a */
    public void mo17089a(int i) {
        this.f2031b = i;
    }

    /* renamed from: a */
    public int mo17088a() {
        return this.f2031b;
    }

    /* renamed from: b */
    public Bitmap mo17091b() {
        return this.f2030a;
    }

    /* renamed from: a */
    public void mo17090a(Bitmap bitmap) {
        this.f2030a = bitmap;
    }

    /* renamed from: c */
    public Matrix mo17092c() {
        Matrix matrix = new Matrix();
        if (this.f2031b != 0) {
            matrix.preTranslate((float) (-(this.f2030a.getWidth() / 2)), (float) (-(this.f2030a.getHeight() / 2)));
            matrix.postRotate((float) this.f2031b);
            matrix.postTranslate((float) (mo17095f() / 2), (float) (mo17094e() / 2));
        }
        return matrix;
    }

    /* renamed from: d */
    public boolean mo17093d() {
        return (this.f2031b / 90) % 2 != 0;
    }

    /* renamed from: e */
    public int mo17094e() {
        if (mo17093d()) {
            return this.f2030a.getWidth();
        }
        return this.f2030a.getHeight();
    }

    /* renamed from: f */
    public int mo17095f() {
        if (mo17093d()) {
            return this.f2030a.getHeight();
        }
        return this.f2030a.getWidth();
    }
}
