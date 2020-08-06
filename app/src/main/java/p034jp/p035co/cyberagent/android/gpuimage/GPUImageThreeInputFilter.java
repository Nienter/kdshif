package p034jp.p035co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import p034jp.p035co.cyberagent.android.gpuimage.p036a.TextureRotationUtil;

/* renamed from: jp.co.cyberagent.android.gpuimage.x */
public class GPUImageThreeInputFilter extends GPUImageTwoInputFilter {

    /* renamed from: a */
    public int f2867a;

    /* renamed from: i */
    public int f2868i;

    /* renamed from: j */
    public int f2869j;

    /* renamed from: k */
    public int f2870k;

    /* renamed from: l */
    private ByteBuffer f2871l;

    /* renamed from: m */
    private Bitmap f2872m;

    public GPUImageThreeInputFilter(String str) {
        this(" attribute vec4 position;\n attribute vec4 inputTextureCoordinate;\n attribute vec4 inputTextureCoordinate2;\n attribute vec4 inputTextureCoordinate3;\n \n varying vec2 textureCoordinate;\n varying vec2 textureCoordinate2;\n varying vec2 textureCoordinate3;\n \n void main()\n {\n     gl_Position = position;\n     textureCoordinate = inputTextureCoordinate.xy;\n     textureCoordinate2 = inputTextureCoordinate2.xy;\n     textureCoordinate3 = inputTextureCoordinate3.xy;\n }\n", str);
    }

    public GPUImageThreeInputFilter(String str, String str2) {
        super(str, str2);
        this.f2869j = -1;
        this.f2870k = -1;
        mo17804b(Rotation.NORMAL, false, false);
        mo17901a(Rotation.NORMAL, false, false);
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2867a = GLES20.glGetAttribLocation(mo17850l(), "inputTextureCoordinate3");
        this.f2868i = GLES20.glGetUniformLocation(mo17850l(), "inputImageTexture3");
        GLES20.glEnableVertexAttribArray(this.f2867a);
        mo17900a(this.f2872m);
    }

    /* renamed from: a */
    public void mo17900a(final Bitmap bitmap) {
        if (bitmap == null || !bitmap.isRecycled()) {
            this.f2872m = bitmap;
            if (this.f2872m != null) {
                mo17839a((Runnable) new Runnable() {
                    public void run() {
                        if (GPUImageThreeInputFilter.this.f2869j == -1 && bitmap != null && !bitmap.isRecycled()) {
                            GLES20.glActiveTexture(33988);
                            GPUImageThreeInputFilter.this.f2869j = OpenGlUtils.m3743a(bitmap, -1, false);
                        }
                    }
                });
            }
        }
    }

    /* renamed from: a */
    public void mo17899a(int i) {
        this.f2870k = i;
    }

    /* renamed from: m */
    public Bitmap mo17902m() {
        return this.f2872m;
    }

    /* renamed from: g */
    public void mo17806g() {
        super.mo17806g();
        GLES20.glDeleteTextures(1, new int[]{this.f2869j}, 0);
        this.f2869j = -1;
    }

    /* renamed from: e */
    public boolean mo17844e() {
        return this.f2872m == null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17801a(boolean z) {
        super.mo17801a(z);
        int i = this.f2869j == -1 ? this.f2870k : this.f2869j;
        GLES20.glEnableVertexAttribArray(this.f2867a);
        GLES20.glActiveTexture(33988);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(this.f2868i, 4);
        if (z) {
            mo17901a(Rotation.NORMAL, false, z);
        }
        this.f2871l.position(0);
        GLES20.glVertexAttribPointer(this.f2867a, 2, 5126, false, 0, this.f2871l);
    }

    /* renamed from: a */
    public void mo17901a(Rotation agVar, boolean z, boolean z2) {
        float[] a = TextureRotationUtil.m3713a(agVar, z, z2);
        ByteBuffer order = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = order.asFloatBuffer();
        asFloatBuffer.put(a);
        asFloatBuffer.flip();
        this.f2871l = order;
    }
}
