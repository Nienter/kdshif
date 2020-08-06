package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzaa;

public final class zzzs<O extends Api.ApiOptions> {
    private final Api<O> zzawb;
    private final O zzaxG;
    private final boolean zzayv = true;
    private final int zzayw;

    private zzzs(Api<O> api) {
        this.zzawb = api;
        this.zzaxG = null;
        this.zzayw = System.identityHashCode(this);
    }

    private zzzs(Api<O> api, O o) {
        this.zzawb = api;
        this.zzaxG = o;
        this.zzayw = zzaa.hashCode(this.zzawb, this.zzaxG);
    }

    public static <O extends Api.ApiOptions> zzzs<O> zza(Api<O> api, O o) {
        return new zzzs<>(api, o);
    }

    public static <O extends Api.ApiOptions> zzzs<O> zzb(Api<O> api) {
        return new zzzs<>(api);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzzs)) {
            return false;
        }
        zzzs zzzs = (zzzs) obj;
        return !this.zzayv && !zzzs.zzayv && zzaa.equal(this.zzawb, zzzs.zzawb) && zzaa.equal(this.zzaxG, zzzs.zzaxG);
    }

    public int hashCode() {
        return this.zzayw;
    }

    public String zzuV() {
        return this.zzawb.getName();
    }
}
