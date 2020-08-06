package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzmi implements Parcelable.Creator<zzmh> {
    static void zza(zzmh zzmh, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzmh.versionCode);
        zzc.zza(parcel, 2, zzmh.zzRc, false);
        zzc.zza(parcel, 3, (Parcelable) zzmh.zzRd, i, false);
        zzc.zza(parcel, 4, (Parcelable) zzmh.zzvj, i, false);
        zzc.zza(parcel, 5, zzmh.zzvd, false);
        zzc.zza(parcel, 6, (Parcelable) zzmh.applicationInfo, i, false);
        zzc.zza(parcel, 7, (Parcelable) zzmh.zzRe, i, false);
        zzc.zza(parcel, 8, zzmh.zzRf, false);
        zzc.zza(parcel, 9, zzmh.zzRg, false);
        zzc.zza(parcel, 10, zzmh.zzRh, false);
        zzc.zza(parcel, 11, (Parcelable) zzmh.zzvf, i, false);
        zzc.zza(parcel, 12, zzmh.zzRi, false);
        zzc.zzc(parcel, 13, zzmh.zzRj);
        zzc.zzb(parcel, 14, zzmh.zzvB, false);
        zzc.zza(parcel, 15, zzmh.zzRk, false);
        zzc.zza(parcel, 16, zzmh.zzRl);
        zzc.zza(parcel, 17, (Parcelable) zzmh.zzRm, i, false);
        zzc.zzc(parcel, 18, zzmh.zzRn);
        zzc.zzc(parcel, 19, zzmh.zzRo);
        zzc.zza(parcel, 20, zzmh.zzxa);
        zzc.zza(parcel, 21, zzmh.zzRp, false);
        zzc.zza(parcel, 25, zzmh.zzRq);
        zzc.zza(parcel, 26, zzmh.zzRr, false);
        zzc.zzb(parcel, 27, zzmh.zzRs, false);
        zzc.zza(parcel, 28, zzmh.zzvc, false);
        zzc.zza(parcel, 29, (Parcelable) zzmh.zzvx, i, false);
        zzc.zzb(parcel, 30, zzmh.zzRt, false);
        zzc.zza(parcel, 31, zzmh.zzRu);
        zzc.zza(parcel, 32, (Parcelable) zzmh.zzRv, i, false);
        zzc.zza(parcel, 33, zzmh.zzRw, false);
        zzc.zza(parcel, 34, zzmh.zzRx);
        zzc.zzc(parcel, 35, zzmh.zzRy);
        zzc.zzc(parcel, 36, zzmh.zzRz);
        zzc.zza(parcel, 37, zzmh.zzRA);
        zzc.zza(parcel, 38, zzmh.zzRB);
        zzc.zza(parcel, 39, zzmh.zzRC, false);
        zzc.zza(parcel, 40, zzmh.zzRD);
        zzc.zza(parcel, 41, zzmh.zzRE, false);
        zzc.zza(parcel, 42, zzmh.zzKc);
        zzc.zzc(parcel, 43, zzmh.zzRF);
        zzc.zza(parcel, 44, zzmh.zzRG, false);
        zzc.zza(parcel, 45, zzmh.zzRH, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzT */
    public zzmh[] newArray(int i) {
        return new zzmh[i];
    }

    /* renamed from: zzm */
    public zzmh createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        Bundle bundle = null;
        zzdy zzdy = null;
        zzec zzec = null;
        String str = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        zzqa zzqa = null;
        Bundle bundle2 = null;
        int i2 = 0;
        ArrayList<String> arrayList = null;
        Bundle bundle3 = null;
        boolean z = false;
        Messenger messenger = null;
        int i3 = 0;
        int i4 = 0;
        float f = 0.0f;
        String str5 = null;
        long j = 0;
        String str6 = null;
        ArrayList<String> arrayList2 = null;
        String str7 = null;
        zzgw zzgw = null;
        ArrayList<String> arrayList3 = null;
        long j2 = 0;
        zzmo zzmo = null;
        String str8 = null;
        float f2 = 0.0f;
        boolean z2 = false;
        int i5 = 0;
        int i6 = 0;
        boolean z3 = false;
        boolean z4 = false;
        String str9 = null;
        String str10 = null;
        boolean z5 = false;
        int i7 = 0;
        Bundle bundle4 = null;
        String str11 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    bundle = zzb.zzs(parcel, zzaT);
                    break;
                case 3:
                    zzdy = (zzdy) zzb.zza(parcel, zzaT, zzdy.CREATOR);
                    break;
                case 4:
                    zzec = (zzec) zzb.zza(parcel, zzaT, zzec.CREATOR);
                    break;
                case 5:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    applicationInfo = (ApplicationInfo) zzb.zza(parcel, zzaT, ApplicationInfo.CREATOR);
                    break;
                case 7:
                    packageInfo = (PackageInfo) zzb.zza(parcel, zzaT, PackageInfo.CREATOR);
                    break;
                case 8:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 9:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 10:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 11:
                    zzqa = (zzqa) zzb.zza(parcel, zzaT, zzqa.CREATOR);
                    break;
                case 12:
                    bundle2 = zzb.zzs(parcel, zzaT);
                    break;
                case 13:
                    i2 = zzb.zzg(parcel, zzaT);
                    break;
                case 14:
                    arrayList = zzb.zzE(parcel, zzaT);
                    break;
                case 15:
                    bundle3 = zzb.zzs(parcel, zzaT);
                    break;
                case 16:
                    z = zzb.zzc(parcel, zzaT);
                    break;
                case 17:
                    messenger = (Messenger) zzb.zza(parcel, zzaT, Messenger.CREATOR);
                    break;
                case 18:
                    i3 = zzb.zzg(parcel, zzaT);
                    break;
                case 19:
                    i4 = zzb.zzg(parcel, zzaT);
                    break;
                case 20:
                    f = zzb.zzl(parcel, zzaT);
                    break;
                case 21:
                    str5 = zzb.zzq(parcel, zzaT);
                    break;
                case 25:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                case 26:
                    str6 = zzb.zzq(parcel, zzaT);
                    break;
                case 27:
                    arrayList2 = zzb.zzE(parcel, zzaT);
                    break;
                case 28:
                    str7 = zzb.zzq(parcel, zzaT);
                    break;
                case 29:
                    zzgw = (zzgw) zzb.zza(parcel, zzaT, zzgw.CREATOR);
                    break;
                case 30:
                    arrayList3 = zzb.zzE(parcel, zzaT);
                    break;
                case 31:
                    j2 = zzb.zzi(parcel, zzaT);
                    break;
                case 32:
                    zzmo = (zzmo) zzb.zza(parcel, zzaT, zzmo.CREATOR);
                    break;
                case 33:
                    str8 = zzb.zzq(parcel, zzaT);
                    break;
                case 34:
                    f2 = zzb.zzl(parcel, zzaT);
                    break;
                case 35:
                    i5 = zzb.zzg(parcel, zzaT);
                    break;
                case 36:
                    i6 = zzb.zzg(parcel, zzaT);
                    break;
                case 37:
                    z3 = zzb.zzc(parcel, zzaT);
                    break;
                case 38:
                    z4 = zzb.zzc(parcel, zzaT);
                    break;
                case 39:
                    str9 = zzb.zzq(parcel, zzaT);
                    break;
                case 40:
                    z2 = zzb.zzc(parcel, zzaT);
                    break;
                case 41:
                    str10 = zzb.zzq(parcel, zzaT);
                    break;
                case 42:
                    z5 = zzb.zzc(parcel, zzaT);
                    break;
                case 43:
                    i7 = zzb.zzg(parcel, zzaT);
                    break;
                case 44:
                    bundle4 = zzb.zzs(parcel, zzaT);
                    break;
                case 45:
                    str11 = zzb.zzq(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzmh(i, bundle, zzdy, zzec, str, applicationInfo, packageInfo, str2, str3, str4, zzqa, bundle2, i2, arrayList, bundle3, z, messenger, i3, i4, f, str5, j, str6, arrayList2, str7, zzgw, arrayList3, j2, zzmo, str8, f2, z2, i5, i6, z3, z4, str9, str10, z5, i7, bundle4, str11);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }
}
