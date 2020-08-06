package com.p028a.p029a.p030a;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.a.a.a.cw */
/* compiled from: UMDBCreater */
class C0632cw extends SQLiteOpenHelper {
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static Context f1558b = null;

    /* renamed from: a */
    private String f1559a;

    /* renamed from: com.a.a.a.cw$a */
    /* compiled from: UMDBCreater */
    private static class C0634a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final C0632cw f1560a = new C0632cw(C0632cw.f1558b, UMDBUtils.m2239a(C0632cw.f1558b), "ua.db", null, 1);
    }

    /* renamed from: a */
    public static synchronized C0632cw m2228a(Context context) {
        C0632cw a;
        synchronized (C0632cw.class) {
            f1558b = context;
            a = C0634a.f1560a;
        }
        return a;
    }

    private C0632cw(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(new UMDBCreater(context, str), str2, cursorFactory, i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private C0632cw(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, (str == null || str.equals("")) ? "ua.db" : "ua.db", cursorFactory, i);
        this.f1559a = null;
        mo9549a();
    }

    /* renamed from: a */
    public void mo9549a() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!UMDBUtils.m2240a("__sd", writableDatabase)) {
                m2232c(writableDatabase);
            }
            if (!UMDBUtils.m2240a("__et", writableDatabase)) {
                m2231b(writableDatabase);
            }
            if (!UMDBUtils.m2240a("__er", writableDatabase)) {
                m2229a(writableDatabase);
            }
        } catch (Exception e) {
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            m2232c(sQLiteDatabase);
            m2231b(sQLiteDatabase);
            m2229a(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th) {
                }
            }
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m2241b(f1558b);
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th2) {
        }
    }

    /* renamed from: a */
    private void m2229a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f1559a = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER)";
            sQLiteDatabase.execSQL(this.f1559a);
        } catch (SQLException e) {
        }
    }

    /* renamed from: b */
    private void m2231b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f1559a = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER)";
            sQLiteDatabase.execSQL(this.f1559a);
        } catch (SQLException e) {
        }
    }

    /* renamed from: c */
    private void m2232c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f1559a = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT)";
            sQLiteDatabase.execSQL(this.f1559a);
        } catch (SQLException e) {
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
