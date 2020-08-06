package com.p028a.p029a.p030a;

import android.content.Context;

/* renamed from: com.a.a.a.h */
public class MacTracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1615a;

    public MacTracker(Context context) {
        super("mac");
        this.f1615a = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        boolean z = false;
        try {
            return DeviceConfig.m1810n(this.f1615a);
        } catch (Exception e) {
            return z;
        }
    }
}
