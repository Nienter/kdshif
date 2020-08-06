package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzeq;

@zzmb
public class zzdx extends zzg<zzeq> {
    public zzdx() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public zzep zza(Context context, zzec zzec, String str, zzjs zzjs, int i) {
        try {
            return zzep.zza.zzq(((zzeq) zzaT(context)).zza(zze.zzA(context), zzec, str, zzjs, 10084000, i));
        } catch (RemoteException | zzg.zza e) {
            zzpy.zza("Could not create remote AdManager.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzk */
    public zzeq zzc(IBinder iBinder) {
        return zzeq.zza.zzr(iBinder);
    }
}
