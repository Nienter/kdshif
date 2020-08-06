package com.google.android.gms.internal;

import java.util.Map;
import org.json.JSONObject;

@zzmb
public class zzcs implements zzcu {
    private final zzhx zzwA = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            zzcs.this.zzwx.zza((zzcu) zzcs.this, map);
        }
    };
    private final zzhx zzwB = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            zzcs.this.zzwx.zzc(map);
        }
    };
    /* access modifiers changed from: private */
    public final zzcq zzwx;
    private final zzjb zzwy;
    private final zzhx zzwz = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            zzcs.this.zzwx.zzb(zzqp, map);
        }
    };

    public zzcs(zzcq zzcq, zzjb zzjb) {
        this.zzwx = zzcq;
        this.zzwy = zzjb;
        zzc(this.zzwy);
        String valueOf = String.valueOf(this.zzwx.zzdN().zzdy());
        zzpe.zzbc(valueOf.length() != 0 ? "Custom JS tracking ad unit: ".concat(valueOf) : new String("Custom JS tracking ad unit: "));
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzjb zzjb) {
        zzjb.zza("/updateActiveView", this.zzwz);
        zzjb.zza("/untrackActiveViewUnit", this.zzwA);
        zzjb.zza("/visibilityChanged", this.zzwB);
    }

    public void zzc(JSONObject jSONObject, boolean z) {
        if (!z) {
            this.zzwy.zza("AFMA_updateActiveView", jSONObject);
        } else {
            this.zzwx.zzb((zzcu) this);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzd(zzjb zzjb) {
        zzjb.zzb("/visibilityChanged", this.zzwB);
        zzjb.zzb("/untrackActiveViewUnit", this.zzwA);
        zzjb.zzb("/updateActiveView", this.zzwz);
    }

    public boolean zzdR() {
        return true;
    }

    public void zzdS() {
        zzd(this.zzwy);
    }
}
