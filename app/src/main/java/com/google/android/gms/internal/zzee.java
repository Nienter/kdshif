package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zzer;

@zzmb
public final class zzee extends zzer.zza {
    private final AppEventListener zzzq;

    public zzee(AppEventListener appEventListener) {
        this.zzzq = appEventListener;
    }

    public void onAppEvent(String str, String str2) {
        this.zzzq.onAppEvent(str, str2);
    }
}
