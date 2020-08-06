package p005b.p006a.p007a.p008a.p009a.p011b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStore;
import p005b.p006a.p007a.p008a.p009a.p016f.PreferenceStoreImpl;

/* renamed from: b.a.a.a.a.b.c */
class AdvertisingInfoProvider {

    /* renamed from: a */
    private final Context f110a;

    /* renamed from: b */
    private final PreferenceStore f111b;

    public AdvertisingInfoProvider(Context context) {
        this.f110a = context.getApplicationContext();
        this.f111b = new PreferenceStoreImpl(context, "TwitterAdvertisingInfoPreferences");
    }

    /* renamed from: a */
    public AdvertisingInfo mo8258a() {
        AdvertisingInfo b = mo8259b();
        if (m99c(b)) {
            Fabric.m456h().mo8506a("Fabric", "Using AdvertisingInfo from Preference Store");
            m96a(b);
            return b;
        }
        AdvertisingInfo e = m100e();
        m98b(e);
        return e;
    }

    /* renamed from: a */
    private void m96a(final AdvertisingInfo bVar) {
        new Thread(new BackgroundPriorityRunnable() {
            public void onRun() {
                AdvertisingInfo a = AdvertisingInfoProvider.this.m100e();
                if (!bVar.equals(a)) {
                    Fabric.m456h().mo8506a("Fabric", "Asychronously getting Advertising Info and storing it to preferences");
                    AdvertisingInfoProvider.this.m98b(a);
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: b */
    public void m98b(AdvertisingInfo bVar) {
        if (m99c(bVar)) {
            this.f111b.mo8482a(this.f111b.mo8483b().putString("advertising_id", bVar.f108a).putBoolean("limit_ad_tracking_enabled", bVar.f109b));
        } else {
            this.f111b.mo8482a(this.f111b.mo8483b().remove("advertising_id").remove("limit_ad_tracking_enabled"));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public AdvertisingInfo mo8259b() {
        return new AdvertisingInfo(this.f111b.mo8481a().getString("advertising_id", ""), this.f111b.mo8481a().getBoolean("limit_ad_tracking_enabled", false));
    }

    /* renamed from: c */
    public AdvertisingInfoStrategy mo8260c() {
        return new AdvertisingInfoReflectionStrategy(this.f110a);
    }

    /* renamed from: d */
    public AdvertisingInfoStrategy mo8261d() {
        return new AdvertisingInfoServiceStrategy(this.f110a);
    }

    /* renamed from: c */
    private boolean m99c(AdvertisingInfo bVar) {
        return bVar != null && !TextUtils.isEmpty(bVar.f108a);
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public AdvertisingInfo m100e() {
        AdvertisingInfo a = mo8260c().mo8263a();
        if (!m99c(a)) {
            a = mo8261d().mo8263a();
            if (!m99c(a)) {
                Fabric.m456h().mo8506a("Fabric", "AdvertisingInfo not present");
            } else {
                Fabric.m456h().mo8506a("Fabric", "Using AdvertisingInfo from Service Provider");
            }
        } else {
            Fabric.m456h().mo8506a("Fabric", "Using AdvertisingInfo from Reflection Provider");
        }
        return a;
    }
}
