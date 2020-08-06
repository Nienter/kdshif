package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.squareup.picasso.Picasso;
import p033d.Source;

/* renamed from: com.squareup.picasso.aa */
public abstract class RequestHandler {

    /* renamed from: com.squareup.picasso.aa$a */
    /* compiled from: RequestHandler */
    public static final class C1612a {

        /* renamed from: a */
        private final Picasso.C1640d f2427a;

        /* renamed from: b */
        private final Bitmap f2428b;

        /* renamed from: c */
        private final Source f2429c;

        /* renamed from: d */
        private final int f2430d;

        public C1612a(@NonNull Bitmap bitmap, @NonNull Picasso.C1640d dVar) {
            this((Bitmap) Utils.m3276a(bitmap, "bitmap == null"), null, dVar, 0);
        }

        public C1612a(@NonNull Source sVar, @NonNull Picasso.C1640d dVar) {
            this(null, (Source) Utils.m3276a(sVar, "source == null"), dVar, 0);
        }

        C1612a(@Nullable Bitmap bitmap, @Nullable Source sVar, @NonNull Picasso.C1640d dVar, int i) {
            boolean z;
            boolean z2 = true;
            if (bitmap != null) {
                z = true;
            } else {
                z = false;
            }
            if (z == (sVar == null ? false : z2)) {
                throw new AssertionError();
            }
            this.f2428b = bitmap;
            this.f2429c = sVar;
            this.f2427a = (Picasso.C1640d) Utils.m3276a(dVar, "loadedFrom == null");
            this.f2430d = i;
        }

        @Nullable
        /* renamed from: a */
        public Bitmap mo17480a() {
            return this.f2428b;
        }

        @Nullable
        /* renamed from: b */
        public Source mo17481b() {
            return this.f2429c;
        }

        @NonNull
        /* renamed from: c */
        public Picasso.C1640d mo17482c() {
            return this.f2427a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public int mo17483d() {
            return this.f2430d;
        }
    }

    @Nullable
    /* renamed from: a */
    public abstract C1612a mo17476a(C1645y yVar, int i);

    /* renamed from: a */
    public abstract boolean mo17477a(C1645y yVar);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo17475a() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo17478a(boolean z, NetworkInfo networkInfo) {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo17479b() {
        return false;
    }

    /* renamed from: c */
    static BitmapFactory.Options m3236c(C1645y yVar) {
        boolean d = yVar.mo17601d();
        boolean z = yVar.f2611s != null;
        BitmapFactory.Options options = null;
        if (d || z || yVar.f2610r) {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = d;
            options.inInputShareable = yVar.f2610r;
            options.inPurgeable = yVar.f2610r;
            if (z) {
                options.inPreferredConfig = yVar.f2611s;
            }
        }
        return options;
    }

    /* renamed from: a */
    static boolean m3235a(BitmapFactory.Options options) {
        return options != null && options.inJustDecodeBounds;
    }

    /* renamed from: a */
    static void m3234a(int i, int i2, BitmapFactory.Options options, C1645y yVar) {
        m3233a(i, i2, options.outWidth, options.outHeight, options, yVar);
    }

    /* renamed from: a */
    static void m3233a(int i, int i2, int i3, int i4, BitmapFactory.Options options, C1645y yVar) {
        int i5 = 1;
        if (i4 > i2 || i3 > i) {
            if (i2 == 0) {
                i5 = (int) Math.floor((double) (((float) i3) / ((float) i)));
            } else if (i == 0) {
                i5 = (int) Math.floor((double) (((float) i4) / ((float) i2)));
            } else {
                int floor = (int) Math.floor((double) (((float) i4) / ((float) i2)));
                int floor2 = (int) Math.floor((double) (((float) i3) / ((float) i)));
                if (yVar.f2604l) {
                    i5 = Math.max(floor, floor2);
                } else {
                    i5 = Math.min(floor, floor2);
                }
            }
        }
        options.inSampleSize = i5;
        options.inJustDecodeBounds = false;
    }
}
