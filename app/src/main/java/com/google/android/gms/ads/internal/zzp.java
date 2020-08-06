package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzeu;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpl;
import com.google.android.gms.internal.zzqa;

@zzmb
public class zzp extends zzeu.zza {
    private static final Object zztU = new Object();
    @Nullable
    private static zzp zztV;
    private final Context mContext;
    private final Object zzrN = new Object();
    private boolean zztW;
    private boolean zztX;
    private float zztY = -1.0f;
    private zzqa zztZ;

    zzp(Context context, zzqa zzqa) {
        this.mContext = context;
        this.zztZ = zzqa;
        this.zztW = false;
    }

    public static zzp zza(Context context, zzqa zzqa) {
        zzp zzp;
        synchronized (zztU) {
            if (zztV == null) {
                zztV = new zzp(context.getApplicationContext(), zzqa);
            }
            zzp = zztV;
        }
        return zzp;
    }

    @Nullable
    public static zzp zzcn() {
        zzp zzp;
        synchronized (zztU) {
            zzp = zztV;
        }
        return zzp;
    }

    public void initialize() {
        synchronized (zztU) {
            if (this.zztW) {
                zzpe.zzbe("Mobile ads is initialized already.");
                return;
            }
            this.zztW = true;
            zzfx.initialize(this.mContext);
            zzv.zzcN().zzc(this.mContext, this.zztZ);
            zzv.zzcO().initialize(this.mContext);
        }
    }

    public void setAppMuted(boolean z) {
        synchronized (this.zzrN) {
            this.zztX = z;
        }
    }

    public void setAppVolume(float f) {
        synchronized (this.zzrN) {
            this.zztY = f;
        }
    }

    public void zzb(zzd zzd, String str) {
        zzpl zzc = zzc(zzd, str);
        if (zzc == null) {
            zzpe.m2432e("Context is null. Failed to open debug menu.");
        } else {
            zzc.showDialog();
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public zzpl zzc(zzd zzd, String str) {
        if (zzd == null) {
            return null;
        }
        Context context = (Context) zze.zzE(zzd);
        if (context == null) {
            return null;
        }
        zzpl zzpl = new zzpl(context);
        zzpl.setAdUnitId(str);
        return zzpl;
    }

    public float zzco() {
        float f;
        synchronized (this.zzrN) {
            f = this.zztY;
        }
        return f;
    }

    public boolean zzcp() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zztY >= 0.0f;
        }
        return z;
    }

    public boolean zzcq() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zztX;
        }
        return z;
    }

    public void zzy(String str) {
        zzfx.initialize(this.mContext);
        if (!TextUtils.isEmpty(str) && zzfx.zzEp.get().booleanValue()) {
            zzv.zzdf().zza(this.mContext, this.zztZ, true, null, str, null);
        }
    }
}
