package p005b.p006a.p007a.p008a.p009a.p017g;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import org.json.JSONObject;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.CurrentTimeProvider;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStore;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStoreImpl;

/* renamed from: b.a.a.a.a.g.j */
class DefaultSettingsController implements SettingsController {

    /* renamed from: a */
    private final SettingsRequest f303a;

    /* renamed from: b */
    private final SettingsJsonTransform f304b;

    /* renamed from: c */
    private final CurrentTimeProvider f305c;

    /* renamed from: d */
    private final CachedSettingsIo f306d;

    /* renamed from: e */
    private final SettingsSpiCall f307e;

    /* renamed from: f */
    private final Kit f308f;

    /* renamed from: g */
    private final PreferenceStore f309g = new PreferenceStoreImpl(this.f308f);

    public DefaultSettingsController(Kit iVar, SettingsRequest wVar, CurrentTimeProvider kVar, SettingsJsonTransform vVar, CachedSettingsIo gVar, SettingsSpiCall xVar) {
        this.f308f = iVar;
        this.f303a = wVar;
        this.f305c = kVar;
        this.f304b = vVar;
        this.f306d = gVar;
        this.f307e = xVar;
    }

    /* renamed from: a */
    public SettingsData mo8489a() {
        return mo8490a(SettingsCacheBehavior.USE_CACHE);
    }

    /* renamed from: a */
    public SettingsData mo8490a(SettingsCacheBehavior rVar) {
        Exception e;
        SettingsData tVar;
        SettingsData tVar2 = null;
        try {
            if (!Fabric.m457i() && !mo8494d()) {
                tVar2 = m395b(rVar);
            }
            if (tVar2 == null) {
                try {
                    JSONObject a = this.f307e.mo8497a(this.f303a);
                    if (a != null) {
                        tVar2 = this.f304b.mo8495a(this.f305c, a);
                        this.f306d.mo8488a(tVar2.f344g, a);
                        m394a(a, "Loaded settings: ");
                        mo8491a(mo8492b());
                    }
                } catch (Exception e2) {
                    Exception exc = e2;
                    tVar = tVar2;
                    e = exc;
                    Fabric.m456h().mo8516e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", e);
                    return tVar;
                }
            }
            tVar = tVar2;
            if (tVar != null) {
                return tVar;
            }
            try {
                return m395b(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            Exception exc2 = e4;
            tVar = null;
            e = exc2;
            Fabric.m456h().mo8516e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", e);
            return tVar;
        }
    }

    /* renamed from: b */
    private SettingsData m395b(SettingsCacheBehavior rVar) {
        SettingsData tVar = null;
        try {
            if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(rVar)) {
                return null;
            }
            JSONObject a = this.f306d.mo8487a();
            if (a != null) {
                SettingsData a2 = this.f304b.mo8495a(this.f305c, a);
                if (a2 != null) {
                    m394a(a, "Loaded cached settings: ");
                    long a3 = this.f305c.mo8282a();
                    if (SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(rVar) || !a2.mo8503a(a3)) {
                        try {
                            Fabric.m456h().mo8506a("Fabric", "Returning cached settings.");
                            return a2;
                        } catch (Exception e) {
                            Exception exc = e;
                            tVar = a2;
                            e = exc;
                            Fabric.m456h().mo8516e("Fabric", "Failed to get cached settings", e);
                            return tVar;
                        }
                    } else {
                        Fabric.m456h().mo8506a("Fabric", "Cached settings have expired.");
                        return null;
                    }
                } else {
                    Fabric.m456h().mo8516e("Fabric", "Failed to transform cached settings data.", null);
                    return null;
                }
            } else {
                Fabric.m456h().mo8506a("Fabric", "No cached settings data found.");
                return null;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* renamed from: a */
    private void m394a(JSONObject jSONObject, String str) {
        Fabric.m456h().mo8506a("Fabric", str + jSONObject.toString());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo8492b() {
        return CommonUtils.m135a(CommonUtils.m164m(this.f308f.getContext()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public String mo8493c() {
        return this.f309g.mo8481a().getString("existing_instance_identifier", "");
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: a */
    public boolean mo8491a(String str) {
        SharedPreferences.Editor b = this.f309g.mo8483b();
        b.putString("existing_instance_identifier", str);
        return this.f309g.mo8482a(b);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean mo8494d() {
        return !mo8493c().equals(mo8492b());
    }
}
