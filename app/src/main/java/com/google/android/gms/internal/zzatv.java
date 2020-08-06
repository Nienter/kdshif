package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.p001v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class zzatv extends zzats {
    protected zza zzbul;
    private volatile AppMeasurement.zzf zzbum;
    private AppMeasurement.zzf zzbun;
    private long zzbuo;
    private final Map<Activity, zza> zzbup = new ArrayMap();
    private final CopyOnWriteArrayList<AppMeasurement.zzd> zzbuq = new CopyOnWriteArrayList<>();
    private boolean zzbur;
    private AppMeasurement.zzf zzbus;
    private String zzbut;

    static class zza extends AppMeasurement.zzf {
        public boolean zzbuy;

        public zza(zza zza) {
            this.zzbpz = zza.zzbpz;
            this.zzbpA = zza.zzbpA;
            this.zzbpB = zza.zzbpB;
            this.zzbuy = zza.zzbuy;
        }

        public zza(String str, String str2, long j) {
            this.zzbpz = str;
            this.zzbpA = str2;
            this.zzbpB = j;
            this.zzbuy = false;
        }
    }

    public zzatv(zzatp zzatp) {
        super(zzatp);
    }

    @MainThread
    private void zza(Activity activity, zza zza2, final boolean z) {
        boolean z2;
        boolean z3 = true;
        AppMeasurement.zzf zzf = this.zzbum != null ? this.zzbum : (this.zzbun == null || Math.abs(zznq().elapsedRealtime() - this.zzbuo) >= 1000) ? null : this.zzbun;
        AppMeasurement.zzf zzf2 = zzf != null ? new AppMeasurement.zzf(zzf) : null;
        this.zzbur = true;
        try {
            Iterator<AppMeasurement.zzd> it = this.zzbuq.iterator();
            while (it.hasNext()) {
                try {
                    z2 = it.next().zza(zzf2, zza2) & z3;
                } catch (Exception e) {
                    zzJt().zzLa().zzj("onScreenChangeCallback threw exception", e);
                    z2 = z3;
                }
                z3 = z2;
            }
        } catch (Exception e2) {
            zzJt().zzLa().zzj("onScreenChangeCallback loop threw exception", e2);
        } finally {
            this.zzbur = false;
        }
        if (z3) {
            if (zza2.zzbpA == null) {
                zza2.zzbpA = zzfV(activity.getClass().getCanonicalName());
            }
            final zza zza3 = new zza(zza2);
            this.zzbun = this.zzbum;
            this.zzbuo = zznq().elapsedRealtime();
            this.zzbum = zza3;
            zzJs().zzm(new Runnable() {
                public void run() {
                    if (z && zzatv.this.zzbul != null) {
                        zzatv.this.zza(zzatv.this.zzbul);
                    }
                    zzatv.this.zzbul = zza3;
                    zzatv.this.zzJl().zza((AppMeasurement.zzf) zza3);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zza(@NonNull zza zza2) {
        zzJg().zzV(zznq().elapsedRealtime());
        if (zzJr().zzaJ(zza2.zzbuy)) {
            zza2.zzbuy = false;
        }
    }

    public static void zza(AppMeasurement.zzf zzf, Bundle bundle) {
        if (bundle != null && zzf != null && !bundle.containsKey("_sc")) {
            if (zzf.zzbpz != null) {
                bundle.putString("_sn", zzf.zzbpz);
            }
            bundle.putString("_sc", zzf.zzbpA);
            bundle.putLong("_si", zzf.zzbpB);
        }
    }

    static String zzfV(String str) {
        String[] split = str.split("\\.");
        if (split.length == 0) {
            return str.substring(0, 36);
        }
        String str2 = split[split.length - 1];
        return str2.length() > 36 ? str2.substring(0, 36) : str2;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @MainThread
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.firebase.analytics.screen_service");
            if (bundle2 != null) {
                zza zzv = zzv(activity);
                zzv.zzbpB = bundle2.getLong("id");
                zzv.zzbpz = bundle2.getString("name");
                zzv.zzbpA = bundle2.getString("referrer_name");
            }
        }
    }

    @MainThread
    public void onActivityDestroyed(Activity activity) {
        this.zzbup.remove(activity);
    }

    @MainThread
    public void onActivityPaused(Activity activity) {
        final zza zzv = zzv(activity);
        this.zzbun = this.zzbum;
        this.zzbuo = zznq().elapsedRealtime();
        this.zzbum = null;
        zzJs().zzm(new Runnable() {
            public void run() {
                zzatv.this.zza(zzv);
                zzatv.this.zzbul = null;
                zzatv.this.zzJl().zza((AppMeasurement.zzf) null);
            }
        });
    }

    @MainThread
    public void onActivityResumed(Activity activity) {
        zza(activity, zzv(activity), false);
        zzJg().zzJc();
    }

    @MainThread
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (bundle != null) {
            zza zza2 = this.zzbup.get(activity);
            if (zza2 != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong("id", zza2.zzbpB);
                bundle2.putString("name", zza2.zzbpz);
                bundle2.putString("referrer_name", zza2.zzbpA);
                bundle.putBundle("com.google.firebase.analytics.screen_service", bundle2);
            }
        }
    }

    @MainThread
    public void registerOnScreenChangeCallback(@NonNull AppMeasurement.zzd zzd) {
        zzJe();
        if (zzd == null) {
            zzJt().zzLc().log("Attempting to register null OnScreenChangeCallback");
            return;
        }
        this.zzbuq.remove(zzd);
        this.zzbuq.add(zzd);
    }

    @MainThread
    public void setCurrentScreen(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        if (Build.VERSION.SDK_INT < 14) {
            zzJt().zzLd().log("Screen engagement recording is only available at API level 14+");
        } else if (activity == null) {
            zzJt().zzLc().log("setCurrentScreen must be called with a non-null activity");
        } else if (!zzJs().zzbd()) {
            zzJt().zzLc().log("setCurrentScreen must be called from the main thread");
        } else if (this.zzbur) {
            zzJt().zzLc().log("Cannot call setCurrentScreen from onScreenChangeCallback");
        } else if (this.zzbum == null) {
            zzJt().zzLc().log("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzbup.get(activity) == null) {
            zzJt().zzLc().log("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zzfV(activity.getClass().getCanonicalName());
            }
            boolean equals = this.zzbum.zzbpA.equals(str2);
            boolean z = (this.zzbum.zzbpz == null && str == null) || (this.zzbum.zzbpz != null && this.zzbum.zzbpz.equals(str));
            if (equals && z) {
                zzJt().zzLd().log("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() < 1 || str.length() > zzJv().zzJX())) {
                zzJt().zzLc().zzj("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() >= 1 && str2.length() <= zzJv().zzJX())) {
                zzJt().zzLg().zze("Setting current screen to name, class", str == null ? "null" : str, str2);
                zza zza2 = new zza(str, str2, zzJp().zzMi());
                this.zzbup.put(activity, zza2);
                zza(activity, zza2, true);
            } else {
                zzJt().zzLc().zzj("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    @MainThread
    public void unregisterOnScreenChangeCallback(@NonNull AppMeasurement.zzd zzd) {
        zzJe();
        this.zzbuq.remove(zzd);
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

    @WorkerThread
    public zza zzLU() {
        zznA();
        zzmq();
        return this.zzbul;
    }

    public AppMeasurement.zzf zzLV() {
        zzJe();
        AppMeasurement.zzf zzf = this.zzbum;
        if (zzf == null) {
            return null;
        }
        return new AppMeasurement.zzf(zzf);
    }

    @WorkerThread
    public void zza(String str, AppMeasurement.zzf zzf) {
        zzmq();
        synchronized (this) {
            if (this.zzbut == null || this.zzbut.equals(str) || zzf != null) {
                this.zzbut = str;
                this.zzbus = zzf;
            }
        }
    }

    public AppMeasurement.zzf zzfU(String str) {
        AppMeasurement.zzf zzf;
        synchronized (this) {
            zzf = (this.zzbus == null || this.zzbut == null || !this.zzbut.equals(str)) ? null : this.zzbus;
        }
        return zzf;
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
    @MainThread
    public zza zzv(@NonNull Activity activity) {
        zzac.zzw(activity);
        zza zza2 = this.zzbup.get(activity);
        if (zza2 != null) {
            return zza2;
        }
        zza zza3 = new zza(null, zzfV(activity.getClass().getCanonicalName()), zzJp().zzMi());
        this.zzbup.put(activity, zza3);
        return zza3;
    }
}
