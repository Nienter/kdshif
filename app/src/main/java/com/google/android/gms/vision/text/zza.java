package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.p004v7.widget.ActivityChooserView;
import com.google.android.gms.internal.zzbhg;

final class zza {
    static Rect zza(Text text) {
        int i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Point point : text.getCornerPoints()) {
            i4 = Math.min(i4, point.x);
            i3 = Math.max(i3, point.x);
            i = Math.min(i, point.y);
            i2 = Math.max(i2, point.y);
        }
        return new Rect(i4, i, i3, i2);
    }

    static Point[] zza(zzbhg zzbhg) {
        Point[] pointArr = new Point[4];
        double sin = Math.sin(Math.toRadians((double) zzbhg.zzbNA));
        double cos = Math.cos(Math.toRadians((double) zzbhg.zzbNA));
        pointArr[0] = new Point(zzbhg.left, zzbhg.top);
        pointArr[1] = new Point((int) (((double) zzbhg.left) + (((double) zzbhg.width) * cos)), (int) (((double) zzbhg.top) + (((double) zzbhg.width) * sin)));
        pointArr[2] = new Point((int) (((double) pointArr[1].x) - (sin * ((double) zzbhg.height))), (int) ((cos * ((double) zzbhg.height)) + ((double) pointArr[1].y)));
        pointArr[3] = new Point(pointArr[0].x + (pointArr[2].x - pointArr[1].x), pointArr[0].y + (pointArr[2].y - pointArr[1].y));
        return pointArr;
    }
}
