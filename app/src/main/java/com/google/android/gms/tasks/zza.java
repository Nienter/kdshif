package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult> implements zzf<TResult> {
    private final Executor zzbDK;
    /* access modifiers changed from: private */
    public final Continuation<TResult, TContinuationResult> zzbLs;
    /* access modifiers changed from: private */
    public final zzh<TContinuationResult> zzbLt;

    public zza(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzh<TContinuationResult> zzh) {
        this.zzbDK = executor;
        this.zzbLs = continuation;
        this.zzbLt = zzh;
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.zzbDK.execute(new Runnable() {
            public void run() {
                try {
                    zza.this.zzbLt.setResult(zza.this.zzbLs.then(task));
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zza.this.zzbLt.setException((Exception) e.getCause());
                    } else {
                        zza.this.zzbLt.setException(e);
                    }
                } catch (Exception e2) {
                    zza.this.zzbLt.setException(e2);
                }
            }
        });
    }
}
