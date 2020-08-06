package p005b.p006a.p007a.p008a.p009a.p015e;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;

/* renamed from: b.a.a.a.a.e.a */
final class CertificateChainCleaner {
    /* renamed from: a */
    public static X509Certificate[] m290a(X509Certificate[] x509CertificateArr, SystemKeyStore iVar) {
        boolean z;
        boolean z2 = true;
        LinkedList linkedList = new LinkedList();
        if (iVar.mo8477a(x509CertificateArr[0])) {
            z = true;
        } else {
            z = false;
        }
        linkedList.add(x509CertificateArr[0]);
        boolean z3 = z;
        for (int i = 1; i < x509CertificateArr.length; i++) {
            if (iVar.mo8477a(x509CertificateArr[i])) {
                z3 = true;
            }
            if (!m289a(x509CertificateArr[i], x509CertificateArr[i - 1])) {
                break;
            }
            linkedList.add(x509CertificateArr[i]);
        }
        X509Certificate b = iVar.mo8478b(x509CertificateArr[i - 1]);
        if (b != null) {
            linkedList.add(b);
        } else {
            z2 = z3;
        }
        if (z2) {
            return (X509Certificate[]) linkedList.toArray(new X509Certificate[linkedList.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
    }

    /* renamed from: a */
    private static boolean m289a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (!x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getIssuerX500Principal())) {
            return false;
        }
        try {
            x509Certificate2.verify(x509Certificate.getPublicKey());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }
}
