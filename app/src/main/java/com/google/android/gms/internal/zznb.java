package com.google.android.gms.internal;

import com.google.android.gms.internal.zzja;
import java.util.Map;
import java.util.concurrent.Future;

@zzmb
public final class zznb {
    /* access modifiers changed from: private */
    public String zzOn;
    /* access modifiers changed from: private */
    public String zzTh;
    /* access modifiers changed from: private */
    public zzqc<zzne> zzTi = new zzqc<>();
    zzja.zzc zzTj;
    public final zzhx zzTk = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            synchronized (zznb.this.zzrN) {
                if (!zznb.this.zzTi.isDone()) {
                    if (zznb.this.zzOn.equals(map.get("request_id"))) {
                        zzne zzne = new zzne(1, map);
                        String valueOf = String.valueOf(zzne.getType());
                        String valueOf2 = String.valueOf(zzne.zzji());
                        zzpe.zzbe(new StringBuilder(String.valueOf(valueOf).length() + 24 + String.valueOf(valueOf2).length()).append("Invalid ").append(valueOf).append(" request error: ").append(valueOf2).toString());
                        zznb.this.zzTi.zzh(zzne);
                    }
                }
            }
        }
    };
    public final zzhx zzTl = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            synchronized (zznb.this.zzrN) {
                if (!zznb.this.zzTi.isDone()) {
                    zzne zzne = new zzne(-2, map);
                    if (zznb.this.zzOn.equals(zzne.getRequestId())) {
                        String url = zzne.getUrl();
                        if (url == null) {
                            zzpe.zzbe("URL missing in loadAdUrl GMSG.");
                            return;
                        }
                        if (url.contains("%40mediation_adapters%40")) {
                            String replaceAll = url.replaceAll("%40mediation_adapters%40", zzpc.zza(zzqp.getContext(), map.get("check_adapters"), zznb.this.zzTh));
                            zzne.setUrl(replaceAll);
                            String valueOf = String.valueOf(replaceAll);
                            zzpe.m2431v(valueOf.length() != 0 ? "Ad request URL modified to ".concat(valueOf) : new String("Ad request URL modified to "));
                        }
                        zznb.this.zzTi.zzh(zzne);
                    }
                }
            }
        }
    };
    public final zzhx zzTm = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            synchronized (zznb.this.zzrN) {
                if (!zznb.this.zzTi.isDone()) {
                    zzne zzne = new zzne(-2, map);
                    if (zznb.this.zzOn.equals(zzne.getRequestId())) {
                        zznb.this.zzTi.zzh(zzne);
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();

    public zznb(String str, String str2) {
        this.zzTh = str2;
        this.zzOn = str;
    }

    public void zzb(zzja.zzc zzc) {
        this.zzTj = zzc;
    }

    public zzja.zzc zzjg() {
        return this.zzTj;
    }

    public Future<zzne> zzjh() {
        return this.zzTi;
    }
}
