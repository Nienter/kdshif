package p018c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLSocket;
import p018c.p019a.Util;

/* renamed from: c.k */
public final class ConnectionSpec {

    /* renamed from: a */
    public static final ConnectionSpec f992a = new C0531a(true).mo8913a(f995h).mo8912a(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).mo8911a(true).mo8915a();

    /* renamed from: b */
    public static final ConnectionSpec f993b = new C0531a(f992a).mo8912a(TlsVersion.TLS_1_0).mo8911a(true).mo8915a();

    /* renamed from: c */
    public static final ConnectionSpec f994c = new C0531a(false).mo8915a();

    /* renamed from: h */
    private static final CipherSuite[] f995h = {CipherSuite.f918aW, CipherSuite.f949ba, CipherSuite.f919aX, CipherSuite.f950bb, CipherSuite.f956bh, CipherSuite.f955bg, CipherSuite.f945ax, CipherSuite.f903aH, CipherSuite.f946ay, CipherSuite.f904aI, CipherSuite.f927af, CipherSuite.f928ag, CipherSuite.f872D, CipherSuite.f876H, CipherSuite.f963h};

    /* renamed from: d */
    final boolean f996d;

    /* renamed from: e */
    final boolean f997e;

    /* renamed from: f */
    final String[] f998f;

    /* renamed from: g */
    final String[] f999g;

    /* renamed from: c.k$a */
    /* compiled from: ConnectionSpec */
    public static final class C0531a {

        /* renamed from: a */
        boolean f1000a;

        /* renamed from: b */
        String[] f1001b;

        /* renamed from: c */
        String[] f1002c;

        /* renamed from: d */
        boolean f1003d;

        C0531a(boolean z) {
            this.f1000a = z;
        }

        public C0531a(ConnectionSpec kVar) {
            this.f1000a = kVar.f996d;
            this.f1001b = kVar.f998f;
            this.f1002c = kVar.f999g;
            this.f1003d = kVar.f997e;
        }

        /* renamed from: a */
        public C0531a mo8913a(CipherSuite... hVarArr) {
            if (!this.f1000a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[hVarArr.length];
            for (int i = 0; i < hVarArr.length; i++) {
                strArr[i] = hVarArr[i].f982bi;
            }
            return mo8914a(strArr);
        }

        /* renamed from: a */
        public C0531a mo8914a(String... strArr) {
            if (!this.f1000a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            } else {
                this.f1001b = (String[]) strArr.clone();
                return this;
            }
        }

        /* renamed from: a */
        public C0531a mo8912a(TlsVersion... acVarArr) {
            if (!this.f1000a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            String[] strArr = new String[acVarArr.length];
            for (int i = 0; i < acVarArr.length; i++) {
                strArr[i] = acVarArr[i].javaName;
            }
            return mo8916b(strArr);
        }

        /* renamed from: b */
        public C0531a mo8916b(String... strArr) {
            if (!this.f1000a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            } else {
                this.f1002c = (String[]) strArr.clone();
                return this;
            }
        }

        /* renamed from: a */
        public C0531a mo8911a(boolean z) {
            if (!this.f1000a) {
                throw new IllegalStateException("no TLS extensions for cleartext connections");
            }
            this.f1003d = z;
            return this;
        }

        /* renamed from: a */
        public ConnectionSpec mo8915a() {
            return new ConnectionSpec(this);
        }
    }

    ConnectionSpec(C0531a aVar) {
        this.f996d = aVar.f1000a;
        this.f998f = aVar.f1001b;
        this.f999g = aVar.f1002c;
        this.f997e = aVar.f1003d;
    }

    /* renamed from: a */
    public boolean mo8903a() {
        return this.f996d;
    }

    /* renamed from: b */
    public List<CipherSuite> mo8905b() {
        if (this.f998f == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.f998f.length);
        for (String a : this.f998f) {
            arrayList.add(CipherSuite.m1081a(a));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* renamed from: c */
    public List<TlsVersion> mo8906c() {
        if (this.f999g == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.f999g.length);
        for (String forJavaName : this.f999g) {
            arrayList.add(TlsVersion.forJavaName(forJavaName));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* renamed from: d */
    public boolean mo8907d() {
        return this.f997e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8902a(SSLSocket sSLSocket, boolean z) {
        ConnectionSpec b = m1092b(sSLSocket, z);
        if (b.f999g != null) {
            sSLSocket.setEnabledProtocols(b.f999g);
        }
        if (b.f998f != null) {
            sSLSocket.setEnabledCipherSuites(b.f998f);
        }
    }

    /* renamed from: b */
    private ConnectionSpec m1092b(SSLSocket sSLSocket, boolean z) {
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        if (this.f998f != null) {
            enabledCipherSuites = (String[]) Util.m657a(String.class, (T[]) this.f998f, (T[]) sSLSocket.getEnabledCipherSuites());
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        if (this.f999g != null) {
            enabledProtocols = (String[]) Util.m657a(String.class, (T[]) this.f999g, (T[]) sSLSocket.getEnabledProtocols());
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        if (z && Util.m643a((T[]) sSLSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV") != -1) {
            enabledCipherSuites = Util.m658a(enabledCipherSuites, "TLS_FALLBACK_SCSV");
        }
        return new C0531a(this).mo8914a(enabledCipherSuites).mo8916b(enabledProtocols).mo8915a();
    }

    /* renamed from: a */
    public boolean mo8904a(SSLSocket sSLSocket) {
        if (!this.f996d) {
            return false;
        }
        if (this.f999g != null && !m1091a(this.f999g, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        if (this.f998f == null || m1091a(this.f998f, sSLSocket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m1091a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length == 0 || strArr2.length == 0) {
            return false;
        }
        for (String a : strArr) {
            if (Util.m643a((T[]) strArr2, a) != -1) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConnectionSpec)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ConnectionSpec kVar = (ConnectionSpec) obj;
        if (this.f996d != kVar.f996d) {
            return false;
        }
        if (!this.f996d || (Arrays.equals(this.f998f, kVar.f998f) && Arrays.equals(this.f999g, kVar.f999g) && this.f997e == kVar.f997e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (!this.f996d) {
            return 17;
        }
        return (this.f997e ? 0 : 1) + ((((Arrays.hashCode(this.f998f) + 527) * 31) + Arrays.hashCode(this.f999g)) * 31);
    }

    public String toString() {
        if (!this.f996d) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.f998f != null ? mo8905b().toString() : "[all enabled]") + ", tlsVersions=" + (this.f999g != null ? mo8906c().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f997e + ")";
    }
}
