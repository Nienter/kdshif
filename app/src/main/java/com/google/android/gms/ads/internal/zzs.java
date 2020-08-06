package com.google.android.gms.ads.internal;

import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzdy;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import java.lang.ref.WeakReference;

@zzmb
public class zzs {
    private final zza zzuf;
    /* access modifiers changed from: private */
    @Nullable
    public zzdy zzug;
    /* access modifiers changed from: private */
    public boolean zzuh;
    private boolean zzui;
    private long zzuj;
    private final Runnable zzv;

    public static class zza {
        private final Handler mHandler;

        public zza(Handler handler) {
            this.mHandler = handler;
        }

        public boolean postDelayed(Runnable runnable, long j) {
            return this.mHandler.postDelayed(runnable, j);
        }

        public void removeCallbacks(Runnable runnable) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    public zzs(zza zza2) {
        this(zza2, new zza(zzpi.zzWR));
    }

    zzs(zza zza2, zza zza3) {
        this.zzuh = false;
        this.zzui = false;
        this.zzuj = 0;
        this.zzuf = zza3;
        final WeakReference weakReference = new WeakReference(zza2);
        this.zzv = new Runnable() {
            public void run() {
                boolean unused = zzs.this.zzuh = false;
                zza zza = (zza) weakReference.get();
                if (zza != null) {
                    zza.zzd(zzs.this.zzug);
                }
            }
        };
    }

    public void cancel() {
        this.zzuh = false;
        this.zzuf.removeCallbacks(this.zzv);
    }

    public void pause() {
        this.zzui = true;
        if (this.zzuh) {
            this.zzuf.removeCallbacks(this.zzv);
        }
    }

    public void resume() {
        this.zzui = false;
        if (this.zzuh) {
            this.zzuh = false;
            zza(this.zzug, this.zzuj);
        }
    }

    public void zza(zzdy zzdy, long j) {
        if (this.zzuh) {
            zzpe.zzbe("An ad refresh is already scheduled.");
            return;
        }
        this.zzug = zzdy;
        this.zzuh = true;
        this.zzuj = j;
        if (!this.zzui) {
            zzpe.zzbd(new StringBuilder(65).append("Scheduling ad refresh ").append(j).append(" milliseconds from now.").toString());
            this.zzuf.postDelayed(this.zzv, j);
        }
    }

    public boolean zzcv() {
        return this.zzuh;
    }

    public void zzg(zzdy zzdy) {
        this.zzug = zzdy;
    }

    public void zzh(zzdy zzdy) {
        zza(zzdy, 60000);
    }
}
