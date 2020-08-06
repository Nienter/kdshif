package com.snaperfect.style.daguerre.application;

import android.app.Application;
import android.util.DisplayMetrics;
import com.crashlytics.android.Crashlytics;
import com.snaperfect.style.daguerre.utils.StoreUtils;
import com.squareup.leakcanary.LeakCanary;
import p005b.p006a.p007a.p008a.Fabric;

public class DaguerreApp extends Application {

    /* renamed from: a */
    public static DisplayMetrics f1910a;

    public void onCreate() {
        super.onCreate();
        f1910a = getResources().getDisplayMetrics();
        StoreUtils.m3115a(getApplicationContext());
        Fabric.m446a(getApplicationContext(), new Crashlytics());
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }
}
