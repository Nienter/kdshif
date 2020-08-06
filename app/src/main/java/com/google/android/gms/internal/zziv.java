package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zziu;

@zzmb
public class zziv extends zzep.zza {
    private final zzip zzIB;
    @Nullable
    private zzl zzIG;
    private final zzir zzIN;
    @Nullable
    private zzld zzIO;
    private String zzIP;
    private final String zztq;

    public zziv(Context context, String str, zzjs zzjs, zzqa zzqa, zzd zzd) {
        this(str, new zzip(context, zzjs, zzqa, zzd));
    }

    zziv(String str, zzip zzip) {
        this.zztq = str;
        this.zzIB = zzip;
        this.zzIN = new zzir();
        zzv.zzcY().zza(zzip);
    }

    private void zzgr() {
        if (this.zzIG != null && this.zzIO != null) {
            this.zzIG.zza(this.zzIO, this.zzIP);
        }
    }

    static boolean zzq(zzdy zzdy) {
        Bundle zzk = zzis.zzk(zzdy);
        return zzk != null && zzk.containsKey("gw");
    }

    static boolean zzr(zzdy zzdy) {
        Bundle zzk = zzis.zzk(zzdy);
        return zzk != null && zzk.containsKey("_ad");
    }

    /* access modifiers changed from: package-private */
    public void abort() {
        if (this.zzIG == null) {
            this.zzIG = this.zzIB.zzag(this.zztq);
            this.zzIN.zzc(this.zzIG);
            zzgr();
        }
    }

    public void destroy() {
        if (this.zzIG != null) {
            this.zzIG.destroy();
        }
    }

    @Nullable
    public String getMediationAdapterClassName() {
        if (this.zzIG != null) {
            return this.zzIG.getMediationAdapterClassName();
        }
        return null;
    }

    public boolean isLoading() {
        return this.zzIG != null && this.zzIG.isLoading();
    }

    public boolean isReady() {
        return this.zzIG != null && this.zzIG.isReady();
    }

    public void pause() {
        if (this.zzIG != null) {
            this.zzIG.pause();
        }
    }

    public void resume() {
        if (this.zzIG != null) {
            this.zzIG.resume();
        }
    }

    public void setManualImpressionsEnabled(boolean z) {
        abort();
        if (this.zzIG != null) {
            this.zzIG.setManualImpressionsEnabled(z);
        }
    }

    public void setUserId(String str) {
    }

    public void showInterstitial() {
        if (this.zzIG != null) {
            this.zzIG.showInterstitial();
        } else {
            zzpe.zzbe("Interstitial ad must be loaded before showInterstitial().");
        }
    }

    public void stopLoading() {
        if (this.zzIG != null) {
            this.zzIG.stopLoading();
        }
    }

    public void zza(zzec zzec) {
        if (this.zzIG != null) {
            this.zzIG.zza(zzec);
        }
    }

    public void zza(zzek zzek) {
        this.zzIN.zzIw = zzek;
        if (this.zzIG != null) {
            this.zzIN.zzc(this.zzIG);
        }
    }

    public void zza(zzel zzel) {
        this.zzIN.zzti = zzel;
        if (this.zzIG != null) {
            this.zzIN.zzc(this.zzIG);
        }
    }

    public void zza(zzer zzer) {
        this.zzIN.zzIt = zzer;
        if (this.zzIG != null) {
            this.zzIN.zzc(this.zzIG);
        }
    }

    public void zza(zzet zzet) {
        abort();
        if (this.zzIG != null) {
            this.zzIG.zza(zzet);
        }
    }

    public void zza(zzfn zzfn) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public void zza(zzgj zzgj) {
        this.zzIN.zzIv = zzgj;
        if (this.zzIG != null) {
            this.zzIN.zzc(this.zzIG);
        }
    }

    public void zza(zzkz zzkz) {
        this.zzIN.zzIu = zzkz;
        if (this.zzIG != null) {
            this.zzIN.zzc(this.zzIG);
        }
    }

    public void zza(zzld zzld, String str) {
        this.zzIO = zzld;
        this.zzIP = str;
        zzgr();
    }

    public void zza(zznt zznt) {
        this.zzIN.zzIx = zznt;
        if (this.zzIG != null) {
            this.zzIN.zzc(this.zzIG);
        }
    }

    public boolean zzb(zzdy zzdy) {
        if (zzfx.zzCy.get().booleanValue()) {
            zzdy.zzj(zzdy);
        }
        if (!zzq(zzdy)) {
            abort();
        }
        if (zzis.zzm(zzdy)) {
            abort();
        }
        if (zzdy.zzyM != null) {
            abort();
        }
        if (this.zzIG != null) {
            return this.zzIG.zzb(zzdy);
        }
        zzis zzcY = zzv.zzcY();
        if (zzr(zzdy)) {
            zzcY.zzb(zzdy, this.zztq);
        }
        zziu.zza zza = zzcY.zza(zzdy, this.zztq);
        if (zza != null) {
            if (!zza.zzIK) {
                zza.zzgq();
            }
            this.zzIG = zza.zzIG;
            zza.zzII.zza(this.zzIN);
            this.zzIN.zzc(this.zzIG);
            zzgr();
            return zza.zzIL;
        }
        abort();
        return this.zzIG.zzb(zzdy);
    }

    @Nullable
    public com.google.android.gms.dynamic.zzd zzbC() {
        if (this.zzIG != null) {
            return this.zzIG.zzbC();
        }
        return null;
    }

    @Nullable
    public zzec zzbD() {
        if (this.zzIG != null) {
            return this.zzIG.zzbD();
        }
        return null;
    }

    public void zzbF() {
        if (this.zzIG != null) {
            this.zzIG.zzbF();
        } else {
            zzpe.zzbe("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public zzew zzbG() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
}
