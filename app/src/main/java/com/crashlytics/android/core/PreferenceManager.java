package com.crashlytics.android.core;

import android.annotation.SuppressLint;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStore;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStoreImpl;

@SuppressLint({"CommitPrefEdits"})
class PreferenceManager {
    static final String PREF_ALWAYS_SEND_REPORTS_KEY = "always_send_reports_opt_in";
    private static final String PREF_MIGRATION_COMPLETE = "preferences_migration_complete";
    private static final boolean SHOULD_ALWAYS_SEND_REPORTS_DEFAULT = false;
    private final PreferenceStore preferenceStore;

    public static PreferenceManager create(PreferenceStore cVar, CrashlyticsCore crashlyticsCore) {
        if (!cVar.mo8481a().getBoolean(PREF_MIGRATION_COMPLETE, false)) {
            PreferenceStoreImpl dVar = new PreferenceStoreImpl(crashlyticsCore);
            if (!cVar.mo8481a().contains(PREF_ALWAYS_SEND_REPORTS_KEY) && dVar.mo8481a().contains(PREF_ALWAYS_SEND_REPORTS_KEY)) {
                cVar.mo8482a(cVar.mo8483b().putBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, dVar.mo8481a().getBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, false)));
            }
            cVar.mo8482a(cVar.mo8483b().putBoolean(PREF_MIGRATION_COMPLETE, true));
        }
        return new PreferenceManager(cVar);
    }

    public PreferenceManager(PreferenceStore cVar) {
        this.preferenceStore = cVar;
    }

    /* access modifiers changed from: package-private */
    public void setShouldAlwaysSendReports(boolean z) {
        this.preferenceStore.mo8482a(this.preferenceStore.mo8483b().putBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, z));
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAlwaysSendReports() {
        return this.preferenceStore.mo8481a().getBoolean(PREF_ALWAYS_SEND_REPORTS_KEY, false);
    }
}
