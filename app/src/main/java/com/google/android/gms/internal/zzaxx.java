package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzaxx implements Parcelable.Creator<zzaxw> {
    static void zza(zzaxw zzaxw, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzaxw.mVersionCode);
        zzc.zza(parcel, 2, (Parcelable) zzaxw.getAccount(), i, false);
        zzc.zza(parcel, 3, (T[]) zzaxw.zzOm(), i, false);
        zzc.zza(parcel, 4, zzaxw.zzqN(), false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zziQ */
    public zzaxw createFromParcel(Parcel parcel) {
        String zzq;
        Scope[] scopeArr;
        Account account;
        int i;
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        int i2 = 0;
        Scope[] scopeArr2 = null;
        Account account2 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    String str2 = str;
                    scopeArr = scopeArr2;
                    account = account2;
                    i = zzb.zzg(parcel, zzaT);
                    zzq = str2;
                    break;
                case 2:
                    i = i2;
                    Scope[] scopeArr3 = scopeArr2;
                    account = (Account) zzb.zza(parcel, zzaT, Account.CREATOR);
                    zzq = str;
                    scopeArr = scopeArr3;
                    break;
                case 3:
                    account = account2;
                    i = i2;
                    String str3 = str;
                    scopeArr = (Scope[]) zzb.zzb(parcel, zzaT, Scope.CREATOR);
                    zzq = str3;
                    break;
                case 4:
                    zzq = zzb.zzq(parcel, zzaT);
                    scopeArr = scopeArr2;
                    account = account2;
                    i = i2;
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    zzq = str;
                    scopeArr = scopeArr2;
                    account = account2;
                    i = i2;
                    break;
            }
            i2 = i;
            account2 = account;
            scopeArr2 = scopeArr;
            str = zzq;
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzaxw(i2, account2, scopeArr2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzmL */
    public zzaxw[] newArray(int i) {
        return new zzaxw[i];
    }
}
