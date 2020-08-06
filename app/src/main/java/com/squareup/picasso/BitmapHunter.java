package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.NetworkInfo;
import android.os.Build;
import com.squareup.picasso.NetworkRequestHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import p033d.BufferedSource;
import p033d.Okio;
import p033d.Source;

/* renamed from: com.squareup.picasso.c */
class BitmapHunter implements Runnable {

    /* renamed from: t */
    private static final Object f2470t = new Object();

    /* renamed from: u */
    private static final ThreadLocal<StringBuilder> f2471u = new ThreadLocal<StringBuilder>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public StringBuilder initialValue() {
            return new StringBuilder("Picasso-");
        }
    };

    /* renamed from: v */
    private static final AtomicInteger f2472v = new AtomicInteger();

    /* renamed from: w */
    private static final RequestHandler f2473w = new RequestHandler() {
        /* renamed from: a */
        public boolean mo17477a(C1645y yVar) {
            return true;
        }

        /* renamed from: a */
        public RequestHandler.C1612a mo17476a(C1645y yVar, int i) {
            throw new IllegalStateException("Unrecognized type of request: " + yVar);
        }
    };

    /* renamed from: a */
    final int f2474a = f2472v.incrementAndGet();

    /* renamed from: b */
    final Picasso f2475b;

    /* renamed from: c */
    final C1627i f2476c;

    /* renamed from: d */
    final C1624d f2477d;

    /* renamed from: e */
    final Stats f2478e;

    /* renamed from: f */
    final String f2479f;

    /* renamed from: g */
    final C1645y f2480g;

    /* renamed from: h */
    final int f2481h;

    /* renamed from: i */
    int f2482i;

    /* renamed from: j */
    final RequestHandler f2483j;

    /* renamed from: k */
    Action f2484k;

    /* renamed from: l */
    List<Action> f2485l;

    /* renamed from: m */
    Bitmap f2486m;

    /* renamed from: n */
    Future<?> f2487n;

    /* renamed from: o */
    Picasso.C1640d f2488o;

    /* renamed from: p */
    Exception f2489p;

    /* renamed from: q */
    int f2490q;

    /* renamed from: r */
    int f2491r;

    /* renamed from: s */
    Picasso.C1641e f2492s;

    BitmapHunter(Picasso vVar, C1627i iVar, C1624d dVar, Stats acVar, Action aVar, RequestHandler aaVar) {
        this.f2475b = vVar;
        this.f2476c = iVar;
        this.f2477d = dVar;
        this.f2478e = acVar;
        this.f2484k = aVar;
        this.f2479f = aVar.mo17467d();
        this.f2480g = aVar.mo17465b();
        this.f2492s = aVar.mo17473j();
        this.f2481h = aVar.mo17470g();
        this.f2482i = aVar.mo17471h();
        this.f2483j = aaVar;
        this.f2491r = aaVar.mo17475a();
    }

    /* renamed from: a */
    static Bitmap m3296a(Source sVar, C1645y yVar) {
        MarkableInputStream oVar;
        BufferedSource a = Okio.m3590a(sVar);
        boolean a2 = Utils.m3285a(a);
        boolean z = yVar.f2610r && Build.VERSION.SDK_INT < 21;
        BitmapFactory.Options c = RequestHandler.m3236c(yVar);
        boolean a3 = RequestHandler.m3235a(c);
        if (a2 || z) {
            byte[] r = a.mo17689r();
            if (a3) {
                BitmapFactory.decodeByteArray(r, 0, r.length, c);
                RequestHandler.m3234a(yVar.f2600h, yVar.f2601i, c, yVar);
            }
            return BitmapFactory.decodeByteArray(r, 0, r.length, c);
        }
        InputStream f = a.mo17667f();
        if (a3) {
            MarkableInputStream oVar2 = new MarkableInputStream(f);
            oVar2.mo17560a(false);
            long a4 = oVar2.mo17558a(1024);
            BitmapFactory.decodeStream(oVar2, null, c);
            RequestHandler.m3234a(yVar.f2600h, yVar.f2601i, c, yVar);
            oVar2.mo17559a(a4);
            oVar2.mo17560a(true);
            oVar = oVar2;
        } else {
            oVar = f;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(oVar, null, c);
        if (decodeStream != null) {
            return decodeStream;
        }
        throw new IOException("Failed to decode stream.");
    }

    public void run() {
        String str;
        try {
            m3299a(this.f2480g);
            if (this.f2475b.f2560l) {
                Utils.m3283a("Hunter", "executing", Utils.m3277a(this));
            }
            this.f2486m = mo17502a();
            if (this.f2486m == null) {
                this.f2476c.mo17549c(this);
            } else {
                this.f2476c.mo17539a(this);
            }
        } catch (NetworkRequestHandler.C1634b e) {
            if (!NetworkPolicy.isOfflineOnly(e.networkPolicy) || e.code != 504) {
                this.f2489p = e;
            }
            this.f2476c.mo17549c(this);
        } catch (IOException e2) {
            this.f2489p = e2;
            this.f2476c.mo17545b(this);
        } catch (OutOfMemoryError e3) {
            StringWriter stringWriter = new StringWriter();
            this.f2478e.mo17494e().mo17497a(new PrintWriter(stringWriter));
            this.f2489p = new RuntimeException(stringWriter.toString(), e3);
            this.f2476c.mo17549c(this);
        } catch (Exception e4) {
            this.f2489p = e4;
            this.f2476c.mo17549c(this);
        } finally {
            str = "Picasso-Idle";
            Thread.currentThread().setName(str);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Bitmap mo17502a() {
        Bitmap bitmap = null;
        if (MemoryPolicy.shouldReadFromMemoryCache(this.f2481h)) {
            bitmap = this.f2477d.mo17527a(this.f2479f);
            if (bitmap != null) {
                this.f2478e.mo17484a();
                this.f2488o = Picasso.C1640d.MEMORY;
                if (this.f2475b.f2560l) {
                    Utils.m3284a("Hunter", "decoded", this.f2480g.mo17598a(), "from cache");
                }
                return bitmap;
            }
        }
        this.f2482i = this.f2491r == 0 ? NetworkPolicy.OFFLINE.index : this.f2482i;
        RequestHandler.C1612a a = this.f2483j.mo17476a(this.f2480g, this.f2482i);
        if (a != null) {
            this.f2488o = a.mo17482c();
            this.f2490q = a.mo17483d();
            bitmap = a.mo17480a();
            if (bitmap == null) {
                Source b = a.mo17481b();
                try {
                    bitmap = m3296a(b, this.f2480g);
                } finally {
                    try {
                        b.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        if (bitmap != null) {
            if (this.f2475b.f2560l) {
                Utils.m3283a("Hunter", "decoded", this.f2480g.mo17598a());
            }
            this.f2478e.mo17486a(bitmap);
            if (this.f2480g.mo17602e() || this.f2490q != 0) {
                synchronized (f2470t) {
                    if (this.f2480g.mo17603f() || this.f2490q != 0) {
                        bitmap = m3295a(this.f2480g, bitmap, this.f2490q);
                        if (this.f2475b.f2560l) {
                            Utils.m3283a("Hunter", "transformed", this.f2480g.mo17598a());
                        }
                    }
                    if (this.f2480g.mo17604g()) {
                        bitmap = m3297a(this.f2480g.f2599g, bitmap);
                        if (this.f2475b.f2560l) {
                            Utils.m3284a("Hunter", "transformed", this.f2480g.mo17598a(), "from custom transformations");
                        }
                    }
                }
                if (bitmap != null) {
                    this.f2478e.mo17490b(bitmap);
                }
            }
        }
        return bitmap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17503a(Action aVar) {
        boolean z = this.f2475b.f2560l;
        C1645y yVar = aVar.f2415b;
        if (this.f2484k == null) {
            this.f2484k = aVar;
            if (!z) {
                return;
            }
            if (this.f2485l == null || this.f2485l.isEmpty()) {
                Utils.m3284a("Hunter", "joined", yVar.mo17598a(), "to empty hunter");
            } else {
                Utils.m3284a("Hunter", "joined", yVar.mo17598a(), Utils.m3278a(this, "to "));
            }
        } else {
            if (this.f2485l == null) {
                this.f2485l = new ArrayList(3);
            }
            this.f2485l.add(aVar);
            if (z) {
                Utils.m3284a("Hunter", "joined", yVar.mo17598a(), Utils.m3278a(this, "to "));
            }
            Picasso.C1641e j = aVar.mo17473j();
            if (j.ordinal() > this.f2492s.ordinal()) {
                this.f2492s = j;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17505b(Action aVar) {
        boolean z = false;
        if (this.f2484k == aVar) {
            this.f2484k = null;
            z = true;
        } else if (this.f2485l != null) {
            z = this.f2485l.remove(aVar);
        }
        if (z && aVar.mo17473j() == this.f2492s) {
            this.f2492s = m3302o();
        }
        if (this.f2475b.f2560l) {
            Utils.m3284a("Hunter", "removed", aVar.f2415b.mo17598a(), Utils.m3278a(this, "from "));
        }
    }

    /* renamed from: o */
    private Picasso.C1641e m3302o() {
        Picasso.C1641e eVar;
        boolean z = true;
        int i = 0;
        Picasso.C1641e eVar2 = Picasso.C1641e.LOW;
        boolean z2 = this.f2485l != null && !this.f2485l.isEmpty();
        if (this.f2484k == null && !z2) {
            z = false;
        }
        if (!z) {
            return eVar2;
        }
        if (this.f2484k != null) {
            eVar = this.f2484k.mo17473j();
        } else {
            eVar = eVar2;
        }
        if (!z2) {
            return eVar;
        }
        int size = this.f2485l.size();
        while (i < size) {
            Picasso.C1641e j = this.f2485l.get(i).mo17473j();
            if (j.ordinal() <= eVar.ordinal()) {
                j = eVar;
            }
            i++;
            eVar = j;
        }
        return eVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo17506b() {
        if (this.f2484k != null) {
            return false;
        }
        if ((this.f2485l == null || this.f2485l.isEmpty()) && this.f2487n != null && this.f2487n.cancel(false)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo17507c() {
        return this.f2487n != null && this.f2487n.isCancelled();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo17504a(boolean z, NetworkInfo networkInfo) {
        if (!(this.f2491r > 0)) {
            return false;
        }
        this.f2491r--;
        return this.f2483j.mo17478a(z, networkInfo);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean mo17508d() {
        return this.f2483j.mo17479b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public Bitmap mo17509e() {
        return this.f2486m;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public String mo17510f() {
        return this.f2479f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public int mo17511g() {
        return this.f2481h;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public C1645y mo17512h() {
        return this.f2480g;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public Action mo17513i() {
        return this.f2484k;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public Picasso mo17514j() {
        return this.f2475b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public List<Action> mo17515k() {
        return this.f2485l;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public Exception mo17516l() {
        return this.f2489p;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public Picasso.C1640d mo17517m() {
        return this.f2488o;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: n */
    public Picasso.C1641e mo17518n() {
        return this.f2492s;
    }

    /* renamed from: a */
    static void m3299a(C1645y yVar) {
        String c = yVar.mo17600c();
        StringBuilder sb = f2471u.get();
        sb.ensureCapacity("Picasso-".length() + c.length());
        sb.replace("Picasso-".length(), sb.length(), c);
        Thread.currentThread().setName(sb.toString());
    }

    /* renamed from: a */
    static BitmapHunter m3298a(Picasso vVar, C1627i iVar, C1624d dVar, Stats acVar, Action aVar) {
        C1645y b = aVar.mo17465b();
        List<RequestHandler> a = vVar.mo17575a();
        int size = a.size();
        for (int i = 0; i < size; i++) {
            RequestHandler aaVar = a.get(i);
            if (aaVar.mo17477a(b)) {
                return new BitmapHunter(vVar, iVar, dVar, acVar, aVar, aaVar);
            }
        }
        return new BitmapHunter(vVar, iVar, dVar, acVar, aVar, f2473w);
    }

    /* renamed from: a */
    static Bitmap m3297a(List<Transformation> list, Bitmap bitmap) {
        int size = list.size();
        int i = 0;
        Bitmap bitmap2 = bitmap;
        while (i < size) {
            final Transformation agVar = list.get(i);
            try {
                Bitmap a = agVar.mo17273a(bitmap2);
                if (a == null) {
                    final StringBuilder append = new StringBuilder().append("Transformation ").append(agVar.mo17274a()).append(" returned null after ").append(i).append(" previous transformation(s).\n\nTransformation list:\n");
                    for (Transformation a2 : list) {
                        append.append(a2.mo17274a()).append(10);
                    }
                    Picasso.f2549a.post(new Runnable() {
                        public void run() {
                            throw new NullPointerException(append.toString());
                        }
                    });
                    return null;
                } else if (a == bitmap2 && bitmap2.isRecycled()) {
                    Picasso.f2549a.post(new Runnable() {
                        public void run() {
                            throw new IllegalStateException("Transformation " + agVar.mo17274a() + " returned input Bitmap but recycled it.");
                        }
                    });
                    return null;
                } else if (a == bitmap2 || bitmap2.isRecycled()) {
                    i++;
                    bitmap2 = a;
                } else {
                    Picasso.f2549a.post(new Runnable() {
                        public void run() {
                            throw new IllegalStateException("Transformation " + agVar.mo17274a() + " mutated input Bitmap but failed to recycle the original.");
                        }
                    });
                    return null;
                }
            } catch (RuntimeException e) {
                Picasso.f2549a.post(new Runnable() {
                    public void run() {
                        throw new RuntimeException("Transformation " + agVar.mo17274a() + " crashed with exception.", e);
                    }
                });
                return null;
            }
        }
        return bitmap2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    static Bitmap m3295a(C1645y yVar, Bitmap bitmap, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        Bitmap createBitmap;
        int i6;
        float f;
        int i7;
        int i8;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        boolean z = yVar.f2605m;
        int i9 = 0;
        int i10 = 0;
        Matrix matrix = new Matrix();
        if (yVar.mo17603f() || i != 0) {
            int i11 = yVar.f2600h;
            int i12 = yVar.f2601i;
            float f2 = yVar.f2606n;
            if (f2 != 0.0f) {
                double cos = Math.cos(Math.toRadians((double) f2));
                double sin = Math.sin(Math.toRadians((double) f2));
                if (yVar.f2609q) {
                    matrix.setRotate(f2, yVar.f2607o, yVar.f2608p);
                    double d = (((double) yVar.f2607o) * (1.0d - cos)) + (((double) yVar.f2608p) * sin);
                    double d2 = (((double) yVar.f2608p) * (1.0d - cos)) - (((double) yVar.f2607o) * sin);
                    double d3 = (((double) yVar.f2600h) * cos) + d;
                    double d4 = (((double) yVar.f2600h) * sin) + d2;
                    double d5 = ((((double) yVar.f2600h) * cos) + d) - (((double) yVar.f2601i) * sin);
                    double d6 = (((double) yVar.f2600h) * sin) + d2 + (((double) yVar.f2601i) * cos);
                    double d7 = d - (sin * ((double) yVar.f2601i));
                    double d8 = (cos * ((double) yVar.f2601i)) + d2;
                    double max = Math.max(d7, Math.max(d5, Math.max(d, d3)));
                    double min = Math.min(d7, Math.min(d5, Math.min(d, d3)));
                    double max2 = Math.max(d8, Math.max(d6, Math.max(d2, d4)));
                    double min2 = Math.min(d8, Math.min(d6, Math.min(d2, d4)));
                    i11 = (int) Math.floor(max - min);
                    i12 = (int) Math.floor(max2 - min2);
                } else {
                    matrix.setRotate(f2);
                    double d9 = ((double) yVar.f2600h) * cos;
                    double d10 = ((double) yVar.f2600h) * sin;
                    double d11 = (((double) yVar.f2600h) * cos) - (((double) yVar.f2601i) * sin);
                    double d12 = (((double) yVar.f2600h) * sin) + (((double) yVar.f2601i) * cos);
                    double d13 = -(sin * ((double) yVar.f2601i));
                    double d14 = cos * ((double) yVar.f2601i);
                    double max3 = Math.max(d13, Math.max(d11, Math.max(0.0d, d9)));
                    double min3 = Math.min(d13, Math.min(d11, Math.min(0.0d, d9)));
                    double max4 = Math.max(d14, Math.max(d12, Math.max(0.0d, d10)));
                    double min4 = Math.min(d14, Math.min(d12, Math.min(0.0d, d10)));
                    i11 = (int) Math.floor(max3 - min3);
                    i12 = (int) Math.floor(max4 - min4);
                }
            }
            if (i != 0) {
                int a = m3294a(i);
                int b = m3301b(i);
                if (a != 0) {
                    matrix.preRotate((float) a);
                    if (a == 90 || a == 270) {
                        int i13 = i11;
                        i11 = i12;
                        i12 = i13;
                    }
                }
                if (b != 1) {
                    matrix.postScale((float) b, 1.0f);
                }
            }
            if (yVar.f2602j) {
                float f3 = i11 != 0 ? ((float) i11) / ((float) width) : ((float) i12) / ((float) height);
                float f4 = i12 != 0 ? ((float) i12) / ((float) height) : ((float) i11) / ((float) width);
                if (f3 > f4) {
                    int ceil = (int) Math.ceil((double) (((float) height) * (f4 / f3)));
                    if ((yVar.f2603k & 48) == 48) {
                        i8 = 0;
                    } else if ((yVar.f2603k & 80) == 80) {
                        i8 = height - ceil;
                    } else {
                        i8 = (height - ceil) / 2;
                    }
                    i10 = i8;
                    f = ((float) i12) / ((float) ceil);
                    f4 = f3;
                    i2 = ceil;
                    i6 = width;
                } else if (f3 < f4) {
                    i6 = (int) Math.ceil((double) (((float) width) * (f3 / f4)));
                    if ((yVar.f2603k & 3) == 3) {
                        i7 = 0;
                    } else if ((yVar.f2603k & 5) == 5) {
                        i7 = width - i6;
                    } else {
                        i7 = (width - i6) / 2;
                    }
                    i9 = i7;
                    f = f4;
                    f4 = ((float) i11) / ((float) i6);
                    i2 = height;
                } else {
                    i2 = height;
                    i6 = width;
                    i9 = 0;
                    f = f4;
                }
                if (m3300a(z, width, height, i11, i12)) {
                    matrix.preScale(f4, f);
                }
                i3 = i6;
                i4 = i10;
                i5 = i9;
            } else if (yVar.f2604l) {
                float f5 = i11 != 0 ? ((float) i11) / ((float) width) : ((float) i12) / ((float) height);
                float f6 = i12 != 0 ? ((float) i12) / ((float) height) : ((float) i11) / ((float) width);
                if (f5 >= f6) {
                    f5 = f6;
                }
                float f7 = 1.0f;
                float f8 = 1.0f;
                int a2 = m3294a(i);
                if (a2 == 90 || a2 == 270) {
                    f8 = (((float) Math.round((((float) height) * f5) / 2.0f)) * 2.0f) / (((float) height) * f5);
                } else {
                    f7 = (((float) Math.round((((float) width) * f5) / 2.0f)) * 2.0f) / (((float) width) * f5);
                }
                if (m3300a(z, width, height, i11, i12)) {
                    matrix.preScale(f5 * f7, f5 * f8);
                }
                i2 = height;
                i3 = width;
                i4 = 0;
                i5 = 0;
            } else if (!((i11 == 0 && i12 == 0) || (i11 == width && i12 == height))) {
                float f9 = i11 != 0 ? ((float) i11) / ((float) width) : ((float) i12) / ((float) height);
                float f10 = i12 != 0 ? ((float) i12) / ((float) height) : ((float) i11) / ((float) width);
                if (m3300a(z, width, height, i11, i12)) {
                    matrix.preScale(f9, f10);
                }
            }
            createBitmap = Bitmap.createBitmap(bitmap, i5, i4, i3, i2, matrix, true);
            if (createBitmap != bitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createBitmap;
        }
        i2 = height;
        i3 = width;
        i4 = 0;
        i5 = 0;
        createBitmap = Bitmap.createBitmap(bitmap, i5, i4, i3, i2, matrix, true);
        if (createBitmap != bitmap) {
        }
    }

    /* renamed from: a */
    private static boolean m3300a(boolean z, int i, int i2, int i3, int i4) {
        return !z || (i3 != 0 && i > i3) || (i4 != 0 && i2 > i4);
    }

    /* renamed from: a */
    static int m3294a(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    /* renamed from: b */
    static int m3301b(int i) {
        switch (i) {
            case 2:
            case 4:
            case 5:
            case 7:
                return -1;
            default:
                return 1;
        }
    }
}
