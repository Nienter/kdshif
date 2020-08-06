package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.zzc;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzcl;
import com.google.android.gms.internal.zzcm;

public final class zzck extends zzg<zzcm> {
    private static final zzck zzrW = new zzck();

    private zzck() {
        super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
    }

    public static zzcl zzb(String str, Context context, boolean z) {
        if (zzc.zzuz().isGooglePlayServicesAvailable(context) == 0) {
            zzcl zzc = zzrW.zzc(str, context, z);
            if (zzc != null) {
                return zzc;
            }
        }
        return new zzcj(str, context, z);
    }

    private zzcl zzc(String str, Context context, boolean z) {
        IBinder zzb;
        zzd zzA = zze.zzA(context);
        if (z) {
            try {
                zzb = ((zzcm) zzaT(context)).zza(str, zzA);
            } catch (RemoteException | zzg.zza e) {
                return null;
            }
        } else {
            zzb = ((zzcm) zzaT(context)).zzb(str, zzA);
        }
        return zzcl.zza.zzd(zzb);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzb */
    public zzcm zzc(IBinder iBinder) {
        return zzcm.zza.zze(iBinder);
    }
}
