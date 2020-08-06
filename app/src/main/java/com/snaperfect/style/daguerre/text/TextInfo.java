package com.snaperfect.style.daguerre.text;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.TextPaint;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.math.CGSize;
import com.snaperfect.style.daguerre.utils.DisplayUtil;

/* renamed from: com.snaperfect.style.daguerre.text.c */
public class TextInfo {

    /* renamed from: a */
    private Typeface f2188a;

    /* renamed from: b */
    private float f2189b;

    /* renamed from: c */
    private Layout.Alignment f2190c;

    /* renamed from: d */
    private int f2191d;

    /* renamed from: e */
    private float f2192e;

    /* renamed from: f */
    private float f2193f;

    /* renamed from: g */
    private float f2194g;

    /* renamed from: h */
    private float f2195h;

    /* renamed from: i */
    private float f2196i;

    /* renamed from: j */
    private CGPoint f2197j;

    /* renamed from: k */
    private CGSize f2198k;

    /* renamed from: l */
    private float f2199l;

    /* renamed from: m */
    private String f2200m;

    /* renamed from: n */
    private TextPaint f2201n;

    private TextInfo() {
    }

    public TextInfo(TextInfo cVar, float f) {
        this.f2188a = cVar.mo17223a();
        this.f2189b = cVar.mo17230b() * f;
        this.f2190c = cVar.mo17234d();
        this.f2191d = cVar.mo17235e();
        this.f2192e = cVar.mo17236f() * f;
        this.f2193f = cVar.mo17237g() * f;
        this.f2194g = cVar.mo17238h() * f;
        this.f2195h = cVar.mo17239i() * f;
        this.f2196i = cVar.mo17240j();
        if (cVar.f2197j != null) {
            this.f2197j = CGPoint.m2831a(cVar.f2197j, f);
            this.f2198k = CGSize.m2870a(cVar.f2198k, f);
            this.f2199l = cVar.f2199l;
            this.f2200m = cVar.f2200m;
        }
    }

    /* renamed from: a */
    public static TextInfo m2978a(Context context) {
        TextInfo cVar = new TextInfo();
        cVar.f2188a = Typeface.DEFAULT;
        cVar.f2189b = 30.0f;
        cVar.f2190c = Layout.Alignment.ALIGN_NORMAL;
        cVar.f2191d = -1;
        cVar.f2192e = 0.0f;
        cVar.f2193f = 0.0f;
        cVar.f2194g = 0.0f;
        cVar.f2195h = 0.0f;
        cVar.f2196i = 1.0f;
        return cVar;
    }

    /* renamed from: a */
    public Typeface mo17223a() {
        return this.f2188a;
    }

    /* renamed from: b */
    public float mo17230b() {
        return this.f2189b;
    }

    /* renamed from: c */
    public float mo17232c() {
        return (float) DisplayUtil.m3089b(this.f2189b);
    }

    /* renamed from: d */
    public Layout.Alignment mo17234d() {
        return this.f2190c;
    }

    /* renamed from: e */
    public int mo17235e() {
        return this.f2191d;
    }

    /* renamed from: f */
    public float mo17236f() {
        return this.f2192e;
    }

    /* renamed from: g */
    public float mo17237g() {
        return this.f2193f;
    }

    /* renamed from: h */
    public float mo17238h() {
        return this.f2194g;
    }

    /* renamed from: i */
    public float mo17239i() {
        return this.f2195h;
    }

    /* renamed from: j */
    public float mo17240j() {
        return this.f2196i;
    }

    /* renamed from: k */
    public CGPoint mo17241k() {
        return this.f2197j;
    }

    /* renamed from: l */
    public CGSize mo17242l() {
        return this.f2198k;
    }

    /* renamed from: m */
    public float mo17243m() {
        return this.f2199l;
    }

    /* renamed from: n */
    public String mo17244n() {
        return this.f2200m;
    }

    /* renamed from: a */
    public void mo17227a(Typeface typeface) {
        this.f2188a = typeface;
        if (this.f2201n != null) {
            this.f2201n.setTypeface(typeface);
        }
    }

    /* renamed from: a */
    public void mo17224a(float f) {
        this.f2189b = f;
        if (this.f2201n != null) {
            this.f2201n.setTextSize(mo17232c());
        }
    }

    /* renamed from: a */
    public void mo17226a(int i) {
        this.f2191d = i;
        if (this.f2201n != null) {
            this.f2201n.setColor(i);
        }
    }

    /* renamed from: a */
    public void mo17228a(Layout.Alignment alignment) {
        this.f2190c = alignment;
    }

    /* renamed from: b */
    public void mo17231b(float f) {
        this.f2193f = f;
    }

    /* renamed from: a */
    public void mo17225a(float f, float f2) {
        this.f2194g = f;
        this.f2195h = f2;
    }

    /* renamed from: c */
    public void mo17233c(float f) {
        this.f2196i = f;
    }

    /* renamed from: a */
    public void mo17229a(CGPoint cGPoint, CGSize cGSize, float f, String str) {
        this.f2197j = cGPoint;
        this.f2198k = cGSize;
        this.f2199l = f;
        this.f2200m = str;
    }

    /* renamed from: o */
    public TextPaint mo17245o() {
        if (this.f2201n == null) {
            this.f2201n = new TextPaint();
            this.f2201n.setTypeface(this.f2188a);
            this.f2201n.setTextSize(mo17232c());
            this.f2201n.setColor(this.f2191d);
        }
        return this.f2201n;
    }
}
