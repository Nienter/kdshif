package p018c;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import p018c.HttpUrl;
import p018c.p019a.Util;

/* renamed from: c.a */
public final class Address {

    /* renamed from: a */
    final HttpUrl f407a;

    /* renamed from: b */
    final Dns f408b;

    /* renamed from: c */
    final SocketFactory f409c;

    /* renamed from: d */
    final Authenticator f410d;

    /* renamed from: e */
    final List<Protocol> f411e;

    /* renamed from: f */
    final List<ConnectionSpec> f412f;

    /* renamed from: g */
    final ProxySelector f413g;

    /* renamed from: h */
    final Proxy f414h;

    /* renamed from: i */
    final SSLSocketFactory f415i;

    /* renamed from: j */
    final HostnameVerifier f416j;

    /* renamed from: k */
    final CertificatePinner f417k;

    public Address(String str, int i, Dns oVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, CertificatePinner gVar, Authenticator bVar, Proxy proxy, List<Protocol> list, List<ConnectionSpec> list2, ProxySelector proxySelector) {
        this.f407a = new HttpUrl.C0535a().mo8975a(sSLSocketFactory != null ? "https" : "http").mo8980d(str).mo8974a(i).mo8979c();
        if (oVar == null) {
            throw new NullPointerException("dns == null");
        }
        this.f408b = oVar;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.f409c = socketFactory;
        if (bVar == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.f410d = bVar;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.f411e = Util.m647a(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f412f = Util.m647a(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.f413g = proxySelector;
        this.f414h = proxy;
        this.f415i = sSLSocketFactory;
        this.f416j = hostnameVerifier;
        this.f417k = gVar;
    }

    /* renamed from: a */
    public HttpUrl mo8568a() {
        return this.f407a;
    }

    /* renamed from: b */
    public Dns mo8569b() {
        return this.f408b;
    }

    /* renamed from: c */
    public SocketFactory mo8570c() {
        return this.f409c;
    }

    /* renamed from: d */
    public Authenticator mo8571d() {
        return this.f410d;
    }

    /* renamed from: e */
    public List<Protocol> mo8572e() {
        return this.f411e;
    }

    /* renamed from: f */
    public List<ConnectionSpec> mo8574f() {
        return this.f412f;
    }

    /* renamed from: g */
    public ProxySelector mo8575g() {
        return this.f413g;
    }

    /* renamed from: h */
    public Proxy mo8576h() {
        return this.f414h;
    }

    /* renamed from: i */
    public SSLSocketFactory mo8578i() {
        return this.f415i;
    }

    /* renamed from: j */
    public HostnameVerifier mo8579j() {
        return this.f416j;
    }

    /* renamed from: k */
    public CertificatePinner mo8580k() {
        return this.f417k;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Address)) {
            return false;
        }
        Address aVar = (Address) obj;
        if (!this.f407a.equals(aVar.f407a) || !this.f408b.equals(aVar.f408b) || !this.f410d.equals(aVar.f410d) || !this.f411e.equals(aVar.f411e) || !this.f412f.equals(aVar.f412f) || !this.f413g.equals(aVar.f413g) || !Util.m656a((Object) this.f414h, (Object) aVar.f414h) || !Util.m656a((Object) this.f415i, (Object) aVar.f415i) || !Util.m656a((Object) this.f416j, (Object) aVar.f416j) || !Util.m656a((Object) this.f417k, (Object) aVar.f417k)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int hashCode = (((((((((((this.f407a.hashCode() + 527) * 31) + this.f408b.hashCode()) * 31) + this.f410d.hashCode()) * 31) + this.f411e.hashCode()) * 31) + this.f412f.hashCode()) * 31) + this.f413g.hashCode()) * 31;
        if (this.f414h != null) {
            i = this.f414h.hashCode();
        } else {
            i = 0;
        }
        int i5 = (i + hashCode) * 31;
        if (this.f415i != null) {
            i2 = this.f415i.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i2 + i5) * 31;
        if (this.f416j != null) {
            i3 = this.f416j.hashCode();
        } else {
            i3 = 0;
        }
        int i7 = (i3 + i6) * 31;
        if (this.f417k != null) {
            i4 = this.f417k.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("Address{").append(this.f407a.mo8961f()).append(":").append(this.f407a.mo8962g());
        if (this.f414h != null) {
            append.append(", proxy=").append(this.f414h);
        } else {
            append.append(", proxySelector=").append(this.f413g);
        }
        append.append("}");
        return append.toString();
    }
}
