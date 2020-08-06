package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzob;
import com.google.android.gms.internal.zzov;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;
import p005b.p006a.p007a.p008a.p009a.p014d.EventsFilesManager;

@zzmb
public class zzog extends zzpd implements zzof {
    private final Context mContext;
    private final zzov.zza zzPo;
    private final long zzUP;
    private final ArrayList<Future> zzVa;
    private final ArrayList<String> zzVb;
    private final HashMap<String, zzoa> zzVc;
    private final List<zzob> zzVd;
    private final HashSet<String> zzVe;
    /* access modifiers changed from: private */
    public final zznp zzVf;
    private final Object zzrN;

    public zzog(Context context, zzov.zza zza, zznp zznp) {
        this(context, zza, zznp, zzfx.zzCs.get().longValue());
    }

    zzog(Context context, zzov.zza zza, zznp zznp, long j) {
        this.zzVa = new ArrayList<>();
        this.zzVb = new ArrayList<>();
        this.zzVc = new HashMap<>();
        this.zzVd = new ArrayList();
        this.zzVe = new HashSet<>();
        this.zzrN = new Object();
        this.mContext = context;
        this.zzPo = zza;
        this.zzVf = zznp;
        this.zzUP = j;
    }

    private static int zzR(int i) {
        switch (i) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 0;
            case 7:
                return 3;
            default:
                return 6;
        }
    }

    private zzov zza(int i, @Nullable String str, @Nullable zzji zzji) {
        return new zzov(this.zzPo.zzSF.zzRd, null, this.zzPo.zzVB.zzJY, i, this.zzPo.zzVB.zzJZ, this.zzPo.zzVB.zzRM, this.zzPo.zzVB.orientation, this.zzPo.zzVB.zzKe, this.zzPo.zzSF.zzRg, this.zzPo.zzVB.zzRK, zzji, null, str, this.zzPo.zzVr, null, this.zzPo.zzVB.zzRL, this.zzPo.zzvj, this.zzPo.zzVB.zzRJ, this.zzPo.zzVv, this.zzPo.zzVB.zzRO, this.zzPo.zzVB.zzRP, this.zzPo.zzVp, null, this.zzPo.zzVB.zzRZ, this.zzPo.zzVB.zzSa, this.zzPo.zzVB.zzSb, this.zzPo.zzVB.zzSc, this.zzPo.zzVB.zzSd, zzjv(), this.zzPo.zzVB.zzKb, this.zzPo.zzVB.zzSg);
    }

    private zzov zza(String str, zzji zzji) {
        return zza(-2, str, zzji);
    }

    private static String zza(zzob zzob) {
        String str = zzob.zzJJ;
        int zzR = zzR(zzob.errorCode);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(zzR).append(".").append(zzob.zzKF).toString();
    }

    private void zza(String str, String str2, zzji zzji) {
        synchronized (this.zzrN) {
            zzoh zzaM = this.zzVf.zzaM(str);
            if (zzaM == null || zzaM.zzjx() == null || zzaM.zzjw() == null) {
                this.zzVd.add(new zzob.zza().zzaP(zzji.zzJJ).zzaO(str).zzl(0).zzac(7).zzjt());
                return;
            }
            zzoa zza = zza(str, str2, zzji, zzaM);
            this.zzVa.add((Future) zza.zziw());
            this.zzVb.add(str);
            this.zzVc.put(str, zza);
        }
    }

    private zzov zzju() {
        return zza(3, (String) null, (zzji) null);
    }

    private String zzjv() {
        StringBuilder sb = new StringBuilder("");
        if (this.zzVd == null) {
            return sb.toString();
        }
        for (zzob next : this.zzVd) {
            if (next != null && !TextUtils.isEmpty(next.zzJJ)) {
                sb.append(String.valueOf(zza(next)).concat(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR));
            }
        }
        return sb.substring(0, Math.max(0, sb.length() - 1));
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public zzoa zza(String str, String str2, zzji zzji, zzoh zzoh) {
        return new zzoa(this.mContext, str, str2, zzji, this.zzPo, zzoh, this, this.zzUP);
    }

    public void zza(String str, int i) {
    }

    public void zzaN(String str) {
        synchronized (this.zzrN) {
            this.zzVe.add(str);
        }
    }

    public void zzcm() {
        for (zzji next : this.zzPo.zzVr.zzJW) {
            String str = next.zzJO;
            for (String next2 : next.zzJI) {
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(next2) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(next2)) {
                    try {
                        next2 = new JSONObject(str).getString("class_name");
                    } catch (JSONException e) {
                        zzpe.zzb("Unable to determine custom event class name, skipping...", e);
                    }
                }
                zza(next2, str, next);
            }
        }
        int i = 0;
        while (i < this.zzVa.size()) {
            try {
                this.zzVa.get(i).get();
                synchronized (this.zzrN) {
                    String str2 = this.zzVb.get(i);
                    if (!TextUtils.isEmpty(str2)) {
                        zzoa zzoa = this.zzVc.get(str2);
                        if (zzoa != null) {
                            this.zzVd.add(zzoa.zzjq());
                        }
                    }
                }
                synchronized (this.zzrN) {
                    if (this.zzVe.contains(this.zzVb.get(i))) {
                        String str3 = this.zzVb.get(i);
                        final zzov zza = zza(str3, this.zzVc.get(str3) != null ? this.zzVc.get(str3).zzjr() : null);
                        zzpx.zzXU.post(new Runnable() {
                            public void run() {
                                zzog.this.zzVf.zzb(zza);
                            }
                        });
                        return;
                    }
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                synchronized (this.zzrN) {
                    String str4 = this.zzVb.get(i);
                    if (!TextUtils.isEmpty(str4)) {
                        zzoa zzoa2 = this.zzVc.get(str4);
                        if (zzoa2 != null) {
                            this.zzVd.add(zzoa2.zzjq());
                        }
                    }
                }
            } catch (Exception e3) {
                zzpe.zzc("Unable to resolve rewarded adapter.", e3);
                synchronized (this.zzrN) {
                    String str5 = this.zzVb.get(i);
                    if (!TextUtils.isEmpty(str5)) {
                        zzoa zzoa3 = this.zzVc.get(str5);
                        if (zzoa3 != null) {
                            this.zzVd.add(zzoa3.zzjq());
                        }
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                synchronized (this.zzrN) {
                    String str6 = this.zzVb.get(i);
                    if (!TextUtils.isEmpty(str6)) {
                        zzoa zzoa4 = this.zzVc.get(str6);
                        if (zzoa4 != null) {
                            this.zzVd.add(zzoa4.zzjq());
                        }
                    }
                    throw th2;
                }
            }
        }
        final zzov zzju = zzju();
        zzpx.zzXU.post(new Runnable() {
            public void run() {
                zzog.this.zzVf.zzb(zzju);
            }
        });
        return;
        i++;
    }
}
