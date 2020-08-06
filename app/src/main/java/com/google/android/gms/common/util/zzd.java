package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzacx;

public class zzd {
    public static int zza(PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return -1;
        }
        Bundle bundle = packageInfo.applicationInfo.metaData;
        if (bundle != null) {
            return bundle.getInt("com.google.android.gms.version", -1);
        }
        return -1;
    }

    public static int zzv(Context context, String str) {
        return zza(zzw(context, str));
    }

    @Nullable
    public static PackageInfo zzw(Context context, String str) {
        try {
            return zzacx.zzaQ(context).getPackageInfo(str, 128);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @TargetApi(12)
    public static boolean zzx(Context context, String str) {
        if (!zzs.zzyy()) {
            return false;
        }
        "com.google.android.gms".equals(str);
        try {
            return (zzacx.zzaQ(context).getApplicationInfo(str, 0).flags & 2097152) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
