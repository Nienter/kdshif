package com.snaperfect.style.daguerre.math;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;

public class CGRect implements Parcelable {
    public static final Parcelable.Creator<CGRect> CREATOR = new Parcelable.Creator<CGRect>() {
        /* renamed from: a */
        public CGRect createFromParcel(Parcel parcel) {
            return new CGRect(parcel);
        }

        /* renamed from: a */
        public CGRect[] newArray(int i) {
            return new CGRect[i];
        }
    };

    /* renamed from: a */
    public float f2098a;

    /* renamed from: b */
    public float f2099b;

    /* renamed from: c */
    public float f2100c;

    /* renamed from: d */
    public float f2101d;

    public CGRect() {
    }

    public CGRect(float f, float f2, float f3, float f4) {
        this.f2098a = f;
        this.f2099b = f2;
        this.f2100c = f3;
        this.f2101d = f4;
    }

    public CGRect(Parcel parcel) {
        this(parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
    }

    /* renamed from: a */
    public CGRect mo17135a(CGSize cGSize) {
        this.f2098a /= cGSize.f2102a;
        this.f2099b /= cGSize.f2103b;
        this.f2100c /= cGSize.f2102a;
        this.f2101d /= cGSize.f2103b;
        return this;
    }

    /* renamed from: a */
    public CGRect mo17134a(float f, float f2) {
        float max = Math.max(f / this.f2100c, f2 / this.f2101d);
        float f3 = f / max;
        float f4 = f2 / max;
        this.f2098a += (this.f2100c - f3) / 2.0f;
        this.f2099b += (this.f2101d - f4) / 2.0f;
        this.f2100c = f3;
        this.f2101d = f4;
        return this;
    }

    /* renamed from: a */
    public CGRect mo17133a() {
        this.f2098a = (float) Math.round(this.f2098a);
        this.f2099b = (float) Math.round(this.f2099b);
        this.f2100c = (float) Math.round(this.f2100c);
        this.f2101d = (float) Math.round(this.f2101d);
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CGRect)) {
            return false;
        }
        CGRect cGRect = (CGRect) obj;
        if (Math.abs(this.f2098a - cGRect.f2098a) >= 1.0E-4f || Math.abs(this.f2099b - cGRect.f2099b) >= 1.0E-4f || Math.abs(this.f2100c - cGRect.f2100c) >= 1.0E-4f || Math.abs(this.f2101d - cGRect.f2101d) >= 1.0E-4f) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public CGPoint mo17137b() {
        return new CGPoint(this.f2098a + (this.f2100c / 2.0f), this.f2099b + (this.f2101d / 2.0f));
    }

    /* renamed from: c */
    public CGPoint mo17139c() {
        return new CGPoint(this.f2098a, this.f2099b);
    }

    /* renamed from: b */
    public boolean mo17138b(float f, float f2) {
        return f >= this.f2098a && f <= this.f2098a + this.f2100c && f2 >= this.f2099b && f2 <= this.f2099b + this.f2101d;
    }

    /* renamed from: a */
    public boolean mo17136a(float[] fArr) {
        int length = fArr.length / 2;
        for (int i = 0; i < length; i++) {
            if (mo17138b(fArr[i * 2], fArr[(i * 2) + 1])) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public float mo17140d() {
        return Math.min(this.f2100c, this.f2101d);
    }

    /* renamed from: e */
    public RectF mo17142e() {
        return new RectF(this.f2098a, this.f2099b, this.f2098a + this.f2100c, this.f2099b + this.f2101d);
    }

    /* renamed from: f */
    public Rect mo17144f() {
        return new Rect((int) this.f2098a, (int) this.f2099b, (int) (this.f2098a + this.f2100c), (int) (this.f2099b + this.f2101d));
    }

    public String toString() {
        return "{{" + this.f2098a + ", " + this.f2099b + "}, {" + this.f2100c + ", " + this.f2101d + "}}";
    }

    /* renamed from: a */
    public static CGRect m2851a(CGPoint cGPoint, CGSize cGSize) {
        return new CGRect(cGPoint.x - (cGSize.f2102a / 2.0f), cGPoint.y - (cGSize.f2103b / 2.0f), cGSize.f2102a, cGSize.f2103b);
    }

    /* renamed from: a */
    public static CGRect m2852a(CGRect cGRect, CGSize cGSize) {
        return new CGRect(cGRect.f2098a * cGSize.f2102a, cGRect.f2099b * cGSize.f2103b, cGRect.f2100c * cGSize.f2102a, cGRect.f2101d * cGSize.f2103b);
    }

    /* renamed from: a */
    private static CGRect m2854a(CGRect cGRect, CGSize cGSize, boolean z, boolean z2) {
        float f = cGSize.f2102a / cGRect.f2100c;
        float f2 = cGSize.f2103b / cGRect.f2101d;
        float max = z ? Math.max(f, f2) : Math.min(f, f2);
        if (z && z2 && Math.abs(cGSize.f2102a - cGSize.f2103b) < 10.0f) {
            max *= 1.25f;
        }
        float f3 = cGSize.f2102a / max;
        float f4 = cGSize.f2103b / max;
        return new CGRect(cGRect.f2098a + ((cGRect.f2100c - f3) / 2.0f), cGRect.f2099b + ((cGRect.f2101d - f4) / 2.0f), f3, f4);
    }

    /* renamed from: a */
    public static CGRect m2853a(CGRect cGRect, CGSize cGSize, boolean z) {
        return m2854a(cGRect, cGSize, true, z);
    }

    /* renamed from: b */
    public static CGRect m2856b(CGRect cGRect, CGSize cGSize) {
        return m2854a(cGRect, cGSize, false, false);
    }

    /* renamed from: c */
    public static CGRect m2857c(CGRect cGRect, CGSize cGSize) {
        return new CGRect(cGRect.f2098a * cGSize.f2102a, cGRect.f2099b * cGSize.f2103b, cGRect.f2100c * cGSize.f2102a, cGRect.f2101d * cGSize.f2103b);
    }

    /* renamed from: a */
    private static Matrix m2850a(CGRect cGRect, CGSize cGSize, Matrix matrix, float[] fArr, boolean z) {
        CGRect a = m2854a(cGRect, cGSize, z, true);
        float f = a.f2100c / cGSize.f2102a;
        float f2 = a.f2098a - cGRect.f2098a;
        float f3 = a.f2099b - cGRect.f2099b;
        if (matrix == null) {
            matrix = new Matrix();
        }
        matrix.reset();
        matrix.postScale(f, f);
        matrix.postTranslate(f2, f3);
        if (fArr != null && fArr.length > 0) {
            fArr[0] = f;
        }
        return matrix;
    }

    /* renamed from: a */
    public static Matrix m2849a(CGRect cGRect, CGSize cGSize, Matrix matrix, float[] fArr) {
        return m2850a(cGRect, cGSize, matrix, fArr, false);
    }

    /* renamed from: b */
    public static Matrix m2855b(CGRect cGRect, CGSize cGSize, Matrix matrix, float[] fArr) {
        return m2850a(cGRect, cGSize, matrix, fArr, true);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f2098a);
        parcel.writeFloat(this.f2099b);
        parcel.writeFloat(this.f2100c);
        parcel.writeFloat(this.f2101d);
    }
}
