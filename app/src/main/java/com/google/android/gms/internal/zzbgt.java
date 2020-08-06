package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.zzbgz;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

public class zzbgt extends zzbhc<zzbgy> {
    private final zzbgw zzbNc;

    public zzbgt(Context context, zzbgw zzbgw) {
        super(context, "FaceNativeHandle");
        this.zzbNc = zzbgw;
        zzSq();
    }

    private Face zza(zzbgu zzbgu) {
        return new Face(zzbgu.f1723id, new PointF(zzbgu.centerX, zzbgu.centerY), zzbgu.width, zzbgu.height, zzbgu.zzbNd, zzbgu.zzbNe, zzb(zzbgu), zzbgu.zzbNg, zzbgu.zzbNh, zzbgu.zzbNi);
    }

    private Landmark zza(zzbha zzbha) {
        return new Landmark(new PointF(zzbha.f1724x, zzbha.f1725y), zzbha.type);
    }

    private Landmark[] zzb(zzbgu zzbgu) {
        zzbha[] zzbhaArr = zzbgu.zzbNf;
        if (zzbhaArr == null) {
            return new Landmark[0];
        }
        Landmark[] landmarkArr = new Landmark[zzbhaArr.length];
        for (int i = 0; i < zzbhaArr.length; i++) {
            landmarkArr[i] = zza(zzbhaArr[i]);
        }
        return landmarkArr;
    }

    /* access modifiers changed from: protected */
    public void zzSn() {
        ((zzbgy) zzSq()).zzSo();
    }

    public Face[] zzb(ByteBuffer byteBuffer, zzbhd zzbhd) {
        if (!isOperational()) {
            return new Face[0];
        }
        try {
            zzbgu[] zzc = ((zzbgy) zzSq()).zzc(zze.zzA(byteBuffer), zzbhd);
            Face[] faceArr = new Face[zzc.length];
            for (int i = 0; i < zzc.length; i++) {
                faceArr[i] = zza(zzc[i]);
            }
            return faceArr;
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new Face[0];
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzc */
    public zzbgy zzb(DynamiteModule dynamiteModule, Context context) {
        return zzbgz.zza.zzfj(dynamiteModule.zzdX("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator")).zza(zze.zzA(context), this.zzbNc);
    }

    public boolean zznw(int i) {
        if (!isOperational()) {
            return false;
        }
        try {
            return ((zzbgy) zzSq()).zznw(i);
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return false;
        }
    }
}
