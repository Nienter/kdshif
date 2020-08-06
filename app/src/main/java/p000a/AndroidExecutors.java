package p000a;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: a.a */
final class AndroidExecutors {

    /* renamed from: a */
    static final int f0a = (f3e + 1);

    /* renamed from: b */
    static final int f1b = ((f3e * 2) + 1);

    /* renamed from: c */
    private static final AndroidExecutors f2c = new AndroidExecutors();

    /* renamed from: e */
    private static final int f3e = Runtime.getRuntime().availableProcessors();

    /* renamed from: d */
    private final Executor f4d = new C0001a();

    /* renamed from: a.a$a */
    /* compiled from: AndroidExecutors */
    private static class C0001a implements Executor {
        private C0001a() {
        }

        public void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    private AndroidExecutors() {
    }

    /* renamed from: a */
    public static ExecutorService m0a() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(f0a, f1b, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
        m1a(threadPoolExecutor, true);
        return threadPoolExecutor;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m1a(ThreadPoolExecutor threadPoolExecutor, boolean z) {
        if (Build.VERSION.SDK_INT >= 9) {
            threadPoolExecutor.allowCoreThreadTimeOut(z);
        }
    }

    /* renamed from: b */
    public static Executor m2b() {
        return f2c.f4d;
    }
}
