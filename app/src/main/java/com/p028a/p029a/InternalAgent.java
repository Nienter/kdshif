package com.p028a.p029a;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import com.facebook.places.model.PlaceFields;
import com.p028a.p029a.p030a.AutoViewPageTracker;
import com.p028a.p029a.p030a.C0614cl;
import com.p028a.p029a.p030a.CacheService;
import com.p028a.p029a.p030a.CrashHandler;
import com.p028a.p029a.p030a.DataHelper;
import com.p028a.p029a.p030a.EventTracker;
import com.p028a.p029a.p030a.ISysListener;
import com.p028a.p029a.p030a.MLog;
import com.p028a.p029a.p030a.OnAppCrashHandler;
import com.p028a.p029a.p030a.PreferenceWrapper;
import com.p028a.p029a.p030a.QueuedWork;
import com.p028a.p029a.p030a.SafeRunnable;
import com.p028a.p029a.p030a.SessionTracker;
import com.p028a.p029a.p030a.UMCCAggregatedManager;
import com.p028a.p029a.p030a.UMStoreManager;
import com.p028a.p029a.p030a.ViewPageTracker;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.a.a.d */
public class InternalAgent implements OnAppCrashHandler {

    /* renamed from: a */
    private Context f1694a = null;

    /* renamed from: b */
    private ISysListener f1695b;

    /* renamed from: c */
    private CrashHandler f1696c = new CrashHandler();

    /* renamed from: d */
    private ViewPageTracker f1697d = new ViewPageTracker();

    /* renamed from: e */
    private SessionTracker f1698e = new SessionTracker();

    /* renamed from: f */
    private EventTracker f1699f = null;

    /* renamed from: g */
    private CacheService f1700g = null;

    /* renamed from: h */
    private AutoViewPageTracker f1701h = null;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public UMCCAggregatedManager f1702i = null;

    /* renamed from: j */
    private boolean f1703j = false;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public boolean f1704k = false;

    /* renamed from: l */
    private JSONObject f1705l = null;

    /* renamed from: m */
    private boolean f1706m = false;

    InternalAgent() {
        this.f1696c.mo9627a((OnAppCrashHandler) this);
    }

    /* renamed from: c */
    private void m2418c(Context context) {
        if (context != null) {
            try {
                if (Build.VERSION.SDK_INT > 13 && !this.f1706m && (context instanceof Activity)) {
                    this.f1701h = new AutoViewPageTracker((Activity) context);
                    this.f1706m = true;
                }
                if (!this.f1703j) {
                    this.f1694a = context.getApplicationContext();
                    this.f1699f = new EventTracker(this.f1694a);
                    this.f1700g = CacheService.m2369b(this.f1694a);
                    this.f1703j = true;
                    if (this.f1702i == null) {
                        this.f1702i = UMCCAggregatedManager.m2160a(this.f1694a);
                    }
                    if (!this.f1704k) {
                        QueuedWork.m1849b(new SafeRunnable() {
                            /* renamed from: a */
                            public void mo9386a() {
                                InternalAgent.this.f1702i.mo9521a((C0614cl) new C0614cl() {
                                    /* renamed from: a */
                                    public void mo9396a(Object obj, boolean z) {
                                        boolean unused = InternalAgent.this.f1704k = true;
                                    }
                                });
                            }
                        });
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo9639a(final Context context) {
        if (context == null) {
            MLog.m1844c("unexpected null context in onResume");
            return;
        }
        if (AnalyticsConfig.f1160e) {
            this.f1697d.mo9102a(context.getClass().getName());
        }
        try {
            if (!this.f1703j || !this.f1706m) {
                m2418c(context);
            }
            QueuedWork.m1847a(new SafeRunnable() {
                /* renamed from: a */
                public void mo9386a() {
                    InternalAgent.this.m2419d(context.getApplicationContext());
                }
            });
        } catch (Exception e) {
            MLog.m1838a("Exception occurred in Mobclick.onResume(). ", (Throwable) e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo9642b(final Context context) {
        if (context == null) {
            MLog.m1844c("unexpected null context in onPause");
            return;
        }
        if (AnalyticsConfig.f1160e) {
            this.f1697d.mo9103b(context.getClass().getName());
        }
        try {
            if (!this.f1703j || !this.f1706m) {
                m2418c(context);
            }
            QueuedWork.m1847a(new SafeRunnable() {
                /* renamed from: a */
                public void mo9386a() {
                    InternalAgent.this.m2420e(context.getApplicationContext());
                    InternalAgent.this.f1702i.mo9525d();
                }
            });
        } catch (Exception e) {
            if (MLog.f1376a) {
                MLog.m1838a("Exception occurred in Mobclick.onRause(). ", (Throwable) e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m2419d(Context context) {
        this.f1698e.mo9085c(context);
        if (this.f1695b != null) {
            this.f1695b.mo9370a();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m2420e(Context context) {
        this.f1698e.mo9086d(context);
        ViewPageTracker.m1373a(context);
        AutoViewPageTracker.m2334b(context);
        this.f1700g.mo9626a(this.f1694a).mo9619a(context);
        if (this.f1695b != null) {
            this.f1695b.mo9371b();
        }
    }

    /* renamed from: a */
    public void mo9640a(Context context, String str, String str2, long j, int i) {
        try {
            if (!this.f1703j || !this.f1706m) {
                m2418c(context);
            }
            this.f1699f.mo9629a(str, str2, j, i);
        } catch (Exception e) {
            if (MLog.f1376a) {
                MLog.m1840a((Throwable) e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo9641a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (!this.f1703j || !this.f1706m) {
                m2418c(context);
            }
            this.f1699f.mo9630a(str, map, j);
        } catch (Exception e) {
            if (MLog.f1376a) {
                MLog.m1840a((Throwable) e);
            }
        }
    }

    /* renamed from: a */
    public void mo9634a(Throwable th) {
        try {
            this.f1697d.mo9101a();
            if (this.f1694a != null) {
                if (!(th == null || this.f1700g == null)) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONObject.put("error_source", 1);
                    jSONObject.put(PlaceFields.CONTEXT, DataHelper.m1772a(th));
                    UMStoreManager.m2242a(this.f1694a).mo9558a(SessionTracker.m1349a(), jSONObject.toString(), 1);
                }
                this.f1702i.mo9524c();
                this.f1701h.mo9609a(this.f1694a);
                m2420e(this.f1694a);
                PreferenceWrapper.m1333a(this.f1694a).edit().commit();
            }
            QueuedWork.m1846a();
        } catch (Exception e) {
            if (MLog.f1376a) {
                MLog.m1838a("Exception in onAppCrash", (Throwable) e);
            }
        }
    }
}
