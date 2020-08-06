package com.crashlytics.android.answers;

import android.content.Context;
import java.util.UUID;
import p005b.p006a.p007a.p008a.p009a.p011b.CurrentTimeProvider;
import p005b.p006a.p007a.p008a.p009a.p014d.EventsFilesManager;
import p005b.p006a.p007a.p008a.p009a.p014d.EventsStorage;
import p005b.p006a.p007a.p008a.p009a.p017g.AnalyticsSettingsData;

class SessionAnalyticsFilesManager extends EventsFilesManager<SessionEvent> {
    private static final String SESSION_ANALYTICS_TO_SEND_FILE_EXTENSION = ".tap";
    private static final String SESSION_ANALYTICS_TO_SEND_FILE_PREFIX = "sa";
    private AnalyticsSettingsData analyticsSettingsData;

    SessionAnalyticsFilesManager(Context context, SessionEventTransform sessionEventTransform, CurrentTimeProvider kVar, EventsStorage cVar) {
        super(context, sessionEventTransform, kVar, cVar, 100);
    }

    /* access modifiers changed from: protected */
    public String generateUniqueRollOverFileName() {
        return SESSION_ANALYTICS_TO_SEND_FILE_PREFIX + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + UUID.randomUUID().toString() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.currentTimeProvider.mo8282a() + SESSION_ANALYTICS_TO_SEND_FILE_EXTENSION;
    }

    /* access modifiers changed from: protected */
    public int getMaxFilesToKeep() {
        return this.analyticsSettingsData == null ? super.getMaxFilesToKeep() : this.analyticsSettingsData.f272e;
    }

    /* access modifiers changed from: protected */
    public int getMaxByteSizePerFile() {
        return this.analyticsSettingsData == null ? super.getMaxByteSizePerFile() : this.analyticsSettingsData.f270c;
    }

    /* access modifiers changed from: package-private */
    public void setAnalyticsSettingsData(AnalyticsSettingsData bVar) {
        this.analyticsSettingsData = bVar;
    }
}
