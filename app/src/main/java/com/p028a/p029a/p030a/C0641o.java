package com.p028a.p029a.p030a;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.a.a.a.o */
/* compiled from: NetworkHelper */
class C0641o implements X509TrustManager {

    /* renamed from: a */
    X509TrustManager f1627a;

    public C0641o() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init(null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            for (int i = 0; i < trustManagers.length; i++) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    this.f1627a = (X509TrustManager) trustManagers[i];
                    return;
                }
            }
        } catch (Throwable th) {
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        try {
            this.f1627a.checkServerTrusted(x509CertificateArr, str);
        } catch (CertificateException e) {
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.f1627a.getAcceptedIssuers();
    }
}
