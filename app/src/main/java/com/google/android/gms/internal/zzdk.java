package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzda;

@zzmb
public class zzdk {
    @Nullable
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();
    private final Runnable zzys = new Runnable() {
        public void run() {
            zzdk.this.disconnect();
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public zzdn zzyt;
    /* access modifiers changed from: private */
    @Nullable
    public zzdr zzyu;

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    public void connect() {
        synchronized (this.zzrN) {
            if (this.mContext != null && this.zzyt == null) {
                this.zzyt = zza((zzf.zzb) new zzf.zzb() {
                    public void onConnected(@Nullable Bundle bundle) {
                        synchronized (zzdk.this.zzrN) {
                            try {
                                zzdr unused = zzdk.this.zzyu = zzdk.this.zzyt.zzew();
                            } catch (DeadObjectException e) {
                                zzpe.zzb("Unable to obtain a cache service instance.", e);
                                zzdk.this.disconnect();
                            }
                            zzdk.this.zzrN.notifyAll();
                        }
                    }

                    public void onConnectionSuspended(int i) {
                        synchronized (zzdk.this.zzrN) {
                            zzdn unused = zzdk.this.zzyt = null;
                            zzdr unused2 = zzdk.this.zzyu = null;
                            zzdk.this.zzrN.notifyAll();
                            zzv.zzcZ().zzkD();
                        }
                    }
                }, (zzf.zzc) new zzf.zzc() {
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        synchronized (zzdk.this.zzrN) {
                            zzdn unused = zzdk.this.zzyt = null;
                            zzdr unused2 = zzdk.this.zzyu = null;
                            zzdk.this.zzrN.notifyAll();
                            zzv.zzcZ().zzkD();
                        }
                    }
                });
                this.zzyt.zzwT();
            }
        }
    }

    /* access modifiers changed from: private */
    public void disconnect() {
        synchronized (this.zzrN) {
            if (this.zzyt != null) {
                if (this.zzyt.isConnected() || this.zzyt.isConnecting()) {
                    this.zzyt.disconnect();
                }
                this.zzyt = null;
                this.zzyu = null;
                Binder.flushPendingCommands();
                zzv.zzcZ().zzkD();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    public void initialize(Context context) {
        if (context != null) {
            synchronized (this.zzrN) {
                if (this.mContext == null) {
                    this.mContext = context.getApplicationContext();
                    if (zzfx.zzEL.get().booleanValue()) {
                        connect();
                    } else if (zzfx.zzEK.get().booleanValue()) {
                        zza((zzda.zzb) new zzda.zzb() {
                            public void zzk(boolean z) {
                                if (z) {
                                    zzdk.this.connect();
                                } else {
                                    zzdk.this.disconnect();
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    public zzdl zza(zzdo zzdo) {
        zzdl zzdl;
        synchronized (this.zzrN) {
            if (this.zzyu == null) {
                zzdl = new zzdl();
            } else {
                try {
                    zzdl = this.zzyu.zza(zzdo);
                } catch (RemoteException e) {
                    zzpe.zzb("Unable to call into cache service.", e);
                    zzdl = new zzdl();
                }
            }
        }
        return zzdl;
    }

    /* access modifiers changed from: protected */
    public zzdn zza(zzf.zzb zzb, zzf.zzc zzc) {
        return new zzdn(this.mContext, zzv.zzcZ().zzkC(), zzb, zzc);
    }

    /* access modifiers changed from: protected */
    public void zza(zzda.zzb zzb) {
        zzv.zzcM().zza(zzb);
    }

    public void zzeq() {
        if (zzfx.zzEM.get().booleanValue()) {
            synchronized (this.zzrN) {
                connect();
                zzv.zzcJ();
                zzpi.zzWR.removeCallbacks(this.zzys);
                zzv.zzcJ();
                zzpi.zzWR.postDelayed(this.zzys, zzfx.zzEN.get().longValue());
            }
        }
    }
}
