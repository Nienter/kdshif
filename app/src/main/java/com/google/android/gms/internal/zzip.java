package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzl;

@zzmb
public class zzip {
    private final Context mContext;
    private final zzjs zzsD;
    private final zzd zzsz;
    private final zzqa zztr;

    zzip(Context context, zzjs zzjs, zzqa zzqa, zzd zzd) {
        this.mContext = context;
        this.zzsD = zzjs;
        this.zztr = zzqa;
        this.zzsz = zzd;
    }

    public Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }

    public zzl zzag(String str) {
        return new zzl(this.mContext, new zzec(), str, this.zzsD, this.zztr, this.zzsz);
    }

    public zzl zzah(String str) {
        return new zzl(this.mContext.getApplicationContext(), new zzec(), str, this.zzsD, this.zztr, this.zzsz);
    }

    public zzip zzgi() {
        return new zzip(getApplicationContext(), this.zzsD, this.zztr, this.zzsz);
    }
}
