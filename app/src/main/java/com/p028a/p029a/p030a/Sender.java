package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.p028a.p029a.AnalyticsConfig;
import com.p028a.p029a.p030a.StoreHelper;
import com.p028a.p029a.p030a.TBinaryProtocol;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.json.JSONObject;

/* renamed from: com.a.a.a.ac */
public class Sender {

    /* renamed from: d */
    private static Context f1172d;

    /* renamed from: a */
    private IdTracker f1173a;

    /* renamed from: b */
    private ImprintHandler f1174b;

    /* renamed from: c */
    private final int f1175c = 1;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public StatTracer f1176e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C0648w f1177f;

    /* renamed from: g */
    private JSONObject f1178g;

    /* renamed from: h */
    private boolean f1179h = false;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f1180i;

    public Sender(Context context, StatTracer aeVar) {
        this.f1173a = IdTracker.m2268a(context);
        this.f1174b = ImprintHandler.m2284a(context);
        f1172d = context;
        this.f1176e = aeVar;
        this.f1177f = new C0648w(context);
        this.f1177f.mo9632a((IRequestStat) this.f1176e);
    }

    /* renamed from: a */
    public void mo9077a(JSONObject jSONObject) {
        this.f1178g = jSONObject;
    }

    /* renamed from: a */
    public void mo9078a(boolean z) {
        this.f1179h = z;
    }

    /* renamed from: b */
    public void mo9079b(boolean z) {
        this.f1180i = z;
    }

    /* renamed from: a */
    public void mo9076a(OnImprintChangedListener yVar) {
        this.f1174b.mo9581a(yVar);
    }

    /* renamed from: a */
    public void mo9075a() {
        try {
            if (this.f1178g != null) {
                m1340c();
            } else {
                m1337b();
            }
        } catch (Throwable th) {
        }
        try {
            if (DeviceConfig.m1805i(f1172d)) {
                SharedPreferences a = PreferenceWrapper.m1333a(f1172d);
                if (a != null) {
                    String string = a.getString("uopdta", "");
                    long b = UMCCTimeRange.m2224b(System.currentTimeMillis());
                    if (TextUtils.isEmpty(string)) {
                        long j = a.getLong("uopdte", -1);
                        int i = a.getInt("uopcnt", 0);
                        if (j == -1) {
                            a.edit().putInt("uopcnt", i + 1).commit();
                            this.f1177f.mo9631a();
                        } else if (b != j) {
                            a.edit().putInt("uopcnt", 1).commit();
                            this.f1177f.mo9631a();
                        } else if (i < 2) {
                            a.edit().putInt("uopcnt", i + 1).commit();
                            this.f1177f.mo9631a();
                        }
                        a.edit().putLong("uopdte", b).commit();
                        return;
                    }
                    return;
                }
                this.f1177f.mo9631a();
            }
        } catch (Throwable th2) {
        }
    }

    /* renamed from: b */
    private void m1337b() {
        StoreHelper.m1888a(f1172d).mo9395g().mo9397a((StoreHelper.C0609b) new StoreHelper.C0609b() {
            /* renamed from: a */
            public void mo9080a(File file) {
            }

            /* renamed from: b */
            public boolean mo9081b(File file) {
                FileInputStream fileInputStream;
                byte[] b;
                int a;
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        b = HelperUtils.m1831b((InputStream) fileInputStream);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        HelperUtils.m1832c(fileInputStream);
                        byte[] a2 = Sender.this.f1177f.mo9633a(b);
                        if (a2 == null) {
                            a = 1;
                        } else {
                            a = Sender.this.m1335a(a2);
                        }
                        if (!Sender.this.f1180i && a == 1) {
                            return false;
                        }
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = null;
                    HelperUtils.m1832c(fileInputStream);
                    throw th;
                }
            }

            /* renamed from: c */
            public void mo9082c(File file) {
                Sender.this.f1176e.mo9098j();
            }
        });
    }

    /* renamed from: c */
    private void m1340c() {
        Envelope b;
        int a;
        try {
            this.f1173a.mo9569a();
            try {
                String encodeToString = Base64.encodeToString(new TSerializer().mo9408a(this.f1173a.mo9571b()), 0);
                if (!TextUtils.isEmpty(encodeToString)) {
                    JSONObject jSONObject = this.f1178g.getJSONObject("header");
                    jSONObject.put("id_tracking", encodeToString);
                    this.f1178g.put("header", jSONObject);
                }
            } catch (Exception e) {
            }
            byte[] bytes = String.valueOf(this.f1178g).getBytes();
            if (bytes != null && !DataHelper.m1774a(f1172d, bytes)) {
                if (!this.f1179h) {
                    b = Envelope.m1874a(f1172d, AnalyticsConfig.m1311a(f1172d), bytes);
                } else {
                    b = Envelope.m1877b(f1172d, AnalyticsConfig.m1311a(f1172d), bytes);
                }
                byte[] c = b.mo9384c();
                StoreHelper.m1888a(f1172d).mo9393e();
                byte[] a2 = this.f1177f.mo9633a(c);
                if (a2 == null) {
                    a = 1;
                } else {
                    a = m1335a(a2);
                }
                switch (a) {
                    case 1:
                        if (!this.f1180i) {
                            StoreHelper.m1888a(f1172d).mo9388a(c);
                            return;
                        }
                        return;
                    case 2:
                        this.f1173a.mo9572c();
                        this.f1176e.mo9098j();
                        return;
                    case 3:
                        this.f1176e.mo9098j();
                        return;
                    default:
                        return;
                }
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public int m1335a(byte[] bArr) {
        C0576ao aoVar = new C0576ao();
        try {
            new TDeserializer(new TBinaryProtocol.C0612a()).mo9407a(aoVar, bArr);
            if (aoVar.f1316a == 1) {
                this.f1174b.mo9584b(aoVar.mo9287i());
                this.f1174b.mo9586d();
            }
            MLog.m1842b("send log:" + aoVar.mo9284f());
        } catch (Throwable th) {
        }
        if (aoVar.f1316a == 1) {
            return 2;
        }
        return 3;
    }
}
