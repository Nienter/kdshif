package com.p028a.p029a.p030a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.a.a.a.z */
public class DatabaseManager {

    /* renamed from: c */
    private static DatabaseManager f1680c;

    /* renamed from: d */
    private static SQLiteOpenHelper f1681d;

    /* renamed from: a */
    private AtomicInteger f1682a = new AtomicInteger();

    /* renamed from: b */
    private AtomicInteger f1683b = new AtomicInteger();

    /* renamed from: e */
    private SQLiteDatabase f1684e;

    /* renamed from: b */
    private static synchronized void m2406b(Context context) {
        synchronized (DatabaseManager.class) {
            if (f1680c == null) {
                f1680c = new DatabaseManager();
                f1681d = UMCCDBHelper.m1852a(context);
            }
        }
    }

    /* renamed from: a */
    public static synchronized DatabaseManager m2405a(Context context) {
        DatabaseManager zVar;
        synchronized (DatabaseManager.class) {
            if (f1680c == null) {
                m2406b(context);
            }
            zVar = f1680c;
        }
        return zVar;
    }

    /* renamed from: a */
    public synchronized SQLiteDatabase mo9635a() {
        if (this.f1682a.incrementAndGet() == 1) {
            this.f1684e = f1681d.getReadableDatabase();
        }
        return this.f1684e;
    }

    /* renamed from: b */
    public synchronized SQLiteDatabase mo9636b() {
        if (this.f1682a.incrementAndGet() == 1) {
            this.f1684e = f1681d.getWritableDatabase();
        }
        return this.f1684e;
    }

    /* renamed from: c */
    public synchronized void mo9637c() {
        if (this.f1682a.decrementAndGet() == 0) {
            this.f1684e.close();
        }
        if (this.f1683b.decrementAndGet() == 0) {
            this.f1684e.close();
        }
    }
}
