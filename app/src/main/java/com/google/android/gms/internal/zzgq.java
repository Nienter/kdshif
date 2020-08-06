package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzhf;
import java.util.List;

@zzmb
public class zzgq extends zzhf.zza implements zzgu.zza {
    private Bundle mExtras;
    private String zzFS;
    private List<zzgo> zzFT;
    private String zzFU;
    private String zzFW;
    @Nullable
    private zzgm zzGa;
    private zzgu zzGd;
    private zzgz zzGe;
    private String zzGf;
    private Object zzrN = new Object();

    public zzgq(String str, List list, String str2, zzgz zzgz, String str3, String str4, @Nullable zzgm zzgm, Bundle bundle) {
        this.zzFS = str;
        this.zzFT = list;
        this.zzFU = str2;
        this.zzGe = zzgz;
        this.zzFW = str3;
        this.zzGf = str4;
        this.zzGa = zzgm;
        this.mExtras = bundle;
    }

    public void destroy() {
        this.zzFS = null;
        this.zzFT = null;
        this.zzFU = null;
        this.zzGe = null;
        this.zzFW = null;
        this.zzGf = null;
        this.zzGa = null;
        this.mExtras = null;
        this.zzrN = null;
        this.zzGd = null;
    }

    public String getAdvertiser() {
        return this.zzGf;
    }

    public String getBody() {
        return this.zzFU;
    }

    public String getCallToAction() {
        return this.zzFW;
    }

    public String getCustomTemplateId() {
        return "";
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public String getHeadline() {
        return this.zzFS;
    }

    public List getImages() {
        return this.zzFT;
    }

    public void zzb(zzgu zzgu) {
        synchronized (this.zzrN) {
            this.zzGd = zzgu;
        }
    }

    public zzd zzfM() {
        return zze.zzA(this.zzGd);
    }

    public String zzfN() {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES;
    }

    public zzgm zzfO() {
        return this.zzGa;
    }

    public zzgz zzfQ() {
        return this.zzGe;
    }
}
