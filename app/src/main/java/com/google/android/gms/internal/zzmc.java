package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzmh;
import com.google.android.gms.internal.zzov;

@zzmb
public class zzmc {

    public interface zza {
        void zza(zzov.zza zza);
    }

    public zzpd zza(Context context, zzmh.zza zza2, zzav zzav, zza zza3) {
        zzpd zzmu = zza2.zzRd.extras.getBundle("sdk_less_server_data") != null ? new zzmu(context, zza2, zza3) : new zzmd(context, zza2, zzav, zza3);
        zzmu.zziw();
        return zzmu;
    }
}
