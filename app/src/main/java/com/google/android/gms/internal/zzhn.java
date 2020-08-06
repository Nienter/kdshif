package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzhc;

@zzmb
public class zzhn extends zzg<zzhc> {
    public zzhn() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzL */
    public zzhc zzc(IBinder iBinder) {
        return zzhc.zza.zzD(iBinder);
    }

    public zzhb zzb(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        try {
            return zzhb.zza.zzC(((zzhc) zzaT(context)).zza(zze.zzA(context), zze.zzA(frameLayout), zze.zzA(frameLayout2), 10084000));
        } catch (RemoteException | zzg.zza e) {
            zzpy.zzc("Could not create remote NativeAdViewDelegate.", e);
            return null;
        }
    }
}
