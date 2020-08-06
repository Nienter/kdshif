package p018c;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p018c.Headers;
import p018c.Request;
import p018c.Response;
import p018c.p019a.Util;
import p018c.p019a.p020a.CacheRequest;
import p018c.p019a.p020a.CacheStrategy;
import p018c.p019a.p020a.DiskLruCache;
import p018c.p019a.p020a.InternalCache;
import p018c.p019a.p022c.C0478f;
import p018c.p019a.p022c.HttpHeaders;
import p018c.p019a.p022c.StatusLine;
import p018c.p019a.p025f.FileSystem;
import p018c.p019a.p026g.Platform;
import p033d.Buffer;
import p033d.BufferedSink;
import p033d.BufferedSource;
import p033d.ByteString;
import p033d.ForwardingSink;
import p033d.ForwardingSource;
import p033d.Okio;
import p033d.Sink;
import p033d.Source;

/* renamed from: c.c */
public final class Cache implements Closeable, Flushable {

    /* renamed from: a */
    final InternalCache f806a;

    /* renamed from: b */
    final DiskLruCache f807b;

    /* renamed from: c */
    int f808c;

    /* renamed from: d */
    int f809d;

    /* renamed from: e */
    private int f810e;

    /* renamed from: f */
    private int f811f;

    /* renamed from: g */
    private int f812g;

    /* renamed from: c.c$a */
    /* compiled from: Cache */
    private final class C0521a implements CacheRequest {

        /* renamed from: a */
        boolean f814a;

        /* renamed from: c */
        private final DiskLruCache.C0471a f816c;

        /* renamed from: d */
        private Sink f817d;

        /* renamed from: e */
        private Sink f818e;

        public C0521a(final DiskLruCache.C0471a aVar) {
            this.f816c = aVar;
            this.f817d = aVar.mo8614a(1);
            this.f818e = new ForwardingSink(this.f817d, Cache.this) {
                public void close() {
                    synchronized (Cache.this) {
                        if (!C0521a.this.f814a) {
                            C0521a.this.f814a = true;
                            Cache.this.f808c++;
                            super.close();
                            aVar.mo8616b();
                        }
                    }
                }
            };
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* renamed from: a */
        public void mo8595a() {
            synchronized (Cache.this) {
                if (!this.f814a) {
                    this.f814a = true;
                    Cache.this.f809d++;
                    Util.m652a((Closeable) this.f817d);
                    try {
                        this.f816c.mo8617c();
                    } catch (IOException e) {
                    }
                }
            }
        }

        /* renamed from: b */
        public Sink mo8596b() {
            return this.f818e;
        }
    }

    /* renamed from: c.c$b */
    /* compiled from: Cache */
    private static class C0523b extends ResponseBody {

        /* renamed from: a */
        final DiskLruCache.C0474c f822a;

        /* renamed from: b */
        private final BufferedSource f823b;

        /* renamed from: c */
        private final String f824c;

        /* renamed from: d */
        private final String f825d;

        public C0523b(final DiskLruCache.C0474c cVar, String str, String str2) {
            this.f822a = cVar;
            this.f824c = str;
            this.f825d = str2;
            this.f823b = Okio.m3590a((Source) new ForwardingSource(cVar.mo8622a(1)) {
                public void close() {
                    cVar.close();
                    super.close();
                }
            });
        }

        /* renamed from: a */
        public long mo8681a() {
            try {
                if (this.f825d != null) {
                    return Long.parseLong(this.f825d);
                }
                return -1;
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        /* renamed from: b */
        public BufferedSource mo8682b() {
            return this.f823b;
        }
    }

    /* renamed from: c.c$c */
    /* compiled from: Cache */
    private static final class C0525c {

        /* renamed from: a */
        private static final String f828a = (Platform.m981b().mo8833c() + "-Sent-Millis");

        /* renamed from: b */
        private static final String f829b = (Platform.m981b().mo8833c() + "-Received-Millis");

        /* renamed from: c */
        private final String f830c;

        /* renamed from: d */
        private final Headers f831d;

        /* renamed from: e */
        private final String f832e;

        /* renamed from: f */
        private final Protocol f833f;

        /* renamed from: g */
        private final int f834g;

        /* renamed from: h */
        private final String f835h;

        /* renamed from: i */
        private final Headers f836i;

        /* renamed from: j */
        private final Handshake f837j;

        /* renamed from: k */
        private final long f838k;

        /* renamed from: l */
        private final long f839l;

        public C0525c(Source sVar) {
            long j = 0;
            TlsVersion acVar = null;
            try {
                BufferedSource a = Okio.m3590a(sVar);
                this.f830c = a.mo17688q();
                this.f832e = a.mo17688q();
                Headers.C0534a aVar = new Headers.C0534a();
                int a2 = Cache.m1028a(a);
                for (int i = 0; i < a2; i++) {
                    aVar.mo8946a(a.mo17688q());
                }
                this.f831d = aVar.mo8948a();
                StatusLine a3 = StatusLine.m716a(a.mo17688q());
                this.f833f = a3.f564a;
                this.f834g = a3.f565b;
                this.f835h = a3.f566c;
                Headers.C0534a aVar2 = new Headers.C0534a();
                int a4 = Cache.m1028a(a);
                for (int i2 = 0; i2 < a4; i2++) {
                    aVar2.mo8946a(a.mo17688q());
                }
                String c = aVar2.mo8952c(f828a);
                String c2 = aVar2.mo8952c(f829b);
                aVar2.mo8949b(f828a);
                aVar2.mo8949b(f829b);
                this.f838k = c != null ? Long.parseLong(c) : 0;
                this.f839l = c2 != null ? Long.parseLong(c2) : j;
                this.f836i = aVar2.mo8948a();
                if (m1049a()) {
                    if (a.mo17688q().length() > 0) {
                        throw new IOException("expected \"\" but was \"" + r1 + "\"");
                    }
                    this.f837j = Handshake.m1130a(!a.mo17664e() ? TlsVersion.forJavaName(a.mo17688q()) : acVar, CipherSuite.m1081a(a.mo17688q()), m1047a(a), m1047a(a));
                } else {
                    this.f837j = null;
                }
            } finally {
                sVar.close();
            }
        }

        public C0525c(Response zVar) {
            this.f830c = zVar.mo9041a().mo9020a().toString();
            this.f831d = HttpHeaders.m688c(zVar);
            this.f832e = zVar.mo9041a().mo9022b();
            this.f833f = zVar.mo9044b();
            this.f834g = zVar.mo9045c();
            this.f835h = zVar.mo9048e();
            this.f836i = zVar.mo9050g();
            this.f837j = zVar.mo9049f();
            this.f838k = zVar.mo9056m();
            this.f839l = zVar.mo9057n();
        }

        /* renamed from: a */
        public void mo8863a(DiskLruCache.C0471a aVar) {
            BufferedSink a = Okio.m3589a(aVar.mo8614a(0));
            a.mo17652b(this.f830c).mo17677i(10);
            a.mo17652b(this.f832e).mo17677i(10);
            a.mo17681k((long) this.f831d.mo8937a()).mo17677i(10);
            int a2 = this.f831d.mo8937a();
            for (int i = 0; i < a2; i++) {
                a.mo17652b(this.f831d.mo8938a(i)).mo17652b(": ").mo17652b(this.f831d.mo8941b(i)).mo17677i(10);
            }
            a.mo17652b(new StatusLine(this.f833f, this.f834g, this.f835h).toString()).mo17677i(10);
            a.mo17681k((long) (this.f836i.mo8937a() + 2)).mo17677i(10);
            int a3 = this.f836i.mo8937a();
            for (int i2 = 0; i2 < a3; i2++) {
                a.mo17652b(this.f836i.mo8938a(i2)).mo17652b(": ").mo17652b(this.f836i.mo8941b(i2)).mo17677i(10);
            }
            a.mo17652b(f828a).mo17652b(": ").mo17681k(this.f838k).mo17677i(10);
            a.mo17652b(f829b).mo17652b(": ").mo17681k(this.f839l).mo17677i(10);
            if (m1049a()) {
                a.mo17677i(10);
                a.mo17652b(this.f837j.mo8932b().mo8894a()).mo17677i(10);
                m1048a(a, this.f837j.mo8933c());
                m1048a(a, this.f837j.mo8934d());
                if (this.f837j.mo8931a() != null) {
                    a.mo17652b(this.f837j.mo8931a().javaName()).mo17677i(10);
                }
            }
            a.close();
        }

        /* renamed from: a */
        private boolean m1049a() {
            return this.f830c.startsWith("https://");
        }

        /* renamed from: a */
        private List<Certificate> m1047a(BufferedSource eVar) {
            int a = Cache.m1028a(eVar);
            if (a == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(a);
                for (int i = 0; i < a; i++) {
                    String q = eVar.mo17688q();
                    Buffer cVar = new Buffer();
                    cVar.mo17639a(ByteString.decodeBase64(q));
                    arrayList.add(instance.generateCertificate(cVar.mo17667f()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        /* renamed from: a */
        private void m1048a(BufferedSink dVar, List<Certificate> list) {
            try {
                dVar.mo17681k((long) list.size()).mo17677i(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    dVar.mo17652b(ByteString.m3563of(list.get(i).getEncoded()).base64()).mo17677i(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        /* renamed from: a */
        public boolean mo8864a(Request xVar, Response zVar) {
            return this.f830c.equals(xVar.mo9020a().toString()) && this.f832e.equals(xVar.mo9022b()) && HttpHeaders.m684a(zVar, this.f831d, xVar);
        }

        /* renamed from: a */
        public Response mo8862a(DiskLruCache.C0474c cVar) {
            String a = this.f836i.mo8939a("Content-Type");
            String a2 = this.f836i.mo8939a("Content-Length");
            return new Response.C0543a().mo9065a(new Request.C0541a().mo9033a(this.f830c).mo9034a(this.f832e, (RequestBody) null).mo9031a(this.f831d).mo9036a()).mo9064a(this.f833f).mo9059a(this.f834g).mo9067a(this.f835h).mo9063a(this.f836i).mo9061a((ResponseBody) new C0523b(cVar, a, a2)).mo9062a(this.f837j).mo9060a(this.f838k).mo9070b(this.f839l).mo9069a();
        }
    }

    public Cache(File file, long j) {
        this(file, j, FileSystem.f759a);
    }

    Cache(File file, long j, FileSystem aVar) {
        this.f806a = new InternalCache() {
            /* renamed from: a */
            public Response mo8628a(Request xVar) {
                return Cache.this.mo8855a(xVar);
            }

            /* renamed from: a */
            public CacheRequest mo8627a(Response zVar) {
                return Cache.this.mo8854a(zVar);
            }

            /* renamed from: b */
            public void mo8632b(Request xVar) {
                Cache.this.mo8859b(xVar);
            }

            /* renamed from: a */
            public void mo8631a(Response zVar, Response zVar2) {
                Cache.this.mo8858a(zVar, zVar2);
            }

            /* renamed from: a */
            public void mo8629a() {
                Cache.this.mo8856a();
            }

            /* renamed from: a */
            public void mo8630a(CacheStrategy cVar) {
                Cache.this.mo8857a(cVar);
            }
        };
        this.f807b = DiskLruCache.m549a(aVar, file, 201105, 2, j);
    }

    /* renamed from: a */
    public static String m1029a(HttpUrl rVar) {
        return ByteString.encodeUtf8(rVar.toString()).md5().hex();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Response mo8855a(Request xVar) {
        try {
            DiskLruCache.C0474c a = this.f807b.mo8599a(m1029a(xVar.mo9020a()));
            if (a == null) {
                return null;
            }
            try {
                C0525c cVar = new C0525c(a.mo8622a(0));
                Response a2 = cVar.mo8862a(a);
                if (cVar.mo8864a(xVar, a2)) {
                    return a2;
                }
                Util.m652a((Closeable) a2.mo9051h());
                return null;
            } catch (IOException e) {
                Util.m652a((Closeable) a);
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public CacheRequest mo8854a(Response zVar) {
        DiskLruCache.C0471a aVar;
        String b = zVar.mo9041a().mo9022b();
        if (C0478f.m692a(zVar.mo9041a().mo9022b())) {
            try {
                mo8859b(zVar.mo9041a());
                return null;
            } catch (IOException e) {
                return null;
            }
        } else if (!b.equals("GET") || HttpHeaders.m687b(zVar)) {
            return null;
        } else {
            C0525c cVar = new C0525c(zVar);
            try {
                DiskLruCache.C0471a b2 = this.f807b.mo8603b(m1029a(zVar.mo9041a().mo9020a()));
                if (b2 == null) {
                    return null;
                }
                try {
                    cVar.mo8863a(b2);
                    return new C0521a(b2);
                } catch (IOException e2) {
                    aVar = b2;
                    m1030a(aVar);
                    return null;
                }
            } catch (IOException e3) {
                aVar = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo8859b(Request xVar) {
        this.f807b.mo8606c(m1029a(xVar.mo9020a()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8858a(Response zVar, Response zVar2) {
        C0525c cVar = new C0525c(zVar2);
        try {
            DiskLruCache.C0471a a = ((C0523b) zVar.mo9051h()).f822a.mo8621a();
            if (a != null) {
                cVar.mo8863a(a);
                a.mo8616b();
            }
        } catch (IOException e) {
            m1030a((DiskLruCache.C0471a) null);
        }
    }

    /* renamed from: a */
    private void m1030a(DiskLruCache.C0471a aVar) {
        if (aVar != null) {
            try {
                aVar.mo8617c();
            } catch (IOException e) {
            }
        }
    }

    public void flush() {
        this.f807b.flush();
    }

    public void close() {
        this.f807b.close();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo8857a(CacheStrategy cVar) {
        this.f812g++;
        if (cVar.f425a != null) {
            this.f810e++;
        } else if (cVar.f426b != null) {
            this.f811f++;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo8856a() {
        this.f811f++;
    }

    /* renamed from: a */
    static int m1028a(BufferedSource eVar) {
        try {
            long m = eVar.mo17684m();
            String q = eVar.mo17688q();
            if (m >= 0 && m <= 2147483647L && q.isEmpty()) {
                return (int) m;
            }
            throw new IOException("expected an int but was \"" + m + q + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }
}
