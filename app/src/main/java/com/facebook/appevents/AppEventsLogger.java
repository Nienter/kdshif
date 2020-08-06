package com.facebook.appevents;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.share.internal.ShareConstants;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p000a.AppLinks;

public class AppEventsLogger {
    public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    private static final String APP_EVENT_NAME_PUSH_OPENED = "fb_mobile_push_opened";
    public static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String APP_EVENT_PUSH_PARAMETER_ACTION = "fb_push_action";
    private static final String APP_EVENT_PUSH_PARAMETER_CAMPAIGN = "fb_push_campaign";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
    private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
    private static final String PUSH_PAYLOAD_CAMPAIGN_KEY = "campaign";
    private static final String PUSH_PAYLOAD_KEY = "fb_push_payload";
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    /* access modifiers changed from: private */
    public static final String TAG = AppEventsLogger.class.getCanonicalName();
    private static String anonymousAppDeviceGUID;
    /* access modifiers changed from: private */
    public static ScheduledThreadPoolExecutor backgroundExecutor;
    private static String externalAnalyticsUserID;
    private static FlushBehavior flushBehavior = FlushBehavior.AUTO;
    private static boolean isActivateAppEventRequested;
    private static boolean isOpenedByApplink;
    private static String pushNotificationsRegistrationId;
    private static String sourceApplication;
    private static Object staticLock = new Object();
    private final AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;

    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    static class PersistedAppSessionInfo {
        private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
        private static final Runnable appSessionInfoFlushRunnable = new Runnable() {
            public void run() {
                PersistedAppSessionInfo.saveAppSessionInformation(FacebookSdk.getApplicationContext());
            }
        };
        private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
        private static boolean hasChanges = false;
        private static boolean isLoaded = false;
        private static final Object staticLock = new Object();

        PersistedAppSessionInfo() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x004c A[Catch:{ FileNotFoundException -> 0x003f, Exception -> 0x005d, all -> 0x0099 }] */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x008b A[Catch:{ FileNotFoundException -> 0x003f, Exception -> 0x005d, all -> 0x0099 }] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00a9 A[Catch:{ FileNotFoundException -> 0x003f, Exception -> 0x005d, all -> 0x0099 }] */
        private static void restoreAppSessionInformation(Context context) {
            ObjectInputStream objectInputStream;
            Throwable th;
            Exception e;
            ObjectInputStream objectInputStream2 = null;
            synchronized (staticLock) {
                if (!isLoaded) {
                    try {
                        objectInputStream = new ObjectInputStream(context.openFileInput(PERSISTED_SESSION_INFO_FILENAME));
                        try {
                            appSessionInfoMap = (HashMap) objectInputStream.readObject();
                            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "App session info loaded");
                            Utility.closeQuietly(objectInputStream);
                            context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                            if (appSessionInfoMap == null) {
                                appSessionInfoMap = new HashMap();
                            }
                            isLoaded = true;
                            hasChanges = false;
                        } catch (FileNotFoundException e2) {
                            objectInputStream2 = objectInputStream;
                            Utility.closeQuietly(objectInputStream2);
                            context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                            if (appSessionInfoMap == null) {
                            }
                            isLoaded = true;
                            hasChanges = false;
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                Log.w(AppEventsLogger.TAG, "Got unexpected exception restoring app session info: " + e.toString());
                                Utility.closeQuietly(objectInputStream);
                                context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                                if (appSessionInfoMap == null) {
                                }
                                isLoaded = true;
                                hasChanges = false;
                            } catch (Throwable th2) {
                                th = th2;
                                Utility.closeQuietly(objectInputStream);
                                context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                                if (appSessionInfoMap == null) {
                                }
                                isLoaded = true;
                                hasChanges = false;
                                throw th;
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        Utility.closeQuietly(objectInputStream2);
                        context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                        if (appSessionInfoMap == null) {
                            appSessionInfoMap = new HashMap();
                        }
                        isLoaded = true;
                        hasChanges = false;
                    } catch (Exception e5) {
                        Exception exc = e5;
                        objectInputStream = null;
                        e = exc;
                        Log.w(AppEventsLogger.TAG, "Got unexpected exception restoring app session info: " + e.toString());
                        Utility.closeQuietly(objectInputStream);
                        context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                        if (appSessionInfoMap == null) {
                            appSessionInfoMap = new HashMap();
                        }
                        isLoaded = true;
                        hasChanges = false;
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        objectInputStream = null;
                        th = th4;
                        Utility.closeQuietly(objectInputStream);
                        context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                        if (appSessionInfoMap == null) {
                            appSessionInfoMap = new HashMap();
                        }
                        isLoaded = true;
                        hasChanges = false;
                        throw th;
                    }
                }
            }
        }

        static void saveAppSessionInformation(Context context) {
            ObjectOutputStream objectOutputStream;
            synchronized (staticLock) {
                if (hasChanges) {
                    try {
                        objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(context.openFileOutput(PERSISTED_SESSION_INFO_FILENAME, 0)));
                        try {
                            objectOutputStream.writeObject(appSessionInfoMap);
                            hasChanges = false;
                            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "App session info saved");
                            Utility.closeQuietly(objectOutputStream);
                        } catch (Exception e) {
                            e = e;
                            try {
                                Log.w(AppEventsLogger.TAG, "Got unexpected exception while writing app session info: " + e.toString());
                                Utility.closeQuietly(objectOutputStream);
                            } catch (Throwable th) {
                                th = th;
                                Utility.closeQuietly(objectOutputStream);
                                throw th;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        objectOutputStream = null;
                        Log.w(AppEventsLogger.TAG, "Got unexpected exception while writing app session info: " + e.toString());
                        Utility.closeQuietly(objectOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        objectOutputStream = null;
                        Utility.closeQuietly(objectOutputStream);
                        throw th;
                    }
                }
            }
        }

        static void onResume(Context context, AccessTokenAppIdPair accessTokenAppIdPair, AppEventsLogger appEventsLogger, long j, String str) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onResume(appEventsLogger, j, str);
                onTimeSpentDataUpdate();
            }
        }

        static void onSuspend(Context context, AccessTokenAppIdPair accessTokenAppIdPair, AppEventsLogger appEventsLogger, long j) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onSuspend(appEventsLogger, j);
                onTimeSpentDataUpdate();
            }
        }

        private static FacebookTimeSpentData getTimeSpentData(Context context, AccessTokenAppIdPair accessTokenAppIdPair) {
            restoreAppSessionInformation(context);
            FacebookTimeSpentData facebookTimeSpentData = appSessionInfoMap.get(accessTokenAppIdPair);
            if (facebookTimeSpentData != null) {
                return facebookTimeSpentData;
            }
            FacebookTimeSpentData facebookTimeSpentData2 = new FacebookTimeSpentData();
            appSessionInfoMap.put(accessTokenAppIdPair, facebookTimeSpentData2);
            return facebookTimeSpentData2;
        }

        private static void onTimeSpentDataUpdate() {
            if (!hasChanges) {
                hasChanges = true;
                AppEventsLogger.backgroundExecutor.schedule(appSessionInfoFlushRunnable, 30, TimeUnit.SECONDS);
            }
        }
    }

    public static void activateApp(Application application) {
        activateApp(application, (String) null);
    }

    public static void activateApp(Application application, String str) {
        if (!FacebookSdk.isInitialized()) {
            throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
        }
        AnalyticsUserIDStore.initStore();
        if (str == null) {
            str = FacebookSdk.getApplicationId();
        }
        FacebookSdk.publishInstallAsync(application, str);
        ActivityLifecycleTracker.startTracking(application, str);
    }

    @Deprecated
    public static void activateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
            return;
        }
        FacebookSdk.sdkInitialize(context);
        activateApp(context, Utility.getMetadataApplicationId(context));
    }

    @Deprecated
    public static void activateApp(Context context, String str) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
        } else if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            AnalyticsUserIDStore.initStore();
            if (context instanceof Activity) {
                setSourceApplication((Activity) context);
            } else {
                resetSourceApplication();
                Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
            }
            FacebookSdk.publishInstallAsync(context, str);
            AppEventsLogger appEventsLogger = new AppEventsLogger(context, str, (AccessToken) null);
            final long currentTimeMillis = System.currentTimeMillis();
            final String sourceApplication2 = getSourceApplication();
            backgroundExecutor.execute(new Runnable(appEventsLogger) {
                final /* synthetic */ AppEventsLogger val$logger;

                {
                    this.val$logger = r1;
                }

                public void run() {
                    this.val$logger.logAppSessionResumeEvent(currentTimeMillis, sourceApplication2);
                }
            });
        }
    }

    @Deprecated
    public static void deactivateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else {
            deactivateApp(context, Utility.getMetadataApplicationId(context));
        }
    }

    @Deprecated
    public static void deactivateApp(Context context, String str) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            resetSourceApplication();
            AppEventsLogger appEventsLogger = new AppEventsLogger(context, str, (AccessToken) null);
            final long currentTimeMillis = System.currentTimeMillis();
            backgroundExecutor.execute(new Runnable(appEventsLogger) {
                final /* synthetic */ AppEventsLogger val$logger;

                {
                    this.val$logger = r1;
                }

                public void run() {
                    this.val$logger.logAppSessionSuspendEvent(currentTimeMillis);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void logAppSessionResumeEvent(long j, String str) {
        PersistedAppSessionInfo.onResume(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, j, str);
    }

    /* access modifiers changed from: private */
    public void logAppSessionSuspendEvent(long j) {
        PersistedAppSessionInfo.onSuspend(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, j);
    }

    public static AppEventsLogger newLogger(Context context) {
        return new AppEventsLogger(context, (String) null, (AccessToken) null);
    }

    public static AppEventsLogger newLogger(Context context, AccessToken accessToken) {
        return new AppEventsLogger(context, (String) null, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String str, AccessToken accessToken) {
        return new AppEventsLogger(context, str, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String str) {
        return new AppEventsLogger(context, str, (AccessToken) null);
    }

    public static FlushBehavior getFlushBehavior() {
        FlushBehavior flushBehavior2;
        synchronized (staticLock) {
            flushBehavior2 = flushBehavior;
        }
        return flushBehavior2;
    }

    public static void setFlushBehavior(FlushBehavior flushBehavior2) {
        synchronized (staticLock) {
            flushBehavior = flushBehavior2;
        }
    }

    public void logEvent(String str) {
        logEvent(str, (Bundle) null);
    }

    public void logEvent(String str, double d) {
        logEvent(str, d, (Bundle) null);
    }

    public void logEvent(String str, Bundle bundle) {
        logEvent(str, null, bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logEvent(String str, double d, Bundle bundle) {
        logEvent(str, Double.valueOf(d), bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logPurchase(BigDecimal bigDecimal, Currency currency) {
        logPurchase(bigDecimal, currency, null);
    }

    public void logPurchase(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        if (bigDecimal == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
        } else if (currency == null) {
            notifyDeveloperError("currency cannot be null");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
            logEvent(AppEventsConstants.EVENT_NAME_PURCHASED, bigDecimal.doubleValue(), bundle);
            eagerFlush();
        }
    }

    public void logPushNotificationOpen(Bundle bundle) {
        logPushNotificationOpen(bundle, null);
    }

    public void logPushNotificationOpen(Bundle bundle, String str) {
        String str2 = null;
        try {
            String string = bundle.getString(PUSH_PAYLOAD_KEY);
            if (!Utility.isNullOrEmpty(string)) {
                str2 = new JSONObject(string).getString(PUSH_PAYLOAD_CAMPAIGN_KEY);
                if (str2 == null) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "Malformed payload specified for logging a push notification open.");
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString(APP_EVENT_PUSH_PARAMETER_CAMPAIGN, str2);
                if (str != null) {
                    bundle2.putString(APP_EVENT_PUSH_PARAMETER_ACTION, str);
                }
                logEvent(APP_EVENT_NAME_PUSH_OPENED, bundle2);
            }
        } catch (JSONException e) {
        }
    }

    public void flush() {
        AppEventQueue.flush(FlushReason.EXPLICIT);
    }

    public static void onContextStop() {
        AppEventQueue.persistToDisk();
    }

    public boolean isValidForAccessToken(AccessToken accessToken) {
        return this.accessTokenAppId.equals(new AccessTokenAppIdPair(accessToken));
    }

    public static void setPushNotificationsRegistrationId(String str) {
        synchronized (staticLock) {
            if (!Utility.stringsEqualOrEmpty(pushNotificationsRegistrationId, str)) {
                pushNotificationsRegistrationId = str;
                AppEventsLogger newLogger = newLogger(FacebookSdk.getApplicationContext());
                newLogger.logEvent(AppEventsConstants.EVENT_NAME_PUSH_TOKEN_OBTAINED);
                if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                    newLogger.flush();
                }
            }
        }
    }

    static String getPushNotificationsRegistrationId() {
        String str;
        synchronized (staticLock) {
            str = pushNotificationsRegistrationId;
        }
        return str;
    }

    public static void setUserID(String str) {
        AnalyticsUserIDStore.setUserID(str);
    }

    public static String getUserID() {
        return AnalyticsUserIDStore.getUserID();
    }

    public static void clearUserID() {
        AnalyticsUserIDStore.setUserID(null);
    }

    public static void updateUserProperties(Bundle bundle, GraphRequest.Callback callback) {
        updateUserProperties(bundle, FacebookSdk.getApplicationId(), callback);
    }

    public static void updateUserProperties(final Bundle bundle, final String str, final GraphRequest.Callback callback) {
        final String userID = getUserID();
        if (userID == null || userID.isEmpty()) {
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "AppEventsLogger userID cannot be null or empty");
        } else {
            getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putString("user_unique_id", userID);
                    bundle.putBundle("custom_data", bundle);
                    AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                    if (!(attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null)) {
                        bundle.putString("advertiser_id", attributionIdentifiers.getAndroidAdvertiserId());
                    }
                    Bundle bundle2 = new Bundle();
                    try {
                        JSONObject convertToJSON = BundleJSONConverter.convertToJSON(bundle);
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(convertToJSON);
                        bundle2.putString(ShareConstants.WEB_DIALOG_PARAM_DATA, jSONArray.toString());
                        GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.US, "%s/user_properties", new Object[]{str}), bundle2, HttpMethod.POST, callback);
                        graphRequest.setSkipClientToken(true);
                        graphRequest.executeAsync();
                    } catch (JSONException e) {
                        throw new FacebookException("Failed to construct request", (Throwable) e);
                    }
                }
            });
        }
    }

    public void logSdkEvent(String str, Double d, Bundle bundle) {
        logEvent(str, d, bundle, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }

    private AppEventsLogger(Context context, String str, AccessToken accessToken) {
        this(Utility.getActivityName(context), str, accessToken);
    }

    protected AppEventsLogger(String str, String str2, AccessToken accessToken) {
        Validate.sdkInitialized();
        this.contextName = str;
        accessToken = accessToken == null ? AccessToken.getCurrentAccessToken() : accessToken;
        if (accessToken == null || (str2 != null && !str2.equals(accessToken.getApplicationId()))) {
            this.accessTokenAppId = new AccessTokenAppIdPair(null, str2 == null ? Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext()) : str2);
        } else {
            this.accessTokenAppId = new AccessTokenAppIdPair(accessToken);
        }
        initializeTimersIfNeeded();
    }

    private static void initializeTimersIfNeeded() {
        synchronized (staticLock) {
            if (backgroundExecutor == null) {
                backgroundExecutor = new ScheduledThreadPoolExecutor(1);
                backgroundExecutor.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        HashSet<String> hashSet = new HashSet<>();
                        for (AccessTokenAppIdPair applicationId : AppEventQueue.getKeySet()) {
                            hashSet.add(applicationId.getApplicationId());
                        }
                        for (String queryAppSettings : hashSet) {
                            FetchedAppSettingsManager.queryAppSettings(queryAppSettings, true);
                        }
                    }
                }, 0, 86400, TimeUnit.SECONDS);
            }
        }
    }

    private void logEvent(String str, Double d, Bundle bundle, boolean z, @Nullable UUID uuid) {
        try {
            logEvent(FacebookSdk.getApplicationContext(), new AppEvent(this.contextName, str, d, bundle, z, uuid), this.accessTokenAppId);
        } catch (JSONException e) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e.toString());
        } catch (FacebookException e2) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e2.toString());
        }
    }

    private static void logEvent(Context context, AppEvent appEvent, AccessTokenAppIdPair accessTokenAppIdPair) {
        AppEventQueue.add(accessTokenAppIdPair, appEvent);
        if (!appEvent.getIsImplicit() && !isActivateAppEventRequested) {
            if (appEvent.getName() == AppEventsConstants.EVENT_NAME_ACTIVATED_APP) {
                isActivateAppEventRequested = true;
            } else {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    static void eagerFlush() {
        if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }

    private static void notifyDeveloperError(String str) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", str);
    }

    private static void setSourceApplication(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            String packageName = callingActivity.getPackageName();
            if (packageName.equals(activity.getPackageName())) {
                resetSourceApplication();
                return;
            }
            sourceApplication = packageName;
        }
        Intent intent = activity.getIntent();
        if (intent == null || intent.getBooleanExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, false)) {
            resetSourceApplication();
            return;
        }
        Bundle a = AppLinks.m3a(intent);
        if (a == null) {
            resetSourceApplication();
            return;
        }
        isOpenedByApplink = true;
        Bundle bundle = a.getBundle("referer_app_link");
        if (bundle == null) {
            sourceApplication = null;
            return;
        }
        sourceApplication = bundle.getString("package");
        intent.putExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
    }

    static void setSourceApplication(String str, boolean z) {
        sourceApplication = str;
        isOpenedByApplink = z;
    }

    static String getSourceApplication() {
        String str = "Unclassified";
        if (isOpenedByApplink) {
            str = "Applink";
        }
        if (sourceApplication != null) {
            return str + "(" + sourceApplication + ")";
        }
        return str;
    }

    static void resetSourceApplication() {
        sourceApplication = null;
        isOpenedByApplink = false;
    }

    static Executor getAnalyticsExecutor() {
        if (backgroundExecutor == null) {
            initializeTimersIfNeeded();
        }
        return backgroundExecutor;
    }

    public static String getAnonymousAppDeviceGUID(Context context) {
        if (anonymousAppDeviceGUID == null) {
            synchronized (staticLock) {
                if (anonymousAppDeviceGUID == null) {
                    anonymousAppDeviceGUID = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).getString("anonymousAppDeviceGUID", null);
                    if (anonymousAppDeviceGUID == null) {
                        anonymousAppDeviceGUID = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
                    }
                }
            }
        }
        return anonymousAppDeviceGUID;
    }
}
