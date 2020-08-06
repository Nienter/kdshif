package com.crashlytics.android.core;

import android.content.Context;
import android.util.Log;
import com.crashlytics.android.core.internal.CrashEventDataProvider;
import com.crashlytics.android.core.internal.models.SessionEventData;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.net.ssl.HttpsURLConnection;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.ApiKey;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.ExecutorUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.FirebaseInfo;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p012c.C0449l;
import p005b.p006a.p007a.p008a.p009a.p012c.DependsOn;
import p005b.p006a.p007a.p008a.p009a.p012c.Priority;
import p005b.p006a.p007a.p008a.p009a.p012c.PriorityCallable;
import p005b.p006a.p007a.p008a.p009a.p012c.UnmetDependencyException;
import p005b.p006a.p007a.p008a.p009a.p015e.DefaultHttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpMethod;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequest;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p016f.FileStoreImpl;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStoreImpl;
import p005b.p006a.p007a.p008a.p009a.p017g.Settings;
import p005b.p006a.p007a.p008a.p009a.p017g.SettingsData;

@DependsOn(mo8365a = {CrashEventDataProvider.class})
public class CrashlyticsCore extends Kit<Void> {
    static final float CLS_DEFAULT_PROCESS_DELAY = 1.0f;
    static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
    static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
    static final String CRASH_MARKER_FILE_NAME = "crash_marker";
    static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 4;
    private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
    static final int MAX_ATTRIBUTES = 64;
    static final int MAX_ATTRIBUTE_SIZE = 1024;
    private static final String MISSING_BUILD_ID_MSG = "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.";
    private static final String PREFERENCE_STORE_NAME = "com.crashlytics.android.core.CrashlyticsCore";
    public static final String TAG = "CrashlyticsCore";
    private final ConcurrentHashMap<String, String> attributes;
    private CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsController controller;
    private CrashlyticsFileMarker crashMarker;
    private float delay;
    private boolean disabled;
    private CrashEventDataProvider externalCrashEventDataProvider;
    private HttpRequestFactory httpRequestFactory;
    /* access modifiers changed from: private */
    public CrashlyticsFileMarker initializationMarker;
    private CrashlyticsListener listener;
    private final PinningInfoProvider pinningInfo;
    private final long startTime;
    private String userEmail;
    private String userId;
    private String userName;

    public static class Builder {
        private float delay = -1.0f;
        private boolean disabled = false;
        private CrashlyticsListener listener;
        private PinningInfoProvider pinningInfoProvider;

        public Builder delay(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("delay must be greater than 0");
            } else if (this.delay > 0.0f) {
                throw new IllegalStateException("delay already set.");
            } else {
                this.delay = f;
                return this;
            }
        }

        public Builder listener(CrashlyticsListener crashlyticsListener) {
            if (crashlyticsListener == null) {
                throw new IllegalArgumentException("listener must not be null.");
            } else if (this.listener != null) {
                throw new IllegalStateException("listener already set.");
            } else {
                this.listener = crashlyticsListener;
                return this;
            }
        }

        @Deprecated
        public Builder pinningInfo(PinningInfoProvider pinningInfoProvider2) {
            if (pinningInfoProvider2 == null) {
                throw new IllegalArgumentException("pinningInfoProvider must not be null.");
            } else if (this.pinningInfoProvider != null) {
                throw new IllegalStateException("pinningInfoProvider already set.");
            } else {
                this.pinningInfoProvider = pinningInfoProvider2;
                return this;
            }
        }

        public Builder disabled(boolean z) {
            this.disabled = z;
            return this;
        }

        public CrashlyticsCore build() {
            if (this.delay < 0.0f) {
                this.delay = CrashlyticsCore.CLS_DEFAULT_PROCESS_DELAY;
            }
            return new CrashlyticsCore(this.delay, this.listener, this.pinningInfoProvider, this.disabled);
        }
    }

    private static final class CrashMarkerCheck implements Callable<Boolean> {
        private final CrashlyticsFileMarker crashMarker;

        public CrashMarkerCheck(CrashlyticsFileMarker crashlyticsFileMarker) {
            this.crashMarker = crashlyticsFileMarker;
        }

        public Boolean call() {
            if (!this.crashMarker.isPresent()) {
                return Boolean.FALSE;
            }
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Found previous crash marker.");
            this.crashMarker.remove();
            return Boolean.TRUE;
        }
    }

    private static final class NoOpListener implements CrashlyticsListener {
        private NoOpListener() {
        }

        public void crashlyticsDidDetectCrashDuringPreviousExecution() {
        }
    }

    public CrashlyticsCore() {
        this(CLS_DEFAULT_PROCESS_DELAY, null, null, false);
    }

    CrashlyticsCore(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z) {
        this(f, crashlyticsListener, pinningInfoProvider, z, ExecutorUtils.m170a("Crashlytics Exception Handler"));
    }

    CrashlyticsCore(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z, ExecutorService executorService) {
        this.userId = null;
        this.userEmail = null;
        this.userName = null;
        this.delay = f;
        this.listener = crashlyticsListener == null ? new NoOpListener() : crashlyticsListener;
        this.pinningInfo = pinningInfoProvider;
        this.disabled = z;
        this.backgroundWorker = new CrashlyticsBackgroundWorker(executorService);
        this.attributes = new ConcurrentHashMap<>();
        this.startTime = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public boolean onPreExecute() {
        return onPreExecute(super.getContext());
    }

    /* access modifiers changed from: package-private */
    public boolean onPreExecute(Context context) {
        if (this.disabled) {
            return false;
        }
        String a = new ApiKey().mo8272a(context);
        if (a == null) {
            return false;
        }
        String m = CommonUtils.m164m(context);
        if (!isBuildIdValid(m, CommonUtils.m143a(context, CRASHLYTICS_REQUIRE_BUILD_ID, (boolean) CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT))) {
            throw new UnmetDependencyException(MISSING_BUILD_ID_MSG);
        }
        try {
            Fabric.m456h().mo8511c(TAG, "Initializing Crashlytics " + getVersion());
            FileStoreImpl bVar = new FileStoreImpl(this);
            this.crashMarker = new CrashlyticsFileMarker(CRASH_MARKER_FILE_NAME, bVar);
            this.initializationMarker = new CrashlyticsFileMarker(INITIALIZATION_MARKER_FILE_NAME, bVar);
            PreferenceManager create = PreferenceManager.create(new PreferenceStoreImpl(getContext(), PREFERENCE_STORE_NAME), this);
            CrashlyticsPinningInfoProvider crashlyticsPinningInfoProvider = this.pinningInfo != null ? new CrashlyticsPinningInfoProvider(this.pinningInfo) : null;
            this.httpRequestFactory = new DefaultHttpRequestFactory(Fabric.m456h());
            this.httpRequestFactory.mo8421a(crashlyticsPinningInfoProvider);
            IdManager idManager = getIdManager();
            AppData create2 = AppData.create(context, idManager, a, m);
            ManifestUnityVersionProvider manifestUnityVersionProvider = new ManifestUnityVersionProvider(context, create2.packageName);
            Fabric.m456h().mo8506a(TAG, "Installer package name is: " + create2.installerPackageName);
            this.controller = new CrashlyticsController(this, this.backgroundWorker, this.httpRequestFactory, idManager, create, bVar, create2, manifestUnityVersionProvider, new FirebaseInfo().mo8289b(context));
            boolean didPreviousInitializationFail = didPreviousInitializationFail();
            checkForPreviousCrash();
            this.controller.enableExceptionHandling(Thread.getDefaultUncaughtExceptionHandler());
            if (!didPreviousInitializationFail || !CommonUtils.m165n(context)) {
                Fabric.m456h().mo8506a(TAG, "Exception handling initialization successful");
                return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
            }
            Fabric.m456h().mo8506a(TAG, "Crashlytics did not finish previous background initialization. Initializing synchronously.");
            finishInitSynchronously();
            return false;
        } catch (Exception e) {
            Fabric.m456h().mo8516e(TAG, "Crashlytics was not started due to an exception during initialization", e);
            this.controller = null;
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public Void doInBackground() {
        markInitializationStarted();
        SessionEventData externalCrashEventData = getExternalCrashEventData();
        if (externalCrashEventData != null) {
            this.controller.writeExternalCrashEvent(externalCrashEventData);
        }
        this.controller.cleanInvalidTempFiles();
        try {
            SettingsData b = Settings.m419a().mo8500b();
            if (b == null) {
                Fabric.m456h().mo8513d(TAG, "Received null settings, skipping report submission!");
            } else if (!b.f341d.f312c) {
                Fabric.m456h().mo8506a(TAG, "Collection of crash reports disabled in Crashlytics settings.");
                markInitializationComplete();
            } else {
                if (!this.controller.finalizeSessions(b.f339b)) {
                    Fabric.m456h().mo8506a(TAG, "Could not finalize previous sessions.");
                }
                this.controller.submitAllReports(this.delay, b);
                markInitializationComplete();
            }
        } catch (Exception e) {
            Fabric.m456h().mo8516e(TAG, "Crashlytics encountered a problem during asynchronous initialization.", e);
        } finally {
            markInitializationComplete();
        }
        return null;
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android.crashlytics-core";
    }

    public String getVersion() {
        return "2.4.1.19";
    }

    public static CrashlyticsCore getInstance() {
        return (CrashlyticsCore) Fabric.m447a(CrashlyticsCore.class);
    }

    public PinningInfoProvider getPinningInfoProvider() {
        if (!this.disabled) {
            return this.pinningInfo;
        }
        return null;
    }

    public void logException(Throwable th) {
        if (this.disabled || !ensureFabricWithCalled("prior to logging exceptions.")) {
            return;
        }
        if (th == null) {
            Fabric.m456h().mo8504a(5, TAG, "Crashlytics is ignoring a request to log a null exception.");
        } else {
            this.controller.writeNonFatalException(Thread.currentThread(), th);
        }
    }

    public void log(String str) {
        doLog(3, TAG, str);
    }

    private void doLog(int i, String str, String str2) {
        if (!this.disabled && ensureFabricWithCalled("prior to logging messages.")) {
            this.controller.writeToLog(System.currentTimeMillis() - this.startTime, formatLogMessage(i, str, str2));
        }
    }

    public void log(int i, String str, String str2) {
        doLog(i, str, str2);
        Fabric.m456h().mo8505a(i, "" + str, "" + str2, CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT);
    }

    public void setUserIdentifier(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userId = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setUserName(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userName = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setUserEmail(String str) {
        if (!this.disabled && ensureFabricWithCalled("prior to setting user data.")) {
            this.userEmail = sanitizeAttribute(str);
            this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
        }
    }

    public void setString(String str, String str2) {
        if (this.disabled || !ensureFabricWithCalled("prior to setting keys.")) {
            return;
        }
        if (str == null) {
            Context context = getContext();
            if (context == null || !CommonUtils.m160i(context)) {
                Fabric.m456h().mo8516e(TAG, "Attempting to set custom attribute with null key, ignoring.", null);
                return;
            }
            throw new IllegalArgumentException("Custom attribute key must not be null.");
        }
        String sanitizeAttribute = sanitizeAttribute(str);
        if (this.attributes.size() < 64 || this.attributes.containsKey(sanitizeAttribute)) {
            this.attributes.put(sanitizeAttribute, str2 == null ? "" : sanitizeAttribute(str2));
            this.controller.cacheKeyData(this.attributes);
            return;
        }
        Fabric.m456h().mo8506a(TAG, "Exceeded maximum number of custom attributes (64)");
    }

    public void setBool(String str, boolean z) {
        setString(str, Boolean.toString(z));
    }

    public void setDouble(String str, double d) {
        setString(str, Double.toString(d));
    }

    public void setFloat(String str, float f) {
        setString(str, Float.toString(f));
    }

    public void setInt(String str, int i) {
        setString(str, Integer.toString(i));
    }

    public void setLong(String str, long j) {
        setString(str, Long.toString(j));
    }

    public void crash() {
        new CrashTest().indexOutOfBounds();
    }

    public boolean verifyPinning(URL url) {
        try {
            return internalVerifyPinning(url);
        } catch (Exception e) {
            Fabric.m456h().mo8516e(TAG, "Could not verify SSL pinning", e);
            return false;
        }
    }

    @Deprecated
    public synchronized void setListener(CrashlyticsListener crashlyticsListener) {
        Fabric.m456h().mo8513d(TAG, "Use of setListener is deprecated.");
        if (crashlyticsListener == null) {
            throw new IllegalArgumentException("listener must not be null.");
        }
        this.listener = crashlyticsListener;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    /* access modifiers changed from: package-private */
    public CrashlyticsController getController() {
        return this.controller;
    }

    /* access modifiers changed from: package-private */
    public String getUserIdentifier() {
        if (getIdManager().mo8290a()) {
            return this.userId;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getUserEmail() {
        if (getIdManager().mo8290a()) {
            return this.userEmail;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getUserName() {
        if (getIdManager().mo8290a()) {
            return this.userName;
        }
        return null;
    }

    private void finishInitSynchronously() {
        C07071 r1 = new PriorityCallable<Void>() {
            public Void call() {
                return CrashlyticsCore.this.doInBackground();
            }

            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        for (C0449l addDependency : getDependencies()) {
            r1.addDependency(addDependency);
        }
        Future submit = getFabric().mo8526f().submit(r1);
        Fabric.m456h().mo8506a(TAG, "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            submit.get(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Fabric.m456h().mo8516e(TAG, "Crashlytics was interrupted during initialization.", e);
        } catch (ExecutionException e2) {
            Fabric.m456h().mo8516e(TAG, "Problem encountered during Crashlytics initialization.", e2);
        } catch (TimeoutException e3) {
            Fabric.m456h().mo8516e(TAG, "Crashlytics timed out during initialization.", e3);
        }
    }

    /* access modifiers changed from: package-private */
    public void markInitializationStarted() {
        this.backgroundWorker.submitAndWait(new Callable<Void>() {
            public Void call() {
                CrashlyticsCore.this.initializationMarker.create();
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Initialization marker file created.");
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void markInitializationComplete() {
        this.backgroundWorker.submit(new Callable<Boolean>() {
            public Boolean call() {
                try {
                    boolean remove = CrashlyticsCore.this.initializationMarker.remove();
                    Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Initialization marker file removed: " + remove);
                    return Boolean.valueOf(remove);
                } catch (Exception e) {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Problem encountered deleting Crashlytics initialization marker.", e);
                    return false;
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean didPreviousInitializationFail() {
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() {
            public Boolean call() {
                return Boolean.valueOf(CrashlyticsCore.this.initializationMarker.isPresent());
            }
        })).booleanValue();
    }

    /* access modifiers changed from: package-private */
    public void setExternalCrashEventDataProvider(CrashEventDataProvider crashEventDataProvider) {
        this.externalCrashEventDataProvider = crashEventDataProvider;
    }

    /* access modifiers changed from: package-private */
    public SessionEventData getExternalCrashEventData() {
        if (this.externalCrashEventDataProvider != null) {
            return this.externalCrashEventDataProvider.getCrashEventData();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean internalVerifyPinning(URL url) {
        if (getPinningInfoProvider() == null) {
            return false;
        }
        HttpRequest a = this.httpRequestFactory.mo8419a(HttpMethod.GET, url.toString());
        ((HttpsURLConnection) a.mo8435a()).setInstanceFollowRedirects(false);
        a.mo8436b();
        return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
    }

    private void checkForPreviousCrash() {
        if (Boolean.TRUE.equals((Boolean) this.backgroundWorker.submitAndWait(new CrashMarkerCheck(this.crashMarker)))) {
            try {
                this.listener.crashlyticsDidDetectCrashDuringPreviousExecution();
            } catch (Exception e) {
                Fabric.m456h().mo8516e(TAG, "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void createCrashMarker() {
        this.crashMarker.create();
    }

    private static String formatLogMessage(int i, String str, String str2) {
        return CommonUtils.m146b(i) + "/" + str + " " + str2;
    }

    private static boolean ensureFabricWithCalled(String str) {
        CrashlyticsCore instance = getInstance();
        if (instance != null && instance.controller != null) {
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        }
        Fabric.m456h().mo8516e(TAG, "Crashlytics must be initialized by calling Fabric.with(Context) " + str, null);
        return false;
    }

    private static String sanitizeAttribute(String str) {
        if (str == null) {
            return str;
        }
        String trim = str.trim();
        if (trim.length() > 1024) {
            return trim.substring(0, 1024);
        }
        return trim;
    }

    static boolean isBuildIdValid(String str, boolean z) {
        if (!z) {
            Fabric.m456h().mo8506a(TAG, "Configured not to require a build ID.");
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        } else if (!CommonUtils.m155d(str)) {
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        } else {
            Log.e(TAG, ".");
            Log.e(TAG, ".     |  | ");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".   \\ |  | /");
            Log.e(TAG, ".    \\    /");
            Log.e(TAG, ".     \\  /");
            Log.e(TAG, ".      \\/");
            Log.e(TAG, ".");
            Log.e(TAG, MISSING_BUILD_ID_MSG);
            Log.e(TAG, ".");
            Log.e(TAG, ".      /\\");
            Log.e(TAG, ".     /  \\");
            Log.e(TAG, ".    /    \\");
            Log.e(TAG, ".   / |  | \\");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".     |  |");
            Log.e(TAG, ".");
            return false;
        }
    }
}
