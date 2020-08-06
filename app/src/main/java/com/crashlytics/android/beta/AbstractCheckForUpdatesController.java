package com.crashlytics.android.beta;

import android.annotation.SuppressLint;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p011b.ApiKey;
import p005b.p006a.p007a.p008a.p009a.p011b.CurrentTimeProvider;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStore;
import p005b.p006a.p007a.p008a.p009a.p017g.BetaSettingsData;

abstract class AbstractCheckForUpdatesController implements UpdatesController {
    static final long LAST_UPDATE_CHECK_DEFAULT = 0;
    static final String LAST_UPDATE_CHECK_KEY = "last_update_check";
    private static final long MILLIS_PER_SECOND = 1000;
    private Beta beta;
    private BetaSettingsData betaSettings;
    private BuildProperties buildProps;
    private Context context;
    private CurrentTimeProvider currentTimeProvider;
    private final AtomicBoolean externallyReady;
    private HttpRequestFactory httpRequestFactory;
    private IdManager idManager;
    private final AtomicBoolean initialized;
    private long lastCheckTimeMillis;
    private PreferenceStore preferenceStore;

    public AbstractCheckForUpdatesController() {
        this(false);
    }

    public AbstractCheckForUpdatesController(boolean z) {
        this.initialized = new AtomicBoolean();
        this.lastCheckTimeMillis = 0;
        this.externallyReady = new AtomicBoolean(z);
    }

    public void initialize(Context context2, Beta beta2, IdManager pVar, BetaSettingsData fVar, BuildProperties buildProperties, PreferenceStore cVar, CurrentTimeProvider kVar, HttpRequestFactory eVar) {
        this.context = context2;
        this.beta = beta2;
        this.idManager = pVar;
        this.betaSettings = fVar;
        this.buildProps = buildProperties;
        this.preferenceStore = cVar;
        this.currentTimeProvider = kVar;
        this.httpRequestFactory = eVar;
        if (signalInitialized()) {
            checkForUpdates();
        }
    }

    /* access modifiers changed from: protected */
    public boolean signalExternallyReady() {
        this.externallyReady.set(true);
        return this.initialized.get();
    }

    /* access modifiers changed from: package-private */
    public boolean signalInitialized() {
        this.initialized.set(true);
        return this.externallyReady.get();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"CommitPrefEdits"})
    public void checkForUpdates() {
        synchronized (this.preferenceStore) {
            if (this.preferenceStore.mo8481a().contains(LAST_UPDATE_CHECK_KEY)) {
                this.preferenceStore.mo8482a(this.preferenceStore.mo8483b().remove(LAST_UPDATE_CHECK_KEY));
            }
        }
        long a = this.currentTimeProvider.mo8282a();
        long j = ((long) this.betaSettings.f301b) * MILLIS_PER_SECOND;
        Fabric.m456h().mo8506a(Beta.TAG, "Check for updates delay: " + j);
        Fabric.m456h().mo8506a(Beta.TAG, "Check for updates last check time: " + getLastCheckTimeMillis());
        long lastCheckTimeMillis2 = j + getLastCheckTimeMillis();
        Fabric.m456h().mo8506a(Beta.TAG, "Check for updates current time: " + a + ", next check time: " + lastCheckTimeMillis2);
        if (a >= lastCheckTimeMillis2) {
            try {
                performUpdateCheck();
            } finally {
                setLastCheckTimeMillis(a);
            }
        } else {
            Fabric.m456h().mo8506a(Beta.TAG, "Check for updates next check time was not passed");
        }
    }

    private void performUpdateCheck() {
        Fabric.m456h().mo8506a(Beta.TAG, "Performing update check");
        new CheckForUpdatesRequest(this.beta, this.beta.getOverridenSpiEndpoint(), this.betaSettings.f300a, this.httpRequestFactory, new CheckForUpdatesResponseTransform()).invoke(new ApiKey().mo8272a(this.context), this.idManager.mo8297h().get(IdManager.C0430a.FONT_TOKEN), this.buildProps);
    }

    /* access modifiers changed from: package-private */
    public void setLastCheckTimeMillis(long j) {
        this.lastCheckTimeMillis = j;
    }

    /* access modifiers changed from: package-private */
    public long getLastCheckTimeMillis() {
        return this.lastCheckTimeMillis;
    }
}
