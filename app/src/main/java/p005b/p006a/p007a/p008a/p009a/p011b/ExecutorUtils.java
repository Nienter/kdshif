package p005b.p006a.p007a.p008a.p009a.p011b;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p005b.p006a.p007a.p008a.Fabric;

/* renamed from: b.a.a.a.a.b.n */
public final class ExecutorUtils {
    /* renamed from: a */
    public static ExecutorService m170a(String str) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(m174c(str));
        m171a(str, newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    /* renamed from: b */
    public static ScheduledExecutorService m173b(String str) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(m174c(str));
        m171a(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    /* renamed from: c */
    public static final ThreadFactory m174c(final String str) {
        final AtomicLong atomicLong = new AtomicLong(1);
        return new ThreadFactory() {
            public Thread newThread(final Runnable runnable) {
                Thread newThread = Executors.defaultThreadFactory().newThread(new BackgroundPriorityRunnable() {
                    public void onRun() {
                        runnable.run();
                    }
                });
                newThread.setName(str + atomicLong.getAndIncrement());
                return newThread;
            }
        };
    }

    /* renamed from: a */
    private static final void m171a(String str, ExecutorService executorService) {
        m172a(str, executorService, 2, TimeUnit.SECONDS);
    }

    /* renamed from: a */
    public static final void m172a(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
        final String str2 = str;
        final ExecutorService executorService2 = executorService;
        final long j2 = j;
        final TimeUnit timeUnit2 = timeUnit;
        Runtime.getRuntime().addShutdownHook(new Thread(new BackgroundPriorityRunnable() {
            public void onRun() {
                try {
                    Fabric.m456h().mo8506a("Fabric", "Executing shutdown hook for " + str2);
                    executorService2.shutdown();
                    if (!executorService2.awaitTermination(j2, timeUnit2)) {
                        Fabric.m456h().mo8506a("Fabric", str2 + " did not shut down in the allocated time. Requesting immediate shutdown.");
                        executorService2.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    Fabric.m456h().mo8506a("Fabric", String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", new Object[]{str2}));
                    executorService2.shutdownNow();
                }
            }
        }, "Crashlytics Shutdown Hook for " + str));
    }
}
