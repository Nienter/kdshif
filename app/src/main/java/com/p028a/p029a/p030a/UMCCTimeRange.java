package com.p028a.p029a.p030a;

import android.support.p001v4.view.PointerIconCompat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* renamed from: com.a.a.a.cu */
public class UMCCTimeRange {
    /* renamed from: a */
    public static String m2222a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return String.valueOf(((long) ((((instance.get(12) / 6) + 1) + (instance.get(11) * 10)) - 1)) + (m2224b(j) * 240));
    }

    /* renamed from: b */
    public static long m2224b(long j) {
        long j2 = 0;
        try {
            long time = new SimpleDateFormat("yyyy", Locale.getDefault()).parse("1970").getTime();
            long j3 = (j - time) / 86400000;
            if ((j - time) % 86400000 > 0) {
                j2 = 1;
            }
            return j2 + j3;
        } catch (Throwable th) {
            return 0;
        }
    }

    /* renamed from: c */
    public static long m2225c(long j) {
        return m2221a(j, (int) PointerIconCompat.TYPE_CONTEXT_MENU);
    }

    /* renamed from: d */
    public static long m2226d(long j) {
        return m2221a(j, (int) PointerIconCompat.TYPE_HAND);
    }

    /* renamed from: a */
    private static long m2221a(long j, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i2 = (instance.get(12) / 6) + 1 + (instance.get(11) * 10);
        int i3 = instance.get(13);
        int i4 = 0;
        if (i == 1002) {
            i4 = 360 - (((instance.get(12) % 6) * 60) + i3);
        } else if (i == 1001) {
            i4 = 60 - (i3 % 60);
            if (i2 % 6 == 0) {
                i4 += 60;
            }
        }
        return (long) (i4 * 1000);
    }

    /* renamed from: a */
    public static boolean m2223a(long j, long j2) {
        if (m2227e(j) == m2227e(j2)) {
            return true;
        }
        return false;
    }

    /* renamed from: e */
    private static int m2227e(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(5);
    }
}
