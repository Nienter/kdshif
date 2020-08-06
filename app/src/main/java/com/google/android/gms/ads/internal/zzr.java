package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.p001v4.util.SimpleArrayMap;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzdy;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzgf;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzgs;
import com.google.android.gms.internal.zzgt;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzgw;
import com.google.android.gms.internal.zzhj;
import com.google.android.gms.internal.zzhk;
import com.google.android.gms.internal.zzhl;
import com.google.android.gms.internal.zzhm;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzjw;
import com.google.android.gms.internal.zzjx;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import com.google.android.gms.internal.zzqa;
import com.google.android.gms.internal.zzqp;
import java.util.List;

@zzmb
public class zzr extends zzb {
    private zzqp zzua;

    public zzr(Context context, zzd zzd, zzec zzec, String str, zzjs zzjs, zzqa zzqa) {
        super(context, zzec, str, zzjs, zzqa, zzd);
    }

    private static zzgp zza(zzjw zzjw) {
        return new zzgp(zzjw.getHeadline(), zzjw.getImages(), zzjw.getBody(), zzjw.zzfL() != null ? zzjw.zzfL() : null, zzjw.getCallToAction(), zzjw.getStarRating(), zzjw.getStore(), zzjw.getPrice(), null, zzjw.getExtras(), zzjw.zzbG(), null);
    }

    private static zzgq zza(zzjx zzjx) {
        return new zzgq(zzjx.getHeadline(), zzjx.getImages(), zzjx.getBody(), zzjx.zzfQ() != null ? zzjx.zzfQ() : null, zzjx.getCallToAction(), zzjx.getAdvertiser(), null, zzjx.getExtras());
    }

    private void zza(final zzgp zzgp) {
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                try {
                    if (zzr.this.zzsw.zzvt != null) {
                        zzr.this.zzsw.zzvt.zza(zzgp);
                    }
                } catch (RemoteException e) {
                    zzpe.zzc("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", e);
                }
            }
        });
    }

    private void zza(final zzgq zzgq) {
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                try {
                    if (zzr.this.zzsw.zzvu != null) {
                        zzr.this.zzsw.zzvu.zza(zzgq);
                    }
                } catch (RemoteException e) {
                    zzpe.zzc("Could not call OnContentAdLoadedListener.onContentAdLoaded().", e);
                }
            }
        });
    }

    private void zza(final zzov zzov, final String str) {
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                try {
                    zzr.this.zzsw.zzvw.get(str).zza((zzgr) zzov.zzVx);
                } catch (RemoteException e) {
                    zzpe.zzc("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", e);
                }
            }
        });
    }

    public void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public void zza(SimpleArrayMap<String, zzhm> simpleArrayMap) {
        zzac.zzdn("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        this.zzsw.zzvw = simpleArrayMap;
    }

    public void zza(zzgj zzgj) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public void zza(zzgs zzgs) {
        if (this.zzua != null) {
            this.zzua.zzb(zzgs);
        }
    }

    public void zza(zzgu zzgu) {
        if (this.zzsw.zzvk.zzVp != null) {
            zzv.zzcN().zzjZ().zza(this.zzsw.zzvj, this.zzsw.zzvk, zzgu);
        }
    }

    public void zza(zzkz zzkz) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    public void zza(final zzov.zza zza, zzgf zzgf) {
        if (zza.zzvj != null) {
            this.zzsw.zzvj = zza.zzvj;
        }
        if (zza.errorCode != -2) {
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zzr.this.zzb(new zzov(zza, null, null, null, null, null, null, null));
                }
            });
            return;
        }
        this.zzsw.zzvF = 0;
        this.zzsw.zzvi = zzv.zzcI().zza(this.zzsw.zzqr, this, zza, this.zzsw.zzve, null, this.zzsD, this, zzgf);
        String valueOf = String.valueOf(this.zzsw.zzvi.getClass().getName());
        zzpe.zzbc(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzdy zzdy, zzov zzov, boolean z) {
        return this.zzsv.zzcv();
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzov zzov, zzov zzov2) {
        zzb((List<String>) null);
        if (!this.zzsw.zzdm()) {
            throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
        }
        if (zzov2.zzRK) {
            try {
                zzjw zzgJ = zzov2.zzKB != null ? zzov2.zzKB.zzgJ() : null;
                zzjx zzgK = zzov2.zzKB != null ? zzov2.zzKB.zzgK() : null;
                if (zzgJ == null || this.zzsw.zzvt == null) {
                    if (zzgK != null) {
                        if (this.zzsw.zzvu != null) {
                            zzgq zza = zza(zzgK);
                            zza.zzb(new zzgt(this.zzsw.zzqr, this, this.zzsw.zzve, zzgK, (zzgu.zza) zza));
                            zza(zza);
                        }
                    }
                    zzpe.zzbe("No matching mapper/listener for retrieved native ad template.");
                    zzh(0);
                    return false;
                }
                zzgp zza2 = zza(zzgJ);
                zza2.zzb(new zzgt(this.zzsw.zzqr, this, this.zzsw.zzve, zzgJ, (zzgu.zza) zza2));
                zza(zza2);
            } catch (RemoteException e) {
                zzpe.zzc("Failed to get native ad mapper", e);
            }
        } else {
            zzgu.zza zza3 = zzov2.zzVx;
            if ((zza3 instanceof zzgq) && this.zzsw.zzvu != null) {
                zza((zzgq) zzov2.zzVx);
            } else if ((zza3 instanceof zzgp) && this.zzsw.zzvt != null) {
                zza((zzgp) zzov2.zzVx);
            } else if (!(zza3 instanceof zzgr) || this.zzsw.zzvw == null || this.zzsw.zzvw.get(((zzgr) zza3).getCustomTemplateId()) == null) {
                zzpe.zzbe("No matching listener for retrieved native ad template.");
                zzh(0);
                return false;
            } else {
                zza(zzov2, ((zzgr) zza3).getCustomTemplateId());
            }
        }
        return super.zza(zzov, zzov2);
    }

    public void zzb(SimpleArrayMap<String, zzhl> simpleArrayMap) {
        zzac.zzdn("setOnCustomClickListener must be called on the main UI thread.");
        this.zzsw.zzvv = simpleArrayMap;
    }

    public void zzb(zzgw zzgw) {
        zzac.zzdn("setNativeAdOptions must be called on the main UI thread.");
        this.zzsw.zzvx = zzgw;
    }

    public void zzb(zzhj zzhj) {
        zzac.zzdn("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        this.zzsw.zzvt = zzhj;
    }

    public void zzb(zzhk zzhk) {
        zzac.zzdn("setOnContentAdLoadedListener must be called on the main UI thread.");
        this.zzsw.zzvu = zzhk;
    }

    public void zzb(@Nullable List<String> list) {
        zzac.zzdn("setNativeTemplates must be called on the main UI thread.");
        this.zzsw.zzvB = list;
    }

    public void zzc(zzqp zzqp) {
        this.zzua = zzqp;
    }

    public void zzcr() {
        if (this.zzsw.zzvk == null || this.zzua == null) {
            zzpe.zzbe("Request to enable ActiveView before adState is available.");
        } else {
            zzv.zzcN().zzjZ().zza(this.zzsw.zzvj, this.zzsw.zzvk, this.zzua.getView(), (zzjb) this.zzua);
        }
    }

    public SimpleArrayMap<String, zzhm> zzcs() {
        zzac.zzdn("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzsw.zzvw;
    }

    public void zzct() {
        if (this.zzua != null) {
            this.zzua.destroy();
            this.zzua = null;
        }
    }

    public void zzcu() {
        if (this.zzua != null && this.zzua.zzlg() != null && this.zzsw.zzvx != null && this.zzsw.zzvx.zzGH != null) {
            this.zzua.zzlg().zzP(this.zzsw.zzvx.zzGH.zzAE);
        }
    }

    @Nullable
    public zzhl zzz(String str) {
        zzac.zzdn("getOnCustomClickListener must be called on the main UI thread.");
        return this.zzsw.zzvv.get(str);
    }
}
