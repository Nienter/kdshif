package com.p028a.p029a.p030a;

import android.content.Context;

/* renamed from: com.a.a.a.d */
public class IDMD5Tracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1576a;

    public IDMD5Tracker(Context context) {
        super("idmd5");
        this.f1576a = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        return DeviceConfig.m1798d(this.f1576a);
    }
}
