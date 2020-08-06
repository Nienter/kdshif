package com.p028a.p029a.p030a;

import com.p028a.p029a.AnalyticsConfig;
import java.lang.Thread;

/* renamed from: com.a.a.a.s */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private Thread.UncaughtExceptionHandler f1665a;

    /* renamed from: b */
    private OnAppCrashHandler f1666b;

    public CrashHandler() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.f1665a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    /* renamed from: a */
    public void mo9627a(OnAppCrashHandler xVar) {
        this.f1666b = xVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        m2377a(th);
        if (this.f1665a != null && this.f1665a != Thread.getDefaultUncaughtExceptionHandler()) {
            this.f1665a.uncaughtException(thread, th);
        }
    }

    /* renamed from: a */
    private void m2377a(Throwable th) {
        if (AnalyticsConfig.f1161f) {
            this.f1666b.mo9634a(th);
        } else {
            this.f1666b.mo9634a(null);
        }
    }
}
