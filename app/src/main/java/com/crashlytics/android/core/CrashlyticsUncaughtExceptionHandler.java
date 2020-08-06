package com.crashlytics.android.core;

import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;
import p005b.p006a.p007a.p008a.Fabric;

class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);

    interface CrashListener {
        void onUncaughtException(Thread thread, Throwable th);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener2, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.crashListener = crashListener2;
        this.defaultHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String str;
        this.isHandlingException.set(true);
        try {
            this.crashListener.onUncaughtException(thread, th);
        } catch (Exception e) {
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An error occurred in the uncaught exception handler", e);
        } finally {
            str = "Crashlytics completed exception processing. Invoking default exception handler.";
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, str);
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }
}
