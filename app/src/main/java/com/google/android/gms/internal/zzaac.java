package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p001v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzzv;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzaac implements zzaau {
    private final zzaap zzaxK;
    /* access modifiers changed from: private */
    public ConnectionResult zzazA;
    /* access modifiers changed from: private */
    public final Lock zzazn;
    /* access modifiers changed from: private */
    public final zzg zzazs;
    /* access modifiers changed from: private */
    public final Map<Api.zzc<?>, zzc<?>> zzazt = new HashMap();
    /* access modifiers changed from: private */
    public final Map<Api<?>, Integer> zzazu;
    /* access modifiers changed from: private */
    public final zzaal zzazv;
    /* access modifiers changed from: private */
    public final com.google.android.gms.common.zzc zzazw;
    /* access modifiers changed from: private */
    public final Condition zzazx;
    /* access modifiers changed from: private */
    public boolean zzazy;
    /* access modifiers changed from: private */
    public Map<zzzs<?>, ConnectionResult> zzazz;
    private final Looper zzrx;

    private class zza implements OnFailureListener, OnSuccessListener<Void> {
        private zza() {
        }

        @Nullable
        private ConnectionResult zzvs() {
            ConnectionResult connectionResult = null;
            int i = 0;
            for (Api api : zzaac.this.zzazu.keySet()) {
                ConnectionResult connectionResult2 = (ConnectionResult) zzaac.this.zzazz.get(((zzc) zzaac.this.zzazt.get(api.zzuH())).getApiKey());
                if (!connectionResult2.isSuccess()) {
                    int intValue = ((Integer) zzaac.this.zzazu.get(api)).intValue();
                    if (intValue != 2 && (intValue != 1 || connectionResult2.hasResolution() || zzaac.this.zzazw.isUserResolvableError(connectionResult2.getErrorCode()))) {
                        int priority = api.zzuF().getPriority();
                        if (connectionResult != null && i <= priority) {
                            priority = i;
                            connectionResult2 = connectionResult;
                        }
                        i = priority;
                        connectionResult = connectionResult2;
                    }
                }
            }
            return connectionResult;
        }

        private void zzvt() {
            if (zzaac.this.zzazs == null) {
                zzaac.this.zzazv.zzaAs = Collections.emptySet();
                return;
            }
            HashSet hashSet = new HashSet(zzaac.this.zzazs.zzxe());
            Map<Api<?>, zzg.zza> zzxg = zzaac.this.zzazs.zzxg();
            for (Api next : zzxg.keySet()) {
                ConnectionResult connectionResult = (ConnectionResult) zzaac.this.zzazz.get(((zzc) zzaac.this.zzazt.get(next.zzuH())).getApiKey());
                if (connectionResult != null && connectionResult.isSuccess()) {
                    hashSet.addAll(zzxg.get(next).zzajm);
                }
            }
            zzaac.this.zzazv.zzaAs = hashSet;
        }

        public void onFailure(@NonNull Exception exc) {
            zzb zzb = (zzb) exc;
            zzaac.this.zzazn.lock();
            try {
                Map unused = zzaac.this.zzazz = zzb.zzuK();
                ConnectionResult unused2 = zzaac.this.zzazA = zzvs();
                if (zzaac.this.zzazA == null) {
                    zzvt();
                    zzaac.this.zzazv.zzo(null);
                } else {
                    boolean unused3 = zzaac.this.zzazy = false;
                    zzaac.this.zzazv.zzc(zzaac.this.zzazA);
                }
                zzaac.this.zzazx.signalAll();
            } finally {
                zzaac.this.zzazn.unlock();
            }
        }

        /* renamed from: zza */
        public void onSuccess(Void voidR) {
            zzaac.this.zzazn.lock();
            try {
                Map unused = zzaac.this.zzazz = new ArrayMap(zzaac.this.zzazt.size());
                for (Api.zzc zzc : zzaac.this.zzazt.keySet()) {
                    zzaac.this.zzazz.put(((zzc) zzaac.this.zzazt.get(zzc)).getApiKey(), ConnectionResult.zzawX);
                }
                zzvt();
                zzaac.this.zzazv.zzo(null);
                zzaac.this.zzazx.signalAll();
            } finally {
                zzaac.this.zzazn.unlock();
            }
        }
    }

    public zzaac(Context context, Lock lock, Looper looper, com.google.android.gms.common.zzc zzc, Map<Api.zzc<?>, Api.zze> map, zzg zzg, Map<Api<?>, Integer> map2, Api.zza<? extends zzaxn, zzaxo> zza2, ArrayList<zzzy> arrayList, zzaal zzaal) {
        this.zzazn = lock;
        this.zzrx = looper;
        this.zzazx = lock.newCondition();
        this.zzazw = zzc;
        this.zzazv = zzaal;
        this.zzazu = map2;
        this.zzazs = zzg;
        HashMap hashMap = new HashMap();
        for (Api next : map2.keySet()) {
            hashMap.put(next.zzuH(), next);
        }
        HashMap hashMap2 = new HashMap();
        Iterator<zzzy> it = arrayList.iterator();
        while (it.hasNext()) {
            zzzy next2 = it.next();
            hashMap2.put(next2.zzawb, next2);
        }
        for (Map.Entry next3 : map.entrySet()) {
            Api api = (Api) hashMap.get(next3.getKey());
            this.zzazt.put((Api.zzc) next3.getKey(), new zzaab(context, api, looper, (Api.zze) next3.getValue(), (zzzy) hashMap2.get(api), zzg, zza2));
        }
        this.zzaxK = zzaap.zzvS();
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzazx.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzawX : this.zzazA != null ? this.zzazA : new ConnectionResult(13, null);
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            } else {
                nanos = this.zzazx.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.zzawX : this.zzazA != null ? this.zzazA : new ConnectionResult(13, null);
    }

    public void connect() {
        this.zzazn.lock();
        try {
            if (!this.zzazy) {
                this.zzazy = true;
                this.zzazz = null;
                this.zzazA = null;
                zza zza2 = new zza();
                zzact zzact = new zzact(this.zzrx);
                this.zzaxK.zza((Iterable<zzc<?>>) this.zzazt.values()).addOnSuccessListener((Executor) zzact, zza2).addOnFailureListener((Executor) zzact, (OnFailureListener) zza2);
                this.zzazn.unlock();
            }
        } finally {
            this.zzazn.unlock();
        }
    }

    public void disconnect() {
        this.zzazn.lock();
        try {
            this.zzazy = false;
            this.zzazz = null;
            this.zzazA = null;
            this.zzazx.signalAll();
        } finally {
            this.zzazn.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.zzazn.lock();
        try {
            if (((zzaab) this.zzazt.get(api.zzuH())).zzvr().isConnected()) {
                return ConnectionResult.zzawX;
            }
            if (this.zzazz != null) {
                ConnectionResult connectionResult = this.zzazz.get(this.zzazt.get(api.zzuH()).getApiKey());
                this.zzazn.unlock();
                return connectionResult;
            }
            this.zzazn.unlock();
            return null;
        } finally {
            this.zzazn.unlock();
        }
    }

    public boolean isConnected() {
        this.zzazn.lock();
        try {
            return this.zzazz != null && this.zzazA == null;
        } finally {
            this.zzazn.unlock();
        }
    }

    public boolean isConnecting() {
        this.zzazn.lock();
        try {
            return this.zzazz == null && this.zzazy;
        } finally {
            this.zzazn.unlock();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(@NonNull T t) {
        this.zzazv.zzaAx.zzb(t);
        return this.zzazt.get(t.zzuH()).doRead(t);
    }

    public boolean zza(zzabi zzabi) {
        return false;
    }

    public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(@NonNull T t) {
        this.zzazv.zzaAx.zzb(t);
        return this.zzazt.get(t.zzuH()).doWrite(t);
    }

    public void zzuN() {
    }

    public void zzvj() {
    }
}
