package p034jp.p035co.cyberagent.android.gpuimage;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.view.WindowManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* renamed from: jp.co.cyberagent.android.gpuimage.a */
public class GPUImage {

    /* renamed from: a */
    private static final Bitmap f2706a = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);

    /* renamed from: b */
    private static final GPUImageFilter f2707b = new GPUImageFilter();
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final Context f2708c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final GPUImageRenderer f2709d;

    /* renamed from: e */
    private GLSurfaceView f2710e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public GPUImageFilter f2711f;

    /* renamed from: g */
    private Bitmap f2712g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C1665d f2713h = C1665d.CENTER_CROP;

    /* renamed from: jp.co.cyberagent.android.gpuimage.a$a */
    /* compiled from: GPUImage */
    private class C1662a extends C1663b {

        /* renamed from: c */
        private final File f2716c;

        public C1662a(GPUImage aVar, File file) {
            super(aVar);
            this.f2716c = file;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap mo17795a(BitmapFactory.Options options) {
            return BitmapFactory.decodeFile(this.f2716c.getAbsolutePath(), options);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public int mo17794a() {
            switch (new ExifInterface(this.f2716c.getAbsolutePath()).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        }
    }

    /* renamed from: jp.co.cyberagent.android.gpuimage.a$b */
    /* compiled from: GPUImage */
    private abstract class C1663b extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        private final GPUImage f2717a;

        /* renamed from: c */
        private int f2719c;

        /* renamed from: d */
        private int f2720d;

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public abstract int mo17794a();

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public abstract Bitmap mo17795a(BitmapFactory.Options options);

        public C1663b(GPUImage aVar) {
            this.f2717a = aVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(Void... voidArr) {
            if (GPUImage.this.f2709d != null && GPUImage.this.f2709d.mo17885b() == 0) {
                try {
                    synchronized (GPUImage.this.f2709d.f2829b) {
                        GPUImage.this.f2709d.f2829b.wait(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.f2719c = GPUImage.this.m3683d();
            this.f2720d = GPUImage.this.m3685e();
            return m3703b();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            this.f2717a.mo17792c();
            this.f2717a.mo17783a(bitmap);
        }

        /* renamed from: b */
        private Bitmap m3703b() {
            boolean z;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            mo17795a(options);
            int i = 1;
            while (true) {
                if (options.outWidth / i > this.f2719c) {
                    z = true;
                } else {
                    z = false;
                }
                if (!m3701a(z, options.outHeight / i > this.f2720d)) {
                    break;
                }
                i++;
            }
            int i2 = i - 1;
            if (i2 < 1) {
                i2 = 1;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i2;
            options2.inPreferredConfig = Bitmap.Config.RGB_565;
            options2.inPurgeable = true;
            options2.inTempStorage = new byte[32768];
            Bitmap a = mo17795a(options2);
            if (a == null) {
                return null;
            }
            return m3704b(m3705c(a));
        }

        /* renamed from: b */
        private Bitmap m3704b(Bitmap bitmap) {
            int[] a = m3702a(bitmap.getWidth(), bitmap.getHeight());
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, a[0], a[1], true);
            if (createScaledBitmap != bitmap) {
                bitmap.recycle();
                bitmap = createScaledBitmap;
                System.gc();
            }
            if (GPUImage.this.f2713h != C1665d.CENTER_CROP) {
                return bitmap;
            }
            int i = a[0] - this.f2719c;
            int i2 = a[1] - this.f2720d;
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, i / 2, i2 / 2, a[0] - i, a[1] - i2);
            if (createBitmap == bitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createBitmap;
        }

        /* renamed from: a */
        private int[] m3702a(int i, int i2) {
            float f;
            float f2;
            float f3 = ((float) i) / ((float) this.f2719c);
            float f4 = ((float) i2) / ((float) this.f2720d);
            if (GPUImage.this.f2713h == C1665d.CENTER_CROP ? f3 > f4 : f3 < f4) {
                f2 = (float) this.f2720d;
                f = (f2 / ((float) i2)) * ((float) i);
            } else {
                f = (float) this.f2719c;
                f2 = (f / ((float) i)) * ((float) i2);
            }
            return new int[]{Math.round(f), Math.round(f2)};
        }

        /* renamed from: a */
        private boolean m3701a(boolean z, boolean z2) {
            boolean z3 = false;
            if (GPUImage.this.f2713h != C1665d.CENTER_CROP) {
                if (z || z2) {
                    z3 = true;
                }
                return z3;
            } else if (!z || !z2) {
                return false;
            } else {
                return true;
            }
        }

        /* renamed from: c */
        private Bitmap m3705c(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            try {
                int a = mo17794a();
                if (a == 0) {
                    return bitmap;
                }
                Matrix matrix = new Matrix();
                matrix.postRotate((float) a);
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                try {
                    bitmap.recycle();
                    return createBitmap;
                } catch (IOException e) {
                    bitmap = createBitmap;
                    e = e;
                    e.printStackTrace();
                    return bitmap;
                }
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return bitmap;
            }
        }
    }

    /* renamed from: jp.co.cyberagent.android.gpuimage.a$c */
    /* compiled from: GPUImage */
    private class C1664c extends C1663b {

        /* renamed from: c */
        private final Uri f2722c;

        public C1664c(GPUImage aVar, Uri uri) {
            super(aVar);
            this.f2722c = uri;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap mo17795a(BitmapFactory.Options options) {
            InputStream inputStream;
            try {
                if (this.f2722c.getScheme().startsWith("http") || this.f2722c.getScheme().startsWith("https")) {
                    inputStream = new URL(this.f2722c.toString()).openStream();
                } else {
                    inputStream = GPUImage.this.f2708c.getContentResolver().openInputStream(this.f2722c);
                }
                return BitmapFactory.decodeStream(inputStream, null, options);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public int mo17794a() {
            Cursor query = GPUImage.this.f2708c.getContentResolver().query(this.f2722c, new String[]{"orientation"}, null, null, null);
            if (query == null || query.getCount() != 1) {
                return 0;
            }
            query.moveToFirst();
            int i = query.getInt(0);
            query.close();
            return i;
        }
    }

    /* renamed from: jp.co.cyberagent.android.gpuimage.a$d */
    /* compiled from: GPUImage */
    public enum C1665d {
        CENTER_INSIDE,
        CENTER_CROP
    }

    public GPUImage(Context context) {
        if (!m3679a(context)) {
            throw new IllegalStateException("OpenGL ES 2.0 is not supported on this phone.");
        }
        this.f2708c = context;
        this.f2711f = new GPUImageFilter();
        this.f2709d = new GPUImageRenderer(this.f2711f);
    }

    /* renamed from: a */
    public Context mo17782a() {
        return this.f2708c;
    }

    /* renamed from: a */
    private boolean m3679a(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    /* renamed from: a */
    public void mo17785a(GLSurfaceView gLSurfaceView) {
        this.f2710e = gLSurfaceView;
        this.f2710e.setEGLContextClientVersion(2);
        this.f2710e.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.f2710e.getHolder().setFormat(1);
        this.f2710e.setRenderer(this.f2709d);
        this.f2710e.setRenderMode(0);
        this.f2710e.requestRender();
    }

    /* renamed from: b */
    public void mo17791b() {
        if (this.f2710e != null) {
            this.f2710e.requestRender();
        }
    }

    /* renamed from: a */
    public void mo17789a(GPUImageFilter fVar) {
        this.f2711f = fVar;
        this.f2709d.mo17884a(this.f2711f);
        mo17791b();
    }

    /* renamed from: a */
    public void mo17783a(Bitmap bitmap) {
        this.f2712g = bitmap;
        this.f2709d.mo17879a(bitmap, false);
        mo17791b();
    }

    /* renamed from: a */
    public void mo17787a(C1665d dVar) {
        this.f2713h = dVar;
        this.f2709d.mo17881a(dVar);
        this.f2709d.mo17878a();
        this.f2712g = null;
        mo17791b();
    }

    /* renamed from: a */
    public void mo17788a(Rotation agVar) {
        this.f2709d.mo17882a(agVar);
    }

    /* renamed from: c */
    public void mo17792c() {
        this.f2709d.mo17878a();
        this.f2712g = null;
        mo17791b();
    }

    /* renamed from: a */
    public void mo17784a(Uri uri) {
        new C1664c(this, uri).execute(new Void[0]);
    }

    /* renamed from: a */
    public void mo17786a(File file) {
        new C1662a(this, file).execute(new Void[0]);
    }

    /* renamed from: c */
    private Bitmap m3681c(Bitmap bitmap) {
        if (this.f2710e != null) {
            this.f2709d.mo17878a();
            this.f2709d.mo17880a((Runnable) new Runnable() {
                public void run() {
                    synchronized (GPUImage.this.f2711f) {
                        GPUImage.this.f2711f.mo17845f();
                        GPUImage.this.f2711f.notify();
                    }
                }
            });
            synchronized (this.f2711f) {
                mo17791b();
                try {
                    this.f2711f.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        GPUImageRenderer uVar = new GPUImageRenderer(this.f2711f);
        uVar.mo17883a(Rotation.NORMAL, this.f2709d.mo17887d(), this.f2709d.mo17888e());
        uVar.mo17881a(this.f2713h);
        PixelBuffer afVar = new PixelBuffer(bitmap.getWidth(), bitmap.getHeight());
        uVar.mo17879a(bitmap, false);
        afVar.mo17827a((GLSurfaceView.Renderer) uVar);
        Bitmap a = afVar.mo17826a();
        this.f2711f.mo17845f();
        uVar.mo17878a();
        afVar.mo17829c();
        this.f2709d.mo17884a(this.f2711f);
        if (this.f2712g != null) {
            this.f2709d.mo17879a(this.f2712g, false);
        }
        mo17791b();
        return a;
    }

    /* renamed from: b */
    public Bitmap mo17790b(Bitmap bitmap) {
        Bitmap c = m3681c(bitmap);
        mo17789a(f2707b);
        m3681c(f2706a);
        return c;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public int m3683d() {
        if (this.f2709d != null && this.f2709d.mo17885b() != 0) {
            return this.f2709d.mo17885b();
        }
        if (this.f2712g != null) {
            return this.f2712g.getWidth();
        }
        return ((WindowManager) this.f2708c.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public int m3685e() {
        if (this.f2709d != null && this.f2709d.mo17886c() != 0) {
            return this.f2709d.mo17886c();
        }
        if (this.f2712g != null) {
            return this.f2712g.getHeight();
        }
        return ((WindowManager) this.f2708c.getSystemService("window")).getDefaultDisplay().getHeight();
    }
}
