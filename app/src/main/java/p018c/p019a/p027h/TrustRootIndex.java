package p018c.p019a.p027h;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* renamed from: c.a.h.e */
public abstract class TrustRootIndex {

    /* renamed from: c.a.h.e$a */
    /* compiled from: TrustRootIndex */
    static final class C0516a extends TrustRootIndex {

        /* renamed from: a */
        private final X509TrustManager f795a;

        /* renamed from: b */
        private final Method f796b;

        C0516a(X509TrustManager x509TrustManager, Method method) {
            this.f796b = method;
            this.f795a = x509TrustManager;
        }

        /* renamed from: a */
        public X509Certificate mo8839a(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.f796b.invoke(this.f795a, new Object[]{x509Certificate});
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
                return null;
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0516a)) {
                return false;
            }
            C0516a aVar = (C0516a) obj;
            if (!this.f795a.equals(aVar.f795a) || !this.f796b.equals(aVar.f796b)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.f795a.hashCode() + (this.f796b.hashCode() * 31);
        }
    }

    /* renamed from: c.a.h.e$b */
    /* compiled from: TrustRootIndex */
    static final class C0517b extends TrustRootIndex {

        /* renamed from: a */
        private final Map<X500Principal, Set<X509Certificate>> f797a = new LinkedHashMap();

        public C0517b(X509Certificate... x509CertificateArr) {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
                Set set = this.f797a.get(subjectX500Principal);
                if (set == null) {
                    set = new LinkedHashSet(1);
                    this.f797a.put(subjectX500Principal, set);
                }
                set.add(x509Certificate);
            }
        }

        /* renamed from: a */
        public X509Certificate mo8839a(X509Certificate x509Certificate) {
            Set<X509Certificate> set = this.f797a.get(x509Certificate.getIssuerX500Principal());
            if (set == null) {
                return null;
            }
            for (X509Certificate x509Certificate2 : set) {
                try {
                    x509Certificate.verify(x509Certificate2.getPublicKey());
                    return x509Certificate2;
                } catch (Exception e) {
                }
            }
            return null;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0517b) || !((C0517b) obj).f797a.equals(this.f797a)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.f797a.hashCode();
        }
    }

    /* renamed from: a */
    public abstract X509Certificate mo8839a(X509Certificate x509Certificate);

    /* renamed from: a */
    public static TrustRootIndex m1011a(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[]{X509Certificate.class});
            declaredMethod.setAccessible(true);
            return new C0516a(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException e) {
            return m1012a(x509TrustManager.getAcceptedIssuers());
        }
    }

    /* renamed from: a */
    public static TrustRootIndex m1012a(X509Certificate... x509CertificateArr) {
        return new C0517b(x509CertificateArr);
    }
}
