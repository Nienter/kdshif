package p018c;

import android.support.p004v7.widget.ActivityChooserView;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p018c.p019a.Util;
import p018c.p019a.p021b.RealConnection;
import p018c.p019a.p021b.RouteDatabase;
import p018c.p019a.p021b.StreamAllocation;
import p018c.p019a.p026g.Platform;

/* renamed from: c.j */
public final class ConnectionPool {

    /* renamed from: c */
    static final /* synthetic */ boolean f983c = (!ConnectionPool.class.desiredAssertionStatus());

    /* renamed from: d */
    private static final Executor f984d = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.m650a("OkHttp ConnectionPool", true));

    /* renamed from: a */
    final RouteDatabase f985a;

    /* renamed from: b */
    boolean f986b;

    /* renamed from: e */
    private final int f987e;

    /* renamed from: f */
    private final long f988f;

    /* renamed from: g */
    private final Runnable f989g;

    /* renamed from: h */
    private final Deque<RealConnection> f990h;

    public ConnectionPool() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public ConnectionPool(int i, long j, TimeUnit timeUnit) {
        this.f989g = new Runnable() {
            public void run() {
                while (true) {
                    long a = ConnectionPool.this.mo8896a(System.nanoTime());
                    if (a != -1) {
                        if (a > 0) {
                            long j = a / 1000000;
                            long j2 = a - (j * 1000000);
                            synchronized (ConnectionPool.this) {
                                try {
                                    ConnectionPool.this.wait(j, (int) j2);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        };
        this.f990h = new ArrayDeque();
        this.f985a = new RouteDatabase();
        this.f987e = i;
        this.f988f = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public RealConnection mo8897a(Address aVar, StreamAllocation gVar) {
        if (f983c || Thread.holdsLock(this)) {
            for (RealConnection next : this.f990h) {
                if (next.mo8642a(aVar)) {
                    gVar.mo8658a(next);
                    return next;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public Socket mo8899b(Address aVar, StreamAllocation gVar) {
        if (f983c || Thread.holdsLock(this)) {
            for (RealConnection next : this.f990h) {
                if (next.mo8642a(aVar) && next.mo8646d() && next != gVar.mo8661b()) {
                    return gVar.mo8662b(next);
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8898a(RealConnection cVar) {
        if (f983c || Thread.holdsLock(this)) {
            if (!this.f986b) {
                this.f986b = true;
                f984d.execute(this.f989g);
            }
            this.f990h.add(cVar);
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo8900b(RealConnection cVar) {
        if (!f983c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (cVar.f489a || this.f987e == 0) {
            this.f990h.remove(cVar);
            return true;
        } else {
            notifyAll();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public long mo8896a(long j) {
        RealConnection cVar;
        long j2;
        RealConnection cVar2 = null;
        long j3 = Long.MIN_VALUE;
        synchronized (this) {
            int i = 0;
            int i2 = 0;
            for (RealConnection next : this.f990h) {
                if (m1085a(next, j) > 0) {
                    i2++;
                } else {
                    int i3 = i + 1;
                    long j4 = j - next.f493e;
                    if (j4 > j3) {
                        long j5 = j4;
                        cVar = next;
                        j2 = j5;
                    } else {
                        cVar = cVar2;
                        j2 = j3;
                    }
                    j3 = j2;
                    cVar2 = cVar;
                    i = i3;
                }
            }
            if (j3 >= this.f988f || i > this.f987e) {
                this.f990h.remove(cVar2);
                Util.m653a(cVar2.mo8644b());
                return 0;
            } else if (i > 0) {
                long j6 = this.f988f - j3;
                return j6;
            } else if (i2 > 0) {
                long j7 = this.f988f;
                return j7;
            } else {
                this.f986b = false;
                return -1;
            }
        }
    }

    /* renamed from: a */
    private int m1085a(RealConnection cVar, long j) {
        List<Reference<StreamAllocation>> list = cVar.f492d;
        int i = 0;
        while (i < list.size()) {
            Reference reference = list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                Platform.m981b().mo8817a("A connection to " + cVar.mo8638a().mo8845a().mo8568a() + " was leaked. Did you forget to close a response body?", ((StreamAllocation.C0475a) reference).f525a);
                list.remove(i);
                cVar.f489a = true;
                if (list.isEmpty()) {
                    cVar.f493e = j - this.f988f;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
