package p018c.p019a.p021b;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import p018c.Address;
import p018c.ConnectionPool;
import p018c.OkHttpClient;
import p018c.Route;
import p018c.p019a.Internal;
import p018c.p019a.Util;
import p018c.p019a.p022c.HttpCodec;
import p018c.p019a.p024e.ConnectionShutdownException;
import p018c.p019a.p024e.ErrorCode;
import p018c.p019a.p024e.StreamResetException;

/* renamed from: c.a.b.g */
public final class StreamAllocation {

    /* renamed from: b */
    static final /* synthetic */ boolean f514b = (!StreamAllocation.class.desiredAssertionStatus());

    /* renamed from: a */
    public final Address f515a;

    /* renamed from: c */
    private Route f516c;

    /* renamed from: d */
    private final ConnectionPool f517d;

    /* renamed from: e */
    private final Object f518e;

    /* renamed from: f */
    private final RouteSelector f519f;

    /* renamed from: g */
    private int f520g;

    /* renamed from: h */
    private RealConnection f521h;

    /* renamed from: i */
    private boolean f522i;

    /* renamed from: j */
    private boolean f523j;

    /* renamed from: k */
    private HttpCodec f524k;

    /* renamed from: c.a.b.g$a */
    /* compiled from: StreamAllocation */
    public static final class C0475a extends WeakReference<StreamAllocation> {

        /* renamed from: a */
        public final Object f525a;

        C0475a(StreamAllocation gVar, Object obj) {
            super(gVar);
            this.f525a = obj;
        }
    }

    public StreamAllocation(ConnectionPool jVar, Address aVar, Object obj) {
        this.f517d = jVar;
        this.f515a = aVar;
        this.f519f = new RouteSelector(aVar, m629f());
        this.f518e = obj;
    }

    /* renamed from: a */
    public HttpCodec mo8657a(OkHttpClient uVar, boolean z) {
        try {
            HttpCodec a = m626a(uVar.mo8986a(), uVar.mo8987b(), uVar.mo8988c(), uVar.mo9004s(), z).mo8637a(uVar, this);
            synchronized (this.f517d) {
                this.f524k = a;
            }
            return a;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    /* renamed from: a */
    private RealConnection m626a(int i, int i2, int i3, boolean z, boolean z2) {
        RealConnection a;
        while (true) {
            a = m625a(i, i2, i3, z);
            synchronized (this.f517d) {
                if (a.f490b != 0) {
                    if (a.mo8643a(z2)) {
                        break;
                    }
                    mo8664d();
                } else {
                    break;
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    private RealConnection m625a(int i, int i2, int i3, boolean z) {
        RealConnection cVar;
        RealConnection cVar2;
        Socket socket;
        synchronized (this.f517d) {
            if (this.f522i) {
                throw new IllegalStateException("released");
            } else if (this.f524k != null) {
                throw new IllegalStateException("codec != null");
            } else if (this.f523j) {
                throw new IOException("Canceled");
            } else {
                cVar = this.f521h;
                if (cVar == null || cVar.f489a) {
                    Internal.f418a.mo8583a(this.f517d, this.f515a, this);
                    if (this.f521h != null) {
                        cVar = this.f521h;
                    } else {
                        Route abVar = this.f516c;
                        if (abVar == null) {
                            abVar = this.f519f.mo8655b();
                        }
                        synchronized (this.f517d) {
                            this.f516c = abVar;
                            this.f520g = 0;
                            cVar2 = new RealConnection(this.f517d, abVar);
                            mo8658a(cVar2);
                            if (this.f523j) {
                                throw new IOException("Canceled");
                            }
                        }
                        cVar2.mo8639a(i, i2, i3, z);
                        m629f().mo8649b(cVar2.mo8638a());
                        synchronized (this.f517d) {
                            Internal.f418a.mo8590b(this.f517d, cVar2);
                            if (cVar2.mo8646d()) {
                                Socket b = Internal.f418a.mo8589b(this.f517d, this.f515a, this);
                                cVar = this.f521h;
                                socket = b;
                            } else {
                                cVar = cVar2;
                                socket = null;
                            }
                        }
                        Util.m653a(socket);
                    }
                }
            }
        }
        return cVar;
    }

    /* renamed from: a */
    public void mo8660a(boolean z, HttpCodec cVar) {
        Socket a;
        synchronized (this.f517d) {
            if (cVar != null) {
                if (cVar == this.f524k) {
                    if (!z) {
                        this.f521h.f490b++;
                    }
                    a = m627a(z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.f524k + " but was " + cVar);
        }
        Util.m653a(a);
    }

    /* renamed from: a */
    public HttpCodec mo8656a() {
        HttpCodec cVar;
        synchronized (this.f517d) {
            cVar = this.f524k;
        }
        return cVar;
    }

    /* renamed from: f */
    private RouteDatabase m629f() {
        return Internal.f418a.mo8584a(this.f517d);
    }

    /* renamed from: b */
    public synchronized RealConnection mo8661b() {
        return this.f521h;
    }

    /* renamed from: c */
    public void mo8663c() {
        Socket a;
        synchronized (this.f517d) {
            a = m627a(false, true, false);
        }
        Util.m653a(a);
    }

    /* renamed from: d */
    public void mo8664d() {
        Socket a;
        synchronized (this.f517d) {
            a = m627a(true, false, false);
        }
        Util.m653a(a);
    }

    /* renamed from: a */
    private Socket m627a(boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (f514b || Thread.holdsLock(this.f517d)) {
            if (z3) {
                this.f524k = null;
            }
            if (z2) {
                this.f522i = true;
            }
            if (this.f521h == null) {
                return null;
            }
            if (z) {
                this.f521h.f489a = true;
            }
            if (this.f524k != null) {
                return null;
            }
            if (!this.f522i && !this.f521h.f489a) {
                return null;
            }
            m628c(this.f521h);
            if (this.f521h.f492d.isEmpty()) {
                this.f521h.f493e = System.nanoTime();
                if (Internal.f418a.mo8588a(this.f517d, this.f521h)) {
                    socket = this.f521h.mo8644b();
                    this.f521h = null;
                    return socket;
                }
            }
            socket = null;
            this.f521h = null;
            return socket;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public void mo8659a(IOException iOException) {
        Socket a;
        boolean z = false;
        synchronized (this.f517d) {
            if (iOException instanceof StreamResetException) {
                StreamResetException oVar = (StreamResetException) iOException;
                if (oVar.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.f520g++;
                }
                if (oVar.errorCode != ErrorCode.REFUSED_STREAM || this.f520g > 1) {
                    this.f516c = null;
                }
                a = m627a(z, false, true);
            } else {
                if (this.f521h != null && (!this.f521h.mo8646d() || (iOException instanceof ConnectionShutdownException))) {
                    if (this.f521h.f490b == 0) {
                        if (!(this.f516c == null || iOException == null)) {
                            this.f519f.mo8653a(this.f516c, iOException);
                        }
                        this.f516c = null;
                    }
                }
                a = m627a(z, false, true);
            }
            z = true;
            a = m627a(z, false, true);
        }
        Util.m653a(a);
    }

    /* renamed from: a */
    public void mo8658a(RealConnection cVar) {
        if (!f514b && !Thread.holdsLock(this.f517d)) {
            throw new AssertionError();
        } else if (this.f521h != null) {
            throw new IllegalStateException();
        } else {
            this.f521h = cVar;
            cVar.f492d.add(new C0475a(this, this.f518e));
        }
    }

    /* renamed from: c */
    private void m628c(RealConnection cVar) {
        int size = cVar.f492d.size();
        for (int i = 0; i < size; i++) {
            if (cVar.f492d.get(i).get() == this) {
                cVar.f492d.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    public Socket mo8662b(RealConnection cVar) {
        if (!f514b && !Thread.holdsLock(this.f517d)) {
            throw new AssertionError();
        } else if (this.f524k == null && this.f521h.f492d.size() == 1) {
            Socket a = m627a(true, false, false);
            this.f521h = cVar;
            cVar.f492d.add(this.f521h.f492d.get(0));
            return a;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: e */
    public boolean mo8665e() {
        return this.f516c != null || this.f519f.mo8654a();
    }

    public String toString() {
        RealConnection b = mo8661b();
        return b != null ? b.toString() : this.f515a.toString();
    }
}
