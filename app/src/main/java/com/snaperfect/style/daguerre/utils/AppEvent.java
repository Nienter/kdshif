package com.snaperfect.style.daguerre.utils;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.p028a.p029a.MobclickAgent;
import java.util.HashMap;

/* renamed from: com.snaperfect.style.daguerre.utils.b */
public class AppEvent {
    /* renamed from: a */
    public static void m3081a(Context context, FirebaseAnalytics firebaseAnalytics, String str, String... strArr) {
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("event params must be key value pair");
        } else if (strArr.length == 0) {
            MobclickAgent.m2411a(context, str);
            firebaseAnalytics.logEvent(str, null);
        } else {
            HashMap hashMap = new HashMap(strArr.length / 2);
            Bundle bundle = new Bundle();
            for (int i = 0; i < strArr.length; i += 2) {
                hashMap.put(strArr[i], strArr[i + 1]);
                bundle.putString(strArr[i], strArr[i + 1]);
            }
            MobclickAgent.m2412a(context, str, hashMap);
            firebaseAnalytics.logEvent(str, bundle);
        }
    }
}
