package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.internal.zzy;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;

public final class zzaj extends zzg<zzy> {
    private static final zzaj zzaFl = new zzaj();

    private zzaj() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zzd(Context context, int i, int i2) {
        return zzaFl.zze(context, i, i2);
    }

    private View zze(Context context, int i, int i2) {
        try {
            zzah zzah = new zzah(i, i2, null);
            return (View) zze.zzE(((zzy) zzaT(context)).zza(zze.zzA(context), zzah));
        } catch (Exception e) {
            throw new zzg.zza(new StringBuilder(64).append("Could not get button with size ").append(i).append(" and color ").append(i2).toString(), e);
        }
    }

    /* renamed from: zzby */
    public zzy zzc(IBinder iBinder) {
        return zzy.zza.zzbx(iBinder);
    }
}
