package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzzv;

abstract class zzaca<R extends Result> extends zzzv.zza<R, zzacb> {

    static abstract class zza extends zzaca<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    public zzaca(GoogleApiClient googleApiClient) {
        super((Api<?>) zzabx.API, googleApiClient);
    }
}
