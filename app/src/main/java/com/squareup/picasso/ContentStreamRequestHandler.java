package com.squareup.picasso;

import android.content.Context;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.InputStream;
import p033d.Okio;

/* renamed from: com.squareup.picasso.g */
class ContentStreamRequestHandler extends RequestHandler {

    /* renamed from: a */
    final Context f2501a;

    ContentStreamRequestHandler(Context context) {
        this.f2501a = context;
    }

    /* renamed from: a */
    public boolean mo17477a(C1645y yVar) {
        return "content".equals(yVar.f2596d.getScheme());
    }

    /* renamed from: a */
    public RequestHandler.C1612a mo17476a(C1645y yVar, int i) {
        return new RequestHandler.C1612a(Okio.m3596a(mo17530b(yVar)), Picasso.C1640d.DISK);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public InputStream mo17530b(C1645y yVar) {
        return this.f2501a.getContentResolver().openInputStream(yVar.f2596d);
    }
}
