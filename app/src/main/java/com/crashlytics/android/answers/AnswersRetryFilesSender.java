package com.crashlytics.android.answers;

import java.io.File;
import java.util.List;
import p005b.p006a.p007a.p008a.p009a.p012c.p013a.DefaultRetryPolicy;
import p005b.p006a.p007a.p008a.p009a.p012c.p013a.ExponentialBackoff;
import p005b.p006a.p007a.p008a.p009a.p012c.p013a.RetryState;
import p005b.p006a.p007a.p008a.p009a.p014d.FilesSender;

class AnswersRetryFilesSender implements FilesSender {
    private static final int BACKOFF_MS = 1000;
    private static final int BACKOFF_POWER = 8;
    private static final double JITTER_PERCENT = 0.1d;
    private static final int MAX_RETRIES = 5;
    private final SessionAnalyticsFilesSender filesSender;
    private final RetryManager retryManager;

    public static AnswersRetryFilesSender build(SessionAnalyticsFilesSender sessionAnalyticsFilesSender) {
        return new AnswersRetryFilesSender(sessionAnalyticsFilesSender, new RetryManager(new RetryState(new RandomBackoff(new ExponentialBackoff(1000, 8), JITTER_PERCENT), new DefaultRetryPolicy(5))));
    }

    AnswersRetryFilesSender(SessionAnalyticsFilesSender sessionAnalyticsFilesSender, RetryManager retryManager2) {
        this.filesSender = sessionAnalyticsFilesSender;
        this.retryManager = retryManager2;
    }

    public boolean send(List<File> list) {
        long nanoTime = System.nanoTime();
        if (!this.retryManager.canRetry(nanoTime)) {
            return false;
        }
        if (this.filesSender.send(list)) {
            this.retryManager.reset();
            return true;
        }
        this.retryManager.recordRetry(nanoTime);
        return false;
    }
}
