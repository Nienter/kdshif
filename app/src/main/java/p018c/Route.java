package p018c;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* renamed from: c.ab */
public final class Route {

    /* renamed from: a */
    final Address f801a;

    /* renamed from: b */
    final Proxy f802b;

    /* renamed from: c */
    final InetSocketAddress f803c;

    public Route(Address aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (aVar == null) {
            throw new NullPointerException("address == null");
        } else if (proxy == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        } else {
            this.f801a = aVar;
            this.f802b = proxy;
            this.f803c = inetSocketAddress;
        }
    }

    /* renamed from: a */
    public Address mo8845a() {
        return this.f801a;
    }

    /* renamed from: b */
    public Proxy mo8846b() {
        return this.f802b;
    }

    /* renamed from: c */
    public InetSocketAddress mo8847c() {
        return this.f803c;
    }

    /* renamed from: d */
    public boolean mo8848d() {
        return this.f801a.f415i != null && this.f802b.type() == Proxy.Type.HTTP;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Route)) {
            return false;
        }
        Route abVar = (Route) obj;
        if (!this.f801a.equals(abVar.f801a) || !this.f802b.equals(abVar.f802b) || !this.f803c.equals(abVar.f803c)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.f801a.hashCode() + 527) * 31) + this.f802b.hashCode()) * 31) + this.f803c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f803c + "}";
    }
}
