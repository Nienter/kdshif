package p018c.p019a.p021b;

import p018c.Interceptor;
import p018c.OkHttpClient;
import p018c.Request;
import p018c.Response;
import p018c.p019a.p022c.RealInterceptorChain;

/* renamed from: c.a.b.a */
public final class ConnectInterceptor implements Interceptor {

    /* renamed from: a */
    public final OkHttpClient f484a;

    public ConnectInterceptor(OkHttpClient uVar) {
        this.f484a = uVar;
    }

    /* renamed from: a */
    public Response mo8591a(Interceptor.C0537a aVar) {
        RealInterceptorChain gVar = (RealInterceptorChain) aVar;
        Request a = gVar.mo8676a();
        StreamAllocation b = gVar.mo8679b();
        return gVar.mo8678a(a, b, b.mo8657a(this.f484a, !a.mo9022b().equals("GET")), b.mo8661b());
    }
}
