package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzcl;

@zzmb
public final class zzcj extends zzcl.zza {
    private final zzau zzrS;
    private final zzav zzrT;
    private final zzas zzrU;
    private boolean zzrV = false;

    public zzcj(String str, Context context, boolean z) {
        this.zzrS = zzau.zza(str, context, z);
        this.zzrT = new zzav(this.zzrS);
        this.zzrU = z ? null : zzas.zzc(context);
    }

    private zzd zza(zzd zzd, zzd zzd2, boolean z) {
        try {
            Uri uri = (Uri) zze.zzE(zzd);
            Context context = (Context) zze.zzE(zzd2);
            return zze.zzA(z ? this.zzrT.zza(uri, context) : this.zzrT.zzb(uri, context));
        } catch (zzaw e) {
            return null;
        }
    }

    public zzd zza(zzd zzd, zzd zzd2) {
        return zza(zzd, zzd2, true);
    }

    public String zza(zzd zzd, String str) {
        return this.zzrS.zzb((Context) zze.zzE(zzd), str);
    }

    public String zza(zzd zzd, byte[] bArr) {
        Context context = (Context) zze.zzE(zzd);
        String zza = this.zzrS.zza(context, bArr);
        if (this.zzrU == null || !this.zzrV) {
            return zza;
        }
        String zza2 = this.zzrU.zza(zza, this.zzrU.zza(context, bArr));
        this.zzrV = false;
        return zza2;
    }

    public boolean zza(zzd zzd) {
        return this.zzrT.zza((Uri) zze.zzE(zzd));
    }

    public zzd zzb(zzd zzd, zzd zzd2) {
        return zza(zzd, zzd2, false);
    }

    public void zzb(String str, String str2) {
        this.zzrT.zzb(str, str2);
    }

    public boolean zzb(zzd zzd) {
        return this.zzrT.zzc((Uri) zze.zzE(zzd));
    }

    public boolean zzb(String str, boolean z) {
        if (this.zzrU == null) {
            return false;
        }
        this.zzrU.zza(new AdvertisingIdClient.Info(str, z));
        this.zzrV = true;
        return true;
    }

    public String zzbu() {
        return "ms";
    }

    public String zzc(zzd zzd) {
        return zza(zzd, (byte[]) null);
    }

    public void zzd(zzd zzd) {
        this.zzrT.zza((MotionEvent) zze.zzE(zzd));
    }

    public void zzm(String str) {
        this.zzrT.zzm(str);
    }
}
