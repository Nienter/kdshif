package com.squareup.picasso;

import java.io.PrintWriter;

/* renamed from: com.squareup.picasso.ad */
public class StatsSnapshot {

    /* renamed from: a */
    public final int f2449a;

    /* renamed from: b */
    public final int f2450b;

    /* renamed from: c */
    public final long f2451c;

    /* renamed from: d */
    public final long f2452d;

    /* renamed from: e */
    public final long f2453e;

    /* renamed from: f */
    public final long f2454f;

    /* renamed from: g */
    public final long f2455g;

    /* renamed from: h */
    public final long f2456h;

    /* renamed from: i */
    public final long f2457i;

    /* renamed from: j */
    public final long f2458j;

    /* renamed from: k */
    public final int f2459k;

    /* renamed from: l */
    public final int f2460l;

    /* renamed from: m */
    public final int f2461m;

    /* renamed from: n */
    public final long f2462n;

    public StatsSnapshot(int i, int i2, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, int i3, int i4, int i5, long j9) {
        this.f2449a = i;
        this.f2450b = i2;
        this.f2451c = j;
        this.f2452d = j2;
        this.f2453e = j3;
        this.f2454f = j4;
        this.f2455g = j5;
        this.f2456h = j6;
        this.f2457i = j7;
        this.f2458j = j8;
        this.f2459k = i3;
        this.f2460l = i4;
        this.f2461m = i5;
        this.f2462n = j9;
    }

    /* renamed from: a */
    public void mo17497a(PrintWriter printWriter) {
        printWriter.println("===============BEGIN PICASSO STATS ===============");
        printWriter.println("Memory Cache Stats");
        printWriter.print("  Max Cache Size: ");
        printWriter.println(this.f2449a);
        printWriter.print("  Cache Size: ");
        printWriter.println(this.f2450b);
        printWriter.print("  Cache % Full: ");
        printWriter.println((int) Math.ceil((double) ((((float) this.f2450b) / ((float) this.f2449a)) * 100.0f)));
        printWriter.print("  Cache Hits: ");
        printWriter.println(this.f2451c);
        printWriter.print("  Cache Misses: ");
        printWriter.println(this.f2452d);
        printWriter.println("Network Stats");
        printWriter.print("  Download Count: ");
        printWriter.println(this.f2459k);
        printWriter.print("  Total Download Size: ");
        printWriter.println(this.f2453e);
        printWriter.print("  Average Download Size: ");
        printWriter.println(this.f2456h);
        printWriter.println("Bitmap Stats");
        printWriter.print("  Total Bitmaps Decoded: ");
        printWriter.println(this.f2460l);
        printWriter.print("  Total Bitmap Size: ");
        printWriter.println(this.f2454f);
        printWriter.print("  Total Transformed Bitmaps: ");
        printWriter.println(this.f2461m);
        printWriter.print("  Total Transformed Bitmap Size: ");
        printWriter.println(this.f2455g);
        printWriter.print("  Average Bitmap Size: ");
        printWriter.println(this.f2457i);
        printWriter.print("  Average Transformed Bitmap Size: ");
        printWriter.println(this.f2458j);
        printWriter.println("===============END PICASSO STATS ===============");
        printWriter.flush();
    }

    public String toString() {
        return "StatsSnapshot{maxSize=" + this.f2449a + ", size=" + this.f2450b + ", cacheHits=" + this.f2451c + ", cacheMisses=" + this.f2452d + ", downloadCount=" + this.f2459k + ", totalDownloadSize=" + this.f2453e + ", averageDownloadSize=" + this.f2456h + ", totalOriginalBitmapSize=" + this.f2454f + ", totalTransformedBitmapSize=" + this.f2455g + ", averageOriginalBitmapSize=" + this.f2457i + ", averageTransformedBitmapSize=" + this.f2458j + ", originalBitmapCount=" + this.f2460l + ", transformedBitmapCount=" + this.f2461m + ", timeStamp=" + this.f2462n + '}';
    }
}
