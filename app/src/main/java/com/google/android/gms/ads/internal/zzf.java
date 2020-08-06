package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzdy;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzop;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import com.google.android.gms.internal.zzqa;
import com.google.android.gms.internal.zzqp;
import com.google.android.gms.internal.zzqq;
import com.google.android.gms.internal.zzqu;

@zzmb
public class zzf extends zzc implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private boolean zzsS;

    public class zza {
        public zza() {
        }

        public void onClick() {
            zzf.this.onAdClicked();
        }
    }

    public zzf(Context context, zzec zzec, String str, zzjs zzjs, zzqa zzqa, zzd zzd) {
        super(context, zzec, str, zzjs, zzqa, zzd);
    }

    private zzec zzb(zzov.zza zza2) {
        AdSize zzeA;
        if (zza2.zzVB.zzzo) {
            return this.zzsw.zzvj;
        }
        String str = zza2.zzVB.zzRN;
        if (str != null) {
            String[] split = str.split("[xX]");
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            zzeA = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } else {
            zzeA = this.zzsw.zzvj.zzeA();
        }
        return new zzec(this.zzsw.zzqr, zzeA);
    }

    private boolean zzb(@Nullable zzov zzov, zzov zzov2) {
        if (zzov2.zzRK) {
            View zzg = zzo.zzg(zzov2);
            if (zzg == null) {
                zzpe.zzbe("Could not get mediation view");
                return false;
            }
            View nextView = this.zzsw.zzvg.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzqp) {
                    ((zzqp) nextView).destroy();
                }
                this.zzsw.zzvg.removeView(nextView);
            }
            if (!zzo.zzh(zzov2)) {
                try {
                    zzb(zzg);
                } catch (Throwable th) {
                    zzpe.zzc("Could not add mediation view to view hierarchy.", th);
                    return false;
                }
            }
        } else if (!(zzov2.zzVt == null || zzov2.zzMZ == null)) {
            zzov2.zzMZ.zza(zzov2.zzVt);
            this.zzsw.zzvg.removeAllViews();
            this.zzsw.zzvg.setMinimumWidth(zzov2.zzVt.widthPixels);
            this.zzsw.zzvg.setMinimumHeight(zzov2.zzVt.heightPixels);
            zzb(zzov2.zzMZ.getView());
        }
        if (this.zzsw.zzvg.getChildCount() > 1) {
            this.zzsw.zzvg.showNext();
        }
        if (zzov != null) {
            View nextView2 = this.zzsw.zzvg.getNextView();
            if (nextView2 instanceof zzqp) {
                ((zzqp) nextView2).zza(this.zzsw.zzqr, this.zzsw.zzvj, this.zzsr);
            } else if (nextView2 != null) {
                this.zzsw.zzvg.removeView(nextView2);
            }
            this.zzsw.zzdl();
        }
        this.zzsw.zzvg.setVisibility(0);
        return true;
    }

    private void zze(final zzov zzov) {
        if (zzs.zzyA()) {
            if (this.zzsw.zzdm()) {
                if (zzov.zzMZ != null) {
                    if (zzov.zzVp != null) {
                        this.zzsy.zza(this.zzsw.zzvj, zzov);
                    }
                    if (zzov.zzdz()) {
                        new zzcv(this.zzsw.zzqr, zzov.zzMZ.getView()).zza((zzcv.zzb) zzov.zzMZ);
                    } else {
                        zzov.zzMZ.zzkV().zza((zzqq.zzc) new zzqq.zzc() {
                            public void zzcd() {
                                new zzcv(zzf.this.zzsw.zzqr, zzov.zzMZ.getView()).zza((zzcv.zzb) zzov.zzMZ);
                            }
                        });
                    }
                }
            } else if (this.zzsw.zzvE != null && zzov.zzVp != null) {
                this.zzsy.zza(this.zzsw.zzvj, zzov, this.zzsw.zzvE);
            }
        }
    }

    public void onGlobalLayout() {
        zzf(this.zzsw.zzvk);
    }

    public void onScrollChanged() {
        zzf(this.zzsw.zzvk);
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzac.zzdn("setManualImpressionsEnabled must be called from the main thread.");
        this.zzsS = z;
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    /* access modifiers changed from: protected */
    public zzqp zza(zzov.zza zza2, @Nullable zze zze, @Nullable zzop zzop) {
        if (this.zzsw.zzvj.zzzm == null && this.zzsw.zzvj.zzzo) {
            this.zzsw.zzvj = zzb(zza2);
        }
        return super.zza(zza2, zze, zzop);
    }

    /* access modifiers changed from: protected */
    public void zza(@Nullable zzov zzov, boolean z) {
        super.zza(zzov, z);
        if (zzo.zzh(zzov)) {
            zzo.zza(zzov, new zza());
        }
    }

    public boolean zza(@Nullable zzov zzov, final zzov zzov2) {
        zzqu zzqu;
        if (!super.zza(zzov, zzov2)) {
            return false;
        }
        if (!this.zzsw.zzdm() || zzb(zzov, zzov2)) {
            if (zzov2.zzSc) {
                zzf(zzov2);
                zzv.zzdh().zza((View) this.zzsw.zzvg, (ViewTreeObserver.OnGlobalLayoutListener) this);
                zzv.zzdh().zza((View) this.zzsw.zzvg, (ViewTreeObserver.OnScrollChangedListener) this);
                if (!zzov2.zzVq) {
                    final C09921 r2 = new Runnable() {
                        public void run() {
                            zzf.this.zzf(zzf.this.zzsw.zzvk);
                        }
                    };
                    zzqq zzkV = zzov2.zzMZ != null ? zzov2.zzMZ.zzkV() : null;
                    if (zzkV != null) {
                        zzkV.zza((zzqq.zze) new zzqq.zze(this) {
                            public void zzcc() {
                                if (!zzov2.zzVq) {
                                    zzv.zzcJ();
                                    zzpi.zzb(r2);
                                }
                            }
                        });
                    }
                }
            } else if (!this.zzsw.zzdn() || zzfx.zzDQ.get().booleanValue()) {
                zza(zzov2, false);
            }
            if (zzov2.zzMZ != null) {
                zzqu = zzov2.zzMZ.zzlg();
                zzqq zzkV2 = zzov2.zzMZ.zzkV();
                if (zzkV2 != null) {
                    zzkV2.zzlt();
                }
            } else {
                zzqu = null;
            }
            if (!(this.zzsw.zzvy == null || zzqu == null)) {
                zzqu.zzP(this.zzsw.zzvy.zzAE);
            }
            zze(zzov2);
            return true;
        }
        zzh(0);
        return false;
    }

    public boolean zzb(zzdy zzdy) {
        return super.zzb(zze(zzdy));
    }

    @Nullable
    public zzew zzbG() {
        zzac.zzdn("getVideoController must be called from the main thread.");
        if (this.zzsw.zzvk == null || this.zzsw.zzvk.zzMZ == null) {
            return null;
        }
        return this.zzsw.zzvk.zzMZ.zzlg();
    }

    /* access modifiers changed from: protected */
    public boolean zzbM() {
        boolean z = true;
        if (!zzv.zzcJ().zza(this.zzsw.zzqr.getPackageManager(), this.zzsw.zzqr.getPackageName(), "android.permission.INTERNET")) {
            zzeh.zzeO().zza(this.zzsw.zzvg, this.zzsw.zzvj, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        if (!zzv.zzcJ().zzy(this.zzsw.zzqr)) {
            zzeh.zzeO().zza(this.zzsw.zzvg, this.zzsw.zzvj, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!z && this.zzsw.zzvg != null) {
            this.zzsw.zzvg.setVisibility(0);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public zzdy zze(zzdy zzdy) {
        if (zzdy.zzyK == this.zzsS) {
            return zzdy;
        }
        return new zzdy(zzdy.versionCode, zzdy.zzyF, zzdy.extras, zzdy.zzyG, zzdy.zzyH, zzdy.zzyI, zzdy.zzyJ, zzdy.zzyK || this.zzsS, zzdy.zzyL, zzdy.zzyM, zzdy.zzyN, zzdy.zzyO, zzdy.zzyP, zzdy.zzyQ, zzdy.zzyR, zzdy.zzyS, zzdy.zzyT, zzdy.zzyU);
    }

    /* access modifiers changed from: package-private */
    public void zzf(@Nullable zzov zzov) {
        if (zzov != null && !zzov.zzVq && this.zzsw.zzvg != null && zzv.zzcJ().zza((View) this.zzsw.zzvg, this.zzsw.zzqr) && this.zzsw.zzvg.getGlobalVisibleRect(new Rect(), null)) {
            if (!(zzov == null || zzov.zzMZ == null || zzov.zzMZ.zzkV() == null)) {
                zzov.zzMZ.zzkV().zza((zzqq.zze) null);
            }
            zza(zzov, false);
            zzov.zzVq = true;
        }
    }
}
