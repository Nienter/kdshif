package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.q */
public class GPUImageLightnessFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2814a;

    /* renamed from: i */
    private float f2815i;

    public GPUImageLightnessFilter() {
        this(0.0f);
    }

    public GPUImageLightnessFilter(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float lightness;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     lowp vec3 offset; \n     if (lightness < 0.0) { \n         offset = (vec3(1.0) - textureColor.rgb) * lightness; \n     } else { \n         offset = textureColor.rgb * lightness; \n     }   \n     gl_FragColor = vec4((textureColor.rgb + offset), textureColor.w);\n }");
        this.f2815i = f;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2814a = GLES20.glGetUniformLocation(mo17850l(), "lightness");
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17871a(this.f2815i);
    }

    /* renamed from: a */
    public void mo17871a(float f) {
        this.f2815i = f;
        mo17835a(this.f2814a, this.f2815i);
    }
}
