package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzes;
import com.google.android.gms.internal.zzeu;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzgy;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzno;
import com.google.android.gms.internal.zznr;
import com.google.android.gms.internal.zzqa;

@Keep
@DynamiteApi
@zzmb
public class ClientApi extends zzes.zza {
    public zzen createAdLoaderBuilder(zzd zzd, String str, zzjs zzjs, int i) {
        return new zzk((Context) zze.zzE(zzd), str, zzjs, new zzqa(10084000, i, true), zzd.zzca());
    }

    public zzkr createAdOverlay(zzd zzd) {
        return new com.google.android.gms.ads.internal.overlay.zze((Activity) zze.zzE(zzd));
    }

    public zzep createBannerAdManager(zzd zzd, zzec zzec, String str, zzjs zzjs, int i) {
        return new zzf((Context) zze.zzE(zzd), zzec, str, zzjs, new zzqa(10084000, i, true), zzd.zzca());
    }

    public zzla createInAppPurchaseManager(zzd zzd) {
        return new com.google.android.gms.ads.internal.purchase.zze((Activity) zze.zzE(zzd));
    }

    public zzep createInterstitialAdManager(zzd zzd, zzec zzec, String str, zzjs zzjs, int i) {
        Context context = (Context) zze.zzE(zzd);
        zzfx.initialize(context);
        zzqa zzqa = new zzqa(10084000, i, true);
        boolean equals = "reward_mb".equals(zzec.zzzk);
        if ((!equals && zzfx.zzCA.get().booleanValue()) || (equals && zzfx.zzCB.get().booleanValue())) {
            return new zziv(context, str, zzjs, zzqa, zzd.zzca());
        }
        return new zzl(context, zzec, str, zzjs, zzqa, zzd.zzca());
    }

    public zzhb createNativeAdViewDelegate(zzd zzd, zzd zzd2) {
        return new zzgy((FrameLayout) zze.zzE(zzd), (FrameLayout) zze.zzE(zzd2));
    }

    public zznr createRewardedVideoAd(zzd zzd, zzjs zzjs, int i) {
        return new zzno((Context) zze.zzE(zzd), zzd.zzca(), zzjs, new zzqa(10084000, i, true));
    }

    public zzep createSearchAdManager(zzd zzd, zzec zzec, String str, int i) {
        return new zzu((Context) zze.zzE(zzd), zzec, str, new zzqa(10084000, i, true));
    }

    @Nullable
    public zzeu getMobileAdsSettingsManager(zzd zzd) {
        return null;
    }

    public zzeu getMobileAdsSettingsManagerWithClientJarVersion(zzd zzd, int i) {
        return zzp.zza((Context) zze.zzE(zzd), new zzqa(10084000, i, true));
    }
}
