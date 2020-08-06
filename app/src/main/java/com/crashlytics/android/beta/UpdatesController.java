package com.crashlytics.android.beta;

import android.content.Context;
import p005b.p006a.p007a.p008a.p009a.p011b.CurrentTimeProvider;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStore;
import p005b.p006a.p007a.p008a.p009a.p017g.BetaSettingsData;

interface UpdatesController {
    void initialize(Context context, Beta beta, IdManager pVar, BetaSettingsData fVar, BuildProperties buildProperties, PreferenceStore cVar, CurrentTimeProvider kVar, HttpRequestFactory eVar);

    boolean isActivityLifecycleTriggered();
}
