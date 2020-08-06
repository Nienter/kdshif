package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.internal.zzv;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzf<T extends IInterface> {
    public static final String[] zzaDT = {"service_esmobile", "service_googleme"};
    private final Context mContext;
    final Handler mHandler;
    private int zzaDB;
    private long zzaDC;
    private long zzaDD;
    private int zzaDE;
    private long zzaDF;
    private final zzn zzaDG;
    /* access modifiers changed from: private */
    public final Object zzaDH;
    /* access modifiers changed from: private */
    public zzv zzaDI;
    protected C1686zzf zzaDJ;
    private T zzaDK;
    /* access modifiers changed from: private */
    public final ArrayList<zze<?>> zzaDL;
    private zzh zzaDM;
    private int zzaDN;
    /* access modifiers changed from: private */
    public final zzb zzaDO;
    /* access modifiers changed from: private */
    public final zzc zzaDP;
    private final int zzaDQ;
    private final String zzaDR;
    protected AtomicInteger zzaDS;
    private final com.google.android.gms.common.zzc zzazw;
    private final Object zzrN;
    private final Looper zzrx;

    private abstract class zza extends zze<Boolean> {
        public final int statusCode;
        public final Bundle zzaDU;

        @BinderThread
        protected zza(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.zzaDU = bundle;
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzc */
        public void zzu(Boolean bool) {
            PendingIntent pendingIntent = null;
            if (bool == null) {
                zzf.this.zza(1, null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zzwZ()) {
                        zzf.this.zza(1, null);
                        zzn(new ConnectionResult(8, null));
                        return;
                    }
                    return;
                case 10:
                    zzf.this.zza(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    zzf.this.zza(1, null);
                    if (this.zzaDU != null) {
                        pendingIntent = (PendingIntent) this.zzaDU.getParcelable("pendingIntent");
                    }
                    zzn(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzn(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zzwZ();
    }

    public interface zzb {
        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface zzc {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    final class zzd extends Handler {
        public zzd(Looper looper) {
            super(looper);
        }

        private void zza(Message message) {
            ((zze) message.obj).unregister();
        }

        private boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 5;
        }

        public void handleMessage(Message message) {
            PendingIntent pendingIntent = null;
            if (zzf.this.zzaDS.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 5) && !zzf.this.isConnecting()) {
                zza(message);
            } else if (message.what == 3) {
                if (message.obj instanceof PendingIntent) {
                    pendingIntent = (PendingIntent) message.obj;
                }
                ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                zzf.this.zzaDJ.zzg(connectionResult);
                zzf.this.onConnectionFailed(connectionResult);
            } else if (message.what == 4) {
                zzf.this.zza(4, null);
                if (zzf.this.zzaDO != null) {
                    zzf.this.zzaDO.onConnectionSuspended(message.arg2);
                }
                zzf.this.onConnectionSuspended(message.arg2);
                boolean unused = zzf.this.zza(4, 1, null);
            } else if (message.what == 2 && !zzf.this.isConnected()) {
                zza(message);
            } else if (zzb(message)) {
                ((zze) message.obj).zzxa();
            } else {
                Log.wtf("GmsClient", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), new Exception());
            }
        }
    }

    protected abstract class zze<TListener> {
        private TListener mListener;
        private boolean zzaDW = false;

        public zze(TListener tlistener) {
            this.mListener = tlistener;
        }

        public void unregister() {
            zzxb();
            synchronized (zzf.this.zzaDL) {
                zzf.this.zzaDL.remove(this);
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzu(TListener tlistener);

        public void zzxa() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.mListener;
                if (this.zzaDW) {
                    String valueOf = String.valueOf(this);
                    Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
                }
            }
            if (tlistener != null) {
                try {
                    zzu(tlistener);
                } catch (RuntimeException e) {
                    throw e;
                }
            }
            synchronized (this) {
                this.zzaDW = true;
            }
            unregister();
        }

        public void zzxb() {
            synchronized (this) {
                this.mListener = null;
            }
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$zzf  reason: collision with other inner class name */
    public interface C1686zzf {
        void zzg(@NonNull ConnectionResult connectionResult);
    }

    public static final class zzg extends zzu.zza {
        private zzf zzaDX;
        private final int zzaDY;

        public zzg(@NonNull zzf zzf, int i) {
            this.zzaDX = zzf;
            this.zzaDY = i;
        }

        private void zzxc() {
            this.zzaDX = null;
        }

        @BinderThread
        public void zza(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            zzac.zzb(this.zzaDX, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzaDX.zza(i, iBinder, bundle, this.zzaDY);
            zzxc();
        }

        @BinderThread
        public void zzb(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }
    }

    public final class zzh implements ServiceConnection {
        private final int zzaDY;

        public zzh(int i) {
            this.zzaDY = i;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                zzf.this.zzm(new ConnectionResult(8, null, "ServiceBroker IBinder is null"));
                return;
            }
            synchronized (zzf.this.zzaDH) {
                zzv unused = zzf.this.zzaDI = zzv.zza.zzbu(iBinder);
            }
            zzf.this.zza(0, (Bundle) null, this.zzaDY);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (zzf.this.zzaDH) {
                zzv unused = zzf.this.zzaDI = null;
            }
            zzf.this.mHandler.sendMessage(zzf.this.mHandler.obtainMessage(4, this.zzaDY, 1));
        }
    }

    protected class zzi implements C1686zzf {
        public zzi() {
        }

        public void zzg(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                zzf.this.zza((zzr) null, zzf.this.zzwY());
            } else if (zzf.this.zzaDP != null) {
                zzf.this.zzaDP.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final class zzj extends zza {
        public final IBinder zzaDZ;

        @BinderThread
        public zzj(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzaDZ = iBinder;
        }

        /* access modifiers changed from: protected */
        public void zzn(ConnectionResult connectionResult) {
            if (zzf.this.zzaDP != null) {
                zzf.this.zzaDP.onConnectionFailed(connectionResult);
            }
            zzf.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public boolean zzwZ() {
            try {
                String interfaceDescriptor = this.zzaDZ.getInterfaceDescriptor();
                if (!zzf.this.zzev().equals(interfaceDescriptor)) {
                    String valueOf = String.valueOf(zzf.this.zzev());
                    Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 34 + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(valueOf).append(" vs. ").append(interfaceDescriptor).toString());
                    return false;
                }
                IInterface zzh = zzf.this.zzh(this.zzaDZ);
                if (zzh == null || !zzf.this.zza(2, 3, zzh)) {
                    return false;
                }
                Bundle zzud = zzf.this.zzud();
                if (zzf.this.zzaDO != null) {
                    zzf.this.zzaDO.onConnected(zzud);
                }
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzk extends zza {
        @BinderThread
        public zzk(int i, Bundle bundle) {
            super(i, bundle);
        }

        /* access modifiers changed from: protected */
        public void zzn(ConnectionResult connectionResult) {
            zzf.this.zzaDJ.zzg(connectionResult);
            zzf.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public boolean zzwZ() {
            zzf.this.zzaDJ.zzg(ConnectionResult.zzawX);
            return true;
        }
    }

    protected zzf(Context context, Looper looper, int i, zzb zzb2, zzc zzc2, String str) {
        this(context, looper, zzn.zzaC(context), com.google.android.gms.common.zzc.zzuz(), i, (zzb) zzac.zzw(zzb2), (zzc) zzac.zzw(zzc2), str);
    }

    protected zzf(Context context, Looper looper, zzn zzn, com.google.android.gms.common.zzc zzc2, int i, zzb zzb2, zzc zzc3, String str) {
        this.zzrN = new Object();
        this.zzaDH = new Object();
        this.zzaDL = new ArrayList<>();
        this.zzaDN = 1;
        this.zzaDS = new AtomicInteger(0);
        this.mContext = (Context) zzac.zzb(context, (Object) "Context must not be null");
        this.zzrx = (Looper) zzac.zzb(looper, (Object) "Looper must not be null");
        this.zzaDG = (zzn) zzac.zzb(zzn, (Object) "Supervisor must not be null");
        this.zzazw = (com.google.android.gms.common.zzc) zzac.zzb(zzc2, (Object) "API availability must not be null");
        this.mHandler = new zzd(looper);
        this.zzaDQ = i;
        this.zzaDO = zzb2;
        this.zzaDP = zzc3;
        this.zzaDR = str;
    }

    /* access modifiers changed from: private */
    public void zza(int i, T t) {
        boolean z = true;
        if ((i == 3) != (t != null)) {
            z = false;
        }
        zzac.zzas(z);
        synchronized (this.zzrN) {
            this.zzaDN = i;
            this.zzaDK = t;
            switch (i) {
                case 1:
                    zzwS();
                    break;
                case 2:
                    zzwR();
                    break;
                case 3:
                    zza(t);
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.zzrN) {
            if (this.zzaDN != i) {
                z = false;
            } else {
                zza(i2, t);
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void zzm(ConnectionResult connectionResult) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaDS.get(), connectionResult.getErrorCode(), connectionResult.getResolution()));
    }

    private void zzwR() {
        if (this.zzaDM != null) {
            String valueOf = String.valueOf(zzeu());
            String valueOf2 = String.valueOf(zzwP());
            Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 70 + String.valueOf(valueOf2).length()).append("Calling connect() while still connected, missing disconnect() for ").append(valueOf).append(" on ").append(valueOf2).toString());
            this.zzaDG.zzb(zzeu(), zzwP(), this.zzaDM, zzwQ());
            this.zzaDS.incrementAndGet();
        }
        this.zzaDM = new zzh(this.zzaDS.get());
        if (!this.zzaDG.zza(zzeu(), zzwP(), this.zzaDM, zzwQ())) {
            String valueOf3 = String.valueOf(zzeu());
            String valueOf4 = String.valueOf(zzwP());
            Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf3).length() + 34 + String.valueOf(valueOf4).length()).append("unable to connect to service: ").append(valueOf3).append(" on ").append(valueOf4).toString());
            zza(16, (Bundle) null, this.zzaDS.get());
        }
    }

    private void zzwS() {
        if (this.zzaDM != null) {
            this.zzaDG.zzb(zzeu(), zzwP(), this.zzaDM, zzwQ());
            this.zzaDM = null;
        }
    }

    public void disconnect() {
        this.zzaDS.incrementAndGet();
        synchronized (this.zzaDL) {
            int size = this.zzaDL.size();
            for (int i = 0; i < size; i++) {
                this.zzaDL.get(i).zzxb();
            }
            this.zzaDL.clear();
        }
        synchronized (this.zzaDH) {
            this.zzaDI = null;
        }
        zza(1, (IInterface) null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        synchronized (this.zzrN) {
            i = this.zzaDN;
            t = this.zzaDK;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("CONNECTING");
                break;
            case 3:
                printWriter.print("CONNECTED");
                break;
            case 4:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.println("null");
        } else {
            printWriter.append(zzev()).append("@").println(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzaDD > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzaDD;
            String valueOf = String.valueOf(simpleDateFormat.format(new Date(this.zzaDD)));
            append.println(new StringBuilder(String.valueOf(valueOf).length() + 21).append(j).append(" ").append(valueOf).toString());
        }
        if (this.zzaDC > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzaDB) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzaDB));
                    break;
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzaDC;
            String valueOf2 = String.valueOf(simpleDateFormat.format(new Date(this.zzaDC)));
            append2.println(new StringBuilder(String.valueOf(valueOf2).length() + 21).append(j2).append(" ").append(valueOf2).toString());
        }
        if (this.zzaDF > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzaDE));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzaDF;
            String valueOf3 = String.valueOf(simpleDateFormat.format(new Date(this.zzaDF)));
            append3.println(new StringBuilder(String.valueOf(valueOf3).length() + 21).append(j3).append(" ").append(valueOf3).toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzrx;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzaDN == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzaDN == 2;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzaDE = connectionResult.getErrorCode();
        this.zzaDF = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onConnectionSuspended(int i) {
        this.zzaDB = i;
        this.zzaDC = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i2, -1, new zzk(i, bundle)));
    }

    /* access modifiers changed from: protected */
    @BinderThread
    public void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzj(i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void zza(@NonNull T t) {
        this.zzaDD = System.currentTimeMillis();
    }

    public void zza(@NonNull C1686zzf zzf) {
        this.zzaDJ = (C1686zzf) zzac.zzb(zzf, (Object) "Connection progress callbacks cannot be null.");
        zza(2, (IInterface) null);
    }

    public void zza(C1686zzf zzf, ConnectionResult connectionResult) {
        this.zzaDJ = (C1686zzf) zzac.zzb(zzf, (Object) "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaDS.get(), connectionResult.getErrorCode(), connectionResult.getResolution()));
    }

    @WorkerThread
    public void zza(zzr zzr, Set<Scope> set) {
        zzj zzp = new zzj(this.zzaDQ).zzdq(this.mContext.getPackageName()).zzp(zzql());
        if (set != null) {
            zzp.zzf(set);
        }
        if (zzqD()) {
            zzp.zze(zzwU()).zzb(zzr);
        } else if (zzwX()) {
            zzp.zze(getAccount());
        }
        try {
            synchronized (this.zzaDH) {
                if (this.zzaDI != null) {
                    this.zzaDI.zza((zzu) new zzg(this, this.zzaDS.get()), zzp);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            zzcM(1);
        } catch (RemoteException e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        } catch (SecurityException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e4);
            zzm(new ConnectionResult(8, null, "IGmsServiceBroker.getService failed."));
        }
    }

    public void zzcM(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzaDS.get(), i));
    }

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String zzeu();

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String zzev();

    /* access modifiers changed from: protected */
    @Nullable
    public abstract T zzh(IBinder iBinder);

    public boolean zzqD() {
        return false;
    }

    public boolean zzqS() {
        return false;
    }

    public Intent zzqT() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    /* access modifiers changed from: protected */
    public Bundle zzql() {
        return new Bundle();
    }

    public boolean zzuI() {
        return true;
    }

    @Nullable
    public IBinder zzuJ() {
        IBinder asBinder;
        synchronized (this.zzaDH) {
            asBinder = this.zzaDI == null ? null : this.zzaDI.asBinder();
        }
        return asBinder;
    }

    public Bundle zzud() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String zzwP() {
        return "com.google.android.gms";
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzwQ() {
        return this.zzaDR == null ? this.mContext.getClass().getName() : this.zzaDR;
    }

    public void zzwT() {
        int isGooglePlayServicesAvailable = this.zzazw.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (IInterface) null);
            this.zzaDJ = new zzi();
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaDS.get(), isGooglePlayServicesAvailable));
            return;
        }
        zza((C1686zzf) new zzi());
    }

    public final Account zzwU() {
        return getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
    }

    /* access modifiers changed from: protected */
    public final void zzwV() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzwW() {
        T t;
        synchronized (this.zzrN) {
            if (this.zzaDN == 4) {
                throw new DeadObjectException();
            }
            zzwV();
            zzac.zza(this.zzaDK != null, (Object) "Client is connected but service is null");
            t = this.zzaDK;
        }
        return t;
    }

    public boolean zzwX() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Set<Scope> zzwY() {
        return Collections.EMPTY_SET;
    }
}
