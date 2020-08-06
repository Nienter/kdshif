package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zze;

public class zzatf extends zzats {
    private String zzVQ;
    private String zzabK;
    private String zzabL;
    private String zzbpK;
    private String zzbpR;
    private int zzbrB;
    private long zzbrC;

    zzatf(zzatp zzatp) {
        super(zzatp);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    public String getGmpAppId() {
        zznA();
        return this.zzbpK;
    }

    /* access modifiers changed from: package-private */
    public String zzJC() {
        zznA();
        return this.zzbpR;
    }

    /* access modifiers changed from: package-private */
    public long zzJD() {
        return zzJv().zzJD();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public long zzJE() {
        zznA();
        zzmq();
        if (this.zzbrC == 0) {
            this.zzbrC = this.zzbpw.zzJp().zzE(getContext(), getContext().getPackageName());
        }
        return this.zzbrC;
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

    /* access modifiers changed from: package-private */
    public int zzKZ() {
        zznA();
        return this.zzbrB;
    }

    /* access modifiers changed from: protected */
    public void zzbv(Status status) {
        if (status == null) {
            zzJt().zzLa().log("GoogleService failed to initialize (no status)");
        } else {
            zzJt().zzLa().zze("GoogleService failed to initialize, status", Integer.valueOf(status.getStatusCode()), status.getStatusMessage());
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public zzasq zzfH(String str) {
        zzmq();
        return new zzasq(zzjI(), getGmpAppId(), zzmy(), (long) zzKZ(), zzJC(), zzJD(), zzJE(), str, this.zzbpw.isEnabled(), !zzJu().zzbsu, zzJu().zzJy());
    }

    /* access modifiers changed from: package-private */
    public String zzjI() {
        zznA();
        return this.zzVQ;
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
        boolean z;
        String str = "unknown";
        String str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        int i = Integer.MIN_VALUE;
        String str3 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        String packageName = getContext().getPackageName();
        PackageManager packageManager = getContext().getPackageManager();
        if (packageManager == null) {
            zzJt().zzLa().zzj("PackageManager is null, app identity information might be inaccurate. appId", zzati.zzfI(packageName));
        } else {
            try {
                str = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                zzJt().zzLa().zzj("Error retrieving app installer package name. appId", zzati.zzfI(packageName));
            }
            if (str == null) {
                str = "manual_install";
            } else if ("com.android.vending".equals(str)) {
                str = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    if (!TextUtils.isEmpty(applicationLabel)) {
                        str3 = applicationLabel.toString();
                    }
                    str2 = packageInfo.versionName;
                    i = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                zzJt().zzLa().zze("Error retrieving package info. appId, appName", zzati.zzfI(packageName), str3);
            }
        }
        this.zzVQ = packageName;
        this.zzbpR = str;
        this.zzabL = str2;
        this.zzbrB = i;
        this.zzabK = str3;
        zzJv().zzKk();
        Status zzay = zzaas.zzay(getContext());
        boolean z2 = zzay != null && zzay.isSuccess();
        if (!z2) {
            zzbv(zzay);
        }
        if (z2) {
            Boolean zzKm = zzJv().zzKm();
            if (zzJv().zzKl()) {
                zzJt().zzLe().log("Collection disabled with firebase_analytics_collection_deactivated=1");
                z = false;
            } else if (zzKm != null && !zzKm.booleanValue()) {
                zzJt().zzLe().log("Collection disabled with firebase_analytics_collection_enabled=0");
                z = false;
            } else if (zzKm != null || !zzJv().zzwk()) {
                zzJt().zzLg().log("Collection enabled");
                z = true;
            } else {
                zzJt().zzLe().log("Collection disabled with google_app_measurement_enable=0");
                z = false;
            }
        } else {
            z = false;
        }
        this.zzbpK = "";
        zzJv().zzKk();
        try {
            String zzwj = zzaas.zzwj();
            if (TextUtils.isEmpty(zzwj)) {
                zzwj = "";
            }
            this.zzbpK = zzwj;
            if (z) {
                zzJt().zzLg().zze("App package, google app id", this.zzVQ, this.zzbpK);
            }
        } catch (IllegalStateException e3) {
            zzJt().zzLa().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzati.zzfI(packageName), e3);
        }
    }

    /* access modifiers changed from: package-private */
    public String zzmy() {
        zznA();
        return this.zzabL;
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }
}
