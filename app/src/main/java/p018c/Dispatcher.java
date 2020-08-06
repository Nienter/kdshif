package p018c;

import android.support.p004v7.widget.ActivityChooserView;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p018c.RealCall;
import p018c.p019a.Util;

/* renamed from: c.n */
public final class Dispatcher {

    /* renamed from: a */
    private int f1018a = 64;

    /* renamed from: b */
    private int f1019b = 5;

    /* renamed from: c */
    private Runnable f1020c;

    /* renamed from: d */
    private ExecutorService f1021d;

    /* renamed from: e */
    private final Deque<RealCall.C0540a> f1022e = new ArrayDeque();

    /* renamed from: f */
    private final Deque<RealCall.C0540a> f1023f = new ArrayDeque();

    /* renamed from: g */
    private final Deque<RealCall> f1024g = new ArrayDeque();

    /* renamed from: a */
    public synchronized ExecutorService mo8925a() {
        if (this.f1021d == null) {
            this.f1021d = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.m650a("OkHttp Dispatcher", false));
        }
        return this.f1021d;
    }

    /* renamed from: c */
    private void m1122c() {
        if (this.f1023f.size() < this.f1018a && !this.f1022e.isEmpty()) {
            Iterator<RealCall.C0540a> it = this.f1022e.iterator();
            while (it.hasNext()) {
                RealCall.C0540a next = it.next();
                if (m1121b(next) < this.f1019b) {
                    it.remove();
                    this.f1023f.add(next);
                    mo8925a().execute(next);
                }
                if (this.f1023f.size() >= this.f1018a) {
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    private int m1121b(RealCall.C0540a aVar) {
        int i = 0;
        Iterator<RealCall.C0540a> it = this.f1023f.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            if (it.next().mo9019a().equals(aVar.mo9019a())) {
                i = i2 + 1;
            } else {
                i = i2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo8927a(RealCall wVar) {
        this.f1024g.add(wVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8926a(RealCall.C0540a aVar) {
        m1120a(this.f1023f, aVar, true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo8929b(RealCall wVar) {
        m1120a(this.f1024g, wVar, false);
    }

    /* renamed from: a */
    private <T> void m1120a(Deque<T> deque, T t, boolean z) {
        int b;
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            if (z) {
                m1122c();
            }
            b = mo8928b();
            runnable = this.f1020c;
        }
        if (b == 0 && runnable != null) {
            runnable.run();
        }
    }

    /* renamed from: b */
    public synchronized int mo8928b() {
        return this.f1023f.size() + this.f1024g.size();
    }
}
