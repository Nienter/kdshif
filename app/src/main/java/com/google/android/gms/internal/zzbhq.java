package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.zzbhj;

public class zzbhq extends zzbhc<zzbhi> {
    private final zzbhr zzbNz;

    public zzbhq(Context context, zzbhr zzbhr) {
        super(context, "TextNativeHandle");
        this.zzbNz = zzbhr;
        zzSq();
    }

    /* access modifiers changed from: protected */
    public void zzSn() {
        ((zzbhi) zzSq()).zzSu();
    }

    public zzbhk[] zza(Bitmap bitmap, zzbhd zzbhd, zzbhm zzbhm) {
        if (!isOperational()) {
            return new zzbhk[0];
        }
        try {
            return ((zzbhi) zzSq()).zza(zze.zzA(bitmap), zzbhd, zzbhm);
        } catch (RemoteException e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new zzbhk[0];
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzd */
    public zzbhi zzb(DynamiteModule dynamiteModule, Context context) {
        return zzbhj.zza.zzfl(dynamiteModule.zzdX("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator")).zza(zze.zzA(context), this.zzbNz);
    }
}
