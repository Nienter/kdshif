package p018c.p019a.p022c;

/* renamed from: c.a.c.f */
/* compiled from: HttpMethod */
public final class C0478f {
    /* renamed from: a */
    public static boolean m692a(String str) {
        return str.equals("POST") || str.equals("PATCH") || str.equals("PUT") || str.equals("DELETE") || str.equals("MOVE");
    }

    /* renamed from: b */
    public static boolean m693b(String str) {
        return str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    /* renamed from: c */
    public static boolean m694c(String str) {
        if (m693b(str) || str.equals("OPTIONS") || str.equals("DELETE") || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK")) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public static boolean m695d(String str) {
        return str.equals("PROPFIND");
    }

    /* renamed from: e */
    public static boolean m696e(String str) {
        return !str.equals("PROPFIND");
    }
}
