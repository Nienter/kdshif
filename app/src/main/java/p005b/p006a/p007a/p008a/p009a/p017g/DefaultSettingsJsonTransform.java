package p005b.p006a.p007a.p008a.p009a.p017g;

import com.crashlytics.android.beta.BuildConfig;
import com.facebook.share.internal.ShareConstants;
import org.json.JSONObject;
import p005b.p006a.p007a.p008a.p009a.p011b.CurrentTimeProvider;
import p005b.p006a.p007a.p008a.p009a.p014d.EventsFilesManager;

/* renamed from: b.a.a.a.a.g.k */
class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    DefaultSettingsJsonTransform() {
    }

    /* renamed from: a */
    public SettingsData mo8495a(CurrentTimeProvider kVar, JSONObject jSONObject) {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", 3600);
        return new SettingsData(m402a(kVar, (long) optInt2, jSONObject), m403a(jSONObject.getJSONObject("app")), m407e(jSONObject.getJSONObject("session")), m408f(jSONObject.getJSONObject("prompt")), m405c(jSONObject.getJSONObject("features")), m406d(jSONObject.getJSONObject("analytics")), m409g(jSONObject.getJSONObject(BuildConfig.ARTIFACT_ID)), optInt, optInt2);
    }

    /* renamed from: a */
    private AppSettingsData m403a(JSONObject jSONObject) {
        String string = jSONObject.getString("identifier");
        String string2 = jSONObject.getString("status");
        String string3 = jSONObject.getString("url");
        String string4 = jSONObject.getString("reports_url");
        String string5 = jSONObject.getString("ndk_reports_url");
        boolean optBoolean = jSONObject.optBoolean("update_required", false);
        AppIconSettingsData cVar = null;
        if (jSONObject.has("icon") && jSONObject.getJSONObject("icon").has("hash")) {
            cVar = m404b(jSONObject.getJSONObject("icon"));
        }
        return new AppSettingsData(string, string2, string3, string4, string5, optBoolean, cVar);
    }

    /* renamed from: b */
    private AppIconSettingsData m404b(JSONObject jSONObject) {
        return new AppIconSettingsData(jSONObject.getString("hash"), jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    /* renamed from: c */
    private FeaturesSettingsData m405c(JSONObject jSONObject) {
        return new FeaturesSettingsData(jSONObject.optBoolean("prompt_enabled", false), jSONObject.optBoolean("collect_logged_exceptions", true), jSONObject.optBoolean("collect_reports", true), jSONObject.optBoolean("collect_analytics", false));
    }

    /* renamed from: d */
    private AnalyticsSettingsData m406d(JSONObject jSONObject) {
        return new AnalyticsSettingsData(jSONObject.optString("url", "https://e.crashlytics.com/spi/v2/events"), jSONObject.optInt("flush_interval_secs", 600), jSONObject.optInt("max_byte_size_per_file", EventsFilesManager.MAX_BYTE_SIZE_PER_FILE), jSONObject.optInt("max_file_count_per_send", 1), jSONObject.optInt("max_pending_send_file_count", 100), jSONObject.optBoolean("forward_to_google_analytics", false), jSONObject.optBoolean("include_purchase_events_in_forwarded_events", false), jSONObject.optBoolean("track_custom_events", true), jSONObject.optBoolean("track_predefined_events", true), jSONObject.optInt("sampling_rate", 1), jSONObject.optBoolean("flush_on_background", true));
    }

    /* renamed from: e */
    private SessionSettingsData m407e(JSONObject jSONObject) {
        return new SessionSettingsData(jSONObject.optInt("log_buffer_size", 64000), jSONObject.optInt("max_chained_exception_depth", 8), jSONObject.optInt("max_custom_exception_events", 64), jSONObject.optInt("max_custom_key_value_pairs", 64), jSONObject.optInt("identifier_mask", 255), jSONObject.optBoolean("send_session_without_crash", false), jSONObject.optInt("max_complete_sessions_count", 4));
    }

    /* renamed from: f */
    private PromptSettingsData m408f(JSONObject jSONObject) {
        return new PromptSettingsData(jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_TITLE, "Send Crash Report?"), jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject.optString("send_button_title", "Send"), jSONObject.optBoolean("show_cancel_button", true), jSONObject.optString("cancel_button_title", "Don't Send"), jSONObject.optBoolean("show_always_send_button", true), jSONObject.optString("always_send_button_title", "Always Send"));
    }

    /* renamed from: g */
    private BetaSettingsData m409g(JSONObject jSONObject) {
        return new BetaSettingsData(jSONObject.optString("update_endpoint", SettingsJsonConstants.f347a), jSONObject.optInt("update_suspend_duration", 3600));
    }

    /* renamed from: a */
    private long m402a(CurrentTimeProvider kVar, long j, JSONObject jSONObject) {
        if (jSONObject.has("expires_at")) {
            return jSONObject.getLong("expires_at");
        }
        return kVar.mo8282a() + (1000 * j);
    }
}
