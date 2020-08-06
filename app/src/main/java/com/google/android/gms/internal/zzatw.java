package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzate;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class zzatw extends zzats {
    /* access modifiers changed from: private */
    public zzate zzbuA;
    private Boolean zzbuB;
    private final zzasv zzbuC;
    private final zzatz zzbuD;
    private final List<Runnable> zzbuE = new ArrayList();
    private final zzasv zzbuF;
    /* access modifiers changed from: private */
    public final zza zzbuz;

    protected class zza implements ServiceConnection, zzf.zzb, zzf.zzc {
        /* access modifiers changed from: private */
        public volatile boolean zzbuL;
        private volatile zzath zzbuM;

        protected zza() {
        }

        @MainThread
        public void onConnected(@Nullable Bundle bundle) {
            zzac.zzdn("MeasurementServiceConnection.onConnected");
            synchronized (this) {
                try {
                    final zzate zzate = (zzate) this.zzbuM.zzwW();
                    this.zzbuM = null;
                    zzatw.this.zzJs().zzm(new Runnable() {
                        public void run() {
                            synchronized (zza.this) {
                                boolean unused = zza.this.zzbuL = false;
                                if (!zzatw.this.isConnected()) {
                                    zzatw.this.zzJt().zzLf().log("Connected to remote service");
                                    zzatw.this.zza(zzate);
                                }
                            }
                        }
                    });
                } catch (DeadObjectException | IllegalStateException e) {
                    this.zzbuM = null;
                    this.zzbuL = false;
                }
            }
        }

        @MainThread
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzac.zzdn("MeasurementServiceConnection.onConnectionFailed");
            zzati zzLu = zzatw.this.zzbpw.zzLu();
            if (zzLu != null) {
                zzLu.zzLc().zzj("Service connection failed", connectionResult);
            }
            synchronized (this) {
                this.zzbuL = false;
                this.zzbuM = null;
            }
        }

        @MainThread
        public void onConnectionSuspended(int i) {
            zzac.zzdn("MeasurementServiceConnection.onConnectionSuspended");
            zzatw.this.zzJt().zzLf().log("Service connection suspended");
            zzatw.this.zzJs().zzm(new Runnable() {
                public void run() {
                    zzatw zzatw = zzatw.this;
                    Context context = zzatw.this.getContext();
                    zzatw.this.zzJv().zzKk();
                    zzatw.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                }
            });
        }

        @MainThread
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            zzac.zzdn("MeasurementServiceConnection.onServiceConnected");
            synchronized (this) {
                if (iBinder == null) {
                    this.zzbuL = false;
                    zzatw.this.zzJt().zzLa().log("Service connected with null binder");
                    return;
                }
                final zzate zzate = null;
                try {
                    String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                    if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                        zzate = zzate.zza.zzer(iBinder);
                        zzatw.this.zzJt().zzLg().log("Bound to IMeasurementService interface");
                    } else {
                        zzatw.this.zzJt().zzLa().zzj("Got binder with a wrong descriptor", interfaceDescriptor);
                    }
                } catch (RemoteException e) {
                    zzatw.this.zzJt().zzLa().log("Service connect failed to get IMeasurementService");
                }
                if (zzate == null) {
                    this.zzbuL = false;
                    try {
                        com.google.android.gms.common.stats.zza.zzyc().zza(zzatw.this.getContext(), zzatw.this.zzbuz);
                    } catch (IllegalArgumentException e2) {
                    }
                } else {
                    zzatw.this.zzJs().zzm(new Runnable() {
                        public void run() {
                            synchronized (zza.this) {
                                boolean unused = zza.this.zzbuL = false;
                                if (!zzatw.this.isConnected()) {
                                    zzatw.this.zzJt().zzLg().log("Connected to service");
                                    zzatw.this.zza(zzate);
                                }
                            }
                        }
                    });
                }
            }
        }

        @MainThread
        public void onServiceDisconnected(final ComponentName componentName) {
            zzac.zzdn("MeasurementServiceConnection.onServiceDisconnected");
            zzatw.this.zzJt().zzLf().log("Service disconnected");
            zzatw.this.zzJs().zzm(new Runnable() {
                public void run() {
                    zzatw.this.onServiceDisconnected(componentName);
                }
            });
        }

        @WorkerThread
        public void zzC(Intent intent) {
            zzatw.this.zzmq();
            Context context = zzatw.this.getContext();
            com.google.android.gms.common.stats.zza zzyc = com.google.android.gms.common.stats.zza.zzyc();
            synchronized (this) {
                if (this.zzbuL) {
                    zzatw.this.zzJt().zzLg().log("Connection attempt already in progress");
                    return;
                }
                this.zzbuL = true;
                zzyc.zza(context, intent, (ServiceConnection) zzatw.this.zzbuz, 129);
            }
        }

        @WorkerThread
        public void zzMb() {
            zzatw.this.zzmq();
            Context context = zzatw.this.getContext();
            synchronized (this) {
                if (this.zzbuL) {
                    zzatw.this.zzJt().zzLg().log("Connection attempt already in progress");
                } else if (this.zzbuM != null) {
                    zzatw.this.zzJt().zzLg().log("Already awaiting connection attempt");
                } else {
                    this.zzbuM = new zzath(context, Looper.getMainLooper(), this, this);
                    zzatw.this.zzJt().zzLg().log("Connecting to remote service");
                    this.zzbuL = true;
                    this.zzbuM.zzwT();
                }
            }
        }
    }

    protected zzatw(zzatp zzatp) {
        super(zzatp);
        this.zzbuD = new zzatz(zzatp.zznq());
        this.zzbuz = new zza();
        this.zzbuC = new zzasv(zzatp) {
            public void run() {
                zzatw.this.zznO();
            }
        };
        this.zzbuF = new zzasv(zzatp) {
            public void run() {
                zzatw.this.zzJt().zzLc().log("Tasks have been queued for a long time");
            }
        };
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void onServiceDisconnected(ComponentName componentName) {
        zzmq();
        if (this.zzbuA != null) {
            this.zzbuA = null;
            zzJt().zzLg().zzj("Disconnected from device MeasurementService", componentName);
            zzLZ();
        }
    }

    private boolean zzLX() {
        zzJv().zzKk();
        List<ResolveInfo> queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        return queryIntentServices != null && queryIntentServices.size() > 0;
    }

    @WorkerThread
    private void zzLZ() {
        zzmq();
        zzoc();
    }

    @WorkerThread
    private void zzMa() {
        zzmq();
        zzJt().zzLg().zzj("Processing queued up service tasks", Integer.valueOf(this.zzbuE.size()));
        for (Runnable zzm : this.zzbuE) {
            zzJs().zzm(zzm);
        }
        this.zzbuE.clear();
        this.zzbuF.cancel();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zza(zzate zzate) {
        zzmq();
        zzac.zzw(zzate);
        this.zzbuA = zzate;
        zznN();
        zzMa();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zznN() {
        zzmq();
        this.zzbuD.start();
        this.zzbuC.zzx(zzJv().zzoQ());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zznO() {
        zzmq();
        if (isConnected()) {
            zzJt().zzLg().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    private void zzo(Runnable runnable) {
        zzmq();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzbuE.size()) >= zzJv().zzKq()) {
            zzJt().zzLa().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzbuE.add(runnable);
            this.zzbuF.zzx(60000);
            zzoc();
        }
    }

    @WorkerThread
    public void disconnect() {
        zzmq();
        zznA();
        try {
            com.google.android.gms.common.stats.zza.zzyc().zza(getContext(), this.zzbuz);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        this.zzbuA = null;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public boolean isConnected() {
        zzmq();
        zznA();
        return this.zzbuA != null;
    }

    public /* bridge */ /* synthetic */ void zzJd() {
        super.zzJd();
    }

    public /* bridge */ /* synthetic */ void zzJe() {
        super.zzJe();
    }

    public /* bridge */ /* synthetic */ void zzJf() {
        super.zzJf();
    }

    public /* bridge */ /* synthetic */ zzaso zzJg() {
        return super.zzJg();
    }

    public /* bridge */ /* synthetic */ zzass zzJh() {
        return super.zzJh();
    }

    public /* bridge */ /* synthetic */ zzatu zzJi() {
        return super.zzJi();
    }

    public /* bridge */ /* synthetic */ zzatf zzJj() {
        return super.zzJj();
    }

    public /* bridge */ /* synthetic */ zzasw zzJk() {
        return super.zzJk();
    }

    public /* bridge */ /* synthetic */ zzatw zzJl() {
        return super.zzJl();
    }

    public /* bridge */ /* synthetic */ zzatv zzJm() {
        return super.zzJm();
    }

    public /* bridge */ /* synthetic */ zzatg zzJn() {
        return super.zzJn();
    }

    public /* bridge */ /* synthetic */ zzasu zzJo() {
        return super.zzJo();
    }

    public /* bridge */ /* synthetic */ zzaue zzJp() {
        return super.zzJp();
    }

    public /* bridge */ /* synthetic */ zzatn zzJq() {
        return super.zzJq();
    }

    public /* bridge */ /* synthetic */ zzaty zzJr() {
        return super.zzJr();
    }

    public /* bridge */ /* synthetic */ zzato zzJs() {
        return super.zzJs();
    }

    public /* bridge */ /* synthetic */ zzati zzJt() {
        return super.zzJt();
    }

    public /* bridge */ /* synthetic */ zzatl zzJu() {
        return super.zzJu();
    }

    public /* bridge */ /* synthetic */ zzast zzJv() {
        return super.zzJv();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzLR() {
        zzmq();
        zznA();
        zzo(new Runnable() {
            public void run() {
                zzate zzc = zzatw.this.zzbuA;
                if (zzc == null) {
                    zzatw.this.zzJt().zzLa().log("Discarding data. Failed to send app launch");
                    return;
                }
                try {
                    zzatw.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) null);
                    zzc.zza(zzatw.this.zzJj().zzfH(zzatw.this.zzJt().zzLh()));
                    zzatw.this.zznN();
                } catch (RemoteException e) {
                    zzatw.this.zzJt().zzLa().zzj("Failed to send app launch to the service", e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzLW() {
        zzmq();
        zznA();
        zzo(new Runnable() {
            public void run() {
                zzate zzc = zzatw.this.zzbuA;
                if (zzc == null) {
                    zzatw.this.zzJt().zzLa().log("Failed to send measurementEnabled to service");
                    return;
                }
                try {
                    zzc.zzb(zzatw.this.zzJj().zzfH(zzatw.this.zzJt().zzLh()));
                    zzatw.this.zznN();
                } catch (RemoteException e) {
                    zzatw.this.zzJt().zzLa().zzj("Failed to send measurementEnabled to the service", e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzLY() {
        zzmq();
        zznA();
        zzJv().zzKk();
        zzJt().zzLg().log("Checking service availability");
        switch (zzc.zzuz().isGooglePlayServicesAvailable(getContext())) {
            case 0:
                zzJt().zzLg().log("Service available");
                return true;
            case 1:
                zzJt().zzLg().log("Service missing");
                return false;
            case 2:
                zzJt().zzLf().log("Service container out of date");
                return true;
            case 3:
                zzJt().zzLc().log("Service disabled");
                return false;
            case 9:
                zzJt().zzLc().log("Service invalid");
                return false;
            case 18:
                zzJt().zzLc().log("Service updating");
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0054  */
    @WorkerThread
    public void zza(zzate zzate, com.google.android.gms.common.internal.safeparcel.zza zza2) {
        boolean z;
        zzmq();
        zzJe();
        zznA();
        if (Build.VERSION.SDK_INT >= 11) {
            zzJv().zzKk();
            z = true;
        } else {
            z = false;
        }
        ArrayList<com.google.android.gms.common.internal.safeparcel.zza> arrayList = new ArrayList<>();
        zzJv().zzKt();
        int i = 100;
        for (int i2 = 0; i2 < 1001 && i == 100; i2++) {
            if (z) {
                List<com.google.android.gms.common.internal.safeparcel.zza> zzls = zzJn().zzls(100);
                if (zzls != null) {
                    arrayList.addAll(zzls);
                    i = zzls.size();
                    if (zza2 != null && i < 100) {
                        arrayList.add(zza2);
                    }
                    for (com.google.android.gms.common.internal.safeparcel.zza zza3 : arrayList) {
                        if (zza3 instanceof zzatb) {
                            try {
                                zzate.zza((zzatb) zza3, zzJj().zzfH(zzJt().zzLh()));
                            } catch (RemoteException e) {
                                zzJt().zzLa().zzj("Failed to send event to the service", e);
                            }
                        } else if (zza3 instanceof zzaub) {
                            try {
                                zzate.zza((zzaub) zza3, zzJj().zzfH(zzJt().zzLh()));
                            } catch (RemoteException e2) {
                                zzJt().zzLa().zzj("Failed to send attribute to the service", e2);
                            }
                        } else {
                            zzJt().zzLa().log("Discarding data. Unrecognized parcel type.");
                        }
                    }
                }
            }
            i = 0;
            arrayList.add(zza2);
            while (r7.hasNext()) {
            }
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(final AppMeasurement.zzf zzf) {
        zzmq();
        zznA();
        zzo(new Runnable() {
            public void run() {
                zzate zzc = zzatw.this.zzbuA;
                if (zzc == null) {
                    zzatw.this.zzJt().zzLa().log("Failed to send current screen to service");
                    return;
                }
                try {
                    if (zzf == null) {
                        zzc.zza(0, null, null, zzatw.this.getContext().getPackageName());
                    } else {
                        zzc.zza(zzf.zzbpB, zzf.zzbpz, zzf.zzbpA, zzatw.this.getContext().getPackageName());
                    }
                    zzatw.this.zznN();
                } catch (RemoteException e) {
                    zzatw.this.zzJt().zzLa().zzj("Failed to send current screen to the service", e);
                }
            }
        });
    }

    @WorkerThread
    public void zza(final AtomicReference<String> atomicReference) {
        zzmq();
        zznA();
        zzo(new Runnable() {
            /* JADX INFO: finally extract failed */
            public void run() {
                synchronized (atomicReference) {
                    try {
                        zzate zzc = zzatw.this.zzbuA;
                        if (zzc == null) {
                            zzatw.this.zzJt().zzLa().log("Failed to get app instance id");
                            atomicReference.notify();
                            return;
                        }
                        atomicReference.set(zzc.zzc(zzatw.this.zzJj().zzfH(null)));
                        zzatw.this.zznN();
                        atomicReference.notify();
                    } catch (RemoteException e) {
                        zzatw.this.zzJt().zzLa().zzj("Failed to get app instance id", e);
                        atomicReference.notify();
                    } catch (Throwable th) {
                        atomicReference.notify();
                        throw th;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(final AtomicReference<List<zzaub>> atomicReference, final boolean z) {
        zzmq();
        zznA();
        zzo(new Runnable() {
            /* JADX INFO: finally extract failed */
            public void run() {
                synchronized (atomicReference) {
                    try {
                        zzate zzc = zzatw.this.zzbuA;
                        if (zzc == null) {
                            zzatw.this.zzJt().zzLa().log("Failed to get user properties");
                            atomicReference.notify();
                            return;
                        }
                        atomicReference.set(zzc.zza(zzatw.this.zzJj().zzfH(null), z));
                        zzatw.this.zznN();
                        atomicReference.notify();
                    } catch (RemoteException e) {
                        zzatw.this.zzJt().zzLa().zzj("Failed to get user properties", e);
                        atomicReference.notify();
                    } catch (Throwable th) {
                        atomicReference.notify();
                        throw th;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzb(final zzaub zzaub) {
        boolean z;
        final boolean z2 = true;
        zzmq();
        zznA();
        if (Build.VERSION.SDK_INT >= 11) {
            zzJv().zzKk();
            z = true;
        } else {
            z = false;
        }
        if (!z || !zzJn().zza(zzaub)) {
            z2 = false;
        }
        zzo(new Runnable() {
            public void run() {
                zzate zzc = zzatw.this.zzbuA;
                if (zzc == null) {
                    zzatw.this.zzJt().zzLa().log("Discarding data. Failed to set user attribute");
                    return;
                }
                zzatw.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) z2 ? null : zzaub);
                zzatw.this.zznN();
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzc(zzatb zzatb, String str) {
        final boolean z;
        final boolean z2 = true;
        zzac.zzw(zzatb);
        zzmq();
        zznA();
        if (Build.VERSION.SDK_INT >= 11) {
            zzJv().zzKk();
            z = true;
        } else {
            z = false;
        }
        if (!z || !zzJn().zza(zzatb)) {
            z2 = false;
        }
        final zzatb zzatb2 = zzatb;
        final String str2 = str;
        zzo(new Runnable() {
            public void run() {
                zzate zzc = zzatw.this.zzbuA;
                if (zzc == null) {
                    zzatw.this.zzJt().zzLa().log("Discarding data. Failed to send event to service");
                    return;
                }
                if (z) {
                    zzatw.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) z2 ? null : zzatb2);
                } else {
                    try {
                        if (TextUtils.isEmpty(str2)) {
                            zzc.zza(zzatb2, zzatw.this.zzJj().zzfH(zzatw.this.zzJt().zzLh()));
                        } else {
                            zzc.zza(zzatb2, str2, zzatw.this.zzJt().zzLh());
                        }
                    } catch (RemoteException e) {
                        zzatw.this.zzJt().zzLa().zzj("Failed to send event to the service", e);
                    }
                }
                zzatw.this.zznN();
            }
        });
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzoc() {
        zzmq();
        zznA();
        if (!isConnected()) {
            if (this.zzbuB == null) {
                this.zzbuB = zzJu().zzLn();
                if (this.zzbuB == null) {
                    zzJt().zzLg().log("State of service unknown");
                    this.zzbuB = Boolean.valueOf(zzLY());
                    zzJu().zzaF(this.zzbuB.booleanValue());
                }
            }
            if (this.zzbuB.booleanValue()) {
                zzJt().zzLg().log("Using measurement service");
                this.zzbuz.zzMb();
            } else if (zzLX()) {
                zzJt().zzLg().log("Using local app measurement service");
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                Context context = getContext();
                zzJv().zzKk();
                intent.setComponent(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                this.zzbuz.zzC(intent);
            } else {
                zzJt().zzLa().log("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }
}
