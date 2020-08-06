package p033d;

/* renamed from: d.o */
final class Segment {

    /* renamed from: a */
    final byte[] f2684a;

    /* renamed from: b */
    int f2685b;

    /* renamed from: c */
    int f2686c;

    /* renamed from: d */
    boolean f2687d;

    /* renamed from: e */
    boolean f2688e;

    /* renamed from: f */
    Segment f2689f;

    /* renamed from: g */
    Segment f2690g;

    Segment() {
        this.f2684a = new byte[8192];
        this.f2688e = true;
        this.f2687d = false;
    }

    Segment(Segment oVar) {
        this(oVar.f2684a, oVar.f2685b, oVar.f2686c);
        oVar.f2687d = true;
    }

    Segment(byte[] bArr, int i, int i2) {
        this.f2684a = bArr;
        this.f2685b = i;
        this.f2686c = i2;
        this.f2688e = false;
        this.f2687d = true;
    }

    /* renamed from: a */
    public Segment mo17765a() {
        Segment oVar = this.f2689f != this ? this.f2689f : null;
        this.f2690g.f2689f = this.f2689f;
        this.f2689f.f2690g = this.f2690g;
        this.f2689f = null;
        this.f2690g = null;
        return oVar;
    }

    /* renamed from: a */
    public Segment mo17767a(Segment oVar) {
        oVar.f2690g = this;
        oVar.f2689f = this.f2689f;
        this.f2689f.f2690g = oVar;
        this.f2689f = oVar;
        return oVar;
    }

    /* renamed from: a */
    public Segment mo17766a(int i) {
        Segment a;
        if (i <= 0 || i > this.f2686c - this.f2685b) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            a = new Segment(this);
        } else {
            a = SegmentPool.m3651a();
            System.arraycopy(this.f2684a, this.f2685b, a.f2684a, 0, i);
        }
        a.f2686c = a.f2685b + i;
        this.f2685b += i;
        this.f2690g.mo17767a(a);
        return a;
    }

    /* renamed from: b */
    public void mo17769b() {
        if (this.f2690g == this) {
            throw new IllegalStateException();
        } else if (this.f2690g.f2688e) {
            int i = this.f2686c - this.f2685b;
            if (i <= (this.f2690g.f2687d ? 0 : this.f2690g.f2685b) + (8192 - this.f2690g.f2686c)) {
                mo17768a(this.f2690g, i);
                mo17765a();
                SegmentPool.m3652a(this);
            }
        }
    }

    /* renamed from: a */
    public void mo17768a(Segment oVar, int i) {
        if (!oVar.f2688e) {
            throw new IllegalArgumentException();
        }
        if (oVar.f2686c + i > 8192) {
            if (oVar.f2687d) {
                throw new IllegalArgumentException();
            } else if ((oVar.f2686c + i) - oVar.f2685b > 8192) {
                throw new IllegalArgumentException();
            } else {
                System.arraycopy(oVar.f2684a, oVar.f2685b, oVar.f2684a, 0, oVar.f2686c - oVar.f2685b);
                oVar.f2686c -= oVar.f2685b;
                oVar.f2685b = 0;
            }
        }
        System.arraycopy(this.f2684a, this.f2685b, oVar.f2684a, oVar.f2686c, i);
        oVar.f2686c += i;
        this.f2685b += i;
    }
}
