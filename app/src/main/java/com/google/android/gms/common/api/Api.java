package com.google.android.gms.common.api;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.p004v7.widget.ActivityChooserView;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzr;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api<O extends ApiOptions> {
    private final String mName;
    private final zza<?, O> zzaxu;
    private final zzh<?, O> zzaxv = null;
    private final zzf<?> zzaxw;
    private final zzi<?> zzaxx;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    public static abstract class zza<T extends zze, O> extends zzd<T, O> {
        public abstract T zza(Context context, Looper looper, com.google.android.gms.common.internal.zzg zzg, O o, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener);
    }

    public interface zzb {
    }

    public static class zzc<C extends zzb> {
    }

    public static abstract class zzd<T extends zzb, O> {
        public int getPriority() {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }

        public List<Scope> zzp(O o) {
            return Collections.emptyList();
        }
    }

    public interface zze extends zzb {
        void disconnect();

        void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        boolean isConnected();

        boolean isConnecting();

        void zza(zzf.C1686zzf zzf);

        void zza(zzr zzr, Set<Scope> set);

        boolean zzqD();

        boolean zzqS();

        Intent zzqT();

        boolean zzuI();

        @Nullable
        IBinder zzuJ();
    }

    public static final class zzf<C extends zze> extends zzc<C> {
    }

    public interface zzg<T extends IInterface> extends zzb {
        String zzeu();

        String zzev();

        T zzh(IBinder iBinder);
    }

    public static abstract class zzh<T extends zzg, O> extends zzd<T, O> {
    }

    public static final class zzi<C extends zzg> extends zzc<C> {
    }

    public <C extends zze> Api(String str, zza<C, O> zza2, zzf<C> zzf2) {
        zzac.zzb(zza2, (Object) "Cannot construct an Api with a null ClientBuilder");
        zzac.zzb(zzf2, (Object) "Cannot construct an Api with a null ClientKey");
        this.mName = str;
        this.zzaxu = zza2;
        this.zzaxw = zzf2;
        this.zzaxx = null;
    }

    public String getName() {
        return this.mName;
    }

    public zzd<?, O> zzuF() {
        return this.zzaxu;
    }

    public zza<?, O> zzuG() {
        zzac.zza(this.zzaxu != null, (Object) "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zzaxu;
    }

    public zzc<?> zzuH() {
        if (this.zzaxw != null) {
            return this.zzaxw;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }
}
