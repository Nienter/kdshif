package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzaf;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzau extends zzat {
    private static final String TAG = zzau.class.getSimpleName();

    protected zzau(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public static zzau zza(String str, Context context, boolean z) {
        zza(context, z);
        return new zzau(context, str, z);
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zzb(zzbc zzbc, zzaf.zza zza, zzad.zza zza2) {
        if (zzbc.zzaJ() == null || !this.zzpX) {
            return super.zzb(zzbc, zza, zza2);
        }
        int zzT = zzbc.zzT();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.zzb(zzbc, zza, zza2));
        arrayList.add(new zzbm(zzbc, zzay.zzaj(), zzay.zzak(), zza, zzT, 24));
        return arrayList;
    }
}
