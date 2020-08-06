package com.p028a.p029a.p030a;

/* renamed from: com.a.a.a.ci */
public abstract class TTransport {
    /* renamed from: a */
    public abstract int mo9461a(byte[] bArr, int i, int i2);

    /* renamed from: b */
    public abstract void mo9462b(byte[] bArr, int i, int i2);

    /* renamed from: d */
    public int mo9471d(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int a = mo9461a(bArr, i + i3, i2 - i3);
            if (a <= 0) {
                throw new TTransportException("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
            i3 += a;
        }
        return i3;
    }

    /* renamed from: b */
    public void mo9470b(byte[] bArr) {
        mo9462b(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public byte[] mo9466b() {
        return null;
    }

    /* renamed from: c */
    public int mo9467c() {
        return 0;
    }

    /* renamed from: d */
    public int mo9469d() {
        return -1;
    }

    /* renamed from: a */
    public void mo9464a(int i) {
    }
}
