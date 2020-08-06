package p034jp.p035co.cyberagent.android.gpuimage;

import android.graphics.Point;
import android.graphics.PointF;
import android.opengl.GLES20;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* renamed from: jp.co.cyberagent.android.gpuimage.y */
public class GPUImageToneCurveFilter extends GPUImageFilter {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public int[] f2875a;

    /* renamed from: i */
    private int f2876i;

    /* renamed from: j */
    private PointF[] f2877j;

    /* renamed from: k */
    private PointF[] f2878k;

    /* renamed from: l */
    private PointF[] f2879l;

    /* renamed from: m */
    private PointF[] f2880m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public ArrayList<Float> f2881n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public ArrayList<Float> f2882o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public ArrayList<Float> f2883p;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public ArrayList<Float> f2884q;

    public GPUImageToneCurveFilter() {
        this(new PointF[]{new PointF(0.0f, 0.0f), new PointF(0.5f, 0.5f), new PointF(1.0f, 1.0f)});
    }

    public GPUImageToneCurveFilter(PointF[] pointFArr) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n uniform sampler2D inputImageTexture;\n uniform sampler2D toneCurveTexture;\n\n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     lowp float redCurveValue = texture2D(toneCurveTexture, vec2(textureColor.r, 0.0)).r;\n     lowp float greenCurveValue = texture2D(toneCurveTexture, vec2(textureColor.g, 0.0)).g;\n     lowp float blueCurveValue = texture2D(toneCurveTexture, vec2(textureColor.b, 0.0)).b;\n\n     gl_FragColor = vec4(redCurveValue, greenCurveValue, blueCurveValue, textureColor.a);\n }");
        this.f2875a = new int[]{-1};
        this.f2877j = pointFArr;
        this.f2878k = pointFArr;
        this.f2879l = pointFArr;
        this.f2880m = pointFArr;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2876i = GLES20.glGetUniformLocation(mo17850l(), "toneCurveTexture");
        GLES20.glActiveTexture(33987);
        GLES20.glGenTextures(1, this.f2875a, 0);
        GLES20.glBindTexture(3553, this.f2875a[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17905a(this.f2877j);
        mo17906b(this.f2878k);
        mo17907c(this.f2879l);
        mo17908d(this.f2880m);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17801a(boolean z) {
        if (this.f2875a[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.f2875a[0]);
            GLES20.glUniform1i(this.f2876i, 3);
        }
    }

    /* JADX WARNING: type inference failed for: r0v15, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    public void mo17904a(InputStream inputStream) {
        try {
            m3876b(inputStream);
            short b = m3876b(inputStream);
            ArrayList arrayList = new ArrayList(b);
            for (short s = 0; s < b; s++) {
                int b2 = m3876b(inputStream);
                PointF[] pointFArr = new PointF[b2];
                for (int i = 0; i < b2; i++) {
                    pointFArr[i] = new PointF(((float) m3876b(inputStream)) * 0.003921569f, ((float) m3876b(inputStream)) * 0.003921569f);
                }
                arrayList.add(pointFArr);
            }
            inputStream.close();
            this.f2877j = (PointF[]) arrayList.get(0);
            this.f2878k = (PointF[]) arrayList.get(1);
            this.f2879l = (PointF[]) arrayList.get(2);
            this.f2880m = (PointF[]) arrayList.get(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private short m3876b(InputStream inputStream) {
        return (short) ((inputStream.read() << 8) | inputStream.read());
    }

    /* renamed from: a */
    public void mo17905a(PointF[] pointFArr) {
        this.f2877j = pointFArr;
        this.f2881n = m3880e(this.f2877j);
        m3881m();
    }

    /* renamed from: b */
    public void mo17906b(PointF[] pointFArr) {
        this.f2878k = pointFArr;
        this.f2882o = m3880e(this.f2878k);
        m3881m();
    }

    /* renamed from: c */
    public void mo17907c(PointF[] pointFArr) {
        this.f2879l = pointFArr;
        this.f2883p = m3880e(this.f2879l);
        m3881m();
    }

    /* renamed from: d */
    public void mo17908d(PointF[] pointFArr) {
        this.f2880m = pointFArr;
        this.f2884q = m3880e(this.f2880m);
        m3881m();
    }

    /* renamed from: m */
    private void m3881m() {
        mo17839a((Runnable) new Runnable() {
            public void run() {
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, GPUImageToneCurveFilter.this.f2875a[0]);
                if (GPUImageToneCurveFilter.this.f2882o.size() >= 256 && GPUImageToneCurveFilter.this.f2883p.size() >= 256 && GPUImageToneCurveFilter.this.f2884q.size() >= 256 && GPUImageToneCurveFilter.this.f2881n.size() >= 256) {
                    byte[] bArr = new byte[1024];
                    for (int i = 0; i < 256; i++) {
                        bArr[(i * 4) + 2] = (byte) (((int) Math.min(Math.max(((Float) GPUImageToneCurveFilter.this.f2881n.get(i)).floatValue() + ((float) i) + ((Float) GPUImageToneCurveFilter.this.f2884q.get(i)).floatValue(), 0.0f), 255.0f)) & 255);
                        bArr[(i * 4) + 1] = (byte) (((int) Math.min(Math.max(((Float) GPUImageToneCurveFilter.this.f2881n.get(i)).floatValue() + ((float) i) + ((Float) GPUImageToneCurveFilter.this.f2883p.get(i)).floatValue(), 0.0f), 255.0f)) & 255);
                        bArr[i * 4] = (byte) (((int) Math.min(Math.max(((Float) GPUImageToneCurveFilter.this.f2881n.get(i)).floatValue() + ((float) i) + ((Float) GPUImageToneCurveFilter.this.f2882o.get(i)).floatValue(), 0.0f), 255.0f)) & 255);
                        bArr[(i * 4) + 3] = -1;
                    }
                    GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, ByteBuffer.wrap(bArr));
                }
            }
        });
    }

    /* renamed from: e */
    private ArrayList<Float> m3880e(PointF[] pointFArr) {
        float f;
        if (pointFArr == null || pointFArr.length <= 0) {
            return null;
        }
        PointF[] pointFArr2 = (PointF[]) pointFArr.clone();
        Arrays.sort(pointFArr2, new Comparator<PointF>() {
            /* renamed from: a */
            public int compare(PointF pointF, PointF pointF2) {
                if (pointF.x < pointF2.x) {
                    return -1;
                }
                if (pointF.x > pointF2.x) {
                    return 1;
                }
                return 0;
            }
        });
        Point[] pointArr = new Point[pointFArr2.length];
        for (int i = 0; i < pointFArr.length; i++) {
            PointF pointF = pointFArr2[i];
            pointArr[i] = new Point((int) (pointF.x * 255.0f), (int) (pointF.y * 255.0f));
        }
        ArrayList<Point> a = m3872a(pointArr);
        Point point = a.get(0);
        if (point.x > 0) {
            for (int i2 = point.x; i2 >= 0; i2--) {
                a.add(0, new Point(i2, 0));
            }
        }
        Point point2 = a.get(a.size() - 1);
        if (point2.x < 255) {
            for (int i3 = point2.x + 1; i3 <= 255; i3++) {
                a.add(new Point(i3, 255));
            }
        }
        ArrayList<Float> arrayList = new ArrayList<>(a.size());
        Iterator<Point> it = a.iterator();
        while (it.hasNext()) {
            Point next = it.next();
            Point point3 = new Point(next.x, next.x);
            float sqrt = (float) Math.sqrt(Math.pow((double) (point3.x - next.x), 2.0d) + Math.pow((double) (point3.y - next.y), 2.0d));
            if (point3.y > next.y) {
                f = -sqrt;
            } else {
                f = sqrt;
            }
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    /* renamed from: a */
    private ArrayList<Point> m3872a(Point[] pointArr) {
        ArrayList<Double> b = m3875b(pointArr);
        int size = b.size();
        if (size < 1) {
            return null;
        }
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = b.get(i).doubleValue();
        }
        ArrayList<Point> arrayList = new ArrayList<>(size + 1);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= size - 1) {
                break;
            }
            Point point = pointArr[i3];
            Point point2 = pointArr[i3 + 1];
            for (int i4 = point.x; i4 < point2.x; i4++) {
                double d = ((double) (i4 - point.x)) / ((double) (point2.x - point.x));
                double d2 = 1.0d - d;
                double d3 = (double) (point2.x - point.x);
                double d4 = ((((((d * d) * d) - d) * dArr[i3 + 1]) + ((((d2 * d2) * d2) - d2) * dArr[i3])) * ((d3 * d3) / 6.0d)) + (((double) point.y) * d2) + (((double) point2.y) * d);
                if (d4 > 255.0d) {
                    d4 = 255.0d;
                } else if (d4 < 0.0d) {
                    d4 = 0.0d;
                }
                arrayList.add(new Point(i4, (int) Math.round(d4)));
            }
            i2 = i3 + 1;
        }
        if (arrayList.size() == 255) {
            arrayList.add(pointArr[pointArr.length - 1]);
        }
        return arrayList;
    }

    /* renamed from: b */
    private ArrayList<Double> m3875b(Point[] pointArr) {
        int length = pointArr.length;
        if (length <= 1) {
            return null;
        }
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, 3});
        double[] dArr2 = new double[length];
        dArr[0][1] = 1.0d;
        dArr[0][0] = 0.0d;
        dArr[0][2] = 0.0d;
        for (int i = 1; i < length - 1; i++) {
            Point point = pointArr[i - 1];
            Point point2 = pointArr[i];
            Point point3 = pointArr[i + 1];
            dArr[i][0] = ((double) (point2.x - point.x)) / 6.0d;
            dArr[i][1] = ((double) (point3.x - point.x)) / 3.0d;
            dArr[i][2] = ((double) (point3.x - point2.x)) / 6.0d;
            dArr2[i] = (((double) (point3.y - point2.y)) / ((double) (point3.x - point2.x))) - (((double) (point2.y - point.y)) / ((double) (point2.x - point.x)));
        }
        dArr2[0] = 0.0d;
        dArr2[length - 1] = 0.0d;
        dArr[length - 1][1] = 1.0d;
        dArr[length - 1][0] = 0.0d;
        dArr[length - 1][2] = 0.0d;
        for (int i2 = 1; i2 < length; i2++) {
            double d = dArr[i2][0] / dArr[i2 - 1][1];
            double[] dArr3 = dArr[i2];
            dArr3[1] = dArr3[1] - (dArr[i2 - 1][2] * d);
            dArr[i2][0] = 0.0d;
            dArr2[i2] = dArr2[i2] - (d * dArr2[i2 - 1]);
        }
        for (int i3 = length - 2; i3 >= 0; i3--) {
            double d2 = dArr[i3][2] / dArr[i3 + 1][1];
            double[] dArr4 = dArr[i3];
            dArr4[1] = dArr4[1] - (dArr[i3 + 1][0] * d2);
            dArr[i3][2] = 0.0d;
            dArr2[i3] = dArr2[i3] - (d2 * dArr2[i3 + 1]);
        }
        ArrayList<Double> arrayList = new ArrayList<>(length);
        for (int i4 = 0; i4 < length; i4++) {
            arrayList.add(Double.valueOf(dArr2[i4] / dArr[i4][1]));
        }
        return arrayList;
    }
}
