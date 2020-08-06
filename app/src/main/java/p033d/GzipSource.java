package p033d;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* renamed from: d.j */
public final class GzipSource implements Source {

    /* renamed from: a */
    private int f2662a = 0;

    /* renamed from: b */
    private final BufferedSource f2663b;

    /* renamed from: c */
    private final Inflater f2664c;

    /* renamed from: d */
    private final InflaterSource f2665d;

    /* renamed from: e */
    private final CRC32 f2666e = new CRC32();

    public GzipSource(Source sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f2664c = new Inflater(true);
        this.f2663b = Okio.m3590a(sVar);
        this.f2665d = new InflaterSource(this.f2663b, this.f2664c);
    }

    /* renamed from: a */
    public long mo8592a(Buffer cVar, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j == 0) {
            return 0;
        } else {
            if (this.f2662a == 0) {
                m3581b();
                this.f2662a = 1;
            }
            if (this.f2662a == 1) {
                long j2 = cVar.f2657b;
                long a = this.f2665d.mo8592a(cVar, j);
                if (a != -1) {
                    m3579a(cVar, j2, a);
                    return a;
                }
                this.f2662a = 2;
            }
            if (this.f2662a == 2) {
                m3582c();
                this.f2662a = 3;
                if (!this.f2663b.mo17664e()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    /* renamed from: b */
    private void m3581b() {
        boolean z;
        this.f2663b.mo17643a(10);
        byte b = this.f2663b.mo17653c().mo17647b(3);
        if (((b >> 1) & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m3579a(this.f2663b.mo17653c(), 0, 10);
        }
        m3580a("ID1ID2", 8075, (int) this.f2663b.mo17678i());
        this.f2663b.mo17671g(8);
        if (((b >> 2) & 1) == 1) {
            this.f2663b.mo17643a(2);
            if (z) {
                m3579a(this.f2663b.mo17653c(), 0, 2);
            }
            short k = this.f2663b.mo17653c().mo17682k();
            this.f2663b.mo17643a((long) k);
            if (z) {
                m3579a(this.f2663b.mo17653c(), 0, (long) k);
            }
            this.f2663b.mo17671g((long) k);
        }
        if (((b >> 3) & 1) == 1) {
            long a = this.f2663b.mo17634a((byte) 0);
            if (a == -1) {
                throw new EOFException();
            }
            if (z) {
                m3579a(this.f2663b.mo17653c(), 0, 1 + a);
            }
            this.f2663b.mo17671g(1 + a);
        }
        if (((b >> 4) & 1) == 1) {
            long a2 = this.f2663b.mo17634a((byte) 0);
            if (a2 == -1) {
                throw new EOFException();
            }
            if (z) {
                m3579a(this.f2663b.mo17653c(), 0, 1 + a2);
            }
            this.f2663b.mo17671g(1 + a2);
        }
        if (z) {
            m3580a("FHCRC", (int) this.f2663b.mo17682k(), (int) (short) ((int) this.f2666e.getValue()));
            this.f2666e.reset();
        }
    }

    /* renamed from: c */
    private void m3582c() {
        m3580a("CRC", this.f2663b.mo17683l(), (int) this.f2666e.getValue());
        m3580a("ISIZE", this.f2663b.mo17683l(), (int) this.f2664c.getBytesWritten());
    }

    /* renamed from: a */
    public Timeout mo8593a() {
        return this.f2663b.mo8593a();
    }

    public void close() {
        this.f2665d.close();
    }

    /* renamed from: a */
    private void m3579a(Buffer cVar, long j, long j2) {
        Segment oVar = cVar.f2656a;
        while (j >= ((long) (oVar.f2686c - oVar.f2685b))) {
            j -= (long) (oVar.f2686c - oVar.f2685b);
            oVar = oVar.f2689f;
        }
        while (j2 > 0) {
            int i = (int) (((long) oVar.f2685b) + j);
            int min = (int) Math.min((long) (oVar.f2686c - i), j2);
            this.f2666e.update(oVar.f2684a, i, min);
            j2 -= (long) min;
            oVar = oVar.f2689f;
            j = 0;
        }
    }

    /* renamed from: a */
    private void m3580a(String str, int i, int i2) {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
