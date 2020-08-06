package com.google.android.gms.internal;

import com.google.android.gms.internal.zzqi;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzmb
public class zzqj<T> implements zzqi<T> {
    protected int zzJh = 0;
    protected final BlockingQueue<zza> zzYt = new LinkedBlockingQueue();
    protected T zzYu;
    private final Object zzrN = new Object();

    class zza {
        public final zzqi.zzc<T> zzYv;
        public final zzqi.zza zzYw;

        public zza(zzqj zzqj, zzqi.zzc<T> zzc, zzqi.zza zza) {
            this.zzYv = zzc;
            this.zzYw = zza;
        }
    }

    public int getStatus() {
        return this.zzJh;
    }

    public void reject() {
        synchronized (this.zzrN) {
            if (this.zzJh != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzJh = -1;
            for (zza zza2 : this.zzYt) {
                zza2.zzYw.run();
            }
            this.zzYt.clear();
        }
    }

    public void zza(zzqi.zzc<T> zzc, zzqi.zza zza2) {
        synchronized (this.zzrN) {
            if (this.zzJh == 1) {
                zzc.zzd(this.zzYu);
            } else if (this.zzJh == -1) {
                zza2.run();
            } else if (this.zzJh == 0) {
                this.zzYt.add(new zza(this, zzc, zza2));
            }
        }
    }

    public void zzg(T t) {
        synchronized (this.zzrN) {
            if (this.zzJh != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzYu = t;
            this.zzJh = 1;
            for (zza zza2 : this.zzYt) {
                zza2.zzYv.zzd(t);
            }
            this.zzYt.clear();
        }
    }
}
