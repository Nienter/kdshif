package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.WorkSource;
import android.util.Log;
import com.google.android.gms.internal.zzacx;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zzy {
    private static final Method zzaHc = zzyL();
    private static final Method zzaHd = zzyM();
    private static final Method zzaHe = zzyN();
    private static final Method zzaHf = zzyO();
    private static final Method zzaHg = zzyP();

    public static int zza(WorkSource workSource) {
        if (zzaHe != null) {
            try {
                return ((Integer) zzaHe.invoke(workSource, new Object[0])).intValue();
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    public static String zza(WorkSource workSource, int i) {
        if (zzaHg != null) {
            try {
                return (String) zzaHg.invoke(workSource, new Object[]{Integer.valueOf(i)});
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return null;
    }

    public static void zza(WorkSource workSource, int i, String str) {
        if (zzaHd != null) {
            if (str == null) {
                str = "";
            }
            try {
                zzaHd.invoke(workSource, new Object[]{Integer.valueOf(i), str});
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        } else if (zzaHc != null) {
            try {
                zzaHc.invoke(workSource, new Object[]{Integer.valueOf(i)});
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        }
    }

    public static boolean zzaO(Context context) {
        return (context == null || context.getPackageManager() == null || zzacx.zzaQ(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) ? false : true;
    }

    public static List<String> zzb(WorkSource workSource) {
        int zza = workSource == null ? 0 : zza(workSource);
        if (zza == 0) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < zza; i++) {
            String zza2 = zza(workSource, i);
            if (!zzv.zzdD(zza2)) {
                arrayList.add(zza2);
            }
        }
        return arrayList;
    }

    public static WorkSource zzf(int i, String str) {
        WorkSource workSource = new WorkSource();
        zza(workSource, i, str);
        return workSource;
    }

    public static WorkSource zzy(Context context, String str) {
        if (context == null || context.getPackageManager() == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = zzacx.zzaQ(context).getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return zzf(applicationInfo.uid, str);
            }
            String valueOf = String.valueOf(str);
            Log.e("WorkSourceUtil", valueOf.length() != 0 ? "Could not get applicationInfo from package: ".concat(valueOf) : new String("Could not get applicationInfo from package: "));
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf2 = String.valueOf(str);
            Log.e("WorkSourceUtil", valueOf2.length() != 0 ? "Could not find package: ".concat(valueOf2) : new String("Could not find package: "));
            return null;
        }
    }

    private static Method zzyL() {
        boolean z = false;
        try {
            return WorkSource.class.getMethod("add", new Class[]{Integer.TYPE});
        } catch (Exception e) {
            return z;
        }
    }

    private static Method zzyM() {
        Method method = null;
        if (!zzs.zzyE()) {
            return method;
        }
        try {
            return WorkSource.class.getMethod("add", new Class[]{Integer.TYPE, String.class});
        } catch (Exception e) {
            return method;
        }
    }

    private static Method zzyN() {
        boolean z = false;
        try {
            return WorkSource.class.getMethod("size", new Class[0]);
        } catch (Exception e) {
            return z;
        }
    }

    private static Method zzyO() {
        boolean z = false;
        try {
            return WorkSource.class.getMethod("get", new Class[]{Integer.TYPE});
        } catch (Exception e) {
            return z;
        }
    }

    private static Method zzyP() {
        Method method = null;
        if (!zzs.zzyE()) {
            return method;
        }
        try {
            return WorkSource.class.getMethod("getName", new Class[]{Integer.TYPE});
        } catch (Exception e) {
            return method;
        }
    }
}
