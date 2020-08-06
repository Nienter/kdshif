package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.r */
public class GPUImageMixBlendFilter extends GPUImageTwoInputFilter {

    /* renamed from: a */
    private int f2816a;

    /* renamed from: i */
    private float f2817i;

    public GPUImageMixBlendFilter(float f) {
        this("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n\n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n uniform lowp float mixturePercent;\n\n void main()\n {\n   lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n   lowp vec4 textureColor2 = texture2D(inputImageTexture2, textureCoordinate2);\n\n   gl_FragColor = mix(textureColor, textureColor2, mixturePercent);\n }", f);
    }

    public GPUImageMixBlendFilter(String str) {
        this(str, 0.5f);
    }

    public GPUImageMixBlendFilter(String str, float f) {
        super(str);
        this.f2817i = f;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2816a = GLES20.glGetUniformLocation(mo17850l(), "mixturePercent");
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17872a(this.f2817i);
    }

    /* renamed from: a */
    public void mo17872a(float f) {
        this.f2817i = f;
        mo17835a(this.f2816a, this.f2817i);
    }
}
