package com.p028a.p029a.p030a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* renamed from: com.a.a.a.cy */
class UMDBUtils {
    /* renamed from: a */
    public static boolean m2240a(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        boolean z = false;
        if (str != null) {
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
                if (rawQuery.moveToNext() && rawQuery.getInt(0) > 0) {
                    z = true;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (Exception e) {
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
        return z;
    }

    /* renamed from: a */
    public static String m2239a(Context context) {
        return "/data/data/" + context.getPackageName() + "/databases/";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: b */
    public static void m2241b(Context context) {
        if (context != null) {
            File file = new File("/data/data/" + context.getPackageName() + "/databases/" + "ua.db");
            if (file != null && file.exists()) {
                file.delete();
            }
            C0632cw.m2228a(context).mo9549a();
        }
    }
}
