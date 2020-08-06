package com.squareup.picasso;

import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import p033d.Okio;

/* renamed from: com.squareup.picasso.k */
class FileRequestHandler extends ContentStreamRequestHandler {
    FileRequestHandler(Context context) {
        super(context);
    }

    /* renamed from: a */
    public boolean mo17477a(C1645y yVar) {
        return "file".equals(yVar.f2596d.getScheme());
    }

    /* renamed from: a */
    public RequestHandler.C1612a mo17476a(C1645y yVar, int i) {
        return new RequestHandler.C1612a(null, Okio.m3596a(mo17530b(yVar)), Picasso.C1640d.DISK, m3365a(yVar.f2596d));
    }

    /* renamed from: a */
    static int m3365a(Uri uri) {
        return new ExifInterface(uri.getPath()).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1);
    }
}
