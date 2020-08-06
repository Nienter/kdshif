package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.e */
public class GPUImageExposureFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2759a;

    /* renamed from: i */
    private float f2760i;

    public GPUImageExposureFilter() {
        this(1.0f);
    }

    public GPUImageExposureFilter(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform highp float exposure;\n \n void main()\n {\n     highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(textureColor.rgb * pow(2.0, exposure), textureColor.w);\n } ");
        this.f2760i = f;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2759a = GLES20.glGetUniformLocation(mo17850l(), "exposure");
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17834a(this.f2760i);
    }

    /* renamed from: a */
    public void mo17834a(float f) {
        this.f2760i = f;
        mo17835a(this.f2759a, this.f2760i);
    }
}
