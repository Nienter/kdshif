package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzatv;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public class zzatu extends zzats {
    protected zza zzbtU;
    private AppMeasurement.zzb zzbtV;
    private final Set<AppMeasurement.zzc> zzbtW = new CopyOnWriteArraySet();
    private boolean zzbtX;
    private String zzbtY = null;
    private String zzbtZ = null;

    @MainThread
    @TargetApi(14)
    private class zza implements Application.ActivityLifecycleCallbacks {
        private zza() {
        }

        private boolean zzfT(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            zzatu.this.zzd("auto", "_ldl", str);
            return true;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            try {
                zzatu.this.zzJt().zzLg().log("onActivityCreated");
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Uri data = intent.getData();
                    if (data != null && data.isHierarchical()) {
                        if (bundle == null) {
                            Bundle zzu = zzatu.this.zzJp().zzu(data);
                            String str = zzatu.this.zzJp().zzD(intent) ? "gs" : "auto";
                            if (zzu != null) {
                                zzatu.this.zze(str, "_cmp", zzu);
                            }
                        }
                        String queryParameter = data.getQueryParameter("referrer");
                        if (!TextUtils.isEmpty(queryParameter)) {
                            if (!(queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content")))) {
                                zzatu.this.zzJt().zzLf().log("Activity created with data 'referrer' param without gclid and at least one utm field");
                                return;
                            } else {
                                zzatu.this.zzJt().zzLf().zzj("Activity created with referrer", queryParameter);
                                zzfT(queryParameter);
                            }
                        } else {
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                zzatu.this.zzJt().zzLa().zzj("Throwable caught in onActivityCreated", th);
            }
            zzatu.this.zzJm().onActivityCreated(activity, bundle);
        }

        public void onActivityDestroyed(Activity activity) {
            zzatu.this.zzJm().onActivityDestroyed(activity);
        }

        @MainThread
        public void onActivityPaused(Activity activity) {
            zzatu.this.zzJm().onActivityPaused(activity);
            zzatu.this.zzJr().zzMe();
        }

        @MainThread
        public void onActivityResumed(Activity activity) {
            zzatu.this.zzJm().onActivityResumed(activity);
            zzatu.this.zzJr().zzMc();
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzatu.this.zzJm().onActivitySaveInstanceState(activity, bundle);
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    protected zzatu(zzatp zzatp) {
        super(zzatp);
    }

    @WorkerThread
    private void zzLS() {
        try {
            zzf(Class.forName(zzLT()));
        } catch (ClassNotFoundException e) {
            zzJt().zzLe().log("Tag Manager is not found and thus will not be used");
        }
    }

    private String zzLT() {
        return "com.google.android.gms.tagmanager.TagManagerService";
    }

    private void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zza(str, str2, zznq().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zza(String str, String str2, Object obj, long j) {
        zzac.zzdv(str);
        zzac.zzdv(str2);
        zzmq();
        zzJe();
        zznA();
        if (!this.zzbpw.isEnabled()) {
            zzJt().zzLf().log("User property not set since app measurement is disabled");
        } else if (this.zzbpw.zzLt()) {
            zzJt().zzLf().zze("Setting user property (FE)", str2, obj);
            zzJl().zzb(new zzaub(str2, j, obj, str));
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzaH(boolean z) {
        zzmq();
        zzJe();
        zznA();
        zzJt().zzLf().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzJu().setMeasurementEnabled(z);
        zzJl().zzLW();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzac.zzdv(str);
        zzac.zzdv(str2);
        zzac.zzw(bundle);
        zzmq();
        zznA();
        if (!this.zzbpw.isEnabled()) {
            zzJt().zzLf().log("Event not sent since app measurement is disabled");
            return;
        }
        if (!this.zzbtX) {
            this.zzbtX = true;
            zzLS();
        }
        boolean equals = "am".equals(str);
        boolean zzgg = zzaue.zzgg(str2);
        if (z && this.zzbtV != null && !zzgg && !equals) {
            zzJt().zzLf().zze("Passing event to registered event handler (FE)", str2, bundle);
            this.zzbtV.zzb(str, str2, bundle, j);
        } else if (this.zzbpw.zzLt()) {
            int zzfY = zzJp().zzfY(str2);
            if (zzfY != 0) {
                this.zzbpw.zzJp().zza(zzfY, "_ev", zzJp().zza(str2, zzJv().zzJU(), true), str2 != null ? str2.length() : 0);
                return;
            }
            bundle.putString("_o", str);
            Bundle zza2 = zzJp().zza(str2, bundle, (List<String>) zzf.zzx("_o"), z3);
            if (!bundle.containsKey("_sc")) {
                zzJv().zzKk();
                zzatv.zza zzLU = zzJm().zzLU();
                if (zzLU != null) {
                    zzLU.zzbuy = true;
                }
                zzatv.zza((AppMeasurement.zzf) zzLU, zza2);
            }
            Bundle zzN = z2 ? zzN(zza2) : zza2;
            zzJt().zzLf().zze("Logging event (FE)", str2, zzN);
            zzJl().zzc(new zzatb(str2, new zzasz(zzN), str, j), str3);
            if (!equals) {
                for (AppMeasurement.zzc zzc : this.zzbtW) {
                    zzc.zzc(str, str2, new Bundle(zzN), j);
                }
            }
        }
    }

    @Nullable
    public String getAppInstanceIdOnPackageSide(String str) {
        zzJd();
        return this.zzbpw.zzfR(str);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Nullable
    public String getGmpAppIdOnPackageSide(String str) {
        zzJd();
        return this.zzbpw.getGmpAppIdOnPackageSide(str);
    }

    public void setMeasurementEnabled(final boolean z) {
        zznA();
        zzJe();
        zzJs().zzm(new Runnable() {
            public void run() {
                zzatu.this.zzaH(z);
            }
        });
    }

    public void setMinimumSessionDuration(final long j) {
        zzJe();
        zzJs().zzm(new Runnable() {
            public void run() {
                zzatu.this.zzJu().zzbsp.set(j);
                zzatu.this.zzJt().zzLf().zzj("Minimum session duration set", Long.valueOf(j));
            }
        });
    }

    public void setSessionTimeoutDuration(final long j) {
        zzJe();
        zzJs().zzm(new Runnable() {
            public void run() {
                zzatu.this.zzJu().zzbsq.set(j);
                zzatu.this.zzJt().zzLf().zzj("Session timeout duration set", Long.valueOf(j));
            }
        });
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

    @TargetApi(14)
    public void zzLQ() {
        if (getContext().getApplicationContext() instanceof Application) {
            Application application = (Application) getContext().getApplicationContext();
            if (this.zzbtU == null) {
                this.zzbtU = new zza();
            }
            application.unregisterActivityLifecycleCallbacks(this.zzbtU);
            application.registerActivityLifecycleCallbacks(this.zzbtU);
            zzJt().zzLg().log("Registered activity lifecycle callback");
        }
    }

    @WorkerThread
    public void zzLR() {
        zzmq();
        zzJe();
        zznA();
        if (this.zzbpw.zzLt()) {
            zzJl().zzLR();
            String zzLp = zzJu().zzLp();
            if (!TextUtils.isEmpty(zzLp) && !zzLp.equals(zzJk().zzKU())) {
                Bundle bundle = new Bundle();
                bundle.putString("_po", zzLp);
                zze("auto", "_ou", bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Bundle zzN(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzl = zzJp().zzl(str, bundle.get(str));
                if (zzl == null) {
                    zzJt().zzLc().zzj("Param value can't be null", str);
                } else {
                    zzJp().zza(bundle2, str, zzl);
                }
            }
        }
        return bundle2;
    }

    @WorkerThread
    public void zza(AppMeasurement.zzb zzb) {
        zzmq();
        zzJe();
        zznA();
        if (!(zzb == null || zzb == this.zzbtV)) {
            zzac.zza(this.zzbtV == null, (Object) "EventInterceptor already set.");
        }
        this.zzbtV = zzb;
    }

    public void zza(AppMeasurement.zzc zzc) {
        zzJe();
        zznA();
        zzac.zzw(zzc);
        if (!this.zzbtW.add(zzc)) {
            zzJt().zzLc().log("OnEventListener already registered");
        }
    }

    /* access modifiers changed from: protected */
    public void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        final Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        final String str4 = str;
        final String str5 = str2;
        final long j2 = j;
        final boolean z4 = z;
        final boolean z5 = z2;
        final boolean z6 = z3;
        final String str6 = str3;
        zzJs().zzm(new Runnable() {
            public void run() {
                zzatu.this.zzb(str4, str5, j2, bundle2, z4, z5, z6, str6);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void zza(String str, String str2, long j, Object obj) {
        final String str3 = str;
        final String str4 = str2;
        final Object obj2 = obj;
        final long j2 = j;
        zzJs().zzm(new Runnable() {
            public void run() {
                zzatu.this.zza(str3, str4, obj2, j2);
            }
        });
    }

    public void zza(String str, String str2, Bundle bundle, boolean z) {
        zzJe();
        zza(str, str2, bundle, true, this.zzbtV == null || zzaue.zzgg(str2), z, null);
    }

    public List<zzaub> zzaI(final boolean z) {
        zzJe();
        zznA();
        zzJt().zzLf().log("Fetching user attributes (FE)");
        final AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzbpw.zzJs().zzm(new Runnable() {
                public void run() {
                    zzatu.this.zzJl().zza((AtomicReference<List<zzaub>>) atomicReference, z);
                }
            });
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzJt().zzLc().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzaub> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzJt().zzLc().log("Timed out waiting for get user properties");
        return Collections.emptyList();
    }

    public void zzd(String str, String str2, Bundle bundle, long j) {
        zzJe();
        zza(str, str2, j, bundle, false, true, true, null);
    }

    public void zzd(String str, String str2, Object obj) {
        int i = 0;
        zzac.zzdv(str);
        long currentTimeMillis = zznq().currentTimeMillis();
        int zzga = zzJp().zzga(str2);
        if (zzga != 0) {
            String zza2 = zzJp().zza(str2, zzJv().zzJV(), true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzbpw.zzJp().zza(zzga, "_ev", zza2, i);
        } else if (obj != null) {
            int zzm = zzJp().zzm(str2, obj);
            if (zzm != 0) {
                String zza3 = zzJp().zza(str2, zzJv().zzJV(), true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzbpw.zzJp().zza(zzm, "_ev", zza3, i);
                return;
            }
            Object zzn = zzJp().zzn(str2, obj);
            if (zzn != null) {
                zza(str, str2, currentTimeMillis, zzn);
            }
        } else {
            zza(str, str2, currentTimeMillis, (Object) null);
        }
    }

    public void zze(String str, String str2, Bundle bundle) {
        zzJe();
        zza(str, str2, bundle, true, this.zzbtV == null || zzaue.zzgg(str2), false, null);
    }

    @WorkerThread
    public void zzf(Class<?> cls) {
        try {
            cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{getContext()});
        } catch (Exception e) {
            zzJt().zzLc().zzj("Failed to invoke Tag Manager's initialize() method", e);
        }
    }

    @Nullable
    @WorkerThread
    public synchronized String zzfS(String str) {
        String str2 = null;
        synchronized (this) {
            zznA();
            zzJe();
            if (zzJs().zzLr()) {
                zzJt().zzLa().log("Cannot retrieve app instance id from analytics worker thread");
            } else if (zzJs().zzbd()) {
                zzJt().zzLa().log("Cannot retrieve app instance id from main thread");
            } else {
                if (str != null) {
                    if (str.equals(this.zzbtZ)) {
                        str2 = this.zzbtY;
                    }
                }
                final AtomicReference atomicReference = new AtomicReference();
                synchronized (atomicReference) {
                    this.zzbpw.zzJs().zzm(new Runnable() {
                        public void run() {
                            zzatu.this.zzJl().zza((AtomicReference<String>) atomicReference);
                        }
                    });
                    try {
                        atomicReference.wait(30000);
                    } catch (InterruptedException e) {
                        zzJt().zzLc().log("Interrupted waiting for app instance id");
                    }
                }
                this.zzbtZ = str;
                this.zzbtY = (String) atomicReference.get();
                str2 = this.zzbtY;
            }
        }
        return str2;
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
}
