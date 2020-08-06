package p018c.p019a.p022c;

import java.net.Proxy;
import p018c.HttpUrl;
import p018c.Request;

/* renamed from: c.a.c.i */
public final class RequestLine {
    /* renamed from: a */
    public static String m706a(Request xVar, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(xVar.mo9022b());
        sb.append(' ');
        if (m707b(xVar, type)) {
            sb.append(xVar.mo9020a());
        } else {
            sb.append(m705a(xVar.mo9020a()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    /* renamed from: b */
    private static boolean m707b(Request xVar, Proxy.Type type) {
        return !xVar.mo9028g() && type == Proxy.Type.HTTP;
    }

    /* renamed from: a */
    public static String m705a(HttpUrl rVar) {
        String h = rVar.mo8963h();
        String j = rVar.mo8966j();
        return j != null ? h + '?' + j : h;
    }
}
