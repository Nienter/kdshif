package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzc;
import java.util.Map;

@zzmb
public class zzim implements zzhx {
    public void zza(zzqp zzqp, Map<String, String> map) {
        int i;
        zzik zzdg = zzv.zzdg();
        if (!map.containsKey("abort")) {
            String str = map.get("src");
            if (str == null) {
                zzpe.zzbe("Precache video action is missing the src parameter.");
                return;
            }
            try {
                i = Integer.parseInt(map.get("player"));
            } catch (NumberFormatException e) {
                i = 0;
            }
            String str2 = map.containsKey("mimetype") ? map.get("mimetype") : "";
            if (zzdg.zzf(zzqp)) {
                zzpe.zzbe("Precache task already running.");
                return;
            }
            zzc.zzt(zzqp.zzbz());
            new zzij(zzqp, zzqp.zzbz().zzsM.zza(zzqp, i, str2), str).zziw();
        } else if (!zzdg.zze(zzqp)) {
            zzpe.zzbe("Precache abort but no preload task running.");
        }
    }
}
