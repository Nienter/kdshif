package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;
import com.google.android.gms.internal.zzapq;

public class zzapp {
    private zzapq zzaWI = null;
    private boolean zztW = false;

    public void initialize(Context context) {
        synchronized (this) {
            if (!this.zztW) {
                try {
                    this.zzaWI = zzapq.zza.asInterface(DynamiteModule.zza(context, DynamiteModule.zzaQw, ModuleDescriptor.MODULE_ID).zzdX("com.google.android.gms.flags.impl.FlagProviderImpl"));
                    this.zzaWI.init(zze.zzA(context));
                    this.zztW = true;
                } catch (RemoteException | DynamiteModule.zza e) {
                    Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
                }
                return;
            }
            return;
        }
    }

    public <T> T zzb(zzapn<T> zzapn) {
        synchronized (this) {
            if (this.zztW) {
                return zzapn.zza(this.zzaWI);
            }
            T zzfm = zzapn.zzfm();
            return zzfm;
        }
    }
}
