package com.p028a.p029a.p030a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsConstants;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.a.a.a.cs */
public class UMCCStorageManager {

    /* renamed from: a */
    private static Context f1554a;

    /* renamed from: com.a.a.a.cs$a */
    /* compiled from: UMCCStorageManager */
    private static final class C0631a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final UMCCStorageManager f1555a = new UMCCStorageManager();
    }

    private UMCCStorageManager() {
        if (f1554a != null) {
        }
    }

    /* renamed from: a */
    public static UMCCStorageManager m2200a(Context context) {
        f1554a = context;
        return C0631a.f1555a;
    }

    /* renamed from: a */
    public void mo9531a(C0614cl clVar) {
        try {
            SQLiteDatabase a = DatabaseManager.m2405a(f1554a).mo9635a();
            String a2 = CCSQLManager.m1318a(a);
            String a3 = UMCCTimeRange.m2222a(System.currentTimeMillis());
            if (a2.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                clVar.mo9396a("faild", false);
                return;
            }
            if (!a2.equals(a3)) {
                CCSQLManager.m1324a(a, clVar);
            } else {
                CCSQLManager.m1330b(a, clVar);
            }
            DatabaseManager.m2405a(f1554a).mo9637c();
        } catch (Exception e) {
            clVar.mo9396a(false, false);
            MLog.m1844c("load agg data error");
        } finally {
            DatabaseManager.m2405a(f1554a).mo9637c();
        }
    }

    /* renamed from: a */
    public void mo9534a(C0614cl clVar, Map<List<String>, UMCCAggregatedObject> map) {
        try {
            CCSQLManager.m1326a(DatabaseManager.m2405a(f1554a).mo9636b(), map.values());
            clVar.mo9396a(GraphResponse.SUCCESS_KEY, false);
        } catch (Exception e) {
            MLog.m1844c("save agg data error");
        } finally {
            DatabaseManager.m2405a(f1554a).mo9637c();
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public JSONObject mo9530a() {
        try {
            JSONObject b = CCSQLManager.m1329b(DatabaseManager.m2405a(f1554a).mo9635a());
            DatabaseManager.m2405a(f1554a).mo9637c();
            return b;
        } catch (Exception e) {
            MLog.m1844c("upload agg date error");
            DatabaseManager.m2405a(f1554a).mo9637c();
            return null;
        } catch (Throwable th) {
            DatabaseManager.m2405a(f1554a).mo9637c();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: b */
    public JSONObject mo9537b(C0614cl clVar) {
        try {
            JSONObject a = CCSQLManager.m1319a(clVar, DatabaseManager.m2405a(f1554a).mo9635a());
            DatabaseManager.m2405a(f1554a).mo9637c();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseManager.m2405a(f1554a).mo9637c();
            return null;
        } catch (Throwable th) {
            DatabaseManager.m2405a(f1554a).mo9637c();
            throw th;
        }
    }

    /* renamed from: a */
    public void mo9535a(C0614cl clVar, boolean z) {
        try {
            CCSQLManager.m1322a(DatabaseManager.m2405a(f1554a).mo9636b(), z, clVar);
        } catch (Exception e) {
            MLog.m1844c("notifyUploadSuccess error");
        } finally {
            DatabaseManager.m2405a(f1554a).mo9637c();
        }
    }

    /* renamed from: a */
    public void mo9532a(C0614cl clVar, String str, long j, long j2) {
        try {
            CCSQLManager.m1320a(DatabaseManager.m2405a(f1554a).mo9636b(), str, j, j2);
            clVar.mo9396a(GraphResponse.SUCCESS_KEY, false);
        } catch (Exception e) {
            MLog.m1844c("package size to big or envelopeOverflowPackageCount exception");
        } finally {
            DatabaseManager.m2405a(f1554a).mo9637c();
        }
    }

    /* renamed from: a */
    public void mo9533a(C0614cl clVar, List<String> list) {
        try {
            CCSQLManager.m1323a(clVar, DatabaseManager.m2405a(f1554a).mo9636b(), list);
        } catch (Exception e) {
            MLog.m1844c("saveToLimitCKTable exception");
        } finally {
            DatabaseManager.m2405a(f1554a).mo9637c();
        }
    }

    /* renamed from: b */
    public void mo9538b(C0614cl clVar, Map<String, UMCCSystemBuffer> map) {
        try {
            CCSQLManager.m1321a(DatabaseManager.m2405a(f1554a).mo9636b(), map, clVar);
        } catch (Exception e) {
            MLog.m1844c("arrgetated system buffer exception");
        } finally {
            DatabaseManager.m2405a(f1554a).mo9637c();
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: b */
    public List<String> mo9536b() {
        try {
            List<String> c = CCSQLManager.m1331c(DatabaseManager.m2405a(f1554a).mo9635a());
            DatabaseManager.m2405a(f1554a).mo9637c();
            return c;
        } catch (Exception e) {
            MLog.m1844c("loadCKToMemory exception");
            DatabaseManager.m2405a(f1554a).mo9637c();
            return null;
        } catch (Throwable th) {
            DatabaseManager.m2405a(f1554a).mo9637c();
            throw th;
        }
    }

    /* renamed from: c */
    public void mo9539c(C0614cl clVar, Map<List<String>, UMCCAggregatedObject> map) {
        try {
            CCSQLManager.m1327a(clVar, DatabaseManager.m2405a(f1554a).mo9636b(), map.values());
        } catch (Exception e) {
            MLog.m1844c("cacheToData error");
        } finally {
            DatabaseManager.m2405a(f1554a).mo9637c();
        }
    }
}
