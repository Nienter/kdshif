package p018c.p019a.p026g;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;
import p018c.Protocol;
import p018c.p019a.Util;

/* renamed from: c.a.g.c */
class JdkWithJettyBootPlatform extends Platform {

    /* renamed from: a */
    private final Method f773a;

    /* renamed from: b */
    private final Method f774b;

    /* renamed from: c */
    private final Method f775c;

    /* renamed from: d */
    private final Class<?> f776d;

    /* renamed from: e */
    private final Class<?> f777e;

    /* renamed from: c.a.g.c$a */
    /* compiled from: JdkWithJettyBootPlatform */
    private static class C0514a implements InvocationHandler {

        /* renamed from: a */
        boolean f778a;

        /* renamed from: b */
        String f779b;

        /* renamed from: c */
        private final List<String> f780c;

        public C0514a(List<String> list) {
            this.f780c = list;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = Util.f527b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f778a = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.f780c;
            } else {
                if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                    List list = (List) objArr[0];
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.f780c.contains(list.get(i))) {
                            String str = (String) list.get(i);
                            this.f779b = str;
                            return str;
                        }
                    }
                    String str2 = this.f780c.get(0);
                    this.f779b = str2;
                    return str2;
                } else if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                } else {
                    this.f779b = (String) objArr[0];
                    return null;
                }
            }
        }
    }

    public JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f773a = method;
        this.f774b = method2;
        this.f775c = method3;
        this.f776d = cls;
        this.f777e = cls2;
    }

    /* renamed from: a */
    public void mo8819a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        List<String> a = m980a(list);
        try {
            Object newProxyInstance = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.f776d, this.f777e}, new C0514a(a));
            this.f773a.invoke(null, new Object[]{sSLSocket, newProxyInstance});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: b */
    public void mo8826b(SSLSocket sSLSocket) {
        try {
            this.f775c.invoke(null, new Object[]{sSLSocket});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public String mo8815a(SSLSocket sSLSocket) {
        try {
            C0514a aVar = (C0514a) Proxy.getInvocationHandler(this.f774b.invoke(null, new Object[]{sSLSocket}));
            if (aVar.f778a || aVar.f779b != null) {
                return aVar.f778a ? null : aVar.f779b;
            }
            Platform.m981b().mo8816a(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", (Throwable) null);
            return null;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public static Platform m968a() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN" + "$Provider");
            Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN" + "$ClientProvider");
            Class<?> cls4 = Class.forName("org.eclipse.jetty.alpn.ALPN" + "$ServerProvider");
            return new JdkWithJettyBootPlatform(cls.getMethod("put", new Class[]{SSLSocket.class, cls2}), cls.getMethod("get", new Class[]{SSLSocket.class}), cls.getMethod("remove", new Class[]{SSLSocket.class}), cls3, cls4);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return null;
        }
    }
}
