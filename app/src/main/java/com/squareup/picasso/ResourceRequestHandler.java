package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;

/* renamed from: com.squareup.picasso.ab */
class ResourceRequestHandler extends RequestHandler {

    /* renamed from: a */
    private final Context f2431a;

    ResourceRequestHandler(Context context) {
        this.f2431a = context;
    }

    /* renamed from: a */
    public boolean mo17477a(C1645y yVar) {
        if (yVar.f2597e != 0) {
            return true;
        }
        return "android.resource".equals(yVar.f2596d.getScheme());
    }

    /* renamed from: a */
    public RequestHandler.C1612a mo17476a(C1645y yVar, int i) {
        Resources a = Utils.m3273a(this.f2431a, yVar);
        return new RequestHandler.C1612a(m3246a(a, Utils.m3270a(a, yVar), yVar), Picasso.C1640d.DISK);
    }

    /* renamed from: a */
    private static Bitmap m3246a(Resources resources, int i, C1645y yVar) {
        BitmapFactory.Options c = m3236c(yVar);
        if (m3235a(c)) {
            BitmapFactory.decodeResource(resources, i, c);
            m3234a(yVar.f2600h, yVar.f2601i, c, yVar);
        }
        return BitmapFactory.decodeResource(resources, i, c);
    }
}
