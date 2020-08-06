package com.snaperfect.style.daguerre.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.snaperfect.style.daguerre.utils.ScreenInfo;

/* renamed from: com.snaperfect.style.daguerre.adapter.b */
public class ImageViewPopupHelper {

    /* renamed from: a */
    private Activity f1901a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Drawable f1902b;

    /* renamed from: c */
    private int f1903c;

    /* renamed from: d */
    private int f1904d;

    /* renamed from: e */
    private boolean f1905e;

    /* renamed from: f */
    private ImageView f1906f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Dialog f1907g;

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2635a(Drawable drawable, boolean z, boolean z2) {
        this.f1902b = drawable;
        int intrinsicWidth = this.f1902b.getIntrinsicWidth();
        int intrinsicHeight = this.f1902b.getIntrinsicHeight();
        Point i = ScreenInfo.m3112a(this.f1901a).mo17167i();
        int i2 = i.x;
        int i3 = i.y;
        while (z && (intrinsicWidth >= i2 || intrinsicHeight >= i3)) {
            intrinsicWidth = (int) (((double) intrinsicWidth) * 0.9d);
            intrinsicHeight = (int) (((double) intrinsicHeight) * 0.9d);
            this.f1905e = true;
        }
        while (z2 && ((double) intrinsicWidth) * 1.1d <= ((double) i2) && ((double) intrinsicHeight) * 1.1d <= ((double) i3)) {
            intrinsicWidth = (int) (((double) intrinsicWidth) * 1.1d);
            intrinsicHeight = (int) (((double) intrinsicHeight) * 1.1d);
            this.f1905e = true;
        }
        this.f1903c = intrinsicWidth;
        this.f1904d = intrinsicHeight;
        if (this.f1905e) {
            this.f1906f.setBackgroundDrawable(new BitmapDrawable(this.f1901a.getResources(), Bitmap.createScaledBitmap(m2631a(this.f1902b), this.f1903c, this.f1904d, false)));
            return;
        }
        this.f1906f.setBackgroundDrawable(this.f1902b);
    }

    /* renamed from: a */
    public static void m2633a(Activity activity, ImageView imageView) {
        new ImageViewPopupHelper().m2638b(activity, imageView);
    }

    /* renamed from: a */
    private void m2634a(Activity activity, ImageView imageView, final Drawable drawable) {
        this.f1901a = activity;
        this.f1906f = new ImageView(activity);
        this.f1907g = new Dialog(activity);
        this.f1907g.requestWindowFeature(1);
        this.f1907g.setContentView(this.f1906f);
        this.f1907g.getWindow().setBackgroundDrawable(null);
        this.f1907g.setCanceledOnTouchOutside(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (drawable == null) {
                    ImageView imageView = (ImageView) view;
                    if (ImageViewPopupHelper.this.f1902b != imageView.getDrawable()) {
                        ImageViewPopupHelper.this.m2635a(imageView.getDrawable(), true, true);
                    }
                } else if (ImageViewPopupHelper.this.f1902b != drawable) {
                    ImageViewPopupHelper.this.m2635a(drawable, true, true);
                }
                ImageViewPopupHelper.this.f1907g.show();
            }
        });
    }

    /* renamed from: b */
    private void m2638b(Activity activity, ImageView imageView) {
        m2634a(activity, imageView, (Drawable) null);
    }

    /* renamed from: a */
    private Bitmap m2631a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }
}
