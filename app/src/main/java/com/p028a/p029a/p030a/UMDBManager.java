package com.p028a.p029a.p030a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.a.a.a.cx */
class UMDBManager {

    /* renamed from: c */
    private static UMDBManager f1561c;

    /* renamed from: d */
    private static SQLiteOpenHelper f1562d;

    /* renamed from: a */
    private AtomicInteger f1563a = new AtomicInteger();

    /* renamed from: b */
    private AtomicInteger f1564b = new AtomicInteger();

    /* renamed from: e */
    private SQLiteDatabase f1565e;

    UMDBManager() {
    }

    /* renamed from: b */
    private static synchronized void m2236b(Context context) {
        synchronized (UMDBManager.class) {
            if (f1561c == null) {
                f1561c = new UMDBManager();
                f1562d = C0632cw.m2228a(context);
            }
        }
    }

    /* renamed from: a */
    public static synchronized UMDBManager m2235a(Context context) {
        UMDBManager cxVar;
        synchronized (UMDBManager.class) {
            if (f1561c == null) {
                m2236b(context);
            }
            cxVar = f1561c;
        }
        return cxVar;
    }

    /* renamed from: a */
    public synchronized SQLiteDatabase mo9552a() {
        if (this.f1563a.incrementAndGet() == 1) {
            this.f1565e = f1562d.getWritableDatabase();
        }
        return this.f1565e;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: b */
    public synchronized void mo9553b() {
        if (this.f1563a.decrementAndGet() == 0) {
            this.f1565e.close();
        }
        if (this.f1564b.decrementAndGet() == 0) {
            this.f1565e.close();
        }
    }
}
