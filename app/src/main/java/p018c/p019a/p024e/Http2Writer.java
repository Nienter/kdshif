package p018c.p019a.p024e;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import p018c.p019a.Util;
import p018c.p019a.p024e.Hpack;
import p033d.Buffer;
import p033d.BufferedSink;

/* renamed from: c.a.e.j */
final class Http2Writer implements Closeable {

    /* renamed from: b */
    private static final Logger f739b = Logger.getLogger(Http2.class.getName());

    /* renamed from: a */
    final Hpack.C0487b f740a = new Hpack.C0487b(this.f743e);

    /* renamed from: c */
    private final BufferedSink f741c;

    /* renamed from: d */
    private final boolean f742d;

    /* renamed from: e */
    private final Buffer f743e = new Buffer();

    /* renamed from: f */
    private int f744f = 16384;

    /* renamed from: g */
    private boolean f745g;

    public Http2Writer(BufferedSink dVar, boolean z) {
        this.f741c = dVar;
        this.f742d = z;
    }

    /* renamed from: a */
    public synchronized void mo8769a() {
        if (this.f745g) {
            throw new IOException("closed");
        } else if (this.f742d) {
            if (f739b.isLoggable(Level.FINE)) {
                f739b.fine(Util.m646a(">> CONNECTION %s", Http2.f620a.hex()));
            }
            this.f741c.mo17655c(Http2.f620a.toByteArray());
            this.f741c.flush();
        }
    }

    /* renamed from: a */
    public synchronized void mo8776a(C0510n nVar) {
        if (this.f745g) {
            throw new IOException("closed");
        }
        this.f744f = nVar.mo8804d(this.f744f);
        if (nVar.mo8801c() != -1) {
            this.f740a.mo8703a(nVar.mo8801c());
        }
        mo8771a(0, 0, (byte) 4, (byte) 1);
        this.f741c.flush();
    }

    /* renamed from: a */
    public synchronized void mo8772a(int i, int i2, List<Header> list) {
        if (this.f745g) {
            throw new IOException("closed");
        }
        this.f740a.mo8706a(list);
        long b = this.f743e.mo17648b();
        int min = (int) Math.min((long) (this.f744f - 4), b);
        mo8771a(i, min + 4, (byte) 5, b == ((long) min) ? (byte) 4 : 0);
        this.f741c.mo17670g(Integer.MAX_VALUE & i2);
        this.f741c.mo8624a_(this.f743e, (long) min);
        if (b > ((long) min)) {
            m893b(i, b - ((long) min));
        }
    }

    /* renamed from: b */
    public synchronized void mo8781b() {
        if (this.f745g) {
            throw new IOException("closed");
        }
        this.f741c.flush();
    }

    /* renamed from: a */
    public synchronized void mo8778a(boolean z, int i, int i2, List<Header> list) {
        if (this.f745g) {
            throw new IOException("closed");
        }
        mo8780a(z, i, list);
    }

    /* renamed from: a */
    public synchronized void mo8774a(int i, ErrorCode bVar) {
        if (this.f745g) {
            throw new IOException("closed");
        } else if (bVar.httpCode == -1) {
            throw new IllegalArgumentException();
        } else {
            mo8771a(i, 4, (byte) 3, (byte) 0);
            this.f741c.mo17670g(bVar.httpCode);
            this.f741c.flush();
        }
    }

    /* renamed from: c */
    public int mo8783c() {
        return this.f744f;
    }

    /* renamed from: a */
    public synchronized void mo8779a(boolean z, int i, Buffer cVar, int i2) {
        if (this.f745g) {
            throw new IOException("closed");
        }
        byte b = 0;
        if (z) {
            b = (byte) 1;
        }
        mo8770a(i, b, cVar, i2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8770a(int i, byte b, Buffer cVar, int i2) {
        mo8771a(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.f741c.mo8624a_(cVar, (long) i2);
        }
    }

    /* renamed from: b */
    public synchronized void mo8782b(C0510n nVar) {
        int i;
        synchronized (this) {
            if (this.f745g) {
                throw new IOException("closed");
            }
            mo8771a(0, nVar.mo8799b() * 6, (byte) 4, (byte) 0);
            for (int i2 = 0; i2 < 10; i2++) {
                if (nVar.mo8798a(i2)) {
                    if (i2 == 4) {
                        i = 3;
                    } else if (i2 == 7) {
                        i = 4;
                    } else {
                        i = i2;
                    }
                    this.f741c.mo17674h(i);
                    this.f741c.mo17670g(nVar.mo8800b(i2));
                }
            }
            this.f741c.flush();
        }
    }

    /* renamed from: a */
    public synchronized void mo8777a(boolean z, int i, int i2) {
        byte b = 0;
        synchronized (this) {
            if (this.f745g) {
                throw new IOException("closed");
            }
            if (z) {
                b = 1;
            }
            mo8771a(0, 8, (byte) 6, b);
            this.f741c.mo17670g(i);
            this.f741c.mo17670g(i2);
            this.f741c.flush();
        }
    }

    /* renamed from: a */
    public synchronized void mo8775a(int i, ErrorCode bVar, byte[] bArr) {
        if (this.f745g) {
            throw new IOException("closed");
        } else if (bVar.httpCode == -1) {
            throw Http2.m770a("errorCode.httpCode == -1", new Object[0]);
        } else {
            mo8771a(0, bArr.length + 8, (byte) 7, (byte) 0);
            this.f741c.mo17670g(i);
            this.f741c.mo17670g(bVar.httpCode);
            if (bArr.length > 0) {
                this.f741c.mo17655c(bArr);
            }
            this.f741c.flush();
        }
    }

    /* renamed from: a */
    public synchronized void mo8773a(int i, long j) {
        if (this.f745g) {
            throw new IOException("closed");
        } else if (j == 0 || j > 2147483647L) {
            throw Http2.m770a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        } else {
            mo8771a(i, 4, (byte) 8, (byte) 0);
            this.f741c.mo17670g((int) j);
            this.f741c.flush();
        }
    }

    /* renamed from: a */
    public void mo8771a(int i, int i2, byte b, byte b2) {
        if (f739b.isLoggable(Level.FINE)) {
            f739b.fine(Http2.m772a(false, i, i2, b, b2));
        }
        if (i2 > this.f744f) {
            throw Http2.m770a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.f744f), Integer.valueOf(i2));
        } else if ((Integer.MIN_VALUE & i) != 0) {
            throw Http2.m770a("reserved bit set: %s", Integer.valueOf(i));
        } else {
            m892a(this.f741c, i2);
            this.f741c.mo17677i(b & 255);
            this.f741c.mo17677i(b2 & 255);
            this.f741c.mo17670g(Integer.MAX_VALUE & i);
        }
    }

    public synchronized void close() {
        this.f745g = true;
        this.f741c.close();
    }

    /* renamed from: a */
    private static void m892a(BufferedSink dVar, int i) {
        dVar.mo17677i((i >>> 16) & 255);
        dVar.mo17677i((i >>> 8) & 255);
        dVar.mo17677i(i & 255);
    }

    /* renamed from: b */
    private void m893b(int i, long j) {
        while (j > 0) {
            int min = (int) Math.min((long) this.f744f, j);
            j -= (long) min;
            mo8771a(i, min, (byte) 9, j == 0 ? (byte) 4 : 0);
            this.f741c.mo8624a_(this.f743e, (long) min);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8780a(boolean z, int i, List<Header> list) {
        if (this.f745g) {
            throw new IOException("closed");
        }
        this.f740a.mo8706a(list);
        long b = this.f743e.mo17648b();
        int min = (int) Math.min((long) this.f744f, b);
        byte b2 = b == ((long) min) ? (byte) 4 : 0;
        if (z) {
            b2 = (byte) (b2 | 1);
        }
        mo8771a(i, min, (byte) 1, b2);
        this.f741c.mo8624a_(this.f743e, (long) min);
        if (b > ((long) min)) {
            m893b(i, b - ((long) min));
        }
    }
}
