package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzky;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzmb
public class zzd extends zzky.zza {
    private Context mContext;
    private String zzOX;
    private ArrayList<String> zzOY;
    private String zzvU;

    public zzd(String str, ArrayList<String> arrayList, Context context, String str2) {
        this.zzOX = str;
        this.zzOY = arrayList;
        this.zzvU = str2;
        this.mContext = context;
    }

    public String getProductId() {
        return this.zzOX;
    }

    public void recordPlayBillingResolution(int i) {
        if (i == 0) {
            zziq();
        }
        Map<String, String> zzip = zzip();
        zzip.put("google_play_status", String.valueOf(i));
        zzip.put("sku", this.zzOX);
        zzip.put("status", String.valueOf(zzN(i)));
        LinkedList linkedList = new LinkedList();
        Iterator<String> it = this.zzOY.iterator();
        while (it.hasNext()) {
            linkedList.add(zzv.zzcJ().zzb(it.next(), zzip));
        }
        zzv.zzcJ().zza(this.mContext, this.zzvU, (List<String>) linkedList);
    }

    public void recordResolution(int i) {
        if (i == 1) {
            zziq();
        }
        Map<String, String> zzip = zzip();
        zzip.put("status", String.valueOf(i));
        zzip.put("sku", this.zzOX);
        LinkedList linkedList = new LinkedList();
        Iterator<String> it = this.zzOY.iterator();
        while (it.hasNext()) {
            linkedList.add(zzv.zzcJ().zzb(it.next(), zzip));
        }
        zzv.zzcJ().zza(this.mContext, this.zzvU, (List<String>) linkedList);
    }

    /* access modifiers changed from: protected */
    public int zzN(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        return i == 4 ? 3 : 0;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zzip() {
        String packageName = this.mContext.getPackageName();
        String str = "";
        try {
            str = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            zzpe.zzc("Error to retrieve app version", e);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - zzv.zzcN().zzjM().zzkd();
        HashMap hashMap = new HashMap();
        hashMap.put("sessionid", zzv.zzcN().getSessionId());
        hashMap.put("appid", packageName);
        hashMap.put("osversion", String.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("sdkversion", this.zzvU);
        hashMap.put("appversion", str);
        hashMap.put("timestamp", String.valueOf(elapsedRealtime));
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void zziq() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", new Class[]{Context.class, String.class, String.class, Boolean.TYPE}).invoke(null, new Object[]{this.mContext, this.zzOX, "", true});
        } catch (ClassNotFoundException e) {
            zzpe.zzbe("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (NoSuchMethodException e2) {
            zzpe.zzbe("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (Exception e3) {
            zzpe.zzc("Fail to report a conversion.", e3);
        }
    }
}
