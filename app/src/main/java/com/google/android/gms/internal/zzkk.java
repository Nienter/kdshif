package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzv;
import java.util.Map;

@zzmb
public class zzkk {
    private final zzqp zzGt;
    private final boolean zzLE;
    private final String zzLF;

    public zzkk(zzqp zzqp, Map<String, String> map) {
        this.zzGt = zzqp;
        this.zzLF = map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzLE = Boolean.parseBoolean(map.get("allowOrientationChange"));
        } else {
            this.zzLE = true;
        }
    }

    public void execute() {
        if (this.zzGt == null) {
            zzpe.zzbe("AdWebView is null");
        } else {
            this.zzGt.setRequestedOrientation("portrait".equalsIgnoreCase(this.zzLF) ? zzv.zzcL().zzkq() : "landscape".equalsIgnoreCase(this.zzLF) ? zzv.zzcL().zzkp() : this.zzLE ? -1 : zzv.zzcL().zzkr());
        }
    }
}
