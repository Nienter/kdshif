package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.zzo;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzov;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import p005b.p006a.p007a.p008a.p009a.p014d.EventsFilesManager;

@zzmb
public class zzlr extends zzlm {
    /* access modifiers changed from: private */
    public final zzqp zzGt;
    private zzjj zzKq;
    zzjh zzPL;
    protected zzjn zzPM;
    /* access modifiers changed from: private */
    public boolean zzPN;
    private zzjs zzsD;
    private final zzgf zzsr;

    zzlr(Context context, zzov.zza zza, zzjs zzjs, zzln.zza zza2, zzgf zzgf, zzqp zzqp) {
        super(context, zza, zza2);
        this.zzsD = zzjs;
        this.zzKq = zza.zzVr;
        this.zzsr = zzgf;
        this.zzGt = zzqp;
    }

    private static int zzR(int i) {
        switch (i) {
            case -1:
                return 4;
            case 0:
                return 0;
            case 1:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 5;
            default:
                return 6;
        }
    }

    private static String zza(zzjn zzjn) {
        String str = zzjn.zzKA.zzJJ;
        int zzR = zzR(zzjn.zzKz);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(zzR).append(".").append(zzjn.zzKF).toString();
    }

    private static String zzg(List<zzjn> list) {
        if (list == null) {
            return "".toString();
        }
        String str = "";
        for (zzjn next : list) {
            if (!(next == null || next.zzKA == null || TextUtils.isEmpty(next.zzKA.zzJJ))) {
                String valueOf = String.valueOf(str);
                String valueOf2 = String.valueOf(zza(next));
                str = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append(valueOf2).append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).toString();
            }
        }
        return str.substring(0, Math.max(0, str.length() - 1));
    }

    private void zziD() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                synchronized (zzlr.this.zzPr) {
                    boolean unused = zzlr.this.zzPN = zzo.zza(zzlr.this.zzGt, zzlr.this.zzPM, countDownLatch);
                }
            }
        });
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
            synchronized (this.zzPr) {
                if (!this.zzPN) {
                    throw new zzlm.zza("View could not be prepared", 0);
                } else if (this.zzGt.isDestroyed()) {
                    throw new zzlm.zza("Assets not loaded, web view is destroyed", 0);
                }
            }
        } catch (InterruptedException e) {
            String valueOf = String.valueOf(e);
            throw new zzlm.zza(new StringBuilder(String.valueOf(valueOf).length() + 38).append("Interrupted while waiting for latch : ").append(valueOf).toString(), 0);
        }
    }

    public void onStop() {
        synchronized (this.zzPr) {
            super.onStop();
            if (this.zzPL != null) {
                this.zzPL.cancel();
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzov zzP(int i) {
        zzmh zzmh = this.zzPo.zzSF;
        return new zzov(zzmh.zzRd, this.zzGt, this.zzPp.zzJY, i, this.zzPp.zzJZ, this.zzPp.zzRM, this.zzPp.orientation, this.zzPp.zzKe, zzmh.zzRg, this.zzPp.zzRK, this.zzPM != null ? this.zzPM.zzKA : null, this.zzPM != null ? this.zzPM.zzKB : null, this.zzPM != null ? this.zzPM.zzKC : AdMobAdapter.class.getName(), this.zzKq, this.zzPM != null ? this.zzPM.zzKD : null, this.zzPp.zzRL, this.zzPo.zzvj, this.zzPp.zzRJ, this.zzPo.zzVv, this.zzPp.zzRO, this.zzPp.zzRP, this.zzPo.zzVp, null, this.zzPp.zzRZ, this.zzPp.zzSa, this.zzPp.zzSb, this.zzKq != null ? this.zzKq.zzKj : false, this.zzPp.zzSd, this.zzPL != null ? zzg(this.zzPL.zzgB()) : null, this.zzPp.zzKb, this.zzPp.zzSg);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0084  */
    public void zzh(long j) {
        boolean z;
        synchronized (this.zzPr) {
            this.zzPL = zzi(j);
        }
        ArrayList arrayList = new ArrayList(this.zzKq.zzJW);
        Bundle bundle = this.zzPo.zzSF.zzRd.zzyP;
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            if (bundle2 != null) {
                z = bundle2.getBoolean("_skipMediation");
                if (z) {
                    ListIterator listIterator = arrayList.listIterator();
                    while (listIterator.hasNext()) {
                        if (!((zzji) listIterator.next()).zzJI.contains("com.google.ads.mediation.admob.AdMobAdapter")) {
                            listIterator.remove();
                        }
                    }
                }
                this.zzPM = this.zzPL.zzd(arrayList);
                switch (this.zzPM.zzKz) {
                    case 0:
                        if (this.zzPM.zzKA != null && this.zzPM.zzKA.zzJR != null) {
                            zziD();
                            return;
                        }
                        return;
                    case 1:
                        throw new zzlm.zza("No fill from any mediation ad networks.", 3);
                    default:
                        throw new zzlm.zza(new StringBuilder(40).append("Unexpected mediation result: ").append(this.zzPM.zzKz).toString(), 0);
                }
            }
        }
        z = false;
        if (z) {
        }
        this.zzPM = this.zzPL.zzd(arrayList);
        switch (this.zzPM.zzKz) {
            case 0:
                break;
            case 1:
                break;
        }
    }

    /* access modifiers changed from: package-private */
    public zzjh zzi(long j) {
        if (this.zzKq.zzKh != -1) {
            return new zzjp(this.mContext, this.zzPo.zzSF, this.zzsD, this.zzKq, this.zzPp.zzzn, this.zzPp.zzzp, j, zzfx.zzDe.get().longValue(), 2);
        }
        return new zzjq(this.mContext, this.zzPo.zzSF, this.zzsD, this.zzKq, this.zzPp.zzzn, this.zzPp.zzzp, j, zzfx.zzDe.get().longValue(), this.zzsr);
    }
}
