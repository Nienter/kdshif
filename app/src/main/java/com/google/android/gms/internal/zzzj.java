package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.internal.zzzm;
import com.google.android.gms.internal.zzzv;

public class zzzj extends zzc<Api.ApiOptions.NoOptions> implements zzzg {

    static final class zza extends zzzv.zza<Status, zzzk> {
        private final zzzh zzawO;

        zza(zzzh zzzh, GoogleApiClient googleApiClient) {
            super((Api<?>) zzzf.API, googleApiClient);
            this.zzawO = zzzh;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            return this.zzawO.equals(((zza) obj).zzawO);
        }

        public String toString() {
            String valueOf = String.valueOf(this.zzawO);
            return new StringBuilder(String.valueOf(valueOf).length() + 20).append("LogEventMethodImpl(").append(valueOf).append(")").toString();
        }

        /* access modifiers changed from: protected */
        public void zza(zzzk zzzk) {
            C14291 r0 = new zzzm.zza() {
                public void zzv(Status status) {
                    zza.this.zzb(status);
                }

                public void zzw(Status status) {
                    throw new UnsupportedOperationException();
                }
            };
            try {
                zzzj.zzb(this.zzawO);
                zzzk.zza(r0, this.zzawO);
            } catch (RuntimeException e) {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
                zzA(new Status(10, "MessageProducer"));
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    zzzj(@NonNull Context context) {
        super(context, zzzf.API, null, (zzabk) new zzzr());
    }

    public static zzzg zzai(@NonNull Context context) {
        return new zzzj(context);
    }

    static void zzb(zzzh zzzh) {
        if (zzzh.zzawM != null && zzzh.zzawL.zzcsF.length == 0) {
            zzzh.zzawL.zzcsF = zzzh.zzawM.zzuw();
        }
        if (zzzh.zzawN != null && zzzh.zzawL.zzcsL.length == 0) {
            zzzh.zzawL.zzcsL = zzzh.zzawN.zzuw();
        }
        zzzh.zzawF = zzbut.zzf(zzzh.zzawL);
    }

    public PendingResult<Status> zza(zzzh zzzh) {
        return doBestEffortWrite(new zza(zzzh, asGoogleApiClient()));
    }
}
