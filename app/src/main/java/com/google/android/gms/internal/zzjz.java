package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzjt;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzmb
public final class zzjz extends zzjt.zza {
    private final MediationAdapter zzKU;
    private zzka zzKV;

    public zzjz(MediationAdapter mediationAdapter) {
        this.zzKU = mediationAdapter;
    }

    private Bundle zza(String str, int i, String str2) {
        String valueOf = String.valueOf(str);
        zzpy.zzbe(valueOf.length() != 0 ? "Server parameters: ".concat(valueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.zzKU instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                bundle.putInt("tagForChildDirectedTreatment", i);
            }
            return bundle;
        } catch (Throwable th) {
            zzpy.zzc("Could not get Server Parameters Bundle.", th);
            throw new RemoteException();
        }
    }

    public void destroy() {
        try {
            this.zzKU.onDestroy();
        } catch (Throwable th) {
            zzpy.zzc("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        if (this.zzKU instanceof zzrd) {
            return ((zzrd) this.zzKU).getInterstitialAdapterInfo();
        }
        String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
        zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a v2 MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a v2 MediationInterstitialAdapter: "));
        return new Bundle();
    }

    public zzd getView() {
        if (!(this.zzKU instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return zze.zzA(((MediationBannerAdapter) this.zzKU).getBannerView());
        } catch (Throwable th) {
            zzpy.zzc("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    public boolean isInitialized() {
        if (!(this.zzKU instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Check if adapter is initialized.");
        try {
            return ((MediationRewardedVideoAdAdapter) this.zzKU).isInitialized();
        } catch (Throwable th) {
            zzpy.zzc("Could not check if adapter is initialized.", th);
            throw new RemoteException();
        }
    }

    public void pause() {
        try {
            this.zzKU.onPause();
        } catch (Throwable th) {
            zzpy.zzc("Could not pause adapter.", th);
            throw new RemoteException();
        }
    }

    public void resume() {
        try {
            this.zzKU.onResume();
        } catch (Throwable th) {
            zzpy.zzc("Could not resume adapter.", th);
            throw new RemoteException();
        }
    }

    public void showInterstitial() {
        if (!(this.zzKU instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzKU).showInterstitial();
        } catch (Throwable th) {
            zzpy.zzc("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }

    public void showVideo() {
        if (!(this.zzKU instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Show rewarded video ad from adapter.");
        try {
            ((MediationRewardedVideoAdAdapter) this.zzKU).showVideo();
        } catch (Throwable th) {
            zzpy.zzc("Could not show rewarded video ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(zzd zzd, zzdy zzdy, String str, zzju zzju) {
        zza(zzd, zzdy, str, (String) null, zzju);
    }

    public void zza(zzd zzd, zzdy zzdy, String str, zzoi zzoi, String str2) {
        if (!(this.zzKU instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Initialize rewarded video adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzKU;
            mediationRewardedVideoAdAdapter.initialize((Context) zze.zzE(zzd), new zzjy(zzdy.zzyF == -1 ? null : new Date(zzdy.zzyF), zzdy.zzyG, zzdy.zzyH != null ? new HashSet(zzdy.zzyH) : null, zzdy.zzyN, zzdy.zzyI, zzdy.zzyJ, zzdy.zzyU), str, new zzoj(zzoi), zza(str2, zzdy.zzyJ, (String) null), zzdy.zzyP != null ? zzdy.zzyP.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzpy.zzc("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(zzd zzd, zzdy zzdy, String str, String str2, zzju zzju) {
        if (!(this.zzKU instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzKU;
            mediationInterstitialAdapter.requestInterstitialAd((Context) zze.zzE(zzd), new zzka(zzju), zza(str, zzdy.zzyJ, str2), new zzjy(zzdy.zzyF == -1 ? null : new Date(zzdy.zzyF), zzdy.zzyG, zzdy.zzyH != null ? new HashSet(zzdy.zzyH) : null, zzdy.zzyN, zzdy.zzyI, zzdy.zzyJ, zzdy.zzyU), zzdy.zzyP != null ? zzdy.zzyP.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzpy.zzc("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(zzd zzd, zzdy zzdy, String str, String str2, zzju zzju, zzgw zzgw, List<String> list) {
        if (!(this.zzKU instanceof MediationNativeAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationNativeAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationNativeAdapter: "));
            throw new RemoteException();
        }
        try {
            MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) this.zzKU;
            zzkd zzkd = new zzkd(zzdy.zzyF == -1 ? null : new Date(zzdy.zzyF), zzdy.zzyG, zzdy.zzyH != null ? new HashSet(zzdy.zzyH) : null, zzdy.zzyN, zzdy.zzyI, zzdy.zzyJ, zzgw, list, zzdy.zzyU);
            Bundle bundle = zzdy.zzyP != null ? zzdy.zzyP.getBundle(mediationNativeAdapter.getClass().getName()) : null;
            this.zzKV = new zzka(zzju);
            mediationNativeAdapter.requestNativeAd((Context) zze.zzE(zzd), this.zzKV, zza(str, zzdy.zzyJ, str2), zzkd, bundle);
        } catch (Throwable th) {
            zzpy.zzc("Could not request native ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(zzd zzd, zzec zzec, zzdy zzdy, String str, zzju zzju) {
        zza(zzd, zzec, zzdy, str, null, zzju);
    }

    public void zza(zzd zzd, zzec zzec, zzdy zzdy, String str, String str2, zzju zzju) {
        if (!(this.zzKU instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzKU;
            mediationBannerAdapter.requestBannerAd((Context) zze.zzE(zzd), new zzka(zzju), zza(str, zzdy.zzyJ, str2), zza.zza(zzec.width, zzec.height, zzec.zzzk), new zzjy(zzdy.zzyF == -1 ? null : new Date(zzdy.zzyF), zzdy.zzyG, zzdy.zzyH != null ? new HashSet(zzdy.zzyH) : null, zzdy.zzyN, zzdy.zzyI, zzdy.zzyJ, zzdy.zzyU), zzdy.zzyP != null ? zzdy.zzyP.getBundle(mediationBannerAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzpy.zzc("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(zzdy zzdy, String str, String str2) {
        if (!(this.zzKU instanceof MediationRewardedVideoAdAdapter)) {
            String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Requesting rewarded video ad from adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzKU;
            mediationRewardedVideoAdAdapter.loadAd(new zzjy(zzdy.zzyF == -1 ? null : new Date(zzdy.zzyF), zzdy.zzyG, zzdy.zzyH != null ? new HashSet(zzdy.zzyH) : null, zzdy.zzyN, zzdy.zzyI, zzdy.zzyJ, zzdy.zzyU), zza(str, zzdy.zzyJ, str2), zzdy.zzyP != null ? zzdy.zzyP.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzpy.zzc("Could not load rewarded video ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zzc(zzdy zzdy, String str) {
        zza(zzdy, str, (String) null);
    }

    public zzjw zzgJ() {
        NativeAdMapper zzgN = this.zzKV.zzgN();
        if (zzgN instanceof NativeAppInstallAdMapper) {
            return new zzkb((NativeAppInstallAdMapper) zzgN);
        }
        return null;
    }

    public zzjx zzgK() {
        NativeAdMapper zzgN = this.zzKV.zzgN();
        if (zzgN instanceof NativeContentAdMapper) {
            return new zzkc((NativeContentAdMapper) zzgN);
        }
        return null;
    }

    public Bundle zzgL() {
        if (this.zzKU instanceof zzrc) {
            return ((zzrc) this.zzKU).zzgL();
        }
        String valueOf = String.valueOf(this.zzKU.getClass().getCanonicalName());
        zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a v2 MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a v2 MediationBannerAdapter: "));
        return new Bundle();
    }

    public Bundle zzgM() {
        return new Bundle();
    }

    public void zzj(zzd zzd) {
        try {
            ((OnContextChangedListener) this.zzKU).onContextChanged((Context) zze.zzE(zzd));
        } catch (Throwable th) {
            zzpy.zza("Could not inform adapter of changed context", th);
        }
    }
}
