package p000a;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

/* renamed from: a.d */
final class BoltsExecutors {

    /* renamed from: a */
    private static final BoltsExecutors f12a = new BoltsExecutors();

    /* renamed from: b */
    private final ExecutorService f13b;

    /* renamed from: c */
    private final ScheduledExecutorService f14c;

    /* renamed from: d */
    private final Executor f15d;

    /* renamed from: a.d$a */
    /* compiled from: BoltsExecutors */
    private static class C0004a implements Executor {

        /* renamed from: a */
        private ThreadLocal<Integer> f16a;

        private C0004a() {
            this.f16a = new ThreadLocal<>();
        }

        /* renamed from: a */
        private int m8a() {
            Integer num = this.f16a.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() + 1;
            this.f16a.set(Integer.valueOf(intValue));
            return intValue;
        }

        /* renamed from: b */
        private int m9b() {
            Integer num = this.f16a.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.f16a.remove();
            } else {
                this.f16a.set(Integer.valueOf(intValue));
            }
            return intValue;
        }

        public void execute(Runnable runnable) {
            if (m8a() <= 15) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    m9b();
                    throw th;
                }
            } else {
                BoltsExecutors.m5a().execute(runnable);
            }
            m9b();
        }
    }

    /* renamed from: c */
    private static boolean m7c() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains(AbstractSpiCall.ANDROID_CLIENT_TYPE);
    }

    private BoltsExecutors() {
        this.f13b = !m7c() ? Executors.newCachedThreadPool() : AndroidExecutors.m0a();
        this.f14c = Executors.newSingleThreadScheduledExecutor();
        this.f15d = new C0004a();
    }

    /* renamed from: a */
    public static ExecutorService m5a() {
        return f12a.f13b;
    }

    /* renamed from: b */
    static Executor m6b() {
        return f12a.f15d;
    }
}
