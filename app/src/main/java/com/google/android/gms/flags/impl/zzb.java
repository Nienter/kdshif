package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzaps;
import java.util.concurrent.Callable;

public class zzb {
    private static SharedPreferences zzaWS = null;

    public static SharedPreferences zzm(final Context context) {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (zzaWS == null) {
                zzaWS = (SharedPreferences) zzaps.zzb(new Callable<SharedPreferences>() {
                    /* renamed from: zzCU */
                    public SharedPreferences call() {
                        return context.getSharedPreferences("google_sdk_flags", 1);
                    }
                });
            }
            sharedPreferences = zzaWS;
        }
        return sharedPreferences;
    }
}
