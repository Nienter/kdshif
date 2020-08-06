package p018c.p019a.p026g;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import p018c.Protocol;

/* renamed from: c.a.g.b */
final class Jdk9Platform extends Platform {

    /* renamed from: a */
    final Method f771a;

    /* renamed from: b */
    final Method f772b;

    public Jdk9Platform(Method method, Method method2) {
        this.f771a = method;
        this.f772b = method2;
    }

    /* renamed from: a */
    public void mo8819a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> a = m980a(list);
            this.f771a.invoke(sSLParameters, new Object[]{a.toArray(new String[a.size()])});
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public String mo8815a(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f772b.invoke(sSLSocket, new Object[0]);
            if (str == null || str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public static Jdk9Platform m965a() {
        try {
            return new Jdk9Platform(SSLParameters.class.getMethod("setApplicationProtocols", new Class[]{String[].class}), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
