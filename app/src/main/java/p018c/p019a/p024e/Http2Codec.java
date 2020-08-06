package p018c.p019a.p024e;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import p018c.Headers;
import p018c.OkHttpClient;
import p018c.Protocol;
import p018c.Request;
import p018c.Response;
import p018c.ResponseBody;
import p018c.p019a.Internal;
import p018c.p019a.Util;
import p018c.p019a.p021b.StreamAllocation;
import p018c.p019a.p022c.HttpCodec;
import p018c.p019a.p022c.RealResponseBody;
import p018c.p019a.p022c.RequestLine;
import p018c.p019a.p022c.StatusLine;
import p033d.ByteString;
import p033d.ForwardingSource;
import p033d.Okio;
import p033d.Sink;
import p033d.Source;

/* renamed from: c.a.e.f */
public final class Http2Codec implements HttpCodec {

    /* renamed from: b */
    private static final ByteString f624b = ByteString.encodeUtf8("connection");

    /* renamed from: c */
    private static final ByteString f625c = ByteString.encodeUtf8("host");

    /* renamed from: d */
    private static final ByteString f626d = ByteString.encodeUtf8("keep-alive");

    /* renamed from: e */
    private static final ByteString f627e = ByteString.encodeUtf8("proxy-connection");

    /* renamed from: f */
    private static final ByteString f628f = ByteString.encodeUtf8("transfer-encoding");

    /* renamed from: g */
    private static final ByteString f629g = ByteString.encodeUtf8("te");

    /* renamed from: h */
    private static final ByteString f630h = ByteString.encodeUtf8("encoding");

    /* renamed from: i */
    private static final ByteString f631i = ByteString.encodeUtf8("upgrade");

    /* renamed from: j */
    private static final List<ByteString> f632j = Util.m648a((T[]) new ByteString[]{f624b, f625c, f626d, f627e, f629g, f628f, f630h, f631i, Header.f593c, Header.f594d, Header.f595e, Header.f596f});

    /* renamed from: k */
    private static final List<ByteString> f633k = Util.m648a((T[]) new ByteString[]{f624b, f625c, f626d, f627e, f629g, f628f, f630h, f631i});

    /* renamed from: a */
    final StreamAllocation f634a;

    /* renamed from: l */
    private final OkHttpClient f635l;

    /* renamed from: m */
    private final Http2Connection f636m;

    /* renamed from: n */
    private Http2Stream f637n;

    /* renamed from: c.a.e.f$a */
    /* compiled from: Http2Codec */
    class C0488a extends ForwardingSource {
        public C0488a(Source sVar) {
            super(sVar);
        }

        public void close() {
            Http2Codec.this.f634a.mo8660a(false, (HttpCodec) Http2Codec.this);
            super.close();
        }
    }

    public Http2Codec(OkHttpClient uVar, StreamAllocation gVar, Http2Connection gVar2) {
        this.f635l = uVar;
        this.f634a = gVar;
        this.f636m = gVar2;
    }

    /* renamed from: a */
    public Sink mo8670a(Request xVar, long j) {
        return this.f637n.mo8760h();
    }

    /* renamed from: a */
    public void mo8672a(Request xVar) {
        if (this.f637n == null) {
            this.f637n = this.f636m.mo8709a(m775b(xVar), xVar.mo9025d() != null);
            this.f637n.mo8757e().mo17745a((long) this.f635l.mo8987b(), TimeUnit.MILLISECONDS);
            this.f637n.mo8758f().mo17745a((long) this.f635l.mo8988c(), TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: a */
    public void mo8671a() {
        this.f636m.mo8722b();
    }

    /* renamed from: b */
    public void mo8673b() {
        this.f637n.mo8760h().close();
    }

    /* renamed from: a */
    public Response.C0543a mo8669a(boolean z) {
        Response.C0543a a = m774a(this.f637n.mo8756d());
        if (!z || Internal.f418a.mo8582a(a) != 100) {
            return a;
        }
        return null;
    }

    /* renamed from: b */
    public static List<Header> m775b(Request xVar) {
        Headers c = xVar.mo9024c();
        ArrayList arrayList = new ArrayList(c.mo8937a() + 4);
        arrayList.add(new Header(Header.f593c, xVar.mo9022b()));
        arrayList.add(new Header(Header.f594d, RequestLine.m705a(xVar.mo9020a())));
        String a = xVar.mo9021a("Host");
        if (a != null) {
            arrayList.add(new Header(Header.f596f, a));
        }
        arrayList.add(new Header(Header.f595e, xVar.mo9020a().mo8954b()));
        int a2 = c.mo8937a();
        for (int i = 0; i < a2; i++) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(c.mo8938a(i).toLowerCase(Locale.US));
            if (!f632j.contains(encodeUtf8)) {
                arrayList.add(new Header(encodeUtf8, c.mo8941b(i)));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static Response.C0543a m774a(List<Header> list) {
        Headers.C0534a aVar;
        StatusLine kVar;
        Headers.C0534a aVar2 = new Headers.C0534a();
        int size = list.size();
        int i = 0;
        StatusLine kVar2 = null;
        while (i < size) {
            Header cVar = list.get(i);
            if (cVar == null) {
                if (kVar2 != null && kVar2.f565b == 100) {
                    aVar = new Headers.C0534a();
                    kVar = null;
                }
                aVar = aVar2;
                kVar = kVar2;
            } else {
                ByteString fVar = cVar.f597g;
                String utf8 = cVar.f598h.utf8();
                if (fVar.equals(Header.f592b)) {
                    Headers.C0534a aVar3 = aVar2;
                    kVar = StatusLine.m716a("HTTP/1.1 " + utf8);
                    aVar = aVar3;
                } else {
                    if (!f633k.contains(fVar)) {
                        Internal.f418a.mo8587a(aVar2, fVar.utf8(), utf8);
                    }
                    aVar = aVar2;
                    kVar = kVar2;
                }
            }
            i++;
            kVar2 = kVar;
            aVar2 = aVar;
        }
        if (kVar2 != null) {
            return new Response.C0543a().mo9064a(Protocol.HTTP_2).mo9059a(kVar2.f565b).mo9067a(kVar2.f566c).mo9063a(aVar2.mo8948a());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    /* renamed from: a */
    public ResponseBody mo8668a(Response zVar) {
        return new RealResponseBody(zVar.mo9050g(), Okio.m3590a((Source) new C0488a(this.f637n.mo8759g())));
    }
}
