package p033d;

import java.util.concurrent.TimeUnit;

/* renamed from: d.i */
public class ForwardingTimeout extends Timeout {

    /* renamed from: a */
    private Timeout f2661a;

    public ForwardingTimeout(Timeout tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2661a = tVar;
    }

    /* renamed from: a */
    public final Timeout mo17743a() {
        return this.f2661a;
    }

    /* renamed from: a */
    public final ForwardingTimeout mo17742a(Timeout tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2661a = tVar;
        return this;
    }

    /* renamed from: a */
    public Timeout mo17745a(long j, TimeUnit timeUnit) {
        return this.f2661a.mo17745a(j, timeUnit);
    }

    /* renamed from: b_ */
    public long mo17746b_() {
        return this.f2661a.mo17746b_();
    }

    /* renamed from: c_ */
    public boolean mo17747c_() {
        return this.f2661a.mo17747c_();
    }

    /* renamed from: d */
    public long mo17748d() {
        return this.f2661a.mo17748d();
    }

    /* renamed from: a */
    public Timeout mo17744a(long j) {
        return this.f2661a.mo17744a(j);
    }

    /* renamed from: d_ */
    public Timeout mo17749d_() {
        return this.f2661a.mo17749d_();
    }

    /* renamed from: e_ */
    public Timeout mo17750e_() {
        return this.f2661a.mo17750e_();
    }

    /* renamed from: g */
    public void mo17751g() {
        this.f2661a.mo17751g();
    }
}
