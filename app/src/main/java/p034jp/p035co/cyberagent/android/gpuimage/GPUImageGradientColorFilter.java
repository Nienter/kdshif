package p034jp.p035co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.k */
public class GPUImageGradientColorFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2806a;

    /* renamed from: i */
    private int f2807i;

    /* renamed from: j */
    private PointF f2808j;

    /* renamed from: k */
    private PointF f2809k;

    /* renamed from: l */
    private int f2810l;

    /* renamed from: m */
    private int f2811m;

    /* renamed from: n */
    private int f2812n;

    /* renamed from: o */
    private int f2813o;

    public GPUImageGradientColorFilter() {
        this(-1, 255);
    }

    public GPUImageGradientColorFilter(int i, int i2) {
        this(i, i2, new PointF(0.0f, 0.0f), new PointF(1.0f, 0.0f));
    }

    public GPUImageGradientColorFilter(int i, int i2, PointF pointF, PointF pointF2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\nuniform lowp vec4 startColor;\nuniform lowp vec4 endColor;\nuniform highp vec2 startPosition;\nuniform highp vec2 endPosition;\n \nvoid main()\n{\n     float len0 = length(textureCoordinate - vec2(0.0, 1.0));\n     float len1 = length(textureCoordinate - vec2(1.0, 0.0));\n     gl_FragColor = mix(startColor, endColor, len0 / (len0 + len1));\n}");
        this.f2806a = i;
        this.f2807i = i2;
        this.f2808j = pointF;
        this.f2809k = pointF2;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2810l = GLES20.glGetUniformLocation(mo17850l(), "startColor");
        this.f2811m = GLES20.glGetUniformLocation(mo17850l(), "endColor");
        this.f2812n = GLES20.glGetUniformLocation(mo17850l(), "startPosition");
        this.f2813o = GLES20.glGetUniformLocation(mo17850l(), "endPosition");
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17866a(this.f2806a);
        mo17868b(this.f2807i);
        mo17867a(this.f2808j);
        mo17870b(this.f2809k);
    }

    /* renamed from: b */
    public void mo17869b(int i, int i2) {
        float[] fArr = new float[4];
        fArr[3] = ((float) ((i >> 24) & 255)) / 255.0f;
        fArr[0] = ((float) ((i >> 16) & 255)) / 255.0f;
        fArr[1] = ((float) ((i >> 8) & 255)) / 255.0f;
        fArr[2] = ((float) ((i >> 0) & 255)) / 255.0f;
        mo17841b(i2, fArr);
    }

    /* renamed from: a */
    public void mo17866a(int i) {
        this.f2806a = i;
        mo17869b(i, this.f2810l);
    }

    /* renamed from: b */
    public void mo17868b(int i) {
        this.f2807i = i;
        mo17869b(i, this.f2811m);
    }

    /* renamed from: a */
    public void mo17867a(PointF pointF) {
        this.f2808j = pointF;
        mo17838a(this.f2812n, new float[]{pointF.x, pointF.y});
    }

    /* renamed from: b */
    public void mo17870b(PointF pointF) {
        this.f2809k = pointF;
        mo17838a(this.f2811m, new float[]{pointF.x, pointF.y});
    }
}
