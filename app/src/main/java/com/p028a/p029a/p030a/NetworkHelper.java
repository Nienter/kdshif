package com.p028a.p029a.p030a;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* renamed from: com.a.a.a.ab */
class NetworkHelper extends SSLSocketFactory {

    /* renamed from: a */
    SSLContext f1170a = SSLContext.getInstance("TLS");

    public NetworkHelper(KeyStore keyStore) {
        super(keyStore);
        try {
            C05441 r0 = new C0641o() {
            };
            this.f1170a.init(null, new TrustManager[]{r0}, null);
        } catch (Throwable th) {
        }
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.f1170a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket createSocket() {
        return this.f1170a.getSocketFactory().createSocket();
    }
}
