package com.google.android.gms.common.images;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.p001v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.images.zza;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzabv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    /* access modifiers changed from: private */
    public static final Object zzaCG = new Object();
    /* access modifiers changed from: private */
    public static HashSet<Uri> zzaCH = new HashSet<>();
    private static ImageManager zzaCI;
    private static ImageManager zzaCJ;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final ExecutorService zzaCK = Executors.newFixedThreadPool(4);
    /* access modifiers changed from: private */
    public final zzb zzaCL;
    /* access modifiers changed from: private */
    public final zzabv zzaCM;
    /* access modifiers changed from: private */
    public final Map<zza, ImageReceiver> zzaCN;
    /* access modifiers changed from: private */
    public final Map<Uri, ImageReceiver> zzaCO;
    /* access modifiers changed from: private */
    public final Map<Uri, Long> zzaCP;

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        /* access modifiers changed from: private */
        public final ArrayList<zza> zzaCQ = new ArrayList<>();

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.zzaCK.execute(new zzc(this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public void zzb(zza zza) {
            com.google.android.gms.common.internal.zzc.zzdn("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zzaCQ.add(zza);
        }

        public void zzc(zza zza) {
            com.google.android.gms.common.internal.zzc.zzdn("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zzaCQ.remove(zza);
        }

        public void zzwL() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    @TargetApi(11)
    private static final class zza {
        static int zza(ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }

    private static final class zzb extends LruCache<zza.C1685zza, Bitmap> {
        public zzb(Context context) {
            super(zzaz(context));
        }

        @TargetApi(11)
        private static int zzaz(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return (int) (((float) (((!((context.getApplicationInfo().flags & 1048576) != 0) || !zzs.zzyx()) ? activityManager.getMemoryClass() : zza.zza(activityManager)) * 1048576)) * 0.33f);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public int sizeOf(zza.C1685zza zza, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public void entryRemoved(boolean z, zza.C1685zza zza, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zza, bitmap, bitmap2);
        }
    }

    private final class zzc implements Runnable {
        private final Uri mUri;
        private final ParcelFileDescriptor zzaCS;

        public zzc(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.zzaCS = parcelFileDescriptor;
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzdo("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.zzaCS != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.zzaCS.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.mUri);
                    Log.e("ImageManager", new StringBuilder(String.valueOf(valueOf).length() + 34).append("OOM while loading bitmap for uri: ").append(valueOf).toString(), e);
                    z = true;
                }
                try {
                    this.zzaCS.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(new zzf(this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String valueOf2 = String.valueOf(this.mUri);
                Log.w("ImageManager", new StringBuilder(String.valueOf(valueOf2).length() + 32).append("Latch interrupted while posting ").append(valueOf2).toString());
            }
        }
    }

    private final class zzd implements Runnable {
        private final zza zzaCT;

        public zzd(zza zza) {
            this.zzaCT = zza;
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzdn("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzaCN.get(this.zzaCT);
            if (imageReceiver != null) {
                ImageManager.this.zzaCN.remove(this.zzaCT);
                imageReceiver.zzc(this.zzaCT);
            }
            zza.C1685zza zza = this.zzaCT.zzaCV;
            if (zza.uri == null) {
                this.zzaCT.zza(ImageManager.this.mContext, ImageManager.this.zzaCM, true);
                return;
            }
            Bitmap zza2 = ImageManager.this.zza(zza);
            if (zza2 != null) {
                this.zzaCT.zza(ImageManager.this.mContext, zza2, true);
                return;
            }
            Long l = (Long) ImageManager.this.zzaCP.get(zza.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zzaCT.zza(ImageManager.this.mContext, ImageManager.this.zzaCM, true);
                    return;
                }
                ImageManager.this.zzaCP.remove(zza.uri);
            }
            this.zzaCT.zza(ImageManager.this.mContext, ImageManager.this.zzaCM);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zzaCO.get(zza.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(zza.uri);
                ImageManager.this.zzaCO.put(zza.uri, imageReceiver2);
            }
            imageReceiver2.zzb(this.zzaCT);
            if (!(this.zzaCT instanceof zza.zzc)) {
                ImageManager.this.zzaCN.put(this.zzaCT, imageReceiver2);
            }
            synchronized (ImageManager.zzaCG) {
                if (!ImageManager.zzaCH.contains(zza.uri)) {
                    ImageManager.zzaCH.add(zza.uri);
                    imageReceiver2.zzwL();
                }
            }
        }
    }

    @TargetApi(14)
    private static final class zze implements ComponentCallbacks2 {
        private final zzb zzaCL;

        public zze(zzb zzb) {
            this.zzaCL = zzb;
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
            this.zzaCL.evictAll();
        }

        public void onTrimMemory(int i) {
            if (i >= 60) {
                this.zzaCL.evictAll();
            } else if (i >= 20) {
                this.zzaCL.trimToSize(this.zzaCL.size() / 2);
            }
        }
    }

    private final class zzf implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private boolean zzaCU;
        private final CountDownLatch zzth;

        public zzf(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zzaCU = z;
            this.zzth = countDownLatch;
        }

        private void zza(ImageReceiver imageReceiver, boolean z) {
            ArrayList zza = imageReceiver.zzaCQ;
            int size = zza.size();
            for (int i = 0; i < size; i++) {
                zza zza2 = (zza) zza.get(i);
                if (z) {
                    zza2.zza(ImageManager.this.mContext, this.mBitmap, false);
                } else {
                    ImageManager.this.zzaCP.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    zza2.zza(ImageManager.this.mContext, ImageManager.this.zzaCM, false);
                }
                if (!(zza2 instanceof zza.zzc)) {
                    ImageManager.this.zzaCN.remove(zza2);
                }
            }
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzdn("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.zzaCL != null) {
                if (this.zzaCU) {
                    ImageManager.this.zzaCL.evictAll();
                    System.gc();
                    this.zzaCU = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (z) {
                    ImageManager.this.zzaCL.put(new zza.C1685zza(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzaCO.remove(this.mUri);
            if (imageReceiver != null) {
                zza(imageReceiver, z);
            }
            this.zzth.countDown();
            synchronized (ImageManager.zzaCG) {
                ImageManager.zzaCH.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
        if (z) {
            this.zzaCL = new zzb(this.mContext);
            if (zzs.zzyA()) {
                zzwJ();
            }
        } else {
            this.zzaCL = null;
        }
        this.zzaCM = new zzabv();
        this.zzaCN = new HashMap();
        this.zzaCO = new HashMap();
        this.zzaCP = new HashMap();
    }

    public static ImageManager create(Context context) {
        return zzg(context, false);
    }

    /* access modifiers changed from: private */
    public Bitmap zza(zza.C1685zza zza2) {
        if (this.zzaCL == null) {
            return null;
        }
        return (Bitmap) this.zzaCL.get(zza2);
    }

    public static ImageManager zzg(Context context, boolean z) {
        if (z) {
            if (zzaCJ == null) {
                zzaCJ = new ImageManager(context, true);
            }
            return zzaCJ;
        }
        if (zzaCI == null) {
            zzaCI = new ImageManager(context, false);
        }
        return zzaCI;
    }

    @TargetApi(14)
    private void zzwJ() {
        this.mContext.registerComponentCallbacks(new zze(this.zzaCL));
    }

    public void loadImage(ImageView imageView, int i) {
        zza((zza) new zza.zzb(imageView, i));
    }

    public void loadImage(ImageView imageView, Uri uri) {
        zza((zza) new zza.zzb(imageView, uri));
    }

    public void loadImage(ImageView imageView, Uri uri, int i) {
        zza.zzb zzb2 = new zza.zzb(imageView, uri);
        zzb2.zzcI(i);
        zza((zza) zzb2);
    }

    public void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zza((zza) new zza.zzc(onImageLoadedListener, uri));
    }

    public void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zza.zzc zzc2 = new zza.zzc(onImageLoadedListener, uri);
        zzc2.zzcI(i);
        zza((zza) zzc2);
    }

    public void zza(zza zza2) {
        com.google.android.gms.common.internal.zzc.zzdn("ImageManager.loadImage() must be called in the main thread");
        new zzd(zza2).run();
    }
}
