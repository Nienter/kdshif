package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzac;

@zzmb
public class zzpq {
    private Handler mHandler = null;
    private HandlerThread zzXF = null;
    /* access modifiers changed from: private */
    public int zzXG = 0;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();

    public Looper zzkC() {
        Looper looper;
        synchronized (this.zzrN) {
            if (this.zzXG != 0) {
                zzac.zzb(this.zzXF, (Object) "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.zzXF == null) {
                zzpe.m2431v("Starting the looper thread.");
                this.zzXF = new HandlerThread("LooperProvider");
                this.zzXF.start();
                this.mHandler = new Handler(this.zzXF.getLooper());
                zzpe.m2431v("Looper thread started.");
            } else {
                zzpe.m2431v("Resuming the looper thread");
                this.zzrN.notifyAll();
            }
            this.zzXG++;
            looper = this.zzXF.getLooper();
        }
        return looper;
    }

    public void zzkD() {
        synchronized (this.zzrN) {
            zzac.zzb(this.zzXG > 0, (Object) "Invalid state: release() called more times than expected.");
            int i = this.zzXG - 1;
            this.zzXG = i;
            if (i == 0) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        synchronized (zzpq.this.zzrN) {
                            zzpe.m2431v("Suspending the looper thread");
                            while (zzpq.this.zzXG == 0) {
                                try {
                                    zzpq.this.zzrN.wait();
                                    zzpe.m2431v("Looper thread resumed");
                                } catch (InterruptedException e) {
                                    zzpe.m2431v("Looper thread interrupted.");
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
