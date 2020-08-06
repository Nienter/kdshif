package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.ads.internal.zzv;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@zzmb
public final class zzph {
    private static final ThreadPoolExecutor zzWJ = new ThreadPoolExecutor(10, 10, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzaU("Default"));
    private static final ThreadPoolExecutor zzWK = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzaU("Loader"));

    static {
        zzWJ.allowCoreThreadTimeOut(true);
        zzWK.allowCoreThreadTimeOut(true);
    }

    public static zzqf<Void> zza(int i, final Runnable runnable) {
        return i == 1 ? zza((ExecutorService) zzWK, new Callable<Void>() {
            /* renamed from: zzbl */
            public Void call() {
                runnable.run();
                return null;
            }
        }) : zza((ExecutorService) zzWJ, new Callable<Void>() {
            /* renamed from: zzbl */
            public Void call() {
                runnable.run();
                return null;
            }
        });
    }

    public static zzqf<Void> zza(Runnable runnable) {
        return zza(0, runnable);
    }

    public static <T> zzqf<T> zza(Callable<T> callable) {
        return zza((ExecutorService) zzWJ, callable);
    }

    public static <T> zzqf<T> zza(ExecutorService executorService, final Callable<T> callable) {
        final zzqc zzqc = new zzqc();
        try {
            final Future<?> submit = executorService.submit(new Runnable() {
                public void run() {
                    try {
                        Process.setThreadPriority(10);
                        zzqc.this.zzh(callable.call());
                    } catch (Exception e) {
                        zzv.zzcN().zza((Throwable) e, "AdThreadPool.submit");
                        zzqc.this.zze(e);
                    }
                }
            });
            zzqc.zzd(new Runnable() {
                public void run() {
                    if (zzqc.this.isCancelled()) {
                        submit.cancel(true);
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            zzpe.zzc("Thread execution is rejected.", e);
            zzqc.cancel(true);
        }
        return zzqc;
    }

    private static ThreadFactory zzaU(final String str) {
        return new ThreadFactory() {
            private final AtomicInteger zzWP = new AtomicInteger(1);

            public Thread newThread(Runnable runnable) {
                String str = str;
                return new Thread(runnable, new StringBuilder(String.valueOf(str).length() + 23).append("AdWorker(").append(str).append(") #").append(this.zzWP.getAndIncrement()).toString());
            }
        };
    }
}
