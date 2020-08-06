package p018c.p019a.p021b;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import p018c.Address;
import p018c.HttpUrl;
import p018c.Route;
import p018c.p019a.Util;

/* renamed from: c.a.b.f */
public final class RouteSelector {

    /* renamed from: a */
    private final Address f505a;

    /* renamed from: b */
    private final RouteDatabase f506b;

    /* renamed from: c */
    private Proxy f507c;

    /* renamed from: d */
    private InetSocketAddress f508d;

    /* renamed from: e */
    private List<Proxy> f509e = Collections.emptyList();

    /* renamed from: f */
    private int f510f;

    /* renamed from: g */
    private List<InetSocketAddress> f511g = Collections.emptyList();

    /* renamed from: h */
    private int f512h;

    /* renamed from: i */
    private final List<Route> f513i = new ArrayList();

    public RouteSelector(Address aVar, RouteDatabase dVar) {
        this.f505a = aVar;
        this.f506b = dVar;
        m614a(aVar.mo8568a(), aVar.mo8576h());
    }

    /* renamed from: a */
    public boolean mo8654a() {
        return m618e() || m616c() || m620g();
    }

    /* renamed from: b */
    public Route mo8655b() {
        if (!m618e()) {
            if (m616c()) {
                this.f507c = m617d();
            } else if (m620g()) {
                return m621h();
            } else {
                throw new NoSuchElementException();
            }
        }
        this.f508d = m619f();
        Route abVar = new Route(this.f505a, this.f507c, this.f508d);
        if (!this.f506b.mo8650c(abVar)) {
            return abVar;
        }
        this.f513i.add(abVar);
        return mo8655b();
    }

    /* renamed from: a */
    public void mo8653a(Route abVar, IOException iOException) {
        if (!(abVar.mo8846b().type() == Proxy.Type.DIRECT || this.f505a.mo8575g() == null)) {
            this.f505a.mo8575g().connectFailed(this.f505a.mo8568a().mo8953a(), abVar.mo8846b().address(), iOException);
        }
        this.f506b.mo8648a(abVar);
    }

    /* renamed from: a */
    private void m614a(HttpUrl rVar, Proxy proxy) {
        List<Proxy> a;
        if (proxy != null) {
            this.f509e = Collections.singletonList(proxy);
        } else {
            List<Proxy> select = this.f505a.mo8575g().select(rVar.mo8953a());
            if (select == null || select.isEmpty()) {
                a = Util.m648a((T[]) new Proxy[]{Proxy.NO_PROXY});
            } else {
                a = Util.m647a(select);
            }
            this.f509e = a;
        }
        this.f510f = 0;
    }

    /* renamed from: c */
    private boolean m616c() {
        return this.f510f < this.f509e.size();
    }

    /* renamed from: d */
    private Proxy m617d() {
        if (!m616c()) {
            throw new SocketException("No route to " + this.f505a.mo8568a().mo8961f() + "; exhausted proxy configurations: " + this.f509e);
        }
        List<Proxy> list = this.f509e;
        int i = this.f510f;
        this.f510f = i + 1;
        Proxy proxy = list.get(i);
        m615a(proxy);
        return proxy;
    }

    /* renamed from: a */
    private void m615a(Proxy proxy) {
        int i;
        String str;
        this.f511g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            String f = this.f505a.mo8568a().mo8961f();
            i = this.f505a.mo8568a().mo8962g();
            str = f;
        } else {
            SocketAddress address = proxy.address();
            if (!(address instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address.getClass());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
            String a = m613a(inetSocketAddress);
            i = inetSocketAddress.getPort();
            str = a;
        }
        if (i < 1 || i > 65535) {
            throw new SocketException("No route to " + str + ":" + i + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.f511g.add(InetSocketAddress.createUnresolved(str, i));
        } else {
            List<InetAddress> a2 = this.f505a.mo8569b().mo8930a(str);
            int size = a2.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f511g.add(new InetSocketAddress(a2.get(i2), i));
            }
        }
        this.f512h = 0;
    }

    /* renamed from: a */
    static String m613a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }

    /* renamed from: e */
    private boolean m618e() {
        return this.f512h < this.f511g.size();
    }

    /* renamed from: f */
    private InetSocketAddress m619f() {
        if (!m618e()) {
            throw new SocketException("No route to " + this.f505a.mo8568a().mo8961f() + "; exhausted inet socket addresses: " + this.f511g);
        }
        List<InetSocketAddress> list = this.f511g;
        int i = this.f512h;
        this.f512h = i + 1;
        return list.get(i);
    }

    /* renamed from: g */
    private boolean m620g() {
        return !this.f513i.isEmpty();
    }

    /* renamed from: h */
    private Route m621h() {
        return this.f513i.remove(0);
    }
}
