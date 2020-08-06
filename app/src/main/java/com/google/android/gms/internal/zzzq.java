package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzaap;
import com.google.android.gms.internal.zzaaz;
import com.google.android.gms.internal.zzzv;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzzq {
    public final int zzanR;

    private static abstract class zza extends zzzq {
        protected final TaskCompletionSource<Void> zzayo;

        public zza(int i, TaskCompletionSource<Void> taskCompletionSource) {
            super(i);
            this.zzayo = taskCompletionSource;
        }

        public void zza(@NonNull zzaad zzaad, boolean z) {
        }

        public final void zza(zzaap.zza<?> zza) {
            try {
                zzb(zza);
            } catch (DeadObjectException e) {
                zzy(zzzq.zza((RemoteException) e));
                throw e;
            } catch (RemoteException e2) {
                zzy(zzzq.zza(e2));
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzb(zzaap.zza<?> zza);

        public void zzy(@NonNull Status status) {
            this.zzayo.trySetException(new com.google.android.gms.common.api.zza(status));
        }
    }

    public static class zzb<A extends zzzv.zza<? extends Result, Api.zzb>> extends zzzq {
        protected final A zzayp;

        public zzb(int i, A a) {
            super(i);
            this.zzayp = a;
        }

        public void zza(@NonNull zzaad zzaad, boolean z) {
            zzaad.zza((zzzx<? extends Result>) this.zzayp, z);
        }

        public void zza(zzaap.zza<?> zza) {
            this.zzayp.zzb(zza.zzvr());
        }

        public void zzy(@NonNull Status status) {
            this.zzayp.zzA(status);
        }
    }

    public static final class zzc extends zza {
        public final zzabe<Api.zzb, ?> zzayq;
        public final zzabr<Api.zzb, ?> zzayr;

        public zzc(zzabf zzabf, TaskCompletionSource<Void> taskCompletionSource) {
            super(3, taskCompletionSource);
            this.zzayq = zzabf.zzayq;
            this.zzayr = zzabf.zzayr;
        }

        public /* bridge */ /* synthetic */ void zza(@NonNull zzaad zzaad, boolean z) {
            super.zza(zzaad, z);
        }

        public void zzb(zzaap.zza<?> zza) {
            if (this.zzayq.zzwp() != null) {
                zza.zzwc().put(this.zzayq.zzwp(), new zzabf(this.zzayq, this.zzayr));
            }
        }

        public /* bridge */ /* synthetic */ void zzy(@NonNull Status status) {
            super.zzy(status);
        }
    }

    public static final class zzd<TResult> extends zzzq {
        private final TaskCompletionSource<TResult> zzayo;
        private final zzabn<Api.zzb, TResult> zzays;
        private final zzabk zzayt;

        public zzd(int i, zzabn<Api.zzb, TResult> zzabn, TaskCompletionSource<TResult> taskCompletionSource, zzabk zzabk) {
            super(i);
            this.zzayo = taskCompletionSource;
            this.zzays = zzabn;
            this.zzayt = zzabk;
        }

        public void zza(@NonNull zzaad zzaad, boolean z) {
            zzaad.zza(this.zzayo, z);
        }

        public void zza(zzaap.zza<?> zza) {
            try {
                this.zzays.zza(zza.zzvr(), this.zzayo);
            } catch (DeadObjectException e) {
                throw e;
            } catch (RemoteException e2) {
                zzy(zzzq.zza(e2));
            }
        }

        public void zzy(@NonNull Status status) {
            this.zzayo.trySetException(this.zzayt.zzz(status));
        }
    }

    public static final class zze extends zza {
        public final zzaaz.zzb<?> zzayu;

        public zze(zzaaz.zzb<?> zzb, TaskCompletionSource<Void> taskCompletionSource) {
            super(4, taskCompletionSource);
            this.zzayu = zzb;
        }

        public /* bridge */ /* synthetic */ void zza(@NonNull zzaad zzaad, boolean z) {
            super.zza(zzaad, z);
        }

        public void zzb(zzaap.zza<?> zza) {
            zzabf remove = zza.zzwc().remove(this.zzayu);
            if (remove != null) {
                remove.zzayq.zzwq();
                return;
            }
            Log.wtf("UnregisterListenerTask", "Received call to unregister a listener without a matching registration call.", new Exception());
            this.zzayo.trySetException(new com.google.android.gms.common.api.zza(Status.zzayj));
        }

        public /* bridge */ /* synthetic */ void zzy(@NonNull Status status) {
            super.zzy(status);
        }
    }

    public zzzq(int i) {
        this.zzanR = i;
    }

    /* access modifiers changed from: private */
    public static Status zza(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        if (zzs.zzyB() && (remoteException instanceof TransactionTooLargeException)) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }

    public abstract void zza(@NonNull zzaad zzaad, boolean z);

    public abstract void zza(zzaap.zza<?> zza2);

    public abstract void zzy(@NonNull Status status);
}
