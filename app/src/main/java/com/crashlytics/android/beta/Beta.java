package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p010a.MemoryValueCache;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.DeviceIdentifierProvider;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p011b.SystemCurrentTimeProvider;
import p005b.p006a.p007a.p008a.p009a.p015e.DefaultHttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStoreImpl;
import p005b.p006a.p007a.p008a.p009a.p017g.BetaSettingsData;
import p005b.p006a.p007a.p008a.p009a.p017g.Settings;
import p005b.p006a.p007a.p008a.p009a.p017g.SettingsData;

public class Beta extends Kit<Boolean> implements DeviceIdentifierProvider {
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String CRASHLYTICS_BUILD_PROPERTIES = "crashlytics-build.properties";
    static final String NO_DEVICE_TOKEN = "";
    public static final String TAG = "Beta";
    private final MemoryValueCache<String> deviceTokenCache = new MemoryValueCache<>();
    private final DeviceTokenLoader deviceTokenLoader = new DeviceTokenLoader();
    private UpdatesController updatesController;

    public static Beta getInstance() {
        return (Beta) Fabric.m447a(Beta.class);
    }

    /* access modifiers changed from: protected */
    @TargetApi(14)
    public boolean onPreExecute() {
        this.updatesController = createUpdatesController(Build.VERSION.SDK_INT, (Application) getContext().getApplicationContext());
        return true;
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground() {
        Fabric.m456h().mo8506a(TAG, "Beta kit initializing...");
        Context context = getContext();
        IdManager idManager = getIdManager();
        if (TextUtils.isEmpty(getBetaDeviceToken(context, idManager.mo8298i()))) {
            Fabric.m456h().mo8506a(TAG, "A Beta device token was not found for this app");
            return false;
        }
        Fabric.m456h().mo8506a(TAG, "Beta device token is present, checking for app updates.");
        BetaSettingsData betaSettingsData = getBetaSettingsData();
        BuildProperties loadBuildProperties = loadBuildProperties(context);
        if (canCheckForUpdates(betaSettingsData, loadBuildProperties)) {
            this.updatesController.initialize(context, this, idManager, betaSettingsData, loadBuildProperties, new PreferenceStoreImpl(this), new SystemCurrentTimeProvider(), new DefaultHttpRequestFactory(Fabric.m456h()));
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    public UpdatesController createUpdatesController(int i, Application application) {
        if (i >= 14) {
            return new ActivityLifecycleCheckForUpdatesController(getFabric().mo8525e(), getFabric().mo8526f());
        }
        return new ImmediateCheckForUpdatesController();
    }

    public Map<IdManager.C0430a, String> getDeviceIdentifiers() {
        String betaDeviceToken = getBetaDeviceToken(getContext(), getIdManager().mo8298i());
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(betaDeviceToken)) {
            hashMap.put(IdManager.C0430a.FONT_TOKEN, betaDeviceToken);
        }
        return hashMap;
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:beta";
    }

    public String getVersion() {
        return "1.2.7.19";
    }

    /* access modifiers changed from: package-private */
    public boolean canCheckForUpdates(BetaSettingsData fVar, BuildProperties buildProperties) {
        return (fVar == null || TextUtils.isEmpty(fVar.f300a) || buildProperties == null) ? false : true;
    }

    private String getBetaDeviceToken(Context context, String str) {
        String str2;
        try {
            str2 = this.deviceTokenCache.mo8250a(context, this.deviceTokenLoader);
            if ("".equals(str2)) {
                str2 = null;
            }
        } catch (Exception e) {
            Fabric.m456h().mo8516e(TAG, "Failed to load the Beta device token", e);
            str2 = null;
        }
        Fabric.m456h().mo8506a(TAG, "Beta device token present: " + (!TextUtils.isEmpty(str2)));
        return str2;
    }

    private BetaSettingsData getBetaSettingsData() {
        SettingsData b = Settings.m419a().mo8500b();
        if (b != null) {
            return b.f343f;
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.crashlytics.android.beta.BuildProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.crashlytics.android.beta.BuildProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.crashlytics.android.beta.BuildProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: com.crashlytics.android.beta.BuildProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: com.crashlytics.android.beta.BuildProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: com.crashlytics.android.beta.BuildProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: com.crashlytics.android.beta.BuildProperties} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0088 A[SYNTHETIC, Splitter:B:27:0x0088] */
    private BuildProperties loadBuildProperties(Context context) {
        InputStream inputStream;
        Exception exc;
        InputStream inputStream2;
        InputStream inputStream3;
        BuildProperties fromPropertiesStream;
        InputStream inputStream4 = null;
        try {
            inputStream = context.getAssets().open(CRASHLYTICS_BUILD_PROPERTIES);
            if (inputStream != null) {
                try {
                    fromPropertiesStream = BuildProperties.fromPropertiesStream(inputStream);
                } catch (Exception e) {
                    Exception exc2 = e;
                    inputStream2 = inputStream4;
                    exc = exc2;
                }
                try {
                    Fabric.m456h().mo8506a(TAG, fromPropertiesStream.packageName + " build properties: " + fromPropertiesStream.versionName + " (" + fromPropertiesStream.versionCode + ") - " + fromPropertiesStream.buildId);
                    inputStream3 = fromPropertiesStream;
                } catch (Exception e2) {
                    Exception exc3 = e2;
                    inputStream2 = fromPropertiesStream;
                    exc = exc3;
                    try {
                        Fabric.m456h().mo8516e(TAG, "Error reading Beta build properties", exc);
                        inputStream3 = inputStream2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                                inputStream3 = inputStream2;
                            } catch (IOException e3) {
                                Fabric.m456h().mo8516e(TAG, "Error closing Beta build properties asset", e3);
                                inputStream3 = inputStream2;
                            }
                        }
                        return inputStream3;
                    } catch (Throwable th) {
                        th = th;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                Fabric.m456h().mo8516e(TAG, "Error closing Beta build properties asset", e4);
                            }
                        }
                        throw th;
                    }
                }
            } else {
                inputStream3 = inputStream4;
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    Fabric.m456h().mo8516e(TAG, "Error closing Beta build properties asset", e5);
                }
            }
        } catch (Exception e6) {
            inputStream = inputStream4;
            InputStream inputStream5 = inputStream4;
            exc = e6;
            inputStream2 = inputStream5;
        } catch (Throwable th2) {
            th = th2;
            inputStream = inputStream4;
            if (inputStream != null) {
            }
            throw th;
        }
        return inputStream3;
    }

    /* access modifiers changed from: package-private */
    public String getOverridenSpiEndpoint() {
        return CommonUtils.m147b(getContext(), CRASHLYTICS_API_ENDPOINT);
    }
}
