package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.zzbgs;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

public class zzbgq extends zzbhc<zzbgr> {
    private final zzbgo zzbMK;

    public zzbgq(Context context, zzbgo zzbgo) {
        super(context, "BarcodeNativeHandle");
        this.zzbMK = zzbgo;
        zzSq();
    }

    /* access modifiers changed from: protected */
    public void zzSn() {
        ((zzbgr) zzSq()).zzSo();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public zzbgr zzb(DynamiteModule dynamiteModule, Context context) {
        return zzbgs.zza.zzfh(dynamiteModule.zzdX("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator")).zza(zze.zzA(context), this.zzbMK);
    }

    public Barcode[] zza(Bitmap bitmap, zzbhd zzbhd) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzbgr) zzSq()).zzb(zze.zzA(bitmap), zzbhd);
        } catch (RemoteException e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    public Barcode[] zza(ByteBuffer byteBuffer, zzbhd zzbhd) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzbgr) zzSq()).zza(zze.zzA(byteBuffer), zzbhd);
        } catch (RemoteException e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }
}
