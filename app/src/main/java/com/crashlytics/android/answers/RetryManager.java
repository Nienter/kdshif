package com.crashlytics.android.answers;

import p005b.p006a.p007a.p008a.p009a.p012c.p013a.RetryState;

class RetryManager {
    private static final long NANOSECONDS_IN_MS = 1000000;
    long lastRetry;
    private RetryState retryState;

    public RetryManager(RetryState eVar) {
        if (eVar == null) {
            throw new NullPointerException("retryState must not be null");
        }
        this.retryState = eVar;
    }

    public boolean canRetry(long j) {
        return j - this.lastRetry >= NANOSECONDS_IN_MS * this.retryState.mo8340a();
    }

    public void recordRetry(long j) {
        this.lastRetry = j;
        this.retryState = this.retryState.mo8341b();
    }

    public void reset() {
        this.lastRetry = 0;
        this.retryState = this.retryState.mo8342c();
    }
}
