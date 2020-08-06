package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class zzbhf {
    public static Bitmap zzb(Bitmap bitmap, zzbhd zzbhd) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (zzbhd.rotation != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate((float) zznB(zzbhd.rotation));
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        }
        if (zzbhd.rotation == 1 || zzbhd.rotation == 3) {
            zzbhd.width = height;
            zzbhd.height = width;
        }
        return bitmap;
    }

    private static int zznB(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                throw new IllegalArgumentException("Unsupported rotation degree.");
        }
    }
}
