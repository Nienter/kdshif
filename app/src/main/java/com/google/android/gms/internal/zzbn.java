package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf;
import java.util.concurrent.Callable;

public class zzbn implements Callable {
    private final zzbc zzpC;
    private final zzaf.zza zzra;

    public zzbn(zzbc zzbc, zzaf.zza zza) {
        this.zzpC = zzbc;
        this.zzra = zza;
    }

    /* renamed from: zzbl */
    public Void call() {
        if (this.zzpC.zzaT() != null) {
            this.zzpC.zzaT().get();
        }
        zzaf.zza zzaS = this.zzpC.zzaS();
        if (zzaS != null) {
            try {
                synchronized (this.zzra) {
                    zzbut.zza(this.zzra, zzbut.zzf(zzaS));
                }
            } catch (zzbus e) {
            }
        }
        return null;
    }
}
