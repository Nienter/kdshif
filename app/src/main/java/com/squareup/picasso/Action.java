package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* renamed from: com.squareup.picasso.a */
abstract class Action<T> {

    /* renamed from: a */
    final Picasso f2414a;

    /* renamed from: b */
    final C1645y f2415b;

    /* renamed from: c */
    final WeakReference<T> f2416c;

    /* renamed from: d */
    final boolean f2417d;

    /* renamed from: e */
    final int f2418e;

    /* renamed from: f */
    final int f2419f;

    /* renamed from: g */
    final int f2420g;

    /* renamed from: h */
    final Drawable f2421h;

    /* renamed from: i */
    final String f2422i;

    /* renamed from: j */
    final Object f2423j;

    /* renamed from: k */
    boolean f2424k;

    /* renamed from: l */
    boolean f2425l;

    /* renamed from: com.squareup.picasso.a$a */
    /* compiled from: Action */
    static class C1611a<M> extends WeakReference<M> {

        /* renamed from: a */
        final Action f2426a;

        public C1611a(Action aVar, M m, ReferenceQueue<? super M> referenceQueue) {
            super(m, referenceQueue);
            this.f2426a = aVar;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract void mo17463a(Bitmap bitmap, Picasso.C1640d dVar);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract void mo17464a(Exception exc);

    Action(Picasso vVar, T t, C1645y yVar, int i, int i2, int i3, Drawable drawable, String str, Object obj, boolean z) {
        this.f2414a = vVar;
        this.f2415b = yVar;
        this.f2416c = t == null ? null : new C1611a(this, t, vVar.f2557i);
        this.f2418e = i;
        this.f2419f = i2;
        this.f2417d = z;
        this.f2420g = i3;
        this.f2421h = drawable;
        this.f2422i = str;
        this.f2423j = obj == null ? this : obj;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17462a() {
        this.f2425l = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C1645y mo17465b() {
        return this.f2415b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public T mo17466c() {
        if (this.f2416c == null) {
            return null;
        }
        return this.f2416c.get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public String mo17467d() {
        return this.f2422i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo17468e() {
        return this.f2425l;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean mo17469f() {
        return this.f2424k;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public int mo17470g() {
        return this.f2418e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public int mo17471h() {
        return this.f2419f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public Picasso mo17472i() {
        return this.f2414a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public Picasso.C1641e mo17473j() {
        return this.f2415b.f2612t;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public Object mo17474k() {
        return this.f2423j;
    }
}
