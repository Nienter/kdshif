package p018c;

import java.util.List;
import p018c.Headers;
import p018c.p019a.p022c.C0478f;

/* renamed from: c.x */
public final class Request {

    /* renamed from: a */
    final HttpUrl f1116a;

    /* renamed from: b */
    final String f1117b;

    /* renamed from: c */
    final Headers f1118c;

    /* renamed from: d */
    final RequestBody f1119d;

    /* renamed from: e */
    final Object f1120e;

    /* renamed from: f */
    private volatile CacheControl f1121f;

    /* renamed from: c.x$a */
    /* compiled from: Request */
    public static class C0541a {

        /* renamed from: a */
        HttpUrl f1122a;

        /* renamed from: b */
        String f1123b;

        /* renamed from: c */
        Headers.C0534a f1124c;

        /* renamed from: d */
        RequestBody f1125d;

        /* renamed from: e */
        Object f1126e;

        public C0541a() {
            this.f1123b = "GET";
            this.f1124c = new Headers.C0534a();
        }

        C0541a(Request xVar) {
            this.f1122a = xVar.f1116a;
            this.f1123b = xVar.f1117b;
            this.f1125d = xVar.f1119d;
            this.f1126e = xVar.f1120e;
            this.f1124c = xVar.f1118c.mo8940b();
        }

        /* renamed from: a */
        public C0541a mo9032a(HttpUrl rVar) {
            if (rVar == null) {
                throw new NullPointerException("url == null");
            }
            this.f1122a = rVar;
            return this;
        }

        /* renamed from: a */
        public C0541a mo9033a(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            HttpUrl e = HttpUrl.m1164e(str);
            if (e != null) {
                return mo9032a(e);
            }
            throw new IllegalArgumentException("unexpected url: " + str);
        }

        /* renamed from: a */
        public C0541a mo9035a(String str, String str2) {
            this.f1124c.mo8951c(str, str2);
            return this;
        }

        /* renamed from: b */
        public C0541a mo9037b(String str) {
            this.f1124c.mo8949b(str);
            return this;
        }

        /* renamed from: a */
        public C0541a mo9031a(Headers qVar) {
            this.f1124c = qVar.mo8940b();
            return this;
        }

        /* renamed from: a */
        public C0541a mo9030a(CacheControl dVar) {
            String dVar2 = dVar.toString();
            if (dVar2.isEmpty()) {
                return mo9037b("Cache-Control");
            }
            return mo9035a("Cache-Control", dVar2);
        }

        /* renamed from: a */
        public C0541a mo9034a(String str, RequestBody yVar) {
            if (str == null) {
                throw new NullPointerException("method == null");
            } else if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            } else if (yVar != null && !C0478f.m694c(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            } else if (yVar != null || !C0478f.m693b(str)) {
                this.f1123b = str;
                this.f1125d = yVar;
                return this;
            } else {
                throw new IllegalArgumentException("method " + str + " must have a request body.");
            }
        }

        /* renamed from: a */
        public Request mo9036a() {
            if (this.f1122a != null) {
                return new Request(this);
            }
            throw new IllegalStateException("url == null");
        }
    }

    Request(C0541a aVar) {
        Object obj;
        this.f1116a = aVar.f1122a;
        this.f1117b = aVar.f1123b;
        this.f1118c = aVar.f1124c.mo8948a();
        this.f1119d = aVar.f1125d;
        if (aVar.f1126e != null) {
            obj = aVar.f1126e;
        } else {
            obj = this;
        }
        this.f1120e = obj;
    }

    /* renamed from: a */
    public HttpUrl mo9020a() {
        return this.f1116a;
    }

    /* renamed from: b */
    public String mo9022b() {
        return this.f1117b;
    }

    /* renamed from: c */
    public Headers mo9024c() {
        return this.f1118c;
    }

    /* renamed from: a */
    public String mo9021a(String str) {
        return this.f1118c.mo8939a(str);
    }

    /* renamed from: b */
    public List<String> mo9023b(String str) {
        return this.f1118c.mo8942b(str);
    }

    /* renamed from: d */
    public RequestBody mo9025d() {
        return this.f1119d;
    }

    /* renamed from: e */
    public C0541a mo9026e() {
        return new C0541a(this);
    }

    /* renamed from: f */
    public CacheControl mo9027f() {
        CacheControl dVar = this.f1121f;
        if (dVar != null) {
            return dVar;
        }
        CacheControl a = CacheControl.m1053a(this.f1118c);
        this.f1121f = a;
        return a;
    }

    /* renamed from: g */
    public boolean mo9028g() {
        return this.f1116a.mo8956c();
    }

    public String toString() {
        return "Request{method=" + this.f1117b + ", url=" + this.f1116a + ", tag=" + (this.f1120e != this ? this.f1120e : null) + '}';
    }
}
