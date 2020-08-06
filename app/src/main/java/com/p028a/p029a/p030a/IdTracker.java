package com.p028a.p029a.p030a;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.a.a.a.e */
public class IdTracker {

    /* renamed from: a */
    public static IdTracker f1583a;

    /* renamed from: b */
    private final String f1584b = "umeng_it.cache";

    /* renamed from: c */
    private File f1585c;

    /* renamed from: d */
    private IdTracking f1586d = null;

    /* renamed from: e */
    private long f1587e;

    /* renamed from: f */
    private long f1588f;

    /* renamed from: g */
    private Set<AbstractIdTracker> f1589g = new HashSet();

    /* renamed from: h */
    private C0638a f1590h = null;

    /* renamed from: com.a.a.a.e$a */
    /* compiled from: IdTracker */
    public static class C0638a {

        /* renamed from: a */
        private Context f1591a;

        /* renamed from: b */
        private Set<String> f1592b = new HashSet();

        public C0638a(Context context) {
            this.f1591a = context;
        }

        /* renamed from: a */
        public boolean mo9576a(String str) {
            return !this.f1592b.contains(str);
        }

        /* renamed from: b */
        public void mo9578b(String str) {
            this.f1592b.add(str);
        }

        /* renamed from: a */
        public void mo9575a() {
            if (!this.f1592b.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String append : this.f1592b) {
                    sb.append(append);
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
                PreferenceWrapper.m1333a(this.f1591a).edit().putString("invld_id", sb.toString()).commit();
            }
        }

        /* renamed from: b */
        public void mo9577b() {
            String string = PreferenceWrapper.m1333a(this.f1591a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(",");
                if (split != null) {
                    for (String str : split) {
                        if (!TextUtils.isEmpty(str)) {
                            this.f1592b.add(str);
                        }
                    }
                }
            }
        }
    }

    IdTracker(Context context) {
        this.f1585c = new File(context.getFilesDir(), "umeng_it.cache");
        this.f1588f = 86400000;
        this.f1590h = new C0638a(context);
        this.f1590h.mo9577b();
    }

    /* renamed from: a */
    public static synchronized IdTracker m2268a(Context context) {
        IdTracker eVar;
        synchronized (IdTracker.class) {
            if (f1583a == null) {
                f1583a = new IdTracker(context);
                f1583a.mo9570a((AbstractIdTracker) new ImeiTracker(context));
                f1583a.mo9570a((AbstractIdTracker) new AndroidIdTracker(context));
                f1583a.mo9570a((AbstractIdTracker) new UTDIdTracker(context));
                f1583a.mo9570a((AbstractIdTracker) new IDMD5Tracker(context));
                f1583a.mo9570a((AbstractIdTracker) new IDFATracker(context));
                f1583a.mo9570a((AbstractIdTracker) new MacTracker(context));
                f1583a.mo9570a((AbstractIdTracker) new SerialTracker());
                f1583a.mo9570a((AbstractIdTracker) new UUIDTracker(context));
                UOPTracker lVar = new UOPTracker(context);
                if (!TextUtils.isEmpty(lVar.mo9458a())) {
                    f1583a.mo9570a((AbstractIdTracker) lVar);
                }
                OldUMIDTracker jVar = new OldUMIDTracker(context);
                if (jVar.mo9601b()) {
                    f1583a.mo9570a((AbstractIdTracker) jVar);
                    f1583a.mo9570a((AbstractIdTracker) new NewUMIDTracker(context));
                    jVar.mo9603d();
                }
                f1583a.mo9573d();
            }
            eVar = f1583a;
        }
        return eVar;
    }

    /* renamed from: a */
    public boolean mo9570a(AbstractIdTracker daVar) {
        if (this.f1590h.mo9576a(daVar.mo9565f())) {
            return this.f1589g.add(daVar);
        }
        return false;
    }

    /* renamed from: a */
    public void mo9569a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f1587e >= this.f1588f) {
            boolean z = false;
            for (AbstractIdTracker next : this.f1589g) {
                if (next.mo9566g()) {
                    if (next.mo9564e()) {
                        z = true;
                        if (!next.mo9566g()) {
                            this.f1590h.mo9578b(next.mo9565f());
                        }
                    }
                    z = z;
                }
            }
            if (z) {
                m2270f();
                this.f1590h.mo9575a();
                mo9574e();
            }
            this.f1587e = currentTimeMillis;
        }
    }

    /* renamed from: b */
    public IdTracking mo9571b() {
        return this.f1586d;
    }

    /* renamed from: f */
    private void m2270f() {
        IdTracking alVar = new IdTracking();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (AbstractIdTracker next : this.f1589g) {
            if (next.mo9566g()) {
                if (next.mo9567h() != null) {
                    hashMap.put(next.mo9565f(), next.mo9567h());
                }
                if (next.mo9568i() != null && !next.mo9568i().isEmpty()) {
                    arrayList.addAll(next.mo9568i());
                }
            }
        }
        alVar.mo9188a((List<IdJournal>) arrayList);
        alVar.mo9189a((Map<String, IdSnapshot>) hashMap);
        synchronized (this) {
            this.f1586d = alVar;
        }
    }

    /* renamed from: c */
    public void mo9572c() {
        boolean z = false;
        for (AbstractIdTracker next : this.f1589g) {
            if (next.mo9566g()) {
                if (next.mo9568i() != null && !next.mo9568i().isEmpty()) {
                    next.mo9563a((List<IdJournal>) null);
                    z = true;
                }
                z = z;
            }
        }
        if (z) {
            this.f1586d.mo9193b(false);
            mo9574e();
        }
    }

    /* renamed from: d */
    public void mo9573d() {
        IdTracking g = m2271g();
        if (g != null) {
            ArrayList<AbstractIdTracker> arrayList = new ArrayList<>(this.f1589g.size());
            synchronized (this) {
                this.f1586d = g;
                for (AbstractIdTracker next : this.f1589g) {
                    next.mo9562a(this.f1586d);
                    if (!next.mo9566g()) {
                        arrayList.add(next);
                    }
                }
                for (AbstractIdTracker remove : arrayList) {
                    this.f1589g.remove(remove);
                }
            }
            m2270f();
        }
    }

    /* renamed from: e */
    public void mo9574e() {
        if (this.f1586d != null) {
            m2269a(this.f1586d);
        }
    }

    /* renamed from: g */
    private IdTracking m2271g() {
        FileInputStream fileInputStream;
        Throwable th;
        if (!this.f1585c.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(this.f1585c);
            try {
                byte[] b = HelperUtils.m1831b((InputStream) fileInputStream);
                IdTracking alVar = new IdTracking();
                new TDeserializer().mo9407a(alVar, b);
                HelperUtils.m1832c(fileInputStream);
                return alVar;
            } catch (Exception e) {
                e = e;
                try {
                    e.printStackTrace();
                    HelperUtils.m1832c(fileInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    HelperUtils.m1832c(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            e.printStackTrace();
            HelperUtils.m1832c(fileInputStream);
            return null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            HelperUtils.m1832c(fileInputStream);
            throw th;
        }
    }

    /* renamed from: a */
    private void m2269a(IdTracking alVar) {
        byte[] a;
        if (alVar != null) {
            try {
                synchronized (this) {
                    a = new TSerializer().mo9408a(alVar);
                }
                if (a != null) {
                    HelperUtils.m1828a(this.f1585c, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
