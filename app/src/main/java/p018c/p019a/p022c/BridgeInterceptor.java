package p018c.p019a.p022c;

import java.util.List;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;
import p018c.Cookie;
import p018c.CookieJar;
import p018c.Headers;
import p018c.Interceptor;
import p018c.MediaType;
import p018c.Request;
import p018c.RequestBody;
import p018c.Response;
import p018c.ResponseBody;
import p018c.p019a.Util;
import p018c.p019a.Version;
import p033d.GzipSource;
import p033d.Okio;
import p033d.Source;

/* renamed from: c.a.c.a */
public final class BridgeInterceptor implements Interceptor {

    /* renamed from: a */
    private final CookieJar f544a;

    public BridgeInterceptor(CookieJar mVar) {
        this.f544a = mVar;
    }

    /* renamed from: a */
    public Response mo8591a(Interceptor.C0537a aVar) {
        boolean z = false;
        Request a = aVar.mo8676a();
        Request.C0541a e = a.mo9026e();
        RequestBody d = a.mo9025d();
        if (d != null) {
            MediaType a2 = d.mo9038a();
            if (a2 != null) {
                e.mo9035a("Content-Type", a2.toString());
            }
            long b = d.mo9040b();
            if (b != -1) {
                e.mo9035a("Content-Length", Long.toString(b));
                e.mo9037b("Transfer-Encoding");
            } else {
                e.mo9035a("Transfer-Encoding", "chunked");
                e.mo9037b("Content-Length");
            }
        }
        if (a.mo9021a("Host") == null) {
            e.mo9035a("Host", Util.m644a(a.mo9020a(), false));
        }
        if (a.mo9021a("Connection") == null) {
            e.mo9035a("Connection", "Keep-Alive");
        }
        if (a.mo9021a("Accept-Encoding") == null && a.mo9021a("Range") == null) {
            z = true;
            e.mo9035a("Accept-Encoding", "gzip");
        }
        List<Cookie> a3 = this.f544a.mo8923a(a.mo9020a());
        if (!a3.isEmpty()) {
            e.mo9035a("Cookie", m665a(a3));
        }
        if (a.mo9021a(AbstractSpiCall.HEADER_USER_AGENT) == null) {
            e.mo9035a(AbstractSpiCall.HEADER_USER_AGENT, Version.m717a());
        }
        Response a4 = aVar.mo8677a(e.mo9036a());
        HttpHeaders.m683a(this.f544a, a.mo9020a(), a4.mo9050g());
        Response.C0543a a5 = a4.mo9052i().mo9065a(a);
        if (z && "gzip".equalsIgnoreCase(a4.mo9042a("Content-Encoding")) && HttpHeaders.m690d(a4)) {
            GzipSource jVar = new GzipSource(a4.mo9051h().mo8682b());
            Headers a6 = a4.mo9050g().mo8940b().mo8949b("Content-Encoding").mo8949b("Content-Length").mo8948a();
            a5.mo9063a(a6);
            a5.mo9061a((ResponseBody) new RealResponseBody(a6, Okio.m3590a((Source) jVar)));
        }
        return a5.mo9069a();
    }

    /* renamed from: a */
    private String m665a(List<Cookie> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            Cookie lVar = list.get(i);
            sb.append(lVar.mo8917a()).append('=').append(lVar.mo8919b());
        }
        return sb.toString();
    }
}
