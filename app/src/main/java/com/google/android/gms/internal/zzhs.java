package com.google.android.gms.internal;

import java.util.Map;

@zzmb
public final class zzhs implements zzhx {
    private final zzht zzHc;

    public zzhs(zzht zzht) {
        this.zzHc = zzht;
    }

    public void zza(zzqp zzqp, Map<String, String> map) {
        String str = map.get("name");
        if (str == null) {
            zzpe.zzbe("App event with no name parameter.");
        } else {
            this.zzHc.onAppEvent(str, map.get("info"));
        }
    }
}
