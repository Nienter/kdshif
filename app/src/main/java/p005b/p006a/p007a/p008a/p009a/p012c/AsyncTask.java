package p005b.p006a.p007a.p008a.p009a.p012c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: b.a.a.a.a.c.a */
public abstract class AsyncTask<Params, Progress, Result> {

    /* renamed from: a */
    private static final int f176a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b */
    public static final Executor f177b = new ThreadPoolExecutor(f179d, f180e, 1, TimeUnit.SECONDS, f182g, f181f);

    /* renamed from: c */
    public static final Executor f178c = new C0442c();

    /* renamed from: d */
    private static final int f179d = (f176a + 1);

    /* renamed from: e */
    private static final int f180e = ((f176a * 2) + 1);

    /* renamed from: f */
    private static final ThreadFactory f181f = new ThreadFactory() {

        /* renamed from: a */
        private final AtomicInteger f190a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.f190a.getAndIncrement());
        }
    };

    /* renamed from: g */
    private static final BlockingQueue<Runnable> f182g = new LinkedBlockingQueue(128);

    /* renamed from: h */
    private static final C0441b f183h = new C0441b();

    /* renamed from: i */
    private static volatile Executor f184i = f178c;

    /* renamed from: j */
    private final C0445e<Params, Result> f185j = new C0445e<Params, Result>() {
        public Result call() {
            AsyncTask.this.f189n.set(true);
            Process.setThreadPriority(10);
            return AsyncTask.this.m238d(AsyncTask.this.mo8323a((Params[]) this.f201b));
        }
    };

    /* renamed from: k */
    private final FutureTask<Result> f186k = new FutureTask<Result>(this.f185j) {
        /* access modifiers changed from: protected */
        public void done() {
            try {
                AsyncTask.this.m237c(get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                AsyncTask.this.m237c(null);
            }
        }
    };

    /* renamed from: l */
    private volatile C0444d f187l = C0444d.PENDING;

    /* renamed from: m */
    private final AtomicBoolean f188m = new AtomicBoolean();
    /* access modifiers changed from: private */

    /* renamed from: n */
    public final AtomicBoolean f189n = new AtomicBoolean();

    /* renamed from: b.a.a.a.a.c.a$a */
    /* compiled from: AsyncTask */
    private static class C0440a<Data> {

        /* renamed from: a */
        final AsyncTask f194a;

        /* renamed from: b */
        final Data[] f195b;

        C0440a(AsyncTask aVar, Data... dataArr) {
            this.f194a = aVar;
            this.f195b = dataArr;
        }
    }

    /* renamed from: b.a.a.a.a.c.a$b */
    /* compiled from: AsyncTask */
    private static class C0441b extends Handler {
        public C0441b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            C0440a aVar = (C0440a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.f194a.m239e(aVar.f195b[0]);
                    return;
                case 2:
                    aVar.f194a.mo8329b((Progress[]) aVar.f195b);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: b.a.a.a.a.c.a$c */
    /* compiled from: AsyncTask */
    private static class C0442c implements Executor {

        /* renamed from: a */
        final LinkedList<Runnable> f196a;

        /* renamed from: b */
        Runnable f197b;

        private C0442c() {
            this.f196a = new LinkedList<>();
        }

        public synchronized void execute(final Runnable runnable) {
            this.f196a.offer(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        C0442c.this.mo8336a();
                    }
                }
            });
            if (this.f197b == null) {
                mo8336a();
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public synchronized void mo8336a() {
            Runnable poll = this.f196a.poll();
            this.f197b = poll;
            if (poll != null) {
                AsyncTask.f177b.execute(this.f197b);
            }
        }
    }

    /* renamed from: b.a.a.a.a.c.a$d */
    /* compiled from: AsyncTask */
    public enum C0444d {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* renamed from: b.a.a.a.a.c.a$e */
    /* compiled from: AsyncTask */
    private static abstract class C0445e<Params, Result> implements Callable<Result> {

        /* renamed from: b */
        Params[] f201b;

        private C0445e() {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Result mo8323a(Params... paramsArr);

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m237c(Result result) {
        if (!this.f189n.get()) {
            m238d(result);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public Result m238d(Result result) {
        f183h.obtainMessage(1, new C0440a(this, result)).sendToTarget();
        return result;
    }

    /* renamed from: b */
    public final C0444d mo8327b() {
        return this.f187l;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8324a() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8325a(Result result) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8329b(Progress... progressArr) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8328b(Result result) {
        mo8330c();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo8330c() {
    }

    /* renamed from: d */
    public final boolean mo8331d() {
        return this.f188m.get();
    }

    /* renamed from: a */
    public final boolean mo8326a(boolean z) {
        this.f188m.set(true);
        return this.f186k.cancel(z);
    }

    /* renamed from: a */
    public final AsyncTask<Params, Progress, Result> mo8322a(Executor executor, Params... paramsArr) {
        if (this.f187l != C0444d.PENDING) {
            switch (this.f187l) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f187l = C0444d.RUNNING;
        mo8324a();
        this.f185j.f201b = paramsArr;
        executor.execute(this.f186k);
        return this;
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m239e(Result result) {
        if (mo8331d()) {
            mo8328b(result);
        } else {
            mo8325a(result);
        }
        this.f187l = C0444d.FINISHED;
    }
}
