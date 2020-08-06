package com.google.android.gms.internal;

import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzqi;
import java.util.Map;
import org.json.JSONObject;

@zzmb
public class zzct implements zzcu {
    private final zzhx zzwA = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            if (zzct.this.zzwx.zzb(map)) {
                zzct.this.zzwx.zza((zzcu) zzct.this, map);
            }
        }
    };
    private final zzhx zzwB = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            if (zzct.this.zzwx.zzb(map)) {
                zzct.this.zzwx.zzc(map);
            }
        }
    };
    private zzja.zzc zzwD;
    /* access modifiers changed from: private */
    public boolean zzwE;
    /* access modifiers changed from: private */
    public final zzcq zzwx;
    private final zzhx zzwz = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            if (zzct.this.zzwx.zzb(map)) {
                zzct.this.zzwx.zzb(zzqp, map);
            }
        }
    };

    public zzct(zzcq zzcq, zzja zzja) {
        this.zzwx = zzcq;
        this.zzwD = zzja.zzgv();
        this.zzwD.zza(new zzqi.zzc<zzjb>() {
            /* renamed from: zzb */
            public void zzd(zzjb zzjb) {
                boolean unused = zzct.this.zzwE = true;
                zzct.this.zzc(zzjb);
            }
        }, new zzqi.zza() {
            public void run() {
                zzct.this.zzwx.zzb((zzcu) zzct.this);
            }
        });
        String valueOf = String.valueOf(this.zzwx.zzdN().zzdy());
        zzpe.zzbc(valueOf.length() != 0 ? "Core JS tracking ad unit: ".concat(valueOf) : new String("Core JS tracking ad unit: "));
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzjb zzjb) {
        zzjb.zza("/updateActiveView", this.zzwz);
        zzjb.zza("/untrackActiveViewUnit", this.zzwA);
        zzjb.zza("/visibilityChanged", this.zzwB);
    }

    public void zzc(final JSONObject jSONObject, boolean z) {
        this.zzwD.zza(new zzqi.zzc<zzjb>(this) {
            /* renamed from: zzb */
            public void zzd(zzjb zzjb) {
                zzjb.zza("AFMA_updateActiveView", jSONObject);
            }
        }, new zzqi.zzb());
    }

    /* access modifiers changed from: package-private */
    public void zzd(zzjb zzjb) {
        zzjb.zzb("/visibilityChanged", this.zzwB);
        zzjb.zzb("/untrackActiveViewUnit", this.zzwA);
        zzjb.zzb("/updateActiveView", this.zzwz);
    }

    public boolean zzdR() {
        return this.zzwE;
    }

    public void zzdS() {
        this.zzwD.zza(new zzqi.zzc<zzjb>() {
            /* renamed from: zzb */
            public void zzd(zzjb zzjb) {
                zzct.this.zzd(zzjb);
            }
        }, new zzqi.zzb());
        this.zzwD.release();
    }
}
