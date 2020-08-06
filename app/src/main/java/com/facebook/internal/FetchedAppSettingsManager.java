package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class FetchedAppSettingsManager {
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    private static final String APP_SETTING_APP_EVENTS_FEATURE_BITMASK = "app_events_feature_bitmask";
    private static final String APP_SETTING_APP_EVENTS_SESSION_TIMEOUT = "app_events_session_timeout";
    private static final String APP_SETTING_CUSTOM_TABS_ENABLED = "gdpv4_chrome_custom_tabs_enabled";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String[] APP_SETTING_FIELDS = {APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, APP_SETTING_NUX_CONTENT, APP_SETTING_NUX_ENABLED, APP_SETTING_CUSTOM_TABS_ENABLED, APP_SETTING_DIALOG_CONFIGS, APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES, APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, APP_SETTING_APP_EVENTS_FEATURE_BITMASK, APP_SETTING_SMART_LOGIN_OPTIONS, SMART_LOGIN_BOOKMARK_ICON_URL, SMART_LOGIN_MENU_ICON_URL};
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_SMART_LOGIN_OPTIONS = "seamless_login";
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    private static final int AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 8;
    private static final String SMART_LOGIN_BOOKMARK_ICON_URL = "smart_login_bookmark_icon_url";
    private static final String SMART_LOGIN_MENU_ICON_URL = "smart_login_menu_icon_url";
    private static Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static AtomicBoolean loadingSettings = new AtomicBoolean(false);

    public static void loadAppSettingsAsync() {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        final String applicationId = FacebookSdk.getApplicationId();
        boolean compareAndSet = loadingSettings.compareAndSet(false, true);
        if (!Utility.isNullOrEmpty(applicationId) && !fetchedAppSettings.containsKey(applicationId) && compareAndSet) {
            final String format = String.format(APP_SETTINGS_PREFS_KEY_FORMAT, new Object[]{applicationId});
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    JSONObject jSONObject;
                    SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(FetchedAppSettingsManager.APP_SETTINGS_PREFS_STORE, 0);
                    String string = sharedPreferences.getString(format, null);
                    if (!Utility.isNullOrEmpty(string)) {
                        try {
                            jSONObject = new JSONObject(string);
                        } catch (JSONException e) {
                            Utility.logd("FacebookSDK", (Exception) e);
                            jSONObject = null;
                        }
                        if (jSONObject != null) {
                            FetchedAppSettings unused = FetchedAppSettingsManager.parseAppSettingsFromJSON(applicationId, jSONObject);
                        }
                    }
                    JSONObject access$100 = FetchedAppSettingsManager.getAppSettingsQueryResponse(applicationId);
                    if (access$100 != null) {
                        FetchedAppSettings unused2 = FetchedAppSettingsManager.parseAppSettingsFromJSON(applicationId, access$100);
                        sharedPreferences.edit().putString(format, access$100.toString()).apply();
                    }
                    AutomaticAnalyticsLogger.logActivateAppEvent();
                    FetchedAppSettingsManager.loadingSettings.set(false);
                }
            });
        }
    }

    public static FetchedAppSettings getAppSettingsWithoutQuery(String str) {
        if (str != null) {
            return fetchedAppSettings.get(str);
        }
        return null;
    }

    public static FetchedAppSettings queryAppSettings(String str, boolean z) {
        if (!z && fetchedAppSettings.containsKey(str)) {
            return fetchedAppSettings.get(str);
        }
        JSONObject appSettingsQueryResponse = getAppSettingsQueryResponse(str);
        if (appSettingsQueryResponse == null) {
            return null;
        }
        return parseAppSettingsFromJSON(str, appSettingsQueryResponse);
    }

    /* access modifiers changed from: private */
    public static FetchedAppSettings parseAppSettingsFromJSON(String str, JSONObject jSONObject) {
        FacebookRequestErrorClassification createFromJSON;
        boolean z;
        JSONArray optJSONArray = jSONObject.optJSONArray(APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES);
        if (optJSONArray == null) {
            createFromJSON = FacebookRequestErrorClassification.getDefaultErrorClassification();
        } else {
            createFromJSON = FacebookRequestErrorClassification.createFromJSON(optJSONArray);
        }
        if ((jSONObject.optInt(APP_SETTING_APP_EVENTS_FEATURE_BITMASK, 0) & 8) != 0) {
            z = true;
        } else {
            z = false;
        }
        FetchedAppSettings fetchedAppSettings2 = new FetchedAppSettings(jSONObject.optBoolean(APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, false), jSONObject.optString(APP_SETTING_NUX_CONTENT, ""), jSONObject.optBoolean(APP_SETTING_NUX_ENABLED, false), jSONObject.optBoolean(APP_SETTING_CUSTOM_TABS_ENABLED, false), jSONObject.optInt(APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, Constants.getDefaultAppEventsSessionTimeoutInSeconds()), SmartLoginOption.parseOptions(jSONObject.optLong(APP_SETTING_SMART_LOGIN_OPTIONS)), parseDialogConfigurations(jSONObject.optJSONObject(APP_SETTING_DIALOG_CONFIGS)), z, createFromJSON, jSONObject.optString(SMART_LOGIN_BOOKMARK_ICON_URL), jSONObject.optString(SMART_LOGIN_MENU_ICON_URL));
        fetchedAppSettings.put(str, fetchedAppSettings2);
        return fetchedAppSettings2;
    }

    /* access modifiers changed from: private */
    public static JSONObject getAppSettingsQueryResponse(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", APP_SETTING_FIELDS));
        GraphRequest newGraphPathRequest = GraphRequest.newGraphPathRequest(null, str, null);
        newGraphPathRequest.setSkipClientToken(true);
        newGraphPathRequest.setParameters(bundle);
        return newGraphPathRequest.executeAndWait().getJSONObject();
    }

    private static Map<String, Map<String, FetchedAppSettings.DialogFeatureConfig>> parseDialogConfigurations(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(ShareConstants.WEB_DIALOG_PARAM_DATA);
            if (optJSONArray != null) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= optJSONArray.length()) {
                        break;
                    }
                    FetchedAppSettings.DialogFeatureConfig parseDialogConfig = FetchedAppSettings.DialogFeatureConfig.parseDialogConfig(optJSONArray.optJSONObject(i2));
                    if (parseDialogConfig != null) {
                        String dialogName = parseDialogConfig.getDialogName();
                        Map map = (Map) hashMap.get(dialogName);
                        if (map == null) {
                            map = new HashMap();
                            hashMap.put(dialogName, map);
                        }
                        map.put(parseDialogConfig.getFeatureName(), parseDialogConfig);
                    }
                    i = i2 + 1;
                }
            }
        }
        return hashMap;
    }
}
