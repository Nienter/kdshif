package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.internal.zzhl;

@zzmb
public class zzhq extends zzhl.zza {
    private final NativeCustomTemplateAd.OnCustomClickListener zzHa;

    public zzhq(NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
        this.zzHa = onCustomClickListener;
    }

    public void zza(zzhh zzhh, String str) {
        this.zzHa.onCustomClick(new zzhi(zzhh), str);
    }
}
