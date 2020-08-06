package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzjw;
import java.util.ArrayList;
import java.util.List;

@zzmb
public class zzkb extends zzjw.zza {
    private final NativeAppInstallAdMapper zzKY;

    public zzkb(NativeAppInstallAdMapper nativeAppInstallAdMapper) {
        this.zzKY = nativeAppInstallAdMapper;
    }

    public String getBody() {
        return this.zzKY.getBody();
    }

    public String getCallToAction() {
        return this.zzKY.getCallToAction();
    }

    public Bundle getExtras() {
        return this.zzKY.getExtras();
    }

    public String getHeadline() {
        return this.zzKY.getHeadline();
    }

    public List getImages() {
        List<NativeAd.Image> images = this.zzKY.getImages();
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
        return this.zzKY.getOverrideClickHandling();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzKY.getOverrideImpressionRecording();
    }

    public String getPrice() {
        return this.zzKY.getPrice();
    }

    public double getStarRating() {
        return this.zzKY.getStarRating();
    }

    public String getStore() {
        return this.zzKY.getStore();
    }

    public void recordImpression() {
        this.zzKY.recordImpression();
    }

    public zzew zzbG() {
        if (this.zzKY.getVideoController() != null) {
            return this.zzKY.getVideoController().zzbt();
        }
        return null;
    }

    public zzgz zzfL() {
        NativeAd.Image icon = this.zzKY.getIcon();
        if (icon != null) {
            return new zzgo(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        return null;
    }

    public void zzk(zzd zzd) {
        this.zzKY.handleClick((View) zze.zzE(zzd));
    }

    public void zzl(zzd zzd) {
        this.zzKY.trackView((View) zze.zzE(zzd));
    }

    public void zzm(zzd zzd) {
        this.zzKY.untrackView((View) zze.zzE(zzd));
    }
}
