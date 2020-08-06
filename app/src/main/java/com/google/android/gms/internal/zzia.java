package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import java.util.Map;

@zzmb
public class zzia implements zzhx {
    private final zzib zzHL;

    public zzia(zzib zzib) {
        this.zzHL = zzib;
    }

    public void zza(zzqp zzqp, Map<String, String> map) {
        float f;
        boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("transparentBackground"));
        boolean equals2 = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("blur"));
        try {
            if (map.get("blurRadius") != null) {
                f = Float.parseFloat(map.get("blurRadius"));
                this.zzHL.zzg(equals);
                this.zzHL.zza(equals2, f);
            }
        } catch (NumberFormatException e) {
            zzpe.zzb("Fail to parse float", e);
        }
        f = 0.0f;
        this.zzHL.zzg(equals);
        this.zzHL.zza(equals2, f);
    }
}
