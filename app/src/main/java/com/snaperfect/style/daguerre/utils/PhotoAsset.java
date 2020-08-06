package com.snaperfect.style.daguerre.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p001v4.content.FileProvider;
import android.util.Log;
import android.widget.ImageView;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.filter.FilterObj;
import com.snaperfect.style.daguerre.math.CGRect;
import com.snaperfect.style.daguerre.math.CGSize;
import com.snaperfect.style.daguerre.p031a.Directories;
import com.snaperfect.style.daguerre.style.StyleBitmapTask;
import com.snaperfect.style.daguerre.style.StyleObj;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageAlphaMaskGenFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageDissolveBlendFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageExposureFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFilterGraph;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFragScaleFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageGreenAndBlueChannelOverlayFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageHardLight3TimesBoostFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageHighPassFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageHighpassSkinSmoothingCompositingFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageNormalBlendFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageSharpenFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageToneCurveFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GraphNode;

public class PhotoAsset implements Parcelable {
    public static final Parcelable.Creator<PhotoAsset> CREATOR = new Parcelable.Creator<PhotoAsset>() {
        /* renamed from: a */
        public PhotoAsset[] newArray(int i) {
            return new PhotoAsset[i];
        }

        /* renamed from: a */
        public PhotoAsset createFromParcel(Parcel parcel) {
            return new PhotoAsset(parcel);
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final HashMap<String, C1578b> f2202a = new HashMap<>();

    /* renamed from: b */
    private final String f2203b;

    /* renamed from: c */
    private final String f2204c;

    /* renamed from: d */
    private final String f2205d;

    /* renamed from: e */
    private final String f2206e;

    /* renamed from: f */
    private float f2207f;

    /* renamed from: g */
    private float f2208g;

    /* renamed from: h */
    private float f2209h;

    /* renamed from: i */
    private int f2210i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public CGRect f2211j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public String f2212k = "NULL";

    /* renamed from: com.snaperfect.style.daguerre.utils.PhotoAsset$a */
    private static class C1577a implements Transformation {

        /* renamed from: a */
        private C1578b f2229a;

        /* renamed from: b */
        private final GPUImage f2230b;

        /* renamed from: c */
        private final CGRect f2231c;

        /* renamed from: d */
        private final String f2232d;

        /* renamed from: e */
        private final int f2233e;

        /* renamed from: f */
        private final int f2234f;

        /* renamed from: g */
        private final boolean f2235g;

        /* renamed from: h */
        private final boolean f2236h;

        /* renamed from: i */
        private final float f2237i;

        private C1577a(GPUImage aVar, CGRect cGRect, String str, int i, int i2, boolean z, boolean z2, float f) {
            int i3;
            int i4 = 1;
            this.f2230b = aVar;
            this.f2231c = cGRect;
            this.f2233e = i;
            this.f2235g = z;
            this.f2236h = z2;
            this.f2234f = i2;
            this.f2237i = f;
            Object[] objArr = new Object[5];
            objArr[0] = str;
            objArr[1] = Integer.valueOf(i);
            objArr[2] = Integer.valueOf(i2);
            if (z) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            objArr[3] = Integer.valueOf(i3);
            objArr[4] = Integer.valueOf(!z2 ? 0 : i4);
            this.f2232d = Localize.m3098a("%s_%d_%d_%d_%d", objArr);
        }

        C1577a(CGRect cGRect, String str, boolean z) {
            this(null, cGRect, str, 0, 0, false, z, 0.0f);
        }

        C1577a(GPUImage aVar, CGRect cGRect, String str, int i, int i2, boolean z, float f) {
            this(aVar, cGRect, str, i, i2, z, false, f);
        }

        /* renamed from: a */
        public Bitmap mo17273a(Bitmap bitmap) {
            if (this.f2231c != null) {
                Bitmap a = BitmapUtils.m3084a(bitmap, this.f2231c, true);
                if (!a.equals(bitmap)) {
                    bitmap.recycle();
                }
                bitmap = a;
            }
            if (this.f2236h) {
                this.f2229a.mo17282a(new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight()));
                this.f2229a = null;
                Bitmap a2 = BitmapUtils.m3082a(bitmap);
                if (!a2.equals(bitmap)) {
                    bitmap.recycle();
                }
                return a2;
            } else if ((this.f2233e == 0 && !this.f2235g && this.f2234f == 0) || bitmap == null) {
                return bitmap;
            } else {
                CGSize cGSize = new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight());
                this.f2230b.mo17789a((GPUImageFilter) PhotoAsset.m3008a(this.f2230b.mo17782a(), cGSize, this.f2235g, this.f2233e, this.f2234f, Math.round(cGSize.mo17164f() * this.f2237i * 0.0667f)));
                Bitmap b = this.f2230b.mo17790b(bitmap);
                if (!(b == null || b == bitmap)) {
                    bitmap.recycle();
                }
                if (b != null) {
                    return b;
                }
                return bitmap;
            }
        }

        /* renamed from: a */
        public String mo17274a() {
            return this.f2232d;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public boolean mo17278b() {
            return this.f2233e == 0 && !this.f2235g && !this.f2236h;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public boolean mo17279c() {
            return this.f2236h;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17277a(C1578b bVar) {
            this.f2229a = bVar;
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.utils.PhotoAsset$b */
    private static class C1578b implements Target {

        /* renamed from: a */
        PhotoAsset f2238a;

        /* renamed from: b */
        private C1581d f2239b;

        /* renamed from: c */
        private C1582e f2240c;

        /* renamed from: d */
        private C1580c f2241d;

        /* renamed from: e */
        private final String f2242e;

        /* renamed from: f */
        private final boolean f2243f;

        /* renamed from: g */
        private final CGSize f2244g;

        /* renamed from: h */
        private CGSize f2245h;

        C1578b(C1581d dVar, String str, boolean z) {
            this.f2239b = dVar;
            this.f2240c = null;
            this.f2241d = null;
            this.f2242e = str;
            this.f2243f = z;
            this.f2244g = null;
        }

        C1578b(C1582e eVar, String str, boolean z) {
            this.f2239b = null;
            this.f2240c = eVar;
            this.f2241d = null;
            this.f2242e = str;
            this.f2243f = z;
            this.f2244g = null;
        }

        C1578b(C1580c cVar, String str, CGSize cGSize, boolean z) {
            this.f2239b = null;
            this.f2240c = null;
            this.f2241d = cVar;
            this.f2242e = str;
            this.f2243f = z;
            this.f2244g = cGSize;
        }

        /* renamed from: a */
        public void mo17282a(CGSize cGSize) {
            this.f2245h = cGSize;
        }

        /* renamed from: a */
        public void mo17280a(Bitmap bitmap, Picasso.C1640d dVar) {
            if (this.f2239b != null) {
                if (this.f2238a != null && bitmap != null && this.f2238a.f2211j == null && !this.f2238a.m3013a(bitmap)) {
                    this.f2238a.m3022k();
                }
                this.f2239b.mo16936a(bitmap);
            } else if (this.f2240c != null) {
                this.f2240c.mo17276a(this.f2242e, bitmap);
            } else if (this.f2241d != null) {
                if (this.f2245h == null) {
                    this.f2245h = CGSize.m2875d(this.f2244g, new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight())).mo17151a();
                }
                Log.d("ASSET", Localize.m3098a("%s_%d_%d", this.f2245h, Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight())));
                this.f2241d.mo17275a(this.f2245h, false, bitmap);
            }
            if (this.f2243f && bitmap != null && !bitmap.isRecycled()) {
                new AsyncTask<Object, Void, Void>() {
                    /* access modifiers changed from: protected */
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x0047 A[SYNTHETIC, Splitter:B:28:0x0047] */
                    /* JADX WARNING: Removed duplicated region for block: B:34:0x0051 A[SYNTHETIC, Splitter:B:34:0x0051] */
                    /* renamed from: a */
                    public Void doInBackground(Object... objArr) {
                        FileOutputStream fileOutputStream;
                        FileOutputStream fileOutputStream2;
                        Bitmap bitmap = objArr[0];
                        String str = objArr[1];
                        try {
                            fileOutputStream2 = new FileOutputStream(str);
                            try {
                                if (str.endsWith(".jpg")) {
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream2);
                                } else {
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                                }
                                fileOutputStream2.flush();
                                if (fileOutputStream2 != null) {
                                    try {
                                        fileOutputStream2.close();
                                    } catch (Exception e) {
                                    }
                                }
                            } catch (FileNotFoundException e2) {
                                e = e2;
                                fileOutputStream = fileOutputStream2;
                            } catch (IOException e3) {
                                e = e3;
                                try {
                                    e.printStackTrace();
                                    Directories.m2435a(str);
                                    if (fileOutputStream2 != null) {
                                        try {
                                            fileOutputStream2.close();
                                        } catch (Exception e4) {
                                        }
                                    }
                                    return null;
                                } catch (Throwable th) {
                                    th = th;
                                    if (fileOutputStream2 != null) {
                                        try {
                                            fileOutputStream2.close();
                                        } catch (Exception e5) {
                                        }
                                    }
                                    throw th;
                                }
                            }
                        } catch (FileNotFoundException e6) {
                            e = e6;
                            fileOutputStream = null;
                            try {
                                e.printStackTrace();
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (Exception e7) {
                                    }
                                }
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream2 = fileOutputStream;
                                if (fileOutputStream2 != null) {
                                }
                                throw th;
                            }
                        } catch (IOException e8) {
                            e = e8;
                            fileOutputStream2 = null;
                            e.printStackTrace();
                            Directories.m2435a(str);
                            if (fileOutputStream2 != null) {
                            }
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream2 = null;
                            if (fileOutputStream2 != null) {
                            }
                            throw th;
                        }
                        return null;
                    }
                }.execute(new Object[]{bitmap, this.f2242e});
            }
            PhotoAsset.f2202a.remove(this.f2242e);
        }

        /* renamed from: a */
        public void mo17283a(Exception exc, Drawable drawable) {
            Bitmap bitmap;
            CGSize cGSize;
            Bitmap bitmap2 = null;
            if (drawable == null || !(drawable instanceof BitmapDrawable)) {
                bitmap = null;
            } else {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
            if (this.f2239b != null) {
                this.f2239b.mo16936a(bitmap);
            } else if (this.f2240c != null) {
                this.f2240c.mo17276a(this.f2242e, bitmap);
            } else if (this.f2241d != null) {
                if (bitmap != null) {
                    CGSize cGSize2 = new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight());
                    bitmap2 = BitmapUtils.m3082a(bitmap);
                    cGSize = cGSize2;
                } else {
                    cGSize = null;
                }
                this.f2241d.mo17275a(cGSize, true, bitmap2);
            }
            PhotoAsset.f2202a.remove(this.f2242e);
        }

        /* renamed from: a */
        public void mo17281a(Drawable drawable) {
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.utils.PhotoAsset$c */
    private interface C1580c {
        /* renamed from: a */
        void mo17275a(@Nullable CGSize cGSize, boolean z, @Nullable Bitmap bitmap);
    }

    /* renamed from: com.snaperfect.style.daguerre.utils.PhotoAsset$d */
    public interface C1581d {
        /* renamed from: a */
        void mo16936a(@Nullable Bitmap bitmap);
    }

    /* renamed from: com.snaperfect.style.daguerre.utils.PhotoAsset$e */
    private static abstract class C1582e {

        /* renamed from: a */
        String[] f2247a;

        /* renamed from: b */
        Bitmap[] f2248b = new Bitmap[this.f2247a.length];

        /* renamed from: c */
        C1583f f2249c;

        /* renamed from: a */
        public abstract void mo17276a(@NonNull String str, @Nullable Bitmap bitmap);

        C1582e(@NonNull String[] strArr, C1583f fVar) {
            this.f2247a = strArr;
            this.f2249c = fVar;
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.utils.PhotoAsset$f */
    public interface C1583f {
        /* renamed from: a */
        void mo16938a(Bitmap[] bitmapArr);
    }

    public PhotoAsset(String str, String str2, String str3, String str4) {
        this.f2203b = str;
        this.f2204c = str2;
        this.f2205d = str3;
        this.f2206e = str4;
    }

    public PhotoAsset(Parcel parcel) {
        this.f2203b = parcel.readString();
        this.f2204c = parcel.readString();
        this.f2205d = parcel.readString();
        this.f2206e = parcel.readString();
        this.f2207f = parcel.readFloat();
        this.f2208g = parcel.readFloat();
        this.f2209h = parcel.readFloat();
        this.f2210i = parcel.readInt();
        this.f2211j = (CGRect) parcel.readParcelable(CGRect.class.getClassLoader());
        this.f2212k = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2203b);
        parcel.writeString(this.f2204c);
        parcel.writeString(this.f2205d);
        parcel.writeString(this.f2206e);
        parcel.writeFloat(this.f2207f);
        parcel.writeFloat(this.f2208g);
        parcel.writeFloat(this.f2209h);
        parcel.writeInt(this.f2210i);
        parcel.writeParcelable(this.f2211j, i);
        parcel.writeString(this.f2212k);
    }

    /* renamed from: a */
    public Uri mo17247a(Context context) {
        return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(this.f2204c));
    }

    /* renamed from: a */
    public String mo17248a() {
        return this.f2203b;
    }

    /* renamed from: b */
    public String mo17259b() {
        return this.f2204c;
    }

    /* renamed from: j */
    private CGSize m3021j() {
        return new CGSize(this.f2207f, this.f2208g);
    }

    /* renamed from: a */
    public void mo17256a(CGSize cGSize) {
        this.f2207f = cGSize.f2102a;
        this.f2208g = cGSize.f2103b;
    }

    /* renamed from: c */
    public int mo17261c() {
        return this.f2210i;
    }

    /* renamed from: a */
    public void mo17250a(int i) {
        this.f2210i = i;
    }

    /* renamed from: d */
    public float mo17262d() {
        return this.f2209h;
    }

    /* renamed from: a */
    public void mo17249a(float f) {
        this.f2209h = f;
    }

    /* renamed from: e */
    public CGRect mo17264e() {
        return this.f2211j;
    }

    /* renamed from: a */
    public void mo17255a(CGRect cGRect) {
        this.f2211j = cGRect;
        CGRect a = CGRect.m2857c(this.f2211j, new CGSize(2048.0f)).mo17133a();
        this.f2212k = Localize.m3098a("%d_%d_%d_%d", Integer.valueOf((int) a.f2098a), Integer.valueOf((int) a.f2099b), Integer.valueOf((int) a.f2100c), Integer.valueOf((int) a.f2101d));
    }

    /* renamed from: f */
    public boolean mo17265f() {
        return new File(this.f2204c).exists();
    }

    /* renamed from: g */
    public CGSize mo17266g() {
        if (this.f2211j == null) {
            return m3021j();
        }
        return new CGSize(this.f2211j.f2100c, this.f2211j.f2101d).mo17153a(this.f2207f, this.f2208g);
    }

    /* renamed from: a */
    public int mo17246a(float f, float f2) {
        if (this.f2211j == null) {
            return (int) m3021j().mo17159c(new CGSize(f, f2)).mo17151a().mo17163e();
        }
        CGSize a = new CGSize(this.f2211j.f2100c, this.f2211j.f2101d).mo17153a(this.f2207f, this.f2208g);
        float f3 = CGSize.m2874c(a, new CGSize(f, f2)).f2102a / a.f2102a;
        return Math.round(Math.max(this.f2207f * f3, f3 * this.f2208g));
    }

    /* renamed from: c */
    private String m3018c(int i) {
        return Directories.m2434a("preview_cache", Localize.m3098a("PREVIEW_%s_%d.jpg", this.f2203b, Integer.valueOf(i)));
    }

    /* renamed from: a */
    private String m3005a(int i, String str) {
        return Directories.m2434a("edit_cache", Localize.m3098a("IMG_%s_%s_%d.%s", this.f2203b, str, Integer.valueOf(i), this.f2204c.endsWith(".jpg") ? "jpg" : "png"));
    }

    /* renamed from: a */
    private String m3004a(int i, StyleObj bVar) {
        return Directories.m2434a("style_cache", Localize.m3098a("STY_%s_%s_%d_%d.jpg", this.f2203b, this.f2212k, Integer.valueOf(i), Integer.valueOf(bVar.f2138a)));
    }

    @Nullable
    /* renamed from: b */
    public Bitmap mo17258b(int i) {
        File file = new File(m3005a(i, this.f2212k));
        boolean exists = file.exists();
        if (!exists) {
            file = new File(this.f2204c);
        }
        RequestCreator a = Picasso.m3395b().mo17574a(file);
        if (!exists) {
            a.mo17622d().mo17615a(i, i);
        }
        if (!exists && this.f2211j != null) {
            a.mo17616a((Transformation) new Transformation() {
                /* renamed from: a */
                public Bitmap mo17273a(Bitmap bitmap) {
                    Bitmap a = BitmapUtils.m3084a(bitmap, PhotoAsset.this.f2211j, true);
                    if (a != bitmap) {
                        bitmap.recycle();
                    }
                    return a;
                }

                /* renamed from: a */
                public String mo17274a() {
                    return PhotoAsset.this.f2212k;
                }
            });
        }
        return a.mo17623e();
    }

    /* renamed from: a */
    public void mo17252a(int i, ImageView imageView) {
        String c = m3018c(i);
        File file = new File(c);
        if (file.exists()) {
            Picasso.m3395b().mo17574a(file).mo17617a(imageView);
            return;
        }
        File file2 = new File(this.f2204c);
        final int hashCode = this.f2204c.hashCode();
        imageView.setImageDrawable(null);
        imageView.setTag(R.id.key_preview_tag, Integer.valueOf(hashCode));
        final WeakReference weakReference = new WeakReference(imageView);
        C1578b bVar = new C1578b((C1581d) new C1581d() {
            /* renamed from: a */
            public void mo16936a(@Nullable Bitmap bitmap) {
                ImageView imageView = (ImageView) weakReference.get();
                if (imageView != null && ((Integer) imageView.getTag(R.id.key_preview_tag)).intValue() == hashCode) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }, c, true);
        f2202a.put(c, bVar);
        Picasso.m3395b().mo17574a(file2).mo17615a(i, i).mo17621c().mo17619a((Target) bVar);
    }

    /* renamed from: a */
    public void mo17251a(int i, final AsyncTask<Bitmap, Void, Float> asyncTask) {
        String c = m3018c(i);
        File file = new File(this.f2204c);
        this.f2204c.hashCode();
        C1578b bVar = new C1578b((C1581d) new C1581d() {
            /* renamed from: a */
            public void mo16936a(@Nullable Bitmap bitmap) {
                if (bitmap != null) {
                    asyncTask.execute(new Bitmap[]{bitmap});
                }
            }
        }, c, false);
        f2202a.put(c, bVar);
        Picasso.m3395b().mo17574a(file).mo17615a(i, i).mo17622d().mo17619a((Target) bVar);
    }

    /* renamed from: a */
    private void m3009a(int i, C1577a aVar, int i2, C1581d dVar, C1580c cVar) {
        File file;
        boolean z;
        String a = m3005a(i, aVar != null ? aVar.mo17274a() : "NULL");
        File file2 = new File(a);
        boolean exists = file2.exists();
        C1578b bVar = null;
        if (dVar != null) {
            if (aVar == null || aVar.mo17278b()) {
                z = true;
            } else {
                z = false;
            }
            C1578b bVar2 = new C1578b(dVar, a, z);
            bVar2.f2238a = this;
            bVar = bVar2;
        } else if (cVar != null) {
            bVar = new C1578b(cVar, a, mo17266g(), false);
        }
        f2202a.put(a, bVar);
        if (!exists) {
            file = new File(this.f2204c);
        } else {
            file = file2;
        }
        RequestCreator a2 = Picasso.m3395b().mo17574a(file);
        if (!exists) {
            a2.mo17622d().mo17615a(i, i);
        }
        if (i2 != 0) {
            a2.mo17614a(i2);
        }
        if (!exists && aVar != null) {
            if (aVar.mo17279c()) {
                aVar.mo17277a(bVar);
            }
            a2.mo17616a((Transformation) aVar);
        }
        a2.mo17619a((Target) bVar);
    }

    /* renamed from: a */
    public void mo17254a(Context context, int i, C1581d dVar) {
        m3009a(i, (C1577a) null, 0, dVar, (C1580c) null);
    }

    /* renamed from: a */
    private void m3010a(Context context, int i, int i2, C1580c cVar) {
        m3009a(i, new C1577a(this.f2211j, this.f2212k, true), i2, (C1581d) null, cVar);
    }

    /* renamed from: a */
    public void mo17257a(GPUImage aVar, int i, int i2, int i3, C1581d dVar) {
        if (i2 == 0 && this.f2211j == null && i3 == 0) {
            m3009a(i, (C1577a) null, 0, dVar, (C1580c) null);
            return;
        }
        m3009a(i, new C1577a(aVar, this.f2211j, this.f2212k, i2, i3, false, this.f2209h), 0, dVar, (C1580c) null);
    }

    /* renamed from: b */
    private static Pair<CGSize, Float> m3015b(CGSize cGSize) {
        float f = (float) (0.009765625d * ((double) cGSize.mo17164f()));
        return new Pair<>(CGSize.m2870a(cGSize, (cGSize.mo17164f() - (4.0f * f)) / cGSize.mo17164f()), Float.valueOf(f));
    }

    /* renamed from: a */
    public static GPUImageFilterGraph m3008a(Context context, CGSize cGSize, boolean z, int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        GraphNode adVar = GraphNode.f2737a;
        if (i2 > 0) {
            arrayList.addAll(m3006a(adVar, ((float) Math.min(i2, 100)) / 100.0f, i3));
            adVar = (GraphNode) arrayList.get(arrayList.size() - 1);
        }
        if (i > 0) {
            GraphNode adVar2 = new GraphNode(FilterObj.f2048a[i].mo17103a(context));
            adVar2.mo17817a(adVar);
            arrayList.add(adVar2);
            adVar = adVar2;
        }
        if (z) {
            Pair<CGSize, Float> b = m3015b(cGSize);
            GraphNode adVar3 = new GraphNode(new GPUImageFragScaleFilter(CGSize.m2871a(cGSize, (CGSize) b.f2264a).mo17156b(2.0f).mo17157b(cGSize).mo17166h(), CGSize.m2873b((CGSize) b.f2264a, cGSize).mo17166h()));
            GraphNode adVar4 = new GraphNode(new GPUImageAlphaMaskGenFilter());
            GraphNode adVar5 = new GraphNode(new GPUImageGaussianBlurFilter(((Float) b.f2265b).floatValue(), true));
            GraphNode adVar6 = new GraphNode(new GPUImageNormalBlendFilter());
            adVar3.mo17817a(adVar);
            adVar4.mo17817a(adVar3);
            adVar5.mo17817a(adVar4);
            adVar6.mo17817a(adVar5);
            adVar6.mo17819b(adVar3);
            arrayList.addAll(Arrays.asList(new GraphNode[]{adVar3, adVar4, adVar5, adVar6}));
        }
        return new GPUImageFilterGraph(arrayList);
    }

    /* renamed from: a */
    private static List<GraphNode> m3006a(GraphNode adVar, float f, int i) {
        ArrayList arrayList = new ArrayList();
        GraphNode adVar2 = new GraphNode(new GPUImageToneCurveFilter(new PointF[]{new PointF(0.0f, 0.0f), new PointF(0.25f, 0.25f), new PointF(0.47058824f, 0.57254905f), new PointF(0.75f, 0.8f), new PointF(1.0f, 1.0f)}));
        GraphNode adVar3 = new GraphNode(new GPUImageDissolveBlendFilter(f * 0.75f));
        GraphNode adVar4 = new GraphNode(new GPUImageHighpassSkinSmoothingCompositingFilter());
        GraphNode adVar5 = new GraphNode(new GPUImageSharpenFilter(0.4f * f));
        List<GraphNode> a = m3007a(adVar, i);
        adVar2.mo17817a(adVar);
        adVar3.mo17817a(adVar);
        adVar3.mo17819b(adVar2);
        adVar4.mo17817a(adVar);
        adVar4.mo17819b(adVar3);
        adVar4.mo17821c(a.get(a.size() - 1));
        adVar5.mo17817a(adVar4);
        arrayList.addAll(a);
        arrayList.add(adVar2);
        arrayList.add(adVar3);
        arrayList.add(adVar4);
        arrayList.add(adVar5);
        return arrayList;
    }

    /* renamed from: a */
    private static List<GraphNode> m3007a(GraphNode adVar, int i) {
        new ArrayList();
        GraphNode adVar2 = new GraphNode(new GPUImageExposureFilter(-1.0f));
        GraphNode adVar3 = new GraphNode(new GPUImageGreenAndBlueChannelOverlayFilter());
        GraphNode adVar4 = new GraphNode(new GPUImageGaussianBlurFilter((float) i));
        GraphNode adVar5 = new GraphNode(new GPUImageHighPassFilter());
        GraphNode adVar6 = new GraphNode(new GPUImageHardLight3TimesBoostFilter());
        adVar2.mo17817a(adVar);
        adVar3.mo17817a(adVar2);
        adVar4.mo17817a(adVar3);
        adVar5.mo17817a(adVar3);
        adVar5.mo17819b(adVar4);
        adVar6.mo17817a(adVar5);
        return Arrays.asList(new GraphNode[]{adVar2, adVar3, adVar4, adVar5, adVar6});
    }

    /* renamed from: b */
    public void mo17260b(GPUImage aVar, int i, int i2, int i3, C1581d dVar) {
        m3009a(i, new C1577a(aVar, this.f2211j, this.f2212k, i2, i3, true, this.f2209h), 0, dVar, (C1580c) null);
    }

    /* renamed from: a */
    public void mo17253a(int i, GPUImage aVar, int i2, int i3, ImageView imageView) {
        final StyleObj bVar = StyleObj.f2134h[i];
        final String a = m3004a(i2, bVar);
        File file = new File(a);
        if (file.exists()) {
            Picasso.m3395b().mo17574a(file).mo17617a(imageView);
            return;
        }
        mo17267h();
        final GPUImage aVar2 = aVar;
        final ImageView imageView2 = imageView;
        final int i4 = i;
        m3010a(aVar.mo17782a(), i2, i3, (C1580c) new C1580c() {
            /* renamed from: a */
            public void mo17275a(@Nullable final CGSize cGSize, final boolean z, @Nullable final Bitmap bitmap) {
                if (bitmap != null && cGSize != null) {
                    PhotoAsset.m3012a(aVar2.mo17782a(), (int) cGSize.mo17163e(), new int[]{0, bVar.mo17200a(true), bVar.mo17202b(true), bVar.f2143f.mo17185g()}, (C1583f) new C1583f() {
                        /* renamed from: a */
                        public void mo16938a(Bitmap[] bitmapArr) {
                            boolean z;
                            bitmapArr[0] = bitmap;
                            ImageView imageView = imageView2;
                            int i = i4;
                            if (!z) {
                                z = true;
                            } else {
                                z = false;
                            }
                            new StyleBitmapTask(imageView, i, z).execute(new Object[]{aVar2, bitmapArr, cGSize, bVar, a});
                        }
                    });
                }
            }
        });
    }

    /* renamed from: h */
    public float mo17267h() {
        if (this.f2207f == 0.0f || this.f2208g == 0.0f) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.f2204c, options);
            this.f2207f = (float) options.outWidth;
            this.f2208g = (float) options.outHeight;
        }
        return Math.max(this.f2207f, this.f2208g);
    }

    /* renamed from: b */
    private static String m3016b(Context context, int i, int i2) {
        String resourceEntryName = context.getResources().getResourceEntryName(i);
        return Directories.m2434a("edit_cache", Localize.m3098a("RES_%s_%d.%s", resourceEntryName, Integer.valueOf(i2), resourceEntryName.endsWith("_bg") ? "jpg" : "png"));
    }

    /* renamed from: a */
    public static void m3011a(Context context, int i, int i2, C1581d dVar) {
        int min = Math.min(i, 1024);
        String b = m3016b(context, i2, min);
        File file = new File(b);
        C1578b bVar = new C1578b(dVar, b, false);
        f2202a.put(b, bVar);
        if (file.exists()) {
            Picasso.m3395b().mo17574a(file).mo17619a((Target) bVar);
        } else {
            Picasso.m3395b().mo17572a(i2).mo17615a(min, min).mo17622d().mo17619a((Target) bVar);
        }
    }

    /* renamed from: a */
    public static Bitmap m3002a(Context context, int i, int i2) {
        File file = new File(m3016b(context, i2, Math.min(i, 1024)));
        if (file.exists()) {
            return Picasso.m3395b().mo17574a(file).mo17623e();
        }
        return Picasso.m3395b().mo17572a(i2).mo17623e();
    }

    /* renamed from: a */
    public static void m3012a(Context context, int i, int[] iArr, C1583f fVar) {
        int min = Math.min(i, 1024);
        String[] strArr = new String[iArr.length];
        boolean z = false;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] != 0) {
                strArr[i2] = m3016b(context, iArr[i2], min);
                z = true;
            }
        }
        C15766 r5 = new C1582e(strArr, fVar) {
            /* renamed from: a */
            public void mo17276a(@NonNull String str, @Nullable Bitmap bitmap) {
                boolean z = false;
                for (int i = 0; i < this.f2247a.length; i++) {
                    if (this.f2247a[i] == str) {
                        this.f2248b[i] = bitmap;
                        this.f2247a[i] = null;
                    }
                }
                String[] strArr = this.f2247a;
                int length = strArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = true;
                        break;
                    } else if (strArr[i2] != null) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    this.f2249c.mo16938a(this.f2248b);
                }
            }
        };
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (strArr[i3] != null) {
                C1578b bVar = new C1578b((C1582e) r5, strArr[i3], false);
                f2202a.put(strArr[i3], bVar);
                File file = new File(strArr[i3]);
                if (file.exists()) {
                    Picasso.m3395b().mo17574a(file).mo17619a((Target) bVar);
                } else {
                    Picasso.m3395b().mo17572a(iArr[i3]).mo17615a(min, min).mo17622d().mo17619a((Target) bVar);
                }
            }
        }
        if (!z) {
            fVar.mo16938a(new Bitmap[iArr.length]);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m3013a(Bitmap bitmap) {
        return (bitmap.getWidth() > bitmap.getHeight() && this.f2207f > this.f2208g) || (bitmap.getWidth() <= bitmap.getHeight() && this.f2207f <= this.f2208g);
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public void m3022k() {
        float f = this.f2207f;
        this.f2207f = this.f2208g;
        this.f2208g = f;
    }
}
