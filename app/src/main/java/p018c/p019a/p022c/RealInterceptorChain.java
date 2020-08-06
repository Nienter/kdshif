package p018c.p019a.p022c;

import java.util.List;
import p018c.Connection;
import p018c.HttpUrl;
import p018c.Interceptor;
import p018c.Request;
import p018c.Response;
import p018c.p019a.p021b.StreamAllocation;

/* renamed from: c.a.c.g */
public final class RealInterceptorChain implements Interceptor.C0537a {

    /* renamed from: a */
    private final List<Interceptor> f550a;

    /* renamed from: b */
    private final StreamAllocation f551b;

    /* renamed from: c */
    private final HttpCodec f552c;

    /* renamed from: d */
    private final Connection f553d;

    /* renamed from: e */
    private final int f554e;

    /* renamed from: f */
    private final Request f555f;

    /* renamed from: g */
    private int f556g;

    public RealInterceptorChain(List<Interceptor> list, StreamAllocation gVar, HttpCodec cVar, Connection iVar, int i, Request xVar) {
        this.f550a = list;
        this.f553d = iVar;
        this.f551b = gVar;
        this.f552c = cVar;
        this.f554e = i;
        this.f555f = xVar;
    }

    /* renamed from: b */
    public StreamAllocation mo8679b() {
        return this.f551b;
    }

    /* renamed from: c */
    public HttpCodec mo8680c() {
        return this.f552c;
    }

    /* renamed from: a */
    public Request mo8676a() {
        return this.f555f;
    }

    /* renamed from: a */
    public Response mo8677a(Request xVar) {
        return mo8678a(xVar, this.f551b, this.f552c, this.f553d);
    }

    /* renamed from: a */
    public Response mo8678a(Request xVar, StreamAllocation gVar, HttpCodec cVar, Connection iVar) {
        if (this.f554e >= this.f550a.size()) {
            throw new AssertionError();
        }
        this.f556g++;
        if (this.f552c != null && !m697a(xVar.mo9020a())) {
            throw new IllegalStateException("network interceptor " + this.f550a.get(this.f554e - 1) + " must retain the same host and port");
        } else if (this.f552c == null || this.f556g <= 1) {
            RealInterceptorChain gVar2 = new RealInterceptorChain(this.f550a, gVar, cVar, iVar, this.f554e + 1, xVar);
            Response a = this.f550a.get(this.f554e).mo8591a(gVar2);
            if (cVar != null && this.f554e + 1 < this.f550a.size() && gVar2.f556g != 1) {
                throw new IllegalStateException("network interceptor " + r1 + " must call proceed() exactly once");
            } else if (a != null) {
                return a;
            } else {
                throw new NullPointerException("interceptor " + r1 + " returned null");
            }
        } else {
            throw new IllegalStateException("network interceptor " + this.f550a.get(this.f554e - 1) + " must call proceed() exactly once");
        }
    }

    /* renamed from: a */
    private boolean m697a(HttpUrl rVar) {
        return rVar.mo8961f().equals(this.f553d.mo8638a().mo8845a().mo8568a().mo8961f()) && rVar.mo8962g() == this.f553d.mo8638a().mo8845a().mo8568a().mo8962g();
    }
}
