package p005b.p006a.p007a.p008a.p009a.p017g;

import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpMethod;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequest;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;

/* renamed from: b.a.a.a.a.g.l */
class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    public DefaultSettingsSpiCall(Kit iVar, String str, String str2, HttpRequestFactory eVar) {
        this(iVar, str, str2, eVar, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit iVar, String str, String str2, HttpRequestFactory eVar, HttpMethod cVar) {
        super(iVar, str, str2, eVar, cVar);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: b.a.a.a.a.e.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: b.a.a.a.a.e.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: b.a.a.a.a.e.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: b.a.a.a.a.e.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: b.a.a.a.a.e.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: b.a.a.a.a.e.d} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00a9  */
    /* renamed from: a */
    public JSONObject mo8497a(SettingsRequest wVar) {
        HttpRequest dVar;
        Throwable th;
        HttpRequest dVar2;
        HttpRequest a;
        JSONObject jSONObject = null;
        try {
            Map<String, String> b = m414b(wVar);
            try {
                a = m411a(getHttpRequest(b), wVar);
                Fabric.m456h().mo8506a("Fabric", "Requesting settings from " + getUrl());
                Fabric.m456h().mo8506a("Fabric", "Settings query params were: " + b);
                jSONObject = mo8496a(a);
                if (a != null) {
                    Fabric.m456h().mo8506a("Fabric", "Settings request ID: " + a.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
                }
            } catch (HttpRequest.C0457c e) {
                e = e;
                dVar2 = a;
            }
        } catch (HttpRequest.C0457c e2) {
            e = e2;
            dVar2 = jSONObject;
        } catch (Throwable th2) {
            dVar = jSONObject;
            th = th2;
            if (dVar != 0) {
                Fabric.m456h().mo8506a("Fabric", "Settings request ID: " + dVar.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
            }
            throw th;
        }
        return jSONObject;
        try {
            Fabric.m456h().mo8516e("Fabric", "Settings request failed.", e);
            if (dVar2 != 0) {
                Fabric.m456h().mo8506a("Fabric", "Settings request ID: " + dVar2.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
            }
            return jSONObject;
        } catch (Throwable th3) {
            th = th3;
            dVar = dVar2;
            if (dVar != 0) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public JSONObject mo8496a(HttpRequest dVar) {
        int b = dVar.mo8436b();
        Fabric.m456h().mo8506a("Fabric", "Settings result was: " + b);
        if (mo8498a(b)) {
            return m412a(dVar.mo8447e());
        }
        Fabric.m456h().mo8515e("Fabric", "Failed to retrieve settings from " + getUrl());
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo8498a(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }

    /* renamed from: a */
    private JSONObject m412a(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            Fabric.m456h().mo8507a("Fabric", "Failed to parse settings JSON from " + getUrl(), (Throwable) e);
            Fabric.m456h().mo8506a("Fabric", "Settings response " + str);
            return null;
        }
    }

    /* renamed from: b */
    private Map<String, String> m414b(SettingsRequest wVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("build_version", wVar.f357j);
        hashMap.put("display_version", wVar.f356i);
        hashMap.put(ShareConstants.FEED_SOURCE_PARAM, Integer.toString(wVar.f358k));
        if (wVar.f359l != null) {
            hashMap.put("icon_hash", wVar.f359l);
        }
        String str = wVar.f355h;
        if (!CommonUtils.m155d(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    /* renamed from: a */
    private HttpRequest m411a(HttpRequest dVar, SettingsRequest wVar) {
        m413a(dVar, AbstractSpiCall.HEADER_API_KEY, wVar.f348a);
        m413a(dVar, AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE);
        m413a(dVar, AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
        m413a(dVar, AbstractSpiCall.HEADER_ACCEPT, AbstractSpiCall.ACCEPT_JSON_VALUE);
        m413a(dVar, "X-CRASHLYTICS-DEVICE-MODEL", wVar.f349b);
        m413a(dVar, "X-CRASHLYTICS-OS-BUILD-VERSION", wVar.f350c);
        m413a(dVar, "X-CRASHLYTICS-OS-DISPLAY-VERSION", wVar.f351d);
        m413a(dVar, "X-CRASHLYTICS-INSTALLATION-ID", wVar.f353f);
        if (TextUtils.isEmpty(wVar.f352e)) {
            m413a(dVar, "X-CRASHLYTICS-ANDROID-ID", wVar.f354g);
        } else {
            m413a(dVar, "X-CRASHLYTICS-ADVERTISING-TOKEN", wVar.f352e);
        }
        return dVar;
    }

    /* renamed from: a */
    private void m413a(HttpRequest dVar, String str, String str2) {
        if (str2 != null) {
            dVar.mo8426a(str, str2);
        }
    }
}
