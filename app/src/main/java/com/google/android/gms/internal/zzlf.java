package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zzlb;

@zzmb
public final class zzlf extends zzg<zzlb> {
    public zzlf() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzac */
    public zzlb zzc(IBinder iBinder) {
        return zzlb.zza.zzZ(iBinder);
    }

    public zzla zzg(Activity activity) {
        try {
            return zzla.zza.zzY(((zzlb) zzaT(activity)).zzp(zze.zzA(activity)));
        } catch (RemoteException e) {
            zzpy.zzc("Could not create remote InAppPurchaseManager.", e);
            return null;
        } catch (zzg.zza e2) {
            zzpy.zzc("Could not create remote InAppPurchaseManager.", e2);
            return null;
        }
    }
}
