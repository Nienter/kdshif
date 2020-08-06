package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* renamed from: jp.co.cyberagent.android.gpuimage.ac */
public class GPUImageTwoPassTextureSamplingFilter extends GPUImageTwoPassFilter {
    public GPUImageTwoPassTextureSamplingFilter(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        mo17811o();
    }

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public void mo17811o() {
        float q = mo17813q();
        GPUImageFilter fVar = (GPUImageFilter) this.f2793a.get(0);
        int glGetUniformLocation = GLES20.glGetUniformLocation(fVar.mo17850l(), "texelWidthOffset");
        int glGetUniformLocation2 = GLES20.glGetUniformLocation(fVar.mo17850l(), "texelHeightOffset");
        fVar.mo17835a(glGetUniformLocation, q / ((float) this.f2766f));
        fVar.mo17835a(glGetUniformLocation2, 0.0f);
        float p = mo17812p();
        GPUImageFilter fVar2 = (GPUImageFilter) this.f2793a.get(1);
        int glGetUniformLocation3 = GLES20.glGetUniformLocation(fVar2.mo17850l(), "texelWidthOffset");
        int glGetUniformLocation4 = GLES20.glGetUniformLocation(fVar2.mo17850l(), "texelHeightOffset");
        fVar2.mo17835a(glGetUniformLocation3, 0.0f);
        fVar2.mo17835a(glGetUniformLocation4, p / ((float) this.f2767g));
    }

    /* renamed from: a */
    public void mo17810a(int i, int i2) {
        super.mo17810a(i, i2);
        mo17811o();
    }

    /* renamed from: p */
    public float mo17812p() {
        return 1.0f;
    }

    /* renamed from: q */
    public float mo17813q() {
        return 1.0f;
    }
}
