package com.snaperfect.style.daguerre.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.MediaStore;
import android.widget.BaseAdapter;

/* renamed from: com.snaperfect.style.daguerre.utils.a */
public class AlbumManager {

    /* renamed from: a */
    private ContentResolver f2250a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public BaseAdapter f2251b;

    /* renamed from: c */
    private C1584a f2252c = new C1584a(new Handler());

    /* renamed from: d */
    private Cursor f2253d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final int f2254e;

    /* renamed from: f */
    private final int f2255f;

    /* renamed from: g */
    private final int f2256g;

    /* renamed from: h */
    private int f2257h;

    /* renamed from: i */
    private int f2258i;

    /* renamed from: j */
    private int f2259j;

    /* renamed from: k */
    private int[] f2260k;

    /* renamed from: l */
    private boolean f2261l;

    /* renamed from: com.snaperfect.style.daguerre.utils.a$a */
    /* compiled from: AlbumManager */
    private class C1584a extends ContentObserver {
        public C1584a(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            AlbumManager.this.m3071c(AlbumManager.this.f2254e);
            AlbumManager.this.f2251b.notifyDataSetChanged();
        }
    }

    public AlbumManager(Context context, BaseAdapter baseAdapter, int i, int i2, int i3) {
        this.f2250a = context.getContentResolver();
        this.f2251b = baseAdapter;
        this.f2254e = i;
        this.f2255f = i2;
        this.f2256g = i3;
        m3071c(this.f2254e);
        this.f2250a.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.f2252c);
    }

    /* renamed from: a */
    public int mo17286a() {
        return this.f2258i + this.f2259j;
    }

    /* renamed from: b */
    public int mo17288b() {
        return this.f2257h;
    }

    /* renamed from: c */
    public int mo17290c() {
        return this.f2258i;
    }

    /* renamed from: d */
    public boolean mo17291d() {
        return this.f2261l;
    }

    /* renamed from: e */
    public void mo17292e() {
        this.f2261l = false;
    }

    /* renamed from: f */
    public void mo17293f() {
        if (this.f2253d != null && !this.f2253d.isClosed()) {
            this.f2253d.close();
        }
        this.f2250a.unregisterContentObserver(this.f2252c);
    }

    /* renamed from: a */
    private PhotoAsset[] m3069a(int i, int i2) {
        int i3 = i * i2;
        int min = Math.min(this.f2253d.getCount() - i3, i2);
        PhotoAsset[] photoAssetArr = new PhotoAsset[min];
        this.f2253d.move(i3 - this.f2253d.getPosition());
        for (int i4 = 0; i4 < min; i4++) {
            photoAssetArr[i4] = new PhotoAsset(this.f2253d.getString(this.f2260k[0]), this.f2253d.getString(this.f2260k[1]), this.f2253d.getString(this.f2260k[2]), this.f2253d.getString(this.f2260k[3]));
            if (!this.f2253d.moveToNext()) {
                break;
            }
        }
        return photoAssetArr;
    }

    /* renamed from: a */
    public PhotoAsset mo17287a(int i) {
        this.f2253d.moveToFirst();
        this.f2253d.move(i);
        return new PhotoAsset(this.f2253d.getString(this.f2260k[0]), this.f2253d.getString(this.f2260k[1]), this.f2253d.getString(this.f2260k[2]), this.f2253d.getString(this.f2260k[3]));
    }

    /* renamed from: b */
    public PhotoAsset[] mo17289b(int i) {
        return m3069a(i - this.f2258i, this.f2256g);
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m3071c(int i) {
        int i2 = 0;
        this.f2261l = true;
        if (this.f2253d != null && !this.f2253d.isClosed()) {
            this.f2253d.close();
        }
        String[] g = m3072g();
        this.f2253d = this.f2250a.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, g, null, null, "date_modified desc");
        if (this.f2253d != null) {
            this.f2260k = new int[g.length];
            for (int i3 = 0; i3 < g.length; i3++) {
                this.f2260k[i3] = this.f2253d.getColumnIndexOrThrow(g[i3]);
            }
            int count = this.f2253d.getCount();
            this.f2257h = Math.min(count, i);
            if (this.f2257h > 0) {
                i2 = 1;
            }
            this.f2258i = i2;
            this.f2259j = (int) Math.ceil((double) (((float) count) / ((float) this.f2256g)));
            return;
        }
        this.f2258i = 0;
        this.f2259j = 0;
    }

    /* renamed from: g */
    private String[] m3072g() {
        return new String[]{"_id", "_data", "_display_name", "datetaken"};
    }
}
