package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStore;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStoreImpl;

class AnswersPreferenceManager {
    static final String PREFKEY_ANALYTICS_LAUNCHED = "analytics_launched";
    static final String PREF_STORE_NAME = "settings";
    private final PreferenceStore prefStore;

    public static AnswersPreferenceManager build(Context context) {
        return new AnswersPreferenceManager(new PreferenceStoreImpl(context, PREF_STORE_NAME));
    }

    AnswersPreferenceManager(PreferenceStore cVar) {
        this.prefStore = cVar;
    }

    @SuppressLint({"CommitPrefEdits"})
    public void setAnalyticsLaunched() {
        this.prefStore.mo8482a(this.prefStore.mo8483b().putBoolean(PREFKEY_ANALYTICS_LAUNCHED, true));
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean hasAnalyticsLaunched() {
        return this.prefStore.mo8481a().getBoolean(PREFKEY_ANALYTICS_LAUNCHED, false);
    }
}
