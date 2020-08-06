package com.snaperfect.style.daguerre.math;

import android.graphics.PointF;
import java.util.StringTokenizer;

public class CGPoint extends PointF {

    /* renamed from: a */
    public static final CGPoint f2097a = new CGPoint();

    public CGPoint() {
    }

    public CGPoint(float f) {
        super(f, f);
    }

    public CGPoint(float f, float f2) {
        super(f, f2);
    }

    public CGPoint(CGSize cGSize) {
        this.x = cGSize.f2102a;
        this.y = cGSize.f2103b;
    }

    public CGPoint(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "{}, \t");
        this.x = Float.parseFloat(stringTokenizer.nextToken());
        this.y = Float.parseFloat(stringTokenizer.nextToken());
    }

    /* renamed from: a */
    public CGPoint mo17123a(CGPoint cGPoint) {
        this.x += cGPoint.x;
        this.y += cGPoint.y;
        return this;
    }

    /* renamed from: b */
    public CGPoint mo17127b(CGPoint cGPoint) {
        this.x -= cGPoint.x;
        this.y -= cGPoint.y;
        return this;
    }

    /* renamed from: a */
    public CGPoint mo17122a(float f) {
        this.x *= f;
        this.y *= f;
        return this;
    }

    /* renamed from: b */
    public CGPoint mo17126b(float f) {
        this.x /= f;
        this.y /= f;
        return this;
    }

    /* renamed from: a */
    public CGPoint mo17124a(CGSize cGSize) {
        this.x /= cGSize.f2102a;
        this.y /= cGSize.f2103b;
        return this;
    }

    /* renamed from: a */
    public CGPoint mo17121a() {
        this.x *= -1.0f;
        this.y *= -1.0f;
        return this;
    }

    /* renamed from: b */
    public CGPoint mo17125b() {
        this.x = (float) Math.round(this.x);
        this.y = (float) Math.round(this.y);
        return this;
    }

    /* renamed from: c */
    public CGPoint mo17130c(float f) {
        float sin = (float) Math.sin((double) f);
        float cos = (float) Math.cos((double) f);
        this.x = (this.x * cos) + (this.y * sin);
        this.y = (sin * (-this.x)) + (cos * this.y);
        return this;
    }

    /* renamed from: c */
    public float mo17129c(CGPoint cGPoint) {
        return (this.x * cGPoint.y) - (cGPoint.x * this.y);
    }

    /* renamed from: d */
    public float mo17131d(CGPoint cGPoint) {
        return (this.x * cGPoint.x) + (this.y * cGPoint.y);
    }

    /* renamed from: c */
    public float mo17128c() {
        CGPoint e = m2836e(this);
        float acos = (float) Math.acos((double) e.x);
        if (e.y > 0.0f) {
            return 6.2831855f - acos;
        }
        return acos;
    }

    public String toString() {
        return "{" + this.x + ", " + this.y + "}";
    }

    /* renamed from: a */
    public static CGPoint m2832a(CGPoint cGPoint, CGPoint cGPoint2) {
        return new CGPoint(cGPoint.x + cGPoint2.x, cGPoint.y + cGPoint2.y);
    }

    /* renamed from: b */
    public static CGPoint m2834b(CGPoint cGPoint, CGPoint cGPoint2) {
        return new CGPoint(cGPoint.x - cGPoint2.x, cGPoint.y - cGPoint2.y);
    }

    /* renamed from: a */
    public static CGPoint m2831a(CGPoint cGPoint, float f) {
        return new CGPoint(cGPoint.x * f, cGPoint.y * f);
    }

    /* renamed from: a */
    public static CGPoint m2833a(CGPoint cGPoint, CGSize cGSize) {
        return new CGPoint(cGPoint.x * cGSize.f2102a, cGPoint.y * cGSize.f2103b);
    }

    /* renamed from: e */
    public static CGPoint m2836e(CGPoint cGPoint) {
        return new CGPoint(cGPoint.x / cGPoint.length(), cGPoint.y / cGPoint.length());
    }

    /* renamed from: c */
    public static CGPoint m2835c(CGPoint cGPoint, CGPoint cGPoint2) {
        return m2832a(cGPoint, cGPoint2).mo17126b(2.0f);
    }

    /* renamed from: f */
    public static CGPoint m2837f(CGPoint cGPoint) {
        return new CGPoint(-cGPoint.x, -cGPoint.y);
    }
}
