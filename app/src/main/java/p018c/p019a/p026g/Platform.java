package p018c.p019a.p026g;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import p018c.OkHttpClient;
import p018c.Protocol;
import p018c.p019a.p027h.BasicCertificateChainCleaner;
import p018c.p019a.p027h.C0515b;
import p018c.p019a.p027h.TrustRootIndex;
import p033d.Buffer;

/* renamed from: c.a.g.e */
public class Platform {

    /* renamed from: a */
    private static final Platform f784a = m979a();

    /* renamed from: b */
    private static final Logger f785b = Logger.getLogger(OkHttpClient.class.getName());

    /* renamed from: b */
    public static Platform m981b() {
        return f784a;
    }

    /* renamed from: c */
    public String mo8833c() {
        return "OkHttp";
    }

    /* renamed from: a */
    public void mo8819a(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    /* renamed from: b */
    public void mo8826b(SSLSocket sSLSocket) {
    }

    /* renamed from: a */
    public String mo8815a(SSLSocket sSLSocket) {
        return null;
    }

    /* renamed from: a */
    public void mo8818a(Socket socket, InetSocketAddress inetSocketAddress, int i) {
        socket.connect(inetSocketAddress, i);
    }

    /* renamed from: a */
    public void mo8816a(int i, String str, Throwable th) {
        f785b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    /* renamed from: b */
    public boolean mo8820b(String str) {
        return true;
    }

    /* renamed from: a */
    public Object mo8814a(String str) {
        if (f785b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    /* renamed from: a */
    public void mo8817a(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        mo8816a(5, str, (Throwable) obj);
    }

    /* renamed from: a */
    public static List<String> m980a(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol vVar = list.get(i);
            if (vVar != Protocol.HTTP_1_0) {
                arrayList.add(vVar.toString());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public C0515b mo8813a(X509TrustManager x509TrustManager) {
        return new BasicCertificateChainCleaner(TrustRootIndex.m1011a(x509TrustManager));
    }

    /* renamed from: a */
    private static Platform m979a() {
        Platform a = AndroidPlatform.m952a();
        if (a != null) {
            return a;
        }
        Jdk9Platform a2 = Jdk9Platform.m965a();
        if (a2 != null) {
            return a2;
        }
        Platform a3 = JdkWithJettyBootPlatform.m968a();
        return a3 == null ? new Platform() : a3;
    }

    /* renamed from: b */
    static byte[] m982b(List<Protocol> list) {
        Buffer cVar = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol vVar = list.get(i);
            if (vVar != Protocol.HTTP_1_0) {
                cVar.mo17677i(vVar.toString().length());
                cVar.mo17652b(vVar.toString());
            }
        }
        return cVar.mo17689r();
    }
}
