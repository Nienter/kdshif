package com.squareup.picasso;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* renamed from: com.squareup.picasso.ac */
class Stats {

    /* renamed from: a */
    final HandlerThread f2432a = new HandlerThread("Picasso-Stats", 10);

    /* renamed from: b */
    final C1624d f2433b;

    /* renamed from: c */
    final Handler f2434c;

    /* renamed from: d */
    long f2435d;

    /* renamed from: e */
    long f2436e;

    /* renamed from: f */
    long f2437f;

    /* renamed from: g */
    long f2438g;

    /* renamed from: h */
    long f2439h;

    /* renamed from: i */
    long f2440i;

    /* renamed from: j */
    long f2441j;

    /* renamed from: k */
    long f2442k;

    /* renamed from: l */
    int f2443l;

    /* renamed from: m */
    int f2444m;

    /* renamed from: n */
    int f2445n;

    /* renamed from: com.squareup.picasso.ac$a */
    /* compiled from: Stats */
    private static class C1613a extends Handler {

        /* renamed from: a */
        private final Stats f2446a;

        public C1613a(Looper looper, Stats acVar) {
            super(looper);
            this.f2446a = acVar;
        }

        public void handleMessage(final Message message) {
            switch (message.what) {
                case 0:
                    this.f2446a.mo17491c();
                    return;
                case 1:
                    this.f2446a.mo17493d();
                    return;
                case 2:
                    this.f2446a.mo17489b((long) message.arg1);
                    return;
                case 3:
                    this.f2446a.mo17492c((long) message.arg1);
                    return;
                case 4:
                    this.f2446a.mo17487a((Long) message.obj);
                    return;
                default:
                    Picasso.f2549a.post(new Runnable() {
                        public void run() {
                            throw new AssertionError("Unhandled stats message." + message.what);
                        }
                    });
                    return;
            }
        }
    }

    Stats(C1624d dVar) {
        this.f2433b = dVar;
        this.f2432a.start();
        Utils.m3282a(this.f2432a.getLooper());
        this.f2434c = new C1613a(this.f2432a.getLooper(), this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17486a(Bitmap bitmap) {
        m3250a(bitmap, 2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17490b(Bitmap bitmap) {
        m3250a(bitmap, 3);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17485a(long j) {
        this.f2434c.sendMessage(this.f2434c.obtainMessage(4, Long.valueOf(j)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17484a() {
        this.f2434c.sendEmptyMessage(0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17488b() {
        this.f2434c.sendEmptyMessage(1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo17491c() {
        this.f2435d++;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo17493d() {
        this.f2436e++;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17487a(Long l) {
        this.f2443l++;
        this.f2437f += l.longValue();
        this.f2440i = m3249a(this.f2443l, this.f2437f);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17489b(long j) {
        this.f2444m++;
        this.f2438g += j;
        this.f2441j = m3249a(this.f2444m, this.f2438g);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo17492c(long j) {
        this.f2445n++;
        this.f2439h += j;
        this.f2442k = m3249a(this.f2444m, this.f2439h);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public StatsSnapshot mo17494e() {
        return new StatsSnapshot(this.f2433b.mo17529b(), this.f2433b.mo17526a(), this.f2435d, this.f2436e, this.f2437f, this.f2438g, this.f2439h, this.f2440i, this.f2441j, this.f2442k, this.f2443l, this.f2444m, this.f2445n, System.currentTimeMillis());
    }

    /* renamed from: a */
    private void m3250a(Bitmap bitmap, int i) {
        this.f2434c.sendMessage(this.f2434c.obtainMessage(i, Utils.m3271a(bitmap), 0));
    }

    /* renamed from: a */
    private static long m3249a(int i, long j) {
        return j / ((long) i);
    }
}
