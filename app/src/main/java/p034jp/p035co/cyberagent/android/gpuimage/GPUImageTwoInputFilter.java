package p034jp.p035co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import p034jp.p035co.cyberagent.android.gpuimage.p036a.TextureRotationUtil;

/* renamed from: jp.co.cyberagent.android.gpuimage.aa */
public class GPUImageTwoInputFilter extends GPUImageFilter {

    /* renamed from: a */
    private int f2729a;

    /* renamed from: i */
    private int f2730i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f2731j;

    /* renamed from: k */
    private int f2732k;

    /* renamed from: l */
    private ByteBuffer f2733l;

    /* renamed from: m */
    private Bitmap f2734m;

    public GPUImageTwoInputFilter(String str) {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate2;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate2;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate2 = inputTextureCoordinate2.xy;\n}", str);
    }

    public GPUImageTwoInputFilter(String str, String str2) {
        super(str, str2);
        this.f2731j = -1;
        this.f2732k = -1;
        mo17804b(Rotation.NORMAL, false, false);
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2729a = GLES20.glGetAttribLocation(mo17850l(), "inputTextureCoordinate2");
        this.f2730i = GLES20.glGetUniformLocation(mo17850l(), "inputImageTexture2");
        GLES20.glEnableVertexAttribArray(this.f2729a);
        if (this.f2734m != null && !this.f2734m.isRecycled()) {
            mo17803b(this.f2734m);
        }
    }

    /* renamed from: b */
    public void mo17803b(final Bitmap bitmap) {
        if (bitmap == null || !bitmap.isRecycled()) {
            this.f2734m = bitmap;
            if (this.f2734m != null) {
                mo17839a((Runnable) new Runnable() {
                    public void run() {
                        if (GPUImageTwoInputFilter.this.f2731j == -1 && bitmap != null && !bitmap.isRecycled()) {
                            GLES20.glActiveTexture(33987);
                            int unused = GPUImageTwoInputFilter.this.f2731j = OpenGlUtils.m3743a(bitmap, -1, false);
                        }
                    }
                });
            }
        }
    }

    /* renamed from: b */
    public void mo17802b(int i) {
        this.f2732k = i;
    }

    /* renamed from: n */
    public Bitmap mo17807n() {
        return this.f2734m;
    }

    /* renamed from: g */
    public void mo17806g() {
        super.mo17806g();
        GLES20.glDeleteTextures(1, new int[]{this.f2731j}, 0);
        this.f2731j = -1;
    }

    /* renamed from: d */
    public boolean mo17805d() {
        return this.f2734m == null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17801a(boolean z) {
        int i = this.f2731j == -1 ? this.f2732k : this.f2731j;
        GLES20.glEnableVertexAttribArray(this.f2729a);
        GLES20.glActiveTexture(33987);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(this.f2730i, 3);
        if (z) {
            mo17804b(Rotation.NORMAL, false, z);
        }
        this.f2733l.position(0);
        GLES20.glVertexAttribPointer(this.f2729a, 2, 5126, false, 0, this.f2733l);
    }

    /* renamed from: b */
    public void mo17804b(Rotation agVar, boolean z, boolean z2) {
        float[] a = TextureRotationUtil.m3713a(agVar, z, z2);
        ByteBuffer order = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = order.asFloatBuffer();
        asFloatBuffer.put(a);
        asFloatBuffer.flip();
        this.f2733l = order;
    }
}
