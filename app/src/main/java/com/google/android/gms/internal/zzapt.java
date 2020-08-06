package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzaf;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class zzapt {

    static class zza implements zzf.zzb, zzf.zzc {
        private final String packageName;
        protected zzapu zzbfB;
        private final String zzbfC;
        private final LinkedBlockingQueue<zzaf.zza> zzbfD;
        private final HandlerThread zzbfE = new HandlerThread("GassClient");

        public zza(Context context, String str, String str2) {
            this.packageName = str;
            this.zzbfC = str2;
            this.zzbfE.start();
            this.zzbfB = new zzapu(context, this.zzbfE.getLooper(), this, this);
            this.zzbfD = new LinkedBlockingQueue<>();
            connect();
        }

        /* access modifiers changed from: protected */
        public void connect() {
            this.zzbfB.zzwT();
        }

        public void onConnected(Bundle bundle) {
            zzapz zzFW = zzFW();
            if (zzFW != null) {
                try {
                    this.zzbfD.put(zzFW.zza(new zzapv(this.packageName, this.zzbfC)).zzFZ());
                } catch (Throwable th) {
                } finally {
                    zziY();
                    this.zzbfE.quit();
                }
            }
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            try {
                this.zzbfD.put(new zzaf.zza());
            } catch (InterruptedException e) {
            }
        }

        public void onConnectionSuspended(int i) {
            try {
                this.zzbfD.put(new zzaf.zza());
            } catch (InterruptedException e) {
            }
        }

        /* access modifiers changed from: protected */
        public zzapz zzFW() {
            try {
                return this.zzbfB.zzFX();
            } catch (DeadObjectException | IllegalStateException e) {
                return null;
            }
        }

        public zzaf.zza zzaS() {
            return zzjo(2000);
        }

        public void zziY() {
            if (this.zzbfB == null) {
                return;
            }
            if (this.zzbfB.isConnected() || this.zzbfB.isConnecting()) {
                this.zzbfB.disconnect();
            }
        }

        public zzaf.zza zzjo(int i) {
            zzaf.zza zza;
            try {
                zza = this.zzbfD.poll((long) i, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                zza = null;
            }
            return zza == null ? new zzaf.zza() : zza;
        }
    }

    public static zzaf.zza zzi(Context context, String str, String str2) {
        return new zza(context, str, str2).zzaS();
    }
}
