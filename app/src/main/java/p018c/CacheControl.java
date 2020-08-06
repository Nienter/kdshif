package p018c;

import android.support.p004v7.widget.ActivityChooserView;
import java.util.concurrent.TimeUnit;
import p018c.p019a.p022c.HttpHeaders;

/* renamed from: c.d */
public final class CacheControl {

    /* renamed from: a */
    public static final CacheControl f840a = new C0526a().mo8875a().mo8879d();

    /* renamed from: b */
    public static final CacheControl f841b = new C0526a().mo8878c().mo8876a(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, TimeUnit.SECONDS).mo8879d();

    /* renamed from: c */
    String f842c;

    /* renamed from: d */
    private final boolean f843d;

    /* renamed from: e */
    private final boolean f844e;

    /* renamed from: f */
    private final int f845f;

    /* renamed from: g */
    private final int f846g;

    /* renamed from: h */
    private final boolean f847h;

    /* renamed from: i */
    private final boolean f848i;

    /* renamed from: j */
    private final boolean f849j;

    /* renamed from: k */
    private final int f850k;

    /* renamed from: l */
    private final int f851l;

    /* renamed from: m */
    private final boolean f852m;

    /* renamed from: n */
    private final boolean f853n;

    /* renamed from: c.d$a */
    /* compiled from: CacheControl */
    public static final class C0526a {

        /* renamed from: a */
        boolean f854a;

        /* renamed from: b */
        boolean f855b;

        /* renamed from: c */
        int f856c = -1;

        /* renamed from: d */
        int f857d = -1;

        /* renamed from: e */
        int f858e = -1;

        /* renamed from: f */
        boolean f859f;

        /* renamed from: g */
        boolean f860g;

        /* renamed from: a */
        public C0526a mo8875a() {
            this.f854a = true;
            return this;
        }

        /* renamed from: b */
        public C0526a mo8877b() {
            this.f855b = true;
            return this;
        }

        /* renamed from: a */
        public C0526a mo8876a(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("maxStale < 0: " + i);
            }
            long seconds = timeUnit.toSeconds((long) i);
            this.f857d = seconds > 2147483647L ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) seconds;
            return this;
        }

        /* renamed from: c */
        public C0526a mo8878c() {
            this.f859f = true;
            return this;
        }

        /* renamed from: d */
        public CacheControl mo8879d() {
            return new CacheControl(this);
        }
    }

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.f843d = z;
        this.f844e = z2;
        this.f845f = i;
        this.f846g = i2;
        this.f847h = z3;
        this.f848i = z4;
        this.f849j = z5;
        this.f850k = i3;
        this.f851l = i4;
        this.f852m = z6;
        this.f853n = z7;
        this.f842c = str;
    }

    CacheControl(C0526a aVar) {
        this.f843d = aVar.f854a;
        this.f844e = aVar.f855b;
        this.f845f = aVar.f856c;
        this.f846g = -1;
        this.f847h = false;
        this.f848i = false;
        this.f849j = false;
        this.f850k = aVar.f857d;
        this.f851l = aVar.f858e;
        this.f852m = aVar.f859f;
        this.f853n = aVar.f860g;
    }

    /* renamed from: a */
    public boolean mo8865a() {
        return this.f843d;
    }

    /* renamed from: b */
    public boolean mo8866b() {
        return this.f844e;
    }

    /* renamed from: c */
    public int mo8867c() {
        return this.f845f;
    }

    /* renamed from: d */
    public boolean mo8868d() {
        return this.f847h;
    }

    /* renamed from: e */
    public boolean mo8869e() {
        return this.f848i;
    }

    /* renamed from: f */
    public boolean mo8870f() {
        return this.f849j;
    }

    /* renamed from: g */
    public int mo8871g() {
        return this.f850k;
    }

    /* renamed from: h */
    public int mo8872h() {
        return this.f851l;
    }

    /* renamed from: i */
    public boolean mo8873i() {
        return this.f852m;
    }

    /* renamed from: a */
    public static CacheControl m1053a(Headers qVar) {
        boolean z;
        String str;
        String str2;
        boolean z2 = false;
        boolean z3 = false;
        int i = -1;
        int i2 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i3 = -1;
        int i4 = -1;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = true;
        int a = qVar.mo8937a();
        int i5 = 0;
        String str3 = null;
        while (true) {
            z = z2;
            if (i5 >= a) {
                break;
            }
            String a2 = qVar.mo8938a(i5);
            String b = qVar.mo8941b(i5);
            if (a2.equalsIgnoreCase("Cache-Control")) {
                if (str3 != null) {
                    z9 = false;
                } else {
                    str3 = b;
                }
            } else if (a2.equalsIgnoreCase("Pragma")) {
                z9 = false;
            } else {
                z2 = z;
                i5++;
            }
            z2 = z;
            int i6 = 0;
            while (i6 < b.length()) {
                int a3 = HttpHeaders.m678a(b, i6, "=,;");
                String trim = b.substring(i6, a3).trim();
                if (a3 == b.length() || b.charAt(a3) == ',' || b.charAt(a3) == ';') {
                    i6 = a3 + 1;
                    str2 = null;
                } else {
                    int a4 = HttpHeaders.m677a(b, a3 + 1);
                    if (a4 >= b.length() || b.charAt(a4) != '\"') {
                        int a5 = HttpHeaders.m678a(b, a4, ",;");
                        String trim2 = b.substring(a4, a5).trim();
                        i6 = a5;
                        str2 = trim2;
                    } else {
                        int i7 = a4 + 1;
                        int a6 = HttpHeaders.m678a(b, i7, "\"");
                        String substring = b.substring(i7, a6);
                        i6 = a6 + 1;
                        str2 = substring;
                    }
                }
                if ("no-cache".equalsIgnoreCase(trim)) {
                    z2 = true;
                } else if ("no-store".equalsIgnoreCase(trim)) {
                    z3 = true;
                } else if ("max-age".equalsIgnoreCase(trim)) {
                    i = HttpHeaders.m685b(str2, -1);
                } else if ("s-maxage".equalsIgnoreCase(trim)) {
                    i2 = HttpHeaders.m685b(str2, -1);
                } else if ("private".equalsIgnoreCase(trim)) {
                    z4 = true;
                } else if ("public".equalsIgnoreCase(trim)) {
                    z5 = true;
                } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                    z6 = true;
                } else if ("max-stale".equalsIgnoreCase(trim)) {
                    i3 = HttpHeaders.m685b(str2, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                } else if ("min-fresh".equalsIgnoreCase(trim)) {
                    i4 = HttpHeaders.m685b(str2, -1);
                } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    z7 = true;
                } else if ("no-transform".equalsIgnoreCase(trim)) {
                    z8 = true;
                }
            }
            i5++;
        }
        if (!z9) {
            str = null;
        } else {
            str = str3;
        }
        return new CacheControl(z, z3, i, i2, z4, z5, z6, i3, i4, z7, z8, str);
    }

    public String toString() {
        String str = this.f842c;
        if (str != null) {
            return str;
        }
        String j = m1054j();
        this.f842c = j;
        return j;
    }

    /* renamed from: j */
    private String m1054j() {
        StringBuilder sb = new StringBuilder();
        if (this.f843d) {
            sb.append("no-cache, ");
        }
        if (this.f844e) {
            sb.append("no-store, ");
        }
        if (this.f845f != -1) {
            sb.append("max-age=").append(this.f845f).append(", ");
        }
        if (this.f846g != -1) {
            sb.append("s-maxage=").append(this.f846g).append(", ");
        }
        if (this.f847h) {
            sb.append("private, ");
        }
        if (this.f848i) {
            sb.append("public, ");
        }
        if (this.f849j) {
            sb.append("must-revalidate, ");
        }
        if (this.f850k != -1) {
            sb.append("max-stale=").append(this.f850k).append(", ");
        }
        if (this.f851l != -1) {
            sb.append("min-fresh=").append(this.f851l).append(", ");
        }
        if (this.f852m) {
            sb.append("only-if-cached, ");
        }
        if (this.f853n) {
            sb.append("no-transform, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
