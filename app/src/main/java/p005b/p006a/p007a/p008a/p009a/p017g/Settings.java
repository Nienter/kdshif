package p005b.p006a.p007a.p008a.p009a.p017g;

import android.content.Context;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.ApiKey;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.DeliveryMechanism;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p011b.SystemCurrentTimeProvider;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;

/* renamed from: b.a.a.a.a.g.q */
public class Settings {

    /* renamed from: a */
    private final AtomicReference<SettingsData> f332a;

    /* renamed from: b */
    private final CountDownLatch f333b;

    /* renamed from: c */
    private SettingsController f334c;

    /* renamed from: d */
    private boolean f335d;

    /* renamed from: b.a.a.a.a.g.q$a */
    /* compiled from: Settings */
    static class C0461a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final Settings f336a = new Settings();
    }

    /* renamed from: a */
    public static Settings m419a() {
        return C0461a.f336a;
    }

    private Settings() {
        this.f332a = new AtomicReference<>();
        this.f333b = new CountDownLatch(1);
        this.f335d = false;
    }

    /* renamed from: a */
    public synchronized Settings mo8499a(Kit iVar, IdManager pVar, HttpRequestFactory eVar, String str, String str2, String str3) {
        Settings qVar;
        if (this.f335d) {
            qVar = this;
        } else {
            if (this.f334c == null) {
                Context context = iVar.getContext();
                String c = pVar.mo8292c();
                String a = new ApiKey().mo8272a(context);
                String i = pVar.mo8298i();
                SystemCurrentTimeProvider tVar = new SystemCurrentTimeProvider();
                DefaultSettingsJsonTransform kVar = new DefaultSettingsJsonTransform();
                DefaultCachedSettingsIo iVar2 = new DefaultCachedSettingsIo(iVar);
                String k = CommonUtils.m162k(context);
                Kit iVar3 = iVar;
                String str4 = str3;
                DefaultSettingsSpiCall lVar = new DefaultSettingsSpiCall(iVar3, str4, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", new Object[]{c}), eVar);
                String str5 = str2;
                String str6 = str;
                this.f334c = new DefaultSettingsController(iVar, new SettingsRequest(a, pVar.mo8296g(), pVar.mo8295f(), pVar.mo8294e(), pVar.mo8300k(), pVar.mo8291b(), pVar.mo8301l(), CommonUtils.m135a(CommonUtils.m164m(context)), str5, str6, DeliveryMechanism.determineFrom(i).getId(), k), tVar, kVar, iVar2, lVar);
            }
            this.f335d = true;
            qVar = this;
        }
        return qVar;
    }

    /* renamed from: b */
    public SettingsData mo8500b() {
        try {
            this.f333b.await();
            return this.f332a.get();
        } catch (InterruptedException e) {
            Fabric.m456h().mo8515e("Fabric", "Interrupted while waiting for settings data.");
            return null;
        }
    }

    /* renamed from: c */
    public synchronized boolean mo8501c() {
        SettingsData a;
        a = this.f334c.mo8489a();
        m420a(a);
        return a != null;
    }

    /* renamed from: d */
    public synchronized boolean mo8502d() {
        SettingsData a;
        a = this.f334c.mo8490a(SettingsCacheBehavior.SKIP_CACHE_LOOKUP);
        m420a(a);
        if (a == null) {
            Fabric.m456h().mo8516e("Fabric", "Failed to force reload of settings from Crashlytics.", null);
        }
        return a != null;
    }

    /* renamed from: a */
    private void m420a(SettingsData tVar) {
        this.f332a.set(tVar);
        this.f333b.countDown();
    }
}
