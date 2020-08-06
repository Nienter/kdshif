package p005b.p006a.p007a.p008a.p009a.p017g;

import android.content.Context;
import android.graphics.BitmapFactory;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;

/* renamed from: b.a.a.a.a.g.n */
public class IconRequest {

    /* renamed from: a */
    public final String f314a;

    /* renamed from: b */
    public final int f315b;

    /* renamed from: c */
    public final int f316c;

    /* renamed from: d */
    public final int f317d;

    public IconRequest(String str, int i, int i2, int i3) {
        this.f314a = str;
        this.f315b = i;
        this.f316c = i2;
        this.f317d = i3;
    }

    /* renamed from: a */
    public static IconRequest m418a(Context context, String str) {
        if (str != null) {
            try {
                int l = CommonUtils.m163l(context);
                Fabric.m456h().mo8506a("Fabric", "App icon resource ID is " + l);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), l, options);
                return new IconRequest(str, l, options.outWidth, options.outHeight);
            } catch (Exception e) {
                Fabric.m456h().mo8516e("Fabric", "Failed to load icon", e);
            }
        }
        return null;
    }
}
