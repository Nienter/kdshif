package p018c.p019a.p022c;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import p018c.Address;
import p018c.CertificatePinner;
import p018c.HttpUrl;
import p018c.Interceptor;
import p018c.OkHttpClient;
import p018c.Request;
import p018c.RequestBody;
import p018c.Response;
import p018c.ResponseBody;
import p018c.Route;
import p018c.p019a.Util;
import p018c.p019a.p021b.RealConnection;
import p018c.p019a.p021b.RouteException;
import p018c.p019a.p021b.StreamAllocation;
import p018c.p019a.p024e.ConnectionShutdownException;

/* renamed from: c.a.c.j */
public final class RetryAndFollowUpInterceptor implements Interceptor {

    /* renamed from: a */
    private final OkHttpClient f559a;

    /* renamed from: b */
    private final boolean f560b;

    /* renamed from: c */
    private StreamAllocation f561c;

    /* renamed from: d */
    private Object f562d;

    /* renamed from: e */
    private volatile boolean f563e;

    public RetryAndFollowUpInterceptor(OkHttpClient uVar, boolean z) {
        this.f559a = uVar;
        this.f560b = z;
    }

    /* renamed from: a */
    public boolean mo8684a() {
        return this.f563e;
    }

    /* renamed from: a */
    public void mo8683a(Object obj) {
        this.f562d = obj;
    }

    /* renamed from: a */
    public Response mo8591a(Interceptor.C0537a aVar) {
        boolean z;
        Request a = aVar.mo8676a();
        this.f561c = new StreamAllocation(this.f559a.mo9001p(), m708a(a.mo9020a()), this.f562d);
        Response zVar = null;
        int i = 0;
        Request xVar = a;
        while (!this.f563e) {
            try {
                Response a2 = ((RealInterceptorChain) aVar).mo8678a(xVar, this.f561c, null, null);
                if (zVar != null) {
                    a2 = a2.mo9052i().mo9072c(zVar.mo9052i().mo9061a((ResponseBody) null).mo9069a()).mo9069a();
                }
                xVar = m709a(a2);
                if (xVar == null) {
                    if (!this.f560b) {
                        this.f561c.mo8663c();
                    }
                    return a2;
                }
                Util.m652a((Closeable) a2.mo9051h());
                int i2 = i + 1;
                if (i2 > 20) {
                    this.f561c.mo8663c();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                } else if (xVar.mo9025d() instanceof UnrepeatableRequestBody) {
                    this.f561c.mo8663c();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", a2.mo9045c());
                } else {
                    if (!m710a(a2, xVar.mo9020a())) {
                        this.f561c.mo8663c();
                        this.f561c = new StreamAllocation(this.f559a.mo9001p(), m708a(xVar.mo9020a()), this.f562d);
                    } else if (this.f561c.mo8656a() != null) {
                        throw new IllegalStateException("Closing the body of " + a2 + " didn't close its backing stream. Bad interceptor?");
                    }
                    i = i2;
                    zVar = a2;
                }
            } catch (RouteException e) {
                if (!m712a(e.getLastConnectException(), false, xVar)) {
                    throw e.getLastConnectException();
                }
            } catch (IOException e2) {
                if (!(e2 instanceof ConnectionShutdownException)) {
                    z = true;
                } else {
                    z = false;
                }
                if (!m712a(e2, z, xVar)) {
                    throw e2;
                }
            } catch (Throwable th) {
                this.f561c.mo8659a((IOException) null);
                this.f561c.mo8663c();
                throw th;
            }
        }
        this.f561c.mo8663c();
        throw new IOException("Canceled");
    }

    /* renamed from: a */
    private Address m708a(HttpUrl rVar) {
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        CertificatePinner gVar = null;
        if (rVar.mo8956c()) {
            sSLSocketFactory = this.f559a.mo8996k();
            hostnameVerifier = this.f559a.mo8997l();
            gVar = this.f559a.mo8998m();
        } else {
            hostnameVerifier = null;
            sSLSocketFactory = null;
        }
        return new Address(rVar.mo8961f(), rVar.mo8962g(), this.f559a.mo8994i(), this.f559a.mo8995j(), sSLSocketFactory, hostnameVerifier, gVar, this.f559a.mo9000o(), this.f559a.mo8989d(), this.f559a.mo9006u(), this.f559a.mo9007v(), this.f559a.mo8990e());
    }

    /* renamed from: a */
    private boolean m712a(IOException iOException, boolean z, Request xVar) {
        this.f561c.mo8659a(iOException);
        if (!this.f559a.mo9004s()) {
            return false;
        }
        if ((!z || !(xVar.mo9025d() instanceof UnrepeatableRequestBody)) && m711a(iOException, z) && this.f561c.mo8665e()) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m711a(IOException iOException, boolean z) {
        boolean z2 = true;
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z) {
                z2 = false;
            }
            return z2;
        } else if ((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: a */
    private Request m709a(Response zVar) {
        Proxy d;
        RequestBody yVar = null;
        if (zVar == null) {
            throw new IllegalStateException();
        }
        RealConnection b = this.f561c.mo8661b();
        Route a = b != null ? b.mo8638a() : null;
        int c = zVar.mo9045c();
        String b2 = zVar.mo9041a().mo9022b();
        switch (c) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case 307:
            case 308:
                if (!b2.equals("GET") && !b2.equals("HEAD")) {
                    return null;
                }
            case 401:
                return this.f559a.mo8999n().mo8853a(a, zVar);
            case 407:
                if (a != null) {
                    d = a.mo8846b();
                } else {
                    d = this.f559a.mo8989d();
                }
                if (d.type() == Proxy.Type.HTTP) {
                    return this.f559a.mo9000o().mo8853a(a, zVar);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case 408:
                if (!(zVar.mo9041a().mo9025d() instanceof UnrepeatableRequestBody)) {
                    return zVar.mo9041a();
                }
                return null;
            default:
                return null;
        }
        if (!this.f559a.mo9003r()) {
            return null;
        }
        String a2 = zVar.mo9042a("Location");
        if (a2 == null) {
            return null;
        }
        HttpUrl c2 = zVar.mo9041a().mo9020a().mo8955c(a2);
        if (c2 == null) {
            return null;
        }
        if (!c2.mo8954b().equals(zVar.mo9041a().mo9020a().mo8954b()) && !this.f559a.mo9002q()) {
            return null;
        }
        Request.C0541a e = zVar.mo9041a().mo9026e();
        if (C0478f.m694c(b2)) {
            boolean d2 = C0478f.m695d(b2);
            if (C0478f.m696e(b2)) {
                e.mo9034a("GET", (RequestBody) null);
            } else {
                if (d2) {
                    yVar = zVar.mo9041a().mo9025d();
                }
                e.mo9034a(b2, yVar);
            }
            if (!d2) {
                e.mo9037b("Transfer-Encoding");
                e.mo9037b("Content-Length");
                e.mo9037b("Content-Type");
            }
        }
        if (!m710a(zVar, c2)) {
            e.mo9037b("Authorization");
        }
        return e.mo9032a(c2).mo9036a();
    }

    /* renamed from: a */
    private boolean m710a(Response zVar, HttpUrl rVar) {
        HttpUrl a = zVar.mo9041a().mo9020a();
        return a.mo8961f().equals(rVar.mo8961f()) && a.mo8962g() == rVar.mo8962g() && a.mo8954b().equals(rVar.mo8954b());
    }
}
