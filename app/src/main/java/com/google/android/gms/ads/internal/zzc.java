package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzgf;
import com.google.android.gms.internal.zzgg;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzkp;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzop;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import com.google.android.gms.internal.zzqa;
import com.google.android.gms.internal.zzqp;
import java.util.Map;

@zzmb
public abstract class zzc extends zzb implements zzh, zzkp {
    public zzc(Context context, zzec zzec, String str, zzjs zzjs, zzqa zzqa, zzd zzd) {
        super(context, zzec, str, zzjs, zzqa, zzd);
    }

    /* access modifiers changed from: protected */
    public zzqp zza(zzov.zza zza, @Nullable zze zze, @Nullable zzop zzop) {
        zzqp zzqp = null;
        View nextView = this.zzsw.zzvg.getNextView();
        if (nextView instanceof zzqp) {
            zzqp = (zzqp) nextView;
            if (zzfx.zzCn.get().booleanValue()) {
                zzpe.zzbc("Reusing webview...");
                zzqp.zza(this.zzsw.zzqr, this.zzsw.zzvj, this.zzsr);
            } else {
                zzqp.destroy();
                zzqp = null;
            }
        }
        if (zzqp == null) {
            if (nextView != null) {
                this.zzsw.zzvg.removeView(nextView);
            }
            zzqp = zzv.zzcK().zza(this.zzsw.zzqr, this.zzsw.zzvj, false, false, this.zzsw.zzve, this.zzsw.zzvf, this.zzsr, this, this.zzsz);
            if (this.zzsw.zzvj.zzzm == null) {
                zzb(zzqp.getView());
            }
        }
        zzqp zzqp2 = zzqp;
        zzqp2.zzkV().zza(this, this, this, this, false, this, null, zze, this, zzop);
        zza((zzjb) zzqp2);
        zzqp2.zzbg(zza.zzSF.zzRr);
        return zzqp2;
    }

    public void zza(int i, int i2, int i3, int i4) {
        zzbJ();
    }

    public void zza(zzgj zzgj) {
        zzac.zzdn("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzsw.zzvz = zzgj;
    }

    /* access modifiers changed from: protected */
    public void zza(zzjb zzjb) {
        zzjb.zza("/trackActiveViewUnit", (zzhx) new zzhx() {
            public void zza(zzqp zzqp, Map<String, String> map) {
                if (zzc.this.zzsw.zzvk != null) {
                    zzc.this.zzsy.zza(zzc.this.zzsw.zzvj, zzc.this.zzsw.zzvk, zzqp.getView(), (zzjb) zzqp);
                } else {
                    zzpe.zzbe("Request to enable ActiveView before adState is available.");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zza(final zzov.zza zza, final zzgf zzgf) {
        if (zza.errorCode != -2) {
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zzc.this.zzb(new zzov(zza, null, null, null, null, null, null, null));
                }
            });
            return;
        }
        if (zza.zzvj != null) {
            this.zzsw.zzvj = zza.zzvj;
        }
        if (!zza.zzVB.zzRK || zza.zzVB.zzzp) {
            zzpi.zzWR.post(new Runnable(null) {
                public void run() {
                    if (zza.zzVB.zzRT && zzc.this.zzsw.zzvz != null) {
                        String str = null;
                        if (zza.zzVB.zzNb != null) {
                            str = zzv.zzcJ().zzaV(zza.zzVB.zzNb);
                        }
                        zzgg zzgg = new zzgg(zzc.this, str, zza.zzVB.body);
                        zzc.this.zzsw.zzvF = 1;
                        try {
                            zzc.this.zzsu = false;
                            zzc.this.zzsw.zzvz.zza(zzgg);
                            return;
                        } catch (RemoteException e) {
                            zzpe.zzc("Could not call the onCustomRenderedAdLoadedListener.", e);
                            zzc.this.zzsu = true;
                        }
                    }
                    final zze zze = new zze(zzc.this.zzsw.zzqr, zza);
                    zzqp zza = zzc.this.zza(zza, zze, null);
                    zza.setOnTouchListener(new View.OnTouchListener(this) {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            zze.recordClick();
                            return false;
                        }
                    });
                    zza.setOnClickListener(new View.OnClickListener(this) {
                        public void onClick(View view) {
                            zze.recordClick();
                        }
                    });
                    zzc.this.zzsw.zzvF = 0;
                    zzc.this.zzsw.zzvi = zzv.zzcI().zza(zzc.this.zzsw.zzqr, zzc.this, zza, zzc.this.zzsw.zzve, zza, zzc.this.zzsD, zzc.this, zzgf);
                }
            });
            return;
        }
        this.zzsw.zzvF = 0;
        this.zzsw.zzvi = zzv.zzcI().zza(this.zzsw.zzqr, this, zza, this.zzsw.zzve, null, this.zzsD, this, zzgf);
    }

    /* access modifiers changed from: protected */
    public boolean zza(@Nullable zzov zzov, zzov zzov2) {
        if (this.zzsw.zzdm() && this.zzsw.zzvg != null) {
            this.zzsw.zzvg.zzds().zzaY(zzov2.zzRP);
        }
        return super.zza(zzov, zzov2);
    }

    public void zzbX() {
        onAdClicked();
    }

    public void zzbY() {
        recordImpression();
        zzbF();
    }

    public void zzbZ() {
        zzbH();
    }

    public void zzc(View view) {
        this.zzsw.zzvE = view;
        zzb(new zzov(this.zzsw.zzvl, null, null, null, null, null, null, null));
    }
}
