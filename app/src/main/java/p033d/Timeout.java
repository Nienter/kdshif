package p033d;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* renamed from: d.t */
public class Timeout {

    /* renamed from: b */
    public static final Timeout f2693b = new Timeout() {
        /* renamed from: a */
        public Timeout mo17745a(long j, TimeUnit timeUnit) {
            return this;
        }

        /* renamed from: a */
        public Timeout mo17744a(long j) {
            return this;
        }

        /* renamed from: g */
        public void mo17751g() {
        }
    };

    /* renamed from: a */
    private boolean f2694a;

    /* renamed from: c */
    private long f2695c;

    /* renamed from: d */
    private long f2696d;

    /* renamed from: a */
    public Timeout mo17745a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        } else if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            this.f2696d = timeUnit.toNanos(j);
            return this;
        }
    }

    /* renamed from: b_ */
    public long mo17746b_() {
        return this.f2696d;
    }

    /* renamed from: c_ */
    public boolean mo17747c_() {
        return this.f2694a;
    }

    /* renamed from: d */
    public long mo17748d() {
        if (this.f2694a) {
            return this.f2695c;
        }
        throw new IllegalStateException("No deadline");
    }

    /* renamed from: a */
    public Timeout mo17744a(long j) {
        this.f2694a = true;
        this.f2695c = j;
        return this;
    }

    /* renamed from: d_ */
    public Timeout mo17749d_() {
        this.f2696d = 0;
        return this;
    }

    /* renamed from: e_ */
    public Timeout mo17750e_() {
        this.f2694a = false;
        return this;
    }

    /* renamed from: g */
    public void mo17751g() {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.f2694a && this.f2695c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
