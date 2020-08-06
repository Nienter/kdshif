package p018c.p019a.p023d;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import p018c.Headers;
import p018c.HttpUrl;
import p018c.OkHttpClient;
import p018c.Request;
import p018c.Response;
import p018c.ResponseBody;
import p018c.p019a.Internal;
import p018c.p019a.Util;
import p018c.p019a.p021b.StreamAllocation;
import p018c.p019a.p022c.HttpCodec;
import p018c.p019a.p022c.HttpHeaders;
import p018c.p019a.p022c.RealResponseBody;
import p018c.p019a.p022c.RequestLine;
import p018c.p019a.p022c.StatusLine;
import p033d.Buffer;
import p033d.BufferedSink;
import p033d.BufferedSource;
import p033d.ForwardingTimeout;
import p033d.Okio;
import p033d.Sink;
import p033d.Source;
import p033d.Timeout;

/* renamed from: c.a.d.a */
public final class Http1Codec implements HttpCodec {

    /* renamed from: a */
    final OkHttpClient f567a;

    /* renamed from: b */
    final StreamAllocation f568b;

    /* renamed from: c */
    final BufferedSource f569c;

    /* renamed from: d */
    final BufferedSink f570d;

    /* renamed from: e */
    int f571e = 0;

    /* renamed from: c.a.d.a$a */
    /* compiled from: Http1Codec */
    private abstract class C0480a implements Source {

        /* renamed from: a */
        protected final ForwardingTimeout f572a;

        /* renamed from: b */
        protected boolean f573b;

        private C0480a() {
            this.f572a = new ForwardingTimeout(Http1Codec.this.f569c.mo8593a());
        }

        /* renamed from: a */
        public Timeout mo8593a() {
            return this.f572a;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public final void mo8694a(boolean z) {
            if (Http1Codec.this.f571e != 6) {
                if (Http1Codec.this.f571e != 5) {
                    throw new IllegalStateException("state: " + Http1Codec.this.f571e);
                }
                Http1Codec.this.mo8689a(this.f572a);
                Http1Codec.this.f571e = 6;
                if (Http1Codec.this.f568b != null) {
                    Http1Codec.this.f568b.mo8660a(!z, (HttpCodec) Http1Codec.this);
                }
            }
        }
    }

    /* renamed from: c.a.d.a$b */
    /* compiled from: Http1Codec */
    private final class C0481b implements Sink {

        /* renamed from: b */
        private final ForwardingTimeout f576b = new ForwardingTimeout(Http1Codec.this.f570d.mo8695a());

        /* renamed from: c */
        private boolean f577c;

        C0481b() {
        }

        /* renamed from: a */
        public Timeout mo8695a() {
            return this.f576b;
        }

        /* renamed from: a_ */
        public void mo8624a_(Buffer cVar, long j) {
            if (this.f577c) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                Http1Codec.this.f570d.mo17680j(j);
                Http1Codec.this.f570d.mo17652b("\r\n");
                Http1Codec.this.f570d.mo8624a_(cVar, j);
                Http1Codec.this.f570d.mo17652b("\r\n");
            }
        }

        public synchronized void flush() {
            if (!this.f577c) {
                Http1Codec.this.f570d.flush();
            }
        }

        public synchronized void close() {
            if (!this.f577c) {
                this.f577c = true;
                Http1Codec.this.f570d.mo17652b("0\r\n\r\n");
                Http1Codec.this.mo8689a(this.f576b);
                Http1Codec.this.f571e = 3;
            }
        }
    }

    /* renamed from: c.a.d.a$c */
    /* compiled from: Http1Codec */
    private class C0482c extends C0480a {

        /* renamed from: e */
        private final HttpUrl f579e;

        /* renamed from: f */
        private long f580f = -1;

        /* renamed from: g */
        private boolean f581g = true;

        C0482c(HttpUrl rVar) {
            super();
            this.f579e = rVar;
        }

        /* renamed from: a */
        public long mo8592a(Buffer cVar, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.f573b) {
                throw new IllegalStateException("closed");
            } else if (!this.f581g) {
                return -1;
            } else {
                if (this.f580f == 0 || this.f580f == -1) {
                    m737b();
                    if (!this.f581g) {
                        return -1;
                    }
                }
                long a = Http1Codec.this.f569c.mo8592a(cVar, Math.min(j, this.f580f));
                if (a == -1) {
                    mo8694a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.f580f -= a;
                return a;
            }
        }

        /* renamed from: b */
        private void m737b() {
            if (this.f580f != -1) {
                Http1Codec.this.f569c.mo17688q();
            }
            try {
                this.f580f = Http1Codec.this.f569c.mo17685n();
                String trim = Http1Codec.this.f569c.mo17688q().trim();
                if (this.f580f < 0 || (!trim.isEmpty() && !trim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f580f + trim + "\"");
                } else if (this.f580f == 0) {
                    this.f581g = false;
                    HttpHeaders.m683a(Http1Codec.this.f567a.mo8991f(), this.f579e, Http1Codec.this.mo8691c());
                    mo8694a(true);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() {
            if (!this.f573b) {
                if (this.f581g && !Util.m654a((Source) this, 100, TimeUnit.MILLISECONDS)) {
                    mo8694a(false);
                }
                this.f573b = true;
            }
        }
    }

    /* renamed from: c.a.d.a$d */
    /* compiled from: Http1Codec */
    private final class C0483d implements Sink {

        /* renamed from: b */
        private final ForwardingTimeout f583b = new ForwardingTimeout(Http1Codec.this.f570d.mo8695a());

        /* renamed from: c */
        private boolean f584c;

        /* renamed from: d */
        private long f585d;

        C0483d(long j) {
            this.f585d = j;
        }

        /* renamed from: a */
        public Timeout mo8695a() {
            return this.f583b;
        }

        /* renamed from: a_ */
        public void mo8624a_(Buffer cVar, long j) {
            if (this.f584c) {
                throw new IllegalStateException("closed");
            }
            Util.m651a(cVar.mo17648b(), 0, j);
            if (j > this.f585d) {
                throw new ProtocolException("expected " + this.f585d + " bytes but received " + j);
            }
            Http1Codec.this.f570d.mo8624a_(cVar, j);
            this.f585d -= j;
        }

        public void flush() {
            if (!this.f584c) {
                Http1Codec.this.f570d.flush();
            }
        }

        public void close() {
            if (!this.f584c) {
                this.f584c = true;
                if (this.f585d > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                Http1Codec.this.mo8689a(this.f583b);
                Http1Codec.this.f571e = 3;
            }
        }
    }

    /* renamed from: c.a.d.a$e */
    /* compiled from: Http1Codec */
    private class C0484e extends C0480a {

        /* renamed from: e */
        private long f587e;

        public C0484e(long j) {
            super();
            this.f587e = j;
            if (this.f587e == 0) {
                mo8694a(true);
            }
        }

        /* renamed from: a */
        public long mo8592a(Buffer cVar, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.f573b) {
                throw new IllegalStateException("closed");
            } else if (this.f587e == 0) {
                return -1;
            } else {
                long a = Http1Codec.this.f569c.mo8592a(cVar, Math.min(this.f587e, j));
                if (a == -1) {
                    mo8694a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.f587e -= a;
                if (this.f587e == 0) {
                    mo8694a(true);
                }
                return a;
            }
        }

        public void close() {
            if (!this.f573b) {
                if (this.f587e != 0 && !Util.m654a((Source) this, 100, TimeUnit.MILLISECONDS)) {
                    mo8694a(false);
                }
                this.f573b = true;
            }
        }
    }

    /* renamed from: c.a.d.a$f */
    /* compiled from: Http1Codec */
    private class C0485f extends C0480a {

        /* renamed from: e */
        private boolean f589e;

        C0485f() {
            super();
        }

        /* renamed from: a */
        public long mo8592a(Buffer cVar, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.f573b) {
                throw new IllegalStateException("closed");
            } else if (this.f589e) {
                return -1;
            } else {
                long a = Http1Codec.this.f569c.mo8592a(cVar, j);
                if (a != -1) {
                    return a;
                }
                this.f589e = true;
                mo8694a(true);
                return -1;
            }
        }

        public void close() {
            if (!this.f573b) {
                if (!this.f589e) {
                    mo8694a(false);
                }
                this.f573b = true;
            }
        }
    }

    public Http1Codec(OkHttpClient uVar, StreamAllocation gVar, BufferedSource eVar, BufferedSink dVar) {
        this.f567a = uVar;
        this.f568b = gVar;
        this.f569c = eVar;
        this.f570d = dVar;
    }

    /* renamed from: a */
    public Sink mo8670a(Request xVar, long j) {
        if ("chunked".equalsIgnoreCase(xVar.mo9021a("Transfer-Encoding"))) {
            return mo8692d();
        }
        if (j != -1) {
            return mo8686a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    /* renamed from: a */
    public void mo8672a(Request xVar) {
        mo8688a(xVar.mo9024c(), RequestLine.m706a(xVar, this.f568b.mo8661b().mo8638a().mo8846b().type()));
    }

    /* renamed from: a */
    public ResponseBody mo8668a(Response zVar) {
        return new RealResponseBody(zVar.mo9050g(), Okio.m3590a(m718b(zVar)));
    }

    /* renamed from: b */
    private Source m718b(Response zVar) {
        if (!HttpHeaders.m690d(zVar)) {
            return mo8690b(0);
        }
        if ("chunked".equalsIgnoreCase(zVar.mo9042a("Transfer-Encoding"))) {
            return mo8687a(zVar.mo9041a().mo9020a());
        }
        long a = HttpHeaders.m680a(zVar);
        if (a != -1) {
            return mo8690b(a);
        }
        return mo8693e();
    }

    /* renamed from: a */
    public void mo8671a() {
        this.f570d.flush();
    }

    /* renamed from: b */
    public void mo8673b() {
        this.f570d.flush();
    }

    /* renamed from: a */
    public void mo8688a(Headers qVar, String str) {
        if (this.f571e != 0) {
            throw new IllegalStateException("state: " + this.f571e);
        }
        this.f570d.mo17652b(str).mo17652b("\r\n");
        int a = qVar.mo8937a();
        for (int i = 0; i < a; i++) {
            this.f570d.mo17652b(qVar.mo8938a(i)).mo17652b(": ").mo17652b(qVar.mo8941b(i)).mo17652b("\r\n");
        }
        this.f570d.mo17652b("\r\n");
        this.f571e = 1;
    }

    /* renamed from: a */
    public Response.C0543a mo8669a(boolean z) {
        if (this.f571e == 1 || this.f571e == 3) {
            try {
                StatusLine a = StatusLine.m716a(this.f569c.mo17688q());
                Response.C0543a a2 = new Response.C0543a().mo9064a(a.f564a).mo9059a(a.f565b).mo9067a(a.f566c).mo9063a(mo8691c());
                if (z && a.f565b == 100) {
                    return null;
                }
                this.f571e = 4;
                return a2;
            } catch (EOFException e) {
                IOException iOException = new IOException("unexpected end of stream on " + this.f568b);
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new IllegalStateException("state: " + this.f571e);
        }
    }

    /* renamed from: c */
    public Headers mo8691c() {
        Headers.C0534a aVar = new Headers.C0534a();
        while (true) {
            String q = this.f569c.mo17688q();
            if (q.length() == 0) {
                return aVar.mo8948a();
            }
            Internal.f418a.mo8586a(aVar, q);
        }
    }

    /* renamed from: d */
    public Sink mo8692d() {
        if (this.f571e != 1) {
            throw new IllegalStateException("state: " + this.f571e);
        }
        this.f571e = 2;
        return new C0481b();
    }

    /* renamed from: a */
    public Sink mo8686a(long j) {
        if (this.f571e != 1) {
            throw new IllegalStateException("state: " + this.f571e);
        }
        this.f571e = 2;
        return new C0483d(j);
    }

    /* renamed from: b */
    public Source mo8690b(long j) {
        if (this.f571e != 4) {
            throw new IllegalStateException("state: " + this.f571e);
        }
        this.f571e = 5;
        return new C0484e(j);
    }

    /* renamed from: a */
    public Source mo8687a(HttpUrl rVar) {
        if (this.f571e != 4) {
            throw new IllegalStateException("state: " + this.f571e);
        }
        this.f571e = 5;
        return new C0482c(rVar);
    }

    /* renamed from: e */
    public Source mo8693e() {
        if (this.f571e != 4) {
            throw new IllegalStateException("state: " + this.f571e);
        } else if (this.f568b == null) {
            throw new IllegalStateException("streamAllocation == null");
        } else {
            this.f571e = 5;
            this.f568b.mo8664d();
            return new C0485f();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8689a(ForwardingTimeout iVar) {
        Timeout a = iVar.mo17743a();
        iVar.mo17742a(Timeout.f2693b);
        a.mo17750e_();
        a.mo17749d_();
    }
}
