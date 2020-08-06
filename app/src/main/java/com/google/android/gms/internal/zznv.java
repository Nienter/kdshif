package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zznr;
import com.google.android.gms.internal.zzns;

@zzmb
public class zznv extends zzg<zzns> {
    public zznv() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzak */
    public zzns zzc(IBinder iBinder) {
        return zzns.zza.zzai(iBinder);
    }

    public zznr zzb(Context context, zzjs zzjs) {
        try {
            return zznr.zza.zzah(((zzns) zzaT(context)).zza(zze.zzA(context), zzjs, 10084000));
        } catch (RemoteException | zzg.zza e) {
            zzpy.zzc("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }
}
