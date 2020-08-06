package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzo extends zzn implements Handler.Callback {
    private final Handler mHandler;
    /* access modifiers changed from: private */
    public final HashMap<zza, zzb> zzaEF = new HashMap<>();
    /* access modifiers changed from: private */
    public final com.google.android.gms.common.stats.zza zzaEG;
    private final long zzaEH;
    /* access modifiers changed from: private */
    public final Context zzvZ;

    private static final class zza {
        private final String zzaEI;
        private final ComponentName zzaEJ;
        private final String zzaca;

        public zza(ComponentName componentName) {
            this.zzaca = null;
            this.zzaEI = null;
            this.zzaEJ = (ComponentName) zzac.zzw(componentName);
        }

        public zza(String str, String str2) {
            this.zzaca = zzac.zzdv(str);
            this.zzaEI = zzac.zzdv(str2);
            this.zzaEJ = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzaa.equal(this.zzaca, zza.zzaca) && zzaa.equal(this.zzaEJ, zza.zzaEJ);
        }

        public int hashCode() {
            return zzaa.hashCode(this.zzaca, this.zzaEJ);
        }

        public String toString() {
            return this.zzaca == null ? this.zzaEJ.flattenToString() : this.zzaca;
        }

        public Intent zzxs() {
            return this.zzaca != null ? new Intent(this.zzaca).setPackage(this.zzaEI) : new Intent().setComponent(this.zzaEJ);
        }
    }

    private final class zzb {
        /* access modifiers changed from: private */
        public int mState = 2;
        /* access modifiers changed from: private */
        public ComponentName zzaEJ;
        private final zza zzaEK = new zza();
        /* access modifiers changed from: private */
        public final Set<ServiceConnection> zzaEL = new HashSet();
        private boolean zzaEM;
        private final zza zzaEN;
        /* access modifiers changed from: private */
        public IBinder zzaEa;

        public class zza implements ServiceConnection {
            public zza() {
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (zzo.this.zzaEF) {
                    IBinder unused = zzb.this.zzaEa = iBinder;
                    ComponentName unused2 = zzb.this.zzaEJ = componentName;
                    for (ServiceConnection onServiceConnected : zzb.this.zzaEL) {
                        onServiceConnected.onServiceConnected(componentName, iBinder);
                    }
                    int unused3 = zzb.this.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (zzo.this.zzaEF) {
                    IBinder unused = zzb.this.zzaEa = null;
                    ComponentName unused2 = zzb.this.zzaEJ = componentName;
                    for (ServiceConnection onServiceDisconnected : zzb.this.zzaEL) {
                        onServiceDisconnected.onServiceDisconnected(componentName);
                    }
                    int unused3 = zzb.this.mState = 2;
                }
            }
        }

        public zzb(zza zza2) {
            this.zzaEN = zza2;
        }

        public IBinder getBinder() {
            return this.zzaEa;
        }

        public ComponentName getComponentName() {
            return this.zzaEJ;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.zzaEM;
        }

        public void zza(ServiceConnection serviceConnection, String str) {
            zzo.this.zzaEG.zza(zzo.this.zzvZ, serviceConnection, str, this.zzaEN.zzxs());
            this.zzaEL.add(serviceConnection);
        }

        public boolean zza(ServiceConnection serviceConnection) {
            return this.zzaEL.contains(serviceConnection);
        }

        public void zzb(ServiceConnection serviceConnection, String str) {
            zzo.this.zzaEG.zzb(zzo.this.zzvZ, serviceConnection);
            this.zzaEL.remove(serviceConnection);
        }

        @TargetApi(14)
        public void zzdr(String str) {
            this.mState = 3;
            this.zzaEM = zzo.this.zzaEG.zza(zzo.this.zzvZ, str, this.zzaEN.zzxs(), this.zzaEK, 129);
            if (!this.zzaEM) {
                this.mState = 2;
                try {
                    zzo.this.zzaEG.zza(zzo.this.zzvZ, this.zzaEK);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        public void zzds(String str) {
            zzo.this.zzaEG.zza(zzo.this.zzvZ, this.zzaEK);
            this.zzaEM = false;
            this.mState = 2;
        }

        public boolean zzxt() {
            return this.zzaEL.isEmpty();
        }
    }

    zzo(Context context) {
        this.zzvZ = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.zzaEG = com.google.android.gms.common.stats.zza.zzyc();
        this.zzaEH = 5000;
    }

    private boolean zza(zza zza2, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzac.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzaEF) {
            zzb zzb2 = this.zzaEF.get(zza2);
            if (zzb2 != null) {
                this.mHandler.removeMessages(0, zza2);
                if (!zzb2.zza(serviceConnection)) {
                    zzb2.zza(serviceConnection, str);
                    switch (zzb2.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzb2.getComponentName(), zzb2.getBinder());
                            break;
                        case 2:
                            zzb2.zzdr(str);
                            break;
                    }
                } else {
                    String valueOf = String.valueOf(zza2);
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
                }
            } else {
                zzb2 = new zzb(zza2);
                zzb2.zza(serviceConnection, str);
                zzb2.zzdr(str);
                this.zzaEF.put(zza2, zzb2);
            }
            isBound = zzb2.isBound();
        }
        return isBound;
    }

    private void zzb(zza zza2, ServiceConnection serviceConnection, String str) {
        zzac.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzaEF) {
            zzb zzb2 = this.zzaEF.get(zza2);
            if (zzb2 == null) {
                String valueOf = String.valueOf(zza2);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (!zzb2.zza(serviceConnection)) {
                String valueOf2 = String.valueOf(zza2);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf2).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf2).toString());
            } else {
                zzb2.zzb(serviceConnection, str);
                if (zzb2.zzxt()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zza2), this.zzaEH);
                }
            }
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                zza zza2 = (zza) message.obj;
                synchronized (this.zzaEF) {
                    zzb zzb2 = this.zzaEF.get(zza2);
                    if (zzb2 != null && zzb2.zzxt()) {
                        if (zzb2.isBound()) {
                            zzb2.zzds("GmsClientSupervisor");
                        }
                        this.zzaEF.remove(zza2);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zza(componentName), serviceConnection, str);
    }

    public boolean zza(String str, String str2, ServiceConnection serviceConnection, String str3) {
        return zza(new zza(str, str2), serviceConnection, str3);
    }

    public void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zza(componentName), serviceConnection, str);
    }

    public void zzb(String str, String str2, ServiceConnection serviceConnection, String str3) {
        zzb(new zza(str, str2), serviceConnection, str3);
    }
}
