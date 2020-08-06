package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzaan;
import com.google.android.gms.internal.zzzv;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public class zzaaj implements zzaam {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Api.zza<? extends zzaxn, zzaxo> zzaxY;
    private ConnectionResult zzazA;
    /* access modifiers changed from: private */
    public final zzaan zzazK;
    private int zzazN;
    private int zzazO = 0;
    private int zzazP;
    private final Bundle zzazQ = new Bundle();
    private final Set<Api.zzc> zzazR = new HashSet();
    /* access modifiers changed from: private */
    public zzaxn zzazS;
    private int zzazT;
    /* access modifiers changed from: private */
    public boolean zzazU;
    private boolean zzazV;
    /* access modifiers changed from: private */
    public zzr zzazW;
    private boolean zzazX;
    private boolean zzazY;
    private ArrayList<Future<?>> zzazZ = new ArrayList<>();
    /* access modifiers changed from: private */
    public final Lock zzazn;
    private final zzg zzazs;
    private final Map<Api<?>, Integer> zzazu;
    /* access modifiers changed from: private */
    public final com.google.android.gms.common.zzc zzazw;

    private static class zza implements zzf.C1686zzf {
        private final WeakReference<zzaaj> zzaAb;
        private final Api<?> zzawb;
        /* access modifiers changed from: private */
        public final int zzazb;

        public zza(zzaaj zzaaj, Api<?> api, int i) {
            this.zzaAb = new WeakReference<>(zzaaj);
            this.zzawb = api;
            this.zzazb = i;
        }

        public void zzg(@NonNull ConnectionResult connectionResult) {
            boolean z = false;
            zzaaj zzaaj = (zzaaj) this.zzaAb.get();
            if (zzaaj != null) {
                if (Looper.myLooper() == zzaaj.zzazK.zzazd.getLooper()) {
                    z = true;
                }
                zzac.zza(z, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                zzaaj.zzazn.lock();
                try {
                    if (zzaaj.zzcv(0)) {
                        if (!connectionResult.isSuccess()) {
                            zzaaj.zzb(connectionResult, this.zzawb, this.zzazb);
                        }
                        if (zzaaj.zzvB()) {
                            zzaaj.zzvC();
                        }
                        zzaaj.zzazn.unlock();
                    }
                } finally {
                    zzaaj.zzazn.unlock();
                }
            }
        }
    }

    private class zzb extends zzf {
        private final Map<Api.zze, zza> zzaAc;

        public zzb(Map<Api.zze, zza> map) {
            super();
            this.zzaAc = map;
        }

        @WorkerThread
        public void zzvA() {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4 = true;
            int i = 0;
            Iterator<Api.zze> it = this.zzaAc.keySet().iterator();
            boolean z5 = true;
            boolean z6 = false;
            while (true) {
                if (!it.hasNext()) {
                    z4 = z6;
                    z = false;
                    break;
                }
                Api.zze next = it.next();
                if (!next.zzuI()) {
                    z3 = false;
                    z2 = z6;
                } else if (this.zzaAc.get(next).zzazb == 0) {
                    z = true;
                    break;
                } else {
                    z3 = z5;
                    z2 = true;
                }
                z6 = z2;
                z5 = z3;
            }
            if (z4) {
                i = zzaaj.this.zzazw.isGooglePlayServicesAvailable(zzaaj.this.mContext);
            }
            if (i == 0 || (!z && !z5)) {
                if (zzaaj.this.zzazU) {
                    zzaaj.this.zzazS.connect();
                }
                for (Api.zze next2 : this.zzaAc.keySet()) {
                    final zzf.C1686zzf zzf = this.zzaAc.get(next2);
                    if (!next2.zzuI() || i == 0) {
                        next2.zza(zzf);
                    } else {
                        zzaaj.this.zzazK.zza((zzaan.zza) new zzaan.zza(this, zzaaj.this) {
                            public void zzvA() {
                                zzf.zzg(new ConnectionResult(16, null));
                            }
                        });
                    }
                }
                return;
            }
            final ConnectionResult connectionResult = new ConnectionResult(i, null);
            zzaaj.this.zzazK.zza((zzaan.zza) new zzaan.zza(zzaaj.this) {
                public void zzvA() {
                    zzaaj.this.zzf(connectionResult);
                }
            });
        }
    }

    private class zzc extends zzf {
        private final ArrayList<Api.zze> zzaAg;

        public zzc(ArrayList<Api.zze> arrayList) {
            super();
            this.zzaAg = arrayList;
        }

        @WorkerThread
        public void zzvA() {
            zzaaj.this.zzazK.zzazd.zzaAs = zzaaj.this.zzvH();
            Iterator<Api.zze> it = this.zzaAg.iterator();
            while (it.hasNext()) {
                it.next().zza(zzaaj.this.zzazW, zzaaj.this.zzazK.zzazd.zzaAs);
            }
        }
    }

    private static class zzd extends zzaxr {
        private final WeakReference<zzaaj> zzaAb;

        zzd(zzaaj zzaaj) {
            this.zzaAb = new WeakReference<>(zzaaj);
        }

        @BinderThread
        public void zzb(final zzayb zzayb) {
            final zzaaj zzaaj = (zzaaj) this.zzaAb.get();
            if (zzaaj != null) {
                zzaaj.zzazK.zza((zzaan.zza) new zzaan.zza(this, zzaaj) {
                    public void zzvA() {
                        zzaaj.zza(zzayb);
                    }
                });
            }
        }
    }

    private class zze implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private zze() {
        }

        public void onConnected(Bundle bundle) {
            zzaaj.this.zzazS.zza(new zzd(zzaaj.this));
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzaaj.this.zzazn.lock();
            try {
                if (zzaaj.this.zze(connectionResult)) {
                    zzaaj.this.zzvF();
                    zzaaj.this.zzvC();
                } else {
                    zzaaj.this.zzf(connectionResult);
                }
            } finally {
                zzaaj.this.zzazn.unlock();
            }
        }

        public void onConnectionSuspended(int i) {
        }
    }

    private abstract class zzf implements Runnable {
        private zzf() {
        }

        @WorkerThread
        public void run() {
            zzaaj.this.zzazn.lock();
            try {
                if (!Thread.interrupted()) {
                    zzvA();
                    zzaaj.this.zzazn.unlock();
                }
            } catch (RuntimeException e) {
                zzaaj.this.zzazK.zza(e);
            } finally {
                zzaaj.this.zzazn.unlock();
            }
        }

        /* access modifiers changed from: protected */
        @WorkerThread
        public abstract void zzvA();
    }

    public zzaaj(zzaan zzaan, zzg zzg, Map<Api<?>, Integer> map, com.google.android.gms.common.zzc zzc2, Api.zza<? extends zzaxn, zzaxo> zza2, Lock lock, Context context) {
        this.zzazK = zzaan;
        this.zzazs = zzg;
        this.zzazu = map;
        this.zzazw = zzc2;
        this.zzaxY = zza2;
        this.zzazn = lock;
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public void zza(zzayb zzayb) {
        if (zzcv(0)) {
            ConnectionResult zzxA = zzayb.zzxA();
            if (zzxA.isSuccess()) {
                zzaf zzOp = zzayb.zzOp();
                ConnectionResult zzxA2 = zzOp.zzxA();
                if (!zzxA2.isSuccess()) {
                    String valueOf = String.valueOf(zzxA2);
                    Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                    zzf(zzxA2);
                    return;
                }
                this.zzazV = true;
                this.zzazW = zzOp.zzxz();
                this.zzazX = zzOp.zzxB();
                this.zzazY = zzOp.zzxC();
                zzvC();
            } else if (zze(zzxA)) {
                zzvF();
                zzvC();
            } else {
                zzf(zzxA);
            }
        }
    }

    private boolean zza(int i, int i2, ConnectionResult connectionResult) {
        if (i2 != 1 || zzd(connectionResult)) {
            return this.zzazA == null || i < this.zzazN;
        }
        return false;
    }

    private void zzaq(boolean z) {
        if (this.zzazS != null) {
            if (this.zzazS.isConnected() && z) {
                this.zzazS.zzOe();
            }
            this.zzazS.disconnect();
            this.zzazW = null;
        }
    }

    /* access modifiers changed from: private */
    public void zzb(ConnectionResult connectionResult, Api<?> api, int i) {
        if (i != 2) {
            int priority = api.zzuF().getPriority();
            if (zza(priority, i, connectionResult)) {
                this.zzazA = connectionResult;
                this.zzazN = priority;
            }
        }
        this.zzazK.zzaAG.put(api.zzuH(), connectionResult);
    }

    /* access modifiers changed from: private */
    public boolean zzcv(int i) {
        if (this.zzazO == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zzazK.zzazd.zzvN());
        String valueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GoogleApiClientConnecting", new StringBuilder(33).append("mRemainingConnections=").append(this.zzazP).toString());
        String valueOf2 = String.valueOf(zzcw(this.zzazO));
        String valueOf3 = String.valueOf(zzcw(i));
        Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf2).length() + 70 + String.valueOf(valueOf3).length()).append("GoogleApiClient connecting is in step ").append(valueOf2).append(" but received callback for step ").append(valueOf3).toString(), new Exception());
        zzf(new ConnectionResult(8, null));
        return false;
    }

    private String zzcw(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    private boolean zzd(ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.zzazw.zzcr(connectionResult.getErrorCode()) != null;
    }

    /* access modifiers changed from: private */
    public boolean zze(ConnectionResult connectionResult) {
        if (this.zzazT != 2) {
            return this.zzazT == 1 && !connectionResult.hasResolution();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void zzf(ConnectionResult connectionResult) {
        zzvG();
        zzaq(!connectionResult.hasResolution());
        this.zzazK.zzh(connectionResult);
        this.zzazK.zzaAK.zzc(connectionResult);
    }

    /* access modifiers changed from: private */
    public boolean zzvB() {
        this.zzazP--;
        if (this.zzazP > 0) {
            return false;
        }
        if (this.zzazP < 0) {
            Log.w("GoogleApiClientConnecting", this.zzazK.zzazd.zzvN());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zzf(new ConnectionResult(8, null));
            return false;
        } else if (this.zzazA == null) {
            return true;
        } else {
            this.zzazK.zzaAJ = this.zzazN;
            zzf(this.zzazA);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void zzvC() {
        if (this.zzazP == 0) {
            if (!this.zzazU || this.zzazV) {
                zzvD();
            }
        }
    }

    private void zzvD() {
        ArrayList arrayList = new ArrayList();
        this.zzazO = 1;
        this.zzazP = this.zzazK.zzaAr.size();
        for (Api.zzc next : this.zzazK.zzaAr.keySet()) {
            if (!this.zzazK.zzaAG.containsKey(next)) {
                arrayList.add(this.zzazK.zzaAr.get(next));
            } else if (zzvB()) {
                zzvE();
            }
        }
        if (!arrayList.isEmpty()) {
            this.zzazZ.add(zzaao.zzvR().submit(new zzc(arrayList)));
        }
    }

    private void zzvE() {
        this.zzazK.zzvP();
        zzaao.zzvR().execute(new Runnable() {
            public void run() {
                zzaaj.this.zzazw.zzan(zzaaj.this.mContext);
            }
        });
        if (this.zzazS != null) {
            if (this.zzazX) {
                this.zzazS.zza(this.zzazW, this.zzazY);
            }
            zzaq(false);
        }
        for (Api.zzc<?> zzc2 : this.zzazK.zzaAG.keySet()) {
            this.zzazK.zzaAr.get(zzc2).disconnect();
        }
        this.zzazK.zzaAK.zzo(this.zzazQ.isEmpty() ? null : this.zzazQ);
    }

    /* access modifiers changed from: private */
    public void zzvF() {
        this.zzazU = false;
        this.zzazK.zzazd.zzaAs = Collections.emptySet();
        for (Api.zzc next : this.zzazR) {
            if (!this.zzazK.zzaAG.containsKey(next)) {
                this.zzazK.zzaAG.put(next, new ConnectionResult(17, null));
            }
        }
    }

    private void zzvG() {
        Iterator<Future<?>> it = this.zzazZ.iterator();
        while (it.hasNext()) {
            it.next().cancel(true);
        }
        this.zzazZ.clear();
    }

    /* access modifiers changed from: private */
    public Set<Scope> zzvH() {
        if (this.zzazs == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zzazs.zzxe());
        Map<Api<?>, zzg.zza> zzxg = this.zzazs.zzxg();
        for (Api next : zzxg.keySet()) {
            if (!this.zzazK.zzaAG.containsKey(next.zzuH())) {
                hashSet.addAll(zzxg.get(next).zzajm);
            }
        }
        return hashSet;
    }

    public void begin() {
        this.zzazK.zzaAG.clear();
        this.zzazU = false;
        this.zzazA = null;
        this.zzazO = 0;
        this.zzazT = 2;
        this.zzazV = false;
        this.zzazX = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api next : this.zzazu.keySet()) {
            Api.zze zze2 = this.zzazK.zzaAr.get(next.zzuH());
            int intValue = this.zzazu.get(next).intValue();
            boolean z2 = (next.zzuF().getPriority() == 1) | z;
            if (zze2.zzqD()) {
                this.zzazU = true;
                if (intValue < this.zzazT) {
                    this.zzazT = intValue;
                }
                if (intValue != 0) {
                    this.zzazR.add(next.zzuH());
                }
            }
            hashMap.put(zze2, new zza(this, next, intValue));
            z = z2;
        }
        if (z) {
            this.zzazU = false;
        }
        if (this.zzazU) {
            this.zzazs.zzc(Integer.valueOf(this.zzazK.zzazd.getSessionId()));
            zze zze3 = new zze();
            this.zzazS = (zzaxn) this.zzaxY.zza(this.mContext, this.zzazK.zzazd.getLooper(), this.zzazs, this.zzazs.zzxk(), zze3, zze3);
        }
        this.zzazP = this.zzazK.zzaAr.size();
        this.zzazZ.add(zzaao.zzvR().submit(new zzb(hashMap)));
    }

    public void connect() {
    }

    public boolean disconnect() {
        zzvG();
        zzaq(true);
        this.zzazK.zzh(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
        if (zzcv(1)) {
            if (bundle != null) {
                this.zzazQ.putAll(bundle);
            }
            if (zzvB()) {
                zzvE();
            }
        }
    }

    public void onConnectionSuspended(int i) {
        zzf(new ConnectionResult(8, null));
    }

    public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(T t) {
        this.zzazK.zzazd.zzaAl.add(t);
        return t;
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
        if (zzcv(1)) {
            zzb(connectionResult, api, i);
            if (zzvB()) {
                zzvE();
            }
        }
    }

    public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
