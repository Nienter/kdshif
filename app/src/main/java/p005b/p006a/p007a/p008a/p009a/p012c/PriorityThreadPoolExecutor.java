package p005b.p006a.p007a.p008a.p009a.p012c;

import android.annotation.TargetApi;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: b.a.a.a.a.c.k */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {

    /* renamed from: a */
    private static final int f214a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b */
    private static final int f215b = (f214a + 1);

    /* renamed from: c */
    private static final int f216c = ((f214a * 2) + 1);

    /* renamed from: b.a.a.a.a.c.k$a */
    /* compiled from: PriorityThreadPoolExecutor */
    protected static final class C0448a implements ThreadFactory {

        /* renamed from: a */
        private final int f217a;

        public C0448a(int i) {
            this.f217a = i;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(this.f217a);
            thread.setName("Queue");
            return thread;
        }
    }

    <T extends Runnable & Dependency & C0449l & PriorityProvider> PriorityThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, DependencyPriorityBlockingQueue<T> cVar, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, cVar, threadFactory);
        prestartAllCoreThreads();
    }

    /* renamed from: a */
    public static <T extends Runnable & Dependency & C0449l & PriorityProvider> PriorityThreadPoolExecutor m264a(int i, int i2) {
        return new PriorityThreadPoolExecutor(i, i2, 1, TimeUnit.SECONDS, new DependencyPriorityBlockingQueue(), new C0448a(10));
    }

    /* renamed from: a */
    public static PriorityThreadPoolExecutor m263a() {
        return m264a(f215b, f216c);
    }

    /* access modifiers changed from: protected */
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new PriorityFutureTask(runnable, t);
    }

    /* access modifiers changed from: protected */
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new PriorityFutureTask(callable);
    }

    @TargetApi(9)
    public void execute(Runnable runnable) {
        if (PriorityTask.isProperDelegate(runnable)) {
            super.execute(runnable);
        } else {
            super.execute(newTaskFor(runnable, null));
        }
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        C0449l lVar = (C0449l) runnable;
        lVar.setFinished(true);
        lVar.setError(th);
        getQueue().recycleBlockedQueue();
        super.afterExecute(runnable, th);
    }

    /* renamed from: b */
    public DependencyPriorityBlockingQueue getQueue() {
        return (DependencyPriorityBlockingQueue) super.getQueue();
    }
}
