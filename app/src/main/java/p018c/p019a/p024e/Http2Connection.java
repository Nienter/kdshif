package p018c.p019a.p024e;

import android.support.p001v4.internal.view.SupportMenu;
import android.support.p004v7.widget.ActivityChooserView;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p018c.p019a.NamedRunnable;
import p018c.p019a.Util;
import p018c.p019a.p024e.Http2Reader;
import p018c.p019a.p026g.Platform;
import p033d.Buffer;
import p033d.BufferedSink;
import p033d.BufferedSource;
import p033d.ByteString;

/* renamed from: c.a.e.g */
public final class Http2Connection implements Closeable {

    /* renamed from: a */
    static final ExecutorService f639a = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.m650a("OkHttp Http2Connection", true));

    /* renamed from: s */
    static final /* synthetic */ boolean f640s;

    /* renamed from: b */
    final boolean f641b;

    /* renamed from: c */
    final C0497b f642c;

    /* renamed from: d */
    final Map<Integer, Http2Stream> f643d = new LinkedHashMap();

    /* renamed from: e */
    final String f644e;

    /* renamed from: f */
    int f645f;

    /* renamed from: g */
    int f646g;

    /* renamed from: h */
    boolean f647h;

    /* renamed from: i */
    final PushObserver f648i;

    /* renamed from: j */
    long f649j = 0;

    /* renamed from: k */
    long f650k;

    /* renamed from: l */
    C0510n f651l = new C0510n();

    /* renamed from: m */
    final C0510n f652m = new C0510n();

    /* renamed from: n */
    boolean f653n = false;

    /* renamed from: o */
    final Socket f654o;

    /* renamed from: p */
    final Http2Writer f655p;

    /* renamed from: q */
    final C0499c f656q;

    /* renamed from: r */
    final Set<Integer> f657r = new LinkedHashSet();

    /* renamed from: t */
    private final ExecutorService f658t;

    /* renamed from: u */
    private Map<Integer, Ping> f659u;

    /* renamed from: v */
    private int f660v;

    /* renamed from: c.a.e.g$a */
    /* compiled from: Http2Connection */
    public static class C0496a {

        /* renamed from: a */
        Socket f687a;

        /* renamed from: b */
        String f688b;

        /* renamed from: c */
        BufferedSource f689c;

        /* renamed from: d */
        BufferedSink f690d;

        /* renamed from: e */
        C0497b f691e = C0497b.f694f;

        /* renamed from: f */
        PushObserver f692f = PushObserver.f756a;

        /* renamed from: g */
        boolean f693g;

        public C0496a(boolean z) {
            this.f693g = z;
        }

        /* renamed from: a */
        public C0496a mo8732a(Socket socket, String str, BufferedSource eVar, BufferedSink dVar) {
            this.f687a = socket;
            this.f688b = str;
            this.f689c = eVar;
            this.f690d = dVar;
            return this;
        }

        /* renamed from: a */
        public C0496a mo8731a(C0497b bVar) {
            this.f691e = bVar;
            return this;
        }

        /* renamed from: a */
        public Http2Connection mo8733a() {
            return new Http2Connection(this);
        }
    }

    /* renamed from: c.a.e.g$b */
    /* compiled from: Http2Connection */
    public static abstract class C0497b {

        /* renamed from: f */
        public static final C0497b f694f = new C0497b() {
            /* renamed from: a */
            public void mo8641a(Http2Stream iVar) {
                iVar.mo8749a(ErrorCode.REFUSED_STREAM);
            }
        };

        /* renamed from: a */
        public abstract void mo8641a(Http2Stream iVar);

        /* renamed from: a */
        public void mo8640a(Http2Connection gVar) {
        }
    }

    /* renamed from: c.a.e.g$c */
    /* compiled from: Http2Connection */
    class C0499c extends NamedRunnable implements Http2Reader.C0504b {

        /* renamed from: a */
        final Http2Reader f695a;

        C0499c(Http2Reader hVar) {
            super("OkHttp %s", Http2Connection.this.f644e);
            this.f695a = hVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo8633b() {
            ErrorCode bVar;
            Throwable th;
            ErrorCode bVar2 = ErrorCode.INTERNAL_ERROR;
            ErrorCode bVar3 = ErrorCode.INTERNAL_ERROR;
            try {
                this.f695a.mo8744a((Http2Reader.C0504b) this);
                do {
                } while (this.f695a.mo8745a(false, (Http2Reader.C0504b) this));
                try {
                    Http2Connection.this.mo8718a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
                } catch (IOException e) {
                }
                Util.m652a((Closeable) this.f695a);
            } catch (IOException e2) {
                bVar = ErrorCode.PROTOCOL_ERROR;
                try {
                    Http2Connection.this.mo8718a(bVar, ErrorCode.PROTOCOL_ERROR);
                } catch (IOException e3) {
                }
                Util.m652a((Closeable) this.f695a);
            } catch (Throwable th2) {
                th = th2;
                Http2Connection.this.mo8718a(bVar, bVar3);
                Util.m652a((Closeable) this.f695a);
                throw th;
            }
        }

        /* renamed from: a */
        public void mo8742a(boolean z, int i, BufferedSource eVar, int i2) {
            if (Http2Connection.this.mo8730d(i)) {
                Http2Connection.this.mo8712a(i, eVar, i2, z);
                return;
            }
            Http2Stream a = Http2Connection.this.mo8708a(i);
            if (a == null) {
                Http2Connection.this.mo8711a(i, ErrorCode.PROTOCOL_ERROR);
                eVar.mo17671g((long) i2);
                return;
            }
            a.mo8750a(eVar, i2);
            if (z) {
                a.mo8761i();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0072, code lost:
            r0.mo8751a(r12);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0075, code lost:
            if (r9 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0077, code lost:
            r0.mo8761i();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* renamed from: a */
        public void mo8741a(boolean z, int i, int i2, List<Header> list) {
            if (Http2Connection.this.mo8730d(i)) {
                Http2Connection.this.mo8714a(i, list, z);
                return;
            }
            synchronized (Http2Connection.this) {
                if (!Http2Connection.this.f647h) {
                    Http2Stream a = Http2Connection.this.mo8708a(i);
                    if (a == null) {
                        if (i > Http2Connection.this.f645f) {
                            if (i % 2 != Http2Connection.this.f646g % 2) {
                                final Http2Stream iVar = new Http2Stream(i, Http2Connection.this, false, z, list);
                                Http2Connection.this.f645f = i;
                                Http2Connection.this.f643d.put(Integer.valueOf(i), iVar);
                                Http2Connection.f639a.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{Http2Connection.this.f644e, Integer.valueOf(i)}) {
                                    /* renamed from: b */
                                    public void mo8633b() {
                                        try {
                                            Http2Connection.this.f642c.mo8641a(iVar);
                                        } catch (IOException e) {
                                            Platform.m981b().mo8816a(4, "Http2Connection.Listener failure for " + Http2Connection.this.f644e, (Throwable) e);
                                            try {
                                                iVar.mo8749a(ErrorCode.PROTOCOL_ERROR);
                                            } catch (IOException e2) {
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }

        /* renamed from: a */
        public void mo8738a(int i, ErrorCode bVar) {
            if (Http2Connection.this.mo8730d(i)) {
                Http2Connection.this.mo8727c(i, bVar);
                return;
            }
            Http2Stream b = Http2Connection.this.mo8721b(i);
            if (b != null) {
                b.mo8754c(bVar);
            }
        }

        /* renamed from: a */
        public void mo8743a(boolean z, C0510n nVar) {
            Http2Stream[] iVarArr;
            long j;
            synchronized (Http2Connection.this) {
                int d = Http2Connection.this.f652m.mo8803d();
                if (z) {
                    Http2Connection.this.f652m.mo8796a();
                }
                Http2Connection.this.f652m.mo8797a(nVar);
                m819a(nVar);
                int d2 = Http2Connection.this.f652m.mo8803d();
                if (d2 == -1 || d2 == d) {
                    iVarArr = null;
                    j = 0;
                } else {
                    long j2 = (long) (d2 - d);
                    if (!Http2Connection.this.f653n) {
                        Http2Connection.this.mo8716a(j2);
                        Http2Connection.this.f653n = true;
                    }
                    if (!Http2Connection.this.f643d.isEmpty()) {
                        j = j2;
                        iVarArr = (Http2Stream[]) Http2Connection.this.f643d.values().toArray(new Http2Stream[Http2Connection.this.f643d.size()]);
                    } else {
                        j = j2;
                        iVarArr = null;
                    }
                }
                Http2Connection.f639a.execute(new NamedRunnable("OkHttp %s settings", Http2Connection.this.f644e) {
                    /* renamed from: b */
                    public void mo8633b() {
                        Http2Connection.this.f642c.mo8640a(Http2Connection.this);
                    }
                });
            }
            if (iVarArr != null && j != 0) {
                for (Http2Stream iVar : iVarArr) {
                    synchronized (iVar) {
                        iVar.mo8748a(j);
                    }
                }
            }
        }

        /* renamed from: a */
        private void m819a(final C0510n nVar) {
            Http2Connection.f639a.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{Http2Connection.this.f644e}) {
                /* renamed from: b */
                public void mo8633b() {
                    try {
                        Http2Connection.this.f655p.mo8776a(nVar);
                    } catch (IOException e) {
                    }
                }
            });
        }

        /* renamed from: a */
        public void mo8734a() {
        }

        /* renamed from: a */
        public void mo8740a(boolean z, int i, int i2) {
            if (z) {
                Ping c = Http2Connection.this.mo8725c(i);
                if (c != null) {
                    c.mo8789b();
                    return;
                }
                return;
            }
            Http2Connection.this.mo8720a(true, i, i2, (Ping) null);
        }

        /* renamed from: a */
        public void mo8739a(int i, ErrorCode bVar, ByteString fVar) {
            Http2Stream[] iVarArr;
            if (fVar.size() > 0) {
            }
            synchronized (Http2Connection.this) {
                iVarArr = (Http2Stream[]) Http2Connection.this.f643d.values().toArray(new Http2Stream[Http2Connection.this.f643d.size()]);
                Http2Connection.this.f647h = true;
            }
            for (Http2Stream iVar : iVarArr) {
                if (iVar.mo8747a() > i && iVar.mo8755c()) {
                    iVar.mo8754c(ErrorCode.REFUSED_STREAM);
                    Http2Connection.this.mo8721b(iVar.mo8747a());
                }
            }
        }

        /* renamed from: a */
        public void mo8737a(int i, long j) {
            if (i == 0) {
                synchronized (Http2Connection.this) {
                    Http2Connection.this.f650k += j;
                    Http2Connection.this.notifyAll();
                }
                return;
            }
            Http2Stream a = Http2Connection.this.mo8708a(i);
            if (a != null) {
                synchronized (a) {
                    a.mo8748a(j);
                }
            }
        }

        /* renamed from: a */
        public void mo8735a(int i, int i2, int i3, boolean z) {
        }

        /* renamed from: a */
        public void mo8736a(int i, int i2, List<Header> list) {
            Http2Connection.this.mo8713a(i2, list);
        }
    }

    static {
        boolean z;
        if (!Http2Connection.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        f640s = z;
    }

    Http2Connection(C0496a aVar) {
        int i = 2;
        this.f648i = aVar.f692f;
        this.f641b = aVar.f693g;
        this.f642c = aVar.f691e;
        this.f646g = aVar.f693g ? 1 : 2;
        if (aVar.f693g) {
            this.f646g += 2;
        }
        this.f660v = aVar.f693g ? 1 : i;
        if (aVar.f693g) {
            this.f651l.mo8795a(7, 16777216);
        }
        this.f644e = aVar.f688b;
        this.f658t = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.m650a(Util.m646a("OkHttp %s Push Observer", this.f644e), true));
        this.f652m.mo8795a(7, SupportMenu.USER_MASK);
        this.f652m.mo8795a(5, 16384);
        this.f650k = (long) this.f652m.mo8803d();
        this.f654o = aVar.f687a;
        this.f655p = new Http2Writer(aVar.f690d, this.f641b);
        this.f656q = new C0499c(new Http2Reader(aVar.f689c, this.f641b));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized Http2Stream mo8708a(int i) {
        return this.f643d.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized Http2Stream mo8721b(int i) {
        Http2Stream remove;
        remove = this.f643d.remove(Integer.valueOf(i));
        notifyAll();
        return remove;
    }

    /* renamed from: a */
    public synchronized int mo8707a() {
        return this.f652m.mo8802c(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    /* renamed from: a */
    public Http2Stream mo8709a(List<Header> list, boolean z) {
        return m782b(0, list, z);
    }

    /* renamed from: b */
    private Http2Stream m782b(int i, List<Header> list, boolean z) {
        int i2;
        Http2Stream iVar;
        boolean z2 = false;
        boolean z3 = !z;
        synchronized (this.f655p) {
            synchronized (this) {
                if (this.f647h) {
                    throw new ConnectionShutdownException();
                }
                i2 = this.f646g;
                this.f646g += 2;
                iVar = new Http2Stream(i2, this, z3, false, list);
                if (!z || this.f650k == 0 || iVar.f715b == 0) {
                    z2 = true;
                }
                if (iVar.mo8753b()) {
                    this.f643d.put(Integer.valueOf(i2), iVar);
                }
            }
            if (i == 0) {
                this.f655p.mo8778a(z3, i2, i, list);
            } else if (this.f641b) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            } else {
                this.f655p.mo8772a(i, i2, list);
            }
        }
        if (z2) {
            this.f655p.mo8781b();
        }
        return iVar;
    }

    /* renamed from: a */
    public void mo8715a(int i, boolean z, Buffer cVar, long j) {
        int min;
        boolean z2;
        if (j == 0) {
            this.f655p.mo8779a(z, i, cVar, 0);
            return;
        }
        while (j > 0) {
            synchronized (this) {
                while (this.f650k <= 0) {
                    try {
                        if (!this.f643d.containsKey(Integer.valueOf(i))) {
                            throw new IOException("stream closed");
                        }
                        wait();
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                min = Math.min((int) Math.min(j, this.f650k), this.f655p.mo8783c());
                this.f650k -= (long) min;
            }
            j -= (long) min;
            Http2Writer jVar = this.f655p;
            if (!z || j != 0) {
                z2 = false;
            } else {
                z2 = true;
            }
            jVar.mo8779a(z2, i, cVar, min);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8716a(long j) {
        this.f650k += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8711a(int i, ErrorCode bVar) {
        final int i2 = i;
        final ErrorCode bVar2 = bVar;
        f639a.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{this.f644e, Integer.valueOf(i)}) {
            /* renamed from: b */
            public void mo8633b() {
                try {
                    Http2Connection.this.mo8723b(i2, bVar2);
                } catch (IOException e) {
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo8723b(int i, ErrorCode bVar) {
        this.f655p.mo8774a(i, bVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8710a(int i, long j) {
        final int i2 = i;
        final long j2 = j;
        f639a.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.f644e, Integer.valueOf(i)}) {
            /* renamed from: b */
            public void mo8633b() {
                try {
                    Http2Connection.this.f655p.mo8773a(i2, j2);
                } catch (IOException e) {
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8720a(boolean z, int i, int i2, Ping lVar) {
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final Ping lVar2 = lVar;
        f639a.execute(new NamedRunnable("OkHttp %s ping %08x%08x", new Object[]{this.f644e, Integer.valueOf(i), Integer.valueOf(i2)}) {
            /* renamed from: b */
            public void mo8633b() {
                try {
                    Http2Connection.this.mo8724b(z2, i3, i4, lVar2);
                } catch (IOException e) {
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo8724b(boolean z, int i, int i2, Ping lVar) {
        synchronized (this.f655p) {
            if (lVar != null) {
                lVar.mo8788a();
            }
            this.f655p.mo8777a(z, i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public synchronized Ping mo8725c(int i) {
        return this.f659u != null ? this.f659u.remove(Integer.valueOf(i)) : null;
    }

    /* renamed from: b */
    public void mo8722b() {
        this.f655p.mo8781b();
    }

    /* renamed from: a */
    public void mo8717a(ErrorCode bVar) {
        synchronized (this.f655p) {
            synchronized (this) {
                if (!this.f647h) {
                    this.f647h = true;
                    int i = this.f645f;
                    this.f655p.mo8775a(i, bVar, Util.f526a);
                }
            }
        }
    }

    public void close() {
        mo8718a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8718a(ErrorCode bVar, ErrorCode bVar2) {
        IOException iOException;
        Http2Stream[] iVarArr;
        Ping[] lVarArr;
        if (f640s || !Thread.holdsLock(this)) {
            try {
                mo8717a(bVar);
                iOException = null;
            } catch (IOException e) {
                iOException = e;
            }
            synchronized (this) {
                if (!this.f643d.isEmpty()) {
                    this.f643d.clear();
                    iVarArr = (Http2Stream[]) this.f643d.values().toArray(new Http2Stream[this.f643d.size()]);
                } else {
                    iVarArr = null;
                }
                if (this.f659u != null) {
                    this.f659u = null;
                    lVarArr = (Ping[]) this.f659u.values().toArray(new Ping[this.f659u.size()]);
                } else {
                    lVarArr = null;
                }
            }
            if (iVarArr != null) {
                IOException iOException2 = iOException;
                for (Http2Stream a : iVarArr) {
                    try {
                        a.mo8749a(bVar2);
                    } catch (IOException e2) {
                        if (iOException2 != null) {
                            iOException2 = e2;
                        }
                    }
                }
                iOException = iOException2;
            }
            if (lVarArr != null) {
                for (Ping c : lVarArr) {
                    c.mo8790c();
                }
            }
            try {
                this.f655p.close();
                e = iOException;
            } catch (IOException e3) {
                e = e3;
                if (iOException != null) {
                    e = iOException;
                }
            }
            try {
                this.f654o.close();
            } catch (IOException e4) {
                e = e4;
            }
            if (e != null) {
                throw e;
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    public void mo8726c() {
        mo8719a(true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8719a(boolean z) {
        if (z) {
            this.f655p.mo8769a();
            this.f655p.mo8782b(this.f651l);
            int d = this.f651l.mo8803d();
            if (d != 65535) {
                this.f655p.mo8773a(0, (long) (d - SupportMenu.USER_MASK));
            }
        }
        new Thread(this.f656q).start();
    }

    /* renamed from: d */
    public synchronized boolean mo8729d() {
        return this.f647h;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean mo8730d(int i) {
        return i != 0 && (i & 1) == 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8713a(int i, List<Header> list) {
        synchronized (this) {
            if (this.f657r.contains(Integer.valueOf(i))) {
                mo8711a(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.f657r.add(Integer.valueOf(i));
            final int i2 = i;
            final List<Header> list2 = list;
            this.f658t.execute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.f644e, Integer.valueOf(i)}) {
                /* renamed from: b */
                public void mo8633b() {
                    if (Http2Connection.this.f648i.mo8793a(i2, (List<Header>) list2)) {
                        try {
                            Http2Connection.this.f655p.mo8774a(i2, ErrorCode.CANCEL);
                            synchronized (Http2Connection.this) {
                                Http2Connection.this.f657r.remove(Integer.valueOf(i2));
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8714a(int i, List<Header> list, boolean z) {
        final int i2 = i;
        final List<Header> list2 = list;
        final boolean z2 = z;
        this.f658t.execute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.f644e, Integer.valueOf(i)}) {
            /* renamed from: b */
            public void mo8633b() {
                boolean a = Http2Connection.this.f648i.mo8794a(i2, list2, z2);
                if (a) {
                    try {
                        Http2Connection.this.f655p.mo8774a(i2, ErrorCode.CANCEL);
                    } catch (IOException e) {
                        return;
                    }
                }
                if (a || z2) {
                    synchronized (Http2Connection.this) {
                        Http2Connection.this.f657r.remove(Integer.valueOf(i2));
                    }
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8712a(int i, BufferedSource eVar, int i2, boolean z) {
        final Buffer cVar = new Buffer();
        eVar.mo17643a((long) i2);
        eVar.mo8592a(cVar, (long) i2);
        if (cVar.mo17648b() != ((long) i2)) {
            throw new IOException(cVar.mo17648b() + " != " + i2);
        }
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        this.f658t.execute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.f644e, Integer.valueOf(i)}) {
            /* renamed from: b */
            public void mo8633b() {
                try {
                    boolean a = Http2Connection.this.f648i.mo8792a(i3, cVar, i4, z2);
                    if (a) {
                        Http2Connection.this.f655p.mo8774a(i3, ErrorCode.CANCEL);
                    }
                    if (a || z2) {
                        synchronized (Http2Connection.this) {
                            Http2Connection.this.f657r.remove(Integer.valueOf(i3));
                        }
                    }
                } catch (IOException e) {
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo8727c(int i, ErrorCode bVar) {
        final int i2 = i;
        final ErrorCode bVar2 = bVar;
        this.f658t.execute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.f644e, Integer.valueOf(i)}) {
            /* renamed from: b */
            public void mo8633b() {
                Http2Connection.this.f648i.mo8791a(i2, bVar2);
                synchronized (Http2Connection.this) {
                    Http2Connection.this.f657r.remove(Integer.valueOf(i2));
                }
            }
        });
    }
}
