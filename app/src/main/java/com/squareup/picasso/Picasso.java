package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p001v4.internal.view.SupportMenu;
import android.widget.ImageView;
import com.squareup.picasso.Action;
import java.io.File;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;

/* renamed from: com.squareup.picasso.v */
public class Picasso {

    /* renamed from: a */
    static final Handler f2549a = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 3:
                    Action aVar = (Action) message.obj;
                    if (aVar.mo17472i().f2560l) {
                        Utils.m3284a("Main", "canceled", aVar.f2415b.mo17598a(), "target got garbage collected");
                    }
                    aVar.f2414a.mo17581a(aVar.mo17466c());
                    return;
                case 8:
                    List list = (List) message.obj;
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        BitmapHunter cVar = (BitmapHunter) list.get(i);
                        cVar.f2475b.mo17580a(cVar);
                    }
                    return;
                case 13:
                    List list2 = (List) message.obj;
                    int size2 = list2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        Action aVar2 = (Action) list2.get(i2);
                        aVar2.f2414a.mo17583c(aVar2);
                    }
                    return;
                default:
                    throw new AssertionError("Unknown handler message received: " + message.what);
            }
        }
    };

    /* renamed from: b */
    static volatile Picasso f2550b = null;

    /* renamed from: c */
    final Context f2551c;

    /* renamed from: d */
    final C1627i f2552d;

    /* renamed from: e */
    final C1624d f2553e;

    /* renamed from: f */
    final Stats f2554f;

    /* renamed from: g */
    final Map<Object, Action> f2555g;

    /* renamed from: h */
    final Map<ImageView, DeferredRequestCreator> f2556h;

    /* renamed from: i */
    final ReferenceQueue<Object> f2557i;

    /* renamed from: j */
    final Bitmap.Config f2558j;

    /* renamed from: k */
    boolean f2559k;

    /* renamed from: l */
    volatile boolean f2560l;

    /* renamed from: m */
    boolean f2561m;

    /* renamed from: n */
    private final C1639c f2562n;

    /* renamed from: o */
    private final C1642f f2563o;

    /* renamed from: p */
    private final C1637b f2564p;

    /* renamed from: q */
    private final List<RequestHandler> f2565q;

    /* renamed from: com.squareup.picasso.v$a */
    /* compiled from: Picasso */
    public static class C1636a {

        /* renamed from: a */
        private final Context f2566a;

        /* renamed from: b */
        private Downloader f2567b;

        /* renamed from: c */
        private ExecutorService f2568c;

        /* renamed from: d */
        private C1624d f2569d;

        /* renamed from: e */
        private C1639c f2570e;

        /* renamed from: f */
        private C1642f f2571f;

        /* renamed from: g */
        private List<RequestHandler> f2572g;

        /* renamed from: h */
        private Bitmap.Config f2573h;

        /* renamed from: i */
        private boolean f2574i;

        /* renamed from: j */
        private boolean f2575j;

        public C1636a(@NonNull Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.f2566a = context.getApplicationContext();
        }

        /* renamed from: a */
        public Picasso mo17585a() {
            Context context = this.f2566a;
            if (this.f2567b == null) {
                this.f2567b = new OkHttp3Downloader(context);
            }
            if (this.f2569d == null) {
                this.f2569d = new LruCache(context);
            }
            if (this.f2568c == null) {
                this.f2568c = new PicassoExecutorService();
            }
            if (this.f2571f == null) {
                this.f2571f = C1642f.f2582a;
            }
            Stats acVar = new Stats(this.f2569d);
            return new Picasso(context, new C1627i(context, this.f2568c, Picasso.f2549a, this.f2567b, this.f2569d, acVar), this.f2569d, this.f2570e, this.f2571f, this.f2572g, acVar, this.f2573h, this.f2574i, this.f2575j);
        }
    }

    /* renamed from: com.squareup.picasso.v$b */
    /* compiled from: Picasso */
    private static class C1637b extends Thread {

        /* renamed from: a */
        private final ReferenceQueue<Object> f2576a;

        /* renamed from: b */
        private final Handler f2577b;

        C1637b(ReferenceQueue<Object> referenceQueue, Handler handler) {
            this.f2576a = referenceQueue;
            this.f2577b = handler;
            setDaemon(true);
            setName("Picasso-refQueue");
        }

        public void run() {
            Process.setThreadPriority(10);
            while (true) {
                try {
                    Action.C1611a aVar = (Action.C1611a) this.f2576a.remove(1000);
                    Message obtainMessage = this.f2577b.obtainMessage();
                    if (aVar != null) {
                        obtainMessage.what = 3;
                        obtainMessage.obj = aVar.f2426a;
                        this.f2577b.sendMessage(obtainMessage);
                    } else {
                        obtainMessage.recycle();
                    }
                } catch (InterruptedException e) {
                    return;
                } catch (Exception e2) {
                    this.f2577b.post(new Runnable() {
                        public void run() {
                            throw new RuntimeException(e2);
                        }
                    });
                    return;
                }
            }
        }
    }

    /* renamed from: com.squareup.picasso.v$c */
    /* compiled from: Picasso */
    public interface C1639c {
        /* renamed from: a */
        void mo17588a(Picasso vVar, Uri uri, Exception exc);
    }

    /* renamed from: com.squareup.picasso.v$d */
    /* compiled from: Picasso */
    public enum C1640d {
        MEMORY(-16711936),
        DISK(-16776961),
        NETWORK(SupportMenu.CATEGORY_MASK);
        
        final int debugColor;

        private C1640d(int i) {
            this.debugColor = i;
        }
    }

    /* renamed from: com.squareup.picasso.v$e */
    /* compiled from: Picasso */
    public enum C1641e {
        LOW,
        NORMAL,
        HIGH
    }

    /* renamed from: com.squareup.picasso.v$f */
    /* compiled from: Picasso */
    public interface C1642f {

        /* renamed from: a */
        public static final C1642f f2582a = new C1642f() {
            /* renamed from: a */
            public C1645y mo17589a(C1645y yVar) {
                return yVar;
            }
        };

        /* renamed from: a */
        C1645y mo17589a(C1645y yVar);
    }

    Picasso(Context context, C1627i iVar, C1624d dVar, C1639c cVar, C1642f fVar, List<RequestHandler> list, Stats acVar, Bitmap.Config config, boolean z, boolean z2) {
        this.f2551c = context;
        this.f2552d = iVar;
        this.f2553e = dVar;
        this.f2562n = cVar;
        this.f2563o = fVar;
        this.f2558j = config;
        ArrayList arrayList = new ArrayList((list != null ? list.size() : 0) + 7);
        arrayList.add(new ResourceRequestHandler(context));
        if (list != null) {
            arrayList.addAll(list);
        }
        arrayList.add(new ContactsPhotoRequestHandler(context));
        arrayList.add(new MediaStoreRequestHandler(context));
        arrayList.add(new ContentStreamRequestHandler(context));
        arrayList.add(new AssetRequestHandler(context));
        arrayList.add(new FileRequestHandler(context));
        arrayList.add(new NetworkRequestHandler(iVar.f2508d, acVar));
        this.f2565q = Collections.unmodifiableList(arrayList);
        this.f2554f = acVar;
        this.f2555g = new WeakHashMap();
        this.f2556h = new WeakHashMap();
        this.f2559k = z;
        this.f2560l = z2;
        this.f2557i = new ReferenceQueue<>();
        this.f2564p = new C1637b(this.f2557i, f2549a);
        this.f2564p.start();
    }

    /* renamed from: a */
    public void mo17576a(@NonNull ImageView imageView) {
        if (imageView == null) {
            throw new IllegalArgumentException("view cannot be null.");
        }
        mo17581a((Object) imageView);
    }

    /* renamed from: a */
    public void mo17579a(@NonNull Target aeVar) {
        if (aeVar == null) {
            throw new IllegalArgumentException("target cannot be null.");
        }
        mo17581a((Object) aeVar);
    }

    /* renamed from: a */
    public RequestCreator mo17573a(@Nullable Uri uri) {
        return new RequestCreator(this, uri, 0);
    }

    /* renamed from: a */
    public RequestCreator mo17574a(@NonNull File file) {
        if (file == null) {
            return new RequestCreator(this, null, 0);
        }
        return mo17573a(Uri.fromFile(file));
    }

    /* renamed from: a */
    public RequestCreator mo17572a(@DrawableRes int i) {
        if (i != 0) {
            return new RequestCreator(this, null, i);
        }
        throw new IllegalArgumentException("Resource ID must not be zero.");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public List<RequestHandler> mo17575a() {
        return this.f2565q;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1645y mo17571a(C1645y yVar) {
        C1645y a = this.f2563o.mo17589a(yVar);
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Request transformer " + this.f2563o.getClass().getCanonicalName() + " returned null for " + yVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17577a(ImageView imageView, DeferredRequestCreator hVar) {
        if (this.f2556h.containsKey(imageView)) {
            mo17581a((Object) imageView);
        }
        this.f2556h.put(imageView, hVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17578a(Action aVar) {
        Object c = aVar.mo17466c();
        if (!(c == null || this.f2555g.get(c) == aVar)) {
            mo17581a(c);
            this.f2555g.put(c, aVar);
        }
        mo17582b(aVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17582b(Action aVar) {
        this.f2552d.mo17537a(aVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Bitmap mo17570a(String str) {
        Bitmap a = this.f2553e.mo17527a(str);
        if (a != null) {
            this.f2554f.mo17484a();
        } else {
            this.f2554f.mo17488b();
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17580a(BitmapHunter cVar) {
        boolean z = true;
        Action i = cVar.mo17513i();
        List<Action> k = cVar.mo17515k();
        boolean z2 = k != null && !k.isEmpty();
        if (i == null && !z2) {
            z = false;
        }
        if (z) {
            Uri uri = cVar.mo17512h().f2596d;
            Exception l = cVar.mo17516l();
            Bitmap e = cVar.mo17509e();
            C1640d m = cVar.mo17517m();
            if (i != null) {
                m3394a(e, m, i, l);
            }
            if (z2) {
                int size = k.size();
                for (int i2 = 0; i2 < size; i2++) {
                    m3394a(e, m, k.get(i2), l);
                }
            }
            if (this.f2562n != null && l != null) {
                this.f2562n.mo17588a(this, uri, l);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo17583c(Action aVar) {
        Bitmap bitmap;
        if (MemoryPolicy.shouldReadFromMemoryCache(aVar.f2418e)) {
            bitmap = mo17570a(aVar.mo17467d());
        } else {
            bitmap = null;
        }
        if (bitmap != null) {
            m3394a(bitmap, C1640d.MEMORY, aVar, null);
            if (this.f2560l) {
                Utils.m3284a("Main", "completed", aVar.f2415b.mo17598a(), "from " + C1640d.MEMORY);
                return;
            }
            return;
        }
        mo17578a(aVar);
        if (this.f2560l) {
            Utils.m3283a("Main", "resumed", aVar.f2415b.mo17598a());
        }
    }

    /* renamed from: a */
    private void m3394a(Bitmap bitmap, C1640d dVar, Action aVar, Exception exc) {
        if (!aVar.mo17468e()) {
            if (!aVar.mo17469f()) {
                this.f2555g.remove(aVar.mo17466c());
            }
            if (bitmap == null) {
                aVar.mo17464a(exc);
                if (this.f2560l) {
                    Utils.m3284a("Main", "errored", aVar.f2415b.mo17598a(), exc.getMessage());
                }
            } else if (dVar == null) {
                throw new AssertionError("LoadedFrom cannot be null.");
            } else {
                aVar.mo17463a(bitmap, dVar);
                if (this.f2560l) {
                    Utils.m3284a("Main", "completed", aVar.f2415b.mo17598a(), "from " + dVar);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17581a(Object obj) {
        Utils.m3287b();
        Action remove = this.f2555g.remove(obj);
        if (remove != null) {
            remove.mo17462a();
            this.f2552d.mo17544b(remove);
        }
        if (obj instanceof ImageView) {
            DeferredRequestCreator remove2 = this.f2556h.remove((ImageView) obj);
            if (remove2 != null) {
                remove2.mo17531a();
            }
        }
    }

    /* renamed from: b */
    public static Picasso m3395b() {
        if (f2550b == null) {
            synchronized (Picasso.class) {
                if (f2550b == null) {
                    if (PicassoProvider.f2413a == null) {
                        throw new IllegalStateException("context == null");
                    }
                    f2550b = new C1636a(PicassoProvider.f2413a).mo17585a();
                }
            }
        }
        return f2550b;
    }
}
