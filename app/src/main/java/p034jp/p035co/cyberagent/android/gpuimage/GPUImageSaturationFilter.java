package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.v */
public class GPUImageSaturationFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2861a;

    /* renamed from: i */
    private float f2862i;

    public GPUImageSaturationFilter() {
        this(1.0f);
    }

    public GPUImageSaturationFilter(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float saturation;\n \n // Values from \"Graphics Shaders: Theory and Practice\" by Bailey and Cunningham\n const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n \n void main()\n {\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp float luminance = dot(textureColor.rgb, luminanceWeighting);\n    lowp vec3 greyScaleColor = vec3(luminance);\n    \n    gl_FragColor = vec4(mix(greyScaleColor, textureColor.rgb, saturation), textureColor.w);\n     \n }");
        this.f2862i = f;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2861a = GLES20.glGetUniformLocation(mo17850l(), "saturation");
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17897a(this.f2862i);
    }

    /* renamed from: a */
    public void mo17897a(float f) {
        this.f2862i = f;
        mo17835a(this.f2861a, this.f2862i);
    }
}
