package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.GraphResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.a.a.a.cq */
public class UMCCAggregatedManager {
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static Context f1528i;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public UMCCAggregatedListObject f1529a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public UMCCStorageManager f1530b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public UMCCSystemBufferManager f1531c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f1532d;

    /* renamed from: e */
    private boolean f1533e;

    /* renamed from: f */
    private long f1534f;

    /* renamed from: g */
    private final String f1535g;

    /* renamed from: h */
    private final String f1536h;

    /* renamed from: j */
    private List<String> f1537j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public C0626a f1538k;

    /* renamed from: l */
    private final Thread f1539l;

    /* renamed from: com.a.a.a.cq$a */
    /* compiled from: UMCCAggregatedManager */
    private static class C0626a extends Handler {

        /* renamed from: a */
        private final WeakReference<UMCCAggregatedManager> f1551a;

        public C0626a(UMCCAggregatedManager cqVar) {
            this.f1551a = new WeakReference<>(cqVar);
        }

        public void handleMessage(Message message) {
            if (this.f1551a != null) {
                switch (message.what) {
                    case 48:
                        sendEmptyMessageDelayed(48, UMCCTimeRange.m2225c(System.currentTimeMillis()));
                        UMCCAggregatedManager.m2160a(UMCCAggregatedManager.f1528i).m2177k();
                        return;
                    case 49:
                        sendEmptyMessageDelayed(49, UMCCTimeRange.m2226d(System.currentTimeMillis()));
                        UMCCAggregatedManager.m2160a(UMCCAggregatedManager.f1528i).m2175j();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* renamed from: com.a.a.a.cq$b */
    /* compiled from: UMCCAggregatedManager */
    private static class C0627b {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final UMCCAggregatedManager f1552a = new UMCCAggregatedManager();
    }

    private UMCCAggregatedManager() {
        this.f1529a = null;
        this.f1530b = null;
        this.f1531c = null;
        this.f1532d = false;
        this.f1533e = false;
        this.f1534f = 0;
        this.f1535g = "main_fest_mode";
        this.f1536h = "main_fest_timestamp";
        this.f1537j = new ArrayList();
        this.f1538k = null;
        this.f1539l = new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                if (UMCCAggregatedManager.this.f1538k == null) {
                    C0626a unused = UMCCAggregatedManager.this.f1538k = new C0626a(UMCCAggregatedManager.this);
                }
                UMCCAggregatedManager.this.m2168f();
            }
        });
        if (f1528i != null) {
            if (this.f1529a == null) {
                this.f1529a = new UMCCAggregatedListObject();
            }
            if (this.f1530b == null) {
                this.f1530b = UMCCStorageManager.m2200a(f1528i);
            }
            if (this.f1531c == null) {
                this.f1531c = new UMCCSystemBufferManager();
            }
        }
        this.f1539l.start();
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m2168f() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f1538k.sendEmptyMessageDelayed(48, UMCCTimeRange.m2225c(currentTimeMillis));
        this.f1538k.sendEmptyMessageDelayed(49, UMCCTimeRange.m2226d(currentTimeMillis));
    }

    /* renamed from: a */
    public static final UMCCAggregatedManager m2160a(Context context) {
        f1528i = context;
        return C0627b.f1552a;
    }

    /* renamed from: a */
    public void mo9521a(final C0614cl clVar) {
        if (!this.f1532d) {
            QueuedWork.m1849b(new SafeRunnable() {
                /* renamed from: a */
                public void mo9386a() {
                    try {
                        UMCCAggregatedManager.this.f1530b.mo9531a((C0614cl) new C0614cl() {
                            /* renamed from: a */
                            public void mo9396a(Object obj, boolean z) {
                                if (obj instanceof Map) {
                                    UMCCAggregatedManager.this.f1529a.mo9482a((Map<List<String>, UMCCAggregatedObject>) (Map) obj);
                                } else if ((obj instanceof String) || (obj instanceof Boolean)) {
                                }
                                boolean unused = UMCCAggregatedManager.this.f1532d = true;
                            }
                        });
                        UMCCAggregatedManager.this.m2171h();
                        UMCCAggregatedManager.this.m2178l();
                        clVar.mo9396a(GraphResponse.SUCCESS_KEY, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* renamed from: g */
    private void m2169g() {
        SharedPreferences.Editor edit = PreferenceWrapper.m1333a(f1528i).edit();
        edit.putBoolean("main_fest_mode", false);
        edit.putLong("main_fest_timestamp", 0);
        edit.commit();
        this.f1533e = false;
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m2171h() {
        SharedPreferences a = PreferenceWrapper.m1333a(f1528i);
        this.f1533e = a.getBoolean("main_fest_mode", false);
        this.f1534f = a.getLong("main_fest_timestamp", 0);
    }

    /* renamed from: a */
    public JSONObject mo9519a() {
        JSONObject a = this.f1530b.mo9530a();
        JSONObject jSONObject = new JSONObject();
        if (a == null || a.length() <= 0) {
            return null;
        }
        for (String next : this.f1537j) {
            if (a.has(next)) {
                try {
                    jSONObject.put(next, a.opt(next));
                } catch (Exception e) {
                }
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    public JSONObject mo9522b() {
        if (this.f1531c.mo9540a().size() > 0) {
            this.f1530b.mo9538b(new C0614cl() {
                /* renamed from: a */
                public void mo9396a(Object obj, boolean z) {
                    if (obj instanceof String) {
                        UMCCAggregatedManager.this.f1531c.mo9545b();
                    }
                }
            }, this.f1531c.mo9540a());
        }
        return this.f1530b.mo9537b(new C0614cl());
    }

    /* renamed from: b */
    public void mo9523b(C0614cl clVar) {
        boolean z = false;
        if (this.f1533e) {
            if (this.f1534f == 0) {
                m2171h();
            }
            z = UMCCTimeRange.m2223a(System.currentTimeMillis(), this.f1534f);
        }
        if (!z) {
            m2169g();
            this.f1537j.clear();
        }
        this.f1531c.mo9545b();
        this.f1530b.mo9535a((C0614cl) new C0614cl() {
            /* renamed from: a */
            public void mo9396a(Object obj, boolean z) {
                if (obj.equals(GraphResponse.SUCCESS_KEY)) {
                    UMCCAggregatedManager.this.m2174i();
                }
            }
        }, z);
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m2174i() {
        for (Map.Entry<List<String>, UMCCAggregatedObject> key : this.f1529a.mo9476a().entrySet()) {
            List list = (List) key.getKey();
            if (!this.f1537j.contains(list)) {
                this.f1537j.add(UMCCDBUtils.m2039a((List<String>) list));
            }
        }
        if (this.f1537j.size() > 0) {
            this.f1530b.mo9533a(new C0614cl(), this.f1537j);
        }
    }

    /* renamed from: a */
    public void mo9520a(long j, long j2, String str) {
        this.f1530b.mo9532a(new C0614cl() {
            /* renamed from: a */
            public void mo9396a(Object obj, boolean z) {
                if (obj.equals(GraphResponse.SUCCESS_KEY)) {
                }
            }
        }, str, j, j2);
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m2175j() {
        try {
            if (this.f1529a.mo9476a().size() > 0) {
                this.f1530b.mo9539c(new C0614cl() {
                    /* renamed from: a */
                    public void mo9396a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            UMCCAggregatedManager.this.f1529a.mo9487d();
                        }
                    }
                }, this.f1529a.mo9476a());
            }
            if (this.f1531c.mo9540a().size() > 0) {
                this.f1530b.mo9538b(new C0614cl() {
                    /* renamed from: a */
                    public void mo9396a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            UMCCAggregatedManager.this.f1531c.mo9545b();
                        }
                    }
                }, this.f1531c.mo9540a());
            }
            if (this.f1537j.size() > 0) {
                this.f1530b.mo9533a(new C0614cl(), this.f1537j);
            }
        } catch (Throwable th) {
            MLog.m1836a("converyMemoryToDataTable happen error: " + th.toString());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public void m2177k() {
        try {
            if (this.f1529a.mo9476a().size() > 0) {
                this.f1530b.mo9534a((C0614cl) new C0614cl() {
                    /* renamed from: a */
                    public void mo9396a(Object obj, boolean z) {
                    }
                }, this.f1529a.mo9476a());
            }
            if (this.f1531c.mo9540a().size() > 0) {
                this.f1530b.mo9538b(new C0614cl() {
                    /* renamed from: a */
                    public void mo9396a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            UMCCAggregatedManager.this.f1531c.mo9545b();
                        }
                    }
                }, this.f1531c.mo9540a());
            }
            if (this.f1537j.size() > 0) {
                this.f1530b.mo9533a(new C0614cl(), this.f1537j);
            }
        } catch (Throwable th) {
            MLog.m1836a("convertMemoryToCacheTable happen error: " + th.toString());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: l */
    public void m2178l() {
        List<String> b = this.f1530b.mo9536b();
        if (b != null) {
            this.f1537j = b;
        }
    }

    /* renamed from: c */
    public void mo9524c() {
        m2177k();
    }

    /* renamed from: d */
    public void mo9525d() {
        m2177k();
    }
}
