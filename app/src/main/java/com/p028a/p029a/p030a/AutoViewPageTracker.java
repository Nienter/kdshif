package com.p028a.p029a.p030a;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.p028a.p029a.p030a.UMStoreManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.a.a.a.p */
public class AutoViewPageTracker {

    /* renamed from: a */
    public static String f1628a = null;

    /* renamed from: d */
    private static JSONObject f1629d = new JSONObject();

    /* renamed from: b */
    Application.ActivityLifecycleCallbacks f1630b = new Application.ActivityLifecycleCallbacks() {
        public void onActivityStopped(Activity activity) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityResumed(Activity activity) {
            AutoViewPageTracker.this.m2333b(activity);
        }

        public void onActivityPaused(Activity activity) {
            AutoViewPageTracker.this.m2336c(activity);
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }
    };

    /* renamed from: c */
    private final Map<String, Long> f1631c = new HashMap();

    /* renamed from: e */
    private Application f1632e = null;

    public AutoViewPageTracker(Activity activity) {
        if (activity != null) {
            this.f1632e = activity.getApplication();
            m2331a(activity);
        }
    }

    /* renamed from: a */
    private void m2331a(Activity activity) {
        this.f1632e.registerActivityLifecycleCallbacks(this.f1630b);
        if (f1628a == null) {
            m2333b(activity);
        }
    }

    /* renamed from: a */
    public void mo9608a() {
        if (this.f1632e != null) {
            this.f1632e.unregisterActivityLifecycleCallbacks(this.f1630b);
        }
    }

    /* renamed from: a */
    public void mo9609a(Context context) {
        m2336c(null);
        mo9608a();
    }

    /* renamed from: b */
    public static void m2334b(Context context) {
        try {
            synchronized (f1629d) {
                if (f1629d.length() > 0) {
                    UMStoreManager.m2242a(context).mo9559a(SessionTracker.m1349a(), f1629d, UMStoreManager.C0636a.AUTOPAGE);
                    f1629d = new JSONObject();
                }
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2333b(Activity activity) {
        f1628a = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized (this.f1631c) {
            this.f1631c.put(f1628a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m2336c(Activity activity) {
        long j = 0;
        try {
            synchronized (this.f1631c) {
                if (this.f1631c.containsKey(f1628a)) {
                    j = System.currentTimeMillis() - this.f1631c.get(f1628a).longValue();
                    this.f1631c.remove(f1628a);
                }
            }
            synchronized (f1629d) {
                try {
                    f1629d = new JSONObject();
                    f1629d.put("page_name", f1628a);
                    f1629d.put("duration", j);
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
