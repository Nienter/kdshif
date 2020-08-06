package com.squareup.picasso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import java.io.File;
import p018c.Cache;
import p018c.Call;
import p018c.OkHttpClient;
import p018c.Request;
import p018c.Response;

/* renamed from: com.squareup.picasso.u */
public final class OkHttp3Downloader implements Downloader {
    @VisibleForTesting

    /* renamed from: a */
    final Call.C0527a f2546a;

    /* renamed from: b */
    private final Cache f2547b;

    /* renamed from: c */
    private boolean f2548c;

    public OkHttp3Downloader(Context context) {
        this(Utils.m3274a(context));
    }

    public OkHttp3Downloader(File file) {
        this(file, Utils.m3272a(file));
    }

    public OkHttp3Downloader(File file, long j) {
        this(new OkHttpClient.C0539a().mo9010a(new Cache(file, j)).mo9011a());
        this.f2548c = false;
    }

    public OkHttp3Downloader(OkHttpClient uVar) {
        this.f2548c = true;
        this.f2546a = uVar;
        this.f2547b = uVar.mo8992g();
    }

    @NonNull
    /* renamed from: a */
    public Response mo17557a(@NonNull Request xVar) {
        return this.f2546a.mo8881a(xVar).mo8880a();
    }
}
