package p018c;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import p018c.p019a.Util;

/* renamed from: c.p */
public final class Handshake {

    /* renamed from: a */
    private final TlsVersion f1026a;

    /* renamed from: b */
    private final CipherSuite f1027b;

    /* renamed from: c */
    private final List<Certificate> f1028c;

    /* renamed from: d */
    private final List<Certificate> f1029d;

    private Handshake(TlsVersion acVar, CipherSuite hVar, List<Certificate> list, List<Certificate> list2) {
        this.f1026a = acVar;
        this.f1027b = hVar;
        this.f1028c = list;
        this.f1029d = list2;
    }

    /* renamed from: a */
    public static Handshake m1131a(SSLSession sSLSession) {
        Certificate[] certificateArr;
        List emptyList;
        List emptyList2;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        CipherSuite a = CipherSuite.m1081a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        TlsVersion forJavaName = TlsVersion.forJavaName(protocol);
        try {
            certificateArr = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            certificateArr = null;
        }
        if (certificateArr != null) {
            emptyList = Util.m648a((T[]) certificateArr);
        } else {
            emptyList = Collections.emptyList();
        }
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            emptyList2 = Util.m648a((T[]) localCertificates);
        } else {
            emptyList2 = Collections.emptyList();
        }
        return new Handshake(forJavaName, a, emptyList, emptyList2);
    }

    /* renamed from: a */
    public static Handshake m1130a(TlsVersion acVar, CipherSuite hVar, List<Certificate> list, List<Certificate> list2) {
        if (hVar != null) {
            return new Handshake(acVar, hVar, Util.m647a(list), Util.m647a(list2));
        }
        throw new NullPointerException("cipherSuite == null");
    }

    /* renamed from: a */
    public TlsVersion mo8931a() {
        return this.f1026a;
    }

    /* renamed from: b */
    public CipherSuite mo8932b() {
        return this.f1027b;
    }

    /* renamed from: c */
    public List<Certificate> mo8933c() {
        return this.f1028c;
    }

    /* renamed from: d */
    public List<Certificate> mo8934d() {
        return this.f1029d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Handshake)) {
            return false;
        }
        Handshake pVar = (Handshake) obj;
        if (!Util.m656a((Object) this.f1027b, (Object) pVar.f1027b) || !this.f1027b.equals(pVar.f1027b) || !this.f1028c.equals(pVar.f1028c) || !this.f1029d.equals(pVar.f1029d)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((this.f1026a != null ? this.f1026a.hashCode() : 0) + 527) * 31) + this.f1027b.hashCode()) * 31) + this.f1028c.hashCode()) * 31) + this.f1029d.hashCode();
    }
}
