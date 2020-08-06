package com.p028a.p029a.p030a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.facebook.GraphResponse;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.a.a.a.a */
public class CCSQLManager {
    /* renamed from: a */
    public static boolean m1325a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            if (m1328b(sQLiteDatabase, str) >= 0) {
                sQLiteDatabase.execSQL("delete from " + str);
            }
            return true;
        } catch (SQLException e) {
            MLog.m1844c("cleanTableData faild!" + e.toString());
            return false;
        }
    }

    /* renamed from: b */
    public static int m1328b(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = sQLiteDatabase.rawQuery("select * from " + str, null);
            i = cursor.getCount();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            MLog.m1844c("count error " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return i;
    }

    /* renamed from: a */
    public static boolean m1326a(SQLiteDatabase sQLiteDatabase, Collection<UMCCAggregatedObject> collection) {
        try {
            sQLiteDatabase.beginTransaction();
            if (m1328b(sQLiteDatabase, "aggregated_cache") > 0) {
                m1325a(sQLiteDatabase, "aggregated_cache");
            }
            for (UMCCAggregatedObject a : collection) {
                sQLiteDatabase.insert("aggregated_cache", null, m1317a(a));
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            return true;
        } catch (SQLException e) {
            MLog.m1844c("insert to Aggregated cache table faild!");
            sQLiteDatabase.endTransaction();
            return false;
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m1327a(C0614cl clVar, SQLiteDatabase sQLiteDatabase, Collection<UMCCAggregatedObject> collection) {
        try {
            sQLiteDatabase.beginTransaction();
            for (UMCCAggregatedObject a : collection) {
                sQLiteDatabase.insert("aggregated", null, m1317a(a));
            }
            sQLiteDatabase.setTransactionSuccessful();
            m1325a(sQLiteDatabase, "aggregated_cache");
            clVar.mo9396a(GraphResponse.SUCCESS_KEY, false);
            sQLiteDatabase.endTransaction();
            return true;
        } catch (SQLException e) {
            MLog.m1844c("insert to Aggregated cache table faild!");
            sQLiteDatabase.endTransaction();
            return false;
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m1324a(SQLiteDatabase sQLiteDatabase, C0614cl clVar) {
        try {
            sQLiteDatabase.beginTransaction();
            if (m1328b(sQLiteDatabase, "aggregated_cache") <= 0) {
                clVar.mo9396a("faild", false);
                return false;
            }
            sQLiteDatabase.execSQL("insert into aggregated(key, count, value, totalTimestamp, timeWindowNum, label) select key, count, value, totalTimestamp, timeWindowNum, label from aggregated_cache");
            sQLiteDatabase.setTransactionSuccessful();
            m1325a(sQLiteDatabase, "aggregated_cache");
            clVar.mo9396a(GraphResponse.SUCCESS_KEY, false);
            sQLiteDatabase.endTransaction();
            return true;
        } catch (SQLException e) {
            clVar.mo9396a(false, false);
            MLog.m1844c("cacheToAggregatedTable happen " + e.toString());
            return false;
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* renamed from: a */
    private static ContentValues m1317a(UMCCAggregatedObject cnVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", cnVar.mo9488a());
        contentValues.put("label", cnVar.mo9498c());
        contentValues.put("count", Long.valueOf(cnVar.mo9503g()));
        contentValues.put(FirebaseAnalytics.Param.VALUE, Long.valueOf(cnVar.mo9502f()));
        contentValues.put("totalTimestamp", Long.valueOf(cnVar.mo9501e()));
        contentValues.put("timeWindowNum", cnVar.mo9504h());
        return contentValues;
    }

    /* renamed from: b */
    public static boolean m1330b(SQLiteDatabase sQLiteDatabase, C0614cl clVar) {
        Cursor cursor = null;
        try {
            HashMap hashMap = new HashMap();
            cursor = sQLiteDatabase.rawQuery("select * from aggregated_cache", null);
            while (cursor.moveToNext()) {
                UMCCAggregatedObject cnVar = new UMCCAggregatedObject();
                cnVar.mo9493a(UMCCDBUtils.m2041b(cursor.getString(cursor.getColumnIndex("key"))));
                cnVar.mo9497b(UMCCDBUtils.m2041b(cursor.getString(cursor.getColumnIndex("label"))));
                cnVar.mo9499c((long) cursor.getInt(cursor.getColumnIndex("count")));
                cnVar.mo9495b((long) cursor.getInt(cursor.getColumnIndex(FirebaseAnalytics.Param.VALUE)));
                cnVar.mo9496b(cursor.getString(cursor.getColumnIndex("timeWindowNum")));
                cnVar.mo9489a(Long.parseLong(cursor.getString(cursor.getColumnIndex("totalTimestamp"))));
                hashMap.put(UMCCDBUtils.m2041b(cursor.getString(cursor.getColumnIndex("key"))), cnVar);
            }
            if (hashMap.size() > 0) {
                clVar.mo9396a(hashMap, false);
            } else {
                clVar.mo9396a("faild", false);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException e) {
            clVar.mo9396a(false, false);
            MLog.m1844c("cacheToMemory happen " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return false;
    }

    /* renamed from: a */
    public static void m1322a(SQLiteDatabase sQLiteDatabase, boolean z, C0614cl clVar) {
        m1325a(sQLiteDatabase, "system");
        m1325a(sQLiteDatabase, "aggregated");
        if (!z) {
            m1325a(sQLiteDatabase, "limitedck");
            clVar.mo9396a(GraphResponse.SUCCESS_KEY, false);
        }
    }

    /* renamed from: a */
    public static void m1320a(SQLiteDatabase sQLiteDatabase, String str, long j, long j2) {
        try {
            int b = m1328b(sQLiteDatabase, "system");
            int c = UMCCAggregatedRestrictionManager.m2196a().mo9529c();
            if (b < c) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("key", str);
                contentValues.put("timeStamp", Long.valueOf(j2));
                contentValues.put("count", Long.valueOf(j));
                sQLiteDatabase.insert("system", null, contentValues);
            } else if (b == c) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", "__meta_ve_of");
                contentValues2.put("timeStamp", Long.valueOf(System.currentTimeMillis()));
                contentValues2.put("count", 1);
                sQLiteDatabase.insert("system", null, contentValues2);
            } else {
                m1332c(sQLiteDatabase, "__meta_ve_of");
            }
        } catch (SQLException e) {
        }
    }

    /* renamed from: c */
    private static void m1332c(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("update system set count=count+1 where key like '" + str + "'");
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (SQLException e) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static void m1323a(C0614cl clVar, SQLiteDatabase sQLiteDatabase, List<String> list) {
        try {
            sQLiteDatabase.beginTransaction();
            if (m1328b(sQLiteDatabase, "limitedck") > 0) {
                m1325a(sQLiteDatabase, "limitedck");
            }
            for (String put : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("ck", put);
                sQLiteDatabase.insert("limitedck", null, contentValues);
            }
            sQLiteDatabase.setTransactionSuccessful();
            clVar.mo9396a(GraphResponse.SUCCESS_KEY, false);
        } catch (SQLException e) {
            MLog.m1844c("insertToLimitCKTable error " + e.toString());
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* renamed from: a */
    public static void m1321a(SQLiteDatabase sQLiteDatabase, Map<String, UMCCSystemBuffer> map, C0614cl clVar) {
        int i = 0;
        Cursor cursor = null;
        try {
            UMCCSystemBuffer coVar = map.get("__ag_of");
            if (coVar != null) {
                cursor = sQLiteDatabase.rawQuery("select * from " + "system where key=\"__ag_of\"", null);
                cursor.moveToFirst();
                long j = 0;
                while (!cursor.isAfterLast()) {
                    if (cursor.getCount() > 0) {
                        i = cursor.getInt(cursor.getColumnIndex("count"));
                        j = cursor.getLong(cursor.getColumnIndex("timeStamp"));
                        sQLiteDatabase.execSQL("delete from " + "system where key=\"__ag_of\"");
                    }
                    cursor.moveToNext();
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("key", coVar.mo9511c());
                contentValues.put("count", Long.valueOf(i == 0 ? coVar.mo9513e() : ((long) i) + coVar.mo9513e()));
                if (j == 0) {
                    j = coVar.mo9512d();
                }
                contentValues.put("timeStamp", Long.valueOf(j));
                sQLiteDatabase.insert("system", null, contentValues);
                clVar.mo9396a(GraphResponse.SUCCESS_KEY, false);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException e) {
            MLog.m1844c("save to system table error " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069  */
    /* renamed from: a */
    public static String m1318a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        SQLException e;
        Object obj;
        String str;
        Object obj2;
        Cursor cursor2 = null;
        try {
            sQLiteDatabase.beginTransaction();
            if (m1328b(sQLiteDatabase, "aggregated_cache") <= 0) {
                String valueOf = String.valueOf(0);
                if (cursor2 != null) {
                    cursor2.close();
                }
                sQLiteDatabase.endTransaction();
                str = valueOf;
            } else {
                cursor = sQLiteDatabase.rawQuery("select * from aggregated_cache", null);
                try {
                    if (cursor.moveToLast()) {
                        obj2 = cursor.getString(cursor.getColumnIndex("timeWindowNum"));
                    } else {
                        obj2 = cursor2;
                    }
                } catch (SQLException e2) {
                    SQLException sQLException = e2;
                    obj = cursor2;
                    e = sQLException;
                    try {
                        MLog.m1844c("queryLastTimeWindowNumFromCache error " + e.toString());
                        if (cursor != null) {
                        }
                        sQLiteDatabase.endTransaction();
                        str = obj;
                        return str;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        sQLiteDatabase.endTransaction();
                        throw th;
                    }
                }
                try {
                    sQLiteDatabase.setTransactionSuccessful();
                    if (cursor != null) {
                        cursor.close();
                    }
                    sQLiteDatabase.endTransaction();
                    str = obj2;
                } catch (SQLException e3) {
                    e = e3;
                    obj = obj2;
                    MLog.m1844c("queryLastTimeWindowNumFromCache error " + e.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                    sQLiteDatabase.endTransaction();
                    str = obj;
                    return str;
                }
            }
        } catch (SQLException e4) {
            cursor = cursor2;
            SQLException sQLException2 = e4;
            obj = cursor2;
            e = sQLException2;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursor2;
            if (cursor != null) {
            }
            sQLiteDatabase.endTransaction();
            throw th;
        }
        return str;
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [org.json.JSONObject] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cb  */
    /* renamed from: b */
    public static JSONObject m1329b(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        JSONArray jSONArray;
        Cursor cursor2 = null;
        try {
            if (m1328b(sQLiteDatabase, "aggregated") > 0) {
                cursor = sQLiteDatabase.rawQuery("select * from aggregated", null);
                try {
                    JSONObject jSONObject = new JSONObject();
                    while (cursor.moveToNext()) {
                        try {
                            String string = cursor.getString(cursor.getColumnIndex("key"));
                            if (jSONObject.has(string)) {
                                jSONArray = jSONObject.getJSONArray(string);
                                jSONObject.remove(string);
                            } else {
                                jSONArray = new JSONArray();
                            }
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("v_sum", cursor.getLong(cursor.getColumnIndex(FirebaseAnalytics.Param.VALUE)));
                            jSONObject2.put("ts_sum", cursor.getLong(cursor.getColumnIndex("totalTimestamp")));
                            jSONObject2.put("tw_num", Integer.parseInt(cursor.getString(cursor.getColumnIndex("timeWindowNum"))));
                            jSONObject2.put("count", cursor.getInt(cursor.getColumnIndex("count")));
                            jSONObject2.put("labels", UMCCDBUtils.m2040a(cursor.getString(cursor.getColumnIndex("label"))));
                            jSONArray.put(jSONObject2);
                            jSONObject.put(string, jSONArray);
                        } catch (Exception e) {
                        }
                    }
                    if (cursor == null) {
                        return jSONObject;
                    }
                    cursor.close();
                    return jSONObject;
                } catch (SQLException e2) {
                    e = e2;
                    try {
                        MLog.m1844c("readAllAggregatedDataForUpload error " + e.toString());
                        if (cursor != null) {
                            cursor.close();
                        }
                        return cursor2;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                }
            } else {
                if (cursor2 != null) {
                    cursor2.close();
                }
                return cursor2;
            }
        } catch (SQLException e3) {
            e = e3;
            cursor = cursor2;
            MLog.m1844c("readAllAggregatedDataForUpload error " + e.toString());
            if (cursor != null) {
            }
            return cursor2;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x009a  */
    /* renamed from: a */
    public static JSONObject m1319a(C0614cl clVar, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        Cursor cursor2;
        JSONArray jSONArray;
        try {
            JSONObject jSONObject = new JSONObject();
            if (m1328b(sQLiteDatabase, "system") > 0) {
                cursor2 = sQLiteDatabase.rawQuery("select * from system", null);
                while (cursor2.moveToNext()) {
                    try {
                        try {
                            String string = cursor2.getString(cursor2.getColumnIndex("key"));
                            if (jSONObject.has(string)) {
                                jSONArray = jSONObject.getJSONArray(string);
                                jSONObject.remove(string);
                            } else {
                                jSONArray = new JSONArray();
                            }
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put(FirebaseAnalytics.Param.VALUE, cursor2.getInt(cursor2.getColumnIndex("count")));
                            jSONObject2.put("ts", cursor2.getLong(cursor2.getColumnIndex("timeStamp")));
                            jSONArray.put(jSONObject2);
                            jSONObject.put(string, jSONArray);
                        } catch (Exception e) {
                        }
                    } catch (SQLException e2) {
                        SQLException sQLException = e2;
                        cursor = cursor2;
                        e = sQLException;
                    } catch (Throwable th) {
                        cursor = cursor2;
                        th = th;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                }
            } else {
                cursor2 = null;
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            return jSONObject;
        } catch (SQLException e3) {
            e = e3;
            cursor = null;
            try {
                clVar.mo9396a("faild", false);
                MLog.m1844c("readAllSystemDataForUpload error " + e.toString());
                if (cursor == null) {
                    return null;
                }
                cursor.close();
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.util.List<java.lang.String>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005b  */
    /* renamed from: c */
    public static List<String> m1331c(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            if (m1328b(sQLiteDatabase, "limitedck") > 0) {
                cursor = sQLiteDatabase.rawQuery("select * from limitedck", null);
                try {
                    ArrayList arrayList = new ArrayList();
                    while (cursor.moveToNext()) {
                        arrayList.add(cursor.getString(cursor.getColumnIndex("ck")));
                    }
                    if (cursor == null) {
                        return arrayList;
                    }
                    cursor.close();
                    return arrayList;
                } catch (SQLException e) {
                    e = e;
                    try {
                        MLog.m1844c("loadLimitCKFromDB error " + e.toString());
                        if (cursor != null) {
                            cursor.close();
                        }
                        return cursor2;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                }
            } else {
                if (cursor2 != null) {
                    cursor2.close();
                }
                return cursor2;
            }
        } catch (SQLException e2) {
            e = e2;
            cursor = cursor2;
            MLog.m1844c("loadLimitCKFromDB error " + e.toString());
            if (cursor != null) {
            }
            return cursor2;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
