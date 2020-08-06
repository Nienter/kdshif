package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzzt extends zzzw {
    private final SparseArray<zza> zzayx = new SparseArray<>();

    private class zza implements GoogleApiClient.OnConnectionFailedListener {
        public final GoogleApiClient.OnConnectionFailedListener zzayA;
        public final int zzayy;
        public final GoogleApiClient zzayz;

        public zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zzayy = i;
            this.zzayz = googleApiClient;
            this.zzayA = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.append(str).append("GoogleApiClient #").print(this.zzayy);
            printWriter.println(":");
            this.zzayz.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            zzzt.this.zzb(connectionResult, this.zzayy);
        }

        public void zzuX() {
            this.zzayz.unregisterConnectionFailedListener(this);
            this.zzayz.disconnect();
        }
    }

    private zzzt(zzaax zzaax) {
        super(zzaax);
        this.zzaBs.zza("AutoManageHelper", (zzaaw) this);
    }

    public static zzzt zza(zzaav zzaav) {
        zzaax zzc = zzc(zzaav);
        zzzt zzzt = (zzzt) zzc.zza("AutoManageHelper", zzzt.class);
        return zzzt != null ? zzzt : new zzzt(zzc);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzayx.size()) {
                this.zzayx.valueAt(i2).dump(str, fileDescriptor, printWriter, strArr);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zzayx);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(" ").append(valueOf).toString());
        if (!this.zzayG) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.zzayx.size()) {
                    this.zzayx.valueAt(i2).zzayz.connect();
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzayx.size()) {
                this.zzayx.valueAt(i2).zzayz.disconnect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzb(googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzac.zza(this.zzayx.indexOfKey(i) < 0, (Object) new StringBuilder(54).append("Already managing a GoogleApiClient with id ").append(i).toString());
        Log.d("AutoManageHelper", new StringBuilder(54).append("starting AutoManage for client ").append(i).append(" ").append(this.mStarted).append(" ").append(this.zzayG).toString());
        this.zzayx.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.zzayG) {
            String valueOf = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 11).append("connecting ").append(valueOf).toString());
            googleApiClient.connect();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zza2 = this.zzayx.get(i);
        if (zza2 != null) {
            zzcu(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zza2.zzayA;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    public void zzcu(int i) {
        zza zza2 = this.zzayx.get(i);
        this.zzayx.remove(i);
        if (zza2 != null) {
            zza2.zzuX();
        }
    }

    /* access modifiers changed from: protected */
    public void zzuW() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzayx.size()) {
                this.zzayx.valueAt(i2).zzayz.connect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }
}
