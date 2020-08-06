package com.squareup.picasso;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import p033d.Okio;

/* renamed from: com.squareup.picasso.b */
class AssetRequestHandler extends RequestHandler {

    /* renamed from: a */
    private static final int f2466a = "file:///android_asset/".length();

    /* renamed from: b */
    private final Context f2467b;

    /* renamed from: c */
    private final Object f2468c = new Object();

    /* renamed from: d */
    private AssetManager f2469d;

    public AssetRequestHandler(Context context) {
        this.f2467b = context;
    }

    /* renamed from: a */
    public boolean mo17477a(C1645y yVar) {
        Uri uri = yVar.f2596d;
        if (!"file".equals(uri.getScheme()) || uri.getPathSegments().isEmpty() || !"android_asset".equals(uri.getPathSegments().get(0))) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public RequestHandler.C1612a mo17476a(C1645y yVar, int i) {
        if (this.f2469d == null) {
            synchronized (this.f2468c) {
                if (this.f2469d == null) {
                    this.f2469d = this.f2467b.getAssets();
                }
            }
        }
        return new RequestHandler.C1612a(Okio.m3596a(this.f2469d.open(m3291b(yVar))), Picasso.C1640d.DISK);
    }

    /* renamed from: b */
    static String m3291b(C1645y yVar) {
        return yVar.f2596d.toString().substring(f2466a);
    }
}
