package com.crashlytics.android.answers;

import com.crashlytics.android.answers.SessionEvent;
import p005b.p006a.p007a.p008a.p009a.p014d.FileRollOverManager;
import p005b.p006a.p007a.p008a.p009a.p017g.AnalyticsSettingsData;

interface SessionAnalyticsManagerStrategy extends FileRollOverManager {
    void deleteAllEvents();

    void processEvent(SessionEvent.Builder builder);

    void sendEvents();

    void setAnalyticsSettingsData(AnalyticsSettingsData bVar, String str);
}
