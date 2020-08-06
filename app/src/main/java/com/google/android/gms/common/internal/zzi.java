package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.internal.zzaax;

public abstract class zzi implements DialogInterface.OnClickListener {
    public static zzi zza(final Activity activity, final Intent intent, final int i) {
        return new zzi() {
            public void zzxm() {
                if (intent != null) {
                    activity.startActivityForResult(intent, i);
                }
            }
        };
    }

    public static zzi zza(@NonNull final Fragment fragment, final Intent intent, final int i) {
        return new zzi() {
            public void zzxm() {
                if (intent != null) {
                    fragment.startActivityForResult(intent, i);
                }
            }
        };
    }

    public static zzi zza(@NonNull final zzaax zzaax, final Intent intent, final int i) {
        return new zzi() {
            @TargetApi(11)
            public void zzxm() {
                if (intent != null) {
                    zzaax.startActivityForResult(intent, i);
                }
            }
        };
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            zzxm();
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } finally {
            dialogInterface.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzxm();
}
