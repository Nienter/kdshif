package p005b.p006a.p007a.p008a.p009a.p011b;

import android.content.Context;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p010a.MemoryValueCache;
import p005b.p006a.p007a.p008a.p009a.p010a.ValueLoader;

/* renamed from: b.a.a.a.a.b.q */
public class InstallerPackageNameProvider {

    /* renamed from: a */
    private final ValueLoader<String> f152a = new ValueLoader<String>() {
        /* renamed from: a */
        public String load(Context context) {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? "" : installerPackageName;
        }
    };

    /* renamed from: b */
    private final MemoryValueCache<String> f153b = new MemoryValueCache<>();

    /* renamed from: a */
    public String mo8304a(Context context) {
        try {
            String a = this.f153b.mo8250a(context, this.f152a);
            if ("".equals(a)) {
                return null;
            }
            return a;
        } catch (Exception e) {
            Fabric.m456h().mo8516e("Fabric", "Failed to determine installer package name", e);
            return null;
        }
    }
}
