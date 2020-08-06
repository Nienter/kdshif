package com.snaperfect.style.daguerre.utils;

/* renamed from: com.snaperfect.style.daguerre.utils.k */
class ParserHelper {

    /* renamed from: e */
    private static final double[] f2266e = new double[128];

    /* renamed from: a */
    public int f2267a = 0;

    /* renamed from: b */
    private char f2268b;

    /* renamed from: c */
    private final CharSequence f2269c;

    /* renamed from: d */
    private final int f2270d;

    public ParserHelper(CharSequence charSequence) {
        this.f2269c = charSequence;
        this.f2270d = charSequence.length();
        this.f2268b = charSequence.charAt(this.f2267a);
    }

    /* renamed from: f */
    private char m3104f() {
        if (this.f2267a < this.f2270d) {
            this.f2267a++;
        }
        if (this.f2267a == this.f2270d) {
            return 0;
        }
        return this.f2269c.charAt(this.f2267a);
    }

    /* renamed from: a */
    public void mo17298a() {
        while (this.f2267a < this.f2270d && Character.isWhitespace(this.f2269c.charAt(this.f2267a))) {
            mo17300c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17299b() {
        while (this.f2267a < this.f2270d) {
            switch (this.f2269c.charAt(this.f2267a)) {
                case 9:
                case 10:
                case ' ':
                case ',':
                    mo17300c();
                default:
                    return;
            }
        }
    }

    /* renamed from: c */
    public void mo17300c() {
        this.f2268b = m3104f();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0023, code lost:
        if (r11.f2268b != '.') goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        r11.f2268b = m3104f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        switch(r11.f2268b) {
            case 48: goto L_0x006b;
            case 49: goto L_0x0100;
            case 50: goto L_0x0100;
            case 51: goto L_0x0100;
            case 52: goto L_0x0100;
            case 53: goto L_0x0100;
            case 54: goto L_0x0100;
            case 55: goto L_0x0100;
            case 56: goto L_0x0100;
            case 57: goto L_0x0100;
            default: goto L_0x0030;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        if (r4 != false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        m3103a(r11.f2268b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        r11.f2268b = m3104f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        switch(r11.f2268b) {
            case 46: goto L_0x0046;
            case 48: goto L_0x0039;
            case 49: goto L_0x004b;
            case 50: goto L_0x004b;
            case 51: goto L_0x004b;
            case 52: goto L_0x004b;
            case 53: goto L_0x004b;
            case 54: goto L_0x004b;
            case 55: goto L_0x004b;
            case 56: goto L_0x004b;
            case 57: goto L_0x004b;
            case 69: goto L_0x0046;
            case 101: goto L_0x0046;
            default: goto L_0x0044;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        r3 = 0;
        r4 = true;
        r5 = 0;
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        r3 = 0;
        r4 = 0;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
        if (r4 >= 9) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0050, code lost:
        r4 = r4 + 1;
        r5 = (r5 * 10) + (r11.f2268b - '0');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        r11.f2268b = m3104f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0061, code lost:
        switch(r11.f2268b) {
            case 48: goto L_0x004e;
            case 49: goto L_0x004e;
            case 50: goto L_0x004e;
            case 51: goto L_0x004e;
            case 52: goto L_0x004e;
            case 53: goto L_0x004e;
            case 54: goto L_0x004e;
            case 55: goto L_0x004e;
            case 56: goto L_0x004e;
            case 57: goto L_0x004e;
            default: goto L_0x0064;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0064, code lost:
        r6 = r5;
        r5 = r4;
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0068, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006b, code lost:
        if (r5 != 0) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006d, code lost:
        r11.f2268b = m3104f();
        r3 = r3 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0077, code lost:
        switch(r11.f2268b) {
            case 48: goto L_0x006d;
            case 49: goto L_0x007e;
            case 50: goto L_0x007e;
            case 51: goto L_0x007e;
            case 52: goto L_0x007e;
            case 53: goto L_0x007e;
            case 54: goto L_0x007e;
            case 55: goto L_0x007e;
            case 56: goto L_0x007e;
            case 57: goto L_0x007e;
            default: goto L_0x007a;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007a, code lost:
        if (r4 != false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007e, code lost:
        r4 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0080, code lost:
        if (r4 >= 9) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0082, code lost:
        r4 = r4 + 1;
        r5 = (r5 * 10) + (r11.f2268b - '0');
        r3 = r3 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        r11.f2268b = m3104f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0095, code lost:
        switch(r11.f2268b) {
            case 48: goto L_0x0080;
            case 49: goto L_0x0080;
            case 50: goto L_0x0080;
            case 51: goto L_0x0080;
            case 52: goto L_0x0080;
            case 53: goto L_0x0080;
            case 54: goto L_0x0080;
            case 55: goto L_0x0080;
            case 56: goto L_0x0080;
            case 57: goto L_0x0080;
            default: goto L_0x0098;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0098, code lost:
        r6 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009b, code lost:
        switch(r11.f2268b) {
            case 69: goto L_0x00ab;
            case 101: goto L_0x00ab;
            default: goto L_0x009e;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009e, code lost:
        if (r2 != false) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a0, code lost:
        r1 = -r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a1, code lost:
        r1 = r1 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a2, code lost:
        if (r0 != false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a4, code lost:
        r6 = -r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ab, code lost:
        r11.f2268b = m3104f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b3, code lost:
        switch(r11.f2268b) {
            case 43: goto L_0x00bf;
            case 44: goto L_0x00b6;
            case 45: goto L_0x00be;
            case 46: goto L_0x00b6;
            case 47: goto L_0x00b6;
            case 48: goto L_0x00d2;
            case 49: goto L_0x00d2;
            case 50: goto L_0x00d2;
            case 51: goto L_0x00d2;
            case 52: goto L_0x00d2;
            case 53: goto L_0x00d2;
            case 54: goto L_0x00d2;
            case 55: goto L_0x00d2;
            case 56: goto L_0x00d2;
            case 57: goto L_0x00d2;
            default: goto L_0x00b6;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b6, code lost:
        m3103a(r11.f2268b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00be, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00bf, code lost:
        r11.f2268b = m3104f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c7, code lost:
        switch(r11.f2268b) {
            case 48: goto L_0x00d2;
            case 49: goto L_0x00d2;
            case 50: goto L_0x00d2;
            case 51: goto L_0x00d2;
            case 52: goto L_0x00d2;
            case 53: goto L_0x00d2;
            case 54: goto L_0x00d2;
            case 55: goto L_0x00d2;
            case 56: goto L_0x00d2;
            case 57: goto L_0x00d2;
            default: goto L_0x00ca;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ca, code lost:
        m3103a(r11.f2268b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d4, code lost:
        switch(r11.f2268b) {
            case 48: goto L_0x00d8;
            case 49: goto L_0x00fe;
            case 50: goto L_0x00fe;
            case 51: goto L_0x00fe;
            case 52: goto L_0x00fe;
            case 53: goto L_0x00fe;
            case 54: goto L_0x00fe;
            case 55: goto L_0x00fe;
            case 56: goto L_0x00fe;
            case 57: goto L_0x00fe;
            default: goto L_0x00d7;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d8, code lost:
        r11.f2268b = m3104f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e0, code lost:
        switch(r11.f2268b) {
            case 48: goto L_0x00d8;
            case 49: goto L_0x00e4;
            case 50: goto L_0x00e4;
            case 51: goto L_0x00e4;
            case 52: goto L_0x00e4;
            case 53: goto L_0x00e4;
            case 54: goto L_0x00e4;
            case 55: goto L_0x00e4;
            case 56: goto L_0x00e4;
            case 57: goto L_0x00e4;
            default: goto L_0x00e3;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e4, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e6, code lost:
        if (r1 >= 3) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e8, code lost:
        r1 = r1 + 1;
        r4 = (r4 * 10) + (r11.f2268b - '0');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f1, code lost:
        r11.f2268b = m3104f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f9, code lost:
        switch(r11.f2268b) {
            case 48: goto L_0x00e5;
            case 49: goto L_0x00e5;
            case 50: goto L_0x00e5;
            case 51: goto L_0x00e5;
            case 52: goto L_0x00e5;
            case 53: goto L_0x00e5;
            case 54: goto L_0x00e5;
            case 55: goto L_0x00e5;
            case 56: goto L_0x00e5;
            case 57: goto L_0x00e5;
            default: goto L_0x00fc;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00fc, code lost:
        r1 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00fe, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0100, code lost:
        r4 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        return 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        return 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        return 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        return m3102a(r6, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        return 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        return 0.0f;
     */
    /* renamed from: d */
    public float mo17301d() {
        boolean z;
        boolean z2 = true;
        int i = 0;
        switch (this.f2268b) {
            case '+':
                z = true;
                break;
            case '-':
                z = false;
                break;
            default:
                z = true;
                break;
        }
        this.f2268b = m3104f();
        switch (this.f2268b) {
            case '.':
                int i2 = 0;
                boolean z3 = false;
                int i3 = 0;
                int i4 = 0;
                break;
            case '0':
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                break;
            default:
                return Float.NaN;
        }
    }

    /* renamed from: a */
    private void m3103a(char c) {
        throw new RuntimeException("Unexpected char '" + c + "'.");
    }

    /* renamed from: a */
    private static float m3102a(int i, int i2) {
        if (i2 < -125 || i == 0) {
            return 0.0f;
        }
        if (i2 >= 128) {
            return i > 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        if (i2 == 0) {
            return (float) i;
        }
        if (i >= 67108864) {
            i++;
        }
        return (float) (i2 > 0 ? ((double) i) * f2266e[i2] : ((double) i) / f2266e[-i2]);
    }

    static {
        for (int i = 0; i < f2266e.length; i++) {
            f2266e[i] = Math.pow(10.0d, (double) i);
        }
    }

    /* renamed from: e */
    public float mo17302e() {
        mo17298a();
        float d = mo17301d();
        mo17299b();
        return d;
    }
}
