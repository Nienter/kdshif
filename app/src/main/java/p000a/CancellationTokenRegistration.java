package p000a;

import java.io.Closeable;

/* renamed from: a.f */
public class CancellationTokenRegistration implements Closeable {

    /* renamed from: a */
    private final Object f18a;

    /* renamed from: b */
    private CancellationTokenSource f19b;

    /* renamed from: c */
    private Runnable f20c;

    /* renamed from: d */
    private boolean f21d;

    public void close() {
        synchronized (this.f18a) {
            if (!this.f21d) {
                this.f21d = true;
                this.f19b.mo6a(this);
                this.f19b = null;
                this.f20c = null;
            }
        }
    }
}
