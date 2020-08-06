package p005b.p006a.p007a.p008a.p009a.p011b;

import android.content.Context;
import p005b.p006a.p007a.p008a.Fabric;

/* renamed from: b.a.a.a.a.b.d */
class AdvertisingInfoReflectionStrategy implements AdvertisingInfoStrategy {

    /* renamed from: a */
    private final Context f114a;

    public AdvertisingInfoReflectionStrategy(Context context) {
        this.f114a = context.getApplicationContext();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo8264a(Context context) {
        try {
            if (((Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public AdvertisingInfo mo8263a() {
        if (mo8264a(this.f114a)) {
            return new AdvertisingInfo(m105b(), m106c());
        }
        return null;
    }

    /* renamed from: b */
    private String m105b() {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(m107d(), new Object[0]);
        } catch (Exception e) {
            Fabric.m456h().mo8513d("Fabric", "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    /* renamed from: c */
    private boolean m106c() {
        try {
            return ((Boolean) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(m107d(), new Object[0])).booleanValue();
        } catch (Exception e) {
            Fabric.m456h().mo8513d("Fabric", "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return false;
        }
    }

    /* renamed from: d */
    private Object m107d() {
        boolean z = false;
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.f114a});
        } catch (Exception e) {
            Fabric.m456h().mo8513d("Fabric", "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
            return z;
        }
    }
}
