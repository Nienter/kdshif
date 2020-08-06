package p018c;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p018c.p019a.Util;
import p033d.Buffer;

/* renamed from: c.r */
public final class HttpUrl {

    /* renamed from: d */
    private static final char[] f1032d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    final String f1033a;

    /* renamed from: b */
    final String f1034b;

    /* renamed from: c */
    final int f1035c;

    /* renamed from: e */
    private final String f1036e;

    /* renamed from: f */
    private final String f1037f;

    /* renamed from: g */
    private final List<String> f1038g;

    /* renamed from: h */
    private final List<String> f1039h;

    /* renamed from: i */
    private final String f1040i;

    /* renamed from: j */
    private final String f1041j;

    /* renamed from: c.r$a */
    /* compiled from: HttpUrl */
    public static final class C0535a {

        /* renamed from: a */
        String f1042a;

        /* renamed from: b */
        String f1043b = "";

        /* renamed from: c */
        String f1044c = "";

        /* renamed from: d */
        String f1045d;

        /* renamed from: e */
        int f1046e = -1;

        /* renamed from: f */
        final List<String> f1047f = new ArrayList();

        /* renamed from: g */
        List<String> f1048g;

        /* renamed from: h */
        String f1049h;

        /* renamed from: c.r$a$a */
        /* compiled from: HttpUrl */
        enum C0536a {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public C0535a() {
            this.f1047f.add("");
        }

        /* renamed from: a */
        public C0535a mo8975a(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase("http")) {
                this.f1042a = "http";
            } else if (str.equalsIgnoreCase("https")) {
                this.f1042a = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + str);
            }
            return this;
        }

        /* renamed from: b */
        public C0535a mo8977b(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.f1043b = HttpUrl.m1155a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        /* renamed from: c */
        public C0535a mo8978c(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.f1044c = HttpUrl.m1155a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        /* renamed from: d */
        public C0535a mo8980d(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String e = m1189e(str, 0, str.length());
            if (e == null) {
                throw new IllegalArgumentException("unexpected host: " + str);
            }
            this.f1045d = e;
            return this;
        }

        /* renamed from: a */
        public C0535a mo8974a(int i) {
            if (i <= 0 || i > 65535) {
                throw new IllegalArgumentException("unexpected port: " + i);
            }
            this.f1046e = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo8972a() {
            return this.f1046e != -1 ? this.f1046e : HttpUrl.m1152a(this.f1042a);
        }

        /* renamed from: e */
        public C0535a mo8981e(String str) {
            this.f1048g = str != null ? HttpUrl.m1162b(HttpUrl.m1155a(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public C0535a mo8976b() {
            int size = this.f1047f.size();
            for (int i = 0; i < size; i++) {
                this.f1047f.set(i, HttpUrl.m1155a(this.f1047f.get(i), "[]", true, true, false, true));
            }
            if (this.f1048g != null) {
                int size2 = this.f1048g.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = this.f1048g.get(i2);
                    if (str != null) {
                        this.f1048g.set(i2, HttpUrl.m1155a(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            if (this.f1049h != null) {
                this.f1049h = HttpUrl.m1155a(this.f1049h, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        /* renamed from: c */
        public HttpUrl mo8979c() {
            if (this.f1042a == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.f1045d != null) {
                return new HttpUrl(this);
            } else {
                throw new IllegalStateException("host == null");
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f1042a);
            sb.append("://");
            if (!this.f1043b.isEmpty() || !this.f1044c.isEmpty()) {
                sb.append(this.f1043b);
                if (!this.f1044c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f1044c);
                }
                sb.append('@');
            }
            if (this.f1045d.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.f1045d);
                sb.append(']');
            } else {
                sb.append(this.f1045d);
            }
            int a = mo8972a();
            if (a != HttpUrl.m1152a(this.f1042a)) {
                sb.append(':');
                sb.append(a);
            }
            HttpUrl.m1160a(sb, this.f1047f);
            if (this.f1048g != null) {
                sb.append('?');
                HttpUrl.m1163b(sb, this.f1048g);
            }
            if (this.f1049h != null) {
                sb.append('#');
                sb.append(this.f1049h);
            }
            return sb.toString();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C0536a mo8973a(HttpUrl rVar, String str) {
            int i;
            int a = Util.m640a(str, 0, str.length());
            int b = Util.m660b(str, a, str.length());
            if (m1185b(str, a, b) != -1) {
                if (str.regionMatches(true, a, "https:", 0, 6)) {
                    this.f1042a = "https";
                    a += "https:".length();
                } else if (!str.regionMatches(true, a, "http:", 0, 5)) {
                    return C0536a.UNSUPPORTED_SCHEME;
                } else {
                    this.f1042a = "http";
                    a += "http:".length();
                }
            } else if (rVar == null) {
                return C0536a.MISSING_SCHEME;
            } else {
                this.f1042a = rVar.f1033a;
            }
            boolean z = false;
            boolean z2 = false;
            int c = m1186c(str, a, b);
            if (c >= 2 || rVar == null || !rVar.f1033a.equals(this.f1042a)) {
                int i2 = a + c;
                while (true) {
                    boolean z3 = z2;
                    boolean z4 = z;
                    int i3 = i2;
                    int a2 = Util.m642a(str, i3, b, "@/\\?#");
                    switch (a2 != b ? str.charAt(a2) : 65535) {
                        case 65535:
                        case '#':
                        case '/':
                        case '?':
                        case '\\':
                            int d = m1187d(str, i3, a2);
                            if (d + 1 < a2) {
                                this.f1045d = m1189e(str, i3, d);
                                this.f1046e = m1192g(str, d + 1, a2);
                                if (this.f1046e == -1) {
                                    return C0536a.INVALID_PORT;
                                }
                            } else {
                                this.f1045d = m1189e(str, i3, d);
                                this.f1046e = HttpUrl.m1152a(this.f1042a);
                            }
                            if (this.f1045d != null) {
                                a = a2;
                                break;
                            } else {
                                return C0536a.INVALID_HOST;
                            }
                        case '@':
                            if (!z3) {
                                int a3 = Util.m641a(str, i3, a2, ':');
                                String a4 = HttpUrl.m1153a(str, i3, a3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                if (z4) {
                                    a4 = this.f1043b + "%40" + a4;
                                }
                                this.f1043b = a4;
                                if (a3 != a2) {
                                    z3 = true;
                                    this.f1044c = HttpUrl.m1153a(str, a3 + 1, a2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                }
                                z4 = true;
                            } else {
                                this.f1044c += "%40" + HttpUrl.m1153a(str, i3, a2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            }
                            i2 = a2 + 1;
                            z2 = z3;
                            continue;
                        default:
                            z2 = z3;
                            i2 = i3;
                            continue;
                    }
                    z = z4;
                }
            } else {
                this.f1043b = rVar.mo8958d();
                this.f1044c = rVar.mo8959e();
                this.f1045d = rVar.f1034b;
                this.f1046e = rVar.f1035c;
                this.f1047f.clear();
                this.f1047f.addAll(rVar.mo8965i());
                if (a == b || str.charAt(a) == '#') {
                    mo8981e(rVar.mo8966j());
                }
            }
            int a5 = Util.m642a(str, a, b, "?#");
            m1182a(str, a, a5);
            if (a5 >= b || str.charAt(a5) != '?') {
                i = a5;
            } else {
                i = Util.m641a(str, a5, b, '#');
                this.f1048g = HttpUrl.m1162b(HttpUrl.m1153a(str, a5 + 1, i, " \"'<>#", true, false, true, true));
            }
            if (i < b && str.charAt(i) == '#') {
                this.f1049h = HttpUrl.m1153a(str, i + 1, b, "", true, false, false, false);
            }
            return C0536a.SUCCESS;
        }

        /* renamed from: a */
        private void m1182a(String str, int i, int i2) {
            if (i != i2) {
                char charAt = str.charAt(i);
                if (charAt == '/' || charAt == '\\') {
                    this.f1047f.clear();
                    this.f1047f.add("");
                    i++;
                } else {
                    this.f1047f.set(this.f1047f.size() - 1, "");
                }
                int i3 = i;
                while (i3 < i2) {
                    int a = Util.m642a(str, i3, i2, "/\\");
                    boolean z = a < i2;
                    m1183a(str, i3, a, z, true);
                    if (z) {
                        a++;
                    }
                    i3 = a;
                }
            }
        }

        /* renamed from: a */
        private void m1183a(String str, int i, int i2, boolean z, boolean z2) {
            String a = HttpUrl.m1153a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true);
            if (!m1191f(a)) {
                if (m1193g(a)) {
                    m1188d();
                    return;
                }
                if (this.f1047f.get(this.f1047f.size() - 1).isEmpty()) {
                    this.f1047f.set(this.f1047f.size() - 1, a);
                } else {
                    this.f1047f.add(a);
                }
                if (z) {
                    this.f1047f.add("");
                }
            }
        }

        /* renamed from: f */
        private boolean m1191f(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        /* renamed from: g */
        private boolean m1193g(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        /* renamed from: d */
        private void m1188d() {
            if (!this.f1047f.remove(this.f1047f.size() - 1).isEmpty() || this.f1047f.isEmpty()) {
                this.f1047f.add("");
            } else {
                this.f1047f.set(this.f1047f.size() - 1, "");
            }
        }

        /* renamed from: b */
        private static int m1185b(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char charAt = str.charAt(i);
            if ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z')) {
                return -1;
            }
            int i3 = i + 1;
            while (i3 < i2) {
                char charAt2 = str.charAt(i3);
                if ((charAt2 >= 'a' && charAt2 <= 'z') || ((charAt2 >= 'A' && charAt2 <= 'Z') || ((charAt2 >= '0' && charAt2 <= '9') || charAt2 == '+' || charAt2 == '-' || charAt2 == '.'))) {
                    i3++;
                } else if (charAt2 == ':') {
                    return i3;
                } else {
                    return -1;
                }
            }
            return -1;
        }

        /* renamed from: c */
        private static int m1186c(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
            if (r0 >= r5) goto L_0x000a;
         */
        /* renamed from: d */
        private static int m1187d(String str, int i, int i2) {
            int i3 = i;
            while (i3 < i2) {
                switch (str.charAt(i3)) {
                    case ':':
                        return i3;
                    case '[':
                        do {
                            i3++;
                            break;
                        } while (str.charAt(i3) != ']');
                        break;
                }
                i3++;
            }
            return i2;
        }

        /* renamed from: e */
        private static String m1189e(String str, int i, int i2) {
            InetAddress f;
            String a = HttpUrl.m1154a(str, i, i2, false);
            if (!a.contains(":")) {
                return Util.m645a(a);
            }
            if (!a.startsWith("[") || !a.endsWith("]")) {
                f = m1190f(a, 0, a.length());
            } else {
                f = m1190f(a, 1, a.length() - 1);
            }
            if (f == null) {
                return null;
            }
            byte[] address = f.getAddress();
            if (address.length == 16) {
                return m1181a(address);
            }
            throw new AssertionError();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
            return null;
         */
        /* renamed from: f */
        private static InetAddress m1190f(String str, int i, int i2) {
            byte[] bArr = new byte[16];
            int i3 = i;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                } else if (i6 == bArr.length) {
                    return null;
                } else {
                    if (i3 + 2 > i2 || !str.regionMatches(i3, "::", 0, 2)) {
                        if (i6 != 0) {
                            if (str.regionMatches(i3, ":", 0, 1)) {
                                i3++;
                            } else if (!str.regionMatches(i3, ".", 0, 1)) {
                                return null;
                            } else {
                                if (!m1184a(str, i4, i2, bArr, i6 - 2)) {
                                    return null;
                                }
                                i6 += 2;
                            }
                        }
                    } else if (i5 != -1) {
                        return null;
                    } else {
                        i3 += 2;
                        i5 = i6 + 2;
                        if (i3 == i2) {
                            i6 = i5;
                            break;
                        }
                        i6 = i5;
                    }
                    int i7 = 0;
                    int i8 = i3;
                    while (i8 < i2) {
                        int a = HttpUrl.m1151a(str.charAt(i8));
                        if (a == -1) {
                            break;
                        }
                        i7 = (i7 << 4) + a;
                        i8++;
                    }
                    int i9 = i8 - i3;
                    if (i9 != 0 && i9 <= 4) {
                        int i10 = i6 + 1;
                        bArr[i6] = (byte) ((i7 >>> 8) & 255);
                        i6 = i10 + 1;
                        bArr[i10] = (byte) (i7 & 255);
                        i4 = i3;
                        i3 = i8;
                    }
                }
            }
            if (i6 != bArr.length) {
                if (i5 == -1) {
                    return null;
                }
                System.arraycopy(bArr, i5, bArr, bArr.length - (i6 - i5), i6 - i5);
                Arrays.fill(bArr, i5, (bArr.length - i6) + i5, (byte) 0);
            }
            try {
                return InetAddress.getByAddress(bArr);
            } catch (UnknownHostException e) {
                throw new AssertionError();
            }
        }

        /* renamed from: a */
        private static boolean m1184a(String str, int i, int i2, byte[] bArr, int i3) {
            int i4 = i;
            int i5 = i3;
            while (i4 < i2) {
                if (i5 == bArr.length) {
                    return false;
                }
                if (i5 != i3) {
                    if (str.charAt(i4) != '.') {
                        return false;
                    }
                    i4++;
                }
                int i6 = 0;
                int i7 = i4;
                while (i7 < i2) {
                    char charAt = str.charAt(i7);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    } else if (i6 == 0 && i4 != i7) {
                        return false;
                    } else {
                        i6 = ((i6 * 10) + charAt) - 48;
                        if (i6 > 255) {
                            return false;
                        }
                        i7++;
                    }
                }
                if (i7 - i4 == 0) {
                    return false;
                }
                bArr[i5] = (byte) i6;
                i5++;
                i4 = i7;
            }
            if (i5 != i3 + 4) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        private static String m1181a(byte[] bArr) {
            int i = 0;
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            while (i4 < bArr.length) {
                int i5 = i4;
                while (i5 < 16 && bArr[i5] == 0 && bArr[i5 + 1] == 0) {
                    i5 += 2;
                }
                int i6 = i5 - i4;
                if (i6 > i2) {
                    i2 = i6;
                    i3 = i4;
                }
                i4 = i5 + 2;
            }
            Buffer cVar = new Buffer();
            while (i < bArr.length) {
                if (i == i3) {
                    cVar.mo17677i(58);
                    i += i2;
                    if (i == 16) {
                        cVar.mo17677i(58);
                    }
                } else {
                    if (i > 0) {
                        cVar.mo17677i(58);
                    }
                    cVar.mo17680j((long) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)));
                    i += 2;
                }
            }
            return cVar.mo17687p();
        }

        /* renamed from: g */
        private static int m1192g(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(HttpUrl.m1153a(str, i, i2, "", false, false, false, true));
                if (parseInt <= 0 || parseInt > 65535) {
                    return -1;
                }
                return parseInt;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    HttpUrl(C0535a aVar) {
        String str = null;
        this.f1033a = aVar.f1042a;
        this.f1036e = m1156a(aVar.f1043b, false);
        this.f1037f = m1156a(aVar.f1044c, false);
        this.f1034b = aVar.f1045d;
        this.f1035c = aVar.mo8972a();
        this.f1038g = m1157a(aVar.f1047f, false);
        this.f1039h = aVar.f1048g != null ? m1157a(aVar.f1048g, true) : null;
        this.f1040i = aVar.f1049h != null ? m1156a(aVar.f1049h, false) : str;
        this.f1041j = aVar.toString();
    }

    /* renamed from: a */
    public URI mo8953a() {
        String aVar = mo8970n().mo8976b().toString();
        try {
            return new URI(aVar);
        } catch (URISyntaxException e) {
            try {
                return URI.create(aVar.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception e2) {
                throw new RuntimeException(e);
            }
        }
    }

    /* renamed from: b */
    public String mo8954b() {
        return this.f1033a;
    }

    /* renamed from: c */
    public boolean mo8956c() {
        return this.f1033a.equals("https");
    }

    /* renamed from: d */
    public String mo8958d() {
        if (this.f1036e.isEmpty()) {
            return "";
        }
        int length = this.f1033a.length() + 3;
        return this.f1041j.substring(length, Util.m642a(this.f1041j, length, this.f1041j.length(), ":@"));
    }

    /* renamed from: e */
    public String mo8959e() {
        if (this.f1037f.isEmpty()) {
            return "";
        }
        int indexOf = this.f1041j.indexOf(64);
        return this.f1041j.substring(this.f1041j.indexOf(58, this.f1033a.length() + 3) + 1, indexOf);
    }

    /* renamed from: f */
    public String mo8961f() {
        return this.f1034b;
    }

    /* renamed from: g */
    public int mo8962g() {
        return this.f1035c;
    }

    /* renamed from: a */
    public static int m1152a(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals("https")) {
            return 443;
        }
        return -1;
    }

    /* renamed from: h */
    public String mo8963h() {
        int indexOf = this.f1041j.indexOf(47, this.f1033a.length() + 3);
        return this.f1041j.substring(indexOf, Util.m642a(this.f1041j, indexOf, this.f1041j.length(), "?#"));
    }

    /* renamed from: a */
    static void m1160a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append('/');
            sb.append(list.get(i));
        }
    }

    /* renamed from: i */
    public List<String> mo8965i() {
        int indexOf = this.f1041j.indexOf(47, this.f1033a.length() + 3);
        int a = Util.m642a(this.f1041j, indexOf, this.f1041j.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < a) {
            int i = indexOf + 1;
            indexOf = Util.m641a(this.f1041j, i, a, '/');
            arrayList.add(this.f1041j.substring(i, indexOf));
        }
        return arrayList;
    }

    /* renamed from: j */
    public String mo8966j() {
        if (this.f1039h == null) {
            return null;
        }
        int indexOf = this.f1041j.indexOf(63) + 1;
        return this.f1041j.substring(indexOf, Util.m641a(this.f1041j, indexOf + 1, this.f1041j.length(), '#'));
    }

    /* renamed from: b */
    static void m1163b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    /* renamed from: b */
    static List<String> m1162b(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i, indexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, indexOf));
            }
            i = indexOf + 1;
        }
        return arrayList;
    }

    /* renamed from: k */
    public String mo8967k() {
        if (this.f1039h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        m1163b(sb, this.f1039h);
        return sb.toString();
    }

    /* renamed from: l */
    public String mo8968l() {
        if (this.f1040i == null) {
            return null;
        }
        return this.f1041j.substring(this.f1041j.indexOf(35) + 1);
    }

    /* renamed from: m */
    public String mo8969m() {
        return mo8957d("/...").mo8977b("").mo8978c("").mo8979c().toString();
    }

    /* renamed from: c */
    public HttpUrl mo8955c(String str) {
        C0535a d = mo8957d(str);
        if (d != null) {
            return d.mo8979c();
        }
        return null;
    }

    /* renamed from: n */
    public C0535a mo8970n() {
        C0535a aVar = new C0535a();
        aVar.f1042a = this.f1033a;
        aVar.f1043b = mo8958d();
        aVar.f1044c = mo8959e();
        aVar.f1045d = this.f1034b;
        aVar.f1046e = this.f1035c != m1152a(this.f1033a) ? this.f1035c : -1;
        aVar.f1047f.clear();
        aVar.f1047f.addAll(mo8965i());
        aVar.mo8981e(mo8966j());
        aVar.f1049h = mo8968l();
        return aVar;
    }

    /* renamed from: d */
    public C0535a mo8957d(String str) {
        C0535a aVar = new C0535a();
        if (aVar.mo8973a(this, str) == C0535a.C0536a.SUCCESS) {
            return aVar;
        }
        return null;
    }

    /* renamed from: e */
    public static HttpUrl m1164e(String str) {
        C0535a aVar = new C0535a();
        if (aVar.mo8973a(null, str) == C0535a.C0536a.SUCCESS) {
            return aVar.mo8979c();
        }
        return null;
    }

    public boolean equals(Object obj) {
        return (obj instanceof HttpUrl) && ((HttpUrl) obj).f1041j.equals(this.f1041j);
    }

    public int hashCode() {
        return this.f1041j.hashCode();
    }

    public String toString() {
        return this.f1041j;
    }

    /* renamed from: a */
    static String m1156a(String str, boolean z) {
        return m1154a(str, 0, str.length(), z);
    }

    /* renamed from: a */
    private List<String> m1157a(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            arrayList.add(str != null ? m1156a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* renamed from: a */
    static String m1154a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '%' || (charAt == '+' && z)) {
                Buffer cVar = new Buffer();
                cVar.mo17641a(str, i, i3);
                m1159a(cVar, str, i3, i2, z);
                return cVar.mo17687p();
            }
        }
        return str.substring(i, i2);
    }

    /* renamed from: a */
    static void m1159a(Buffer cVar, String str, int i, int i2, boolean z) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt != 37 || i3 + 2 >= i2) {
                if (codePointAt == 43 && z) {
                    cVar.mo17677i(32);
                }
                cVar.mo17637a(codePointAt);
            } else {
                int a = m1151a(str.charAt(i3 + 1));
                int a2 = m1151a(str.charAt(i3 + 2));
                if (!(a == -1 || a2 == -1)) {
                    cVar.mo17677i((a << 4) + a2);
                    i3 += 2;
                }
                cVar.mo17637a(codePointAt);
            }
            i3 += Character.charCount(codePointAt);
        }
    }

    /* renamed from: a */
    static boolean m1161a(String str, int i, int i2) {
        return i + 2 < i2 && str.charAt(i) == '%' && m1151a(str.charAt(i + 1)) != -1 && m1151a(str.charAt(i + 2)) != -1;
    }

    /* renamed from: a */
    static int m1151a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    /* renamed from: a */
    static String m1153a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || ((codePointAt == 37 && (!z || (z2 && !m1161a(str, i3, i2)))) || (codePointAt == 43 && z3)))) {
                Buffer cVar = new Buffer();
                cVar.mo17641a(str, i, i3);
                m1158a(cVar, str, i3, i2, str2, z, z2, z3, z4);
                return cVar.mo17687p();
            }
            i3 += Character.charCount(codePointAt);
        }
        return str.substring(i, i2);
    }

    /* renamed from: a */
    static void m1158a(Buffer cVar, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        Buffer cVar2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!z || !(codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13)) {
                if (codePointAt == 43 && z3) {
                    cVar.mo17652b(z ? "+" : "%2B");
                } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && (!z || (z2 && !m1161a(str, i, i2)))))) {
                    if (cVar2 == null) {
                        cVar2 = new Buffer();
                    }
                    cVar2.mo17637a(codePointAt);
                    while (!cVar2.mo17664e()) {
                        byte h = cVar2.mo17672h() & 255;
                        cVar.mo17677i(37);
                        cVar.mo17677i((int) f1032d[(h >> 4) & 15]);
                        cVar.mo17677i((int) f1032d[h & 15]);
                    }
                } else {
                    cVar.mo17637a(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    /* renamed from: a */
    static String m1155a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return m1153a(str, 0, str.length(), str2, z, z2, z3, z4);
    }
}
