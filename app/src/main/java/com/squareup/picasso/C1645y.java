package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.C0013Px;
import android.support.annotation.NonNull;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: com.squareup.picasso.y */
/* compiled from: Request */
public final class C1645y {

    /* renamed from: u */
    private static final long f2592u = TimeUnit.SECONDS.toNanos(5);

    /* renamed from: a */
    int f2593a;

    /* renamed from: b */
    long f2594b;

    /* renamed from: c */
    int f2595c;

    /* renamed from: d */
    public final Uri f2596d;

    /* renamed from: e */
    public final int f2597e;

    /* renamed from: f */
    public final String f2598f;

    /* renamed from: g */
    public final List<Transformation> f2599g;

    /* renamed from: h */
    public final int f2600h;

    /* renamed from: i */
    public final int f2601i;

    /* renamed from: j */
    public final boolean f2602j;

    /* renamed from: k */
    public final int f2603k;

    /* renamed from: l */
    public final boolean f2604l;

    /* renamed from: m */
    public final boolean f2605m;

    /* renamed from: n */
    public final float f2606n;

    /* renamed from: o */
    public final float f2607o;

    /* renamed from: p */
    public final float f2608p;

    /* renamed from: q */
    public final boolean f2609q;

    /* renamed from: r */
    public final boolean f2610r;

    /* renamed from: s */
    public final Bitmap.Config f2611s;

    /* renamed from: t */
    public final Picasso.C1641e f2612t;

    /* renamed from: com.squareup.picasso.y$a */
    /* compiled from: Request */
    public static final class C1647a {

        /* renamed from: a */
        private Uri f2613a;

        /* renamed from: b */
        private int f2614b;

        /* renamed from: c */
        private String f2615c;

        /* renamed from: d */
        private int f2616d;

        /* renamed from: e */
        private int f2617e;

        /* renamed from: f */
        private boolean f2618f;

        /* renamed from: g */
        private int f2619g;

        /* renamed from: h */
        private boolean f2620h;

        /* renamed from: i */
        private boolean f2621i;

        /* renamed from: j */
        private float f2622j;

        /* renamed from: k */
        private float f2623k;

        /* renamed from: l */
        private float f2624l;

        /* renamed from: m */
        private boolean f2625m;

        /* renamed from: n */
        private boolean f2626n;

        /* renamed from: o */
        private List<Transformation> f2627o;

        /* renamed from: p */
        private Bitmap.Config f2628p;

        /* renamed from: q */
        private Picasso.C1641e f2629q;

        C1647a(Uri uri, int i, Bitmap.Config config) {
            this.f2613a = uri;
            this.f2614b = i;
            this.f2628p = config;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo17609a() {
            return (this.f2613a == null && this.f2614b == 0) ? false : true;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public boolean mo17610b() {
            return (this.f2616d == 0 && this.f2617e == 0) ? false : true;
        }

        /* renamed from: a */
        public C1647a mo17607a(@C0013Px int i, @C0013Px int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Width must be positive number or 0.");
            } else if (i2 < 0) {
                throw new IllegalArgumentException("Height must be positive number or 0.");
            } else if (i2 == 0 && i == 0) {
                throw new IllegalArgumentException("At least one dimension has to be positive number.");
            } else {
                this.f2616d = i;
                this.f2617e = i2;
                return this;
            }
        }

        /* renamed from: a */
        public C1647a mo17606a(int i) {
            if (this.f2620h) {
                throw new IllegalStateException("Center crop can not be used after calling centerInside");
            }
            this.f2618f = true;
            this.f2619g = i;
            return this;
        }

        /* renamed from: c */
        public C1647a mo17611c() {
            if (this.f2618f) {
                throw new IllegalStateException("Center inside can not be used after calling centerCrop");
            }
            this.f2620h = true;
            return this;
        }

        /* renamed from: a */
        public C1647a mo17608a(@NonNull Transformation agVar) {
            if (agVar == null) {
                throw new IllegalArgumentException("Transformation must not be null.");
            } else if (agVar.mo17274a() == null) {
                throw new IllegalArgumentException("Transformation key must not be null.");
            } else {
                if (this.f2627o == null) {
                    this.f2627o = new ArrayList(2);
                }
                this.f2627o.add(agVar);
                return this;
            }
        }

        /* renamed from: d */
        public C1645y mo17612d() {
            if (this.f2620h && this.f2618f) {
                throw new IllegalStateException("Center crop and center inside can not be used together.");
            } else if (this.f2618f && this.f2616d == 0 && this.f2617e == 0) {
                throw new IllegalStateException("Center crop requires calling resize with positive width and height.");
            } else if (this.f2620h && this.f2616d == 0 && this.f2617e == 0) {
                throw new IllegalStateException("Center inside requires calling resize with positive width and height.");
            } else {
                if (this.f2629q == null) {
                    this.f2629q = Picasso.C1641e.NORMAL;
                }
                return new C1645y(this.f2613a, this.f2614b, this.f2615c, this.f2627o, this.f2616d, this.f2617e, this.f2618f, this.f2620h, this.f2619g, this.f2621i, this.f2622j, this.f2623k, this.f2624l, this.f2625m, this.f2626n, this.f2628p, this.f2629q);
            }
        }
    }

    private C1645y(Uri uri, int i, String str, List<Transformation> list, int i2, int i3, boolean z, boolean z2, int i4, boolean z3, float f, float f2, float f3, boolean z4, boolean z5, Bitmap.Config config, Picasso.C1641e eVar) {
        this.f2596d = uri;
        this.f2597e = i;
        this.f2598f = str;
        if (list == null) {
            this.f2599g = null;
        } else {
            this.f2599g = Collections.unmodifiableList(list);
        }
        this.f2600h = i2;
        this.f2601i = i3;
        this.f2602j = z;
        this.f2604l = z2;
        this.f2603k = i4;
        this.f2605m = z3;
        this.f2606n = f;
        this.f2607o = f2;
        this.f2608p = f3;
        this.f2609q = z4;
        this.f2610r = z5;
        this.f2611s = config;
        this.f2612t = eVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Request{");
        if (this.f2597e > 0) {
            sb.append(this.f2597e);
        } else {
            sb.append(this.f2596d);
        }
        if (this.f2599g != null && !this.f2599g.isEmpty()) {
            for (Transformation a : this.f2599g) {
                sb.append(' ').append(a.mo17274a());
            }
        }
        if (this.f2598f != null) {
            sb.append(" stableKey(").append(this.f2598f).append(')');
        }
        if (this.f2600h > 0) {
            sb.append(" resize(").append(this.f2600h).append(',').append(this.f2601i).append(')');
        }
        if (this.f2602j) {
            sb.append(" centerCrop");
        }
        if (this.f2604l) {
            sb.append(" centerInside");
        }
        if (this.f2606n != 0.0f) {
            sb.append(" rotation(").append(this.f2606n);
            if (this.f2609q) {
                sb.append(" @ ").append(this.f2607o).append(',').append(this.f2608p);
            }
            sb.append(')');
        }
        if (this.f2610r) {
            sb.append(" purgeable");
        }
        if (this.f2611s != null) {
            sb.append(' ').append(this.f2611s);
        }
        sb.append('}');
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo17598a() {
        long nanoTime = System.nanoTime() - this.f2594b;
        if (nanoTime > f2592u) {
            return mo17599b() + '+' + TimeUnit.NANOSECONDS.toSeconds(nanoTime) + 's';
        }
        return mo17599b() + '+' + TimeUnit.NANOSECONDS.toMillis(nanoTime) + "ms";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo17599b() {
        return "[R" + this.f2593a + ']';
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public String mo17600c() {
        if (this.f2596d != null) {
            return String.valueOf(this.f2596d.getPath());
        }
        return Integer.toHexString(this.f2597e);
    }

    /* renamed from: d */
    public boolean mo17601d() {
        return (this.f2600h == 0 && this.f2601i == 0) ? false : true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo17602e() {
        return mo17603f() || mo17604g();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean mo17603f() {
        return mo17601d() || this.f2606n != 0.0f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean mo17604g() {
        return this.f2599g != null;
    }
}
