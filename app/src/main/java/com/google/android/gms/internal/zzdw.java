package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.internal.zzeo;

@zzmb
public final class zzdw extends zzg<zzeo> {
    public zzdw() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public zzen zza(Context context, String str, zzjs zzjs) {
        try {
            return zzen.zza.zzo(((zzeo) zzaT(context)).zza(zze.zzA(context), str, zzjs, 10084000));
        } catch (RemoteException e) {
            zzpy.zzc("Could not create remote builder for AdLoader.", e);
        } catch (zzg.zza e2) {
            zzpy.zzc("Could not create remote builder for AdLoader.", e2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzj */
    public zzeo zzc(IBinder iBinder) {
        return zzeo.zza.zzp(iBinder);
    }
}
