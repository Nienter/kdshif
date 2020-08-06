package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.internal.zzjs;
import java.util.Map;

@zzmb
public final class zzjr extends zzjs.zza {
    private Map<Class<? extends NetworkExtras>, NetworkExtras> zzKS;

    private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> zzjt zzat(String str) {
        try {
            Class<?> cls = Class.forName(str, false, zzjr.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(cls)) {
                MediationAdapter mediationAdapter = (MediationAdapter) cls.newInstance();
                return new zzke(mediationAdapter, (com.google.ads.mediation.NetworkExtras) this.zzKS.get(mediationAdapter.getAdditionalParametersType()));
            } else if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(cls)) {
                return new zzjz((com.google.android.gms.ads.mediation.MediationAdapter) cls.newInstance());
            } else {
                zzpy.zzbe(new StringBuilder(String.valueOf(str).length() + 64).append("Could not instantiate mediation adapter: ").append(str).append(" (not a valid adapter).").toString());
                throw new RemoteException();
            }
        } catch (Throwable th) {
            return zzau(str);
        }
    }

    private zzjt zzau(String str) {
        try {
            zzpy.zzbc("Reflection failed, retrying using direct instantiation");
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                return new zzjz(new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                return new zzjz(new AdUrlAdapter());
            }
            if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                return new zzjz(new CustomEventAdapter());
            }
            if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
                return new zzke(customEventAdapter, (CustomEventExtras) this.zzKS.get(customEventAdapter.getAdditionalParametersType()));
            }
            throw new RemoteException();
        } catch (Throwable th) {
            zzpy.zzc(new StringBuilder(String.valueOf(str).length() + 43).append("Could not instantiate mediation adapter: ").append(str).append(". ").toString(), th);
        }
    }

    public zzjt zzar(String str) {
        return zzat(str);
    }

    public boolean zzas(String str) {
        boolean z = false;
        try {
            return CustomEvent.class.isAssignableFrom(Class.forName(str, false, zzjr.class.getClassLoader()));
        } catch (Throwable th) {
            zzpy.zzbe(new StringBuilder(String.valueOf(str).length() + 80).append("Could not load custom event implementation class: ").append(str).append(", assuming old implementation.").toString());
            return z;
        }
    }

    public void zzi(Map<Class<? extends NetworkExtras>, NetworkExtras> map) {
        this.zzKS = map;
    }
}
