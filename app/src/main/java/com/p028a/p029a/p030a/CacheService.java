package com.p028a.p029a.p030a;

import android.content.Context;

/* renamed from: com.a.a.a.r */
public final class CacheService implements ICacheService {

    /* renamed from: c */
    private static CacheService f1658c;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public ICacheService f1659a = new CacheImpl(this.f1660b);

    /* renamed from: b */
    private Context f1660b;

    private CacheService(Context context) {
        this.f1660b = context;
    }

    /* renamed from: a */
    public synchronized CacheImpl mo9626a(Context context) {
        return (CacheImpl) this.f1659a;
    }

    /* renamed from: b */
    public static synchronized CacheService m2369b(Context context) {
        CacheService rVar;
        synchronized (CacheService.class) {
            if (f1658c == null && context != null) {
                f1658c = new CacheService(context);
            }
            rVar = f1658c;
        }
        return rVar;
    }

    /* renamed from: a */
    public void mo9620a(final Object obj) {
        QueuedWork.m1849b(new SafeRunnable() {
            /* renamed from: a */
            public void mo9386a() {
                CacheService.this.f1659a.mo9620a(obj);
            }
        });
    }

    /* renamed from: a */
    public void mo9618a() {
        QueuedWork.m1849b(new SafeRunnable() {
            /* renamed from: a */
            public void mo9386a() {
                CacheService.this.f1659a.mo9618a();
            }
        });
    }

    /* renamed from: b */
    public void mo9622b() {
        QueuedWork.m1850c(new SafeRunnable() {
            /* renamed from: a */
            public void mo9386a() {
                CacheService.this.f1659a.mo9622b();
            }
        });
    }
}
