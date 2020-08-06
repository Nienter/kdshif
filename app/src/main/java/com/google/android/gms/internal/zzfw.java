package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.zze;
import java.util.concurrent.Callable;

@zzmb
public class zzfw {
    private final ConditionVariable zzAM = new ConditionVariable();
    /* access modifiers changed from: private */
    @Nullable
    public SharedPreferences zzAN = null;
    private final Object zzrN = new Object();
    private volatile boolean zztW = false;

    public void initialize(Context context) {
        if (!this.zztW) {
            synchronized (this.zzrN) {
                if (!this.zztW) {
                    try {
                        Context remoteContext = zze.getRemoteContext(context);
                        if (remoteContext != null) {
                            this.zzAN = zzv.zzcT().zzm(remoteContext);
                            this.zztW = true;
                            this.zzAM.open();
                        }
                    } finally {
                        this.zzAM.open();
                    }
                }
            }
        }
    }

    public <T> T zzd(final zzft<T> zzft) {
        if (!this.zzAM.block(5000)) {
            throw new IllegalStateException("Flags.initialize() was not called!");
        }
        if (!this.zztW) {
            synchronized (this.zzrN) {
                if (!this.zztW) {
                    T zzfm = zzft.zzfm();
                    return zzfm;
                }
            }
        }
        return zzpv.zzb(new Callable<T>() {
            public T call() {
                return zzft.zza(zzfw.this.zzAN);
            }
        });
    }
}
