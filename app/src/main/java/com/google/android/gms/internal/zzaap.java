package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzaaz;
import com.google.android.gms.internal.zzabj;
import com.google.android.gms.internal.zzzq;
import com.google.android.gms.internal.zzzv;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class zzaap implements Handler.Callback {
    public static final Status zzaAO = new Status(4, "Sign-out occurred while this API call was in progress.");
    /* access modifiers changed from: private */
    public static final Status zzaAP = new Status(4, "The user must be signed in to make this API call.");
    private static zzaap zzaAR;
    /* access modifiers changed from: private */
    public static final Object zztU = new Object();
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    public long zzaAQ = 10000;
    /* access modifiers changed from: private */
    public int zzaAS = -1;
    private final AtomicInteger zzaAT = new AtomicInteger(1);
    private final AtomicInteger zzaAU = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public zzaae zzaAV = null;
    /* access modifiers changed from: private */
    public final Set<zzzs<?>> zzaAW = new com.google.android.gms.common.util.zza();
    private final Set<zzzs<?>> zzaAX = new com.google.android.gms.common.util.zza();
    /* access modifiers changed from: private */
    public long zzaAn = 120000;
    /* access modifiers changed from: private */
    public long zzaAo = 5000;
    /* access modifiers changed from: private */
    public final GoogleApiAvailability zzaxX;
    /* access modifiers changed from: private */
    public final Map<zzzs<?>, zza<?>> zzazt = new ConcurrentHashMap(5, 0.75f, 1);

    public class zza<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzzz {
        private final Queue<zzzq> zzaAY = new LinkedList();
        private final Api.zzb zzaAZ;
        private boolean zzaAm;
        private final zzaad zzaBa;
        private final Set<zzzu> zzaBb = new HashSet();
        private final Map<zzaaz.zzb<?>, zzabf> zzaBc = new HashMap();
        private final int zzaBd;
        private final zzabj zzaBe;
        private ConnectionResult zzaBf = null;
        private final zzzs<O> zzaxH;
        private final Api.zze zzazq;

        @WorkerThread
        public zza(zzc<O> zzc) {
            this.zzazq = zzc.buildApiClient(zzaap.this.mHandler.getLooper(), this);
            if (this.zzazq instanceof zzal) {
                this.zzaAZ = ((zzal) this.zzazq).zzxG();
            } else {
                this.zzaAZ = this.zzazq;
            }
            this.zzaxH = zzc.getApiKey();
            this.zzaBa = new zzaad();
            this.zzaBd = zzc.getInstanceId();
            if (this.zzazq.zzqD()) {
                this.zzaBe = zzc.createSignInCoordinator(zzaap.this.mContext, zzaap.this.mHandler);
            } else {
                this.zzaBe = null;
            }
        }

        @WorkerThread
        private void zzb(zzzq zzzq) {
            zzzq.zza(this.zzaBa, zzqD());
            try {
                zzzq.zza((zza<?>) this);
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zzazq.disconnect();
            }
        }

        @WorkerThread
        private void zzj(ConnectionResult connectionResult) {
            for (zzzu zza : this.zzaBb) {
                zza.zza(this.zzaxH, connectionResult);
            }
            this.zzaBb.clear();
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void zzvZ() {
            zzwd();
            zzj(ConnectionResult.zzawX);
            zzwf();
            Iterator<zzabf> it = this.zzaBc.values().iterator();
            while (it.hasNext()) {
                it.next();
                try {
                    new TaskCompletionSource();
                } catch (DeadObjectException e) {
                    onConnectionSuspended(1);
                    this.zzazq.disconnect();
                } catch (RemoteException e2) {
                }
            }
            zzwb();
            zzwg();
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void zzwa() {
            zzwd();
            this.zzaAm = true;
            this.zzaBa.zzvw();
            zzaap.this.mHandler.sendMessageDelayed(Message.obtain(zzaap.this.mHandler, 7, this.zzaxH), zzaap.this.zzaAo);
            zzaap.this.mHandler.sendMessageDelayed(Message.obtain(zzaap.this.mHandler, 9, this.zzaxH), zzaap.this.zzaAn);
            int unused = zzaap.this.zzaAS = -1;
        }

        @WorkerThread
        private void zzwb() {
            while (this.zzazq.isConnected() && !this.zzaAY.isEmpty()) {
                zzb(this.zzaAY.remove());
            }
        }

        @WorkerThread
        private void zzwf() {
            if (this.zzaAm) {
                zzaap.this.mHandler.removeMessages(9, this.zzaxH);
                zzaap.this.mHandler.removeMessages(7, this.zzaxH);
                this.zzaAm = false;
            }
        }

        private void zzwg() {
            zzaap.this.mHandler.removeMessages(10, this.zzaxH);
            zzaap.this.mHandler.sendMessageDelayed(zzaap.this.mHandler.obtainMessage(10, this.zzaxH), zzaap.this.zzaAQ);
        }

        @WorkerThread
        public void connect() {
            zzac.zza(zzaap.this.mHandler);
            if (!this.zzazq.isConnected() && !this.zzazq.isConnecting()) {
                if (this.zzazq.zzuI() && zzaap.this.zzaAS != 0) {
                    int unused = zzaap.this.zzaAS = zzaap.this.zzaxX.isGooglePlayServicesAvailable(zzaap.this.mContext);
                    if (zzaap.this.zzaAS != 0) {
                        onConnectionFailed(new ConnectionResult(zzaap.this.zzaAS, null));
                        return;
                    }
                }
                zzb zzb = new zzb(this.zzazq, this.zzaxH);
                if (this.zzazq.zzqD()) {
                    this.zzaBe.zza(zzb);
                }
                this.zzazq.zza(zzb);
            }
        }

        public int getInstanceId() {
            return this.zzaBd;
        }

        /* access modifiers changed from: package-private */
        public boolean isConnected() {
            return this.zzazq.isConnected();
        }

        public void onConnected(@Nullable Bundle bundle) {
            if (Looper.myLooper() == zzaap.this.mHandler.getLooper()) {
                zzvZ();
            } else {
                zzaap.this.mHandler.post(new Runnable() {
                    public void run() {
                        zza.this.zzvZ();
                    }
                });
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x006c, code lost:
            if (r5.zzaBg.zzc(r6, r5.zzaBd) != false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0074, code lost:
            if (r6.getErrorCode() != 18) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0076, code lost:
            r5.zzaAm = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x007b, code lost:
            if (r5.zzaAm == false) goto L_0x009a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x007d, code lost:
            com.google.android.gms.internal.zzaap.zza(r5.zzaBg).sendMessageDelayed(android.os.Message.obtain(com.google.android.gms.internal.zzaap.zza(r5.zzaBg), 7, r5.zzaxH), com.google.android.gms.internal.zzaap.zzc(r5.zzaBg));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x009a, code lost:
            r2 = java.lang.String.valueOf(r5.zzaxH.zzuV());
            zzC(new com.google.android.gms.common.api.Status(17, new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 38).append("API: ").append(r2).append(" is not available on this device.").toString()));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
            return;
         */
        @WorkerThread
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzac.zza(zzaap.this.mHandler);
            if (this.zzaBe != null) {
                this.zzaBe.zzwr();
            }
            zzwd();
            int unused = zzaap.this.zzaAS = -1;
            zzj(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zzC(zzaap.zzaAP);
            } else if (this.zzaAY.isEmpty()) {
                this.zzaBf = connectionResult;
            } else {
                synchronized (zzaap.zztU) {
                    if (zzaap.this.zzaAV != null && zzaap.this.zzaAW.contains(this.zzaxH)) {
                        zzaap.this.zzaAV.zzb(connectionResult, this.zzaBd);
                    }
                }
            }
        }

        public void onConnectionSuspended(int i) {
            if (Looper.myLooper() == zzaap.this.mHandler.getLooper()) {
                zzwa();
            } else {
                zzaap.this.mHandler.post(new Runnable() {
                    public void run() {
                        zza.this.zzwa();
                    }
                });
            }
        }

        @WorkerThread
        public void resume() {
            zzac.zza(zzaap.this.mHandler);
            if (this.zzaAm) {
                connect();
            }
        }

        @WorkerThread
        public void signOut() {
            zzac.zza(zzaap.this.mHandler);
            zzC(zzaap.zzaAO);
            this.zzaBa.zzvv();
            for (zzaaz.zzb<?> zze : this.zzaBc.keySet()) {
                zza(new zzzq.zze(zze, new TaskCompletionSource()));
            }
            this.zzazq.disconnect();
        }

        @WorkerThread
        public void zzC(Status status) {
            zzac.zza(zzaap.this.mHandler);
            for (zzzq zzy : this.zzaAY) {
                zzy.zzy(status);
            }
            this.zzaAY.clear();
        }

        public void zza(final ConnectionResult connectionResult, Api<?> api, int i) {
            if (Looper.myLooper() == zzaap.this.mHandler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                zzaap.this.mHandler.post(new Runnable() {
                    public void run() {
                        zza.this.onConnectionFailed(connectionResult);
                    }
                });
            }
        }

        @WorkerThread
        public void zza(zzzq zzzq) {
            zzac.zza(zzaap.this.mHandler);
            if (this.zzazq.isConnected()) {
                zzb(zzzq);
                zzwg();
                return;
            }
            this.zzaAY.add(zzzq);
            if (this.zzaBf == null || !this.zzaBf.hasResolution()) {
                connect();
            } else {
                onConnectionFailed(this.zzaBf);
            }
        }

        @WorkerThread
        public void zzb(zzzu zzzu) {
            zzac.zza(zzaap.this.mHandler);
            this.zzaBb.add(zzzu);
        }

        @WorkerThread
        public void zzi(@NonNull ConnectionResult connectionResult) {
            zzac.zza(zzaap.this.mHandler);
            this.zzazq.disconnect();
            onConnectionFailed(connectionResult);
        }

        public boolean zzqD() {
            return this.zzazq.zzqD();
        }

        @WorkerThread
        public void zzvJ() {
            zzac.zza(zzaap.this.mHandler);
            if (this.zzaAm) {
                zzwf();
                zzC(zzaap.this.zzaxX.isGooglePlayServicesAvailable(zzaap.this.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.zzazq.disconnect();
            }
        }

        public Api.zze zzvr() {
            return this.zzazq;
        }

        public Map<zzaaz.zzb<?>, zzabf> zzwc() {
            return this.zzaBc;
        }

        @WorkerThread
        public void zzwd() {
            zzac.zza(zzaap.this.mHandler);
            this.zzaBf = null;
        }

        @WorkerThread
        public ConnectionResult zzwe() {
            zzac.zza(zzaap.this.mHandler);
            return this.zzaBf;
        }

        @WorkerThread
        public void zzwh() {
            zzac.zza(zzaap.this.mHandler);
            if (this.zzazq.isConnected() && this.zzaBc.size() == 0) {
                if (this.zzaBa.zzvu()) {
                    zzwg();
                } else {
                    this.zzazq.disconnect();
                }
            }
        }
    }

    private class zzb implements zzf.C1686zzf, zzabj.zza {
        /* access modifiers changed from: private */
        public boolean zzaBj = false;
        private Set<Scope> zzajm = null;
        /* access modifiers changed from: private */
        public final zzzs<?> zzaxH;
        private zzr zzazW = null;
        /* access modifiers changed from: private */
        public final Api.zze zzazq;

        public zzb(Api.zze zze, zzzs<?> zzzs) {
            this.zzazq = zze;
            this.zzaxH = zzzs;
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void zzwi() {
            if (this.zzaBj && this.zzazW != null) {
                this.zzazq.zza(this.zzazW, this.zzajm);
            }
        }

        @WorkerThread
        public void zzb(zzr zzr, Set<Scope> set) {
            if (zzr == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zzi(new ConnectionResult(4));
                return;
            }
            this.zzazW = zzr;
            this.zzajm = set;
            zzwi();
        }

        public void zzg(@NonNull final ConnectionResult connectionResult) {
            zzaap.this.mHandler.post(new Runnable() {
                public void run() {
                    if (connectionResult.isSuccess()) {
                        boolean unused = zzb.this.zzaBj = true;
                        if (zzb.this.zzazq.zzqD()) {
                            zzb.this.zzwi();
                        } else {
                            zzb.this.zzazq.zza(null, Collections.emptySet());
                        }
                    } else {
                        ((zza) zzaap.this.zzazt.get(zzb.this.zzaxH)).onConnectionFailed(connectionResult);
                    }
                }
            });
        }

        @WorkerThread
        public void zzi(ConnectionResult connectionResult) {
            ((zza) zzaap.this.zzazt.get(this.zzaxH)).zzi(connectionResult);
        }
    }

    private zzaap(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.mContext = context;
        this.mHandler = new Handler(looper, this);
        this.zzaxX = googleApiAvailability;
    }

    @WorkerThread
    private void zza(int i, ConnectionResult connectionResult) {
        zza zza2;
        Iterator<zza<?>> it = this.zzazt.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                zza2 = null;
                break;
            }
            zza2 = it.next();
            if (zza2.getInstanceId() == i) {
                break;
            }
        }
        if (zza2 != null) {
            String valueOf = String.valueOf(this.zzaxX.getErrorString(connectionResult.getErrorCode()));
            String valueOf2 = String.valueOf(connectionResult.getErrorMessage());
            zza2.zzC(new Status(17, new StringBuilder(String.valueOf(valueOf).length() + 69 + String.valueOf(valueOf2).length()).append("Error resolution was canceled by the user, original error message: ").append(valueOf).append(": ").append(valueOf2).toString()));
            return;
        }
        Log.wtf("GoogleApiManager", new StringBuilder(76).append("Could not find API instance ").append(i).append(" while trying to fail enqueued calls.").toString(), new Exception());
    }

    @WorkerThread
    private void zza(zzabd zzabd) {
        zza zza2 = this.zzazt.get(zzabd.zzaBF.getApiKey());
        if (zza2 == null) {
            zzb(zzabd.zzaBF);
            zza2 = this.zzazt.get(zzabd.zzaBF.getApiKey());
        }
        if (!zza2.zzqD() || this.zzaAU.get() == zzabd.zzaBE) {
            zza2.zza(zzabd.zzaBD);
            return;
        }
        zzabd.zzaBD.zzy(zzaAO);
        zza2.signOut();
    }

    @WorkerThread
    private void zza(zzzu zzzu) {
        for (zzzs next : zzzu.zzuY()) {
            zza zza2 = this.zzazt.get(next);
            if (zza2 == null) {
                zzzu.zza(next, new ConnectionResult(13));
                return;
            } else if (zza2.isConnected()) {
                zzzu.zza(next, ConnectionResult.zzawX);
            } else if (zza2.zzwe() != null) {
                zzzu.zza(next, zza2.zzwe());
            } else {
                zza2.zzb(zzzu);
            }
        }
    }

    public static zzaap zzax(Context context) {
        zzaap zzaap;
        synchronized (zztU) {
            if (zzaAR == null) {
                zzaAR = new zzaap(context.getApplicationContext(), zzvT(), GoogleApiAvailability.getInstance());
            }
            zzaap = zzaAR;
        }
        return zzaap;
    }

    @WorkerThread
    private void zzb(zzc<?> zzc) {
        zzzs<?> apiKey = zzc.getApiKey();
        if (!this.zzazt.containsKey(apiKey)) {
            this.zzazt.put(apiKey, new zza(zzc));
        }
        zza zza2 = this.zzazt.get(apiKey);
        if (zza2.zzqD()) {
            this.zzaAX.add(apiKey);
        }
        zza2.connect();
    }

    public static zzaap zzvS() {
        zzaap zzaap;
        synchronized (zztU) {
            zzac.zzb(zzaAR, (Object) "Must guarantee manager is non-null before using getInstance");
            zzaap = zzaAR;
        }
        return zzaap;
    }

    private static Looper zzvT() {
        HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    @WorkerThread
    private void zzvV() {
        for (zza next : this.zzazt.values()) {
            next.zzwd();
            next.connect();
        }
    }

    @WorkerThread
    private void zzvW() {
        for (zzzs<?> remove : this.zzaAX) {
            this.zzazt.remove(remove).signOut();
        }
        this.zzaAX.clear();
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                zza((zzzu) message.obj);
                break;
            case 2:
                zzvV();
                break;
            case 3:
            case 6:
            case 11:
                zza((zzabd) message.obj);
                break;
            case 4:
                zza(message.arg1, (ConnectionResult) message.obj);
                break;
            case 5:
                zzb((zzc<?>) (zzc) message.obj);
                break;
            case 7:
                if (this.zzazt.containsKey(message.obj)) {
                    this.zzazt.get(message.obj).resume();
                    break;
                }
                break;
            case 8:
                zzvW();
                break;
            case 9:
                if (this.zzazt.containsKey(message.obj)) {
                    this.zzazt.get(message.obj).zzvJ();
                    break;
                }
                break;
            case 10:
                if (this.zzazt.containsKey(message.obj)) {
                    this.zzazt.get(message.obj).zzwh();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                return false;
        }
        return true;
    }

    public <O extends Api.ApiOptions> Task<Void> zza(@NonNull zzc<O> zzc, @NonNull zzaaz.zzb<?> zzb2) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(11, new zzabd(new zzzq.zze(zzb2, taskCompletionSource), this.zzaAU.get(), zzc)));
        return taskCompletionSource.getTask();
    }

    public <O extends Api.ApiOptions> Task<Void> zza(@NonNull zzc<O> zzc, @NonNull zzabe<Api.zzb, ?> zzabe, @NonNull zzabr<Api.zzb, ?> zzabr) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, new zzabd(new zzzq.zzc(new zzabf(zzabe, zzabr), taskCompletionSource), this.zzaAU.get(), zzc)));
        return taskCompletionSource.getTask();
    }

    public Task<Void> zza(Iterable<zzc<?>> iterable) {
        zzzu zzzu = new zzzu(iterable);
        for (zzc<?> apiKey : iterable) {
            zza zza2 = this.zzazt.get(apiKey.getApiKey());
            if (zza2 != null) {
                if (!zza2.isConnected()) {
                }
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, zzzu));
            return zzzu.getTask();
        }
        zzzu.zzuZ();
        return zzzu.getTask();
    }

    public void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(4, i, 0, connectionResult));
        }
    }

    public void zza(zzc<?> zzc) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, zzc));
    }

    public <O extends Api.ApiOptions, TResult> void zza(zzc<O> zzc, int i, zzabn<Api.zzb, TResult> zzabn, TaskCompletionSource<TResult> taskCompletionSource, zzabk zzabk) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new zzabd(new zzzq.zzd(i, zzabn, taskCompletionSource, zzabk), this.zzaAU.get(), zzc)));
    }

    public <O extends Api.ApiOptions> void zza(zzc<O> zzc, int i, zzzv.zza<? extends Result, Api.zzb> zza2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new zzabd(new zzzq.zzb(i, zza2), this.zzaAU.get(), zzc)));
    }

    public void zza(@NonNull zzaae zzaae) {
        synchronized (zztU) {
            if (this.zzaAV != zzaae) {
                this.zzaAV = zzaae;
                this.zzaAW.clear();
                this.zzaAW.addAll(zzaae.zzvx());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(@NonNull zzaae zzaae) {
        synchronized (zztU) {
            if (this.zzaAV == zzaae) {
                this.zzaAV = null;
                this.zzaAW.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzc(ConnectionResult connectionResult, int i) {
        if (!connectionResult.hasResolution() && !this.zzaxX.isUserResolvableError(connectionResult.getErrorCode())) {
            return false;
        }
        this.zzaxX.zza(this.mContext, connectionResult, i);
        return true;
    }

    public void zzuW() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
    }

    public int zzvU() {
        return this.zzaAT.getAndIncrement();
    }
}
