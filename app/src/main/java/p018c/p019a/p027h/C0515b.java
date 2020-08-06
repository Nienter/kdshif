package p018c.p019a.p027h;

import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import p018c.p019a.p026g.Platform;

/* renamed from: c.a.h.b */
/* compiled from: CertificateChainCleaner */
public abstract class C0515b {
    /* renamed from: a */
    public abstract List<Certificate> mo8821a(List<Certificate> list, String str);

    /* renamed from: a */
    public static C0515b m995a(X509TrustManager x509TrustManager) {
        return Platform.m981b().mo8813a(x509TrustManager);
    }
}
