package com.p028a.p029a.p030a;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.a.a.a.ay */
class UMCCDBHelper extends SQLiteOpenHelper {
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static Context f1383b;

    /* renamed from: a */
    private String f1384a;

    /* renamed from: com.a.a.a.ay$a */
    /* compiled from: UMCCDBHelper */
    private static class C0594a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final UMCCDBHelper f1385a = new UMCCDBHelper(UMCCDBHelper.f1383b, UMCCDBUtils.m2038a(UMCCDBHelper.f1383b), "cc.db", null, 1);
    }

    /* renamed from: a */
    public static synchronized UMCCDBHelper m1852a(Context context) {
        UMCCDBHelper a;
        synchronized (UMCCDBHelper.class) {
            f1383b = context;
            a = C0594a.f1385a;
        }
        return a;
    }

    private UMCCDBHelper(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(new UMCCPathDatabaseContext(context, str), str2, cursorFactory, i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private UMCCDBHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, (str == null || str.equals("")) ? "cc.db" : "cc.db", cursorFactory, i);
        m1854b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: b */
    private void m1854b() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (!mo9372a("aggregated", writableDatabase) || !mo9372a("aggregated_cache", writableDatabase)) {
            m1856c(writableDatabase);
        }
        if (!mo9372a("system", writableDatabase)) {
            m1855b(writableDatabase);
        }
        if (!mo9372a("limitedck", writableDatabase)) {
            m1853a(writableDatabase);
        }
    }

    /* renamed from: a */
    public boolean mo9372a(String str, SQLiteDatabase sQLiteDatabase) {
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

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            m1856c(sQLiteDatabase);
            m1855b(sQLiteDatabase);
            m1853a(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* renamed from: a */
    private boolean m1853a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f1384a = "create table if not exists limitedck(Id INTEGER primary key autoincrement, ck TEXT unique)";
            sQLiteDatabase.execSQL(this.f1384a);
            return true;
        } catch (SQLException e) {
            MLog.m1844c("create reference table error!");
            return false;
        }
    }

    /* renamed from: b */
    private boolean m1855b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f1384a = "create table if not exists system(Id INTEGER primary key autoincrement, key TEXT, timeStamp INTEGER, count INTEGER)";
            sQLiteDatabase.execSQL(this.f1384a);
            return true;
        } catch (SQLException e) {
            MLog.m1844c("create system table error!");
            return false;
        }
    }

    /* renamed from: c */
    private boolean m1856c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f1384a = "create table if not exists aggregated_cache(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.f1384a);
            this.f1384a = "create table if not exists aggregated(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.f1384a);
            return true;
        } catch (SQLException e) {
            MLog.m1844c("create aggregated table error!");
            return false;
        }
    }
}
