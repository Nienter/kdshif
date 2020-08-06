package p034jp.p035co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.t */
public class GPUImageOverlayFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2818a;

    /* renamed from: i */
    private int f2819i;

    /* renamed from: j */
    private int f2820j;

    /* renamed from: k */
    private int f2821k;

    /* renamed from: l */
    private int f2822l;

    /* renamed from: m */
    private PointF f2823m;

    /* renamed from: n */
    private int f2824n;

    /* renamed from: o */
    private int f2825o;

    /* renamed from: p */
    private float f2826p;

    /* renamed from: q */
    private float f2827q;

    public GPUImageOverlayFilter() {
        this(new PointF(0.5f, 0.5f), -5855578, -9605779, 0.3f, 0.75f);
    }

    public GPUImageOverlayFilter(PointF pointF, int i, int i2, float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " uniform sampler2D inputImageTexture;\n varying highp vec2 textureCoordinate;\n \n uniform lowp vec2 overlayCenter;\n uniform lowp vec4 overlayStartColor;\n uniform lowp vec4 overlayEndColor;\n uniform highp float overlayStart;\n uniform highp float overlayEnd;\n \n void main()\n {\n     mediump vec4 base = texture2D(inputImageTexture, textureCoordinate);\n     lowp float d = distance(textureCoordinate, vec2(overlayCenter.x, overlayCenter.y));\n     lowp float percent = smoothstep(overlayStart, overlayEnd, d);\n     mediump vec4 overlay = mix(overlayStartColor, overlayEndColor, percent); \n     \n     mediump float ra;\n     if (2.0 * base.r < base.a) {\n         ra = 2.0 * overlay.r * base.r + overlay.r * (1.0 - base.a) + base.r * (1.0 - overlay.a);\n     } else {\n         ra = overlay.a * base.a - 2.0 * (base.a - base.r) * (overlay.a - overlay.r) + overlay.r * (1.0 - base.a) + base.r * (1.0 - overlay.a);\n     }\n     \n     mediump float ga;\n     if (2.0 * base.g < base.a) {\n         ga = 2.0 * overlay.g * base.g + overlay.g * (1.0 - base.a) + base.g * (1.0 - overlay.a);\n     } else {\n         ga = overlay.a * base.a - 2.0 * (base.a - base.g) * (overlay.a - overlay.g) + overlay.g * (1.0 - base.a) + base.g * (1.0 - overlay.a);\n     }\n     \n     mediump float ba;\n     if (2.0 * base.b < base.a) {\n         ba = 2.0 * overlay.b * base.b + overlay.b * (1.0 - base.a) + base.b * (1.0 - overlay.a);\n     } else {\n         ba = overlay.a * base.a - 2.0 * (base.a - base.b) * (overlay.a - overlay.b) + overlay.b * (1.0 - base.a) + base.b * (1.0 - overlay.a);\n     }\n     \n     gl_FragColor = vec4(ra, ga, ba, 1.0);\n }");
        this.f2823m = pointF;
        this.f2824n = i;
        this.f2825o = i2;
        this.f2826p = f;
        this.f2827q = f2;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2818a = GLES20.glGetUniformLocation(mo17850l(), "overlayCenter");
        this.f2819i = GLES20.glGetUniformLocation(mo17850l(), "overlayStartColor");
        this.f2820j = GLES20.glGetUniformLocation(mo17850l(), "overlayEndColor");
        this.f2821k = GLES20.glGetUniformLocation(mo17850l(), "overlayStart");
        this.f2822l = GLES20.glGetUniformLocation(mo17850l(), "overlayEnd");
    }

    /* renamed from: b */
    public void mo17832b() {
        mo17875a(this.f2823m);
        mo17874a(this.f2824n);
        mo17877b(this.f2825o);
        mo17873a(this.f2826p);
        mo17876b(this.f2827q);
    }

    /* renamed from: a */
    public void mo17875a(PointF pointF) {
        this.f2823m = pointF;
        mo17836a(this.f2818a, this.f2823m);
    }

    /* renamed from: b */
    private void m3824b(int i, int i2) {
        mo17841b(i, new float[]{((float) ((i2 >> 16) & 255)) / 255.0f, ((float) ((i2 >> 8) & 255)) / 255.0f, ((float) ((i2 >> 0) & 255)) / 255.0f, ((float) ((i2 >> 24) & 255)) / 255.0f});
    }

    /* renamed from: a */
    public void mo17874a(int i) {
        this.f2824n = i;
        m3824b(this.f2819i, this.f2824n);
    }

    /* renamed from: b */
    public void mo17877b(int i) {
        this.f2825o = i;
        m3824b(this.f2820j, this.f2825o);
    }

    /* renamed from: a */
    public void mo17873a(float f) {
        this.f2826p = f;
        mo17835a(this.f2821k, this.f2826p);
    }

    /* renamed from: b */
    public void mo17876b(float f) {
        this.f2827q = f;
        mo17835a(this.f2822l, this.f2827q);
    }
}
