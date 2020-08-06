package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.internal.ServerProtocol;
import com.p028a.p029a.AnalyticsConfig;
import com.p028a.p029a.InternalConfig;
import com.p028a.p029a.p030a.ImprintHandler;
import com.p028a.p029a.p030a.ReportPolicy;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.a.a.a.q */
public final class CacheImpl implements ICacheService, OnImprintChangedListener {
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static Context f1634j;

    /* renamed from: a */
    String f1635a = null;

    /* renamed from: b */
    private StoreHelper f1636b = null;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public StatTracer f1637c = null;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Defcon f1638d = null;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public ABTest f1639e = null;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public ImLatent f1640f = null;

    /* renamed from: g */
    private C0644a f1641g = null;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public ImprintHandler.C0639a f1642h = null;

    /* renamed from: i */
    private long f1643i = 0;

    /* renamed from: k */
    private int f1644k = 10;

    /* renamed from: l */
    private JSONArray f1645l = new JSONArray();

    /* renamed from: m */
    private final int f1646m = 5000;

    /* renamed from: n */
    private int f1647n = 0;

    /* renamed from: o */
    private int f1648o = 0;

    /* renamed from: p */
    private long f1649p = 0;

    /* renamed from: q */
    private final long f1650q = 28800000;

    /* renamed from: com.a.a.a.q$a */
    /* compiled from: CacheImpl */
    public class C0644a {

        /* renamed from: b */
        private ReportPolicy.C0602h f1653b;

        /* renamed from: c */
        private int f1654c = -1;

        /* renamed from: d */
        private int f1655d = -1;

        /* renamed from: e */
        private int f1656e = -1;

        /* renamed from: f */
        private int f1657f = -1;

        public C0644a() {
            int[] a = CacheImpl.this.f1642h.mo9592a(-1, -1);
            this.f1654c = a[0];
            this.f1655d = a[1];
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo9624a(boolean z) {
            ReportPolicy.C0602h bVar;
            boolean z2 = true;
            int i = 0;
            if (CacheImpl.this.f1638d.mo9115c()) {
                if (!(this.f1653b instanceof ReportPolicy.C0596b) || !this.f1653b.mo9376a()) {
                    z2 = false;
                }
                if (z2) {
                    bVar = this.f1653b;
                } else {
                    bVar = new ReportPolicy.C0596b(CacheImpl.this.f1637c, CacheImpl.this.f1638d);
                }
                this.f1653b = bVar;
                return;
            }
            if (!(this.f1653b instanceof ReportPolicy.C0597c) || !this.f1653b.mo9376a()) {
                z2 = false;
            }
            if (z2) {
                return;
            }
            if (z && CacheImpl.this.f1640f.mo9116a()) {
                this.f1653b = new ReportPolicy.C0597c((int) CacheImpl.this.f1640f.mo9117b());
                CacheImpl.this.m2344b((int) CacheImpl.this.f1640f.mo9117b());
            } else if (MLog.f1376a && CacheImpl.this.f1642h.mo9595b()) {
                this.f1653b = new ReportPolicy.C0595a(CacheImpl.this.f1637c);
            } else if (!CacheImpl.this.f1639e.mo9106a() || !"RPT".equals(CacheImpl.this.f1639e.mo9109d())) {
                int i2 = this.f1656e;
                int i3 = this.f1657f;
                if (this.f1654c != -1) {
                    i2 = this.f1654c;
                    i3 = this.f1655d;
                }
                this.f1653b = m2364a(i2, i3);
            } else {
                if (CacheImpl.this.f1639e.mo9107b() == 6) {
                    if (CacheImpl.this.f1642h.mo9591a()) {
                        i = CacheImpl.this.f1642h.mo9598d(90000);
                    } else if (this.f1655d > 0) {
                        i = this.f1655d;
                    } else {
                        i = this.f1657f;
                    }
                }
                this.f1653b = m2364a(CacheImpl.this.f1639e.mo9107b(), i);
            }
        }

        /* renamed from: b */
        public ReportPolicy.C0602h mo9625b(boolean z) {
            mo9624a(z);
            return this.f1653b;
        }

        /* renamed from: a */
        private ReportPolicy.C0602h m2364a(int i, int i2) {
            switch (i) {
                case 0:
                    return this.f1653b instanceof ReportPolicy.C0601g ? this.f1653b : new ReportPolicy.C0601g();
                case 1:
                    return this.f1653b instanceof ReportPolicy.C0598d ? this.f1653b : new ReportPolicy.C0598d();
                case 4:
                    if (this.f1653b instanceof ReportPolicy.C0600f) {
                        return this.f1653b;
                    }
                    return new ReportPolicy.C0600f(CacheImpl.this.f1637c);
                case 5:
                    if (this.f1653b instanceof ReportPolicy.C0603i) {
                        return this.f1653b;
                    }
                    return new ReportPolicy.C0603i(CacheImpl.f1634j);
                case 6:
                    if (!(this.f1653b instanceof ReportPolicy.C0599e)) {
                        return new ReportPolicy.C0599e(CacheImpl.this.f1637c, (long) i2);
                    }
                    ReportPolicy.C0602h hVar = this.f1653b;
                    ((ReportPolicy.C0599e) hVar).mo9377a((long) i2);
                    return hVar;
                case 8:
                    if (this.f1653b instanceof ReportPolicy.C0604j) {
                        return this.f1653b;
                    }
                    return new ReportPolicy.C0604j(CacheImpl.this.f1637c);
                default:
                    if (this.f1653b instanceof ReportPolicy.C0598d) {
                        return this.f1653b;
                    }
                    return new ReportPolicy.C0598d();
            }
        }

        /* renamed from: a */
        public void mo9623a(ImprintHandler.C0639a aVar) {
            int[] a = aVar.mo9592a(-1, -1);
            this.f1654c = a[0];
            this.f1655d = a[1];
        }
    }

    public CacheImpl(Context context) {
        f1634j = context;
        this.f1637c = new StatTracer(context);
        this.f1636b = StoreHelper.m1888a(context);
        this.f1642h = ImprintHandler.m2284a(context).mo9583b();
        this.f1641g = new C0644a();
        this.f1639e = ABTest.m1377a(f1634j);
        this.f1638d = Defcon.m1388a(f1634j);
        this.f1640f = ImLatent.m1395a(f1634j, this.f1637c);
        SharedPreferences a = PreferenceWrapper.m1333a(f1634j);
        this.f1649p = a.getLong("thtstart", 0);
        this.f1647n = a.getInt("gkvc", 0);
        this.f1648o = a.getInt("ekvc", 0);
        this.f1635a = ImprintHandler.m2284a(f1634j).mo9583b().mo9589a((String) null);
    }

    /* renamed from: a */
    public void mo9618a() {
        if (DeviceConfig.m1806j(f1634j)) {
            m2351d();
        } else {
            MLog.m1836a("network is unavailable");
        }
    }

    /* renamed from: a */
    public void mo9620a(Object obj) {
        if (this.f1637c.mo9093e()) {
            this.f1643i = this.f1637c.mo9099k();
        }
        boolean z = true;
        if (obj instanceof JSONObject) {
            z = false;
            try {
                m2345b((JSONObject) obj);
            } catch (Throwable th) {
            }
        }
        if (m2342a(z)) {
            m2351d();
        }
    }

    /* renamed from: b */
    private void m2345b(JSONObject jSONObject) {
        try {
            if (2050 == jSONObject.getInt("__t")) {
                if (m2349c(this.f1647n)) {
                    this.f1647n++;
                } else {
                    return;
                }
            } else if (2049 == jSONObject.getInt("__t")) {
                if (m2349c(this.f1648o)) {
                    this.f1648o++;
                } else {
                    return;
                }
            }
            if (this.f1645l.length() > this.f1644k) {
                UMStoreManager.m2242a(f1634j).mo9556a(this.f1645l);
                this.f1645l = new JSONArray();
            }
            if (this.f1649p == 0) {
                this.f1649p = System.currentTimeMillis();
            }
            this.f1645l.put(jSONObject);
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public void mo9622b() {
        m2348c(mo9617a(new int[0]));
    }

    /* renamed from: a */
    private void m2340a(int i) {
        m2348c(mo9617a(i, (int) (System.currentTimeMillis() - this.f1637c.mo9100l())));
        QueuedWork.m1848a(new SafeRunnable() {
            /* renamed from: a */
            public void mo9386a() {
                CacheImpl.this.mo9618a();
            }
        }, (long) i);
    }

    /* renamed from: c */
    private void m2348c(JSONObject jSONObject) {
        Envelope a;
        if (jSONObject != null) {
            try {
                IdTracker a2 = IdTracker.m2268a(f1634j);
                a2.mo9569a();
                try {
                    String encodeToString = Base64.encodeToString(new TSerializer().mo9408a(a2.mo9571b()), 0);
                    if (!TextUtils.isEmpty(encodeToString)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("header");
                        jSONObject2.put("id_tracking", encodeToString);
                        jSONObject.put("header", jSONObject2);
                    }
                } catch (Exception e) {
                }
                byte[] bytes = String.valueOf(jSONObject).getBytes();
                if (bytes != null && !DataHelper.m1774a(f1634j, bytes)) {
                    if (m2354e()) {
                        a = Envelope.m1877b(f1634j, AnalyticsConfig.m1311a(f1634j), bytes);
                    } else {
                        a = Envelope.m1874a(f1634j, AnalyticsConfig.m1311a(f1634j), bytes);
                    }
                    byte[] c = a.mo9384c();
                    StoreHelper a3 = StoreHelper.m1888a(f1634j);
                    a3.mo9393e();
                    a3.mo9388a(c);
                    a2.mo9572c();
                }
            } catch (Exception e2) {
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public JSONObject mo9617a(int... iArr) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(AnalyticsConfig.m1311a(f1634j))) {
            MLog.m1844c("Appkey is missing ,Please check AndroidManifest.xml");
            return null;
        }
        mo9619a(f1634j);
        JSONObject a = UMStoreManager.m2242a(f1634j).mo9555a();
        if (a == null) {
            a = new JSONObject();
        }
        try {
            jSONObject = a.getJSONObject("body");
        } catch (Throwable th) {
            jSONObject = new JSONObject();
        }
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
            SharedPreferences a2 = PreferenceWrapper.m1333a(f1634j);
            if (a2 != null) {
                String string = a2.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put("userlevel", string);
                }
            }
            if (this.f1637c.mo9093e() && this.f1643i != 0) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("ts", this.f1643i);
                jSONObject.put("activate_msg", jSONObject3);
                jSONObject2.put("activate_msg", jSONObject3);
            }
            JSONObject jSONObject4 = new JSONObject();
            JSONObject a3 = UMCCAggregatedManager.m2160a(f1634j).mo9519a();
            if (a3 != null && a3.length() > 0) {
                jSONObject4.put("ag", a3);
            }
            JSONObject b = UMCCAggregatedManager.m2160a(f1634j).mo9522b();
            if (b != null && b.length() > 0) {
                jSONObject4.put("ve_meta", b);
            }
            if (jSONObject4.length() > 0) {
                jSONObject.put("cc", jSONObject4);
                jSONObject2.put("cc", jSONObject4);
            }
            String[] a4 = InternalConfig.m2430a(f1634j);
            if (a4 != null && !TextUtils.isEmpty(a4[0]) && !TextUtils.isEmpty(a4[1])) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("provider", a4[0]);
                jSONObject5.put("puid", a4[1]);
                if (jSONObject5.length() > 0) {
                    jSONObject.put("active_user", jSONObject5);
                    jSONObject2.put("active_user", jSONObject5);
                }
            }
            if (ABTest.m1377a(f1634j).mo9106a()) {
                m2352d(jSONObject);
            }
            this.f1638d.mo9113a(jSONObject, f1634j);
            if (iArr != null && iArr.length == 2) {
                JSONObject jSONObject6 = new JSONObject();
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.put("interval", iArr[0] / 1000);
                jSONObject7.put("latency", iArr[1]);
                jSONObject6.put("latent", jSONObject7);
                jSONObject.put("control_policy", jSONObject6);
            }
            if (jSONObject.length() > 0) {
                a.put("body", jSONObject);
            } else {
                try {
                    a.remove("body");
                } catch (Throwable th2) {
                }
            }
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("appkey", AnalyticsConfig.m1311a(f1634j));
            jSONObject8.put("channel", AnalyticsConfig.m1313b(f1634j));
            String a5 = HelperUtils.m1825a(AnalyticsConfig.m1314c(f1634j));
            if (!TextUtils.isEmpty(a5)) {
                jSONObject8.put("secret", a5);
            }
            jSONObject8.put("display_name", DeviceConfig.m1816t(f1634j));
            jSONObject8.put("package_name", DeviceConfig.m1813q(f1634j));
            jSONObject8.put("app_signature", DeviceConfig.m1814r(f1634j));
            if (a2 == null) {
                try {
                    a2 = PreferenceWrapper.m1333a(f1634j);
                } catch (Throwable th3) {
                    jSONObject8.put("app_version", DeviceConfig.m1793b(f1634j));
                    jSONObject8.put("version_code", Integer.parseInt(DeviceConfig.m1787a(f1634j)));
                }
            }
            if (a2 != null) {
                String string2 = a2.getString("vers_name", "");
                if (!TextUtils.isEmpty(string2)) {
                    jSONObject8.put("app_version", string2);
                    jSONObject8.put("version_code", a2.getInt("vers_code", 0));
                } else {
                    jSONObject8.put("app_version", DeviceConfig.m1793b(f1634j));
                    jSONObject8.put("version_code", Integer.parseInt(DeviceConfig.m1787a(f1634j)));
                }
            }
            if (!(AnalyticsConfig.f1156a == null || AnalyticsConfig.f1157b == null)) {
                jSONObject8.put("wrapper_type", AnalyticsConfig.f1156a);
                jSONObject8.put("wrapper_version", AnalyticsConfig.f1157b);
            }
            jSONObject8.put("sdk_type", "Android");
            jSONObject8.put("sdk_version", AnalyticsConfig.m1316e(f1634j));
            jSONObject8.put("vertical_type", AnalyticsConfig.m1315d(f1634j));
            jSONObject8.put("idmd5", DeviceConfig.m1798d(f1634j));
            jSONObject8.put("cpu", DeviceConfig.m1786a());
            jSONObject8.put("os", "Android");
            jSONObject8.put("os_version", Build.VERSION.RELEASE);
            if (DeviceConfig.m1811o(f1634j) != null) {
                jSONObject8.put("resolution", r5[1] + "*" + r5[0]);
            }
            jSONObject8.put("mc", DeviceConfig.m1810n(f1634j));
            jSONObject8.put("device_id", DeviceConfig.m1796c(f1634j));
            jSONObject8.put("device_model", Build.MODEL);
            jSONObject8.put("device_board", Build.BOARD);
            jSONObject8.put("device_brand", Build.BRAND);
            jSONObject8.put("device_manutime", Build.TIME);
            jSONObject8.put("device_manufacturer", Build.MANUFACTURER);
            jSONObject8.put("device_manuid", Build.ID);
            jSONObject8.put("device_name", Build.DEVICE);
            String u = DeviceConfig.m1817u(f1634j);
            if (!TextUtils.isEmpty(u)) {
                jSONObject8.put("sub_os_name", u);
            }
            String v = DeviceConfig.m1818v(f1634j);
            if (!TextUtils.isEmpty(v)) {
                jSONObject8.put("sub_os_version", v);
            }
            String[] h = DeviceConfig.m1804h(f1634j);
            if ("Wi-Fi".equals(h[0])) {
                jSONObject8.put("access", "wifi");
            } else if ("2G/3G".equals(h[0])) {
                jSONObject8.put("access", "2G/3G");
            } else {
                jSONObject8.put("access", "unknow");
            }
            if (!"".equals(h[1])) {
                jSONObject8.put("access_subtype", h[1]);
            }
            String e = DeviceConfig.m1799e(f1634j);
            if (!TextUtils.isEmpty(e)) {
                jSONObject8.put("mccmnc", e);
            } else {
                jSONObject8.put("mccmnc", "");
            }
            String[] l = DeviceConfig.m1808l(f1634j);
            jSONObject8.put("country", l[0]);
            jSONObject8.put("language", l[1]);
            jSONObject8.put("timezone", DeviceConfig.m1807k(f1634j));
            jSONObject8.put("carrier", DeviceConfig.m1803g(f1634j));
            try {
                jSONObject8.put("successful_requests", a2.getInt("successful_request", 0));
                jSONObject8.put("failed_requests", a2.getInt("failed_requests", 0));
                jSONObject8.put("req_time", a2.getInt("last_request_spent_ms", 0));
            } catch (Exception e2) {
            }
            try {
                Imprint a6 = ImprintHandler.m2284a(f1634j).mo9579a();
                if (a6 != null) {
                    jSONObject8.put("imprint", Base64.encodeToString(new TSerializer().mo9408a(a6), 0));
                }
            } catch (Exception e3) {
            }
            a.put("header", jSONObject8);
            jSONObject2.put("sdk_version", jSONObject8.getString("sdk_version")).put("device_id", jSONObject8.getString("device_id")).put("device_model", jSONObject8.getString("device_model")).put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, jSONObject8.getString("version_code")).put("appkey", jSONObject8.getString("appkey")).put("channel", jSONObject8.getString("channel"));
            if (!mo9621a(jSONObject8)) {
                a = null;
            }
            if (MLog.f1376a && jSONObject2.length() > 0) {
                MLog.m1836a(String.valueOf(jSONObject2));
            }
            if (a2 == null) {
                return a;
            }
            SharedPreferences.Editor edit = a2.edit();
            edit.remove("vers_name");
            edit.remove("vers_code");
            edit.remove("vers_date");
            edit.remove("vers_pre_version");
            edit.commit();
            return a;
        } catch (Throwable th4) {
            StoreHelper.m1888a(f1634j).mo9393e();
            return null;
        }
    }

    /* renamed from: d */
    private void m2352d(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(ABTest.m1377a(f1634j).mo9109d(), ABTest.m1377a(f1634j).mo9108c());
        jSONObject.put("group_info", jSONObject2);
    }

    /* renamed from: a */
    public boolean mo9621a(JSONObject jSONObject) {
        if (TextUtils.isEmpty("device_id") || TextUtils.isEmpty("mc") || TextUtils.isEmpty("resolution") || TextUtils.isEmpty("appkey") || TextUtils.isEmpty("channel") || TextUtils.isEmpty("app_signature") || TextUtils.isEmpty("package_name") || TextUtils.isEmpty("app_version")) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private boolean m2342a(boolean z) {
        if (!DeviceConfig.m1806j(f1634j)) {
            MLog.m1844c("network is unavailable");
            return false;
        } else if (this.f1637c.mo9093e()) {
            return true;
        } else {
            return this.f1641g.mo9625b(z).mo9375a(z);
        }
    }

    /* renamed from: d */
    private void m2351d() {
        try {
            if (this.f1636b.mo9394f()) {
                Sender acVar = new Sender(f1634j, this.f1637c);
                acVar.mo9076a((OnImprintChangedListener) this);
                if (this.f1638d.mo9115c()) {
                    acVar.mo9079b(true);
                }
                acVar.mo9075a();
                return;
            }
            JSONObject a = mo9617a(new int[0]);
            if (a.length() > 0) {
                Sender acVar2 = new Sender(f1634j, this.f1637c);
                acVar2.mo9076a((OnImprintChangedListener) this);
                if (this.f1638d.mo9115c()) {
                    acVar2.mo9079b(true);
                }
                acVar2.mo9077a(a);
                acVar2.mo9078a(m2354e());
                acVar2.mo9075a();
            }
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: e */
    private boolean m2354e() {
        switch (this.f1642h.mo9596c(-1)) {
            case -1:
                return AnalyticsConfig.f1163h;
            case 1:
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2344b(int i) {
        m2340a(i);
    }

    /* renamed from: a */
    public void mo9104a(ImprintHandler.C0639a aVar) {
        this.f1639e.mo9104a(aVar);
        this.f1638d.mo9104a(aVar);
        this.f1640f.mo9104a(aVar);
        this.f1641g.mo9623a(aVar);
        this.f1635a = ImprintHandler.m2284a(f1634j).mo9583b().mo9589a((String) null);
    }

    /* renamed from: c */
    private boolean m2349c(int i) {
        if (this.f1649p == 0) {
            return true;
        }
        if (System.currentTimeMillis() - this.f1649p > 28800000) {
            m2355f();
            return true;
        } else if (i > 5000) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: a */
    public void mo9619a(Context context) {
        try {
            if (f1634j == null) {
                f1634j = context;
            }
            if (this.f1645l.length() > 0) {
                UMStoreManager.m2242a(f1634j).mo9556a(this.f1645l);
                this.f1645l = new JSONArray();
            }
            PreferenceWrapper.m1333a(f1634j).edit().putLong("thtstart", this.f1649p).putInt("gkvc", this.f1647n).putInt("ekvc", this.f1648o).commit();
        } catch (Throwable th) {
        }
    }

    /* renamed from: f */
    private void m2355f() {
        this.f1647n = 0;
        this.f1648o = 0;
        this.f1649p = System.currentTimeMillis();
    }
}
