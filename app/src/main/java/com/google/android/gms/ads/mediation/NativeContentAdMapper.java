package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

public abstract class NativeContentAdMapper extends NativeAdMapper {
    private String zzFS;
    private List<NativeAd.Image> zzFT;
    private String zzFU;
    private String zzFW;
    private String zzGf;
    private NativeAd.Image zzZP;

    public final String getAdvertiser() {
        return this.zzGf;
    }

    public final String getBody() {
        return this.zzFU;
    }

    public final String getCallToAction() {
        return this.zzFW;
    }

    public final String getHeadline() {
        return this.zzFS;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzFT;
    }

    public final NativeAd.Image getLogo() {
        return this.zzZP;
    }

    public final void setAdvertiser(String str) {
        this.zzGf = str;
    }

    public final void setBody(String str) {
        this.zzFU = str;
    }

    public final void setCallToAction(String str) {
        this.zzFW = str;
    }

    public final void setHeadline(String str) {
        this.zzFS = str;
    }

    public final void setImages(List<NativeAd.Image> list) {
        this.zzFT = list;
    }

    public final void setLogo(NativeAd.Image image) {
        this.zzZP = image;
    }
}
