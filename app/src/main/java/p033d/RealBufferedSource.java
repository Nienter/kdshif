package p033d;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: d.n */
final class RealBufferedSource implements BufferedSource {

    /* renamed from: a */
    public final Buffer f2680a = new Buffer();

    /* renamed from: b */
    public final Source f2681b;

    /* renamed from: c */
    boolean f2682c;

    RealBufferedSource(Source sVar) {
        if (sVar == null) {
            throw new NullPointerException("source == null");
        }
        this.f2681b = sVar;
    }

    /* renamed from: c */
    public Buffer mo17653c() {
        return this.f2680a;
    }

    /* renamed from: a */
    public long mo8592a(Buffer cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f2682c) {
            throw new IllegalStateException("closed");
        } else if (this.f2680a.f2657b == 0 && this.f2681b.mo8592a(this.f2680a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        } else {
            return this.f2680a.mo8592a(cVar, Math.min(j, this.f2680a.f2657b));
        }
    }

    /* renamed from: e */
    public boolean mo17664e() {
        if (!this.f2682c) {
            return this.f2680a.mo17664e() && this.f2681b.mo8592a(this.f2680a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        }
        throw new IllegalStateException("closed");
    }

    /* renamed from: a */
    public void mo17643a(long j) {
        if (!mo17758b(j)) {
            throw new EOFException();
        }
    }

    /* renamed from: b */
    public boolean mo17758b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f2682c) {
            throw new IllegalStateException("closed");
        } else {
            while (this.f2680a.f2657b < j) {
                if (this.f2681b.mo8592a(this.f2680a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    /* renamed from: h */
    public byte mo17672h() {
        mo17643a(1);
        return this.f2680a.mo17672h();
    }

    /* renamed from: c */
    public ByteString mo17657c(long j) {
        mo17643a(j);
        return this.f2680a.mo17657c(j);
    }

    /* renamed from: r */
    public byte[] mo17689r() {
        this.f2680a.mo17636a(this.f2681b);
        return this.f2680a.mo17689r();
    }

    /* renamed from: f */
    public byte[] mo17668f(long j) {
        mo17643a(j);
        return this.f2680a.mo17668f(j);
    }

    /* renamed from: q */
    public String mo17688q() {
        long a = mo17634a((byte) 10);
        if (a != -1) {
            return this.f2680a.mo17663e(a);
        }
        Buffer cVar = new Buffer();
        this.f2680a.mo17638a(cVar, 0, Math.min(32, this.f2680a.mo17648b()));
        throw new EOFException("\\n not found: size=" + this.f2680a.mo17648b() + " content=" + cVar.mo17686o().hex() + "…");
    }

    /* renamed from: i */
    public short mo17678i() {
        mo17643a(2);
        return this.f2680a.mo17678i();
    }

    /* renamed from: k */
    public short mo17682k() {
        mo17643a(2);
        return this.f2680a.mo17682k();
    }

    /* renamed from: j */
    public int mo17679j() {
        mo17643a(4);
        return this.f2680a.mo17679j();
    }

    /* renamed from: l */
    public int mo17683l() {
        mo17643a(4);
        return this.f2680a.mo17683l();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0027  */
    /* renamed from: m */
    public long mo17684m() {
        mo17643a(1);
        int i = 0;
        while (true) {
            if (!mo17758b((long) (i + 1))) {
                break;
            }
            byte b = this.f2680a.mo17647b((long) i);
            if ((b >= 48 && b <= 57) || (i == 0 && b == 45)) {
                i++;
            } else if (i == 0) {
                throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", new Object[]{Byte.valueOf(b)}));
            }
        }
        if (i == 0) {
        }
        return this.f2680a.mo17684m();
    }

    /* renamed from: n */
    public long mo17685n() {
        mo17643a(1);
        int i = 0;
        while (true) {
            if (!mo17758b((long) (i + 1))) {
                break;
            }
            byte b = this.f2680a.mo17647b((long) i);
            if ((b >= 48 && b <= 57) || ((b >= 97 && b <= 102) || (b >= 65 && b <= 70))) {
                i++;
            } else if (i == 0) {
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[]{Byte.valueOf(b)}));
            }
        }
        return this.f2680a.mo17685n();
    }

    /* renamed from: g */
    public void mo17671g(long j) {
        if (this.f2682c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.f2680a.f2657b == 0 && this.f2681b.mo8592a(this.f2680a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.f2680a.mo17648b());
            this.f2680a.mo17671g(min);
            j -= min;
        }
    }

    /* renamed from: a */
    public long mo17634a(byte b) {
        return mo17756a(b, 0);
    }

    /* renamed from: a */
    public long mo17756a(byte b, long j) {
        if (this.f2682c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long a = this.f2680a.mo17635a(b, j);
            if (a != -1) {
                return a;
            }
            long j2 = this.f2680a.f2657b;
            if (this.f2681b.mo8592a(this.f2680a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            }
            j = Math.max(j, j2);
        }
    }

    /* renamed from: a */
    public boolean mo17645a(long j, ByteString fVar) {
        return mo17757a(j, fVar, 0, fVar.size());
    }

    /* renamed from: a */
    public boolean mo17757a(long j, ByteString fVar, int i, int i2) {
        if (this.f2682c) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || i < 0 || i2 < 0 || fVar.size() - i < i2) {
            return false;
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = ((long) i3) + j;
                if (!mo17758b(1 + j2) || this.f2680a.mo17647b(j2) != fVar.getByte(i + i3)) {
                    return false;
                }
            }
            return true;
        }
    }

    /* renamed from: f */
    public InputStream mo17667f() {
        return new InputStream() {
            public int read() {
                if (RealBufferedSource.this.f2682c) {
                    throw new IOException("closed");
                } else if (RealBufferedSource.this.f2680a.f2657b == 0 && RealBufferedSource.this.f2681b.mo8592a(RealBufferedSource.this.f2680a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                } else {
                    return RealBufferedSource.this.f2680a.mo17672h() & 255;
                }
            }

            public int read(byte[] bArr, int i, int i2) {
                if (RealBufferedSource.this.f2682c) {
                    throw new IOException("closed");
                }
                C1658u.m3672a((long) bArr.length, (long) i, (long) i2);
                if (RealBufferedSource.this.f2680a.f2657b == 0 && RealBufferedSource.this.f2681b.mo8592a(RealBufferedSource.this.f2680a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return RealBufferedSource.this.f2680a.mo17633a(bArr, i, i2);
            }

            public int available() {
                if (!RealBufferedSource.this.f2682c) {
                    return (int) Math.min(RealBufferedSource.this.f2680a.f2657b, 2147483647L);
                }
                throw new IOException("closed");
            }

            public void close() {
                RealBufferedSource.this.close();
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }

    public void close() {
        if (!this.f2682c) {
            this.f2682c = true;
            this.f2681b.close();
            this.f2680a.mo17690s();
        }
    }

    /* renamed from: a */
    public Timeout mo8593a() {
        return this.f2681b.mo8593a();
    }

    public String toString() {
        return "buffer(" + this.f2681b + ")";
    }
}
