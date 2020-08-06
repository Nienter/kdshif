package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqi;

@zzmb
public abstract class zzmf implements zzme.zza, zzpk<Void> {
    private final zzqi<zzmh> zzQV;
    private final zzme.zza zzQW;
    private final Object zzrN = new Object();

    @zzmb
    public static final class zza extends zzmf {
        private final Context mContext;

        public zza(Context context, zzqi<zzmh> zzqi, zzme.zza zza) {
            super(zzqi, zza);
            this.mContext = context;
        }

        public void zziY() {
        }

        public zzmq zziZ() {
            return zzmz.zza(this.mContext, new zzfq(zzfx.zzAR.get()), zzmy.zzjf());
        }

        public /* synthetic */ Object zziw() {
            return zzmf.super.zziw();
        }
    }

    @zzmb
    public static class zzb extends zzmf implements zzf.zzb, zzf.zzc {
        private Context mContext;
        private zzqi<zzmh> zzQV;
        private final zzme.zza zzQW;
        protected zzmg zzQZ;
        private boolean zzRa;
        private final Object zzrN = new Object();
        private zzqa zztr;

        public zzb(Context context, zzqa zzqa, zzqi<zzmh> zzqi, zzme.zza zza) {
            super(zzqi, zza);
            Looper mainLooper;
            this.mContext = context;
            this.zztr = zzqa;
            this.zzQV = zzqi;
            this.zzQW = zza;
            if (zzfx.zzBE.get().booleanValue()) {
                this.zzRa = true;
                mainLooper = zzv.zzcZ().zzkC();
            } else {
                mainLooper = context.getMainLooper();
            }
            this.zzQZ = new zzmg(context, mainLooper, this, this, this.zztr.zzYc);
            connect();
        }

        /* access modifiers changed from: protected */
        public void connect() {
            this.zzQZ.zzwT();
        }

        public void onConnected(Bundle bundle) {
            zziw();
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzpe.zzbc("Cannot connect to remote service, fallback to local instance.");
            zzja().zziw();
            Bundle bundle = new Bundle();
            bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "gms_connection_failed_fallback_to_local");
            zzv.zzcJ().zzb(this.mContext, this.zztr.zzaZ, "gmob-apps", bundle, true);
        }

        public void onConnectionSuspended(int i) {
            zzpe.zzbc("Disconnected from remote ad request service.");
        }

        public void zziY() {
            synchronized (this.zzrN) {
                if (this.zzQZ.isConnected() || this.zzQZ.isConnecting()) {
                    this.zzQZ.disconnect();
                }
                Binder.flushPendingCommands();
                if (this.zzRa) {
                    zzv.zzcZ().zzkD();
                    this.zzRa = false;
                }
            }
        }

        public zzmq zziZ() {
            zzmq zzmq;
            synchronized (this.zzrN) {
                try {
                    zzmq = this.zzQZ.zzjb();
                } catch (DeadObjectException | IllegalStateException e) {
                    zzmq = null;
                }
            }
            return zzmq;
        }

        public /* synthetic */ Object zziw() {
            return zzmf.super.zziw();
        }

        /* access modifiers changed from: package-private */
        public zzpk zzja() {
            return new zza(this.mContext, this.zzQV, this.zzQW);
        }
    }

    public zzmf(zzqi<zzmh> zzqi, zzme.zza zza2) {
        this.zzQV = zzqi;
        this.zzQW = zza2;
    }

    public void cancel() {
        zziY();
    }

    /* access modifiers changed from: package-private */
    public boolean zza(zzmq zzmq, zzmh zzmh) {
        try {
            zzmq.zza(zzmh, new zzmj(this));
            return true;
        } catch (RemoteException e) {
            zzpe.zzc("Could not fetch ad response from ad request service.", e);
            zzv.zzcN().zza((Throwable) e, "AdRequestClientTask.getAdResponseFromService");
            this.zzQW.zzb(new zzmk(0));
            return false;
        } catch (NullPointerException e2) {
            zzpe.zzc("Could not fetch ad response from ad request service due to an Exception.", e2);
            zzv.zzcN().zza((Throwable) e2, "AdRequestClientTask.getAdResponseFromService");
            this.zzQW.zzb(new zzmk(0));
            return false;
        } catch (SecurityException e3) {
            zzpe.zzc("Could not fetch ad response from ad request service due to an Exception.", e3);
            zzv.zzcN().zza((Throwable) e3, "AdRequestClientTask.getAdResponseFromService");
            this.zzQW.zzb(new zzmk(0));
            return false;
        } catch (Throwable th) {
            zzpe.zzc("Could not fetch ad response from ad request service due to an Exception.", th);
            zzv.zzcN().zza(th, "AdRequestClientTask.getAdResponseFromService");
            this.zzQW.zzb(new zzmk(0));
            return false;
        }
    }

    public void zzb(zzmk zzmk) {
        synchronized (this.zzrN) {
            this.zzQW.zzb(zzmk);
            zziY();
        }
    }

    public abstract void zziY();

    public abstract zzmq zziZ();

    /* renamed from: zzit */
    public Void zziw() {
        final zzmq zziZ = zziZ();
        if (zziZ == null) {
            this.zzQW.zzb(new zzmk(0));
            zziY();
        } else {
            this.zzQV.zza(new zzqi.zzc<zzmh>() {
                /* renamed from: zzc */
                public void zzd(zzmh zzmh) {
                    if (!zzmf.this.zza(zziZ, zzmh)) {
                        zzmf.this.zziY();
                    }
                }
            }, new zzqi.zza() {
                public void run() {
                    zzmf.this.zziY();
                }
            });
        }
        return null;
    }
}
