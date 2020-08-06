package com.p028a.p029a.p030a;

import android.support.p001v4.media.TransportMediator;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.a.a.a.bq */
public class TCompactProtocol extends TProtocol {

    /* renamed from: d */
    private static final TStruct f1462d = new TStruct("");

    /* renamed from: f */
    private static final TField f1463f = new TField("", (byte) 0, 0);

    /* renamed from: g */
    private static final byte[] f1464g = new byte[16];

    /* renamed from: a */
    byte[] f1465a = new byte[5];

    /* renamed from: b */
    byte[] f1466b = new byte[10];

    /* renamed from: c */
    byte[] f1467c = new byte[1];

    /* renamed from: h */
    private ShortStack f1468h = new ShortStack(15);

    /* renamed from: i */
    private short f1469i = 0;

    /* renamed from: j */
    private TField f1470j = null;

    /* renamed from: k */
    private Boolean f1471k = null;

    /* renamed from: l */
    private final long f1472l;

    /* renamed from: m */
    private byte[] f1473m = new byte[1];

    /* renamed from: com.a.a.a.bq$a */
    /* compiled from: TCompactProtocol */
    public static class C0613a implements TProtocolFactory {

        /* renamed from: a */
        private final long f1474a;

        public C0613a() {
            this.f1474a = -1;
        }

        public C0613a(int i) {
            this.f1474a = (long) i;
        }

        /* renamed from: a */
        public TProtocol mo9450a(TTransport ciVar) {
            return new TCompactProtocol(ciVar, this.f1474a);
        }
    }

    static {
        f1464g[0] = 0;
        f1464g[2] = 1;
        f1464g[3] = 3;
        f1464g[6] = 4;
        f1464g[8] = 5;
        f1464g[10] = 6;
        f1464g[4] = 7;
        f1464g[11] = 8;
        f1464g[15] = 9;
        f1464g[14] = 10;
        f1464g[13] = 11;
        f1464g[12] = 12;
    }

    public TCompactProtocol(TTransport ciVar, long j) {
        super(ciVar);
        this.f1472l = j;
    }

    /* renamed from: x */
    public void mo9454x() {
        this.f1468h.mo9404b();
        this.f1469i = 0;
    }

    /* renamed from: a */
    public void mo9421a(TStruct caVar) {
        this.f1468h.mo9403a(this.f1469i);
        this.f1469i = 0;
    }

    /* renamed from: a */
    public void mo9414a() {
        this.f1469i = this.f1468h.mo9402a();
    }

    /* renamed from: a */
    public void mo9418a(TField bsVar) {
        if (bsVar.f1476b == 2) {
            this.f1470j = bsVar;
        } else {
            m1986a(bsVar, (byte) -1);
        }
    }

    /* renamed from: a */
    private void m1986a(TField bsVar, byte b) {
        if (b == -1) {
            b = m1997e(bsVar.f1476b);
        }
        if (bsVar.f1477c <= this.f1469i || bsVar.f1477c - this.f1469i > 15) {
            m1988b(b);
            mo9453a(bsVar.f1477c);
        } else {
            m1996d((int) ((bsVar.f1477c - this.f1469i) << 4) | b);
        }
        this.f1469i = bsVar.f1477c;
    }

    /* renamed from: c */
    public void mo9427c() {
        m1988b((byte) 0);
    }

    /* renamed from: a */
    public void mo9420a(TMap buVar) {
        if (buVar.f1482c == 0) {
            m1996d(0);
            return;
        }
        mo9460b(buVar.f1482c);
        m1996d((int) (m1997e(buVar.f1480a) << 4) | m1997e(buVar.f1481b));
    }

    /* renamed from: a */
    public void mo9419a(TList btVar) {
        mo9452a(btVar.f1478a, btVar.f1479b);
    }

    /* renamed from: a */
    public void mo9451a(byte b) {
        m1988b(b);
    }

    /* renamed from: a */
    public void mo9453a(short s) {
        mo9460b(m1991c((int) s));
    }

    /* renamed from: a */
    public void mo9416a(int i) {
        mo9460b(m1991c(i));
    }

    /* renamed from: a */
    public void mo9417a(long j) {
        m1990b(m1992c(j));
    }

    /* renamed from: a */
    public void mo9422a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            m1987a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new TException("UTF-8 not supported!");
        }
    }

    /* renamed from: a */
    public void mo9423a(ByteBuffer byteBuffer) {
        m1987a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    /* renamed from: a */
    private void m1987a(byte[] bArr, int i, int i2) {
        mo9460b(i2);
        this.f1483e.mo9462b(bArr, i, i2);
    }

    /* renamed from: d */
    public void mo9429d() {
    }

    /* renamed from: e */
    public void mo9431e() {
    }

    /* renamed from: b */
    public void mo9426b() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo9452a(byte b, int i) {
        if (i <= 14) {
            m1996d((int) (i << 4) | m1997e(b));
            return;
        }
        m1996d((int) m1997e(b) | 240);
        mo9460b(i);
    }

    /* renamed from: b */
    private void mo9460b(int i) {
        int i2 = 0;
        while ((i & -128) != 0) {
            this.f1465a[i2] = (byte) ((i & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            i >>>= 7;
            i2++;
        }
        this.f1465a[i2] = (byte) i;
        this.f1483e.mo9462b(this.f1465a, 0, i2 + 1);
    }

    /* renamed from: b */
    private void m1990b(long j) {
        int i = 0;
        while ((-128 & j) != 0) {
            this.f1466b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i++;
        }
        this.f1466b[i] = (byte) ((int) j);
        this.f1483e.mo9462b(this.f1466b, 0, i + 1);
    }

    /* renamed from: c */
    private long m1992c(long j) {
        return (j << 1) ^ (j >> 63);
    }

    /* renamed from: c */
    private int m1991c(int i) {
        return (i << 1) ^ (i >> 31);
    }

    /* renamed from: b */
    private void m1988b(byte b) {
        this.f1473m[0] = b;
        this.f1483e.mo9470b(this.f1473m);
    }

    /* renamed from: d */
    private void m1996d(int i) {
        m1988b((byte) i);
    }

    /* renamed from: f */
    public TStruct mo9432f() {
        this.f1468h.mo9403a(this.f1469i);
        this.f1469i = 0;
        return f1462d;
    }

    /* renamed from: g */
    public void mo9433g() {
        this.f1469i = this.f1468h.mo9402a();
    }

    /* renamed from: h */
    public TField mo9434h() {
        short s;
        byte q = mo9443q();
        if (q == 0) {
            return f1463f;
        }
        short s2 = (short) ((q & 240) >> 4);
        if (s2 == 0) {
            s = mo9444r();
        } else {
            s = (short) (s2 + this.f1469i);
        }
        TField bsVar = new TField("", m1994d((byte) (q & 15)), s);
        if (m1993c(q)) {
            this.f1471k = ((byte) (q & 15)) == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.f1469i = bsVar.f1477c;
        return bsVar;
    }

    /* renamed from: j */
    public TMap mo9436j() {
        int z = m2001z();
        byte q = z == 0 ? 0 : mo9443q();
        return new TMap(m1994d((byte) (q >> 4)), m1994d((byte) (q & 15)), z);
    }

    /* renamed from: l */
    public TList mo9438l() {
        byte q = mo9443q();
        int i = (q >> 4) & 15;
        if (i == 15) {
            i = m2001z();
        }
        return new TList(m1994d(q), i);
    }

    /* renamed from: n */
    public TSet mo9440n() {
        return new TSet(mo9438l());
    }

    /* renamed from: p */
    public boolean mo9442p() {
        if (this.f1471k != null) {
            boolean booleanValue = this.f1471k.booleanValue();
            this.f1471k = null;
            return booleanValue;
        } else if (mo9443q() != 1) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: q */
    public byte mo9443q() {
        if (this.f1483e.mo9469d() > 0) {
            byte b = this.f1483e.mo9466b()[this.f1483e.mo9467c()];
            this.f1483e.mo9464a(1);
            return b;
        }
        this.f1483e.mo9471d(this.f1467c, 0, 1);
        return this.f1467c[0];
    }

    /* renamed from: r */
    public short mo9444r() {
        return (short) m2000g(m2001z());
    }

    /* renamed from: s */
    public int mo9445s() {
        return m2000g(m2001z());
    }

    /* renamed from: t */
    public long mo9446t() {
        return m1995d(m1984A());
    }

    /* renamed from: u */
    public double mo9447u() {
        byte[] bArr = new byte[8];
        this.f1483e.mo9471d(bArr, 0, 8);
        return Double.longBitsToDouble(m1985a(bArr));
    }

    /* renamed from: v */
    public String mo9448v() {
        int z = m2001z();
        m1999f(z);
        if (z == 0) {
            return "";
        }
        try {
            if (this.f1483e.mo9469d() < z) {
                return new String(m1998e(z), "UTF-8");
            }
            String str = new String(this.f1483e.mo9466b(), this.f1483e.mo9467c(), z, "UTF-8");
            this.f1483e.mo9464a(z);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new TException("UTF-8 not supported!");
        }
    }

    /* renamed from: w */
    public ByteBuffer mo9449w() {
        int z = m2001z();
        m1999f(z);
        if (z == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[z];
        this.f1483e.mo9471d(bArr, 0, z);
        return ByteBuffer.wrap(bArr);
    }

    /* renamed from: e */
    private byte[] m1998e(int i) {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.f1483e.mo9471d(bArr, 0, i);
        return bArr;
    }

    /* renamed from: f */
    private void m1999f(int i) {
        if (i < 0) {
            throw new TProtocolException("Negative length: " + i);
        } else if (this.f1472l != -1 && ((long) i) > this.f1472l) {
            throw new TProtocolException("Length exceeded max allowed: " + i);
        }
    }

    /* renamed from: i */
    public void mo9435i() {
    }

    /* renamed from: k */
    public void mo9437k() {
    }

    /* renamed from: m */
    public void mo9439m() {
    }

    /* renamed from: o */
    public void mo9441o() {
    }

    /* renamed from: z */
    private int m2001z() {
        int i = 0;
        if (this.f1483e.mo9469d() >= 5) {
            byte[] b = this.f1483e.mo9466b();
            int c = this.f1483e.mo9467c();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte b2 = b[c + i];
                i3 |= (b2 & Byte.MAX_VALUE) << i2;
                if ((b2 & 128) != 128) {
                    this.f1483e.mo9464a(i + 1);
                    return i3;
                }
                i2 += 7;
                i++;
            }
        } else {
            int i4 = 0;
            while (true) {
                byte q = mo9443q();
                i4 |= (q & Byte.MAX_VALUE) << i;
                if ((q & 128) != 128) {
                    return i4;
                }
                i += 7;
            }
        }
    }

    /* renamed from: A */
    private long m1984A() {
        int i = 0;
        long j = 0;
        if (this.f1483e.mo9469d() >= 10) {
            byte[] b = this.f1483e.mo9466b();
            int c = this.f1483e.mo9467c();
            int i2 = 0;
            while (true) {
                byte b2 = b[c + i];
                j |= ((long) (b2 & Byte.MAX_VALUE)) << i2;
                if ((b2 & 128) != 128) {
                    break;
                }
                i2 += 7;
                i++;
            }
            this.f1483e.mo9464a(i + 1);
        } else {
            while (true) {
                byte q = mo9443q();
                j |= ((long) (q & Byte.MAX_VALUE)) << i;
                if ((q & 128) != 128) {
                    break;
                }
                i += 7;
            }
        }
        return j;
    }

    /* renamed from: g */
    private int m2000g(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    /* renamed from: d */
    private long m1995d(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    /* renamed from: a */
    private long m1985a(byte[] bArr) {
        return ((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[1]) & 255) << 8) | (((long) bArr[0]) & 255);
    }

    /* renamed from: c */
    private boolean m1993c(byte b) {
        byte b2 = b & 15;
        if (b2 == 1 || b2 == 2) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    private byte m1994d(byte b) {
        switch ((byte) (b & 15)) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 8;
            case 6:
                return 10;
            case 7:
                return 4;
            case 8:
                return 11;
            case 9:
                return 15;
            case 10:
                return 14;
            case 11:
                return 13;
            case 12:
                return 12;
            default:
                throw new TProtocolException("don't know what type: " + ((byte) (b & 15)));
        }
    }

    /* renamed from: e */
    private byte m1997e(byte b) {
        return f1464g[b];
    }
}
