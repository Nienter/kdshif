package com.crashlytics.android.answers;

import android.content.Context;
import android.os.Looper;
import p005b.p006a.p007a.p008a.p009a.p011b.SystemCurrentTimeProvider;
import p005b.p006a.p007a.p008a.p009a.p014d.GZIPQueueFileEventStorage;
import p005b.p006a.p007a.p008a.p009a.p016f.FileStore;

class AnswersFilesManagerProvider {
    static final String SESSION_ANALYTICS_FILE_NAME = "session_analytics.tap";
    static final String SESSION_ANALYTICS_TO_SEND_DIR = "session_analytics_to_send";
    final Context context;
    final FileStore fileStore;

    public AnswersFilesManagerProvider(Context context2, FileStore aVar) {
        this.context = context2;
        this.fileStore = aVar;
    }

    public SessionAnalyticsFilesManager getAnalyticsFilesManager() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("AnswersFilesManagerProvider cannot be called on the main thread");
        }
        return new SessionAnalyticsFilesManager(this.context, new SessionEventTransform(), new SystemCurrentTimeProvider(), new GZIPQueueFileEventStorage(this.context, this.fileStore.mo8479a(), SESSION_ANALYTICS_FILE_NAME, SESSION_ANALYTICS_TO_SEND_DIR));
    }
}
