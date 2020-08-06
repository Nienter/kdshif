package p018c.p019a.p021b;

import android.support.p004v7.widget.ActivityChooserView;
import android.support.p004v7.widget.helper.ItemTouchHelper;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;
import p018c.Address;
import p018c.CertificatePinner;
import p018c.Connection;
import p018c.ConnectionPool;
import p018c.ConnectionSpec;
import p018c.Handshake;
import p018c.HttpUrl;
import p018c.OkHttpClient;
import p018c.Protocol;
import p018c.Request;
import p018c.Response;
import p018c.Route;
import p018c.p019a.Util;
import p018c.p019a.Version;
import p018c.p019a.p022c.HttpCodec;
import p018c.p019a.p022c.HttpHeaders;
import p018c.p019a.p023d.Http1Codec;
import p018c.p019a.p024e.ErrorCode;
import p018c.p019a.p024e.Http2Codec;
import p018c.p019a.p024e.Http2Connection;
import p018c.p019a.p024e.Http2Stream;
import p018c.p019a.p026g.Platform;
import p018c.p019a.p027h.OkHostnameVerifier;
import p033d.BufferedSink;
import p033d.BufferedSource;
import p033d.Okio;
import p033d.Source;

/* renamed from: c.a.b.c */
public final class RealConnection extends Http2Connection.C0497b implements Connection {

    /* renamed from: a */
    public boolean f489a;

    /* renamed from: b */
    public int f490b;

    /* renamed from: c */
    public int f491c = 1;

    /* renamed from: d */
    public final List<Reference<StreamAllocation>> f492d = new ArrayList();

    /* renamed from: e */
    public long f493e = Long.MAX_VALUE;

    /* renamed from: g */
    private final ConnectionPool f494g;

    /* renamed from: h */
    private final Route f495h;

    /* renamed from: i */
    private Socket f496i;

    /* renamed from: j */
    private Socket f497j;

    /* renamed from: k */
    private Handshake f498k;

    /* renamed from: l */
    private Protocol f499l;

    /* renamed from: m */
    private Http2Connection f500m;

    /* renamed from: n */
    private BufferedSource f501n;

    /* renamed from: o */
    private BufferedSink f502o;

    public RealConnection(ConnectionPool jVar, Route abVar) {
        this.f494g = jVar;
        this.f495h = abVar;
    }

    /* renamed from: a */
    public void mo8639a(int i, int i2, int i3, boolean z) {
        if (this.f499l != null) {
            throw new IllegalStateException("already connected");
        }
        List<ConnectionSpec> f = this.f495h.mo8845a().mo8574f();
        ConnectionSpecSelector bVar = new ConnectionSpecSelector(f);
        if (this.f495h.mo8845a().mo8578i() == null) {
            if (!f.contains(ConnectionSpec.f994c)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            if (!Platform.m981b().mo8820b(this.f495h.mo8845a().mo8568a().mo8961f())) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + r0 + " not permitted by network security policy"));
            }
        }
        RouteException eVar = null;
        do {
            try {
                if (this.f495h.mo8848d()) {
                    m595a(i, i2, i3);
                } else {
                    m594a(i, i2);
                }
                m596a(bVar);
                if (this.f500m != null) {
                    synchronized (this.f494g) {
                        this.f491c = this.f500m.mo8707a();
                    }
                    return;
                }
                return;
            } catch (IOException e) {
                Util.m653a(this.f497j);
                Util.m653a(this.f496i);
                this.f497j = null;
                this.f496i = null;
                this.f501n = null;
                this.f502o = null;
                this.f498k = null;
                this.f499l = null;
                this.f500m = null;
                if (eVar == null) {
                    eVar = new RouteException(e);
                } else {
                    eVar.addConnectException(e);
                }
                if (!z) {
                    break;
                } else if (!bVar.mo8636a(e)) {
                }
                throw eVar;
            }
        } while (!bVar.mo8636a(e));
        throw eVar;
    }

    /* renamed from: a */
    private void m595a(int i, int i2, int i3) {
        Request e = m598e();
        HttpUrl a = e.mo9020a();
        int i4 = 0;
        while (true) {
            i4++;
            if (i4 > 21) {
                throw new ProtocolException("Too many tunnel connections attempted: " + 21);
            }
            m594a(i, i2);
            e = m593a(i2, i3, e, a);
            if (e != null) {
                Util.m653a(this.f496i);
                this.f496i = null;
                this.f502o = null;
                this.f501n = null;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private void m594a(int i, int i2) {
        Proxy b = this.f495h.mo8846b();
        this.f496i = (b.type() == Proxy.Type.DIRECT || b.type() == Proxy.Type.HTTP) ? this.f495h.mo8845a().mo8570c().createSocket() : new Socket(b);
        this.f496i.setSoTimeout(i2);
        try {
            Platform.m981b().mo8818a(this.f496i, this.f495h.mo8847c(), i);
            this.f501n = Okio.m3590a(Okio.m3600b(this.f496i));
            this.f502o = Okio.m3589a(Okio.m3594a(this.f496i));
        } catch (ConnectException e) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f495h.mo8847c());
            connectException.initCause(e);
            throw connectException;
        }
    }

    /* renamed from: a */
    private void m596a(ConnectionSpecSelector bVar) {
        if (this.f495h.mo8845a().mo8578i() == null) {
            this.f499l = Protocol.HTTP_1_1;
            this.f497j = this.f496i;
            return;
        }
        m597b(bVar);
        if (this.f499l == Protocol.HTTP_2) {
            this.f497j.setSoTimeout(0);
            this.f500m = new Http2Connection.C0496a(true).mo8732a(this.f497j, this.f495h.mo8845a().mo8568a().mo8961f(), this.f501n, this.f502o).mo8731a(this).mo8733a();
            this.f500m.mo8726c();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    private void m597b(ConnectionSpecSelector bVar) {
        SSLSocket sSLSocket;
        String str = null;
        Address a = this.f495h.mo8845a();
        try {
            SSLSocket sSLSocket2 = (SSLSocket) a.mo8578i().createSocket(this.f496i, a.mo8568a().mo8961f(), a.mo8568a().mo8962g(), true);
            try {
                ConnectionSpec a2 = bVar.mo8635a(sSLSocket2);
                if (a2.mo8907d()) {
                    Platform.m981b().mo8819a(sSLSocket2, a.mo8568a().mo8961f(), a.mo8572e());
                }
                sSLSocket2.startHandshake();
                Handshake a3 = Handshake.m1131a(sSLSocket2.getSession());
                if (!a.mo8579j().verify(a.mo8568a().mo8961f(), sSLSocket2.getSession())) {
                    X509Certificate x509Certificate = (X509Certificate) a3.mo8933c().get(0);
                    throw new SSLPeerUnverifiedException("Hostname " + a.mo8568a().mo8961f() + " not verified:\n    certificate: " + CertificatePinner.m1074a((Certificate) x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.m1005a(x509Certificate));
                }
                a.mo8580k().mo8886a(a.mo8568a().mo8961f(), a3.mo8933c());
                if (a2.mo8907d()) {
                    str = Platform.m981b().mo8815a(sSLSocket2);
                }
                this.f497j = sSLSocket2;
                this.f501n = Okio.m3590a(Okio.m3600b(this.f497j));
                this.f502o = Okio.m3589a(Okio.m3594a(this.f497j));
                this.f498k = a3;
                this.f499l = str != null ? Protocol.get(str) : Protocol.HTTP_1_1;
                if (sSLSocket2 != null) {
                    Platform.m981b().mo8826b(sSLSocket2);
                }
            } catch (AssertionError e) {
                AssertionError assertionError = e;
                str = sSLSocket2;
                e = assertionError;
            } catch (Throwable th) {
                Throwable th2 = th;
                sSLSocket = sSLSocket2;
                th = th2;
                if (sSLSocket != 0) {
                    Platform.m981b().mo8826b(sSLSocket);
                }
                Util.m653a((Socket) sSLSocket);
                throw th;
            }
        } catch (AssertionError e2) {
            e = e2;
            try {
                if (Util.m655a(e)) {
                    throw new IOException(e);
                }
                throw e;
            } catch (Throwable th3) {
                th = th3;
                sSLSocket = str;
            }
        }
    }

    /* renamed from: a */
    private Request m593a(int i, int i2, Request xVar, HttpUrl rVar) {
        Response a;
        String str = "CONNECT " + Util.m644a(rVar, true) + " HTTP/1.1";
        do {
            Http1Codec aVar = new Http1Codec(null, null, this.f501n, this.f502o);
            this.f501n.mo8593a().mo17745a((long) i, TimeUnit.MILLISECONDS);
            this.f502o.mo8695a().mo17745a((long) i2, TimeUnit.MILLISECONDS);
            aVar.mo8688a(xVar.mo9024c(), str);
            aVar.mo8673b();
            a = aVar.mo8669a(false).mo9065a(xVar).mo9069a();
            long a2 = HttpHeaders.m680a(a);
            if (a2 == -1) {
                a2 = 0;
            }
            Source b = aVar.mo8690b(a2);
            Util.m661b(b, (int) ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, TimeUnit.MILLISECONDS);
            b.close();
            switch (a.mo9045c()) {
                case ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION:
                    if (this.f501n.mo17653c().mo17664e() && this.f502o.mo17653c().mo17664e()) {
                        return null;
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                case 407:
                    xVar = this.f495h.mo8845a().mo8571d().mo8853a(this.f495h, a);
                    if (xVar != null) {
                        break;
                    } else {
                        throw new IOException("Failed to authenticate with proxy");
                    }
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + a.mo9045c());
            }
        } while (!"close".equalsIgnoreCase(a.mo9042a("Connection")));
        return xVar;
    }

    /* renamed from: e */
    private Request m598e() {
        return new Request.C0541a().mo9032a(this.f495h.mo8845a().mo8568a()).mo9035a("Host", Util.m644a(this.f495h.mo8845a().mo8568a(), true)).mo9035a("Proxy-Connection", "Keep-Alive").mo9035a(AbstractSpiCall.HEADER_USER_AGENT, Version.m717a()).mo9036a();
    }

    /* renamed from: a */
    public boolean mo8642a(Address aVar) {
        return this.f492d.size() < this.f491c && aVar.equals(mo8638a().mo8845a()) && !this.f489a;
    }

    /* renamed from: a */
    public HttpCodec mo8637a(OkHttpClient uVar, StreamAllocation gVar) {
        if (this.f500m != null) {
            return new Http2Codec(uVar, gVar, this.f500m);
        }
        this.f497j.setSoTimeout(uVar.mo8987b());
        this.f501n.mo8593a().mo17745a((long) uVar.mo8987b(), TimeUnit.MILLISECONDS);
        this.f502o.mo8695a().mo17745a((long) uVar.mo8988c(), TimeUnit.MILLISECONDS);
        return new Http1Codec(uVar, gVar, this.f501n, this.f502o);
    }

    /* renamed from: a */
    public Route mo8638a() {
        return this.f495h;
    }

    /* renamed from: b */
    public Socket mo8644b() {
        return this.f497j;
    }

    /* renamed from: a */
    public boolean mo8643a(boolean z) {
        int soTimeout;
        if (this.f497j.isClosed() || this.f497j.isInputShutdown() || this.f497j.isOutputShutdown()) {
            return false;
        }
        if (this.f500m != null) {
            if (this.f500m.mo8729d()) {
                return false;
            }
            return true;
        } else if (!z) {
            return true;
        } else {
            try {
                soTimeout = this.f497j.getSoTimeout();
                this.f497j.setSoTimeout(1);
                if (this.f501n.mo17664e()) {
                    this.f497j.setSoTimeout(soTimeout);
                    return false;
                }
                this.f497j.setSoTimeout(soTimeout);
                return true;
            } catch (SocketTimeoutException e) {
                return true;
            } catch (IOException e2) {
                return false;
            } catch (Throwable th) {
                this.f497j.setSoTimeout(soTimeout);
                throw th;
            }
        }
    }

    /* renamed from: a */
    public void mo8641a(Http2Stream iVar) {
        iVar.mo8749a(ErrorCode.REFUSED_STREAM);
    }

    /* renamed from: a */
    public void mo8640a(Http2Connection gVar) {
        synchronized (this.f494g) {
            this.f491c = gVar.mo8707a();
        }
    }

    /* renamed from: c */
    public Handshake mo8645c() {
        return this.f498k;
    }

    /* renamed from: d */
    public boolean mo8646d() {
        return this.f500m != null;
    }

    public String toString() {
        return "Connection{" + this.f495h.mo8845a().mo8568a().mo8961f() + ":" + this.f495h.mo8845a().mo8568a().mo8962g() + ", proxy=" + this.f495h.mo8846b() + " hostAddress=" + this.f495h.mo8847c() + " cipherSuite=" + (this.f498k != null ? this.f498k.mo8932b() : "none") + " protocol=" + this.f499l + '}';
    }
}
