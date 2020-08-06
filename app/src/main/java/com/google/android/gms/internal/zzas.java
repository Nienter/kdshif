package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzaf;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzas extends zzat {
    private static final String TAG = zzas.class.getSimpleName();
    private AdvertisingIdClient.Info zzpU;

    protected zzas(Context context) {
        super(context, "");
    }

    public static zzas zzc(Context context) {
        zza(context, true);
        return new zzas(context);
    }

    /* access modifiers changed from: protected */
    public zzaf.zza zza(Context context, View view) {
        return null;
    }

    public String zza(String str, String str2) {
        return zzan.zza(str, str2, true);
    }

    public void zza(AdvertisingIdClient.Info info) {
        this.zzpU = info;
    }

    /* access modifiers changed from: protected */
    public void zza(zzbc zzbc, zzaf.zza zza, zzad.zza zza2) {
        if (!zzbc.zzaO()) {
            zza(zzb(zzbc, zza, zza2));
        } else if (this.zzpU != null) {
            String id = this.zzpU.getId();
            if (!TextUtils.isEmpty(id)) {
                zza.zzbV = zzbe.zzr(id);
                zza.zzbW = 5;
                zza.zzbX = Boolean.valueOf(this.zzpU.isLimitAdTrackingEnabled());
            }
            this.zzpU = null;
        }
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zzb(zzbc zzbc, zzaf.zza zza, zzad.zza zza2) {
        ArrayList arrayList = new ArrayList();
        if (zzbc.zzaJ() == null) {
            return arrayList;
        }
        zzbc zzbc2 = zzbc;
        arrayList.add(new zzbm(zzbc2, zzay.zzaj(), zzay.zzak(), zza, zzbc.zzT(), 24));
        return arrayList;
    }
}
