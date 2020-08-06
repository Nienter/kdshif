package p005b.p006a.p007a.p008a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.AdRequest;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;

/* renamed from: b.a.a.a.e */
class FabricKitsFinder implements Callable<Map<String, KitInfo>> {

    /* renamed from: a */
    final String f390a;

    FabricKitsFinder(String str) {
        this.f390a = str;
    }

    /* renamed from: a */
    public Map<String, KitInfo> call() {
        HashMap hashMap = new HashMap();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        hashMap.putAll(m475c());
        hashMap.putAll(m476d());
        Fabric.m456h().mo8509b("Fabric", "finish scanning in " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return hashMap;
    }

    /* renamed from: c */
    private Map<String, KitInfo> m475c() {
        HashMap hashMap = new HashMap();
        try {
            Class.forName("com.google.android.gms.ads.AdView");
            KitInfo kVar = new KitInfo("com.google.firebase.firebase-ads", AdRequest.VERSION, "binary");
            hashMap.put(kVar.mo8562a(), kVar);
            Fabric.m456h().mo8509b("Fabric", "Found kit: com.google.firebase.firebase-ads");
        } catch (Exception e) {
        }
        return hashMap;
    }

    /* renamed from: d */
    private Map<String, KitInfo> m476d() {
        HashMap hashMap = new HashMap();
        ZipFile b = mo8541b();
        Enumeration<? extends ZipEntry> entries = b.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.getName().startsWith("fabric/") && zipEntry.getName().length() > "fabric/".length()) {
                KitInfo a = m474a(zipEntry, b);
                if (a != null) {
                    hashMap.put(a.mo8562a(), a);
                    Fabric.m456h().mo8509b("Fabric", String.format("Found kit:[%s] version:[%s]", new Object[]{a.mo8562a(), a.mo8563b()}));
                }
            }
        }
        if (b != null) {
            try {
                b.close();
            } catch (IOException e) {
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    private KitInfo m474a(ZipEntry zipEntry, ZipFile zipFile) {
        InputStream inputStream;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                String property = properties.getProperty("fabric-identifier");
                String property2 = properties.getProperty("fabric-version");
                String property3 = properties.getProperty("fabric-build-type");
                if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                    throw new IllegalStateException("Invalid format of fabric file," + zipEntry.getName());
                }
                KitInfo kVar = new KitInfo(property, property2, property3);
                CommonUtils.m139a((Closeable) inputStream);
                return kVar;
            } catch (IOException e) {
                e = e;
                try {
                    Fabric.m456h().mo8516e("Fabric", "Error when parsing fabric properties " + zipEntry.getName(), e);
                    CommonUtils.m139a((Closeable) inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.m139a((Closeable) inputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            CommonUtils.m139a((Closeable) inputStream);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public ZipFile mo8541b() {
        return new ZipFile(this.f390a);
    }
}
