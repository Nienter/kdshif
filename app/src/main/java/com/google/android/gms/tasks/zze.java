package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult> {
    private final Executor zzbDK;
    /* access modifiers changed from: private */
    public OnSuccessListener<? super TResult> zzbLB;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();

    public zze(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzbDK = executor;
        this.zzbLB = onSuccessListener;
    }

    public void cancel() {
        synchronized (this.zzrN) {
            this.zzbLB = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzrN) {
                if (this.zzbLB != null) {
                    this.zzbDK.execute(new Runnable() {
                        public void run() {
                            synchronized (zze.this.zzrN) {
                                if (zze.this.zzbLB != null) {
                                    zze.this.zzbLB.onSuccess(task.getResult());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
