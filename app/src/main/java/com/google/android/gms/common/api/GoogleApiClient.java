package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p001v4.app.FragmentActivity;
import android.support.p001v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzaal;
import com.google.android.gms.internal.zzaav;
import com.google.android.gms.internal.zzaaz;
import com.google.android.gms.internal.zzabi;
import com.google.android.gms.internal.zzabp;
import com.google.android.gms.internal.zzaxm;
import com.google.android.gms.internal.zzaxn;
import com.google.android.gms.internal.zzaxo;
import com.google.android.gms.internal.zzzt;
import com.google.android.gms.internal.zzzv;
import com.google.android.gms.internal.zzzy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */
    public static final Set<GoogleApiClient> zzaxM = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {
        private final Context mContext;
        private Account zzagg;
        private String zzahp;
        private final Set<Scope> zzaxN;
        private final Set<Scope> zzaxO;
        private int zzaxP;
        private View zzaxQ;
        private String zzaxR;
        private final Map<Api<?>, zzg.zza> zzaxS;
        private final Map<Api<?>, Api.ApiOptions> zzaxT;
        private zzaav zzaxU;
        private int zzaxV;
        private OnConnectionFailedListener zzaxW;
        private GoogleApiAvailability zzaxX;
        private Api.zza<? extends zzaxn, zzaxo> zzaxY;
        private final ArrayList<ConnectionCallbacks> zzaxZ;
        private final ArrayList<OnConnectionFailedListener> zzaya;
        private boolean zzayb;
        private Looper zzrx;

        public Builder(@NonNull Context context) {
            this.zzaxN = new HashSet();
            this.zzaxO = new HashSet();
            this.zzaxS = new ArrayMap();
            this.zzaxT = new ArrayMap();
            this.zzaxV = -1;
            this.zzaxX = GoogleApiAvailability.getInstance();
            this.zzaxY = zzaxm.zzahd;
            this.zzaxZ = new ArrayList<>();
            this.zzaya = new ArrayList<>();
            this.zzayb = false;
            this.mContext = context;
            this.zzrx = context.getMainLooper();
            this.zzahp = context.getPackageName();
            this.zzaxR = context.getClass().getName();
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzac.zzb(connectionCallbacks, (Object) "Must provide a connected listener");
            this.zzaxZ.add(connectionCallbacks);
            zzac.zzb(onConnectionFailedListener, (Object) "Must provide a connection failed listener");
            this.zzaya.add(onConnectionFailedListener);
        }

        private static <C extends Api.zze, O> C zza(Api.zza<C, O> zza, Object obj, Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zza.zza(context, looper, zzg, obj, connectionCallbacks, onConnectionFailedListener);
        }

        private Builder zza(@NonNull zzaav zzaav, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            zzac.zzb(i >= 0, (Object) "clientId must be non-negative");
            this.zzaxV = i;
            this.zzaxW = onConnectionFailedListener;
            this.zzaxU = zzaav;
            return this;
        }

        private <O extends Api.ApiOptions> void zza(Api<O> api, O o, int i, Scope... scopeArr) {
            boolean z = true;
            if (i != 1) {
                if (i == 2) {
                    z = false;
                } else {
                    throw new IllegalArgumentException(new StringBuilder(90).append("Invalid resolution mode: '").append(i).append("', use a constant from GoogleApiClient.ResolutionMode").toString());
                }
            }
            HashSet hashSet = new HashSet(api.zzuF().zzp(o));
            for (Scope add : scopeArr) {
                hashSet.add(add);
            }
            this.zzaxS.put(api, new zzg.zza(hashSet, z));
        }

        private void zzf(GoogleApiClient googleApiClient) {
            zzzt.zza(this.zzaxU).zza(this.zzaxV, googleApiClient, this.zzaxW);
        }

        private GoogleApiClient zzuQ() {
            zzg zzuP = zzuP();
            Api api = null;
            Map<Api<?>, zzg.zza> zzxg = zzuP.zzxg();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            Api api2 = null;
            for (Api next : this.zzaxT.keySet()) {
                Api.ApiOptions apiOptions = this.zzaxT.get(next);
                int i = 0;
                if (zzxg.get(next) != null) {
                    i = zzxg.get(next).zzaEf ? 1 : 2;
                }
                arrayMap.put(next, Integer.valueOf(i));
                zzzy zzzy = new zzzy(next, i);
                arrayList.add(zzzy);
                Api.zza zzuG = next.zzuG();
                Api api3 = zzuG.getPriority() == 1 ? next : api2;
                Api.zze zza = zza(zzuG, apiOptions, this.mContext, this.zzrx, zzuP, zzzy, zzzy);
                arrayMap2.put(next.zzuH(), zza);
                if (!zza.zzqS()) {
                    next = api;
                } else if (api != null) {
                    String valueOf = String.valueOf(next.getName());
                    String valueOf2 = String.valueOf(api.getName());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(valueOf2).length()).append(valueOf).append(" cannot be used with ").append(valueOf2).toString());
                }
                api2 = api3;
                api = next;
            }
            if (api != null) {
                if (api2 != null) {
                    String valueOf3 = String.valueOf(api.getName());
                    String valueOf4 = String.valueOf(api2.getName());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf3).length() + 21 + String.valueOf(valueOf4).length()).append(valueOf3).append(" cannot be used with ").append(valueOf4).toString());
                }
                zzac.zza(this.zzagg == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                zzac.zza(this.zzaxN.equals(this.zzaxO), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            return new zzaal(this.mContext, new ReentrantLock(), this.zzrx, zzuP, this.zzaxX, this.zzaxY, arrayMap, this.zzaxZ, this.zzaya, arrayMap2, this.zzaxV, zzaal.zza(arrayMap2.values(), true), arrayList, false);
        }

        public Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            zzac.zzb(api, (Object) "Api must not be null");
            this.zzaxT.put(api, null);
            List<Scope> zzp = api.zzuF().zzp(null);
            this.zzaxO.addAll(zzp);
            this.zzaxN.addAll(zzp);
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o) {
            zzac.zzb(api, (Object) "Api must not be null");
            zzac.zzb(o, (Object) "Null options are not permitted for this Api");
            this.zzaxT.put(api, o);
            List<Scope> zzp = api.zzuF().zzp(o);
            this.zzaxO.addAll(zzp);
            this.zzaxN.addAll(zzp);
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o, Scope... scopeArr) {
            zzac.zzb(api, (Object) "Api must not be null");
            zzac.zzb(o, (Object) "Null options are not permitted for this Api");
            this.zzaxT.put(api, o);
            zza(api, o, 1, scopeArr);
            return this;
        }

        public Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope... scopeArr) {
            zzac.zzb(api, (Object) "Api must not be null");
            this.zzaxT.put(api, null);
            zza(api, null, 1, scopeArr);
            return this;
        }

        public Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            zzac.zzb(connectionCallbacks, (Object) "Listener must not be null");
            this.zzaxZ.add(connectionCallbacks);
            return this;
        }

        public Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            zzac.zzb(onConnectionFailedListener, (Object) "Listener must not be null");
            this.zzaya.add(onConnectionFailedListener);
            return this;
        }

        public Builder addScope(@NonNull Scope scope) {
            zzac.zzb(scope, (Object) "Scope must not be null");
            this.zzaxN.add(scope);
            return this;
        }

        public GoogleApiClient build() {
            zzac.zzb(!this.zzaxT.isEmpty(), (Object) "must call addApi() to add at least one API");
            GoogleApiClient zzuQ = zzuQ();
            synchronized (GoogleApiClient.zzaxM) {
                GoogleApiClient.zzaxM.add(zzuQ);
            }
            if (this.zzaxV >= 0) {
                zzf(zzuQ);
            }
            return zzuQ;
        }

        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return zza(new zzaav(fragmentActivity), i, onConnectionFailedListener);
        }

        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        public Builder setAccountName(String str) {
            this.zzagg = str == null ? null : new Account(str, "com.google");
            return this;
        }

        public Builder setGravityForPopups(int i) {
            this.zzaxP = i;
            return this;
        }

        public Builder setHandler(@NonNull Handler handler) {
            zzac.zzb(handler, (Object) "Handler must not be null");
            this.zzrx = handler.getLooper();
            return this;
        }

        public Builder setViewForPopups(@NonNull View view) {
            zzac.zzb(view, (Object) "View must not be null");
            this.zzaxQ = view;
            return this;
        }

        public Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public zzg zzuP() {
            zzaxo zzaxo = zzaxo.zzbCg;
            if (this.zzaxT.containsKey(zzaxm.API)) {
                zzaxo = (zzaxo) this.zzaxT.get(zzaxm.API);
            }
            return new zzg(this.zzagg, this.zzaxN, this.zzaxS, this.zzaxP, this.zzaxQ, this.zzahp, this.zzaxR, zzaxo);
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (zzaxM) {
            String concat = String.valueOf(str).concat("  ");
            int i = 0;
            for (GoogleApiClient dump : zzaxM) {
                printWriter.append(str).append("GoogleApiClient#").println(i);
                dump.dump(concat, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    public static Set<GoogleApiClient> zzuM() {
        Set<GoogleApiClient> set;
        synchronized (zzaxM) {
            set = zzaxM;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @NonNull
    public <C extends Api.zze> C zza(@NonNull Api.zzc<C> zzc) {
        throw new UnsupportedOperationException();
    }

    public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public void zza(zzabp zzabp) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(zzabi zzabi) {
        throw new UnsupportedOperationException();
    }

    public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzabp zzabp) {
        throw new UnsupportedOperationException();
    }

    public <L> zzaaz<L> zzr(@NonNull L l) {
        throw new UnsupportedOperationException();
    }

    public void zzuN() {
        throw new UnsupportedOperationException();
    }
}
