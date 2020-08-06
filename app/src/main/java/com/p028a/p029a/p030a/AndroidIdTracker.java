package com.p028a.p029a.p030a;

import android.content.Context;
import android.provider.Settings;

/* renamed from: com.a.a.a.db */
public class AndroidIdTracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1582a;

    public AndroidIdTracker(Context context) {
        super("android_id");
        this.f1582a = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        boolean z = false;
        try {
            return Settings.Secure.getString(this.f1582a.getContentResolver(), "android_id");
        } catch (Exception e) {
            return z;
        }
    }
}
