package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public class zze implements zzn {
    private final Executor zzr;

    private class zza implements Runnable {
        private final zzk zzt;
        private final zzm zzu;
        private final Runnable zzv;

        public zza(zze zze, zzk zzk, zzm zzm, Runnable runnable) {
            this.zzt = zzk;
            this.zzu = zzm;
            this.zzv = runnable;
        }

        public void run() {
            if (this.zzu.isSuccess()) {
                this.zzt.zza(this.zzu.result);
            } else {
                this.zzt.zzc(this.zzu.zzaf);
            }
            if (this.zzu.zzag) {
                this.zzt.zzc("intermediate-response");
            } else {
                this.zzt.zzd("done");
            }
            if (this.zzv != null) {
                this.zzv.run();
            }
        }
    }

    public zze(final Handler handler) {
        this.zzr = new Executor(this) {
            public void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    public void zza(zzk<?> zzk, zzm<?> zzm) {
        zza(zzk, zzm, null);
    }

    public void zza(zzk<?> zzk, zzm<?> zzm, Runnable runnable) {
        zzk.zzr();
        zzk.zzc("post-response");
        this.zzr.execute(new zza(this, zzk, zzm, runnable));
    }

    public void zza(zzk<?> zzk, zzr zzr2) {
        zzk.zzc("post-error");
        this.zzr.execute(new zza(this, zzk, zzm.zzd(zzr2), null));
    }
}
