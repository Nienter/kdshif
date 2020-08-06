package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;

@zzmb
public class zza {
    public boolean zza(Context context, Intent intent, zzq zzq) {
        try {
            String valueOf = String.valueOf(intent.toURI());
            zzpe.m2431v(valueOf.length() != 0 ? "Launching an intent: ".concat(valueOf) : new String("Launching an intent: "));
            zzv.zzcJ().zzb(context, intent);
            if (zzq != null) {
                zzq.zzbE();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            zzpe.zzbe(e.getMessage());
            return false;
        }
    }

    public boolean zza(Context context, zzc zzc, zzq zzq) {
        int i;
        if (zzc == null) {
            zzpe.zzbe("No intent data for launcher overlay.");
            return false;
        } else if (zzc.intent != null) {
            return zza(context, zzc.intent, zzq);
        } else {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(zzc.url)) {
                zzpe.zzbe("Open GMSG did not contain a URL.");
                return false;
            }
            if (!TextUtils.isEmpty(zzc.mimeType)) {
                intent.setDataAndType(Uri.parse(zzc.url), zzc.mimeType);
            } else {
                intent.setData(Uri.parse(zzc.url));
            }
            intent.setAction("android.intent.action.VIEW");
            if (!TextUtils.isEmpty(zzc.packageName)) {
                intent.setPackage(zzc.packageName);
            }
            if (!TextUtils.isEmpty(zzc.zzLZ)) {
                String[] split = zzc.zzLZ.split("/", 2);
                if (split.length < 2) {
                    String valueOf = String.valueOf(zzc.zzLZ);
                    zzpe.zzbe(valueOf.length() != 0 ? "Could not parse component name from open GMSG: ".concat(valueOf) : new String("Could not parse component name from open GMSG: "));
                    return false;
                }
                intent.setClassName(split[0], split[1]);
            }
            String str = zzc.zzMa;
            if (!TextUtils.isEmpty(str)) {
                try {
                    i = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    zzpe.zzbe("Could not parse intent flags.");
                    i = 0;
                }
                intent.addFlags(i);
            }
            return zza(context, intent, zzq);
        }
    }
}
