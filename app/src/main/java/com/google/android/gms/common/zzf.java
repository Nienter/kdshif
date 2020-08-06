package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.zzd;
import com.google.android.gms.internal.zzacx;

public class zzf {
    private static zzf zzaxr;
    private final Context mContext;

    private zzf(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static zzf zzav(Context context) {
        zzac.zzw(context);
        synchronized (zzf.class) {
            if (zzaxr == null) {
                zzd.zzao(context);
                zzaxr = new zzf(context);
            }
        }
        return zzaxr;
    }

    /* access modifiers changed from: package-private */
    public zzd.zza zza(PackageInfo packageInfo, zzd.zza... zzaArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzd.zzb zzb = new zzd.zzb(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzaArr.length; i++) {
            if (zzaArr[i].equals(zzb)) {
                return zzaArr[i];
            }
        }
        return null;
    }

    public boolean zza(PackageInfo packageInfo, boolean z) {
        zzd.zza zza;
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if (z) {
                zza = zza(packageInfo, zzd.C1694zzd.zzaxk);
            } else {
                zza = zza(packageInfo, zzd.C1694zzd.zzaxk[0]);
            }
            if (zza != null) {
                return true;
            }
        }
        return false;
    }

    public boolean zza(PackageManager packageManager, int i) {
        String[] packagesForUid = zzacx.zzaQ(this.mContext).getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return false;
        }
        for (String zzb : packagesForUid) {
            if (zzb(packageManager, zzb)) {
                return true;
            }
        }
        return false;
    }

    public boolean zza(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zze.zzar(this.mContext)) {
            return zzb(packageInfo, true);
        }
        boolean zzb = zzb(packageInfo, false);
        if (zzb || !zzb(packageInfo, true)) {
            return zzb;
        }
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public boolean zzb(PackageInfo packageInfo, boolean z) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return false;
        }
        zzd.zzb zzb = new zzd.zzb(packageInfo.signatures[0].toByteArray());
        String str = packageInfo.packageName;
        return z ? zzd.zzb(str, zzb) : zzd.zza(str, zzb);
    }

    public boolean zzb(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (!zza(packageInfo, true)) {
            return false;
        }
        if (zze.zzar(this.mContext)) {
            return true;
        }
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        return false;
    }

    public boolean zzb(PackageManager packageManager, String str) {
        try {
            return zza(packageManager, zzacx.zzaQ(this.mContext).getPackageInfo(str, 64));
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
