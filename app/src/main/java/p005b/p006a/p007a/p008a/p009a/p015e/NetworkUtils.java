package p005b.p006a.p007a.p008a.p009a.p015e;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* renamed from: b.a.a.a.a.e.f */
public final class NetworkUtils {
    /* renamed from: a */
    public static final SSLSocketFactory m365a(PinningInfoProvider gVar) {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, new TrustManager[]{new PinningTrustManager(new SystemKeyStore(gVar.getKeyStoreStream(), gVar.getKeyStorePassword()), gVar)}, null);
        return instance.getSocketFactory();
    }
}
