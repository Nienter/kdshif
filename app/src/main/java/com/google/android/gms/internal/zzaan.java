package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzaau;
import com.google.android.gms.internal.zzzv;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzaan implements zzaau, zzzz {
    private final Context mContext;
    private final Condition zzaAE;
    private final zzb zzaAF;
    final Map<Api.zzc<?>, ConnectionResult> zzaAG = new HashMap();
    /* access modifiers changed from: private */
    public volatile zzaam zzaAH;
    private ConnectionResult zzaAI = null;
    int zzaAJ;
    final zzaau.zza zzaAK;
    final Map<Api.zzc<?>, Api.zze> zzaAr;
    final Api.zza<? extends zzaxn, zzaxo> zzaxY;
    final zzaal zzazd;
    /* access modifiers changed from: private */
    public final Lock zzazn;
    final zzg zzazs;
    final Map<Api<?>, Integer> zzazu;
    private final zzc zzazw;

    static abstract class zza {
        private final zzaam zzaAL;

        protected zza(zzaam zzaam) {
            this.zzaAL = zzaam;
        }

        public final void zzc(zzaan zzaan) {
            zzaan.zzazn.lock();
            try {
                if (zzaan.zzaAH == this.zzaAL) {
                    zzvA();
                    zzaan.zzazn.unlock();
                }
            } finally {
                zzaan.zzazn.unlock();
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzvA();
    }

    final class zzb extends Handler {
        zzb(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    ((zza) message.obj).zzc(zzaan.this);
                    return;
                case 2:
                    throw ((RuntimeException) message.obj);
                default:
                    Log.w("GACStateManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                    return;
            }
        }
    }

    public zzaan(Context context, zzaal zzaal, Lock lock, Looper looper, zzc zzc, Map<Api.zzc<?>, Api.zze> map, zzg zzg, Map<Api<?>, Integer> map2, Api.zza<? extends zzaxn, zzaxo> zza2, ArrayList<zzzy> arrayList, zzaau.zza zza3) {
        this.mContext = context;
        this.zzazn = lock;
        this.zzazw = zzc;
        this.zzaAr = map;
        this.zzazs = zzg;
        this.zzazu = map2;
        this.zzaxY = zza2;
        this.zzazd = zzaal;
        this.zzaAK = zza3;
        Iterator<zzzy> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().zza(this);
        }
        this.zzaAF = new zzb(looper);
        this.zzaAE = lock.newCondition();
        this.zzaAH = new zzaak(this);
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzaAE.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzawX : this.zzaAI != null ? this.zzaAI : new ConnectionResult(13, null);
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
                nanos = this.zzaAE.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.zzawX : this.zzaAI != null ? this.zzaAI : new ConnectionResult(13, null);
    }

    public void connect() {
        this.zzaAH.connect();
    }

    public void disconnect() {
        if (this.zzaAH.disconnect()) {
            this.zzaAG.clear();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.zzaAH);
        for (Api next : this.zzazu.keySet()) {
            printWriter.append(str).append(next.getName()).println(":");
            this.zzaAr.get(next.zzuH()).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        Api.zzc<?> zzuH = api.zzuH();
        if (this.zzaAr.containsKey(zzuH)) {
            if (this.zzaAr.get(zzuH).isConnected()) {
                return ConnectionResult.zzawX;
            }
            if (this.zzaAG.containsKey(zzuH)) {
                return this.zzaAG.get(zzuH);
            }
        }
        return null;
    }

    public boolean isConnected() {
        return this.zzaAH instanceof zzaai;
    }

    public boolean isConnecting() {
        return this.zzaAH instanceof zzaaj;
    }

    public void onConnected(@Nullable Bundle bundle) {
        this.zzazn.lock();
        try {
            this.zzaAH.onConnected(bundle);
        } finally {
            this.zzazn.unlock();
        }
    }

    public void onConnectionSuspended(int i) {
        this.zzazn.lock();
        try {
            this.zzaAH.onConnectionSuspended(i);
        } finally {
            this.zzazn.unlock();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(@NonNull T t) {
        t.zzvf();
        return this.zzaAH.zza(t);
    }

    public void zza(@NonNull ConnectionResult connectionResult, @NonNull Api<?> api, int i) {
        this.zzazn.lock();
        try {
            this.zzaAH.zza(connectionResult, api, i);
        } finally {
            this.zzazn.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zza zza2) {
        this.zzaAF.sendMessage(this.zzaAF.obtainMessage(1, zza2));
    }

    /* access modifiers changed from: package-private */
    public void zza(RuntimeException runtimeException) {
        this.zzaAF.sendMessage(this.zzaAF.obtainMessage(2, runtimeException));
    }

    public boolean zza(zzabi zzabi) {
        return false;
    }

    public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(@NonNull T t) {
        t.zzvf();
        return this.zzaAH.zzb(t);
    }

    /* access modifiers changed from: package-private */
    public void zzh(ConnectionResult connectionResult) {
        this.zzazn.lock();
        try {
            this.zzaAI = connectionResult;
            this.zzaAH = new zzaak(this);
            this.zzaAH.begin();
            this.zzaAE.signalAll();
        } finally {
            this.zzazn.unlock();
        }
    }

    public void zzuN() {
    }

    /* access modifiers changed from: package-private */
    public void zzvO() {
        this.zzazn.lock();
        try {
            this.zzaAH = new zzaaj(this, this.zzazs, this.zzazu, this.zzazw, this.zzaxY, this.zzazn, this.mContext);
            this.zzaAH.begin();
            this.zzaAE.signalAll();
        } finally {
            this.zzazn.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzvP() {
        this.zzazn.lock();
        try {
            this.zzazd.zzvL();
            this.zzaAH = new zzaai(this);
            this.zzaAH.begin();
            this.zzaAE.signalAll();
        } finally {
            this.zzazn.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzvQ() {
        for (Api.zze disconnect : this.zzaAr.values()) {
            disconnect.disconnect();
        }
    }

    public void zzvj() {
        if (isConnected()) {
            ((zzaai) this.zzaAH).zzvz();
        }
    }
}
