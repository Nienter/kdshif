package com.google.android.gms.internal;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.zza;
import java.util.Date;
import java.util.HashSet;

@zzmb
public final class zzkg {

    /* renamed from: com.google.android.gms.internal.zzkg$1 */
    static /* synthetic */ class C13071 {
        static final /* synthetic */ int[] zzLe = new int[AdRequest.Gender.values().length];

        static {
            zzLf = new int[AdRequest.ErrorCode.values().length];
            try {
                zzLf[AdRequest.ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                zzLf[AdRequest.ErrorCode.INVALID_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                zzLf[AdRequest.ErrorCode.NETWORK_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                zzLf[AdRequest.ErrorCode.NO_FILL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                zzLe[AdRequest.Gender.FEMALE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                zzLe[AdRequest.Gender.MALE.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                zzLe[AdRequest.Gender.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static AdRequest.Gender zzG(int i) {
        switch (i) {
            case 1:
                return AdRequest.Gender.MALE;
            case 2:
                return AdRequest.Gender.FEMALE;
            default:
                return AdRequest.Gender.UNKNOWN;
        }
    }

    public static int zza(AdRequest.ErrorCode errorCode) {
        switch (errorCode) {
            case INVALID_REQUEST:
                return 1;
            case NETWORK_ERROR:
                return 2;
            case NO_FILL:
                return 3;
            default:
                return 0;
        }
    }

    public static AdSize zzc(zzec zzec) {
        AdSize[] adSizeArr = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
        for (int i = 0; i < 6; i++) {
            if (adSizeArr[i].getWidth() == zzec.width && adSizeArr[i].getHeight() == zzec.height) {
                return adSizeArr[i];
            }
        }
        return new AdSize(zza.zza(zzec.width, zzec.height, zzec.zzzk));
    }

    public static MediationAdRequest zzs(zzdy zzdy) {
        return new MediationAdRequest(new Date(zzdy.zzyF), zzG(zzdy.zzyG), zzdy.zzyH != null ? new HashSet(zzdy.zzyH) : null, zzdy.zzyI, zzdy.zzyN);
    }
}
