package com.google.android.gms.internal;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public class zzbva extends CustomTabsServiceConnection {
    private WeakReference<zzbvb> zzcsV;

    public zzbva(zzbvb zzbvb) {
        this.zzcsV = new WeakReference<>(zzbvb);
    }

    public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbvb zzbvb = (zzbvb) this.zzcsV.get();
        if (zzbvb != null) {
            zzbvb.zza(customTabsClient);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzbvb zzbvb = (zzbvb) this.zzcsV.get();
        if (zzbvb != null) {
            zzbvb.zzfD();
        }
    }
}
