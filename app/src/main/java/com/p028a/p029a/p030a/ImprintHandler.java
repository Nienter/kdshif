package com.p028a.p029a.p030a;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.a.a.a.g */
public class ImprintHandler {

    /* renamed from: a */
    private static final byte[] f1594a = "pbl0".getBytes();

    /* renamed from: e */
    private static ImprintHandler f1595e;

    /* renamed from: b */
    private OnImprintChangedListener f1596b;

    /* renamed from: c */
    private C0639a f1597c = new C0639a();

    /* renamed from: d */
    private Imprint f1598d = null;

    /* renamed from: f */
    private Context f1599f;

    /* renamed from: com.a.a.a.g$a */
    /* compiled from: ImprintHandler */
    public static class C0639a {

        /* renamed from: a */
        private int f1600a = -1;

        /* renamed from: b */
        private int f1601b = -1;

        /* renamed from: c */
        private int f1602c = -1;

        /* renamed from: d */
        private int f1603d = -1;

        /* renamed from: e */
        private int f1604e = -1;

        /* renamed from: f */
        private String f1605f = null;

        /* renamed from: g */
        private int f1606g = -1;

        /* renamed from: h */
        private String f1607h = null;

        /* renamed from: i */
        private int f1608i = -1;

        /* renamed from: j */
        private int f1609j = -1;

        /* renamed from: k */
        private String f1610k = null;

        /* renamed from: l */
        private String f1611l = null;

        /* renamed from: m */
        private String f1612m = null;

        /* renamed from: n */
        private String f1613n = null;

        /* renamed from: o */
        private String f1614o = null;

        C0639a() {
        }

        /* renamed from: a */
        public void mo9590a(Imprint amVar) {
            if (amVar != null) {
                this.f1600a = m2296a(amVar, "defcon");
                this.f1601b = m2296a(amVar, "latent");
                this.f1602c = m2296a(amVar, "codex");
                this.f1603d = m2296a(amVar, "report_policy");
                this.f1604e = m2296a(amVar, "report_interval");
                this.f1605f = m2297b(amVar, "client_test");
                this.f1606g = m2296a(amVar, "test_report_interval");
                this.f1607h = m2297b(amVar, "umid");
                this.f1608i = m2296a(amVar, "integrated_test");
                this.f1609j = m2296a(amVar, "latent_hours");
                this.f1610k = m2297b(amVar, "country");
                this.f1611l = m2297b(amVar, "domain_p");
                this.f1612m = m2297b(amVar, "domain_s");
                this.f1613n = m2297b(amVar, "initial_view_time");
                this.f1614o = m2297b(amVar, "track_list");
            }
        }

        /* renamed from: a */
        public String mo9589a(String str) {
            if (this.f1614o != null) {
                return this.f1614o;
            }
            return str;
        }

        /* renamed from: b */
        public String mo9594b(String str) {
            if (this.f1612m != null) {
                return this.f1612m;
            }
            return str;
        }

        /* renamed from: c */
        public String mo9597c(String str) {
            if (this.f1611l != null) {
                return this.f1611l;
            }
            return str;
        }

        /* renamed from: a */
        public int mo9587a(int i) {
            return (this.f1600a != -1 && this.f1600a <= 3 && this.f1600a >= 0) ? this.f1600a : i;
        }

        /* renamed from: b */
        public int mo9593b(int i) {
            return (this.f1601b != -1 && this.f1601b >= 0 && this.f1601b <= 1800) ? this.f1601b * 1000 : i;
        }

        /* renamed from: c */
        public int mo9596c(int i) {
            if (this.f1602c == 0 || this.f1602c == 1 || this.f1602c == -1) {
                return this.f1602c;
            }
            return i;
        }

        /* renamed from: a */
        public int[] mo9592a(int i, int i2) {
            if (this.f1603d == -1 || !ReportPolicy.m1859a(this.f1603d)) {
                return new int[]{i, i2};
            }
            if (this.f1604e == -1 || this.f1604e < 90 || this.f1604e > 86400) {
                this.f1604e = 90;
            }
            return new int[]{this.f1603d, this.f1604e * 1000};
        }

        /* renamed from: d */
        public String mo9599d(String str) {
            return (this.f1605f == null || !ABTest.m1378a(this.f1605f)) ? str : this.f1605f;
        }

        /* renamed from: d */
        public int mo9598d(int i) {
            return (this.f1606g == -1 || this.f1606g < 90 || this.f1606g > 86400) ? i : this.f1606g * 1000;
        }

        /* renamed from: a */
        public boolean mo9591a() {
            return this.f1606g != -1;
        }

        /* renamed from: e */
        public String mo9600e(String str) {
            return this.f1607h;
        }

        /* renamed from: b */
        public boolean mo9595b() {
            return this.f1608i == 1;
        }

        /* renamed from: a */
        public long mo9588a(long j) {
            return (this.f1609j != -1 && this.f1609j >= 48) ? 3600000 * ((long) this.f1609j) : j;
        }

        /* renamed from: a */
        private int m2296a(Imprint amVar, String str) {
            if (amVar != null) {
                try {
                    if (amVar.mo9229f()) {
                        ImprintValue anVar = amVar.mo9227d().get(str);
                        if (anVar == null || TextUtils.isEmpty(anVar.mo9253c())) {
                            return -1;
                        }
                        try {
                            return Integer.parseInt(anVar.mo9253c().trim());
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return -1;
                }
            }
            return -1;
        }

        /* renamed from: b */
        private String m2297b(Imprint amVar, String str) {
            String str2;
            if (amVar == null) {
                return null;
            }
            try {
                if (!amVar.mo9229f()) {
                    return null;
                }
                ImprintValue anVar = amVar.mo9227d().get(str);
                if (anVar == null || TextUtils.isEmpty(anVar.mo9253c())) {
                    return null;
                }
                str2 = anVar.mo9253c();
                return str2;
            } catch (Exception e) {
                e.printStackTrace();
                str2 = null;
            }
        }
    }

    ImprintHandler(Context context) {
        this.f1599f = context;
    }

    /* renamed from: a */
    public static synchronized ImprintHandler m2284a(Context context) {
        ImprintHandler gVar;
        synchronized (ImprintHandler.class) {
            if (f1595e == null) {
                f1595e = new ImprintHandler(context);
                f1595e.mo9585c();
            }
            gVar = f1595e;
        }
        return gVar;
    }

    /* renamed from: a */
    public void mo9581a(OnImprintChangedListener yVar) {
        this.f1596b = yVar;
    }

    /* renamed from: a */
    public String mo9580a(Imprint amVar) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(amVar.mo9227d()).entrySet()) {
            sb.append((String) entry.getKey());
            if (((ImprintValue) entry.getValue()).mo9256e()) {
                sb.append(((ImprintValue) entry.getValue()).mo9253c());
            }
            sb.append(((ImprintValue) entry.getValue()).mo9257f());
            sb.append(((ImprintValue) entry.getValue()).mo9260i());
        }
        sb.append(amVar.f1282b);
        return HelperUtils.m1825a(sb.toString()).toLowerCase(Locale.US);
    }

    /* renamed from: c */
    private boolean m2286c(Imprint amVar) {
        if (!amVar.mo9233j().equals(mo9580a(amVar))) {
            return false;
        }
        for (ImprintValue next : amVar.mo9227d().values()) {
            byte[] a = DataHelper.m1775a(next.mo9260i());
            byte[] a2 = mo9582a(next);
            int i = 0;
            while (true) {
                if (i < 4) {
                    if (a[i] != a2[i]) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public byte[] mo9582a(ImprintValue anVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(anVar.mo9257f());
        byte[] array = allocate.array();
        byte[] bArr = f1594a;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    /* renamed from: b */
    public void mo9584b(Imprint amVar) {
        Imprint a;
        String str = null;
        if (amVar != null && m2286c(amVar)) {
            boolean z = false;
            synchronized (this) {
                Imprint amVar2 = this.f1598d;
                String j = amVar2 == null ? null : amVar2.mo9233j();
                if (amVar2 == null) {
                    a = m2287d(amVar);
                } else {
                    a = m2283a(amVar2, amVar);
                }
                this.f1598d = a;
                if (a != null) {
                    str = a.mo9233j();
                }
                if (!m2285a(j, str)) {
                    z = true;
                }
            }
            if (this.f1598d != null && z) {
                this.f1597c.mo9590a(this.f1598d);
                if (this.f1596b != null) {
                    this.f1596b.mo9104a(this.f1597c);
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m2285a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private Imprint m2283a(Imprint amVar, Imprint amVar2) {
        if (amVar2 != null) {
            Map<String, ImprintValue> d = amVar.mo9227d();
            for (Map.Entry next : amVar2.mo9227d().entrySet()) {
                if (((ImprintValue) next.getValue()).mo9256e()) {
                    d.put(next.getKey(), next.getValue());
                } else {
                    d.remove(next.getKey());
                }
            }
            amVar.mo9218a(amVar2.mo9230g());
            amVar.mo9219a(mo9580a(amVar));
        }
        return amVar;
    }

    /* renamed from: d */
    private Imprint m2287d(Imprint amVar) {
        Map<String, ImprintValue> d = amVar.mo9227d();
        ArrayList<String> arrayList = new ArrayList<>(d.size() / 2);
        for (Map.Entry next : d.entrySet()) {
            if (!((ImprintValue) next.getValue()).mo9256e()) {
                arrayList.add(next.getKey());
            }
        }
        for (String remove : arrayList) {
            d.remove(remove);
        }
        return amVar;
    }

    /* renamed from: a */
    public synchronized Imprint mo9579a() {
        return this.f1598d;
    }

    /* renamed from: b */
    public C0639a mo9583b() {
        return this.f1597c;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026 A[SYNTHETIC, Splitter:B:8:0x0026] */
    /* renamed from: c */
    public void mo9585c() {
        FileInputStream fileInputStream;
        ? r2 = 0;
        if (new File(this.f1599f.getFilesDir(), ".imprint").exists()) {
            try {
                fileInputStream = this.f1599f.openFileInput(".imprint");
                try {
                    byte[] b = HelperUtils.m1831b((InputStream) fileInputStream);
                    HelperUtils.m1832c(fileInputStream);
                    r2 = b;
                } catch (Exception e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        HelperUtils.m1832c(fileInputStream);
                        if (r2 == 0) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        r2 = fileInputStream;
                        HelperUtils.m1832c(r2);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                fileInputStream = r2;
            } catch (Throwable th2) {
                th = th2;
                HelperUtils.m1832c(r2);
                throw th;
            }
            if (r2 == 0) {
                try {
                    Imprint amVar = new Imprint();
                    new TDeserializer().mo9407a(amVar, r2);
                    this.f1598d = amVar;
                    this.f1597c.mo9590a(amVar);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    public void mo9586d() {
        if (this.f1598d != null) {
            try {
                HelperUtils.m1828a(new File(this.f1599f.getFilesDir(), ".imprint"), new TSerializer().mo9408a(this.f1598d));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
