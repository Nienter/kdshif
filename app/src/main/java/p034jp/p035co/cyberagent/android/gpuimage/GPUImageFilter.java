package p034jp.p035co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;
import java.nio.FloatBuffer;
import java.util.LinkedList;

/* renamed from: jp.co.cyberagent.android.gpuimage.f */
public class GPUImageFilter {

    /* renamed from: a */
    private final LinkedList<Runnable> f2761a;

    /* renamed from: b */
    protected int f2762b;

    /* renamed from: c */
    protected int f2763c;

    /* renamed from: d */
    protected int f2764d;

    /* renamed from: e */
    protected int f2765e;

    /* renamed from: f */
    protected int f2766f;

    /* renamed from: g */
    protected int f2767g;

    /* renamed from: h */
    protected boolean f2768h;

    /* renamed from: i */
    private String f2769i;

    /* renamed from: j */
    private String f2770j;

    public GPUImageFilter() {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
    }

    public GPUImageFilter(String str, String str2) {
        this.f2761a = new LinkedList<>();
        this.f2769i = str;
        this.f2770j = str2;
    }

    /* renamed from: c */
    public final void mo17842c() {
        mo17800a();
        this.f2768h = true;
        mo17832b();
    }

    /* renamed from: a */
    public void mo17800a() {
        this.f2762b = OpenGlUtils.m3745a(this.f2769i, this.f2770j);
        this.f2763c = GLES20.glGetAttribLocation(this.f2762b, "position");
        this.f2764d = GLES20.glGetUniformLocation(this.f2762b, "inputImageTexture");
        this.f2765e = GLES20.glGetAttribLocation(this.f2762b, "inputTextureCoordinate");
        this.f2768h = true;
    }

    /* renamed from: b */
    public void mo17832b() {
    }

    /* renamed from: a */
    public final void mo17840a(String str, String str2) {
        this.f2769i = str;
        this.f2770j = str2;
    }

    /* renamed from: d */
    public boolean mo17805d() {
        return false;
    }

    /* renamed from: e */
    public boolean mo17844e() {
        return false;
    }

    /* renamed from: f */
    public final void mo17845f() {
        this.f2768h = false;
        GLES20.glDeleteProgram(this.f2762b);
        mo17806g();
    }

    /* renamed from: g */
    public void mo17806g() {
    }

    /* renamed from: a */
    public void mo17810a(int i, int i2) {
        this.f2766f = i;
        this.f2767g = i2;
    }

    /* renamed from: a */
    public void mo17837a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, boolean z) {
        GLES20.glUseProgram(this.f2762b);
        mo17846h();
        if (this.f2768h) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.f2763c, 2, 5126, false, 0, floatBuffer);
            GLES20.glEnableVertexAttribArray(this.f2763c);
            FloatBuffer floatBuffer4 = z ? floatBuffer3 : floatBuffer2;
            floatBuffer4.position(0);
            GLES20.glVertexAttribPointer(this.f2765e, 2, 5126, false, 0, floatBuffer4);
            GLES20.glEnableVertexAttribArray(this.f2765e);
            if (i != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i);
                GLES20.glUniform1i(this.f2764d, 0);
            }
            mo17801a(z);
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f2763c);
            GLES20.glDisableVertexAttribArray(this.f2765e);
            GLES20.glBindTexture(3553, 0);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17801a(boolean z) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo17846h() {
        while (!this.f2761a.isEmpty()) {
            this.f2761a.removeFirst().run();
        }
    }

    /* renamed from: i */
    public boolean mo17847i() {
        return this.f2768h;
    }

    /* renamed from: j */
    public int mo17848j() {
        return this.f2766f;
    }

    /* renamed from: k */
    public int mo17849k() {
        return this.f2767g;
    }

    /* renamed from: l */
    public int mo17850l() {
        return this.f2762b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17835a(final int i, final float f) {
        mo17839a((Runnable) new Runnable() {
            public void run() {
                GLES20.glUniform1f(i, f);
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17838a(final int i, final float[] fArr) {
        mo17839a((Runnable) new Runnable() {
            public void run() {
                GLES20.glUniform2fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo17841b(final int i, final float[] fArr) {
        mo17839a((Runnable) new Runnable() {
            public void run() {
                GLES20.glUniform4fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17836a(final int i, final PointF pointF) {
        mo17839a((Runnable) new Runnable() {
            public void run() {
                GLES20.glUniform2fv(i, 1, new float[]{pointF.x, pointF.y}, 0);
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo17843c(final int i, final float[] fArr) {
        mo17839a((Runnable) new Runnable() {
            public void run() {
                GLES20.glUniformMatrix4fv(i, 1, false, fArr, 0);
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17839a(Runnable runnable) {
        synchronized (this.f2761a) {
            this.f2761a.addLast(runnable);
        }
    }
}
