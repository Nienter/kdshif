package p005b.p006a.p007a.p008a.p009a.p015e;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import p005b.p006a.p007a.p008a.DefaultLogger;
import p005b.p006a.p007a.p008a.Logger;

/* renamed from: b.a.a.a.a.e.b */
public class DefaultHttpRequestFactory implements HttpRequestFactory {

    /* renamed from: a */
    private final Logger f229a;

    /* renamed from: b */
    private PinningInfoProvider f230b;

    /* renamed from: c */
    private SSLSocketFactory f231c;

    /* renamed from: d */
    private boolean f232d;

    public DefaultHttpRequestFactory() {
        this(new DefaultLogger());
    }

    public DefaultHttpRequestFactory(Logger lVar) {
        this.f229a = lVar;
    }

    /* renamed from: a */
    public void mo8421a(PinningInfoProvider gVar) {
        if (this.f230b != gVar) {
            this.f230b = gVar;
            m291a();
        }
    }

    /* renamed from: a */
    private synchronized void m291a() {
        this.f232d = false;
        this.f231c = null;
    }

    /* renamed from: a */
    public HttpRequest mo8419a(HttpMethod cVar, String str) {
        return mo8420a(cVar, str, Collections.emptyMap());
    }

    /* renamed from: a */
    public HttpRequest mo8420a(HttpMethod cVar, String str, Map<String, String> map) {
        HttpRequest e;
        switch (cVar) {
            case GET:
                e = HttpRequest.m299a((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case POST:
                e = HttpRequest.m304b((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case PUT:
                e = HttpRequest.m307d((CharSequence) str);
                break;
            case DELETE:
                e = HttpRequest.m308e((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (m292a(str) && this.f230b != null) {
            SSLSocketFactory b = m293b();
            if (b != null) {
                ((HttpsURLConnection) e.mo8435a()).setSSLSocketFactory(b);
            }
        }
        return e;
    }

    /* renamed from: a */
    private boolean m292a(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith("https");
    }

    /* renamed from: b */
    private synchronized SSLSocketFactory m293b() {
        if (this.f231c == null && !this.f232d) {
            this.f231c = m294c();
        }
        return this.f231c;
    }

    /* renamed from: c */
    private synchronized SSLSocketFactory m294c() {
        SSLSocketFactory sSLSocketFactory;
        this.f232d = true;
        try {
            sSLSocketFactory = NetworkUtils.m365a(this.f230b);
            this.f229a.mo8506a("Fabric", "Custom SSL pinning enabled");
        } catch (Exception e) {
            this.f229a.mo8516e("Fabric", "Exception while validating pinned certs", e);
            sSLSocketFactory = null;
        }
        return sSLSocketFactory;
    }
}
