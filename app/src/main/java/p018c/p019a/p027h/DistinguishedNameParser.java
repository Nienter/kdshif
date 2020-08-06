package p018c.p019a.p027h;

import javax.security.auth.x500.X500Principal;

/* renamed from: c.a.h.c */
final class DistinguishedNameParser {

    /* renamed from: a */
    private final String f787a;

    /* renamed from: b */
    private final int f788b = this.f787a.length();

    /* renamed from: c */
    private int f789c;

    /* renamed from: d */
    private int f790d;

    /* renamed from: e */
    private int f791e;

    /* renamed from: f */
    private int f792f;

    /* renamed from: g */
    private char[] f793g;

    public DistinguishedNameParser(X500Principal x500Principal) {
        this.f787a = x500Principal.getName("RFC2253");
    }

    /* renamed from: a */
    private String m998a() {
        while (this.f789c < this.f788b && this.f793g[this.f789c] == ' ') {
            this.f789c++;
        }
        if (this.f789c == this.f788b) {
            return null;
        }
        this.f790d = this.f789c;
        this.f789c++;
        while (this.f789c < this.f788b && this.f793g[this.f789c] != '=' && this.f793g[this.f789c] != ' ') {
            this.f789c++;
        }
        if (this.f789c >= this.f788b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f787a);
        }
        this.f791e = this.f789c;
        if (this.f793g[this.f789c] == ' ') {
            while (this.f789c < this.f788b && this.f793g[this.f789c] != '=' && this.f793g[this.f789c] == ' ') {
                this.f789c++;
            }
            if (this.f793g[this.f789c] != '=' || this.f789c == this.f788b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f787a);
            }
        }
        this.f789c++;
        while (this.f789c < this.f788b && this.f793g[this.f789c] == ' ') {
            this.f789c++;
        }
        if (this.f791e - this.f790d > 4 && this.f793g[this.f790d + 3] == '.' && ((this.f793g[this.f790d] == 'O' || this.f793g[this.f790d] == 'o') && ((this.f793g[this.f790d + 1] == 'I' || this.f793g[this.f790d + 1] == 'i') && (this.f793g[this.f790d + 2] == 'D' || this.f793g[this.f790d + 2] == 'd')))) {
            this.f790d += 4;
        }
        return new String(this.f793g, this.f790d, this.f791e - this.f790d);
    }

    /* renamed from: b */
    private String m999b() {
        this.f789c++;
        this.f790d = this.f789c;
        this.f791e = this.f790d;
        while (this.f789c != this.f788b) {
            if (this.f793g[this.f789c] == '\"') {
                this.f789c++;
                while (this.f789c < this.f788b && this.f793g[this.f789c] == ' ') {
                    this.f789c++;
                }
                return new String(this.f793g, this.f790d, this.f791e - this.f790d);
            }
            if (this.f793g[this.f789c] == '\\') {
                this.f793g[this.f791e] = m1002e();
            } else {
                this.f793g[this.f791e] = this.f793g[this.f789c];
            }
            this.f789c++;
            this.f791e++;
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f787a);
    }

    /* renamed from: c */
    private String m1000c() {
        if (this.f789c + 4 >= this.f788b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f787a);
        }
        this.f790d = this.f789c;
        this.f789c++;
        while (true) {
            if (this.f789c == this.f788b || this.f793g[this.f789c] == '+' || this.f793g[this.f789c] == ',' || this.f793g[this.f789c] == ';') {
                this.f791e = this.f789c;
            } else if (this.f793g[this.f789c] == ' ') {
                this.f791e = this.f789c;
                this.f789c++;
                while (this.f789c < this.f788b && this.f793g[this.f789c] == ' ') {
                    this.f789c++;
                }
            } else {
                if (this.f793g[this.f789c] >= 'A' && this.f793g[this.f789c] <= 'F') {
                    char[] cArr = this.f793g;
                    int i = this.f789c;
                    cArr[i] = (char) (cArr[i] + ' ');
                }
                this.f789c++;
            }
        }
        this.f791e = this.f789c;
        int i2 = this.f791e - this.f790d;
        if (i2 < 5 || (i2 & 1) == 0) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f787a);
        }
        byte[] bArr = new byte[(i2 / 2)];
        int i3 = this.f790d + 1;
        for (int i4 = 0; i4 < bArr.length; i4++) {
            bArr[i4] = (byte) m997a(i3);
            i3 += 2;
        }
        return new String(this.f793g, this.f790d, i2);
    }

    /* renamed from: d */
    private String m1001d() {
        this.f790d = this.f789c;
        this.f791e = this.f789c;
        while (this.f789c < this.f788b) {
            switch (this.f793g[this.f789c]) {
                case ' ':
                    this.f792f = this.f791e;
                    this.f789c++;
                    char[] cArr = this.f793g;
                    int i = this.f791e;
                    this.f791e = i + 1;
                    cArr[i] = ' ';
                    while (this.f789c < this.f788b && this.f793g[this.f789c] == ' ') {
                        char[] cArr2 = this.f793g;
                        int i2 = this.f791e;
                        this.f791e = i2 + 1;
                        cArr2[i2] = ' ';
                        this.f789c++;
                    }
                    if (this.f789c != this.f788b && this.f793g[this.f789c] != ',' && this.f793g[this.f789c] != '+' && this.f793g[this.f789c] != ';') {
                        break;
                    } else {
                        return new String(this.f793g, this.f790d, this.f792f - this.f790d);
                    }
                    break;
                case '+':
                case ',':
                case ';':
                    return new String(this.f793g, this.f790d, this.f791e - this.f790d);
                case '\\':
                    char[] cArr3 = this.f793g;
                    int i3 = this.f791e;
                    this.f791e = i3 + 1;
                    cArr3[i3] = m1002e();
                    this.f789c++;
                    break;
                default:
                    char[] cArr4 = this.f793g;
                    int i4 = this.f791e;
                    this.f791e = i4 + 1;
                    cArr4[i4] = this.f793g[this.f789c];
                    this.f789c++;
                    break;
            }
        }
        return new String(this.f793g, this.f790d, this.f791e - this.f790d);
    }

    /* renamed from: e */
    private char m1002e() {
        this.f789c++;
        if (this.f789c == this.f788b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f787a);
        }
        switch (this.f793g[this.f789c]) {
            case ' ':
            case '\"':
            case '#':
            case '%':
            case '*':
            case '+':
            case ',':
            case ';':
            case '<':
            case '=':
            case '>':
            case '\\':
            case '_':
                return this.f793g[this.f789c];
            default:
                return m1003f();
        }
    }

    /* renamed from: f */
    private char m1003f() {
        int i;
        int i2;
        int a = m997a(this.f789c);
        this.f789c++;
        if (a < 128) {
            return (char) a;
        }
        if (a < 192 || a > 247) {
            return '?';
        }
        if (a <= 223) {
            i = 1;
            i2 = a & 31;
        } else if (a <= 239) {
            i = 2;
            i2 = a & 15;
        } else {
            i = 3;
            i2 = a & 7;
        }
        int i3 = i2;
        for (int i4 = 0; i4 < i; i4++) {
            this.f789c++;
            if (this.f789c == this.f788b || this.f793g[this.f789c] != '\\') {
                return '?';
            }
            this.f789c++;
            int a2 = m997a(this.f789c);
            this.f789c++;
            if ((a2 & 192) != 128) {
                return '?';
            }
            i3 = (i3 << 6) + (a2 & 63);
        }
        return (char) i3;
    }

    /* renamed from: a */
    private int m997a(int i) {
        int i2;
        int i3;
        if (i + 1 >= this.f788b) {
            throw new IllegalStateException("Malformed DN: " + this.f787a);
        }
        char c = this.f793g[i];
        if (c >= '0' && c <= '9') {
            i2 = c - '0';
        } else if (c >= 'a' && c <= 'f') {
            i2 = c - 'W';
        } else if (c < 'A' || c > 'F') {
            throw new IllegalStateException("Malformed DN: " + this.f787a);
        } else {
            i2 = c - '7';
        }
        char c2 = this.f793g[i + 1];
        if (c2 >= '0' && c2 <= '9') {
            i3 = c2 - '0';
        } else if (c2 >= 'a' && c2 <= 'f') {
            i3 = c2 - 'W';
        } else if (c2 < 'A' || c2 > 'F') {
            throw new IllegalStateException("Malformed DN: " + this.f787a);
        } else {
            i3 = c2 - '7';
        }
        return (i2 << 4) + i3;
    }

    /* renamed from: a */
    public String mo8836a(String str) {
        this.f789c = 0;
        this.f790d = 0;
        this.f791e = 0;
        this.f792f = 0;
        this.f793g = this.f787a.toCharArray();
        String a = m998a();
        if (a == null) {
            return null;
        }
        do {
            String str2 = "";
            if (this.f789c == this.f788b) {
                return null;
            }
            switch (this.f793g[this.f789c]) {
                case '\"':
                    str2 = m999b();
                    break;
                case '#':
                    str2 = m1000c();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    str2 = m1001d();
                    break;
            }
            if (str.equalsIgnoreCase(a)) {
                return str2;
            }
            if (this.f789c >= this.f788b) {
                return null;
            }
            if (this.f793g[this.f789c] == ',' || this.f793g[this.f789c] == ';' || this.f793g[this.f789c] == '+') {
                this.f789c++;
                a = m998a();
            } else {
                throw new IllegalStateException("Malformed DN: " + this.f787a);
            }
        } while (a != null);
        throw new IllegalStateException("Malformed DN: " + this.f787a);
    }
}
