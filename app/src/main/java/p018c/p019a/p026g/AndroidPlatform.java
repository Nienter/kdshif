package p018c.p019a.p026g;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import p018c.Protocol;
import p018c.p019a.Util;
import p018c.p019a.p027h.C0515b;

/* renamed from: c.a.g.a */
class AndroidPlatform extends Platform {

    /* renamed from: a */
    private final Class<?> f760a;

    /* renamed from: b */
    private final OptionalMethod<Socket> f761b;

    /* renamed from: c */
    private final OptionalMethod<Socket> f762c;

    /* renamed from: d */
    private final OptionalMethod<Socket> f763d;

    /* renamed from: e */
    private final OptionalMethod<Socket> f764e;

    /* renamed from: f */
    private final C0513b f765f = C0513b.m962a();

    /* renamed from: c.a.g.a$a */
    /* compiled from: AndroidPlatform */
    static final class C0512a extends C0515b {

        /* renamed from: a */
        private final Object f766a;

        /* renamed from: b */
        private final Method f767b;

        C0512a(Object obj, Method method) {
            this.f766a = obj;
            this.f767b = method;
        }

        /* renamed from: a */
        public List<Certificate> mo8821a(List<Certificate> list, String str) {
            try {
                return (List) this.f767b.invoke(this.f766a, new Object[]{(X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str});
            } catch (InvocationTargetException e) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
                sSLPeerUnverifiedException.initCause(e);
                throw sSLPeerUnverifiedException;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof C0512a;
        }

        public int hashCode() {
            return 0;
        }
    }

    /* renamed from: c.a.g.a$b */
    /* compiled from: AndroidPlatform */
    static final class C0513b {

        /* renamed from: a */
        private final Method f768a;

        /* renamed from: b */
        private final Method f769b;

        /* renamed from: c */
        private final Method f770c;

        C0513b(Method method, Method method2, Method method3) {
            this.f768a = method;
            this.f769b = method2;
            this.f770c = method3;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public Object mo8824a(String str) {
            if (this.f768a != null) {
                try {
                    Object invoke = this.f768a.invoke(null, new Object[0]);
                    this.f769b.invoke(invoke, new Object[]{str});
                    return invoke;
                } catch (Exception e) {
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo8825a(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                this.f770c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        /* renamed from: a */
        static C0513b m962a() {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                method2 = cls.getMethod("get", new Class[0]);
                method = cls.getMethod("open", new Class[]{String.class});
                method3 = cls.getMethod("warnIfOpen", new Class[0]);
            } catch (Exception e) {
                method = method3;
                method2 = method3;
            }
            return new C0513b(method2, method, method3);
        }
    }

    public AndroidPlatform(Class<?> cls, OptionalMethod<Socket> dVar, OptionalMethod<Socket> dVar2, OptionalMethod<Socket> dVar3, OptionalMethod<Socket> dVar4) {
        this.f760a = cls;
        this.f761b = dVar;
        this.f762c = dVar2;
        this.f763d = dVar3;
        this.f764e = dVar4;
    }

    /* renamed from: a */
    public void mo8818a(Socket socket, InetSocketAddress inetSocketAddress, int i) {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (Util.m655a(e)) {
                throw new IOException(e);
            }
            throw e;
        } catch (SecurityException e2) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e2);
            throw iOException;
        }
    }

    /* renamed from: a */
    public void mo8819a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.f761b.mo8830b(sSLSocket, true);
            this.f762c.mo8830b(sSLSocket, str);
        }
        if (this.f764e != null && this.f764e.mo8829a(sSLSocket)) {
            this.f764e.mo8832d(sSLSocket, m982b(list));
        }
    }

    /* renamed from: a */
    public String mo8815a(SSLSocket sSLSocket) {
        if (this.f763d == null || !this.f763d.mo8829a(sSLSocket)) {
            return null;
        }
        byte[] bArr = (byte[]) this.f763d.mo8832d(sSLSocket, new Object[0]);
        return bArr != null ? new String(bArr, Util.f530e) : null;
    }

    /* renamed from: a */
    public void mo8816a(int i, String str, Throwable th) {
        int min;
        int i2 = i == 5 ? 5 : 3;
        if (th != null) {
            str = str + 10 + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int indexOf = str.indexOf(10, i3);
            if (indexOf == -1) {
                indexOf = length;
            }
            while (true) {
                min = Math.min(indexOf, i3 + 4000);
                Log.println(i2, "OkHttp", str.substring(i3, min));
                if (min >= indexOf) {
                    break;
                }
                i3 = min;
            }
            i3 = min + 1;
        }
    }

    /* renamed from: a */
    public Object mo8814a(String str) {
        return this.f765f.mo8824a(str);
    }

    /* renamed from: a */
    public void mo8817a(String str, Object obj) {
        if (!this.f765f.mo8825a(obj)) {
            mo8816a(5, str, (Throwable) null);
        }
    }

    /* renamed from: b */
    public boolean mo8820b(String str) {
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[]{String.class}).invoke(invoke, new Object[]{str})).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return super.mo8820b(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public C0515b mo8813a(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0512a(cls.getConstructor(new Class[]{X509TrustManager.class}).newInstance(new Object[]{x509TrustManager}), cls.getMethod("checkServerTrusted", new Class[]{X509Certificate[].class, String.class, String.class}));
        } catch (Exception e) {
            return super.mo8813a(x509TrustManager);
        }
    }

    /* renamed from: a */
    public static Platform m952a() {
        Class<?> cls;
        OptionalMethod dVar;
        OptionalMethod dVar2;
        OptionalMethod dVar3;
        try {
            cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException e) {
            cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
        }
        try {
            OptionalMethod dVar4 = new OptionalMethod(null, "setUseSessionTickets", Boolean.TYPE);
            OptionalMethod dVar5 = new OptionalMethod(null, "setHostname", String.class);
            try {
                Class.forName("android.net.Network");
                dVar = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                try {
                    dVar2 = new OptionalMethod(null, "setAlpnProtocols", byte[].class);
                    dVar3 = dVar;
                } catch (ClassNotFoundException e2) {
                    dVar2 = null;
                    dVar3 = dVar;
                    return new AndroidPlatform(cls, dVar4, dVar5, dVar3, dVar2);
                }
            } catch (ClassNotFoundException e3) {
                dVar = null;
                dVar2 = null;
                dVar3 = dVar;
                return new AndroidPlatform(cls, dVar4, dVar5, dVar3, dVar2);
            }
            return new AndroidPlatform(cls, dVar4, dVar5, dVar3, dVar2);
        } catch (ClassNotFoundException e4) {
            return null;
        }
    }
}
