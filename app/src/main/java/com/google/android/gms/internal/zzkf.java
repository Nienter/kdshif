package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zzmb
public final class zzkf<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    /* access modifiers changed from: private */
    public final zzju zzKW;

    public zzkf(zzju zzju) {
        this.zzKW = zzju;
    }

    public void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzpy.zzbc("Adapter called onClick.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onClick must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdClicked();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdClicked.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdClicked();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdClicked.", e);
        }
    }

    public void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzpy.zzbc("Adapter called onDismissScreen.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onDismissScreen must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdClosed();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdClosed.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdClosed();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzpy.zzbc("Adapter called onDismissScreen.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onDismissScreen must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdClosed();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdClosed.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdClosed();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, final AdRequest.ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzpy.zzbc(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error. ").append(valueOf).toString());
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onFailedToReceiveAd must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdFailedToLoad(zzkg.zza(errorCode));
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdFailedToLoad(zzkg.zza(errorCode));
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final AdRequest.ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzpy.zzbc(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error ").append(valueOf).append(".").toString());
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onFailedToReceiveAd must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdFailedToLoad(zzkg.zza(errorCode));
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdFailedToLoad(zzkg.zza(errorCode));
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzpy.zzbc("Adapter called onLeaveApplication.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onLeaveApplication must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdLeftApplication();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdLeftApplication.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdLeftApplication();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzpy.zzbc("Adapter called onLeaveApplication.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onLeaveApplication must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdLeftApplication();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdLeftApplication.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdLeftApplication();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzpy.zzbc("Adapter called onPresentScreen.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onPresentScreen must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdOpened();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdOpened.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdOpened();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzpy.zzbc("Adapter called onPresentScreen.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onPresentScreen must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdOpened();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdOpened.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdOpened();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzpy.zzbc("Adapter called onReceivedAd.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onReceivedAd must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdLoaded();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdLoaded.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdLoaded();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzpy.zzbc("Adapter called onReceivedAd.");
        if (!zzeh.zzeO().zzkJ()) {
            zzpy.zzbe("onReceivedAd must be called on the main UI thread.");
            zzpx.zzXU.post(new Runnable() {
                public void run() {
                    try {
                        zzkf.this.zzKW.onAdLoaded();
                    } catch (RemoteException e) {
                        zzpy.zzc("Could not call onAdLoaded.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzKW.onAdLoaded();
        } catch (RemoteException e) {
            zzpy.zzc("Could not call onAdLoaded.", e);
        }
    }
}
