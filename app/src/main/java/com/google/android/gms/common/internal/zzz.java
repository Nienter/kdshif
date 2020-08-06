package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzacx;

public class zzz {
    private static boolean zzPT;
    private static String zzaEW;
    private static int zzaEX;
    private static Object zztU = new Object();

    public static String zzaD(Context context) {
        zzaF(context);
        return zzaEW;
    }

    public static int zzaE(Context context) {
        zzaF(context);
        return zzaEX;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    private static void zzaF(Context context) {
        synchronized (zztU) {
            if (!zzPT) {
                zzPT = true;
                try {
                    Bundle bundle = zzacx.zzaQ(context).getApplicationInfo(context.getPackageName(), 128).metaData;
                    if (bundle != null) {
                        zzaEW = bundle.getString("com.google.app.id");
                        zzaEX = bundle.getInt("com.google.android.gms.version");
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.wtf("MetadataValueReader", "This should never happen.", e);
                }
            }
        }
    }
}
