package com.p028a.p029a.p030a;

import android.content.Context;

/* renamed from: com.a.a.a.i */
public class NewUMIDTracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1616a;

    public NewUMIDTracker(Context context) {
        super("newumid");
        this.f1616a = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        return ImprintHandler.m2284a(this.f1616a).mo9583b().mo9600e(null);
    }
}
