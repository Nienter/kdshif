package p034jp.p035co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.Log;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: jp.co.cyberagent.android.gpuimage.af */
public class PixelBuffer {

    /* renamed from: m */
    private static Boolean f2740m = null;

    /* renamed from: a */
    GLSurfaceView.Renderer f2741a;

    /* renamed from: b */
    int f2742b;

    /* renamed from: c */
    int f2743c;

    /* renamed from: d */
    Bitmap f2744d;

    /* renamed from: e */
    EGL10 f2745e = ((EGL10) EGLContext.getEGL());

    /* renamed from: f */
    EGLDisplay f2746f = this.f2745e.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

    /* renamed from: g */
    EGLConfig[] f2747g;

    /* renamed from: h */
    EGLConfig f2748h;

    /* renamed from: i */
    EGLContext f2749i;

    /* renamed from: j */
    EGLSurface f2750j;

    /* renamed from: k */
    GL10 f2751k;

    /* renamed from: l */
    String f2752l;

    public PixelBuffer(int i, int i2) {
        this.f2742b = i;
        this.f2743c = i2;
        int[] iArr = {12375, this.f2742b, 12374, this.f2743c, 12344};
        this.f2745e.eglInitialize(this.f2746f, new int[2]);
        this.f2748h = m3748d();
        this.f2749i = this.f2745e.eglCreateContext(this.f2746f, this.f2748h, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        this.f2750j = this.f2745e.eglCreatePbufferSurface(this.f2746f, this.f2748h, iArr);
        if (this.f2745e.eglGetError() != 12288) {
            mo17828b();
            throw new IllegalArgumentException();
        }
        this.f2745e.eglMakeCurrent(this.f2746f, this.f2750j, this.f2750j, this.f2749i);
        this.f2751k = (GL10) this.f2749i.getGL();
        this.f2752l = Thread.currentThread().getName();
    }

    /* renamed from: a */
    public void mo17827a(GLSurfaceView.Renderer renderer) {
        this.f2741a = renderer;
        if (this.f2741a == null) {
            throw new IllegalArgumentException("renderer can not be null");
        } else if (!Thread.currentThread().getName().equals(this.f2752l)) {
            throw new IllegalStateException("setRenderer: This thread does not own the OpenGL context.");
        } else {
            this.f2741a.onSurfaceCreated(this.f2751k, this.f2748h);
            this.f2741a.onSurfaceChanged(this.f2751k, this.f2742b, this.f2743c);
        }
    }

    /* renamed from: a */
    private boolean m3747a(String[] strArr) {
        String str = Build.MODEL;
        String str2 = Build.DEVICE;
        for (String str3 : strArr) {
            if (str.startsWith(str3) || str2.startsWith(str3)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public Bitmap mo17826a() {
        if (this.f2741a == null) {
            Log.e("PixelBuffer", "getBitmap: Renderer was not set.");
            return null;
        } else if (!Thread.currentThread().getName().equals(this.f2752l)) {
            Log.e("PixelBuffer", "getBitmap: This thread does not own the OpenGL context.");
            return null;
        } else {
            this.f2741a.onDrawFrame(this.f2751k);
            this.f2741a.onDrawFrame(this.f2751k);
            if (f2740m == null) {
                f2740m = Boolean.valueOf(m3747a(new String[]{"GT-I9502", "GT-I9500", "GT-I9505", "GT-I9508", "GT-I959"}));
            }
            if (this.f2742b <= 8 || !f2740m.booleanValue()) {
                m3750f();
            } else {
                m3749e();
            }
            return this.f2744d;
        }
    }

    /* renamed from: b */
    public void mo17828b() {
        this.f2745e.eglMakeCurrent(this.f2746f, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        this.f2745e.eglDestroySurface(this.f2746f, this.f2750j);
        this.f2745e.eglDestroyContext(this.f2746f, this.f2749i);
        this.f2745e.eglTerminate(this.f2746f);
    }

    /* renamed from: c */
    public void mo17829c() {
        this.f2741a.onDrawFrame(this.f2751k);
        this.f2741a.onDrawFrame(this.f2751k);
        this.f2745e.eglMakeCurrent(this.f2746f, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        this.f2745e.eglDestroySurface(this.f2746f, this.f2750j);
        this.f2745e.eglDestroyContext(this.f2746f, this.f2749i);
        this.f2745e.eglTerminate(this.f2746f);
    }

    /* renamed from: d */
    private EGLConfig m3748d() {
        int[] iArr = {12325, 0, 12326, 0, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344};
        int[] iArr2 = new int[1];
        this.f2745e.eglChooseConfig(this.f2746f, iArr, null, 0, iArr2);
        int i = iArr2[0];
        this.f2747g = new EGLConfig[i];
        this.f2745e.eglChooseConfig(this.f2746f, iArr, this.f2747g, i, iArr2);
        return this.f2747g[0];
    }

    /* renamed from: e */
    private void m3749e() {
        int i = this.f2742b - (this.f2742b % 8);
        int[] iArr = new int[(this.f2743c * i)];
        IntBuffer allocate = IntBuffer.allocate(this.f2743c * i);
        this.f2751k.glReadPixels(0, 0, i, this.f2743c, 6408, 5121, allocate);
        int[] array = allocate.array();
        for (int i2 = 0; i2 < this.f2743c; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                iArr[(((this.f2743c - i2) - 1) * i) + i3] = array[(i2 * i) + i3];
            }
        }
        this.f2744d = Bitmap.createBitmap(i, this.f2743c, Bitmap.Config.ARGB_8888);
        this.f2744d.copyPixelsFromBuffer(IntBuffer.wrap(iArr));
    }

    /* renamed from: f */
    private void m3750f() {
        try {
            int[] iArr = new int[(this.f2742b * this.f2743c)];
            IntBuffer allocate = IntBuffer.allocate(this.f2742b * this.f2743c);
            this.f2751k.glReadPixels(0, 0, this.f2742b, this.f2743c, 6408, 5121, allocate);
            int[] array = allocate.array();
            for (int i = 0; i < this.f2743c; i++) {
                for (int i2 = 0; i2 < this.f2742b; i2++) {
                    iArr[(((this.f2743c - i) - 1) * this.f2742b) + i2] = array[(this.f2742b * i) + i2];
                }
            }
            allocate.clear();
            this.f2744d = Bitmap.createBitmap(this.f2742b, this.f2743c, Bitmap.Config.ARGB_8888);
            this.f2744d.copyPixelsFromBuffer(IntBuffer.wrap(iArr));
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (this.f2744d != null) {
                this.f2744d.recycle();
                this.f2744d = null;
            }
        }
    }
}
