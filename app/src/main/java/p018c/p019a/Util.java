package p018c.p019a;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.net.IDN;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import p018c.HttpUrl;
import p018c.RequestBody;
import p018c.ResponseBody;
import p033d.Buffer;
import p033d.ByteString;
import p033d.Source;

/* renamed from: c.a.c */
public final class Util {

    /* renamed from: a */
    public static final byte[] f526a = new byte[0];

    /* renamed from: b */
    public static final String[] f527b = new String[0];

    /* renamed from: c */
    public static final ResponseBody f528c = ResponseBody.m1017a(null, f526a);

    /* renamed from: d */
    public static final RequestBody f529d = RequestBody.m1271a(null, f526a);

    /* renamed from: e */
    public static final Charset f530e = Charset.forName("UTF-8");

    /* renamed from: f */
    public static final TimeZone f531f = TimeZone.getTimeZone("GMT");

    /* renamed from: g */
    private static final ByteString f532g = ByteString.decodeHex("efbbbf");

    /* renamed from: h */
    private static final ByteString f533h = ByteString.decodeHex("feff");

    /* renamed from: i */
    private static final ByteString f534i = ByteString.decodeHex("fffe");

    /* renamed from: j */
    private static final ByteString f535j = ByteString.decodeHex("0000ffff");

    /* renamed from: k */
    private static final ByteString f536k = ByteString.decodeHex("ffff0000");

    /* renamed from: l */
    private static final Charset f537l = Charset.forName("UTF-16BE");

    /* renamed from: m */
    private static final Charset f538m = Charset.forName("UTF-16LE");

    /* renamed from: n */
    private static final Charset f539n = Charset.forName("UTF-32BE");

    /* renamed from: o */
    private static final Charset f540o = Charset.forName("UTF-32LE");

    /* renamed from: p */
    private static final Pattern f541p = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    /* renamed from: a */
    public static void m651a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /* renamed from: a */
    public static boolean m656a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    public static void m652a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: a */
    public static void m653a(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!m655a(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
            }
        }
    }

    /* renamed from: a */
    public static boolean m654a(Source sVar, int i, TimeUnit timeUnit) {
        try {
            return m661b(sVar, i, timeUnit);
        } catch (IOException e) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m661b(Source sVar, int i, TimeUnit timeUnit) {
        long nanoTime = System.nanoTime();
        long d = sVar.mo8593a().mo17747c_() ? sVar.mo8593a().mo17748d() - nanoTime : Long.MAX_VALUE;
        sVar.mo8593a().mo17744a(Math.min(d, timeUnit.toNanos((long) i)) + nanoTime);
        try {
            Buffer cVar = new Buffer();
            while (sVar.mo8592a(cVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                cVar.mo17690s();
            }
            if (d == Long.MAX_VALUE) {
                sVar.mo8593a().mo17750e_();
            } else {
                sVar.mo8593a().mo17744a(d + nanoTime);
            }
            return true;
        } catch (InterruptedIOException e) {
            if (d == Long.MAX_VALUE) {
                sVar.mo8593a().mo17750e_();
            } else {
                sVar.mo8593a().mo17744a(d + nanoTime);
            }
            return false;
        } catch (Throwable th) {
            if (d == Long.MAX_VALUE) {
                sVar.mo8593a().mo17750e_();
            } else {
                sVar.mo8593a().mo17744a(d + nanoTime);
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static <T> List<T> m647a(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    /* renamed from: a */
    public static <T> List<T> m648a(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    /* renamed from: a */
    public static ThreadFactory m650a(final String str, final boolean z) {
        return new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    /* renamed from: a */
    public static <T> T[] m657a(Class<T> cls, T[] tArr, T[] tArr2) {
        List a = m649a(tArr, tArr2);
        return a.toArray((Object[]) Array.newInstance(cls, a.size()));
    }

    /* renamed from: a */
    private static <T> List<T> m649a(T[] tArr, T[] tArr2) {
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            int length = tArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                T t2 = tArr2[i];
                if (t.equals(t2)) {
                    arrayList.add(t2);
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m644a(HttpUrl rVar, boolean z) {
        String f;
        if (rVar.mo8961f().contains(":")) {
            f = "[" + rVar.mo8961f() + "]";
        } else {
            f = rVar.mo8961f();
        }
        if (z || rVar.mo8962g() != HttpUrl.m1152a(rVar.mo8954b())) {
            return f + ":" + rVar.mo8962g();
        }
        return f;
    }

    /* renamed from: a */
    public static boolean m655a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    /* renamed from: a */
    public static <T> int m643a(T[] tArr, T t) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (m656a((Object) tArr[i], (Object) t)) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static String[] m658a(String[] strArr, String str) {
        String[] strArr2 = new String[(strArr.length + 1)];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        return strArr2;
    }

    /* renamed from: a */
    public static int m640a(String str, int i, int i2) {
        int i3 = i;
        while (i3 < i2) {
            switch (str.charAt(i3)) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    i3++;
                default:
                    return i3;
            }
        }
        return i2;
    }

    /* renamed from: b */
    public static int m660b(String str, int i, int i2) {
        int i3 = i2 - 1;
        while (i3 >= i) {
            switch (str.charAt(i3)) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    i3--;
                default:
                    return i3 + 1;
            }
        }
        return i;
    }

    /* renamed from: c */
    public static String m662c(String str, int i, int i2) {
        int a = m640a(str, i, i2);
        return str.substring(a, m660b(str, a, i2));
    }

    /* renamed from: a */
    public static int m642a(String str, int i, int i2, String str2) {
        for (int i3 = i; i3 < i2; i3++) {
            if (str2.indexOf(str.charAt(i3)) != -1) {
                return i3;
            }
        }
        return i2;
    }

    /* renamed from: a */
    public static int m641a(String str, int i, int i2, char c) {
        for (int i3 = i; i3 < i2; i3++) {
            if (str.charAt(i3) == c) {
                return i3;
            }
        }
        return i2;
    }

    /* renamed from: a */
    public static String m645a(String str) {
        try {
            String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (!lowerCase.isEmpty() && !m664d(lowerCase)) {
                return lowerCase;
            }
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /* renamed from: d */
    private static boolean m664d(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                return true;
            }
            if (" #%/:?@[\\]".indexOf(charAt) != -1) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static int m659b(String str) {
        int i = 0;
        int length = str.length();
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* renamed from: c */
    public static boolean m663c(String str) {
        return f541p.matcher(str).matches();
    }

    /* renamed from: a */
    public static String m646a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }
}
