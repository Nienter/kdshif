package p018c.p019a.p024e;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import p033d.AsyncTimeout;
import p033d.Buffer;
import p033d.BufferedSource;
import p033d.Sink;
import p033d.Source;
import p033d.Timeout;

/* renamed from: c.a.e.i */
public final class Http2Stream {

    /* renamed from: i */
    static final /* synthetic */ boolean f713i = (!Http2Stream.class.desiredAssertionStatus());

    /* renamed from: a */
    long f714a = 0;

    /* renamed from: b */
    long f715b;

    /* renamed from: c */
    final int f716c;

    /* renamed from: d */
    final Http2Connection f717d;

    /* renamed from: e */
    final C0505a f718e;

    /* renamed from: f */
    final C0507c f719f = new C0507c();

    /* renamed from: g */
    final C0507c f720g = new C0507c();

    /* renamed from: h */
    ErrorCode f721h = null;

    /* renamed from: j */
    private final List<Header> f722j;

    /* renamed from: k */
    private List<Header> f723k;

    /* renamed from: l */
    private boolean f724l;

    /* renamed from: m */
    private final C0506b f725m;

    /* renamed from: c.a.e.i$a */
    /* compiled from: Http2Stream */
    final class C0505a implements Sink {

        /* renamed from: c */
        static final /* synthetic */ boolean f726c = (!Http2Stream.class.desiredAssertionStatus());

        /* renamed from: a */
        boolean f727a;

        /* renamed from: b */
        boolean f728b;

        /* renamed from: e */
        private final Buffer f730e = new Buffer();

        C0505a() {
        }

        /* renamed from: a_ */
        public void mo8624a_(Buffer cVar, long j) {
            if (f726c || !Thread.holdsLock(Http2Stream.this)) {
                this.f730e.mo8624a_(cVar, j);
                while (this.f730e.mo17648b() >= PlaybackStateCompat.ACTION_PREPARE) {
                    m881a(false);
                }
                return;
            }
            throw new AssertionError();
        }

        /* renamed from: a */
        private void m881a(boolean z) {
            long min;
            synchronized (Http2Stream.this) {
                Http2Stream.this.f720g.mo17629c();
                while (Http2Stream.this.f715b <= 0 && !this.f728b && !this.f727a && Http2Stream.this.f721h == null) {
                    try {
                        Http2Stream.this.mo8764l();
                    } catch (Throwable th) {
                        Http2Stream.this.f720g.mo8768b();
                        throw th;
                    }
                }
                Http2Stream.this.f720g.mo8768b();
                Http2Stream.this.mo8763k();
                min = Math.min(Http2Stream.this.f715b, this.f730e.mo17648b());
                Http2Stream.this.f715b -= min;
            }
            Http2Stream.this.f720g.mo17629c();
            try {
                Http2Stream.this.f717d.mo8715a(Http2Stream.this.f716c, z && min == this.f730e.mo17648b(), this.f730e, min);
            } finally {
                Http2Stream.this.f720g.mo8768b();
            }
        }

        public void flush() {
            if (f726c || !Thread.holdsLock(Http2Stream.this)) {
                synchronized (Http2Stream.this) {
                    Http2Stream.this.mo8763k();
                }
                while (this.f730e.mo17648b() > 0) {
                    m881a(false);
                    Http2Stream.this.f717d.mo8722b();
                }
                return;
            }
            throw new AssertionError();
        }

        /* renamed from: a */
        public Timeout mo8695a() {
            return Http2Stream.this.f720g;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
            if (r6.f729d.f718e.f728b != false) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
            if (r6.f730e.mo17648b() <= 0) goto L_0x0042;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
            if (r6.f730e.mo17648b() <= 0) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
            m881a(true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
            r6.f729d.f717d.mo8715a(r6.f729d.f716c, true, (p033d.Buffer) null, 0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
            r1 = r6.f729d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            r6.f727a = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0054, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0055, code lost:
            r6.f729d.f717d.mo8722b();
            r6.f729d.mo8762j();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
            return;
         */
        public void close() {
            if (f726c || !Thread.holdsLock(Http2Stream.this)) {
                synchronized (Http2Stream.this) {
                    if (this.f727a) {
                    }
                }
            } else {
                throw new AssertionError();
            }
        }
    }

    /* renamed from: c.a.e.i$b */
    /* compiled from: Http2Stream */
    private final class C0506b implements Source {

        /* renamed from: c */
        static final /* synthetic */ boolean f731c = (!Http2Stream.class.desiredAssertionStatus());

        /* renamed from: a */
        boolean f732a;

        /* renamed from: b */
        boolean f733b;

        /* renamed from: e */
        private final Buffer f735e = new Buffer();

        /* renamed from: f */
        private final Buffer f736f = new Buffer();

        /* renamed from: g */
        private final long f737g;

        C0506b(long j) {
            this.f737g = j;
        }

        /* renamed from: a */
        public long mo8592a(Buffer cVar, long j) {
            long a;
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (Http2Stream.this) {
                m884b();
                m885c();
                if (this.f736f.mo17648b() == 0) {
                    a = -1;
                } else {
                    a = this.f736f.mo8592a(cVar, Math.min(j, this.f736f.mo17648b()));
                    Http2Stream.this.f714a += a;
                    if (Http2Stream.this.f714a >= ((long) (Http2Stream.this.f717d.f651l.mo8803d() / 2))) {
                        Http2Stream.this.f717d.mo8710a(Http2Stream.this.f716c, Http2Stream.this.f714a);
                        Http2Stream.this.f714a = 0;
                    }
                    synchronized (Http2Stream.this.f717d) {
                        Http2Stream.this.f717d.f649j += a;
                        if (Http2Stream.this.f717d.f649j >= ((long) (Http2Stream.this.f717d.f651l.mo8803d() / 2))) {
                            Http2Stream.this.f717d.mo8710a(0, Http2Stream.this.f717d.f649j);
                            Http2Stream.this.f717d.f649j = 0;
                        }
                    }
                }
            }
            return a;
        }

        /* renamed from: b */
        private void m884b() {
            Http2Stream.this.f719f.mo17629c();
            while (this.f736f.mo17648b() == 0 && !this.f733b && !this.f732a && Http2Stream.this.f721h == null) {
                try {
                    Http2Stream.this.mo8764l();
                } finally {
                    Http2Stream.this.f719f.mo8768b();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8765a(BufferedSource eVar, long j) {
            boolean z;
            boolean z2;
            boolean z3;
            if (f731c || !Thread.holdsLock(Http2Stream.this)) {
                while (j > 0) {
                    synchronized (Http2Stream.this) {
                        z = this.f733b;
                        z2 = this.f736f.mo17648b() + j > this.f737g;
                    }
                    if (z2) {
                        eVar.mo17671g(j);
                        Http2Stream.this.mo8752b(ErrorCode.FLOW_CONTROL_ERROR);
                        return;
                    } else if (z) {
                        eVar.mo17671g(j);
                        return;
                    } else {
                        long a = eVar.mo8592a(this.f735e, j);
                        if (a == -1) {
                            throw new EOFException();
                        }
                        j -= a;
                        synchronized (Http2Stream.this) {
                            if (this.f736f.mo17648b() == 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            this.f736f.mo17636a((Source) this.f735e);
                            if (z3) {
                                Http2Stream.this.notifyAll();
                            }
                        }
                    }
                }
                return;
            }
            throw new AssertionError();
        }

        /* renamed from: a */
        public Timeout mo8593a() {
            return Http2Stream.this.f719f;
        }

        public void close() {
            synchronized (Http2Stream.this) {
                this.f732a = true;
                this.f736f.mo17690s();
                Http2Stream.this.notifyAll();
            }
            Http2Stream.this.mo8762j();
        }

        /* renamed from: c */
        private void m885c() {
            if (this.f732a) {
                throw new IOException("stream closed");
            } else if (Http2Stream.this.f721h != null) {
                throw new StreamResetException(Http2Stream.this.f721h);
            }
        }
    }

    /* renamed from: c.a.e.i$c */
    /* compiled from: Http2Stream */
    class C0507c extends AsyncTimeout {
        C0507c() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo8767a() {
            Http2Stream.this.mo8752b(ErrorCode.CANCEL);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public IOException mo8766a(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        /* renamed from: b */
        public void mo8768b() {
            if (mo17627a_()) {
                throw mo8766a(null);
            }
        }
    }

    Http2Stream(int i, Http2Connection gVar, boolean z, boolean z2, List<Header> list) {
        if (gVar == null) {
            throw new NullPointerException("connection == null");
        } else if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        } else {
            this.f716c = i;
            this.f717d = gVar;
            this.f715b = (long) gVar.f652m.mo8803d();
            this.f725m = new C0506b((long) gVar.f651l.mo8803d());
            this.f718e = new C0505a();
            this.f725m.f733b = z2;
            this.f718e.f728b = z;
            this.f722j = list;
        }
    }

    /* renamed from: a */
    public int mo8747a() {
        return this.f716c;
    }

    /* renamed from: b */
    public synchronized boolean mo8753b() {
        boolean z = false;
        synchronized (this) {
            if (this.f721h == null) {
                if ((!this.f725m.f733b && !this.f725m.f732a) || ((!this.f718e.f728b && !this.f718e.f727a) || !this.f724l)) {
                    z = true;
                }
            }
        }
        return z;
    }

    /* renamed from: c */
    public boolean mo8755c() {
        boolean z;
        if ((this.f716c & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        return this.f717d.f641b == z;
    }

    /* renamed from: d */
    public synchronized List<Header> mo8756d() {
        List<Header> list;
        if (!mo8755c()) {
            throw new IllegalStateException("servers cannot read response headers");
        }
        this.f719f.mo17629c();
        while (this.f723k == null && this.f721h == null) {
            try {
                mo8764l();
            } catch (Throwable th) {
                this.f719f.mo8768b();
                throw th;
            }
        }
        this.f719f.mo8768b();
        list = this.f723k;
        if (list != null) {
            this.f723k = null;
        } else {
            throw new StreamResetException(this.f721h);
        }
        return list;
    }

    /* renamed from: e */
    public Timeout mo8757e() {
        return this.f719f;
    }

    /* renamed from: f */
    public Timeout mo8758f() {
        return this.f720g;
    }

    /* renamed from: g */
    public Source mo8759g() {
        return this.f725m;
    }

    /* renamed from: h */
    public Sink mo8760h() {
        synchronized (this) {
            if (!this.f724l && !mo8755c()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f718e;
    }

    /* renamed from: a */
    public void mo8749a(ErrorCode bVar) {
        if (m862d(bVar)) {
            this.f717d.mo8723b(this.f716c, bVar);
        }
    }

    /* renamed from: b */
    public void mo8752b(ErrorCode bVar) {
        if (m862d(bVar)) {
            this.f717d.mo8711a(this.f716c, bVar);
        }
    }

    /* renamed from: d */
    private boolean m862d(ErrorCode bVar) {
        if (f713i || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (this.f721h != null) {
                    return false;
                }
                if (this.f725m.f733b && this.f718e.f728b) {
                    return false;
                }
                this.f721h = bVar;
                notifyAll();
                this.f717d.mo8721b(this.f716c);
                return true;
            }
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8751a(List<Header> list) {
        boolean z = true;
        if (f713i || !Thread.holdsLock(this)) {
            synchronized (this) {
                this.f724l = true;
                if (this.f723k == null) {
                    this.f723k = list;
                    z = mo8753b();
                    notifyAll();
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(this.f723k);
                    arrayList.add(null);
                    arrayList.addAll(list);
                    this.f723k = arrayList;
                }
            }
            if (!z) {
                this.f717d.mo8721b(this.f716c);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8750a(BufferedSource eVar, int i) {
        if (f713i || !Thread.holdsLock(this)) {
            this.f725m.mo8765a(eVar, (long) i);
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public void mo8761i() {
        boolean b;
        if (f713i || !Thread.holdsLock(this)) {
            synchronized (this) {
                this.f725m.f733b = true;
                b = mo8753b();
                notifyAll();
            }
            if (!b) {
                this.f717d.mo8721b(this.f716c);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public synchronized void mo8754c(ErrorCode bVar) {
        if (this.f721h == null) {
            this.f721h = bVar;
            notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo8762j() {
        boolean z;
        boolean b;
        if (f713i || !Thread.holdsLock(this)) {
            synchronized (this) {
                z = !this.f725m.f733b && this.f725m.f732a && (this.f718e.f728b || this.f718e.f727a);
                b = mo8753b();
            }
            if (z) {
                mo8749a(ErrorCode.CANCEL);
            } else if (!b) {
                this.f717d.mo8721b(this.f716c);
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8748a(long j) {
        this.f715b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public void mo8763k() {
        if (this.f718e.f727a) {
            throw new IOException("stream closed");
        } else if (this.f718e.f728b) {
            throw new IOException("stream finished");
        } else if (this.f721h != null) {
            throw new StreamResetException(this.f721h);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public void mo8764l() {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
    }
}
