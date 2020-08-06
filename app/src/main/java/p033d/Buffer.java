package p033d;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* renamed from: d.c */
public final class Buffer implements BufferedSink, BufferedSource, Cloneable {

    /* renamed from: c */
    private static final byte[] f2655c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: a */
    Segment f2656a;

    /* renamed from: b */
    long f2657b;

    /* renamed from: b */
    public long mo17648b() {
        return this.f2657b;
    }

    /* renamed from: c */
    public Buffer mo17653c() {
        return this;
    }

    /* renamed from: d */
    public Buffer mo17694v() {
        return this;
    }

    /* renamed from: e */
    public boolean mo17664e() {
        return this.f2657b == 0;
    }

    /* renamed from: a */
    public void mo17643a(long j) {
        if (this.f2657b < j) {
            throw new EOFException();
        }
    }

    /* renamed from: f */
    public InputStream mo17667f() {
        return new InputStream() {
            public int read() {
                if (Buffer.this.f2657b > 0) {
                    return Buffer.this.mo17672h() & 255;
                }
                return -1;
            }

            public int read(byte[] bArr, int i, int i2) {
                return Buffer.this.mo17633a(bArr, i, i2);
            }

            public int available() {
                return (int) Math.min(Buffer.this.f2657b, 2147483647L);
            }

            public void close() {
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    /* renamed from: a */
    public Buffer mo17638a(Buffer cVar, long j, long j2) {
        if (cVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        C1658u.m3672a(this.f2657b, j, j2);
        if (j2 != 0) {
            cVar.f2657b += j2;
            Segment oVar = this.f2656a;
            while (j >= ((long) (oVar.f2686c - oVar.f2685b))) {
                j -= (long) (oVar.f2686c - oVar.f2685b);
                oVar = oVar.f2689f;
            }
            while (j2 > 0) {
                Segment oVar2 = new Segment(oVar);
                oVar2.f2685b = (int) (((long) oVar2.f2685b) + j);
                oVar2.f2686c = Math.min(oVar2.f2685b + ((int) j2), oVar2.f2686c);
                if (cVar.f2656a == null) {
                    oVar2.f2690g = oVar2;
                    oVar2.f2689f = oVar2;
                    cVar.f2656a = oVar2;
                } else {
                    cVar.f2656a.f2690g.mo17767a(oVar2);
                }
                j2 -= (long) (oVar2.f2686c - oVar2.f2685b);
                oVar = oVar.f2689f;
                j = 0;
            }
        }
        return this;
    }

    /* renamed from: g */
    public long mo17669g() {
        long j = this.f2657b;
        if (j == 0) {
            return 0;
        }
        Segment oVar = this.f2656a.f2690g;
        if (oVar.f2686c >= 8192 || !oVar.f2688e) {
            return j;
        }
        return j - ((long) (oVar.f2686c - oVar.f2685b));
    }

    /* renamed from: h */
    public byte mo17672h() {
        if (this.f2657b == 0) {
            throw new IllegalStateException("size == 0");
        }
        Segment oVar = this.f2656a;
        int i = oVar.f2685b;
        int i2 = oVar.f2686c;
        int i3 = i + 1;
        byte b = oVar.f2684a[i];
        this.f2657b--;
        if (i3 == i2) {
            this.f2656a = oVar.mo17765a();
            SegmentPool.m3652a(oVar);
        } else {
            oVar.f2685b = i3;
        }
        return b;
    }

    /* renamed from: b */
    public byte mo17647b(long j) {
        C1658u.m3672a(this.f2657b, j, 1);
        Segment oVar = this.f2656a;
        while (true) {
            int i = oVar.f2686c - oVar.f2685b;
            if (j < ((long) i)) {
                return oVar.f2684a[oVar.f2685b + ((int) j)];
            }
            j -= (long) i;
            oVar = oVar.f2689f;
        }
    }

    /* renamed from: i */
    public short mo17678i() {
        if (this.f2657b < 2) {
            throw new IllegalStateException("size < 2: " + this.f2657b);
        }
        Segment oVar = this.f2656a;
        int i = oVar.f2685b;
        int i2 = oVar.f2686c;
        if (i2 - i < 2) {
            return (short) (((mo17672h() & 255) << 8) | (mo17672h() & 255));
        }
        byte[] bArr = oVar.f2684a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        byte b = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.f2657b -= 2;
        if (i4 == i2) {
            this.f2656a = oVar.mo17765a();
            SegmentPool.m3652a(oVar);
        } else {
            oVar.f2685b = i4;
        }
        return (short) b;
    }

    /* renamed from: j */
    public int mo17679j() {
        if (this.f2657b < 4) {
            throw new IllegalStateException("size < 4: " + this.f2657b);
        }
        Segment oVar = this.f2656a;
        int i = oVar.f2685b;
        int i2 = oVar.f2686c;
        if (i2 - i < 4) {
            return ((mo17672h() & 255) << 24) | ((mo17672h() & 255) << 16) | ((mo17672h() & 255) << 8) | (mo17672h() & 255);
        }
        byte[] bArr = oVar.f2684a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        byte b = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i5 = i4 + 1;
        byte b2 = b | ((bArr[i4] & 255) << 8);
        int i6 = i5 + 1;
        byte b3 = b2 | (bArr[i5] & 255);
        this.f2657b -= 4;
        if (i6 == i2) {
            this.f2656a = oVar.mo17765a();
            SegmentPool.m3652a(oVar);
            return b3;
        }
        oVar.f2685b = i6;
        return b3;
    }

    /* renamed from: k */
    public short mo17682k() {
        return C1658u.m3671a(mo17678i());
    }

    /* renamed from: l */
    public int mo17683l() {
        return C1658u.m3670a(mo17679j());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b8, code lost:
        if (r7 != r14) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ba, code lost:
        r20.f2656a = r12.mo17765a();
        p033d.SegmentPool.m3652a(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c5, code lost:
        if (r4 != false) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00da, code lost:
        r12.f2685b = r7;
     */
    /* renamed from: m */
    public long mo17684m() {
        byte b;
        if (this.f2657b == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j = 0;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        long j2 = -7;
        loop0:
        do {
            Segment oVar = this.f2656a;
            byte[] bArr = oVar.f2684a;
            int i2 = oVar.f2685b;
            int i3 = oVar.f2686c;
            while (true) {
                if (i2 >= i3) {
                    break;
                }
                b = bArr[i2];
                if (b >= 48 && b <= 57) {
                    int i4 = 48 - b;
                    if (j < -922337203685477580L || (j == -922337203685477580L && ((long) i4) < j2)) {
                        Buffer b2 = new Buffer().mo17681k(j).mo17677i((int) b);
                    } else {
                        j = (j * 10) + ((long) i4);
                    }
                } else if (b == 45 && i == 0) {
                    z = true;
                    j2--;
                } else if (i == 0) {
                    throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(b));
                } else {
                    z2 = true;
                }
                i2++;
                i++;
            }
            Buffer b22 = new Buffer().mo17681k(j).mo17677i((int) b);
            if (!z) {
                b22.mo17672h();
            }
            throw new NumberFormatException("Number too large: " + b22.mo17687p());
        } while (this.f2656a != null);
        this.f2657b -= (long) i;
        if (z) {
            return j;
        }
        return -j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009c, code lost:
        if (r7 != r12) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009e, code lost:
        r18.f2656a = r10.mo17765a();
        p033d.SegmentPool.m3652a(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a9, code lost:
        if (r2 != false) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c9, code lost:
        r10.f2685b = r7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007e A[SYNTHETIC] */
    /* renamed from: n */
    public long mo17685n() {
        int i;
        if (this.f2657b == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j = 0;
        int i2 = 0;
        boolean z = false;
        do {
            Segment oVar = this.f2656a;
            byte[] bArr = oVar.f2684a;
            int i3 = oVar.f2685b;
            int i4 = oVar.f2686c;
            int i5 = i3;
            while (true) {
                if (i5 >= i4) {
                    break;
                }
                byte b = bArr[i5];
                if (b >= 48 && b <= 57) {
                    i = b - 48;
                } else if (b >= 97 && b <= 102) {
                    i = (b - 97) + 10;
                } else if (b >= 65 && b <= 70) {
                    i = (b - 65) + 10;
                } else if (i2 != 0) {
                    throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                } else {
                    z = true;
                }
                if ((-1152921504606846976L & j) != 0) {
                    throw new NumberFormatException("Number too large: " + new Buffer().mo17680j(j).mo17677i((int) b).mo17687p());
                }
                i2++;
                i5++;
                j = ((long) i) | (j << 4);
            }
            if (i2 != 0) {
            }
        } while (this.f2656a != null);
        this.f2657b -= (long) i2;
        return j;
    }

    /* renamed from: o */
    public ByteString mo17686o() {
        return new ByteString(mo17689r());
    }

    /* renamed from: c */
    public ByteString mo17657c(long j) {
        return new ByteString(mo17668f(j));
    }

    /* renamed from: p */
    public String mo17687p() {
        try {
            return mo17642a(this.f2657b, C1658u.f2697a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: d */
    public String mo17661d(long j) {
        return mo17642a(j, C1658u.f2697a);
    }

    /* renamed from: a */
    public String mo17642a(long j, Charset charset) {
        C1658u.m3672a(this.f2657b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            Segment oVar = this.f2656a;
            if (((long) oVar.f2685b) + j > ((long) oVar.f2686c)) {
                return new String(mo17668f(j), charset);
            }
            String str = new String(oVar.f2684a, oVar.f2685b, (int) j, charset);
            oVar.f2685b = (int) (((long) oVar.f2685b) + j);
            this.f2657b -= j;
            if (oVar.f2685b != oVar.f2686c) {
                return str;
            }
            this.f2656a = oVar.mo17765a();
            SegmentPool.m3652a(oVar);
            return str;
        }
    }

    /* renamed from: q */
    public String mo17688q() {
        long a = mo17634a((byte) 10);
        if (a != -1) {
            return mo17663e(a);
        }
        Buffer cVar = new Buffer();
        mo17638a(cVar, 0, Math.min(32, this.f2657b));
        throw new EOFException("\\n not found: size=" + mo17648b() + " content=" + cVar.mo17686o().hex() + "…");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public String mo17663e(long j) {
        if (j <= 0 || mo17647b(j - 1) != 13) {
            String d = mo17661d(j);
            mo17671g(1);
            return d;
        }
        String d2 = mo17661d(j - 1);
        mo17671g(2);
        return d2;
    }

    /* renamed from: r */
    public byte[] mo17689r() {
        try {
            return mo17668f(this.f2657b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: f */
    public byte[] mo17668f(long j) {
        C1658u.m3672a(this.f2657b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        mo17644a(bArr);
        return bArr;
    }

    /* renamed from: a */
    public void mo17644a(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int a = mo17633a(bArr, i, bArr.length - i);
            if (a == -1) {
                throw new EOFException();
            }
            i += a;
        }
    }

    /* renamed from: a */
    public int mo17633a(byte[] bArr, int i, int i2) {
        C1658u.m3672a((long) bArr.length, (long) i, (long) i2);
        Segment oVar = this.f2656a;
        if (oVar == null) {
            return -1;
        }
        int min = Math.min(i2, oVar.f2686c - oVar.f2685b);
        System.arraycopy(oVar.f2684a, oVar.f2685b, bArr, i, min);
        oVar.f2685b += min;
        this.f2657b -= (long) min;
        if (oVar.f2685b != oVar.f2686c) {
            return min;
        }
        this.f2656a = oVar.mo17765a();
        SegmentPool.m3652a(oVar);
        return min;
    }

    /* renamed from: s */
    public void mo17690s() {
        try {
            mo17671g(this.f2657b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: g */
    public void mo17671g(long j) {
        while (j > 0) {
            if (this.f2656a == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.f2656a.f2686c - this.f2656a.f2685b));
            this.f2657b -= (long) min;
            j -= (long) min;
            Segment oVar = this.f2656a;
            oVar.f2685b = min + oVar.f2685b;
            if (this.f2656a.f2685b == this.f2656a.f2686c) {
                Segment oVar2 = this.f2656a;
                this.f2656a = oVar2.mo17765a();
                SegmentPool.m3652a(oVar2);
            }
        }
    }

    /* renamed from: a */
    public Buffer mo17639a(ByteString fVar) {
        if (fVar == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        fVar.write(this);
        return this;
    }

    /* renamed from: a */
    public Buffer mo17652b(String str) {
        return mo17641a(str, 0, str.length());
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 152 */
    /* renamed from: a */
    public Buffer mo17641a(String str, int i, int i2) {
        int i3;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    Segment e = mo17662e(1);
                    byte[] bArr = e.f2684a;
                    int i4 = e.f2686c - i;
                    int min = Math.min(i2, 8192 - i4);
                    i3 = i + 1;
                    bArr[i4 + i] = (byte) charAt;
                    while (i3 < min) {
                        char charAt2 = str.charAt(i3);
                        if (charAt2 < 128) {
                            bArr[i3 + i4] = (byte) charAt2;
                            i3++;
                        }
                    }
                    int i5 = (i3 + i4) - e.f2686c;
                    e.f2686c += i5;
                    this.f2657b += (long) i5;
                    break;
                } else if (charAt < 2048) {
                    mo17677i((charAt >> 6) | 192);
                    mo17677i((int) (charAt & '?') | 128);
                    i3 = i + 1;
                } else if (charAt < 55296 || charAt > 57343) {
                    mo17677i((charAt >> 12) | 224);
                    mo17677i(((charAt >> 6) & 63) | 128);
                    mo17677i((int) (charAt & '?') | 128);
                    i3 = i + 1;
                } else {
                    char charAt3 = i + 1 < i2 ? str.charAt(i + 1) : 0;
                    if (charAt > 56319 || charAt3 < 56320 || charAt3 > 57343) {
                        mo17677i(63);
                        i++;
                    } else {
                        int i6 = ((charAt3 & 9215) | ((charAt & 10239) << 10)) + 0;
                        mo17677i((i6 >> 18) | 240);
                        mo17677i(((i6 >> 12) & 63) | 128);
                        mo17677i(((i6 >> 6) & 63) | 128);
                        mo17677i((i6 & 63) | 128);
                        i3 = i + 2;
                    }
                }
                i = i3;
            }
            return this;
        }
    }

    /* renamed from: a */
    public Buffer mo17637a(int i) {
        if (i < 128) {
            mo17677i(i);
        } else if (i < 2048) {
            mo17677i((i >> 6) | 192);
            mo17677i((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                mo17677i((i >> 12) | 224);
                mo17677i(((i >> 6) & 63) | 128);
                mo17677i((i & 63) | 128);
            } else {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
            }
        } else if (i <= 1114111) {
            mo17677i((i >> 18) | 240);
            mo17677i(((i >> 12) & 63) | 128);
            mo17677i(((i >> 6) & 63) | 128);
            mo17677i((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    /* renamed from: b */
    public Buffer mo17655c(byte[] bArr) {
        if (bArr != null) {
            return mo17656c(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: b */
    public Buffer mo17656c(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        C1658u.m3672a((long) bArr.length, (long) i, (long) i2);
        int i3 = i + i2;
        while (i < i3) {
            Segment e = mo17662e(1);
            int min = Math.min(i3 - i, 8192 - e.f2686c);
            System.arraycopy(bArr, i, e.f2684a, e.f2686c, min);
            i += min;
            e.f2686c = min + e.f2686c;
        }
        this.f2657b += (long) i2;
        return this;
    }

    /* renamed from: a */
    public long mo17636a(Source sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long a = sVar.mo8592a(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (a == -1) {
                return j;
            }
            j += a;
        }
    }

    /* renamed from: b */
    public Buffer mo17677i(int i) {
        Segment e = mo17662e(1);
        byte[] bArr = e.f2684a;
        int i2 = e.f2686c;
        e.f2686c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f2657b++;
        return this;
    }

    /* renamed from: c */
    public Buffer mo17674h(int i) {
        Segment e = mo17662e(2);
        byte[] bArr = e.f2684a;
        int i2 = e.f2686c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        e.f2686c = i3 + 1;
        this.f2657b += 2;
        return this;
    }

    /* renamed from: d */
    public Buffer mo17670g(int i) {
        Segment e = mo17662e(4);
        byte[] bArr = e.f2684a;
        int i2 = e.f2686c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        e.f2686c = i5 + 1;
        this.f2657b += 4;
        return this;
    }

    /* renamed from: h */
    public Buffer mo17681k(long j) {
        boolean z;
        long j2;
        int i;
        if (j == 0) {
            return mo17677i(48);
        }
        if (j < 0) {
            j2 = -j;
            if (j2 < 0) {
                return mo17652b("-9223372036854775808");
            }
            z = true;
        } else {
            z = false;
            j2 = j;
        }
        if (j2 < 100000000) {
            i = j2 < 10000 ? j2 < 100 ? j2 < 10 ? 1 : 2 : j2 < 1000 ? 3 : 4 : j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8;
        } else {
            i = j2 < 1000000000000L ? j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        }
        if (z) {
            i++;
        }
        Segment e = mo17662e(i);
        byte[] bArr = e.f2684a;
        int i2 = e.f2686c + i;
        while (j2 != 0) {
            i2--;
            bArr[i2] = f2655c[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        e.f2686c += i;
        this.f2657b = ((long) i) + this.f2657b;
        return this;
    }

    /* renamed from: i */
    public Buffer mo17680j(long j) {
        if (j == 0) {
            return mo17677i(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        Segment e = mo17662e(numberOfTrailingZeros);
        byte[] bArr = e.f2684a;
        int i = e.f2686c;
        for (int i2 = (e.f2686c + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = f2655c[(int) (15 & j)];
            j >>>= 4;
        }
        e.f2686c += numberOfTrailingZeros;
        this.f2657b = ((long) numberOfTrailingZeros) + this.f2657b;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public Segment mo17662e(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.f2656a == null) {
            this.f2656a = SegmentPool.m3651a();
            Segment oVar = this.f2656a;
            Segment oVar2 = this.f2656a;
            Segment oVar3 = this.f2656a;
            oVar2.f2690g = oVar3;
            oVar.f2689f = oVar3;
            return oVar3;
        } else {
            Segment oVar4 = this.f2656a.f2690g;
            if (oVar4.f2686c + i > 8192 || !oVar4.f2688e) {
                return oVar4.mo17767a(SegmentPool.m3651a());
            }
            return oVar4;
        }
    }

    /* renamed from: a_ */
    public void mo8624a_(Buffer cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (cVar == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            C1658u.m3672a(cVar.f2657b, 0, j);
            while (j > 0) {
                if (j < ((long) (cVar.f2656a.f2686c - cVar.f2656a.f2685b))) {
                    Segment oVar = this.f2656a != null ? this.f2656a.f2690g : null;
                    if (oVar != null && oVar.f2688e) {
                        if ((((long) oVar.f2686c) + j) - ((long) (oVar.f2687d ? 0 : oVar.f2685b)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            cVar.f2656a.mo17768a(oVar, (int) j);
                            cVar.f2657b -= j;
                            this.f2657b += j;
                            return;
                        }
                    }
                    cVar.f2656a = cVar.f2656a.mo17766a((int) j);
                }
                Segment oVar2 = cVar.f2656a;
                long j2 = (long) (oVar2.f2686c - oVar2.f2685b);
                cVar.f2656a = oVar2.mo17765a();
                if (this.f2656a == null) {
                    this.f2656a = oVar2;
                    Segment oVar3 = this.f2656a;
                    Segment oVar4 = this.f2656a;
                    Segment oVar5 = this.f2656a;
                    oVar4.f2690g = oVar5;
                    oVar3.f2689f = oVar5;
                } else {
                    this.f2656a.f2690g.mo17767a(oVar2).mo17769b();
                }
                cVar.f2657b -= j2;
                this.f2657b += j2;
                j -= j2;
            }
        }
    }

    /* renamed from: a */
    public long mo8592a(Buffer cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f2657b == 0) {
            return -1;
        } else {
            if (j > this.f2657b) {
                j = this.f2657b;
            }
            cVar.mo8624a_(this, j);
            return j;
        }
    }

    /* renamed from: a */
    public long mo17634a(byte b) {
        return mo17635a(b, 0);
    }

    /* renamed from: a */
    public long mo17635a(byte b, long j) {
        Segment oVar;
        long j2;
        long j3 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment oVar2 = this.f2656a;
        if (oVar2 == null) {
            return -1;
        }
        if (this.f2657b - j >= j) {
            Segment oVar3 = oVar2;
            while (true) {
                long j4 = ((long) (oVar.f2686c - oVar.f2685b)) + j2;
                if (j4 >= j) {
                    break;
                }
                oVar3 = oVar.f2689f;
                j3 = j4;
            }
        } else {
            j2 = this.f2657b;
            oVar = oVar2;
            while (j2 > j) {
                oVar = oVar.f2690g;
                j2 -= (long) (oVar.f2686c - oVar.f2685b);
            }
        }
        while (j2 < this.f2657b) {
            byte[] bArr = oVar.f2684a;
            int i = oVar.f2686c;
            for (int i2 = (int) ((((long) oVar.f2685b) + j) - j2); i2 < i; i2++) {
                if (bArr[i2] == b) {
                    return j2 + ((long) (i2 - oVar.f2685b));
                }
            }
            j2 += (long) (oVar.f2686c - oVar.f2685b);
            oVar = oVar.f2689f;
            j = j2;
        }
        return -1;
    }

    /* renamed from: a */
    public boolean mo17645a(long j, ByteString fVar) {
        return mo17646a(j, fVar, 0, fVar.size());
    }

    /* renamed from: a */
    public boolean mo17646a(long j, ByteString fVar, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.f2657b - j < ((long) i2) || fVar.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (mo17647b(((long) i3) + j) != fVar.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    public void flush() {
    }

    public void close() {
    }

    /* renamed from: a */
    public Timeout mo8695a() {
        return Timeout.f2693b;
    }

    public boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer cVar = (Buffer) obj;
        if (this.f2657b != cVar.f2657b) {
            return false;
        }
        if (this.f2657b == 0) {
            return true;
        }
        Segment oVar = this.f2656a;
        Segment oVar2 = cVar.f2656a;
        int i = oVar.f2685b;
        int i2 = oVar2.f2685b;
        while (j < this.f2657b) {
            long min = (long) Math.min(oVar.f2686c - i, oVar2.f2686c - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                byte b = oVar.f2684a[i];
                int i5 = i2 + 1;
                if (b != oVar2.f2684a[i2]) {
                    return false;
                }
                i3++;
                i2 = i5;
                i = i4;
            }
            if (i == oVar.f2686c) {
                oVar = oVar.f2689f;
                i = oVar.f2685b;
            }
            if (i2 == oVar2.f2686c) {
                oVar2 = oVar2.f2689f;
                i2 = oVar2.f2685b;
            }
            j += min;
        }
        return true;
    }

    public int hashCode() {
        Segment oVar = this.f2656a;
        if (oVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = oVar.f2685b;
            int i3 = oVar.f2686c;
            while (i2 < i3) {
                i2++;
                i = oVar.f2684a[i2] + (i * 31);
            }
            oVar = oVar.f2689f;
        } while (oVar != this.f2656a);
        return i;
    }

    public String toString() {
        return mo17693u().toString();
    }

    /* renamed from: t */
    public Buffer clone() {
        Buffer cVar = new Buffer();
        if (this.f2657b == 0) {
            return cVar;
        }
        cVar.f2656a = new Segment(this.f2656a);
        Segment oVar = cVar.f2656a;
        Segment oVar2 = cVar.f2656a;
        Segment oVar3 = cVar.f2656a;
        oVar2.f2690g = oVar3;
        oVar.f2689f = oVar3;
        for (Segment oVar4 = this.f2656a.f2689f; oVar4 != this.f2656a; oVar4 = oVar4.f2689f) {
            cVar.f2656a.f2690g.mo17767a(new Segment(oVar4));
        }
        cVar.f2657b = this.f2657b;
        return cVar;
    }

    /* renamed from: u */
    public ByteString mo17693u() {
        if (this.f2657b <= 2147483647L) {
            return mo17666f((int) this.f2657b);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f2657b);
    }

    /* renamed from: f */
    public ByteString mo17666f(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i);
    }
}
