package p034jp.p035co.cyberagent.android.gpuimage;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import p034jp.p035co.cyberagent.android.gpuimage.p036a.TextureRotationUtil;

/* renamed from: jp.co.cyberagent.android.gpuimage.g */
public class GPUImageFilterGraph extends GPUImageFilter {

    /* renamed from: a */
    protected List<GraphNode> f2786a;

    /* renamed from: i */
    protected List<GraphNode> f2787i;

    /* renamed from: j */
    private int[] f2788j;

    /* renamed from: k */
    private int[] f2789k;

    /* renamed from: l */
    private final FloatBuffer f2790l;

    /* renamed from: m */
    private final FloatBuffer f2791m;

    /* renamed from: n */
    private final FloatBuffer f2792n;

    public GPUImageFilterGraph() {
        this(null);
    }

    public GPUImageFilterGraph(List<GraphNode> list) {
        this.f2786a = list;
        if (this.f2786a == null) {
            this.f2786a = new ArrayList();
        } else {
            mo17857m();
        }
        this.f2790l = ByteBuffer.allocateDirect(GPUImageRenderer.f2828a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f2790l.put(GPUImageRenderer.f2828a).position(0);
        this.f2791m = ByteBuffer.allocateDirect(TextureRotationUtil.f2724a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f2791m.put(TextureRotationUtil.f2724a).position(0);
        this.f2792n = ByteBuffer.allocateDirect(TextureRotationUtil.f2724a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f2792n.put(TextureRotationUtil.m3713a(Rotation.NORMAL, false, true)).position(0);
    }

    /* renamed from: a */
    public void mo17856a(List<GraphNode> list) {
        this.f2786a.clear();
        if (list != null) {
            this.f2786a.addAll(list);
        }
        mo17857m();
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        for (GraphNode b : this.f2786a) {
            b.mo17818b();
        }
    }

    /* renamed from: g */
    public void mo17806g() {
        m3786n();
        for (GraphNode c : this.f2786a) {
            c.mo17820c();
        }
        super.mo17806g();
    }

    /* renamed from: n */
    private synchronized void m3786n() {
        if (this.f2789k != null) {
            GLES20.glDeleteTextures(this.f2789k.length, this.f2789k, 0);
            this.f2789k = null;
        }
        if (this.f2788j != null) {
            GLES20.glDeleteFramebuffers(this.f2788j.length, this.f2788j, 0);
            this.f2788j = null;
        }
    }

    /* renamed from: a */
    public void mo17810a(int i, int i2) {
        synchronized (this) {
            super.mo17810a(i, i2);
            if (this.f2788j != null) {
                m3786n();
            }
            int size = this.f2786a.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.f2786a.get(i3).mo17816a(i, i2);
            }
            if (this.f2787i != null && this.f2787i.size() > 0) {
                int size2 = this.f2787i.size();
                this.f2788j = new int[(size2 - 1)];
                this.f2789k = new int[(size2 - 1)];
                for (int i4 = 0; i4 < size2 - 1; i4++) {
                    GLES20.glGenFramebuffers(1, this.f2788j, i4);
                    GLES20.glGenTextures(1, this.f2789k, i4);
                    GLES20.glBindTexture(3553, this.f2789k[i4]);
                    GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
                    GLES20.glTexParameterf(3553, 10240, 9729.0f);
                    GLES20.glTexParameterf(3553, 10241, 9729.0f);
                    GLES20.glTexParameterf(3553, 10242, 33071.0f);
                    GLES20.glTexParameterf(3553, 10243, 33071.0f);
                    GLES20.glBindFramebuffer(36160, this.f2788j[i4]);
                    GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f2789k[i4], 0);
                    GLES20.glBindTexture(3553, 0);
                    GLES20.glBindFramebuffer(36160, 0);
                }
            }
        }
    }

    @SuppressLint({"WrongCall"})
    /* renamed from: a */
    public void mo17837a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, boolean z) {
        mo17846h();
        if (mo17847i() && this.f2788j != null && this.f2789k != null) {
            synchronized (this) {
                if (!mo17847i() || this.f2788j == null || this.f2789k == null || this.f2790l == null || floatBuffer2 == null || floatBuffer3 == null) {
                    throw new IllegalStateException(this.f2786a + "not ready");
                }
                int size = this.f2787i.size();
                if (this.f2787i != null && size > 0) {
                    int i2 = 0;
                    while (i2 < this.f2787i.size()) {
                        GraphNode adVar = this.f2787i.get(i2);
                        boolean z2 = i2 < size + -1;
                        if (z2) {
                            GLES20.glBindFramebuffer(36160, this.f2788j[i2]);
                            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                        }
                        if (adVar.mo17815a().mo17805d()) {
                            ((GPUImageTwoInputFilter) adVar.mo17815a()).mo17802b(adVar.mo17823e() == GraphNode.f2737a ? i : this.f2789k[this.f2787i.indexOf(adVar.mo17823e())]);
                        }
                        if (adVar.mo17815a().mo17844e()) {
                            ((GPUImageThreeInputFilter) adVar.mo17815a()).mo17899a(adVar.mo17824f() == GraphNode.f2737a ? i : this.f2789k[this.f2787i.indexOf(adVar.mo17824f())]);
                        }
                        if (adVar.mo17822d() == GraphNode.f2737a) {
                            adVar.mo17815a().mo17837a(i, floatBuffer, floatBuffer2, floatBuffer3, !z2);
                        } else {
                            adVar.mo17815a().mo17837a(this.f2789k[this.f2787i.indexOf(adVar.mo17822d())], this.f2790l, floatBuffer2, floatBuffer3, !z2);
                        }
                        if (z2) {
                            GLES20.glBindFramebuffer(36160, 0);
                        }
                        i2++;
                    }
                }
            }
        }
    }

    /* renamed from: m */
    public void mo17857m() {
        if (this.f2786a != null && this.f2786a.size() != 0) {
            if (this.f2787i == null) {
                this.f2787i = new ArrayList();
            } else {
                this.f2787i.clear();
            }
            this.f2786a.get(this.f2786a.size() - 1).mo17814a(this.f2787i);
        }
    }
}
