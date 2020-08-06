package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzm;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzhz;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzqa;
import com.google.android.gms.internal.zzqp;

@zzmb
public final class AdOverlayInfoParcel extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator<AdOverlayInfoParcel> CREATOR = new zzg();
    public final int orientation;
    public final String url;
    public final int versionCode;
    public final zzc zzMW;
    public final zzdt zzMX;
    public final zzh zzMY;
    public final zzqp zzMZ;
    public final zzht zzNa;
    public final String zzNb;
    public final boolean zzNc;
    public final String zzNd;
    public final zzq zzNe;
    public final int zzNf;
    public final zzhz zzNg;
    public final String zzNh;
    public final zzm zzNi;
    public final zzqa zzvf;

    AdOverlayInfoParcel(int i, zzc zzc, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i2, int i3, String str3, zzqa zzqa, IBinder iBinder6, String str4, zzm zzm) {
        this.versionCode = i;
        this.zzMW = zzc;
        this.zzMX = (zzdt) zze.zzE(zzd.zza.zzcd(iBinder));
        this.zzMY = (zzh) zze.zzE(zzd.zza.zzcd(iBinder2));
        this.zzMZ = (zzqp) zze.zzE(zzd.zza.zzcd(iBinder3));
        this.zzNa = (zzht) zze.zzE(zzd.zza.zzcd(iBinder4));
        this.zzNb = str;
        this.zzNc = z;
        this.zzNd = str2;
        this.zzNe = (zzq) zze.zzE(zzd.zza.zzcd(iBinder5));
        this.orientation = i2;
        this.zzNf = i3;
        this.url = str3;
        this.zzvf = zzqa;
        this.zzNg = (zzhz) zze.zzE(zzd.zza.zzcd(iBinder6));
        this.zzNh = str4;
        this.zzNi = zzm;
    }

    public AdOverlayInfoParcel(zzc zzc, zzdt zzdt, zzh zzh, zzq zzq, zzqa zzqa) {
        this.versionCode = 4;
        this.zzMW = zzc;
        this.zzMX = zzdt;
        this.zzMY = zzh;
        this.zzMZ = null;
        this.zzNa = null;
        this.zzNb = null;
        this.zzNc = false;
        this.zzNd = null;
        this.zzNe = zzq;
        this.orientation = -1;
        this.zzNf = 4;
        this.url = null;
        this.zzvf = zzqa;
        this.zzNg = null;
        this.zzNh = null;
        this.zzNi = null;
    }

    public AdOverlayInfoParcel(zzdt zzdt, zzh zzh, zzq zzq, zzqp zzqp, int i, zzqa zzqa, String str, zzm zzm) {
        this.versionCode = 4;
        this.zzMW = null;
        this.zzMX = zzdt;
        this.zzMY = zzh;
        this.zzMZ = zzqp;
        this.zzNa = null;
        this.zzNb = null;
        this.zzNc = false;
        this.zzNd = null;
        this.zzNe = zzq;
        this.orientation = i;
        this.zzNf = 1;
        this.url = null;
        this.zzvf = zzqa;
        this.zzNg = null;
        this.zzNh = str;
        this.zzNi = zzm;
    }

    public AdOverlayInfoParcel(zzdt zzdt, zzh zzh, zzq zzq, zzqp zzqp, boolean z, int i, zzqa zzqa) {
        this.versionCode = 4;
        this.zzMW = null;
        this.zzMX = zzdt;
        this.zzMY = zzh;
        this.zzMZ = zzqp;
        this.zzNa = null;
        this.zzNb = null;
        this.zzNc = z;
        this.zzNd = null;
        this.zzNe = zzq;
        this.orientation = i;
        this.zzNf = 2;
        this.url = null;
        this.zzvf = zzqa;
        this.zzNg = null;
        this.zzNh = null;
        this.zzNi = null;
    }

    public AdOverlayInfoParcel(zzdt zzdt, zzh zzh, zzht zzht, zzq zzq, zzqp zzqp, boolean z, int i, String str, zzqa zzqa, zzhz zzhz) {
        this.versionCode = 4;
        this.zzMW = null;
        this.zzMX = zzdt;
        this.zzMY = zzh;
        this.zzMZ = zzqp;
        this.zzNa = zzht;
        this.zzNb = null;
        this.zzNc = z;
        this.zzNd = null;
        this.zzNe = zzq;
        this.orientation = i;
        this.zzNf = 3;
        this.url = str;
        this.zzvf = zzqa;
        this.zzNg = zzhz;
        this.zzNh = null;
        this.zzNi = null;
    }

    public AdOverlayInfoParcel(zzdt zzdt, zzh zzh, zzht zzht, zzq zzq, zzqp zzqp, boolean z, int i, String str, String str2, zzqa zzqa, zzhz zzhz) {
        this.versionCode = 4;
        this.zzMW = null;
        this.zzMX = zzdt;
        this.zzMY = zzh;
        this.zzMZ = zzqp;
        this.zzNa = zzht;
        this.zzNb = str2;
        this.zzNc = z;
        this.zzNd = str;
        this.zzNe = zzq;
        this.orientation = i;
        this.zzNf = 3;
        this.url = null;
        this.zzvf = zzqa;
        this.zzNg = zzhz;
        this.zzNh = null;
        this.zzNi = null;
    }

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzb(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhs() {
        return zze.zzA(this.zzMX).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzht() {
        return zze.zzA(this.zzMY).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhu() {
        return zze.zzA(this.zzMZ).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhv() {
        return zze.zzA(this.zzNa).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhw() {
        return zze.zzA(this.zzNg).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder zzhx() {
        return zze.zzA(this.zzNe).asBinder();
    }
}
