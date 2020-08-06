package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.zzbhg;
import com.google.android.gms.internal.zzbhk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzbhk[] zzbNu;
    private List<Line> zzbNv;
    private String zzbNw;
    private Rect zzbNx;

    TextBlock(SparseArray<zzbhk> sparseArray) {
        this.zzbNu = new zzbhk[sparseArray.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzbNu.length) {
                this.zzbNu[i2] = sparseArray.valueAt(i2);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private static Point[] zza(int i, int i2, int i3, int i4, zzbhg zzbhg) {
        int i5 = zzbhg.left;
        int i6 = zzbhg.top;
        double sin = Math.sin(Math.toRadians((double) zzbhg.zzbNA));
        double cos = Math.cos(Math.toRadians((double) zzbhg.zzbNA));
        Point[] pointArr = {new Point(i, i2), new Point(i3, i2), new Point(i3, i4), new Point(i, i4)};
        for (int i7 = 0; i7 < 4; i7++) {
            int i8 = (int) ((((double) pointArr[i7].x) * sin) + (((double) pointArr[i7].y) * cos));
            pointArr[i7].x = (int) ((((double) pointArr[i7].x) * cos) - (((double) pointArr[i7].y) * sin));
            pointArr[i7].y = i8;
            pointArr[i7].offset(i5, i6);
        }
        return pointArr;
    }

    private static Point[] zza(zzbhg zzbhg, zzbhg zzbhg2) {
        double sin = Math.sin(Math.toRadians((double) zzbhg2.zzbNA));
        double cos = Math.cos(Math.toRadians((double) zzbhg2.zzbNA));
        Point[] pointArr = new Point[4];
        pointArr[0] = new Point(zzbhg.left, zzbhg.top);
        pointArr[0].offset(-zzbhg2.left, -zzbhg2.top);
        int i = (int) ((((double) pointArr[0].x) * cos) + (((double) pointArr[0].y) * sin));
        int i2 = (int) ((sin * ((double) (-pointArr[0].x))) + (cos * ((double) pointArr[0].y)));
        pointArr[0].x = i;
        pointArr[0].y = i2;
        pointArr[1] = new Point(zzbhg.width + i, i2);
        pointArr[2] = new Point(zzbhg.width + i, zzbhg.height + i2);
        pointArr[3] = new Point(i, i2 + zzbhg.height);
        return pointArr;
    }

    public Rect getBoundingBox() {
        if (this.zzbNx == null) {
            this.zzbNx = zza.zza((Text) this);
        }
        return this.zzbNx;
    }

    public List<? extends Text> getComponents() {
        return zzSt();
    }

    public Point[] getCornerPoints() {
        if (this.cornerPoints == null) {
            zzSs();
        }
        return this.cornerPoints;
    }

    public String getLanguage() {
        if (this.zzbNw != null) {
            return this.zzbNw;
        }
        HashMap hashMap = new HashMap();
        for (zzbhk zzbhk : this.zzbNu) {
            hashMap.put(zzbhk.zzbNw, Integer.valueOf((hashMap.containsKey(zzbhk.zzbNw) ? ((Integer) hashMap.get(zzbhk.zzbNw)).intValue() : 0) + 1));
        }
        this.zzbNw = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new Comparator<Map.Entry<String, Integer>>(this) {
            /* renamed from: zza */
            public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                return entry.getValue().compareTo(entry2.getValue());
            }
        })).getKey();
        if (this.zzbNw == null || this.zzbNw.isEmpty()) {
            this.zzbNw = "und";
        }
        return this.zzbNw;
    }

    public String getValue() {
        if (this.zzbNu.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(this.zzbNu[0].zzbNF);
        for (int i = 1; i < this.zzbNu.length; i++) {
            sb.append("\n");
            sb.append(this.zzbNu[i].zzbNF);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void zzSs() {
        if (this.zzbNu.length == 0) {
            this.cornerPoints = new Point[0];
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        for (zzbhk zzbhk : this.zzbNu) {
            Point[] zza = zza(zzbhk.zzbNC, this.zzbNu[0].zzbNC);
            int i5 = 0;
            while (i5 < 4) {
                Point point = zza[i5];
                int min = Math.min(i3, point.x);
                int max = Math.max(i2, point.x);
                int min2 = Math.min(i, point.y);
                i5++;
                i4 = Math.max(i4, point.y);
                i = min2;
                i2 = max;
                i3 = min;
            }
        }
        this.cornerPoints = zza(i3, i, i2, i4, this.zzbNu[0].zzbNC);
    }

    /* access modifiers changed from: package-private */
    public List<Line> zzSt() {
        if (this.zzbNu.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzbNv == null) {
            this.zzbNv = new ArrayList(this.zzbNu.length);
            for (zzbhk line : this.zzbNu) {
                this.zzbNv.add(new Line(line));
            }
        }
        return this.zzbNv;
    }
}
