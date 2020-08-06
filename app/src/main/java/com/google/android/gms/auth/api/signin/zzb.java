package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzb implements Parcelable.Creator<GoogleSignInOptions> {
    static void zza(GoogleSignInOptions googleSignInOptions, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, googleSignInOptions.versionCode);
        zzc.zzc(parcel, 2, googleSignInOptions.zzqJ(), false);
        zzc.zza(parcel, 3, (Parcelable) googleSignInOptions.getAccount(), i, false);
        zzc.zza(parcel, 4, googleSignInOptions.zzqK());
        zzc.zza(parcel, 5, googleSignInOptions.zzqL());
        zzc.zza(parcel, 6, googleSignInOptions.zzqM());
        zzc.zza(parcel, 7, googleSignInOptions.zzqN(), false);
        zzc.zza(parcel, 8, googleSignInOptions.zzqO(), false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzW */
    public GoogleSignInOptions createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = false;
        int zzaU = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(parcel);
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        Account account = null;
        ArrayList<Scope> arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(zzaT)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    arrayList = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, zzaT, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaT, Account.CREATOR);
                    break;
                case 4:
                    z3 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, zzaT);
                    break;
                case 5:
                    z2 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, zzaT);
                    break;
                case 6:
                    z = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, zzaT);
                    break;
                case 7:
                    str2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                case 8:
                    str = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaT);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new GoogleSignInOptions(i, arrayList, account, z3, z2, z, str2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzbk */
    public GoogleSignInOptions[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }
}
