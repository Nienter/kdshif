package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.squareup.picasso.n */
public class LruCache implements C1624d {

    /* renamed from: b */
    final LinkedHashMap<String, Bitmap> f2526b;

    /* renamed from: c */
    private final int f2527c;

    /* renamed from: d */
    private int f2528d;

    /* renamed from: e */
    private int f2529e;

    /* renamed from: f */
    private int f2530f;

    /* renamed from: g */
    private int f2531g;

    /* renamed from: h */
    private int f2532h;

    public LruCache(@NonNull Context context) {
        this(Utils.m3286b(context));
    }

    public LruCache(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Max size must be positive.");
        }
        this.f2527c = i;
        this.f2526b = new LinkedHashMap<>(0, 0.75f, true);
    }

    /* renamed from: a */
    public Bitmap mo17527a(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            Bitmap bitmap = this.f2526b.get(str);
            if (bitmap != null) {
                this.f2531g++;
                return bitmap;
            }
            this.f2532h++;
            return null;
        }
    }

    /* renamed from: a */
    public void mo17528a(@NonNull String str, @NonNull Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || bitmap == null");
        }
        int a = Utils.m3271a(bitmap);
        if (a <= this.f2527c) {
            synchronized (this) {
                this.f2529e++;
                this.f2528d = a + this.f2528d;
                Bitmap bitmap2 = (Bitmap) this.f2526b.put(str, bitmap);
                if (bitmap2 != null) {
                    this.f2528d -= Utils.m3271a(bitmap2);
                }
            }
            m3373a(this.f2527c);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* renamed from: a */
    private void m3373a(int i) {
        while (true) {
            synchronized (this) {
                if (this.f2528d >= 0 && (!this.f2526b.isEmpty() || this.f2528d == 0)) {
                    if (this.f2528d > i && !this.f2526b.isEmpty()) {
                        Map.Entry next = this.f2526b.entrySet().iterator().next();
                        this.f2526b.remove((String) next.getKey());
                        this.f2528d -= Utils.m3271a((Bitmap) next.getValue());
                        this.f2530f++;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public final synchronized int mo17526a() {
        return this.f2528d;
    }

    /* renamed from: b */
    public final synchronized int mo17529b() {
        return this.f2527c;
    }
}
