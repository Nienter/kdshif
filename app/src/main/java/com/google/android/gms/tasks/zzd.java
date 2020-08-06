package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult> implements zzf<TResult> {
    private final Executor zzbDK;
    /* access modifiers changed from: private */
    public OnFailureListener zzbLz;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();

    public zzd(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzbDK = executor;
        this.zzbLz = onFailureListener;
    }

    public void cancel() {
        synchronized (this.zzrN) {
            this.zzbLz = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (!task.isSuccessful()) {
            synchronized (this.zzrN) {
                if (this.zzbLz != null) {
                    this.zzbDK.execute(new Runnable() {
                        public void run() {
                            synchronized (zzd.this.zzrN) {
                                if (zzd.this.zzbLz != null) {
                                    zzd.this.zzbLz.onFailure(task.getException());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
