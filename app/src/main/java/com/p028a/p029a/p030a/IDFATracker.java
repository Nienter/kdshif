package com.p028a.p029a.p030a;

import android.content.Context;

/* renamed from: com.a.a.a.c */
public class IDFATracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1494a;

    public IDFATracker(Context context) {
        super("idfa");
        this.f1494a = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        String a = AdvertisingId.m1764a(this.f1494a);
        return a == null ? "" : a;
    }
}
