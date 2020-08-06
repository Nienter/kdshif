package com.squareup.picasso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.facebook.internal.ServerProtocol;
import com.squareup.picasso.NetworkRequestHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;

/* renamed from: com.squareup.picasso.i */
/* compiled from: Dispatcher */
class C1627i {

    /* renamed from: a */
    final C1630b f2505a = new C1630b();

    /* renamed from: b */
    final Context f2506b;

    /* renamed from: c */
    final ExecutorService f2507c;

    /* renamed from: d */
    final Downloader f2508d;

    /* renamed from: e */
    final Map<String, BitmapHunter> f2509e;

    /* renamed from: f */
    final Map<Object, Action> f2510f;

    /* renamed from: g */
    final Map<Object, Action> f2511g;

    /* renamed from: h */
    final Set<Object> f2512h;

    /* renamed from: i */
    final Handler f2513i;

    /* renamed from: j */
    final Handler f2514j;

    /* renamed from: k */
    final C1624d f2515k;

    /* renamed from: l */
    final Stats f2516l;

    /* renamed from: m */
    final List<BitmapHunter> f2517m;

    /* renamed from: n */
    final C1631c f2518n;

    /* renamed from: o */
    final boolean f2519o;

    /* renamed from: p */
    boolean f2520p;

    /* renamed from: com.squareup.picasso.i$a */
    /* compiled from: Dispatcher */
    private static class C1628a extends Handler {

        /* renamed from: a */
        private final C1627i f2521a;

        public C1628a(Looper looper, C1627i iVar) {
            super(looper);
            this.f2521a = iVar;
        }

        public void handleMessage(final Message message) {
            boolean z = true;
            switch (message.what) {
                case 1:
                    this.f2521a.mo17548c((Action) message.obj);
                    return;
                case 2:
                    this.f2521a.mo17550d((Action) message.obj);
                    return;
                case 4:
                    this.f2521a.mo17552e((BitmapHunter) message.obj);
                    return;
                case 5:
                    this.f2521a.mo17551d((BitmapHunter) message.obj);
                    return;
                case 6:
                    this.f2521a.mo17540a((BitmapHunter) message.obj, false);
                    return;
                case 7:
                    this.f2521a.mo17535a();
                    return;
                case 9:
                    this.f2521a.mo17543b((NetworkInfo) message.obj);
                    return;
                case 10:
                    C1627i iVar = this.f2521a;
                    if (message.arg1 != 1) {
                        z = false;
                    }
                    iVar.mo17547b(z);
                    return;
                case 11:
                    this.f2521a.mo17541a(message.obj);
                    return;
                case 12:
                    this.f2521a.mo17546b(message.obj);
                    return;
                default:
                    Picasso.f2549a.post(new Runnable() {
                        public void run() {
                            throw new AssertionError("Unknown handler message received: " + message.what);
                        }
                    });
                    return;
            }
        }
    }

    /* renamed from: com.squareup.picasso.i$b */
    /* compiled from: Dispatcher */
    static class C1630b extends HandlerThread {
        C1630b() {
            super("Picasso-Dispatcher", 10);
        }
    }

    /* renamed from: com.squareup.picasso.i$c */
    /* compiled from: Dispatcher */
    static class C1631c extends BroadcastReceiver {

        /* renamed from: a */
        private final C1627i f2524a;

        C1631c(C1627i iVar) {
            this.f2524a = iVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17555a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            if (this.f2524a.f2519o) {
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
            this.f2524a.f2506b.registerReceiver(this, intentFilter);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                    if (intent.hasExtra(ServerProtocol.DIALOG_PARAM_STATE)) {
                        this.f2524a.mo17542a(intent.getBooleanExtra(ServerProtocol.DIALOG_PARAM_STATE, false));
                    }
                } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                    this.f2524a.mo17536a(((ConnectivityManager) Utils.m3275a(context, "connectivity")).getActiveNetworkInfo());
                }
            }
        }
    }

    C1627i(Context context, ExecutorService executorService, Handler handler, Downloader jVar, C1624d dVar, Stats acVar) {
        this.f2505a.start();
        Utils.m3282a(this.f2505a.getLooper());
        this.f2506b = context;
        this.f2507c = executorService;
        this.f2509e = new LinkedHashMap();
        this.f2510f = new WeakHashMap();
        this.f2511g = new WeakHashMap();
        this.f2512h = new HashSet();
        this.f2513i = new C1628a(this.f2505a.getLooper(), this);
        this.f2508d = jVar;
        this.f2514j = handler;
        this.f2515k = dVar;
        this.f2516l = acVar;
        this.f2517m = new ArrayList(4);
        this.f2520p = Utils.m3290c(this.f2506b);
        this.f2519o = Utils.m3288b(context, "android.permission.ACCESS_NETWORK_STATE");
        this.f2518n = new C1631c(this);
        this.f2518n.mo17555a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17537a(Action aVar) {
        this.f2513i.sendMessage(this.f2513i.obtainMessage(1, aVar));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17544b(Action aVar) {
        this.f2513i.sendMessage(this.f2513i.obtainMessage(2, aVar));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17539a(BitmapHunter cVar) {
        this.f2513i.sendMessage(this.f2513i.obtainMessage(4, cVar));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17545b(BitmapHunter cVar) {
        this.f2513i.sendMessageDelayed(this.f2513i.obtainMessage(5, cVar), 500);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo17549c(BitmapHunter cVar) {
        this.f2513i.sendMessage(this.f2513i.obtainMessage(6, cVar));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17536a(NetworkInfo networkInfo) {
        this.f2513i.sendMessage(this.f2513i.obtainMessage(9, networkInfo));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17542a(boolean z) {
        int i;
        Handler handler = this.f2513i;
        Handler handler2 = this.f2513i;
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        handler.sendMessage(handler2.obtainMessage(10, i, 0));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo17548c(Action aVar) {
        mo17538a(aVar, true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17538a(Action aVar, boolean z) {
        if (this.f2512h.contains(aVar.mo17474k())) {
            this.f2511g.put(aVar.mo17466c(), aVar);
            if (aVar.mo17472i().f2560l) {
                Utils.m3284a("Dispatcher", "paused", aVar.f2415b.mo17598a(), "because tag '" + aVar.mo17474k() + "' is paused");
                return;
            }
            return;
        }
        BitmapHunter cVar = this.f2509e.get(aVar.mo17467d());
        if (cVar != null) {
            cVar.mo17503a(aVar);
        } else if (!this.f2507c.isShutdown()) {
            BitmapHunter a = BitmapHunter.m3298a(aVar.mo17472i(), this, this.f2515k, this.f2516l, aVar);
            a.f2487n = this.f2507c.submit(a);
            this.f2509e.put(aVar.mo17467d(), a);
            if (z) {
                this.f2510f.remove(aVar.mo17466c());
            }
            if (aVar.mo17472i().f2560l) {
                Utils.m3283a("Dispatcher", "enqueued", aVar.f2415b.mo17598a());
            }
        } else if (aVar.mo17472i().f2560l) {
            Utils.m3284a("Dispatcher", "ignored", aVar.f2415b.mo17598a(), "because shut down");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo17550d(Action aVar) {
        String d = aVar.mo17467d();
        BitmapHunter cVar = this.f2509e.get(d);
        if (cVar != null) {
            cVar.mo17505b(aVar);
            if (cVar.mo17506b()) {
                this.f2509e.remove(d);
                if (aVar.mo17472i().f2560l) {
                    Utils.m3283a("Dispatcher", "canceled", aVar.mo17465b().mo17598a());
                }
            }
        }
        if (this.f2512h.contains(aVar.mo17474k())) {
            this.f2511g.remove(aVar.mo17466c());
            if (aVar.mo17472i().f2560l) {
                Utils.m3284a("Dispatcher", "canceled", aVar.mo17465b().mo17598a(), "because paused request got canceled");
            }
        }
        Action remove = this.f2510f.remove(aVar.mo17466c());
        if (remove != null && remove.mo17472i().f2560l) {
            Utils.m3284a("Dispatcher", "canceled", remove.mo17465b().mo17598a(), "from replaying");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17541a(Object obj) {
        if (this.f2512h.add(obj)) {
            Iterator<BitmapHunter> it = this.f2509e.values().iterator();
            while (it.hasNext()) {
                BitmapHunter next = it.next();
                boolean z = next.mo17514j().f2560l;
                Action i = next.mo17513i();
                List<Action> k = next.mo17515k();
                boolean z2 = k != null && !k.isEmpty();
                if (i != null || z2) {
                    if (i != null && i.mo17474k().equals(obj)) {
                        next.mo17505b(i);
                        this.f2511g.put(i.mo17466c(), i);
                        if (z) {
                            Utils.m3284a("Dispatcher", "paused", i.f2415b.mo17598a(), "because tag '" + obj + "' was paused");
                        }
                    }
                    if (z2) {
                        for (int size = k.size() - 1; size >= 0; size--) {
                            Action aVar = k.get(size);
                            if (aVar.mo17474k().equals(obj)) {
                                next.mo17505b(aVar);
                                this.f2511g.put(aVar.mo17466c(), aVar);
                                if (z) {
                                    Utils.m3284a("Dispatcher", "paused", aVar.f2415b.mo17598a(), "because tag '" + obj + "' was paused");
                                }
                            }
                        }
                    }
                    if (next.mo17506b()) {
                        it.remove();
                        if (z) {
                            Utils.m3284a("Dispatcher", "canceled", Utils.m3277a(next), "all actions paused");
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17546b(Object obj) {
        if (this.f2512h.remove(obj)) {
            ArrayList arrayList = null;
            Iterator<Action> it = this.f2511g.values().iterator();
            while (it.hasNext()) {
                Action next = it.next();
                if (next.mo17474k().equals(obj)) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(next);
                    it.remove();
                }
            }
            if (arrayList != null) {
                this.f2514j.sendMessage(this.f2514j.obtainMessage(13, arrayList));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo17551d(BitmapHunter cVar) {
        boolean z;
        if (!cVar.mo17507c()) {
            if (this.f2507c.isShutdown()) {
                mo17540a(cVar, false);
                return;
            }
            NetworkInfo networkInfo = null;
            if (this.f2519o) {
                networkInfo = ((ConnectivityManager) Utils.m3275a(this.f2506b, "connectivity")).getActiveNetworkInfo();
            }
            if (cVar.mo17504a(this.f2520p, networkInfo)) {
                if (cVar.mo17514j().f2560l) {
                    Utils.m3283a("Dispatcher", "retrying", Utils.m3277a(cVar));
                }
                if (cVar.mo17516l() instanceof NetworkRequestHandler.C1633a) {
                    cVar.f2482i |= NetworkPolicy.NO_CACHE.index;
                }
                cVar.f2487n = this.f2507c.submit(cVar);
                return;
            }
            if (!this.f2519o || !cVar.mo17508d()) {
                z = false;
            } else {
                z = true;
            }
            mo17540a(cVar, z);
            if (z) {
                m3343f(cVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo17552e(BitmapHunter cVar) {
        if (MemoryPolicy.shouldWriteToMemoryCache(cVar.mo17511g())) {
            this.f2515k.mo17528a(cVar.mo17510f(), cVar.mo17509e());
        }
        this.f2509e.remove(cVar.mo17510f());
        m3344g(cVar);
        if (cVar.mo17514j().f2560l) {
            Utils.m3284a("Dispatcher", "batched", Utils.m3277a(cVar), "for completion");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17535a() {
        ArrayList arrayList = new ArrayList(this.f2517m);
        this.f2517m.clear();
        this.f2514j.sendMessage(this.f2514j.obtainMessage(8, arrayList));
        m3340a((List<BitmapHunter>) arrayList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17540a(BitmapHunter cVar, boolean z) {
        if (cVar.mo17514j().f2560l) {
            Utils.m3284a("Dispatcher", "batched", Utils.m3277a(cVar), "for error" + (z ? " (will replay)" : ""));
        }
        this.f2509e.remove(cVar.mo17510f());
        m3344g(cVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17547b(boolean z) {
        this.f2520p = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17543b(NetworkInfo networkInfo) {
        if (this.f2507c instanceof PicassoExecutorService) {
            ((PicassoExecutorService) this.f2507c).mo17594a(networkInfo);
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            m3341b();
        }
    }

    /* renamed from: b */
    private void m3341b() {
        if (!this.f2510f.isEmpty()) {
            Iterator<Action> it = this.f2510f.values().iterator();
            while (it.hasNext()) {
                Action next = it.next();
                it.remove();
                if (next.mo17472i().f2560l) {
                    Utils.m3283a("Dispatcher", "replaying", next.mo17465b().mo17598a());
                }
                mo17538a(next, false);
            }
        }
    }

    /* renamed from: f */
    private void m3343f(BitmapHunter cVar) {
        Action i = cVar.mo17513i();
        if (i != null) {
            m3342e(i);
        }
        List<Action> k = cVar.mo17515k();
        if (k != null) {
            int size = k.size();
            for (int i2 = 0; i2 < size; i2++) {
                m3342e(k.get(i2));
            }
        }
    }

    /* renamed from: e */
    private void m3342e(Action aVar) {
        Object c = aVar.mo17466c();
        if (c != null) {
            aVar.f2424k = true;
            this.f2510f.put(c, aVar);
        }
    }

    /* renamed from: g */
    private void m3344g(BitmapHunter cVar) {
        if (!cVar.mo17507c()) {
            if (cVar.f2486m != null) {
                cVar.f2486m.prepareToDraw();
            }
            this.f2517m.add(cVar);
            if (!this.f2513i.hasMessages(7)) {
                this.f2513i.sendEmptyMessageDelayed(7, 200);
            }
        }
    }

    /* renamed from: a */
    private void m3340a(List<BitmapHunter> list) {
        if (list != null && !list.isEmpty() && list.get(0).mo17514j().f2560l) {
            StringBuilder sb = new StringBuilder();
            for (BitmapHunter next : list) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(Utils.m3277a(next));
            }
            Utils.m3283a("Dispatcher", "delivered", sb.toString());
        }
    }
}
