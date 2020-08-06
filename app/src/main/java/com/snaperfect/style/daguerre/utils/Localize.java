package com.snaperfect.style.daguerre.utils;

import android.content.Context;
import android.content.res.Resources;
import java.util.Formatter;
import java.util.Locale;

/* renamed from: com.snaperfect.style.daguerre.utils.h */
public class Localize {
    /* renamed from: a */
    public static String m3097a(Context context, int i, Object... objArr) {
        String[] strArr = new String[objArr.length];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= objArr.length) {
                return new Formatter(Locale.US).format(context.getString(i), strArr).toString();
            }
            if (objArr[i3] instanceof Integer) {
                try {
                    strArr[i3] = context.getString(objArr[i3].intValue());
                } catch (Resources.NotFoundException e) {
                    strArr[i3] = objArr[i3];
                }
            } else {
                strArr[i3] = objArr[i3];
            }
            i2 = i3 + 1;
        }
    }

    /* renamed from: a */
    public static String m3098a(String str, Object... objArr) {
        return new Formatter(Locale.US).format(str, objArr).toString();
    }
}
