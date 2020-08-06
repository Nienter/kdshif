package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;

@zzmb
public class zzga {
    @Nullable
    public zzfz zza(@Nullable zzfy zzfy) {
        if (zzfy == null) {
            throw new IllegalArgumentException("CSI configuration can't be null. ");
        } else if (!zzfy.zzfp()) {
            zzpe.m2431v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
            return null;
        } else if (zzfy.getContext() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (!TextUtils.isEmpty(zzfy.zzdw())) {
            return new zzfz(zzfy.getContext(), zzfy.zzdw(), zzfy.zzfq(), zzfy.zzfr());
        } else {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
    }
}
