package p005b.p006a.p007a.p008a.p009a.p015e;

import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import p005b.p006a.p007a.p008a.Fabric;

/* renamed from: b.a.a.a.a.e.h */
class PinningTrustManager implements X509TrustManager {

    /* renamed from: a */
    private static final X509Certificate[] f254a = new X509Certificate[0];

    /* renamed from: b */
    private final TrustManager[] f255b;

    /* renamed from: c */
    private final SystemKeyStore f256c;

    /* renamed from: d */
    private final long f257d;

    /* renamed from: e */
    private final List<byte[]> f258e = new LinkedList();

    /* renamed from: f */
    private final Set<X509Certificate> f259f = Collections.synchronizedSet(new HashSet());

    public PinningTrustManager(SystemKeyStore iVar, PinningInfoProvider gVar) {
        this.f255b = m370a(iVar);
        this.f256c = iVar;
        this.f257d = gVar.getPinCreationTimeInMillis();
        for (String a : gVar.getPins()) {
            this.f258e.add(m369a(a));
        }
    }

    /* renamed from: a */
    private TrustManager[] m370a(SystemKeyStore iVar) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init(iVar.f260a);
            return instance.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        }
    }

    /* renamed from: a */
    private boolean m368a(X509Certificate x509Certificate) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] equals : this.f258e) {
                if (Arrays.equals(equals, digest)) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(e);
        }
    }

    /* renamed from: a */
    private void m367a(X509Certificate[] x509CertificateArr, String str) {
        for (TrustManager trustManager : this.f255b) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
    }

    /* renamed from: a */
    private void m366a(X509Certificate[] x509CertificateArr) {
        if (this.f257d == -1 || System.currentTimeMillis() - this.f257d <= 15552000000L) {
            X509Certificate[] a = CertificateChainCleaner.m290a(x509CertificateArr, this.f256c);
            int length = a.length;
            int i = 0;
            while (i < length) {
                if (!m368a(a[i])) {
                    i++;
                } else {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        Fabric.m456h().mo8513d("Fabric", "Certificate pins are stale, (" + (System.currentTimeMillis() - this.f257d) + " millis vs " + 15552000000L + " millis) falling back to system trust.");
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (!this.f259f.contains(x509CertificateArr[0])) {
            m367a(x509CertificateArr, str);
            m366a(x509CertificateArr);
            this.f259f.add(x509CertificateArr[0]);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return f254a;
    }

    /* renamed from: a */
    private byte[] m369a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
