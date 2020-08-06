package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzm implements Handler.Callback {
    private final Handler mHandler;
    private volatile boolean zzaEA = false;
    private final AtomicInteger zzaEB = new AtomicInteger(0);
    private boolean zzaEC = false;
    private final zza zzaEw;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzaEx = new ArrayList<>();
    final ArrayList<GoogleApiClient.ConnectionCallbacks> zzaEy = new ArrayList<>();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzaEz = new ArrayList<>();
    private final Object zzrN = new Object();

    public interface zza {
        boolean isConnected();

        Bundle zzud();
    }

    public zzm(Looper looper, zza zza2) {
        this.zzaEw = zza2;
        this.mHandler = new Handler(looper, this);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
            synchronized (this.zzrN) {
                if (this.zzaEA && this.zzaEw.isConnected() && this.zzaEx.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zzaEw.zzud());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), new Exception());
        return false;
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        zzac.zzw(connectionCallbacks);
        synchronized (this.zzrN) {
            contains = this.zzaEx.contains(connectionCallbacks);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        zzac.zzw(onConnectionFailedListener);
        synchronized (this.zzrN) {
            contains = this.zzaEz.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzac.zzw(connectionCallbacks);
        synchronized (this.zzrN) {
            if (this.zzaEx.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zzaEx.add(connectionCallbacks);
            }
        }
        if (this.zzaEw.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzw(onConnectionFailedListener);
        synchronized (this.zzrN) {
            if (this.zzaEz.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zzaEz.add(onConnectionFailedListener);
            }
        }
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzac.zzw(connectionCallbacks);
        synchronized (this.zzrN) {
            if (!this.zzaEx.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 52).append("unregisterConnectionCallbacks(): listener ").append(valueOf).append(" not found").toString());
            } else if (this.zzaEC) {
                this.zzaEy.add(connectionCallbacks);
            }
        }
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzw(onConnectionFailedListener);
        synchronized (this.zzrN) {
            if (!this.zzaEz.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
            }
        }
    }

    public void zzcP(int i) {
        boolean z = false;
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            z = true;
        }
        zzac.zza(z, (Object) "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzrN) {
            this.zzaEC = true;
            ArrayList arrayList = new ArrayList(this.zzaEx);
            int i2 = this.zzaEB.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.zzaEA || this.zzaEB.get() != i2) {
                    break;
                } else if (this.zzaEx.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.zzaEy.clear();
            this.zzaEC = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    public void zzo(ConnectionResult connectionResult) {
        zzac.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object) "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzrN) {
            ArrayList arrayList = new ArrayList(this.zzaEz);
            int i = this.zzaEB.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) it.next();
                if (this.zzaEA && this.zzaEB.get() == i) {
                    if (this.zzaEz.contains(onConnectionFailedListener)) {
                        onConnectionFailedListener.onConnectionFailed(connectionResult);
                    }
                }
            }
        }
    }

    public void zzq(Bundle bundle) {
        boolean z = true;
        zzac.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object) "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zzrN) {
            zzac.zzar(!this.zzaEC);
            this.mHandler.removeMessages(1);
            this.zzaEC = true;
            if (this.zzaEy.size() != 0) {
                z = false;
            }
            zzac.zzar(z);
            ArrayList arrayList = new ArrayList(this.zzaEx);
            int i = this.zzaEB.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.zzaEA || !this.zzaEw.isConnected() || this.zzaEB.get() != i) {
                    break;
                } else if (!this.zzaEy.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.zzaEy.clear();
            this.zzaEC = false;
        }
    }

    public void zzxq() {
        this.zzaEA = false;
        this.zzaEB.incrementAndGet();
    }

    public void zzxr() {
        this.zzaEA = true;
    }
}
