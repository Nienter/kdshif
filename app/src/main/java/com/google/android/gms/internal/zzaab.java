package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzaap;

public final class zzaab<O extends Api.ApiOptions> extends zzc<O> {
    private final Api.zza<? extends zzaxn, zzaxo> zzaxY;
    private final Api.zze zzazq;
    private final zzzy zzazr;
    private final zzg zzazs;

    public zzaab(@NonNull Context context, Api<O> api, Looper looper, @NonNull Api.zze zze, @NonNull zzzy zzzy, zzg zzg, Api.zza<? extends zzaxn, zzaxo> zza) {
        super(context, api, looper);
        this.zzazq = zze;
        this.zzazr = zzzy;
        this.zzazs = zzg;
        this.zzaxY = zza;
        this.zzaxK.zza((zzc<?>) this);
    }

    public Api.zze buildApiClient(Looper looper, zzaap.zza<O> zza) {
        this.zzazr.zza(zza);
        return this.zzazq;
    }

    public zzabj createSignInCoordinator(Context context, Handler handler) {
        return new zzabj(context, handler, this.zzazs, this.zzaxY);
    }

    public Api.zze zzvr() {
        return this.zzazq;
    }
}
