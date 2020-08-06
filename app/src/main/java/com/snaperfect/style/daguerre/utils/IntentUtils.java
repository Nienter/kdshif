package com.snaperfect.style.daguerre.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

/* renamed from: com.snaperfect.style.daguerre.utils.g */
public class IntentUtils {
    /* renamed from: a */
    public static void m3095a(Intent intent, Object... objArr) {
        Bundle bundleExtra = intent.getBundleExtra("WARP_BUNDLE");
        if (bundleExtra == null) {
            bundleExtra = new Bundle();
            intent.putExtra("WARP_BUNDLE", bundleExtra);
        }
        m3096a(bundleExtra, objArr);
    }

    /* renamed from: a */
    public static void m3096a(Bundle bundle, Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("must be key value pair");
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < objArr.length) {
                String str = objArr[i2];
                Integer num = objArr[i2 + 1];
                if (num instanceof Integer) {
                    bundle.putInt(str, num.intValue());
                } else if (num instanceof Float) {
                    bundle.putFloat(str, ((Float) num).floatValue());
                } else if (num instanceof String) {
                    bundle.putString(str, (String) num);
                } else if (num instanceof Parcelable) {
                    bundle.putByteArray(str, ParcelableUtil.m3101a((Parcelable) num));
                } else {
                    throw new IllegalArgumentException("value " + num + " not support yet");
                }
                i = i2 + 2;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public static int m3092a(Intent intent, String str, int i) {
        Bundle bundleExtra = intent.getBundleExtra("WARP_BUNDLE");
        if (bundleExtra != null) {
            return bundleExtra.getInt(str);
        }
        return i;
    }

    /* renamed from: a */
    public static <T> T m3093a(Intent intent, String str, Parcelable.Creator<T> creator) {
        return m3094a(intent.getBundleExtra("WARP_BUNDLE"), str, creator);
    }

    /* renamed from: a */
    public static <T> T m3094a(Bundle bundle, String str, Parcelable.Creator<T> creator) {
        if (bundle != null) {
            byte[] byteArray = bundle.getByteArray(str);
            if (byteArray != null) {
                return ParcelableUtil.m3100a(byteArray, creator);
            }
        }
        return null;
    }
}
