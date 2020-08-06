package com.crashlytics.android.beta;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpMethod;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequest;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;

class CheckForUpdatesRequest extends AbstractSpiCall {
    static final String BETA_SOURCE = "3";
    static final String BUILD_VERSION = "build_version";
    static final String DISPLAY_VERSION = "display_version";
    static final String HEADER_BETA_TOKEN = "X-CRASHLYTICS-BETA-TOKEN";
    static final String INSTANCE = "instance";
    static final String SDK_ANDROID_DIR_TOKEN_TYPE = "3";
    static final String SOURCE = "source";
    private final CheckForUpdatesResponseTransform responseTransform;

    static String createBetaTokenHeaderValueFor(String str) {
        return "3:" + str;
    }

    public CheckForUpdatesRequest(Kit iVar, String str, String str2, HttpRequestFactory eVar, CheckForUpdatesResponseTransform checkForUpdatesResponseTransform) {
        super(iVar, str, str2, eVar, HttpMethod.GET);
        this.responseTransform = checkForUpdatesResponseTransform;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0120  */
    public CheckForUpdatesResponse invoke(String str, String str2, BuildProperties buildProperties) {
        HttpRequest dVar;
        Throwable th;
        CheckForUpdatesResponse checkForUpdatesResponse = null;
        try {
            try {
                dVar = applyHeadersTo(getHttpRequest(getQueryParamsFor(buildProperties)), str, str2);
                Fabric.m456h().mo8506a(Beta.TAG, "Checking for updates from " + getUrl());
                Fabric.m456h().mo8506a(Beta.TAG, "Checking for updates query params are: " + r1);
                if (dVar.mo8442c()) {
                    Fabric.m456h().mo8506a(Beta.TAG, "Checking for updates was successful");
                    checkForUpdatesResponse = this.responseTransform.fromJson(new JSONObject(dVar.mo8447e()));
                    if (dVar != null) {
                        Fabric.m456h().mo8506a("Fabric", "Checking for updates request ID: " + dVar.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
                    }
                } else {
                    Fabric.m456h().mo8515e(Beta.TAG, "Checking for updates failed. Response code: " + dVar.mo8436b());
                    if (dVar != null) {
                        Fabric.m456h().mo8506a("Fabric", "Checking for updates request ID: " + dVar.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
                    }
                }
            } catch (Exception e) {
                e = e;
                try {
                    Fabric.m456h().mo8516e(Beta.TAG, "Error while checking for updates from " + getUrl(), e);
                    if (dVar != null) {
                        Fabric.m456h().mo8506a("Fabric", "Checking for updates request ID: " + dVar.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
                    }
                    return checkForUpdatesResponse;
                } catch (Throwable th2) {
                    th = th2;
                    if (dVar != null) {
                        Fabric.m456h().mo8506a("Fabric", "Checking for updates request ID: " + dVar.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
                    }
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            dVar = null;
        } catch (Throwable th3) {
            dVar = null;
            th = th3;
            if (dVar != null) {
            }
            throw th;
        }
        return checkForUpdatesResponse;
    }

    private HttpRequest applyHeadersTo(HttpRequest dVar, String str, String str2) {
        return dVar.mo8426a(AbstractSpiCall.HEADER_ACCEPT, AbstractSpiCall.ACCEPT_JSON_VALUE).mo8426a(AbstractSpiCall.HEADER_USER_AGENT, AbstractSpiCall.CRASHLYTICS_USER_AGENT + this.kit.getVersion()).mo8426a(AbstractSpiCall.HEADER_DEVELOPER_TOKEN, AbstractSpiCall.CLS_ANDROID_SDK_DEVELOPER_TOKEN).mo8426a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).mo8426a(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion()).mo8426a(AbstractSpiCall.HEADER_API_KEY, str).mo8426a(HEADER_BETA_TOKEN, createBetaTokenHeaderValueFor(str2));
    }

    private Map<String, String> getQueryParamsFor(BuildProperties buildProperties) {
        HashMap hashMap = new HashMap();
        hashMap.put(BUILD_VERSION, buildProperties.versionCode);
        hashMap.put(DISPLAY_VERSION, buildProperties.versionName);
        hashMap.put(INSTANCE, buildProperties.buildId);
        hashMap.put("source", "3");
        return hashMap;
    }
}
