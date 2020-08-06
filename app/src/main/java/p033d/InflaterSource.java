package p033d;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* renamed from: d.k */
public final class InflaterSource implements Source {

    /* renamed from: a */
    private final BufferedSource f2667a;

    /* renamed from: b */
    private final Inflater f2668b;

    /* renamed from: c */
    private int f2669c;

    /* renamed from: d */
    private boolean f2670d;

    InflaterSource(BufferedSource eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.f2667a = eVar;
            this.f2668b = inflater;
        }
    }

    /* renamed from: a */
    public long mo8592a(Buffer cVar, long j) {
        boolean b;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f2670d) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            do {
                b = mo17752b();
                try {
                    Segment e = cVar.mo17662e(1);
                    int inflate = this.f2668b.inflate(e.f2684a, e.f2686c, 8192 - e.f2686c);
                    if (inflate > 0) {
                        e.f2686c += inflate;
                        cVar.f2657b += (long) inflate;
                        return (long) inflate;
                    } else if (this.f2668b.finished() || this.f2668b.needsDictionary()) {
                        m3585c();
                        if (e.f2685b == e.f2686c) {
                            cVar.f2656a = e.mo17765a();
                            SegmentPool.m3652a(e);
                        }
                        return -1;
                    }
                } catch (DataFormatException e2) {
                    throw new IOException(e2);
                }
            } while (!b);
            throw new EOFException("source exhausted prematurely");
        }
    }

    /* renamed from: b */
    public boolean mo17752b() {
        if (!this.f2668b.needsInput()) {
            return false;
        }
        m3585c();
        if (this.f2668b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.f2667a.mo17664e()) {
            return true;
        } else {
            Segment oVar = this.f2667a.mo17653c().f2656a;
            this.f2669c = oVar.f2686c - oVar.f2685b;
            this.f2668b.setInput(oVar.f2684a, oVar.f2685b, this.f2669c);
            return false;
        }
    }

    /* renamed from: c */
    private void m3585c() {
        if (this.f2669c != 0) {
            int remaining = this.f2669c - this.f2668b.getRemaining();
            this.f2669c -= remaining;
            this.f2667a.mo17671g((long) remaining);
        }
    }

    /* renamed from: a */
    public Timeout mo8593a() {
        return this.f2667a.mo8593a();
    }

    public void close() {
        if (!this.f2670d) {
            this.f2668b.end();
            this.f2670d = true;
            this.f2667a.close();
        }
    }
}
