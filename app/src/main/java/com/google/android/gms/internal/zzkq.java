package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzks;

@zzmb
public final class zzkq extends zzg<zzks> {
    public zzkq() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzS */
    public zzks zzc(IBinder iBinder) {
        return zzks.zza.zzU(iBinder);
    }

    public zzkr zzf(Activity activity) {
        try {
            return zzkr.zza.zzT(((zzks) zzaT(activity)).zzo(zze.zzA(activity)));
        } catch (RemoteException e) {
            zzpy.zzc("Could not create remote AdOverlay.", e);
            return null;
        } catch (zzg.zza e2) {
            zzpy.zzc("Could not create remote AdOverlay.", e2);
            return null;
        }
    }
}
