package p005b.p006a.p007a.p008a.p009a.p011b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;

/* renamed from: b.a.a.a.a.b.p */
public class IdManager {

    /* renamed from: e */
    private static final Pattern f137e = Pattern.compile("[^\\p{Alnum}]");

    /* renamed from: f */
    private static final String f138f = Pattern.quote("/");

    /* renamed from: a */
    AdvertisingInfoProvider f139a;

    /* renamed from: b */
    AdvertisingInfo f140b;

    /* renamed from: c */
    boolean f141c;

    /* renamed from: d */
    FirebaseInfo f142d;

    /* renamed from: g */
    private final ReentrantLock f143g = new ReentrantLock();

    /* renamed from: h */
    private final InstallerPackageNameProvider f144h;

    /* renamed from: i */
    private final boolean f145i;

    /* renamed from: j */
    private final boolean f146j;

    /* renamed from: k */
    private final Context f147k;

    /* renamed from: l */
    private final String f148l;

    /* renamed from: m */
    private final String f149m;

    /* renamed from: n */
    private final Collection<Kit> f150n;

    /* renamed from: b.a.a.a.a.b.p$a */
    /* compiled from: IdManager */
    public enum C0430a {
        WIFI_MAC_ADDRESS(1),
        BLUETOOTH_MAC_ADDRESS(2),
        FONT_TOKEN(53),
        ANDROID_ID(100),
        ANDROID_DEVICE_ID(101),
        ANDROID_SERIAL(102),
        ANDROID_ADVERTISING_ID(103);
        
        public final int protobufIndex;

        private C0430a(int i) {
            this.protobufIndex = i;
        }
    }

    public IdManager(Context context, String str, String str2, Collection<Kit> collection) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        } else if (collection == null) {
            throw new IllegalArgumentException("kits must not be null");
        } else {
            this.f147k = context;
            this.f148l = str;
            this.f149m = str2;
            this.f150n = collection;
            this.f144h = new InstallerPackageNameProvider();
            this.f139a = new AdvertisingInfoProvider(context);
            this.f142d = new FirebaseInfo();
            this.f145i = CommonUtils.m143a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.f145i) {
                Fabric.m456h().mo8506a("Fabric", "Device ID collection disabled for " + context.getPackageName());
            }
            this.f146j = CommonUtils.m143a(context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.f146j) {
                Fabric.m456h().mo8506a("Fabric", "User information collection disabled for " + context.getPackageName());
            }
        }
    }

    /* renamed from: a */
    public boolean mo8290a() {
        return this.f146j;
    }

    /* renamed from: a */
    private String m179a(String str) {
        if (str == null) {
            return null;
        }
        return f137e.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    /* renamed from: b */
    public String mo8291b() {
        String str = this.f149m;
        if (str != null) {
            return str;
        }
        SharedPreferences a = CommonUtils.m126a(this.f147k);
        m183b(a);
        String string = a.getString("crashlytics.installation.id", null);
        if (string == null) {
            return m178a(a);
        }
        return string;
    }

    /* renamed from: c */
    public String mo8292c() {
        return this.f148l;
    }

    /* renamed from: d */
    public String mo8293d() {
        return mo8294e() + "/" + mo8295f();
    }

    /* renamed from: e */
    public String mo8294e() {
        return m182b(Build.VERSION.RELEASE);
    }

    /* renamed from: f */
    public String mo8295f() {
        return m182b(Build.VERSION.INCREMENTAL);
    }

    /* renamed from: g */
    public String mo8296g() {
        return String.format(Locale.US, "%s/%s", new Object[]{m182b(Build.MANUFACTURER), m182b(Build.MODEL)});
    }

    /* renamed from: b */
    private String m182b(String str) {
        return str.replaceAll(f138f, "");
    }

    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: a */
    private String m178a(SharedPreferences sharedPreferences) {
        this.f143g.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = m179a(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            return string;
        } finally {
            this.f143g.unlock();
        }
    }

    /* renamed from: b */
    private void m183b(SharedPreferences sharedPreferences) {
        AdvertisingInfo n = mo8303n();
        if (n != null) {
            m180a(sharedPreferences, n.f108a);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: a */
    private void m180a(SharedPreferences sharedPreferences, String str) {
        this.f143g.lock();
        try {
            if (!TextUtils.isEmpty(str)) {
                String string = sharedPreferences.getString("crashlytics.advertising.id", null);
                if (TextUtils.isEmpty(string)) {
                    sharedPreferences.edit().putString("crashlytics.advertising.id", str).commit();
                } else if (!string.equals(str)) {
                    sharedPreferences.edit().remove("crashlytics.installation.id").putString("crashlytics.advertising.id", str).commit();
                }
                this.f143g.unlock();
            }
        } finally {
            this.f143g.unlock();
        }
    }

    /* renamed from: h */
    public Map<C0430a, String> mo8297h() {
        HashMap hashMap = new HashMap();
        for (Kit next : this.f150n) {
            if (next instanceof DeviceIdentifierProvider) {
                for (Map.Entry next2 : ((DeviceIdentifierProvider) next).getDeviceIdentifiers().entrySet()) {
                    m181a(hashMap, (C0430a) next2.getKey(), (String) next2.getValue());
                }
            }
        }
        String k = mo8300k();
        if (TextUtils.isEmpty(k)) {
            m181a(hashMap, C0430a.ANDROID_ID, mo8301l());
        } else {
            m181a(hashMap, C0430a.ANDROID_ADVERTISING_ID, k);
        }
        return Collections.unmodifiableMap(hashMap);
    }

    /* renamed from: i */
    public String mo8298i() {
        return this.f144h.mo8304a(this.f147k);
    }

    /* renamed from: j */
    public Boolean mo8299j() {
        if (mo8302m()) {
            return m184o();
        }
        return null;
    }

    /* renamed from: k */
    public String mo8300k() {
        if (!mo8302m()) {
            return null;
        }
        AdvertisingInfo n = mo8303n();
        if (n == null || n.f109b) {
            return null;
        }
        return n.f108a;
    }

    /* renamed from: a */
    private void m181a(Map<C0430a, String> map, C0430a aVar, String str) {
        if (str != null) {
            map.put(aVar, str);
        }
    }

    /* renamed from: l */
    public String mo8301l() {
        boolean equals = Boolean.TRUE.equals(m184o());
        if (!mo8302m() || equals) {
            return null;
        }
        String string = Settings.Secure.getString(this.f147k.getContentResolver(), "android_id");
        if (!"9774d56d682e549c".equals(string)) {
            return m179a(string);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public boolean mo8302m() {
        return this.f145i && !this.f142d.mo8289b(this.f147k);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: n */
    public synchronized AdvertisingInfo mo8303n() {
        if (!this.f141c) {
            this.f140b = this.f139a.mo8258a();
            this.f141c = true;
        }
        return this.f140b;
    }

    /* renamed from: o */
    private Boolean m184o() {
        AdvertisingInfo n = mo8303n();
        if (n != null) {
            return Boolean.valueOf(n.f109b);
        }
        return null;
    }
}
