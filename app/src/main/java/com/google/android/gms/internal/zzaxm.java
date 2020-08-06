package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzg;

public final class zzaxm {
    public static final Api<zzaxo> API = new Api<>("SignIn.API", zzahd, zzahc);
    public static final Api<zza> zzaJq = new Api<>("SignIn.INTERNAL_API", zzbCe, zzbCd);
    public static final Api.zzf<zzaxy> zzahc = new Api.zzf<>();
    public static final Api.zza<zzaxy, zzaxo> zzahd = new Api.zza<zzaxy, zzaxo>() {
        public zzaxy zza(Context context, Looper looper, zzg zzg, zzaxo zzaxo, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzaxy(context, looper, true, zzg, zzaxo == null ? zzaxo.zzbCg : zzaxo, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final Scope zzajd = new Scope(Scopes.PROFILE);
    public static final Scope zzaje = new Scope("email");
    public static final Api.zzf<zzaxy> zzbCd = new Api.zzf<>();
    static final Api.zza<zzaxy, zza> zzbCe = new Api.zza<zzaxy, zza>() {
        public zzaxy zza(Context context, Looper looper, zzg zzg, zza zza, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzaxy(context, looper, false, zzg, zza.zzOd(), connectionCallbacks, onConnectionFailedListener);
        }
    };

    public static class zza implements Api.ApiOptions.HasOptions {
        private final Bundle zzbCf;

        public Bundle zzOd() {
            return this.zzbCf;
        }
    }
}
