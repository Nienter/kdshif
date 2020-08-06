package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzeu;
import com.google.android.gms.internal.zzev;

@zzmb
public class zzfd extends zzg<zzev> {
    public zzfd() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }

    public zzeu zzl(Context context) {
        try {
            return zzeu.zza.zzu(((zzev) zzaT(context)).zza(zze.zzA(context), 10084000));
        } catch (RemoteException e) {
            zzpy.zzc("Could not get remote MobileAdsSettingManager.", e);
            return null;
        } catch (zzg.zza e2) {
            zzpy.zzc("Could not get remote MobileAdsSettingManager.", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzy */
    public zzev zzc(IBinder iBinder) {
        return zzev.zza.zzv(iBinder);
    }
}
