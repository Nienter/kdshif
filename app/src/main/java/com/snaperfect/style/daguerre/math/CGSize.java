package com.snaperfect.style.daguerre.math;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.StringTokenizer;

public class CGSize implements Parcelable {
    public static final Parcelable.Creator<CGSize> CREATOR = new Parcelable.Creator<CGSize>() {
        /* renamed from: a */
        public CGSize createFromParcel(Parcel parcel) {
            return new CGSize(parcel);
        }

        /* renamed from: a */
        public CGSize[] newArray(int i) {
            return new CGSize[i];
        }
    };

    /* renamed from: a */
    public float f2102a;

    /* renamed from: b */
    public float f2103b;

    public CGSize() {
    }

    public CGSize(float f) {
        this.f2102a = f;
        this.f2103b = f;
    }

    public CGSize(float f, float f2) {
        this.f2102a = f;
        this.f2103b = f2;
    }

    public CGSize(Parcel parcel) {
        this.f2102a = parcel.readFloat();
        this.f2103b = parcel.readFloat();
    }

    public CGSize(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "{}, \t");
        this.f2102a = Float.parseFloat(stringTokenizer.nextToken());
        this.f2103b = Float.parseFloat(stringTokenizer.nextToken());
    }

    /* renamed from: a */
    public CGSize mo17152a(float f) {
        this.f2102a *= f;
        this.f2103b *= f;
        return this;
    }

    /* renamed from: a */
    public CGSize mo17154a(CGSize cGSize) {
        this.f2102a *= cGSize.f2102a;
        this.f2103b *= cGSize.f2103b;
        return this;
    }

    /* renamed from: a */
    public CGSize mo17153a(float f, float f2) {
        this.f2102a *= f;
        this.f2103b *= f2;
        return this;
    }

    /* renamed from: b */
    public CGSize mo17156b(float f) {
        this.f2102a /= f;
        this.f2103b /= f;
        return this;
    }

    /* renamed from: b */
    public CGSize mo17157b(CGSize cGSize) {
        this.f2102a /= cGSize.f2102a;
        this.f2103b /= cGSize.f2103b;
        return this;
    }

    /* renamed from: c */
    public CGSize mo17159c(CGSize cGSize) {
        return mo17152a(m2873b(cGSize, this).mo17163e());
    }

    /* renamed from: d */
    public CGSize mo17161d(CGSize cGSize) {
        return mo17152a(m2873b(cGSize, this).mo17164f());
    }

    /* renamed from: a */
    public CGSize mo17151a() {
        this.f2102a = (float) Math.round(this.f2102a);
        this.f2103b = (float) Math.round(this.f2103b);
        return this;
    }

    /* renamed from: b */
    public CGSize mo17155b() {
        int round = Math.round(this.f2102a);
        int round2 = Math.round(this.f2103b);
        this.f2102a = round % 2 == 0 ? (float) round : (float) (round + 1);
        this.f2103b = round2 % 2 == 0 ? (float) round2 : (float) (round2 + 1);
        return this;
    }

    /* renamed from: c */
    public CGSize mo17158c() {
        if (this.f2102a > this.f2103b) {
            this.f2103b = this.f2102a;
        } else {
            this.f2102a = this.f2103b;
        }
        return this;
    }

    /* renamed from: d */
    public CGSize mo17160d() {
        if (this.f2102a < this.f2103b) {
            this.f2103b = this.f2102a;
        } else {
            this.f2102a = this.f2103b;
        }
        return this;
    }

    /* renamed from: e */
    public float mo17163e() {
        return this.f2102a > this.f2103b ? this.f2102a : this.f2103b;
    }

    /* renamed from: f */
    public float mo17164f() {
        return this.f2102a < this.f2103b ? this.f2102a : this.f2103b;
    }

    /* renamed from: g */
    public boolean mo17165g() {
        return this.f2102a == 0.0f && this.f2103b == 0.0f;
    }

    public String toString() {
        return "{" + this.f2102a + ", " + this.f2103b + "}";
    }

    /* renamed from: h */
    public PointF mo17166h() {
        return new PointF(this.f2102a, this.f2103b);
    }

    /* renamed from: i */
    public Point mo17167i() {
        return new Point((int) this.f2102a, (int) this.f2103b);
    }

    /* renamed from: a */
    public static CGSize m2871a(CGSize cGSize, CGSize cGSize2) {
        return new CGSize(cGSize.f2102a - cGSize2.f2102a, cGSize.f2103b - cGSize2.f2103b);
    }

    /* renamed from: a */
    public static CGSize m2870a(CGSize cGSize, float f) {
        return new CGSize(cGSize.f2102a * f, cGSize.f2103b * f);
    }

    /* renamed from: b */
    public static CGSize m2872b(CGSize cGSize, float f) {
        return new CGSize(cGSize.f2102a / f, cGSize.f2103b / f);
    }

    /* renamed from: b */
    public static CGSize m2873b(CGSize cGSize, CGSize cGSize2) {
        return new CGSize(cGSize.f2102a / cGSize2.f2102a, cGSize.f2103b / cGSize2.f2103b);
    }

    /* renamed from: c */
    public static CGSize m2874c(CGSize cGSize, CGSize cGSize2) {
        return m2873b(cGSize2, cGSize).mo17158c().mo17154a(cGSize);
    }

    /* renamed from: d */
    public static CGSize m2875d(CGSize cGSize, CGSize cGSize2) {
        return m2873b(cGSize2, cGSize).mo17160d().mo17154a(cGSize);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f2102a);
        parcel.writeFloat(this.f2103b);
    }
}
