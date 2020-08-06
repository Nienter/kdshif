package p005b.p006a.p007a.p008a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.appevents.AppEventsConstants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import p005b.p006a.p007a.p008a.p009a.p011b.ApiKey;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.DeliveryMechanism;
import p005b.p006a.p007a.p008a.p009a.p015e.DefaultHttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p017g.AppRequestData;
import p005b.p006a.p007a.p008a.p009a.p017g.AppSettingsData;
import p005b.p006a.p007a.p008a.p009a.p017g.CreateAppSpiCall;
import p005b.p006a.p007a.p008a.p009a.p017g.IconRequest;
import p005b.p006a.p007a.p008a.p009a.p017g.Settings;
import p005b.p006a.p007a.p008a.p009a.p017g.SettingsData;
import p005b.p006a.p007a.p008a.p009a.p017g.UpdateAppSpiCall;

/* renamed from: b.a.a.a.m */
class Onboarding extends Kit<Boolean> {

    /* renamed from: a */
    private final HttpRequestFactory f396a = new DefaultHttpRequestFactory();

    /* renamed from: b */
    private PackageManager f397b;

    /* renamed from: c */
    private String f398c;

    /* renamed from: d */
    private PackageInfo f399d;

    /* renamed from: e */
    private String f400e;

    /* renamed from: f */
    private String f401f;

    /* renamed from: g */
    private String f402g;

    /* renamed from: h */
    private String f403h;

    /* renamed from: i */
    private String f404i;

    /* renamed from: j */
    private final Future<Map<String, KitInfo>> f405j;

    /* renamed from: k */
    private final Collection<Kit> f406k;

    public Onboarding(Future<Map<String, KitInfo>> future, Collection<Kit> collection) {
        this.f405j = future;
        this.f406k = collection;
    }

    public String getVersion() {
        return "1.4.1.19";
    }

    /* access modifiers changed from: protected */
    public boolean onPreExecute() {
        try {
            this.f402g = getIdManager().mo8298i();
            this.f397b = getContext().getPackageManager();
            this.f398c = getContext().getPackageName();
            this.f399d = this.f397b.getPackageInfo(this.f398c, 0);
            this.f400e = Integer.toString(this.f399d.versionCode);
            this.f401f = this.f399d.versionName == null ? "0.0" : this.f399d.versionName;
            this.f403h = this.f397b.getApplicationLabel(getContext().getApplicationInfo()).toString();
            this.f404i = Integer.toString(getContext().getApplicationInfo().targetSdkVersion);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Fabric.m456h().mo8516e("Fabric", "Failed init", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Boolean doInBackground() {
        boolean z;
        Map hashMap;
        String k = CommonUtils.m162k(getContext());
        SettingsData c = m507c();
        if (c != null) {
            try {
                if (this.f405j != null) {
                    hashMap = this.f405j.get();
                } else {
                    hashMap = new HashMap();
                }
                z = m505a(k, c.f338a, mo8566a((Map<String, KitInfo>) hashMap, this.f406k).values());
            } catch (Exception e) {
                Fabric.m456h().mo8516e("Fabric", "Error performing auto configuration.", e);
            }
            return Boolean.valueOf(z);
        }
        z = false;
        return Boolean.valueOf(z);
    }

    /* renamed from: c */
    private SettingsData m507c() {
        try {
            Settings.m419a().mo8499a(this, this.idManager, this.f396a, this.f400e, this.f401f, mo8567b()).mo8501c();
            return Settings.m419a().mo8500b();
        } catch (Exception e) {
            Fabric.m456h().mo8516e("Fabric", "Error dealing with settings", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Map<String, KitInfo> mo8566a(Map<String, KitInfo> map, Collection<Kit> collection) {
        for (Kit next : collection) {
            if (!map.containsKey(next.getIdentifier())) {
                map.put(next.getIdentifier(), new KitInfo(next.getIdentifier(), next.getVersion(), "binary"));
            }
        }
        return map;
    }

    public String getIdentifier() {
        return "io.fabric.sdk.android:fabric";
    }

    /* renamed from: a */
    private boolean m505a(String str, AppSettingsData eVar, Collection<KitInfo> collection) {
        if ("new".equals(eVar.f294b)) {
            if (m506b(str, eVar, collection)) {
                return Settings.m419a().mo8502d();
            }
            Fabric.m456h().mo8516e("Fabric", "Failed to create app with Crashlytics service.", null);
            return false;
        } else if ("configured".equals(eVar.f294b)) {
            return Settings.m419a().mo8502d();
        } else {
            if (!eVar.f298f) {
                return true;
            }
            Fabric.m456h().mo8506a("Fabric", "Server says an update is required - forcing a full App update.");
            m508c(str, eVar, collection);
            return true;
        }
    }

    /* renamed from: b */
    private boolean m506b(String str, AppSettingsData eVar, Collection<KitInfo> collection) {
        return new CreateAppSpiCall(this, mo8567b(), eVar.f295c, this.f396a).mo8485a(m503a(IconRequest.m418a(getContext(), str), collection));
    }

    /* renamed from: c */
    private boolean m508c(String str, AppSettingsData eVar, Collection<KitInfo> collection) {
        return m504a(eVar, IconRequest.m418a(getContext(), str), collection);
    }

    /* renamed from: a */
    private boolean m504a(AppSettingsData eVar, IconRequest nVar, Collection<KitInfo> collection) {
        return new UpdateAppSpiCall(this, mo8567b(), eVar.f295c, this.f396a).mo8485a(m503a(nVar, collection));
    }

    /* renamed from: a */
    private AppRequestData m503a(IconRequest nVar, Collection<KitInfo> collection) {
        Context context = getContext();
        return new AppRequestData(new ApiKey().mo8272a(context), getIdManager().mo8292c(), this.f401f, this.f400e, CommonUtils.m135a(CommonUtils.m164m(context)), this.f403h, DeliveryMechanism.determineFrom(this.f402g).getId(), this.f404i, AppEventsConstants.EVENT_PARAM_VALUE_NO, nVar, collection);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo8567b() {
        return CommonUtils.m147b(getContext(), "com.crashlytics.ApiEndpoint");
    }
}
