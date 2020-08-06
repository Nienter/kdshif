package com.crashlytics.android.answers;

import com.crashlytics.android.answers.SessionEvent;
import p005b.p006a.p007a.p008a.p009a.p017g.AnalyticsSettingsData;

class DisabledSessionAnalyticsManagerStrategy implements SessionAnalyticsManagerStrategy {
    DisabledSessionAnalyticsManagerStrategy() {
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData bVar, String str) {
    }

    public void processEvent(SessionEvent.Builder builder) {
    }

    public void sendEvents() {
    }

    public void deleteAllEvents() {
    }

    public boolean rollFileOver() {
        return false;
    }

    public void scheduleTimeBasedRollOverIfNeeded() {
    }

    public void cancelTimeBasedFileRollOver() {
    }
}
