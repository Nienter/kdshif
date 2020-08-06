package p018c;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;
import p018c.Call;
import p018c.Headers;
import p018c.Response;
import p018c.p019a.Internal;
import p018c.p019a.Util;
import p018c.p019a.p020a.InternalCache;
import p018c.p019a.p021b.RealConnection;
import p018c.p019a.p021b.RouteDatabase;
import p018c.p019a.p021b.StreamAllocation;
import p018c.p019a.p027h.C0515b;
import p018c.p019a.p027h.OkHostnameVerifier;

/* renamed from: c.u */
public class OkHttpClient implements Call.C0527a, Cloneable {

    /* renamed from: a */
    static final List<Protocol> f1054a = Util.m648a((T[]) new Protocol[]{Protocol.HTTP_2, Protocol.HTTP_1_1});

    /* renamed from: b */
    static final List<ConnectionSpec> f1055b = Util.m648a((T[]) new ConnectionSpec[]{ConnectionSpec.f992a, ConnectionSpec.f993b, ConnectionSpec.f994c});

    /* renamed from: A */
    final int f1056A;

    /* renamed from: B */
    final int f1057B;

    /* renamed from: c */
    final Dispatcher f1058c;

    /* renamed from: d */
    final Proxy f1059d;

    /* renamed from: e */
    final List<Protocol> f1060e;

    /* renamed from: f */
    final List<ConnectionSpec> f1061f;

    /* renamed from: g */
    final List<Interceptor> f1062g;

    /* renamed from: h */
    final List<Interceptor> f1063h;

    /* renamed from: i */
    final ProxySelector f1064i;

    /* renamed from: j */
    final CookieJar f1065j;

    /* renamed from: k */
    final Cache f1066k;

    /* renamed from: l */
    final InternalCache f1067l;

    /* renamed from: m */
    final SocketFactory f1068m;

    /* renamed from: n */
    final SSLSocketFactory f1069n;

    /* renamed from: o */
    final C0515b f1070o;

    /* renamed from: p */
    final HostnameVerifier f1071p;

    /* renamed from: q */
    final CertificatePinner f1072q;

    /* renamed from: r */
    final Authenticator f1073r;

    /* renamed from: s */
    final Authenticator f1074s;

    /* renamed from: t */
    final ConnectionPool f1075t;

    /* renamed from: u */
    final Dns f1076u;

    /* renamed from: v */
    final boolean f1077v;

    /* renamed from: w */
    final boolean f1078w;

    /* renamed from: x */
    final boolean f1079x;

    /* renamed from: y */
    final int f1080y;

    /* renamed from: z */
    final int f1081z;

    /* renamed from: c.u$a */
    /* compiled from: OkHttpClient */
    public static final class C0539a {

        /* renamed from: a */
        Dispatcher f1082a = new Dispatcher();

        /* renamed from: b */
        Proxy f1083b;

        /* renamed from: c */
        List<Protocol> f1084c = OkHttpClient.f1054a;

        /* renamed from: d */
        List<ConnectionSpec> f1085d = OkHttpClient.f1055b;

        /* renamed from: e */
        final List<Interceptor> f1086e = new ArrayList();

        /* renamed from: f */
        final List<Interceptor> f1087f = new ArrayList();

        /* renamed from: g */
        ProxySelector f1088g = ProxySelector.getDefault();

        /* renamed from: h */
        CookieJar f1089h = CookieJar.f1017a;

        /* renamed from: i */
        Cache f1090i;

        /* renamed from: j */
        InternalCache f1091j;

        /* renamed from: k */
        SocketFactory f1092k = SocketFactory.getDefault();

        /* renamed from: l */
        SSLSocketFactory f1093l;

        /* renamed from: m */
        C0515b f1094m;

        /* renamed from: n */
        HostnameVerifier f1095n = OkHostnameVerifier.f794a;

        /* renamed from: o */
        CertificatePinner f1096o = CertificatePinner.f861a;

        /* renamed from: p */
        Authenticator f1097p = Authenticator.f805a;

        /* renamed from: q */
        Authenticator f1098q = Authenticator.f805a;

        /* renamed from: r */
        ConnectionPool f1099r = new ConnectionPool();

        /* renamed from: s */
        Dns f1100s = Dns.f1025a;

        /* renamed from: t */
        boolean f1101t = true;

        /* renamed from: u */
        boolean f1102u = true;

        /* renamed from: v */
        boolean f1103v = true;

        /* renamed from: w */
        int f1104w = AbstractSpiCall.DEFAULT_TIMEOUT;

        /* renamed from: x */
        int f1105x = AbstractSpiCall.DEFAULT_TIMEOUT;

        /* renamed from: y */
        int f1106y = AbstractSpiCall.DEFAULT_TIMEOUT;

        /* renamed from: z */
        int f1107z = 0;

        /* renamed from: a */
        public C0539a mo9010a(Cache cVar) {
            this.f1090i = cVar;
            this.f1091j = null;
            return this;
        }

        /* renamed from: a */
        public OkHttpClient mo9011a() {
            return new OkHttpClient(this);
        }
    }

    static {
        Internal.f418a = new Internal() {
            /* renamed from: a */
            public void mo8586a(Headers.C0534a aVar, String str) {
                aVar.mo8946a(str);
            }

            /* renamed from: a */
            public void mo8587a(Headers.C0534a aVar, String str, String str2) {
                aVar.mo8950b(str, str2);
            }

            /* renamed from: a */
            public boolean mo8588a(ConnectionPool jVar, RealConnection cVar) {
                return jVar.mo8900b(cVar);
            }

            /* renamed from: a */
            public RealConnection mo8583a(ConnectionPool jVar, Address aVar, StreamAllocation gVar) {
                return jVar.mo8897a(aVar, gVar);
            }

            /* renamed from: b */
            public Socket mo8589b(ConnectionPool jVar, Address aVar, StreamAllocation gVar) {
                return jVar.mo8899b(aVar, gVar);
            }

            /* renamed from: b */
            public void mo8590b(ConnectionPool jVar, RealConnection cVar) {
                jVar.mo8898a(cVar);
            }

            /* renamed from: a */
            public RouteDatabase mo8584a(ConnectionPool jVar) {
                return jVar.f985a;
            }

            /* renamed from: a */
            public int mo8582a(Response.C0543a aVar) {
                return aVar.f1146c;
            }

            /* renamed from: a */
            public void mo8585a(ConnectionSpec kVar, SSLSocket sSLSocket, boolean z) {
                kVar.mo8902a(sSLSocket, z);
            }
        };
    }

    public OkHttpClient() {
        this(new C0539a());
    }

    OkHttpClient(C0539a aVar) {
        boolean z;
        this.f1058c = aVar.f1082a;
        this.f1059d = aVar.f1083b;
        this.f1060e = aVar.f1084c;
        this.f1061f = aVar.f1085d;
        this.f1062g = Util.m647a(aVar.f1086e);
        this.f1063h = Util.m647a(aVar.f1087f);
        this.f1064i = aVar.f1088g;
        this.f1065j = aVar.f1089h;
        this.f1066k = aVar.f1090i;
        this.f1067l = aVar.f1091j;
        this.f1068m = aVar.f1092k;
        boolean z2 = false;
        for (ConnectionSpec next : this.f1061f) {
            if (z2 || next.mo8903a()) {
                z = true;
            } else {
                z = false;
            }
            z2 = z;
        }
        if (aVar.f1093l != null || !z2) {
            this.f1069n = aVar.f1093l;
            this.f1070o = aVar.f1094m;
        } else {
            X509TrustManager y = m1208y();
            this.f1069n = m1207a(y);
            this.f1070o = C0515b.m995a(y);
        }
        this.f1071p = aVar.f1095n;
        this.f1072q = aVar.f1096o.mo8884a(this.f1070o);
        this.f1073r = aVar.f1097p;
        this.f1074s = aVar.f1098q;
        this.f1075t = aVar.f1099r;
        this.f1076u = aVar.f1100s;
        this.f1077v = aVar.f1101t;
        this.f1078w = aVar.f1102u;
        this.f1079x = aVar.f1103v;
        this.f1080y = aVar.f1104w;
        this.f1081z = aVar.f1105x;
        this.f1056A = aVar.f1106y;
        this.f1057B = aVar.f1107z;
    }

    /* renamed from: y */
    private X509TrustManager m1208y() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init(null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    private SSLSocketFactory m1207a(X509TrustManager x509TrustManager) {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{x509TrustManager}, null);
            return instance.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public int mo8986a() {
        return this.f1080y;
    }

    /* renamed from: b */
    public int mo8987b() {
        return this.f1081z;
    }

    /* renamed from: c */
    public int mo8988c() {
        return this.f1056A;
    }

    /* renamed from: d */
    public Proxy mo8989d() {
        return this.f1059d;
    }

    /* renamed from: e */
    public ProxySelector mo8990e() {
        return this.f1064i;
    }

    /* renamed from: f */
    public CookieJar mo8991f() {
        return this.f1065j;
    }

    /* renamed from: g */
    public Cache mo8992g() {
        return this.f1066k;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public InternalCache mo8993h() {
        return this.f1066k != null ? this.f1066k.f806a : this.f1067l;
    }

    /* renamed from: i */
    public Dns mo8994i() {
        return this.f1076u;
    }

    /* renamed from: j */
    public SocketFactory mo8995j() {
        return this.f1068m;
    }

    /* renamed from: k */
    public SSLSocketFactory mo8996k() {
        return this.f1069n;
    }

    /* renamed from: l */
    public HostnameVerifier mo8997l() {
        return this.f1071p;
    }

    /* renamed from: m */
    public CertificatePinner mo8998m() {
        return this.f1072q;
    }

    /* renamed from: n */
    public Authenticator mo8999n() {
        return this.f1074s;
    }

    /* renamed from: o */
    public Authenticator mo9000o() {
        return this.f1073r;
    }

    /* renamed from: p */
    public ConnectionPool mo9001p() {
        return this.f1075t;
    }

    /* renamed from: q */
    public boolean mo9002q() {
        return this.f1077v;
    }

    /* renamed from: r */
    public boolean mo9003r() {
        return this.f1078w;
    }

    /* renamed from: s */
    public boolean mo9004s() {
        return this.f1079x;
    }

    /* renamed from: t */
    public Dispatcher mo9005t() {
        return this.f1058c;
    }

    /* renamed from: u */
    public List<Protocol> mo9006u() {
        return this.f1060e;
    }

    /* renamed from: v */
    public List<ConnectionSpec> mo9007v() {
        return this.f1061f;
    }

    /* renamed from: w */
    public List<Interceptor> mo9008w() {
        return this.f1062g;
    }

    /* renamed from: x */
    public List<Interceptor> mo9009x() {
        return this.f1063h;
    }

    /* renamed from: a */
    public Call mo8881a(Request xVar) {
        return new RealCall(this, xVar, false);
    }
}
