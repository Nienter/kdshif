package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {

    private static final class zza implements zzb {
        private final CountDownLatch zzth;

        private zza() {
            this.zzth = new CountDownLatch(1);
        }

        public void await() {
            this.zzth.await();
        }

        public boolean await(long j, TimeUnit timeUnit) {
            return this.zzth.await(j, timeUnit);
        }

        public void onFailure(@NonNull Exception exc) {
            this.zzth.countDown();
        }

        public void onSuccess(Object obj) {
            this.zzth.countDown();
        }
    }

    interface zzb extends OnFailureListener, OnSuccessListener<Object> {
    }

    private static final class zzc implements zzb {
        private final zzh<Void> zzbLF;
        private Exception zzbLK;
        private final int zzbLM;
        private int zzbLN;
        private int zzbLO;
        private final Object zzrN = new Object();

        public zzc(int i, zzh<Void> zzh) {
            this.zzbLM = i;
            this.zzbLF = zzh;
        }

        private void zzSh() {
            if (this.zzbLN + this.zzbLO != this.zzbLM) {
                return;
            }
            if (this.zzbLK == null) {
                this.zzbLF.setResult(null);
                return;
            }
            zzh<Void> zzh = this.zzbLF;
            int i = this.zzbLO;
            zzh.setException(new ExecutionException(new StringBuilder(54).append(i).append(" out of ").append(this.zzbLM).append(" underlying tasks failed").toString(), this.zzbLK));
        }

        public void onFailure(@NonNull Exception exc) {
            synchronized (this.zzrN) {
                this.zzbLO++;
                this.zzbLK = exc;
                zzSh();
            }
        }

        public void onSuccess(Object obj) {
            synchronized (this.zzrN) {
                this.zzbLN++;
                zzSh();
            }
        }
    }

    private Tasks() {
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task) {
        zzac.zzxx();
        zzac.zzb(task, (Object) "Task must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        zza zza2 = new zza();
        zza(task, zza2);
        zza2.await();
        return zzb(task);
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task, long j, @NonNull TimeUnit timeUnit) {
        zzac.zzxx();
        zzac.zzb(task, (Object) "Task must not be null");
        zzac.zzb(timeUnit, (Object) "TimeUnit must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        zza zza2 = new zza();
        zza(task, zza2);
        if (zza2.await(j, timeUnit)) {
            return zzb(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static <TResult> Task<TResult> call(@NonNull Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> call(@NonNull Executor executor, @NonNull final Callable<TResult> callable) {
        zzac.zzb(executor, (Object) "Executor must not be null");
        zzac.zzb(callable, (Object) "Callback must not be null");
        final zzh zzh = new zzh();
        executor.execute(new Runnable() {
            public void run() {
                try {
                    zzh.this.setResult(callable.call());
                } catch (Exception e) {
                    zzh.this.setException(e);
                }
            }
        });
        return zzh;
    }

    public static <TResult> Task<TResult> forException(@NonNull Exception exc) {
        zzh zzh = new zzh();
        zzh.setException(exc);
        return zzh;
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        zzh zzh = new zzh();
        zzh.setResult(tresult);
        return zzh;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult(null);
        }
        for (Task task : collection) {
            if (task == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        zzh zzh = new zzh();
        zzc zzc2 = new zzc(collection.size(), zzh);
        for (Task zza2 : collection) {
            zza(zza2, zzc2);
        }
        return zzh;
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        return taskArr.length == 0 ? forResult(null) : whenAll((Collection<? extends Task<?>>) Arrays.asList(taskArr));
    }

    private static void zza(Task<?> task, zzb zzb2) {
        task.addOnSuccessListener(TaskExecutors.zzbLG, (OnSuccessListener<? super Object>) zzb2);
        task.addOnFailureListener(TaskExecutors.zzbLG, (OnFailureListener) zzb2);
    }

    private static <TResult> TResult zzb(Task<TResult> task) {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }
}
