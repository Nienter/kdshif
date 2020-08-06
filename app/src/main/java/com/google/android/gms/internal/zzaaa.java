package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p001v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzaau;
import com.google.android.gms.internal.zzzv;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzaaa implements zzaau {
    private final Context mContext;
    private final zzaal zzazd;
    /* access modifiers changed from: private */
    public final zzaan zzaze;
    /* access modifiers changed from: private */
    public final zzaan zzazf;
    private final Map<Api.zzc<?>, zzaan> zzazg;
    private final Set<zzabi> zzazh = Collections.newSetFromMap(new WeakHashMap());
    private final Api.zze zzazi;
    private Bundle zzazj;
    /* access modifiers changed from: private */
    public ConnectionResult zzazk = null;
    /* access modifiers changed from: private */
    public ConnectionResult zzazl = null;
    /* access modifiers changed from: private */
    public boolean zzazm = false;
    /* access modifiers changed from: private */
    public final Lock zzazn;
    private int zzazo = 0;
    private final Looper zzrx;

    private class zza implements zzaau.zza {
        private zza() {
        }

        public void zzc(int i, boolean z) {
            zzaaa.this.zzazn.lock();
            try {
                if (zzaaa.this.zzazm || zzaaa.this.zzazl == null || !zzaaa.this.zzazl.isSuccess()) {
                    boolean unused = zzaaa.this.zzazm = false;
                    zzaaa.this.zzb(i, z);
                    return;
                }
                boolean unused2 = zzaaa.this.zzazm = true;
                zzaaa.this.zzazf.onConnectionSuspended(i);
                zzaaa.this.zzazn.unlock();
            } finally {
                zzaaa.this.zzazn.unlock();
            }
        }

        public void zzc(@NonNull ConnectionResult connectionResult) {
            zzaaa.this.zzazn.lock();
            try {
                ConnectionResult unused = zzaaa.this.zzazk = connectionResult;
                zzaaa.this.zzvm();
            } finally {
                zzaaa.this.zzazn.unlock();
            }
        }

        public void zzo(@Nullable Bundle bundle) {
            zzaaa.this.zzazn.lock();
            try {
                zzaaa.this.zzn(bundle);
                ConnectionResult unused = zzaaa.this.zzazk = ConnectionResult.zzawX;
                zzaaa.this.zzvm();
            } finally {
                zzaaa.this.zzazn.unlock();
            }
        }
    }

    private class zzb implements zzaau.zza {
        private zzb() {
        }

        public void zzc(int i, boolean z) {
            zzaaa.this.zzazn.lock();
            try {
                if (zzaaa.this.zzazm) {
                    boolean unused = zzaaa.this.zzazm = false;
                    zzaaa.this.zzb(i, z);
                    return;
                }
                boolean unused2 = zzaaa.this.zzazm = true;
                zzaaa.this.zzaze.onConnectionSuspended(i);
                zzaaa.this.zzazn.unlock();
            } finally {
                zzaaa.this.zzazn.unlock();
            }
        }

        public void zzc(@NonNull ConnectionResult connectionResult) {
            zzaaa.this.zzazn.lock();
            try {
                ConnectionResult unused = zzaaa.this.zzazl = connectionResult;
                zzaaa.this.zzvm();
            } finally {
                zzaaa.this.zzazn.unlock();
            }
        }

        public void zzo(@Nullable Bundle bundle) {
            zzaaa.this.zzazn.lock();
            try {
                ConnectionResult unused = zzaaa.this.zzazl = ConnectionResult.zzawX;
                zzaaa.this.zzvm();
            } finally {
                zzaaa.this.zzazn.unlock();
            }
        }
    }

    private zzaaa(Context context, zzaal zzaal, Lock lock, Looper looper, zzc zzc, Map<Api.zzc<?>, Api.zze> map, Map<Api.zzc<?>, Api.zze> map2, zzg zzg, Api.zza<? extends zzaxn, zzaxo> zza2, Api.zze zze, ArrayList<zzzy> arrayList, ArrayList<zzzy> arrayList2, Map<Api<?>, Integer> map3, Map<Api<?>, Integer> map4) {
        this.mContext = context;
        this.zzazd = zzaal;
        this.zzazn = lock;
        this.zzrx = looper;
        this.zzazi = zze;
        this.zzaze = new zzaan(context, this.zzazd, lock, looper, zzc, map2, null, map4, null, arrayList2, new zza());
        this.zzazf = new zzaan(context, this.zzazd, lock, looper, zzc, map, zzg, map3, zza2, arrayList, new zzb());
        ArrayMap arrayMap = new ArrayMap();
        for (Api.zzc<?> put : map2.keySet()) {
            arrayMap.put(put, this.zzaze);
        }
        for (Api.zzc<?> put2 : map.keySet()) {
            arrayMap.put(put2, this.zzazf);
        }
        this.zzazg = Collections.unmodifiableMap(arrayMap);
    }

    public static zzaaa zza(Context context, zzaal zzaal, Lock lock, Looper looper, zzc zzc, Map<Api.zzc<?>, Api.zze> map, zzg zzg, Map<Api<?>, Integer> map2, Api.zza<? extends zzaxn, zzaxo> zza2, ArrayList<zzzy> arrayList) {
        Api.zze zze = null;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (Map.Entry next : map.entrySet()) {
            Api.zze zze2 = (Api.zze) next.getValue();
            if (zze2.zzqS()) {
                zze = zze2;
            }
            if (zze2.zzqD()) {
                arrayMap.put((Api.zzc) next.getKey(), zze2);
            } else {
                arrayMap2.put((Api.zzc) next.getKey(), zze2);
            }
        }
        zzac.zza(!arrayMap.isEmpty(), (Object) "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api next2 : map2.keySet()) {
            Api.zzc<?> zzuH = next2.zzuH();
            if (arrayMap.containsKey(zzuH)) {
                arrayMap3.put(next2, map2.get(next2));
            } else if (arrayMap2.containsKey(zzuH)) {
                arrayMap4.put(next2, map2.get(next2));
            } else {
                throw new IllegalStateException("Each API in the apiTypeMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator<zzzy> it = arrayList.iterator();
        while (it.hasNext()) {
            zzzy next3 = it.next();
            if (arrayMap3.containsKey(next3.zzawb)) {
                arrayList2.add(next3);
            } else if (arrayMap4.containsKey(next3.zzawb)) {
                arrayList3.add(next3);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the apiTypeMap");
            }
        }
        return new zzaaa(context, zzaal, lock, looper, zzc, arrayMap, arrayMap2, zzg, zza2, zze, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private void zza(ConnectionResult connectionResult) {
        switch (this.zzazo) {
            case 1:
                break;
            case 2:
                this.zzazd.zzc(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zzvo();
        this.zzazo = 0;
    }

    /* access modifiers changed from: private */
    public void zzb(int i, boolean z) {
        this.zzazd.zzc(i, z);
        this.zzazl = null;
        this.zzazk = null;
    }

    private static boolean zzb(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    private boolean zzc(zzzv.zza<? extends Result, ? extends Api.zzb> zza2) {
        Api.zzc<? extends Api.zzb> zzuH = zza2.zzuH();
        zzac.zzb(this.zzazg.containsKey(zzuH), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        return this.zzazg.get(zzuH).equals(this.zzazf);
    }

    /* access modifiers changed from: private */
    public void zzn(Bundle bundle) {
        if (this.zzazj == null) {
            this.zzazj = bundle;
        } else if (bundle != null) {
            this.zzazj.putAll(bundle);
        }
    }

    private void zzvl() {
        this.zzazl = null;
        this.zzazk = null;
        this.zzaze.connect();
        this.zzazf.connect();
    }

    /* access modifiers changed from: private */
    public void zzvm() {
        if (zzb(this.zzazk)) {
            if (zzb(this.zzazl) || zzvp()) {
                zzvn();
            } else if (this.zzazl == null) {
            } else {
                if (this.zzazo == 1) {
                    zzvo();
                    return;
                }
                zza(this.zzazl);
                this.zzaze.disconnect();
            }
        } else if (this.zzazk != null && zzb(this.zzazl)) {
            this.zzazf.disconnect();
            zza(this.zzazk);
        } else if (this.zzazk != null && this.zzazl != null) {
            ConnectionResult connectionResult = this.zzazk;
            if (this.zzazf.zzaAJ < this.zzaze.zzaAJ) {
                connectionResult = this.zzazl;
            }
            zza(connectionResult);
        }
    }

    private void zzvn() {
        switch (this.zzazo) {
            case 1:
                break;
            case 2:
                this.zzazd.zzo(this.zzazj);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                break;
        }
        zzvo();
        this.zzazo = 0;
    }

    private void zzvo() {
        for (zzabi zzqR : this.zzazh) {
            zzqR.zzqR();
        }
        this.zzazh.clear();
    }

    private boolean zzvp() {
        return this.zzazl != null && this.zzazl.getErrorCode() == 4;
    }

    @Nullable
    private PendingIntent zzvq() {
        if (this.zzazi == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, this.zzazd.getSessionId(), this.zzazi.zzqT(), 134217728);
    }

    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public void connect() {
        this.zzazo = 2;
        this.zzazm = false;
        zzvl();
    }

    public void disconnect() {
        this.zzazl = null;
        this.zzazk = null;
        this.zzazo = 0;
        this.zzaze.disconnect();
        this.zzazf.disconnect();
        zzvo();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.zzazf.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.zzaze.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return this.zzazg.get(api.zzuH()).equals(this.zzazf) ? zzvp() ? new ConnectionResult(4, zzvq()) : this.zzazf.getConnectionResult(api) : this.zzaze.getConnectionResult(api);
    }

    public boolean isConnected() {
        boolean z = true;
        this.zzazn.lock();
        try {
            if (!this.zzaze.isConnected() || (!zzvk() && !zzvp() && this.zzazo != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.zzazn.unlock();
        }
    }

    public boolean isConnecting() {
        this.zzazn.lock();
        try {
            return this.zzazo == 2;
        } finally {
            this.zzazn.unlock();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(@NonNull T t) {
        if (!zzc((zzzv.zza<? extends Result, ? extends Api.zzb>) t)) {
            return this.zzaze.zza(t);
        }
        if (!zzvp()) {
            return this.zzazf.zza(t);
        }
        t.zzA(new Status(4, null, zzvq()));
        return t;
    }

    public boolean zza(zzabi zzabi) {
        this.zzazn.lock();
        try {
            if ((isConnecting() || isConnected()) && !zzvk()) {
                this.zzazh.add(zzabi);
                if (this.zzazo == 0) {
                    this.zzazo = 1;
                }
                this.zzazl = null;
                this.zzazf.connect();
                return true;
            }
            this.zzazn.unlock();
            return false;
        } finally {
            this.zzazn.unlock();
        }
    }

    public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(@NonNull T t) {
        if (!zzc((zzzv.zza<? extends Result, ? extends Api.zzb>) t)) {
            return this.zzaze.zzb(t);
        }
        if (!zzvp()) {
            return this.zzazf.zzb(t);
        }
        t.zzA(new Status(4, null, zzvq()));
        return t;
    }

    public void zzuN() {
        this.zzazn.lock();
        try {
            boolean isConnecting = isConnecting();
            this.zzazf.disconnect();
            this.zzazl = new ConnectionResult(4);
            if (isConnecting) {
                new Handler(this.zzrx).post(new Runnable() {
                    public void run() {
                        zzaaa.this.zzazn.lock();
                        try {
                            zzaaa.this.zzvm();
                        } finally {
                            zzaaa.this.zzazn.unlock();
                        }
                    }
                });
            } else {
                zzvo();
            }
        } finally {
            this.zzazn.unlock();
        }
    }

    public void zzvj() {
        this.zzaze.zzvj();
        this.zzazf.zzvj();
    }

    public boolean zzvk() {
        return this.zzazf.isConnected();
    }
}
