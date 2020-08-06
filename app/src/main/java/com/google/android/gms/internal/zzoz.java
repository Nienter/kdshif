package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzv;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

@zzmb
public class zzoz {
    final String zzVW;
    int zzWo = -1;
    long zzWp = -1;
    long zzWq = -1;
    int zzWr = -1;
    int zzWs = 0;
    int zzWt = 0;
    private final Object zzrN = new Object();

    public zzoz(String str) {
        this.zzVW = str;
    }

    public static boolean zzx(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, AbstractSpiCall.ANDROID_CLIENT_TYPE);
        if (identifier == 0) {
            zzpe.zzbd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzpe.zzbd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            zzpe.zzbe("Fail to fetch AdActivity theme");
            zzpe.zzbd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    public void zzag(int i) {
        this.zzWo = i;
    }

    public void zzb(zzdy zzdy, long j) {
        synchronized (this.zzrN) {
            if (this.zzWq == -1) {
                if (j - zzv.zzcN().zzjV() > zzfx.zzCv.get().longValue()) {
                    zzag(-1);
                } else {
                    zzag(zzv.zzcN().zzjW());
                }
                this.zzWq = j;
                this.zzWp = this.zzWq;
            } else {
                this.zzWp = j;
            }
            if (zzdy.extras == null || zzdy.extras.getInt("gw", 2) != 1) {
                this.zzWr++;
                this.zzWo++;
            }
        }
    }

    public Bundle zze(Context context, String str) {
        Bundle bundle;
        synchronized (this.zzrN) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzVW);
            bundle.putLong("basets", this.zzWq);
            bundle.putLong("currts", this.zzWp);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzWr);
            bundle.putInt("preqs_in_session", this.zzWo);
            bundle.putInt("pclick", this.zzWs);
            bundle.putInt("pimp", this.zzWt);
            bundle.putBoolean("support_transparent_background", zzx(context));
        }
        return bundle;
    }

    public void zzjA() {
        synchronized (this.zzrN) {
            this.zzWs++;
        }
    }

    public int zzjW() {
        return this.zzWo;
    }

    public void zzjz() {
        synchronized (this.zzrN) {
            this.zzWt++;
        }
    }

    public long zzkd() {
        return this.zzWq;
    }
}
