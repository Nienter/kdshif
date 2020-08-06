package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgu;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@zzmb
public class zzgt extends zzgv {
    @Nullable
    private zzjw zzGj;
    @Nullable
    private zzjx zzGk;
    private final zzr zzGl;
    @Nullable
    private zzgu zzGm;
    private boolean zzGn;
    private Object zzrN;

    private zzgt(Context context, zzr zzr, zzav zzav, zzgu.zza zza) {
        super(context, zzr, null, zzav, null, zza, null, null);
        this.zzGn = false;
        this.zzrN = new Object();
        this.zzGl = zzr;
    }

    public zzgt(Context context, zzr zzr, zzav zzav, zzjw zzjw, zzgu.zza zza) {
        this(context, zzr, zzav, zza);
        this.zzGj = zzjw;
    }

    public zzgt(Context context, zzr zzr, zzav zzav, zzjx zzjx, zzgu.zza zza) {
        this(context, zzr, zzav, zza);
        this.zzGk = zzjx;
    }

    @Nullable
    public zzgn zza(View.OnClickListener onClickListener) {
        return null;
    }

    public void zza(View view, Map<String, WeakReference<View>> map, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        synchronized (this.zzrN) {
            this.zzGn = true;
            try {
                if (this.zzGj != null) {
                    this.zzGj.zzl(zze.zzA(view));
                } else if (this.zzGk != null) {
                    this.zzGk.zzl(zze.zzA(view));
                }
            } catch (RemoteException e) {
                zzpe.zzc("Failed to call prepareAd", e);
            }
            this.zzGn = false;
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, View view2) {
        zzac.zzdn("performClick must be called on the main UI thread.");
        synchronized (this.zzrN) {
            if (this.zzGm != null) {
                this.zzGm.zza(view, map, jSONObject, view2);
                this.zzGl.onAdClicked();
            } else {
                try {
                    if (this.zzGj != null && !this.zzGj.getOverrideClickHandling()) {
                        this.zzGj.zzk(zze.zzA(view));
                        this.zzGl.onAdClicked();
                    }
                    if (this.zzGk != null && !this.zzGk.getOverrideClickHandling()) {
                        this.zzGk.zzk(zze.zzA(view));
                        this.zzGl.onAdClicked();
                    }
                } catch (RemoteException e) {
                    zzpe.zzc("Failed to call performClick", e);
                }
            }
        }
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        zzac.zzdn("recordImpression must be called on the main UI thread.");
        synchronized (this.zzrN) {
            zzq(true);
            if (this.zzGm != null) {
                this.zzGm.zzb(view, map);
                this.zzGl.recordImpression();
            } else {
                try {
                    if (this.zzGj != null && !this.zzGj.getOverrideImpressionRecording()) {
                        this.zzGj.recordImpression();
                        this.zzGl.recordImpression();
                    } else if (this.zzGk != null && !this.zzGk.getOverrideImpressionRecording()) {
                        this.zzGk.recordImpression();
                        this.zzGl.recordImpression();
                    }
                } catch (RemoteException e) {
                    zzpe.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    public void zzc(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.zzrN) {
            try {
                if (this.zzGj != null) {
                    this.zzGj.zzm(zze.zzA(view));
                } else if (this.zzGk != null) {
                    this.zzGk.zzm(zze.zzA(view));
                }
            } catch (RemoteException e) {
                zzpe.zzc("Failed to call untrackView", e);
            }
        }
    }

    public void zzc(@Nullable zzgu zzgu) {
        synchronized (this.zzrN) {
            this.zzGm = zzgu;
        }
    }

    public boolean zzfS() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzGn;
        }
        return z;
    }

    public zzgu zzfT() {
        zzgu zzgu;
        synchronized (this.zzrN) {
            zzgu = this.zzGm;
        }
        return zzgu;
    }

    @Nullable
    public zzqp zzfU() {
        return null;
    }
}
