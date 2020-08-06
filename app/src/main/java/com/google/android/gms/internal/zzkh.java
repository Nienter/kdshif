package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzh;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.internal.zzgl;

@zzmb
public class zzkh implements MediationInterstitialAdapter {
    private Uri mUri;
    /* access modifiers changed from: private */
    public Activity zzLg;
    /* access modifiers changed from: private */
    public zzgl zzLh;
    /* access modifiers changed from: private */
    public MediationInterstitialListener zzLi;

    public static boolean zzo(Context context) {
        return zzgl.zzn(context);
    }

    public void onDestroy() {
        zzpy.zzbc("Destroying AdMobCustomTabsAdapter adapter.");
        try {
            this.zzLh.zzd(this.zzLg);
        } catch (Exception e) {
            zzpy.zzb("Exception while unbinding from CustomTabsService.", e);
        }
    }

    public void onPause() {
        zzpy.zzbc("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public void onResume() {
        zzpy.zzbc("Resuming AdMobCustomTabsAdapter adapter.");
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzLi = mediationInterstitialListener;
        if (this.zzLi == null) {
            zzpy.zzbe("Listener not set for mediation. Returning.");
        } else if (!(context instanceof Activity)) {
            zzpy.zzbe("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzLi.onAdFailedToLoad(this, 0);
        } else if (!zzo(context)) {
            zzpy.zzbe("Default browser does not support custom tabs. Bailing out.");
            this.zzLi.onAdFailedToLoad(this, 0);
        } else {
            String string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                zzpy.zzbe("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.zzLi.onAdFailedToLoad(this, 0);
                return;
            }
            this.zzLg = (Activity) context;
            this.mUri = Uri.parse(string);
            this.zzLh = new zzgl();
            this.zzLh.zza((zzgl.zza) new zzgl.zza(this) {
                public void zzfE() {
                    zzpy.zzbc("Hinting CustomTabsService for the load of the new url.");
                }

                public void zzfF() {
                    zzpy.zzbc("Disconnecting from CustomTabs service.");
                }
            });
            this.zzLh.zze(this.zzLg);
            this.zzLi.onAdLoaded(this);
        }
    }

    public void showInterstitial() {
        CustomTabsIntent build = new CustomTabsIntent.Builder(this.zzLh.zzfC()).build();
        build.intent.setData(this.mUri);
        final AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(new zzc(build.intent), null, new zzh() {
            public void onPause() {
                zzpy.zzbc("AdMobCustomTabsAdapter overlay is paused.");
            }

            public void onResume() {
                zzpy.zzbc("AdMobCustomTabsAdapter overlay is resumed.");
            }

            public void zzbN() {
                zzpy.zzbc("AdMobCustomTabsAdapter overlay is closed.");
                zzkh.this.zzLi.onAdClosed(zzkh.this);
                zzkh.this.zzLh.zzd(zzkh.this.zzLg);
            }

            public void zzbO() {
                zzpy.zzbc("Opening AdMobCustomTabsAdapter overlay.");
                zzkh.this.zzLi.onAdOpened(zzkh.this);
            }
        }, null, new zzqa(0, 0, false));
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                zzv.zzcH().zza(zzkh.this.zzLg, adOverlayInfoParcel);
            }
        });
        zzv.zzcN().zzG(false);
    }
}
