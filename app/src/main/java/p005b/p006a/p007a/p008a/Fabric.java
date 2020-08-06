package p005b.p006a.p007a.p008a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import p005b.p006a.p007a.p008a.ActivityLifecycleManager;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p012c.DependsOn;
import p005b.p006a.p007a.p008a.p009a.p012c.PriorityThreadPoolExecutor;
import p005b.p006a.p007a.p008a.p009a.p012c.UnmetDependencyException;

/* renamed from: b.a.a.a.c */
public class Fabric {

    /* renamed from: a */
    static volatile Fabric f361a;

    /* renamed from: b */
    static final Logger f362b = new DefaultLogger();

    /* renamed from: c */
    final Logger f363c;

    /* renamed from: d */
    final boolean f364d;

    /* renamed from: e */
    private final Context f365e;

    /* renamed from: f */
    private final Map<Class<? extends Kit>, Kit> f366f;

    /* renamed from: g */
    private final ExecutorService f367g;

    /* renamed from: h */
    private final Handler f368h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public final InitializationCallback<Fabric> f369i;

    /* renamed from: j */
    private final InitializationCallback<?> f370j;

    /* renamed from: k */
    private final IdManager f371k;

    /* renamed from: l */
    private ActivityLifecycleManager f372l;

    /* renamed from: m */
    private WeakReference<Activity> f373m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public AtomicBoolean f374n = new AtomicBoolean(false);

    /* renamed from: b.a.a.a.c$a */
    /* compiled from: Fabric */
    public static class C0464a {

        /* renamed from: a */
        private final Context f379a;

        /* renamed from: b */
        private Kit[] f380b;

        /* renamed from: c */
        private PriorityThreadPoolExecutor f381c;

        /* renamed from: d */
        private Handler f382d;

        /* renamed from: e */
        private Logger f383e;

        /* renamed from: f */
        private boolean f384f;

        /* renamed from: g */
        private String f385g;

        /* renamed from: h */
        private String f386h;

        /* renamed from: i */
        private InitializationCallback<Fabric> f387i;

        public C0464a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.f379a = context;
        }

        /* renamed from: a */
        public C0464a mo8530a(Kit... iVarArr) {
            if (this.f380b != null) {
                throw new IllegalStateException("Kits already set.");
            }
            this.f380b = iVarArr;
            return this;
        }

        /* renamed from: a */
        public Fabric mo8531a() {
            Map a;
            if (this.f381c == null) {
                this.f381c = PriorityThreadPoolExecutor.m263a();
            }
            if (this.f382d == null) {
                this.f382d = new Handler(Looper.getMainLooper());
            }
            if (this.f383e == null) {
                if (this.f384f) {
                    this.f383e = new DefaultLogger(3);
                } else {
                    this.f383e = new DefaultLogger();
                }
            }
            if (this.f386h == null) {
                this.f386h = this.f379a.getPackageName();
            }
            if (this.f387i == null) {
                this.f387i = InitializationCallback.f391d;
            }
            if (this.f380b == null) {
                a = new HashMap();
            } else {
                a = Fabric.m452b((Collection<? extends Kit>) Arrays.asList(this.f380b));
            }
            Context applicationContext = this.f379a.getApplicationContext();
            return new Fabric(applicationContext, a, this.f381c, this.f382d, this.f383e, this.f384f, this.f387i, new IdManager(applicationContext, this.f386h, this.f385g, a.values()), Fabric.m455d(this.f379a));
        }
    }

    /* renamed from: a */
    static Fabric m445a() {
        if (f361a != null) {
            return f361a;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    Fabric(Context context, Map<Class<? extends Kit>, Kit> map, PriorityThreadPoolExecutor kVar, Handler handler, Logger lVar, boolean z, InitializationCallback fVar, IdManager pVar, Activity activity) {
        this.f365e = context;
        this.f366f = map;
        this.f367g = kVar;
        this.f368h = handler;
        this.f363c = lVar;
        this.f364d = z;
        this.f369i = fVar;
        this.f370j = mo8518a(map.size());
        this.f371k = pVar;
        mo8517a(activity);
    }

    /* renamed from: a */
    public static Fabric m446a(Context context, Kit... iVarArr) {
        if (f361a == null) {
            synchronized (Fabric.class) {
                if (f361a == null) {
                    m454c(new C0464a(context).mo8530a(iVarArr).mo8531a());
                }
            }
        }
        return f361a;
    }

    /* renamed from: c */
    private static void m454c(Fabric cVar) {
        f361a = cVar;
        cVar.m458j();
    }

    /* renamed from: a */
    public Fabric mo8517a(Activity activity) {
        this.f373m = new WeakReference<>(activity);
        return this;
    }

    /* renamed from: b */
    public Activity mo8521b() {
        if (this.f373m != null) {
            return (Activity) this.f373m.get();
        }
        return null;
    }

    /* renamed from: j */
    private void m458j() {
        this.f372l = new ActivityLifecycleManager(this.f365e);
        this.f372l.mo8234a(new ActivityLifecycleManager.C0418b() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Fabric.this.mo8517a(activity);
            }

            public void onActivityStarted(Activity activity) {
                Fabric.this.mo8517a(activity);
            }

            public void onActivityResumed(Activity activity) {
                Fabric.this.mo8517a(activity);
            }
        });
        mo8519a(this.f365e);
    }

    /* renamed from: c */
    public String mo8523c() {
        return "1.4.1.19";
    }

    /* renamed from: d */
    public String mo8524d() {
        return "io.fabric.sdk.android:fabric";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8519a(Context context) {
        StringBuilder sb;
        Future<Map<String, KitInfo>> b = mo8522b(context);
        Collection<Kit> g = mo8527g();
        Onboarding mVar = new Onboarding(b, g);
        ArrayList<Kit> arrayList = new ArrayList<>(g);
        Collections.sort(arrayList);
        mVar.injectParameters(context, this, InitializationCallback.f391d, this.f371k);
        for (Kit injectParameters : arrayList) {
            injectParameters.injectParameters(context, this, this.f370j, this.f371k);
        }
        mVar.initialize();
        if (m456h().mo8508a("Fabric", 3)) {
            sb = new StringBuilder("Initializing ").append(mo8524d()).append(" [Version: ").append(mo8523c()).append("], with the following kits:\n");
        } else {
            sb = null;
        }
        for (Kit iVar : arrayList) {
            iVar.initializationTask.addDependency(mVar.initializationTask);
            mo8520a(this.f366f, iVar);
            iVar.initialize();
            if (sb != null) {
                sb.append(iVar.getIdentifier()).append(" [Version: ").append(iVar.getVersion()).append("]\n");
            }
        }
        if (sb != null) {
            m456h().mo8506a("Fabric", sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8520a(Map<Class<? extends Kit>, Kit> map, Kit iVar) {
        DependsOn dVar = iVar.dependsOnAnnotation;
        if (dVar != null) {
            for (Class cls : dVar.mo8365a()) {
                if (cls.isInterface()) {
                    for (Kit next : map.values()) {
                        if (cls.isAssignableFrom(next.getClass())) {
                            iVar.initializationTask.addDependency(next.initializationTask);
                        }
                    }
                } else if (map.get(cls) == null) {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                } else {
                    iVar.initializationTask.addDependency(map.get(cls).initializationTask);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static Activity m455d(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    /* renamed from: e */
    public ActivityLifecycleManager mo8525e() {
        return this.f372l;
    }

    /* renamed from: f */
    public ExecutorService mo8526f() {
        return this.f367g;
    }

    /* renamed from: g */
    public Collection<Kit> mo8527g() {
        return this.f366f.values();
    }

    /* renamed from: a */
    public static <T extends Kit> T m447a(Class<T> cls) {
        return (Kit) m445a().f366f.get(cls);
    }

    /* renamed from: h */
    public static Logger m456h() {
        if (f361a == null) {
            return f362b;
        }
        return f361a.f363c;
    }

    /* renamed from: i */
    public static boolean m457i() {
        if (f361a == null) {
            return false;
        }
        return f361a.f364d;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static Map<Class<? extends Kit>, Kit> m452b(Collection<? extends Kit> collection) {
        HashMap hashMap = new HashMap(collection.size());
        m450a((Map<Class<? extends Kit>, Kit>) hashMap, collection);
        return hashMap;
    }

    /* renamed from: a */
    private static void m450a(Map<Class<? extends Kit>, Kit> map, Collection<? extends Kit> collection) {
        for (Kit iVar : collection) {
            map.put(iVar.getClass(), iVar);
            if (iVar instanceof KitGroup) {
                m450a(map, ((KitGroup) iVar).getKits());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public InitializationCallback<?> mo8518a(final int i) {
        return new InitializationCallback() {

            /* renamed from: a */
            final CountDownLatch f376a = new CountDownLatch(i);

            /* renamed from: a */
            public void mo8529a(Object obj) {
                this.f376a.countDown();
                if (this.f376a.getCount() == 0) {
                    Fabric.this.f374n.set(true);
                    Fabric.this.f369i.mo8529a(Fabric.this);
                }
            }

            /* renamed from: a */
            public void mo8528a(Exception exc) {
                Fabric.this.f369i.mo8528a(exc);
            }
        };
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public Future<Map<String, KitInfo>> mo8522b(Context context) {
        return mo8526f().submit(new FabricKitsFinder(context.getPackageCodePath()));
    }
}
