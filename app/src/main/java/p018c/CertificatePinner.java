package p018c;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import p018c.p019a.Util;
import p018c.p019a.p027h.C0515b;
import p033d.ByteString;

/* renamed from: c.g */
public final class CertificatePinner {

    /* renamed from: a */
    public static final CertificatePinner f861a = new C0528a().mo8889a();

    /* renamed from: b */
    private final Set<C0529b> f862b;

    /* renamed from: c */
    private final C0515b f863c;

    /* renamed from: c.g$a */
    /* compiled from: CertificatePinner */
    public static final class C0528a {

        /* renamed from: a */
        private final List<C0529b> f864a = new ArrayList();

        /* renamed from: a */
        public CertificatePinner mo8889a() {
            return new CertificatePinner(new LinkedHashSet(this.f864a), null);
        }
    }

    /* renamed from: c.g$b */
    /* compiled from: CertificatePinner */
    static final class C0529b {

        /* renamed from: a */
        final String f865a;

        /* renamed from: b */
        final String f866b;

        /* renamed from: c */
        final String f867c;

        /* renamed from: d */
        final ByteString f868d;

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo8890a(String str) {
            if (!this.f865a.startsWith("*.")) {
                return str.equals(this.f866b);
            }
            return str.regionMatches(false, str.indexOf(46) + 1, this.f866b, 0, this.f866b.length());
        }

        public boolean equals(Object obj) {
            return (obj instanceof C0529b) && this.f865a.equals(((C0529b) obj).f865a) && this.f867c.equals(((C0529b) obj).f867c) && this.f868d.equals(((C0529b) obj).f868d);
        }

        public int hashCode() {
            return ((((this.f865a.hashCode() + 527) * 31) + this.f867c.hashCode()) * 31) + this.f868d.hashCode();
        }

        public String toString() {
            return this.f867c + this.f868d.base64();
        }
    }

    CertificatePinner(Set<C0529b> set, C0515b bVar) {
        this.f862b = set;
        this.f863c = bVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CertificatePinner) && Util.m656a((Object) this.f863c, (Object) ((CertificatePinner) obj).f863c) && this.f862b.equals(((CertificatePinner) obj).f862b);
    }

    public int hashCode() {
        return ((this.f863c != null ? this.f863c.hashCode() : 0) * 31) + this.f862b.hashCode();
    }

    /* renamed from: a */
    public void mo8886a(String str, List<Certificate> list) {
        List<C0529b> a = mo8885a(str);
        if (!a.isEmpty()) {
            if (this.f863c != null) {
                list = this.f863c.mo8821a(list, str);
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i);
                int size2 = a.size();
                int i2 = 0;
                ByteString fVar = null;
                ByteString fVar2 = null;
                while (i2 < size2) {
                    C0529b bVar = a.get(i2);
                    if (bVar.f867c.equals("sha256/")) {
                        if (fVar == null) {
                            fVar = m1075b(x509Certificate);
                        }
                        if (bVar.f868d.equals(fVar)) {
                            return;
                        }
                    } else if (bVar.f867c.equals("sha1/")) {
                        if (fVar2 == null) {
                            fVar2 = m1073a(x509Certificate);
                        }
                        if (bVar.f868d.equals(fVar2)) {
                            return;
                        }
                    } else {
                        throw new AssertionError();
                    }
                    i2++;
                    fVar2 = fVar2;
                    fVar = fVar;
                }
            }
            StringBuilder append = new StringBuilder().append("Certificate pinning failure!").append("\n  Peer certificate chain:");
            int size3 = list.size();
            for (int i3 = 0; i3 < size3; i3++) {
                X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
                append.append("\n    ").append(m1074a((Certificate) x509Certificate2)).append(": ").append(x509Certificate2.getSubjectDN().getName());
            }
            append.append("\n  Pinned certificates for ").append(str).append(":");
            int size4 = a.size();
            for (int i4 = 0; i4 < size4; i4++) {
                append.append("\n    ").append(a.get(i4));
            }
            throw new SSLPeerUnverifiedException(append.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public List<C0529b> mo8885a(String str) {
        List<C0529b> emptyList = Collections.emptyList();
        for (C0529b next : this.f862b) {
            if (next.mo8890a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList<>();
                }
                emptyList.add(next);
            }
        }
        return emptyList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public CertificatePinner mo8884a(C0515b bVar) {
        return Util.m656a((Object) this.f863c, (Object) bVar) ? this : new CertificatePinner(this.f862b, bVar);
    }

    /* renamed from: a */
    public static String m1074a(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + m1075b((X509Certificate) certificate).base64();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    /* renamed from: a */
    static ByteString m1073a(X509Certificate x509Certificate) {
        return ByteString.m3563of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    /* renamed from: b */
    static ByteString m1075b(X509Certificate x509Certificate) {
        return ByteString.m3563of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }
}
