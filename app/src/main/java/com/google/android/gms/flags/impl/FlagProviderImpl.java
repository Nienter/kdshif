package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.flags.impl.zza;
import com.google.android.gms.internal.zzapq;

@DynamiteApi
public class FlagProviderImpl extends zzapq.zza {
    private SharedPreferences zzAN;
    private boolean zztW = false;

    public boolean getBooleanFlagValue(String str, boolean z, int i) {
        return !this.zztW ? z : zza.C1702zza.zza(this.zzAN, str, Boolean.valueOf(z)).booleanValue();
    }

    public int getIntFlagValue(String str, int i, int i2) {
        return !this.zztW ? i : zza.zzb.zza(this.zzAN, str, Integer.valueOf(i)).intValue();
    }

    public long getLongFlagValue(String str, long j, int i) {
        return !this.zztW ? j : zza.zzc.zza(this.zzAN, str, Long.valueOf(j)).longValue();
    }

    public String getStringFlagValue(String str, String str2, int i) {
        return !this.zztW ? str2 : zza.zzd.zza(this.zzAN, str, str2);
    }

    public void init(zzd zzd) {
        Context context = (Context) zze.zzE(zzd);
        if (!this.zztW) {
            try {
                this.zzAN = zzb.zzm(context.createPackageContext("com.google.android.gms", 0));
                this.zztW = true;
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
    }
}
