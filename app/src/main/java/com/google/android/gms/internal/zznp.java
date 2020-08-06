package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzov;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zznp extends zzb implements zzod {
    private static final zzjr zzUF = new zzjr();
    private final Map<String, zzoh> zzUG = new HashMap();
    private boolean zzUH;

    public zznp(Context context, zzd zzd, zzec zzec, zzjs zzjs, zzqa zzqa) {
        super(context, zzec, null, zzjs, zzqa, zzd);
    }

    private zzov.zza zzd(zzov.zza zza) {
        zzpe.m2431v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            String jSONObject = zzna.zzc(zza.zzVB).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zza.zzSF.zzvd);
            zzjj zzjj = new zzjj(Arrays.asList(new zzji[]{new zzji(jSONObject, null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList())}), -1, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false);
            return new zzov.zza(zza.zzSF, zza.zzVB, zzjj, zza.zzvj, zza.errorCode, zza.zzVv, zza.zzVw, zza.zzVp);
        } catch (JSONException e) {
            zzpe.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            return zze(zza);
        }
    }

    private zzov.zza zze(zzov.zza zza) {
        return new zzov.zza(zza.zzSF, zza.zzVB, null, zza.zzvj, 0, zza.zzVv, zza.zzVw, zza.zzVp);
    }

    public void destroy() {
        zzac.zzdn("destroy must be called on the main UI thread.");
        for (String next : this.zzUG.keySet()) {
            try {
                zzoh zzoh = this.zzUG.get(next);
                if (!(zzoh == null || zzoh.zzjw() == null)) {
                    zzoh.zzjw().destroy();
                }
            } catch (RemoteException e) {
                String valueOf = String.valueOf(next);
                zzpe.zzbe(valueOf.length() != 0 ? "Fail to destroy adapter: ".concat(valueOf) : new String("Fail to destroy adapter: "));
            }
        }
    }

    public boolean isLoaded() {
        zzac.zzdn("isLoaded must be called on the main UI thread.");
        return this.zzsw.zzvh == null && this.zzsw.zzvi == null && this.zzsw.zzvk != null && !this.zzUH;
    }

    public void onContextChanged(@NonNull Context context) {
        for (zzoh zzjw : this.zzUG.values()) {
            try {
                zzjw.zzjw().zzj(zze.zzA(context));
            } catch (RemoteException e) {
                zzpe.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public void onRewardedVideoAdClosed() {
        zzbH();
    }

    public void onRewardedVideoAdLeftApplication() {
        zzbI();
    }

    public void onRewardedVideoAdOpened() {
        zza(this.zzsw.zzvk, false);
        zzbJ();
    }

    public void onRewardedVideoStarted() {
        if (!(this.zzsw.zzvk == null || this.zzsw.zzvk.zzKA == null)) {
            zzv.zzdc().zza(this.zzsw.zzqr, this.zzsw.zzvf.zzaZ, this.zzsw.zzvk, this.zzsw.zzvd, false, this.zzsw.zzvk.zzKA.zzJP);
        }
        zzbL();
    }

    public void pause() {
        zzac.zzdn("pause must be called on the main UI thread.");
        for (String next : this.zzUG.keySet()) {
            try {
                zzoh zzoh = this.zzUG.get(next);
                if (!(zzoh == null || zzoh.zzjw() == null)) {
                    zzoh.zzjw().pause();
                }
            } catch (RemoteException e) {
                String valueOf = String.valueOf(next);
                zzpe.zzbe(valueOf.length() != 0 ? "Fail to pause adapter: ".concat(valueOf) : new String("Fail to pause adapter: "));
            }
        }
    }

    public void resume() {
        zzac.zzdn("resume must be called on the main UI thread.");
        for (String next : this.zzUG.keySet()) {
            try {
                zzoh zzoh = this.zzUG.get(next);
                if (!(zzoh == null || zzoh.zzjw() == null)) {
                    zzoh.zzjw().resume();
                }
            } catch (RemoteException e) {
                String valueOf = String.valueOf(next);
                zzpe.zzbe(valueOf.length() != 0 ? "Fail to resume adapter: ".concat(valueOf) : new String("Fail to resume adapter: "));
            }
        }
    }

    public void zza(zznx zznx) {
        zzac.zzdn("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(zznx.zzvd)) {
            zzpe.zzbe("Invalid ad unit id. Aborting.");
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zznp.this.zzh(1);
                }
            });
            return;
        }
        this.zzUH = false;
        this.zzsw.zzvd = zznx.zzvd;
        super.zzb(zznx.zzRd);
    }

    public void zza(final zzov.zza zza, zzgf zzgf) {
        if (zza.errorCode != -2) {
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zznp.this.zzb(new zzov(zza, null, null, null, null, null, null, null));
                }
            });
            return;
        }
        this.zzsw.zzvl = zza;
        if (zza.zzVr == null) {
            this.zzsw.zzvl = zzd(zza);
        }
        this.zzsw.zzvF = 0;
        this.zzsw.zzvi = zzv.zzcI().zza(this.zzsw.zzqr, this.zzsw.zzvl, this);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzdy zzdy, zzov zzov, boolean z) {
        return false;
    }

    public boolean zza(zzov zzov, zzov zzov2) {
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    @Nullable
    public zzoh zzaM(String str) {
        Exception exc;
        zzoh zzoh;
        zzoh zzoh2 = this.zzUG.get(str);
        if (zzoh2 != null) {
            return zzoh2;
        }
        try {
            zzoh = new zzoh(("com.google.ads.mediation.admob.AdMobAdapter".equals(str) ? zzUF : this.zzsD).zzar(str), this);
            try {
                this.zzUG.put(str, zzoh);
                return zzoh;
            } catch (Exception e) {
                exc = e;
                String valueOf = String.valueOf(str);
                zzpe.zzc(valueOf.length() == 0 ? "Fail to instantiate adapter ".concat(valueOf) : new String("Fail to instantiate adapter "), exc);
                return zzoh;
            }
        } catch (Exception e2) {
            exc = e2;
            zzoh = zzoh2;
            String valueOf2 = String.valueOf(str);
            zzpe.zzc(valueOf2.length() == 0 ? "Fail to instantiate adapter ".concat(valueOf2) : new String("Fail to instantiate adapter "), exc);
            return zzoh;
        }
    }

    public void zzc(@Nullable zzok zzok) {
        if (!(this.zzsw.zzvk == null || this.zzsw.zzvk.zzKA == null)) {
            zzv.zzdc().zza(this.zzsw.zzqr, this.zzsw.zzvf.zzaZ, this.zzsw.zzvk, this.zzsw.zzvd, false, this.zzsw.zzvk.zzKA.zzJQ);
        }
        if (!(this.zzsw.zzvk == null || this.zzsw.zzvk.zzVr == null || TextUtils.isEmpty(this.zzsw.zzvk.zzVr.zzKf))) {
            zzok = new zzok(this.zzsw.zzvk.zzVr.zzKf, this.zzsw.zzvk.zzVr.zzKg);
        }
        zza(zzok);
    }

    public void zzjo() {
        zzac.zzdn("showAd must be called on the main UI thread.");
        if (!isLoaded()) {
            zzpe.zzbe("The reward video has not loaded.");
            return;
        }
        this.zzUH = true;
        zzoh zzaM = zzaM(this.zzsw.zzvk.zzKC);
        if (zzaM != null && zzaM.zzjw() != null) {
            try {
                zzaM.zzjw().showVideo();
            } catch (RemoteException e) {
                zzpe.zzc("Could not call showVideo.", e);
            }
        }
    }

    public void zzjp() {
        onAdClicked();
    }
}
