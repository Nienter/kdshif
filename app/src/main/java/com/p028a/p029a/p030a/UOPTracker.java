package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.a.a.a.l */
public class UOPTracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1620a;

    public UOPTracker(Context context) {
        super("uop");
        this.f1620a = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        SharedPreferences a = PreferenceWrapper.m1333a(this.f1620a);
        if (a != null) {
            return a.getString("uopdta", "");
        }
        return "";
    }
}
