package com.google.android.gms.internal;

import com.google.android.gms.internal.zzb;

public class zzm<T> {
    public final T result;
    public final zzb.zza zzae;
    public final zzr zzaf;
    public boolean zzag;

    public interface zza {
        void zze(zzr zzr);
    }

    public interface zzb<T> {
        void zzb(T t);
    }

    private zzm(zzr zzr) {
        this.zzag = false;
        this.result = null;
        this.zzae = null;
        this.zzaf = zzr;
    }

    private zzm(T t, zzb.zza zza2) {
        this.zzag = false;
        this.result = t;
        this.zzae = zza2;
        this.zzaf = null;
    }

    public static <T> zzm<T> zza(T t, zzb.zza zza2) {
        return new zzm<>(t, zza2);
    }

    public static <T> zzm<T> zzd(zzr zzr) {
        return new zzm<>(zzr);
    }

    public boolean isSuccess() {
        return this.zzaf == null;
    }
}
