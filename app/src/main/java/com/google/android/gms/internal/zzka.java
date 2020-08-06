package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.common.internal.zzac;

@zzmb
public final class zzka implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
    private final zzju zzKW;
    private NativeAdMapper zzKX;

    public zzka(zzju zzju) {
        this.zzKW = zzju;
    }

    public void onAdClicked(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdn("onAdClicked must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdClicked.");
        try {
            this.zzKW.onAdClicked();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdClicked.", e);
        }
    }

    public void onAdClicked(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdn("onAdClicked must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdClicked.");
        try {
            this.zzKW.onAdClicked();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdClicked.", e);
        }
    }

    public void onAdClicked(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdn("onAdClicked must be called on the main UI thread.");
        NativeAdMapper zzgN = zzgN();
        if (zzgN == null) {
            zzpy.zzbe("Could not call onAdClicked since NativeAdMapper is null.");
        } else if (!zzgN.getOverrideClickHandling()) {
            zzpy.zzbc("Could not call onAdClicked since setOverrideClickHandling is not set to true");
        } else {
            zzpy.zzbc("Adapter called onAdClicked.");
            try {
                this.zzKW.onAdClicked();
            } catch (RemoteException e) {
                zzpy.zzc("Could not call onAdClicked.", e);
            }
        }
    }

    public void onAdClosed(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdn("onAdClosed must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdClosed.");
        try {
            this.zzKW.onAdClosed();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onAdClosed(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdn("onAdClosed must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdClosed.");
        try {
            this.zzKW.onAdClosed();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onAdClosed(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdn("onAdClosed must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdClosed.");
        try {
            this.zzKW.onAdClosed();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onAdFailedToLoad(MediationBannerAdapter mediationBannerAdapter, int i) {
        zzac.zzdn("onAdFailedToLoad must be called on the main UI thread.");
        zzpy.zzbc(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error. ").append(i).toString());
        try {
            this.zzKW.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdFailedToLoad(MediationInterstitialAdapter mediationInterstitialAdapter, int i) {
        zzac.zzdn("onAdFailedToLoad must be called on the main UI thread.");
        zzpy.zzbc(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error ").append(i).append(".").toString());
        try {
            this.zzKW.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdFailedToLoad(MediationNativeAdapter mediationNativeAdapter, int i) {
        zzac.zzdn("onAdFailedToLoad must be called on the main UI thread.");
        zzpy.zzbc(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error ").append(i).append(".").toString());
        try {
            this.zzKW.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdImpression(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdn("onAdImpression must be called on the main UI thread.");
        NativeAdMapper zzgN = zzgN();
        if (zzgN == null) {
            zzpy.zzbe("Could not call onAdImpression since NativeAdMapper is null. ");
        } else if (!zzgN.getOverrideImpressionRecording()) {
            zzpy.zzbc("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
        } else {
            zzpy.zzbc("Adapter called onAdImpression.");
            try {
                this.zzKW.onAdImpression();
            } catch (RemoteException e) {
                zzpy.zzc("Could not call onAdImpression.", e);
            }
        }
    }

    public void onAdLeftApplication(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdn("onAdLeftApplication must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdLeftApplication.");
        try {
            this.zzKW.onAdLeftApplication();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLeftApplication(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdn("onAdLeftApplication must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdLeftApplication.");
        try {
            this.zzKW.onAdLeftApplication();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLeftApplication(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdn("onAdLeftApplication must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdLeftApplication.");
        try {
            this.zzKW.onAdLeftApplication();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLoaded(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdn("onAdLoaded must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdLoaded.");
        try {
            this.zzKW.onAdLoaded();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onAdLoaded(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdn("onAdLoaded must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdLoaded.");
        try {
            this.zzKW.onAdLoaded();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, NativeAdMapper nativeAdMapper) {
        zzac.zzdn("onAdLoaded must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdLoaded.");
        this.zzKX = nativeAdMapper;
        try {
            this.zzKW.onAdLoaded();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onAdOpened(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdn("onAdOpened must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdOpened.");
        try {
            this.zzKW.onAdOpened();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onAdOpened(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdn("onAdOpened must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdOpened.");
        try {
            this.zzKW.onAdOpened();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onAdOpened(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdn("onAdOpened must be called on the main UI thread.");
        zzpy.zzbc("Adapter called onAdOpened.");
        try {
            this.zzKW.onAdOpened();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdOpened.", e);
        }
    }

    public NativeAdMapper zzgN() {
        return this.zzKX;
    }
}
