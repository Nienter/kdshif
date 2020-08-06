package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzjx;
import java.util.ArrayList;
import java.util.List;

@zzmb
public class zzkc extends zzjx.zza {
    private final NativeContentAdMapper zzKZ;

    public zzkc(NativeContentAdMapper nativeContentAdMapper) {
        this.zzKZ = nativeContentAdMapper;
    }

    public String getAdvertiser() {
        return this.zzKZ.getAdvertiser();
    }

    public String getBody() {
        return this.zzKZ.getBody();
    }

    public String getCallToAction() {
        return this.zzKZ.getCallToAction();
    }

    public Bundle getExtras() {
        return this.zzKZ.getExtras();
    }

    public String getHeadline() {
        return this.zzKZ.getHeadline();
    }

    public List getImages() {
        List<NativeAd.Image> images = this.zzKZ.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeAd.Image next : images) {
            arrayList.add(new zzgo(next.getDrawable(), next.getUri(), next.getScale()));
        }
        return arrayList;
    }

    public boolean getOverrideClickHandling() {
        return this.zzKZ.getOverrideClickHandling();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzKZ.getOverrideImpressionRecording();
    }

    public void recordImpression() {
        this.zzKZ.recordImpression();
    }

    public zzgz zzfQ() {
        NativeAd.Image logo = this.zzKZ.getLogo();
        if (logo != null) {
            return new zzgo(logo.getDrawable(), logo.getUri(), logo.getScale());
        }
        return null;
    }

    public void zzk(zzd zzd) {
        this.zzKZ.handleClick((View) zze.zzE(zzd));
    }

    public void zzl(zzd zzd) {
        this.zzKZ.trackView((View) zze.zzE(zzd));
    }

    public void zzm(zzd zzd) {
        this.zzKZ.untrackView((View) zze.zzE(zzd));
    }
}
