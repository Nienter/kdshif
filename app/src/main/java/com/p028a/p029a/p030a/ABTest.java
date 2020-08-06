package com.p028a.p029a.p030a;

import android.content.Context;
import android.text.TextUtils;
import com.p028a.p029a.p030a.ImprintHandler;

/* renamed from: com.a.a.a.ag */
public class ABTest implements OnImprintChangedListener {

    /* renamed from: i */
    private static ABTest f1196i = null;

    /* renamed from: a */
    private boolean f1197a = false;

    /* renamed from: b */
    private int f1198b = -1;

    /* renamed from: c */
    private int f1199c = -1;

    /* renamed from: d */
    private int f1200d = -1;

    /* renamed from: e */
    private float f1201e = 0.0f;

    /* renamed from: f */
    private float f1202f = 0.0f;

    /* renamed from: g */
    private String f1203g = null;

    /* renamed from: h */
    private Context f1204h = null;

    /* renamed from: a */
    public static synchronized ABTest m1377a(Context context) {
        ABTest agVar;
        synchronized (ABTest.class) {
            if (f1196i == null) {
                ImprintHandler.C0639a b = ImprintHandler.m2284a(context).mo9583b();
                f1196i = new ABTest(context, b.mo9599d((String) null), b.mo9598d(0));
            }
            agVar = f1196i;
        }
        return agVar;
    }

    private ABTest(Context context, String str, int i) {
        this.f1204h = context;
        mo9105a(str, i);
    }

    /* renamed from: b */
    private float m1379b(String str, int i) {
        int i2 = i * 2;
        if (str == null) {
            return 0.0f;
        }
        return ((float) Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue()) / 1048576.0f;
    }

    /* renamed from: a */
    public void mo9105a(String str, int i) {
        this.f1199c = i;
        String a = Envelope.m1875a(this.f1204h);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
            this.f1197a = false;
            return;
        }
        try {
            this.f1201e = m1379b(a, 12);
            this.f1202f = m1379b(a, 6);
            if (str.startsWith("SIG7")) {
                m1380b(str);
            } else if (str.startsWith("FIXED")) {
                m1381c(str);
            }
        } catch (Exception e) {
            this.f1197a = false;
            MLog.m1838a("v:" + str, (Throwable) e);
        }
    }

    /* renamed from: a */
    public static boolean m1378a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(",").length == split[5].split(",").length) {
            return true;
        }
        if (!split[0].startsWith("FIXED")) {
            return false;
        }
        int length = split[5].split(",").length;
        int parseInt = Integer.parseInt(split[1]);
        if (length < parseInt || parseInt < 1) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    private void m1380b(String str) {
        float f;
        if (str != null) {
            String[] split = str.split("\\|");
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            } else {
                f = 0.0f;
            }
            if (this.f1201e > f) {
                this.f1197a = false;
                return;
            }
            float[] fArr = null;
            if (split[0].equals("SIG7")) {
                String[] split2 = split[1].split(",");
                float[] fArr2 = new float[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    fArr2[i] = Float.valueOf(split2[i]).floatValue();
                }
                fArr = fArr2;
            }
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                this.f1203g = "RPT";
                String[] split3 = split[5].split(",");
                int[] iArr2 = new int[split3.length];
                for (int i2 = 0; i2 < split3.length; i2++) {
                    iArr2[i2] = Integer.valueOf(split3[i2]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                this.f1197a = true;
                this.f1203g = "DOM";
                try {
                    String[] split4 = split[5].split(",");
                    iArr = new int[split4.length];
                    for (int i3 = 0; i3 < split4.length; i3++) {
                        iArr[i3] = Integer.valueOf(split4[i3]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            float f2 = 0.0f;
            int i4 = 0;
            while (true) {
                if (i4 >= fArr.length) {
                    i4 = -1;
                    break;
                }
                f2 += fArr[i4];
                if (this.f1202f < f2) {
                    break;
                }
                i4++;
            }
            if (i4 != -1) {
                this.f1197a = true;
                this.f1200d = i4 + 1;
                if (iArr != null) {
                    this.f1198b = iArr[i4];
                    return;
                }
                return;
            }
            this.f1197a = false;
        }
    }

    /* renamed from: c */
    private void m1381c(String str) {
        int i;
        if (str != null) {
            String[] split = str.split("\\|");
            float f = 0.0f;
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            }
            if (this.f1201e > f) {
                this.f1197a = false;
                return;
            }
            if (split[0].equals("FIXED")) {
                i = Integer.valueOf(split[1]).intValue();
            } else {
                i = -1;
            }
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                this.f1203g = "RPT";
                String[] split2 = split[5].split(",");
                int[] iArr2 = new int[split2.length];
                for (int i2 = 0; i2 < split2.length; i2++) {
                    iArr2[i2] = Integer.valueOf(split2[i2]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                this.f1203g = "DOM";
                this.f1197a = true;
                try {
                    String[] split3 = split[5].split(",");
                    iArr = new int[split3.length];
                    for (int i3 = 0; i3 < split3.length; i3++) {
                        iArr[i3] = Integer.valueOf(split3[i3]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            if (i != -1) {
                this.f1197a = true;
                this.f1200d = i;
                if (iArr != null) {
                    this.f1198b = iArr[i - 1];
                    return;
                }
                return;
            }
            this.f1197a = false;
        }
    }

    /* renamed from: a */
    public boolean mo9106a() {
        return this.f1197a;
    }

    /* renamed from: b */
    public int mo9107b() {
        return this.f1198b;
    }

    /* renamed from: c */
    public String mo9108c() {
        if (!this.f1197a) {
            return "error";
        }
        return String.valueOf(this.f1200d);
    }

    /* renamed from: d */
    public String mo9109d() {
        return this.f1203g;
    }

    /* renamed from: a */
    public void mo9104a(ImprintHandler.C0639a aVar) {
        mo9105a(aVar.mo9599d((String) null), aVar.mo9598d(0));
    }

    public String toString() {
        return " p13:" + this.f1201e + " p07:" + this.f1202f + " policy:" + this.f1198b + " interval:" + this.f1199c;
    }
}
