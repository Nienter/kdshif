package p018c.p019a.p022c;

import java.net.ProtocolException;
import p018c.Interceptor;
import p018c.Request;
import p018c.Response;
import p018c.p019a.Util;
import p018c.p019a.p021b.StreamAllocation;
import p033d.BufferedSink;
import p033d.Okio;

/* renamed from: c.a.c.b */
public final class CallServerInterceptor implements Interceptor {

    /* renamed from: a */
    private final boolean f545a;

    public CallServerInterceptor(boolean z) {
        this.f545a = z;
    }

    /* renamed from: a */
    public Response mo8591a(Interceptor.C0537a aVar) {
        Response a;
        HttpCodec c = ((RealInterceptorChain) aVar).mo8680c();
        StreamAllocation b = ((RealInterceptorChain) aVar).mo8679b();
        Request a2 = aVar.mo8676a();
        long currentTimeMillis = System.currentTimeMillis();
        c.mo8672a(a2);
        Response.C0543a aVar2 = null;
        if (C0478f.m694c(a2.mo9022b()) && a2.mo9025d() != null) {
            if ("100-continue".equalsIgnoreCase(a2.mo9021a("Expect"))) {
                c.mo8671a();
                aVar2 = c.mo8669a(true);
            }
            if (aVar2 == null) {
                BufferedSink a3 = Okio.m3589a(c.mo8670a(a2, a2.mo9025d().mo9040b()));
                a2.mo9025d().mo9039a(a3);
                a3.close();
            }
        }
        c.mo8673b();
        if (aVar2 == null) {
            aVar2 = c.mo8669a(false);
        }
        Response a4 = aVar2.mo9065a(a2).mo9062a(b.mo8661b().mo8645c()).mo9060a(currentTimeMillis).mo9070b(System.currentTimeMillis()).mo9069a();
        int c2 = a4.mo9045c();
        if (!this.f545a || c2 != 101) {
            a = a4.mo9052i().mo9061a(c.mo8668a(a4)).mo9069a();
        } else {
            a = a4.mo9052i().mo9061a(Util.f528c).mo9069a();
        }
        if ("close".equalsIgnoreCase(a.mo9041a().mo9021a("Connection")) || "close".equalsIgnoreCase(a.mo9042a("Connection"))) {
            b.mo8664d();
        }
        if ((c2 != 204 && c2 != 205) || a.mo9051h().mo8681a() <= 0) {
            return a;
        }
        throw new ProtocolException("HTTP " + c2 + " had non-zero Content-Length: " + a.mo9051h().mo8681a());
    }
}
