package p018c;

import java.io.Closeable;
import p018c.Headers;

/* renamed from: c.z */
public final class Response implements Closeable {

    /* renamed from: a */
    final Request f1131a;

    /* renamed from: b */
    final Protocol f1132b;

    /* renamed from: c */
    final int f1133c;

    /* renamed from: d */
    final String f1134d;

    /* renamed from: e */
    final Handshake f1135e;

    /* renamed from: f */
    final Headers f1136f;

    /* renamed from: g */
    final ResponseBody f1137g;

    /* renamed from: h */
    final Response f1138h;

    /* renamed from: i */
    final Response f1139i;

    /* renamed from: j */
    final Response f1140j;

    /* renamed from: k */
    final long f1141k;

    /* renamed from: l */
    final long f1142l;

    /* renamed from: m */
    private volatile CacheControl f1143m;

    /* renamed from: c.z$a */
    /* compiled from: Response */
    public static class C0543a {

        /* renamed from: a */
        Request f1144a;

        /* renamed from: b */
        Protocol f1145b;

        /* renamed from: c */
        int f1146c;

        /* renamed from: d */
        String f1147d;

        /* renamed from: e */
        Handshake f1148e;

        /* renamed from: f */
        Headers.C0534a f1149f;

        /* renamed from: g */
        ResponseBody f1150g;

        /* renamed from: h */
        Response f1151h;

        /* renamed from: i */
        Response f1152i;

        /* renamed from: j */
        Response f1153j;

        /* renamed from: k */
        long f1154k;

        /* renamed from: l */
        long f1155l;

        public C0543a() {
            this.f1146c = -1;
            this.f1149f = new Headers.C0534a();
        }

        C0543a(Response zVar) {
            this.f1146c = -1;
            this.f1144a = zVar.f1131a;
            this.f1145b = zVar.f1132b;
            this.f1146c = zVar.f1133c;
            this.f1147d = zVar.f1134d;
            this.f1148e = zVar.f1135e;
            this.f1149f = zVar.f1136f.mo8940b();
            this.f1150g = zVar.f1137g;
            this.f1151h = zVar.f1138h;
            this.f1152i = zVar.f1139i;
            this.f1153j = zVar.f1140j;
            this.f1154k = zVar.f1141k;
            this.f1155l = zVar.f1142l;
        }

        /* renamed from: a */
        public C0543a mo9065a(Request xVar) {
            this.f1144a = xVar;
            return this;
        }

        /* renamed from: a */
        public C0543a mo9064a(Protocol vVar) {
            this.f1145b = vVar;
            return this;
        }

        /* renamed from: a */
        public C0543a mo9059a(int i) {
            this.f1146c = i;
            return this;
        }

        /* renamed from: a */
        public C0543a mo9067a(String str) {
            this.f1147d = str;
            return this;
        }

        /* renamed from: a */
        public C0543a mo9062a(Handshake pVar) {
            this.f1148e = pVar;
            return this;
        }

        /* renamed from: a */
        public C0543a mo9068a(String str, String str2) {
            this.f1149f.mo8947a(str, str2);
            return this;
        }

        /* renamed from: a */
        public C0543a mo9063a(Headers qVar) {
            this.f1149f = qVar.mo8940b();
            return this;
        }

        /* renamed from: a */
        public C0543a mo9061a(ResponseBody aaVar) {
            this.f1150g = aaVar;
            return this;
        }

        /* renamed from: a */
        public C0543a mo9066a(Response zVar) {
            if (zVar != null) {
                m1295a("networkResponse", zVar);
            }
            this.f1151h = zVar;
            return this;
        }

        /* renamed from: b */
        public C0543a mo9071b(Response zVar) {
            if (zVar != null) {
                m1295a("cacheResponse", zVar);
            }
            this.f1152i = zVar;
            return this;
        }

        /* renamed from: a */
        private void m1295a(String str, Response zVar) {
            if (zVar.f1137g != null) {
                throw new IllegalArgumentException(str + ".body != null");
            } else if (zVar.f1138h != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            } else if (zVar.f1139i != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            } else if (zVar.f1140j != null) {
                throw new IllegalArgumentException(str + ".priorResponse != null");
            }
        }

        /* renamed from: c */
        public C0543a mo9072c(Response zVar) {
            if (zVar != null) {
                m1296d(zVar);
            }
            this.f1153j = zVar;
            return this;
        }

        /* renamed from: d */
        private void m1296d(Response zVar) {
            if (zVar.f1137g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        /* renamed from: a */
        public C0543a mo9060a(long j) {
            this.f1154k = j;
            return this;
        }

        /* renamed from: b */
        public C0543a mo9070b(long j) {
            this.f1155l = j;
            return this;
        }

        /* renamed from: a */
        public Response mo9069a() {
            if (this.f1144a == null) {
                throw new IllegalStateException("request == null");
            } else if (this.f1145b == null) {
                throw new IllegalStateException("protocol == null");
            } else if (this.f1146c >= 0) {
                return new Response(this);
            } else {
                throw new IllegalStateException("code < 0: " + this.f1146c);
            }
        }
    }

    Response(C0543a aVar) {
        this.f1131a = aVar.f1144a;
        this.f1132b = aVar.f1145b;
        this.f1133c = aVar.f1146c;
        this.f1134d = aVar.f1147d;
        this.f1135e = aVar.f1148e;
        this.f1136f = aVar.f1149f.mo8948a();
        this.f1137g = aVar.f1150g;
        this.f1138h = aVar.f1151h;
        this.f1139i = aVar.f1152i;
        this.f1140j = aVar.f1153j;
        this.f1141k = aVar.f1154k;
        this.f1142l = aVar.f1155l;
    }

    /* renamed from: a */
    public Request mo9041a() {
        return this.f1131a;
    }

    /* renamed from: b */
    public Protocol mo9044b() {
        return this.f1132b;
    }

    /* renamed from: c */
    public int mo9045c() {
        return this.f1133c;
    }

    /* renamed from: d */
    public boolean mo9047d() {
        return this.f1133c >= 200 && this.f1133c < 300;
    }

    /* renamed from: e */
    public String mo9048e() {
        return this.f1134d;
    }

    /* renamed from: f */
    public Handshake mo9049f() {
        return this.f1135e;
    }

    /* renamed from: a */
    public String mo9042a(String str) {
        return mo9043a(str, null);
    }

    /* renamed from: a */
    public String mo9043a(String str, String str2) {
        String a = this.f1136f.mo8939a(str);
        return a != null ? a : str2;
    }

    /* renamed from: g */
    public Headers mo9050g() {
        return this.f1136f;
    }

    /* renamed from: h */
    public ResponseBody mo9051h() {
        return this.f1137g;
    }

    /* renamed from: i */
    public C0543a mo9052i() {
        return new C0543a(this);
    }

    /* renamed from: j */
    public Response mo9053j() {
        return this.f1138h;
    }

    /* renamed from: k */
    public Response mo9054k() {
        return this.f1139i;
    }

    /* renamed from: l */
    public CacheControl mo9055l() {
        CacheControl dVar = this.f1143m;
        if (dVar != null) {
            return dVar;
        }
        CacheControl a = CacheControl.m1053a(this.f1136f);
        this.f1143m = a;
        return a;
    }

    /* renamed from: m */
    public long mo9056m() {
        return this.f1141k;
    }

    /* renamed from: n */
    public long mo9057n() {
        return this.f1142l;
    }

    public void close() {
        this.f1137g.close();
    }

    public String toString() {
        return "Response{protocol=" + this.f1132b + ", code=" + this.f1133c + ", message=" + this.f1134d + ", url=" + this.f1131a.mo9020a() + '}';
    }
}
