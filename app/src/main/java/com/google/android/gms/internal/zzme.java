package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzmf;

@zzmb
public final class zzme {

    public interface zza {
        void zzb(zzmk zzmk);
    }

    interface zzb {
        boolean zza(zzqa zzqa);
    }

    public static zzpk zza(final Context context, zzqa zzqa, zzqi<zzmh> zzqi, zza zza2) {
        return zza(context, zzqa, zzqi, zza2, new zzb() {
            public boolean zza(zzqa zzqa) {
                return zzqa.zzYd || (zzi.zzaK(context) && !zzfx.zzBF.get().booleanValue());
            }
        });
    }

    static zzpk zza(Context context, zzqa zzqa, zzqi<zzmh> zzqi, zza zza2, zzb zzb2) {
        return zzb2.zza(zzqa) ? zza(context, zzqi, zza2) : zzb(context, zzqa, zzqi, zza2);
    }

    private static zzpk zza(Context context, zzqi<zzmh> zzqi, zza zza2) {
        zzpe.zzbc("Fetching ad response from local ad request service.");
        zzmf.zza zza3 = new zzmf.zza(context, zzqi, zza2);
        zza3.zziw();
        return zza3;
    }

    private static zzpk zzb(Context context, zzqa zzqa, zzqi<zzmh> zzqi, zza zza2) {
        zzpe.zzbc("Fetching ad response from remote ad request service.");
        if (zzeh.zzeO().zzP(context)) {
            return new zzmf.zzb(context, zzqa, zzqi, zza2);
        }
        zzpe.zzbe("Failed to connect to remote ad request service.");
        return null;
    }
}
