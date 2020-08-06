package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzae implements Parcelable.Creator<zzad> {
    static void zza(zzad zzad, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzad.mVersionCode);
        zzc.zza(parcel, 2, (Parcelable) zzad.getAccount(), i, false);
        zzc.zzc(parcel, 3, zzad.getSessionId());
        zzc.zza(parcel, 4, (Parcelable) zzad.zzxy(), i, false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzaP */
    public zzad createFromParcel(Parcel parcel) {
        GoogleSignInAccount googleSignInAccount;
        int i;
        Account account;
        int i2;
        GoogleSignInAccount googleSignInAccount2 = null;
        int i3 = 0;
        int zzaU = zzb.zzaU(parcel);
        Account account2 = null;
        int i4 = 0;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    GoogleSignInAccount googleSignInAccount3 = googleSignInAccount2;
                    i = i3;
                    account = account2;
                    i2 = zzb.zzg(parcel, zzaT);
                    googleSignInAccount = googleSignInAccount3;
                    break;
                case 2:
                    i2 = i4;
                    int i5 = i3;
                    account = (Account) zzb.zza(parcel, zzaT, Account.CREATOR);
                    googleSignInAccount = googleSignInAccount2;
                    i = i5;
                    break;
                case 3:
                    account = account2;
                    i2 = i4;
                    GoogleSignInAccount googleSignInAccount4 = googleSignInAccount2;
                    i = zzb.zzg(parcel, zzaT);
                    googleSignInAccount = googleSignInAccount4;
                    break;
                case 4:
                    googleSignInAccount = (GoogleSignInAccount) zzb.zza(parcel, zzaT, GoogleSignInAccount.CREATOR);
                    i = i3;
                    account = account2;
                    i2 = i4;
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    googleSignInAccount = googleSignInAccount2;
                    i = i3;
                    account = account2;
                    i2 = i4;
                    break;
            }
            i4 = i2;
            account2 = account;
            i3 = i;
            googleSignInAccount2 = googleSignInAccount;
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzad(i4, account2, i3, googleSignInAccount2);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzcS */
    public zzad[] newArray(int i) {
        return new zzad[i];
    }
}
