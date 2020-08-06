package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzjt;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzmb
public final class zzke<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzjt.zza {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzLa;
    private final NETWORK_EXTRAS zzLb;

    public zzke(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zzLa = mediationAdapter;
        this.zzLb = network_extras;
    }

    private SERVER_PARAMETERS zzb(String str, int i, String str2) {
        HashMap hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            } catch (Throwable th) {
                zzpy.zzc("Could not get MediationServerParameters.", th);
                throw new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class<SERVER_PARAMETERS> serverParametersType = this.zzLa.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        SERVER_PARAMETERS server_parameters = (MediationServerParameters) serverParametersType.newInstance();
        server_parameters.load(hashMap);
        return server_parameters;
    }

    public void destroy() {
        try {
            this.zzLa.destroy();
        } catch (Throwable th) {
            zzpy.zzc("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public zzd getView() {
        if (!(this.zzLa instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(this.zzLa.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return zze.zzA(((MediationBannerAdapter) this.zzLa).getBannerView());
        } catch (Throwable th) {
            zzpy.zzc("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    public boolean isInitialized() {
        return true;
    }

    public void pause() {
        throw new RemoteException();
    }

    public void resume() {
        throw new RemoteException();
    }

    public void showInterstitial() {
        if (!(this.zzLa instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(this.zzLa.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzLa).showInterstitial();
        } catch (Throwable th) {
            zzpy.zzc("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }

    public void showVideo() {
    }

    public void zza(zzd zzd, zzdy zzdy, String str, zzju zzju) {
        zza(zzd, zzdy, str, (String) null, zzju);
    }

    public void zza(zzd zzd, zzdy zzdy, String str, zzoi zzoi, String str2) {
    }

    public void zza(zzd zzd, zzdy zzdy, String str, String str2, zzju zzju) {
        if (!(this.zzLa instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(this.zzLa.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationInterstitialAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzLa).requestInterstitialAd(new zzkf(zzju), (Activity) zze.zzE(zzd), zzb(str, zzdy.zzyJ, str2), zzkg.zzs(zzdy), this.zzLb);
        } catch (Throwable th) {
            zzpy.zzc("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(zzd zzd, zzdy zzdy, String str, String str2, zzju zzju, zzgw zzgw, List<String> list) {
    }

    public void zza(zzd zzd, zzec zzec, zzdy zzdy, String str, zzju zzju) {
        zza(zzd, zzec, zzdy, str, null, zzju);
    }

    public void zza(zzd zzd, zzec zzec, zzdy zzdy, String str, String str2, zzju zzju) {
        if (!(this.zzLa instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(this.zzLa.getClass().getCanonicalName());
            zzpy.zzbe(valueOf.length() != 0 ? "MediationAdapter is not a MediationBannerAdapter: ".concat(valueOf) : new String("MediationAdapter is not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzpy.zzbc("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter) this.zzLa).requestBannerAd(new zzkf(zzju), (Activity) zze.zzE(zzd), zzb(str, zzdy.zzyJ, str2), zzkg.zzc(zzec), zzkg.zzs(zzdy), this.zzLb);
        } catch (Throwable th) {
            zzpy.zzc("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void zza(zzdy zzdy, String str, String str2) {
    }

    public void zzc(zzdy zzdy, String str) {
    }

    public zzjw zzgJ() {
        return null;
    }

    public zzjx zzgK() {
        return null;
    }

    public Bundle zzgL() {
        return new Bundle();
    }

    public Bundle zzgM() {
        return new Bundle();
    }

    public void zzj(zzd zzd) {
    }
}
