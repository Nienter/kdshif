package p018c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p018c.p019a.Util;
import p018c.p019a.p022c.HttpDate;

/* renamed from: c.l */
public final class Cookie {

    /* renamed from: a */
    private static final Pattern f1004a = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* renamed from: b */
    private static final Pattern f1005b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* renamed from: c */
    private static final Pattern f1006c = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* renamed from: d */
    private static final Pattern f1007d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* renamed from: e */
    private final String f1008e;

    /* renamed from: f */
    private final String f1009f;

    /* renamed from: g */
    private final long f1010g;

    /* renamed from: h */
    private final String f1011h;

    /* renamed from: i */
    private final String f1012i;

    /* renamed from: j */
    private final boolean f1013j;

    /* renamed from: k */
    private final boolean f1014k;

    /* renamed from: l */
    private final boolean f1015l;

    /* renamed from: m */
    private final boolean f1016m;

    private Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f1008e = str;
        this.f1009f = str2;
        this.f1010g = j;
        this.f1011h = str3;
        this.f1012i = str4;
        this.f1013j = z;
        this.f1014k = z2;
        this.f1016m = z3;
        this.f1015l = z4;
    }

    /* renamed from: a */
    public String mo8917a() {
        return this.f1008e;
    }

    /* renamed from: b */
    public String mo8919b() {
        return this.f1009f;
    }

    /* renamed from: b */
    private static boolean m1112b(HttpUrl rVar, String str) {
        String f = rVar.mo8961f();
        if (f.equals(str)) {
            return true;
        }
        if (!f.endsWith(str) || f.charAt((f.length() - str.length()) - 1) != '.' || Util.m663c(f)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static Cookie m1109a(HttpUrl rVar, String str) {
        return m1108a(System.currentTimeMillis(), rVar, str);
    }

    /* renamed from: a */
    static Cookie m1108a(long j, HttpUrl rVar, String str) {
        long j2;
        String str2;
        String str3;
        int length = str.length();
        int a = Util.m641a(str, 0, length, ';');
        int a2 = Util.m641a(str, 0, a, '=');
        if (a2 == a) {
            return null;
        }
        String c = Util.m662c(str, 0, a2);
        if (c.isEmpty() || Util.m659b(c) != -1) {
            return null;
        }
        String c2 = Util.m662c(str, a2 + 1, a);
        if (Util.m659b(c2) != -1) {
            return null;
        }
        long j3 = 253402300799999L;
        long j4 = -1;
        String str4 = null;
        String str5 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        int i = a + 1;
        while (i < length) {
            int a3 = Util.m641a(str, i, length, ';');
            int a4 = Util.m641a(str, i, a3, '=');
            String c3 = Util.m662c(str, i, a4);
            String c4 = a4 < a3 ? Util.m662c(str, a4 + 1, a3) : "";
            if (c3.equalsIgnoreCase("expires")) {
                try {
                    j3 = m1107a(c4, 0, c4.length());
                    z4 = true;
                    str3 = str4;
                } catch (IllegalArgumentException e) {
                    str3 = str4;
                }
            } else if (c3.equalsIgnoreCase("max-age")) {
                try {
                    j4 = m1106a(c4);
                    z4 = true;
                    str3 = str4;
                } catch (NumberFormatException e2) {
                    str3 = str4;
                }
            } else if (c3.equalsIgnoreCase("domain")) {
                try {
                    str3 = m1111b(c4);
                    z3 = false;
                } catch (IllegalArgumentException e3) {
                    str3 = str4;
                }
            } else if (c3.equalsIgnoreCase("path")) {
                str5 = c4;
                str3 = str4;
            } else if (c3.equalsIgnoreCase("secure")) {
                z = true;
                str3 = str4;
            } else if (c3.equalsIgnoreCase("httponly")) {
                z2 = true;
                str3 = str4;
            } else {
                str3 = str4;
            }
            String str6 = str3;
            i = a3 + 1;
            j3 = j3;
            str4 = str6;
        }
        if (j4 == Long.MIN_VALUE) {
            j2 = Long.MIN_VALUE;
        } else if (j4 != -1) {
            j2 = (j4 <= 9223372036854775L ? j4 * 1000 : Long.MAX_VALUE) + j;
            if (j2 < j || j2 > 253402300799999L) {
                j2 = 253402300799999L;
            }
        } else {
            j2 = j3;
        }
        if (str4 == null) {
            str4 = rVar.mo8961f();
        } else if (!m1112b(rVar, str4)) {
            return null;
        }
        if (str5 == null || !str5.startsWith("/")) {
            String h = rVar.mo8963h();
            int lastIndexOf = h.lastIndexOf(47);
            str2 = lastIndexOf != 0 ? h.substring(0, lastIndexOf) : "/";
        } else {
            str2 = str5;
        }
        return new Cookie(c, c2, j2, str4, str2, z, z2, z3, z4);
    }

    /* renamed from: a */
    private static long m1107a(String str, int i, int i2) {
        int a = m1105a(str, i, i2, false);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        Matcher matcher = f1007d.matcher(str);
        while (a < i2) {
            int a2 = m1105a(str, a + 1, i2, true);
            matcher.region(a, a2);
            if (i3 == -1 && matcher.usePattern(f1007d).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
                i4 = Integer.parseInt(matcher.group(2));
                i5 = Integer.parseInt(matcher.group(3));
            } else if (i6 == -1 && matcher.usePattern(f1006c).matches()) {
                i6 = Integer.parseInt(matcher.group(1));
            } else if (i7 == -1 && matcher.usePattern(f1005b).matches()) {
                i7 = f1005b.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i8 == -1 && matcher.usePattern(f1004a).matches()) {
                i8 = Integer.parseInt(matcher.group(1));
            }
            a = m1105a(str, a2 + 1, i2, false);
        }
        if (i8 >= 70 && i8 <= 99) {
            i8 += 1900;
        }
        if (i8 >= 0 && i8 <= 69) {
            i8 += 2000;
        }
        if (i8 < 1601) {
            throw new IllegalArgumentException();
        } else if (i7 == -1) {
            throw new IllegalArgumentException();
        } else if (i6 < 1 || i6 > 31) {
            throw new IllegalArgumentException();
        } else if (i3 < 0 || i3 > 23) {
            throw new IllegalArgumentException();
        } else if (i4 < 0 || i4 > 59) {
            throw new IllegalArgumentException();
        } else if (i5 < 0 || i5 > 59) {
            throw new IllegalArgumentException();
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.f531f);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i8);
            gregorianCalendar.set(2, i7 - 1);
            gregorianCalendar.set(5, i6);
            gregorianCalendar.set(11, i3);
            gregorianCalendar.set(12, i4);
            gregorianCalendar.set(13, i5);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }
    }

    /* renamed from: a */
    private static int m1105a(String str, int i, int i2, boolean z) {
        boolean z2;
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            boolean z3 = (charAt < ' ' && charAt != 9) || charAt >= 127 || (charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'));
            if (!z) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z3 == z2) {
                return i3;
            }
        }
        return i2;
    }

    /* renamed from: a */
    private static long m1106a(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            if (!str.matches("-?\\d+")) {
                throw e;
            } else if (!str.startsWith("-")) {
                return Long.MAX_VALUE;
            } else {
                return Long.MIN_VALUE;
            }
        }
    }

    /* renamed from: b */
    private static String m1111b(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String a = Util.m645a(str);
        if (a != null) {
            return a;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    public static List<Cookie> m1110a(HttpUrl rVar, Headers qVar) {
        ArrayList arrayList;
        List<String> b = qVar.mo8942b("Set-Cookie");
        ArrayList arrayList2 = null;
        int size = b.size();
        for (int i = 0; i < size; i++) {
            Cookie a = m1109a(rVar, b.get(i));
            if (a != null) {
                if (arrayList2 == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = arrayList2;
                }
                arrayList.add(a);
                arrayList2 = arrayList;
            }
        }
        if (arrayList2 != null) {
            return Collections.unmodifiableList(arrayList2);
        }
        return Collections.emptyList();
    }

    public String toString() {
        return mo8918a(false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo8918a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1008e);
        sb.append('=');
        sb.append(this.f1009f);
        if (this.f1015l) {
            if (this.f1010g == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=").append(HttpDate.m674a(new Date(this.f1010g)));
            }
        }
        if (!this.f1016m) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.f1011h);
        }
        sb.append("; path=").append(this.f1012i);
        if (this.f1013j) {
            sb.append("; secure");
        }
        if (this.f1014k) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie lVar = (Cookie) obj;
        if (lVar.f1008e.equals(this.f1008e) && lVar.f1009f.equals(this.f1009f) && lVar.f1011h.equals(this.f1011h) && lVar.f1012i.equals(this.f1012i) && lVar.f1010g == this.f1010g && lVar.f1013j == this.f1013j && lVar.f1014k == this.f1014k && lVar.f1015l == this.f1015l && lVar.f1016m == this.f1016m) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int hashCode = (((((((((this.f1008e.hashCode() + 527) * 31) + this.f1009f.hashCode()) * 31) + this.f1011h.hashCode()) * 31) + this.f1012i.hashCode()) * 31) + ((int) (this.f1010g ^ (this.f1010g >>> 32)))) * 31;
        if (this.f1013j) {
            i = 0;
        } else {
            i = 1;
        }
        int i5 = (i + hashCode) * 31;
        if (this.f1014k) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        int i6 = (i2 + i5) * 31;
        if (this.f1015l) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        int i7 = (i3 + i6) * 31;
        if (!this.f1016m) {
            i4 = 1;
        }
        return i7 + i4;
    }
}
