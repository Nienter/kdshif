package p033d;

/* renamed from: d.m */
final class RealBufferedSink implements BufferedSink {

    /* renamed from: a */
    public final Buffer f2677a = new Buffer();

    /* renamed from: b */
    public final Sink f2678b;

    /* renamed from: c */
    boolean f2679c;

    RealBufferedSink(Sink rVar) {
        if (rVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.f2678b = rVar;
    }

    /* renamed from: c */
    public Buffer mo17653c() {
        return this.f2677a;
    }

    /* renamed from: a_ */
    public void mo8624a_(Buffer cVar, long j) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo8624a_(cVar, j);
        mo17694v();
    }

    /* renamed from: b */
    public BufferedSink mo17652b(String str) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo17652b(str);
        return mo17694v();
    }

    /* renamed from: c */
    public BufferedSink mo17655c(byte[] bArr) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo17655c(bArr);
        return mo17694v();
    }

    /* renamed from: c */
    public BufferedSink mo17656c(byte[] bArr, int i, int i2) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo17656c(bArr, i, i2);
        return mo17694v();
    }

    /* renamed from: i */
    public BufferedSink mo17677i(int i) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo17677i(i);
        return mo17694v();
    }

    /* renamed from: h */
    public BufferedSink mo17674h(int i) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo17674h(i);
        return mo17694v();
    }

    /* renamed from: g */
    public BufferedSink mo17670g(int i) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo17670g(i);
        return mo17694v();
    }

    /* renamed from: k */
    public BufferedSink mo17681k(long j) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo17681k(j);
        return mo17694v();
    }

    /* renamed from: j */
    public BufferedSink mo17680j(long j) {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        this.f2677a.mo17680j(j);
        return mo17694v();
    }

    /* renamed from: v */
    public BufferedSink mo17694v() {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        long g = this.f2677a.mo17669g();
        if (g > 0) {
            this.f2678b.mo8624a_(this.f2677a, g);
        }
        return this;
    }

    public void flush() {
        if (this.f2679c) {
            throw new IllegalStateException("closed");
        }
        if (this.f2677a.f2657b > 0) {
            this.f2678b.mo8624a_(this.f2677a, this.f2677a.f2657b);
        }
        this.f2678b.flush();
    }

    public void close() {
        if (!this.f2679c) {
            Throwable th = null;
            try {
                if (this.f2677a.f2657b > 0) {
                    this.f2678b.mo8624a_(this.f2677a, this.f2677a.f2657b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.f2678b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.f2679c = true;
            if (th != null) {
                C1658u.m3673a(th);
            }
        }
    }

    /* renamed from: a */
    public Timeout mo8695a() {
        return this.f2678b.mo8695a();
    }

    public String toString() {
        return "buffer(" + this.f2678b + ")";
    }
}
