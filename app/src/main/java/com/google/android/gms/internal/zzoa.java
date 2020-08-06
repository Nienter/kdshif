package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzob;
import com.google.android.gms.internal.zzov;

@zzmb
public class zzoa extends zzpd implements zzoc, zzof {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String zzKo;
    private int zzPF = 3;
    private final zzov.zza zzPo;
    private final zzoh zzUL;
    private final zzof zzUM;
    /* access modifiers changed from: private */
    public final String zzUN;
    private final zzji zzUO;
    private final long zzUP;
    private int zzUQ = 0;
    private zzob zzUR;
    private final Object zzrN;

    public zzoa(Context context, String str, String str2, zzji zzji, zzov.zza zza, zzoh zzoh, zzof zzof, long j) {
        this.mContext = context;
        this.zzKo = str;
        this.zzUN = str2;
        this.zzUO = zzji;
        this.zzPo = zza;
        this.zzUL = zzoh;
        this.zzrN = new Object();
        this.zzUM = zzof;
        this.zzUP = j;
    }

    /* access modifiers changed from: private */
    public void zza(zzdy zzdy, zzjt zzjt) {
        this.zzUL.zzjx().zza((zzof) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzKo)) {
                zzjt.zza(zzdy, this.zzUN, this.zzUO.zzJG);
            } else {
                zzjt.zzc(zzdy, this.zzUN);
            }
        } catch (RemoteException e) {
            zzpe.zzc("Fail to load ad from adapter.", e);
            zza(this.zzKo, 0);
        }
    }

    private void zzk(long j) {
        while (true) {
            synchronized (this.zzrN) {
                if (this.zzUQ != 0) {
                    this.zzUR = new zzob.zza().zzl(zzv.zzcP().elapsedRealtime() - j).zzac(1 == this.zzUQ ? 6 : this.zzPF).zzaO(this.zzKo).zzaP(this.zzUO.zzJJ).zzjt();
                    return;
                } else if (!zzf(j)) {
                    this.zzUR = new zzob.zza().zzac(this.zzPF).zzl(zzv.zzcP().elapsedRealtime() - j).zzaO(this.zzKo).zzaP(this.zzUO.zzJJ).zzjt();
                    return;
                }
            }
        }
    }

    public void onStop() {
    }

    public void zza(String str, int i) {
        synchronized (this.zzrN) {
            this.zzUQ = 2;
            this.zzPF = i;
            this.zzrN.notify();
        }
    }

    public void zzaN(String str) {
        synchronized (this.zzrN) {
            this.zzUQ = 1;
            this.zzrN.notify();
        }
    }

    public void zzab(int i) {
        zza(this.zzKo, 0);
    }

    public void zzcm() {
        if (this.zzUL != null && this.zzUL.zzjx() != null && this.zzUL.zzjw() != null) {
            final zzoe zzjx = this.zzUL.zzjx();
            zzjx.zza((zzof) null);
            zzjx.zza((zzoc) this);
            final zzdy zzdy = this.zzPo.zzSF.zzRd;
            final zzjt zzjw = this.zzUL.zzjw();
            try {
                if (zzjw.isInitialized()) {
                    zzpx.zzXU.post(new Runnable() {
                        public void run() {
                            zzoa.this.zza(zzdy, zzjw);
                        }
                    });
                } else {
                    zzpx.zzXU.post(new Runnable() {
                        public void run() {
                            try {
                                zzjw.zza(zze.zzA(zzoa.this.mContext), zzdy, (String) null, (zzoi) zzjx, zzoa.this.zzUN);
                            } catch (RemoteException e) {
                                RemoteException remoteException = e;
                                String valueOf = String.valueOf(zzoa.this.zzKo);
                                zzpe.zzc(valueOf.length() != 0 ? "Fail to initialize adapter ".concat(valueOf) : new String("Fail to initialize adapter "), remoteException);
                                zzoa.this.zza(zzoa.this.zzKo, 0);
                            }
                        }
                    });
                }
            } catch (RemoteException e) {
                zzpe.zzc("Fail to check if adapter is initialized.", e);
                zza(this.zzKo, 0);
            }
            zzk(zzv.zzcP().elapsedRealtime());
            zzjx.zza((zzof) null);
            zzjx.zza((zzoc) null);
            if (this.zzUQ == 1) {
                this.zzUM.zzaN(this.zzKo);
            } else {
                this.zzUM.zza(this.zzKo, this.zzPF);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzf(long j) {
        long elapsedRealtime = this.zzUP - (zzv.zzcP().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            this.zzPF = 4;
            return false;
        }
        try {
            this.zzrN.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.zzPF = 5;
            return false;
        }
    }

    public zzob zzjq() {
        zzob zzob;
        synchronized (this.zzrN) {
            zzob = this.zzUR;
        }
        return zzob;
    }

    public zzji zzjr() {
        return this.zzUO;
    }

    public void zzjs() {
        zza(this.zzPo.zzSF.zzRd, this.zzUL.zzjw());
    }
}
