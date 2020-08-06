package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@zzmb
public class zzjp implements zzjh {
    private final Context mContext;
    private final zzmh zzKG;
    /* access modifiers changed from: private */
    public final long zzKH;
    /* access modifiers changed from: private */
    public final long zzKI;
    private final int zzKJ;
    /* access modifiers changed from: private */
    public boolean zzKK = false;
    /* access modifiers changed from: private */
    public final Map<zzqf<zzjn>, zzjm> zzKL = new HashMap();
    private List<zzjn> zzKM = new ArrayList();
    private final zzjj zzKq;
    private final boolean zzKs;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();
    private final zzjs zzsD;
    private final boolean zzvW;

    public zzjp(Context context, zzmh zzmh, zzjs zzjs, zzjj zzjj, boolean z, boolean z2, long j, long j2, int i) {
        this.mContext = context;
        this.zzKG = zzmh;
        this.zzsD = zzjs;
        this.zzKq = zzjj;
        this.zzvW = z;
        this.zzKs = z2;
        this.zzKH = j;
        this.zzKI = j2;
        this.zzKJ = i;
    }

    private void zza(final zzqf<zzjn> zzqf) {
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                for (zzqf zzqf : zzjp.this.zzKL.keySet()) {
                    if (zzqf != zzqf) {
                        ((zzjm) zzjp.this.zzKL.get(zzqf)).cancel();
                    }
                }
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r2.hasNext() == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r0 = r2.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1 = (com.google.android.gms.internal.zzjn) r0.get();
        r4.zzKM.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        if (r1 == null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r1.zzKz != 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        zza((com.google.android.gms.internal.zzqf<com.google.android.gms.internal.zzjn>) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        com.google.android.gms.internal.zzpe.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003f, code lost:
        zza((com.google.android.gms.internal.zzqf<com.google.android.gms.internal.zzjn>) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return new com.google.android.gms.internal.zzjn(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r2 = r5.iterator();
     */
    private zzjn zze(List<zzqf<zzjn>> list) {
        synchronized (this.zzrN) {
            if (this.zzKK) {
                zzjn zzjn = new zzjn(-1);
                return zzjn;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        r0 = r15.zzKq.zzKi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r8 = r16.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r6 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r8.hasNext() == false) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r0 = r8.next();
        r10 = com.google.android.gms.ads.internal.zzv.zzcP().currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        if (r6 != 0) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        if (r0.isDone() == false) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r1 = (com.google.android.gms.internal.zzjn) r0.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        r15.zzKM.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        if (r1 == null) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        if (r1.zzKz != 0) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0057, code lost:
        r5 = r1.zzKE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        if (r5 == null) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
        if (r5.zzgH() <= r4) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0061, code lost:
        r2 = r5.zzgH();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0064, code lost:
        r14 = r1;
        r1 = r0;
        r0 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        r3 = r1;
        r14 = r0;
        r0 = java.lang.Math.max(r6 - (com.google.android.gms.ads.internal.zzv.zzcP().currentTimeMillis() - r10), 0);
        r4 = r2;
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0083, code lost:
        r0 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r1 = (com.google.android.gms.internal.zzjn) r0.get(r6, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        com.google.android.gms.internal.zzpe.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0095, code lost:
        r0 = java.lang.Math.max(r6 - (com.google.android.gms.ads.internal.zzv.zzcP().currentTimeMillis() - r10), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a8, code lost:
        java.lang.Math.max(r6 - (com.google.android.gms.ads.internal.zzv.zzcP().currentTimeMillis() - r10), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b8, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b9, code lost:
        zza((com.google.android.gms.internal.zzqf<com.google.android.gms.internal.zzjn>) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00bc, code lost:
        if (r2 != null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00cc, code lost:
        r0 = r2;
        r1 = r3;
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return new com.google.android.gms.internal.zzjn(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r4 = -1;
        r3 = null;
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (r15.zzKq.zzKi == -1) goto L_0x0083;
     */
    private zzjn zzf(List<zzqf<zzjn>> list) {
        synchronized (this.zzrN) {
            if (this.zzKK) {
                zzjn zzjn = new zzjn(-1);
                return zzjn;
            }
        }
    }

    public void cancel() {
        synchronized (this.zzrN) {
            this.zzKK = true;
            for (zzjm cancel : this.zzKL.values()) {
                cancel.cancel();
            }
        }
    }

    public zzjn zzd(List<zzji> list) {
        zzpe.zzbc("Starting mediation.");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        for (zzji next : list) {
            String valueOf = String.valueOf(next.zzJH);
            zzpe.zzbd(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            for (String zzjm : next.zzJI) {
                final zzjm zzjm2 = new zzjm(this.mContext, zzjm, this.zzsD, this.zzKq, next, this.zzKG.zzRd, this.zzKG.zzvj, this.zzKG.zzvf, this.zzvW, this.zzKs, this.zzKG.zzvx, this.zzKG.zzvB);
                zzqf zza = zzph.zza(newCachedThreadPool, new Callable<zzjn>() {
                    /* renamed from: zzgI */
                    public zzjn call() {
                        synchronized (zzjp.this.zzrN) {
                            if (zzjp.this.zzKK) {
                                return null;
                            }
                            return zzjm2.zza(zzjp.this.zzKH, zzjp.this.zzKI);
                        }
                    }
                });
                this.zzKL.put(zza, zzjm2);
                arrayList.add(zza);
            }
        }
        switch (this.zzKJ) {
            case 2:
                return zzf(arrayList);
            default:
                return zze((List<zzqf<zzjn>>) arrayList);
        }
    }

    public List<zzjn> zzgB() {
        return this.zzKM;
    }
}
