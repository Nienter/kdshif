package p018c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p018c.p019a.Util;

/* renamed from: c.q */
public final class Headers {

    /* renamed from: a */
    private final String[] f1030a;

    /* renamed from: c.q$a */
    /* compiled from: Headers */
    public static final class C0534a {

        /* renamed from: a */
        final List<String> f1031a = new ArrayList(20);

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C0534a mo8946a(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                return mo8950b(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(":")) {
                return mo8950b("", str.substring(1));
            }
            return mo8950b("", str);
        }

        /* renamed from: a */
        public C0534a mo8947a(String str, String str2) {
            m1143d(str, str2);
            return mo8950b(str, str2);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public C0534a mo8950b(String str, String str2) {
            this.f1031a.add(str);
            this.f1031a.add(str2.trim());
            return this;
        }

        /* renamed from: b */
        public C0534a mo8949b(String str) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.f1031a.size()) {
                    return this;
                }
                if (str.equalsIgnoreCase(this.f1031a.get(i2))) {
                    this.f1031a.remove(i2);
                    this.f1031a.remove(i2);
                    i2 -= 2;
                }
                i = i2 + 2;
            }
        }

        /* renamed from: c */
        public C0534a mo8951c(String str, String str2) {
            m1143d(str, str2);
            mo8949b(str);
            mo8950b(str, str2);
            return this;
        }

        /* renamed from: d */
        private void m1143d(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            } else {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt <= ' ' || charAt >= 127) {
                        throw new IllegalArgumentException(Util.m646a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                    }
                }
                if (str2 == null) {
                    throw new NullPointerException("value == null");
                }
                int length2 = str2.length();
                int i2 = 0;
                while (i2 < length2) {
                    char charAt2 = str2.charAt(i2);
                    if ((charAt2 > 31 || charAt2 == 9) && charAt2 < 127) {
                        i2++;
                    } else {
                        throw new IllegalArgumentException(Util.m646a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                    }
                }
            }
        }

        /* renamed from: c */
        public String mo8952c(String str) {
            for (int size = this.f1031a.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase(this.f1031a.get(size))) {
                    return this.f1031a.get(size + 1);
                }
            }
            return null;
        }

        /* renamed from: a */
        public Headers mo8948a() {
            return new Headers(this);
        }
    }

    Headers(C0534a aVar) {
        this.f1030a = (String[]) aVar.f1031a.toArray(new String[aVar.f1031a.size()]);
    }

    /* renamed from: a */
    public String mo8939a(String str) {
        return m1136a(this.f1030a, str);
    }

    /* renamed from: a */
    public int mo8937a() {
        return this.f1030a.length / 2;
    }

    /* renamed from: a */
    public String mo8938a(int i) {
        return this.f1030a[i * 2];
    }

    /* renamed from: b */
    public String mo8941b(int i) {
        return this.f1030a[(i * 2) + 1];
    }

    /* renamed from: b */
    public List<String> mo8942b(String str) {
        int a = mo8937a();
        ArrayList arrayList = null;
        for (int i = 0; i < a; i++) {
            if (str.equalsIgnoreCase(mo8938a(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(mo8941b(i));
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    /* renamed from: b */
    public C0534a mo8940b() {
        C0534a aVar = new C0534a();
        Collections.addAll(aVar.f1031a, this.f1030a);
        return aVar;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Headers) && Arrays.equals(((Headers) obj).f1030a, this.f1030a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f1030a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int a = mo8937a();
        for (int i = 0; i < a; i++) {
            sb.append(mo8938a(i)).append(": ").append(mo8941b(i)).append("\n");
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static String m1136a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }
}
