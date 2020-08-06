package p018c.p019a.p024e;

import java.util.concurrent.CountDownLatch;

/* renamed from: c.a.e.l */
final class Ping {

    /* renamed from: a */
    private final CountDownLatch f753a = new CountDownLatch(1);

    /* renamed from: b */
    private long f754b = -1;

    /* renamed from: c */
    private long f755c = -1;

    Ping() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8788a() {
        if (this.f754b != -1) {
            throw new IllegalStateException();
        }
        this.f754b = System.nanoTime();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo8789b() {
        if (this.f755c != -1 || this.f754b == -1) {
            throw new IllegalStateException();
        }
        this.f755c = System.nanoTime();
        this.f753a.countDown();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo8790c() {
        if (this.f755c != -1 || this.f754b == -1) {
            throw new IllegalStateException();
        }
        this.f755c = this.f754b - 1;
        this.f753a.countDown();
    }
}
