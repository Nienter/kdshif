package com.p028a.p029a.p030a;

import android.os.Build;

/* renamed from: com.a.a.a.k */
public class SerialTracker extends AbstractIdTracker {
    public SerialTracker() {
        super("serial");
    }

    /* renamed from: a */
    public String mo9458a() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return null;
    }
}
