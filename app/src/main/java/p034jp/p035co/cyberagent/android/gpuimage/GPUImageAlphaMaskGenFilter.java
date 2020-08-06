package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.b */
public class GPUImageAlphaMaskGenFilter extends GPUImageFilter {

    /* renamed from: a */
    protected int f2755a;

    /* renamed from: i */
    private int f2756i;

    public GPUImageAlphaMaskGenFilter() {
        this(Integer.MIN_VALUE);
    }

    public GPUImageAlphaMaskGenFilter(int i) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\nuniform lowp vec4 maskColor ;\n \nvoid main()\n{\n     vec4 color = texture2D(inputImageTexture, textureCoordinate);\n     gl_FragColor = maskColor * color.a;\n}");
        this.f2756i = i;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2755a = GLES20.glGetUniformLocation(this.f2762b, "maskColor");
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17831a(this.f2756i);
    }

    /* renamed from: a */
    public void mo17831a(int i) {
        this.f2756i = i;
        float[] fArr = new float[4];
        fArr[3] = ((float) ((i >> 24) & 255)) / 255.0f;
        fArr[0] = ((float) ((i >> 16) & 255)) / 255.0f;
        fArr[1] = ((float) ((i >> 8) & 255)) / 255.0f;
        fArr[2] = ((float) ((i >> 0) & 255)) / 255.0f;
        mo17841b(this.f2755a, fArr);
    }
}
