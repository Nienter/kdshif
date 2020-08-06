package com.crashlytics.android.core;

import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import p005b.p006a.p007a.p008a.Fabric;

class CrashlyticsBackgroundWorker {
    private final ExecutorService executorService;

    public CrashlyticsBackgroundWorker(ExecutorService executorService2) {
        this.executorService = executorService2;
    }

    /* access modifiers changed from: package-private */
    public <T> T submitAndWait(Callable<T> callable) {
        try {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                return this.executorService.submit(callable).get(4, TimeUnit.SECONDS);
            }
            return this.executorService.submit(callable).get();
        } catch (RejectedExecutionException e) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        } catch (Exception e2) {
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Failed to execute task.", e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Future<?> submit(final Runnable runnable) {
        try {
            return this.executorService.submit(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Failed to execute task.", e);
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public <T> Future<T> submit(final Callable<T> callable) {
        try {
            return this.executorService.submit(new Callable<T>() {
                public T call() {
                    try {
                        return callable.call();
                    } catch (Exception e) {
                        Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Failed to execute task.", e);
                        return null;
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }
}
