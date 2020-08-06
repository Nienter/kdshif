package p034jp.p035co.cyberagent.android.gpuimage;

import android.opengl.GLES20;
import android.opengl.Matrix;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* renamed from: jp.co.cyberagent.android.gpuimage.z */
public class GPUImageTransformFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2887a;

    /* renamed from: i */
    private int f2888i;

    /* renamed from: j */
    private float[] f2889j = new float[16];

    /* renamed from: k */
    private float[] f2890k;

    /* renamed from: l */
    private boolean f2891l;

    public GPUImageTransformFilter() {
        super(" attribute vec4 position;\n attribute vec4 inputTextureCoordinate;\n \n uniform mat4 transformMatrix;\n uniform mat4 orthographicMatrix;\n \n varying vec2 textureCoordinate;\n \n void main()\n {\n     gl_Position = transformMatrix * vec4(position.xyz, 1.0) * orthographicMatrix;\n     textureCoordinate = inputTextureCoordinate.xy;\n }", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
        Matrix.orthoM(this.f2889j, 0, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
        this.f2890k = new float[16];
        Matrix.setIdentityM(this.f2890k, 0);
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2887a = GLES20.glGetUniformLocation(mo17850l(), "transformMatrix");
        this.f2888i = GLES20.glGetUniformLocation(mo17850l(), "orthographicMatrix");
        mo17843c(this.f2887a, this.f2890k);
        mo17843c(this.f2888i, this.f2889j);
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
    }

    /* renamed from: a */
    public void mo17810a(int i, int i2) {
        super.mo17810a(i, i2);
        if (!this.f2891l) {
            Matrix.orthoM(this.f2889j, 0, -1.0f, 1.0f, (((float) i2) * -1.0f) / ((float) i), (((float) i2) * 1.0f) / ((float) i), -1.0f, 1.0f);
            mo17843c(this.f2888i, this.f2889j);
        }
    }

    /* renamed from: a */
    public void mo17837a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, boolean z) {
        FloatBuffer floatBuffer4;
        if (!this.f2891l) {
            float[] fArr = new float[8];
            floatBuffer.position(0);
            floatBuffer.get(fArr);
            float k = ((float) mo17849k()) / ((float) mo17848j());
            fArr[1] = fArr[1] * k;
            fArr[3] = fArr[3] * k;
            fArr[5] = fArr[5] * k;
            fArr[7] = k * fArr[7];
            floatBuffer4 = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            floatBuffer4.put(fArr).position(0);
        } else {
            floatBuffer4 = floatBuffer;
        }
        super.mo17837a(i, floatBuffer4, floatBuffer2, floatBuffer3, z);
    }

    /* renamed from: a */
    public void mo17912a(float[] fArr) {
        this.f2890k = fArr;
        mo17843c(this.f2887a, fArr);
    }
}
