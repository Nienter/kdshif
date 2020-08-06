package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzov;

@zzmb
public abstract class zzlm extends zzpd {
    protected final Context mContext;
    protected final zzln.zza zzPn;
    protected final zzov.zza zzPo;
    protected zzmk zzPp;
    protected final Object zzPr = new Object();
    protected final Object zzrN = new Object();

    protected static final class zza extends Exception {
        private final int zzPF;

        public zza(String str, int i) {
            super(str);
            this.zzPF = i;
        }

        public int getErrorCode() {
            return this.zzPF;
        }
    }

    protected zzlm(Context context, zzov.zza zza2, zzln.zza zza3) {
        super(true);
        this.mContext = context;
        this.zzPo = zza2;
        this.zzPp = zza2.zzVB;
        this.zzPn = zza3;
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public abstract zzov zzP(int i);

    public void zzcm() {
        synchronized (this.zzrN) {
            zzpe.zzbc("AdRendererBackgroundTask started.");
            int i = this.zzPo.errorCode;
            try {
                zzh(SystemClock.elapsedRealtime());
            } catch (zza e) {
                int errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    zzpe.zzbd(e.getMessage());
                } else {
                    zzpe.zzbe(e.getMessage());
                }
                if (this.zzPp == null) {
                    this.zzPp = new zzmk(errorCode);
                } else {
                    this.zzPp = new zzmk(errorCode, this.zzPp.zzKe);
                }
                zzpi.zzWR.post(new Runnable() {
                    public void run() {
                        zzlm.this.onStop();
                    }
                });
                i = errorCode;
            }
            final zzov zzP = zzP(i);
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    synchronized (zzlm.this.zzrN) {
                        zzlm.this.zzn(zzP);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzh(long j);

    /* access modifiers changed from: protected */
    public void zzn(zzov zzov) {
        this.zzPn.zzb(zzov);
    }
}
