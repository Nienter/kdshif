package p034jp.p035co.cyberagent.android.gpuimage;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import p034jp.p035co.cyberagent.android.gpuimage.p036a.TextureRotationUtil;

/* renamed from: jp.co.cyberagent.android.gpuimage.h */
public class GPUImageFilterGroup extends GPUImageFilter {

    /* renamed from: a */
    protected List<GPUImageFilter> f2793a;

    /* renamed from: i */
    protected List<GPUImageFilter> f2794i;

    /* renamed from: j */
    private int[] f2795j;

    /* renamed from: k */
    private int[] f2796k;

    /* renamed from: l */
    private final FloatBuffer f2797l;

    /* renamed from: m */
    private final FloatBuffer f2798m;

    /* renamed from: n */
    private final FloatBuffer f2799n;

    public GPUImageFilterGroup() {
        this(null);
    }

    public GPUImageFilterGroup(List<GPUImageFilter> list) {
        this.f2793a = list;
        if (this.f2793a == null) {
            this.f2793a = new ArrayList();
        } else {
            mo17861n();
        }
        this.f2797l = ByteBuffer.allocateDirect(GPUImageRenderer.f2828a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f2797l.put(GPUImageRenderer.f2828a).position(0);
        this.f2798m = ByteBuffer.allocateDirect(TextureRotationUtil.f2724a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f2798m.put(TextureRotationUtil.f2724a).position(0);
        this.f2799n = ByteBuffer.allocateDirect(TextureRotationUtil.f2724a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f2799n.put(TextureRotationUtil.m3713a(Rotation.NORMAL, false, true)).position(0);
    }

    /* renamed from: a */
    public void mo17859a(GPUImageFilter fVar) {
        if (fVar != null) {
            this.f2793a.add(fVar);
            mo17861n();
        }
    }

    /* renamed from: a */
    public void mo17858a(List<GPUImageFilter> list) {
        this.f2793a.clear();
        if (list != null) {
            this.f2793a.addAll(list);
        }
        mo17861n();
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        for (GPUImageFilter c : this.f2793a) {
            c.mo17842c();
        }
    }

    /* renamed from: g */
    public void mo17806g() {
        mo17811o();
        for (GPUImageFilter f : this.f2793a) {
            f.mo17845f();
        }
        super.mo17806g();
    }

    /* renamed from: o */
    private void mo17811o() {
        if (this.f2796k != null) {
            GLES20.glDeleteTextures(this.f2796k.length, this.f2796k, 0);
            this.f2796k = null;
        }
        if (this.f2795j != null) {
            GLES20.glDeleteFramebuffers(this.f2795j.length, this.f2795j, 0);
            this.f2795j = null;
        }
    }

    /* renamed from: a */
    public void mo17810a(int i, int i2) {
        super.mo17810a(i, i2);
        if (this.f2795j != null) {
            mo17811o();
        }
        int size = this.f2793a.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f2793a.get(i3).mo17810a(i, i2);
        }
        if (this.f2794i != null && this.f2794i.size() > 0) {
            int size2 = this.f2794i.size();
            this.f2795j = new int[(size2 - 1)];
            this.f2796k = new int[(size2 - 1)];
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 < size2 - 1) {
                    GLES20.glGenFramebuffers(1, this.f2795j, i5);
                    GLES20.glGenTextures(1, this.f2796k, i5);
                    GLES20.glBindTexture(3553, this.f2796k[i5]);
                    GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
                    GLES20.glTexParameterf(3553, 10240, 9729.0f);
                    GLES20.glTexParameterf(3553, 10241, 9729.0f);
                    GLES20.glTexParameterf(3553, 10242, 33071.0f);
                    GLES20.glTexParameterf(3553, 10243, 33071.0f);
                    GLES20.glBindFramebuffer(36160, this.f2795j[i5]);
                    GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f2796k[i5], 0);
                    GLES20.glBindTexture(3553, 0);
                    GLES20.glBindFramebuffer(36160, 0);
                    i4 = i5 + 1;
                } else {
                    return;
                }
            }
        }
    }

    @SuppressLint({"WrongCall"})
    /* renamed from: a */
    public void mo17837a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, boolean z) {
        mo17846h();
        if (mo17847i() && this.f2795j != null && this.f2796k != null && this.f2794i != null) {
            int size = this.f2794i.size();
            int i2 = 0;
            int i3 = i;
            while (i2 < size) {
                GPUImageFilter fVar = this.f2794i.get(i2);
                boolean z2 = i2 < size + -1;
                if (z2) {
                    GLES20.glBindFramebuffer(36160, this.f2795j[i2]);
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                }
                if (i2 == 0) {
                    fVar.mo17837a(i3, floatBuffer, floatBuffer2, floatBuffer3, !z2);
                } else {
                    fVar.mo17837a(i3, this.f2797l, this.f2798m, this.f2799n, !z2);
                }
                if (z2) {
                    GLES20.glBindFramebuffer(36160, 0);
                    i3 = this.f2796k[i2];
                }
                i2++;
            }
        }
    }

    /* renamed from: m */
    public List<GPUImageFilter> mo17860m() {
        return this.f2794i;
    }

    /* renamed from: n */
    public void mo17861n() {
        if (this.f2793a != null) {
            if (this.f2794i == null) {
                this.f2794i = new ArrayList();
            } else {
                this.f2794i.clear();
            }
            for (GPUImageFilter next : this.f2793a) {
                if (next instanceof GPUImageFilterGroup) {
                    ((GPUImageFilterGroup) next).mo17861n();
                    List<GPUImageFilter> m = ((GPUImageFilterGroup) next).mo17860m();
                    if (m != null && !m.isEmpty()) {
                        this.f2794i.addAll(m);
                    }
                } else {
                    this.f2794i.add(next);
                }
            }
        }
    }
}
