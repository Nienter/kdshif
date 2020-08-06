package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.c */
public class GPUImageConstColorFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2757a;

    /* renamed from: i */
    private int f2758i;

    public GPUImageConstColorFilter() {
        this(-16776961);
    }

    public GPUImageConstColorFilter(int i) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\nuniform lowp vec4 color;\n \nvoid main()\n{\n     gl_FragColor = color;\n}");
        this.f2757a = i;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2758i = GLES20.glGetUniformLocation(mo17850l(), "color");
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17833a(this.f2757a);
    }

    /* renamed from: a */
    public void mo17833a(int i) {
        this.f2757a = i;
        float[] fArr = new float[4];
        fArr[3] = ((float) ((i >> 24) & 255)) / 255.0f;
        fArr[0] = ((float) ((i >> 16) & 255)) / 255.0f;
        fArr[1] = ((float) ((i >> 8) & 255)) / 255.0f;
        fArr[2] = ((float) ((i >> 0) & 255)) / 255.0f;
        mo17841b(this.f2758i, fArr);
    }
}
