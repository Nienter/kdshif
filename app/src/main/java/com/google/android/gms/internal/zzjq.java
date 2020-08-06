package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzmb
public class zzjq implements zzjh {
    private final Context mContext;
    private final zzmh zzKG;
    private final long zzKH;
    private final long zzKI;
    private boolean zzKK = false;
    private List<zzjn> zzKM = new ArrayList();
    private zzjm zzKQ;
    private final zzjj zzKq;
    private final boolean zzKs;
    private final Object zzrN = new Object();
    private final zzjs zzsD;
    private final zzgf zzsr;
    private final boolean zzvW;

    public zzjq(Context context, zzmh zzmh, zzjs zzjs, zzjj zzjj, boolean z, boolean z2, long j, long j2, zzgf zzgf) {
        this.mContext = context;
        this.zzKG = zzmh;
        this.zzsD = zzjs;
        this.zzKq = zzjj;
        this.zzvW = z;
        this.zzKs = z2;
        this.zzKH = j;
        this.zzKI = j2;
        this.zzsr = zzgf;
    }

    public void cancel() {
        synchronized (this.zzrN) {
            this.zzKK = true;
            if (this.zzKQ != null) {
                this.zzKQ.cancel();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a8, code lost:
        r2 = r21.zzKQ.zza(r21.zzKH, r21.zzKI);
        r21.zzKM.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c1, code lost:
        if (r2.zzKz != 0) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c3, code lost:
        com.google.android.gms.internal.zzpe.zzbc("Adapter succeeded.");
        r21.zzsr.zzg("mediation_network_succeed", r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00d5, code lost:
        if (r15.isEmpty() != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00d7, code lost:
        r21.zzsr.zzg("mediation_networks_fail", android.text.TextUtils.join(",", r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e6, code lost:
        r21.zzsr.zza(r19, "mls");
        r21.zzsr.zza(r16, "ttm");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x010d, code lost:
        r15.add(r4);
        r21.zzsr.zza(r19, "mlf");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0123, code lost:
        if (r2.zzKB == null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0125, code lost:
        com.google.android.gms.internal.zzpi.zzWR.post(new com.google.android.gms.internal.zzjq.C12941(r21));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        return r2;
     */
    public zzjn zzd(List<zzji> list) {
        zzpe.zzbc("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzgd zzfw = this.zzsr.zzfw();
        for (zzji next : list) {
            String valueOf = String.valueOf(next.zzJH);
            zzpe.zzbd(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            Iterator<String> it = next.zzJI.iterator();
            while (true) {
                if (it.hasNext()) {
                    String next2 = it.next();
                    zzgd zzfw2 = this.zzsr.zzfw();
                    synchronized (this.zzrN) {
                        if (this.zzKK) {
                            zzjn zzjn = new zzjn(-1);
                            return zzjn;
                        }
                        this.zzKQ = new zzjm(this.mContext, next2, this.zzsD, this.zzKq, next, this.zzKG.zzRd, this.zzKG.zzvj, this.zzKG.zzvf, this.zzvW, this.zzKs, this.zzKG.zzvx, this.zzKG.zzvB);
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.zzsr.zzg("mediation_networks_fail", TextUtils.join(",", arrayList));
        }
        return new zzjn(1);
    }

    public List<zzjn> zzgB() {
        return this.zzKM;
    }
}
