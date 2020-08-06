package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import java.util.List;

@zzmb
public class zzhi implements NativeCustomTemplateAd {
    private final zzhh zzGX;

    public zzhi(zzhh zzhh) {
        this.zzGX = zzhh;
    }

    public List<String> getAvailableAssetNames() {
        try {
            return this.zzGX.getAvailableAssetNames();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get available asset names.", e);
            return null;
        }
    }

    public String getCustomTemplateId() {
        try {
            return this.zzGX.getCustomTemplateId();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get custom template id.", e);
            return null;
        }
    }

    public NativeAd.Image getImage(String str) {
        try {
            zzgz zzZ = this.zzGX.zzZ(str);
            if (zzZ != null) {
                return new zzha(zzZ);
            }
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get image.", e);
        }
        return null;
    }

    public CharSequence getText(String str) {
        try {
            return this.zzGX.zzY(str);
        } catch (RemoteException e) {
            zzpy.zzb("Failed to get string.", e);
            return null;
        }
    }

    public void performClick(String str) {
        try {
            this.zzGX.performClick(str);
        } catch (RemoteException e) {
            zzpy.zzb("Failed to perform click.", e);
        }
    }

    public void recordImpression() {
        try {
            this.zzGX.recordImpression();
        } catch (RemoteException e) {
            zzpy.zzb("Failed to record impression.", e);
        }
    }
}
