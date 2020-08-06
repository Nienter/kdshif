package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;

public class zzzv {

    public static abstract class zza<R extends Result, A extends Api.zzb> extends zzzx<R> implements zzb<R> {
        private final Api<?> zzawb;
        private final Api.zzc<A> zzayF;

        @Deprecated
        protected zza(Api.zzc<A> zzc, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzac.zzb(googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.zzayF = (Api.zzc) zzac.zzw(zzc);
            this.zzawb = null;
        }

        protected zza(Api<?> api, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzac.zzb(googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.zzayF = api.zzuH();
            this.zzawb = api;
        }

        private void zzc(RemoteException remoteException) {
            zzA(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        public final Api<?> getApi() {
            return this.zzawb;
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzb((Result) obj);
        }

        public final void zzA(Status status) {
            zzac.zzb(!status.isSuccess(), (Object) "Failed result must not be success");
            zzb(zzc(status));
        }

        /* access modifiers changed from: protected */
        public abstract void zza(A a);

        public final void zzb(A a) {
            try {
                zza(a);
            } catch (DeadObjectException e) {
                zzc(e);
                throw e;
            } catch (RemoteException e2) {
                zzc(e2);
            }
        }

        public final Api.zzc<A> zzuH() {
            return this.zzayF;
        }
    }

    public interface zzb<R> {
        void setResult(R r);

        void zzA(Status status);
    }
}
