package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.ImageView;
import com.squareup.picasso.C1645y;
import com.squareup.picasso.Picasso;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.squareup.picasso.z */
public class RequestCreator {

    /* renamed from: a */
    private static final AtomicInteger f2630a = new AtomicInteger();

    /* renamed from: b */
    private final Picasso f2631b;

    /* renamed from: c */
    private final C1645y.C1647a f2632c;

    /* renamed from: d */
    private boolean f2633d;

    /* renamed from: e */
    private boolean f2634e;

    /* renamed from: f */
    private boolean f2635f;

    /* renamed from: g */
    private int f2636g;

    /* renamed from: h */
    private int f2637h;

    /* renamed from: i */
    private int f2638i;

    /* renamed from: j */
    private int f2639j;

    /* renamed from: k */
    private Drawable f2640k;

    /* renamed from: l */
    private Drawable f2641l;

    /* renamed from: m */
    private Object f2642m;

    RequestCreator(Picasso vVar, Uri uri, int i) {
        this.f2635f = true;
        if (vVar.f2561m) {
            throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
        }
        this.f2631b = vVar;
        this.f2632c = new C1645y.C1647a(uri, i, vVar.f2558j);
    }

    @VisibleForTesting
    RequestCreator() {
        this.f2635f = true;
        this.f2631b = null;
        this.f2632c = new C1645y.C1647a(null, 0, null);
    }

    /* renamed from: a */
    public RequestCreator mo17614a(@DrawableRes int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Error image resource invalid.");
        } else if (this.f2641l != null) {
            throw new IllegalStateException("Error image already set.");
        } else {
            this.f2637h = i;
            return this;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public RequestCreator mo17613a() {
        this.f2634e = false;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public RequestCreator mo17620b() {
        this.f2642m = null;
        return this;
    }

    /* renamed from: a */
    public RequestCreator mo17615a(int i, int i2) {
        this.f2632c.mo17607a(i, i2);
        return this;
    }

    /* renamed from: c */
    public RequestCreator mo17621c() {
        this.f2632c.mo17606a(17);
        return this;
    }

    /* renamed from: d */
    public RequestCreator mo17622d() {
        this.f2632c.mo17611c();
        return this;
    }

    /* renamed from: a */
    public RequestCreator mo17616a(@NonNull Transformation agVar) {
        this.f2632c.mo17608a(agVar);
        return this;
    }

    /* renamed from: e */
    public Bitmap mo17623e() {
        long nanoTime = System.nanoTime();
        Utils.m3281a();
        if (this.f2634e) {
            throw new IllegalStateException("Fit cannot be used with get.");
        } else if (!this.f2632c.mo17609a()) {
            return null;
        } else {
            C1645y a = m3435a(nanoTime);
            return BitmapHunter.m3298a(this.f2631b, this.f2631b.f2552d, this.f2631b.f2553e, this.f2631b.f2554f, (Action) new GetAction(this.f2631b, a, this.f2638i, this.f2639j, this.f2642m, Utils.m3280a(a, new StringBuilder()))).mo17502a();
        }
    }

    /* renamed from: a */
    public void mo17619a(@NonNull Target aeVar) {
        Drawable drawable = null;
        long nanoTime = System.nanoTime();
        Utils.m3287b();
        if (aeVar == null) {
            throw new IllegalArgumentException("Target must not be null.");
        } else if (this.f2634e) {
            throw new IllegalStateException("Fit cannot be used with a Target.");
        } else if (!this.f2632c.mo17609a()) {
            this.f2631b.mo17579a(aeVar);
            if (this.f2635f) {
                drawable = m3436f();
            }
            aeVar.mo17281a(drawable);
        } else {
            C1645y a = m3435a(nanoTime);
            String a2 = Utils.m3279a(a);
            if (MemoryPolicy.shouldReadFromMemoryCache(this.f2638i)) {
                Bitmap a3 = this.f2631b.mo17570a(a2);
                if (a3 != null) {
                    this.f2631b.mo17579a(aeVar);
                    aeVar.mo17280a(a3, Picasso.C1640d.MEMORY);
                    return;
                }
            }
            if (this.f2635f) {
                drawable = m3436f();
            }
            aeVar.mo17281a(drawable);
            this.f2631b.mo17578a((Action) new TargetAction(this.f2631b, aeVar, a, this.f2638i, this.f2639j, this.f2641l, a2, this.f2642m, this.f2637h));
        }
    }

    /* renamed from: a */
    public void mo17617a(ImageView imageView) {
        mo17618a(imageView, (C1626e) null);
    }

    /* renamed from: a */
    public void mo17618a(ImageView imageView, C1626e eVar) {
        long nanoTime = System.nanoTime();
        Utils.m3287b();
        if (imageView == null) {
            throw new IllegalArgumentException("Target must not be null.");
        } else if (!this.f2632c.mo17609a()) {
            this.f2631b.mo17576a(imageView);
            if (this.f2635f) {
                PicassoDrawable.m3417a(imageView, m3436f());
            }
        } else {
            if (this.f2634e) {
                if (this.f2632c.mo17610b()) {
                    throw new IllegalStateException("Fit cannot be used with resize.");
                }
                int width = imageView.getWidth();
                int height = imageView.getHeight();
                if (width == 0 || height == 0 || imageView.isLayoutRequested()) {
                    if (this.f2635f) {
                        PicassoDrawable.m3417a(imageView, m3436f());
                    }
                    this.f2631b.mo17577a(imageView, new DeferredRequestCreator(this, imageView, eVar));
                    return;
                }
                this.f2632c.mo17607a(width, height);
            }
            C1645y a = m3435a(nanoTime);
            String a2 = Utils.m3279a(a);
            if (MemoryPolicy.shouldReadFromMemoryCache(this.f2638i)) {
                Bitmap a3 = this.f2631b.mo17570a(a2);
                if (a3 != null) {
                    this.f2631b.mo17576a(imageView);
                    PicassoDrawable.m3416a(imageView, this.f2631b.f2551c, a3, Picasso.C1640d.MEMORY, this.f2633d, this.f2631b.f2559k);
                    if (this.f2631b.f2560l) {
                        Utils.m3284a("Main", "completed", a.mo17599b(), "from " + Picasso.C1640d.MEMORY);
                    }
                    if (eVar != null) {
                        eVar.mo16960a();
                        return;
                    }
                    return;
                }
            }
            if (this.f2635f) {
                PicassoDrawable.m3417a(imageView, m3436f());
            }
            this.f2631b.mo17578a((Action) new ImageViewAction(this.f2631b, imageView, a, this.f2638i, this.f2639j, this.f2637h, this.f2641l, a2, this.f2642m, eVar, this.f2633d));
        }
    }

    /* renamed from: f */
    private Drawable m3436f() {
        if (this.f2636g != 0) {
            return this.f2631b.f2551c.getResources().getDrawable(this.f2636g);
        }
        return this.f2640k;
    }

    /* renamed from: a */
    private C1645y m3435a(long j) {
        int andIncrement = f2630a.getAndIncrement();
        C1645y d = this.f2632c.mo17612d();
        d.f2593a = andIncrement;
        d.f2594b = j;
        boolean z = this.f2631b.f2560l;
        if (z) {
            Utils.m3284a("Main", "created", d.mo17599b(), d.toString());
        }
        C1645y a = this.f2631b.mo17571a(d);
        if (a != d) {
            a.f2593a = andIncrement;
            a.f2594b = j;
            if (z) {
                Utils.m3284a("Main", "changed", a.mo17598a(), "into " + a);
            }
        }
        return a;
    }
}
