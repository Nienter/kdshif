package com.snaperfect.style.daguerre.frame;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.share.internal.ShareConstants;
import com.snaperfect.style.daguerre.math.CGFloat;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.math.CGRect;
import com.snaperfect.style.daguerre.math.CGSize;
import com.snaperfect.style.daguerre.utils.Pair;
import com.snaperfect.style.daguerre.utils.PathUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.snaperfect.style.daguerre.frame.d */
public class CollageFrameTile {

    /* renamed from: a */
    final CGPoint[] f2086a;

    /* renamed from: b */
    final CGPoint[] f2087b;

    /* renamed from: c */
    public final CGPoint[] f2088c;

    /* renamed from: d */
    public final int[] f2089d;

    /* renamed from: e */
    public final Path f2090e;

    /* renamed from: f */
    public final CGRect f2091f;

    /* renamed from: g */
    public final int f2092g;

    /* renamed from: h */
    public int f2093h;

    /* renamed from: i */
    public float f2094i;

    /* renamed from: a */
    private static Path m2816a(String str) {
        return PathUtils.m3110a(str);
    }

    /* renamed from: a */
    private static CGPoint[] m2819a(JSONArray jSONArray) {
        CGPoint[] cGPointArr = new CGPoint[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            cGPointArr[i] = new CGPoint(jSONArray.getString(i));
        }
        return cGPointArr;
    }

    /* renamed from: b */
    private static int[] m2821b(JSONArray jSONArray) {
        int[] iArr = new int[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            iArr[i] = jSONArray.getInt(i);
        }
        return iArr;
    }

    /* renamed from: a */
    private static CGPoint[] m2820a(CGPoint[] cGPointArr, CGSize cGSize) {
        CGPoint[] cGPointArr2 = new CGPoint[cGPointArr.length];
        for (int i = 0; i < cGPointArr.length; i++) {
            cGPointArr2[i] = CGPoint.m2833a(cGPointArr[i], cGSize).mo17125b();
        }
        return cGPointArr2;
    }

    public CollageFrameTile(JSONObject jSONObject) {
        int i;
        int i2 = 0;
        if (jSONObject.has("path")) {
            this.f2086a = null;
            this.f2087b = null;
            this.f2088c = null;
            this.f2089d = null;
            this.f2090e = m2816a(jSONObject.getString("path"));
            RectF rectF = new RectF();
            this.f2090e.computeBounds(rectF, true);
            this.f2091f = new CGRect(rectF.left, rectF.top, rectF.width(), rectF.height());
        } else {
            this.f2090e = null;
            this.f2086a = m2819a(jSONObject.getJSONArray("verteices"));
            this.f2087b = (CGPoint[]) this.f2086a.clone();
            this.f2088c = (CGPoint[]) this.f2087b.clone();
            this.f2089d = m2821b(jSONObject.getJSONArray("vertexTypes"));
            this.f2091f = m2822d();
        }
        if (jSONObject.has(ShareConstants.MEDIA_TYPE)) {
            i = jSONObject.getInt(ShareConstants.MEDIA_TYPE);
        } else {
            i = 0;
        }
        this.f2092g = i;
        this.f2093h = jSONObject.has("filter") ? jSONObject.getInt("filter") : i2;
        this.f2094i = 1.0f;
    }

    public CollageFrameTile(CollageFrameTile dVar, CGSize cGSize) {
        if (dVar.f2090e == null) {
            this.f2090e = null;
            this.f2086a = m2820a(dVar.f2086a, cGSize);
            this.f2087b = (CGPoint[]) this.f2086a.clone();
            this.f2088c = (CGPoint[]) this.f2087b.clone();
            this.f2089d = dVar.f2089d;
            this.f2091f = m2822d();
        } else {
            this.f2090e = new Path();
            Matrix matrix = new Matrix();
            matrix.preScale(cGSize.f2102a, cGSize.f2103b);
            dVar.f2090e.transform(matrix, this.f2090e);
            this.f2088c = null;
            this.f2089d = null;
            this.f2086a = null;
            this.f2087b = null;
            RectF rectF = new RectF();
            this.f2090e.computeBounds(rectF, true);
            this.f2091f = new CGRect(rectF.left, rectF.top, rectF.width(), rectF.height());
        }
        this.f2092g = dVar.f2092g;
        this.f2093h = dVar.f2093h;
        this.f2094i = dVar.f2094i;
    }

    /* renamed from: a */
    public boolean mo17118a() {
        return this.f2092g == 2;
    }

    /* renamed from: d */
    private CGRect m2822d() {
        float f = this.f2088c[0].x;
        float f2 = this.f2088c[0].y;
        float f3 = f2;
        float f4 = f;
        for (CGPoint cGPoint : this.f2088c) {
            if (cGPoint.x < f4) {
                f4 = cGPoint.x;
            }
            if (cGPoint.x > f) {
                f = cGPoint.x;
            }
            if (cGPoint.y < f3) {
                f3 = cGPoint.y;
            }
            if (cGPoint.y > f2) {
                f2 = cGPoint.y;
            }
        }
        return new CGRect(f4, f3, f - f4, f2 - f3);
    }

    /* renamed from: a */
    private void m2818a(Path path, CGPoint[] cGPointArr, int i, float f, float f2) {
        int length = cGPointArr.length;
        CGPoint cGPoint = cGPointArr[i];
        CGPoint cGPoint2 = cGPointArr[((i + length) - 1) % length];
        CGPoint cGPoint3 = cGPointArr[(i + 1) % length];
        CGPoint b = CGPoint.m2834b(cGPoint2, cGPoint);
        CGPoint f3 = CGPoint.m2837f(b);
        CGPoint b2 = CGPoint.m2834b(cGPoint3, cGPoint);
        CGPoint b3 = CGPoint.m2834b(cGPoint3, cGPoint2);
        float length2 = b.length();
        float length3 = b2.length();
        float length4 = b3.length();
        boolean z = f3.mo17129c(b2) > 0.0f;
        float f4 = f2 + (z ? 0.0f : 2.0f * f);
        float f5 = length2 + length3 + length4;
        float abs = (f4 * f5) / Math.abs(b.mo17129c(b2));
        CGPoint b4 = new CGPoint((cGPoint.x * length4) + (cGPoint2.x * length3) + (cGPoint3.x * length2), (cGPoint2.y * length3) + (length4 * cGPoint.y) + (cGPoint3.y * length2)).mo17126b(f5);
        CGPoint.m2834b(cGPoint, b4).mo17122a(abs).mo17121a().mo17123a(cGPoint);
        float acos = (float) (3.141592653589793d - (Math.acos((double) (b.mo17131d(b2) / (length2 * length3))) * ((double) (z ? 1 : -1))));
        float c = (float) ((((double) (z ? 1 : -1)) * 3.141592653589793d) - ((double) b.mo17128c()));
        path.addArc(CGRect.m2851a(b4, new CGSize(2.0f * f4)).mo17142e(), c * 180.0f, (((float) (z ? 1 : -1)) * acos) + c);
    }

    /* renamed from: a */
    public Path mo17117a(float f, int i) {
        int i2 = 0;
        if (this.f2090e == null || this.f2088c != null) {
            CGPoint c = this.f2091f.mo17139c();
            CGPoint[] cGPointArr = new CGPoint[this.f2088c.length];
            for (int i3 = 0; i3 < this.f2088c.length; i3++) {
                cGPointArr[i3] = CGPoint.m2834b(this.f2088c[i3], c);
            }
            Path path = new Path();
            if (mo17119b()) {
                CGRect cGRect = new CGRect(0.0f, 0.0f, this.f2091f.f2100c, this.f2091f.f2101d);
                float[] fArr = new float[8];
                while (i2 < this.f2088c.length) {
                    if (((1 << i2) & i) == 0) {
                        fArr[i2 * 2] = f;
                        fArr[(i2 * 2) + 1] = f;
                    }
                    i2++;
                }
                path.addRoundRect(cGRect.mo17142e(), fArr, Path.Direction.CW);
                return path;
            }
            float min = Math.min(f, mo17120c());
            if (min < 2.0f) {
                int length = cGPointArr.length - 1;
                path.moveTo(cGPointArr[length].x, cGPointArr[length].y);
                while (i2 < cGPointArr.length) {
                    path.lineTo(cGPointArr[i2].x, cGPointArr[i2].y);
                    i2++;
                }
            } else {
                CGPoint c2 = CGPoint.m2835c(cGPointArr[0], cGPointArr[1]);
                if (cGPointArr.length == 3) {
                }
                path.moveTo(c2.x, c2.y);
                while (i2 < cGPointArr.length) {
                    if (((1 << i2) & i) != 0) {
                        path.lineTo(cGPointArr[i2].x, cGPointArr[i2].y);
                    } else {
                        m2818a(path, cGPointArr, i2, (float) i, min);
                    }
                    i2++;
                }
            }
            path.close();
            return path;
        }
        Path path2 = new Path();
        Matrix matrix = new Matrix();
        matrix.postTranslate(-this.f2091f.f2098a, -this.f2091f.f2099b);
        this.f2090e.transform(matrix, path2);
        return path2;
    }

    /* renamed from: b */
    public boolean mo17119b() {
        if (this.f2088c.length == 4) {
            if (this.f2088c[0].x == this.f2088c[1].x && this.f2088c[1].y == this.f2088c[2].y && this.f2088c[2].x == this.f2088c[3].x && this.f2088c[3].y == this.f2088c[0].y) {
                return true;
            }
            if (this.f2088c[0].y == this.f2088c[1].y && this.f2088c[1].x == this.f2088c[2].x && this.f2088c[2].y == this.f2088c[3].y && this.f2088c[3].x == this.f2088c[0].x) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public float mo17116a(CGPoint[] cGPointArr) {
        CGPoint b = CGPoint.m2834b(cGPointArr[1], cGPointArr[0]);
        CGPoint b2 = CGPoint.m2834b(cGPointArr[2], cGPointArr[0]);
        return Math.abs(b.mo17129c(b2) / ((b.length() + b2.length()) + CGPoint.m2834b(cGPointArr[2], cGPointArr[1]).length()));
    }

    /* renamed from: a */
    private static Pair<Integer, CGPoint> m2817a(CGPoint cGPoint, CGPoint cGPoint2, CGPoint cGPoint3, CGPoint cGPoint4) {
        float f = cGPoint2.y - cGPoint.y;
        float f2 = cGPoint.x - cGPoint2.x;
        float f3 = (cGPoint2.x * cGPoint.y) - (cGPoint.x * cGPoint2.y);
        float f4 = cGPoint4.y - cGPoint3.y;
        float f5 = cGPoint3.x - cGPoint4.x;
        float f6 = (cGPoint4.x * cGPoint3.y) - (cGPoint3.x * cGPoint4.y);
        if (!CGFloat.m2895a(f * f5, f2 * f4)) {
            return new Pair<>(1, new CGPoint(((f5 * f3) - (f2 * f6)) / ((f4 * f2) - (f * f5)), ((f6 * f) - (f3 * f4)) / ((f2 * f4) - (f * f5))));
        }
        if (CGFloat.m2895a((f + f2) * f6, (f4 + f5) * f3)) {
            return new Pair<>(3, new CGPoint());
        }
        return new Pair<>(2, new CGPoint((float) (((double) Math.abs(f6 - f3)) / Math.sqrt((double) ((f * f) + (f2 * f2))))));
    }

    /* renamed from: c */
    public float mo17120c() {
        float abs;
        float f = Float.MAX_VALUE;
        if (this.f2090e != null) {
            return 0.0f;
        }
        if (mo17119b()) {
            return this.f2091f.mo17140d() / 2.0f;
        }
        if (this.f2088c.length == 3) {
            return mo17116a(this.f2088c);
        }
        int length = this.f2088c.length;
        CGPoint[] cGPointArr = this.f2088c;
        int i = 0;
        while (i < length) {
            int i2 = ((i + length) - 1) % length;
            int i3 = (i + 1) % length;
            int i4 = (i + 2) % length;
            CGPoint b = CGPoint.m2834b(cGPointArr[i2], cGPointArr[i]);
            CGPoint b2 = CGPoint.m2834b(cGPointArr[i3], cGPointArr[i]);
            if (CGFloat.m2896a(Math.abs(b.mo17129c(CGPoint.m2834b(cGPointArr[i3], cGPointArr[i4]))), 0.0f, 0.01f)) {
                abs = Math.abs(b.mo17129c(b2) * 0.5f) / b.length();
            } else {
                Pair<Integer, CGPoint> a = m2817a(cGPointArr[i2], cGPointArr[i], cGPointArr[i3], cGPointArr[i4]);
                CGPoint b3 = CGPoint.m2834b(cGPointArr[i], (CGPoint) a.f2265b);
                CGPoint b4 = CGPoint.m2834b(cGPointArr[i3], (CGPoint) a.f2265b);
                abs = Math.abs(b3.mo17129c(b4) / ((b4.length() + b3.length()) - b2.length()));
            }
            if (abs >= f) {
                abs = f;
            }
            i++;
            f = abs;
        }
        return f;
    }
}
