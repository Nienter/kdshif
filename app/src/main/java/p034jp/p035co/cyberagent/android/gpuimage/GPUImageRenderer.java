package p034jp.p035co.cyberagent.android.gpuimage;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;
import p034jp.p035co.cyberagent.android.gpuimage.p036a.TextureRotationUtil;

@TargetApi(11)
/* renamed from: jp.co.cyberagent.android.gpuimage.u */
public class GPUImageRenderer implements Camera.PreviewCallback, GLSurfaceView.Renderer {

    /* renamed from: a */
    static final float[] f2828a = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};

    /* renamed from: b */
    public final Object f2829b = new Object();
    /* access modifiers changed from: private */

    /* renamed from: c */
    public GPUImageFilter f2830c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public int f2831d = -1;

    /* renamed from: e */
    private SurfaceTexture f2832e = null;

    /* renamed from: f */
    private final FloatBuffer f2833f;

    /* renamed from: g */
    private final FloatBuffer f2834g;

    /* renamed from: h */
    private final FloatBuffer f2835h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public IntBuffer f2836i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f2837j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public int f2838k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public int f2839l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public int f2840m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public int f2841n;

    /* renamed from: o */
    private final Queue<Runnable> f2842o;

    /* renamed from: p */
    private final Queue<Runnable> f2843p;

    /* renamed from: q */
    private Rotation f2844q;

    /* renamed from: r */
    private boolean f2845r;

    /* renamed from: s */
    private boolean f2846s;

    /* renamed from: t */
    private GPUImage.C1665d f2847t = GPUImage.C1665d.CENTER_CROP;

    /* renamed from: u */
    private float f2848u = 0.0f;

    /* renamed from: v */
    private float f2849v = 0.0f;

    /* renamed from: w */
    private float f2850w = 0.0f;

    public GPUImageRenderer(GPUImageFilter fVar) {
        this.f2830c = fVar;
        this.f2842o = new LinkedList();
        this.f2843p = new LinkedList();
        this.f2833f = ByteBuffer.allocateDirect(f2828a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f2833f.put(f2828a).position(0);
        this.f2835h = ByteBuffer.allocateDirect(TextureRotationUtil.f2724a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f2834g = ByteBuffer.allocateDirect(TextureRotationUtil.f2724a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mo17883a(Rotation.NORMAL, false, false);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(this.f2848u, this.f2849v, this.f2850w, 1.0f);
        GLES20.glDisable(2929);
        this.f2830c.mo17842c();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.f2837j = i;
        this.f2838k = i2;
        GLES20.glViewport(0, 0, i, i2);
        GLES20.glUseProgram(this.f2830c.mo17850l());
        this.f2830c.mo17810a(i, i2);
        m3845f();
        synchronized (this.f2829b) {
            this.f2829b.notifyAll();
        }
    }

    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16640);
        m3836a(this.f2842o);
        this.f2830c.mo17837a(this.f2831d, this.f2833f, this.f2834g, this.f2835h, true);
        m3836a(this.f2843p);
        if (this.f2832e != null) {
            this.f2832e.updateTexImage();
        }
    }

    /* renamed from: a */
    private void m3836a(Queue<Runnable> queue) {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                queue.poll().run();
            }
        }
    }

    public void onPreviewFrame(final byte[] bArr, final Camera camera) {
        final Camera.Size previewSize = camera.getParameters().getPreviewSize();
        if (this.f2836i == null) {
            this.f2836i = IntBuffer.allocate(previewSize.width * previewSize.height);
        }
        if (this.f2842o.isEmpty()) {
            mo17880a((Runnable) new Runnable() {
                public void run() {
                    GPUImageNativeLibrary.YUVtoRBGA(bArr, previewSize.width, previewSize.height, GPUImageRenderer.this.f2836i.array());
                    int unused = GPUImageRenderer.this.f2831d = OpenGlUtils.m3746a(GPUImageRenderer.this.f2836i, previewSize, GPUImageRenderer.this.f2831d);
                    camera.addCallbackBuffer(bArr);
                    if (GPUImageRenderer.this.f2839l != previewSize.width) {
                        int unused2 = GPUImageRenderer.this.f2839l = previewSize.width;
                        int unused3 = GPUImageRenderer.this.f2840m = previewSize.height;
                        GPUImageRenderer.this.m3845f();
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void mo17884a(final GPUImageFilter fVar) {
        mo17880a((Runnable) new Runnable() {
            public void run() {
                GPUImageFilter e = GPUImageRenderer.this.f2830c;
                GPUImageFilter unused = GPUImageRenderer.this.f2830c = fVar;
                if (e != null) {
                    e.mo17845f();
                }
                GPUImageRenderer.this.f2830c.mo17842c();
                GLES20.glUseProgram(GPUImageRenderer.this.f2830c.mo17850l());
                GPUImageRenderer.this.f2830c.mo17810a(GPUImageRenderer.this.f2837j, GPUImageRenderer.this.f2838k);
            }
        });
    }

    /* renamed from: a */
    public void mo17878a() {
        mo17880a((Runnable) new Runnable() {
            public void run() {
                GLES20.glDeleteTextures(1, new int[]{GPUImageRenderer.this.f2831d}, 0);
                int unused = GPUImageRenderer.this.f2831d = -1;
            }
        });
    }

    /* renamed from: a */
    public void mo17879a(final Bitmap bitmap, final boolean z) {
        if (bitmap != null) {
            mo17880a((Runnable) new Runnable() {
                public void run() {
                    Bitmap bitmap;
                    if (bitmap.getWidth() % 2 == 1) {
                        bitmap = Bitmap.createBitmap(bitmap.getWidth() + 1, bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        canvas.drawARGB(0, 0, 0, 0);
                        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
                        int unused = GPUImageRenderer.this.f2841n = 1;
                    } else {
                        int unused2 = GPUImageRenderer.this.f2841n = 0;
                        bitmap = null;
                    }
                    int unused3 = GPUImageRenderer.this.f2831d = OpenGlUtils.m3743a(bitmap != null ? bitmap : bitmap, GPUImageRenderer.this.f2831d, z);
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    int unused4 = GPUImageRenderer.this.f2839l = bitmap.getWidth();
                    int unused5 = GPUImageRenderer.this.f2840m = bitmap.getHeight();
                    GPUImageRenderer.this.m3845f();
                }
            });
        }
    }

    /* renamed from: a */
    public void mo17881a(GPUImage.C1665d dVar) {
        this.f2847t = dVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo17885b() {
        return this.f2837j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo17886c() {
        return this.f2838k;
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m3845f() {
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        float f = (float) this.f2837j;
        float f2 = (float) this.f2838k;
        if (this.f2844q == Rotation.ROTATION_270 || this.f2844q == Rotation.ROTATION_90) {
            f = (float) this.f2838k;
            f2 = (float) this.f2837j;
        }
        float max = Math.max(f / ((float) this.f2839l), f2 / ((float) this.f2840m));
        float round = ((float) Math.round(((float) this.f2839l) * max)) / f;
        float round2 = ((float) Math.round(max * ((float) this.f2840m))) / f2;
        float[] fArr4 = f2828a;
        float[] a = TextureRotationUtil.m3713a(this.f2844q, this.f2845r, this.f2846s);
        float[] a2 = TextureRotationUtil.m3713a(this.f2844q, this.f2845r, !this.f2846s);
        if (this.f2847t == GPUImage.C1665d.CENTER_CROP) {
            float f3 = (1.0f - (1.0f / round)) / 2.0f;
            float f4 = (1.0f - (1.0f / round2)) / 2.0f;
            float[] fArr5 = {m3832a(a[0], f3), m3832a(a[1], f4), m3832a(a[2], f3), m3832a(a[3], f4), m3832a(a[4], f3), m3832a(a[5], f4), m3832a(a[6], f3), m3832a(a[7], f4)};
            fArr2 = new float[]{m3832a(a2[0], f3), m3832a(a2[1], f4), m3832a(a2[2], f3), m3832a(a2[3], f4), m3832a(a2[4], f3), m3832a(a2[5], f4), m3832a(a2[6], f3), m3832a(a2[7], f4)};
            fArr3 = fArr5;
            fArr = fArr4;
        } else {
            fArr = new float[]{f2828a[0] / round2, f2828a[1] / round, f2828a[2] / round2, f2828a[3] / round, f2828a[4] / round2, f2828a[5] / round, f2828a[6] / round2, f2828a[7] / round};
            fArr2 = a2;
            fArr3 = a;
        }
        this.f2833f.clear();
        this.f2833f.put(fArr).position(0);
        this.f2834g.clear();
        this.f2834g.put(fArr3).position(0);
        this.f2835h.put(fArr2).position(0);
    }

    /* renamed from: a */
    private float m3832a(float f, float f2) {
        return f == 0.0f ? f2 : 1.0f - f2;
    }

    /* renamed from: a */
    public void mo17882a(Rotation agVar) {
        this.f2844q = agVar;
        m3845f();
    }

    /* renamed from: a */
    public void mo17883a(Rotation agVar, boolean z, boolean z2) {
        this.f2845r = z;
        this.f2846s = z2;
        mo17882a(agVar);
    }

    /* renamed from: d */
    public boolean mo17887d() {
        return this.f2845r;
    }

    /* renamed from: e */
    public boolean mo17888e() {
        return this.f2846s;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17880a(Runnable runnable) {
        synchronized (this.f2842o) {
            this.f2842o.add(runnable);
        }
    }
}
