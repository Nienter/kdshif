package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.internal.zzld;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpd;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzmb
public class zzc extends zzpd implements ServiceConnection {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public zzld zzIO;
    private boolean zzOQ;
    private zzb zzOR;
    private zzh zzOS;
    private List<zzf> zzOT;
    /* access modifiers changed from: private */
    public zzk zzOU;
    private final Object zzrN;

    public zzc(Context context, zzld zzld, zzk zzk) {
        this(context, zzld, zzk, new zzb(context), zzh.zzq(context.getApplicationContext()));
    }

    zzc(Context context, zzld zzld, zzk zzk, zzb zzb, zzh zzh) {
        this.zzrN = new Object();
        this.zzOQ = false;
        this.zzOT = null;
        this.mContext = context;
        this.zzIO = zzld;
        this.zzOU = zzk;
        this.zzOR = zzb;
        this.zzOS = zzh;
        this.zzOT = this.zzOS.zzg(10);
    }

    private void zze(long j) {
        do {
            if (!zzf(j)) {
                zzpe.m2431v("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.zzOQ);
    }

    private boolean zzf(long j) {
        long elapsedRealtime = 60000 - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.zzrN.wait(elapsedRealtime);
        } catch (InterruptedException e) {
            zzpe.zzbe("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzrN) {
            this.zzOR.zzV(iBinder);
            zzio();
            this.zzOQ = true;
            this.zzrN.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzpe.zzbd("In-app billing service disconnected.");
        this.zzOR.destroy();
    }

    public void onStop() {
        synchronized (this.zzrN) {
            zza.zzyc().zza(this.mContext, this);
            this.zzOR.destroy();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(final zzf zzf, String str, String str2) {
        final Intent intent = new Intent();
        zzv.zzcX();
        intent.putExtra("RESPONSE_CODE", 0);
        zzv.zzcX();
        intent.putExtra("INAPP_PURCHASE_DATA", str);
        zzv.zzcX();
        intent.putExtra("INAPP_DATA_SIGNATURE", str2);
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                try {
                    if (zzc.this.zzOU.zza(zzf.zzPf, -1, intent)) {
                        zzc.this.zzIO.zza(new zzg(zzc.this.mContext, zzf.zzPg, true, -1, intent, zzf));
                    } else {
                        zzc.this.zzIO.zza(new zzg(zzc.this.mContext, zzf.zzPg, false, -1, intent, zzf));
                    }
                } catch (RemoteException e) {
                    zzpe.zzbe("Fail to verify and dispatch pending transaction");
                }
            }
        });
    }

    public void zzcm() {
        synchronized (this.zzrN) {
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            zza.zzyc().zza(this.mContext, intent, (ServiceConnection) this, 1);
            zze(SystemClock.elapsedRealtime());
            zza.zzyc().zza(this.mContext, this);
            this.zzOR.destroy();
        }
    }

    /* access modifiers changed from: protected */
    public void zzio() {
        if (!this.zzOT.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (zzf next : this.zzOT) {
                hashMap.put(next.zzPg, next);
            }
            String str = null;
            while (true) {
                Bundle zzm = this.zzOR.zzm(this.mContext.getPackageName(), str);
                if (zzm == null || zzv.zzcX().zzd(zzm) != 0) {
                    break;
                }
                ArrayList<String> stringArrayList = zzm.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = zzm.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = zzm.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                String string = zzm.getString("INAPP_CONTINUATION_TOKEN");
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= stringArrayList.size()) {
                        break;
                    }
                    if (hashMap.containsKey(stringArrayList.get(i2))) {
                        String str2 = stringArrayList.get(i2);
                        String str3 = stringArrayList2.get(i2);
                        String str4 = stringArrayList3.get(i2);
                        zzf zzf = (zzf) hashMap.get(str2);
                        if (zzf.zzPf.equals(zzv.zzcX().zzaD(str3))) {
                            zza(zzf, str3, str4);
                            hashMap.remove(str2);
                        }
                    }
                    i = i2 + 1;
                }
                if (string == null || hashMap.isEmpty()) {
                    break;
                }
                str = string;
            }
            for (String str5 : hashMap.keySet()) {
                this.zzOS.zza((zzf) hashMap.get(str5));
            }
        }
    }
}
