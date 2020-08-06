package p018c.p019a.p022c;

import android.support.p004v7.widget.ActivityChooserView;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import p018c.Cookie;
import p018c.CookieJar;
import p018c.Headers;
import p018c.HttpUrl;
import p018c.Request;
import p018c.Response;
import p018c.p019a.Util;

/* renamed from: c.a.c.e */
public final class HttpHeaders {

    /* renamed from: a */
    private static final Pattern f549a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    /* renamed from: a */
    public static long m680a(Response zVar) {
        return m679a(zVar.mo9050g());
    }

    /* renamed from: a */
    public static long m679a(Headers qVar) {
        return m681a(qVar.mo8939a("Content-Length"));
    }

    /* renamed from: a */
    private static long m681a(String str) {
        long j = -1;
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return j;
        }
    }

    /* renamed from: a */
    public static boolean m684a(Response zVar, Headers qVar, Request xVar) {
        for (String next : m691e(zVar)) {
            if (!Util.m656a((Object) qVar.mo8942b(next), (Object) xVar.mo9023b(next))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m687b(Response zVar) {
        return m686b(zVar.mo9050g());
    }

    /* renamed from: b */
    public static boolean m686b(Headers qVar) {
        return m689c(qVar).contains("*");
    }

    /* renamed from: e */
    private static Set<String> m691e(Response zVar) {
        return m689c(zVar.mo9050g());
    }

    /* renamed from: c */
    public static Set<String> m689c(Headers qVar) {
        Set<String> emptySet = Collections.emptySet();
        int a = qVar.mo8937a();
        for (int i = 0; i < a; i++) {
            if ("Vary".equalsIgnoreCase(qVar.mo8938a(i))) {
                String b = qVar.mo8941b(i);
                if (emptySet.isEmpty()) {
                    emptySet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
                }
                for (String trim : b.split(",")) {
                    emptySet.add(trim.trim());
                }
            }
        }
        return emptySet;
    }

    /* renamed from: c */
    public static Headers m688c(Response zVar) {
        return m682a(zVar.mo9053j().mo9041a().mo9024c(), zVar.mo9050g());
    }

    /* renamed from: a */
    public static Headers m682a(Headers qVar, Headers qVar2) {
        Set<String> c = m689c(qVar2);
        if (c.isEmpty()) {
            return new Headers.C0534a().mo8948a();
        }
        Headers.C0534a aVar = new Headers.C0534a();
        int a = qVar.mo8937a();
        for (int i = 0; i < a; i++) {
            String a2 = qVar.mo8938a(i);
            if (c.contains(a2)) {
                aVar.mo8947a(a2, qVar.mo8941b(i));
            }
        }
        return aVar.mo8948a();
    }

    /* renamed from: a */
    public static void m683a(CookieJar mVar, HttpUrl rVar, Headers qVar) {
        if (mVar != CookieJar.f1017a) {
            List<Cookie> a = Cookie.m1110a(rVar, qVar);
            if (!a.isEmpty()) {
                mVar.mo8924a(rVar, a);
            }
        }
    }

    /* renamed from: d */
    public static boolean m690d(Response zVar) {
        if (zVar.mo9041a().mo9022b().equals("HEAD")) {
            return false;
        }
        int c = zVar.mo9045c();
        if ((c < 100 || c >= 200) && c != 204 && c != 304) {
            return true;
        }
        if (m680a(zVar) != -1 || "chunked".equalsIgnoreCase(zVar.mo9042a("Transfer-Encoding"))) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static int m678a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    /* renamed from: a */
    public static int m677a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != 9) {
                break;
            }
            i++;
        }
        return i;
    }

    /* renamed from: b */
    public static int m685b(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException e) {
            return i;
        }
    }
}
