package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgz;

@zzmb
public class zzgo extends zzgz.zza {
    private final Uri mUri;
    private final Drawable zzFQ;
    private final double zzFR;

    public zzgo(Drawable drawable, Uri uri, double d) {
        this.zzFQ = drawable;
        this.mUri = uri;
        this.zzFR = d;
    }

    public double getScale() {
        return this.zzFR;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public zzd zzfK() {
        return zze.zzA(this.zzFQ);
    }
}
