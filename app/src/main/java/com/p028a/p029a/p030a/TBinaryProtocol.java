package com.p028a.p029a.p030a;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.a.a.a.bp */
public class TBinaryProtocol extends TProtocol {

    /* renamed from: f */
    private static final TStruct f1446f = new TStruct();

    /* renamed from: a */
    protected boolean f1447a = false;

    /* renamed from: b */
    protected boolean f1448b = true;

    /* renamed from: c */
    protected int f1449c;

    /* renamed from: d */
    protected boolean f1450d = false;

    /* renamed from: g */
    private byte[] f1451g = new byte[1];

    /* renamed from: h */
    private byte[] f1452h = new byte[2];

    /* renamed from: i */
    private byte[] f1453i = new byte[4];

    /* renamed from: j */
    private byte[] f1454j = new byte[8];

    /* renamed from: k */
    private byte[] f1455k = new byte[1];

    /* renamed from: l */
    private byte[] f1456l = new byte[2];

    /* renamed from: m */
    private byte[] f1457m = new byte[4];

    /* renamed from: n */
    private byte[] f1458n = new byte[8];

    /* renamed from: com.a.a.a.bp$a */
    /* compiled from: TBinaryProtocol */
    public static class C0612a implements TProtocolFactory {

        /* renamed from: a */
        protected boolean f1459a;

        /* renamed from: b */
        protected boolean f1460b;

        /* renamed from: c */
        protected int f1461c;

        public C0612a() {
            this(false, true);
        }

        public C0612a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public C0612a(boolean z, boolean z2, int i) {
            this.f1459a = false;
            this.f1460b = true;
            this.f1459a = z;
            this.f1460b = z2;
            this.f1461c = i;
        }

        /* renamed from: a */
        public TProtocol mo9450a(TTransport ciVar) {
            TBinaryProtocol bpVar = new TBinaryProtocol(ciVar, this.f1459a, this.f1460b);
            if (this.f1461c != 0) {
                bpVar.mo9428c(this.f1461c);
            }
            return bpVar;
        }
    }

    public TBinaryProtocol(TTransport ciVar, boolean z, boolean z2) {
        super(ciVar);
        this.f1447a = z;
        this.f1448b = z2;
    }

    /* renamed from: a */
    public void mo9421a(TStruct caVar) {
    }

    /* renamed from: a */
    public void mo9414a() {
    }

    /* renamed from: a */
    public void mo9418a(TField bsVar) {
        mo9415a(bsVar.f1476b);
        mo9424a(bsVar.f1477c);
    }

    /* renamed from: b */
    public void mo9426b() {
    }

    /* renamed from: c */
    public void mo9427c() {
        mo9415a((byte) 0);
    }

    /* renamed from: a */
    public void mo9420a(TMap buVar) {
        mo9415a(buVar.f1480a);
        mo9415a(buVar.f1481b);
        mo9416a(buVar.f1482c);
    }

    /* renamed from: d */
    public void mo9429d() {
    }

    /* renamed from: a */
    public void mo9419a(TList btVar) {
        mo9415a(btVar.f1478a);
        mo9416a(btVar.f1479b);
    }

    /* renamed from: e */
    public void mo9431e() {
    }

    /* renamed from: a */
    public void mo9415a(byte b) {
        this.f1451g[0] = b;
        this.f1483e.mo9462b(this.f1451g, 0, 1);
    }

    /* renamed from: a */
    public void mo9424a(short s) {
        this.f1452h[0] = (byte) ((s >> 8) & 255);
        this.f1452h[1] = (byte) (s & 255);
        this.f1483e.mo9462b(this.f1452h, 0, 2);
    }

    /* renamed from: a */
    public void mo9416a(int i) {
        this.f1453i[0] = (byte) ((i >> 24) & 255);
        this.f1453i[1] = (byte) ((i >> 16) & 255);
        this.f1453i[2] = (byte) ((i >> 8) & 255);
        this.f1453i[3] = (byte) (i & 255);
        this.f1483e.mo9462b(this.f1453i, 0, 4);
    }

    /* renamed from: a */
    public void mo9417a(long j) {
        this.f1454j[0] = (byte) ((int) ((j >> 56) & 255));
        this.f1454j[1] = (byte) ((int) ((j >> 48) & 255));
        this.f1454j[2] = (byte) ((int) ((j >> 40) & 255));
        this.f1454j[3] = (byte) ((int) ((j >> 32) & 255));
        this.f1454j[4] = (byte) ((int) ((j >> 24) & 255));
        this.f1454j[5] = (byte) ((int) ((j >> 16) & 255));
        this.f1454j[6] = (byte) ((int) ((j >> 8) & 255));
        this.f1454j[7] = (byte) ((int) (255 & j));
        this.f1483e.mo9462b(this.f1454j, 0, 8);
    }

    /* renamed from: a */
    public void mo9422a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            mo9416a(bytes.length);
            this.f1483e.mo9462b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    /* renamed from: a */
    public void mo9423a(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit() - byteBuffer.position();
        mo9416a(limit);
        this.f1483e.mo9462b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    /* renamed from: f */
    public TStruct mo9432f() {
        return f1446f;
    }

    /* renamed from: g */
    public void mo9433g() {
    }

    /* renamed from: h */
    public TField mo9434h() {
        byte q = mo9443q();
        return new TField("", q, q == 0 ? 0 : mo9444r());
    }

    /* renamed from: i */
    public void mo9435i() {
    }

    /* renamed from: j */
    public TMap mo9436j() {
        return new TMap(mo9443q(), mo9443q(), mo9445s());
    }

    /* renamed from: k */
    public void mo9437k() {
    }

    /* renamed from: l */
    public TList mo9438l() {
        return new TList(mo9443q(), mo9445s());
    }

    /* renamed from: m */
    public void mo9439m() {
    }

    /* renamed from: n */
    public TSet mo9440n() {
        return new TSet(mo9443q(), mo9445s());
    }

    /* renamed from: o */
    public void mo9441o() {
    }

    /* renamed from: p */
    public boolean mo9442p() {
        return mo9443q() == 1;
    }

    /* renamed from: q */
    public byte mo9443q() {
        if (this.f1483e.mo9469d() >= 1) {
            byte b = this.f1483e.mo9466b()[this.f1483e.mo9467c()];
            this.f1483e.mo9464a(1);
            return b;
        }
        m1946a(this.f1455k, 0, 1);
        return this.f1455k[0];
    }

    /* renamed from: r */
    public short mo9444r() {
        int i = 0;
        byte[] bArr = this.f1456l;
        if (this.f1483e.mo9469d() >= 2) {
            bArr = this.f1483e.mo9466b();
            i = this.f1483e.mo9467c();
            this.f1483e.mo9464a(2);
        } else {
            m1946a(this.f1456l, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    /* renamed from: s */
    public int mo9445s() {
        int i = 0;
        byte[] bArr = this.f1457m;
        if (this.f1483e.mo9469d() >= 4) {
            bArr = this.f1483e.mo9466b();
            i = this.f1483e.mo9467c();
            this.f1483e.mo9464a(4);
        } else {
            m1946a(this.f1457m, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    /* renamed from: t */
    public long mo9446t() {
        int i = 0;
        byte[] bArr = this.f1458n;
        if (this.f1483e.mo9469d() >= 8) {
            bArr = this.f1483e.mo9466b();
            i = this.f1483e.mo9467c();
            this.f1483e.mo9464a(8);
        } else {
            m1946a(this.f1458n, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48) | (((long) (bArr[i + 2] & 255)) << 40) | (((long) (bArr[i + 3] & 255)) << 32) | (((long) (bArr[i + 4] & 255)) << 24) | (((long) (bArr[i + 5] & 255)) << 16) | (((long) (bArr[i + 6] & 255)) << 8);
    }

    /* renamed from: u */
    public double mo9447u() {
        return Double.longBitsToDouble(mo9446t());
    }

    /* renamed from: v */
    public String mo9448v() {
        int s = mo9445s();
        if (this.f1483e.mo9469d() < s) {
            return mo9425b(s);
        }
        try {
            String str = new String(this.f1483e.mo9466b(), this.f1483e.mo9467c(), s, "UTF-8");
            this.f1483e.mo9464a(s);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    /* renamed from: b */
    public String mo9425b(int i) {
        try {
            mo9430d(i);
            byte[] bArr = new byte[i];
            this.f1483e.mo9471d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    /* renamed from: w */
    public ByteBuffer mo9449w() {
        int s = mo9445s();
        mo9430d(s);
        if (this.f1483e.mo9469d() >= s) {
            ByteBuffer wrap = ByteBuffer.wrap(this.f1483e.mo9466b(), this.f1483e.mo9467c(), s);
            this.f1483e.mo9464a(s);
            return wrap;
        }
        byte[] bArr = new byte[s];
        this.f1483e.mo9471d(bArr, 0, s);
        return ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private int m1946a(byte[] bArr, int i, int i2) {
        mo9430d(i2);
        return this.f1483e.mo9471d(bArr, i, i2);
    }

    /* renamed from: c */
    public void mo9428c(int i) {
        this.f1449c = i;
        this.f1450d = true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo9430d(int i) {
        if (i < 0) {
            throw new TProtocolException("Negative length: " + i);
        } else if (this.f1450d) {
            this.f1449c -= i;
            if (this.f1449c < 0) {
                throw new TProtocolException("Message length exceeded: " + i);
            }
        }
    }
}
