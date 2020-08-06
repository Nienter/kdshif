package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzgz;
import java.util.ArrayList;
import java.util.List;

@zzmb
public class zzhg extends NativeContentAd {
    private final List<NativeAd.Image> zzGT = new ArrayList();
    private final zzhf zzGV;
    private final zzha zzGW;

    public zzhg(zzhf zzhf) {
        zzha zzha;
        this.zzGV = zzhf;
        try {
            List<Object> images = this.zzGV.getImages();
            if (images != null) {
                for (Object zze : images) {
                    zzgz zze2 = zze(zze);
                    if (zze2 != null) {
                        this.zzGT.add(new zzha(zze2));
                    }
                }
            }
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get image.", e);
        }
        try {
            zzgz zzfQ = this.zzGV.zzfQ();
            if (zzfQ != null) {
                zzha = new zzha(zzfQ);
                this.zzGW = zzha;
            }
        } catch (RemoteException e2) {
            zzpy.zzb("Failed to get icon.", e2);
        }
        zzha = null;
        this.zzGW = zzha;
    }

    public void destroy() {
        try {
            this.zzGV.destroy();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to destroy", e);
        }
    }

    public CharSequence getAdvertiser() {
        try {
            return this.zzGV.getAdvertiser();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get attribution.", e);
            return null;
        }
    }

    public CharSequence getBody() {
        try {
            return this.zzGV.getBody();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get body.", e);
            return null;
        }
    }

    public CharSequence getCallToAction() {
        try {
            return this.zzGV.getCallToAction();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public Bundle getExtras() {
        try {
            return this.zzGV.getExtras();
        } catch (RemoteException e) {
            zzpy.zzc("Failed to get extras", e);
            return null;
        }
    }

    public CharSequence getHeadline() {
        try {
            return this.zzGV.getHeadline();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public List<NativeAd.Image> getImages() {
        return this.zzGT;
    }

    public NativeAd.Image getLogo() {
        return this.zzGW;
    }

    /* access modifiers changed from: package-private */
    public zzgz zze(Object obj) {
        if (obj instanceof IBinder) {
            return zzgz.zza.zzB((IBinder) obj);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzfM */
    public zzd zzbv() {
        try {
            return this.zzGV.zzfM();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}
