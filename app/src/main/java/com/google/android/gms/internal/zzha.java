package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zzmb
public class zzha extends NativeAd.Image {
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzFR;
    private final zzgz zzGR;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: android.graphics.drawable.Drawable} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: Multi-variable type inference failed */
    public zzha(zzgz zzgz) {
        Drawable drawable;
        Uri uri = null;
        this.zzGR = zzgz;
        try {
            zzd zzfK = this.zzGR.zzfK();
            if (zzfK != null) {
                drawable = (Drawable) zze.zzE(zzfK);
                this.mDrawable = drawable;
                uri = this.zzGR.getUri();
                this.mUri = uri;
                double d = 1.0d;
                d = this.zzGR.getScale();
                this.zzFR = d;
            }
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get drawable.", e);
        }
        drawable = uri;
        this.mDrawable = drawable;
        try {
            uri = this.zzGR.getUri();
        } catch (RemoteException e2) {
            zzpy.zzb("Failed to get uri.", e2);
        }
        this.mUri = uri;
        double d2 = 1.0d;
        try {
            d2 = this.zzGR.getScale();
        } catch (RemoteException e3) {
            zzpy.zzb("Failed to get scale.", e3);
        }
        this.zzFR = d2;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public double getScale() {
        return this.zzFR;
    }

    public Uri getUri() {
        return this.mUri;
    }
}
