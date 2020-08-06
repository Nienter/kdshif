package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.internal.zzcq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@zzmb
public class zzcp implements zzcr {
    private final Object zzrN = new Object();
    private final zzqa zztr;
    private final WeakHashMap<zzov, zzcq> zzvX = new WeakHashMap<>();
    private final ArrayList<zzcq> zzvY = new ArrayList<>();
    private final Context zzvZ;
    private final zzja zzwa;

    public zzcp(Context context, zzqa zzqa, zzja zzja) {
        this.zzvZ = context.getApplicationContext();
        this.zztr = zzqa;
        this.zzwa = zzja;
    }

    public void zza(zzcq zzcq) {
        synchronized (this.zzrN) {
            if (!zzcq.zzdF()) {
                this.zzvY.remove(zzcq);
                Iterator<Map.Entry<zzov, zzcq>> it = this.zzvX.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().getValue() == zzcq) {
                        it.remove();
                    }
                }
            }
        }
    }

    public void zza(zzec zzec, zzov zzov) {
        zza(zzec, zzov, zzov.zzMZ.getView());
    }

    public void zza(zzec zzec, zzov zzov, View view) {
        zza(zzec, zzov, (zzcx) new zzcq.zzd(view, zzov), (zzjb) null);
    }

    public void zza(zzec zzec, zzov zzov, View view, zzjb zzjb) {
        zza(zzec, zzov, (zzcx) new zzcq.zzd(view, zzov), zzjb);
    }

    public void zza(zzec zzec, zzov zzov, zzcx zzcx, @Nullable zzjb zzjb) {
        zzcq zzcq;
        synchronized (this.zzrN) {
            if (zzi(zzov)) {
                zzcq = this.zzvX.get(zzov);
            } else {
                zzcq = new zzcq(this.zzvZ, zzec, zzov, this.zztr, zzcx);
                zzcq.zza((zzcr) this);
                this.zzvX.put(zzov, zzcq);
                this.zzvY.add(zzcq);
            }
            if (zzjb != null) {
                zzcq.zza((zzcu) new zzcs(zzcq, zzjb));
            } else {
                zzcq.zza((zzcu) new zzct(zzcq, this.zzwa));
            }
        }
    }

    public void zza(zzec zzec, zzov zzov, zzgu zzgu) {
        zza(zzec, zzov, (zzcx) new zzcq.zza(zzgu), (zzjb) null);
    }

    public boolean zzi(zzov zzov) {
        boolean z;
        synchronized (this.zzrN) {
            zzcq zzcq = this.zzvX.get(zzov);
            z = zzcq != null && zzcq.zzdF();
        }
        return z;
    }

    public void zzj(zzov zzov) {
        synchronized (this.zzrN) {
            zzcq zzcq = this.zzvX.get(zzov);
            if (zzcq != null) {
                zzcq.zzdD();
            }
        }
    }

    public void zzk(zzov zzov) {
        synchronized (this.zzrN) {
            zzcq zzcq = this.zzvX.get(zzov);
            if (zzcq != null) {
                zzcq.stop();
            }
        }
    }

    public void zzl(zzov zzov) {
        synchronized (this.zzrN) {
            zzcq zzcq = this.zzvX.get(zzov);
            if (zzcq != null) {
                zzcq.pause();
            }
        }
    }

    public void zzm(zzov zzov) {
        synchronized (this.zzrN) {
            zzcq zzcq = this.zzvX.get(zzov);
            if (zzcq != null) {
                zzcq.resume();
            }
        }
    }
}
