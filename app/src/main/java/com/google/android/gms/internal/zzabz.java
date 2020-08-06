package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaca;
import com.google.android.gms.internal.zzzv;

public final class zzabz implements zzaby {

    private static class zza extends zzabw {
        private final zzzv.zzb<Status> zzaFq;

        public zza(zzzv.zzb<Status> zzb) {
            this.zzaFq = zzb;
        }

        public void zzcX(int i) {
            this.zzaFq.setResult(new Status(i));
        }
    }

    public PendingResult<Status> zzg(GoogleApiClient googleApiClient) {
        return googleApiClient.zzb(new zzaca.zza(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzacb zzacb) {
                ((zzacd) zzacb.zzwW()).zza(new zza(this));
            }
        });
    }
}
