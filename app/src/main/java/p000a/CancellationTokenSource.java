package p000a;

import java.io.Closeable;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;

/* renamed from: a.g */
public class CancellationTokenSource implements Closeable {

    /* renamed from: a */
    private final Object f22a;

    /* renamed from: b */
    private final List<CancellationTokenRegistration> f23b;

    /* renamed from: c */
    private ScheduledFuture<?> f24c;

    /* renamed from: d */
    private boolean f25d;

    /* renamed from: e */
    private boolean f26e;

    /* renamed from: a */
    public boolean mo7a() {
        boolean z;
        synchronized (this.f22a) {
            m11b();
            z = this.f25d;
        }
        return z;
    }

    public void close() {
        synchronized (this.f22a) {
            if (!this.f26e) {
                m12c();
                for (CancellationTokenRegistration close : this.f23b) {
                    close.close();
                }
                this.f23b.clear();
                this.f26e = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo6a(CancellationTokenRegistration fVar) {
        synchronized (this.f22a) {
            m11b();
            this.f23b.remove(fVar);
        }
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(mo7a())});
    }

    /* renamed from: b */
    private void m11b() {
        if (this.f26e) {
            throw new IllegalStateException("Object already closed");
        }
    }

    /* renamed from: c */
    private void m12c() {
        if (this.f24c != null) {
            this.f24c.cancel(true);
            this.f24c = null;
        }
    }
}
