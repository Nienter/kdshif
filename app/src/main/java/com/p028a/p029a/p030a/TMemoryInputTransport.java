package com.p028a.p029a.p030a;

/* renamed from: com.a.a.a.ch */
public final class TMemoryInputTransport extends TTransport {

    /* renamed from: a */
    private byte[] f1498a;

    /* renamed from: b */
    private int f1499b;

    /* renamed from: c */
    private int f1500c;

    /* renamed from: a */
    public void mo9465a(byte[] bArr) {
        mo9468c(bArr, 0, bArr.length);
    }

    /* renamed from: c */
    public void mo9468c(byte[] bArr, int i, int i2) {
        this.f1498a = bArr;
        this.f1499b = i;
        this.f1500c = i + i2;
    }

    /* renamed from: a */
    public void mo9463a() {
        this.f1498a = null;
    }

    /* renamed from: a */
    public int mo9461a(byte[] bArr, int i, int i2) {
        int d = mo9469d();
        if (i2 > d) {
            i2 = d;
        }
        if (i2 > 0) {
            System.arraycopy(this.f1498a, this.f1499b, bArr, i, i2);
            mo9464a(i2);
        }
        return i2;
    }

    /* renamed from: b */
    public void mo9462b(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    /* renamed from: b */
    public byte[] mo9466b() {
        return this.f1498a;
    }

    /* renamed from: c */
    public int mo9467c() {
        return this.f1499b;
    }

    /* renamed from: d */
    public int mo9469d() {
        return this.f1500c - this.f1499b;
    }

    /* renamed from: a */
    public void mo9464a(int i) {
        this.f1499b += i;
    }
}
