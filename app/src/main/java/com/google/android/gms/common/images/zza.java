package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.p004v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzabt;
import com.google.android.gms.internal.zzabu;
import com.google.android.gms.internal.zzabv;
import java.lang.ref.WeakReference;

public abstract class zza {
    final C1685zza zzaCV;
    protected int zzaCW = 0;
    protected int zzaCX = 0;
    protected boolean zzaCY = false;
    private boolean zzaCZ = true;
    private boolean zzaDa = false;
    private boolean zzaDb = true;

    /* renamed from: com.google.android.gms.common.images.zza$zza  reason: collision with other inner class name */
    static final class C1685zza {
        public final Uri uri;

        public C1685zza(Uri uri2) {
            this.uri = uri2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C1685zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return zzaa.equal(((C1685zza) obj).uri, this.uri);
        }

        public int hashCode() {
            return zzaa.hashCode(this.uri);
        }
    }

    public static final class zzb extends zza {
        private WeakReference<ImageView> zzaDc;

        public zzb(ImageView imageView, int i) {
            super(null, i);
            com.google.android.gms.common.internal.zzc.zzt(imageView);
            this.zzaDc = new WeakReference<>(imageView);
        }

        public zzb(ImageView imageView, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzt(imageView);
            this.zzaDc = new WeakReference<>(imageView);
        }

        private void zza(ImageView imageView, Drawable drawable, boolean z, boolean z2, boolean z3) {
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof zzabu)) {
                int zzwO = ((zzabu) imageView).zzwO();
                if (this.zzaCX != 0 && zzwO == this.zzaCX) {
                    return;
                }
            }
            boolean zzc = zzc(z, z2);
            Drawable zza = zzc ? zza(imageView.getDrawable(), drawable) : drawable;
            imageView.setImageDrawable(zza);
            if (imageView instanceof zzabu) {
                zzabu zzabu = (zzabu) imageView;
                zzabu.zzr(z3 ? this.zzaCV.uri : null);
                zzabu.zzcK(z4 ? this.zzaCX : 0);
            }
            if (zzc) {
                ((zzabt) zza).startTransition(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.zzaDc.get();
            ImageView imageView2 = (ImageView) ((zzb) obj).zzaDc.get();
            return (imageView2 == null || imageView == null || !zzaa.equal(imageView2, imageView)) ? false : true;
        }

        public int hashCode() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = (ImageView) this.zzaDc.get();
            if (imageView != null) {
                zza(imageView, drawable, z, z2, z3);
            }
        }
    }

    public static final class zzc extends zza {
        private WeakReference<ImageManager.OnImageLoadedListener> zzaDd;

        public zzc(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzt(onImageLoadedListener);
            this.zzaDd = new WeakReference<>(onImageLoadedListener);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc zzc = (zzc) obj;
            ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zzaDd.get();
            ImageManager.OnImageLoadedListener onImageLoadedListener2 = (ImageManager.OnImageLoadedListener) zzc.zzaDd.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && zzaa.equal(onImageLoadedListener2, onImageLoadedListener) && zzaa.equal(zzc.zzaCV, this.zzaCV);
        }

        public int hashCode() {
            return zzaa.hashCode(this.zzaCV);
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            if (!z2) {
                ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zzaDd.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.zzaCV.uri, drawable, z3);
                }
            }
        }
    }

    public zza(Uri uri, int i) {
        this.zzaCV = new C1685zza(uri);
        this.zzaCX = i;
    }

    private Drawable zza(Context context, zzabv zzabv, int i) {
        return context.getResources().getDrawable(i);
    }

    /* access modifiers changed from: protected */
    public zzabt zza(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof zzabt) {
            drawable = ((zzabt) drawable).zzwM();
        }
        return new zzabt(drawable, drawable2);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, Bitmap bitmap, boolean z) {
        com.google.android.gms.common.internal.zzc.zzt(bitmap);
        zza(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzabv zzabv) {
        if (this.zzaDb) {
            zza(null, false, true, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzabv zzabv, boolean z) {
        Drawable drawable = null;
        if (this.zzaCX != 0) {
            drawable = zza(context, zzabv, this.zzaCX);
        }
        zza(drawable, z, false, false);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: protected */
    public boolean zzc(boolean z, boolean z2) {
        return this.zzaCZ && !z2 && !z;
    }

    public void zzcI(int i) {
        this.zzaCX = i;
    }
}
