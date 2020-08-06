package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult> {
    private final Executor zzbDK;
    /* access modifiers changed from: private */
    public final Continuation<TResult, Task<TContinuationResult>> zzbLs;
    /* access modifiers changed from: private */
    public final zzh<TContinuationResult> zzbLt;

    public zzb(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzh<TContinuationResult> zzh) {
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
                    Task task = (Task) zzb.this.zzbLs.then(task);
                    if (task == null) {
                        zzb.this.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    task.addOnSuccessListener(TaskExecutors.zzbLG, zzb.this);
                    task.addOnFailureListener(TaskExecutors.zzbLG, (OnFailureListener) zzb.this);
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zzb.this.zzbLt.setException((Exception) e.getCause());
                    } else {
                        zzb.this.zzbLt.setException(e);
                    }
                } catch (Exception e2) {
                    zzb.this.zzbLt.setException(e2);
                }
            }
        });
    }

    public void onFailure(@NonNull Exception exc) {
        this.zzbLt.setException(exc);
    }

    public void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzbLt.setResult(tcontinuationresult);
    }
}
