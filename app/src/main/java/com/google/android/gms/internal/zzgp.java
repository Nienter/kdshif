package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzhd;
import java.util.List;

@zzmb
public class zzgp extends zzhd.zza implements zzgu.zza {
    private Bundle mExtras;
    private String zzFS;
    private List<zzgo> zzFT;
    private String zzFU;
    private zzgz zzFV;
    private String zzFW;
    private double zzFX;
    private String zzFY;
    private String zzFZ;
    @Nullable
    private zzgm zzGa;
    @Nullable
    private zzew zzGb;
    @Nullable
    private View zzGc;
    private zzgu zzGd;
    private Object zzrN = new Object();

    public zzgp(String str, List list, String str2, zzgz zzgz, String str3, double d, String str4, String str5, @Nullable zzgm zzgm, Bundle bundle, zzew zzew, View view) {
        this.zzFS = str;
        this.zzFT = list;
        this.zzFU = str2;
        this.zzFV = zzgz;
        this.zzFW = str3;
        this.zzFX = d;
        this.zzFY = str4;
        this.zzFZ = str5;
        this.zzGa = zzgm;
        this.mExtras = bundle;
        this.zzGb = zzew;
        this.zzGc = view;
    }

    public void destroy() {
        this.zzFS = null;
        this.zzFT = null;
        this.zzFU = null;
        this.zzFV = null;
        this.zzFW = null;
        this.zzFX = 0.0d;
        this.zzFY = null;
        this.zzFZ = null;
        this.zzGa = null;
        this.mExtras = null;
        this.zzrN = null;
        this.zzGd = null;
        this.zzGb = null;
        this.zzGc = null;
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

    public String getPrice() {
        return this.zzFZ;
    }

    public double getStarRating() {
        return this.zzFX;
    }

    public String getStore() {
        return this.zzFY;
    }

    public void zzb(zzgu zzgu) {
        synchronized (this.zzrN) {
            this.zzGd = zzgu;
        }
    }

    public zzew zzbG() {
        return this.zzGb;
    }

    public zzgz zzfL() {
        return this.zzFV;
    }

    public zzd zzfM() {
        return zze.zzA(this.zzGd);
    }

    public String zzfN() {
        return "2";
    }

    public zzgm zzfO() {
        return this.zzGa;
    }

    public View zzfP() {
        return this.zzGc;
    }
}
