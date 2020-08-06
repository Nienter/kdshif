package com.p028a.p029a.p030a;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.text.TextUtils;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.a.a.a.cz */
public class UMStoreManager {

    /* renamed from: a */
    private static Context f1566a = null;

    /* renamed from: b */
    private static String f1567b = null;

    /* renamed from: c */
    private List<String> f1568c;

    /* renamed from: com.a.a.a.cz$a */
    /* compiled from: UMStoreManager */
    public enum C0636a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION
    }

    /* renamed from: com.a.a.a.cz$b */
    /* compiled from: UMStoreManager */
    private static class C0637b {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final UMStoreManager f1575a = new UMStoreManager();
    }

    private UMStoreManager() {
        this.f1568c = new ArrayList();
        if (f1566a != null) {
            m2245b();
            this.f1568c.clear();
        }
    }

    /* renamed from: a */
    public static final UMStoreManager m2242a(Context context) {
        f1566a = context;
        return C0637b.f1575a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        com.p028a.p029a.p030a.UMDBUtils.m2241b(f1566a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008f, code lost:
        if (r0 != null) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009f, code lost:
        if (r0 != null) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ae, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00af, code lost:
        r6 = r1;
        r1 = r0;
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0089 A[ExcHandler: SQLiteDatabaseCorruptException (e android.database.sqlite.SQLiteDatabaseCorruptException), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009e A[ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b4 A[SYNTHETIC, Splitter:B:36:0x00b4] */
    /* renamed from: a */
    public void mo9556a(JSONArray jSONArray) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase a = UMDBManager.m2235a(f1566a).mo9552a();
            a.beginTransaction();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    ContentValues contentValues = new ContentValues();
                    String optString = jSONObject.optString("__i");
                    if (TextUtils.isEmpty(optString)) {
                        optString = SessionTracker.m1353g(f1566a);
                        if (TextUtils.isEmpty(optString)) {
                            optString = "";
                        }
                    }
                    contentValues.put("__i", optString);
                    contentValues.put("__e", jSONObject.optString("id"));
                    contentValues.put("__t", Integer.valueOf(jSONObject.optInt("__t")));
                    jSONObject.remove("__i");
                    jSONObject.remove("__t");
                    contentValues.put("__s", mo9554a(jSONObject.toString()));
                    a.insert("__et", null, contentValues);
                } catch (Exception e) {
                }
            }
            a.setTransactionSuccessful();
            if (a != null) {
                try {
                    a.endTransaction();
                } catch (Throwable th) {
                }
            }
            UMDBManager.m2235a(f1566a).mo9553b();
            return;
            UMDBManager.m2235a(f1566a).mo9553b();
            return;
            UMDBManager.m2235a(f1566a).mo9553b();
        } catch (SQLiteDatabaseCorruptException e2) {
        } catch (Throwable th2) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        com.p028a.p029a.p030a.UMDBUtils.m2241b(f1566a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004f, code lost:
        if (r0 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        if (r0 != null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006f, code lost:
        r4 = r1;
        r1 = r0;
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049 A[ExcHandler: SQLiteDatabaseCorruptException (e android.database.sqlite.SQLiteDatabaseCorruptException), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005e A[ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074 A[SYNTHETIC, Splitter:B:29:0x0074] */
    /* renamed from: a */
    public boolean mo9558a(String str, String str2, int i) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase a = UMDBManager.m2235a(f1566a).mo9552a();
            a.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("__i", str);
            String a2 = mo9554a(str2);
            if (!TextUtils.isEmpty(a2)) {
                contentValues.put("__a", a2);
                contentValues.put("__t", Integer.valueOf(i));
                a.insert("__er", null, contentValues);
            }
            a.setTransactionSuccessful();
            if (a != null) {
                try {
                    a.endTransaction();
                } catch (Throwable th) {
                }
            }
            UMDBManager.m2235a(f1566a).mo9553b();
        } catch (SQLiteDatabaseCorruptException e) {
        } catch (Throwable th2) {
        }
        return false;
        UMDBManager.m2235a(f1566a).mo9553b();
        return false;
        UMDBManager.m2235a(f1566a).mo9553b();
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v49 */
    /* JADX WARNING: type inference failed for: r1v54 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01f2  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01f7 A[SYNTHETIC, Splitter:B:79:0x01f7] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x020e A[SYNTHETIC, Splitter:B:87:0x020e] */
    /* renamed from: a */
    public boolean mo9559a(String str, JSONObject jSONObject, C0636a aVar) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        JSONObject jSONObject2;
        String str2;
        ? r1 = 0;
        if (jSONObject != null) {
            try {
                sQLiteDatabase = UMDBManager.m2235a(f1566a).mo9552a();
                try {
                    sQLiteDatabase.beginTransaction();
                    if (aVar == C0636a.BEGIN) {
                        long longValue = ((Long) jSONObject.get("__e")).longValue();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("__ii", str);
                        contentValues.put("__e", String.valueOf(longValue));
                        sQLiteDatabase.insert("__sd", null, contentValues);
                        cursor = null;
                    } else if (aVar == C0636a.END) {
                        sQLiteDatabase.execSQL("update __sd set __f=\"" + ((Long) jSONObject.get("__f")).longValue() + "\" where " + "__ii" + "=\"" + str + "\"");
                        cursor = null;
                    } else if (aVar == C0636a.PAGE) {
                        m2243a(str, jSONObject, sQLiteDatabase, "__a");
                        cursor = null;
                    } else if (aVar == C0636a.AUTOPAGE) {
                        m2243a(str, jSONObject, sQLiteDatabase, "__b");
                        cursor = null;
                    } else if (aVar == C0636a.NEWSESSION) {
                        try {
                            jSONObject2 = jSONObject.getJSONObject("__d");
                        } catch (Exception e) {
                            jSONObject2 = null;
                        }
                        if (jSONObject2 != null) {
                            cursor = sQLiteDatabase.rawQuery("select __d from __sd where __ii=\"" + str + "\"", null);
                            if (cursor != null) {
                                while (cursor.moveToNext()) {
                                    try {
                                        r1 = mo9560b(cursor.getString(cursor.getColumnIndex("__d")));
                                    } catch (SQLiteDatabaseCorruptException e2) {
                                        r1 = sQLiteDatabase;
                                        try {
                                            UMDBUtils.m2241b(f1566a);
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            if (r1 != 0) {
                                                try {
                                                    r1.endTransaction();
                                                } catch (Throwable th) {
                                                }
                                            }
                                            UMDBManager.m2235a(f1566a).mo9553b();
                                            return false;
                                        } catch (Throwable th2) {
                                            Throwable th3 = th2;
                                            sQLiteDatabase = r1;
                                            r1 = cursor;
                                            th = th3;
                                            if (r1 != 0) {
                                            }
                                            if (sQLiteDatabase != null) {
                                            }
                                            UMDBManager.m2235a(f1566a).mo9553b();
                                            throw th;
                                        }
                                    } catch (Throwable th4) {
                                        Throwable th5 = th4;
                                        r1 = cursor;
                                        th = th5;
                                        if (r1 != 0) {
                                            r1.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            try {
                                                sQLiteDatabase.endTransaction();
                                            } catch (Throwable th6) {
                                            }
                                        }
                                        UMDBManager.m2235a(f1566a).mo9553b();
                                        throw th;
                                    }
                                }
                                str2 = r1;
                            } else {
                                str2 = null;
                            }
                        } else {
                            str2 = null;
                            cursor = null;
                        }
                        if (jSONObject2 != null) {
                            try {
                                JSONArray jSONArray = new JSONArray();
                                if (!TextUtils.isEmpty(str2)) {
                                    jSONArray = new JSONArray(str2);
                                }
                                jSONArray.put(jSONObject2);
                                if (!TextUtils.isEmpty(mo9554a(jSONArray.toString()))) {
                                    sQLiteDatabase.execSQL("update  __sd set __d=\"" + r1 + "\" where " + "__ii" + "=\"" + str + "\"");
                                }
                            } catch (Exception e3) {
                            }
                        }
                        try {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("__c");
                            if (jSONObject3 != null) {
                                if (!TextUtils.isEmpty(mo9554a(jSONObject3.toString()))) {
                                    sQLiteDatabase.execSQL("update  __sd set __c=\"" + r1 + "\" where " + "__ii" + "=\"" + str + "\"");
                                }
                            }
                        } catch (Exception e4) {
                        }
                        try {
                            sQLiteDatabase.execSQL("update  __sd set __f=\"" + String.valueOf(jSONObject.getLong("__f")) + "\" where " + "__ii" + "=\"" + str + "\"");
                        } catch (Exception e5) {
                        }
                    } else {
                        cursor = null;
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th7) {
                        }
                    }
                    UMDBManager.m2235a(f1566a).mo9553b();
                } catch (SQLiteDatabaseCorruptException e6) {
                    cursor = null;
                    r1 = sQLiteDatabase;
                } catch (Throwable th8) {
                    th = th8;
                    if (r1 != 0) {
                    }
                    if (sQLiteDatabase != null) {
                    }
                    UMDBManager.m2235a(f1566a).mo9553b();
                    throw th;
                }
            } catch (SQLiteDatabaseCorruptException e7) {
                cursor = null;
            } catch (Throwable th9) {
                th = th9;
                sQLiteDatabase = null;
                if (r1 != 0) {
                }
                if (sQLiteDatabase != null) {
                }
                UMDBManager.m2235a(f1566a).mo9553b();
                throw th;
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r1v20, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c5  */
    /* renamed from: a */
    private void m2243a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase, String str2) {
        Cursor cursor;
        String str3;
        Cursor cursor2 = null;
        try {
            cursor = sQLiteDatabase.rawQuery("select " + str2 + " from " + "__sd" + " where " + "__ii" + "=\"" + str + "\"", null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    try {
                        cursor2 = mo9560b(cursor.getString(cursor.getColumnIndex(str2)));
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        cursor2 = cursor;
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
                str3 = cursor2;
            } else {
                str3 = null;
            }
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(str3)) {
                jSONArray = new JSONArray(str3);
            }
            jSONArray.put(jSONObject);
            if (!TextUtils.isEmpty(mo9554a(jSONArray.toString()))) {
                sQLiteDatabase.execSQL("update __sd set " + str2 + "=\"" + r1 + "\" where " + "__ii" + "=\"" + str + "\"");
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* renamed from: a */
    public JSONObject mo9555a() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        m2247c(jSONObject2);
        m2246b(jSONObject2);
        m2244a(jSONObject2);
        try {
            if (jSONObject2.length() > 0) {
                jSONObject.put("body", jSONObject2);
            }
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009f A[SYNTHETIC, Splitter:B:37:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d5 A[SYNTHETIC, Splitter:B:51:0x00d5] */
    /* renamed from: a */
    private void m2244a(JSONObject jSONObject) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase2;
        Throwable th;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        Cursor cursor2 = null;
        try {
            sQLiteDatabase2 = UMDBManager.m2235a(f1566a).mo9552a();
            try {
                sQLiteDatabase2.beginTransaction();
                cursor = sQLiteDatabase2.rawQuery("select *  from __et", null);
                if (cursor != null) {
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        JSONObject jSONObject3 = new JSONObject();
                        while (cursor.moveToNext()) {
                            int i = cursor.getInt(cursor.getColumnIndex("__t"));
                            String string = cursor.getString(cursor.getColumnIndex("__i"));
                            String string2 = cursor.getString(cursor.getColumnIndex("__s"));
                            if ("".equals(string)) {
                                string = SessionTracker.m1349a();
                            }
                            switch (i) {
                                case 2049:
                                    if (TextUtils.isEmpty(string2)) {
                                        break;
                                    } else {
                                        JSONObject jSONObject4 = new JSONObject(mo9560b(string2));
                                        if (jSONObject2.has(string)) {
                                            jSONArray2 = jSONObject2.optJSONArray(string);
                                        } else {
                                            jSONArray2 = new JSONArray();
                                        }
                                        jSONArray2.put(jSONObject4);
                                        jSONObject2.put(string, jSONArray2);
                                        break;
                                    }
                                case 2050:
                                    if (TextUtils.isEmpty(string2)) {
                                        break;
                                    } else {
                                        JSONObject jSONObject5 = new JSONObject(mo9560b(string2));
                                        if (jSONObject3.has(string)) {
                                            jSONArray = jSONObject3.optJSONArray(string);
                                        } else {
                                            jSONArray = new JSONArray();
                                        }
                                        jSONArray.put(jSONObject5);
                                        jSONObject3.put(string, jSONArray);
                                        break;
                                    }
                            }
                        }
                        if (jSONObject2.length() > 0) {
                            JSONArray jSONArray3 = new JSONArray();
                            Iterator<String> keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                JSONObject jSONObject6 = new JSONObject();
                                String next = keys.next();
                                jSONObject6.put(next, new JSONArray(jSONObject2.optString(next)));
                                if (jSONObject6.length() > 0) {
                                    jSONArray3.put(jSONObject6);
                                }
                            }
                            if (jSONArray3.length() > 0) {
                                jSONObject.put("ekv", jSONArray3);
                            }
                        }
                        if (jSONObject3.length() > 0) {
                            JSONArray jSONArray4 = new JSONArray();
                            Iterator<String> keys2 = jSONObject3.keys();
                            while (keys2.hasNext()) {
                                JSONObject jSONObject7 = new JSONObject();
                                String next2 = keys2.next();
                                jSONObject7.put(next2, new JSONArray(jSONObject3.optString(next2)));
                                if (jSONObject7.length() > 0) {
                                    jSONArray4.put(jSONObject7);
                                }
                            }
                            if (jSONArray4.length() > 0) {
                                jSONObject.put("gkv", jSONArray4);
                            }
                        }
                    } catch (SQLiteDatabaseCorruptException e) {
                        cursor2 = cursor;
                        sQLiteDatabase = sQLiteDatabase2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase2 != null) {
                        }
                        UMDBManager.m2235a(f1566a).mo9553b();
                        throw th;
                    }
                }
                sQLiteDatabase2.setTransactionSuccessful();
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase2 != null) {
                    try {
                        sQLiteDatabase2.endTransaction();
                    } catch (Throwable th3) {
                    }
                }
                UMDBManager.m2235a(f1566a).mo9553b();
                return;
            } catch (SQLiteDatabaseCorruptException e2) {
                sQLiteDatabase = sQLiteDatabase2;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                cursor = null;
                th = th5;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase2 != null) {
                    try {
                        sQLiteDatabase2.endTransaction();
                    } catch (Throwable th6) {
                    }
                }
                UMDBManager.m2235a(f1566a).mo9553b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
            sQLiteDatabase = null;
        } catch (Throwable th7) {
            sQLiteDatabase2 = null;
            th = th7;
            cursor = null;
            if (cursor != null) {
            }
            if (sQLiteDatabase2 != null) {
            }
            UMDBManager.m2235a(f1566a).mo9553b();
            throw th;
        }
        UMDBManager.m2235a(f1566a).mo9553b();
        try {
            UMDBUtils.m2241b(f1566a);
            if (cursor2 != null) {
                cursor2.close();
            }
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th8) {
                }
            }
            UMDBManager.m2235a(f1566a).mo9553b();
        } catch (Throwable th9) {
            Throwable th10 = th9;
            sQLiteDatabase2 = sQLiteDatabase;
            cursor = cursor2;
            th = th10;
            if (cursor != null) {
            }
            if (sQLiteDatabase2 != null) {
            }
            UMDBManager.m2235a(f1566a).mo9553b();
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0098, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b2, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b3, code lost:
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0084 A[SYNTHETIC, Splitter:B:38:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x009d A[SYNTHETIC, Splitter:B:46:0x009d] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: b */
    private void m2246b(JSONObject jSONObject) {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2;
        ? r1;
        Throwable th;
        SQLiteDatabase sQLiteDatabase3 = null;
        try {
            sQLiteDatabase = UMDBManager.m2235a(f1566a).mo9552a();
            try {
                sQLiteDatabase.beginTransaction();
                Cursor rawQuery = sQLiteDatabase.rawQuery("select *  from __er", null);
                if (rawQuery != null) {
                    JSONArray jSONArray = new JSONArray();
                    while (rawQuery.moveToNext()) {
                        String string = rawQuery.getString(rawQuery.getColumnIndex("__a"));
                        if (!TextUtils.isEmpty(string)) {
                            jSONArray.put(new JSONObject(mo9560b(string)));
                        }
                    }
                    if (jSONArray.length() > 0) {
                        jSONObject.put("error", jSONArray);
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th2) {
                    }
                }
                UMDBManager.m2235a(f1566a).mo9553b();
                return;
            } catch (SQLiteDatabaseCorruptException e) {
            } catch (Throwable th3) {
                if (sQLiteDatabase3 != null) {
                    sQLiteDatabase3.close();
                }
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th4) {
                    }
                }
                UMDBManager.m2235a(f1566a).mo9553b();
                return;
            }
        } catch (SQLiteDatabaseCorruptException e2) {
            sQLiteDatabase = sQLiteDatabase3;
        } catch (Throwable th5) {
            sQLiteDatabase2 = sQLiteDatabase3;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase3;
            th = th5;
            r1 = sQLiteDatabase4;
            if (r1 != 0) {
            }
            if (sQLiteDatabase2 != null) {
            }
            UMDBManager.m2235a(f1566a).mo9553b();
            throw th;
        }
        try {
            UMDBUtils.m2241b(f1566a);
            if (sQLiteDatabase3 != null) {
                sQLiteDatabase3.close();
            }
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th6) {
                }
            }
            UMDBManager.m2235a(f1566a).mo9553b();
        } catch (Throwable th7) {
            Throwable th8 = th7;
            sQLiteDatabase2 = sQLiteDatabase;
            r1 = sQLiteDatabase3;
            th = th8;
            if (r1 != 0) {
            }
            if (sQLiteDatabase2 != null) {
            }
            UMDBManager.m2235a(f1566a).mo9553b();
            throw th;
        }
        UMDBManager.m2235a(f1566a).mo9553b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        com.p028a.p029a.p030a.UMDBUtils.m2241b(f1566a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x011e, code lost:
        if (r2 != null) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0120, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0123, code lost:
        if (r3 != null) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0157, code lost:
        if (r2 != null) goto L_0x0159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0159, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x015c, code lost:
        if (r3 != null) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0174, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r4.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x018e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x018f, code lost:
        r16 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0196, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0197, code lost:
        r16 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r16;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0118 A[ExcHandler: SQLiteDatabaseCorruptException (e android.database.sqlite.SQLiteDatabaseCorruptException), Splitter:B:1:0x0002] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0156 A[ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:1:0x0002] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0179 A[SYNTHETIC, Splitter:B:63:0x0179] */
    /* renamed from: c */
    private void m2247c(JSONObject jSONObject) {
        SQLiteDatabase sQLiteDatabase = null;
        Cursor cursor = null;
        try {
            SQLiteDatabase a = UMDBManager.m2235a(f1566a).mo9552a();
            a.beginTransaction();
            Cursor rawQuery = a.rawQuery("select *  from __sd", null);
            if (rawQuery != null) {
                JSONArray jSONArray = new JSONArray();
                this.f1568c.clear();
                while (rawQuery.moveToNext()) {
                    JSONObject jSONObject2 = new JSONObject();
                    String string = rawQuery.getString(rawQuery.getColumnIndex("__f"));
                    String string2 = rawQuery.getString(rawQuery.getColumnIndex("__e"));
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && Long.parseLong(string) - Long.parseLong(string2) > 0) {
                        String string3 = rawQuery.getString(rawQuery.getColumnIndex("__a"));
                        String string4 = rawQuery.getString(rawQuery.getColumnIndex("__b"));
                        String string5 = rawQuery.getString(rawQuery.getColumnIndex("__c"));
                        String string6 = rawQuery.getString(rawQuery.getColumnIndex("__d"));
                        String string7 = rawQuery.getString(rawQuery.getColumnIndex("__ii"));
                        this.f1568c.add(string7);
                        jSONObject2.put("id", string7);
                        jSONObject2.put("start_time", string2);
                        jSONObject2.put("end_time", string);
                        jSONObject2.put("duration", Long.parseLong(string) - Long.parseLong(string2));
                        if (!TextUtils.isEmpty(string3)) {
                            jSONObject2.put("pages", new JSONArray(mo9560b(string3)));
                        }
                        if (!TextUtils.isEmpty(string4)) {
                            jSONObject2.put("autopages", new JSONArray(mo9560b(string4)));
                        }
                        if (!TextUtils.isEmpty(string5)) {
                            jSONObject2.put("traffic", new JSONObject(mo9560b(string5)));
                        }
                        if (!TextUtils.isEmpty(string6)) {
                            jSONObject2.put("locations", new JSONArray(mo9560b(string6)));
                        }
                        if (jSONObject2.length() > 0) {
                            jSONArray.put(jSONObject2);
                        }
                    }
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("sessions", jSONArray);
                }
            }
            a.setTransactionSuccessful();
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (a != null) {
                try {
                    a.endTransaction();
                } catch (Throwable th) {
                }
            }
            UMDBManager.m2235a(f1566a).mo9553b();
            return;
            UMDBManager.m2235a(f1566a).mo9553b();
            return;
            UMDBManager.m2235a(f1566a).mo9553b();
        } catch (SQLiteDatabaseCorruptException e) {
        } catch (Throwable th2) {
            Throwable th3 = th2;
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            Cursor cursor2 = cursor;
            Throwable th4 = th3;
            if (cursor2 != null) {
            }
            if (sQLiteDatabase2 != null) {
            }
            UMDBManager.m2235a(f1566a).mo9553b();
            throw th4;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x008d A[SYNTHETIC, Splitter:B:32:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a0 A[SYNTHETIC, Splitter:B:38:0x00a0] */
    /* renamed from: a */
    public void mo9557a(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            sQLiteDatabase = UMDBManager.m2235a(f1566a).mo9552a();
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("delete from __er");
                sQLiteDatabase.execSQL("delete from __et");
                if (!z2) {
                    if (this.f1568c.size() > 0) {
                        for (int i = 0; i < this.f1568c.size(); i++) {
                            sQLiteDatabase.execSQL("delete from __sd where __ii=\"" + this.f1568c.get(i) + "\"");
                        }
                    }
                    this.f1568c.clear();
                } else if (z) {
                    sQLiteDatabase.execSQL("delete from __sd");
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th2) {
                    }
                }
                UMDBManager.m2235a(f1566a).mo9553b();
            } catch (SQLiteDatabaseCorruptException e) {
                sQLiteDatabase2 = sQLiteDatabase;
                try {
                    UMDBUtils.m2241b(f1566a);
                    if (sQLiteDatabase2 != null) {
                        try {
                            sQLiteDatabase2.endTransaction();
                        } catch (Throwable th3) {
                        }
                    }
                    UMDBManager.m2235a(f1566a).mo9553b();
                } catch (Throwable th4) {
                    Throwable th5 = th4;
                    sQLiteDatabase = sQLiteDatabase2;
                    th = th5;
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th6) {
                        }
                    }
                    UMDBManager.m2235a(f1566a).mo9553b();
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                if (sQLiteDatabase != null) {
                }
                UMDBManager.m2235a(f1566a).mo9553b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e2) {
        } catch (Throwable th8) {
            Throwable th9 = th8;
            sQLiteDatabase = null;
            th = th9;
            if (sQLiteDatabase != null) {
            }
            UMDBManager.m2235a(f1566a).mo9553b();
            throw th;
        }
    }

    /* renamed from: b */
    private void m2245b() {
        try {
            if (TextUtils.isEmpty(f1567b)) {
                SharedPreferences a = PreferenceWrapper.m1333a(f1566a);
                String string = a.getString("ek__id", null);
                if (TextUtils.isEmpty(string)) {
                    string = DeviceConfig.m1819w(f1566a);
                    if (!TextUtils.isEmpty(string)) {
                        a.edit().putString("ek__id", string).commit();
                    }
                }
                if (!TextUtils.isEmpty(string)) {
                    String substring = string.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < substring.length(); i++) {
                        char charAt = substring.charAt(i);
                        if (!Character.isDigit(charAt)) {
                            sb.append(charAt);
                        } else if (Integer.parseInt(Character.toString(charAt)) == 0) {
                            sb.append(0);
                        } else {
                            sb.append(10 - Integer.parseInt(Character.toString(charAt)));
                        }
                    }
                    f1567b = sb.toString();
                }
                if (!TextUtils.isEmpty(f1567b)) {
                    f1567b += new StringBuilder(f1567b).reverse().toString();
                    String string2 = a.getString("ek_key", null);
                    if (TextUtils.isEmpty(string2)) {
                        a.edit().putString("ek_key", mo9554a("umeng+")).commit();
                    } else if (!"umeng+".equals(mo9560b(string2))) {
                        mo9557a(true, false);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public String mo9554a(String str) {
        try {
            if (TextUtils.isEmpty(f1567b)) {
                return str;
            }
            return Base64.encodeToString(DataHelper.m1776a(str.getBytes(), f1567b.getBytes()), 0);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public String mo9560b(String str) {
        try {
            if (TextUtils.isEmpty(f1567b)) {
                return str;
            }
            return new String(DataHelper.m1779b(Base64.decode(str.getBytes(), 0), f1567b.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }
}
