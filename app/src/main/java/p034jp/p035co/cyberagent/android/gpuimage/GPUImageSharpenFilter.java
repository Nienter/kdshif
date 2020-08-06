package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.w */
public class GPUImageSharpenFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2863a;

    /* renamed from: i */
    private float f2864i;

    /* renamed from: j */
    private int f2865j;

    /* renamed from: k */
    private int f2866k;

    public GPUImageSharpenFilter() {
        this(0.0f);
    }

    public GPUImageSharpenFilter(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform float imageWidthFactor; \nuniform float imageHeightFactor; \nuniform float sharpness;\n\nvarying vec2 textureCoordinate;\nvarying vec2 leftTextureCoordinate;\nvarying vec2 rightTextureCoordinate; \nvarying vec2 topTextureCoordinate;\nvarying vec2 bottomTextureCoordinate;\n\nvarying float centerMultiplier;\nvarying float edgeMultiplier;\n\nvoid main()\n{\n    gl_Position = position;\n    \n    mediump vec2 widthStep = vec2(imageWidthFactor, 0.0);\n    mediump vec2 heightStep = vec2(0.0, imageHeightFactor);\n    \n    textureCoordinate = inputTextureCoordinate.xy;\n    leftTextureCoordinate = inputTextureCoordinate.xy - widthStep;\n    rightTextureCoordinate = inputTextureCoordinate.xy + widthStep;\n    topTextureCoordinate = inputTextureCoordinate.xy + heightStep;     \n    bottomTextureCoordinate = inputTextureCoordinate.xy - heightStep;\n    \n    centerMultiplier = 1.0 + 4.0 * sharpness;\n    edgeMultiplier = sharpness;\n}", "precision highp float;\n\nvarying highp vec2 textureCoordinate;\nvarying highp vec2 leftTextureCoordinate;\nvarying highp vec2 rightTextureCoordinate; \nvarying highp vec2 topTextureCoordinate;\nvarying highp vec2 bottomTextureCoordinate;\n\nvarying highp float centerMultiplier;\nvarying highp float edgeMultiplier;\n\nuniform sampler2D inputImageTexture;\n\nvoid main()\n{\n    mediump vec3 textureColor = texture2D(inputImageTexture, textureCoordinate).rgb;\n    mediump vec3 leftTextureColor = texture2D(inputImageTexture, leftTextureCoordinate).rgb;\n    mediump vec3 rightTextureColor = texture2D(inputImageTexture, rightTextureCoordinate).rgb;\n    mediump vec3 topTextureColor = texture2D(inputImageTexture, topTextureCoordinate).rgb;\n    mediump vec3 bottomTextureColor = texture2D(inputImageTexture, bottomTextureCoordinate).rgb;\n\n    gl_FragColor = vec4((textureColor * centerMultiplier - (leftTextureColor * edgeMultiplier + rightTextureColor * edgeMultiplier + topTextureColor * edgeMultiplier + bottomTextureColor * edgeMultiplier)), texture2D(inputImageTexture, bottomTextureCoordinate).w);\n}");
        this.f2864i = f;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2863a = GLES20.glGetUniformLocation(mo17850l(), "sharpness");
        this.f2865j = GLES20.glGetUniformLocation(mo17850l(), "imageWidthFactor");
        this.f2866k = GLES20.glGetUniformLocation(mo17850l(), "imageHeightFactor");
        mo17898a(this.f2864i);
    }

    /* renamed from: a */
    public void mo17810a(int i, int i2) {
        super.mo17810a(i, i2);
        mo17835a(this.f2865j, 1.0f / ((float) i));
        mo17835a(this.f2866k, 1.0f / ((float) i2));
    }

    /* renamed from: a */
    public void mo17898a(float f) {
        this.f2864i = f;
        mo17835a(this.f2863a, this.f2864i);
    }
}
