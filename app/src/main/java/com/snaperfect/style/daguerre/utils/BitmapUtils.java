package com.snaperfect.style.daguerre.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.support.media.ExifInterface;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.math.CGRect;
import com.snaperfect.style.daguerre.math.CGSize;

/* renamed from: com.snaperfect.style.daguerre.utils.c */
public class BitmapUtils {

    /* renamed from: a */
    static final String[] f2263a = {ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_DATETIME, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_FLASH, ExifInterface.TAG_FOCAL_LENGTH, ExifInterface.TAG_GPS_ALTITUDE, ExifInterface.TAG_GPS_ALTITUDE_REF, ExifInterface.TAG_GPS_DATESTAMP, ExifInterface.TAG_GPS_LATITUDE, ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_LATITUDE_REF, ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_LONGITUDE_REF, ExifInterface.TAG_GPS_PROCESSING_METHOD, ExifInterface.TAG_GPS_TIMESTAMP, ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.TAG_ISO_SPEED_RATINGS, ExifInterface.TAG_MODEL, ExifInterface.TAG_MAKE, ExifInterface.TAG_ORIENTATION, ExifInterface.TAG_WHITE_BALANCE};

    /* renamed from: a */
    public static Bitmap m3083a(Bitmap bitmap, CGRect cGRect) {
        return Bitmap.createBitmap(bitmap, (int) cGRect.f2098a, (int) cGRect.f2099b, (int) cGRect.f2100c, (int) cGRect.f2101d);
    }

    /* renamed from: a */
    public static Bitmap m3084a(Bitmap bitmap, CGRect cGRect, boolean z) {
        CGRect a = CGRect.m2852a(cGRect, new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight())).mo17133a();
        a.f2100c = ((float) Math.round(a.f2100c / 2.0f)) * 2.0f;
        if (a.f2098a + a.f2100c > ((float) bitmap.getWidth())) {
            a.f2100c = ((float) bitmap.getWidth()) - a.f2098a;
            int i = (int) a.f2100c;
            a.f2100c = (float) (i - (i % 2));
        }
        if (!z) {
            return m3083a(bitmap, a);
        }
        int i2 = (int) a.f2098a;
        int i3 = (int) a.f2099b;
        int i4 = (int) a.f2100c;
        int i5 = (int) a.f2101d;
        if (i2 == 0 && i3 == 0 && i4 == bitmap.getWidth() && i5 == bitmap.getHeight()) {
            return bitmap;
        }
        return m3083a(bitmap, a);
    }

    /* renamed from: a */
    public static Bitmap m3082a(Bitmap bitmap) {
        if (bitmap.getWidth() == bitmap.getHeight()) {
            return bitmap;
        }
        int max = Math.max(bitmap.getWidth(), bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(max, max, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        CGSize cGSize = new CGSize((float) max, (float) max);
        CGSize cGSize2 = new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight());
        cGSize2.mo17161d(cGSize);
        CGPoint cGPoint = new CGPoint(CGSize.m2872b(CGSize.m2871a(cGSize, cGSize2), 2.0f).mo17151a());
        canvas.drawBitmap(bitmap, null, new RectF(cGPoint.x, cGPoint.y, cGPoint.x + cGSize2.f2102a, cGSize2.f2103b + cGPoint.y), null);
        return createBitmap;
    }
}
