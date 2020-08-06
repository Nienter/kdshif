package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzatd;
import java.lang.reflect.InvocationTargetException;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

public class zzast extends zzatr {
    static final String zzbqo = String.valueOf(zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    private Boolean zzadY;

    zzast(zzatp zzatp) {
        super(zzatp);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public long zzJD() {
        return 10084;
    }

    /* access modifiers changed from: package-private */
    public String zzJS() {
        return zzatd.zzbqV.get();
    }

    public int zzJT() {
        return 25;
    }

    public int zzJU() {
        return 40;
    }

    public int zzJV() {
        return 24;
    }

    /* access modifiers changed from: package-private */
    public int zzJW() {
        return 40;
    }

    /* access modifiers changed from: package-private */
    public int zzJX() {
        return 100;
    }

    /* access modifiers changed from: package-private */
    public int zzJY() {
        return 256;
    }

    public int zzJZ() {
        return 36;
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

    public long zzKA() {
        return Math.max(0, zzatd.zzbrr.get().longValue());
    }

    public long zzKB() {
        return zzatd.zzbrn.get().longValue();
    }

    public long zzKC() {
        return Math.max(0, zzatd.zzbru.get().longValue());
    }

    public long zzKD() {
        return Math.max(0, zzatd.zzbrv.get().longValue());
    }

    public int zzKE() {
        return Math.min(20, Math.max(0, zzatd.zzbrw.get().intValue()));
    }

    public String zzKF() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            zzJt().zzLa().zzj("Could not find SystemProperties class", e);
        } catch (NoSuchMethodException e2) {
            zzJt().zzLa().zzj("Could not find SystemProperties.get() method", e2);
        } catch (IllegalAccessException e3) {
            zzJt().zzLa().zzj("Could not access SystemProperties.get()", e3);
        } catch (InvocationTargetException e4) {
            zzJt().zzLa().zzj("SystemProperties.get() threw an exception", e4);
        }
        return "";
    }

    public int zzKa() {
        return 2048;
    }

    /* access modifiers changed from: package-private */
    public int zzKb() {
        return 500;
    }

    public long zzKc() {
        return (long) zzatd.zzbrf.get().intValue();
    }

    public long zzKd() {
        return (long) zzatd.zzbrh.get().intValue();
    }

    /* access modifiers changed from: package-private */
    public int zzKe() {
        return 25;
    }

    /* access modifiers changed from: package-private */
    public int zzKf() {
        return 50;
    }

    /* access modifiers changed from: package-private */
    public long zzKg() {
        return 3600000;
    }

    /* access modifiers changed from: package-private */
    public long zzKh() {
        return 60000;
    }

    /* access modifiers changed from: package-private */
    public long zzKi() {
        return 61000;
    }

    /* access modifiers changed from: package-private */
    public String zzKj() {
        return "google_app_measurement_local.db";
    }

    public boolean zzKk() {
        return false;
    }

    public boolean zzKl() {
        Boolean zzft = zzft("firebase_analytics_collection_deactivated");
        return zzft != null && zzft.booleanValue();
    }

    public Boolean zzKm() {
        return zzft("firebase_analytics_collection_enabled");
    }

    public long zzKn() {
        return zzatd.zzbrx.get().longValue();
    }

    public long zzKo() {
        return zzatd.zzbrs.get().longValue();
    }

    public long zzKp() {
        return zzatd.zzbrt.get().longValue();
    }

    public long zzKq() {
        return 1000;
    }

    public int zzKr() {
        return Math.max(0, zzatd.zzbrd.get().intValue());
    }

    public int zzKs() {
        return Math.max(1, zzatd.zzbre.get().intValue());
    }

    public int zzKt() {
        return 100000;
    }

    public String zzKu() {
        return zzatd.zzbrl.get();
    }

    public long zzKv() {
        return zzatd.zzbqY.get().longValue();
    }

    public long zzKw() {
        return Math.max(0, zzatd.zzbrm.get().longValue());
    }

    public long zzKx() {
        return Math.max(0, zzatd.zzbro.get().longValue());
    }

    public long zzKy() {
        return Math.max(0, zzatd.zzbrp.get().longValue());
    }

    public long zzKz() {
        return Math.max(0, zzatd.zzbrq.get().longValue());
    }

    public String zzO(String str, String str2) {
        Uri.Builder builder = new Uri.Builder();
        Uri.Builder encodedAuthority = builder.scheme(zzatd.zzbqZ.get()).encodedAuthority(zzatd.zzbra.get());
        String valueOf = String.valueOf(str);
        encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", str2).appendQueryParameter("platform", AbstractSpiCall.ANDROID_CLIENT_TYPE).appendQueryParameter("gmp_version", String.valueOf(10084));
        return builder.build().toString();
    }

    public long zza(String str, zzatd.zza<Long> zza) {
        if (str == null) {
            return zza.get().longValue();
        }
        String zzW = zzJq().zzW(str, zza.getKey());
        if (TextUtils.isEmpty(zzW)) {
            return zza.get().longValue();
        }
        try {
            return zza.get(Long.valueOf(Long.valueOf(zzW).longValue())).longValue();
        } catch (NumberFormatException e) {
            return zza.get().longValue();
        }
    }

    public int zzb(String str, zzatd.zza<Integer> zza) {
        if (str == null) {
            return zza.get().intValue();
        }
        String zzW = zzJq().zzW(str, zza.getKey());
        if (TextUtils.isEmpty(zzW)) {
            return zza.get().intValue();
        }
        try {
            return zza.get(Integer.valueOf(Integer.valueOf(zzW).intValue())).intValue();
        } catch (NumberFormatException e) {
            return zza.get().intValue();
        }
    }

    public int zzfn(@Size(min = 1) String str) {
        return Math.max(0, Math.min(1000000, zzb(str, zzatd.zzbrg)));
    }

    public int zzfo(@Size(min = 1) String str) {
        return zzb(str, zzatd.zzbri);
    }

    public int zzfp(@Size(min = 1) String str) {
        return zzb(str, zzatd.zzbrj);
    }

    /* access modifiers changed from: package-private */
    public long zzfq(String str) {
        return zza(str, zzatd.zzbqW);
    }

    /* access modifiers changed from: package-private */
    public int zzfr(String str) {
        return zzb(str, zzatd.zzbry);
    }

    /* access modifiers changed from: package-private */
    public int zzfs(String str) {
        return Math.max(0, Math.min(2000, zzb(str, zzatd.zzbrz)));
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Boolean zzft(@Size(min = 1) String str) {
        zzac.zzdv(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzJt().zzLa().log("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = zzacx.zzaQ(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo == null) {
                zzJt().zzLa().log("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData == null) {
                zzJt().zzLa().log("Failed to load metadata: Metadata bundle is null");
                return null;
            } else if (applicationInfo.metaData.containsKey(str)) {
                return Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            } else {
                return null;
            }
        } catch (PackageManager.NameNotFoundException e) {
            zzJt().zzLa().zzj("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public int zzfu(String str) {
        return zzb(str, zzatd.zzbrb);
    }

    public int zzfv(String str) {
        return Math.max(0, zzb(str, zzatd.zzbrc));
    }

    public int zzfw(String str) {
        return Math.max(0, Math.min(1000000, zzb(str, zzatd.zzbrk)));
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }

    /* access modifiers changed from: package-private */
    public long zzoQ() {
        return zzatd.zzbrA.get().longValue();
    }

    public String zzoV() {
        return "google_app_measurement.db";
    }

    public long zzoZ() {
        return Math.max(0, zzatd.zzbqX.get().longValue());
    }

    public boolean zzow() {
        if (this.zzadY == null) {
            synchronized (this) {
                if (this.zzadY == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String zzyK = zzt.zzyK();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzadY = Boolean.valueOf(str != null && str.equals(zzyK));
                    }
                    if (this.zzadY == null) {
                        this.zzadY = Boolean.TRUE;
                        zzJt().zzLa().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzadY.booleanValue();
    }

    public boolean zzwk() {
        return zzaas.zzwk();
    }
}
