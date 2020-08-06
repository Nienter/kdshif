package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult> implements zzf<TResult> {
    private final Executor zzbDK;
    /* access modifiers changed from: private */
    public OnCompleteListener<TResult> zzbLx;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();

    public zzc(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzbDK = executor;
        this.zzbLx = onCompleteListener;
    }

    public void cancel() {
        synchronized (this.zzrN) {
            this.zzbLx = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        synchronized (this.zzrN) {
            if (this.zzbLx != null) {
                this.zzbDK.execute(new Runnable() {
                    public void run() {
                        synchronized (zzc.this.zzrN) {
                            if (zzc.this.zzbLx != null) {
                                zzc.this.zzbLx.onComplete(task);
                            }
                        }
                    }
                });
            }
        }
    }
}
