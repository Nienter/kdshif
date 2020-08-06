package p005b.p006a.p007a.p008a.p009a.p017g;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p016f.FileStoreImpl;

/* renamed from: b.a.a.a.a.g.i */
class DefaultCachedSettingsIo implements CachedSettingsIo {

    /* renamed from: a */
    private final Kit f302a;

    public DefaultCachedSettingsIo(Kit iVar) {
        this.f302a = iVar;
    }

    /* renamed from: a */
    public JSONObject mo8487a() {
        FileInputStream fileInputStream;
        JSONObject jSONObject;
        FileInputStream fileInputStream2 = null;
        Fabric.m456h().mo8506a("Fabric", "Reading cached settings...");
        try {
            File file = new File(new FileStoreImpl(this.f302a).mo8479a(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = new JSONObject(CommonUtils.m129a((InputStream) fileInputStream));
                    fileInputStream2 = fileInputStream;
                } catch (Exception e) {
                    e = e;
                    try {
                        Fabric.m456h().mo8516e("Fabric", "Failed to fetch cached settings", e);
                        CommonUtils.m140a((Closeable) fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        CommonUtils.m140a((Closeable) fileInputStream2, "Error while closing settings cache file.");
                        throw th;
                    }
                }
            } else {
                Fabric.m456h().mo8506a("Fabric", "No cached settings found.");
                jSONObject = null;
            }
            CommonUtils.m140a((Closeable) fileInputStream2, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            Fabric.m456h().mo8516e("Fabric", "Failed to fetch cached settings", e);
            CommonUtils.m140a((Closeable) fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th2) {
            th = th2;
            CommonUtils.m140a((Closeable) fileInputStream2, "Error while closing settings cache file.");
            throw th;
        }
    }

    /* renamed from: a */
    public void mo8488a(long j, JSONObject jSONObject) {
        FileWriter fileWriter;
        Fabric.m456h().mo8506a("Fabric", "Writing settings to cache file...");
        if (jSONObject != null) {
            FileWriter fileWriter2 = null;
            try {
                jSONObject.put("expires_at", j);
                fileWriter = new FileWriter(new File(new FileStoreImpl(this.f302a).mo8479a(), "com.crashlytics.settings.json"));
                try {
                    fileWriter.write(jSONObject.toString());
                    fileWriter.flush();
                    CommonUtils.m140a((Closeable) fileWriter, "Failed to close settings writer.");
                } catch (Exception e) {
                    e = e;
                    try {
                        Fabric.m456h().mo8516e("Fabric", "Failed to cache settings", e);
                        CommonUtils.m140a((Closeable) fileWriter, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        th = th;
                        fileWriter2 = fileWriter;
                        CommonUtils.m140a((Closeable) fileWriter2, "Failed to close settings writer.");
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                fileWriter = null;
                Fabric.m456h().mo8516e("Fabric", "Failed to cache settings", e);
                CommonUtils.m140a((Closeable) fileWriter, "Failed to close settings writer.");
            } catch (Throwable th2) {
                th = th2;
                CommonUtils.m140a((Closeable) fileWriter2, "Failed to close settings writer.");
                throw th;
            }
        }
    }
}
