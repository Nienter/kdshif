package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.common.util.zzf;
import java.util.Map;

@zzmb
public class zzid implements zzhx {
    static final Map<String, Integer> zzHP = zzf.zza("resize", 1, "playVideo", 2, "storePicture", 3, "createCalendarEvent", 4, "setOrientationProperties", 5, "closeResizedAd", 6);
    private final zze zzHN;
    private final zzkj zzHO;

    public zzid(zze zze, zzkj zzkj) {
        this.zzHN = zze;
        this.zzHO = zzkj;
    }

    public void zza(zzqp zzqp, Map<String, String> map) {
        int intValue = zzHP.get(map.get("a")).intValue();
        if (intValue == 5 || this.zzHN == null || this.zzHN.zzcb()) {
            switch (intValue) {
                case 1:
                    this.zzHO.execute(map);
                    return;
                case 3:
                    new zzkl(zzqp, map).execute();
                    return;
                case 4:
                    new zzki(zzqp, map).execute();
                    return;
                case 5:
                    new zzkk(zzqp, map).execute();
                    return;
                case 6:
                    this.zzHO.zzs(true);
                    return;
                default:
                    zzpe.zzbd("Unknown MRAID command called.");
                    return;
            }
        } else {
            this.zzHN.zzx(null);
        }
    }
}
