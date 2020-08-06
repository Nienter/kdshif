package com.p028a.p029a.p030a;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.a.a.a.ax */
public class QueuedWork {

    /* renamed from: a */
    private static List<WeakReference<ScheduledFuture<?>>> f1379a = new ArrayList();

    /* renamed from: b */
    private static ExecutorService f1380b = Executors.newSingleThreadExecutor();

    /* renamed from: c */
    private static long f1381c = 5;

    /* renamed from: d */
    private static ScheduledExecutorService f1382d = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: a */
    public static void m1847a(Runnable runnable) {
        if (f1380b.isShutdown()) {
            f1380b = Executors.newSingleThreadExecutor();
        }
        f1380b.execute(runnable);
    }

    /* renamed from: a */
    public static void m1846a() {
        try {
            for (WeakReference<ScheduledFuture<?>> weakReference : f1379a) {
                ScheduledFuture scheduledFuture = (ScheduledFuture) weakReference.get();
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
            }
            f1379a.clear();
            if (!f1380b.isShutdown()) {
                f1380b.shutdown();
            }
            if (!f1382d.isShutdown()) {
                f1382d.shutdown();
            }
            f1380b.awaitTermination(f1381c, TimeUnit.SECONDS);
            f1382d.awaitTermination(f1381c, TimeUnit.SECONDS);
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    public static synchronized void m1849b(Runnable runnable) {
        synchronized (QueuedWork.class) {
            if (f1382d.isShutdown()) {
                f1382d = Executors.newSingleThreadScheduledExecutor();
            }
            f1382d.execute(runnable);
        }
    }

    /* renamed from: a */
    public static synchronized void m1848a(Runnable runnable, long j) {
        synchronized (QueuedWork.class) {
            if (f1382d.isShutdown()) {
                f1382d = Executors.newSingleThreadScheduledExecutor();
            }
            f1379a.add(new WeakReference(f1382d.schedule(runnable, j, TimeUnit.MILLISECONDS)));
        }
    }

    /* renamed from: c */
    public static synchronized void m1850c(Runnable runnable) {
        synchronized (QueuedWork.class) {
            if (f1382d.isShutdown()) {
                f1382d = Executors.newSingleThreadScheduledExecutor();
            }
            try {
                f1382d.submit(runnable).get(5, TimeUnit.SECONDS);
            } catch (Exception e) {
            }
        }
    }
}
