package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zza implements Parcelable.Creator<GoogleSignInAccount> {
    static void zza(GoogleSignInAccount googleSignInAccount, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, googleSignInAccount.versionCode);
        zzc.zza(parcel, 2, googleSignInAccount.getId(), false);
        zzc.zza(parcel, 3, googleSignInAccount.getIdToken(), false);
        zzc.zza(parcel, 4, googleSignInAccount.getEmail(), false);
        zzc.zza(parcel, 5, googleSignInAccount.getDisplayName(), false);
        zzc.zza(parcel, 6, (Parcelable) googleSignInAccount.getPhotoUrl(), i, false);
        zzc.zza(parcel, 7, googleSignInAccount.getServerAuthCode(), false);
        zzc.zza(parcel, 8, googleSignInAccount.zzqE());
        zzc.zza(parcel, 9, googleSignInAccount.zzqF(), false);
        zzc.zzc(parcel, 10, googleSignInAccount.zzahM, false);
        zzc.zza(parcel, 11, googleSignInAccount.getGivenName(), false);
        zzc.zza(parcel, 12, googleSignInAccount.getFamilyName(), false);
        zzc.zzJ(parcel, zzaV);
    }

    /* renamed from: zzV */
    public GoogleSignInAccount createFromParcel(Parcel parcel) {
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        long j = 0;
        String str6 = null;
        ArrayList<Scope> arrayList = null;
        String str7 = null;
        String str8 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    str4 = zzb.zzq(parcel, zzaT);
                    break;
                case 6:
                    uri = (Uri) zzb.zza(parcel, zzaT, Uri.CREATOR);
                    break;
                case 7:
                    str5 = zzb.zzq(parcel, zzaT);
                    break;
                case 8:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                case 9:
                    str6 = zzb.zzq(parcel, zzaT);
                    break;
                case 10:
                    arrayList = zzb.zzc(parcel, zzaT, Scope.CREATOR);
                    break;
                case 11:
                    str7 = zzb.zzq(parcel, zzaT);
                    break;
                case 12:
                    str8 = zzb.zzq(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, arrayList, str7, str8);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaU).toString(), parcel);
    }

    /* renamed from: zzbj */
    public GoogleSignInAccount[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
