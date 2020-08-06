package p018c.p019a.p020a;

import com.facebook.appevents.AppEventsConstants;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import p018c.Headers;
import p018c.Interceptor;
import p018c.Protocol;
import p018c.Request;
import p018c.Response;
import p018c.ResponseBody;
import p018c.p019a.Internal;
import p018c.p019a.Util;
import p018c.p019a.p020a.CacheStrategy;
import p018c.p019a.p022c.C0478f;
import p018c.p019a.p022c.HttpHeaders;
import p018c.p019a.p022c.RealResponseBody;
import p033d.Buffer;
import p033d.BufferedSink;
import p033d.BufferedSource;
import p033d.Okio;
import p033d.Sink;
import p033d.Source;
import p033d.Timeout;

/* renamed from: c.a.a.a */
public final class CacheInterceptor implements Interceptor {

    /* renamed from: a */
    final InternalCache f419a;

    public CacheInterceptor(InternalCache fVar) {
        this.f419a = fVar;
    }

    /* renamed from: a */
    public Response mo8591a(Interceptor.C0537a aVar) {
        Response zVar = null;
        Response a = this.f419a != null ? this.f419a.mo8628a(aVar.mo8676a()) : zVar;
        CacheStrategy a2 = new CacheStrategy.C0468a(System.currentTimeMillis(), aVar.mo8676a(), a).mo8597a();
        Request xVar = a2.f425a;
        Response zVar2 = a2.f426b;
        if (this.f419a != null) {
            this.f419a.mo8630a(a2);
        }
        if (a != null && zVar2 == null) {
            Util.m652a((Closeable) a.mo9051h());
        }
        if (xVar == null && zVar2 == null) {
            return new Response.C0543a().mo9065a(aVar.mo8676a()).mo9064a(Protocol.HTTP_1_1).mo9059a(504).mo9067a("Unsatisfiable Request (only-if-cached)").mo9061a(Util.f528c).mo9060a(-1).mo9070b(System.currentTimeMillis()).mo9069a();
        }
        if (xVar == null) {
            return zVar2.mo9052i().mo9071b(m535a(zVar2)).mo9069a();
        }
        try {
            zVar = aVar.mo8677a(xVar);
            if (zVar2 != null) {
                if (zVar.mo9045c() == 304) {
                    Response a3 = zVar2.mo9052i().mo9063a(m533a(zVar2.mo9050g(), zVar.mo9050g())).mo9060a(zVar.mo9056m()).mo9070b(zVar.mo9057n()).mo9071b(m535a(zVar2)).mo9066a(m535a(zVar)).mo9069a();
                    zVar.mo9051h().close();
                    this.f419a.mo8629a();
                    this.f419a.mo8631a(zVar2, a3);
                    return a3;
                }
                Util.m652a((Closeable) zVar2.mo9051h());
            }
            Response a4 = zVar.mo9052i().mo9071b(m535a(zVar2)).mo9066a(m535a(zVar)).mo9069a();
            if (HttpHeaders.m690d(a4)) {
                return m534a(m532a(a4, zVar.mo9041a(), this.f419a), a4);
            }
            return a4;
        } finally {
            if (zVar == null && a != null) {
                Util.m652a((Closeable) a.mo9051h());
            }
        }
    }

    /* renamed from: a */
    private static Response m535a(Response zVar) {
        if (zVar == null || zVar.mo9051h() == null) {
            return zVar;
        }
        return zVar.mo9052i().mo9061a((ResponseBody) null).mo9069a();
    }

    /* renamed from: a */
    private CacheRequest m532a(Response zVar, Request xVar, InternalCache fVar) {
        if (fVar == null) {
            return null;
        }
        if (CacheStrategy.m542a(zVar, xVar)) {
            return fVar.mo8627a(zVar);
        }
        if (!C0478f.m692a(xVar.mo9022b())) {
            return null;
        }
        try {
            fVar.mo8632b(xVar);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /* renamed from: a */
    private Response m534a(final CacheRequest bVar, Response zVar) {
        if (bVar == null) {
            return zVar;
        }
        Sink b = bVar.mo8596b();
        if (b == null) {
            return zVar;
        }
        final BufferedSource b2 = zVar.mo9051h().mo8682b();
        final BufferedSink a = Okio.m3589a(b);
        return zVar.mo9052i().mo9061a((ResponseBody) new RealResponseBody(zVar.mo9050g(), Okio.m3590a((Source) new Source() {

            /* renamed from: a */
            boolean f420a;

            /* renamed from: a */
            public long mo8592a(Buffer cVar, long j) {
                try {
                    long a = b2.mo8592a(cVar, j);
                    if (a == -1) {
                        if (!this.f420a) {
                            this.f420a = true;
                            a.close();
                        }
                        return -1;
                    }
                    cVar.mo17638a(a.mo17653c(), cVar.mo17648b() - a, a);
                    a.mo17694v();
                    return a;
                } catch (IOException e) {
                    if (!this.f420a) {
                        this.f420a = true;
                        bVar.mo8595a();
                    }
                    throw e;
                }
            }

            /* renamed from: a */
            public Timeout mo8593a() {
                return b2.mo8593a();
            }

            public void close() {
                if (!this.f420a && !Util.m654a((Source) this, 100, TimeUnit.MILLISECONDS)) {
                    this.f420a = true;
                    bVar.mo8595a();
                }
                b2.close();
            }
        }))).mo9069a();
    }

    /* renamed from: a */
    private static Headers m533a(Headers qVar, Headers qVar2) {
        Headers.C0534a aVar = new Headers.C0534a();
        int a = qVar.mo8937a();
        for (int i = 0; i < a; i++) {
            String a2 = qVar.mo8938a(i);
            String b = qVar.mo8941b(i);
            if ((!"Warning".equalsIgnoreCase(a2) || !b.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES)) && (!m536a(a2) || qVar2.mo8939a(a2) == null)) {
                Internal.f418a.mo8587a(aVar, a2, b);
            }
        }
        int a3 = qVar2.mo8937a();
        for (int i2 = 0; i2 < a3; i2++) {
            String a4 = qVar2.mo8938a(i2);
            if (!"Content-Length".equalsIgnoreCase(a4) && m536a(a4)) {
                Internal.f418a.mo8587a(aVar, a4, qVar2.mo8941b(i2));
            }
        }
        return aVar.mo8948a();
    }

    /* renamed from: a */
    static boolean m536a(String str) {
        if ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
}
