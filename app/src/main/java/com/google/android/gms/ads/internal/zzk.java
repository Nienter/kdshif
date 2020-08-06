package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.p001v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzgw;
import com.google.android.gms.internal.zzhj;
import com.google.android.gms.internal.zzhk;
import com.google.android.gms.internal.zzhl;
import com.google.android.gms.internal.zzhm;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzqa;

@zzmb
public class zzk extends zzen.zza {
    private final Context mContext;
    private final zzjs zzsD;
    private final zzd zzsz;
    private zzel zzti;
    private zzgw zztn;
    private zzet zztp;
    private final String zztq;
    private final zzqa zztr;
    private zzhj zztv;
    private zzhk zztw;
    private SimpleArrayMap<String, zzhl> zztx = new SimpleArrayMap<>();
    private SimpleArrayMap<String, zzhm> zzty = new SimpleArrayMap<>();

    public zzk(Context context, String str, zzjs zzjs, zzqa zzqa, zzd zzd) {
        this.mContext = context;
        this.zztq = str;
        this.zzsD = zzjs;
        this.zztr = zzqa;
        this.zzsz = zzd;
    }

    public void zza(zzgw zzgw) {
        this.zztn = zzgw;
    }

    public void zza(zzhj zzhj) {
        this.zztv = zzhj;
    }

    public void zza(zzhk zzhk) {
        this.zztw = zzhk;
    }

    public void zza(String str, zzhm zzhm, zzhl zzhl) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzty.put(str, zzhm);
        this.zztx.put(str, zzhl);
    }

    public void zzb(zzel zzel) {
        this.zzti = zzel;
    }

    public void zzb(zzet zzet) {
        this.zztp = zzet;
    }

    public zzem zzci() {
        return new zzj(this.mContext, this.zztq, this.zzsD, this.zztr, this.zzti, this.zztv, this.zztw, this.zzty, this.zztx, this.zztn, this.zztp, this.zzsz);
    }
}
