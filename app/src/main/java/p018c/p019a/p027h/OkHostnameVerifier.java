package p018c.p019a.p027h;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import p018c.p019a.Util;

/* renamed from: c.a.h.d */
public final class OkHostnameVerifier implements HostnameVerifier {

    /* renamed from: a */
    public static final OkHostnameVerifier f794a = new OkHostnameVerifier();

    private OkHostnameVerifier() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return mo8837a(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException e) {
            return false;
        }
    }

    /* renamed from: a */
    public boolean mo8837a(String str, X509Certificate x509Certificate) {
        if (Util.m663c(str)) {
            return m1008b(str, x509Certificate);
        }
        return m1009c(str, x509Certificate);
    }

    /* renamed from: b */
    private boolean m1008b(String str, X509Certificate x509Certificate) {
        List<String> a = m1006a(x509Certificate, 7);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(a.get(i))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    private boolean m1009c(String str, X509Certificate x509Certificate) {
        String lowerCase = str.toLowerCase(Locale.US);
        List<String> a = m1006a(x509Certificate, 2);
        int size = a.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            if (m1007a(lowerCase, a.get(i))) {
                return true;
            }
            i++;
            z = true;
        }
        if (!z) {
            String a2 = new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).mo8836a("cn");
            if (a2 != null) {
                return m1007a(lowerCase, a2);
            }
        }
        return false;
    }

    /* renamed from: a */
    public static List<String> m1005a(X509Certificate x509Certificate) {
        List<String> a = m1006a(x509Certificate, 7);
        List<String> a2 = m1006a(x509Certificate, 2);
        ArrayList arrayList = new ArrayList(a.size() + a2.size());
        arrayList.addAll(a);
        arrayList.addAll(a2);
        return arrayList;
    }

    /* renamed from: a */
    private static List<String> m1006a(X509Certificate x509Certificate, int i) {
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List next : subjectAlternativeNames) {
                if (next != null && next.size() >= 2) {
                    Integer num = (Integer) next.get(0);
                    if (num != null && num.intValue() == i) {
                        String str = (String) next.get(1);
                        if (str != null) {
                            arrayList.add(str);
                        }
                    }
                }
            }
            return arrayList;
        } catch (CertificateParsingException e) {
            return Collections.emptyList();
        }
    }

    /* renamed from: a */
    private boolean m1007a(String str, String str2) {
        if (str == null || str.length() == 0 || str.startsWith(".") || str.endsWith("..") || str2 == null || str2.length() == 0 || str2.startsWith(".") || str2.endsWith("..")) {
            return false;
        }
        if (!str.endsWith(".")) {
            str = str + '.';
        }
        if (!str2.endsWith(".")) {
            str2 = str2 + '.';
        }
        String lowerCase = str2.toLowerCase(Locale.US);
        if (!lowerCase.contains("*")) {
            return str.equals(lowerCase);
        }
        if (!lowerCase.startsWith("*.") || lowerCase.indexOf(42, 1) != -1 || str.length() < lowerCase.length() || "*.".equals(lowerCase)) {
            return false;
        }
        String substring = lowerCase.substring(1);
        if (!str.endsWith(substring)) {
            return false;
        }
        if (str.length() - substring.length() <= 0 || str.lastIndexOf(46, r2 - 1) == -1) {
            return true;
        }
        return false;
    }
}
